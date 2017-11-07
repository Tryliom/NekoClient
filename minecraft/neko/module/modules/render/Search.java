package neko.module.modules.render;

import java.util.ArrayList;

import org.lwjgl.input.Keyboard;

import neko.module.Category;
import neko.module.Module;
import neko.module.other.PaintBloc;
import neko.utils.RenderUtils;
import neko.utils.Utils;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.chunk.Chunk;

public class Search extends Module {
	private Block searchBlock;
	private int delay = 0;
	private int renderDistance = 100;
	private static Search instance = null;

	public Search() {
		super("Search", -1, Category.RENDER);
		instance = this;
	}

	public static Search getSearch() {
		return instance;
	}

	public void onEnabled() {
		super.onEnabled();
	}

	public void onDisabled() {
		super.onDisabled();
	}

	public void setValues() {
		this.values = "§6Bloc recherché:§7 "
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
				Utils.addChat("§cBloc cherché supprimé !");
			} else {
				searchBlock = searchBlock_;
				Utils.addChat("§a" + this.searchBlock.getLocalizedName() + " recherché !");
			}
			mc.renderGlobal.loadRenderers();
			delay = 0;
		} else
			delay++;
	}

	public void onRender3D() {
		if (searchBlock != null) {
			for (int y = (int) renderDistance; y >= (int) - renderDistance; y--) {
				for (int z = (int) - renderDistance; z <= renderDistance; z++) {
					for (int x = (int) - renderDistance; x <= renderDistance; x++) {
						int xPos = ((int) Math.round(this.mc.thePlayer.posX + x));
						int yPos = ((int) Math.round(this.mc.thePlayer.posY + y));
						int zPos = ((int) Math.round(this.mc.thePlayer.posZ + z));

						BlockPos b = new BlockPos(xPos, yPos, zPos);
						Block block = mc.theWorld.getChunkFromBlockCoords(b).getBlock(xPos, yPos, zPos);

						if ((block == searchBlock)) {
							RenderUtils.drawOutlinedBlockESP(b.getX() - mc.getRenderManager().renderPosX,
									b.getY() - mc.getRenderManager().renderPosY,
									b.getZ() - mc.getRenderManager().renderPosZ, 
									0.33F, 0.33F, 0.99F, 0.2F, 7F, 1D, 1D, 1D);
						}
					}
				}
			}
		}
	}

}
