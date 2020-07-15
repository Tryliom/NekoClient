package neko.gui.xraymanager;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiListExtended;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.EnumChatFormatting;

public abstract class GuiXrayManagerList extends GuiListExtended {
	
	protected final Minecraft mc;
    protected final List list;

	public GuiXrayManagerList(Minecraft mcIn, int i1, int i2, List list) {
		super(mcIn, i1, i2, 32, i2 - 55 + 4, 36);
        this.mc = mcIn;
        this.list = list;
        this.field_148163_i = false;
        this.setHasListHeader(true, (int)((float)mcIn.fontRendererObj.FONT_HEIGHT * 1.5F));
    }
	
	protected void drawListHeader(int p_148129_1_, int p_148129_2_, Tessellator p_148129_3_)
    {
        String var4 = EnumChatFormatting.UNDERLINE + "" + EnumChatFormatting.BOLD + this.getListHeader();
        this.mc.fontRendererObj.drawString(var4, p_148129_1_ + this.width / 2 - this.mc.fontRendererObj.getStringWidth(var4) / 2, Math.min(this.top + 3, p_148129_2_), 16777215);
    }
	
	protected abstract String getListHeader();
	
	public List getList()
    {
        return this.list;
    }
	
	protected int getSize()
    {
        return this.getList().size();
    }
	
	public XRayListEntry getListEntry(int i)
	{
		return (XRayListEntry)this.getList().get(i);
	}
	
	public void resetLists() {
		for(int zz = 0; zz<this.getSize(); zz++) {
			XRayListEntry xle = this.getListEntry(zz);
			List l = xle.guiScreenXrayManager.getListContaining(xle);
			xle.guiScreenXrayManager.getListContaining(xle).remove(xle);
			xle.guiScreenXrayManager.markChanged();
		}
	}
	
	public int getListWidth()
    {
        return this.width;
    }

    protected int getScrollBarX()
    {
        return this.right - 6;
    }
}
