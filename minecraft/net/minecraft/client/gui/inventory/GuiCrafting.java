package net.minecraft.client.gui.inventory;

import java.io.IOException;

import neko.Client;
import neko.module.modules.player.AutoCraft;
import neko.utils.Utils;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ContainerWorkbench;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class GuiCrafting extends GuiContainer
{
    private static final ResourceLocation craftingTableGuiTextures = new ResourceLocation("textures/gui/container/crafting_table.png");

    public GuiCrafting(InventoryPlayer p_i45504_1_, World worldIn)
    {
        this(p_i45504_1_, worldIn, BlockPos.ORIGIN);
    }

    public GuiCrafting(InventoryPlayer p_i45505_1_, World worldIn, BlockPos p_i45505_3_)
    {
        super(new ContainerWorkbench(p_i45505_1_, worldIn, p_i45505_3_));
    }

    /**
     * Draw the foreground layer for the GuiContainer (everything in front of the items). Args : mouseX, mouseY
     */
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
    {
        this.fontRendererObj.drawString(I18n.format("container.crafting", new Object[0]), 28, 6, 4210752);
        this.fontRendererObj.drawString(I18n.format("container.inventory", new Object[0]), 8, this.ySize - 96 + 2, 4210752);
        if (Utils.isToggle("AutoCraft")) {
        	this.buttonList.clear();
        	int y = 10;
        	int initX = this.width / 4;
        	int x = initX;
        	AutoCraft craft = AutoCraft.getInstance();
        	
        	y = craft.drawItems(x, initX, y, initX, buttonList, 1) + 30;
        	craft.drawItems(x, initX, y, initX, buttonList, 2);
        	y = 40;
        	initX = (this.width / 4);
        	x = initX;
        	craft.drawItems(x, initX, y, this.width, buttonList, 3);
        	this.buttonList.add(new GuiButton(0, this.width - 110, 15, 100, 20, "Mode "+(craft.isCraftable() ? "Craftable" : "All")));
        	this.buttonList.add(new GuiButton(1, this.width - 220, 15, 100, 20, (craft.isInstant() ? "§a" : "§c")+"Flash"));
        	if (craft.getPage()>1)
        		this.buttonList.add(new GuiButton(2, this.width - this.width/8 - 35, this.height - 25, 30, 20, "-"));
        	if (craft.getPage()<craft.getMaxRecipe()/craft.getMaxRecipeByPage())
        		this.buttonList.add(new GuiButton(3, this.width - this.width/8 - 5, this.height - 25, 30, 20, "+"));
    	}
    }
    
    protected void actionPerformed(GuiButton button) throws IOException
    {
    	AutoCraft craft = AutoCraft.getInstance();
    	try {
        	CraftingManager cm = CraftingManager.getInstance();
        	for (Object o : cm.getRecipeList()) {
        		if (o instanceof ShapedRecipes) {
        			ShapedRecipes recipe = (ShapedRecipes)o;
        			if (recipe.getRecipeOutput().hashCode() == button.id) {
        				craft.craftRecipe(recipe);
        			}
        		}
        	}
    	} catch (Exception e) {}
    	
    	switch (button.id) {
    		case 0: 
    			craft.setCraftable(!craft.isCraftable());
    			break;
    		case 1: 
    			craft.setInstant(!craft.isInstant());
    			break;
    		case 2:
    			craft.setPage(craft.getPage()-1);
    			break;
    		case 3:
    			craft.setPage(craft.getPage()+1);
    			break;
    	}
    }

    /**
     * Args : renderPartialTicks, mouseX, mouseY
     */
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
    {
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(craftingTableGuiTextures);
        int var4 = (this.width - this.xSize) / 2;
        int var5 = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(var4, var5, 0, 0, this.xSize, this.ySize);
    }
}
