package com.DragonFire.entity.custom;

import com.DragonFire.block.tree.DFWoodType;
import com.DragonFire.item.DFItems;

import net.minecraft.entity.item.EntityBoat;
import net.minecraft.item.Item;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.world.World;

public class EntityCustomBoat extends EntityBoat {
    public static final DataParameter<Integer> CUSTOM_TYPE = EntityDataManager.createKey(EntityCustomBoat.class, DataSerializers.VARINT);

    public EntityCustomBoat(World world) {
        super(world);
    }

    public EntityCustomBoat(World world, double x, double y, double z) {
        super(world, x, y, z);
    }

    @Override
    public void entityInit() {
        super.entityInit();
        dataManager.register(CUSTOM_TYPE, Type.CHERRY.ordinal());
    }

    public void setCustomBoatType(Type boatType) {
        this.dataManager.set(CUSTOM_TYPE, Integer.valueOf(boatType.ordinal()));
    }

    public Type getCustomBoatType() {
        return Type.byId(this.dataManager.get(CUSTOM_TYPE));
    }
    
    @Override
    public Item getItemBoat()
    {
        switch (this.getCustomBoatType())
        {
            case CHERRY:
            default:
                return DFItems.CHERRY_BOAT;
        }
    }

    public static enum Type {
        CHERRY(DFWoodType.CHERRY.getMetadata(), "cherry"),
        PALM(DFWoodType.PALM.getMetadata(), "palm");

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
        public static Type byId(int id) {
            if (id < 0 || id >= values().length) {
                id = 0;
            }

            return values()[id];
        }

        public static Type getTypeFromString(String nameIn) {
            for (int i = 0; i < values().length; ++i) {
                if (values()[i].getName().equals(nameIn)) {
                    return values()[i];
                }
            }

            return values()[0];
        }
    }
}