package neko.gui;

import java.io.IOException;

import neko.Client;
import neko.gui.xraymanager.GuiScreenXrayManager;
import neko.manager.OnlyRpgManager;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;

public class GuiMenuNeko extends GuiScreen {
	private GuiScreen prevGui;
	
	
	public GuiMenuNeko(GuiScreen prevGui) {
		this.prevGui = prevGui;
	}
	
    /**
     * Adds the buttons (and other controls) to the screen in question.
     */
    public void initGui()
    {
        this.buttonList.clear();
        byte var1 = -16;
        this.buttonList.add(new GuiButton(1, this.width / 2 - 100, this.height / 4 + 120 + var1, "Retour"));

        this.buttonList.add(new GuiButton(4, this.width / 2 - 100, this.height / 4 + 24 + var1, "Wiki Cheats"));
        this.buttonList.add(new GuiButton(0, this.width / 2 - 100, this.height / 4 + 96 + var1, 98, 20, "Bind Manager"));
        this.buttonList.add(new GuiButton(5, this.width / 2 + 2, this.height / 4 + 96 + var1, 98, 20, "Xray Manager"));
        	if (!OnlyRpgManager.getRpg().isActive())
        		this.buttonList.add(new GuiButton(8, this.width / 2 + 2, this.height / 4 + 72 + var1, 98, 20, "Alt Manager"));
        	if (!OnlyRpgManager.getRpg().isActive())
        		this.buttonList.add(new GuiButton(9, this.width / 2 - 100, this.height / 4 + 72 + var1, 98, 20, "Rank Manager"));
        	else
        		this.buttonList.add(new GuiButton(9, this.width / 2 - 100, this.height / 4 + 72 + var1, "Rank Manager"));
    }

    protected void actionPerformed(GuiButton button) throws IOException
    {
        switch (button.id)
        {
            case 0:
            	this.mc.displayGuiScreen(new neko.gui.bindmanager.GuiBindManager(this));
                break;
            case 1:
                this.mc.displayGuiScreen(this.prevGui);
                break;
            case 4:
            	this.mc.displayGuiScreen(new GuiWikiMenu(this));
                break;
            case 5:
            	this.mc.displayGuiScreen(new GuiScreenXrayManager(this));
                break;
            case 8:
                this.mc.displayGuiScreen(new GuiAltManager(this));
                break;
            case 9:
            	this.mc.displayGuiScreen(new GuiRankManager(this));
                break;
        }
    }

    /**
     * Called from the main game loop to update the screen.
     */
    public void updateScreen()
    {
        super.updateScreen();
    }

    /**
     * Draws the screen and all the components in it. Args : mouseX, mouseY, renderPartialTicks
     */
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        this.drawDefaultBackground();
        this.drawCenteredString(Client.getNeko().NekoFont, "§9Panel de Neko", this.width / 2, 40, 16777215);
        super.drawScreen(mouseX, mouseY, partialTicks);
    }
}





















