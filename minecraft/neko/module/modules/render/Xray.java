package neko.module.modules.render;

import java.util.Vector;

import org.lwjgl.input.Keyboard;

import neko.module.Category;
import neko.module.Module;
import neko.utils.Utils;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.util.BlockPos;

public final class Xray extends Module {
	
	private boolean alreadyactivated;
	private Vector<Integer> list = new Vector<Integer>();
	private static Xray instance = null;
	private Integer delay = 0;
	
	public Xray() {
		super("Xray", Keyboard.KEY_X, Category.RENDER, false);
		this.instance = this;
	}
	
	public static Xray getXray() {
		return instance;
	}

	public void onEnabled() {
		
		if(Utils.getModule("Fullbright").isToggled() == true) {
			this.alreadyactivated = true;
		} else {
			Utils.getModule("Fullbright").setToggled(true);
		}
		super.onEnabled();
		mc.renderGlobal.loadRenderers();
	}

	public void onDisabled() {
		
		if (!this.alreadyactivated) {
			Utils.getModule("Fullbright").setToggled(false);
		}
		mc.renderGlobal.loadRenderers();
		super.onDisabled();
	}
	
	public void onUpdate() {
		
		if(Utils.getModule("Fullbright").isToggled() == false) {
			Utils.getModule("Fullbright").setToggled(true);
		}
		Utils.spectator = true;
		if (mc.gameSettings.keyBindPickBlock.getIsKeyPressed() && this.delay > 20 && mc.objectMouseOver.func_178782_a()!=null && !(mc.currentScreen instanceof GuiChat) && !(mc.currentScreen instanceof GuiContainer)) {
			BlockPos bl = mc.objectMouseOver.func_178782_a();
			int id = Block.getIdFromBlock(mc.theWorld.getBlockState(bl).getBlock());
			if (this.list.contains(id)) {
				this.list.removeElement(id);
				Utils.addChat("§cLe bloc "+Block.getBlockById(id).getLocalizedName()+" a été supprimé !");
			} else {
				this.list.add(id);
				Utils.addChat("§aLe bloc "+Block.getBlockById(id).getLocalizedName()+" a été ajouté !");
	 		}
			Minecraft.getMinecraft().renderGlobal.loadRenderers();
			this.delay=0;
		} else
			this.delay++;
		super.onUpdate();
	}
	
	

	public Vector<Integer> getList() {
		return list;
	}

	public void setList(Vector<Integer> list) {
		this.list = list;
	}

	public void setValues() {
		this.values = "";
	}

}
