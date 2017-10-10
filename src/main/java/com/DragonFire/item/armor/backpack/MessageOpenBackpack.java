package com.DragonFire.item.armor.backpack;

import com.DragonFire.utility.ItemUtil;
import com.DragonFire.utility.Util;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageOpenBackpack implements IMessage, IMessageHandler<MessageOpenBackpack, IMessage> {
    public void fromBytes(ByteBuf buf) {}
    public void toBytes(ByteBuf buf) {}

    @Override
    public IMessage onMessage(MessageOpenBackpack msg, MessageContext ctx) {
        EntityPlayerMP ep = ctx.getServerHandler().player;
        ItemStack is = ep.getItemStackFromSlot(EntityEquipmentSlot.CHEST);
        if(!ItemUtil.isAir(is)) {
            Item item = is.getItem();
            if(item instanceof DyableBackpack) {
                DyableBackpack.openInventory(is, ep);
                return null;
            }
        }
        
        String error = "You don't have a backpack on!";
        Util.sendMessage(ep, error);
        return null;
    }
}