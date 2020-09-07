package neko.module.modules.misc;

import neko.module.Category;
import neko.module.Module;
import net.minecraft.block.Block;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.client.C08PacketPlayerBlockPlacement;
import net.minecraft.util.BlockPos;

public class PlaceAndBreak extends Module {
	private int slotBreak = 9;
	private int slotPlace = 8;
	private ItemStack selectedItem;

	public PlaceAndBreak() {
		super("PlaceAndBreak", -1, Category.MISC, false);
	}
	
	public void onEnabled() {
		Slot slot = mc.thePlayer.inventoryContainer.getSlot(35+this.slotPlace);
		if (slot != null) {
			this.selectedItem = slot.getStack();
		}
		super.onEnabled();
	}
	
	public void onDisabled() {
		mc.gameSettings.keyBindSneak.pressed = false;
		mc.gameSettings.keyBindAttack.pressed = false;
		this.slotPlace = 8;
		super.onDisabled();
	}
	
	public void onUpdate() {
		try {
			Slot slot = mc.thePlayer.inventoryContainer.getSlot(35+this.slotPlace);
			if (slot.getHasStack()) {
				Block place = Block.getBlockFromItem(this.selectedItem.getItem());
				BlockPos bp = mc.objectMouseOver.func_178782_a();
				
				mc.gameSettings.keyBindSneak.pressed = true;
				if (bp != null && mc.theWorld.getBlockState(bp).getBlock().equals(place)) {
					mc.thePlayer.inventory.currentItem = this.slotBreak-1;
					mc.gameSettings.keyBindAttack.pressed = true;
				} else {
					mc.thePlayer.inventory.currentItem = this.slotPlace-1;
					mc.gameSettings.keyBindUseItem.pressed = true;
					mc.gameSettings.keyBindAttack.pressed = false;
				}
			} else {
				boolean found = false;
				for (int i=35;i<43;i++) {
					Slot nextSlot = mc.thePlayer.inventoryContainer.getSlot(i);
					if (nextSlot.getHasStack() && nextSlot.getStack().getItem().equals(this.selectedItem.getItem())) {
						System.out.println("Found: "+i+" : "+(i-35));
						found = true;
						this.slotPlace = i - 35;
					}
				}
				if (!found) {
					this.toggleModule();
				}
			}
		} catch(Exception e) {}
	}
}
