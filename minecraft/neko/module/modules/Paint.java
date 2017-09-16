package neko.module.modules;

import java.util.ArrayList;

import neko.module.Category;
import neko.module.Module;
import neko.module.other.PaintBloc;
import neko.utils.RenderUtils;
import net.minecraft.block.material.Material;
import net.minecraft.util.BlockPos;

public class Paint extends Module {	
	public static ArrayList<PaintBloc> pain = new ArrayList<>();
	public static float cR=0.33F;
	public static float cG=0.33F;
	public static float cB=0.33F;
	public static float alpha=0.5F;
	
	public Paint() {
		super("Paint", -1, Category.RENDER);
	}	
	
	public void onEnabled() {
		super.onEnabled();
	}
	
	public void onDisabled() {
		pain.clear();
		super.onDisabled();
	}
	
	public void onRender3D() {
		if (pain.size()!=0) {
			for (PaintBloc p : pain) {
				BlockPos currentBlock=p.getB();
				RenderUtils.drawBlockESP(currentBlock.getX() - mc.getRenderManager().renderPosX, currentBlock.getY() - mc.getRenderManager().renderPosY, currentBlock.getZ() - mc.getRenderManager().renderPosZ, p.getcR(), p.getcG(), p.getcB(), p.getAlpha(), p.getcR(), p.getcG(), p.getcB(), p.getAlpha(), 1F);				
			}
		}
	}
	
	public void onRightClick() {
		BlockPos b = mc.objectMouseOver.func_178782_a();
		if (b!=null) {
			if (mc.theWorld.getBlockState(b).getBlock().getMaterial() != Material.air) {
				for (PaintBloc p : pain) {
					if (p.getB().equals(b) && (p.getcR()==cR && p.getcR()==cG && p.getcR()==cB && p.getAlpha()==alpha))
						return;
				}
				pain.add(new PaintBloc(b, cR, cG, cB, alpha));
			}
		}
	}
}
