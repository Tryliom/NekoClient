package neko.module.modules;

import java.util.ArrayList;

import neko.module.Category;
import neko.module.Module;
import neko.utils.Utils;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;

public class DropShit extends Module {
	private double delay = 500.0D;
	private TimerUtils time = new TimerUtils();
	private ArrayList<Integer> list = new ArrayList<>();
	private static DropShit instance;
	
	public DropShit() {
		super("DropShit", -1, Category.Special);
		instance=this;
	}
	
	public static DropShit getShit() {
		return instance;
	}
	
	public void onEnabled() {		
		if (u.display)
		u.addChat("§a§oDropShit activé !");
		super.onEnabled();
	}
	
	public void onDisabled() {
		if (u.display)
		u.addChat("§c§oDropShit désactivé !");
		super.onDisabled();
	}
	
	public void onUpdate() {	
		if (time.delay((float)this.delay)) {
			drop();
	        time.reset();						
		}	
		
	}
	
	private void drop() {
		for (int i = 9; i < 45; i++) {
			if (mc.thePlayer.inventoryContainer.getSlot(i).getStack() != null) {
				ItemStack is = mc.thePlayer.inventoryContainer.getSlot(i).getStack();
				Item item = is.getItem();
				for (int j : list)
					if (Item.getIdFromItem(item) == j) {
						mc.playerController.windowClick(0, i, 0, 0, mc.thePlayer);
			        	mc.playerController.windowClick(0, -999, 0, 0, mc.thePlayer);
					}
			}
		}
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
