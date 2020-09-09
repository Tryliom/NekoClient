package neko.module.modules.misc;

import neko.Client;
import neko.guicheat.clickgui.settings.Setting;
import neko.module.Category;
import neko.module.Module;
import net.minecraft.block.Block;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;

public class PlaceAndBreak extends Module {
	private static PlaceAndBreak instance;
	private int slotBreak = 9;
	private int slotPlace = 8;
	private boolean auto = false;
	
	private int currentSlotPlace;
	private ItemStack selectedItem;

	public PlaceAndBreak() {
		super("PlaceAndBreak", -1, Category.MISC, false);
		this.instance = this;
	}
	
	public static PlaceAndBreak getInstance() {
		return instance;
	}
	
	@Override
	public void setup() {
		Client.getNeko().settingsManager.rSetting(new Setting("PlaceAndBreak_Break slot", this, this.slotBreak, 1, 9, true));
		Client.getNeko().settingsManager.rSetting(new Setting("PlaceAndBreak_Place slot", this, this.slotPlace, 1, 9, true));
		Client.getNeko().settingsManager.rSetting(new Setting("PlaceAndBreak_Auto", this, this.auto));
	}
	
	public void onEnabled() {
		Slot slot = mc.thePlayer.inventoryContainer.getSlot(35+this.slotPlace);
		if (slot != null) {
			this.selectedItem = slot.getStack();
		}
		this.currentSlotPlace = this.slotPlace;
		super.onEnabled();
	}
	
	public void onDisabled() {
		mc.gameSettings.keyBindSneak.pressed = false;
		mc.gameSettings.keyBindAttack.pressed = false;
		super.onDisabled();
	}
	
	public void onUpdate() {
		try {
			Slot slot = mc.thePlayer.inventoryContainer.getSlot(35+this.currentSlotPlace);
			if (slot.getHasStack()) {
				Block place = Block.getBlockFromItem(this.selectedItem.getItem());
				BlockPos bp = mc.objectMouseOver.func_178782_a();
				
				mc.gameSettings.keyBindSneak.pressed = true;
				if (bp != null && mc.theWorld.getBlockState(bp).getBlock().equals(place)) {
					mc.thePlayer.inventory.currentItem = this.slotBreak-1;
					mc.gameSettings.keyBindAttack.pressed = true;
				} else {
					mc.thePlayer.inventory.currentItem = this.currentSlotPlace-1;
					mc.gameSettings.keyBindUseItem.pressed = true;
					mc.gameSettings.keyBindAttack.pressed = false;
				}
			} else if (this.isAuto()) {
				boolean found = false;
				
				for (int i=35;i<43;i++) {
					Slot nextSlot = mc.thePlayer.inventoryContainer.getSlot(i);
					if (nextSlot.getHasStack() && nextSlot.getStack().getItem().equals(this.selectedItem.getItem())) {
						found = true;
						this.currentSlotPlace = i - 35;
					}
				}
				
				if (!found) {
					this.toggleModule();
				}
			}
		} catch(Exception e) {}
	}

	public int getSlotBreak() {
		return slotBreak;
	}

	public void setSlotBreak(int slotBreak) {
		this.slotBreak = slotBreak;
	}

	public int getSlotPlace() {
		return slotPlace;
	}

	public void setSlotPlace(int slotPlace) {
		this.slotPlace = slotPlace;
	}

	public boolean isAuto() {
		return auto;
	}

	public void setAuto(boolean auto) {
		this.auto = auto;
	}
	
	
}
