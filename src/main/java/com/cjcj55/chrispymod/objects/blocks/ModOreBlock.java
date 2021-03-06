package com.cjcj55.chrispymod.objects.blocks;

import java.util.Random;

import com.cjcj55.chrispymod.init.BlockInit;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;

public class ModOreBlock extends Block
{
	public ModOreBlock(Block.Properties properties)
	{
		super(properties);
	}
	
	protected int getExperience(Random p_220281_1_) {
      if (this == BlockInit.COBALT_ORE.get()) 
      {
         return MathHelper.nextInt(p_220281_1_, 3, 7);
      } 
      
      else if (this == BlockInit.FLAME_ORE_NETHER.get()) 
      {
         return MathHelper.nextInt(p_220281_1_, 2, 5);
      } 
      
      else if (this == BlockInit.OPAL_ORE.get()) 
      {
         return MathHelper.nextInt(p_220281_1_, 1, 3);
      } 
      
      else if (this == BlockInit.PARYTH_ORE.get()) 
      {
         return MathHelper.nextInt(p_220281_1_, 2, 5);
      } 
      
      else if (this == BlockInit.RUBY_ORE.get()) 
      {
         return MathHelper.nextInt(p_220281_1_, 3, 6);
      } 
      
      else if (this == BlockInit.RUBY_ORE_NETHER.get()) 
      {
         return MathHelper.nextInt(p_220281_1_, 3, 6);
      }  
      
      else if (this == BlockInit.HELLFIRE_ORE_NETHER.get()) 
      {
         return MathHelper.nextInt(p_220281_1_, 1, 2);
      } 
      
      else if (this == BlockInit.WHITE_DWARF_STAR_ORE.get()) 
      {
         return MathHelper.nextInt(p_220281_1_, 5, 8);
      }
      
      else if (this == BlockInit.NATURAL_ESSENCE_ORE.get()) 
      {
         return MathHelper.nextInt(p_220281_1_, 4, 7);
      }
      
      else if (this == BlockInit.EXPERIENCE_ORE.get()) 
      {
         return MathHelper.nextInt(p_220281_1_, 1, 3);
      }
      
      else 
      {
         return this == BlockInit.TANGERINE_ORE.get() ? MathHelper.nextInt(p_220281_1_, 2, 4) : 0;
      }
   }
	
   @Override
   public int getExpDrop(BlockState state, net.minecraft.world.IWorldReader reader, BlockPos pos, int fortune, int silktouch) {
      return silktouch == 0 ? this.getExperience(RANDOM) : 0;
   }
}
