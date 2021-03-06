package com.cjcj55.chrispymod.objects.items;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceContext.FluidMode;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class LightningWandItem extends Item
{
	public LightningWandItem(Properties properties) 
	{
		super(properties);
	}

	private RayTraceResult rayTrace(World world, PlayerEntity player)
	{
		float rotationPitch = player.rotationPitch;
		float rotationYaw = player.rotationYaw;
		Vec3d eyePosition = player.getEyePosition(1.0F);
		// Normalize the look vector.
		float f2 = MathHelper.cos(-rotationYaw * ((float)Math.PI / 180F) - (float)Math.PI);
		float f3 = MathHelper.sin(-rotationYaw * ((float)Math.PI / 180F) - (float)Math.PI);
		float f4 = -MathHelper.cos(-rotationPitch * ((float)Math.PI / 180F));
		float f5 = MathHelper.sin(-rotationPitch * ((float)Math.PI / 180F));
		float f6 = f3 * f4;
		float f7 = f2 * f4;
		double rayTraceDistance = 54;
		// Take the start position and add the look vector multiplied by the rayTraceDistance.
		Vec3d endPosition = eyePosition.add((double)f6 * rayTraceDistance, (double)f5 * rayTraceDistance, (double)f7 * rayTraceDistance);
		return world.rayTraceBlocks(new RayTraceContext(eyePosition, endPosition, RayTraceContext.BlockMode.OUTLINE, FluidMode.NONE, player));
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) 
	{
		if(!worldIn.isRemote) // If we are on the logical server
		{
			ItemStack stack = playerIn.getHeldItem(handIn);
			RayTraceResult result = this.rayTrace(worldIn, playerIn);
			if(result instanceof BlockRayTraceResult)
			{
				BlockPos pos = ((BlockRayTraceResult) result).getPos();
				Block block = worldIn.getBlockState(pos).getBlock();
				if(block != Blocks.AIR)
				{
				if(worldIn instanceof ServerWorld)
				{
					((ServerWorld) worldIn).addLightningBolt(new LightningBoltEntity(worldIn, pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5, false));
					
					playerIn.addStat(Stats.ITEM_USED.get(this));
					if (!playerIn.abilities.isCreativeMode) {
						stack.damageItem(1, playerIn, (p_219998_1_) -> {
							p_219998_1_.sendBreakAnimation(handIn);
						});
					}	
				}
				}
				return new ActionResult<ItemStack>(ActionResultType.SUCCESS, stack);
				
			} else if(result instanceof EntityRayTraceResult)
			{
				Entity entity = ((EntityRayTraceResult) result).getEntity();
				if(worldIn instanceof ServerWorld)
				{
					Vec3d entityPos = entity.getPositionVec();
					((ServerWorld) worldIn).addLightningBolt(new LightningBoltEntity(worldIn, entityPos.getX(), entityPos.getY(), entityPos.getZ(), false));
				
					playerIn.addStat(Stats.ITEM_USED.get(this));
					if (!playerIn.abilities.isCreativeMode) {
						stack.damageItem(1, playerIn, (p_219998_1_) -> {
							p_219998_1_.sendBreakAnimation(handIn);
						});
					}
				}
				return new ActionResult<ItemStack>(ActionResultType.SUCCESS, stack);
			}
			// If we have yet to return from one of these branches make the current hand the active hand.
			playerIn.setActiveHand(handIn);
		}
		return super.onItemRightClick(worldIn, playerIn, handIn);
	}
}
