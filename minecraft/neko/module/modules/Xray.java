package neko.module.modules;

import java.util.ArrayList;

import neko.module.Category;
import neko.module.Module;
import neko.utils.Utils;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.util.BlockPos;

import org.lwjgl.input.Keyboard;

public class Xray extends Module {
	public static boolean isOn;
	public static ArrayList<Integer> xray = new ArrayList<Integer>();
	int delay = 0;

	public Xray() {
		super("Xray", Keyboard.KEY_X, Category.RENDER);
	}

	public void onEnabled() {
		isOn = true;
		Minecraft.getMinecraft().renderGlobal.loadRenderers();
		if (u.display)
		u.addChat("§a§oXray activé !");
	}

	public void onDisabled() {
		isOn = false;
		Minecraft.getMinecraft().renderGlobal.loadRenderers();
		if (u.display)
		u.addChat("§c§oXray désactivé !");
	}
	
	public void onUpdate() {
		if (org.lwjgl.input.Mouse.isButtonDown(2) && delay > 20 && mc.objectMouseOver.func_178782_a()!=null && !(mc.currentScreen instanceof GuiChat) && !(mc.currentScreen instanceof GuiInventory)) {
			BlockPos bl = mc.objectMouseOver.func_178782_a();
			int id = Block.getIdFromBlock(mc.theWorld.getBlockState(bl).getBlock());
			if (xray.contains(id)) {
				for (int i=0;i<xray.size();i++) {
					if (xray.get(i)==id) {
						xray.remove(i);
					}						
				}
				u.addChat("§cLe bloc "+Block.getBlockById(id).getLocalizedName()+" a été supprimé !");
			} else {
				xray.add(id);
				u.addChat("§aLe bloc "+Block.getBlockById(id).getLocalizedName()+" a été ajouté !");
			}
			
			delay=0;
		} else
			delay++;
	}

}
