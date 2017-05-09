package neko.module.modules;

import org.lwjgl.input.Keyboard;

import neko.module.Category;
import neko.module.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiChest;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.network.play.client.C0DPacketCloseWindow;

public class Cheststealer extends Module {
	
	private int cooldown;
	public static int waitTime=3;
	Minecraft mc = Minecraft.getMinecraft();
	
	public Cheststealer() {
		super("Cheststealer", Keyboard.KEY_NONE, Category.PLAYER);
	}
	
	public void onEnabled() {		
		if (u.display)
		u.addChat("§a§oCheststealer activé !");
		super.onEnabled();
	}
	
	public void onDisabled() {
		if (u.display)
		u.addChat("§c§oCheststealer désactivé !");
		super.onDisabled();
	}
	
	public void onUpdate() {
		if (!this.getToggled())
			return;
		
		if (this.cooldown > 0)
	    {
	      this.cooldown -= 1;
	      return;
	    }
	    if ((Minecraft.currentScreen instanceof GuiChest))
	    {
	      EntityPlayerSP player = Minecraft.thePlayer;
	      Container chest = player.openContainer;
	      boolean hasSpace = false;
	      for (int i = chest.inventorySlots.size() - 36; i < chest.inventorySlots.size(); i++)
	      {
	        Slot slot = chest.getSlot(i);
	        if ((slot == null) || (!slot.getHasStack()))
	        {
	          hasSpace = true;
	          break;
	        }
	      }
	      if (!hasSpace) {
	        return;
	      }
	      while (this.cooldown == 0)
	      {
	        boolean item_found = false;
	        for (int i = 0; i < chest.inventorySlots.size() - 36; i++)
	        {
	          Slot slot = chest.getSlot(i);
	          if ((slot.getHasStack()) && (slot.getStack() != null))
	          {
	            Minecraft.playerController.windowClick(chest.windowId, i, 0, 1, player);
	            this.cooldown = waitTime;
	            item_found = true;
	            break;
	          }
	        }
	        if (!item_found)
	        {
	          mc.displayGuiScreen((GuiScreen)null);
	          player.sendQueue.addToSendQueue(new C0DPacketCloseWindow(chest.windowId));
	          break;
	        }
	        hasSpace = false;
	        for (int i = chest.inventorySlots.size() - 36; i < chest.inventorySlots.size(); i++)
	        {
	          Slot slot = chest.getSlot(i);
	          if ((slot == null) || (!slot.getHasStack()))
	          {
	            hasSpace = true;
	            break;
	          }
	        }
	        if (!hasSpace) {
	          return;
	        }
	      }
	    }
		
		
		
		
		
		
		
	}
}