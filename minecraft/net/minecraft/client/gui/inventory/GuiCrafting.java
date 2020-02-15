package net.minecraft.client.gui.inventory;

import java.io.IOException;

import org.lwjgl.opengl.GL11;

import neko.utils.Utils;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ContainerWorkbench;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
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
        	try {
            	CraftingManager cm = CraftingManager.getInstance();
            	int y = 10;
            	int initX = this.width / 4;
            	int x = initX;
            	for (Object o : cm.getRecipeList()) {
            		if (o instanceof ShapedRecipes) {
            			ShapedRecipes sr = (ShapedRecipes)o;
            			if (sr.getRecipeOutput().getItem() instanceof ItemArmor
            					|| sr.getRecipeOutput().getItem() instanceof ItemSword
            					//|| sr.getRecipeOutput().getItem() instanceof ItemTool
            					) {
            				int id = sr.getRecipeOutput().hashCode();
            				
            	            GL11.glPushMatrix();
            				this.mc.entityRenderer.setupOverlayRendering();
							mc.getRenderItem().renderItemIntoGUI(sr.getRecipeOutput(), this.width - x + 2, y + 2);
            	            GL11.glPopMatrix();
            	            
            	            this.buttonList.add(new GuiButton(id, this.width - x, y, 20, 20, ""));
							if (x <= 20) {
	            				y += 20;
	            				x = initX;
							} else
								x -= 20;
            			}
            		}
            	}
        	} catch (Exception e) {}
    	}
    }
    
    protected void actionPerformed(GuiButton button) throws IOException
    {
    	try {
        	CraftingManager cm = CraftingManager.getInstance();
        	for (Object o : cm.getRecipeList()) {
        		if (o instanceof ShapedRecipes) {
        			ShapedRecipes sr = (ShapedRecipes)o;
        			if (sr.getRecipeOutput().hashCode() == button.id) {
        				System.out.println(sr.getRecipeSize());
            			for (ItemStack is : sr.getRecipeItems())
        					System.out.println(is);
        			}
        		}
        	}
    	} catch (Exception e) {}
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
