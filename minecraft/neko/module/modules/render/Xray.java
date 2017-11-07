package neko.module.modules.render;

import java.util.ArrayList;

import org.lwjgl.input.Keyboard;

import neko.module.Category;
import neko.module.Module;
import neko.utils.RenderUtils;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.util.BlockPos;

public class Xray extends Module {
	private int renderDistance = 100;

	public Xray() {
		super("Xray", Keyboard.KEY_X, Category.RENDER);
	}

	public void onEnabled() {
		mc.renderGlobal.loadRenderers();
	}

	public void onDisabled() {
		mc.renderGlobal.loadRenderers();
	}

	public void setValues() {
		this.values = "";
	}

	public void onRender3D() {
		for (int y = (int) renderDistance; y >= (int) -renderDistance; y--) {
			for (int z = (int) -renderDistance; z <= renderDistance; z++) {
				for (int x = (int) -renderDistance; x <= renderDistance; x++) {
					int xPos = ((int) Math.round(this.mc.thePlayer.posX + x));
					int yPos = ((int) Math.round(this.mc.thePlayer.posY + y));
					int zPos = ((int) Math.round(this.mc.thePlayer.posZ + z));

					BlockPos b = new BlockPos(xPos, yPos, zPos);
					Block block = mc.theWorld.getChunkFromBlockCoords(b).getBlock(xPos, yPos, zPos);
					
				}
			}
		}
	}

}
