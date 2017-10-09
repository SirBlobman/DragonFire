package com.DragonFire.item.armor.backpack;

import com.DragonFire.creative.DFTabs;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants.NBT;

public class DyableBackpack extends ItemArmor {
    public DyableBackpack() {
        super(ArmorMaterial.LEATHER, 1, EntityEquipmentSlot.CHEST);
        setRegistryName("backpack");
        setUnlocalizedName("backpack");
        setCreativeTab(DFTabs.ARMOR_AND_TOOLS);
    }
    
    @Override
    public boolean hasColor(ItemStack is) {
        NBTTagCompound nbt = is.getTagCompound();
        return (nbt != null && nbt.hasKey("display", 10)) ? nbt.getCompoundTag("display").hasKey("color", 3) : false;
    }
    
    @Override
    public int getColor(ItemStack is) {
        NBTTagCompound nbt = is.getTagCompound();
        if(nbt != null) {
            NBTTagCompound nbt1 = nbt.getCompoundTag("display");
            if(nbt1 != null && nbt1.hasKey("color", 3)) return nbt1.getInteger("color");
        } return 10511680;
    }
    
    @Override
    public void removeColor(ItemStack is) {
        NBTTagCompound nbt = is.getTagCompound();
        if(nbt != null) {
            NBTTagCompound nbt1 = nbt.getCompoundTag("display");
            if(nbt1.hasKey("color")) nbt1.removeTag("color");
        }
    }
    
    @Override
    public void setColor(ItemStack is, int color) {
        NBTTagCompound nbt = is.getTagCompound();
        if(nbt == null) {
            nbt = new NBTTagCompound();
            is.setTagCompound(nbt);
        }
        
        NBTTagCompound nbt1 = nbt.getCompoundTag("display");
        if(!nbt.hasKey("display", 10)) {nbt.setTag("display", nbt1);}
        nbt1.setInteger("color", color);
    }
    
    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer ep, EnumHand eh) {
        ItemStack is = ep.getHeldItem(eh);
        if(eh == EnumHand.MAIN_HAND) openInventory(is, ep);
        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, is);
    }
    
    public static void openInventory(ItemStack backpack, EntityPlayer ep) {
        NBTTagCompound nbt = backpack.getTagCompound();
        if(nbt == null) {
            nbt = new NBTTagCompound();
            backpack.setTagCompound(nbt);
        }
        
        if(nbt.hasKey("Inventory")) {
            NBTTagList tagList = nbt.getTagList("Inventory", NBT.TAG_COMPOUND);
            InventoryBackpack ib = new InventoryBackpack(backpack);
            ib.loadInventoryFromNBT(tagList);
            ep.displayGUIChest(ib);
        } else {
            NBTTagCompound nbt1 = new NBTTagCompound();
            nbt.setTag("Inventory", nbt1);
            openInventory(backpack, ep);
        }
    }
}