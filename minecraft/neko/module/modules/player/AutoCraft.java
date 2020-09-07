package neko.module.modules.player;

import java.util.List;

import org.lwjgl.opengl.GL11;

import neko.module.Category;
import neko.module.Module;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.ShapedRecipes;

public class AutoCraft extends Module {
	private boolean craftable = true;
	private boolean instant = true;
	private int page = 1;
	private static AutoCraft instance = null;
	
	public AutoCraft() {
		super("AutoCraft", -1, Category.PLAYER, false);
		instance = this;
	}
	
	public static AutoCraft getInstance() {
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
	
	public boolean isCraftable() {
		return craftable;
	}

	public void setCraftable(boolean craftable) {
		this.craftable = craftable;
	}

	public boolean isInstant() {
		return instant;
	}

	public void setInstant(boolean instant) {
		this.instant = instant;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public void craftRecipe(ShapedRecipes recipe) {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				int width = recipe.getRecipeWidth();
				int height = recipe.getRecipeHeight();
				int i = 0;
				int containerID = mc.thePlayer.openContainer.windowId;
				for (int h = 0;h<=height-1;h++) {
					for (int w = 1;w<=width;w++) {
						ItemStack target = recipe.getRecipeItems()[i];
						int itemSlot = 0;
						
						if (target != null) {
							for (int in=9;in<45;in++) {
								ItemStack item = mc.thePlayer.inventoryContainer.getSlot(in).getStack();
								
								if (item !=null && item.getItem() == target.getItem()) {
									itemSlot = in+1;
									mc.playerController.windowClick(containerID, itemSlot, 0, 0, mc.thePlayer);
									break;
								}
						    }
							
							try {
								Thread.sleep(20);
							} catch (InterruptedException e) {}
							
							int slot = h*3 + w;
							//mc.playerController.windowClick(containerID, slot, 1, 0, mc.thePlayer);
							mc.playerController.windowClick(containerID, slot, 0, 0, mc.thePlayer); // Put entire stack
							
							try {
								Thread.sleep(20);
							} catch (InterruptedException e) {}
							
							mc.playerController.windowClick(containerID, itemSlot, 0, 0, mc.thePlayer);
								
						}
						i++;
						try {
							Thread.sleep(500);
						} catch (InterruptedException e) {}
					}
				}
				if (AutoCraft.getInstance().isInstant())
					mc.playerController.windowClick(containerID, 0, 0, 1, mc.thePlayer);
				i = 0;
			}
		}).start();
	}
	
	/**
	 * 
	 * @param x
	 * @param initX
	 * @param y
	 * @param width
	 * @param buttonList
	 * @param sort 1 equals Armor and Sword | 2 equals Tool
	 * @return
	 */
	public int drawItems(int x, int initX, int y, int width, List<GuiButton> buttonList, int sort) {
		CraftingManager cm = CraftingManager.getInstance();
		int maxRecipeByPage = this.getMaxRecipeByPage();
		int i = 1;

		if (sort == 3)
			if (this.getPage() != 1 && maxRecipeByPage>this.getMaxRecipe())
				this.setPage(1);
		
		for (Object o : cm.getRecipeList()) {
    		if (o instanceof ShapedRecipes) {
    			if (sort == 3)
	    			if (i<(this.getPage()-1)*maxRecipeByPage) {
	    				i++;
	    				continue;
	    			} else if (i>this.getPage()*maxRecipeByPage) {
	    				i++;
	    				break;
	    			}
    			
    			ShapedRecipes recipe = (ShapedRecipes)o;
    			boolean valid = (this.craftable && sort==3) ? isItemCraftable(recipe) : true;
    			
    			if (valid && this.checkItem(recipe.getRecipeOutput().getItem(), sort)) {
    				int id = recipe.getRecipeOutput().hashCode();
    				
    	            GL11.glPushMatrix();
    				this.mc.entityRenderer.setupOverlayRendering();
    				RenderHelper.enableGUIStandardItemLighting();
					mc.getRenderItem().renderItemIntoGUI(recipe.getRecipeOutput(), width - x + 2, y + 2);
					RenderHelper.disableStandardItemLighting();
    	            GL11.glPopMatrix();
    	            
    	            buttonList.add(new GuiButton(id,width - x, y, 20, 20, ""));
					if (x <= 25 + (sort == 3 ? 25 : 0)) {
        				y += 20;
        				x = initX;
					} else
						x -= 20;
					i++;
    			}
    		}
    	}
		return y;
	}
	
	public boolean isItemCraftable(ShapedRecipes recipe) {
		boolean valid = true;
		
		for (ItemStack is : recipe.getRecipeItems()) {
			boolean found = false;
			if (is != null) {
				for (int in=9;in<45;in++) {
					ItemStack item = mc.thePlayer.inventoryContainer.getSlot(in).getStack();
					if (item !=null && item.getItem() == is.getItem()) {
						found = true;
					}
			    }
			}
			if (!found && is != null)
				valid = false;
		}
		
		return valid;
	}
	
	public boolean checkItem(Item item, int sort) {
		if (sort == 1)
			return item instanceof ItemArmor || item instanceof ItemSword;
		if (sort == 2)
			return item instanceof ItemTool;
		if (sort == 3)
			return item instanceof Item && !(item instanceof ItemTool || item instanceof ItemArmor || item instanceof ItemSword);
		
		return false;
	}
	
	public int getMaxRecipeByPage() {
		ScaledResolution scale = new ScaledResolution(mc);
    	int y = 40;
    	int x = scale.getScaledWidth() / 4;
    	int i = 0;
    	for (i = 0;y < scale.getScaledHeight() - 40;i++) {
    		if (x <= 50) {
				y += 20;
				x = scale.getScaledWidth() / 4;
			} else {
				x -= 20;
			}
    	}

		return i;
	}
	
	
	public int getMaxRecipe() {
		int i = 0;
		for (Object o : CraftingManager.getInstance().getRecipeList()) {
    		if (o instanceof ShapedRecipes) {
    			ShapedRecipes recipe = (ShapedRecipes)o;
    			boolean valid = (this.craftable) ? isItemCraftable(recipe) : true;
    			if (valid && this.checkItem(recipe.getRecipeOutput().getItem(), 3))
    				i++;
    		}
    	}
		
		return i;
	}
}
