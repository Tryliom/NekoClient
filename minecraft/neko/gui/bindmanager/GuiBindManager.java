package neko.gui.bindmanager;

import java.io.IOException;

import org.lwjgl.input.Keyboard;

import neko.module.Module;
import neko.utils.Utils;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiKeyBindingList;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;

public class GuiBindManager extends GuiScreen {
	
	private GuiScreen parentScreen;
    protected String screenTitle = "Bind Manager";
    
	public int buttonId = -1;
	private GuiBindManagerList guiBindManagerList;
	private GuiButton buttonReset;
	
	public GuiBindManager(GuiScreen guiscreen) {
		this.parentScreen = guiscreen;
	}
	
	public void initGui() {
		this.guiBindManagerList = new GuiBindManagerList(this, this.mc);
		this.buttonList.add(new GuiButton(900, this.width / 2 - 155, this.height - 29, 150, 20, I18n.format("gui.done", new Object[0])));
		this.buttonList.add(this.buttonReset = new GuiButton(901, this.width / 2 - 155 + 160, this.height - 29, 150, 20, I18n.format("controls.resetAll", new Object[0])));
        this.screenTitle = "Bind Manager";
	}
	
	public void handleMouseInput() throws IOException {
		super.handleMouseInput();
		this.guiBindManagerList.func_178039_p();
	}
	
	protected void actionPerformed(GuiButton button) throws IOException {
		if (button.id == 900)
        {
            this.mc.displayGuiScreen(this.parentScreen);
        }
		else if (button.id == 901)
        {
			String[] var2 = Utils.getAllKeybindsWithModule();
			int var3 = var2.length;
			
			for(int var4 = 0; var4 < var3; ++var4) {
				String var55 = var2[var4];
				String[] var5 = var55.split(";"); //Name ; Keybind int ; category
				Module m = Utils.getModule(var5[0]);
				m.setBind(m.getDefaultBind());
			}
        } else if (button.id < 900 && button instanceof GuiBindOptionButton) {

        	Module m = Utils.getModuleById(button.id);
        	
        	button.displayString = Utils.getBind(m.getName());
        }
	}
	
	protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
		if(this.buttonId != -1) {
			Module m = Utils.getModuleById(this.buttonId);
			m.setBind(-100+mouseButton);
			this.buttonId = -1;
		} else if (mouseButton != 0 || !this.guiBindManagerList.func_148179_a(mouseX, mouseY, mouseButton)) {
			super.mouseClicked(mouseX, mouseY, mouseButton);
		}
	}
	
	protected void mouseReleased(int mouseX, int mouseY, int state)
    {
        if (state != 0 || !this.guiBindManagerList.func_148181_b(mouseX, mouseY, state))
        {
            super.mouseReleased(mouseX, mouseY, state);
        }
    }
	
	protected void keyTyped(char typedChar, int keyCode) throws IOException
    {
		if (this.buttonId != -1)
        {
			if (keyCode == 1)
            {
				Module m = Utils.getModuleById(this.buttonId);
				m.setBind(-1);
            } else if (keyCode != 0) {
            	Module m = Utils.getModuleById(this.buttonId);
				m.setBind(keyCode);
            } else if (typedChar > 0) {
            	Module m = Utils.getModuleById(this.buttonId);
				m.setBind(typedChar + 256);
            }
			
			this.buttonId = -1;
        } else {
        	if (keyCode == 1) {
        		this.mc.displayGuiScreen(this.parentScreen);
        	}
        	super.keyTyped(typedChar, keyCode);
        }
    }
	
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		this.drawDefaultBackground();
		this.guiBindManagerList.drawScreen(mouseX, mouseY, partialTicks);
		this.drawCenteredString(this.fontRendererObj, this.screenTitle, this.width / 2, 8, 16777215);
		boolean var4 = true;
		String[] var5 = Utils.getAllKeybindsWithModule();
		int var6 = var5.length;
		
		for(int var7 = 0; var7 < var6; ++var7) {
			String var55 = var5[var7];
			String[] var555 = var55.split(";");
			
			Module m = Utils.getModule(var555[0]);
			
			if(m.getBind() != m.getDefaultBind()) {
				var4 = false;
				break;
			}
		}
		
		this.buttonReset.enabled = !var4;
		super.drawScreen(mouseX, mouseY, partialTicks);
	}
	
	public void onGuiClosed() {
		Keyboard.enableRepeatEvents(false);
		super.onGuiClosed();
	}

}
