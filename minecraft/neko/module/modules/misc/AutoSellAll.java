package neko.module.modules.misc;

import java.util.ArrayList;

import neko.event.UpdateEvent;
import neko.module.Category;
import neko.module.Module;
import neko.utils.RenderUtils;
import neko.utils.Utils;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.network.play.client.C07PacketPlayerDigging;
import net.minecraft.network.play.client.C07PacketPlayerDigging.Action;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.chunk.Chunk;

public class AutoSellAll extends Module {
	
	public BlockPos pos1;
	public BlockPos pos2;
	public ArrayList<BlockPos> blockPoses = new ArrayList<BlockPos>();
	public BlockPos currentPos;
	public int x = -1;
	public int y = -1;
	public int z = -1;
	public boolean running;
	public int count = 0;

	public AutoSellAll() {
		super("AutoSellAll", -1, Category.MISC);
	}
	
	public void onEnabled() {
		super.onEnabled();
	}
	
	public void onDisabled() {
		count = 0;
		super.onDisabled();
	}
	
	public void onUpdate() {
		count++;
		if (count==40) {
			mc.thePlayer.sendChatMessage("/sell all");
			count = 0;
		}
	}
	
}
