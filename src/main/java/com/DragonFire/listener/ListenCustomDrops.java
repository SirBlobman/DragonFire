package com.DragonFire.listener;

import com.DragonFire.DragonFire;
import com.DragonFire.block.DFBlocks;
import com.DragonFire.item.DFItems;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityWitherSkeleton;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Enchantments;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.living.LivingExperienceDropEvent;
import net.minecraftforge.event.world.BlockEvent.HarvestDropsEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber(modid=DragonFire.MODID)
public class ListenCustomDrops {
    public static Random RANDOM = new Random();
    
    @SubscribeEvent(priority=EventPriority.HIGHEST)
    public void drops(LivingDropsEvent e) {
        Entity en = e.getEntity();
        World w = en.getEntityWorld();
        int loot = e.getLootingLevel();
        if(!w.isRemote) {
            if(en instanceof EntitySquid) {
                Item item = en.isBurning() ? DFItems.COOKED_CALAMARI : DFItems.RAW_CALAMARI;
                int rand = (RANDOM.nextInt(2) + 1);
                int amount = (rand * ((loot > 0) ? loot : 1));
                ItemStack is = new ItemStack(item, amount);
                dropItem(en, is);
            } else if(en instanceof EntityBat) {
                Item item = DFItems.BAT_WING;
                int amount = ((loot > 0) ? loot : 1);
                ItemStack is = new ItemStack(item, amount);
                dropItem(en, is);
            } else if(en instanceof EntityWitherSkeleton) {
                double chance = (Math.random() * 100);
                if(chance <= (5 + loot)) {
                    Item item = DFItems.WITHERED_BONE;
                    ItemStack is = new ItemStack(item, 1);
                    dropItem(en, is);
                }
            }
        }
    }
    
    @SubscribeEvent(priority=EventPriority.LOWEST)
    public void changeXP(LivingExperienceDropEvent e) {
        EntityPlayer ep = e.getAttackingPlayer();
        if(ep != null) {
            ItemStack is = ep.getHeldItem(EnumHand.MAIN_HAND);
            if(is != null && !is.isEmpty()) {
                Item item = is.getItem();
                if(item == DFItems.PHILOSOPHERS_SWORD) {
                    double oldXP = e.getDroppedExperience();
                    double percent = (oldXP * 0.3D);
                    int newXP = (int) (oldXP + percent);
                    e.setDroppedExperience(newXP);
                }
            }
        }
    }
    
    @SubscribeEvent(priority=EventPriority.LOWEST)
    public void drops(HarvestDropsEvent e) {
        if(e.getHarvester() != null) {
            if(!hasSilkTouch(e.getHarvester().getHeldItemMainhand())) {
                IBlockState ibs = e.getState();
                Block b = ibs.getBlock();
                List<ItemStack> drops = e.getDrops();
                if(b == Blocks.GLASS) {
                    int amount = ThreadLocalRandom.current().nextInt(1, 5);
                    ItemStack is = new ItemStack(DFItems.GLASS_FRAGMENT, amount, 17);
                    drops.clear();
                    drops.add(is);
                } else if(b == Blocks.STAINED_GLASS) {
                    int amount = ThreadLocalRandom.current().nextInt(1, 5);
                    int meta = EnumDyeColor.byMetadata(b.getMetaFromState(ibs)).getDyeDamage();
                    ItemStack is = new ItemStack(DFItems.GLASS_FRAGMENT, amount, meta);
                    drops.clear();
                    drops.add(is);
                } else if(b == DFBlocks.OBSIDIAN_GLASS) {
                    int amount = ThreadLocalRandom.current().nextInt(1, 5);
                    ItemStack is = new ItemStack(DFItems.GLASS_FRAGMENT, amount, 16);
                    drops.clear();
                    drops.add(is);
                }
            }
        }
    }
    
    private void dropItem(Entity dropper, ItemStack is) {
        dropper.entityDropItem(is, 0);
    }
    
    private boolean hasSilkTouch(ItemStack tool) {
        if(tool == null || tool.isEmpty()) return false;
        if(tool.isItemEnchanted()) {
            Map<Enchantment, Integer> map = EnchantmentHelper.getEnchantments(tool);
            return map.containsKey(Enchantments.SILK_TOUCH);
        } return false;
    }
}