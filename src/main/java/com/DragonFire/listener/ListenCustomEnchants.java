package com.DragonFire.listener;

import com.DragonFire.enchantment.DFEnchants;
import com.DragonFire.enchantment.EnchantmentBaneOfHumanoids;
import com.DragonFire.enchantment.EnchantmentExtinguish;
import com.DragonFire.event.PlayerMoveEvent;
import com.DragonFire.utility.ItemUtil;
import com.DragonFire.utility.Util;

import java.util.List;
import java.util.Map;
import java.util.Set;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.world.BlockEvent.HarvestDropsEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ListenCustomEnchants {
    @SubscribeEvent(priority=EventPriority.LOWEST)
    public void breakBlock(HarvestDropsEvent e) {
        EntityPlayer ep = e.getHarvester();
        if(ep != null) {
            ItemStack is = ep.getActiveItemStack();
            if(!ItemUtil.isAir(is)) {
                Map<Enchantment, Integer> map = EnchantmentHelper.getEnchantments(is);
                Set<Enchantment> enchants = map.keySet();
                boolean smelting = enchants.contains(DFEnchants.AUTO_SMELT);
                if(smelting) {
                    List<ItemStack> drops = e.getDrops();
                    List<ItemStack> copyDrops = Util.newList(drops);
                    for(ItemStack drop : copyDrops) {
                        FurnaceRecipes fr = FurnaceRecipes.instance();
                        ItemStack smelt = fr.getSmeltingResult(drop);
                        if(smelt != null && !smelt.isEmpty()) {
                            drops.remove(drop);
                            drops.add(smelt);
                        }
                    }
                }
            }
        }
    }

    @SubscribeEvent(priority=EventPriority.LOWEST)
    public void playerMove(PlayerMoveEvent e) {
        EntityPlayer ep = e.getEntityPlayer();
        ItemStack boots = ep.getItemStackFromSlot(EntityEquipmentSlot.FEET);
        if(!ItemUtil.isAir(boots)) {
            Map<Enchantment, Integer> map = EnchantmentHelper.getEnchantments(boots);
            boolean extinguish = map.containsKey(DFEnchants.EXTINGUISH);
            if(extinguish) {
                World world = ep.getEntityWorld();
                BlockPos bp = e.getTo();
                int level = map.get(DFEnchants.EXTINGUISH);
                EnchantmentExtinguish.extinguishNearby(ep, world, bp, level);
            }
        }
    }

    @SubscribeEvent(priority=EventPriority.LOWEST)
    public void entityDamage(LivingHurtEvent e) {
        Entity hit = e.getEntity();
        DamageSource ds = e.getSource();
        if(ds.getImmediateSource() != null) {
            Entity user = ds.getImmediateSource();
            if(user instanceof EntityLivingBase) {
                EntityLivingBase elb = (EntityLivingBase) user;
                ItemStack is = elb.getItemStackFromSlot(EntityEquipmentSlot.MAINHAND);
                if(is != null && !is.isEmpty()) {
                    Map<Enchantment, Integer> enchants = EnchantmentHelper.getEnchantments(is);
                    if(enchants.containsKey(DFEnchants.BANE_OF_HUMANOIDS)) {
                        int level = enchants.get(DFEnchants.BANE_OF_HUMANOIDS);
                        float damage = e.getAmount();
                        float newDamage = EnchantmentBaneOfHumanoids.getDamageWithEnchant(hit, level, damage);
                        e.setAmount(newDamage);
                    }
                }
            }
        }
    }
}