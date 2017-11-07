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

}
