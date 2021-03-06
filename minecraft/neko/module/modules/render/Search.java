package neko.module.modules.render;

import java.util.Vector;

import neko.module.Category;
import neko.module.Module;
import neko.utils.RenderUtils;
import neko.utils.Utils;
import net.minecraft.block.Block;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.util.BlockPos;

public class Search extends Module {
	private Block searchBlock;
	private int delay = 0;
	private int delay_ = 0;
	private int refreshTime = 5;
	private int renderDistance = 75;
	private Vector<BlockPos> list = new Vector<>();
	private static Search instance = null;

	public Search() {
		super("Search", -1, Category.RENDER, false);
		instance = this;
	}

	public static Search getSearch() {
		return instance;
	}

	public void onEnabled() {
		if(this.searchBlock != null) {
			Utils.addChat("?aLe module SEARCH contient le bloc ?2" + this.searchBlock.getLocalizedName());
		}
		this.refresh();
		super.onEnabled();
	}

	public void onDisabled() {
		super.onDisabled();
	}

	public void setValues() {
		this.values = "?6Bloc recherch? ?7"
				+ (this.searchBlock == null ? "Aucun" : this.searchBlock.getLocalizedName());
	}

	public Block getSearchBlock() {
		return searchBlock;
	}

	public void setSearchBlock(Block searchBlock) {
		this.searchBlock = searchBlock;
	}

	public void onUpdate() {
		if (mc.gameSettings.keyBindPickBlock.getIsKeyPressed() && delay > 15
				&& mc.objectMouseOver.func_178782_a() != null && !(mc.currentScreen instanceof GuiChat)
				&& !(mc.currentScreen instanceof GuiContainer)) {
			BlockPos bl = mc.objectMouseOver.func_178782_a();
			Block searchBlock_ = mc.theWorld.getBlockState(bl).getBlock();
			if (searchBlock == searchBlock_) {
				searchBlock = null;
				Utils.addChat("?cBloc cherch? supprim? !");
				Search.getSearch().refresh();
			} else {
				searchBlock = searchBlock_;
				Utils.addChat("?a" + this.searchBlock.getLocalizedName() + " recherch? !");
				Search.getSearch().refresh();
			}
			delay = 0;
		} else
			delay++;
		if (delay_ > 20*refreshTime) {
			refresh();
			delay_ = 0;
		} else
			delay_++;
	}
	
	public void refresh() {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				Vector<BlockPos> cl = new Vector<>();
				if (searchBlock != null) {
					for (int y = (int) renderDistance; y >= (int) - renderDistance; y--) {				
						for (int z = (int) - renderDistance; z <= renderDistance; z++) {
							for (int x = (int) - renderDistance; x <= renderDistance; x++) {
								int xPos = ((int) Math.round(mc.thePlayer.posX + x));
								int yPos = ((int) Math.round(mc.thePlayer.posY + y));
								int zPos = ((int) Math.round(mc.thePlayer.posZ + z));

								BlockPos b = new BlockPos(xPos, yPos, zPos);
								Block block = mc.theWorld.getChunkFromBlockCoords(b).getBlock(xPos, yPos, zPos);
								if ((block == searchBlock)) {
									cl.add(b);
								}
							}
						}
					}
					list.clear();
					list = (Vector<BlockPos>) cl.clone();
				}
				
			}
		}).start();
	}

	public void onRender3D() {
		try {
			int i = 0;
			if (searchBlock != null) {
				for (BlockPos b : list) {
					i++;
					if (i>=700)
						return;
					RenderUtils.drawOutlinedBlockESP(b.getX() - mc.getRenderManager().renderPosX,
							b.getY() - mc.getRenderManager().renderPosY,
							b.getZ() - mc.getRenderManager().renderPosZ, 
							0.15F, 0.15F, 0.99F, 0.2F, 7F, 1D, 1D, 1D);
				}
			}
		} catch (Exception e) {}
	}

}
