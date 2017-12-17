package com.DragonFire.entity.custom;

import com.DragonFire.block.tree.DFWoodType;
import com.DragonFire.item.DFItems;
import com.google.common.collect.Lists;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityWaterMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.network.play.client.CPacketSteerBoat;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityCustomBoat extends Entity {
    private static final DataParameter<Integer> TIME_SINCE_HIT = EntityDataManager
            .<Integer>createKey(EntityCustomBoat.class, DataSerializers.VARINT);
    private static final DataParameter<Integer> FORWARD_DIRECTION = EntityDataManager
            .<Integer>createKey(EntityCustomBoat.class, DataSerializers.VARINT);
    private static final DataParameter<Float> DAMAGE_TAKEN = EntityDataManager.<Float>createKey(EntityCustomBoat.class,
            DataSerializers.FLOAT);
    private static final DataParameter<Integer> BOAT_TYPE = EntityDataManager.<Integer>createKey(EntityCustomBoat.class,
            DataSerializers.VARINT);
    @SuppressWarnings("unchecked")
    private static final DataParameter<Boolean>[] DATA_ID_PADDLE = new DataParameter[] {
            EntityDataManager.createKey(EntityCustomBoat.class, DataSerializers.BOOLEAN),
            EntityDataManager.createKey(EntityCustomBoat.class, DataSerializers.BOOLEAN) };
    private final float[] paddlePositions;
    /** How much of current speed to retain. Value zero to one. */
    private float momentum;
    private float outOfControlTicks;
    private float deltaRotation;
    private int lerpSteps;
    private double lerpX;
    private double lerpY;
    private double lerpZ;
    private double lerpYaw;
    private double lerpPitch;
    private boolean leftInputDown;
    private boolean rightInputDown;
    private boolean forwardInputDown;
    private boolean backInputDown;
    private double waterLevel;
    /**
     * How much the boat should glide given the slippery blocks it's currently
     * gliding over. Halved every tick.
     */
    private float boatGlide;
    private EntityCustomBoat.Status status;
    private EntityCustomBoat.Status previousStatus;
    private double lastYd;

    public EntityCustomBoat(World worldIn) {
        super(worldIn);
        this.paddlePositions = new float[2];
        this.preventEntitySpawning = true;
        this.setSize(1.375F, 0.5625F);
    }

    public EntityCustomBoat(World worldIn, double x, double y, double z) {
        this(worldIn);
        this.setPosition(x, y, z);
        this.motionX = 0.0D;
        this.motionY = 0.0D;
        this.motionZ = 0.0D;
        this.prevPosX = x;
        this.prevPosY = y;
        this.prevPosZ = z;
    }

    /**
     * returns if this entity triggers Block.onEntityWalking on the blocks they walk
     * on. used for spiders and wolves to prevent them from trampling crops
     */
    protected boolean canTriggerWalking() {
        return false;
    }

    protected void entityInit() {
        this.dataManager.register(TIME_SINCE_HIT, Integer.valueOf(0));
        this.dataManager.register(FORWARD_DIRECTION, Integer.valueOf(1));
        this.dataManager.register(DAMAGE_TAKEN, Float.valueOf(0.0F));
        this.dataManager.register(BOAT_TYPE, Integer.valueOf(EntityCustomBoat.Type.CHERRY.ordinal()));

        for (DataParameter<Boolean> dataparameter : DATA_ID_PADDLE) {
            this.dataManager.register(dataparameter, Boolean.valueOf(false));
        }
    }

    /**
     * Returns a boundingBox used to collide the entity with other entities and
     * blocks. This enables the entity to be pushable on contact, like boats or
     * minecarts.
     */
    @Nullable
    public AxisAlignedBB getCollisionBox(Entity entityIn) {
        return entityIn.canBePushed() ? entityIn.getEntityBoundingBox() : null;
    }

    /**
     * Returns the <b>solid</b> collision bounding box for this entity. Used to make
     * (e.g.) boats solid. Return null if this entity is not solid.
     * 
     * For general purposes, use {@link #width} and {@link #height}.
     * 
     * @see getEntityBoundingBox
     */
    @Nullable
    public AxisAlignedBB getCollisionBoundingBox() {
        return this.getEntityBoundingBox();
    }

    /**
     * Returns true if this entity should push and be pushed by other entities when
     * colliding.
     */
    public boolean canBePushed() {
        return true;
    }

    /**
     * Returns the Y offset from the entity's position for any entity riding this
     * one.
     */
    public double getMountedYOffset() {
        return -0.1D;
    }

    /**
     * Called when the entity is attacked.
     */
    public boolean attackEntityFrom(DamageSource source, float amount) {
        if (this.isEntityInvulnerable(source)) {
            return false;
        } else if (!this.world.isRemote && !this.isDead) {
            if (source instanceof EntityDamageSourceIndirect && source.getTrueSource() != null
                    && this.isPassenger(source.getTrueSource())) {
                return false;
            } else {
                this.setForwardDirection(-this.getForwardDirection());
                this.setTimeSinceHit(10);
                this.setDamageTaken(this.getDamageTaken() + amount * 10.0F);
                this.markVelocityChanged();
                boolean flag = source.getTrueSource() instanceof EntityPlayer
                        && ((EntityPlayer) source.getTrueSource()).capabilities.isCreativeMode;

                if (flag || this.getDamageTaken() > 40.0F) {
                    if (!flag && this.world.getGameRules().getBoolean("doEntityDrops")) {
                        this.dropItemWithOffset(this.getItemBoat(), 1, 0.0F);
                    }

                    this.setDead();
                }

                return true;
            }
        } else {
            return true;
        }
    }

    /**
     * Applies a velocity to the entities, to push them away from eachother.
     */
    public void applyEntityCollision(Entity entityIn) {
        if (entityIn instanceof EntityCustomBoat) {
            if (entityIn.getEntityBoundingBox().minY < this.getEntityBoundingBox().maxY) {
                super.applyEntityCollision(entityIn);
            }
        } else if (entityIn.getEntityBoundingBox().minY <= this.getEntityBoundingBox().minY) {
            super.applyEntityCollision(entityIn);
        }
    }

    public Item getItemBoat() {
        switch (this.getBoatType()) {
            case CHERRY:
            default:
                return DFItems.CHERRY_BOAT;
        }
    }

    /**
     * Setups the entity to do the hurt animation. Only used by packets in
     * multiplayer.
     */
    @SideOnly(Side.CLIENT)
    public void performHurtAnimation() {
        this.setForwardDirection(-this.getForwardDirection());
        this.setTimeSinceHit(10);
        this.setDamageTaken(this.getDamageTaken() * 11.0F);
    }

    /**
     * Returns true if other Entities should be prevented from moving through this
     * Entity.
     */
    public boolean canBeCollidedWith() {
        return !this.isDead;
    }

    /**
     * Set the position and rotation values directly without any clamping.
     */
    @SideOnly(Side.CLIENT)
    public void setPositionAndRotationDirect(double x, double y, double z, float yaw, float pitch,
            int posRotationIncrements, boolean teleport) {
        this.lerpX = x;
        this.lerpY = y;
        this.lerpZ = z;
        this.lerpYaw = yaw;
        this.lerpPitch = pitch;
        this.lerpSteps = 10;
    }

    /**
     * Gets the horizontal facing direction of this Entity, adjusted to take
     * specially-treated entity types into account.
     */
    public EnumFacing getAdjustedHorizontalFacing() {
        return this.getHorizontalFacing().rotateY();
    }

    /**
     * Called to update the entity's position/logic.
     */
    public void onUpdate() {
        this.previousStatus = this.status;
        this.status = this.getBoatStatus();

        if (this.status != EntityCustomBoat.Status.UNDER_WATER
                && this.status != EntityCustomBoat.Status.UNDER_FLOWING_WATER) {
            this.outOfControlTicks = 0.0F;
        } else {
            ++this.outOfControlTicks;
        }

        if (!this.world.isRemote && this.outOfControlTicks >= 60.0F) {
            this.removePassengers();
        }

        if (this.getTimeSinceHit() > 0) {
            this.setTimeSinceHit(this.getTimeSinceHit() - 1);
        }

        if (this.getDamageTaken() > 0.0F) {
            this.setDamageTaken(this.getDamageTaken() - 1.0F);
        }

        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;
        super.onUpdate();
        this.tickLerp();

        if (this.canPassengerSteer()) {
            if (this.getPassengers().isEmpty() || !(this.getPassengers().get(0) instanceof EntityPlayer)) {
                this.setPaddleState(false, false);
            }

            this.updateMotion();

            if (this.world.isRemote) {
                this.controlBoat();
                this.world.sendPacketToServer(new CPacketSteerBoat(this.getPaddleState(0), this.getPaddleState(1)));
            }

            this.move(MoverType.SELF, this.motionX, this.motionY, this.motionZ);
        } else {
            this.motionX = 0.0D;
            this.motionY = 0.0D;
            this.motionZ = 0.0D;
        }

        for (int i = 0; i <= 1; ++i) {
            if (this.getPaddleState(i)) {
                if (!this.isSilent() && this.paddlePositions[i] % ((float) Math.PI * 2F) <= (Math.PI / 4D)
                        && (this.paddlePositions[i] + 0.39269909262657166D) % (Math.PI * 2D) >= (Math.PI / 4D)) {
                    SoundEvent soundevent = this.getPaddleSound();

                    if (soundevent != null) {
                        Vec3d vec3d = this.getLook(1.0F);
                        double d0 = i == 1 ? -vec3d.z : vec3d.z;
                        double d1 = i == 1 ? vec3d.x : -vec3d.x;
                        this.world.playSound((EntityPlayer) null, this.posX + d0, this.posY, this.posZ + d1, soundevent,
                                this.getSoundCategory(), 1.0F, 0.8F + 0.4F * this.rand.nextFloat());
                    }
                }

                this.paddlePositions[i] = (float) (this.paddlePositions[i] + 0.39269909262657166D);
            } else {
                this.paddlePositions[i] = 0.0F;
            }
        }

        this.doBlockCollisions();
        List<Entity> list = this.world.getEntitiesInAABBexcluding(this,
                this.getEntityBoundingBox().grow(0.20000000298023224D, -0.009999999776482582D, 0.20000000298023224D),
                EntitySelectors.getTeamCollisionPredicate(this));

        if (!list.isEmpty()) {
            boolean flag = !this.world.isRemote && !(this.getControllingPassenger() instanceof EntityPlayer);

            for (int j = 0; j < list.size(); ++j) {
                Entity entity = list.get(j);

                if (!entity.isPassenger(this)) {
                    if (flag && this.getPassengers().size() < 2 && !entity.isRiding() && entity.width < this.width
                            && entity instanceof EntityLivingBase && !(entity instanceof EntityWaterMob)
                            && !(entity instanceof EntityPlayer)) {
                        entity.startRiding(this);
                    } else {
                        this.applyEntityCollision(entity);
                    }
                }
            }
        }
    }

    @Nullable
    protected SoundEvent getPaddleSound() {
        switch (this.getBoatStatus()) {
            case IN_WATER:
            case UNDER_WATER:
            case UNDER_FLOWING_WATER:
                return SoundEvents.ENTITY_BOAT_PADDLE_WATER;
            case ON_LAND:
                return SoundEvents.ENTITY_BOAT_PADDLE_LAND;
            case IN_AIR:
            default:
                return null;
        }
    }

    private void tickLerp() {
        if (this.lerpSteps > 0 && !this.canPassengerSteer()) {
            double d0 = this.posX + (this.lerpX - this.posX) / this.lerpSteps;
            double d1 = this.posY + (this.lerpY - this.posY) / this.lerpSteps;
            double d2 = this.posZ + (this.lerpZ - this.posZ) / this.lerpSteps;
            double d3 = MathHelper.wrapDegrees(this.lerpYaw - this.rotationYaw);
            this.rotationYaw = (float) (this.rotationYaw + d3 / this.lerpSteps);
            this.rotationPitch = (float) (this.rotationPitch + (this.lerpPitch - this.rotationPitch) / this.lerpSteps);
            --this.lerpSteps;
            this.setPosition(d0, d1, d2);
            this.setRotation(this.rotationYaw, this.rotationPitch);
        }
    }

    public void setPaddleState(boolean left, boolean right) {
        this.dataManager.set(DATA_ID_PADDLE[0], Boolean.valueOf(left));
        this.dataManager.set(DATA_ID_PADDLE[1], Boolean.valueOf(right));
    }

    @SideOnly(Side.CLIENT)
    public float getRowingTime(int side, float limbSwing) {
        return this.getPaddleState(side)
                ? (float) MathHelper.clampedLerp(this.paddlePositions[side] - 0.39269909262657166D,
                        this.paddlePositions[side], limbSwing)
                : 0.0F;
    }

    /**
     * Determines whether the boat is in water, gliding on land, or in air
     */
    private EntityCustomBoat.Status getBoatStatus() {
        EntityCustomBoat.Status EntityCustomBoat$status = this.getUnderwaterStatus();

        if (EntityCustomBoat$status != null) {
            this.waterLevel = this.getEntityBoundingBox().maxY;
            return EntityCustomBoat$status;
        } else if (this.checkInWater()) {
            return EntityCustomBoat.Status.IN_WATER;
        } else {
            float f = this.getBoatGlide();

            if (f > 0.0F) {
                this.boatGlide = f;
                return EntityCustomBoat.Status.ON_LAND;
            } else {
                return EntityCustomBoat.Status.IN_AIR;
            }
        }
    }

    public float getWaterLevelAbove() {
        AxisAlignedBB axisalignedbb = this.getEntityBoundingBox();
        int i = MathHelper.floor(axisalignedbb.minX);
        int j = MathHelper.ceil(axisalignedbb.maxX);
        int k = MathHelper.floor(axisalignedbb.maxY);
        int l = MathHelper.ceil(axisalignedbb.maxY - this.lastYd);
        int i1 = MathHelper.floor(axisalignedbb.minZ);
        int j1 = MathHelper.ceil(axisalignedbb.maxZ);
        BlockPos.PooledMutableBlockPos blockpos$pooledmutableblockpos = BlockPos.PooledMutableBlockPos.retain();

        try {
            label108:

            for (int k1 = k; k1 < l; ++k1) {
                float f = 0.0F;
                int l1 = i;

                while (true) {
                    if (l1 >= j) {
                        if (f < 1.0F) {
                            float f2 = blockpos$pooledmutableblockpos.getY() + f;
                            return f2;
                        }

                        break;
                    }

                    for (int i2 = i1; i2 < j1; ++i2) {
                        blockpos$pooledmutableblockpos.setPos(l1, k1, i2);
                        IBlockState iblockstate = this.world.getBlockState(blockpos$pooledmutableblockpos);

                        if (iblockstate.getMaterial() == Material.WATER) {
                            f = Math.max(f, BlockLiquid.getBlockLiquidHeight(iblockstate, this.world,
                                    blockpos$pooledmutableblockpos));
                        }

                        if (f >= 1.0F) {
                            continue label108;
                        }
                    }

                    ++l1;
                }
            }

            float f1 = l + 1;
            return f1;
        } finally {
            blockpos$pooledmutableblockpos.release();
        }
    }

    /**
     * Decides how much the boat should be gliding on the land (based on any
     * slippery blocks)
     */
    public float getBoatGlide() {
        AxisAlignedBB axisalignedbb = this.getEntityBoundingBox();
        AxisAlignedBB axisalignedbb1 = new AxisAlignedBB(axisalignedbb.minX, axisalignedbb.minY - 0.001D,
                axisalignedbb.minZ, axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.maxZ);
        int i = MathHelper.floor(axisalignedbb1.minX) - 1;
        int j = MathHelper.ceil(axisalignedbb1.maxX) + 1;
        int k = MathHelper.floor(axisalignedbb1.minY) - 1;
        int l = MathHelper.ceil(axisalignedbb1.maxY) + 1;
        int i1 = MathHelper.floor(axisalignedbb1.minZ) - 1;
        int j1 = MathHelper.ceil(axisalignedbb1.maxZ) + 1;
        List<AxisAlignedBB> list = Lists.<AxisAlignedBB>newArrayList();
        float f = 0.0F;
        int k1 = 0;
        BlockPos.PooledMutableBlockPos blockpos$pooledmutableblockpos = BlockPos.PooledMutableBlockPos.retain();

        try {
            for (int l1 = i; l1 < j; ++l1) {
                for (int i2 = i1; i2 < j1; ++i2) {
                    int j2 = (l1 != i && l1 != j - 1 ? 0 : 1) + (i2 != i1 && i2 != j1 - 1 ? 0 : 1);

                    if (j2 != 2) {
                        for (int k2 = k; k2 < l; ++k2) {
                            if (j2 <= 0 || k2 != k && k2 != l - 1) {
                                blockpos$pooledmutableblockpos.setPos(l1, k2, i2);
                                IBlockState iblockstate = this.world.getBlockState(blockpos$pooledmutableblockpos);
                                iblockstate.addCollisionBoxToList(this.world, blockpos$pooledmutableblockpos,
                                        axisalignedbb1, list, this, false);

                                if (!list.isEmpty()) {
                                    f += iblockstate.getBlock().getSlipperiness(iblockstate, this.world,
                                            blockpos$pooledmutableblockpos, this);
                                    ++k1;
                                }

                                list.clear();
                            }
                        }
                    }
                }
            }
        } finally {
            blockpos$pooledmutableblockpos.release();
        }

        return f / k1;
    }

    private boolean checkInWater() {
        AxisAlignedBB axisalignedbb = this.getEntityBoundingBox();
        int i = MathHelper.floor(axisalignedbb.minX);
        int j = MathHelper.ceil(axisalignedbb.maxX);
        int k = MathHelper.floor(axisalignedbb.minY);
        int l = MathHelper.ceil(axisalignedbb.minY + 0.001D);
        int i1 = MathHelper.floor(axisalignedbb.minZ);
        int j1 = MathHelper.ceil(axisalignedbb.maxZ);
        boolean flag = false;
        this.waterLevel = Double.MIN_VALUE;
        BlockPos.PooledMutableBlockPos blockpos$pooledmutableblockpos = BlockPos.PooledMutableBlockPos.retain();

        try {
            for (int k1 = i; k1 < j; ++k1) {
                for (int l1 = k; l1 < l; ++l1) {
                    for (int i2 = i1; i2 < j1; ++i2) {
                        blockpos$pooledmutableblockpos.setPos(k1, l1, i2);
                        IBlockState iblockstate = this.world.getBlockState(blockpos$pooledmutableblockpos);

                        if (iblockstate.getMaterial() == Material.WATER) {
                            float f = BlockLiquid.getLiquidHeight(iblockstate, this.world,
                                    blockpos$pooledmutableblockpos);
                            this.waterLevel = Math.max(f, this.waterLevel);
                            flag |= axisalignedbb.minY < f;
                        }
                    }
                }
            }
        } finally {
            blockpos$pooledmutableblockpos.release();
        }

        return flag;
    }

    /**
     * Decides whether the boat is currently underwater.
     */
    @Nullable
    private EntityCustomBoat.Status getUnderwaterStatus() {
        AxisAlignedBB axisalignedbb = this.getEntityBoundingBox();
        double d0 = axisalignedbb.maxY + 0.001D;
        int i = MathHelper.floor(axisalignedbb.minX);
        int j = MathHelper.ceil(axisalignedbb.maxX);
        int k = MathHelper.floor(axisalignedbb.maxY);
        int l = MathHelper.ceil(d0);
        int i1 = MathHelper.floor(axisalignedbb.minZ);
        int j1 = MathHelper.ceil(axisalignedbb.maxZ);
        boolean flag = false;
        BlockPos.PooledMutableBlockPos blockpos$pooledmutableblockpos = BlockPos.PooledMutableBlockPos.retain();

        try {
            for (int k1 = i; k1 < j; ++k1) {
                for (int l1 = k; l1 < l; ++l1) {
                    for (int i2 = i1; i2 < j1; ++i2) {
                        blockpos$pooledmutableblockpos.setPos(k1, l1, i2);
                        IBlockState iblockstate = this.world.getBlockState(blockpos$pooledmutableblockpos);

                        if (iblockstate.getMaterial() == Material.WATER && d0 < BlockLiquid.getLiquidHeight(iblockstate,
                                this.world, blockpos$pooledmutableblockpos)) {
                            if (iblockstate.getValue(BlockLiquid.LEVEL).intValue() != 0) {
                                EntityCustomBoat.Status EntityCustomBoat$status = EntityCustomBoat.Status.UNDER_FLOWING_WATER;
                                return EntityCustomBoat$status;
                            }

                            flag = true;
                        }
                    }
                }
            }
        } finally {
            blockpos$pooledmutableblockpos.release();
        }

        return flag ? EntityCustomBoat.Status.UNDER_WATER : null;
    }

    /**
     * Update the boat's speed, based on momentum.
     */
    private void updateMotion() {
        double d1 = this.hasNoGravity() ? 0.0D : -0.03999999910593033D;
        double d2 = 0.0D;
        this.momentum = 0.05F;

        if (this.previousStatus == EntityCustomBoat.Status.IN_AIR && this.status != EntityCustomBoat.Status.IN_AIR
                && this.status != EntityCustomBoat.Status.ON_LAND) {
            this.waterLevel = this.getEntityBoundingBox().minY + this.height;
            this.setPosition(this.posX, this.getWaterLevelAbove() - this.height + 0.101D, this.posZ);
            this.motionY = 0.0D;
            this.lastYd = 0.0D;
            this.status = EntityCustomBoat.Status.IN_WATER;
        } else {
            if (this.status == EntityCustomBoat.Status.IN_WATER) {
                d2 = (this.waterLevel - this.getEntityBoundingBox().minY) / this.height;
                this.momentum = 0.9F;
            } else if (this.status == EntityCustomBoat.Status.UNDER_FLOWING_WATER) {
                d1 = -7.0E-4D;
                this.momentum = 0.9F;
            } else if (this.status == EntityCustomBoat.Status.UNDER_WATER) {
                d2 = 0.009999999776482582D;
                this.momentum = 0.45F;
            } else if (this.status == EntityCustomBoat.Status.IN_AIR) {
                this.momentum = 0.9F;
            } else if (this.status == EntityCustomBoat.Status.ON_LAND) {
                this.momentum = this.boatGlide;

                if (this.getControllingPassenger() instanceof EntityPlayer) {
                    this.boatGlide /= 2.0F;
                }
            }

            this.motionX *= this.momentum;
            this.motionZ *= this.momentum;
            this.deltaRotation *= this.momentum;
            this.motionY += d1;

            if (d2 > 0.0D) {
                this.motionY += d2 * 0.06153846016296973D;
                this.motionY *= 0.75D;
            }
        }
    }

    private void controlBoat() {
        if (this.isBeingRidden()) {
            float f = 0.0F;

            if (this.leftInputDown) {
                this.deltaRotation += -1.0F;
            }

            if (this.rightInputDown) {
                ++this.deltaRotation;
            }

            if (this.rightInputDown != this.leftInputDown && !this.forwardInputDown && !this.backInputDown) {
                f += 0.005F;
            }

            this.rotationYaw += this.deltaRotation;

            if (this.forwardInputDown) {
                f += 0.04F;
            }

            if (this.backInputDown) {
                f -= 0.005F;
            }

            this.motionX += MathHelper.sin(-this.rotationYaw * 0.017453292F) * f;
            this.motionZ += MathHelper.cos(this.rotationYaw * 0.017453292F) * f;
            this.setPaddleState(this.rightInputDown && !this.leftInputDown || this.forwardInputDown,
                    this.leftInputDown && !this.rightInputDown || this.forwardInputDown);
        }
    }

    public void updatePassenger(Entity passenger) {
        if (this.isPassenger(passenger)) {
            float f = 0.0F;
            float f1 = (float) ((this.isDead ? 0.009999999776482582D : this.getMountedYOffset())
                    + passenger.getYOffset());

            if (this.getPassengers().size() > 1) {
                int i = this.getPassengers().indexOf(passenger);

                if (i == 0) {
                    f = 0.2F;
                } else {
                    f = -0.6F;
                }

                if (passenger instanceof EntityAnimal) {
                    f = (float) (f + 0.2D);
                }
            }

            Vec3d vec3d = (new Vec3d(f, 0.0D, 0.0D))
                    .rotateYaw(-this.rotationYaw * 0.017453292F - ((float) Math.PI / 2F));
            passenger.setPosition(this.posX + vec3d.x, this.posY + f1, this.posZ + vec3d.z);
            passenger.rotationYaw += this.deltaRotation;
            passenger.setRotationYawHead(passenger.getRotationYawHead() + this.deltaRotation);
            this.applyYawToEntity(passenger);

            if (passenger instanceof EntityAnimal && this.getPassengers().size() > 1) {
                int j = passenger.getEntityId() % 2 == 0 ? 90 : 270;
                passenger.setRenderYawOffset(((EntityAnimal) passenger).renderYawOffset + j);
                passenger.setRotationYawHead(passenger.getRotationYawHead() + j);
            }
        }
    }

    /**
     * Applies this boat's yaw to the given entity. Used to update the orientation
     * of its passenger.
     */
    protected void applyYawToEntity(Entity entityToUpdate) {
        entityToUpdate.setRenderYawOffset(this.rotationYaw);
        float f = MathHelper.wrapDegrees(entityToUpdate.rotationYaw - this.rotationYaw);
        float f1 = MathHelper.clamp(f, -105.0F, 105.0F);
        entityToUpdate.prevRotationYaw += f1 - f;
        entityToUpdate.rotationYaw += f1 - f;
        entityToUpdate.setRotationYawHead(entityToUpdate.rotationYaw);
    }

    /**
     * Applies this entity's orientation (pitch/yaw) to another entity. Used to
     * update passenger orientation.
     */
    @SideOnly(Side.CLIENT)
    public void applyOrientationToEntity(Entity entityToUpdate) {
        this.applyYawToEntity(entityToUpdate);
    }

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    protected void writeEntityToNBT(NBTTagCompound compound) {
        compound.setString("Type", this.getBoatType().getName());
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    protected void readEntityFromNBT(NBTTagCompound compound) {
        if (compound.hasKey("Type", 8)) {
            this.setBoatType(EntityCustomBoat.Type.getTypeFromString(compound.getString("Type")));
        }
    }

    public boolean processInitialInteract(EntityPlayer player, EnumHand hand) {
        if (player.isSneaking()) {
            return false;
        } else {
            if (!this.world.isRemote && this.outOfControlTicks < 60.0F) {
                player.startRiding(this);
            }

            return true;
        }
    }

    protected void updateFallState(double y, boolean onGroundIn, IBlockState state, BlockPos pos) {
        this.lastYd = this.motionY;

        if (!this.isRiding()) {
            if (onGroundIn) {
                if (this.fallDistance > 3.0F) {
                    if (this.status != EntityCustomBoat.Status.ON_LAND) {
                        this.fallDistance = 0.0F;
                        return;
                    }

                    this.fall(this.fallDistance, 1.0F);

                    if (!this.world.isRemote && !this.isDead) {
                        this.setDead();

                        if (this.world.getGameRules().getBoolean("doEntityDrops")) {
                            for (int i = 0; i < 3; ++i) {
                                this.entityDropItem(new ItemStack(Item.getItemFromBlock(Blocks.PLANKS), 1,
                                        this.getBoatType().getMetadata()), 0.0F);
                            }

                            for (int j = 0; j < 2; ++j) {
                                this.dropItemWithOffset(Items.STICK, 1, 0.0F);
                            }
                        }
                    }
                }

                this.fallDistance = 0.0F;
            } else if (this.world.getBlockState((new BlockPos(this)).down()).getMaterial() != Material.WATER
                    && y < 0.0D) {
                this.fallDistance = (float) (this.fallDistance - y);
            }
        }
    }

    public boolean getPaddleState(int side) {
        return this.dataManager.get(DATA_ID_PADDLE[side]).booleanValue() && this.getControllingPassenger() != null;
    }

    /**
     * Sets the damage taken from the last hit.
     */
    public void setDamageTaken(float damageTaken) {
        this.dataManager.set(DAMAGE_TAKEN, Float.valueOf(damageTaken));
    }

    /**
     * Gets the damage taken from the last hit.
     */
    public float getDamageTaken() {
        return this.dataManager.get(DAMAGE_TAKEN).floatValue();
    }

    /**
     * Sets the time to count down from since the last time entity was hit.
     */
    public void setTimeSinceHit(int timeSinceHit) {
        this.dataManager.set(TIME_SINCE_HIT, Integer.valueOf(timeSinceHit));
    }

    /**
     * Gets the time since the last hit.
     */
    public int getTimeSinceHit() {
        return this.dataManager.get(TIME_SINCE_HIT).intValue();
    }

    /**
     * Sets the forward direction of the entity.
     */
    public void setForwardDirection(int forwardDirection) {
        this.dataManager.set(FORWARD_DIRECTION, Integer.valueOf(forwardDirection));
    }

    /**
     * Gets the forward direction of the entity.
     */
    public int getForwardDirection() {
        return this.dataManager.get(FORWARD_DIRECTION).intValue();
    }

    public void setBoatType(EntityCustomBoat.Type boatType) {
        this.dataManager.set(BOAT_TYPE, Integer.valueOf(boatType.ordinal()));
    }

    public EntityCustomBoat.Type getBoatType() {
        return EntityCustomBoat.Type.byId(this.dataManager.get(BOAT_TYPE).intValue());
    }

    protected boolean canFitPassenger(Entity passenger) {
        return this.getPassengers().size() < 2;
    }

    /**
     * For vehicles, the first passenger is generally considered the controller and
     * "drives" the vehicle. For example, Pigs, Horses, and Boats are generally
     * "steered" by the controlling passenger.
     */
    @Nullable
    public Entity getControllingPassenger() {
        List<Entity> list = this.getPassengers();
        return list.isEmpty() ? null : (Entity) list.get(0);
    }

    @SideOnly(Side.CLIENT)
    public void updateInputs(boolean p_184442_1_, boolean p_184442_2_, boolean p_184442_3_, boolean p_184442_4_) {
        this.leftInputDown = p_184442_1_;
        this.rightInputDown = p_184442_2_;
        this.forwardInputDown = p_184442_3_;
        this.backInputDown = p_184442_4_;
    }

    public static enum Status {
        IN_WATER, UNDER_WATER, UNDER_FLOWING_WATER, ON_LAND, IN_AIR;
    }

    public static enum Type {
        CHERRY(DFWoodType.CHERRY.getMetadata(), "cherry");

        private final String name;
        private final int metadata;

        private Type(int metadataIn, String nameIn) {
            this.name = nameIn;
            this.metadata = metadataIn;
        }

        public String getName() {
            return this.name;
        }

        public int getMetadata() {
            return this.metadata;
        }

        public String toString() {
            return this.name;
        }

        /**
         * Get a boat type by it's enum ordinal
         */
        public static EntityCustomBoat.Type byId(int id) {
            if (id < 0 || id >= values().length) {
                id = 0;
            }

            return values()[id];
        }

        public static EntityCustomBoat.Type getTypeFromString(String nameIn) {
            for (int i = 0; i < values().length; ++i) {
                if (values()[i].getName().equals(nameIn)) {
                    return values()[i];
                }
            }

            return values()[0];
        }
    }

    // Forge: Fix MC-119811 by instantly completing lerp on board
    @Override
    protected void addPassenger(Entity passenger) {
        super.addPassenger(passenger);
        if (this.canPassengerSteer() && this.lerpSteps > 0) {
            this.lerpSteps = 0;
            this.posX = this.lerpX;
            this.posY = this.lerpY;
            this.posZ = this.lerpZ;
            this.rotationYaw = (float) this.lerpYaw;
            this.rotationPitch = (float) this.lerpPitch;
        }
    }
}