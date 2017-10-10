package com.DragonFire;

import com.DragonFire.item.armor.backpack.MessageOpenBackpack;

import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class DragonFirePacketHandler {
    public SimpleNetworkWrapper network = NetworkRegistry.INSTANCE.newSimpleChannel(DragonFire.MODID);
    
    public void initialize() {
        network.registerMessage(MessageOpenBackpack.class, MessageOpenBackpack.class, 0, Side.SERVER);
    }
}