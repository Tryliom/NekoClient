package net.minecraft.client.gui;

import java.io.IOException;

import neko.Client;
import neko.gui.GuiAltManager;
import neko.gui.GuiMenuNeko;
import neko.utils.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.achievement.GuiAchievements;
import net.minecraft.client.gui.achievement.GuiStats;
import net.minecraft.client.multiplayer.GuiConnecting;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.EntityLivingBase;
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
    	Utils.saveAll();
        this.field_146445_a = 0;
        this.buttonList.clear();
        byte var1 = -16;
        this.buttonList.add(new GuiButton(1, this.width / 2 - 100, this.height / 4 + 120 + var1, I18n.format("menu.returnToMenu", new Object[0])));

        if (!this.mc.isIntegratedServerRunning())
        {
            ((GuiButton)this.buttonList.get(0)).displayString = I18n.format("menu.disconnect", new Object[0]);
        }

        this.buttonList.add(new GuiButton(4, this.width / 2 - 100, this.height / 4 + 24 + var1, I18n.format("menu.returnToGame", new Object[0])));
        this.buttonList.add(new GuiButton(0, this.width / 2 - 100, this.height / 4 + 96 + var1, 98, 20, I18n.format("menu.options", new Object[0])));
        if (Utils.verif==null) {
        	this.buttonList.add(new GuiButton(9, this.width / 2 - 100, this.height / 4 + 72 + var1, "§9Neko..."));
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
                this.mc.displayGuiScreen(new GuiMenuNeko(this));
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

    /** The old x position of the mouse pointer */
    private float oldMouseX;

    /** The old y position of the mouse pointer */
    private float oldMouseY;
    
    /**
     * Draws the screen and all the components in it. Args : mouseX, mouseY, renderPartialTicks
     */
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        this.drawDefaultBackground();
        this.drawRect(this.width-140, this.height/2-120, this.width-20, this.height/2-90, 0x77FFFFFF);
    	this.drawRect(this.width-140, this.height/2-120, this.width-20, this.height/2+80, Integer.MIN_VALUE);
        this.drawString(Client.getNeko().NekoFont, "§6§l"+Minecraft.getMinecraft().getSession().getUsername(), 
        		this.width-(120/2)-
        			Client.getNeko().NekoFont.getStringWidth(Minecraft.getMinecraft().getSession().getUsername()),
        			this.height/2-110, -1);
        this.drawCenteredString(this.fontRendererObj, I18n.format("menu.game", new Object[0]), this.width / 2, 40, 16777215);

        this.oldMouseX = (float)mouseX;
        this.oldMouseY = (float)mouseY;
        super.drawScreen(mouseX, mouseY, partialTicks);
        dEOS();
    }
    
    public void dEOS() {
    	drawEntityOnScreen(this.width-80, this.height/2+60, 60, this.width/2-this.oldMouseX, this.height/2-this.oldMouseY, mc.thePlayer);
    }
    
    /**
     * Draws the entity to the screen. Args: xPos, yPos, scale, mouseX, mouseY, entityLiving
     */
    public static void drawEntityOnScreen(int xPos, int yPos, int scale, float mouseX, float mouseY, EntityLivingBase entityLiving)
    {
        GlStateManager.enableColorMaterial();
        GlStateManager.pushMatrix();
        GlStateManager.translate((float)xPos, (float)yPos, 50.0F);
        GlStateManager.scale((float)(-scale), (float)scale, (float)scale);
        GlStateManager.rotate(180.0F, 0.0F, 0.0F, 1.0F);
        float var6 = entityLiving.renderYawOffset;
        float var7 = entityLiving.rotationYaw;
        float var8 = entityLiving.rotationPitch;
        float var9 = entityLiving.prevRotationYawHead;
        float var10 = entityLiving.rotationYawHead;
        GlStateManager.rotate(135.0F, 0.0F, 1.0F, 0.0F);
        RenderHelper.enableStandardItemLighting();
        GlStateManager.rotate(-135.0F, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotate(-((float)Math.atan((double)(mouseY / 40.0F))) * 20.0F, 1.0F, 0.0F, 0.0F);
        entityLiving.renderYawOffset = (float)Math.atan((double)(mouseX / 40.0F)) * 20.0F;
        entityLiving.rotationYaw = (float)Math.atan((double)(mouseX / 40.0F)) * 40.0F;
        entityLiving.rotationPitch = -((float)Math.atan((double)(mouseY / 40.0F))) * 20.0F;
        entityLiving.rotationYawHead = entityLiving.rotationYaw;
        entityLiving.prevRotationYawHead = entityLiving.rotationYaw;
        GlStateManager.translate(0.0F, 0.0F, 0.0F);
        RenderManager var11 = Minecraft.getMinecraft().getRenderManager();
        var11.func_178631_a(180.0F);
        var11.func_178633_a(false);
        var11.renderEntityWithPosYaw(entityLiving, 0.0D, 0.0D, 0.0D, 0.0F, 1.0F);
        var11.func_178633_a(true);
        entityLiving.renderYawOffset = var6;
        entityLiving.rotationYaw = var7;
        entityLiving.rotationPitch = var8;
        entityLiving.prevRotationYawHead = var9;
        entityLiving.rotationYawHead = var10;
        GlStateManager.popMatrix();
        RenderHelper.disableStandardItemLighting();
        GlStateManager.disableRescaleNormal();
        GlStateManager.setActiveTexture(OpenGlHelper.lightmapTexUnit);
        GlStateManager.func_179090_x();
        GlStateManager.setActiveTexture(OpenGlHelper.defaultTexUnit);
    }
}
