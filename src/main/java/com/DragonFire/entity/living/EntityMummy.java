package com.DragonFire.entity.living;

import com.DragonFire.entity.loot.DFLootTables;

import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class EntityMummy extends EntityZombie {
    public EntityMummy(World w) {
        super(w);
    }
    
    @Override
    protected void applyEntityAI() {
        targetTasks.addTask(2, new EntityAINearestAttackableTarget<EntityPlayer>(this, EntityPlayer.class, true));
    }
    
    @Override
    protected ResourceLocation getLootTable() {
        ResourceLocation rl = DFLootTables.MUMMY;
        return rl;
    }
}