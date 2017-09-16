package neko.module.modules;

import neko.module.Category;
import neko.module.Module;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.network.play.client.C07PacketPlayerDigging;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;

public class Fastbow extends Module {
	private boolean nobow=false;
	private int packet=20;
	private boolean isOn=false;
	private static Fastbow instance = null;

	public Fastbow() {
		super("Fastbow", -1, Category.COMBAT);
		instance=this;
	}
	
	public static Fastbow getFast() {
		return instance;
	}
	
	public void onEnabled() {
		if (u.isLock(this.getName()))
			return;
		isOn=true;
		super.onEnabled();
	}
	
	public void onDisabled() {
		isOn=false;
		super.onDisabled();
	}		
	
	public void onUpdate() {
		if (u.isLock(this.getName())) {
			boolean display = u.display;
			u.display=false;
			this.isToggled=false;
			u.display=display;
			u.addWarn(this.getName());
			return;
		}
		
		if (!mc.thePlayer.isSneaking()) {
			isOn=true;			
		} else if (mc.thePlayer.isSneaking()) {
			isOn=false;
		}	
		
		if ((u.limite && u.nbPack>u.limit))
			return;
		try {		
			try {
				Item i = mc.thePlayer.getCurrentEquippedItem().getItem();
				if ((i instanceof ItemBlock) || ((i instanceof ItemFood) && mc.thePlayer.getFoodStats().needFood()))
					return;
			} catch (Exception e) {
				return;
			}
			
			if (mc.thePlayer.isUsingItem() && Item.getIdFromItem(mc.thePlayer.getCurrentEquippedItem().getItem())==261) {
				for (int i=0;i<this.packet;i++) {
					mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer());
				}
				mc.getNetHandler().addToSendQueue(new C07PacketPlayerDigging(C07PacketPlayerDigging.Action.RELEASE_USE_ITEM, BlockPos.ORIGIN, EnumFacing.DOWN));			      
				mc.thePlayer.stopUsingItem();
			} else if (mc.gameSettings.keyBindUseItem.pressed && nobow && isOn && !mc.thePlayer.getFoodStats().needFood()) {
				int actual = mc.thePlayer.inventory.currentItem;
				int bow = getBestBow();
				if (!hasArrow() || bow<0)
					return;
				swap(actual, bow);
				mc.rightClickMouse();
				for (int i=0;i<this.packet;i++) {
					mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer());
				}
				mc.getNetHandler().addToSendQueue(new C07PacketPlayerDigging(C07PacketPlayerDigging.Action.RELEASE_USE_ITEM, BlockPos.ORIGIN, EnumFacing.DOWN));			      
				mc.thePlayer.stopUsingItem();
				swap(actual, bow);
				mc.playerController.syncCurrentPlayItem();
			}
		} catch (Exception ex) {}
	}
	
	private void swap(int prochainItem, int ancienItem) {
		mc.playerController.windowClick(mc.thePlayer.inventoryContainer.windowId, ancienItem, prochainItem, 2, mc.thePlayer);
	}
	
	private int getBestBow() {
		java.util.ArrayList<Integer> list = new java.util.ArrayList<>();
		for (int i = 9; i < 45; i++) {
			if (mc.thePlayer.inventoryContainer.getSlot(i) != null) {
				ItemStack is = mc.thePlayer.inventoryContainer.getSlot(i).getStack();
				if (is!=null) {
					Item item = is.getItem();
					if (Item.getIdFromItem(item) == 261) {
						list.add(i);
					}
				}
			}
		}
		int bestSlot=-1;
		int totEnch=-1;
		int bestPower=-1;
		for (Integer i : list) {
			ItemBow item =(ItemBow) mc.thePlayer.inventoryContainer.getSlot(i).getStack().getItem();
			int lvl = 0;
			int count = 0;
			ItemStack is = mc.thePlayer.inventoryContainer.getSlot(i).getStack();
			if (is.isItemEnchanted()) {
				 NBTTagList NBTenchList = is.getEnchantmentTagList();
	              for (int o = 0; o < NBTenchList.tagCount(); o++) {
	            	  count++;
	            	  short id = NBTenchList.getCompoundTagAt(o).getShort("id");
	                  short niv = NBTenchList.getCompoundTagAt(o).getShort("lvl");
	                  if (Enchantment.func_180306_c(id).getTranslatedName(1).equalsIgnoreCase("Power I")) {
	                	  lvl = niv;
	                  }
	              }
			}
			if (bestPower<lvl || (bestPower==lvl && totEnch<count)) {
				bestSlot=i;
				totEnch=count;
				bestPower=lvl;
			}
			
		}
		
		return bestSlot;	
	}
	
	private boolean hasArrow() {
		for (int i = 9; i < 45; i++) {
			if (mc.thePlayer.inventoryContainer.getSlot(i) != null) {
				ItemStack is = mc.thePlayer.inventoryContainer.getSlot(i).getStack();
				if (is!=null) {
					Item item = is.getItem();
					if (Item.getIdFromItem(item) == 262) {
						return true;
					}
				}
			}
		}
		return false;	
	}

	public boolean isNobow() {
		return nobow;
	}

	public void setNobow(boolean nobow) {
		this.nobow = nobow;
	}

	public int getPacket() {
		return packet;
	}

	public void setPacket(int packet) {
		this.packet = packet;
	}
}
