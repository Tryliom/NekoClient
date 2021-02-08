package neko.module.modules.special;

import java.util.ArrayList;

import neko.module.Category;
import neko.module.Module;
import neko.utils.TimerUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class DropShit extends Module {
	private double delay = 500.0D;
	private TimerUtils time = new TimerUtils();
	private ArrayList<Integer> list = new ArrayList<>();
	private static DropShit instance;
	
	public DropShit() {
		super("DropShit", -1, Category.Special, false);
		instance=this;
	}
	
	public static DropShit getShit() {
		return instance;
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
		if (time.delay((float)this.delay)) {
			drop();
	        time.reset();						
		}	
		
	}
	
	public void drop() {
		for (int i = 9; i < 45; i++) {
			if (mc.thePlayer.inventoryContainer.getSlot(i).getStack() != null) {
				ItemStack is = mc.thePlayer.inventoryContainer.getSlot(i).getStack();
				Item item = is.getItem();
				
				if (canDrop(item)) {
					mc.playerController.windowClick(0, i, 0, 0, mc.thePlayer);
		        	mc.playerController.windowClick(0, -999, 0, 0, mc.thePlayer);
				}
			}
		}
		
		Container chest = mc.thePlayer.openContainer;
		
		if (chest != null) {
			for (int i = 0; i < chest.inventorySlots.size() - 36; i++) {
				Slot slot = chest.getSlot(i);
				
				if (slot != null 
						&& slot.getStack() != null 
						&& slot.getStack().getItem() != null 
						&& canDrop(slot.getStack().getItem())) {
					mc.playerController.windowClick(chest.windowId, i, 0, 0, mc.thePlayer);
		        	mc.playerController.windowClick(chest.windowId, -999, 0, 0, mc.thePlayer);
				}
			}
		}
	}
	
	public Boolean canDrop(Item item) {
		for (int j : list)
			if (Item.getIdFromItem(item) == j) {
				return true;
			}
		
		return false;
	}

	public double getDelay() {
		return delay;
	}

	public void setDelay(double delay) {
		this.delay = delay;
	}

	public TimerUtils getTime() {
		return time;
	}

	public void setTime(TimerUtils time) {
		this.time = time;
	}

	public ArrayList<Integer> getList() {
		return list;
	}

	public void setList(ArrayList<Integer> list) {
		this.list = list;
	}	
	
	
	
}
