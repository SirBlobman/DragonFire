package com.DragonFire.item.tool;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.util.EnumHelper;

public class MushroomPickaxe extends QuickPickaxe {
    public static final ToolMaterial MUSHROOM = EnumHelper.addToolMaterial("MUSHROOM", 3, 1561, 8.0F, 3.0F, 30);
    public MushroomPickaxe() {super(MUSHROOM);}
    
    public boolean shouldCauseReequipAnimation(ItemStack is1, ItemStack is2, boolean slot) {return false;}
    public boolean shouldCauseBlockBreakReset(ItemStack is1, ItemStack is2) {return false;}
    
    @Override
    public void onUpdate(ItemStack is, World world, Entity en, int slot, boolean selected) {
        if(!world.isRemote) {
            NBTTagCompound nbt = is.getTagCompound();
            if(nbt == null) {
                nbt = new NBTTagCompound();
                is.setTagCompound(nbt);
            }
            
            if(nbt.hasKey("repairCooldown")) {
                int cooldown = nbt.getInteger("repairCooldown");
                if(cooldown <= 0) {
                    int dur = is.getItemDamage();
                    if(dur > 0) is.setItemDamage(Math.max(0, dur - 2));
                    int nc = (60 * 20); //1 Minute
                    nbt.setInteger("repairCooldown", nc);
                } else {
                    int nc = (cooldown - 1);
                    nbt.setInteger("repairCooldown", nc);
                }
            } else {
                int nc = (60 * 20); //1 Minute
                nbt.setInteger("repairCooldown", nc);
            }
        }
    }
}