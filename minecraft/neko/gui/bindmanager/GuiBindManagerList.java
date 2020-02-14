package neko.gui.bindmanager;

import neko.module.Module;
import neko.utils.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiKeyBindingList;
import net.minecraft.client.gui.GuiListExtended;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.util.EnumChatFormatting;

public class GuiBindManagerList extends GuiListExtended {
	
	private final GuiBindManager bindManager;
	private final Minecraft mc;
	private final GuiListExtended.IGuiListEntry[] listEntries;
	private int maxListLabelWidth = 0;
	
	public GuiBindManagerList(GuiBindManager bindmng, Minecraft mcIn) {
		super(mcIn, bindmng.width, bindmng.height, 63, bindmng.height - 32, 20);
		this.bindManager = bindmng;
		this.mc = mcIn;
		String[] var3 = Utils.getAllKeybindsWithModule();
		this.listEntries = new GuiListExtended.IGuiListEntry[Utils.getTotModule()];
		int var4 = 0;
		String var5 = null;
		String[] var6 = var3;
		int var7 = var3.length;
		
		for(int var8 = 0; var8 < var7; var8++) {
			
			String var9 = var6[var8];
			String[] split = var9.split(";"); //Name ; Keybind int ; category
			String var10 = split[2]; //Category
			
			if(!var10.equals(var5)) {
				var5 = var10;
				try {
				this.listEntries[var4++] = new GuiBindManagerList.CategoryEntry(var10); //On add la category string
				} catch (Exception e) {
					
				}
			}
			
			int var11 = mcIn.fontRendererObj.getStringWidth(split[0]);
			
			if (var11 > this.maxListLabelWidth) {
				this.maxListLabelWidth = var11;
			}
			
			try {
				this.listEntries[var4++] = new GuiBindManagerList.KeyEntry(Utils.getModule(split[0]), split, var8);
			} catch (Exception e) {
				
			}
			
		}
		
	}
	
	protected int getSize()
    {
        return this.listEntries.length;
    }
	
	public GuiListExtended.IGuiListEntry getListEntry(int p_148180_1_)
    {
        return this.listEntries[p_148180_1_];
    }
	
	protected int getScrollBarX()
    {
        return super.getScrollBarX() + 15;
    }
	
	public int getListWidth()
    {
        return super.getListWidth() + 32;
    }
	
	public class CategoryEntry implements GuiListExtended.IGuiListEntry {

		private final String labelText;
		private final int labelWidth;
		
		public CategoryEntry(String string) {
			this.labelText = string;
			this.labelWidth = GuiBindManagerList.this.mc.fontRendererObj.getStringWidth(this.labelText);
		}

		@Override
		public void drawEntry(int slotIndex, int x, int y, int listWidth, int slotHeight, int mouseX, int mouseY, boolean isSelected) {
			GuiBindManagerList.this.mc.fontRendererObj.drawString(this.labelText, GuiBindManagerList.this.mc.currentScreen.width / 2 - this.labelWidth / 2, y + slotHeight - GuiBindManagerList.this.mc.fontRendererObj.FONT_HEIGHT - 1, 16777215);
		}

		@Override
		public boolean mousePressed(int var1, int var2, int var3, int var4, int var5, int var6) { 
			return false;
		}

		@Override
		public void mouseReleased(int slotIndex, int x, int y, int mouseEvent, int relativeX, int relativeY) {}
		
		@Override
		public void setSelected(int p_178011_1_, int p_178011_2_, int p_178011_3_) {}
		
    }
	
	public class KeyEntry implements GuiListExtended.IGuiListEntry {
		private final Module module;
		private final String[] moduleInfo;
		private final String name;
		private final GuiButton btnChangeKeyBinding;
		private final GuiButton btnReset;
		private final int zzzz;
		
		private KeyEntry(Module module, String[] moduleinfos, int zz) {
			this.moduleInfo = moduleinfos; //Name ; Keybind int ; category
			this.module = module;
			this.name = module.getName();
			this.btnChangeKeyBinding = new GuiButton(0, 0, 0, 75, 18, Utils.getBind(this.name));
			this.btnReset = new GuiButton(0, 0, 0, 50, 18, I18n.format("controls.reset", new Object[0]));
			this.zzzz = zz;
		}
		
		public void drawEntry(int slotIndex, int x, int y, int listWidth, int slotHeight, int mouseX, int mouseY, boolean isSelected) {
			boolean var9 = GuiBindManagerList.this.bindManager.buttonId == this.module.getId();
			GuiBindManagerList.this.mc.fontRendererObj.drawString(this.name, x + 90 - GuiBindManagerList.this.maxListLabelWidth, y + slotHeight / 2 - GuiBindManagerList.this.mc.fontRendererObj.FONT_HEIGHT / 2, 16777215);
			this.btnReset.xPosition = x + 190;
            this.btnReset.yPosition = y;
            this.btnReset.enabled = this.module.getDefaultBind() != this.module.getBind();
            this.btnReset.drawButton(GuiBindManagerList.this.mc, mouseX, mouseY);
            this.btnChangeKeyBinding.xPosition = x + 105;
            this.btnChangeKeyBinding.yPosition = y;
            this.btnChangeKeyBinding.displayString = Utils.getBind(this.module.getName());
            boolean var10 = false;
            
            if(this.module.getBind() != 0) {
        		String[] var11 = Utils.getAllKeybindsWithModule();
        		int var12 = var11.length;
        		
        		for (int var13 = 0; var13 < var12; var13++) {
        			String var14 = var11[var13];
        			String[] var141 = var14.split(";"); //Name ; Keybind int ; category
        			if(var141 != this.moduleInfo && Integer.valueOf(var141[1]) == this.module.getBind()) {
        				var10 = true;
        				break;
        			}
        		}
            }
            
            if(var9) {
            	this.btnChangeKeyBinding.displayString = EnumChatFormatting.WHITE + "> " + EnumChatFormatting.YELLOW + this.btnChangeKeyBinding.displayString + EnumChatFormatting.WHITE + " <";
            } else if (var10) {
            	if(this.module.getBind() == -1) {
            		this.btnChangeKeyBinding.displayString = EnumChatFormatting.RED + this.btnChangeKeyBinding.displayString;
            	} else {
            		this.btnChangeKeyBinding.displayString = EnumChatFormatting.DARK_GREEN + this.btnChangeKeyBinding.displayString;
            	}
            	
            }
            
            this.btnChangeKeyBinding.drawButton(GuiBindManagerList.this.mc, mouseX, mouseY);
		}
		
		public boolean mousePressed(int p_148278_1_, int p_148278_2_, int p_148278_3_, int p_148278_4_, int p_148278_5_, int p_148278_6_) {
			if (this.btnChangeKeyBinding.mousePressed(GuiBindManagerList.this.mc, p_148278_2_, p_148278_3_))
            {
				GuiBindManagerList.this.bindManager.buttonId = this.module.getId();
                return true;
            }
            else if (this.btnReset.mousePressed(GuiBindManagerList.this.mc, p_148278_2_, p_148278_3_))
            {
            	this.module.setBind(this.module.getDefaultBind());
                return true;
            }
            else
            {
                return false;
            }
		}
		
		public void mouseReleased(int slotIndex, int x, int y, int mouseEvent, int relativeX, int relativeY) {
	        this.btnChangeKeyBinding.mouseReleased(x, y);
	        this.btnReset.mouseReleased(x, y);
	    }
		
		public void setSelected(int p_178011_1_, int p_178011_2_, int p_178011_3_) {}

        KeyEntry(Module p_i45030_2_, String[] strlist, Object p_i45030_3_, int zz)
        {
            this(p_i45030_2_, strlist, zz);
        }
	}
	
}
