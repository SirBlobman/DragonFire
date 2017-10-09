package com.DragonFire.command;

import com.DragonFire.utility.ItemUtil;
import com.DragonFire.utility.Util;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.oredict.OreDictionary;

public class CommandOreDictionary extends CommandBase {
    public String getName() {return "oredictionary";}
    public String getUsage(ICommandSender cs) {return "/oredictionary";}

    @Override
    public void execute(MinecraftServer ms, ICommandSender cs, String[] args) {
        Entity en = cs.getCommandSenderEntity();
        if(en != null && en instanceof EntityLivingBase) {
            EntityLivingBase elb = (EntityLivingBase) cs;
            ItemStack is = elb.getItemStackFromSlot(EntityEquipmentSlot.MAINHAND);
            if(!ItemUtil.isAir(is)) {
                int[] oreIDs = OreDictionary.getOreIDs(is);
                StringBuilder sb = new StringBuilder("OreDictionary IDs for " + is.getDisplayName());
                for(int i : oreIDs) {
                    String id = OreDictionary.getOreName(i);
                    sb.append("\n  - " + id);
                }
                Util.sendMessage(elb, sb.toString());
            } else {
                String error = "You are not holding an item in your main hand!";
                Util.sendMessage(elb, error);
            }
        } else {
            String error = "Only living entities can execute this command!";
            Util.sendMessage(cs, error);
        }
    }
}