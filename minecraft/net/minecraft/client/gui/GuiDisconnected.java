package net.minecraft.client.gui;

import java.io.IOException;
import java.net.Socket;
import java.util.Iterator;
import java.util.List;

import com.mojang.authlib.Agent;
import com.mojang.authlib.UserAuthentication;
import com.mojang.authlib.exceptions.AuthenticationException;
import com.mojang.authlib.yggdrasil.YggdrasilAuthenticationService;

import neko.module.modules.Friends;
import neko.module.other.AutoReco;
import neko.utils.Utils;
import net.mcleaks.MCLeaks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.GuiConnecting;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.Session;

public class GuiDisconnected extends GuiScreen
{
    private String reason;
    private IChatComponent message;
    private List multilineMessage;
    private final GuiScreen parentScreen;
    private int field_175353_i;
    private String error = "";

    public GuiDisconnected(GuiScreen p_i45020_1_, String p_i45020_2_, IChatComponent p_i45020_3_)
    {
        this.parentScreen = p_i45020_1_;
        this.reason = I18n.format(p_i45020_2_, new Object[0]);
        this.message = p_i45020_3_;
    }

    /**
     * Fired when a key is typed (except F11 who toggle full screen). This is the equivalent of
     * KeyListener.keyTyped(KeyEvent e). Args : character (character on the key), keyCode (lwjgl Keyboard key code)
     */
    protected void keyTyped(char typedChar, int keyCode) throws IOException {}

    /**
     * Adds the buttons (and other controls) to the screen in question.
     */
    public void initGui()
    {
        this.buttonList.clear();
        this.multilineMessage = this.fontRendererObj.listFormattedStringToWidth(this.message.getFormattedText(), this.width - 50);
        this.field_175353_i = this.multilineMessage.size() * this.fontRendererObj.FONT_HEIGHT;
        this.buttonList.add(new GuiButton(0, this.width / 2 - 100, this.height / 2 + this.field_175353_i / 2 + this.fontRendererObj.FONT_HEIGHT, I18n.format("gui.toMenu", new Object[0])));
        if (!Utils.currServ.isEmpty()) {
	        this.buttonList.add(new GuiButton(1, this.width / 2 - 100, this.height / 2 + this.field_175353_i / 2 + this.fontRendererObj.FONT_HEIGHT + 24, "Reconnect"));
	        this.buttonList.add(new GuiButton(2, this.width / 2 - 100, this.height / 2 + this.field_175353_i / 2 + this.fontRendererObj.FONT_HEIGHT + 48, AutoReco.getActive()));
	        this.buttonList.add(new GuiButton(3, this.width / 2 - 100, this.height / 2 + this.field_175353_i / 2 + this.fontRendererObj.FONT_HEIGHT + 72, "Reconnect with next Alt"));
        }
    }

    protected void actionPerformed(GuiButton button) throws IOException
    {
        if (button.id == 0)
        {
            this.mc.displayGuiScreen(this.parentScreen);
        }
        if (button.id == 1)
        {
        	try {
                this.mc.theWorld.sendQuittingDisconnectingPacket();
                this.mc.loadWorld((WorldClient)null);
        	} catch (Exception e) {}
            this.mc.displayGuiScreen(new GuiConnecting(this.parentScreen, mc, new ServerData("", Utils.currServ)));
        }
        if (button.id == 2)
        {
            AutoReco.setActive();
            this.initGui();
        }
        if (button.id == 3)
        {
        	int size = Utils.getAllAccount().size();
        	if (size>=1) {
        		int acc = Utils.lastAccount>0 ? size==Utils.lastAccount ? 1 : Utils.lastAccount+1 : 1;
        		Utils.lastAccount = acc;
        		String ac = Utils.getAccount(acc);
        		if (ac.length()>0) {
        			String a[] = ac.split(" ");
        			String user = a[0];
        			String mdp = "";
        			if (a.length>1) {
        				mdp = a[1];
        				for (int i=2;i<a.length;i++)
        					mdp+=" "+a[i];
        			}
        			boolean good = false;
        			YggdrasilAuthenticationService authService = new YggdrasilAuthenticationService(mc.getProxy(), "");
        		    UserAuthentication auth = authService.createUserAuthentication(Agent.MINECRAFT);
        		    if (!mdp.isEmpty()) {
        			    auth.setUsername(user);
        			    auth.setPassword(mdp);
        			    try {
        					auth.logIn();
        					Minecraft.getMinecraft().session = new Session(auth.getSelectedProfile().getName(), 
        					auth.getSelectedProfile().getId().toString(), auth.getAuthenticatedToken(), "mojang");
        					if (!Friends.isFriend(mc.session.getUsername()))
        						Friends.friend.add(mc.session.getUsername());
        					if (MCLeaks.isAltActive())
        						MCLeaks.remove();
        					good = true;
        				} catch (Exception e) {}
        		    } else {
        		    	mc.session = new Session(user, "", "", "mojang");
        				
        		    	if (!Friends.isFriend(mc.session.getUsername()))
        					Friends.friend.add(mc.session.getUsername());
        				if (MCLeaks.isAltActive())
        					MCLeaks.remove();
        				good = true;
        		    }
        		    
        		    if (good) {
        		    	try {
        	                this.mc.theWorld.sendQuittingDisconnectingPacket();
        	                this.mc.loadWorld((WorldClient)null);
                    	} catch (Exception e) {}
        		    	this.mc.displayGuiScreen(new GuiConnecting(this.parentScreen, mc, new ServerData("", Utils.currServ)));
        		    	this.error="§aConnecté !";
        		    } else {
        		    	this.error="§cErreur de connection au compte...";
        		    }
        		}
        	}
        }
    }

    /**
     * Draws the screen and all the components in it. Args : mouseX, mouseY, renderPartialTicks
     */
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        this.drawDefaultBackground();
        this.drawCenteredString(this.fontRendererObj, this.reason, this.width / 2, this.height / 2 - this.field_175353_i / 2 - this.fontRendererObj.FONT_HEIGHT * 2, 11184810);
        this.drawCenteredString(this.fontRendererObj, this.error, this.width / 2, this.height / 2 - this.field_175353_i / 2 - this.fontRendererObj.FONT_HEIGHT * 2 + 144, 11184810);
        int var4 = this.height / 2 - this.field_175353_i / 2;

        if (AutoReco.canGo()) {
        	try {
                this.mc.theWorld.sendQuittingDisconnectingPacket();
                this.mc.loadWorld((WorldClient)null);
        	} catch (Exception e) {}
        	this.mc.displayGuiScreen(new GuiConnecting(this.parentScreen, mc, new ServerData("", mc.getCurrentServerData().serverIP)));        	
        }
        if (AutoReco.active)
        	this.initGui();
        
        if (this.multilineMessage != null)
        {
            for (Iterator var5 = this.multilineMessage.iterator(); var5.hasNext(); var4 += this.fontRendererObj.FONT_HEIGHT)
            {
                String var6 = (String)var5.next();
                this.drawCenteredString(this.fontRendererObj, var6, this.width / 2, var4, 16777215);
            }
        }

        super.drawScreen(mouseX, mouseY, partialTicks);
    }
}
