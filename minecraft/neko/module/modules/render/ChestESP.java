package neko.module.modules.render;

import neko.module.Category;
import neko.module.Module;
import neko.utils.RenderUtils;
import neko.utils.Utils;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntityDispenser;
import net.minecraft.tileentity.TileEntityDropper;
import net.minecraft.tileentity.TileEntityEnderChest;

public class ChestESP extends Module {
	public static float cR=0.4F;
	public static float cG=0.6F;
	public static float cB=0.4F;
	public static float width=1F;
	public static float clR=0.4F;
	public static float clG=0.6F;
	public static float clB=0.4F;
	public static boolean tracers = false;
	public static boolean dispenser = false;
	
	
	public ChestESP() {
		super("ChestESP", -1, Category.RENDER, false);
	}	
	
	public void onEnabled() {				
		super.onEnabled();
	}
	
	public void onDisabled() {
		super.onDisabled();
	}
	
	public void setValues() {
		this.values = "";
	}
	
	public void onRender3D() {		
		for (Object o : mc.theWorld.loadedTileEntityList) {
			if (o instanceof TileEntityChest) {
				TileEntity chest = (TileEntity) o;     

				if (Utils.getDistanceToBlock(chest.getPos())>2) {
					if (tracers)
						RenderUtils.drawTracerLine(chest.getPos().getX()-mc.getRenderManager().renderPosX+ 0.5, chest.getPos().getY() + 1 - mc.getRenderManager().renderPosY, 
								chest.getPos().getZ() - mc.getRenderManager().renderPosZ + 0.5, cR, cG, cB, 0.5F, width);
	    			RenderUtils.drawBlockESP(chest.getPos().getX()-mc.getRenderManager().renderPosX, chest.getPos().getY()-mc.getRenderManager().renderPosY, chest.getPos().getZ()-mc.getRenderManager().renderPosZ, cR, cG, cR, 0.11F, clR, clG, clB, 0.11F, width);
				}
			} else if (dispenser && (o instanceof TileEntityDispenser || o instanceof TileEntityDropper)) {
				TileEntity chest = (TileEntity) o; 

				if (Utils.getDistanceToBlock(chest.getPos())>2) {
					if (tracers)
						RenderUtils.drawTracerLine(chest.getPos().getX()-mc.getRenderManager().renderPosX+ 0.5, chest.getPos().getY() + 1 - mc.getRenderManager().renderPosY, 
								chest.getPos().getZ() - mc.getRenderManager().renderPosZ + 0.5, Math.round(cR*0.9), Math.round(cG*0.9), Math.round(cR*0.9), 0.5F, width);
					RenderUtils.drawBlockESP(chest.getPos().getX()-mc.getRenderManager().renderPosX, chest.getPos().getY()-mc.getRenderManager().renderPosY, chest.getPos().getZ()-mc.getRenderManager().renderPosZ, Math.round(cR*0.9), Math.round(cG*0.9), Math.round(cR*0.9), 0.11F, Math.round(clR*0.9), Math.round(clG*0.9), Math.round(clB*0.9), 0.11F, width);
				}
			}
        }
	}
}
