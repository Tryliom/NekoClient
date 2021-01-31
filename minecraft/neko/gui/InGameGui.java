package neko.gui;

import java.awt.Color;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.tools.DocumentationTool.Location;

import org.darkstorm.minecraft.gui.util.GuiManagerDisplayScreen;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

import javazoom.jl.player.Player;
import neko.Client;
import neko.guicheat.clickgui.ClickGUI;
import neko.guicheat.clickgui.util.ColorUtil;
import neko.guicheat.clickgui.util.SettingsUtil;
import neko.manager.ModuleManager;
import neko.module.Category;
import neko.module.Module;
import neko.module.modules.combat.Regen;
import neko.module.modules.hide.Friends;
import neko.module.modules.movements.Blink;
import neko.module.modules.params.HUD;
import neko.module.modules.render.Radar;
import neko.module.other.Active;
import neko.module.other.enums.Rate;
import neko.utils.ChatUtils;
import neko.utils.RenderUtils;
import neko.utils.Utils;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.INpc;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.IAnimals;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.CombatEntry;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;

public class InGameGui {
	public static String color="§3";
	public static int c=-1727000060;
	public static int yP=20;
	public static int p=0;
	static int yPos = 10;
	static int multi=0;
	static Client var = Client.getNeko();
	public static int hudWid;
	public static String ArrayType = "";
	
	public static boolean UniColor = false;
	public static String strUniColor = "";
	
	public static boolean ArrayListV1 = false;
	  public static Color temp;
	  public static int outlineColor;
	
	public static void render() {
		  Minecraft mc = Minecraft.getMinecraft();
		  ScaledResolution scaled = new ScaledResolution(mc);
		  yPos = 10;
		  multi=0;
		  
		  ArrayList<String> combatModule = new ArrayList<String>(),
				  renderModule = new ArrayList<String>(),playerModule = new ArrayList<String>(),
				  movementModule = new ArrayList<String>(),paramsModule = new ArrayList<String>(),
				  miscModule = new ArrayList<String>(),specialModule = new ArrayList<String>();
		  for(Module module : ModuleManager.ActiveModule) {
			    if(module.getToggled() && module.getCategory() != Category.HIDE && !module.getName().equalsIgnoreCase("VanillaTp")) {
			    	multi++;
			    	if(SettingsUtil.getRainbowArray()) {
			    		temp = ColorUtil.rainbowEffekt(1L, 1.0f);
						outlineColor = new Color(temp.getRed(), temp.getGreen(), temp.getBlue(), 150).getRGB();
				    	if(module.getCategory() == Category.COMBAT) {  combatModule.add(module.getName()); } //Regen
				    	if(module.getCategory() == Category.RENDER) {  renderModule.add(module.getName());}
				    	if(module.getCategory() == Category.PLAYER) {  playerModule.add(module.getName());}
				    	if(module.getCategory() == Category.MOVEMENT) {  movementModule.add(module.getName());} //Blink
				    	if(module.getCategory() == Category.PARAMS) {  paramsModule.add(module.getName());}
				    	if(module.getCategory() == Category.MISC) {  miscModule.add(module.getName());}
				    	if(module.getCategory() == Category.Special) {  specialModule.add(module.getName());}
			    	} else if(SettingsUtil.getUniColorArray()){
			    		temp = ColorUtil.getArrayUniqueColor();
						outlineColor = new Color(temp.getRed(), temp.getGreen(), temp.getBlue(), 150).getRGB();
				    	if(module.getCategory() == Category.COMBAT) {  combatModule.add(module.getName()); } //Regen
				    	if(module.getCategory() == Category.RENDER) {  renderModule.add(module.getName());}
				    	if(module.getCategory() == Category.PLAYER) {  playerModule.add(module.getName());}
				    	if(module.getCategory() == Category.MOVEMENT) {  movementModule.add(module.getName());} //Blink
				    	if(module.getCategory() == Category.PARAMS) {  paramsModule.add(module.getName());}
				    	if(module.getCategory() == Category.MISC) {  miscModule.add(module.getName());}
				    	if(module.getCategory() == Category.Special) {  specialModule.add(module.getName());}
			    	} else {
				    	if(module.getCategory() == Category.COMBAT) {  combatModule.add("§c"+module.getName()); } //Regen
				    	if(module.getCategory() == Category.RENDER) {  renderModule.add("§e"+module.getName());}
				    	if(module.getCategory() == Category.PLAYER) {  playerModule.add("§3"+module.getName());}
				    	if(module.getCategory() == Category.MOVEMENT) {  movementModule.add("§2"+module.getName());} //Blink
				    	if(module.getCategory() == Category.PARAMS) {  paramsModule.add("§f"+module.getName());}
				    	if(module.getCategory() == Category.MISC) {  miscModule.add("§7"+module.getName());}
				    	if(module.getCategory() == Category.Special) {  specialModule.add("§6"+module.getName());}
			    	}
			    }
		  }
				  	  
				 //1) Alphabétique / Name Length
				  
				   if(SettingsUtil.getArrayAlphabetique()) {
				    	Collections.sort(combatModule); Collections.sort(renderModule); Collections.sort(playerModule); Collections.sort(movementModule);
			  			Collections.sort(paramsModule); Collections.sort(miscModule); Collections.sort(specialModule);				   
				    }
				    if(SettingsUtil.getArrayNameLength()){
				    	Collections.sort(combatModule, new StringLength()); Collections.sort(renderModule, new StringLength());
				  		Collections.sort(playerModule, new StringLength()); Collections.sort(movementModule, new StringLength());
				  		Collections.sort(paramsModule, new StringLength()); Collections.sort(miscModule, new StringLength());
				  		Collections.sort(specialModule, new StringLength());
				    }
				    
				 //2) Ensemble / Mélanger   
				    ArrayList<String> partie2 = new ArrayList<String>();
				    if(SettingsUtil.getArrayModuleBasic()){
				        partie2.addAll(combatModule); partie2.addAll(renderModule); partie2.addAll(playerModule); partie2.addAll(movementModule);
				        partie2.addAll(paramsModule); partie2.addAll(miscModule); partie2.addAll(specialModule);
				    }
				    if(SettingsUtil.getArrayModuleRandom()){
				        partie2.addAll(combatModule); partie2.addAll(renderModule); partie2.addAll(playerModule); partie2.addAll(movementModule);
				        partie2.addAll(paramsModule); partie2.addAll(miscModule); partie2.addAll(specialModule);
				        if(SettingsUtil.getArrayAlphabetique()){ Collections.sort(partie2); }
				        if(SettingsUtil.getArrayNameLength()){ Collections.sort(partie2, new StringLength()); }
				    }
				   
				  //3) Inverser / Ne pas inverser
				   
				   if(SettingsUtil.getArrayInvert()){
				    Collections.reverse(partie2);
				   }
				   if(SettingsUtil.getArrayNoInvert()) { }
				   
				   //4) Avec Encadré Neko / Sans encadré / Avec encadré par noms
				    
				    if(SettingsUtil.getArrayDrawNekoBox()){
				    	RenderUtils.drawRect(((GuiScreen.width-var.NekoFont.getStringWidth(color)-90)), yPos-2, ((GuiScreen.width)), yPos+multi*(var.NekoFont.FONT_HEIGHT + 1) - 1, c);
				    	for(String s : partie2){
				    		DrawArray(s,yPos,false);
				    	}
				    }
				    if(SettingsUtil.getArrayNoDrawBox()){
				      for(String s : partie2){
				    		DrawArray(s,yPos,false);
				      }
				    }
				    if(SettingsUtil.getArrayDrawNameBox()){
				    	for(String s : partie2){
				    		DrawArray(s,yPos,true);
				      }
				    }
		}
	
	public static void DrawArray(String moduleName, int ypos, boolean toggle) {
		
		if(moduleName.toLowerCase().contains("regen")) {
			moduleName+=" ("+Regen.regen+")";
		}
		if (moduleName.toLowerCase().contains("blink")) {
		  moduleName+=" ("+Blink.packet.size()+")";
		}
		
		if(toggle) {
			//Dessine un rectangle pour chaque modules
			DrawRectangle(moduleName, ypos);
		}
		
		//Si DrawRectangle off, Dessine que le texte.
		if(SettingsUtil.getRainbowArray()) {
			var.NekoFont.drawStringWithShadow(moduleName, ((GuiScreen.width-5)-(var.NekoFont).getStringWidth(moduleName)), ypos, outlineColor);
		} else if(SettingsUtil.getUniColorArray()){
			var.NekoFont.drawStringWithShadow(moduleName, ((GuiScreen.width-5)-(var.NekoFont).getStringWidth(moduleName)), ypos, outlineColor);
		} else {
			var.NekoFont.drawStringWithShadow(moduleName, ((GuiScreen.width-5)-(var.NekoFont).getStringWidth(moduleName)), ypos, 0);
		}
		   yPos += var.NekoFont.FONT_HEIGHT + 1;
	}
	
	public static void DrawRectangle(String moduleName, int ypos) {
		RenderUtils.drawRect((((GuiScreen.width-7)-(var.NekoFont).getStringWidth(moduleName))),
				ypos-2, ((GuiScreen.width)-2), ypos+(var.NekoFont.FONT_HEIGHT + 1) - 1, c);
	}
	
	public static void renderEffect() {
		Minecraft mc = Minecraft.getMinecraft();
		ScaledResolution scaled = new ScaledResolution(mc);
		GL11.glPushMatrix();
        int size = 16;
        int margin = -5;
        float x = (scaled.getScaledWidth() - size * 2) - margin;
        float y = (scaled.getScaledHeight() - size * 2) - margin;
        Collection var4 = mc.thePlayer.getActivePotionEffects();
        int i = 0;
        if (!var4.isEmpty()) {
            for (Iterator var6 = mc.thePlayer.getActivePotionEffects().iterator(); var6.hasNext(); ) {
                PotionEffect var7 = (PotionEffect) var6.next();
                Potion var8 = Potion.potionTypes[var7.getPotionID()];
                GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
                mc.getTextureManager().bindTexture(new ResourceLocation("textures/gui/container/inventory.png"));
                if (var8.hasStatusIcon()) {
                    int var9 = var8.getStatusIconIndex();
                    new Gui().drawTexturedModalRect((int) x - 35, (int) y - (18 * i), var9 % 8 * 18, 198 + var9 / 8 * 18, 18, 18);
                    var.NekoFont.drawStringWithShadow((var7.getDuration() <= 300 ? "§c" : "§f") + Potion.getDurationString(var7), (float) x - Potion.getDurationString(var7).length() - 5, (float) y - (18 * i) + 6, -1);
                    i++;
                }
            }
        }
        GL11.glPopMatrix();
	}
	
	public static void renderArmor() {
		Minecraft mc = Minecraft.getMinecraft();
		ScaledResolution scaled = new ScaledResolution(mc);
		int itemX = scaled.getScaledWidth() / 2 + 9;
        for (int i = 0; i < 5; i++) {
            ItemStack ia = mc.thePlayer.getEquipmentInSlot(i);
            if (ia == null) continue;

            float oldZ = mc.getRenderItem().zLevel;
            GL11.glPushMatrix();
            GlStateManager.clear(GL11.GL_ACCUM);
            GlStateManager.disableAlpha();
            RenderHelper.enableGUIStandardItemLighting();
            mc.getRenderItem().zLevel = -100;
            mc.getRenderItem().renderItemIntoGUI(ia, itemX, scaled.getScaledHeight() - 55);
            mc.getRenderItem().func_175030_a(mc.fontRendererObj, ia, itemX, scaled.getScaledHeight() - 55);
            mc.getRenderItem().zLevel = oldZ;
            RenderHelper.disableStandardItemLighting();
            GlStateManager.enableAlpha();
            GL11.glPopMatrix();

            if (ia.getItem() instanceof ItemSword || ia.getItem() instanceof ItemTool || ia.getItem() instanceof ItemArmor || ia.getItem() instanceof ItemBow) {
                GlStateManager.pushMatrix();
                int durability = ia.getMaxDamage() - ia.getItemDamage();
                int y = scaled.getScaledHeight() - 60;
                GlStateManager.scale(0.5, 0.5, 0.5);
                var.NekoFont.drawStringWithShadow((ia.getMaxDamage()/10>durability ? "§c" : "§f") + durability, (itemX + 4) / (float) 0.5, ((float) y) / (float) 0.5, 0xffffff);
                GlStateManager.popMatrix();
            }
            itemX += 16;
        }
	}	
	
	public static void renderHUD() {
		if (Minecraft.getMinecraft().currentScreen instanceof ClickGUI)
			return;
		  yP=20;
		  String var3="";
		  Minecraft mc = Minecraft.getMinecraft();
		  ScaledResolution scaled = new ScaledResolution(mc);	
		  NumberFormat form = null;
		  Locale loc = new Locale("FR", "CH");
		  int ping=-1;
		  for (Object o : mc.theWorld.playerEntities) {
				if (o instanceof EntityPlayer) {
					EntityPlayer en = (EntityPlayer) o;
					try {
						NetworkPlayerInfo npi = (NetworkPlayerInfo) mc.getNetHandler().getPlayerInfoMap().get(en.getGameProfile().getId());
						ping = npi.getResponseTime();
					} catch (Exception e) {}
				}
			}
		  if (HUD.coord || HUD.fps || HUD.fall || HUD.item || HUD.packet) {
			  int pos=20;
			  if (HUD.coord || HUD.fps)
				  pos+=11;
			  if (HUD.fall)
				  pos+=22;
			  if (HUD.item)
				  pos+=11;
			  if (HUD.packet)
				  pos+=11;
			  if (HUD.time)
				  pos+=11;
			  
			  if (!Utils.claimFinderBar.isEmpty())
				  pos+=11;
			  
			  if (HUD.stuff && mc.pointedEntity!=null)
				  if (mc.pointedEntity instanceof EntityPlayer)
					  pos+=55;
			  int var5=170;
			  
			  if (var.NekoFont.getStringWidth((var.rang.getRate()==Rate.Titan ? "§c§k55"+var.rang.getColor()+var.rang.getName()+"§c§k55" : var.rang.getColor()+var.rang.getName())+" §8[§7"+form.getNumberInstance(loc).format(var.xp)+"xp§8/§7"+form.getNumberInstance(loc).format(var.xpMax)+"xp§8]")>var5)
				  var5 =var.NekoFont.getStringWidth((var.rang.getRate()==Rate.Titan ? "§c§k55"+var.rang.getColor()+var.rang.getName()+"§c§k55" : var.rang.getColor()+var.rang.getName())+" §8[§7"+form.getNumberInstance(loc).format(var.xp)+"xp§8/§7"+form.getNumberInstance(loc).format(var.xpMax)+"xp§8]");
			  if (HUD.stuff && mc.pointedEntity!=null) {
				  if (mc.pointedEntity instanceof EntityPlayer) {
					  EntityPlayer p = (EntityPlayer) mc.pointedEntity;
					  for (int i=0;i<4;i++) {
						  if (var.NekoFont.getStringWidth(Utils.getArmorUsed(p, i)+" §6:§7 "+Utils.setColor(Utils.getArmorEnchant(p, i), "§7"))>var5) {
							  var5 =var.NekoFont.getStringWidth(Utils.getArmorUsed(p, i)+" §6:§7 "+Utils.setColor(Utils.getArmorEnchant(p, i), "§7"));
						  }
					  }
					  if (var.NekoFont.getStringWidth(Utils.getItemUsed(p)+" §6:§7 "+Utils.getItemEnchant(p))>var5) {
						  var5 = var.NekoFont.getStringWidth(Utils.getItemUsed(p)+" §6:§7 "+Utils.getItemEnchant(p));
					  }
					  
				  }
			  }
			  hudWid=var5;
              RenderUtils.drawRect(1, 10 - 1, 2 + var5 + 1, pos - 1, c);
		  }
		  
		  if ((!HUD.coord && HUD.fps) || (HUD.coord && !HUD.fps) || (HUD.coord && HUD.fps)) {
			  var3 = (HUD.coord ? "§8[§6X: §7"+form.getNumberInstance(loc).format(Math.round(mc.thePlayer.posX))+" §6Y: §7"+form.getNumberInstance(loc).format(Math.round(mc.thePlayer.posY))+" §6Z: §7"+form.getNumberInstance(loc).format(Math.round(mc.thePlayer.posZ))+"§8]" : "")+(HUD.fps ? " §7"+form.getNumberInstance(loc).format(mc.debugFPS+mc.fps)+"§6fps" : "");
			  var.NekoFont.drawStringWithShadow(var3, 2, yP, 0);
			  yP+=11;
		  }
		  if (HUD.fall) {
			  var3 = (var.rang.getRate()==Rate.Titan ? "§c§k55"+var.rang.getColor()+var.rang.getName()+"§c§k55" : var.rang.getColor()+var.rang.getName())+" §8[§7"+form.getNumberInstance(loc).format(var.xp)+"xp§8/§7"+form.getNumberInstance(loc).format(var.xpMax)+"xp§8]";
			  var.NekoFont.drawStringWithShadow(var3, 2, yP, 0);
			  yP+=11;
			  
			  var3="§dLvl."+form.getNumberInstance(loc).format(var.niveau)+" §6Bonus: §d"+form.getNumberInstance(loc).format(Math.round(Utils.getTotBonus()*100))+"% §6- §b"+form.getNumberInstance(loc).format(var.ame)+(var.ame>1 ? " souls" : " soul");
			  var.NekoFont.drawStringWithShadow(var3, 2, yP, 0);
			  yP+=11;
		  }
		  
		  if (HUD.packet) {
			  var3 = "§6Packets/sec:§7 "+p+" §6Kills: §7"+form.getNumberInstance(loc).format(Utils.kills);
			  var.NekoFont.drawStringWithShadow(var3, 2, yP, 0);
			  yP+=11;
		  }
		  
		  if (HUD.item) {
			  var3 = "§6Ping actuel: "+(ping<=50 ? "§a" : (ping>50 && ping<100) ? "§e" : "§c")+ping+"ms" ;
			  var.NekoFont.drawStringWithShadow(var3, 2, yP, 0);		
			  yP+=11;
		  }
		  
		  if (HUD.time) {
			  if (Active.bonus==0)
				  var3 = "§6Temps de jeu:§7 "+(Utils.timeInGameHour!=0 ? Utils.timeInGameHour+"h "+Utils.timeInGameMin+"min "+Utils.timeInGameSec : Utils.timeInGameMin!=0 ? Utils.timeInGameMin+"min "+Utils.timeInGameSec : Utils.timeInGameSec)+"s";
			  else
				  var3 = "§6Temps de bonus restant:§7 "+(Active.time/3600==0 ? Active.time/60==0 ? Active.time%60+"s" : Active.time/60+"min "+Active.time%60+"s" : Active.time/3600+"h "+Active.time%3600/60+"min "+Active.time%60+"s");
			  var.NekoFont.drawStringWithShadow(var3, 2, yP, 0);
			  yP+=11;
		  }
		  
		  if (!Utils.claimFinderBar.isEmpty()) {
			  var.NekoFont.drawStringWithShadow(Utils.claimFinderBar, 2, yP, 0);		
			  yP+=11;
		  }
		  
		  if (HUD.stuff && mc.pointedEntity!=null) {
			  if (mc.pointedEntity instanceof EntityPlayer) {
				  EntityPlayer p = (EntityPlayer) mc.pointedEntity;
				  var3 = "§6"+Utils.getArmorUsed(p, 3)+" §6:§7 "+Utils.setColor(Utils.getArmorEnchant(p, 3), "§7");
				  var.NekoFont.drawStringWithShadow(var3, 2, yP, 0);		
				  yP+=11;
				  var3 = "§6"+Utils.getArmorUsed(p, 2)+" §6:§7 "+Utils.setColor(Utils.getArmorEnchant(p, 2), "§7");
				  var.NekoFont.drawStringWithShadow(var3, 2, yP, 0);		
				  yP+=11;
				  var3 = "§6"+Utils.getArmorUsed(p, 1)+" §6:§7 "+Utils.setColor(Utils.getArmorEnchant(p, 1), "§7");
				  var.NekoFont.drawStringWithShadow(var3, 2, yP, 0);		
				  yP+=11;
				  var3 = "§6"+Utils.getArmorUsed(p, 0)+" §6:§7 "+Utils.setColor(Utils.getArmorEnchant(p, 0), "§7");
				  var.NekoFont.drawStringWithShadow(var3, 2, yP, 0);		
				  yP+=11;
				  var3 = "§6"+Utils.getItemUsed(p)+" §6:§7 "+Utils.getItemEnchant(p);
				  var.NekoFont.drawStringWithShadow(var3, 2, yP, 0);		
				  yP+=11;
			  }
		  }
		  var.NekoFont.drawStringWithShadow("§8[§9Neko§8]§6 §6v"+var.CLIENT_VERSION+" ", 2, 10, 0);
	}
	
	public static void renderUnclaimFinder() {
		  Minecraft mc = Minecraft.getMinecraft();
		  ScaledResolution scaled = new ScaledResolution(mc);
		  int scX = GuiScreen.width; int scY = GuiScreen.height;
		  List <TileEntity> tiles = mc.theWorld.loadedTileEntityList;
		  ArrayList <String> tilesNear = new ArrayList<String>();
		  EntityPlayerSP p = mc.thePlayer;
		  Chunk chunk = mc.theWorld.getChunkFromChunkCoords(mc.thePlayer.chunkCoordX, mc.thePlayer.chunkCoordZ);
		  for(TileEntity tile : tiles) {
			  BlockPos b = new BlockPos(tile.getPos().getX(), tile.getPos().getY(), tile.getPos().getZ());
			  ChunkCoordIntPair ct = mc.theWorld.getChunkFromBlockCoords(b).getChunkCoordIntPair();
			  int cx = chunk.xPosition; int cz = chunk.zPosition;
			  int tx = ct.chunkXPos; int tz = ct.chunkZPos;
			  int seeDistance = 2;
			  if((cx >= tx-seeDistance) && (cx <= tx+seeDistance) 
					  && (cz >= tz-seeDistance) && (cz <= tz+seeDistance)) {
				  tilesNear.add(tile+";"+b.getX()+"-"+b.getY()+"-"+b.getZ());
			  }
		  }
		  int percentage = tilesNear.size();
		  int sizeNear = percentage;
		  var.NekoFont.drawString("§6"+Integer.toString(sizeNear)+"%", (scX-scX/2)-(var.NekoFont).getStringWidth("§6"+Integer.toString(sizeNear)+"%"), scY/2-170, 0);
	}
	
	public static void renderRadarMap() {
		Minecraft mc = Minecraft.getMinecraft();
		GL11.glPushMatrix();
		int x1 = GuiScreen.width - 160;
		int x2 = GuiScreen.width - 80;
		int y1 = 0;
		int y2 = 80;
		if (!Utils.isToggle("ArrayList")) {
			x1 = GuiScreen.width - 90;
			x2 = GuiScreen.width - 10;
		}
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glDisable(GL11.GL_CULL_FACE);
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glDisable(GL11.GL_TEXTURE_2D);

		GL11.glColor4f(0.2f, 0.2f, 0.2f, 0.5f);
		GL11.glBegin(GL11.GL_QUADS);
		{
			GL11.glVertex2d(x1, y1);
			GL11.glVertex2d(x2, y1);
			GL11.glVertex2d(x2, y2);
			GL11.glVertex2d(x1, y2);
		}
		GL11.glEnd();
		GL11.glLineWidth(2f);
		GL11.glColor4f(0.7f, 0.3f, 0.3f, 1f);
		GL11.glBegin(GL11.GL_LINE_LOOP);
		{
			GL11.glVertex2d(x1, y1);
			GL11.glVertex2d(x2, y1);
			GL11.glVertex2d(x2, y2);
			GL11.glVertex2d(x1, y2);
		}
		GL11.glEnd();
		GL11.glColor4f(0.1f, 1f, 0.1f, 1f);
		GL11.glLineWidth(1f);
		float rotation = -((mc.thePlayer.prevRotationYawHead
				+ (mc.thePlayer.rotationYawHead - mc.thePlayer.prevRotationYawHead) * mc.timer.renderPartialTicks));
		for (Entity en : mc.theWorld.loadedEntityList) {
			if (!(en instanceof EntityLivingBase)) {
				continue;
			}
			if(en instanceof EntityArmorStand) {
				continue;
			}
			if (en instanceof EntityPlayer) {
				if (Utils.IsInTab(((EntityPlayer) en).getName())) {
					if(Friends.isFriend(((EntityPlayer) en).getName())) {
						GL11.glColor4f(Radar.friend[0], Radar.friend[1], Radar.friend[2], Radar.fr ? 1f : 0f);
					} else {
						if (mc.thePlayer.isOnSameTeam((EntityLivingBase) en)) {
							GL11.glColor4f(Radar.me[0], Radar.me[1], Radar.me[2], Radar.BoolME ? 1f : 0f);
						} else {
							GL11.glColor4f(Radar.enemies[0], Radar.enemies[1], Radar.enemies[2], Radar.BoolENEMIES ? 1f : 0f);
						}
					}
				} else {
					GL11.glColor4f(Radar.npc[0], Radar.npc[1], Radar.npc[2], Radar.boolNPC ? 1f : 0f);
				}
			}
			if (en instanceof IMob) {
				GL11.glColor4f(Radar.mobs[0], Radar.mobs[1], Radar.mobs[2], Radar.BoolMOBS ? 1f : 0f);
			}
			if (en instanceof EntityAnimal) {
				GL11.glColor4f(Radar.animals[0], Radar.animals[1], Radar.animals[2], Radar.BoolANIMALS ? 1f : 0f);
			}
			if (en instanceof INpc || en instanceof EntityIronGolem) {
				GL11.glColor4f(Radar.golem[0], Radar.golem[1], Radar.golem[2], Radar.BoolGOLEM ? 1f : 0f);
			}
			GL11.glTranslated((x1 + x2) / 2, (y1 + y2 / 2), 0);
			GL11.glRotatef(rotation, 0, 0, 1);
			GL11.glRotatef(180, 0, 0, 1);
			double posX = -(mc.thePlayer.posX
					- ((en.lastTickPosX + (en.posX - en.lastTickPosX) * mc.timer.renderPartialTicks))) / 3.1;
			double posZ = -(mc.thePlayer.posZ
					- ((en.lastTickPosZ + (en.posZ - en.lastTickPosZ) * mc.timer.renderPartialTicks))) / 3.1;
			double posY = -(mc.thePlayer.posY
					- ((en.lastTickPosY + (en.posY - en.lastTickPosY) * mc.timer.renderPartialTicks))) / 500;
			GL11.glPushMatrix();
			GL11.glScaled((1.4 + posY), (1.4 + posY), 1);
//			glEnable(GL_BLEND);
//			GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
			GL11.glBegin(GL11.GL_TRIANGLE_FAN);
			{
				for (int i = 0; i <= 10; i++) {
					double angle = 2 * Math.PI * i / 10;
					double x = Math.cos(angle);
					double y = Math.sin(angle);
					GL11.glVertex3d(x + posX, y + posZ, 365 + posY);
				}
			}
			GL11.glEnd();
			GL11.glPopMatrix();
			GL11.glRotatef(-rotation, 0, 0, 1);
			GL11.glRotatef(-180, 0, 0, 1);
			GL11.glTranslated(-((x1 + x2) / 2), -((y1 + y2) / 2), 0);
		}
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glEnable(GL11.GL_CULL_FACE);
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glPopMatrix();
		GL11.glColor4f(1f, 1f, 1f, 1f);
	}
	
	public static void renderRadarInfos() {
		Minecraft mc = Minecraft.getMinecraft();
		//Start
		GL11.glPushMatrix();
		int x1 = GuiScreen.width - 160;
		int x2 = GuiScreen.width - 80;
		int y1 = 85;
		int y2 = 110;
		
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glDisable(GL11.GL_CULL_FACE);
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		
		GL11.glColor4f(0.2f, 0.2f, 0.2f, 0.5f);
		GL11.glBegin(GL11.GL_QUADS);
		{
			GL11.glVertex2d(x1, y1);
			GL11.glVertex2d(x2, y1);
			GL11.glVertex2d(x2, y2);
			GL11.glVertex2d(x1, y2);
		}
		GL11.glEnd();
		
		GL11.glLineWidth(2f);
		GL11.glColor4f(0.7f, 0.3f, 0.3f, 1f);
		GL11.glBegin(GL11.GL_LINE_LOOP);
		{
			GL11.glVertex2d(x1, y1);
			GL11.glVertex2d(x2, y1);
			GL11.glVertex2d(x2, y2);
			GL11.glVertex2d(x1, y2);
		}
		GL11.glEnd();
		GL11.glColor4f(0.1f, 1f, 0.1f, 1f);
		GL11.glLineWidth(1f);
		GL11.glColor4f(Radar.npc[0], Radar.npc[1], Radar.npc[2], Radar.boolNPC ? 1f : 0f);
		GL11.glTranslated((x1 + x2) / 2, (y1 + y2 / 2), 0);
		GL11.glRotatef(180, 0, 0, 1);
		GL11.glPushMatrix();
		GL11.glScaled((1.4), (1.4), 1);
		GL11.glBegin(GL11.GL_TRIANGLE_FAN);
		{
			for (int i = 0; i <= 10; i++) {
				double angle = 2 * Math.PI * i / 10;
				double x = Math.cos(angle);
				double y = Math.sin(angle);
				GL11.glVertex3d(x + 27, y + 37, 365);
			}
		}
		GL11.glEnd();
		GL11.glColor4f(Radar.mobs[0], Radar.mobs[1], Radar.mobs[2], Radar.BoolMOBS ? 1f : 0f);
		GL11.glBegin(GL11.GL_TRIANGLE_FAN);
		{
			for (int i = 0; i <= 10; i++) {
				double angle = 2 * Math.PI * i / 10;
				double x = Math.cos(angle);
				double y = Math.sin(angle);
				GL11.glVertex3d(x + 27, y + 32, 365);
			}
		}
		GL11.glEnd();
		GL11.glPopMatrix();
		GL11.glRotatef(-180, 0, 0, 1);
		GL11.glTranslated(-((x1 + x2) / 2), -((y1 + y2) / 2), 0);
		
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glEnable(GL11.GL_CULL_FACE);
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glPopMatrix();
		GL11.glColor4f(1f, 1f, 1f, 1f);
		var.NekoFont.drawStringWithShadow("Test", x1+10, y1+5, 10856100);
	}
	
	public static void renderRadarInformations() {
		Minecraft mc = Minecraft.getMinecraft();
		//Start
		GL11.glPushMatrix();
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glDisable(GL11.GL_CULL_FACE);
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		
		int x1 = GuiScreen.width - 160;
		int x2 = GuiScreen.width - 80;
		int y1 = 85;
		int y2 = 105;
		//Setup quad
		GL11.glColor4f(0.2f, 0.2f, 0.2f, 0.2f);
		GL11.glPushMatrix();
		GL11.glBegin(GL11.GL_QUADS);
		{
			GL11.glVertex2d(x1, y1);
			GL11.glVertex2d(x2, y1);
			GL11.glVertex2d(x2, y2);
			GL11.glVertex2d(x1, y2);
		}
		GL11.glEnd();
		GL11.glPopMatrix();
		//Actions
		int y = 90;
		int x = GuiScreen.width - 160;
		GL11.glColor4f(Radar.friend[0], Radar.friend[1], Radar.friend[2], Radar.fr ? 1f : 0f);
		GL11.glPushMatrix();
		//GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glBegin(GL11.GL_TRIANGLE_FAN);
		{
			GL11.glVertex2d(x, y);
		}
		GL11.glEnd();
		GL11.glPopMatrix();
		/*GL11.glColor4f(Radar.friend[0], Radar.friend[1], Radar.friend[2], Radar.fr ? 1f : 0f);
		GL11.glPushMatrix();
		Utils.drawGL2DString(var.NekoFont.getFormatFromString("Amis"), x+5, y, 7, var.NekoFont.getStringWidth("Amis"));
		GL11.glPopMatrix();*/
		//End
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glEnable(GL11.GL_CULL_FACE);
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glPopMatrix();
		GL11.glColor4f(1f, 1f, 1f, 1f);
	}
	
	public static int ccc = 0;
	
	public static void renderRadarText() {
		  Minecraft mc = Minecraft.getMinecraft();
		  ScaledResolution scaled = new ScaledResolution(mc);
		  ArrayList<EntityPlayer> s = Utils.getAllPlayer();
		  if (!Utils.isToggle("ArrayList"))
			  yPos=10;
		  else
			  yPos+=5;
		  if(Radar.radarMap && !Utils.isToggle("ArrayList"))
			  yPos = 100;
		  String color="§c";		
		  if (s.size()==0) {
			  return;
		  }
		  double d=200;
		  String f = "§d";
		  int c=0;
		  List<PlayerSort> PlayersStorage = new ArrayList<PlayerSort>();
		  for (EntityPlayer en : s) {
			  if (!en.isDead && en != mc.thePlayer && en.getDistanceToEntity(mc.thePlayer)<200) {
				  if(c < 20) {
					  String var3 = "null";
					  int place = -1;
					  String name = "null";
					  if (Utils.IsInTab(en.getName())) {
						  if(Friends.isFriend(en.getName())) {
							  if(Radar.fr) {
								  var3 = "§a"+ (Radar.radarRealName ? en.getName() : en.getDisplayName().getUnformattedText()) +" §8["+color+Math.round(en.getDistanceToEntity(mc.thePlayer))+"m§8]";
								  c++;
							  }
						  } else {
							  if(Radar.BoolENEMIES) {
								  var3 = "§c"+ (Radar.radarRealName ? en.getName() : en.getDisplayName().getUnformattedText()) +" §8["+color+Math.round(en.getDistanceToEntity(mc.thePlayer))+"m§8]";
								  c++;
							  }
						  }
					  } else {
						  if(Radar.boolNPC) {
							  var3 = "§d"+ (Radar.radarRealName ? en.getName() : en.getDisplayName().getUnformattedText()) +" §8["+color+Math.round(en.getDistanceToEntity(mc.thePlayer))+"m§8]";
							  c++;
						  }
					  }
					  if(var3 != "null") {
						  place = Math.round(en.getDistanceToEntity(mc.thePlayer));
						  name = Radar.radarRealName ? en.getName() : en.getDisplayName().getUnformattedText();
						  PlayersStorage.add(new PlayerSort(place, name, var3));
					  }
				  }
			  }
		  }
		  if(!PlayersStorage.isEmpty()) {
			  Collections.sort(PlayersStorage, new SortByNumber());
			  for (int i=0; i<PlayersStorage.size(); i++) {
				  var.NekoFont.drawStringWithShadow(PlayersStorage.get(i).FullString, ((GuiScreen.width-5)-(var.NekoFont).getStringWidth(PlayersStorage.get(i).FullString)), yPos, 0);
				  yPos+=10;
			  }
		  }
		  
	}
	
}

class PlayerSort {
	
	int Distance;
	String name;
	String FullString;
	
	public PlayerSort(int Distance, String name, String FullString) {
		this.Distance = Distance; this.name = name; this.FullString = FullString;
	}
	
}

class SortByNumber implements Comparator<PlayerSort> {
	
	public int compare(PlayerSort a, PlayerSort b) {
		if(a.Distance == b.Distance) {
			return a.name.compareTo(b.name);
		} else {
			return a.Distance - b.Distance;
		}
	}

	
}

class StringLength implements Comparator<String> {

	static Client var = Client.getNeko();
	  public int compare(String o1, String o2) {
	    if ((var.NekoFont).getStringWidth(o1) < (var.NekoFont).getStringWidth(o2)) {
	      return 1;
	    } else if ((var.NekoFont).getStringWidth(o1) > (var.NekoFont).getStringWidth(o2)) {
	      return -1;
	    } else {
	      return 0;
	    }
	  }
	}
	 
