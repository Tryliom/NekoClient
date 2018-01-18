package net.minecraft.client.gui;

import java.io.IOException;

import neko.gui.GuiAltManager;
import neko.gui.GuiMenuNeko;
import neko.manager.OnlyRpgManager;
import neko.manager.SoundManager;
import neko.utils.Utils;
import net.minecraft.client.gui.achievement.GuiAchievements;
import net.minecraft.client.gui.achievement.GuiStats;
import net.minecraft.client.multiplayer.GuiConnecting;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ChatComponentText;

public class GuiIngameMenu extends GuiScreen
{
    private int field_146445_a;
    private int field_146444_f;

    /**
     * Adds the buttons (and other controls) to the screen in question.
     */
    public void initGui()
    {
    	Utils.saveRpg();
		Utils.saveFriends();
		Utils.saveBind();
		Utils.saveValues();
		Utils.saveNuker();
		Utils.saveLock();
		Utils.saveRank();
		Utils.saveShit();
		Utils.saveCmd();
        this.field_146445_a = 0;
        this.buttonList.clear();
        byte var1 = -16;
        boolean var2 = true;
        this.buttonList.add(new GuiButton(1, this.width / 2 - 100, this.height / 4 + 120 + var1, I18n.format("menu.returnToMenu", new Object[0])));

        if (!this.mc.isIntegratedServerRunning())
        {
            ((GuiButton)this.buttonList.get(0)).displayString = I18n.format("menu.disconnect", new Object[0]);
        }

        this.buttonList.add(new GuiButton(4, this.width / 2 - 100, this.height / 4 + 24 + var1, I18n.format("menu.returnToGame", new Object[0])));
        this.buttonList.add(new GuiButton(0, this.width / 2 - 100, this.height / 4 + 96 + var1, 98, 20, I18n.format("menu.options", new Object[0])));
        if (Utils.verif==null) {
        	this.buttonList.add(new GuiButton(9, this.width / 2 - 100, this.height / 4 + 72 + var1, "§9Neko..."));
        	if (SoundManager.getSM().canStart)
    			this.buttonList.add(new GuiButton(665, this.width / 2 -100, 10, SoundManager.getSM().isActive() ? "♫ Stop ♫" : "♪ Restart ♪"));
    		else
    			this.buttonList.add(new GuiButton(665, this.width / 2 -100, 10, "Music loading..."));
        }
        GuiButton var3;
        this.buttonList.add(var3 = new GuiButton(7, this.width / 2 + 2, this.height / 4 + 96 + var1, 98, 20, I18n.format("menu.shareToLan", new Object[0])));
        this.buttonList.add(new GuiButton(5, this.width / 2 - 100, this.height / 4 + 48 + var1, 98, 20, I18n.format("gui.achievements", new Object[0])));
        this.buttonList.add(new GuiButton(6, this.width / 2 + 2, this.height / 4 + 48 + var1, 98, 20, I18n.format("gui.stats", new Object[0])));
        var3.enabled = this.mc.isSingleplayer() && !this.mc.getIntegratedServer().getPublic();
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
        		mc.displayGuiScreen(new GuiIngameMenu());
        		break;
            case 0:
                this.mc.displayGuiScreen(new GuiOptions(this, this.mc.gameSettings));
                break;

            case 1:
            	try {
	                button.enabled = false;
	                this.mc.theWorld.sendQuittingDisconnectingPacket();
	                this.mc.loadWorld((WorldClient)null);
	                GuiConnecting.networkManager.closeChannel(null);
            	} catch (Exception e) {}
                this.mc.displayGuiScreen(new GuiMainMenu());

            case 2:
            case 3:
            default:
                break;

            case 4:
                this.mc.displayGuiScreen((GuiScreen)null);
                this.mc.setIngameFocus();
                break;

            case 5:
                this.mc.displayGuiScreen(new GuiAchievements(this, this.mc.thePlayer.getStatFileWriter()));
                break;

            case 6:
                this.mc.displayGuiScreen(new GuiStats(this, this.mc.thePlayer.getStatFileWriter()));
                break;

            case 7:
                this.mc.displayGuiScreen(new GuiShareToLan(this));
                break;
            case 9:
                this.mc.displayGuiScreen(new GuiMenuNeko());
                break;
        }
    }

    /**
     * Called from the main game loop to update the screen.
     */
    public void updateScreen()
    {
        super.updateScreen();
        ++this.field_146444_f;
    }

    /**
     * Draws the screen and all the components in it. Args : mouseX, mouseY, renderPartialTicks
     */
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        this.drawDefaultBackground();
        this.drawCenteredString(this.fontRendererObj, I18n.format("menu.game", new Object[0]), this.width / 2, 40, 16777215);
        super.drawScreen(mouseX, mouseY, partialTicks);
    }
}
