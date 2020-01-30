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

import org.darkstorm.minecraft.gui.util.GuiManagerDisplayScreen;
import org.lwjgl.opengl.GL11;

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
import neko.utils.RenderUtils;
import neko.utils.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.CombatEntry;
import net.minecraft.util.ResourceLocation;

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
		  
		  //COMBAT, RENDER, PLAYER, MOVEMENT, PARAMS, MISC, HIDE, Special;
		  
		  //Render du format AllModulesON
		  /*for(String s : allModulesON) {
			  if(s.equalsIgnoreCase("regen")) {
				  s+=" ("+Regen.regen+")";
			  }
			  if(s.equalsIgnoreCase("blind")) {
				  s+=" ("+Blink.packet.size()+")";
			  }
			  DrawArray(s, yPos);
		  }*/
				  
				  
				  
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
            RenderHelper.enableStandardItemLighting();
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
	
	public static void renderRadar() {
		  Minecraft mc = Minecraft.getMinecraft();
		  ScaledResolution scaled = new ScaledResolution(mc);
		  ArrayList<EntityPlayer> s = Utils.getAllPlayer();
		  if (!Utils.isToggle("ArrayList"))
			  yPos=10;
		  else
			  yPos+=5;
		  String color="§c";		
		  if (s.size()==0) {
			  return;
		  }
		  double d=200;
		  String f = "§d";
		  int c=0;
		  for (EntityPlayer en : s) {
			  if (en.getDistanceToEntity(mc.thePlayer)>d)
				  continue;
			  if (en.getDistanceToEntity(mc.thePlayer)>20) {
				  color="§a";
			  } else 
				  color="§c";
			  if (Friends.isFriend(en.getName()))
				  f="§a";
			  else
				  f="§7";
			  if (c<=20) {
				  	if (!Radar.fr && Friends.isFriend(en.getName())) {} else {
				  		String var3 = f+en.getDisplayName().getUnformattedText()+" §8["+color+Math.round(en.getDistanceToEntity(mc.thePlayer))+"m§8]";
				  		var.NekoFont.drawStringWithShadow(var3, ((GuiScreen.width-5)-(var.NekoFont).getStringWidth(var3)), yPos, 0);
				  		yPos+=10;
						c++;
				  	}
			  }
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
	 
