package neko.module.modules.combat;

import neko.module.Category;
import neko.module.Module;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.Item;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.nbt.NBTTagList;

public class AutoSword extends Module {
	private static AutoSword instance = null;
	
	public static AutoSword getSword() {
		return instance;
	}
	
	public AutoSword() {
		super("AutoSword", -1, Category.COMBAT, false);
		instance = this;
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
	
	public void onClick() {
		boolean good=false;
		if (mc.thePlayer.isSneaking()) {
			good=true;
		}
		try {
			int swordSlot = getBestWeaponFromInventory();
			if (swordSlot!=mc.thePlayer.inventory.currentItem && (swordSlot != -1) && good) {
				int prevSlot = mc.thePlayer.inventory.currentItem;
				swap(swordSlot, prevSlot);		
				mc.playerController.syncCurrentPlayItem();
			}	
		} catch (Exception e) {}
		
	}
	
	private void swap(int one, int two) {
		this.mc.playerController.windowClick(this.mc.thePlayer.inventoryContainer.windowId, one, two, 2, this.mc.thePlayer);
	}
	  
	/**
	 * Prend la meilleure des armes ou outils de l'inventaire en comparant avec le matériaux et les enchantements
	 * @return	Le slot de la meilleure arme/outil<br>-1 si rien trouvé
	 */
	public int getBestWeaponFromInventory() {
		java.util.ArrayList<Integer> list = new java.util.ArrayList<>();
		for (int i = 9; i < 45; i++) {
			if (mc.thePlayer.inventoryContainer.getSlot(i).getStack() != null) {
				ItemStack is = mc.thePlayer.inventoryContainer.getSlot(i).getStack();
				Item item = is.getItem();
				if (item instanceof ItemSword || item instanceof ItemTool) {
					list.add(i);
				}
			}
		}
		if (list.isEmpty()) {		
			return -1;
		} else {
			int choose = -1;
			double bestDamage=-1;
			int totalEnchant=-1;
			for (Integer i : list) {
				Item it = mc.thePlayer.inventoryContainer.getSlot(i).getStack().getItem();
				String material;
				if (it instanceof ItemTool)
					material = ((ItemTool) it).getToolMaterialName().toLowerCase();
				else if (it instanceof ItemSword)
					material = ((ItemSword) it).getToolMaterialName().toLowerCase();
				else
					continue;
				
				
				int lvl = 0;
				double totalDamage=0;
				int count = 0;
				ItemStack is = mc.thePlayer.inventoryContainer.getSlot(i).getStack();
				if (is.isItemEnchanted()) {
					 NBTTagList NBTenchList = is.getEnchantmentTagList();
		              for (int o = 0; o < NBTenchList.tagCount(); o++) {
		            	  count++;
		            	  short id = NBTenchList.getCompoundTagAt(o).getShort("id");
		                  short niv = NBTenchList.getCompoundTagAt(o).getShort("lvl");
		                  if (Enchantment.func_180306_c(id).getName().equalsIgnoreCase("enchantment.damage.all")) {
		                	  lvl = niv;
		                  }
		              }
				}
				// emerald = diamond
				if (material.equalsIgnoreCase("emerald")) {
					totalDamage+=7;
				} else if (material.equalsIgnoreCase("iron")) {
					totalDamage+=6;
				} else if (material.equalsIgnoreCase("gold")) {
					totalDamage+=4;
				} else if (material.equalsIgnoreCase("stone")) {
					totalDamage+=5;
				} else if (material.equalsIgnoreCase("wood")) {
					totalDamage+=4;				
				}
				if (it instanceof ItemAxe)
					totalDamage-=1;
				if (it instanceof ItemPickaxe)
					totalDamage-=2;
				if (it instanceof ItemSpade)
					totalDamage-=3;
				totalDamage+=lvl*1.25;
				if (bestDamage<totalDamage || (bestDamage==totalDamage && count>totalEnchant)) {
					bestDamage=totalDamage;
					choose=i;
					totalEnchant=count;
				}
			}
			return choose;
		}
	}		
	
}
