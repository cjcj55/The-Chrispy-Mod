package com.cjcj55.chrispymod.objects.items;

import com.cjcj55.chrispymod.init.ItemInit;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.world.World;

public class SpeedArmorItem extends ArmorItem
{
	public SpeedArmorItem(IArmorMaterial materialIn, EquipmentSlotType slot, Properties builder) 
	{
		super(materialIn, slot, builder);
	}
	
	@Override
	public void onArmorTick(ItemStack stack, World world, PlayerEntity player)
	{		
		if(player.inventory.armorItemInSlot(0).getItem() == ItemInit.WHITE_DWARF_STAR_BOOTS.get() &&
			player.inventory.armorItemInSlot(1).getItem() == ItemInit.WHITE_DWARF_STAR_LEGGINGS.get() && 
			player.inventory.armorItemInSlot(2).getItem() == ItemInit.WHITE_DWARF_STAR_CHESTPLATE.get() &&
			player.inventory.armorItemInSlot(3).getItem() == ItemInit.WHITE_DWARF_STAR_HELMET.get())
		{
			  player.addPotionEffect(new EffectInstance(Effects.SPEED, 0, 2));
		}	else { super.onArmorTick(stack, world, player); }	 		
	}
}