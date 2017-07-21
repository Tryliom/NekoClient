package neko.utils;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.Proxy;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;

import org.darkstorm.minecraft.gui.component.Frame;
import org.darkstorm.minecraft.gui.theme.simple.SimpleTheme;
import org.lwjgl.input.Keyboard;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ibm.icu.text.SimpleDateFormat;
import com.mojang.authlib.Agent;
import com.mojang.authlib.UserAuthentication;
import com.mojang.authlib.exceptions.AuthenticationException;
import com.mojang.authlib.yggdrasil.YggdrasilAuthenticationService;

import jdk.nashorn.internal.parser.JSONParser;
import neko.Client;
import neko.dtb.RequestThread;
import neko.gui.GuiAltManager;
import neko.gui.InGameGui;
import neko.gui.NekoUpdate;
import neko.guicheat.GuiManager;
import neko.lock.Lock;
import neko.module.Category;
import neko.module.Module;
import neko.module.ModuleManager;
import neko.module.modules.Antiafk;
import neko.module.modules.AutoClic;
import neko.module.modules.AutoMLG;
import neko.module.modules.AutoPot;
import neko.module.modules.Autoarmor;
import neko.module.modules.Autosoup;
import neko.module.modules.Build;
import neko.module.modules.CallCmd;
import neko.module.modules.Cheststealer;
import neko.module.modules.ClickAim;
import neko.module.modules.Dolphin;
import neko.module.modules.DropShit;
import neko.module.modules.Fastbow;
import neko.module.modules.Fasteat;
import neko.module.modules.Fire;
import neko.module.modules.FireTrail;
import neko.module.modules.Flight;
import neko.module.modules.Freecam;
import neko.module.modules.Friends;
import neko.module.modules.Glide;
import neko.module.modules.God;
import neko.module.modules.HUD;
import neko.module.modules.ItemESP;
import neko.module.modules.KillAura;
import neko.module.modules.Longjump;
import neko.module.modules.Lot;
import neko.module.modules.NekoChat;
import neko.module.modules.NoClip;
import neko.module.modules.Nuker;
import neko.module.modules.Paint;
import neko.module.modules.Phase;
import neko.module.modules.Ping;
import neko.module.modules.Power;
import neko.module.modules.PushUp;
import neko.module.modules.Pyro;
import neko.module.modules.Radar;
import neko.module.modules.Reach;
import neko.module.modules.Reflect;
import neko.module.modules.Regen;
import neko.module.modules.Register;
import neko.module.modules.SmoothAim;
import neko.module.modules.SpamBot;
import neko.module.modules.Speed709;
import neko.module.modules.Step;
import neko.module.modules.Timer;
import neko.module.modules.TpBack;
import neko.module.modules.Tracers;
import neko.module.modules.Trigger;
import neko.module.modules.VanillaTp;
import neko.module.modules.Velocity;
import neko.module.modules.Wallhack;
import neko.module.modules.Water;
import neko.module.modules.WorldTime;
import neko.module.modules.Xray;
import neko.module.other.Active;
import neko.module.other.BddManager;
import neko.module.other.Chat;
import neko.module.other.Conditions;
import neko.module.other.Event;
import neko.module.other.Irc;
import neko.module.other.IrcMode;
import neko.module.other.OnlyRpgManager;
import neko.module.other.Rank;
import neko.module.other.Rate;
import neko.module.other.SpeedEnum;
import neko.module.other.TempBon;
import neko.module.other.Xp;
import net.mcleaks.MCLeaks;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.event.ClickEvent;
import net.minecraft.event.ClickEvent.Action;
import net.minecraft.event.HoverEvent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.play.client.C00PacketKeepAlive;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.src.Json;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.Session;
import net.minecraft.world.WorldSettings.GameType;

public class Utils {
	/*
	 * Utilité de cette classe:
	 * Sert pour les méthodes pratiques utilisées partout et variables 
	 */	
	public static Minecraft mc = Minecraft.getMinecraft();
	public static boolean warn=false;
	public static double warnB=0;
	public static boolean dim=false;
	public static boolean zoom=false;
	public static boolean display=true;
	public static boolean scoreboard=true;
	public static boolean xp=false;
	public static boolean deathoff=false;
	public static boolean mod=true;
	public static int timeInGameMs=0;
	public static int timeInGameSec=0;
	public static int timeInGameMin=0;
	public static int timeInGameHour=0;
	public static String verif=null;
	public static String vMod=null;
	public static boolean vDisplay=false;
	public static boolean vXp=false;
	public static boolean vZoom=false;
	public static int lastAccount=0;
	public static int maxHelp=7;
	public static boolean nyah=false;
	public static String nyahh="";
	public static double nyahSec=10;
	// Heures
	public static boolean h1=true;
	public static boolean h10=true;
	public static boolean h50=true;
	public static boolean h100=true;
	public static boolean h200=true;
	public static boolean h666=true;
	// Heures
	public static String server;
	public static boolean n=false;
	public static int nbPack=0;
	public static boolean sword=false;
	public static javax.swing.Timer t = new javax.swing.Timer(25, new upLvl());
	public static int lvl=0;
	public static boolean changeRank=true;
	public static int limit=200;
	public static boolean limite=false;
	public static String version="Neko";
	public static int kills=0;
	public static int R=199;
	public static int G=255;
	public static int B=213;
	public static String linkSave = mc.isRunningOnMac ? System.getProperty("user.home") + "/Library/Application Support/minecraft" : System.getenv("APPDATA") + "\\.minecraft\\Neko\\";
	public static Client var = Client.getNeko();
	
	public static void addChat(String m) {
		if (verif==null)
			mc.ingameGUI.getChatGUI().printChatMessage(new ChatComponentText("§8[§9Neko§8]§6 " + m));
	}	
	
	public static void toChat(String m) {
		if (verif==null)
			mc.ingameGUI.getChatGUI().printChatMessage(new ChatComponentText(m));
	}
	
	public static void addChatText(IChatComponent m) {
		if (verif==null)
			mc.ingameGUI.getChatGUI().printChatMessage(m);
	}
	
	
	/**
	 * txt: Texte à afficher
	 * cmd: Commande à executer si action == Chat.Click
	 * desc: Description du hover
	 * onlyHover: Pour détérminer si c'est juste afficher une description sans commandes
	 * action: Détérmine l'action entre Click, Summon (Sans executé) ou Link (Ouvre un lien internet)
	 */
	public static void addChat2(String txt, String cmd, String desc, boolean onlyHover, Chat action) {
		if (verif==null) {
			if (onlyHover) {
				mc.ingameGUI.getChatGUI().printChatMessage(getHoverText(getNeko()+txt, desc));
			} else {
				if (action.equals(Chat.Click)) {
					mc.ingameGUI.getChatGUI().printChatMessage(getHoverText(getNeko(), desc).appendSibling(getClickText(txt, cmd)));
				} else if (action.equals(Chat.Summon)) {
					mc.ingameGUI.getChatGUI().printChatMessage(getHoverText(getNeko(), desc).appendSibling(getClickTextSummon(txt, cmd)));
				} else if (action.equals(Chat.Link)) {
					mc.ingameGUI.getChatGUI().printChatMessage(getHoverText(getNeko(), desc).appendSibling(getLink(txt, cmd, false)));
				}
			}
		}
	}
	
	public static void addChat2Irc(String txt, String cmd, String desc, boolean onlyHover, Chat action) {
		if (verif==null) {
			if (onlyHover) {
				mc.ingameGUI.getChatGUI().printChatMessage(getHoverText(txt, desc));
			} else {
				if (action.equals(Chat.Click)) {
					mc.ingameGUI.getChatGUI().printChatMessage(getHoverText("", desc).appendSibling(getClickText(txt, cmd)));
				} else if (action.equals(Chat.Summon)) {
					mc.ingameGUI.getChatGUI().printChatMessage(getHoverText("", desc).appendSibling(getClickTextSummon(txt, cmd)));
				} else if (action.equals(Chat.Link)) {
					mc.ingameGUI.getChatGUI().printChatMessage(getHoverText("", desc).appendSibling(getLink(txt, cmd, false)));
				}
			}
		}
	}
	
	public static String getNeko() {
		return "§8[§9Neko§8]§6 ";
	}
	
	/**
	 * 	Envoie une phrase dans le chat côté client.
	 * 	cmd: commande à executé ou texte à afficher hover.
	 * 	isCmd: Est une commande à executé ou texte à afficher en hover.
	 */	
	public static void addChatCmd(String m, String cmd, boolean neko, boolean isCmd) {
		// GuiScreen
		if (verif==null) {
			ChatComponentText c = new ChatComponentText((neko ? "§8[§9Neko§8]§6 " : "")+m);
			if (isCmd)
				c.setChatStyle(new ChatStyle().setChatClickEvent(new ClickEvent(Action.SUGGEST_COMMAND, cmd)));
			else
				c.setChatStyle(new ChatStyle().setChatHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ChatComponentText(cmd))));
			mc.ingameGUI.getChatGUI().printChatMessage(c);
		}
	}
	
	public static ChatComponentText getLink(String m, String link, boolean neko) {
		// GuiChat : func_175274_a
		ChatComponentText c = new ChatComponentText((neko ? "§8[§9Neko§8]§6 " : "")+m);
		c.setChatStyle(new ChatStyle().setChatClickEvent((new ClickEvent(Action.OPEN_URL, link))));
		return c;
	}
	
	public static void addWarn(String m) {
		if (verif==null) {
			Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessage(new ChatComponentText("§8[§9Neko§8]§c "+m+"§6 n'as pas encore été débloqué !"));
			mc.thePlayer.playSound("mob.villager.no", 1.0F, 1.0F);
		}
	}
	
	public static ChatComponentText getHoverText(String m, String hover) {
		ChatComponentText c = new ChatComponentText(m);
		c.setChatStyle(new ChatStyle().setChatHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ChatComponentText(hover))));
		return c;
	}
	
	public static ChatComponentText getClickText(String m, String click) {
		ChatComponentText c = new ChatComponentText(m);
		c.setChatStyle(new ChatStyle().setChatClickEvent(new ClickEvent(Action.SUGGEST_COMMAND, click)));
		return c;
	}
	
	public static ChatComponentText getClickTextSummon(String m, String click) {
		ChatComponentText c = new ChatComponentText(m);
		c.setChatStyle(new ChatStyle().setChatClickEvent(new ClickEvent(Action.SUGGEST_COMMAND, ("<>".equalsIgnoreCase(var.prefixCmd) ? "><" : "<>")+click)));
		return c;
	}
	
	public static int getRandInt(int i) {
		return (int) Math.round(Math.random()*i);
	}
	
	public static String getRandRank(Rate r) {
		while (true) {
			int i=0;
			for (Rank rank : ModuleManager.rang) {
				if (rank.getRate()==r) {
					i++;
				}
			}
			int j = getRandInt(i);
			int k=0;
			for (Rank rank : ModuleManager.rang) {
				if (rank.getRate()==r) {
					if (k==j) {
						return rank.getName();
					}
					k++;
				}
			}
		}
	}
	
	public static boolean isRank(String rang) {
		for (Rank r : ModuleManager.rang) {
			if (r.getName().equalsIgnoreCase(rang))
				return true;
		}
		return false;
	}
	
	public static boolean isRankLock(String rang) {
		for (Rank r : ModuleManager.rang) {
			if (r.getName().equalsIgnoreCase(rang))
				return r.isLock();
		}
		return false;
	}
	
	public static boolean setRank(String rang) {
		for (Rank r : ModuleManager.rang) {
			if (r.getName().equalsIgnoreCase(rang)) {
				if (changeRank)
					var.rang=r;
				r.setLvl(r.getLvl()+1);
				r.setLock(false);
				if (isLock("--reach pvp") && (r.getName().contains("JP") || r.getName().contains("Jean-Pierre"))) {
					addChat("§cReach pvp §adébloquée !");
					unlock("--reach pvp");					
				}
				if (r.getName().equalsIgnoreCase("Pyroman") && isLock("Pyro")) {
					addChat("§dPyro débloqué !");
					unlock("Pyro");					
				}
				return true;
			}
		}
		return false;
	}
	
	public static boolean changeRank(String rang) {
		for (Rank r : ModuleManager.rang) {
			if (r.getName().equalsIgnoreCase(rang) && !r.isLock()) {
				var.rang=r;
				if (isLock("--reach pvp") && (r.getName().contains("JP") || r.getName().contains("Jean-Pierre"))) {
					addChat("§cReach pvp §adébloquée !");
					unlock("--reach pvp");					
				}
				return true;
			}
		}
		return false;
	}
	
	public static String getRankColor(String s) {
		for (Rank r : ModuleManager.rang) {
			if (r.getName().equalsIgnoreCase(s)) {
				return r.getColor();
			}
		}
		return "§f";
	}
	
	public static Rank getRank(String s) {
		for (Rank r : ModuleManager.rang) {
			if (r.getName().equalsIgnoreCase(s)) {
				return r;
			}
		}
		return null;
	}
	
	public static int getNbRankUnlock() {
		int i=0;
		for (Rank r : ModuleManager.rang) {
			if (!r.isLock()) {
				i++;
			}
		}
		return i;
	}
	
	public static double getX(Entity entity) {
		return (entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * mc.timer.renderPartialTicks) - mc.getRenderManager().renderPosX;
	}
	
	public static double getY(Entity entity) {
		return (entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * mc.timer.renderPartialTicks) - mc.getRenderManager().renderPosY;
	}
	
	public static double getZ(Entity entity) {
		return (entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * mc.timer.renderPartialTicks) - mc.getRenderManager().renderPosZ;
	}
	
	public static String setColor(String phrase, String color) {
		String s[] = phrase.split(" ");
		String res="";
		for (int i=0;i<s.length;i++) {
			if (i!=s.length-1)
				res+=color+s[i]+" ";
			else
				res+=color+s[i];
		}
		return res;
	}
	
	public static Lock getUnlock(String name) {
		for (Lock lock : ModuleManager.Lock) {
			if (lock.getName().equalsIgnoreCase(name)) {
				return lock;
			}
		}
		return null;
	}
	
	public static void getLock(String name) {
		for (Lock lock : ModuleManager.Lock) {
			if (lock.getName().equalsIgnoreCase(name)) {
				addChat("§6=§b-§6=§b-§6=§b-§7 "+setColor(lock.getName().replaceAll("--", var.prefixCmd), "§6")+" §b-§6=§b-§6=§b-§6=");
				addChat("Description: §7"+setColor(lock.getDesc().replaceAll("--", var.prefixCmd), "§7"));
				addChat("Type: §7"+lock.getType());
				addChat("Coût: §7"+(lock.getUnit().equalsIgnoreCase("???") ? "???" : lock.getCout()+" "+lock.getUnit())+" "+(lock.isLock() ? "§cBloqué" : "§aDébloqué"));
			}
		}
	}
	
	public static void getRandUnlock() {
		int i=0;
		for (Lock lock : ModuleManager.Lock) {
			if (lock.isLock() && lock.getUnit().equalsIgnoreCase("???")) {
				i++;
			}
		}
		if (i!=0) {
			int j = getRandInt(i);
			i=0;
			for (Lock lock : ModuleManager.Lock) {
				if (lock.isLock() && lock.getUnit().equalsIgnoreCase("???")) {
					if (i==j) {
						unlock(lock.getName());
						addChat("§c"+lock.getName().replaceAll("--", var.prefixCmd)+"§6 a été débloqué !!");
						if (lock.getName().equalsIgnoreCase("--rankmanager"))
							addChat("§aDe nouvelle commandes sont disponibles dans le "+var.prefixCmd+"trade !");
					}
					i++;
				}
			}
		} else {
			addChat("§aVous avez déjà tout débloqué !");
			if (Math.random()<0.3) {
				int xp = getRandInt(1500);
				checkXp(xp);
				addChat("§cVous recevez donc la somme de §b"+xp+"§cxp !");
			} else {
				addChat("§cVous recevez donc 10 tickets de lotterie !");
				var.lot+=10;
			}
		}
	}
	
	//TODO: Tot bonus
	public static double getTotBonus() {
		return (var.rang.getTotBonus()+Active.bonus+var.bonus+var.tempBonus)/100;
	}
	
	public static void unlock(String name) {
		for (Lock lock : ModuleManager.Lock) {
			if (lock.getName().equalsIgnoreCase(name)) {
				lock.setLock(false);
			}
		}
	}
	
	public static boolean isLock(String name) {
		for (Lock lock : ModuleManager.Lock) {
			if (lock.getName().equalsIgnoreCase(name)) {
				return lock.isLock(); 
			}
		}
		return false;
	}
	
	public static void toggleModule(String module) {
		try {
			for (Module mod : ModuleManager.ActiveModule) {
				if (mod.getName().equalsIgnoreCase(module)) {
					if (mod.getToggled()) {
						mod.setToggled(false);
					} else {
						mod.setToggled(true);
					}
				}
			}
		} catch (Exception e) {}
	}
	
	public static int getPlayerPing(String name) {
		for (Object o : mc.theWorld.playerEntities) {
			if (o instanceof EntityPlayer) {
				int ping=-1;
				EntityPlayer en = (EntityPlayer) o;
				if (en.getName().equalsIgnoreCase(name)) {
					try {
						NetworkPlayerInfo npi = (NetworkPlayerInfo) mc.getNetHandler().getPlayerInfoMap().get(en.getGameProfile().getId());
						ping = npi.getResponseTime();
					} catch (Exception e) {						
						
					}
					return ping;
				}
			}
		}
		return -1;
	}
	
	public static GameType getPlayerGameType(String name) {
		for (Object o : mc.theWorld.playerEntities) {
			if (o instanceof EntityPlayer) {
				EntityPlayer en = (EntityPlayer) o;
				if (en.getName().equalsIgnoreCase(name)) {
					try {
						NetworkPlayerInfo npi = (NetworkPlayerInfo) mc.getNetHandler().getPlayerInfoMap().get(en.getGameProfile().getId());
						return npi.getGameType();
					} catch (Exception e) {						
						
					}
				}
			}
		}
		return GameType.NOT_SET;
	}
	
	public static boolean IsInTab(String name) {
		for (Object o : mc.theWorld.playerEntities) {
			if (o instanceof EntityPlayer) {
				EntityPlayer en = (EntityPlayer) o;
				if (en.getName().equalsIgnoreCase(name)) {
					try {
						NetworkPlayerInfo npi = (NetworkPlayerInfo) mc.getNetHandler().getPlayerInfoMap().get(en.getGameProfile().getId());
						return npi!=null;
					} catch (Exception e) {						
						
					}
				}
			}
		}
		return false;
	}
		
	
	public static EntityPlayer getPlayer(String s) {
		if (s.equalsIgnoreCase(mc.session.getUsername()))
				return mc.thePlayer;
		if (MCLeaks.isAltActive())
			if (MCLeaks.getMCName().equalsIgnoreCase(s))
				return mc.thePlayer;
		
		for (Object theObject : mc.theWorld.playerEntities) {
			EntityPlayer entity = (EntityPlayer) theObject;                         
                
                if (entity.getName().equalsIgnoreCase(s)) {
                	return (EntityPlayer) entity;
                }                    	                   		           
		}        			
		return null;
	}
	
	public static ArrayList<EntityPlayer> getAllPlayer() {
		ArrayList<EntityPlayer> s = new ArrayList<>();		
		for (Object theObject : mc.theWorld.playerEntities) {
			EntityPlayer en = (EntityPlayer) theObject;                         
                int l=0;
            if (en.getName().equalsIgnoreCase(mc.session.getUsername()) || en.getName().equalsIgnoreCase(mc.thePlayer.getName()))
				l++;
            if (MCLeaks.isAltActive())
            	if (MCLeaks.getMCName().equalsIgnoreCase(en.getName()))
            		l++;
            if (l==0 && !en.isDead) {
            	s.add(en);
            }
		}        			
		return s;
	}
	
	public static Boolean isEntityInFov(Entity entity, double fov) {
		  double x = entity.posX - mc.thePlayer.posX;
	      double z = entity.posZ - mc.thePlayer.posZ;
	      double h = mc.thePlayer.posY + mc.thePlayer.getEyeHeight() - (entity.posY + entity.getEyeHeight());
	      double h2 = Math.sqrt(x * x + z * z);
	      float yaw = (float)(Math.atan2(z, x) * 180.0D / 3.141592653589793D) - 90.0F;
	      float pitch = (float)(Math.atan2(h, h2) * 180.0D / 3.141592653589793D);
	      double xDist = getDistanceBetweenAngles(yaw, mc.thePlayer.rotationYaw % 360.0F);
	      double yDist = getDistanceBetweenAngles(pitch, mc.thePlayer.rotationPitch % 360.0F);
	      double angleDistance = Math.sqrt(xDist * xDist + yDist * yDist);
	      if (angleDistance > fov)
	    	return false;
	      else		
	    	return true;
	}
	
	public static float getDistanceBetweenAngles(float angle1, float angle2) {
	    float angle3 = Math.abs(angle1 - angle2) % 360.0F;
	    if (angle3 > 180.0F) {
	      angle3 = 360.0F - angle3;
	    }
	    return angle3;
	  }	

	public static void checkEC(String user) {
		try {
			URI url = URI.create("http://stats.epicube.fr/player/"+user);
			Desktop.getDesktop().browse(url);
		} catch (Exception e) {
			addChat("Erreur");
			e.printStackTrace();
		}	

	}
	
	public static String getCoord(EntityPlayer p) {
		return "X: "+Math.round(p.posX)+" Y: "+Math.round(p.posY)+" Z: "+Math.round(p.posZ);
	}
	
	public static String getArmorEnchant(EntityPlayer p, int l) {
			if (p.getCurrentArmor(l)!=null) {
				ItemStack item = p.getCurrentArmor(l);
				if (item.isItemEnchanted()) {
					  String enchs = "";
		              NBTTagList NBTenchList = item.getEnchantmentTagList();
		              for (int o = 0; o < NBTenchList.tagCount(); o++) {
		            	  short id = NBTenchList.getCompoundTagAt(o).getShort("id");
		                  short lvl = NBTenchList.getCompoundTagAt(o).getShort("lvl");
		                
		                  if (enchs.isEmpty()) {
		                	  enchs=Enchantment.func_180306_c(id).getTranslatedName(lvl);
		                  } else {
		                	  enchs+=", "+Enchantment.func_180306_c(id).getTranslatedName(lvl);
		                  }		                                  
		              }
		              if (!enchs.isEmpty())
		            	  return setColor(enchs, "§7");
				}
			}
		
		
		return "Aucun enchantement d'armure";
	}
	
	public static String getItemUsed(EntityPlayer p) {
		try {
			if (p.getCurrentEquippedItem()!=null) {
				String res=p.getCurrentEquippedItem().getDisplayName();
				if (p.getCurrentEquippedItem().getMaxDamage()==0) {
					return res;
				} else
				return res + " §c" + (p.getCurrentEquippedItem().getMaxDamage()-p.getCurrentEquippedItem().getItemDamage()) + "/" + p.getCurrentEquippedItem().getMaxDamage();
			}
		} catch (Exception e) {}
		return "Rien";
	}
	
	public static String getArmorUsed(EntityPlayer p, int l) {
		if (p.getCurrentArmor(l)!=null) {
			String res=p.getCurrentArmor(l).getDisplayName();
			if (p.getCurrentArmor(l).getMaxDamage()==0) {
				return res;
			} else
			return res + " §c" + (p.getCurrentArmor(l).getMaxDamage()-p.getCurrentArmor(l).getItemDamage()) + "/" + p.getCurrentArmor(l).getMaxDamage();
		}
		return "Pas d'équippement";
	}	
	
	public static String getItemEnchant(EntityPlayer p) {
		if (p.getCurrentEquippedItem()!=null) {
			ItemStack item = p.getCurrentEquippedItem();
			if (item.isItemEnchanted()) {
            String enchs = "";
            NBTTagList NBTenchList = item.getEnchantmentTagList();
            for (int o = 0; o < NBTenchList.tagCount(); o++) {
              short id = NBTenchList.getCompoundTagAt(o).getShort("id");
              short lvl = NBTenchList.getCompoundTagAt(o).getShort("lvl");
              
              
            if (enchs.isEmpty()) {
            	enchs=Enchantment.func_180306_c(id).getTranslatedName(lvl);
            } else {
            	enchs+=", "+Enchantment.func_180306_c(id).getTranslatedName(lvl);
            }
                              
            }
            if (!enchs.isEmpty())
            	return setColor(enchs, "§7");
			}
		}		
		return "§7Aucun §7enchantement §7d'armes/outils";		
	}
	
	public static Boolean isInFov(EntityLivingBase entity, double fov) {
		double x = entity.posX - mc.thePlayer.posX;
	      double z = entity.posZ - mc.thePlayer.posZ;
	      double h = mc.thePlayer.posY + mc.thePlayer.getEyeHeight() - (entity.posY + entity.getEyeHeight());
	      double h2 = Math.sqrt(x * x + z * z);
	      float yaw = (float)(Math.atan2(z, x) * 180.0D / 3.141592653589793D) - 90.0F;
	      float pitch = (float)(Math.atan2(h, h2) * 180.0D / 3.141592653589793D);
	      double xDist = getDistanceBetweenAngles(yaw, mc.thePlayer.rotationYaw % 360.0F);
	      double yDist = getDistanceBetweenAngles(pitch, mc.thePlayer.rotationPitch % 360.0F);
	      double angleDistance = Math.sqrt(xDist * xDist + yDist * yDist);
	      if (angleDistance > fov) {
	        return false;
	      } else
	      return true;
	}
	
	public static String getVMod() {
		String s="";
		for (Module mod : ModuleManager.ActiveModule) {
				if (mod.getToggled()) {
					s+=mod.getName()+" ";
				}
		}
		return s;
	}
	
	public static void panic() {
		for (Module mod : ModuleManager.ActiveModule) {
			if (mod.getToggled())
				mod.setToggled(false);
		}
	}
	
	public static String getRandPlayer() {
		if (mc.thePlayer!=null) {
			if (KillAura.list.size()==0)
				for(Object o : mc.theWorld.playerEntities) {
		                EntityLivingBase entity = (EntityLivingBase) o;
		               
		                    	if (Math.random()<Math.random()) {
		                    		if (MCLeaks.isAltActive()) {
		                    			if (!entity.getName().equalsIgnoreCase(MCLeaks.getMCName())) {
		                    				return entity.getName();
		                    			}
		                    		} else if (!entity.getName().equalsIgnoreCase(mc.session.getUsername())) {
		                    			return entity.getName();
		                    		}                    		
		                    	}            
		        }
			else {
				for (String en : KillAura.list) {
					if (Math.random()<Math.random()) {
						if (MCLeaks.isAltActive()) {
                			if (!en.equalsIgnoreCase(MCLeaks.getMCName())) {
                				return en;
                			}
                		} else if (!en.equalsIgnoreCase(mc.session.getUsername())) {
                			return en;
                		}
					}
				}
			}
			return getRandPseudo();
		}
		return getRandPseudo();
	}
	
	public static String getRandPseudo() {
		int rand = (int) Math.round(Math.random()*40);
		
		switch (rand) {
			case 0: return "TryTry";
			case 1: return "JP";
			case 2: return "Jean";
			case 3: return "Robert";
			case 4: return "Pauline";
			case 5: return "Mr. X";
			case 6: return "Kuro";
			case 7: return "Koneko";
			case 8: return "Rias";
			case 9: return "Marie-antoinette";
			case 10: return "Saitama";
			case 11: return "Kido";
			case 12: return "Yaboku";
			case 13: return "Marque bleue";
			case 14: return "Marc";
			case 15: return "Shiro";
			case 16: return "Hiyori";
			case 17: return "Alex";
			case 18: return "Louis XIV";
			case 19: return "Monthanmonchax";
			case 20: return "Skelgrimdrorn";
			case 21: return "Sha'ku";
			case 22: return "Cheka";
			case 23: return "lavipoupou";
			case 24: return "Yukine";
			case 25: return "Sia";
			case 26: return "Jean Michel";
			case 27: return "Mama";
			case 28: return "Toto";
			case 29: return "John";
			case 30: return "Nyaaw";
			case 31: return "MrTigreroux";
			case 32: return "Un roux";
			case 33: return "PunKeel";
			case 34: return "SkyLouna";
			case 35: return "Ma bite";
			case 36: return "Speedkill709";
			case 37: return "Aexos";
			case 38: return "neko";
			case 39: return "Pico";
			case 40: return "Ebisu";			
		
		}
		
		return "Rien";
	}	
	
	public static void displayHelp(int num) {
		// addChat2("§6"+var.prefixCmd+"", var.prefixCmd+"", "§7", false, Chat.Summon);
		if (num>maxHelp) {
			addChat("§cErreur, dernière page actuelle: "+maxHelp);
			return;
		}
		if (var.onlyrpg.isActive()) {
			String ch="";
			for (String c : var.onlyrpg.getCheat()) {
				ch+="§7"+c+"§6, ";
			}
			ch = ch.substring(0, ch.length()-2);
			addChat("Cheat autorisés en OnlyRpg: "+ch);
			ch="";
			for (String c : var.onlyrpg.getCmd()) {
				ch+="§7"+c+"§6, ";
			}
			ch = ch.substring(0, ch.length()-2);
			addChat("Commandes autorisées en OnlyRpg: "+ch);			
			return;
		}
		addChat("========================================");
		addChat("§9§oHelp "+num+"/"+maxHelp);
		switch (num) {
		case 1 :	
			addChat2("§6"+var.prefixCmd+"Help <Commande>", var.prefixCmd+"help ", "§7Affiche la commande en détail", false, Chat.Summon);
			addChat2("§6"+var.prefixCmd+"Info", var.prefixCmd+"info ", "§7Affiche les caractérisque du Client. Avec le info <player>, affiche les infos du joueur visé", false, Chat.Summon);
			addChat2("§6"+var.prefixCmd+"Keybind", var.prefixCmd+"keybind", "§7Affiche quel cheat est assigné à quel touche", false, Chat.Summon);
			addChat2("§6"+var.prefixCmd+"Option", var.prefixCmd+"option ", "§7Quelques petits gadjets intressants", false, Chat.Summon);
			addChat2("§6"+var.prefixCmd+"Mcleaks <Token>", var.prefixCmd+"mcleaks ", "§7Permet de se connecter à un compte MCLeaks", false, Chat.Summon);
			addChat2("§6"+var.prefixCmd+"Login <User> <Mdp>", var.prefixCmd+"login ", "§7Vous connecte à un compte.\n§7Si vous laissez vide le champs du mot de passe, le compte devient un crack", false, Chat.Summon);
			addChat2("§6"+var.prefixCmd+"Log", var.prefixCmd+"log ", "§7AltManager qui permet de gérer les comptes.", false, Chat.Summon);
			addChat2("§7Le help log vous aidera pour toutes ses commandes", var.prefixCmd+"help log", "§7Cliquez pour afficher le help complet sur le log", false, Chat.Click);
			addChat2("§6"+var.prefixCmd+"Locklvl", var.prefixCmd+"locklvl", "§7Affiche ce qui est débloqué à quel lvl", false, Chat.Summon);								
			break;
			
		case 2:
			addChat2("§6"+var.prefixCmd+"Lvl", var.prefixCmd+"lvl", "§7Indique votre niveau par un joli message dans le chat :3", false, Chat.Summon);
			addChat2("§6"+var.prefixCmd+"Prefix <String>", var.prefixCmd+"prefix ", "§7Change le prefix des commandes.\n§7Actuellement '"+var.prefixCmd+"'", false, Chat.Summon);
			addChat2("§6"+var.prefixCmd+"Verif <String>", var.prefixCmd+"verif ", "§7Neko se désactive de manière non définitive.", false, Chat.Summon);
			addChat2("§7Essayez "+var.prefixCmd+"help verif pour l'explication complète ;)", var.prefixCmd+"help verif", "§7Cliquez pour afficher l'aide complète sur le verif", false, Chat.Summon);
			addChat2("§6"+var.prefixCmd+"Tp <player>", var.prefixCmd+"tp ", "§7Vous téléporte à un joueur", false, Chat.Summon);
			addChat2("§6"+var.prefixCmd+"Fov <Double>", var.prefixCmd+"fov ", "§7Change votre fov, même au dela des limites", false, Chat.Summon);
			addChat2("§6"+var.prefixCmd+"Clear", var.prefixCmd+"clear", "§7Efface le chat", false, Chat.Summon);
			addChat2("§6"+var.prefixCmd+"Mode <Player:Mob:All>", var.prefixCmd+"mode ", "§7Change la cible pour les cheats qui utilise ce fonctionnement", false, Chat.Summon);
			addChat2("§6"+var.prefixCmd+"MyPing ou Lag", var.prefixCmd+"ping", "§7Affiche votre ping réel par rapport au serveur", false, Chat.Summon);
			break;
			
		case 3:
			addChat2("§6"+var.prefixCmd+"Ip", var.prefixCmd+"ip", "§7Affiche l'ip du serveur actuel", false, Chat.Summon);
			addChat2("§6"+var.prefixCmd+"Disc <String>", var.prefixCmd+"disc ", "§7Joue le disc choisi", false, Chat.Summon);
			addChat2("§6"+var.prefixCmd+"Nyah", var.prefixCmd+"nyah", "§7Envoie un message chelou dans le chat, essayez ;3", false, Chat.Summon);
			addChat2("§6"+var.prefixCmd+"Server", var.prefixCmd+"server", "§7Affiche une liste de serveur bien pour cheat", false, Chat.Summon);
			addChat2("§6"+var.prefixCmd+"Tppos <X> <Y> <Z>", var.prefixCmd+"tppos ", "§7Vous téléporte aux coordonnées entrées", false, Chat.Summon);
			addChat2("§6"+var.prefixCmd+"Fps <Int>", var.prefixCmd+"fps ", "§7Augmente vos fps fictivement", false, Chat.Summon);
			addChat2("§6"+var.prefixCmd+"Trade", var.prefixCmd+"trade", "§7Affiche la liste de gains de jeu", false, Chat.Summon);
			addChat2("§6"+var.prefixCmd+"Coord", var.prefixCmd+"coord", "§7Affiche vos coordonnées actuelles", false, Chat.Summon);
			addChat2("§6"+var.prefixCmd+"Panic", var.prefixCmd+"panic", "§7Désactive tous les cheats activés", false, Chat.Summon);		
			break;
		
		case 4:	
			addChat2("§6"+var.prefixCmd+"Bind <Cheat> <Touche>", var.prefixCmd+"bind ", "§7Change les cheats de touche", false, Chat.Summon);
			addChat2("§6"+var.prefixCmd+"Toggle <Cheat>", var.prefixCmd+"toggle ", "§7Active/désactive un cheat. le "+var.prefixCmd+"<Cheat> fonctionne aussi", false, Chat.Summon);
			addChat2("§6"+var.prefixCmd+"Values", var.prefixCmd+"values", "§7Affiche tous les réglages des cheats", false, Chat.Summon);
			addChat2("§6"+var.prefixCmd+"Reset", var.prefixCmd+"reset", "§7Remet tous les réglages à leurs bases", false, Chat.Summon);
			addChat2("§6"+var.prefixCmd+"List", var.prefixCmd+"list", "§7Dresse une liste de tous les cheats de Neko, en vert ceux actifs", false, Chat.Summon);
			addChat2("§6"+var.prefixCmd+"Vclip <Double>", var.prefixCmd+"vclip ", "§7Vous téléporte au dessus ou en dessous de vous", false, Chat.Summon);
			addChat2("§6"+var.prefixCmd+"CheckEC", var.prefixCmd+"checkec", "§7Affiche la page de stats d'épicube pour savoir si vous êtes bannis ou non", false, Chat.Summon);
			addChat2("§6"+var.prefixCmd+"Damage", var.prefixCmd+"damage", "§7Vous vous infligez 0.5 à 1 coeur de dégât", false, Chat.Summon);
			break;
						
		case 5:		
			addChat2("§6"+var.prefixCmd+"Info <Player>", var.prefixCmd+"info ", "§7Permet de voir les caractéristiques du joueur", false, Chat.Summon);
			addChat2("§6"+var.prefixCmd+"Update", var.prefixCmd+"update", "§7Check s'il y a une mise à jour disponible", false, Chat.Summon);
			addChat2("§6"+var.prefixCmd+"Sword", var.prefixCmd+"sword", "§7Permet d'activer certains cheat que si le joueur a une épée dans la main", false, Chat.Summon);
			addChat2("§6"+var.prefixCmd+"Fov3 <Double>", var.prefixCmd+"fov3 ", "§7Change la distance à laquelle vous voyez à la 3ème personne", false, Chat.Summon);
			addChat2("§6"+var.prefixCmd+"Enchant", var.prefixCmd+"help enchant", "§7Voir l'help de l'enchant (Cliquez pour l'afficher)", false, Chat.Click);
			addChat2("§6"+var.prefixCmd+"Limit", var.prefixCmd+"help limit", "§7Voir l'help du Limit (Cliquez pour l'afficher)", false, Chat.Summon);
			addChat2("§6"+var.prefixCmd+"Config", var.prefixCmd+"config", "§7Cliquer pour affiche le Config Manager", false, Chat.Summon);
			addChat2("§6"+var.prefixCmd+"Meteore", var.prefixCmd+"meteore", "§7Affiche ou non les météores (Cubes coloriés) sur le monde", false, Chat.Summon);
			break;
			
		case 6:
			addChat2("§6"+var.prefixCmd+"Reload", var.prefixCmd+"reload", "§7Reload Neko", false, Chat.Summon);
			addChat2("§6"+var.prefixCmd+"Detector", var.prefixCmd+"detector", "§7Permet de détécter les joueurs qui sont trop rapides (Cheateurs)", false, Chat.Summon);
			addChat2("§6"+var.prefixCmd+"Version <String>", var.prefixCmd+"version ", "§7Change la version lancée dans les Snooper Settings", false, Chat.Summon);
			addChat2("§6"+var.prefixCmd+"Gm <Id>", var.prefixCmd+"gm ", "§7Change votre gamemode. Si ça échoue vous le serez quand même mais seulement\n§7pour vous afin de pouvoir exploit", false, Chat.Summon);
			addChat2("§6"+var.prefixCmd+"Invsee <Player>", var.prefixCmd+"invsee ", "§7Affiche l'inventaire du joueur noté", false, Chat.Summon);
			addChat2("§6"+var.prefixCmd+"GuiOnStart", var.prefixCmd+"guionstart", "§7Active/désactive le fait que le clickgui s'active au démarrage de Neko", false, Chat.Summon);
			addChat2("§6"+var.prefixCmd+"Hclip <Int>", var.prefixCmd+"hclip ", "§7Vous téléporte en avant/arrière", false, Chat.Summon);
			addChat2("§6"+var.prefixCmd+"Irc", var.prefixCmd+"help irc", "§7Voir l'help de l'Irc (Cliquez pour afficher)", false, Chat.Summon);
			break;
			
		case 7:
			addChat2("§6"+var.prefixCmd+"Alt", var.prefixCmd+"alt", "§7Vous connecte à un compte\n§7Commande disponible toutes les 15 secondes\n§7Comptes mis à jour toutes les 2 heures", false, Chat.Summon);
			addChat2("§6"+var.prefixCmd+"Cmd", var.prefixCmd+"cmd ", "§7Permet d'assigner des commandes à des touches\n§7Cmd <Nom> <Touche> <Commande>", false, Chat.Summon);
			addChat2("§6"+var.prefixCmd+"UnBindAll", var.prefixCmd+"unbindall", "§7Met tous les cheats sur la touche 'None'", false, Chat.Summon);
			addChat2("§6"+var.prefixCmd+"Saveall", var.prefixCmd+"saveall", "§7Save vos données manuellement", false, Chat.Summon);
			addChat2("§6"+var.prefixCmd+"Backup", var.prefixCmd+"backup", "§7Crée une backup", false, Chat.Summon);
			addChat2("§6"+var.prefixCmd+"ListPing", var.prefixCmd+"listping", "§7Affiche le ping de tous les joueurs autour de vous", false, Chat.Summon);
			addChat2("§6"+var.prefixCmd+"LvlUp", var.prefixCmd+"lvlup", "§7Active/désactive l'animation quand on augmente de lvl", false, Chat.Summon);
			addChat2("§6"+var.prefixCmd+"Classement", var.prefixCmd+"Classement", "§7Affiche le top 10 des joueurs de Neko en terme de lvl", false, Chat.Summon);
			addChat2("§6"+var.prefixCmd+"Namemc <Pseudo>", var.prefixCmd+"Namemc ", "§7Affiche l'historique des pseudos de la personne", false, Chat.Summon);
			break;															
			
		case -1:
			addChat2("§6"+var.prefixCmd+"", var.prefixCmd+"", "§7", false, Chat.Summon);
			addChat2("§6"+var.prefixCmd+"", var.prefixCmd+"", "§7", false, Chat.Summon);
			addChat2("§6"+var.prefixCmd+"", var.prefixCmd+"", "§7", false, Chat.Summon);
			addChat2("§6"+var.prefixCmd+"", var.prefixCmd+"", "§7", false, Chat.Summon);
			addChat2("§6"+var.prefixCmd+"", var.prefixCmd+"", "§7", false, Chat.Summon);
			addChat2("§6"+var.prefixCmd+"", var.prefixCmd+"", "§7", false, Chat.Summon);
			addChat2("§6"+var.prefixCmd+"", var.prefixCmd+"", "§7", false, Chat.Summon);
			addChat2("§6"+var.prefixCmd+"", var.prefixCmd+"", "§7", false, Chat.Summon);
			break;
			
		}
		if (num!=maxHelp)
			addChat2("§9-> Prochaine page", var.prefixCmd+"help "+(num+1), "§7Affiche la prochaine page", false, Chat.Click);
		addChat2("§9Document Neko disponible ici", "http://nekohc.fr/wiki.php", "§7Cliquez ici pour afficher le document complet sur Neko", false, Chat.Link);
	}
	
	public static Boolean verifBlock(Block id) {
		for (int i=0;i<Nuker.nuke.size();i++) {
      	  if (Block.getIdFromBlock(id) == Nuker.nuke.get(i)) {
      		  return true;
      	  }
        }
		return false;
	}
	
	public static int getTotRankRate(Rate r) {
		int i=0;
		for (Rank rk : ModuleManager.rang) {
			if (rk.getRate()==r)
				i++;
		}
		return i;
	}
	
	public static int getTotRankRateUnlock(Rate r) {
		int i=0;
		for (Rank rk : ModuleManager.rang) {
			if (rk.getRate()==r && !rk.isLock())
				i++;
		}
		return i;
	}
	
	public static void getFace(String email) {
		
	}
	
	public static void crit() {
		if (!mc.thePlayer.onGround)
			return;		
	    mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(mc.thePlayer.posX, mc.thePlayer.posY + 0.05D, mc.thePlayer.posZ, false));
	    mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ, false));
	    mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(mc.thePlayer.posX, mc.thePlayer.posY + 0.012511D, mc.thePlayer.posZ, false));
	    mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ, false));	    
	}
	//TODO: SaveAll
	public static void saveAll() {
		saveBind();
		saveFriends();
		saveMod();
		saveRpg();
		saveValues();
		saveXray();
		saveNuker();
		saveLock();
		saveRank();
		saveIrc();
		saveFrame();
		saveFont();
		saveShit();
		saveCmd();
	}
	
	public static void displayValues(int page) {
		ModuleManager.values.clear();
		ModuleManager.values.add("§nListes des valeurs actuelles:\n");
		ModuleManager.values.add("Vitesse du Dolphin:§7 "+Dolphin.dolph);
		ModuleManager.values.add("- - - - - - - - - - - - - - - - -");
		ModuleManager.values.add("Vitesse du Flight:§7 "+Flight.speed);
		ModuleManager.values.add("Mode 'blink':§7 "+Flight.getBlink());
		ModuleManager.values.add("- - - - - - - - - - - - - - - - -");
		ModuleManager.values.add("Kill Aura:");
		ModuleManager.values.add("Mode:§7 "+KillAura.mode);
		ModuleManager.values.add("Cps:§7 "+KillAura.cps);
		ModuleManager.values.add("Range:§7 "+KillAura.range);
		ModuleManager.values.add("Fov :§7 "+KillAura.fov+"°");
		ModuleManager.values.add("Lockview:§7 "+(KillAura.lockView ? "§aActivé" : "§cDésactivé"));
		ModuleManager.values.add("Temps avant de taper une cible:§7 "+KillAura.live/20+"sec");
		ModuleManager.values.add("Invisible:§7 "+(KillAura.invi ? "§aActivé" : "§cDésactivé"));
		ModuleManager.values.add("OnGround:§7 "+(KillAura.onground ? "§aActivé" : "§cDésactivé"));
		ModuleManager.values.add("NoArmor:§7 "+(KillAura.noarmor ? "§aActivé" : "§cDésactivé"));
		ModuleManager.values.add("Random:§7 "+(KillAura.random ? "§aActivé" : "§cDésactivé"));
		ModuleManager.values.add("Double vérification:§7 "+(KillAura.verif ? "§aActivé" : "§cDésactivé"));
		ModuleManager.values.add("NoBot:§7 "+(KillAura.nobot ? "§aActivé" : "§cDésactivé"));
		ModuleManager.values.add("Premium:§7 "+(KillAura.premium ? "§aActivé" : "§cDésactivé"));
		ModuleManager.values.add("- - - - - - - - - - - - - - - - -");
		ModuleManager.values.add("Taille nametag:§7 "+Render.varNeko);
		ModuleManager.values.add("- - - - - - - - - - - - - - - - -");
		ModuleManager.values.add("Vitesse du NoClip:§7 "+NoClip.speed);
		ModuleManager.values.add("- - - - - - - - - - - - - - - - -");
		ModuleManager.values.add("Regen:");
		ModuleManager.values.add("Paquets:§7 "+Regen.regen);
		ModuleManager.values.add("Bypass:§7 "+(Regen.bypass ? "§aActivé" : "§cDésactivé"));
		ModuleManager.values.add("- - - - - - - - - - - - - - - - -");
		ModuleManager.values.add("Vitesse du Speed:§7 "+Speed709.getSpeed().getSpe());
		ModuleManager.values.add("Mode Speed:§7 "+Speed709.getSpeed().getMode());
		ModuleManager.values.add("- - - - - - - - - - - - - - - - -");
		ModuleManager.values.add("Hauteur du Step:§7 "+Step.step);
		ModuleManager.values.add("- - - - - - - - - - - - - - - - -");
		ModuleManager.values.add("Multiplicateur du Timer:§7 "+Timer.time);
		ModuleManager.values.add("- - - - - - - - - - - - - - - - -");
		Velocity v = Velocity.getVelocity();
		ModuleManager.values.add("Velocity:");
		ModuleManager.values.add("Coefficient horizontal:§7 "+v.getHcoeff());
		ModuleManager.values.add("Coefficient vertical:§7 "+v.getVcoeff());
		ModuleManager.values.add("- - - - - - - - - - - - - - - - -");
		ModuleManager.values.add("Trigger:");
		ModuleManager.values.add("Range:§7 "+Trigger.dist);
		ModuleManager.values.add("Cps:§7 "+Trigger.cps);
		ModuleManager.values.add("Random:§7 "+(Trigger.random ? "§aActivé" : "§cDésactivé"));
		ModuleManager.values.add("- - - - - - - - - - - - - - - - -");
		ModuleManager.values.add("Distance de la Reach:§7 "+Reach.dist);
		ModuleManager.values.add("Reach pvp:§7 "+(Reach.pvp ? "§aActivée" : "§cDésactivée"));
		ModuleManager.values.add("Reach sur les blocs:§7 "+(Reach.bloc ? "§aActivée" : "§cDésactivée"));
		ModuleManager.values.add("Reach classic:§7 "+(Reach.classic ? "§aActivée" : "§cDésactivée"));
		ModuleManager.values.add("Reach aimbot:§7 "+(Reach.aimbot ? "§aActivée" : "§cDésactivée"));
		ModuleManager.values.add("Reach fov:§7 "+Reach.fov+"°");
		ModuleManager.values.add("Reach tnt:§7 "+(Reach.tnt ? "§aActivée" : "§cDésactivée"));
		ModuleManager.values.add("- - - - - - - - - - - - - - - - -");
		ModuleManager.values.add("ClickAim:");
		ModuleManager.values.add("Distance de frappe:§7 "+ClickAim.dist);
		ModuleManager.values.add("MultiAura:§7 "+(ClickAim.multiAura ? "§aActivé" : "§cDésactivé"));	
		ModuleManager.values.add("- - - - - - - - - - - - - - - - -");
		ModuleManager.values.add("VanillaTp:");
		ModuleManager.values.add("Air:§7 "+(VanillaTp.air ? "§aActivé" : "§cDésactivé"));
		ModuleManager.values.add("Classic:§7 "+(VanillaTp.classic ? "§aActivé" : "§cDésactivé"));
		ModuleManager.values.add("Top:§7 "+(VanillaTp.top ? "§aActivé" : "§cDésactivé"));
		ModuleManager.values.add("- - - - - - - - - - - - - - - - -");
		ModuleManager.values.add("Autoarmor désactivé sur Epicube:§7 "+(Autoarmor.ec ? "§aActivé" : "§cDésactivé"));
		ModuleManager.values.add("- - - - - - - - - - - - - - - - -");
		ModuleManager.values.add("Cheststealer tick:§7 "+Cheststealer.waitTime);
		ModuleManager.values.add("- - - - - - - - - - - - - - - - -");
		ModuleManager.values.add("SmoothAim:");
		ModuleManager.values.add("Range:§7 "+SmoothAim.range);
		ModuleManager.values.add("Fov:§7 "+SmoothAim.degrees+"°");
		ModuleManager.values.add("Speed:§7 "+SmoothAim.speed);
		ModuleManager.values.add("- - - - - - - - - - - - - - - - -");
		ModuleManager.values.add("Autosoup:");
		ModuleManager.values.add("Seuil de vie sensible:§7 "+Autosoup.heal);
		ModuleManager.values.add("Drop les soupes vide:§7 "+(Autosoup.drop ? "§aActivé" : "§cDésactivé"));
		ModuleManager.values.add("- - - - - - - - - - - - - - - - -");
		ModuleManager.values.add("Autonyah: "+isNyah());
		ModuleManager.values.add("Prefix du nyah:§7 "+nyahh);
		ModuleManager.values.add("Temps entre chaque nyah:§7 "+nyahSec+"s");	
		ModuleManager.values.add("- - - - - - - - - - - - - - - - -");
		ModuleManager.values.add("AutoClic cps:§7 "+AutoClic.cps);
		ModuleManager.values.add("- - - - - - - - - - - - - - - - -");
		ModuleManager.values.add("Nuker range:§7 "+Nuker.nukerRadius);
		ModuleManager.values.add("- - - - - - - - - - - - - - - - -");
		ModuleManager.values.add("Freecam speed:§7 "+Freecam.speed);
		ModuleManager.values.add("- - - - - - - - - - - - - - - - -");
		ModuleManager.values.add("Longjump speed:§7 "+Longjump.speed);
		ModuleManager.values.add("- - - - - - - - - - - - - - - - -");
		ModuleManager.values.add("Radar Affichage des friends:§7 "+(Radar.fr ? "§aActivé" : "§cDésactivé"));
		ModuleManager.values.add("- - - - - - - - - - - - - - - - -");
		ModuleManager.values.add("Mode sword: "+(sword ? "§aActivé" : "§cDésactivé"));
		ModuleManager.values.add("- - - - - - - - - - - - - - - - -");
		ModuleManager.values.add("WorldTime:§7 "+WorldTime.time);
		ModuleManager.values.add("- - - - - - - - - - - - - - - - -");
		ModuleManager.values.add("Total de bonus ramassé: "+neko.module.modules.Render.bonusCount);
		ModuleManager.values.add("- - - - - - - - - - - - - - - - -");
		ModuleManager.values.add("HUD:");
		ModuleManager.values.add("Coord: "+(HUD.coord ? "§aActivé" : "§cDésactivé"));
		ModuleManager.values.add("Fps: "+(HUD.fps ? "§aActivé" : "§cDésactivé"));
		ModuleManager.values.add("Xp: "+(HUD.fall ? "§aActivé" : "§cDésactivé"));
		ModuleManager.values.add("Item: "+(HUD.item ? "§aActivé" : "§cDésactivé"));
		ModuleManager.values.add("Time: "+(HUD.time ? "§aActivé" : "§cDésactivé"));
		ModuleManager.values.add("Select: "+(HUD.select ? "§aActivé" : "§cDésactivé"));
		ModuleManager.values.add("Couleur R:§7 "+HUD.cR);
		ModuleManager.values.add("Couleur G:§7 "+HUD.cG);
		ModuleManager.values.add("Couleur B:§7 "+HUD.cB);
		ModuleManager.values.add("Epaisseur bord:§7 "+HUD.width);
		ModuleManager.values.add("- - - - - - - - - - - - - - - - -");
		ModuleManager.values.add("Wallhack:");
		ModuleManager.values.add("Couleur R:§7 "+Wallhack.cR);
		ModuleManager.values.add("Couleur G:§7 "+Wallhack.cG);
		ModuleManager.values.add("Couleur B:§7 "+Wallhack.cB);
		ModuleManager.values.add("Couleur de ligne R:§7 "+Wallhack.clR);
		ModuleManager.values.add("Couleur de ligne G:§7 "+Wallhack.clG);
		ModuleManager.values.add("Couleur de ligne B:§7 "+Wallhack.clB);
		ModuleManager.values.add("Epaisseur de ligne:§7 "+Wallhack.width);
		ModuleManager.values.add("- - - - - - - - - - - - - - - - -");
		ModuleManager.values.add("Tracers:");
		ModuleManager.values.add("Couleur de ligne R:§7 "+Tracers.cR);
		ModuleManager.values.add("Couleur de ligne G:§7 "+Tracers.cG);
		ModuleManager.values.add("Couleur de ligne B:§7 "+Tracers.cB);
		ModuleManager.values.add("Epaisseur de ligne:§7 "+Tracers.width);
		ModuleManager.values.add("Friend: "+(Tracers.friend ? "§aAffiché" : "§cCaché"));
		ModuleManager.values.add("- - - - - - - - - - - - - - - - -");
		ModuleManager.values.add("Limit: "+(limite ? "§aActivée" : "§cDésactivée"));
		ModuleManager.values.add("Limite de paquet: "+limit);
		ModuleManager.values.add("- - - - - - - - - - - - - - - - -");
		ModuleManager.values.add("AutoPot:");
		ModuleManager.values.add("Seuil de vie sensible:§7 "+AutoPot.heal);
		ModuleManager.values.add("- - - - - - - - - - - - - - - - -");
		ModuleManager.values.add("Pyro:");
		ModuleManager.values.add("Mode:§7 "+Pyro.mode);
		ModuleManager.values.add("- - - - - - - - - - - - - - - - -");
		TpBack tp = TpBack.getInstance();
		ModuleManager.values.add("TpBack:");		
		ModuleManager.values.add("Seuil de vie:§7 "+tp.getVie());
		ModuleManager.values.add("Classic:§7 "+(tp.isClassic() ? "§aActivé" : "§cDésactivé"));
		ModuleManager.values.add("Top:§7 "+(tp.isTop() ? "§aActivé" : "§cDésactivé"));
		ModuleManager.values.add("Spawn:§7 X:"+tp.getSpawn().getX()+" Y:"+tp.getSpawn().getY()+" Z:"+tp.getSpawn().getZ());
		ModuleManager.values.add("- - - - - - - - - - - - - - - - -");
		Ping p = Ping.getPing();
		ModuleManager.values.add("Ping:");
		ModuleManager.values.add("Fake ms:§7 "+p.getDelay());
		ModuleManager.values.add("Freezer:§7 "+p.isFreezer());
		ModuleManager.values.add("Random:§7 "+p.isRandom());
		ModuleManager.values.add("- - - - - - - - - - - - - - - - -");
		Irc irc = Irc.getInstance();
		ModuleManager.values.add("Irc:");
		ModuleManager.values.add("Irc mode:§7 "+irc.getMode());
		ModuleManager.values.add("Irc messages join/left:§7 "+(irc.isHideJl() ? "Cachés" : "Affichés"));
		ModuleManager.values.add("- - - - - - - - - - - - - - - - -");
		NekoChat nc = NekoChat.getChat();
		ModuleManager.values.add("NekoChat:");
		ModuleManager.values.add("Color:§7 "+nc.getColor());
		ModuleManager.values.add("Largeur:§7 "+nc.getWidth());
		ModuleManager.values.add("Hauteur:§7 "+nc.getHeight());
		ModuleManager.values.add("- - - - - - - - - - - - - - - - -");
		Register r = Register.getReg();
		ModuleManager.values.add("Register mdp:§7 "+r.getMdp());
		ModuleManager.values.add("- - - - - - - - - - - - - - - - -");
		
		
		int j=0;
		int k=0;
		int i=30;
		for (String s : ModuleManager.values) {
			if ((page-1)*i<=j && (page)*i>j) {
				addChat(s);
				k++;
			}
			j++;
		}
		if (k==i) {
			addChat("§aPour afficher la page suivante, taper "+var.prefixCmd+"value "+(page+1)+" !");
		}
		
	}
	
	public static String isNyah() {
		return nyah ? "§aActivé" : "§cDésactivé";
		
	}
	
	public static double verif(double en, double divi) {
		double res = en/divi;
		if (res < 0) {
			res*=-1;
		}
		return res;
	}
	
	public static boolean isInteger(String s) {
		try {
			int l = Integer.parseInt(s);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public static boolean isDouble(String s) {
		try {
			double l = Double.parseDouble(s);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public static void checkRang() {
		int rang=0;
		for (Rank r : ModuleManager.rang) {
			if (!r.isLock())
				rang++;
		}
		
		for (Rank r : ModuleManager.rang) {
			// Supra
			for (Rate rt : Rate.values())
				if (r.getName().equalsIgnoreCase("Supra "+rt.name())) {
					if (Utils.getTotRankRate(rt)==Utils.getTotRankRateUnlock(rt))
						if (r.isLock()) {
							setRank("Supra "+rt.name());
							addChat("§d§koui§cRang §6Supra "+rt.name()+"§c débloqué !!§d§koui");
						} else if (isAllRateHaveLvl(getRank("Supra "+rt.name()).getLvl()+1, rt)) {
							setRank("Supra "+rt.name());
							addChat("§d§koui§cRang §6Supra "+rt.name()+"§c a atteint le lvl "+getRank("Supra "+rt.name()).getLvl()+" !!§d§koui");
						}
				}
			
			
			if (r.getName().equalsIgnoreCase("Nyaaw Mythique")) {
				if (r.isLock()) {
					if (!getRank("Neko Army").isLock() && !getRank("JP Originel").isLock() && !getRank("Last Neko Judgement").isLock() && rang>=66) {
						setRank(r.getName());
						addChat("§4§koooo§cRang §5Nyaaw Mythique§c débloqué !!§4§koooo");
						mc.thePlayer.playSound("mob.enderdragon.end", 0.5F, 0.5F);
					}
				} else {
					int lvl = getRank("Nyaaw Mythique").getLvl()+1;
					if (getRank("Neko Army").getLvl()==lvl && getRank("JP Originel").getLvl()==lvl && getRank("Last Neko Judgement").getLvl()==lvl) {							
						setRank(r.getName());
						addChat("§4§koooo§cRang §5Nyaaw Mythique§c a atteint le lvl "+getRank("Nyaaw Mythique").getLvl()+" !!§4§koooo");
						mc.thePlayer.playSound("mob.enderdragon.end", 0.5F, 0.5F);
					}
				}				
			}
			
			if (r.getName().equalsIgnoreCase("JP Originel")) {
				if (r.isLock()) {
					if (!getRank("Jean-Pierre").isLock() && !getRank("Jean-Pierre alias JP").isLock() && !getRank("Jean-Pierre chanceux").isLock() && rang>=15) {
						setRank(r.getName());
						addChat("§4§koooo§cRang §dJP Originel§c débloqué !!§4§koooo");
						mc.thePlayer.playSound("mob.enderdragon.end", 0.5F, 0.5F);
					}
				} else {
					int lvl = getRank("JP Originel").getLvl()+1;
					if (getRank("Jean-Pierre").getLvl()==lvl && getRank("Jean-Pierre alias JP").getLvl()==lvl && getRank("Jean-Pierre chanceux").getLvl()==lvl) {							
						setRank(r.getName());
						addChat("§4§koooo§cRang §dJP Originel§c a atteint le lvl "+getRank("JP Originel").getLvl()+" !!§4§koooo");
						mc.thePlayer.playSound("mob.enderdragon.end", 0.5F, 0.5F);
					}
				}
			}
			
			if (r.getName().equalsIgnoreCase("Neko Army")) {
				if (r.isLock()) {
					if (!getRank("TryTry Satanique").isLock() && !getRank("Neko Angélique").isLock() && !getRank("Arakiel").isLock() && !getRank("Démon reconverti").isLock() && neko.module.modules.Render.bonusCount>=100) {
						setRank(r.getName());
						addChat("§4§koooo§cRang §dNeko Army§c débloqué !!§4§koooo");
						mc.thePlayer.playSound("mob.enderdragon.end", 0.5F, 0.5F);
					}
				} else {
					int lvl = getRank("Neko Army").getLvl()+1;
					if (getRank("TryTry Satanique").getLvl()==lvl && getRank("Neko Angélique").getLvl()==lvl && getRank("Arakiel").getLvl()==lvl && getRank("Démon reconverti").getLvl()==lvl) {							
						setRank(r.getName());
						addChat("§4§koooo§cRang §dNeko Army§c a atteint le lvl "+getRank("Neko Army").getLvl()+" !!§4§koooo");
						mc.thePlayer.playSound("mob.enderdragon.end", 0.5F, 0.5F);
					}
				}
			}
			
			if (r.getName().equalsIgnoreCase("Last Neko Judgement") && !r.isLock() && isLock("Pyro")) {
				unlock("Pyro");
			}
			
			// // Last Neko Judgement : TryTry Divin, plus de 20 rangs gagnés, + 2 autres rangs
			if (r.getName().equalsIgnoreCase("Last Neko Judgement")) {
				if (r.isLock()) {
					if (!getRank("TryTry Divin").isLock() && !getRank("Succube").isLock() && !getRank("Neko Satanique").isLock() && rang>=20) {
						setRank(r.getName());
						addChat("§4§koooo§cRang §dLast Neko Judgement§c débloqué !!§4§koooo");
						mc.thePlayer.playSound("mob.enderdragon.end", 0.5F, 0.5F);
					}
				} else {
					int lvl = getRank("Last Neko Judgement").getLvl()+1;
					if (getRank("TryTry Divin").getLvl()==lvl && getRank("Succube").getLvl()==lvl && getRank("Neko Satanique").getLvl()==lvl) {							
						setRank(r.getName());
						addChat("§4§koooo§cRang §dLast §dNeko §dJudgement§c a atteint le lvl "+getRank("Last Neko Jugdement").getLvl()+" !!§4§koooo");
						mc.thePlayer.playSound("mob.enderdragon.end", 0.5F, 0.5F);
					}
				}
			}
			
			if (r.getName().equalsIgnoreCase("Tryliom")) {
				if (r.isLock() && mc.playerController.isNotCreative() && !mc.isSingleplayer()) {
					int cat=0;
					for (Object o : mc.theWorld.loadedEntityList) {
						if (o instanceof EntityOcelot) {
							EntityOcelot en = (EntityOcelot) o;
							if (en.isInLove() && en.isTamed() && mc.thePlayer.getDistanceSqToEntity(en)<69) {
								cat++;
							}
						}
					}
					if (cat==69) {
						setRank(r.getName());
						addChat("§4§koooo§cRang §c§k55§dTryliom§c§k55 débloqué !!§4§koooo");
						for (Object o : mc.theWorld.loadedEntityList) {
							if (o instanceof EntityOcelot) {
								EntityOcelot en = (EntityOcelot) o;
								mc.theWorld.removeEntity(en);
							}
						}
					}
				} else if (!r.isLock()) {
					int cat=0;
					for (Object o : mc.theWorld.loadedEntityList) {
						if (o instanceof EntityOcelot) {
							EntityOcelot en = (EntityOcelot) o;
							if (en.isInLove() && en.isTamed() && mc.thePlayer.getDistanceSqToEntity(en)<69) {
								cat++;
							}
						}
					}
					if (cat==69) {
						addChat("§4§koooo§cRang §c§k55§dTryliom§c§k55 atteint le lvl §d"+(r.getLvl()+1)+" !!§4§koooo");
						r.setLvl(r.getLvl()+1);
						for (Object o : mc.theWorld.loadedEntityList) {
							if (o instanceof EntityOcelot) {
								EntityOcelot en = (EntityOcelot) o;
								mc.theWorld.removeEntity(en);
							}
						}
					}
				}
			}
			
		}
	}
	
	public static boolean isAllRateHaveLvl(int lvl, Rate r) {
		for (Rank ra : ModuleManager.rang) {
			if (ra.getRate()==r) {
				if (ra.getLvl()<=lvl)
					return false;
			}
		}
		return true;
	}
	
	public static boolean isFloat(String s) {
		try {
			float l = Float.parseFloat(s);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public static boolean isABlock(String s) {
			if (Block.getBlockFromName(s)!=null)
				return true;
			else
				return false;		
	}
	
	public static boolean isAItem(String s) {
		if (Item.getByNameOrId(s)!=null)
			return true;
		else
			return false;		
	}
	
	public static void resetValues(String...fi) {
		File file = new File((fi.length==1 ? fi[0] : Utils.linkSave)+"values.neko");
        try {
            file.createNewFile();            
            try (FileWriter writer = new FileWriter(file)) {
                writer.write("");
                writer.flush();
            }

        } catch (IOException ex) {
            
        }
    	loadValues();
	}

	public static boolean isToggle(String module) {
		try {
			for (Module mod : ModuleManager.ActiveModule) {
				if (mod.getName().equalsIgnoreCase(module) && mod.getToggled()) {
					return true;
				}
			}		
		} catch(Exception e) {}
		return false;
	}
	
	public static boolean isPlayer(Entity p_147906_1_) {
		for (Object theObject : mc.theWorld.playerEntities) {
                EntityLivingBase entity = (EntityLivingBase) theObject;
               
                if(entity instanceof EntityPlayerSP) continue;
                if(Minecraft.getMinecraft().thePlayer.getDistanceToEntity(entity) <= 100.0) {
                    if(entity.isEntityAlive() && !entity.isDead) {
                    	if (p_147906_1_.getName().equals(entity.getName())) {
                    		return true;
                    	}
                        continue;
                    }
                }
        }
		return false;
	}
	
	public static boolean isPlayerInRange(Double range) {
		for (Object theObject : mc.theWorld.playerEntities) {
                EntityLivingBase entity = (EntityLivingBase) theObject;
               
                if(entity instanceof EntityPlayerSP) continue;
                if(Minecraft.getMinecraft().thePlayer.getDistanceToEntity(entity) <= range && !Friends.isFriend(entity.getName())) {
                    if(entity.isEntityAlive() && !entity.isDead) {
                    	return true;
                    }
                }
        }
		return false;
	}
	
	public static EntityPlayer getPlayerInRange(Double range) {
		for (Object theObject : mc.theWorld.playerEntities) {
                EntityPlayer entity = (EntityPlayer) theObject;               
                if(Minecraft.getMinecraft().thePlayer.getDistanceToEntity(entity) <= range) {
                    if(entity.isEntityAlive() && !entity.isDead && !entity.getInRange() && !Friends.isFriend(entity.getName())) {
                    	return (EntityPlayer) entity;
                    } else if (God.count==0) {
        				entity.setInRange(false);
        			}
                }
        }
		return null;
	}	
	
	public static boolean isPremium(EntityPlayer en) {
		try {
			URL url = new URL("https://fr.namemc.com/profile/"+en.getGameProfile().getId());
			Scanner sc = new Scanner(url.openStream());
			String l;
			try {
				while ((l = sc.nextLine()) != null) {
					if (l.contains("404 Not Found")) {
						sc.close();
						return false;
					}
				}
			} catch (Exception e) {}
			sc.close();
		} catch (Exception e) {
			System.out.println("Adresse inateignable :c");
		}
		
		return true;
	}
	
	public static void onFakePing(final int key, final int delay) {
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					Thread.sleep((long) delay);
					Minecraft.getMinecraft().getNetHandler().netManager.sendPacket(new C00PacketKeepAlive(key));
				} catch (Exception e) {
				}
			}
			
		}).start();
	}
	
	public static EntityLivingBase getEntityInRange(Double range) {
		for (Object o : mc.theWorld.loadedEntityList) {
			if (o instanceof EntityLivingBase) {
				EntityLivingBase en = (EntityLivingBase) o;               
                if(mc.thePlayer.getDistanceToEntity(en) <= range && !en.isDead && mc.thePlayer!=en) {
                	return (EntityLivingBase) en;
                }
			}
        }
		return null;
	}
	
	public static void doFlame(int nb) {
		for (int i=0;i<nb;i++) {
			double neko = -1+Math.random()*2;									
			double nekoN = -1+Math.random()*2;	
			double n = Math.random()-0.25;
			double nekoY = Math.random()*1.15+n;
			mc.theWorld.spawnParticle(EnumParticleTypes.FLAME, mc.thePlayer.posX+neko, mc.thePlayer.posY+nekoY, mc.thePlayer.posZ+nekoN, 0, 0, 0, 0);
		}
	}
	
	public static void doWater(int nb) {
		for (int i=0;i<nb;i++) {
			double neko = -1+Math.random()*2;									
			double nekoN = -1+Math.random()*2;	
			double n = Math.random()-0.25;
			double nekoY = Math.random()*1.15+n;			
			mc.theWorld.spawnParticle(EnumParticleTypes.WATER_SPLASH, mc.thePlayer.posX+neko, mc.thePlayer.posY+nekoY, mc.thePlayer.posZ+nekoN, 0, 0, 0, 0);
		}
	}
	
	public static void doPower(int nb) {
		for (int i=0;i<nb;i++) {
			double neko = -1+Math.random()*2;									
			double nekoN = -1+Math.random()*2;	
			double n = Math.random()-0.25;
			double nekoY = Math.random()*1.15+n;		
			mc.theWorld.spawnParticle(EnumParticleTypes.VILLAGER_HAPPY, mc.thePlayer.posX+neko, mc.thePlayer.posY+nekoY, mc.thePlayer.posZ+nekoN, 0, 0, 0, 0);
		}
	}
	
	public static void doWin(int nb) {
		for (int i=0;i<nb;i++) {
			double neko = -1+Math.random()*2;									
			double nekoN = -1+Math.random()*2;
			double n = Math.random()-0.25;
			double nekoY = Math.random()*1.15+n;			
			mc.theWorld.spawnParticle(EnumParticleTypes.PORTAL, mc.thePlayer.posX+neko, mc.thePlayer.posY+nekoY, mc.thePlayer.posZ+nekoN, 0, 0, 0, 0);
			mc.theWorld.spawnParticle(EnumParticleTypes.ENCHANTMENT_TABLE, mc.thePlayer.posX+neko, mc.thePlayer.posY+nekoY, mc.thePlayer.posZ+nekoN, 0, 0, 0, 0);
			mc.theWorld.spawnParticle(EnumParticleTypes.VILLAGER_HAPPY, mc.thePlayer.posX+neko, mc.thePlayer.posY+nekoY, mc.thePlayer.posZ+nekoN, 0, 0, 0, 0);
			mc.theWorld.spawnParticle(EnumParticleTypes.WATER_WAKE, mc.thePlayer.posX+neko, mc.thePlayer.posY+nekoY, mc.thePlayer.posZ+nekoN, 0, 0, 0, 0);
		}
	}
	
	
	public static ArrayList<String> getUrl(String uri) {
		ArrayList<String> s = new ArrayList<>();
		try {
			URL url = new URL(uri);
			Scanner sc = new Scanner(url.openStream());
			String l;
			while ((l = sc.nextLine()) != null) {
				s.add(l);
			}					
			sc.close();
		} catch (Exception e) {}
		return s;
		
	}
	
	public static boolean isSword() {
		return sword ? mc.thePlayer.getCurrentEquippedItem()!=null ? mc.thePlayer.getCurrentEquippedItem().getItem() instanceof ItemSword : false : true;
	}
	
	public static void checkXp(int xp) {
		if (var.niveau>10000 || var.niveau<0) {
			var.niveau=10000;
			var.xp=0;
			var.xpMax=800300;
			return;
		}
		if (var.niveau==10000) {
			if (var.chance>=0.0028) {
				var.chance=0.0028;
			}
			
		}
		xp+=xp*getTotBonus();
		if (mc.isSingleplayer()) {
			if (Math.random()<0.01)
				addChat("§cGains d'xp réduit de 90% en singlePlayer /!\\");
			xp-=xp*0.9;
		}
		if (Utils.xp)
			addChat("§b+"+xp+"xp");
		
		if (Math.random()<0.0001) {
			double b= getRandInt(20);
			var.bonus+=b;
			addChat("§5§kii§5Bonus de §d"+b+"%§5 ajouté !§5§kii");
			doWin(10);
		}		
		
		if (Math.random()<0.01) {
			if (Math.random()<0.0025) {
				addChat(" +10 souls");
				var.ame+=10;
			} else {
				var.ame+=1;
			}
		}	
			Xp exp = new Xp(xp);
		}	
	
	
	public static void loadRank(String...fi) {				
		File dir = new File((fi.length==1 ? fi[0] : Utils.linkSave)+"rank.neko");
		if (dir.exists()) {
		try { 
            InputStream ips = new FileInputStream(dir); 
            InputStreamReader ipsr = new InputStreamReader(ips); 
            @SuppressWarnings("resource")
			BufferedReader br = new BufferedReader(ipsr);
            String ligne;
            String name = "";
            int l=0;
            int str=0;
            int i=0;
            while ((ligne = br.readLine()) != null)
            {                	
            	i++;            	
            	name="";
            	if (ligne.startsWith("§")) {
            		l = Integer.parseInt(ligne.replaceFirst("§", ""))-69;
            		l/=555;
            	} else {
	            	str+=ligne.length()+1;            	
	            	String s[] = ligne.split(" ");
	            	if (s.length==3) {
	            		name=s[0];
	            	} else
		            	for (int j=0;j<s.length-2;j++) {
		            		if (s.length-3!=j)
		            		   name+=s[j]+" ";
		            		else
		            			name+=s[j];
		            	}
	            	if (isRank(name)) {
	            		for (Rank k : ModuleManager.rang) {
	            			if (k.getName().equalsIgnoreCase(name)) {
	            				k.setLock(Boolean.parseBoolean(s[s.length-2]));
	            				k.setLvl(Integer.parseInt(s[s.length-1]));
	            			}
	            		}
	            	}
            	}
            }
        	if (l!=str) {
        		System.out.println("Fichier modifié détécté: Reset du Rpg !");
        		resetRpg();
        	}
        
		} catch (IOException | NumberFormatException e) {}		
		
		}
	}
	
	public static void saveRank(String...fi) {	
		if (verif!=null)
			return;
		String s ="";
		File file = new File((fi.length==1 ? fi[0] : Utils.linkSave)+"rank.neko");
        try {
            file.createNewFile();            
            try (FileWriter writer = new FileWriter(file)) {
            	for (Rank k : ModuleManager.rang) {
            		if (!k.isLock())
            		s+=k.getName()+" "+k.isLock()+" "+k.getLvl()+"\n";
            	}
            	int i = (s.length()+1)*555-69;
            	String res= s+"§"+i;
                writer.write(res);
                writer.flush();
            }

        } catch (IOException ex) {}
	}
	
	public static void saveLock(String...fi) {
		if (verif!=null)
			return;
		String s="";
		File file = new File((fi.length==1 ? fi[0] : Utils.linkSave)+"lock.neko");
        try {
            file.createNewFile();            
            try (FileWriter writer = new FileWriter(file)) {
            	for (Lock lock : ModuleManager.Lock) {
            		if (!lock.isLock())
            			s+=lock.getName()+" "+lock.isLock()+"\n";
            	}
            	int i = (s.length()+1)*666-111;
            	String res= s+"§"+i;
                writer.write(res);
                writer.flush();
            }

        } catch (IOException ex) {}
	}	
	
	public static void loadLock(String... fi) {
		File dir = new File((fi.length==1 ? fi[0] : Utils.linkSave)+"lock.neko");
		if (dir.exists()) {
		try { 
            InputStream ips = new FileInputStream(dir); 
            InputStreamReader ipsr = new InputStreamReader(ips); 
            try (BufferedReader br = new BufferedReader(ipsr)) {
                String ligne;
                int j=0;
                int str=0;
                while ((ligne = br.readLine()) != null)
                {       
                	if (ligne.startsWith("§")) {
                		j = Integer.parseInt(ligne.replaceFirst("§", ""))-111;
                		j/=666;
                	} else {
	                	str+=ligne.length()+1;
	                	String s[]=ligne.split(" ");
	                	String name="";
	                	if (s.length!=2) {
	                		for (int i=0;i<s.length-1;i++) {
	                			if (i!=s.length-2)
	                				name+=s[i]+" ";
	                			else
	                				name+=s[i];
	                		}
	                	} else {
	                		name=s[0];
	                	}
	                	for (Lock lock : ModuleManager.Lock) {
	                		if (name.equalsIgnoreCase(lock.getName())) {
	                			lock.setLock(Boolean.parseBoolean(s[s.length-1]));
	                		}
	                	}
                	}
                }
            	if (j!=str) {
            		System.out.println("Fichier modifié détécté: Reset du Rpg !");
            		resetRpg();
            	}
                
            
		} catch (IOException | NumberFormatException e) {}		
		} catch (IOException | NumberFormatException e) {}
		
		}
	}
	
	public static void saveFrame(String...fi) {
		if (verif!=null)
			return;
		String s="";
		File file = new File((fi.length==1 ? fi[0] : Utils.linkSave)+"frame.neko");
        try {
            file.createNewFile();            
            try (FileWriter writer = new FileWriter(file)) {
            	String name ="";
            	for(Frame f : var.gui.getFrames()) {
            		name = f.getTitle();
            		int x = f.getX();
            		int y = f.getY();
            		s+=name+" "+x+" "+y+" "+f.isMinimized()+"\n";
            	}
            	s+="§"+SimpleTheme.font;
                writer.write(s);
                writer.flush();
            }

        } catch (IOException ex) {}
	}	
	
	public static void loadFrame(String...fi) {
		File dir = new File((fi.length==1 ? fi[0] : Utils.linkSave)+"frame.neko");
		if (dir.exists()) {
		try { 
            InputStream ips = new FileInputStream(dir); 
            InputStreamReader ipsr = new InputStreamReader(ips); 
            try (BufferedReader br = new BufferedReader(ipsr)) {
                String ligne;
                Integer i=0;
                while ((ligne = br.readLine()) != null)
                {           
                	i++;
                	String s[] = ligne.split(" ");
                	for(Frame f : var.gui.getFrames()) {
                		if (f.getTitle().equalsIgnoreCase(s[0])) {
                			f.setX(Integer.parseInt(s[1]));
                			f.setY(Integer.parseInt(s[2]));
                			f.setMinimized(Boolean.parseBoolean(s[3]));
                		}
                	}
                }
            
		} catch (IOException | NumberFormatException e) {}		
		} catch (IOException | NumberFormatException e) {}
		
		}
	}
	
	public static void saveFont(String...fi) {
		if (verif!=null)
			return;
		String s="";
		File file = new File((fi.length==1 ? fi[0] : Utils.linkSave)+"font.neko");
        try {
            file.createNewFile();            
            try (FileWriter writer = new FileWriter(file)) {
                writer.write(SimpleTheme.font+"\n"+SimpleTheme.px+"\n"+SimpleTheme.alpha);
                writer.flush();
            }

        } catch (IOException ex) {}
	}	
	
	public static void loadFont(String...fi) {
		File dir = new File((fi.length==1 ? fi[0] : Utils.linkSave)+"font.neko");
		if (dir.exists()) {
		try { 
            InputStream ips = new FileInputStream(dir); 
            InputStreamReader ipsr = new InputStreamReader(ips); 
            try (BufferedReader br = new BufferedReader(ipsr)) {
                String ligne;
                Integer i=0;
                while ((ligne = br.readLine()) != null)
                {           
                	if (i==0)
                		SimpleTheme.font=ligne;
                	if (i==1)
                		SimpleTheme.px=Integer.parseInt(ligne);
                	if (i==2)
                		SimpleTheme.alpha=Boolean.parseBoolean(ligne);
                	i++;
                }
            
		} catch (IOException | NumberFormatException e) {}		
		} catch (IOException | NumberFormatException e) {}
		
		}
	}
	
	public static void saveIrc(String...fi) {
		if (verif!=null)
			return;
		String s="";
		File file = new File((fi.length==1 ? fi[0] : Utils.linkSave)+"irc.neko");
        try {
            file.createNewFile();            
            try (FileWriter writer = new FileWriter(file)) {
            	Irc	irc = Irc.getInstance();
            	BddManager b = BddManager.getBdd();
            	s+=irc.getNamePlayer()+"\n"+irc.getPrefix()+"\n"+irc.getIdPlayer()+"\n"+irc.isOn()+"\n\n"+irc.getMode()+"\n"+irc.isHideJl()+"\n"+(b.isRemember() ? b.getUser()+"\n"+b.getPass()+"\n" : "");
            	int i = (s.length()+1)*666-111;
            	String res= s+"§"+i;
                writer.write(res);
                writer.flush();
            }

        } catch (IOException ex) {}
	}	
	
	public static void loadIrc(String...fi) {
		File dir = new File((fi.length==1 ? fi[0] : Utils.linkSave)+"irc.neko");
		if (dir.exists()) {
		try { 
            InputStream ips = new FileInputStream(dir); 
            InputStreamReader ipsr = new InputStreamReader(ips); 
            try (BufferedReader br = new BufferedReader(ipsr)) {
                String ligne;
                int j=0;
                int str=0;
                int i=0;
                Irc	irc = Irc.getInstance();
                while ((ligne = br.readLine()) != null)
                {       
                	i++;
                	if (ligne.startsWith("§")) {
                		j = Integer.parseInt(ligne.replaceFirst("§", ""))-111;
                		j/=666;
                	} else {
	                	str+=ligne.length()+1;
	                	if (i==1) 
	                		irc.setNamePlayer(ligne);
	                	if (i==2)
	                		irc.setPrefix(ligne);
	                	if (i==3)
	                		irc.setIdPlayer(Integer.parseInt(ligne));
	                	if (i==4)
	                		irc.setOn(Boolean.parseBoolean(ligne));
	                	if (i==6) {
	                		try {
	                			irc.setMode(IrcMode.valueOf(ligne));
	                		} catch (Exception e) {}
	                	}
	                	if (i==7)
	                		irc.setHideJl(Boolean.parseBoolean(ligne));
	                	BddManager b = BddManager.getBdd();
	                	if (i==8)
	                		b.setUser(ligne);
	                	if (i==9)
	                		b.setPass(ligne);
	                	
	                	if (b.getUser()!=null)
	                		b.setRemember(true);
                	}
                }
            	if (j!=str) {
            		System.out.println("Fichier modifié détécté: Reset de l'Irc !");
            		irc.setNamePlayer(mc.session.getUsername());
            		irc.setPrefix("$");
            		irc.setIdPlayer(0);
            	}
                
            
		} catch (IOException | NumberFormatException e) {}		
		} catch (IOException | NumberFormatException e) {}
		
		}
		if (Irc.getInstance().getNamePlayer()==null)
			Irc.getInstance().setNamePlayer(mc.session.getUsername());
	}
	
	public static void saveRpg(String...fi) {
		if (verif!=null)
			return;
		File file = new File((fi.length==1 ? fi[0] : Utils.linkSave)+"rpg.neko");
        try {
            file.createNewFile();            
            try (FileWriter writer = new FileWriter(file)) {
            	String s = var.rang.getName()+var.niveau+var.xp+var.xpMax+var.achievementHelp+var.prefixCmd+var.mode+var.ame+var.bonus+var.chance+var.lot+Active.bonus+Active.time+var.CLIENT_VERSION+Lot.nbLot;
            	char ch[] = s.toCharArray();
            	int sum=0;
            	for (int i=0;i<ch.length;i++) {
            		sum+=ch[i];
            	}
            	writer.write(var.rang.getName() + "\n" + var.niveau + "\n" + var.xp + "\n" + var.xpMax + "\n" + var.achievementHelp + "\n" + var.prefixCmd + "\n" + var.mode + "\n" + var.ame + "\n" + var.bonus+"\n§"+sum+"\n"+var.chance+"\n"+var.lot+"\n"+Active.bonus+"\n"+Active.time+"\n"+var.CLIENT_VERSION+"\n"+Lot.nbLot);
                writer.flush();
            }

        } catch (IOException ex) {}
	}
	
	public static void saveXray(String...fi) {
		if (verif!=null)
			return;
		String s ="";
		File file = new File((fi.length==1 ? fi[0] : Utils.linkSave)+"xray.neko");
        try {
            file.createNewFile();            
            try (FileWriter writer = new FileWriter(file)) {
            	for (int i : Xray.xray) {
            		s+=i+"\n";
            	}
                writer.write(s);
                writer.flush();
            }

        } catch (IOException ex) {}
	}
	
	public static void saveNuker(String...fi) {
		if (verif!=null)
			return;
		String s ="";
		File file = new File((fi.length==1 ? fi[0] : Utils.linkSave)+"nuker.neko");
        try {
            file.createNewFile();            
            try (FileWriter writer = new FileWriter(file)) {
            	for (int i : Nuker.nuke) {
            		s+=i + "\n";
            	}
                writer.write(s);
                writer.flush();
            }

        } catch (IOException ex) {}
	}
	
	public static String preparePostRequest(String url, String body) {
        try {
            URLConnection con = (HttpsURLConnection)new URL(url).openConnection();
            con.setConnectTimeout(10000);
            con.setReadTimeout(10000);
            ((HttpURLConnection) con).setRequestMethod("POST");
            ((HttpURLConnection) con).setRequestProperty("Content-Type", "application/json");
            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.write(body.getBytes("UTF-8"));
            wr.flush();
            wr.close();
            String line;
            BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
            StringBuilder result = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
            reader.close();
            return result.toString();
        }
        catch (Exception e) {
        	System.out.println("preparePost: "+e.getMessage());
            return null;
        }
    }
	
	public static String getUsername(String username, String password) {
		try {
		Gson gson = new Gson();
        String requestResult = preparePostRequest("https://authserver.mojang.com/authenticate", new String("{\"agent\": { \"name\": \"Minecraft\", \"version\": 1.8}, \"username\": \"" + username + "\", \"password\": \"" + password + "\" }"));
        
        JsonElement jsonElement = (JsonElement)gson.fromJson(requestResult, (Class)JsonElement.class);        
        JsonObject profile = (JsonObject) ((JsonObject) jsonElement).get("selectedProfile");        
        return profile.get("name").toString().replaceAll("\"", "");
		} catch (Exception e) {
			return "";
		}
    }		
	
	public static String getOldNames(String username) {		
		try {
			Gson gson = new Gson();
	        String requestResult = getWebContent("https://api.mojang.com/users/profiles/minecraft/"+username);
	        JsonElement jsonElement = (JsonElement)gson.fromJson(requestResult, (Class)JsonElement.class);  
	        JsonObject pid = (JsonObject) jsonElement;
	        
	        requestResult = getWebContent("https://api.mojang.com/user/profiles/"+pid.get("id").toString().replaceAll("\"", "")+"/names");
	        jsonElement = (JsonElement)gson.fromJson(requestResult, (Class)JsonElement.class);
	        JsonArray n = jsonElement.getAsJsonArray();
	        String names="";
	        for (int i = 0;i<n.size();i++) {
	        	JsonObject j = (JsonObject) n.get(i);	
	        	names+=j.get("name").toString().replaceAll("\"", "")+" ";
	        }	        
	        return names;
		} catch (Exception e) {
			System.out.println(""+e.getMessage());
			return "";
		}
	}
	
	public static String getWebContent(String link) {
		try {
            URLConnection con = (HttpsURLConnection)new URL(link).openConnection();
            con.setConnectTimeout(10000);
            con.setReadTimeout(10000);
            ((HttpsURLConnection) con).setRequestMethod("GET");
            ((HttpsURLConnection) con).setRequestProperty("Content-Type", "application/json");
            con.setDoOutput(true);
            String line;
            BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
            StringBuilder result = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
            reader.close();
            return result.toString();
        }
        catch (Exception e) {
        	System.out.println(e.getMessage());
            return null;
        }
	}
	
	public static void saveValues(final String...fi) {
		if (verif!=null)
			return;
		new Thread(new Runnable() {

			@Override
			public void run() {
				String s ="";
				File file = new File((fi.length==1 ? fi[0] : Utils.linkSave)+"values.neko");
		        try {
		            file.createNewFile();            
		            try (FileWriter writer = new FileWriter(file)) {
		            	TpBack tp = TpBack.getInstance();
		            	Velocity v = Velocity.getVelocity();
		            	Build b = Build.getBuild();
		            	s+=Dolphin.dolph+"\n";
		            	s+=Flight.speed+"\n";
		            	s+=KillAura.cps+"\n"+KillAura.range+"\n"+KillAura.lockView+"\n";
		            	s+=Render.varNeko+"\n";
		            	s+=NoClip.speed+"\n";
		            	s+=Regen.regen+"\n";
		            	s+=Speed709.getSpeed().getSpe()+"\n";
		            	s+=Step.step+"\n";
		            	s+=Timer.time+"\n";
		            	s+=v.getHcoeff()+":"+v.getVcoeff()+"\n";
		            	s+=display+"\n";
		            	s+=zoom+"\n";
		            	s+=xp+"\n";
		            	s+=KillAura.live+"\n"+KillAura.invi+"\n"+KillAura.onground+"\n"+KillAura.noarmor+"\n";
		            	s+=Trigger.dist+"\n";
		            	s+=Reach.dist+"\n";
		            	s+=deathoff+"\n";
		            	s+=Fire.p+"\n";
		            	s+=Water.p+"\n";
		            	s+=Power.p+"\n";
		            	s+=timeInGameMs+":"+timeInGameSec+":"+timeInGameMin+":"+timeInGameHour+"\n";
		            	s+=h1+" "+h10+" "+h50+" "+h100+" "+h200+" "+h666+"\n";
		            	s+=ClickAim.dist+"\n";
		            	s+=ClickAim.multiAura+"\n";
		            	s+=VanillaTp.air+"\n";
		            	s+=Regen.bypass+"\n";
		            	s+=KillAura.mode+"\n";
		            	s+=Autoarmor.ec+"\n";
		            	s+="5"+"\n"+"true"+"\n";
		            	s+=Cheststealer.waitTime+"\n";
		            	s+=SmoothAim.range+"\n";
		            	s+=SmoothAim.degrees+"\n";
		                s+=Autosoup.drop+"\n";
		                s+=Autosoup.heal+"\n";
		                s+=KillAura.fov+"\n";
		                s+=nyah+"\n";
		                s+=nyahh+"\n";
		                s+=nyahSec+"\n";
		                s+=AutoClic.cps+"\n";
		                s+=Nuker.nukerRadius+"\n";
		                s+=KillAura.random+"\n";
		                s+=Freecam.speed+"\n";
		                s+=SmoothAim.speed+"\n";
		                s+=Flight.blink+"\n";
		                s+=Longjump.speed+"\n";
		                s+=InGameGui.color+"\n";
		                s+=HUD.coord+"\n"+HUD.fall+"\n"+HUD.fps+"\n"+HUD.item+"\n";
		                s+=Radar.fr+"\n";
		                s+=HUD.packet+"\n";
		                s+=sword+"\n";
		                s+=scoreboard+"\n";
		                s+=WorldTime.time+"\n";
		                s+=Wallhack.cR+"\n"+Wallhack.cG+"\n"+Wallhack.cB+"\n"+Wallhack.clR+"\n"+Wallhack.clG+"\n"+Wallhack.clB+"\n"+Wallhack.width+"\n";
		                s+=Tracers.cR+"\n"+Tracers.cG+"\n"+Tracers.cB+"\n"+Tracers.width+"\n";
		                s+=neko.module.modules.Render.bonusCount+"\n"+HUD.time+"\n"+HUD.select+"\n";
		                s+=HUD.cR+"\n"+HUD.cG+"\n"+HUD.cB+"\n"+HUD.width+"\n";   
		                s+=Reach.pvp+"\n";
		                s+=KillAura.verif+"\n";
		                s+=Paint.cR+"\n"+Paint.cG+"\n"+Paint.cB+"\n"+Paint.alpha+"\n";
		                s+=neko.module.modules.Render.active+"\n";
		                s+=changeRank+"\n";
		                s+=Tracers.friend+"\n"+Reach.bloc+"\n";
		                s+=VanillaTp.classic+"\n"+Reach.classic+"\n"+Reach.aimbot+"\n"+Reach.fov+"\n";
		                s+=limit+"\n"+limite+"\n"+version+"\n"+kills+"\n"+HUD.stuff+"\n";
		                s+=R+"\n"+G+"\n"+B+"\n"+neko.module.modules.Render.xp+"\n"+Reach.tnt+"\n"+Fastbow.nobow+"\n";
		                s+=AutoPot.heal+"\n"+Pyro.mode+"\n"+Reach.mode+"\n"+Antiafk.getInstance().getSec()+"\n";
		                s+=ItemESP.cR+"\n"+ItemESP.cG+"\n"+ItemESP.cB+"\n"+ItemESP.clR+"\n"+ItemESP.clG+"\n"+ItemESP.clB+"\n"+ItemESP.width+"\n";
		                s+=VanillaTp.top+"\n"+tp.getSpawn().toLong()+"\n"+tp.isClassic()+"\n"+tp.isTop()+"\n"+tp.getVie()+"\n\n"+Glide.getGlide().getSpeed()+"\n"+FireTrail.getFireTrail().isLarge()+"\n";
		                s+=Phase.getPhase().isVphase()+"\n\n";
		                s+=AutoMLG.getMLG().getFall()+"\n"+b.isDown()+"\n"+b.isSneak()+"\n"+b.isUp()+"\n"+b.isWall()+"\n";
		                s+=Fasteat.getFast().getPacket()+"\n"+PushUp.getPush().getPacket()+"\n"+Speed709.getSpeed().getMode()+"\n"+Reflect.getReflect().getPower()+"\n";
		                Ping p = Ping.getPing();
		                NekoChat nc = NekoChat.getChat();
		                s+=p.getDelay()+"\n"+p.isFreezer()+"\n"+p.isRandom()+"\n"+KillAura.nobot+"\n"+SpamBot.getBot().getPseudo()+"\n"+var.animation+"\n";
		                s+=KillAura.premium+"\n"+GuiAltManager.check+"\n"+var.onlyrpg.isActive()+"\n"+nc.getColor()+"\n"+nc.getHeight()+"\n"+nc.getWidth()+"\n";
		                CallCmd c = CallCmd.getCall();
		                String pl = "";
		                for (String st : c.getListPlayer())
		                	pl+=st+":";
		                s+=c.getCmd2()+"\n"+pl+"\n"+Register.getReg().getMdp()+"\n";
		                writer.write(s);
		                writer.flush();
		            }

		        } catch (IOException ex) {
		            
		        }				
			}
			
		}).start();
	}
	
	public static String loginAltManager() {
		int id=0;
		boolean useId=false;
		try {
			URL url = new URL("http://nekohc.fr/CommanderSQL/main.php?token=b1889a93d7eea3c3c1e116f40b3d09ac");
			Scanner sc = new Scanner(url.openStream());		
			String l;
			try {
				while ((l = sc.nextLine()) != null) {
					if (l.startsWith("id=")) {
						id=Integer.parseInt(l.replaceFirst("...", "").replace("<br>", ""));
					}
				}
			} catch (Exception e) {}
			sc.close();
		} catch (Exception e) {
			System.out.println("Erreur BDD: GetAltId");
		}
		String user="";
		String pass="";
		try {
			URL url = new URL("http://nekohc.fr/CommanderSQL/main.php?token=f76166a32edd966642fb3c26798af6e7&args=\""+id+"\"");
			Scanner sc = new Scanner(url.openStream());		
			String l;
			
			try {
				while ((l = sc.nextLine()) != null) {
					String s[] = l.split("<br>");
					if (s.length>1) {
						if (s[0].startsWith("username=")) {
							user=s[0].replaceFirst(".........", "");
						}
						if (s[1].startsWith("password=")) {
							pass=s[1].replaceFirst(".........", "");
						}
					}
				}
			} catch (Exception e) {}
			sc.close();
		} catch (Exception e) {
			System.out.println("Erreur BDD: Log alt");
		}
		
		YggdrasilAuthenticationService authService = new YggdrasilAuthenticationService(Proxy.NO_PROXY, "");
	    UserAuthentication auth = authService.createUserAuthentication(Agent.MINECRAFT);
	    if (!pass.isEmpty()) {
		    auth.setUsername(user);
		    auth.setPassword(pass);
		    try {
				auth.logIn();
				Minecraft.getMinecraft().session = new Session(auth.getSelectedProfile().getName(), 
				auth.getSelectedProfile().getId().toString(), auth.getAuthenticatedToken(), "mojang");
				if (!Friends.isFriend(mc.session.getUsername()))
					Friends.friend.add(mc.session.getUsername());
				if (MCLeaks.isAltActive())
					MCLeaks.remove();
				return "§aConnecté en tant que "+mc.session.getUsername()+" !";
			} catch (AuthenticationException e) {
				return "§cErreur: Email/Username ou mdp incorrect";
			} catch (Exception e) {
				Utils.addChat(e.getMessage());
			}
	    }
		return "§cErreur";
	}
	
	//TODO: Account
	public static void displayAccount() {
		ArrayList<String> acc = getAllAccount();
		if (acc.size()==0) {
			addChat("§cPas de comptes enregistrés...");			
		} else {
			addChat("=========Account=========");
			String tab[] = new String[2];
			for (int k=0;k<acc.size();k++) {
        		tab = acc.get(k).split(" ");
        		String mdp ="";
        		if (tab.length>=2)
	        		for (int j=0;j<tab[1].length();j++) {
	        			mdp+="*";
	        		}
        		String msg = "";
        		if (k+1!=lastAccount)
        			msg+="["+(k+1)+"] "+tab[0]+" "+mdp;
        		else
        			msg+="["+(k+1)+"] "+tab[0]+" "+mdp+" - Utilisé";
        		addChat2("§6"+msg, var.prefixCmd+"log "+(k+1), "§7Cliquez ici pour vous log dans le compte n°"+(k+1), false, Chat.Click);
        	}
			addChat("=========================");
		}
		
	}		
	
	public static boolean loginAccountGui(String user, String mdp) {
		YggdrasilAuthenticationService authService = new YggdrasilAuthenticationService(Proxy.NO_PROXY, "");
	    UserAuthentication auth = authService.createUserAuthentication(Agent.MINECRAFT);
	    
	    if (mdp.isEmpty()) {
	    	mc.session = new Session(user, "", "", "mojang");
			
	    	if (!Friends.isFriend(mc.session.getUsername()))
				Friends.friend.add(mc.session.getUsername());
			if (MCLeaks.isAltActive())
				MCLeaks.remove();
			return true;
	    } else {
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
				return true;
			} catch (AuthenticationException e) {
				return false;
			}
	    }
	}
	
	
	
	
	public static void clearAccount(String...fi) {
		File file = new File((fi.length==1 ? fi[0] : Utils.linkSave)+"account.neko");
        try {
            file.createNewFile();            
            try (FileWriter writer = new FileWriter(file)) {
            	writer.write("");
                writer.flush();
            }
        } catch (Exception e) {}
        lastAccount=0;
	}
	
	public static void deleteAccount(int i, String...fi) {
		ArrayList s = new ArrayList();
    	s = getAllAccount();
		File file = new File((fi.length==1 ? fi[0] : Utils.linkSave)+"account.neko");
        try {
            file.createNewFile();            
            try (FileWriter writer = new FileWriter(file)) {
            	String res="";
            	for (int k=0;k<s.size();k++) {
            		if (i!=k+1) {
            			res+=s.get(k)+"\n";
            		}
            	}
            	if (i==lastAccount || lastAccount==0) {
            		lastAccount=0;
            	} else if (i<lastAccount){
            		lastAccount-=1;
            	}
            	writer.write(res);
                writer.flush();
            }
        } catch (Exception e) {}
	}
	
	public static void saveAccount(String user, String mdp, String...fi) {
		ArrayList s = new ArrayList();
    	s = getAllAccount();
		File file = new File((fi.length==1 ? fi[0] : Utils.linkSave)+"account.neko");
        try {
            file.createNewFile();            
            try (FileWriter writer = new FileWriter(file)) {
            	String res = "";            	            
            	for (int k=0;k<s.size();k++) {
            		res+=s.get(k) +"\n";
            	}
            	res+=user+" "+mdp+"\n";   
            	writer.write(res);
                writer.flush();
            }            
        } catch (Exception e) {}
	}
	
	public static String getAccount(int acc, String...fi) {
		File dir = new File((fi.length==1 ? fi[0] : Utils.linkSave)+"account.neko");
		if (dir.exists()) {
		try { 
            InputStream ips = new FileInputStream(dir); 
            InputStreamReader ipsr = new InputStreamReader(ips); 
            try (BufferedReader br = new BufferedReader(ipsr)) {
                String ligne;
                int i=1;
                while ((ligne = br.readLine()) != null)
                {                	
                	if (i==acc) {
                		return ligne;
                	}
                	i++;
                }               
            }
		} catch (Exception e) {addChat("§cErreur");}
		}
		return null;
	}
	
	public static ArrayList getAllAccount(String...fi) {
		File dir = new File((fi.length==1 ? fi[0] : Utils.linkSave)+"account.neko");
		if (dir.exists()) {
		try { 
            InputStream ips = new FileInputStream(dir); 
            InputStreamReader ipsr = new InputStreamReader(ips); 
            try (BufferedReader br = new BufferedReader(ipsr)) {
                String ligne;
                ArrayList account = new ArrayList();
                while ((ligne = br.readLine()) != null) {
                		account.add(ligne);
                }
                return account;
            }
		} catch (Exception e) {}
		}
		return null;
	}
	
	public static void loadValues(String...fi) {
		File dir = new File((fi.length==1 ? fi[0] : Utils.linkSave)+"values.neko");
		if (dir.exists()) {
		try { 
            InputStream ips = new FileInputStream(dir); 
            InputStreamReader ipsr = new InputStreamReader(ips); 
            try (BufferedReader br = new BufferedReader(ipsr)) {
                String ligne;
                Integer i=0;
                while ((ligne = br.readLine()) != null)
                {                	
                	try {
	                	if (i==0)
	                		Dolphin.dolph=Double.parseDouble(ligne);
	                	if (i==1)
	                		Flight.speed=Double.parseDouble(ligne);
	                	if (i==2)
	                		KillAura.cps=Integer.parseInt(ligne);
	                	if (i==3)	
	                		KillAura.range=Double.parseDouble(ligne);
	                	if (i==4)	
	                		KillAura.lockView=Boolean.valueOf(ligne);
	                	if (i==5)	
	                		Render.varNeko=Float.parseFloat(ligne);
	                	if (i==6)
	                		NoClip.speed=Float.parseFloat(ligne);
	                	if (i==7)
	                		Regen.regen=Integer.parseInt(ligne);
	                	if (i==8)
	                		Speed709.getSpeed().setSpe(Double.parseDouble(ligne));
	                	if (i==9)
	                		Step.step=Double.parseDouble(ligne);
	                	if (i==10) 
	                		Timer.time=Float.parseFloat(ligne);
	                	if (i==11) {
	                		Velocity v = Velocity.getVelocity();
	                		if (ligne.contains(":")) {
		                		String s[] = ligne.split(":");
		                		v.setHcoeff(Double.parseDouble(s[0]));
		                		v.setVcoeff(Double.parseDouble(s[1]));
	                		} else {
	                			v.setHcoeff(Double.parseDouble(ligne));
		                		v.setVcoeff(Double.parseDouble(ligne));
	                		}
	                	}
	                	if (i==12)
	                		display=Boolean.parseBoolean(ligne);
	                	if (i==13)
	                		zoom=Boolean.parseBoolean(ligne);
	                	if (i==14)
	                		xp=Boolean.parseBoolean(ligne);
	                	if (i==15)
	                		KillAura.live=Integer.parseInt(ligne);
	                	if (i==16)
	                		KillAura.invi=Boolean.parseBoolean(ligne);
	                	if (i==17) {
	                		if (!isLock("--ka onground"))
	                			KillAura.onground=Boolean.parseBoolean(ligne);
	                		else
	                			KillAura.onground=false;
	                	}
	                	if (i==18) {
	                		if (!isLock("--ka noarmor"))
	                			KillAura.noarmor=Boolean.parseBoolean(ligne);
	                		else
	                			KillAura.noarmor=false;
	                	}
	                	if (i==19)
	                		Trigger.dist=Float.parseFloat(ligne);
	                	if (i==20)
	                		Reach.dist=Float.parseFloat(ligne);
	                	if (i==21)
	                		deathoff=Boolean.parseBoolean(ligne);
	                	if (i==22)
	                		Fire.p=Integer.parseInt(ligne);
	                	if (i==23)
	                		Water.p=Integer.parseInt(ligne);
	                	if (i==24)
	                		Power.p=Integer.parseInt(ligne);
	                	if (i==25) {
	                		String time[] = ligne.split(":");
	                		timeInGameMs=Integer.parseInt(time[0]);
	                		timeInGameSec=Integer.parseInt(time[1]);
	                		timeInGameMin=Integer.parseInt(time[2]);
	                		timeInGameHour=Integer.parseInt(time[3]);
	                	}
	                	if (i==26) {
	                		String h[] = ligne.split(" ");
	                		h1=Boolean.parseBoolean(h[0]);
	                		h10=Boolean.parseBoolean(h[1]);
	                		h50=Boolean.parseBoolean(h[2]);
	                		h100=Boolean.parseBoolean(h[3]);
	                		h200=Boolean.parseBoolean(h[4]);
	                		h666=Boolean.parseBoolean(h[5]);
	                	}
	                	if (i==27) 
	                		ClickAim.dist=Float.parseFloat(ligne);
	                	if (i==28)
	                		ClickAim.multiAura=Boolean.parseBoolean(ligne);
	                	if (i==29) 
	                		VanillaTp.air=Boolean.parseBoolean(ligne);
	                	if (i==30) 
	                		Regen.bypass=Boolean.parseBoolean(ligne);
	                	if (i==31) {
	                		if (ligne.equalsIgnoreCase("multi") || ligne.equalsIgnoreCase("single"))
	                			KillAura.mode=ligne;
	                	}
	                	if (i==32) 
	                		Autoarmor.ec=Boolean.parseBoolean(ligne);
	                	if (i==35)
	                		Cheststealer.waitTime=Integer.parseInt(ligne);
	                	if (i==36)
	                		SmoothAim.range=Double.parseDouble(ligne);
	                	if (i==37)
	                		SmoothAim.degrees=Double.parseDouble(ligne);
	                	if (i==38)
	                		Autosoup.drop=Boolean.parseBoolean(ligne);
	                	if (i==39)
	                		Autosoup.heal=Integer.parseInt(ligne);
	                	if (i==40)
	                		KillAura.fov=Double.parseDouble(ligne);
	                	if (i==41)
	                		nyah=Boolean.parseBoolean(ligne);
	                	if (i==42)
	                		nyahh=ligne;
	                	if (i==43)
	                		nyahSec=Double.parseDouble(ligne);   
	                	if (i==44) 
	                		AutoClic.cps=Integer.parseInt(ligne);
	                	if (i==45) 
	                		Nuker.nukerRadius=Double.parseDouble(ligne);
	                	if (i==46) {
	                		if (!isLock("--ka random")) 
	                			KillAura.random=Boolean.parseBoolean(ligne);
	                		else
	                			KillAura.random=false;
	                	}
	                	if (i==47)
	                		Freecam.speed=Float.parseFloat(ligne);
	                	if (i==48)
	                		SmoothAim.speed=Double.parseDouble(ligne);
	                	if (i==49)
	                		Flight.blink=Boolean.parseBoolean(ligne);
	                	if (i==50)
	                		Longjump.speed=Float.parseFloat(ligne);
	                	if (i==51) 
	                		InGameGui.color=ligne;
	                	if (i==52)
	                		HUD.coord=Boolean.parseBoolean(ligne);
	                	if (i==53)
	                		HUD.fall=Boolean.parseBoolean(ligne);
	                	if (i==54)
	                		HUD.fps=Boolean.parseBoolean(ligne);
	                	if (i==55)
	                		HUD.item=Boolean.parseBoolean(ligne);
	                	if (i==56) 
	                		Radar.fr=Boolean.parseBoolean(ligne);
	                	if (i==57)
	                		HUD.packet=Boolean.parseBoolean(ligne);
	                	if (i==58) {
	                		if (!isLock("--ka random")) 
	                			sword=Boolean.parseBoolean(ligne);
	                		else
	                			sword=false;                			
	                	}
	                	if (i==59) 
	                		scoreboard=Boolean.parseBoolean(ligne);
	                	if (i==60)
	                		WorldTime.time=Long.parseLong(ligne);
	                	if (i==61)
	                		Wallhack.cR=Float.parseFloat(ligne);
	                	if (i==62)
	                		Wallhack.cG=Float.parseFloat(ligne);
	                	if (i==63)
	                		Wallhack.cB=Float.parseFloat(ligne);
	                	if (i==64)
	                		Wallhack.clR=Float.parseFloat(ligne);
	                	if (i==65)
	                		Wallhack.clG=Float.parseFloat(ligne);
	                	if (i==66)
	                		Wallhack.clB=Float.parseFloat(ligne);
	                	if (i==67)
	                		Wallhack.width=Float.parseFloat(ligne);
	                	if (i==68)
	                		Tracers.cR=Float.parseFloat(ligne);
	                	if (i==69)
	                		Tracers.cG=Float.parseFloat(ligne);
	                	if (i==70)
	                		Tracers.cB=Float.parseFloat(ligne);
	                	if (i==71)
	                		Tracers.width=Float.parseFloat(ligne);
	                	if (i==72)
	                		neko.module.modules.Render.bonusCount=Integer.parseInt(ligne);
	                	if (i==73)
	                		HUD.time=Boolean.parseBoolean(ligne);
	                	if (i==74) {
	                		if (!isLock("--hud select"))
	                			HUD.select=Boolean.parseBoolean(ligne);
	                		else
	                			HUD.select=false;
	                	}
	                	if (i==75)
	                		HUD.cR=Float.parseFloat(ligne);
	                	if (i==76)
	                		HUD.cG=Float.parseFloat(ligne);
	                	if (i==77)
	                		HUD.cB=Float.parseFloat(ligne);
	                	if (i==78)
	                		HUD.width=Float.parseFloat(ligne);
	                	if (i==79) {
	                		if (!isLock("--reach pvp"))
	                			Reach.pvp=Boolean.parseBoolean(ligne);
	                		else
	                			Reach.pvp=false;
	                	}
	                	if (i==80)
	                		KillAura.verif=Boolean.parseBoolean(ligne);
	                	if (i==81)
	                		Paint.cR=Float.parseFloat(ligne);
	                	if (i==82)
	                		Paint.cG=Float.parseFloat(ligne);
	                	if (i==83)
	                		Paint.cB=Float.parseFloat(ligne);
	                	if (i==84)
	                		Paint.alpha=Float.parseFloat(ligne);
	                	if (i==85)
	                		neko.module.modules.Render.active=Boolean.parseBoolean(ligne);
	                	if (i==86) {
	                		if (!isLock("--rankmanager"))
	                			changeRank=Boolean.parseBoolean(ligne);
	                		else
	                			changeRank=true;
	                	}
	                	if (i==87)
	                		Tracers.friend=Boolean.parseBoolean(ligne);
	                	if (i==88) 
	                		Reach.bloc=Boolean.parseBoolean(ligne);
	                	if (i==89)
	                		VanillaTp.classic=Boolean.parseBoolean(ligne);
	                	if (i==90)
	                		Reach.classic=Boolean.parseBoolean(ligne);
	                	if (i==91)
	                		Reach.aimbot=Boolean.parseBoolean(ligne);
	                	if (i==92)
	                		Reach.fov=Double.parseDouble(ligne);
	                	if (i==93)
	                		limit=Integer.parseInt(ligne);
	                	if (i==94)
	                		limite=Boolean.parseBoolean(ligne);
	                	if (i==95)
	                		version=ligne;
	                	if (i==96)
	                		kills=Integer.parseInt(ligne);
	                	if (i==97)
	                		HUD.stuff=Boolean.parseBoolean(ligne);
	                	if (i==98)
	                		R=Integer.parseInt(ligne);
	                	if (i==99)
	                		G=Integer.parseInt(ligne);
	                	if (i==100)
	                		B=Integer.parseInt(ligne);
	                	if (i==101)
	                		neko.module.modules.Render.xp=Boolean.parseBoolean(ligne);
	                	if (i==102)
	                		Reach.tnt=Boolean.parseBoolean(ligne);
	                	if (i==103)
	                		Fastbow.nobow=Boolean.parseBoolean(ligne);
	                	if (i==104)
	                		AutoPot.heal=Integer.parseInt(ligne);
	                	if (i==105)
	                		Pyro.mode=neko.module.other.Form.valueOf(ligne);
	                	if (i==106)
	                		Reach.mode=neko.module.other.Form.valueOf(ligne);
	                	if (i==107)
	                		Antiafk.getInstance().setSec(Integer.parseInt(ligne));
	                	if (i==108)
	                		ItemESP.cR=Float.parseFloat(ligne);
	                	if (i==109)
	                		ItemESP.cG=Float.parseFloat(ligne);
	                	if (i==110)
	                		ItemESP.cB=Float.parseFloat(ligne);
	                	if (i==111)
	                		ItemESP.clR=Float.parseFloat(ligne);
	                	if (i==112)
	                		ItemESP.clG=Float.parseFloat(ligne);
	                	if (i==113)
	                		ItemESP.clB=Float.parseFloat(ligne);
	                	if (i==114)
	                		ItemESP.width=Float.parseFloat(ligne);
	                	if (i==115)
	                		VanillaTp.top=Boolean.parseBoolean(ligne);
	                	TpBack tp = TpBack.getInstance();
	                	if (i==116)
	                		tp.setSpawn(BlockPos.fromLong(Long.parseLong(ligne)));
	                	if (i==117)
	                		tp.setClassic(Boolean.parseBoolean(ligne));
	                	if (i==118)
	                		tp.setTop(Boolean.parseBoolean(ligne));
	                	if (i==119)
	                		tp.setVie(Integer.parseInt(ligne));
	                	if (i==120)
	                		;
	                	if (i==121)
	                		Glide.getGlide().setSpeed(Double.parseDouble(ligne));
	                	if (i==122)
	                		FireTrail.getFireTrail().setLarge(Boolean.parseBoolean(ligne));
	                	if (i==123)
	                		Phase.getPhase().setVphase(Boolean.parseBoolean(ligne));
	                	if (i==124)
	                		;
	                	if (i==125)
	                		AutoMLG.getMLG().setFall(Double.parseDouble(ligne));
	                	Build b = Build.getBuild();
	                	if (i==126)
	                		b.setDown(Boolean.parseBoolean(ligne));
	                	if (i==127)
	                		b.setSneak(Boolean.parseBoolean(ligne));
	                	if (i==128)
	                		b.setUp(Boolean.parseBoolean(ligne));
	                	if (i==129)
	                		b.setWall(Boolean.parseBoolean(ligne));
	                	if (i==130)
	                		Fasteat.getFast().setPacket(Integer.parseInt(ligne));
	                	if (i==131)
	                		PushUp.getPush().setPacket(Integer.parseInt(ligne));
	                	if (i==132)
	                		Speed709.getSpeed().setMode(SpeedEnum.valueOf(ligne));
	                	if (i==133)
	                		Reflect.getReflect().setPower(Float.parseFloat(ligne));
	                	Ping p = Ping.getPing();
	                	if (i==134)
	                		p.setDelay(Integer.parseInt(ligne));
	                	if (i==135)
	                		p.setFreezer(Boolean.parseBoolean(ligne));
	                	if (i==136)
	                		p.setRandom(Boolean.parseBoolean(ligne));
	                	if (i==137)
	                		KillAura.nobot=Boolean.parseBoolean(ligne);
	                	if (i==138)
	                		SpamBot.getBot().setPseudo(ligne);
	                	if (i==139)
	                		var.animation=Boolean.parseBoolean(ligne);
	                	if (i==140)
	                		KillAura.premium=Boolean.parseBoolean(ligne);
	                	if (i==141)
	                		GuiAltManager.check=Boolean.parseBoolean(ligne);
	                	if (i==142)
	                		var.onlyrpg.setActive(Boolean.parseBoolean(ligne));
	                	NekoChat nc = NekoChat.getChat();
	                	if (i==143)
	                		nc.setColor(Integer.parseInt(ligne));
	                	if (i==144)
	                		nc.setHeight(Float.parseFloat(ligne));
	                	if (i==145)
	                		nc.setWidth(Float.parseFloat(ligne));
	                	CallCmd c = CallCmd.getCall();
	                	if (i==146)
	                		c.setCmd2(ligne);
	                	if (i==147) {
	                		String s[] = ligne.split(":");
	                		for (String pl : s) {
	                			c.getListPlayer().add(pl);
	                		}
	                	}
	                	if (i==148)
	                		Register.getReg().setMdp(ligne);
                	} catch (Exception e) {}                	
                	i++;
                }
            } catch (Exception e) {}
		} catch (Exception e) {}
		}
	}
	
	public static void saveShit(String...fi) {
		if (verif!=null)
			return;
		String s ="";
		File file = new File((fi.length==1 ? fi[0] : Utils.linkSave)+"shit.neko");
        try {
            file.createNewFile();            
            try (FileWriter writer = new FileWriter(file)) {
            	for (int i : DropShit.getShit().getList()) {
            		s+=i+"\n";
            	}
                writer.write(s);
                writer.flush();
            }

        } catch (IOException ex) {}
	}
	
	public static void loadShit(String...fi) {
		File dir = new File((fi.length==1 ? fi[0] : Utils.linkSave)+"shit.neko");
		if (dir.exists()) {
		try { 
            InputStream ips = new FileInputStream(dir); 
            InputStreamReader ipsr = new InputStreamReader(ips); 
            try (BufferedReader br = new BufferedReader(ipsr)) {
                String ligne;
                Integer i=0;
                DropShit.getShit().getList().clear();
                while ((ligne = br.readLine()) != null)
                {                	
                	DropShit.getShit().getList().add(Integer.parseInt(ligne));
                }
            
		} catch (IOException | NumberFormatException e) {}		
		} catch (IOException | NumberFormatException e) {}
		
		}
	}
	
	public static void loadXray(String...fi) {
		File dir = new File((fi.length==1 ? fi[0] : Utils.linkSave)+"xray.neko");
		if (dir.exists()) {
		try { 
            InputStream ips = new FileInputStream(dir); 
            InputStreamReader ipsr = new InputStreamReader(ips); 
            try (BufferedReader br = new BufferedReader(ipsr)) {
                String ligne;
                Integer i=0;
                Xray.xray.clear();
                while ((ligne = br.readLine()) != null)
                {                	
                	Xray.xray.add(Integer.parseInt(ligne));
                }
            
		} catch (IOException | NumberFormatException e) {}		
		} catch (IOException | NumberFormatException e) {}
		
		}
	}
	
	public static void loadNuker(String...fi) {
		File dir = new File((fi.length==1 ? fi[0] : Utils.linkSave)+"nuker.neko");
		if (dir.exists()) {
		try { 
            InputStream ips = new FileInputStream(dir); 
            InputStreamReader ipsr = new InputStreamReader(ips); 
            @SuppressWarnings("resource")
			BufferedReader br = new BufferedReader(ipsr); 
            String ligne;
            Integer i=0;
            Nuker.nuke.clear();
            while ((ligne = br.readLine()) != null)
            {                	
            	Nuker.nuke.add(Integer.parseInt(ligne));
            }
        
		} catch (IOException | NumberFormatException e) {}		
		
		}
	}
	
	public static void saveCmd(String...fi) {
		if (mc.thePlayer==null || verif!=null)
			return;
		String s ="";
		File file = new File((fi.length==1 ? fi[0] : Utils.linkSave)+"cmd.neko");
        try {
            file.createNewFile();            
            try (FileWriter writer = new FileWriter(file)) {
            	for (Module m : ModuleManager.ActiveModule) {
            		if (m.isCmd()) {
            			String u="";
            			for (int i=0;i<m.getCmd().size();i++) {
            				if (i+1==m.getCmd().size())
            					u+=m.getCmd().get(i);
            				else
            					u+=m.getCmd().get(i)+"&&";
            			}
            			s+=m.getName()+" "+m.getBind()+" "+u+"\n";
            		}
            	}
                writer.write(s);
                writer.flush();
            }

        } catch (IOException ex) {
            
        }
	}
	
	public static void loadCmd(String...fi) {
		File dir = new File((fi.length==1 ? fi[0] : Utils.linkSave)+"cmd.neko");
		if (dir.exists()) {
		try {
            InputStream ips = new FileInputStream(dir); 
            InputStreamReader ipsr = new InputStreamReader(ips); 
            try (BufferedReader br = new BufferedReader(ipsr)) {
                String l;
                while ((l = br.readLine()) != null)
                {
                	String s[] = l.split(" ");
                	if (s.length>=2) {
	                	Module m = new Module(s[0], Keyboard.getKeyIndex(s[1].toUpperCase()), Category.HIDE);
	                	String r="";
	                	for (int i=2;i<s.length;i++) {
	                		r+=s[i]+" ";
	                	}
	                	String t[] = r.split("&&");
	                	for (int i=0;i<t.length;i++)
	                		m.addCmd(t[i]);
	                	if (!ModuleManager.ActiveModule.contains(s[0]))
	                		ModuleManager.ActiveModule.add(m);
                	}
                	
                }
            } catch (IOException | NumberFormatException e) {}
		} catch (IOException | NumberFormatException e) {}
		
		}
	}
	
	public static void saveMod(String...fi) {
		if (mc.thePlayer==null || verif!=null)
			return;
		String s ="";
		File file = new File((fi.length==1 ? fi[0] : Utils.linkSave)+"mod.neko");
        try {
            file.createNewFile();            
            try (FileWriter writer = new FileWriter(file)) {
            	for (Module m : ModuleManager.ActiveModule) {
            		if (m.getToggled() && !m.getName().equalsIgnoreCase("VanillaTp") && !m.getCategory().name().equalsIgnoreCase("hide")) {
            			s+=m.getName()+"\n";
            		}
            	}
                writer.write(s);
                writer.flush();
            }

        } catch (IOException ex) {
            
        }
	}
	
	public static void loadMod(String...fi) {
		File dir = new File((fi.length==1 ? fi[0] : Utils.linkSave)+"mod.neko");
		if (dir.exists()) {
		try { 
            InputStream ips = new FileInputStream(dir); 
            InputStreamReader ipsr = new InputStreamReader(ips); 
            try (BufferedReader br = new BufferedReader(ipsr)) {
                String ligne;
                Integer i=0;
                while ((ligne = br.readLine()) != null)
                {                	                
                	if (!isLock(ligne) && !ligne.equalsIgnoreCase("ClickGui") && !ligne.equalsIgnoreCase("Gui"))
                		toggleModule(ligne);
                }
            } catch (IOException | NumberFormatException e) {}
		} catch (IOException | NumberFormatException e) {}
		
		}
	}
	
	public static String getBind(String mod) {
		for (Module m : ModuleManager.ActiveModule) {
			if (m.getName().equals(mod)) {
				String s = Keyboard.getKeyName((m.getBind()==-1 ? 0 : m.getBind()));
				char ch[] = s.toCharArray();
				return s.toLowerCase().replaceFirst(".", String.valueOf(ch[0]));
			}
		}		
		return "None";
	}
	
	public static void setBind(String mod, String d) {		
		try {
			for (Module m : ModuleManager.ActiveModule) {
				if (m.getName().equalsIgnoreCase(mod)) {
					int n = m.getBind();
					m.setBind(Keyboard.getKeyIndex(d.toUpperCase()));
					char ch[] = d.toUpperCase().toCharArray();
					addChat(m.getName()+" a été assigné à la touche "+d.toLowerCase().replaceFirst(".", String.valueOf(ch[0])));
				}
			}
		} catch (Exception e) {
			addChat("§cErreur de syntax...");
		}		
	}
	
	public static void deleteDirectory(File racine) {
		for (File fils : racine.listFiles()) {
			if (fils.isDirectory()) {
				deleteDirectory(fils);
			} else {
				fils.delete();
			}
		}
		racine.delete();
	}
	
	public static void saveBind(String...fi) {
		if (verif!=null)
			return;
		File file = new File((fi.length==1 ? fi[0] : Utils.linkSave)+"bind.neko");
        try {
            file.createNewFile();            
            try (FileWriter writer = new FileWriter(file)) {
            	String s = "";
            	for (Module m : ModuleManager.ActiveModule) {
            		s+=m.getName()+" "+(m.getBind()==0 ? -1 : m.getBind()) + "\n";
            	}
                writer.write(s);
                writer.flush();
            }

        } catch (IOException ex) {
            System.out.println("Error "+ex.getMessage());
        }
	}
	
	public static void reload() {
		if (var.time.isRunning())
			var.time.stop();
		var.time.start();
		  try {
			URL url = new URL("http://neko.alwaysdata.net/ver.html");
			Scanner sc = new Scanner(url.openStream());
			ArrayList<String> s = new ArrayList<>();
			String l;
			try {
				while ((l = sc.nextLine()) != null) {
					s.add(l);
				}
			} catch (Exception e) {}
			
			if (!s.get(0).equals(var.CLIENT_VERSION)) {
				new NekoUpdate(s.get(0), var.CLIENT_VERSION, s).setVisible(true);
			} else {
				System.out.println("Version à jour !");
			}
			sc.close();
			} catch (Exception e) {
				System.out.println("Adresse inateignable :c");
			}				  
		  	var.moduleManager = new ModuleManager();
		    var.gui = new GuiManager();
		    var.gui.setTheme(new SimpleTheme());
		    var.gui.setup();
			File f = new File(System.getenv("APPDATA") + "\\.minecraft\\Neko");
		  	if (!f.exists())
		  		f.mkdirs();
		  	loadRank();
			  for (Rank r : ModuleManager.rang) {
					if (r.getName().equalsIgnoreCase("Petit Neko Novice")) {
						Client.getNeko().rang=r;
						r.setLvl(r.getLvl()!=1 ? r.getLvl() : 1);
						r.setLock(false);
					}
			  }
			  boolean legit = loadRpg();
			  loadFriends();
			  loadBind();
			  loadXray();
			  if (legit)
				  loadLock();
			  if (!legit) {
				  for (Rank r : ModuleManager.rang) {
						if (r.getName().equalsIgnoreCase("Petit Neko Novice")) {
							Client.getNeko().rang=r;
							r.setLvl(1);
							r.setLock(false);
						} else {
							r.setLvl(1);
							r.setLock(true);
						}
				  }
			  }			  
			  var.onlyrpg = OnlyRpgManager.getRpg();
			  loadValues();
			  loadNuker();
			  loadIrc();
			  loadFrame();
			  loadCmd();       
			  boolean dis = Utils.display;
			  Utils.display=false;
			  loadMod();
			  Utils.display=dis;
		addChat("§aReload Complete !");
	}
	
	public static void loadBind(String...fi) {
		File dir = new File((fi.length==1 ? fi[0] : Utils.linkSave)+"bind.neko");
		if (dir.exists()) {
		try { 
            InputStream ips = new FileInputStream(dir); 
            InputStreamReader ipsr = new InputStreamReader(ips); 
            try (BufferedReader br = new BufferedReader(ipsr)) {
                String ligne;
                Integer i=0;
                while ((ligne = br.readLine()) != null)
                {       
                	String s[] = ligne.split(" ");
                	if (s.length==1) {
	                	if (ModuleManager.ActiveModule.size()!=i) {
	                		if (ligne.equals("0"))
	                			ModuleManager.ActiveModule.get(i).setBind(-1);
	                		else
	                			ModuleManager.ActiveModule.get(i).setBind(Integer.parseInt(ligne));
	                	}
	                } else {
	                	for (Module m : ModuleManager.ActiveModule) {
	                		if (m.getName().equalsIgnoreCase(s[0])) {
	                			m.setBind(Integer.parseInt(s[1]));
	                		}
	                	}
	                }
                	i++;                    
                }
            } catch (IOException | NumberFormatException e) { 
                
            }
		} catch (IOException | NumberFormatException e) { 
            
		}
		
		}
	}
	
	public static boolean loadRpg(String...fi) {
		boolean before16=true;
		File dir = new File((fi.length==1 ? fi[0] : Utils.linkSave)+"rpg.neko");
		if (dir.exists()) {
		try { 
            InputStream ips = new FileInputStream(dir);
            InputStreamReader ipsr = new InputStreamReader(ips); 
            try (BufferedReader br = new BufferedReader(ipsr)) {
                String ligne;
                Integer i=0;
                int somme = 0;
                int sum=0;
                double b = 0D;
                while ((ligne = br.readLine()) != null)
                {
                	
                    i++;
                    if (i==1) {
                    	for (Rank r : ModuleManager.rang) {
                    		if (r.getName().equalsIgnoreCase(ligne)) {
                    			var.rang=r;
                    		}
                    	}
                    }
                    if (i==2) {
                        var.niveau=Integer.parseInt(ligne);
                    }
                    if (i==3) {
                        var.xp=Integer.parseInt(ligne);
                    }
                    if (i==4) {
                        var.xpMax=Integer.parseInt(ligne);
                    }
                    if (i==5) {
                    	if (ligne.equals("true")) {
                    		var.achievementHelp=true;
                    	} else {
                    		var.achievementHelp=false;                    		
                    	}
                    }
                    if (i==6) {
                    	var.prefixCmd=ligne;
                    }
                    if (i==7) {
                    	var.mode=ligne;
                    }
                    if (i==8) {
                    	var.ame=Integer.parseInt(ligne);
                    }
                    if (i==9) {
                    	var.bonus=Double.parseDouble(ligne);
                    }
                    if (i==10) {
                    	if (isInteger(ligne))
                    		somme=Integer.parseInt(ligne);
                    	else {
                    		before16=false;
                    		somme=Integer.parseInt(ligne.replaceFirst(".", ""));
                    	}
                    }
                    if (i==11)
                    	var.chance=Double.parseDouble(ligne);
                    if (i==12)
                    	var.lot=Integer.parseInt(ligne);
                    if (i==13)
                		b=Double.parseDouble(ligne);
                	if (i==14) {
                		int time = Integer.parseInt(ligne);
                		if (time!=0) {
                			Active a = new Active(b, time);
                		}
                	}
                	if (i==15) 
                		var.prevVer=ligne;
                	if (i==16) {
                		Lot.nbLot=Integer.parseInt(ligne);
                	}
                	char ch[] = ligne.toCharArray();
                	for (int j=0;j<ch.length;j++) {
                		if (i!=10)
                			sum+=ch[j];
                	}
                }                
                
                if (before16) {
	                if ((somme-323924)/382!=var.niveau) {
	                	System.out.println("Fichier modifié détécté <1.6: Reset du Rpg !");
	                	resetRpg();
	                	return false;
	                }
                } else {
                	if (sum!=somme) {
	                	System.out.println("Fichier modifié détécté >1.6: Reset du Rpg !");
	                	resetRpg();
	                	return false;
                	}
                }
            } catch (IOException | NumberFormatException e) {}
		} catch (IOException | NumberFormatException e) {}
		}
		return true;
	}
	
	public static void resetRpg() {
		removeAllLock();
    	removeAllRank();
    	setRank("Petit Neko Novice");
    	var.niveau=1;
    	var.xp=0;
    	var.xpMax=300+getRandInt(100);
    	var.achievementHelp=false;
    	var.ame=0;
    	var.bonus=0;    
    	var.chance=0;
    	var.lot=0;
    	if (Active.t.isRunning())
    		Active.t.stop();
    	Active.bonus=0;
    	Active.time=0;
    	Utils.timeInGameHour=0;
    	Utils.timeInGameMin=0;
    	Utils.timeInGameSec=0;
    	Utils.timeInGameMs=0;
    	Lot.nbLot=3;
    	Irc i = Irc.getInstance();
    	i.setIdPlayer(-1);
    	i.setNamePlayer(mc.session.getUsername());
    	i.setLastMsg("");
    	i.setLastId(-1);
    	changeRank = true;
    	Utils.kills=0;
    	saveAll();
    	reload();
	}
	
	public static void removeAllLock() {
		for (Lock lock : ModuleManager.Lock) {
			lock.setLock(true);
		}
	}
	
	public static void removeAllRank() {
		for (Rank r : ModuleManager.rang) {
			r.setLock(true);
			r.setLvl(0);
		}
	}
	
	
	public static void saveFriends(String...fi) {
		if (verif!=null)
			return;
		String s = "";
		for (int i=0;i<Friends.friend.size();i++) {
			s+=Friends.friend.get(i).toString() + "\n";
		}
		File file = new File((fi.length==1 ? fi[0] : Utils.linkSave)+"friends.neko");
        try {
            file.createNewFile();            
            try (FileWriter writer = new FileWriter(file)) {
                writer.write(s);
                writer.flush();
            }

        } catch (IOException ex) {
            
        }
		
	}
	
	public static void loadFriends(String...fi) {
		File dir = new File((fi.length==1 ? fi[0] : Utils.linkSave)+"friends.neko");
		if (dir.exists()) {
		try { 
            InputStream ips = new FileInputStream(dir); 
            InputStreamReader ipsr = new InputStreamReader(ips); 
            try (BufferedReader br = new BufferedReader(ipsr)) {
                String ligne;
                while ((ligne = br.readLine()) != null)
                {    
                    Friends.friend.clear();
                	Friends.friend.add(ligne);
                }
            } catch (IOException | NumberFormatException e) { 
                
            }
    		} catch (IOException | NumberFormatException e) { 
                
    		}
		}
	}
	
	public static String getNyah() {
		n=true;
		int randy = (int) Math.round(Math.random()*13);
		if (mc.thePlayer!=null)
		switch (randy) {
			case 0:mc.thePlayer.playSound("records.blocks", 1.0F, 1.0F);break;
			case 1:mc.thePlayer.playSound("mob.cat.purreow", 1.0F, 1.0F);break;
			case 2:mc.thePlayer.playSound("mob.cat.meow", 1.0F, 1.0F);break;
			case 3:mc.thePlayer.playSound("mob.villager.idle", 1.0F, 1.0F);break;
			case 4:mc.thePlayer.playSound("mob.sheep.say", 1.0F, 1.0F);break;
			case 5:mc.thePlayer.playSound("liquid.splash", 1.0F, 1.0F);break;
			case 6:mc.thePlayer.playSound("mob.wither.idle", 1.0F, 1.0F);break;
			case 7:mc.thePlayer.playSound("mob.guardian.death", 1.0F, 1.0F);break;
			case 8:mc.thePlayer.playSound("mob.cat.purr", 1.0F, 1.0F);break;
			case 9:mc.thePlayer.playSound("mob.wither.death", 1.0F, 1.0F);break;
		}
		for (float k=0.0F;k<8;k++) {
			double neko = -1+Math.random()*2;	
			double nekoN = -1+Math.random()*2;
			if (mc.thePlayer!=null)
			mc.theWorld.spawnParticle(EnumParticleTypes.HEART, mc.thePlayer.posX+neko*1/k, mc.thePlayer.posY+0.85, mc.thePlayer.posZ+nekoN*1/k, 1, 1, 1, 1);
		}
		
		int neko = (int) Math.round(Math.random()*560);		
		int rand = (int) Math.round(Math.random()*50);
		String nyah = "";
		switch (neko) {
		case 0:nyah="Nyah nyah nyah :3";var.ame++;addChat(" +1 Ame");break;									
		case 1:nyah="Neko pour la vie <3";break;
		case 2:nyah="Les neko sont tout kawaii :3";	break;
		case 3:nyah="Je suis niveau "+var.niveau+" sur Neko :3"; break;
		case 4:nyah="Plein de petits neko pour twa <3";mc.thePlayer.playSound("mob.cat.purreow", 1.0F, 1.0F);break;
		case 5:nyah="Le coeur des neko te réchauferont <3";break;
		case 6:nyah="Les neko te font des bisous partout <3";mc.thePlayer.playSound("liquid.lava", 1.0F, 1.0F);break;
		case 7:nyah="Ne dit jamais non à Neko !";break;
		case 8:nyah="Les neko sont plus kawaii que les moutons de SkyLouna :3";break;
		case 9:nyah="Calînez ce gentil "+var.rang.getName()+" :3 *hug*";break;
		case 10:nyah="Je suis un nyah très rare :3";checkXp(rand*4);break;
		case 11:nyah="Owwww comme il est kawaii le petit "+getRandPlayer()+" :3";break;
		case 12:nyah="Haaawn kawaii desu ne *-*";break;
		case 13:nyah="Senpaiiii~ Daisuki /*w*/";checkXp(rand);break;
		case 14:nyah="Bonjour, je suis quelqu'un de très chelou.";break;
		case 15:nyah="En réalité je hais les chats *facepalm* (Je déconne je les adore <3)";mc.thePlayer.playSound("records.11", 10.0F, 1.0F);break;
		case 16:nyah="J'ai besoin d'aide...help...:C";mc.thePlayer.playSound("mob.ghast.scream", 1.0F, 1.0F);break;
		case 17:nyah="Grrrrrr *griffe* nyah ! /~*w*/~";	break;
		case 18:nyah="Grrrrrr *mord* nyark !";	break;
		case 19:nyah="Groar I'm a bear \\°o°/";	break;
		case 20:nyah="Ce smiley veut tout dire: ;3";mc.thePlayer.playSound("mob.villager.yes", 1.0F, 1.0F);break;
		case 21:nyah="J'aime mon pays madame oui !";mc.thePlayer.playSound("records.strad", 1.0F, 1.0F);checkXp(rand);break;
		case 22:nyah="Soyons en paix";checkXp(rand);break;
		case 23:nyah="T'as une ptite trace sur la joue ;3";checkXp(rand);break;
		case 24:nyah="This is spartnyaaaaaaa !";break;
		case 25:nyah="Le senpai en a un gros ;3";break;
		case 26:nyah="La senpai en a des gros ;3";break;
		case 27:nyah="Petits pervers ! Les neko ne sont pas des jouets !";break;
		case 28:nyah=mc.thePlayer.getName()+" vous a tous converti au Nyanisme \\o/ !";break;
		case 29:nyah="Neko-sama, prions Neko-sama pour obtenir son ultime pouvoir =w=";break;
		case 30:nyah="Neko-sama m'a accordé son ultime pouvoir /*w*/";break;
		case 31:nyah="La mort va vous attendre ici, par le pouvoir du Nyah !";mc.thePlayer.playSound("mob.enderdragon.end", 1.0F, 1.0F);break;
		case 32:nyah="Ces monstres ne résisteront pas à Neko ! /*3*/";checkXp(rand);break;
		case 33:nyah="Force bleu ! Force rouge ! Force jaune ! Force Nyaaaah !";break;
		case 34:nyah="Par le pouvor du nyah, je vais te nyanyater =w=";break;
		case 35:nyah="Qui est ce petit Neko aux couleurs bleu :o";break;
		case 36:nyah="Pantsuuuu~ \'°w°/";break;
		case 37:nyah="Oppai :3";break;
		case 38:nyah="Ka-ka-ka-kawaii °w° !";break;
		case 39:nyah="Quelle belle baleine.";mc.thePlayer.playSound("mob.guardian.curse", 1.0F, 1.0F);checkXp(rand);break;
		case 40:nyah="Bonjour, je suis tout à fait sain d'esprit.";break;
		case 41:nyah="'^'";break;
		case 42:nyah="Nyaaah /*w*/";break;
		case 43:nyah="Blblbllblblblbllblbblbllblblb";mc.thePlayer.playSound("mob.guardian.flop", 1.0F, 1.0F);break;
		case 44:nyah="Sluuurp ;3";break;
		case 45:nyah="Quel bon lait blanc";mc.thePlayer.playSound("mob.guardian.flop", 1.0F, 1.0F);break;
		case 46:nyah="J'aime la banane au chocolat ;3";break;
		case 47:nyah="C'est 'Nyaaah', espèce d'illétré !";break;
		case 48:nyah="La bonne aubergine ;3";break;
		case 49:nyah="Cet acte est revendiqué par Neko !";break;
		case 50:nyah="Cet acte est revendiqué par Tryliom !";checkXp(rand);break;
		case 51:nyah="Qu'est ce que tu touches là :o ?";mc.thePlayer.playSound("mob.guardian.curse", 1.0F, 1.0F);break;
		case 52:nyah="Eh bien, c'est pas piqué des neko !";break;
		case 53:nyah="Bonjour, j'habite à gland, au revoir chers glandu.";break;
		case 54:nyah="Coucou, tu veux voir mon neko ?";break;
		case 55:nyah="cc C popi";break;
		case 56:nyah="Les lances d'or des Neko vont tous vous convertir !";break;
		case 57:nyah="Je suis neko mon coco";break;
		case 58:nyah="7z a converti Neko en un chaton tout kawaii :3";break;
		case 59:nyah="Minecraft qui tombe dans les mains de chaton kawaii = Neko";break;
		case 60:nyah="Les moutons et les neko se nyanyatent ensemble :3";mc.thePlayer.playSound("mob.sheep.say", 1.0F, 1.0F);break;
		case 61:nyah="Comment cha ? Tu n'as pas pris tes neko quotidiens !?";break;
		case 62:nyah="Mon petit, les neko ne t'en voudront pas si tu nyanyates en public static :3";break;
		case 63:nyah="Do you speak Neko ?";break;
		case 64:nyah="Hi, i'm a kawai and scary neko, do you would play with me °w° ?";break;
		case 65:nyah="Bonjour, le hentai est ma passion, au revoir.";break;
		case 66:nyah="Je suis un chaton sataniste Nyark Nyark Nyark °w°";mc.thePlayer.playSound("mob.enderdragon.end", 1.0F, 1.0F);checkXp(rand);break;
		case 67:nyah="Un autre Neko ici ?! Où ça ?!";break;
		case 68:nyah="Linux est libre comme Neko !";break;
		case 69:nyah="C'est parti pour un 69 entre neko Nyaaaah !";checkXp(rand);break;
		case 70:nyah="Nous avons consommé de la drogue, nyaaaah";break;
		case 71:nyah="Rt si tu veux ces neko kawaii";break;
		case 72:nyah="Le yuri entre neko ça existe :o";checkXp(rand);break;
		case 73:nyah="Notre lapin rose fait du trapèze";mc.thePlayer.playSound("records.cat", 1.0F, 1.0F);break;
		case 74:nyah="T'as un joli cube ;3";break;
		case 75:nyah="Attention neko dangereux /!\'";break;
		case 76:nyah="On sort les griffes, on sort les crocs, Neko, à l'attaque Nyaaaaah !!";break;
		case 77:nyah="Bwaaaaaah *griffe* Nyaaaaaah xC";break;
		case 78:nyah="Boku wa a kawaii no scary neko-chan :3";break;
		case 79:nyah=getRandPlayer()+", tu veux une sucette :3 ?";break;
		case 80:nyah="Les poulpes sont géniaux avec leurs tentacules ;3";mc.thePlayer.playSound("mob.guardian.plop", 1.0F, 1.0F);checkXp(rand);break;
		case 81:nyah="Alors comme ça tu fais pleurer des neko, je vais te nyanyater mwa !";break;
		case 82:nyah="Nyaaa...nyaa...nya...Nyaaah !";break;
		case 83:nyah="Un <3 pour twa <3";break;
		case 84:nyah="Si tu continues je vais te griffer ! ;3";break;
		case 85:nyah="Que le neko se dénonce !";break;
		case 86:nyah="Où êtes-vous caché mes petits ;3 ?";break;
		case 87:nyah="Tellement kawaii °W°";break;
		case 88:nyah="J'ai perdu mon neko qui fait du Metal :C";mc.thePlayer.playSound("records.mellohi", 1.0F, 1.0F);break;
		case 89:nyah="NyanNyanNyanNyanNyanNyanNyanNyanNyanNyanNyan";checkXp(rand);mc.thePlayer.playSound("mob.creeper.say", 1.0F, 1.0F);break;
		case 90:nyah="Nya + nyah = Kawaii";break;
		case 91:nyah="Kawaii desu ne ? =w=/";break;
		case 92:nyah="Comment nyah ?";break;
		case 93:nyah=getRandPlayer()+", je suis ton hentai...";checkXp(rand);break;
		case 94:nyah="ma seule réponse: hentai";break;
		case 95:nyah="Nyaaaah...pas ici =/////=";break;
		case 96:nyah="Neko est ma drogue";break;
		case 97:nyah="J'invoque le Neko Powa !!!";mc.thePlayer.playSound("mob.wither.death", 1.0F, 1.0F);break;
		case 98:nyah="bllblbl neko bllblblbl";break;
		case 99:nyah="Je suis un neko converti '^'";break;
		case 100:nyah="J'adore les *****, "+getRandPlayer()+" et les neko";checkXp(rand);break;
		case 101:nyah="Yamete kudasai ! =////=";mc.thePlayer.playSound("mob.endermen.death", 1.0F, 1.0F);break;
		case 102:nyah="A l'aide on me viole :c";break;
		case 103:nyah="A l'aide on me visite :c";checkXp(rand);break;
		case 104:nyah="Neko>>>All";break;
		case 105:nyah="Neko>Toi>Ta mère";break;
		case 106:nyah="Big boobs";break;
		case 107:nyah="Milk ! I want milk !";break;
		case 108:nyah="Nekopara with patch R18 is awesome *-*";break;
		case 109:nyah=getRandPlayer()+",sale pervers ! =///=";	break;
		case 110:nyah="Kono hentai !";break;
		case 111:nyah="Warden c'est mon potow tout bypass avec nekow *3*";break;
		case 112:nyah="200+ phrases chelou sur le "+var.prefixCmd+"nyah :o";break;
		case 113:nyah="Les neko mènent une dictature sur Epicube !";break;
		case 114:nyah="J'ai peur d'utiliser nyah maintenant :s";break;
		case 115:nyah="Die die die !";break;
		case 116:nyah="Tu as été béni par un Saint Neko";checkXp(rand*3);break;
		case 117:nyah="Comme ma... '^'";mc.thePlayer.playSound("liquid.lava", 1.0F, 1.0F);break;
		case 118:nyah="Diversity <3";break;
		case 119:nyah="Neko>Ton lapin rose>"+getRandPlayer();break;
		case 120:nyah="Purr purr purr~";mc.thePlayer.playSound("mob.cat.meow", 1.0F, 1.0F);break;
		case 121:nyah="Neko senpaaiiiii :3";break;
		case 122:nyah="Pika pika !";break;
		case 123:nyah="Pikachuuuu :3 !";break;
		case 124:nyah="Noragami <3";break;
		case 125:nyah="Les neko gèrent notre économie";break;
		case 126:nyah="Nous sommes des illuminatis '^'";break;
		case 127:nyah="Les neko sont des illuminatis '^'";break;
		case 128:nyah="Un coup de cravache pour twa <3";checkXp(rand);break;
		case 129:nyah="Achète twa un neko <3";break;
		case 130:nyah="Ze veux du poisson /^~^/";break;
		case 131:nyah="Selon une étude californienne, les neko sont creepy";break;
		case 132:nyah="Selon une étude californienne, les neko sont kawaii";break;
		case 133:nyah="Selon une étude californienne, les neko sont chelou";break;
		case 134:nyah="Love Java <3";break;
		case 135:nyah="I drink Java every day <3";break;
		case 136:nyah="I eat Java, i drink Java, i'm Java '^'";break;
		case 137:nyah="The lord Neko is back !";checkXp(rand);break;
		case 138:nyah="The satanic Neko is back !";break;
		case 139:nyah="Keep calm and love Neko !";break;
		case 140:nyah="Keep calm and nyaaah !";break;
		case 141:nyah="Attention, un Kevin s'est évadé !";break;
		case 142:nyah="ça va rester dans les anals...";mc.thePlayer.playSound("dig.gravel", 1.0F, 1.0F);break;
		case 143:nyah="Break meeeee !";break;
		case 144:nyah="Je suis le vide '^'";break;
		case 145:nyah="I'm a satanic Teemo";mc.thePlayer.playSound("mob.enderdragon.end", 1.0F, 1.0F);break;
		case 146:nyah="Un bisous, tu veux un bisous ? Aller un bisous *3*";checkXp(rand);break;
		case 147:nyah="CAliiiiiin *hug*";break;
		case 148:nyah="Qui es-tu :o ?";break;
		case 149:nyah="Que ce passe-t-il lorsqu'un courant traverse une résistance ?";break;
		case 150:nyah="J'aime les short !";break;
		case 151:nyah="Tu veux un coup de fouet ;3 ?";mc.thePlayer.playSound("mob.guardian.curse", 1.0F, 1.0F);checkXp(rand*2);var.ame++;addChat(" +1 Ame");break;
		case 152:nyah=getRandPlayer()+", es-tu blblblbllb ?";break;
		case 153:nyah=getRandPlayer()+", i kick your nyass ;3 !";checkXp(rand);break;
		case 154:nyah=getRandPlayer()+" cheat";break;
		case 155:nyah=getRandPlayer()+" est un bg ;3";break;
		case 156:nyah=getRandPlayer()+" a comploté avec les nekos :o";break;
		case 157:nyah=getRandPlayer()+" est un sataniste !";break;
		case 158:nyah="J'ai vu "+getRandPlayer()+" fly !";break;
		case 159:nyah=getRandPlayer()+" a été très méchant";break;
		case 160:nyah=getRandPlayer()+" m'a violé dans sa cave :c";break;
		case 161:nyah="La maman de "+getRandPlayer()+" m'a violé :c";break;
		case 162:nyah="La maman de "+getRandPlayer()+" est bonne ;3";break;
		case 163:nyah="On avait pas dit les mamans "+getRandPlayer()+" :c";break;
		case 164:nyah="Beeeh"+getRandPlayer()+", beeeeeh";break;
		case 165:nyah=getRandPlayer()+", "+getRandPlayer()+" et "+getRandPlayer()+" ont volé mon goûter :c";checkXp(rand);break;
		case 166:nyah="Un gentil "+getRandPlayer()+" m'a fait un câlin tout doux <3";break;
		case 167:nyah="Mon petit "+getRandPlayer()+" m'a fait un bisous sur la joue =////= <3";checkXp(rand);break;
		case 168:nyah="L'intrus est "+getRandPlayer()+" !";break;
		case 169:nyah="Nyah nyah nyah Bat"+getRandPlayer()+"Nyaaah, nyah nyah nyah Bat"+getRandPlayer()+"Nyaaah !";break;
		case 170:nyah="Mon sensei s'appelle "+getRandPlayer()+"  mais chut faut pas le dire à "+getRandPlayer()+" °w°";checkXp(rand);break;
		case 171:nyah=getRandPlayer()+" veut un cookie de "+getRandPlayer();break;
		case 172:nyah="J'ai sucé "+getRandPlayer()+" et c'étais blbllbl °o°";checkXp(rand);break;
		case 173:nyah=getRandPlayer()+" m'a volé mon innocence °w°";break;
		case 174:nyah=getRandPlayer()+", ma bite est grande en toi";break;
		case 175:nyah="Je t'aime "+getRandPlayer()+", tu es tout kawaii nyaaaah *W*";checkXp(rand);break;
		case 176:nyah="Ogenki desu ka "+getRandPlayer()+"-san ?";break;
		case 177:nyah="Ohayo "+getRandPlayer()+"-san :3";break;
		case 178:nyah="Oh non ! "+getRandPlayer()+" m'a rendu toute collante :c";checkXp(rand);break;
		case 179:nyah=getRandPlayer()+" ? Tu es dispo ce soir ?";break;
		case 180:nyah="Je veux mes deux "+getRandPlayer()+" et "+getRandPlayer()+" dans mon lit ! *w*";checkXp(rand);break;
		case 181:nyah="Vous êtes fatigués ? C'est un lupus !";break;
		case 182:nyah="Vous vous sentez trop plein ? C'est un lupus !";break;
		case 183:nyah="Un jour peut-être je serai le meilleeeeeeur";break;
		case 184:nyah="J'ai glissé "+getRandPlayer()+" !";break;
		case 185:nyah="Oh non c'est tout mouillé maintenant :c";break;
		case 186:nyah="ça va glisser !";break;
		case 187:nyah="ça va chauffer !";break;
		case 188:nyah="Chaaaauuuud devant !";break;
		case 189:nyah="ça colle...";break;
		case 190:nyah="Lick lick lick ;3";break;
		case 191:nyah="Jean-robert ! Arrête de jouer avec ça !";break;
		case 192:nyah="Je vous l'avais dit...";break;
		case 193:nyah="Qui allons-nous torturer aujourd'hui nyark nyark nyark >:3 ?";mc.thePlayer.playSound("mob.wither.idle", 1.0F, 1.0F);break;
		case 194:nyah="ça a l'air appétissant °w°";break;
		case 195:nyah="Kono baka !";break;
		case 196:nyah="Kono butsu !";break;
		case 197:nyah="Bandes de pervers !";break;
		case 198:nyah="Et un coup de martinet ! Un !";break;
		case 199:nyah="Shak Shak Shhhhhh...";break;
		case 200:nyah="Le déséspoir t'envahit doucement >:3";break;
		case 201:nyah="C'est humide par ici '^'";break;
		case 202:nyah="Mange tes brocolis "+getRandPlayer()+" !";break;
		case 203:nyah="On avait dit pas les mamans... >:c";break;
		case 204:nyah="How many tits have you "+getRandPlayer()+" ? :o";mc.thePlayer.playSound("mob.wither.idle", 1.0F, 1.0F);break;
		case 205:nyah="Tits tits tits !";break;
		case 206:nyah="Take a Pen, take a Apple. HAN ! Apple Pen !";break;
		case 207:nyah="Take a Pinapple, take a Pen. HAN ! Pinapple Pen !";break;
		case 208:nyah="PPAP tututututu";break;
		case 209:nyah="Je peux deviner que sous ton t-shirt tu es bien batti "+getRandPlayer()+" ;3";break;
		case 210:nyah="Petite perverse ! Coquine ;3 !";break;
		case 211:nyah="Petit pervers ! Coquin va ;3 !";break;
		case 212:nyah="Tu me met mal à l'aise avec ton skin bizarre "+getRandPlayer()+" >:s";mc.thePlayer.playSound("mob.wither.idle", 1.0F, 1.0F);break;
		case 213:nyah="T'as changé, je ne te reconnais plus "+getRandPlayer()+" !";break;
		case 214:nyah="Arrête ça c'est sensible ici... Naaaaah =///=";break;
		case 215:nyah="°W° C'est mal °W°";break;
		case 216:nyah="°W° ^W^ Nyah ^W^ °W°";break;
		case 217:nyah="Mais, mais ! C'est mamie qui l'a fait !";break;
		case 218:nyah="Taco Power !";break;
		case 219:nyah="Taco Powa !";break;
		case 220:nyah="Taco !";break;
		case 221:nyah="A NekoWizard is out ! Catch him !";break;
		case 222:nyah="nekohc.fr is a good website";break;
		case 223:nyah="Qui veut des cookies :3 ?";break;
		case 224:nyah="Zlatanons ensemble !";break;
		case 225:nyah="Je t'offre une bière !";break;
		case 226:nyah="Mmmmh la bonne baguette ;3";break;
		case 227:nyah="Att je vais trempé mon biscuit dans le lait";break;
		case 228:nyah="Donne moi ton biscuit !";break;
		case 229:nyah="Clitorions nous !";break;
		case 230:nyah="Pedoneko is here !";break;
		case 231:nyah="Sale cheateur";break;
		case 232:nyah="Ohayo mon coquinou :3";break;
		case 233:nyah="J'ai adoré la nuit que j'ai passée avec toi "+getRandPlayer()+" :3";break;
		case 234:nyah="Owiii plus fort ! Vas-y plus fort "+getRandPlayer()+" >:3 !";break;
		case 235:nyah="Pousse pousse !";break;
		case 236:nyah="Comme c'est étroit ici °w°";break;
		case 237:nyah="Ce sera notre petit secret...";break;
		case 238:nyah="Bonjour, je suis le n°"+rand*neko;break;
		case 239:nyah="Aaaaah ! il fait tout noir là dedans :c";break;
		case 240:nyah="Elle est vachement grosse :o";mc.thePlayer.playSound("mob.wither.idle", 1.0F, 1.0F);break;
		case 241:nyah="Toute dur owww :3";break;
		case 242:nyah="L'excitation est à son comble !";break;
		case 243:nyah="Hammer time !";break;
		case 244:nyah="Owi vas-y, enfonce tout d'un tout je veux tout sentir "+getRandPlayer()+" ;3";break;
		case 245:nyah="Arrêtez de penser à des choses perverses !";break;
		case 246:nyah="Ce n'est pas un double sens !";break;
		case 247:nyah="Neko, pour les purs gaullois sorti de l'asile psychiatrique...";break;
		case 248:nyah="Kiss me "+getRandPlayer()+" *3* !";break;
		case 249:nyah="Gang bang réussi !";break;
		case 250:nyah="C'est quoi toutes ces capotes :o ?!";mc.thePlayer.playSound("mob.wither.idle", 1.0F, 1.0F);break;
		case 251:nyah="Va baver ailleurs !";break;
		case 252:nyah="Que tu peux être vicieux dès fois twa ;3 !";mc.thePlayer.playSound("mob.guardian.flop", 1.0F, 1.0F);break;
		case 253:nyah="Si je le fais c'est bien parce que tu me plait...";break;
		case 254:nyah="J'aime les <3 pas toi ? <3";break;
		case 255:nyah="Un dieu Neko est descendu et m'a nyanyaté à l'oreille lorsque je consommais de la Magical Drugs +W+";break;
		case 256:nyah="Neko girl un jour, Neko girl toujours ! Hyaaaaaaaw ! <3";break;
		case 257:nyah="C'est dur de tout avaler "+getRandPlayer()+" :c";break;
		case 258:nyah="Les jours de pluie je pense au TryTry Satanique qui me violente dans sa cabane... /o/";break;
		case 259:nyah="L'homme habillé en rose paillete dans les bois m'a choqué à vie °p°";break;
		case 260:nyah="Oh naaaaan...Je l'ai encore mouillé...c'est tout collant maintenant :c";mc.thePlayer.playSound("mob.guardian.flop", 1.0F, 1.0F);break;
		case 261:nyah="Diversity me berce jusqu'à l'extase °w°";break;
		case 262:nyah="J'ai trop consommé de cette drogue, elle me perverti doucement...he-help me !";break;
		case 263:nyah="Ah bah bravo "+getRandPlayer()+", t'as tout gagné ! Vu tout ce que t'as sortis... :o";mc.thePlayer.playSound("mob.guardian.flop", 1.0F, 1.0F);break;
		case 264:nyah="Owi ce beau jet";break;
		case 265:nyah="OwO";break;
		case 266:nyah=">////>";break;
		case 267:nyah="<////<";break;
		case 268:nyah=">////<";break;
		case 269:nyah=">.<";break;
		case 270:nyah="<.<";break;
		case 271:nyah=getRandPlayer()+", je te purgerais !";break;
		case 272:nyah=getRandPlayer()+", je te purgerais de ton espris sale !";break;
		case 273:nyah="Monsieur les modos, "+getRandPlayer()+" me traite de pervers :c";break;
		case 274:nyah="Coucou mon petit, tu as l'air tellement appétissant ;3";break;
		case 275:nyah="Le Pervers m'a fais disciple...Je suis maintenant votre parfait chasseur c: !";break;
		case 276:nyah="La Perverse m'a fais disciple...Je suis maintenant votre parfaite chasseresse c: !";break;
		case 277:nyah="Le culte du Nyanysme est à prendre au sérieux ! Nous sommes une secte reconnue !";break;
		case 278:nyah="J'adore m'habiller en rose fluo les vendredi soir et m'exposer à la lune !";break;
		case 279:nyah="Le fouet, une arme de domination, mais je l'utilise aussi à mes propres fins ;3";break;
		case 280:nyah="J'ai acheté hier un fouet en cuir à l'Amoureux du Fouet, très efficace sur vous ;3";break;
		case 281:nyah="Le hentai est ma meilleur drogue, après la Magical Drugs...";break;
		case 282:nyah="Plus sataniste que moi y a pas !";break;
		case 283:nyah="Avec mon Fouet Légendaire, vous allez arborer des souffrances si jouissives pour ma personne >:3";break;
		case 284:nyah="Alors comme ça on se touche la nouille..mai-mais..arrêtez ces immondices >:c !";break;
		case 285:nyah="Le liquide visqueux est bizarre...>o<";mc.thePlayer.playSound("mob.guardian.flop", 1.0F, 1.0F);break;
		case 286:nyah="WHAT C'étais quoi ça Oo ?";break;
		case 287:nyah="\\WoW/";break;
		case 288:nyah="Une gentille Succube m'a fait des choses bizarres...";break;
		case 289:nyah="Je suis atteint du Nyanysme, c'est incurable et terriblement transmissible ! Fuyez pauvres fous !";break;
		case 290:nyah="Je suis choqué.";break;
		case 291:nyah="T'y vas pas de main mort toi he-HAAAAN Pas ici ! Non ! Arrête ! >:C";mc.thePlayer.playSound("mob.guardian.flop", 1.0F, 1.0F);break;
		case 292:nyah="C'est gênant :x";break;
		case 293:nyah="He bien c'est pas les pervers qui manque par ici...";break;
		case 294:nyah="Si proche, tu es si proche de-blblbllbl *splotch splotch*";break;
		case 295:nyah="7r Et j'aime bien aussi ce que tu me fais :3";mc.thePlayer.playSound("mob.guardian.flop", 1.0F, 1.0F);break;
		case 296:nyah="7r T'avais promis de me passer de la Magical Drugs :c";break;
		case 297:nyah="7r Suce moi et je dirais rien ;3";break;
		case 298:nyah="Pervers en herbe ! Je suis un pro mwa !";break;
		case 299:nyah="Le Neko ne se repose jamais, toujours du boulot !";break;
		case 300:nyah="Je suis un Neko tellement sataniste que je vais vous parler à voix basse en latin ancien !";break;
		case 301:nyah="Et un esprit perverti en moins ! Un !";break;
		case 302:nyah="Et un esprit perverti en plus >:3 ! Un !";break;
		case 303:nyah="ça rend accro tout ça...Je vais encore finir tout sale...";break;
		case 304:nyah="La Neko Army recrute ! Envoyez vos CV sur neko.fr/forum.php !";break;
		case 305:nyah="Je suis un être normal doté d'une conscience très sale";break;
		case 306:nyah="J'ai vu des choses...que je n'aurais pas voulu voir là bas :c";break;
		case 307:nyah="C'est dangereux de se promener seul la nuit...Tryliom pourrait vous violer sur le chemin...";break;
		case 308:nyah="Blblblblblblbl /+w+/ Sluuuuuuurp +p+";break;
		case 309:nyah="7r Je pensais pas que les modos seraient aussi facile à corrompre tu vois xD";break;
		case 310:nyah="7r Tu utilises Huzuni et tu sais même pas ouvrir le menu Oo";break;
		case 311:nyah="J'ai fait blblblblblbl dans les fesses de "+getRandPlayer();break;
		case 312:nyah="Ulkior est grand quand il est ******";break;
		case 313:nyah="7r Si le mien pouvais autant grossir je pourrais rivaliser... :c";break;
		case 314:nyah="I like peeeeeee peeeeeeeee peeeeee on you.";break;
		case 315:nyah="My lord, please hit me, i love when you hit me <3";break;
		case 316:nyah="Mon amour, embrasse moi ! <3";break;
		case 317:nyah="Les Neko m'ont hacké pendant que je fappais :c";break;
		case 318:nyah="Send nude plz";break;
		case 319:nyah="Je suis le sûpreme et génial: "+var.rang.getName()+" !";break;
		case 320:nyah="-[Tryliom reviens toujours]-";break;
		case 321:nyah="ça rentre toujours pas, aide mwa :c";break;
		case 322:nyah="Trop proche...Trop proche trop proche troooop prooooche Aaaaah D:";break;
		case 323:nyah="Nyaaaaaaw <3";break;
		case 324:nyah="Les "+var.rang.getName()+" sont très dangereux !";break;
		case 325:nyah="Les "+var.rang.getName()+" sont prêt à tout afin de vous dominer !";break;
		case 326:nyah="J'aime tout ce qui est collant mon chou..Un peu comme toi ;3";break;
		case 327:nyah="C'est pas tout ça mais j'ai un fouet à me prendre mwa...";break;
		case 328:nyah="Gérer tous ces Nekos est dur, vous me comprenez ? Je souffre :'c";break;
		case 329:nyah="7r Pleurons ensemble :'c";break;
		case 330:nyah=":r Ce serai vraiment con qu'ils te chopent après tout ce que t'as pris";break;
		case 331:nyah=":r Que ça reste entre nous, c'est très privé cette histoire";break;
		case 332:nyah="Ayzoh lèche tellement bien :o";break;
		case 333:nyah="Mazoow a fait une tâche sur mon pantalon >:c";break;
		case 334:nyah="La punition ? C'est maintenant !";break;
		case 335:nyah="Le carnage fait rage >:3";break;
		case 336:nyah="C'est pas comme si j'y était forcé mais ça a son intérêt";break;
		case 337:nyah="Les mignonneries ça se respecte bande de truant >:c !";break;
		case 338:nyah="Je suis un "+var.rang.getName()+" et je vais vous violer tellement longtemps que vos sequels seront immortels";break;
		case 339:nyah="Ookami et Neko se sont livré un combat sanglant de mignonnerie le dernier siècle, je suis un survivant";break;
		case 340:nyah="La victoire m'appartient ! Nyaaaaaaah !";break;
		case 341:nyah="Mon nombre fétiche est le 69, il me fait mouiller de plaisir ;3";break;
		case 342:nyah="Plaisir, plaisir, oh mon grand plaisir de ***** ! QwQ";break;
		case 343:nyah="C'est très perturbant >w>";break;
		case 344:nyah="J'étouffe, j'étouffe, help help ! Monsieur les modos j'ai activé un truc chelou :c";break;
		case 345:nyah="C'est si délicat de ta part de m'en faire prendre connaissance";break;
		case 346:nyah="Très intéressant tout ça :o";break;
		case 347:nyah="Je peux être très percutant si je le désir !";break;
		case 348:nyah="7r Jpeux y aller ? :c ça commence à devenir génant ce que tu fais :c";break;
		case 349:nyah="-==[Neko Powa]==-";break;
		case 350:nyah="It's too ecchi for me :c !";break;
		case 351:nyah="La Neko Army a encore frappé !";break;
		case 352:nyah=getRandPlayer()+" m'a montré un truc énorme :o !";break;
		case 353:nyah="Plus c'est long, plus c'est bon ;3";break;
		case 354:nyah="Comment ça tu veux pas ? Aller avale tout";break;
		case 355:nyah="LoliPower +w+";break;
		case 356:nyah=":r et quand ça a finit d'installer tu le sélectionnes et normalement tu vas avoir un menu tout joli";break;
		case 357:nyah="Et pouf ! ça fait des Chocapic !";break;
		case 358:nyah="Merci beaucoup "+getRandPlayer()+", j'avais super soif :3";break;
		case 359:nyah="Pas ici, "+getRandPlayer()+", ça va devenir tout mouillé si tu continues :c";break;
		case 360:nyah="I need more, "+getRandPlayer()+", of your milk :3";break;
		case 361:nyah=getRandPlayer()+", ton goûter est servi, j'ai mis un ingrédient mystère de ma conception ;3";break;
		case 362:nyah="Viens vérif "+getRandPlayer()+", ton cheat est cramé...";break;
		case 363:nyah=getRandPlayer()+", j'ai un gros jouet à te faire voir ;3";break;
		case 364:nyah="C'est pas dans mes habitudes de le faire mais bon je vais me hâter de le faire sortir...";break;
		case 365:nyah="Tais-toi et suce c:";break;
		case 366:nyah="Arrête de lécher ça comme ça !";break;
		case 367:nyah="Je suis un pur "+var.rang.getName()+" !";break;
		case 368:nyah="Je suis un être si vicieux que tu ne pourras pas me repérer avant le coup sale que je vais te mettre ;3";break;
		case 369:nyah="Tape moi ça, bien juteux !";break;
		case 370:nyah="Je cheat pas, par contre "+getRandPlayer()+" a une grosse reach";break;
		case 371:nyah="Loli-chan est magnifique *w*";break;
		case 372:nyah="Stop me frapper ici ! :c";break;
		case 373:nyah="Try to get my ***** here c:";break;
		case 374:nyah="Quel type de loli es-tu :o ?";break;
		case 375:nyah="7r T'aime bien ma queue de fer ;3 ?";break;
		case 376:nyah=":r Fais gaffe avec ce que t'envoie des gens pourrait la voir :x";break;
		case 377:nyah="C'est tombé tout seul !";break;
		case 378:nyah=":r Grrrrr Tu m'as touché au mauvais endroit vilain !";break;
		case 379:nyah=":r désolé mais c'est trop petit, je préfère les gros :3";break;
		case 380:nyah="Ce n'est pas dans mon intérêt mais...Je trouve ça joli :3";break;
		case 381:nyah="Les Neko ont apportés aux Titans l'éloquence du savoir Neko";break;
		case 382:nyah="I love KKK";break;
		case 383:nyah="It's an accident !";break;
		case 384:nyah="C'est pas mwa, c'est "+getRandPlayer()+" !";break;
		case 385:nyah="Je suis un Anon e-mouse !";break;
		case 386:nyah="Ooooh so cuuuute :3";break;
		case 387:nyah="It's so delicious :D !";break;
		case 388:nyah="Thx mom";break;
		case 389:nyah="Merci maman";break;
		case 390:nyah="Ma seul erreur est de ne pas t'avoir ****** avant ce jour...";break;
		case 391:nyah="Tryliom a fait de moi ce que je suis aujourd'hui :3";break;
		case 392:nyah="ki connai trylium ?";break;
		case 393:nyah=":r tu sais, c'est si facile de te dox, fais attention à ce que tu dis ;)";break;
		case 394:nyah="Les petites pillules bleu ? Oui je les ai prise, ça me rend tout bizarre maintenant...";break;
		case 395:nyah="Je suis loin, si loin de toi...Et pourtant je me sens si proche...";break;
		case 396:nyah="Ma culotte est tout mouillée :c";break;
		case 397:nyah="Montre moi encore plus de toi ;3";break;
		case 398:nyah="Reviens ici ! Tu auras un bon goûter !";break;
		case 399:nyah="J'ai vu un Léviathan, il est tellement choquant :c Et il y avait un gars chelou dessus :s";break;
		case 400:nyah="Oh Oh Oh my little boy Oh Oh Oh";break;
		case 401:nyah="Oui, un peu comme ma b*** >////>";break;
		case 402:nyah="Aaaah...Dragonaïf...";break;
		case 403:nyah="Tu es si naïf mon petit ;3";break;
		case 404:nyah="Tu crois que je fais quoi depuis tout ce temps voyons c:";break;
		case 405:nyah="Que la nature humaine peut me paraître si inférieure...";break;
		case 406:nyah="Misérables humains ! Je vous maudis !";break;
		case 407:nyah="Tu seras maudit !";break;
		case 408:nyah="La noirceur de ce monde te teindra jusqu'à la fin !";break;
		case 409:nyah="L'Armagedon est si proche...Si délicieuse ;33";break;
		case 410:nyah="Je ne vois que la destruction autour de toi...";break;
		case 411:nyah="Fafnir saurait te recadrer s'il était là...";break;
		case 412:nyah="Oui je l'avoue je suis un Dragomaniac !";break;
		case 413:nyah="C'est si difficile à comprendre que ça ?";break;
		case 414:nyah="Mais...pourtant... :(";break;
		case 415:nyah="Oui je vois ton gros bâton ;3";break;
		case 416:nyah="L'Armagedon approche à grand pas...Soyez prêt...";break;
		case 417:nyah="Sors de là et bats toi !";break;
		case 418:nyah="é.è";break;
		case 419:nyah="Que le chaos engloutisse ce monde !";break;
		case 420:nyah="Votre résistance est vaine par ce Chaos !";break;
		case 421:nyah="Serviteur du Chaos, "+Utils.getRandPlayer()+", je te maudit !";break;
		case 422:nyah="Ne te méprend pas !";break;
		case 423:nyah="Mes intentions sont diaboliques à souhait ;3";break;
		case 424:nyah="Mes intentions sont divines à souhait ;3";break;
		case 425:nyah="Mes intentions sont chaotiques à souhait ;3";break;
		case 426:nyah="Mes intentions sont titanesques à souhait ;3";break;
		case 427:nyah="La situation n'est-elle pas la meilleur pour ce genre de chose ? ;3";break;
		case 428:nyah="C'est le chat ! C'est pas moi !";break;
		case 429:nyah="Qui pourra apporter ce vent de destruction si demandé ?";break;
		case 430:nyah="Je veut ma brioche !";break;
		case 431:nyah="Je vous tuerais tous ! Mon feu destructeur vous détruira !";break;
		case 432:nyah="Cette fois est la dernière "+Utils.getRandPlayer()+"-chan !";break;
		case 433:nyah="Ooooooh qu'elle est mimi cette loli *fond* +w+";break;
		case 434:nyah="La valeur de cet acte n'a donc aucune valeur à vos yeux ?";break;
		case 435:nyah="Tu me fais pitié...Kono busu";break;
		case 436:nyah="Konran wa, anata no sekai ni kimasu !";break;
		case 437:nyah="Kono hiretsuna kōi wa norowa remasu !";break;
		case 438:nyah="Dono yō ni anata wa watashi ni anata o toru tame ni aete !?";break;
		case 439:nyah="Harumagedon wa, kono jaakuna sekai o hakai shimasu !";break;
		case 440:nyah="Lucifer vous punira tous ! >:D";break;
		case 441:nyah="Lucifer, un bel Archange à la base, qui a donné naissance aux démons, n'as pas si mal tourné que ça...";break;
		case 442:nyah="Menteur !";break;
		case 443:nyah="Ils sont si bons mmmmmmmh ! Refait en moi s'il te plait :3";break;
		case 444:nyah="Est ce que les helpers aiment le couscous ?";break;
		case 445:nyah="Mmmmh que c'est gros...Heum heum pardon";break;
		case 446:nyah="C'est si visible que ça ?";break;
		case 447:nyah="Comment aurais-je pu faire ça ? Je n'ai pas encore atteint ce niveau !";break;
		case 448:nyah="Je vais te bouffer...Mmmmh ;3";break;
		case 449:nyah="Ta cuisse est délicieuse";break;
		case 450:nyah="El Satanicha !";break;
		case 451:nyah="Slurp slurp";break;
		case 452:nyah="Les bananes c'est la vie <3";break;
		case 453:nyah="Tiianshii est la Déesse du neko <3";break;
		case 454:nyah="Une banane est monté dans un arbre, la branche a lâchée :/";break;
		case 455:nyah="Modo une banane me course ! Aie ! Arrêtez le !";break;
		case 456:nyah="Cette ressemblance si fortuite entre vous, cher Admin, et ma banane est-elle normale ?";break;
		case 457:nyah="L'achat de banane domestique est-il possible "+getRandPlayer()+" ?";break;
		case 458:nyah=getRandPlayer()+" t'es pas beau";break;
		case 459:nyah="Au coin ;3";break;
		case 460:nyah=":r Aller viens par là que tu subisses ta punition ;3";break;
		case 461:nyah="J'avoue...";break;
		case 462:nyah="Et j'aime ça ;3";break;
		case 463:nyah="ça devrais être légal..";break;
		case 464:nyah="J'aimerais pat des loli <3";break;
		case 465:nyah="I'm gandalf !";break;
		case 466:nyah="The Lord of The Neko ^>.<^";break;
		case 467:nyah="^+^+^";break;
		case 468:nyah="Notre seigneur, Lucifer, viendra pour vous !";break;
		case 469:nyah="ui madame";break;
		case 470:nyah="Pew pew pew !";break;		
		case 471:nyah="Cette banane est dangereuse !";break;
		case 472:nyah="J'aime les glands comme mes amis les bananes :3";break;
		case 473:nyah="C'est endroit magnifique est mon fort ;3 !";break;
		case 474:nyah="Nous sommes dans un endroit sombre et dangereux...";break;
		case 475:nyah="Attention ! La banane mord !";break;
		case 476:nyah="Owwww mon petit neko :3";break;
		case 477:nyah="Tu es si mignon quand tu t'énerves :3 <3";break;
		case 478:nyah="Cette loi s'applique à tous !";break;
		case 479:nyah="Oh grand dieu des bananes Delxer, voulez vous bien m'octroyer un de vos fidèles :3 ?";break;
		case 480:nyah="Banane Powa !";break;
		case 481:nyah="C'est si inabituel de te voir dans cette tenue :o";break;
		case 482:nyah="Je vois touuuuut é.è";break;
		case 483:nyah="*Blush* >///>";break;
		case 484:nyah="Il existe donc :O !";break;
		case 485:nyah="Où est-il !? Le grand ambassadeur doit-être ici !";break;
		case 486:nyah="Co-que..que fais-tu >////> !?";break;
		case 487:nyah=":r Tu n'est pas encore assez bon, je vais t'apprendre voyons :3";break;
		case 488:nyah="La VR est magique °w°";break;
		case 489:nyah="Les "+var.rang.getName()+" domineront !";break;
		case 490:nyah="Je suis une fille innocente toute mimi :3";break;
		case 491:nyah="I'm a cute little girl :3";break;
		case 492:nyah="Où est ma crèèèèème ??!";break;
		case 493:nyah="Elle peut tout faire ;3";break;
		case 494:nyah="Viens goûter mon bon miel :3";break;
		case 495:nyah="J'ai fais une réserve de glands pour le dieu des bananes Delxer, j'espère qu'il va apprécier mon présent :3";break;
		case 496:nyah="C'est si dur de s'appeler Jean-Robert ?";break;
		case 497:nyah="Ta petite bouille est tellement mignonne :3 <3";break;
		case 498:nyah=":r Comment va ton élevage de bananes ?";break;
		case 499:nyah="Je n'ai pas reçu ta requête apparemment :o";break;
		case 500:nyah="Quel projet ?";break;
		case 501:nyah="7r c'est quoi les devoirs pour demain ?";break;
		case 502:nyah="Noob ou pas, j'y vais !";break;
		case 503:nyah="Est-ce que tu as tout bien compris ? Vas-y à fond maintenant ! ;3";break;
		case 504:nyah=":r att, tu verras le temps que j'allume ;3";break;
		case 505:nyah="J'aime pas ce que tu envoies :c";break;
		case 506:nyah="Cette fois ci j'y arriverais !";break;
		case 507:nyah="Les bananes n'ont pas de pitiés !";break;
		case 508:nyah="Le Gardien des Neko protège la Sainte Déesse avec loyauté !";break;
		case 509:nyah="Le Gardien des Neko protège la Sainte Déesse avec soumission !";break;
		case 510:nyah="Je dirigerais ces hommes avec ordre !";break;
		case 511:nyah="Soyez sans crainte !";break;
		case 512:nyah="*Tape";break;
		case 513:nyah="C'est fort :x !";break;
		case 514:nyah="C'est pas comme ça que tu dois faire !";break;
		case 515:nyah="Elle plane bien cette banane, il aurait pas dû sauter dans le tas de coke :o";break;
		case 516:nyah="Je plane depuis que j'ai glissé dans ce tas de coke";break;
		case 517:nyah="Donne à goûter....Argh dégeu...";break;
		case 518:nyah="Le furet, le furet, le furet se glisse partout...";break;
		case 519:nyah="It's "+new SimpleDateFormat("HH:mm:ss", Locale.FRANCE).format(new Date())+" and i'm hungry ;3";break;
		case 520:nyah="Pourquoi tu trembles comme cha :o ?";break;
		case 521:nyah="Je t'ai si émue que ça awwww :3";break;
		case 522:nyah="Tu es tellement chouuuue je crack !";break;
		case 523:nyah="Je veux une banane bien fraîche !";break;
		case 524:nyah="J'aime les bananes :3";break;
		case 525:nyah="7r mdp: toto fais du vélo";break;
		case 526:nyah="J'aime quand ils sont bien mou et arrondi :3";break;
		case 527:nyah="That's weird...";break;
		case 528:nyah="L'aimez vous ? :3";break;
		case 529:nyah="ça a glissé :o";break;
		case 530:nyah="J'aime ce que tu me dis là ;3";break;
		case 531:nyah="Je ne suis plus innocent voyons !";break;
		case 532:nyah="Je me suis perdu...Où est la gare Montparnasse svp ?";break;
		case 533:nyah=":r Mais tu sais et faiden avec ses promesses et toujours son ac de caca on rit xD";break;
		case 534:nyah="As-tu la chose que je t'ai demandé ? Supeeeerbe ;3";break;
		case 535:nyah="Mmmh tellement moelleux _w_ *bave*";break;
		case 536:nyah="-w-";break;
		case 537:nyah="Vilaine petite fille...Il va falloir te punir maintenant...";break;
		case 538:nyah="Il ne fallait pas toucher...";break;
		case 539:nyah="HIAAA";break;
		case 540:nyah="Crcrcrcrcrcr";break;
		case 551:nyah="Krkrkrkkrkrkrk";break;
		case 552:nyah="J'en bave rien que d'imaginer °^°";break;
		case 553:nyah="Excitant";break;
		case 554:nyah="Je les aimes grillés ;3";break;
		case 555:nyah="Je les aimes rebondissantes ;3";break;
		case 556:nyah="C'est moi qui choisis !";break;
		case 557:nyah="Neko et Ecureuils, main dans la main, pervertirons jeunes et grands !";break;
		case 558:nyah="C'était petit...";break;
		case 559:nyah="Plus c'est gros mieux c'est ;3";break;
		case 560:nyah="Montre la moi :3";break;
		case 561:nyah="Eteins la lumière "+Utils.getRandPlayer()+", j'ai pas envie que tu me vois nue :3";break;
		case 562:nyah="Neko is loading";break;
		case 563:nyah="Le maître punis très fort !";break;
		case 564:nyah="AntoZzz ne sait pas coder !";break;
		case 565:nyah="La soumise hurle souvent dans sa chambre";break;
		case 566:nyah="AntoZzz + Discord = 10'000";break;
		case 567:nyah="Tu as été une méchante fille...";break;
		case 568:nyah="Oh qu'il est beau ce collier de bondage ;3";break;
		case 569:nyah="Quelque chose de vilain a jaillit de ma queue :o";break;
		case 570:nyah="ça brille...C'est quoiii ?";break;
		case 571:nyah="Attachée ! Tu vas être attachée !";break;
		case 572:nyah="J'aime cette corde, elle est bien jolie...";break;
		case 573:nyah="Je vois des modos partout c'est horrible D:";break;
		case 574:nyah="J'attache très bien tu sais ;3";break;
		case 575:nyah="J'aime les noeuds de cette corde ;3";break;
		case 576:nyah="Nyan nyan nyan ~";break;
		case 577:nyah="Je suis assistante neko !";break;
		case 578:nyah="BONSOUAR";break;
		case 579:nyah="Le hentai c'est la vie x3";break;
		case 580:nyah="Le hentai m'a bien accueili et aider à tout faire sortir :3";break;
		case 581:nyah="Elle est trop grosse cette banane ds,fjndsjknfd";break;
		case 582:nyah="J'ai coupé ta banane en deux >:3";break;
		case 583:nyah="Du bon jus y sort >:3";break;
		case 584:nyah="Pensez au chinois qui travaille bien là haut";break;
		case 585:nyah="Issou";break;
		case 586:nyah="Je suis venu en paix !";break;
		case 587:nyah="Ne pas me victimiser svp :c";break;
		case 588:nyah="Mange mon ba...Tacos !";break;
		case 589:nyah="J'ai adopté une banane taille XXL...elle rentre pas :c";break;
		case 590:nyah="C'est trop ptit !";break;
		case 591:nyah="Je suis un soumis et j'adore ça ! :333";break;
		case 592:nyah="Fouettez moi ! J'adore ça !";break;
		case 593:nyah="Non.";break;
		case 594:nyah="Oui.";break;
		case 595:nyah="Neko pour vous servir";break;
		case 596:nyah="Je suis été en feu";break;
		case 597:nyah="Pardon monsieur mais je ne connais pas ce mot";break;
		case 598:nyah="Tryliom le sadique !";break;
		case 599:nyah="Didi est la soumise absolue de Tryliom et lui appartient !";break;
		case 600:nyah="Miaou";break;
		case 601:nyah="ça ban fort";break;
		case 602:nyah="Neko tout prêt !";break;
		case 603:nyah="Je m'enfuie pas je voooole :D";break;
		case 604:nyah="J'ai de grandes ailes noires...ça voooole";break;
		case 605:nyah="La belle maman à Tryliom est une sorcière >:D";break;
		case 606:nyah="Give plz";break;
		case 607:nyah="AntoZzz le chinois >:3";break;
		case 608:nyah="Oh j'en ai assez...Je me casse...";break;
		case 609:nyah="Le sadique s'est encore amusé avec moi :c";break;
		case 610:nyah="J'ai trop joué avec ça...je suis toute sale D:";break;
		case 611:nyah=":r J'arrive pas à le retirer je l'ai glissé tout au fond D:";break;
		case 612:nyah="Ma banane est malade D:";break;
		case 613:nyah="Elle a noirci...j'ai peur maman :c";break;
		case 614:nyah="Qui peut m'aider à la rendre plus dur ?";break;
		case 615:nyah="Je veux être plus fort maman !";break;
		case 616:nyah="Ze veux de ton attention Maître >w<";break;
		case 617:nyah="Ze veux de ton attention Maîtresse >w<";break;
		case 618:nyah="Ze te donne de gros câlins <3";break;
		case 619:nyah="*Pat pat pat* :3";break;
		case 620:nyah="AntoZzz est un pédophile qui aime les tokens , il les garde comme des petits enfants...";break;
		case 621:nyah="AntoZzz et Tacos aime se battre entre eux";break;
		case 622:nyah="AntoZzz a bouffé Tacos un jour :o";break;
		case 623:nyah="Vape c'est bien...Neko c'est encore mieux en plus pervy ;3 !";break;
		case 624:nyah="TwitSander a demandé 1000% de bonus d'xp sur Hazonia ! Applaudissez le :D !";break;
		case 625:nyah="J'aime pas les chinois";break;
		case 626:nyah="Le riz c'est dégueu";break;
		case 627:nyah="Fire la POUTINE LEGENDAIRE";break;
		case 628:nyah="AntoZzz code avec son cul mais c'est un bon chinois :3";break;
		case 629:nyah="AntoZzz créé des bugs mais travaille bien :3";break;
		case 630:nyah="Whoaaaaaaa c'est quoi cette reach ?";break;
		case 631:nyah="Le tacos est légendaire (C'est faux)";break;
		case 632:nyah="AntoZzz est chinois, mais ne sais pas faire du riz de bonne qualité...";break;
		case 633:nyah="Montre moi ton NYAAAAAW";break;
		case 634:nyah=":r Et toi, oui toi la, send des nyaw ^^";break;
		case 635:nyah="Traffic d'objets sale, pas venir !";break;
		case 636:nyah="Tacos et AntoZzz se chammaillent comme des frères...mais sous la couette c'est autre chose ;3";break;
		case 637:nyah="à force de s'embêter, AntoZzz et Tacos ont commencés à se rapprocher dangereusement :o";break;
		case 638:nyah="Fire...à force d'observer AntoZzz et Tacos si proches...s'est rapproché de leurs trucs sale...";break;
		case 639:nyah="Tacos est parti démouler un de ces trucs bizarres dans sa cave...y a encore des cris...";break;
		case 640:nyah="Sucer des glands ne fera pas de toi un écureuil ! #Jiessel";break;
		case 641:nyah="Le tacos se fait souvent remettre a l'ordre par ses Maîtres sadiques ;3";break;
		case 642:nyah="On peut reconnaître Tacos à ses tâches de sauce blanche sur le coin de la bouche :o";break;
		case 643:nyah="Tacos demande tous les soirs de la sauce blanche à son confident AntoZzz :3";break;
		case 644:nyah="Delxer se croit le maître mais finit souvent sous le bureau de l'élève";break;
		case 645:nyah="ça envoie des nude en pv oulà";break;
		case 646:nyah="AntoZzz aime le riz...Tacos aime bouffer son riz avec son soumis Fury quand ils sont ensemble ;3";break;
		case 647:nyah="AntoZzz a dominé le Tacos lors de la fête du riz";break;
		case 648:nyah="Ma soumise a été très gentille aujourd'hui, elle a droit à sa récompense :3";break;
		case 649:nyah="J'ai torturé mon soumis pendant toute la soirée >:D";break;
		case 650:nyah="Ne miaule pas !";break;
		
		case -1:nyah="";break;
		}
		
		if (Math.random()<0.1)
			checkXp(rand);
		
		return nyah;
	}	

	public static void displayAn() {
		try {
			URL url = new URL("http://neko.alwaysdata.net/controler/Neko/an.html");
			Scanner sc = new Scanner(url.openStream());
			ArrayList<String> s = new ArrayList<>();
			String l;
			String cl="";
			try {
					while ((l = sc.nextLine()) != null) {
						if (!l.equalsIgnoreCase("")) {
							s.add(l);
							cl+=l+"\n";
						}
					}
			} catch (Exception e) {}
			int k=0;
			for (int i=0;i<s.size();i++) {
				if (s.get(i).startsWith("..")) {
					ChatUtils c = new ChatUtils();
					c.doCommand(s.get(i).replaceFirst("..", Client.getNeko().prefixCmd));
				} else if (s.get(i).startsWith("if")) {									
					String sr[] = s.get(i).split(" ");
					if (s.get(i).startsWith("if $serv ") && sr[3].equalsIgnoreCase("bonus") && (mc.isSingleplayer() || InetAddress.getByName(mc.getCurrentServerData().serverIP).getHostAddress()!=InetAddress.getByName(sr[2]).getHostAddress())) {
						String serv = sr[2];
						int sec = Integer.parseInt(sr[5]);
						double bon = Double.parseDouble(sr[4]);
						Conditions c = Conditions.getInstance();
						c.newActif(serv, sec, bon);
					}
					try {
						if (sr[1].equalsIgnoreCase("$player")) {
							String user;
							if (MCLeaks.isAltActive()) {
								user=MCLeaks.getMCName();
							} else {
								user=mc.session.getUsername();
							}
							if (user.equalsIgnoreCase(sr[2]) || Irc.getInstance().getNamePlayer().equalsIgnoreCase(sr[2])) {
								String r="";
								for (int m=3;m<sr.length;m++) {
									r+=sr[m] + " ";
								}
								if (r.startsWith("..")) {
									ChatUtils c = new ChatUtils();
									r = r.replaceFirst("..", Client.getNeko().prefixCmd);
									r = r.replaceAll("!!ver", Client.getNeko().CLIENT_VERSION);
									c.doCommand(r.replaceAll("!!player", user));
								} else if (r.startsWith("bonus")) {
									String var[] = r.replaceFirst("bonus ", "").split(" ");
									int sec = Integer.parseInt(var[1]);
									double bon = Double.parseDouble(var[0]);
									Client.getNeko().tempBonus=bon;
									TempBon t = new TempBon(sec);	
									if (bon>0)
										Utils.addChat("§aBonus cadeau de §d"+Math.round(bon)+"% §aajouté :3 !");
									else 
										Utils.addChat("§cMalus cadeau de §d"+Math.round(bon)+"% §cajouté >:3 !");
								} else {
									r = r.replaceAll("!!ver", Client.getNeko().CLIENT_VERSION);
									r = r.replaceAll("!!player", user);
									Utils.addChat(r);
								}
							}
						} else if (sr[1].equalsIgnoreCase("!$player")) {
							String user;
							if (MCLeaks.isAltActive()) {
								user=MCLeaks.getMCName();
							} else {
								user=mc.session.getUsername();
							}
							if (!user.equalsIgnoreCase(sr[2]) || !Irc.getInstance().getNamePlayer().equalsIgnoreCase(sr[2])) {
								String r="";
								for (int m=3;m<sr.length;m++) {
									r+=sr[m] + " ";
								}
								if (r.startsWith("..")) {
									ChatUtils c = new ChatUtils();
									r = r.replaceFirst("..", Client.getNeko().prefixCmd);
									r = r.replaceAll("!!ver", Client.getNeko().CLIENT_VERSION);
									c.doCommand(r.replaceAll("!!player", user));
								} else if (r.startsWith("bonus")) {
									String var[] = r.replaceFirst("bonus ", "").split(" ");
									int sec = Integer.parseInt(var[1]);
									double bon = Double.parseDouble(var[0]);
									Client.getNeko().tempBonus=bon;
									TempBon t = new TempBon(sec);
									if (bon>0)
										Utils.addChat("§aBonus cadeau de §d"+Math.round(bon)+"% §aajouté :3 !");
									else 
										Utils.addChat("§cMalus cadeau de §d"+Math.round(bon)+"% §cajouté >:3 !");
								} else {
									r = r.replaceAll("!!ver", Client.getNeko().CLIENT_VERSION);
									r = r.replaceAll("!!player", user);
									Utils.addChat(r);
								}
							}
						} else if (sr[1].equalsIgnoreCase("$serv")) {
							String user;
							if (MCLeaks.isAltActive()) {
								user=MCLeaks.getMCName();
							} else {
								user=mc.session.getUsername();
							}
							String serv="";
							if (!mc.isSingleplayer())
								serv=InetAddress.getByName(mc.getCurrentServerData().serverIP).getHostAddress();
							if (serv.equalsIgnoreCase(InetAddress.getByName(sr[2]).getHostAddress())) {
								String r="";
								for (int m=3;m<sr.length;m++) {
									r+=sr[m] + " ";
								}
								if (r.startsWith("..")) {
									ChatUtils c = new ChatUtils();
									r = r.replaceFirst("..", Client.getNeko().prefixCmd);
									r = r.replaceAll("!!ver", Client.getNeko().CLIENT_VERSION);
									c.doCommand(r.replaceAll("!!player", user));
								} else if (r.startsWith("bonus")) {
									String var[] = r.replaceFirst("bonus ", "").split(" ");
									int sec = Integer.parseInt(var[1]);
									double bon = Double.parseDouble(var[0]);
									Client.getNeko().tempBonus=bon;
									TempBon t = new TempBon(sec);
									if (bon>0)
										Utils.addChat("§aBonus cadeau de §d"+Math.round(bon)+"% §aajouté :3 !");
									else 
										Utils.addChat("§cMalus cadeau de §d"+Math.round(bon)+"% §cajouté >:3 !");
								} else {
									r = r.replaceAll("!!ver", Client.getNeko().CLIENT_VERSION);
									r = r.replaceAll("!!player", user);
									Utils.addChat(r);
								}
							}
						} else if (sr[1].equalsIgnoreCase("!$serv")) {
							String user;
							if (MCLeaks.isAltActive()) {
								user=MCLeaks.getMCName();
							} else {
								user=mc.session.getUsername();
							}
							String serv="";
							if (!mc.isSingleplayer())
								serv=InetAddress.getByName(mc.getCurrentServerData().serverIP).getHostAddress();
							if (!serv.equalsIgnoreCase(InetAddress.getByName(sr[2]).getHostAddress())) {
								String r="";
								for (int m=3;m<sr.length;m++) {
									r+=sr[m] + " ";
								}
								if (r.startsWith("..")) {
									ChatUtils c = new ChatUtils();
									r = r.replaceFirst("..", Client.getNeko().prefixCmd);
									r = r.replaceAll("!!ver", Client.getNeko().CLIENT_VERSION);
									c.doCommand(r.replaceAll("!!player", user));
								} else if (r.startsWith("bonus")) {
									String var[] = r.replaceFirst("bonus ", "").split(" ");
									int sec = Integer.parseInt(var[1]);
									double bon = Double.parseDouble(var[0]);
									Client.getNeko().tempBonus=bon;
									TempBon t = new TempBon(sec);
									if (bon>0)
										Utils.addChat("§aBonus cadeau de §d"+Math.round(bon)+"% §aajouté :3 !");
									else 
										Utils.addChat("§cMalus cadeau de §d"+Math.round(bon)+"% §cajouté >:3 !");
								} else {
									r = r.replaceAll("!!ver", Client.getNeko().CLIENT_VERSION);
									r = r.replaceAll("!!player", user);
									Utils.addChat(r);
								}
							}
						} else if (sr[1].equalsIgnoreCase("$ver")) {
							String user;
							if (MCLeaks.isAltActive()) {
								user=MCLeaks.getMCName();
							} else {
								user=mc.session.getUsername();
							}
							if (Client.getNeko().CLIENT_VERSION.equalsIgnoreCase(sr[2])) {
								String r="";
								for (int m=3;m<sr.length;m++) {
									r+=sr[m] + " ";
								}
								if (r.startsWith("..")) {
									ChatUtils c = new ChatUtils();
									r = r.replaceFirst("..", Client.getNeko().prefixCmd);
									r = r.replaceAll("!!player", user);
									c.doCommand(r.replaceAll("!!ver", Client.getNeko().CLIENT_VERSION));
								} else if (r.startsWith("bonus")) {
									String var[] = r.replaceFirst("bonus ", "").split(" ");
									int sec = Integer.parseInt(var[1]);
									double bon = Double.parseDouble(var[0]);
									Client.getNeko().tempBonus=bon;
									TempBon t = new TempBon(sec);
									if (bon>0)
										Utils.addChat("§aBonus cadeau de §d"+Math.round(bon)+"% §aajouté :3 !");
									else 
										Utils.addChat("§cMalus cadeau de §d"+Math.round(bon)+"% §cajouté >:3 !");
								} else {
									r = r.replaceAll("!!ver", Client.getNeko().CLIENT_VERSION);
									r = r.replaceAll("!!player", user);
									Utils.addChat(r);
								}
							}
						} else if (sr[1].equalsIgnoreCase("$ver")) {
							String user;
							if (MCLeaks.isAltActive()) {
								user=MCLeaks.getMCName();
							} else {
								user=mc.session.getUsername();
							}
							if (!Client.getNeko().CLIENT_VERSION.equalsIgnoreCase(sr[2])) {
								String r="";
								for (int m=3;m<sr.length;m++) {
									r+=sr[m] + " ";
								}
								if (r.startsWith("..")) {
									ChatUtils c = new ChatUtils();
									r = r.replaceFirst("..", Client.getNeko().prefixCmd);
									r = r.replaceAll("!!player", user);
									c.doCommand(r.replaceAll("!!ver", Client.getNeko().CLIENT_VERSION));
								} else if (r.startsWith("bonus")) {
									String var[] = r.replaceFirst("bonus ", "").split(" ");
									int sec = Integer.parseInt(var[1]);
									double bon = Double.parseDouble(var[0]);
									Client.getNeko().tempBonus=bon;
									TempBon t = new TempBon(sec);
									if (bon>0)
										Utils.addChat("§aBonus cadeau de §d"+Math.round(bon)+"% §aajouté :3 !");
									else 
										Utils.addChat("§cMalus cadeau de §d"+Math.round(bon)+"% §cajouté >:3 !");
								} else {
									r = r.replaceAll("!!ver", Client.getNeko().CLIENT_VERSION);
									r = r.replaceAll("!!player", user);
									Utils.addChat(r);
								}
							}
						} else {}
					} catch (Exception e) {}
				} else if (s.get(i).startsWith("bonus")) {
					String var[] = s.get(i).replaceFirst("bonus ", "").split(" ");
					int sec = Integer.parseInt(var[1]);
					double bon = Double.parseDouble(var[0]);
					Client.getNeko().tempBonus=bon;
					TempBon t = new TempBon(sec);
					if (bon>0)
						Utils.addChat("§aBonus cadeau de §d"+Math.round(bon)+"% §aajouté :3 !");
					else 
						Utils.addChat("§cMalus cadeau de §d"+Math.round(bon)+"% §cajouté >:3 !");
				} else {
					String user;
					if (MCLeaks.isAltActive()) {
						user=MCLeaks.getMCName();
					} else {
						user=mc.session.getUsername();
					}
					String sr = s.get(i);
					sr = sr.replaceAll("!!player", user);
					sr = sr.replaceAll("!!ver", var.CLIENT_VERSION);
					Utils.addChat(sr);
				}
			}
		} catch (Exception e) {}
	}

}
	

class upLvl implements ActionListener {
	Minecraft mc = Minecraft.getMinecraft();
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (Utils.lvl==200) {
			Utils.lvl++;		
			
			// Animation du lvl up
			if (!Client.getNeko().animation)
				Utils.t.stop();
			int randy = (int) Math.round(Math.random()*3);
			if (Utils.verif==null) {
				switch (randy) {
					case 0:mc.thePlayer.playSound("mob.cat.purr", 1.0F, 1.0F);break;
					case 1:mc.thePlayer.playSound("mob.cat.purreow", 1.0F, 1.0F);break;
					case 2:mc.thePlayer.playSound("mob.cat.meow", 1.0F, 1.0F);break;
				}
			}
			double i;
			double j;
			double neko;									
			double nekoN;	
			double n;
			double nekoY;
			
			if (Utils.verif==null) {
				neko = -10+Math.random()*20.3;									
				nekoN = -10+Math.random()*20.6;	
				n = Math.random()-0.25;
				nekoY = Math.random()*1.15+n;
				mc.theWorld.spawnParticle(EnumParticleTypes.LAVA, mc.thePlayer.posX+neko, mc.thePlayer.posY+nekoY, mc.thePlayer.posZ+nekoN, 0, 0, 0, 0);
				neko = -10+Math.random()*20.3;									
				nekoN = -10+Math.random()*20.6;	
				n = Math.random()-0.25;
				nekoY = Math.random()*1.15+n;
				mc.theWorld.spawnParticle(EnumParticleTypes.FLAME, mc.thePlayer.posX+neko, mc.thePlayer.posY+nekoY, mc.thePlayer.posZ+nekoN, 0, 0, 0, 0);
				neko = -10+Math.random()*20.3;									
				nekoN = -10+Math.random()*20.6;	
				n = Math.random()-0.25;
				nekoY = Math.random()*1.15+n;
				mc.theWorld.spawnParticle(EnumParticleTypes.DRIP_WATER, mc.thePlayer.posX+neko, mc.thePlayer.posY+nekoY, mc.thePlayer.posZ+nekoN, 0, 0, 0, 0);
				neko = -10+Math.random()*20.3;									
				nekoN = -10+Math.random()*20.6;	
				n = Math.random()-0.25;
				nekoY = Math.random()*1.15+n;
				mc.theWorld.spawnParticle(EnumParticleTypes.REDSTONE, mc.thePlayer.posX+neko, mc.thePlayer.posY+nekoY, mc.thePlayer.posZ+nekoN, 0, 0, 0, 0);
			}			
			
			
		} else {
			
			Utils.lvl=0;
			Utils.t.stop();
		}
		
	}
	
}
