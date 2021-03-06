package neko.module.modules.player;

import neko.module.Category;
import neko.module.Module;
import neko.utils.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

public class Autoarmor extends Module {
	Minecraft mc = Minecraft.getMinecraft();
	private int[] bestArmor;
	public static boolean ec=false;
	int delay=0;
	private int num=5;
	int j;
	double maxValue=-1;
	double mv;
	int item=-1;
	private final int[] boots = { 313, 309, 317, 305, 301 };
	private final int[] chestplate = { 311, 307, 315, 303, 299 };
	private final int[] helmet = { 310, 306, 314, 302, 298 };
	private final int[] leggings = { 312, 308, 316, 304, 300 };
	
	public Autoarmor() {
		super("Autoarmor", -1, Category.PLAYER, false);
	}
	
	public void onEnabled() {
		super.onEnabled();
	}
	
	public void onDisabled() {
		super.onDisabled();
	}
	
	public void setValues() {
		this.values = "?6D?sactiv? sur ec auto:?7 "+Utils.displayBool(ec);
	}
	
	public void onUpdate() {		
		if ((mc.thePlayer.capabilities.isCreativeMode) || (mc.thePlayer.openContainer != null) && 
		        (mc.thePlayer.openContainer.windowId != 0)) {
		        return;
		      }
		
		if (delay>=5) {
			delay=0;
	      maxValue=-1;
	      item=-1;
	      for (int i=9;i<45;i++) {
	    	  if ((mc.thePlayer.inventoryContainer.getSlot(i).getStack()!=null)) {
	    		  if (canEquip(mc.thePlayer.inventoryContainer.getSlot(i).getStack())!=-1 && (canEquip(mc.thePlayer.inventoryContainer.getSlot(i).getStack())==num))
	    			  change(num, i);    	  		    	  
	    	  }
	      }
	      if (item!=-1) {
	    	  if (mc.thePlayer.inventoryContainer.getSlot(item).getStack()!=null)
	    		  mc.playerController.windowClick(0, num, 0, 1, mc.thePlayer);
	    	  mc.playerController.windowClick(0, item, 0, 1, mc.thePlayer);
	    	  delay=0;
	      }
	      if (num==8) {
	    	  num=5;
	      } else {
	    	  num++;
	      }
	      
	      	
	} else
		delay++;
		
	}
	
	private void change(int numy, int i) { 
		  if (maxValue==-1) {			  
			  if (mc.thePlayer.inventoryContainer.getSlot(numy).getStack()!=null) {
				  mv = getProtValue(mc.thePlayer.inventoryContainer.getSlot(numy).getStack());
			  } else
				  mv = maxValue;  
		  } else {
			  mv = maxValue;
		  }   		  
		  if (mv <= getProtValue(mc.thePlayer.inventoryContainer.getSlot(i).getStack())) {	
			  if (mv == getProtValue(mc.thePlayer.inventoryContainer.getSlot(i).getStack())) {	
				  int currentD = (mc.thePlayer.inventoryContainer.getSlot(numy).getStack()!=null ? mc.thePlayer.inventoryContainer.getSlot(numy).getStack().getItemDamage() : 999);
				  int newD = (mc.thePlayer.inventoryContainer.getSlot(i).getStack()!=null ? mc.thePlayer.inventoryContainer.getSlot(i).getStack().getItemDamage() : 500);
				  if (newD <= currentD) {
					  if (newD == currentD) {} else {
						  item = i;
						  maxValue = getProtValue(mc.thePlayer.inventoryContainer.getSlot(i).getStack());
					  }
				  }
			  } else {
				  item = i;
				  maxValue = getProtValue(mc.thePlayer.inventoryContainer.getSlot(i).getStack());
			  }
		  }	    	  
	}	
	
	  
	  private int canEquip(ItemStack stack)
	  {
		  for (int id : this.boots)
			  if (stack.getItem().getIdFromItem(stack.getItem()) == id) {
				  return 8;
			  }
		  for (int id : this.leggings)
			  if (stack.getItem().getIdFromItem(stack.getItem()) == id) {
				  return 7;
			  }
		  for (int id : this.chestplate)
			  if (stack.getItem().getIdFromItem(stack.getItem()) == id) {
				  return 6;
			  }
		  for (int id : this.helmet)
			  if (stack.getItem().getIdFromItem(stack.getItem()) == id) {
				  return 5;
			  }
		  
		  return -1;		  	   
	  }
	  
	  private double getProtValue(ItemStack stack)
	  {
		  try {
		  if (stack!=null)
	    return ((ItemArmor)stack.getItem()).damageReduceAmount + (100 - ((ItemArmor)stack.getItem()).damageReduceAmount * 4) * EnchantmentHelper.getEnchantmentLevel(Enchantment.field_180310_c.effectId, stack) * 4 * 0.0075D;
		  else
			  return 0;
		  } catch (Exception ex) {
			  return 0;
		  }
	  }		  	
	
	public static String getEc() {
		return ec ? "non" : "oui";
	}
	

}
