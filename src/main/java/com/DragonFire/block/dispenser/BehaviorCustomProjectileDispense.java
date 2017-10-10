package com.DragonFire.block.dispenser;

import com.DragonFire.entity.projectile.EntityDynamite;
import com.DragonFire.entity.projectile.EntityExplosiveArrow;
import com.DragonFire.item.DFItems;
import com.DragonFire.item.tool.Dynamite;

import net.minecraft.block.BlockDispenser;
import net.minecraft.dispenser.BehaviorProjectileDispense;
import net.minecraft.dispenser.IBehaviorDispenseItem;
import net.minecraft.dispenser.IPosition;
import net.minecraft.entity.IProjectile;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.registry.RegistryDefaulted;
import net.minecraft.world.World;

public final class BehaviorCustomProjectileDispense extends BehaviorProjectileDispense {
    @Override
    public IProjectile getProjectileEntity(World world, IPosition pos, ItemStack is) {
        if(!is.isEmpty()) {
            Item item = is.getItem();
            if(item == DFItems.EXPLOSIVE_ARROW) {
                double x = pos.getX(), y = pos.getY(), z = pos.getZ();
                EntityExplosiveArrow eea = new EntityExplosiveArrow(world, x, y, z);
                return eea;
            } else if(item instanceof Dynamite) {
                Dynamite d = (Dynamite) item;
                double x = pos.getX(), y = pos.getY(), z = pos.getZ();
                EntityDynamite ed = new EntityDynamite(world, x, y, z);
                ed.setNuclear(d.isNuclear());
                return ed;
            }
        } return null;
    }
    
    public static void register() {
        RegistryDefaulted<Item, IBehaviorDispenseItem> reg = BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY;
        BehaviorCustomProjectileDispense disp = new BehaviorCustomProjectileDispense();
        reg.putObject(DFItems.EXPLOSIVE_ARROW, disp);
        reg.putObject(DFItems.DYNAMITE, disp);
        reg.putObject(DFItems.NUCLEAR_DYNAMITE, disp);
    }
}