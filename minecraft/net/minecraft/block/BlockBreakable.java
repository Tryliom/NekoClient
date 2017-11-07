package net.minecraft.block;

import neko.Client;
import neko.module.modules.render.Xray;
import neko.utils.Utils;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;

public class BlockBreakable extends Block
{
    private boolean ignoreSimilarity;

    protected BlockBreakable(Material p_i45712_1_, boolean p_i45712_2_)
    {
        super(p_i45712_1_);
        this.ignoreSimilarity = p_i45712_2_;
    }

    public boolean isOpaqueCube()
    {
        return false;
    }

    public boolean shouldSideBeRendered(IBlockAccess worldIn, BlockPos pos, EnumFacing side)
    {
    	if (Utils.isToggle("Xray")) {
    		return false;
    	}
    	
        IBlockState var4 = worldIn.getBlockState(pos);
        Block var5 = var4.getBlock();      
        
        if (this == Blocks.glass || this == Blocks.stained_glass)
        {
            if (worldIn.getBlockState(pos.offset(side.getOpposite())) != var4)
            {
                return true;
            }

            if (var5 == this)
            {
                return false;
            }
        }

        return !this.ignoreSimilarity && var5 == this ? false : super.shouldSideBeRendered(worldIn, pos, side);
    }
}
