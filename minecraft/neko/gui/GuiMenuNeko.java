package neko.gui;

import java.awt.event.KeyEvent;
import java.io.IOException;

import org.lwjgl.input.Keyboard;

import neko.Client;
import neko.manager.OnlyRpgManager;
import neko.manager.SoundManager;
import neko.utils.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiIngameMenu;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.client.gui.GuiOptions;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiShareToLan;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.gui.achievement.GuiAchievements;
import net.minecraft.client.gui.achievement.GuiStats;
import net.minecraft.client.multiplayer.GuiConnecting;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.resources.I18n;

public class GuiMenuNeko extends GuiScreen {
    /**
     * Adds the buttons (and other controls) to the screen in question.
     */
    public void initGui()
    {
        this.buttonList.clear();
        byte var1 = -16;
        this.buttonList.add(new GuiButton(1, this.width / 2 - 100, this.height / 4 + 120 + var1, "Retour"));

        this.buttonList.add(new GuiButton(4, this.width / 2 - 100, this.height / 4 + 24 + var1, "Wiki Cheats"));
//        this.buttonList.add(new GuiButton(0, this.width / 2 - 100, this.height / 4 + 96 + var1, 98, 20, I18n.format("menu.options", new Object[0])));
        	if (!OnlyRpgManager.getRpg().isActive())
        		this.buttonList.add(new GuiButton(8, this.width / 2 + 2, this.height / 4 + 72 + var1, 98, 20, "Alt Manager"));
        	if (!OnlyRpgManager.getRpg().isActive())
        		this.buttonList.add(new GuiButton(9, this.width / 2 - 100, this.height / 4 + 72 + var1, 98, 20, "Multiplayer"));
        	else
        		this.buttonList.add(new GuiButton(9, this.width / 2 - 100, this.height / 4 + 72 + var1, "Multiplayer"));
        	if (SoundManager.getSM().canStart)
    			this.buttonList.add(new GuiButton(665, this.width / 2 -100, 10, SoundManager.getSM().isActive() ? "♫ Stop ♫" : "♪ Restart ♪"));
    		else
    			this.buttonList.add(new GuiButton(665, this.width / 2 -100, 10, "Music loading..."));
    }

    protected void actionPerformed(GuiButton button) throws IOException
    {
        switch (button.id)
        {
        	case 665:
        		if (SoundManager.getSM().canStart && !button.displayString.equals("Music loading..."))
    	    		if (SoundManager.getSM().isActive()) 
    	    			SoundManager.getSM().stopMusic();
    	    		else
    	    			SoundManager.getSM().restartMusic();
        		mc.displayGuiScreen(this);
        		break;
            case 0:
                
                break;

            case 1:
                this.mc.displayGuiScreen(new GuiIngameMenu());

            case 2:
            case 3:
            default:
                break;

            case 4:
            	this.mc.displayGuiScreen(new GuiWikiMenu(this));
                break;
            case 8:
                this.mc.displayGuiScreen(new GuiAltManager(this));
                break;
            case 9:
                this.mc.displayGuiScreen(new GuiMultiplayer(this));
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





















