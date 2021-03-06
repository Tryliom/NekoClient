package neko.module.modules.movements;

import org.lwjgl.input.Keyboard;

import neko.module.Category;
import neko.module.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGameOver;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.settings.KeyBinding;

public class InventoryMove extends Module {
	
	public InventoryMove() {
		super("InventoryMove", -1, Category.MOVEMENT, false);
	}
	
	public void onEnabled() {		
		super.onEnabled();
	}
	
	public void onDisabled() {
		super.onDisabled();
	}
	
	public void setValues() {
		this.values = "";
	}
	
	public void onUpdate() {
		
		KeyBinding[] moveKeys = { mc.gameSettings.keyBindForward, mc.gameSettings.keyBindBack, mc.gameSettings.keyBindLeft, mc.gameSettings.keyBindRight, mc.gameSettings.keyBindJump };
	    if ((mc.currentScreen instanceof GuiContainer) || (mc.currentScreen instanceof GuiGameOver))
	    {
	      KeyBinding[] arrayOfKeyBinding1;
	      int j = (arrayOfKeyBinding1 = moveKeys).length;
	      for (int i = 0; i < j; i++)
	      {
	        KeyBinding bind = arrayOfKeyBinding1[i];
	        bind.pressed = Keyboard.isKeyDown(bind.getKeyCode());
	      }
	    }
	    if (mc.currentScreen == null)
	    {
	      KeyBinding[] arrayOfKeyBinding1;
	      int j = (arrayOfKeyBinding1 = moveKeys).length;
	      for (int i = 0; i < j; i++)
	      {
	        KeyBinding bind = arrayOfKeyBinding1[i];
	        if (!Keyboard.isKeyDown(bind.getKeyCode())) {
	          KeyBinding.setKeyBindState(bind.getKeyCode(), false);
	        }
	      }
	    }
	    if ((mc.currentScreen instanceof GuiContainer) || (mc.currentScreen instanceof GuiGameOver))
	    {
	      if (Keyboard.isKeyDown(200)) {
	        Minecraft.thePlayer.rotationPitch -= 2.0F;
	      }
	      if (Keyboard.isKeyDown(208)) {
	        Minecraft.thePlayer.rotationPitch += 2.0F;
	      }
	      if (Keyboard.isKeyDown(203)) {
	        Minecraft.thePlayer.rotationYaw -= 2.0F;
	      }
	      if (Keyboard.isKeyDown(205)) {
	        Minecraft.thePlayer.rotationYaw += 2.0F;
	      }
	    }
		
		
	}
}
