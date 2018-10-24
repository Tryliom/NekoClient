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

	public AutoSellAll() {
		super("AutoSellAll", -1, Category.MISC);
	}
	
	@Override
	public void onEnabled() {
		Utils.addChat("§dCommande en test, sujette à des bugs.");

		running = true;
        thread.start();
		super.onEnabled();
		mc.thePlayer.sendChatMessage("/sellall");
	}
	
	@Override
	public void onDisabled() {
		running = false;
		thread.interrupt();
		super.onDisabled();
	}
	
	@Override
	public void onUpdate() {
		super.onUpdate();
	}
	
	public Thread thread = new Thread(new Runnable() {
		public void run() {
			while (running) {
				
				try {
					mc.thePlayer.sendChatMessage("/sellall");
	                thread.sleep(10000);
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
			}
		}
	});
	
}
