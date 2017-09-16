package neko.module.modules;

import java.util.ArrayList;

import neko.module.Category;
import neko.module.Module;
import neko.utils.RenderUtils;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.util.BlockPos;
import net.minecraft.world.chunk.Chunk;

public class Trail extends Module {	
	public static ArrayList<BlockPos> list = new ArrayList<>();
	
	public Trail() {
		super("Trail", -1, Category.RENDER);
	}
	
	public void onEnabled() {	
		if (u.isLock(this.getName()))
			return;
		super.onEnabled();
	}
	
	public void onDisabled() {
		list.clear();
		super.onDisabled();
	}
	
	public void onRender3D() {
		for (BlockPos bp : list) {
				RenderUtils.drawBlockESP(bp.getX() - mc.getRenderManager().renderPosX, bp.getY() - mc.getRenderManager().renderPosY, bp.getZ() - mc.getRenderManager().renderPosZ, 0.3F, 0.3F, 0.9F, 0.15F, 0.2F, 0.2F, 0.9F, 0.15F, 1F);
		}
	}
	
	public void onUpdate() {
		if (u.isLock(this.getName())) {
			boolean display = u.display;
			u.display=false;
			this.isToggled=false;
			u.display=display;
			u.addWarn(this.getName());
			return;
		}
		BlockPos b = new BlockPos(mc.thePlayer.posX, mc.thePlayer.posY-1, mc.thePlayer.posZ);
		Chunk var2 = mc.theWorld.getChunkFromBlockCoords(b);
        Block blockId = var2.getBlock(b.getX(), b.getY(), b.getZ());
        for (BlockPos bp : list) {
        	if (bp.equals(b)) {
        		return;
        	}
        }
		if (blockId.getMaterial()!=Material.air)
			list.add(b);
	}
}
