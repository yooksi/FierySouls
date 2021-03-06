package com.yooksi.fierysouls.item;

import com.yooksi.fierysouls.block.BlockTorchUnlit;
import com.yooksi.fierysouls.common.CustomSoundEvents;
import com.yooksi.fierysouls.common.FierySouls;
import com.yooksi.fierysouls.common.ResourceLibrary;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemMatchbox extends Item
{
	public static ItemMatchbox localInstance;
	
	public ItemMatchbox() 
	{
		this.setMaxStackSize(1);     // Sets how much items of this type can fit in one slot
		this.setMaxDamage(20);      // How many matches do we get in the matchbox (item durability)
		this.setNoRepair();
		
		this.setCreativeTab(FierySouls.tabTorches);
		localInstance = this;
	}
	
	@Override
	public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
		// The player uses one match to try to light something on fire:
		stack.damageItem(1, playerIn);
		
		// If used on torch, light it on fire
		if (worldIn.getBlockState(pos).getBlock() == ResourceLibrary.TORCH_UNLIT)
			BlockTorchUnlit.lightTorch(worldIn, pos);
		
		worldIn.playSound(playerIn, pos, CustomSoundEvents.item_match_strike, SoundCategory.BLOCKS, 1.0F, itemRand.nextFloat() * 0.4F + 0.8F);
		
		return EnumActionResult.SUCCESS;  // Always allow the item to be used
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, java.util.List<String> info, boolean par4) 
	{	
		info.add(com.mojang.realmsclient.gui.ChatFormatting.ITALIC + "An efficient tool for starting a fire.");
	}
}
