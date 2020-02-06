package net.minecraft.client.gui;

import java.awt.Desktop;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import org.apache.commons.io.Charsets;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GLContext;
import org.lwjgl.util.glu.Project;

import com.google.common.collect.Lists;
import com.ibm.icu.impl.ZoneMeta;

import neko.Client;
import neko.api.NekoCloud;
import neko.event.EventManager;
import neko.gui.GuiAltManager;
import neko.gui.GuiConnect;
import neko.gui.GuiMenuNeko;
import neko.gui.GuiNekoAccount;
import neko.gui.GuiUpdate;
import neko.gui.button.ButtonDiscord;
import neko.guicheat.clickgui.ClickGUI;
import neko.guicheat.clickgui.settings.SettingsManager;
import neko.manager.ModuleManager;
import neko.manager.OnlyRpgManager;
import neko.manager.TutoManager;
import neko.module.other.Rank;
import neko.updater.Updater;
import neko.utils.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.multiplayer.GuiConnecting;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.realms.RealmsBridge;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.demo.DemoWorldServer;
import net.minecraft.world.storage.ISaveFormat;
import net.minecraft.world.storage.WorldInfo;

public class GuiMainMenu extends GuiScreen implements GuiYesNoCallback
{	
    private static final Logger logger = LogManager.getLogger();
    private static final Random field_175374_h = new Random();
    public static int owo = 0;

    ResourceLocation resourceLocation = AbstractClientPlayer.getLocationSkin("Steve");
    
    /** Counts the number of screen updates. */
    private float updateCounter;

    /** The splash message. */
    private String splashText;

    /** Timer used to rotate the panorama, increases every tick. */
    private int panoramaTimer;

    /**
     * Texture allocated for the current viewport of the main menu's panorama background.
     */
    public static DynamicTexture viewportTexture;
    private final Object field_104025_t = new Object();
    private String field_92025_p;
    private String field_146972_A;
    private String field_104024_v;
    private String Bc;
    private static final ResourceLocation splashTexts = new ResourceLocation("texts/splashes.txt");
    private static final ResourceLocation minecraftTitleTextures = new ResourceLocation("textures/gui/title/minecraft.png");
    

    /** An array of all the paths to the panorama pictures. */
    private static final ResourceLocation[] titlePanoramaPaths = new ResourceLocation[] {new ResourceLocation("textures/gui/title/background/"+Utils.getPan()+"_0.png"), new ResourceLocation("textures/gui/title/background/"+Utils.getPan()+"_1.png"), new ResourceLocation("textures/gui/title/background/"+Utils.getPan()+"_2.png"), new ResourceLocation("textures/gui/title/background/"+Utils.getPan()+"_3.png"), new ResourceLocation("textures/gui/title/background/"+Utils.getPan()+"_4.png"), new ResourceLocation("textures/gui/title/background/"+Utils.getPan()+"_5.png")};
    public static final String field_96138_a = "Please click " + EnumChatFormatting.UNDERLINE + "here" + EnumChatFormatting.RESET + " for more information.";
    private int field_92024_r;
    private int field_92023_s;
    private int field_92022_t;
    private int field_92021_u;
    private int field_92020_v;
    private int field_92019_w;
    private ResourceLocation field_110351_G;
    private GuiButton field_175372_K;
    private TutoManager tm = TutoManager.getTuto();
    
    private int scrollingTextPosX;
    private String scrollingText = Client.SCROLLING_TEXT;

    public GuiMainMenu()
    {
        this.field_146972_A = field_96138_a;
        this.splashText = "missingno";
        BufferedReader var1 = null;

        try
        {
            ArrayList<String> var2 = Lists.newArrayList();
            var1 = new BufferedReader(new InputStreamReader(Minecraft.getMinecraft().getResourceManager().getResource(splashTexts).getInputStream(), Charsets.UTF_8));
            String var3;

            while ((var3 = var1.readLine()) != null)
            {
                var3 = var3.trim();

                if (!var3.isEmpty())
                {
                    var2.add(var3);
                }
            }

            if (!var2.isEmpty())
            {
                do
                {
                    this.splashText = (String)var2.get(field_175374_h.nextInt(var2.size()));
                }
                while (this.splashText.hashCode() == 125780783);
            }
        }
        catch (IOException var12)
        {
            ;
        }
        finally
        {
            if (var1 != null)
            {
                try
                {
                    var1.close();
                }
                catch (IOException var11)
                {
                    ;
                }
            }
        }

        this.updateCounter = field_175374_h.nextFloat();
        this.field_92025_p = "";

        if (!GLContext.getCapabilities().OpenGL20 && !OpenGlHelper.areShadersSupported())
        {
            this.field_92025_p = I18n.format("title.oldgl1", new Object[0]);
            this.field_146972_A = I18n.format("title.oldgl2", new Object[0]);
            this.field_104024_v = "https://help.mojang.com/customer/portal/articles/325948?ref=game";
        }        
    }

    /**
     * Called from the main game loop to update the screen.
     */
    public void updateScreen()
    {
    	++this.panoramaTimer;
    }

    /**
     * Returns true if this GUI should pause the game when it is displayed in single-player
     */
    public boolean doesGuiPauseGame()
    {
        return false;
    }

    /**
     * Fired when a key is typed (except F11 who toggle full screen). This is the equivalent of
     * KeyListener.keyTyped(KeyEvent e). Args : character (character on the key), keyCode (lwjgl Keyboard key code)
     */
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
    	if (keyCode == KeyEvent.VK_ESCAPE) {
		}
		super.keyTyped(typedChar, keyCode);
    }

    /**
     * Adds the buttons (and other controls) to the screen in question.
     */
    public void initGui()
    {
    	this.Bc = Utils.getAn();
        this.viewportTexture = new DynamicTexture(256, 256);
        this.field_110351_G = this.mc.getTextureManager().getDynamicTextureLocation("background", this.viewportTexture);
        Calendar var1 = Calendar.getInstance();
        var1.setTime(new Date());

        if (var1.get(2) + 1 == 11 && var1.get(5) == 9)
        {
            this.splashText = "Happy birthday, ez!";
        }
        else if (var1.get(2) + 1 == 6 && var1.get(5) == 1)
        {
        	
            this.splashText = "Happy birthday, Notch!";
        }
        else if (var1.get(2) + 1 == 12 && var1.get(5) == 24)
        {
        	try {
        	if (Utils.verif==null)
        		this.splashText = "§cJ§fo§cy§fe§cu§fx§c n§fo§cë§fl §ca§fv§ce§fc §3Neko §f!§c :§f3";
        	} catch (Exception e) {}
            this.splashText = "Merry X-mas!";
        }
        else if (var1.get(2) + 1 == 1 && var1.get(5) == 1)
        {
            this.splashText = "Happy new year!";
        }
        else if (var1.get(2) + 1 == 10 && var1.get(5) == 31)
        {
            this.splashText = "OOoooOOOoooo! Spooky!";
        }

        int var3 = Utils.verif==null ? 104 : this.height / 4 + 48;

        this.addSingleplayerMultiplayerButtons(Utils.verif==null ? var3+36 : var3, 24);
        this.buttonList.add(new GuiButton(0, Utils.verif==null ? this.width/2 - 19 : this.width / 2 - 100,
        		Utils.verif==null ? 8 : var3 + 72 + 12,
        				Utils.verif==null ? 40 : 98, 20, Utils.verif==null ? "Options" : I18n.format("menu.options", new Object[0])));
        this.buttonList.add(new GuiButton(4, Utils.verif==null ? this.width/2 - 100 : this.width / 2 + 2,
        		Utils.verif==null ? this.height - 37 : var3 + 72 + 12,
        				Utils.verif==null ? 200 : 98, 20, Utils.verif==null ? "§c§lQuitter Neko" : I18n.format("menu.quit", new Object[0])));
        if (Utils.verif==null) {
        	//this.buttonList.add(new GuiButton(668, this.width/2, this.height - 52, 50, 20, "§5Discord"));
        	this.buttonList.add(new ButtonDiscord(668, this.width/2, this.height - 62, 50, 20));
        	this.buttonList.add(new GuiButton(669, this.width/2 + 50, this.height - 62, 50, 20, "§5Site Web"));
        	this.buttonList.add(new GuiButton(670, 10, 55, 130, 15, "Modifier > Alt Manager"));
        	String s[] = this.Bc.split("\n");
        	int h = 0;
        	for (int i=1;i<s.length+1;i++) {
        		if (s[i-1].startsWith("§d") && s[i-1].contains("%")) {
        			h = this.height/2 + -60 + 15 * i;
            		this.buttonList.add(new GuiButton(771, 5, h, 170, 11, "§6"+s[i-1]));
        		}
        	}
        	
    		this.buttonList.add(new GuiButton(769, this.width/2 - 50, 40, 100, 20, tm.isDone() ? "§7Activer le tuto" : "§7Passer le tuto"));
        } else
        	this.buttonList.add(new GuiButtonLanguage(5, Utils.verif==null ? 100 : this.width / 2 - 124, Utils.verif==null ? var3+104+12 : var3 + 72 + 12));

        synchronized (this.field_104025_t)
        {
            this.field_92023_s = this.fontRendererObj.getStringWidth(this.field_92025_p);
            this.field_92024_r = this.fontRendererObj.getStringWidth(this.field_146972_A);
            int var5 = Math.max(this.field_92023_s, this.field_92024_r);
            this.field_92022_t = (this.width - var5) / 2;
            this.field_92021_u = ((GuiButton)this.buttonList.get(0)).yPosition - 24;
            this.field_92020_v = this.field_92022_t + var5;
            this.field_92019_w = this.field_92021_u + 24;
        }
        NekoCloud nc = NekoCloud.getNekoAPI();
        
        
        
        if (!nc.isLogin() && Utils.haveInternet()) {
        	Client var = Client.getNeko();
        	var.settingsManager = new SettingsManager();
        	var.eventManager = new EventManager();
        	var.eventManager.register(var);
    		var.moduleManager = new ModuleManager();
    		var.onlyrpg = OnlyRpgManager.getRpg();
    		if (var.rang==null)
    			for (Rank r : ModuleManager.rang) {
    				if (r.getName().equalsIgnoreCase("Petit Neko Novice")) {
    					var.rang=r;
    					r.setLvl(r.getLvl()!=1 ? r.getLvl() : 1);
    					r.setLock(false);
    				}
    			}
        	Utils.loadCredentials();
    	    String res = nc.loginAccount();
    	    if (res.equalsIgnoreCase("success")) {
    	    	// Load save
    	    	Utils.loadSaveCloud();
    	    	nc.setLogin(true);
    	    } else {
    	    	mc.displayGuiScreen(new GuiConnect(this, 1));
    	    }
        }
    }

    /**
     * Adds Singleplayer and Multiplayer buttons on Main Menu for players who have bought the game.
     */
    private void addSingleplayerMultiplayerButtons(int p_73969_1_, int p_73969_2_)
    {
    	if(Utils.verif==null) {
    		this.buttonList.add(new GuiButton(1, tm.isDone() ? this.width/2 - 90 : this.width/2 - 105, 8, tm.isDone() ? 50 : 80, 20,
            		!tm.isDone() ? "§bCommencer le tuto !" : "§f§lSolo"));
    		this.buttonList.add(new GuiButton(2, tm.isDone() ? this.width/2 + 40 : this.width/2 + 26, 8, tm.isDone() ? 50 : 80, 20,
            				!tm.isDone() ? "§c§kdsjfnkjndsj" : "§f§lMulti"));
    		this.buttonList.add(new GuiButton(359, 10, 8, 100, 20, NekoCloud.getName() == null ? "§b§lNeko" : "§b§l" + NekoCloud.getName()));
    	} else {
    		this.buttonList.add(new GuiButton(1, this.width / 2 - 100, p_73969_1_, I18n.format("menu.singleplayer", new Object[0])));
    		this.buttonList.add(new GuiButton(2, this.width / 2 - 100, p_73969_1_ + p_73969_2_ * 1, I18n.format("menu.multiplayer", new Object[0])));
    	}
        
        
        
        int wid = 5;
        //TODO ACTIVE BUTTON
        if (Utils.verif==null) {
        	this.buttonList.add(this.field_175372_K = new GuiButton(666, this.width/2 - 100, this.height - 62, 100, 20, (!tm.isDone() ? "§k" : "§3")+"Neko..."));
        } else
        	this.buttonList.add(this.field_175372_K = new GuiButton(14, OnlyRpgManager.getRpg().isActive() ? wid : this.width / 2 - 100, p_73969_1_ + p_73969_2_ * 2, I18n.format("menu.online", new Object[0])));
    }

    protected void actionPerformed(GuiButton button) throws IOException
    {
        	if (button.id == 666 && tm.isDone())
            {
        		this.mc.displayGuiScreen(new GuiMenuNeko(this));
            }
        	
        	if (button.id == 668)
            {
        		try {
        			URI url = URI.create("https://discord.gg/Z4ddHEj");
        			Desktop.getDesktop().browse(url);
        		} catch (Exception e) {}	
            }
        	
        	if (button.id == 669)
            {
        		try {
        			URI url = URI.create("https://neko.nekohc.fr/#/login");
        			Desktop.getDesktop().browse(url);
        		} catch (Exception e) {}	
            }
        	
        	if (button.id == 670)
        	{
        		mc.displayGuiScreen(new GuiAltManager(this));
        	}
        	if (button.id == 769)
            {
        		if (!tm.isDone()) {
            		tm.setDone(true);
            		tm.setPart(1);
            		this.mc.displayGuiScreen(new GuiMainMenu());
            	} else {
    	    		tm.setDone(false);    		
    	            this.mc.displayGuiScreen(new GuiMainMenu());
            	}
            }
        	
        	if (button.id == 770) {
    			try {
    				URI url = URI.create("https://nekohc.fr");
    				Desktop.getDesktop().browse(url);
    			} catch (Exception e) {}	
    		}
        	
        	if (button.id == 771) {
    			String s[] = button.displayString.split(" ");
    			if (s.length>=2) {
    				String ip = s[1].replaceAll("§e", "");
    				mc.displayGuiScreen(new GuiConnecting(this, mc, new ServerData(ip, ip)));
    			}
    		}
        	
            if (button.id == 0)
            {
                this.mc.displayGuiScreen(new GuiOptions(this, this.mc.gameSettings));
            }

            if (button.id == 5)
            {
                this.mc.displayGuiScreen(new GuiLanguage(this, this.mc.gameSettings, this.mc.getLanguageManager()));
            }

            if (button.id == 1)
            {
                this.mc.displayGuiScreen(new GuiSelectWorld(this));
            }

            if (button.id == 2)
            {
            	if (tm.isDone())
            		this.mc.displayGuiScreen(new GuiMultiplayer(this));
            }

            if (button.id == 14 && this.field_175372_K.visible)
            {
            	if (!OnlyRpgManager.getRpg().isActive())
            		this.switchToRealms();
            	else
            		mc.displayGuiScreen(new GuiMainMenu());
            }

            if (button.id == 4)
            {
                this.mc.shutdown();
            }

            if (button.id == 11)
            {
                this.mc.launchIntegratedServer("Demo_World", "Demo_World", DemoWorldServer.demoWorldSettings);
            }

            if (button.id == 12)
            {
                ISaveFormat var2 = this.mc.getSaveLoader();
                WorldInfo var3 = var2.getWorldInfo("Demo_World");

                if (var3 != null)
                {
                    GuiYesNo var4 = GuiSelectWorld.func_152129_a(this, var3.getWorldName(), 12);
                    this.mc.displayGuiScreen(var4);
                }
            }
    	if (button.id == 359)
    	{
    		mc.displayGuiScreen(new GuiNekoAccount(this));
    	}
    	
    }

    private void switchToRealms()
    {
        RealmsBridge var1 = new RealmsBridge();
        var1.switchToRealms(this);
    }

    public void confirmClicked(boolean result, int id)
    {
        if (result && id == 12)
        {
            ISaveFormat var6 = this.mc.getSaveLoader();
            var6.flushCache();
            var6.deleteWorldDirectory("Demo_World");
            this.mc.displayGuiScreen(this);
        }
        else if (id == 13)
        {
            if (result)
            {
                try
                {
                    Class<?> var3 = Class.forName("java.awt.Desktop");
                    Object var4 = var3.getMethod("getDesktop", new Class[0]).invoke((Object)null, new Object[0]);
                    var3.getMethod("browse", new Class[] {URI.class}).invoke(var4, new Object[] {new URI(this.field_104024_v)});
                }
                catch (Throwable var5)
                {
                    logger.error("Couldn\'t open link", var5);
                }
            }

            this.mc.displayGuiScreen(this);
        }
    }

    /**
     * Draws the main menu panorama
     */
    private void drawPanorama(int p_73970_1_, int p_73970_2_, float p_73970_3_)
    {
        Tessellator var4 = Tessellator.getInstance();
        WorldRenderer var5 = var4.getWorldRenderer();
        GlStateManager.matrixMode(5889);
        GlStateManager.pushMatrix();
        GlStateManager.loadIdentity();
        Project.gluPerspective(90.0F, 1.5F, 0.05F, 10.0F);
        GlStateManager.matrixMode(5888);
        GlStateManager.pushMatrix();
        GlStateManager.loadIdentity();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.rotate(180.0F, 1.0F, 0.0F, 0.0F);
        GlStateManager.rotate(90.0F, 0.0F, 0F, 1.0F);
        GlStateManager.enableBlend();
        GlStateManager.disableAlpha();
        GlStateManager.disableCull();
        GlStateManager.depthMask(false);
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        byte var6 = 8;

        for (int var7 = 0; var7 < var6 * var6; ++var7)
        {
            GlStateManager.pushMatrix();
            float var8 = ((float)(var7 % var6) / (float)var6 - 0.5F) / 64.0F;
            float var9 = ((float)(var7 / var6) / (float)var6 - 0.5F) / 64.0F;
            float var10 = 0.0F;
            GlStateManager.translate(var8, var9, var10);
            if (Utils.verif!=null) {
	            GlStateManager.rotate(MathHelper.sin(((float)this.panoramaTimer + p_73970_3_) / 400.0F) * 25.0F + 20.0F, 1.0F, 0.0F, 0.0F);
	            GlStateManager.rotate(-((float)this.panoramaTimer + p_73970_3_) * 0.1F, 0.0F, 1.0F, 0.0F);
            }

            for (int var11 = 0; var11 < 6; ++var11)
            {
                GlStateManager.pushMatrix();

                if (var11 == 1)
                {
                    GlStateManager.rotate(90.0F, 0.0F, 1.0F, 0.0F);
                }

                if (var11 == 2)
                {
                    GlStateManager.rotate(180.0F, 0.0F, 1.0F, 0.0F);
                }

                if (var11 == 3)
                {
                    GlStateManager.rotate(-90.0F, 0.0F, 1.0F, 0.0F);
                }

                if (var11 == 4)
                {
                    GlStateManager.rotate(90.0F, 1.0F, 0.0F, 0.0F);
                }

                if (var11 == 5)
                {
                    GlStateManager.rotate(-90.0F, 1.0F, 0.0F, 0.0F);
                }

                this.mc.getTextureManager().bindTexture(titlePanoramaPaths[var11]);
                var5.startDrawingQuads();
                var5.func_178974_a(16777215, 255 / (var7 + 1));
                float var12 = 0.0F;
                var5.addVertexWithUV(-1.0D, -1.0D, 1.0D, (double)(0.0F + var12), (double)(0.0F + var12));
                var5.addVertexWithUV(1.0D, -1.0D, 1.0D, (double)(1.0F - var12), (double)(0.0F + var12));
                var5.addVertexWithUV(1.0D, 1.0D, 1.0D, (double)(1.0F - var12), (double)(1.0F - var12));
                var5.addVertexWithUV(-1.0D, 1.0D, 1.0D, (double)(0.0F + var12), (double)(1.0F - var12));
                var4.draw();
                GlStateManager.popMatrix();
            }

            GlStateManager.popMatrix();
            GlStateManager.colorMask(true, true, true, false);
        }

        var5.setTranslation(0.0D, 0.0D, 0.0D);
        GlStateManager.colorMask(true, true, true, true);
        GlStateManager.matrixMode(5889);
        GlStateManager.popMatrix();
        GlStateManager.matrixMode(5888);
        GlStateManager.popMatrix();
        GlStateManager.depthMask(true);
        GlStateManager.enableCull();
        GlStateManager.enableDepth();
    }

    /**
     * Rotate and blurs the skybox view in the main menu
     */
    private void rotateAndBlurSkybox(float p_73968_1_)
    {
        this.mc.getTextureManager().bindTexture(this.field_110351_G);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);
        GL11.glCopyTexSubImage2D(GL11.GL_TEXTURE_2D, 0, 0, 0, 0, 0, 256, 256);
        GlStateManager.enableBlend();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GlStateManager.colorMask(true, true, true, false);
        Tessellator var2 = Tessellator.getInstance();
        WorldRenderer var3 = var2.getWorldRenderer();
        var3.startDrawingQuads();
        GlStateManager.disableAlpha();
        byte var4 = 3;

        for (int var5 = 0; var5 < var4; ++var5)
        {
            var3.func_178960_a(1.0F, 1.0F, 1.0F, 1.0F / (float)(var5 + 1));
            int var6 = this.width;
            int var7 = this.height;
            float var8 = (float)(var5 - var4 / 2) / 256.0F;
            var3.addVertexWithUV((double)var6, (double)var7, (double)this.zLevel, (double)(0.0F + var8), 1.0D);
            var3.addVertexWithUV((double)var6, 0.0D, (double)this.zLevel, (double)(1.0F + var8), 1.0D);
            var3.addVertexWithUV(0.0D, 0.0D, (double)this.zLevel, (double)(1.0F + var8), 0.0D);
            var3.addVertexWithUV(0.0D, (double)var7, (double)this.zLevel, (double)(0.0F + var8), 0.0D);
        }

        var2.draw();
        GlStateManager.enableAlpha();
        GlStateManager.colorMask(true, true, true, true);
    }

    /**
     * Renders the skybox in the main menu
     */
    private void renderSkybox(int p_73971_1_, int p_73971_2_, float p_73971_3_)
    {
    	this.mc.getFramebuffer().unbindFramebuffer();
        GlStateManager.viewport(0, 0, 256, 256);
    	this.drawPanorama(p_73971_1_, p_73971_2_, p_73971_3_);     
        this.rotateAndBlurSkybox(p_73971_3_);
        this.mc.getFramebuffer().bindFramebuffer(true);
        GlStateManager.viewport(0, 0, this.mc.displayWidth, this.mc.displayHeight);
        Tessellator var4 = Tessellator.getInstance();
        
        WorldRenderer var5 = var4.getWorldRenderer();
        var5.startDrawingQuads();
        float var6 = this.width > this.height ? 120.0F / (float)this.width : 120.0F / (float)this.height;
        float var7 = (float)this.height * var6 / 256.0F;
        float var8 = (float)this.width * var6 / 256.0F;
        var5.func_178960_a(1.0F, 1.0F, 1.0F, 1.0F);
        int var9 = this.width;
        int var10 = this.height;
        var5.addVertexWithUV(0.0D, (double)var10, (double)this.zLevel, (double)(0.5F - var7), (double)(0.5F + var8));
        var5.addVertexWithUV((double)var9, (double)var10, (double)this.zLevel, (double)(0.5F - var7), (double)(0.5F - var8));
        var5.addVertexWithUV((double)var9, 0.0D, (double)this.zLevel, (double)(0.5F + var7), (double)(0.5F - var8));
        var5.addVertexWithUV(0.0D, 0.0D, (double)this.zLevel, (double)(0.5F + var7), (double)(0.5F + var8));
        var4.draw();
    }

    /**
     * Draws the screen and all the components in it. Args : mouseX, mouseY, renderPartialTicks
     */
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        GlStateManager.disableAlpha();
        this.renderSkybox(mouseX, mouseY, partialTicks);
        GlStateManager.enableAlpha();
        Tessellator var4 = Tessellator.getInstance();
        WorldRenderer var5 = var4.getWorldRenderer();
        short var6 = 274;
        int var7 = this.width / 2 - var6 / 2;
        byte var8 = 30;
        
        this.drawGradientRect(0, 0, this.width, this.height, -2130706433, 16777215);
        this.drawGradientRect(0, 0, this.width, this.height, 0, Integer.MIN_VALUE);
        
        if (Utils.verif!=null) {
        this.mc.getTextureManager().bindTexture(minecraftTitleTextures);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        if ((double)this.updateCounter < 1.0E-4D)
        {
            this.drawTexturedModalRect(var7 + 0, var8 + 0, 0, 0, 99, 44);
            this.drawTexturedModalRect(var7 + 99, var8 + 0, 129, 0, 27, 44);
            this.drawTexturedModalRect(var7 + 99 + 26, var8 + 0, 126, 0, 3, 44);
            this.drawTexturedModalRect(var7 + 99 + 26 + 3, var8 + 0, 99, 0, 26, 44);
            this.drawTexturedModalRect(var7 + 155, var8 + 0, 0, 45, 155, 44);
        }
        else
        {
            this.drawTexturedModalRect(var7 + 0, var8 + 0, 0, 0, 155, 44);
            this.drawTexturedModalRect(var7 + 155, var8 + 0, 0, 45, 155, 44);
        }
        
        var5.func_178991_c(-1);
        GlStateManager.pushMatrix();
        GlStateManager.translate((float)(this.width / 2 + 90), 70.0F, 0.0F);
        GlStateManager.rotate(-20.0F, 0.0F, 0.0F, 1.0F);
        float var9 = 1.8F - MathHelper.abs(MathHelper.sin((float)(Minecraft.getSystemTime() % 1000L) / 1000.0F * (float)Math.PI * 2.0F) * 0.1F);
        var9 = var9 * 100.0F / (float)((Utils.verif==null ? Client.getNeko().NekoFont : this.fontRendererObj).getStringWidth(this.splashText) + 32);
        GlStateManager.scale(var9, var9, var9);
        }
        if (Utils.verif!=null)
        	this.drawCenteredString(this.fontRendererObj, this.splashText, 0, -8, -256);
        if (Utils.verif!=null)
        	GlStateManager.popMatrix();
        String var10="";
        if (Utils.verif==null)
        	var10 = "";
        else
        	var10 = "Minecraft 1.8";

        if (this.mc.isDemo())
        {
            var10 = var10 + " Demo";
        }
        
        for (Object o : this.buttonList) {
        	if (o instanceof GuiButton) {
	        	GuiButton gb = (GuiButton) o;
	        	if(gb.id==359) {
	        		gb.displayString = NekoCloud.getName() == null ? "§b§lNeko" : "§b§l" + NekoCloud.getName();
	        	}
        	}
        }

        this.drawString(Utils.verif==null ? Client.getNeko().NekoFont : this.fontRendererObj, var10, 2, this.height - 10, -1);
        String var11 = "Copyright Mojang AB. Do not distribute!";
        if (Utils.verif==null) {
        	String s[] = this.Bc.split("\n");
        	for (int i=1;i<s.length+1;i++) {
    			if (!s[i-1].startsWith("§d") && !s[i-1].contains("%")) {
    				this.drawString(Client.getNeko().NekoFont, "§6"+s[i-1], 5, this.height/2 - 60 + i*15, -1);
    			}      		
        	}
        	
        	// Draw grey box in the main menu
        	drawRect(0, 0, this.width, 35, Integer.MIN_VALUE);
        	
        	this.drawString(fontRendererObj, "§eConnecté en tant que: §b" + Minecraft.getMinecraft().getSession().getUsername(), 10, 40, -1);
        	
        	if (!Client.getNeko().ver.isEmpty()) {
        		
        		//TODO: Update
        		
        		///
        		Updater.update(Client.getNeko().ver);
        		
        		///

    	    	//mc.displayGuiScreen(new GuiUpdate(this, 1, Client.getNeko().ver));

        		/*this.drawCenteredString(Client.getNeko().NekoFont, "§3§nUne nouvelle version est disponible !", this.width - 110, this.height/2 - this.height/12, -1);
        		this.drawCenteredString(Client.getNeko().NekoFont, "§eVersion supérieure: §b"+Client.getNeko().ver, this.width - 110, this.height/2 - this.height/60, -1);
        		this.drawCenteredString(Client.getNeko().NekoFont, "§eAjout principal: §b"+Client.getNeko().changelog, this.width - 110, this.height/2 + this.height/30, -1);
        		
        		boolean b = true;
        		for (Object gb : this.buttonList) {
        			if (gb instanceof GuiButton) {
        				if (((GuiButton) gb).id==770)
        					b = false;
        			}
        		}*/
        		//if (b)
        			//this.buttonList.add(new GuiButton(770, this.width - 155, this.height/2 + 25, 90, 20, "§f§lMettre à jour"));
        	}
        	
        	if(Updater.ReadyToUpdate) {
        		this.drawCenteredString(Client.getNeko().NekoFont, "§3§nLa nouvelle version de Neko est prête !", this.width - 110, this.height/2 - this.height/12, -1);
        		this.drawCenteredString(Client.getNeko().NekoFont, "§cRedémarrez votre jeu pour", this.width - 110, this.height/2 + this.height/6, -1);
        		this.drawCenteredString(Client.getNeko().NekoFont, "§cfinaliser le téléchargement.", this.width - 110, this.height/2 + this.height/5, -1);
        		this.drawCenteredString(Client.getNeko().NekoFont, "§eVersion supérieure: §b"+Client.getNeko().ver, this.width - 110, this.height/2 - this.height/60, -1);
        		this.drawCenteredString(Client.getNeko().NekoFont, "§eAjout principal: §b"+Client.getNeko().changelog, this.width - 110, this.height/2 + this.height/30, -1);
        	}
        	var11=Client.getNeko().strCreator;
            
            this.scrollingTextPosX--;
            if(this.scrollingTextPosX < -Client.getNeko().NekoFont.getStringWidth(this.scrollingText)) {
            	this.scrollingTextPosX = this.width;
            }
            this.drawRect(0, this.height-12, this.width, this.height, 0x77FFFFFF);
            this.drawString(Client.getNeko().NekoFont, this.scrollingText, this.scrollingTextPosX, this.height-10, -1);
            
            if (this.field_92025_p != null && this.field_92025_p.length() > 0)
            {
                drawRect(this.field_92022_t - 2, this.field_92021_u - 2, this.field_92020_v + 2, this.field_92019_w - 1, 1428160512);
                this.drawString(Utils.verif==null ? Client.getNeko().NekoFont : this.fontRendererObj, this.field_92025_p, this.field_92022_t, this.field_92021_u, -1);
                this.drawString(Utils.verif==null ? Client.getNeko().NekoFont : this.fontRendererObj, this.field_146972_A, (this.width - this.field_92024_r) / 2, ((GuiButton)this.buttonList.get(0)).yPosition - 12, -1);
            }
        }
        
        //this.drawString(Utils.verif==null ? Client.getNeko().NekoFont : this.fontRendererObj, var11,
        //this.width - (Utils.verif==null ? Client.getNeko().NekoFont : this.fontRendererObj).getStringWidth(var11) - 2,
        //this.height - 10, -1);
        //drawEntityOnScreen(this.width/2 + 15, this.height/2 - 15, 30, mouseX, mouseY, );
        
        super.drawScreen(mouseX, mouseY, partialTicks);
    }
    

    /**
     * Called when the mouse is clicked. Args : mouseX, mouseY, clickedButton
     */
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException
    {
        super.mouseClicked(mouseX, mouseY, mouseButton);

        synchronized (this.field_104025_t)
        {
            if (this.field_92025_p.length() > 0 && mouseX >= this.field_92022_t && mouseX <= this.field_92020_v && mouseY >= this.field_92021_u && mouseY <= this.field_92019_w)
            {
                GuiConfirmOpenLink var5 = new GuiConfirmOpenLink(this, this.field_104024_v, 13, true);
                var5.disableSecurityWarning();
                this.mc.displayGuiScreen(var5);
            }
        }
    }
}
