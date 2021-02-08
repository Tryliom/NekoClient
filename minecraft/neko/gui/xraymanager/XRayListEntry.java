package neko.gui.xraymanager;

import java.util.List;
import java.util.Vector;

import neko.module.modules.render.Xray;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiListExtended;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

public abstract class XRayListEntry implements GuiListExtended.IGuiListEntry {
	
	protected final Minecraft mc;
	protected final GuiScreenXrayManager guiScreenXrayManager;
	private static final ResourceLocation field_148316_c = new ResourceLocation("textures/gui/resource_packs.png");
	
	public XRayListEntry(GuiScreenXrayManager gsxmn) {
		this.guiScreenXrayManager = gsxmn;
		this.mc = Minecraft.getMinecraft();
	}
	
	public void drawEntry(int slotIndex, int x, int y, int listWidth, int slotHeight, int mouseX, int mouseY, boolean isSelected)
    {
		this.BindIcon();
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        Gui.drawModalRectWithCustomSizedTexture(x, y, 0.0F, 0.0F, 32, 32, 32.0F, 32.0F);
        int var10;
        
        if ((this.mc.gameSettings.touchscreen || isSelected) && this.func_148310_d())
        {
        	this.mc.getTextureManager().bindTexture(field_148316_c);
            Gui.drawRect(x, y, x + 32, y + 32, -1601138544);
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            int var9 = mouseX - x;
            var10 = mouseY - y;
            
            if (this.func_148309_e())
            {
                if (var9 < 32)
                {
                    Gui.drawModalRectWithCustomSizedTexture(x, y, 0.0F, 32.0F, 32, 32, 256.0F, 256.0F);
                }
                else
                {
                    Gui.drawModalRectWithCustomSizedTexture(x, y, 0.0F, 0.0F, 32, 32, 256.0F, 256.0F);
                }
            }
            else
            {
                if (this.func_148308_f())
                {
                    if (var9 < 16)
                    {
                        Gui.drawModalRectWithCustomSizedTexture(x, y, 32.0F, 32.0F, 32, 32, 256.0F, 256.0F);
                    }
                    else
                    {
                        Gui.drawModalRectWithCustomSizedTexture(x, y, 32.0F, 0.0F, 32, 32, 256.0F, 256.0F);
                    }
                }

                if (this.func_148314_g())
                {
                    if (var9 < 32 && var9 > 16 && var10 < 16)
                    {
                        Gui.drawModalRectWithCustomSizedTexture(x, y, 96.0F, 32.0F, 32, 32, 256.0F, 256.0F);
                    }
                    else
                    {
                        Gui.drawModalRectWithCustomSizedTexture(x, y, 96.0F, 0.0F, 32, 32, 256.0F, 256.0F);
                    }
                }

                if (this.func_148307_h())
                {
                    if (var9 < 32 && var9 > 16 && var10 > 16)
                    {
                        Gui.drawModalRectWithCustomSizedTexture(x, y, 64.0F, 32.0F, 32, 32, 256.0F, 256.0F);
                    }
                    else
                    {
                        Gui.drawModalRectWithCustomSizedTexture(x, y, 64.0F, 0.0F, 32, 32, 256.0F, 256.0F);
                    }
                }
            }
        }
        String var13 = this.XrayBlockName();
        var10 = this.mc.fontRendererObj.getStringWidth(var13);

        if (var10 > 157)
        {
            var13 = this.mc.fontRendererObj.trimStringToWidth(var13, 157 - this.mc.fontRendererObj.getStringWidth("...")) + "...";
        }
        this.mc.fontRendererObj.func_175063_a(var13, (float)(x + 32 + 2), (float)(y + 1), 16777215);
        List var11 = this.mc.fontRendererObj.listFormattedStringToWidth(this.XrayBlockDescription(), 157);

        for (int var12 = 0; var12 < 2 && var12 < var11.size(); ++var12)
        {
            this.mc.fontRendererObj.func_175063_a((String)var11.get(var12), (float)(x + 32 + 2), (float)(y + 12 + 10 * var12), 8421504);
        }
    }
	
	protected abstract String XrayBlockDescription();

    protected abstract String XrayBlockName();

    protected abstract void BindIcon();
    
    protected abstract Block getBlock();

    protected boolean func_148310_d()
    {
        return true;
    }
    
    protected boolean func_148309_e()
    {
        return !this.guiScreenXrayManager.hasXraySelectedEntry(this);
    }

    protected boolean func_148308_f()
    {
        return this.guiScreenXrayManager.hasXraySelectedEntry(this);
    }
    
    protected boolean func_148314_g()
    {
        List var1 = this.guiScreenXrayManager.getListContaining(this);
        int var2 = var1.indexOf(this);
        return var2 > 0 && ((XRayListEntry)var1.get(var2 - 1)).func_148310_d();
    }
    
    protected boolean func_148307_h()
    {
        List var1 = this.guiScreenXrayManager.getListContaining(this);
        int var2 = var1.indexOf(this);
        return var2 >= 0 && var2 < var1.size() - 1 && ((XRayListEntry)var1.get(var2 + 1)).func_148310_d();
    }
    
    
    
    public boolean mousePressed(int p_148278_1_, int p_148278_2_, int p_148278_3_, int p_148278_4_, int p_148278_5_, int p_148278_6_)
    {
        if (this.func_148310_d() && p_148278_5_ <= 32)
        {
        	Xray xray = Xray.getXray();
			Vector<Integer> list = xray.getList();
            if (this.func_148309_e())
            {
                this.guiScreenXrayManager.getListContaining(this).remove(this);
                this.guiScreenXrayManager.getSelectedXrayBlocs().add(0, this);
                Xray.getXray().getList().add(Block.getIdFromBlock(this.getBlock()));
                return true;
            }

            if (p_148278_5_ < 16 && this.func_148308_f())
            {
                this.guiScreenXrayManager.getListContaining(this).remove(this);
                this.guiScreenXrayManager.getAvailableXrayBlocs().add(0, this);
                Xray.getXray().getList().removeElement(Block.getIdFromBlock(this.getBlock()));
                this.guiScreenXrayManager.markChanged();
                return true;
            }

            List var7;
            int var8;

            if (p_148278_5_ > 16 && p_148278_6_ < 16 && this.func_148314_g())
            {
                var7 = this.guiScreenXrayManager.getListContaining(this);
                var8 = var7.indexOf(this);
                var7.remove(this);
                var7.add(var8 - 1, this);
                this.guiScreenXrayManager.markChanged();
                return true;
            }

            if (p_148278_5_ > 16 && p_148278_6_ > 16 && this.func_148307_h())
            {
                var7 = this.guiScreenXrayManager.getListContaining(this);
                var8 = var7.indexOf(this);
                var7.remove(this);
                var7.add(var8 + 1, this);
                this.guiScreenXrayManager.markChanged();
                return true;
            }
        }

        return false;
    }
    
    public void setSelected(int p_178011_1_, int p_178011_2_, int p_178011_3_) {}
    
    public void mouseReleased(int slotIndex, int x, int y, int mouseEvent, int relativeX, int relativeY) {}

}
