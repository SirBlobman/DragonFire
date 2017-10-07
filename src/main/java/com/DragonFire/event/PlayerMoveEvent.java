package com.DragonFire.event;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.Cancelable;

@Cancelable
public class PlayerMoveEvent extends PlayerEvent {
    private final BlockPos from, to;
    public PlayerMoveEvent(EntityPlayer ep, BlockPos from, BlockPos to) {
        super(ep);
        this.from = from;
        this.to = to;
    }
    
    public BlockPos getFrom() {return from;}
    public BlockPos getTo() {return to;}
}