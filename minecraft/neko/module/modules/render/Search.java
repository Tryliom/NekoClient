package neko.module.modules.render;

import java.util.ArrayList;

import org.lwjgl.input.Keyboard;

import neko.module.Category;
import neko.module.Module;
import neko.utils.Utils;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.util.BlockPos;

public class Search extends Module {
	private Block searchBlock;
	private int delay = 0;
	private static Search instance = null;

	public Search() {
		super("Search", -1, Category.RENDER);
		instance = this;
	}
	
	public static Search getSearch() {
		return instance;
	}

	public void onEnabled() {
		mc.renderGlobal.loadRenderers();
	}

	public void onDisabled() {
		mc.renderGlobal.loadRenderers();
	}
	
	public void setValues() {
		this.values = "§6Bloc recherché:§7 "+(this.searchBlock==null ? "Aucun" : this.searchBlock.getLocalizedName());
	}
	
	public void onUpdate() {
		if (mc.gameSettings.keyBindPickBlock.getIsKeyPressed() && delay > 15 && mc.objectMouseOver.func_178782_a()!=null && !(mc.currentScreen instanceof GuiChat) && !(mc.currentScreen instanceof GuiContainer)) {
			BlockPos bl = mc.objectMouseOver.func_178782_a();			
			Block searchBlock_ = mc.theWorld.getBlockState(bl).getBlock();
			if (searchBlock==searchBlock_) {
				searchBlock = null;
				Utils.addChat("§cBloc cherché supprimé !");
			} else {
				searchBlock = searchBlock_;
				Utils.addChat("§a"+this.searchBlock.getLocalizedName()+" recherché !");
			}
			mc.renderGlobal.loadRenderers();
			delay=0;
		} else
			delay++;
	}
	
	

}
