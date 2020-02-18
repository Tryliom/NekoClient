package neko.utils;

import java.awt.Color;
import java.awt.Desktop;
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
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.Scanner;
import java.util.Vector;
import java.util.function.Consumer;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.net.ssl.HttpsURLConnection;

import org.apache.commons.codec.binary.Base64;
import org.darkstorm.minecraft.gui.theme.simple.SimpleTheme;
import org.lwjgl.input.Keyboard;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.ibm.icu.util.StringTokenizer;
import com.mojang.authlib.Agent;
import com.mojang.authlib.UserAuthentication;
import com.mojang.authlib.exceptions.AuthenticationException;
import com.mojang.authlib.yggdrasil.YggdrasilAuthenticationService;

import neko.Client;
import neko.api.NekoCloud;
import neko.gui.GuiAltManager;
import neko.gui.GuiWikiMenu;
import neko.gui.GuiXrayManager;
import neko.gui.InGameGui;
import neko.gui.bindmanager.GuiBindManager;
import neko.guicheat.clickgui.ClickGUI;
import neko.guicheat.clickgui.Panel;
import neko.guicheat.clickgui.settings.Setting;
import neko.lock.Lock;
import neko.manager.ModuleManager;
import neko.manager.QuestManager;
import neko.manager.TutoManager;
import neko.module.Category;
import neko.module.Module;
import neko.module.modules.combat.AutoClic;
import neko.module.modules.combat.AutoPot;
import neko.module.modules.combat.Autosoup;
import neko.module.modules.combat.BowAimbot;
import neko.module.modules.combat.ClickAim;
import neko.module.modules.combat.Fastbow;
import neko.module.modules.combat.KillAura;
import neko.module.modules.combat.Reach;
import neko.module.modules.combat.Regen;
import neko.module.modules.combat.SmoothAim;
import neko.module.modules.combat.Trigger;
import neko.module.modules.hide.Friends;
import neko.module.modules.hide.God;
import neko.module.modules.hide.Lot;
import neko.module.modules.hide.Plugins;
import neko.module.modules.misc.Antiafk;
import neko.module.modules.misc.AutoCmd;
import neko.module.modules.misc.AutoMLG;
import neko.module.modules.misc.CallCmd;
import neko.module.modules.misc.Nameprotect;
import neko.module.modules.misc.Phase;
import neko.module.modules.misc.Ping;
import neko.module.modules.misc.Register;
import neko.module.modules.misc.Timer;
import neko.module.modules.movements.Dolphin;
import neko.module.modules.movements.Flight;
import neko.module.modules.movements.Freecam;
import neko.module.modules.movements.Glide;
import neko.module.modules.movements.Highjump;
import neko.module.modules.movements.Longjump;
import neko.module.modules.movements.NoClip;
import neko.module.modules.movements.Speed709;
import neko.module.modules.movements.Step;
import neko.module.modules.params.Gui;
import neko.module.modules.params.HUD;
import neko.module.modules.player.Autoarmor;
import neko.module.modules.player.Build;
import neko.module.modules.player.Cheststealer;
import neko.module.modules.player.Fasteat;
import neko.module.modules.player.Fire;
import neko.module.modules.player.Nuker;
import neko.module.modules.player.PushUp;
import neko.module.modules.player.Velocity;
import neko.module.modules.render.ItemESP;
import neko.module.modules.render.NekoChat;
import neko.module.modules.render.Paint;
import neko.module.modules.render.Power;
import neko.module.modules.render.Radar;
import neko.module.modules.render.Search;
import neko.module.modules.render.Tracers;
import neko.module.modules.render.Wallhack;
import neko.module.modules.render.Water;
import neko.module.modules.render.WorldTime;
import neko.module.modules.render.Xray;
import neko.module.modules.special.DropShit;
import neko.module.modules.special.FastDura;
import neko.module.modules.special.FireTrail;
import neko.module.modules.special.ForceTP;
import neko.module.modules.special.Likaotique;
import neko.module.modules.special.Magnet;
import neko.module.modules.special.Nausicaah;
import neko.module.modules.special.Near;
import neko.module.modules.special.PunKeel;
import neko.module.modules.special.Pyro;
import neko.module.modules.special.Reflect;
import neko.module.modules.special.TpBack;
import neko.module.modules.special.VanillaTp;
import neko.module.other.Active;
import neko.module.other.Conditions;
import neko.module.other.Irc;
import neko.module.other.Necklace;
import neko.module.other.Quest;
import neko.module.other.Rank;
import neko.module.other.TempBon;
import neko.module.other.Xp;
import neko.module.other.enums.BowMode;
import neko.module.other.enums.Chat;
import neko.module.other.enums.IrcMode;
import neko.module.other.enums.Rate;
import neko.module.other.enums.SpeedEnum;
import net.mcleaks.MCLeaks;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.multiplayer.GuiConnecting;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.monster.EntityWitch;
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
import net.minecraft.network.play.client.C02PacketUseEntity;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.network.play.client.C0BPacketEntityAction;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Session;
import net.minecraft.util.Vec3;
import net.minecraft.world.WorldSettings.GameType;

/**
 * Utilité de cette classe:<br>
 * Sert pour les méthodes pratiques utilisées partout et variables
 */	
public class Utils {
	public static boolean spectator;
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
	public static String currServ = "";
	// Heures
	public static boolean h1=true;
	public static boolean h10=true;
	public static boolean h50=true;
	public static boolean h100=true;
	public static boolean h200=true;
	public static boolean h666=true;
	// Heures
	public static boolean cfg = false;
	public static String server;
	public static boolean n=false;
	public static int nbPack=0;
	public static boolean sword=false;
	public static int lvl=0;
	public static boolean changeRank=true;
	public static int limit=200;
	public static boolean limite=false;
	public static String version="Neko";
	public static int kills=0;
	public static int R=199;
	public static int G=255;
	public static int B=213;
	public static double autoMineBlockLimit = 13;
	public static String linkSave = mc.isRunningOnMac ? System.getProperty("user.home") + "/Library/Application Support/minecraft" : System.getenv("APPDATA") + "\\GoodNight_4\\config\\audio\\rpg\\";
	public static String separator = mc.isRunningOnMac ? "/" : "\\";
	public static String sep = "§8§m--------------------------------------";
	public static String sep2 = "§8§m--------------";
	public static String sep3 = "§8§m------------------------";
	public static Client var = Client.getNeko();
	public static Vector<String> ipVote = new Vector<String>();
	public static int xptime = 0;
	public static NekoCloud nc = NekoCloud.getNekoAPI();
	public static boolean admin = false;
	public static Color colorGui = new Color(110, 110, 200, 100);
	public static Color colorFontGui = new Color(200, 200, 200, 255);
	public static int xrayBlockOpacity = 100;
	public static ArrayList<String> xrayBlocks = new ArrayList<String>();
	public static String claimLog = "";
	public static String ignoreFaction = "";
	public static String chatRegex = "";
	public static boolean chatCC = false;
	public static Thread currentClaimFinder = null;
	public static String claimFinderBar = "";
	public static String rinaorcVerifNumber = "";
	public static String currentIP = "";
	
	// Invoc
	public static String pyroStr = "HydpQjxBisg4yq6h3V4OB5StgENvLH5Gq94MQdXxXzTxFt/P3KnTXp0ZYxFwlJBmVrLyQs4iFcvTp42uuOccbFpO8jxSdfbifbiKGxR5eig=";
	public static String choumStr = "1q3apxmv3J26jvvAFNDb3K4rHAOU4+n2CEv60RfJ0SClpW5mUpcBlts7uAWQaBypXlfgGgrvzYnUkad3bGWlgvZfoOBdgTNgvxbYySTporIoJa7IgFQHRAMLAT3uHkAe";
	
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
	 * Sert à afficher une ligne de texte dans le chat suivant différent paramètres, avec le [Neko] devant
	 * @param txt 			Texte à afficher
	 * @param cmd 			Commande à executer si action == Chat.Click
	 * @param desc 			Description du hover
	 * @param onlyHover 	Pour détérminer si c'est juste afficher une description sans commandes
	 * @param action 		Détérmine l'action entre Click, Summon (Sans executé) ou Link (Ouvre un lien internet)
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
	
	/**
	 * Met dans le chat un "Erreur [?]" avec le [?] en hover pour afficher la raison, les deux en rouge
	 * @param reason	Raison de l'erreur
	 */
	public static void addError(String reason) {
		mc.ingameGUI.getChatGUI().printChatMessage(new ChatComponentText(getNeko()+"§cErreur ").appendSibling(getHoverText("§8[§9?§8]", "§c"+reason)));
	}
	
	/**
	 * Retourne le "[Neko] " en couleur
	 * @return	String en fin de ligne couleur orange §6
	 */
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
		if (verif==null && display) {
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
	
	
	
	public static boolean createAllFolderSave() {
		Consumer<File> check = (File f) -> {
			if (!f.exists()) {
				f.mkdirs();
			}
		};
		check.accept(new File(System.getenv("APPDATA") + "\\GoodNight_4"));
		check.accept(new File(System.getenv("APPDATA") + "\\GoodNight_4\\config"));
		check.accept(new File(System.getenv("APPDATA") + "\\GoodNight_4\\config\\settings"));
		check.accept(new File(System.getenv("APPDATA") + "\\GoodNight_4\\config\\audio"));
		check.accept(new File(System.getenv("APPDATA") + "\\GoodNight_4\\config\\video"));
		check.accept(new File(System.getenv("APPDATA") + "\\GoodNight_4\\game"));
		check.accept(new File(System.getenv("APPDATA") + "\\GoodNight_4\\game\\app"));
		check.accept(new File(System.getenv("APPDATA") + "\\GoodNight_4\\picture"));
		check.accept(new File(System.getenv("APPDATA") + "\\GoodNight_4\\config\\audio\\rpg"));
		check.accept(new File(System.getenv("APPDATA") + "\\GoodNight_4\\config\\audio\\rpg\\Config"));
		
		return true;
	}
	

	public static void tranferAllData() {		
		boolean valid = true;
		Consumer<String> check = (String cfgName) -> {
			File f = new File(System.getenv("APPDATA") + "\\GoodNight_4\\config\\audio\\rpg\\Config\\"+cfgName);
			if (!f.exists()) {
				f.mkdirs();
			}
			try {
				Runtime.getRuntime().exec("cmd.exe /c copy /y "+System.getenv("APPDATA") + "\\.minecraft\\Neko\\Config\\"+cfgName+"\\* "
						+System.getenv("APPDATA") + "\\GoodNight_4\\config\\audio\\rpg\\Config\\"+cfgName+"\\");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		};
		if (new File(System.getenv("APPDATA") + "\\.minecraft\\Neko\\").exists())
			try {
				Runtime.getRuntime().exec("cmd.exe /c copy /y "+System.getenv("APPDATA") + "\\.minecraft\\Neko\\* "
						+ ""+System.getenv("APPDATA") + "\\GoodNight_4\\config\\audio\\rpg\\");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		
		File conf = new File(System.getenv("APPDATA") + "\\.minecraft\\Neko\\Config");
		if (conf.exists()) {
			for (String s : conf.list()) {
				check.accept(s);
			}
		}
		
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
	/**
	 * ça ne dit pas dans le chat que le rang est gagné
	 * @param rang
	 * @return
	 */
	public static boolean setRank(String rang) {
		for (Rank r : ModuleManager.rang) {
			if (r.getName().equalsIgnoreCase(rang)) {
				if (changeRank)
					var.rang=r;
				r.setLvl(r.getLvl()+1);
				r.setLock(false);
				checkRang();
				return true;
			}
		}
		return false;
	}
	
	public static Module getModuleWithInt(int nb) {
		int i = 0;
		for (Module m : ModuleManager.ActiveModule) {
			if (i==nb && (m.getCategory() != Category.HIDE || m.getName().equalsIgnoreCase("GUI"))) {
				return m;
			}
			if (m.getCategory() != Category.HIDE)
				i++;
		}
		return null;
	}
	
	public static boolean canEscape(GuiScreen actual) {
		if (actual instanceof GuiBindManager) {
			return false;
		}
		if (actual instanceof GuiWikiMenu.GuiWikiPart) {
			return false;
		}		
		if (actual instanceof GuiXrayManager) {
			return false;
		}
		return true;
	}
	
	public static int getTotModule() {
		int i = 0;
		for (Module m : ModuleManager.ActiveModule) {
			if (m.getCategory() != Category.HIDE) {
				i++;
			}
		}
		return i;
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
	
	public static Lock getLock2(String s) {
		for (Lock r : ModuleManager.Lock) {
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
	
	public static boolean changeNecklace(String necklace) {
		for (Necklace r : ModuleManager.necklace) {
			if (r.getName().equalsIgnoreCase(necklace) && !r.isLock()) {
				var.necklace=r;
				return true;
			}
		}
		return false;
	}
	
	public static String getNecklaceColor(String s) {
		for (Necklace r : ModuleManager.necklace) {
			if (r.getName().equalsIgnoreCase(s)) {
				return r.getColor();
			}
		}
		return "§f";
	}
	
	public static Necklace getNecklace(String s) {
		for (Necklace r : ModuleManager.necklace) {
			if (r.getName().equalsIgnoreCase(s)) {
				return r;
			}
		}
		return null;
	}
	
	public static int getNbNecklaceUnlock() {
		int i=0;
		for (Necklace r : ModuleManager.necklace) {
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
	
	public static float getDistanceToBlock(BlockPos b) {
		EntityWitch en = new EntityWitch(mc.theWorld);
		en.setPosition(b.getX(), b.getY(), b.getZ());
		
		return mc.thePlayer.getDistanceToEntity(en);
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
				addChat2(setColor(lock.getName().replaceAll("--", var.prefixCmd), "§6"), "", "§6Description: §7"+setColor(lock.getDesc().replaceAll("--", var.prefixCmd), "§7")+"\n"
						+ "§6Type: §7"+lock.getType()+"\n"+"§cCoût: §7"+(lock.getUnit().equalsIgnoreCase("???") ? "???" : lock.getCout()+" "+lock.getUnit())+"\n"+(lock.isLock() ? "§cBloqué" : "§aDébloqué"), true, Chat.Click);
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
	
    public static boolean shouldChat(Module module) {
        if(module.getClass().equals(Friends.class)
                || module.getClass().equals(Gui.class)
                || module.getClass().equals(Lot.class)
                || module.getClass().equals(Plugins.class)
                || module.getClass().equals(Register.class)
                || module.getClass().equals(Render.class)
                || module.getClass().equals(VanillaTp.class)
                || !Utils.display
                || Utils.isLock(module.getName())) return false;
        return true;
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
	
	/**
	 * Sert à vérifier si une ip mc est présente dans une liste d'ip mc mais qu'ils ont une adresse différente mais qui mène au même endroit.
	 * @param list	Vector de String, liste d'ip mc
	 * @param s		IP mc à vérifier
	 * @return		True si l'ip est déjà présente dans la liste et False si elle n'y est pas
	 */
	public static Boolean isSameServerIP(Vector<String> list, String s) {
		InetAddress ia = null;
		try {
			ia = InetAddress.getByName(s);
		} catch (Exception e) {}
		
		if (ia==null) {
			return false;
		} else {
			for (String r : list) {
				InetAddress ria = null;
				try {
					ria = InetAddress.getByName(r);
					if (ria.getHostAddress().equalsIgnoreCase(ia.getHostAddress())) {
						return true;
					}						
				} catch (Exception e) {}
			}
		}
		
		return false;
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
		}	

	}
	
	public static void launchConnect(final ServerData sd) {
		new Thread(new Runnable() {			
			@Override
			public void run() {
				try {
					try {
		                mc.theWorld.sendQuittingDisconnectingPacket();
		                mc.loadWorld((WorldClient)null);		                
	            	} catch (Exception e) {}
					if (!mc.isSingleplayer()) {
						try {
							GuiConnecting.networkManager.getNetHandler().onDisconnect(new ChatComponentText(""));
						} catch (Exception e) {}
						try {
							GuiConnecting.networkManager.closeChannel(null);
						} catch (Exception e) {}
						try {
							GuiConnecting.networkManager.getNetHandler().setDisconnected(true);
						} catch (Exception e) {}
					}
					try {
						this.wait(200);
					} catch (InterruptedException e) {}
					mc.displayGuiScreen(new GuiConnecting(new GuiMultiplayer(new GuiMainMenu()), mc, sd));
				} catch (Exception e) {
					mc.displayGuiScreen(null);
				}
			}
		}).start();
	}
	
	public static void faceEntity(EntityLivingBase entity) {
    	mc.thePlayer.rotationPitch=((float)(mc.thePlayer.rotationPitch + getPitchChange(entity) / (1+1*Math.random())));
    	mc.thePlayer.rotationYaw=((float)(mc.thePlayer.rotationYaw + getYawChange(entity) / (1+1*Math.random())));
	}
    
	 public static float getPitchChange(Entity entity)
	  {
	    double deltaX = entity.posX - mc.thePlayer.posX;
	    double deltaZ = entity.posZ - mc.thePlayer.posZ;
	    double deltaY = entity.posY - 2.2D + entity.getEyeHeight() - mc.thePlayer.posY;
	    double distanceXZ = MathHelper.sqrt_double(deltaX * deltaX + deltaZ * deltaZ);
	    double pitchToEntity = -Math.toDegrees(Math.atan(deltaY / distanceXZ));
	    return -MathHelper.wrapAngleTo180_float(mc.thePlayer.rotationPitch - (float)pitchToEntity) - 
	      2.5F;
	  }
	  
	  public static float getYawChange(Entity entity)
	  {
	    double deltaX = entity.posX - mc.thePlayer.posX;
	    double deltaZ = entity.posZ - mc.thePlayer.posZ;
	    double yawToEntity = 0.0D;
	    if ((deltaZ < 0.0D) && (deltaX < 0.0D)) {
	      yawToEntity = 90.0D + Math.toDegrees(Math.atan(deltaZ / deltaX));
	    } else if ((deltaZ < 0.0D) && (deltaX > 0.0D)) {
	      yawToEntity = -90.0D + Math.toDegrees(Math.atan(deltaZ / deltaX));
	    } else {
	      yawToEntity = Math.toDegrees(-Math.atan(deltaX / deltaZ));
	    }
	    return MathHelper.wrapAngleTo180_float(-(mc.thePlayer.rotationYaw - (float)yawToEntity));
	  }
	
	public static void faceBowEntityClient(Entity target)
	  {
	    int bowCharge = mc.thePlayer.getItemInUseCount();
	    float velocity = ((Utils.isToggle("Fastbow") || Utils.isToggle("Fastbow") && Fastbow.getFast().isNobow()) ? mc.thePlayer.getItemInUse().getMaxItemUseDuration() : bowCharge) / 20;
	    velocity = (velocity * velocity + velocity * 2.0F) / 3.0F;
	    if (velocity < 0.1D)
	    {
	      if ((target instanceof EntityLivingBase)) {
	        faceEntity((EntityLivingBase) target);
	      }
	      return;
	    }
	    if (velocity > 1.0F) {
	      velocity = 1.0F;
	    }
	    double posX = target.posX + (target.posX - target.prevPosX) * 5.0D - mc.thePlayer.posX;
	    double posY = target.posY + (target.posY - target.prevPosY) * 5.0D + target.getEyeHeight() - 0.15D - mc.thePlayer.posY - mc.thePlayer.getEyeHeight();
	    double posZ = target.posZ + (target.posZ - target.prevPosZ) * 5.0D - mc.thePlayer.posZ;
	    float yaw = (float)(Math.atan2(posZ, posX) * 180.0D / 3.141592653589793D) - 90.0F;
	    double y2 = Math.sqrt(posX * posX + posZ * posZ);
	    float g = 0.006F;
	    float tmp = (float)(velocity * velocity * velocity * velocity - g * (g * (y2 * y2) + 2.0D * posY * (velocity * velocity)));
	    float pitch = (float)-Math.toDegrees(Math.atan((velocity * velocity - Math.sqrt(tmp)) / (g * y2)));
	    mc.thePlayer.rotationYaw = yaw;
	    mc.thePlayer.rotationPitch = pitch;
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
		                	  try {
		                		  enchs=Enchantment.func_180306_c(id).getTranslatedName(lvl);
		                	  } catch (Exception e) {}
		                  } else {
		                	  try {
		                		  enchs+=", "+Enchantment.func_180306_c(id).getTranslatedName(lvl);
		                	  } catch (Exception e) {}
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
            	try {
            		enchs=Enchantment.func_180306_c(id).getTranslatedName(lvl);
            	} catch (Exception e) {}
            } else {
            	try {
            		enchs+=", "+Enchantment.func_180306_c(id).getTranslatedName(lvl);
            	} catch (Exception e) {}
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
	
	public static String getCurrentName() {
		return (MCLeaks.isAltActive() ? MCLeaks.getMCName() : mc.getSession().getUsername());
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
			if (mod.getToggled() && !mod.isCmd())
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
			case 35: return "Umitsu";
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
		addChat(Utils.sep);
		addChat("§9§oHelp "+num+"/"+maxHelp);
		switch (num) {
		case 1 :	
			addChat2("§6"+var.prefixCmd+"Help <Commande>", var.prefixCmd+"help ", "§7Affiche la commande en détail", false, Chat.Summon);
			addChat2("§6"+var.prefixCmd+"Info", var.prefixCmd+"info ", "§7Affiche les caractérisque du Client. Avec le info <player>, affiche les infos du joueur visé", false, Chat.Summon);
			addChat2("§6"+var.prefixCmd+"Keybind", var.prefixCmd+"keybind", "§7Affiche quel cheat est assigné à quel touche", false, Chat.Summon);
			addChat2("§6"+var.prefixCmd+"Option", var.prefixCmd+"option ", "§7Quelques petits gadjets intressants", false, Chat.Summon);
			addChat2("§6"+var.prefixCmd+"Mcleaks <Token>", var.prefixCmd+"mcleaks ", "§7Permet de se connecter à un compte MCLeaks", false, Chat.Summon);
			addChat2("§6"+var.prefixCmd+"Login <User> <Mdp>", var.prefixCmd+" ", "§7Vous connecte à un compte.\n§7Si vous laissez vide le champs du mot de passe, le compte devient un crack", false, Chat.Summon);
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
			addChat2("§6"+var.prefixCmd+"MyPing", var.prefixCmd+"myping", "§7Affiche votre ping réel par rapport au serveur", false, Chat.Summon);
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
			addChat2("§6"+var.prefixCmd+"Detector", var.prefixCmd+"detector", "§7Permet de détécter les joueurs qui sont trop rapides (Cheateurs)", false, Chat.Summon);
			addChat2("§6"+var.prefixCmd+"Version <String>", var.prefixCmd+"version ", "§7Change la version lancée dans les Snooper Settings", false, Chat.Summon);
			addChat2("§6"+var.prefixCmd+"Gm <Id>", var.prefixCmd+"gm ", "§7Change votre gamemode. Si ça échoue vous le serez quand même mais seulement\n§7pour vous afin de pouvoir exploit", false, Chat.Summon);
			addChat2("§6"+var.prefixCmd+"Invsee <Player>", var.prefixCmd+"invsee ", "§7Affiche l'inventaire du joueur noté", false, Chat.Summon);
			addChat2("§6"+var.prefixCmd+"Broadcast", var.prefixCmd+"bc", "§7Affiche les bonus actuelles des serveurs", false, Chat.Summon);
			addChat2("§6"+var.prefixCmd+"Hclip <Int>", var.prefixCmd+"hclip ", "§7Vous téléporte en avant/arrière", false, Chat.Summon);
			addChat2("§6"+var.prefixCmd+"Irc", var.prefixCmd+"help irc", "§7Voir l'help de l'Irc (Cliquez pour afficher)", false, Chat.Summon);
			break;
			
		case 7:
			addChat2("§6"+var.prefixCmd+"ListServ", var.prefixCmd+"listserv", "§7Affiche la liste des serveurs utilisé par les Neko", false, Chat.Summon);
			addChat2("§6"+var.prefixCmd+"Cmd", var.prefixCmd+"cmd ", "§7Permet d'assigner des commandes à des touches\n§7Cmd <Nom> <Touche> <Commande>", false, Chat.Summon);
			addChat2("§6"+var.prefixCmd+"UnBindAll", var.prefixCmd+"unbindall", "§7Met tous les cheats sur la touche 'None'", false, Chat.Summon);
			addChat2("§6"+var.prefixCmd+"Phantom", var.prefixCmd+"phantom", "§7Permet de vous téléporter en passant par le sol", false, Chat.Summon);
			addChat2("§6"+var.prefixCmd+"ListPing", var.prefixCmd+"listping", "§7Affiche le ping de tous les joueurs autour de vous", false, Chat.Summon);
			addChat2("§6"+var.prefixCmd+"LvlUp", var.prefixCmd+"lvlup", "§7Active/désactive l'animation quand on augmente de lvl", false, Chat.Summon);
			addChat2("§6"+var.prefixCmd+"Classement", var.prefixCmd+"Classement", "§7Affiche le top 10 des joueurs de Neko en terme de lvl", false, Chat.Summon);
			addChat2("§6"+var.prefixCmd+"Namemc <Pseudo>", var.prefixCmd+"Namemc ", "§7Affiche l'historique des pseudos de la personne", false, Chat.Summon);
			break;															
			
		case 8:
			addChat2("§6"+var.prefixCmd+"ToChat", var.prefixCmd+"tochat ", "§7Affiche une ligne dans le chat formatée comme on veut avec les couleurs qu'on veut.\n"
					+ "Par ex, pour faire dire une insulte à un joueur fictif ou réél et le faire accuser ;3", false, Chat.Summon);
			addChat2("§6"+var.prefixCmd+"Wear", var.prefixCmd+"wear", "§7Juste en créatif, met l'objet dans notre main sur notre tête", false, Chat.Summon);
			addChat2("§6"+var.prefixCmd+"Rename <Nom>", var.prefixCmd+"rename ", "§7Juste en créatif, renomme l'item en main en ce que vous mettez.\n"
					+ "§7Vous pouvez mettre des couleurs", false, Chat.Summon);
			addChat2("§6"+var.prefixCmd+"Nbt <Tag> <Value>", var.prefixCmd+"nbt ", "§7Ajoute un nbt à l'item, comprend les values int, double, boolean et string.\n"
					+ "§7Exemple simple, rendre incassable un item: "+var.prefixCmd+"nbt Unbreakable true", false, Chat.Summon);
			addChat2("§6"+var.prefixCmd+"Give <ID ou Name>", var.prefixCmd+"give ", "§7Juste en créatif, vous donne l'item que vous avez spécifié avec l'ID ou son nom (minecraft:stone ou 1 fonctionnent)", false, Chat.Summon);
			addChat2("§6"+var.prefixCmd+"Stat <rien ou Global>", var.prefixCmd+"stat", "§7Affiche les stats du joueur qui utilise plus tel cheat", false, Chat.Summon);
			addChat2("§6"+var.prefixCmd+"findclaim <options> (Voir "+var.prefixCmd+"help findclaim)", var.prefixCmd+"findclaim ", "§7", false, Chat.Summon);
//			addChat2("§6"+var.prefixCmd+"", var.prefixCmd+"", "§7", false, Chat.Summon);
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
	
	public static boolean haveInternet() {
		try {
			URL u = new URL("http://www.perdu.com");
			u.openConnection().setConnectTimeout(1000);
		} catch (Exception e) {
			return false;
		}
		return true;
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
	
	public static void crit() {
		if (!mc.thePlayer.onGround)
			return;
		mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(mc.thePlayer.posX, mc.thePlayer.posY + 0.01D*Math.random(), mc.thePlayer.posZ, false));
	    mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ, false));
	}
	//TODO: SaveAll
	public static void saveAll() {
		saveBind();
		saveFriends();
		saveMod();
		saveRpg();
		saveValues();
		saveNuker();
		saveLock();
		saveRank();
		saveIrc();
		saveFrame();
		saveFont();
		saveShit();
		saveCmd();
		saveCloudAlt();
		saveStat();
		saveSettings();
		saveXray();
	}
	
	public static Block getBlockRelativeToEntity(Entity en, double d) {
		return getBlock(new BlockPos(en.posX, en.posY + d, en.posZ));
	}
	
	public static Block getBlock(BlockPos pos) {
		return mc.theWorld.getBlockState(pos).getBlock();
	}
	
	public static BlockPos getBlockPosRelativeToEntity(Entity en, double d) {
		return new BlockPos(en.posX, en.posY + d, en.posZ);
	}
	
	public static IBlockState getBlockState(BlockPos blockPos) {
		return mc.theWorld.getBlockState(blockPos);
	}
	
	public static ArrayList<EntityItem> getNearbyItems(int range) {
		ArrayList<EntityItem> eList = new ArrayList<EntityItem>();
		for (Object o : Minecraft.getMinecraft().theWorld.getLoadedEntityList()) {
			if (!(o instanceof EntityItem)) {
				continue;
			}
			EntityItem e = (EntityItem) o;
			if (Minecraft.getMinecraft().thePlayer.getDistanceToEntity(e) >= range) {
				continue;
			}

			eList.add(e);
		}
		return eList;
	}
	
	public static boolean isBlockPosAir(BlockPos blockPos) {
		return mc.theWorld.getBlockState(blockPos).getBlock().getMaterial() == Material.air;
	}
	
	public static Vec3 getVec3(BlockPos blockPos) {
		return new Vec3(blockPos.getX(), blockPos.getY(), blockPos.getZ());
	}
	
	public static float[] getFacePos(Vec3 vec) {
		double diffX = vec.xCoord + 0.5 - Minecraft.getMinecraft().thePlayer.posX;
		double diffY = vec.yCoord + 0.5
				- (Minecraft.getMinecraft().thePlayer.posY + Minecraft.getMinecraft().thePlayer.getEyeHeight());
		double diffZ = vec.zCoord + 0.5 - Minecraft.getMinecraft().thePlayer.posZ;
		double dist = MathHelper.sqrt_double(diffX * diffX + diffZ * diffZ);
		float yaw = (float) (Math.atan2(diffZ, diffX) * 180.0D / Math.PI) - 90.0F;
		float pitch = (float) -(Math.atan2(diffY, dist) * 180.0D / Math.PI);
		return new float[] {
				Minecraft.getMinecraft().thePlayer.rotationYaw
						+ MathHelper.wrapAngleTo180_float(yaw - Minecraft.getMinecraft().thePlayer.rotationYaw),
				Minecraft.getMinecraft().thePlayer.rotationPitch
						+ MathHelper.wrapAngleTo180_float(pitch - Minecraft.getMinecraft().thePlayer.rotationPitch) };
	}
	
	public static double normalizeAngle(double angle) {
		return (angle + 360) % 360;
	}

	public static float normalizeAngle(float angle) {
		return (angle + 360) % 360;
	}
	
	public static void faceBlock(BlockPos blockPos) {
		facePos(getVec3(blockPos));
	}
	
	public static void faceEntity(Entity en) {
		facePos(new Vec3(en.posX - 0.5, en.posY + (en.getEyeHeight() - en.height / 1.5), en.posZ - 0.5));
	}
	
	public static void facePos(Vec3 vec) {
		double diffX = vec.xCoord + 0.5 - Minecraft.getMinecraft().thePlayer.posX;
		double diffY = vec.yCoord + 0.5
				- (Minecraft.getMinecraft().thePlayer.posY + Minecraft.getMinecraft().thePlayer.getEyeHeight());
		double diffZ = vec.zCoord + 0.5 - Minecraft.getMinecraft().thePlayer.posZ;
		double dist = MathHelper.sqrt_double(diffX * diffX + diffZ * diffZ);
		float yaw = (float) (Math.atan2(diffZ, diffX) * 180.0D / Math.PI) - 90.0F;
		float pitch = (float) -(Math.atan2(diffY, dist) * 180.0D / Math.PI);
		Minecraft.getMinecraft().thePlayer.rotationYaw = Minecraft.getMinecraft().thePlayer.rotationYaw
				+ MathHelper.wrapAngleTo180_float(yaw - Minecraft.getMinecraft().thePlayer.rotationYaw);
		Minecraft.getMinecraft().thePlayer.rotationPitch = Minecraft.getMinecraft().thePlayer.rotationPitch
				+ MathHelper.wrapAngleTo180_float(pitch - Minecraft.getMinecraft().thePlayer.rotationPitch);
	}
	
	public static boolean isModule(String cheat) {
		Boolean b = false;
		for (Module m : ModuleManager.ActiveModule)
			if (m.getName().equalsIgnoreCase(cheat))
				b=true;
		return b;
	}
	
	public static void displayValues(String cheat) {
		if (cheat==null)
			for (Category c : Category.values()) {
				for (Module m : ModuleManager.ActiveModule) {
					if (!m.isCmd() && c!=Category.HIDE && c==m.getCategory() && m.getValues()!=null && !m.getValues().isEmpty()) {
						Utils.addChat2("§7 "+m.getName(), "", m.getValues(), true, Chat.Click);
					}
				}
				if (c!=Category.HIDE)
					Utils.addChat(Utils.sep);
			}
		else {
			for (Module m : ModuleManager.ActiveModule) {
				if (m.getName().equalsIgnoreCase(cheat) && m.getValues()!=null && !m.getValues().isEmpty())
					Utils.addChat2("§7 "+m.getName(), "", m.getValues(), true, Chat.Click);
			}
		}
		if (cheat==null) {
			ModuleManager.values.add(displayBool(sword));
			disV("Sword");
			ModuleManager.values.add(limite ? "§aActivée" : "§cDésactivée");
			ModuleManager.values.add("Limite de paquet: "+limit);
			disV("Limit");
			Irc irc = Irc.getInstance();
			ModuleManager.values.add("Irc mode:§7 "+irc.getMode());
			ModuleManager.values.add("Irc messages join/left:§7 "+(irc.isHideJl() ? "Cachés" : "Affichés"));
			ModuleManager.values.add("Irc playerclic:§7 "+irc.getPClic());
			ModuleManager.values.add("Irc prefix:§7 "+irc.getPrefix());
			disV("Irc");					
			ModuleManager.values.add("Total de bonus ramassé:§7 "+ neko.module.modules.render.Render.bonusCount);
			ModuleManager.values.add("Bonus d'xp permanent:§7 "+ var.bonus+"%");
			ModuleManager.values.add("Bonus d'xp du rang:§7 "+ var.rang.getTotBonus()+"%");
			ModuleManager.values.add("Autre bonus d'xp:§7 "+ var.tempBonus+"%");
			disV("Statistics");
		}
	}
	
	/**
	 * Sert à afficher en hover un texte mis dans la list du ModuleManager.values sur un String donné.<br>
	 * Principalement pour le ..values pour le moment. Pour les values hors cheat
	 * @param title	String donné qui va être affichée
	 */
	public static void disV(String title) {
		String in = "";
		for (String s : ModuleManager.values) {
			in+="§6"+s+"\n";
		}
		in = in.substring(0, in.length()-1);
		Utils.addChat2("§7 "+title, "", in, true, Chat.Click);
		ModuleManager.values.clear();
	}
	
	/**
	 * Sert à afficher le Activé ou désactivé en couleur de paramètres pour les values ou autre
	 * @param b	Boolean testé
	 * @return	String avec la valeur §aActivé si true et §cDésactivé si false
	 */
	public static String displayBool(boolean b) {
		return b ? "§aActivé" : "§cDésactivé";
		
	}
	
	public static String getPan() {
		return verif==null ? "npanorama" : "panorama";
	}
	
	public static double verif(double en, double divi) {
		double res = en/divi;
		if (res < 0) {
			res*=-1;
		}
		return res;
	}
	
	public static BowMode pass(BowMode b) {
		if (b == BowMode.Max) {
			b = BowMode.Min;
		} else if (b == BowMode.Min) {
			b = BowMode.Désactivé;
		} else if (b == BowMode.Désactivé) {
			b = BowMode.Max;
		}
		return b;
	}
	
	public static boolean isInteger(String s) {
		int l = 0;
		try {
			l = Integer.parseInt(s);
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
	
	public static boolean isBoolean(String s) {
		try {
			Boolean b = Boolean.parseBoolean(s);
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
		
		for (Lock lock : ModuleManager.Lock) {
			if (lock.getUnit().equalsIgnoreCase("Lvl") && lock.getCout()<=var.niveau && lock.isLock()) {
				Utils.unlock(lock.getName());
				//Utils.addChat("§d"+lock.getType()+" "+lock.getName().replaceAll("--", var.prefixCmd)+" débloqué"+(lock.getType().equalsIgnoreCase("Commande") ? "e" : "" )+" !");
			}
		}
		
		for (Rank r : ModuleManager.rang) {
			
			if (isLock("--reach pvp") && !r.isLock() && (r.getName().contains("JP") || r.getName().contains("Jean-Pierre"))) {
				addChat("§cReach pvp §adébloquée !");
				unlock("--reach pvp");					
			}
			if (!r.isLock() && r.getName().equalsIgnoreCase("Pyroman") && isLock("Pyro")) {
				addChat("§dPyro débloqué !");
				unlock("Pyro");					
			}
			
			// Supra
			for (Rate rt : Rate.values())
				if (r.getName().equalsIgnoreCase("Supra "+rt.name())) {
					if (Utils.getTotRankRate(rt)==Utils.getTotRankRateUnlock(rt))
						if (r.isLock()) {
							setRank("Supra "+rt.name());
							displayTitle("", "§d§koui§cRang §6Supra "+rt.name()+"§c débloqué !!§d§koui");
						} else if (isAllRateHaveLvl(getRank("Supra "+rt.name()).getLvl()+1, rt)) {
							setRank("Supra "+rt.name());
							addChat("§d§koui§cRang §6Supra "+rt.name()+"§c a atteint le lvl "+getRank("Supra "+rt.name()).getLvl()+" !!§d§koui");
						}
				}
			
			
			if (r.getName().equalsIgnoreCase("Nyaaw Mythique")) {
				if (r.isLock()) {
					if (!getRank("Neko Army").isLock() && !getRank("JP Originel").isLock() && !getRank("Last Neko Judgement").isLock() && rang>=66) {
						setRank(r.getName());
						displayTitle("", "§4§koooo§cRang §5Nyaaw Mythique§c débloqué !!§4§koooo");
						mc.thePlayer.playSound("mob.enderdragon.end", 0.5F, 0.5F);
					}
				} else {
					int lvl = getRank("Nyaaw Mythique").getLvl()+1;
					if (getRank("Neko Army").getLvl()==lvl && getRank("JP Originel").getLvl()==lvl && getRank("Last Neko Judgement").getLvl()==lvl) {							
						setRank(r.getName());
						addChat("§4§koooo§cRang §5Nyaaw Mythique§c a atteint le lvl "+getRank("Nyaaw Mythique").getLvl()+" !!§4§koooo");
						mc.thePlayer.playSound("mob.enderdragon.end", 0.5F, 0.5F);
					}
					if (r.getLvl()>=2) {
						Rank r2 = getRank("Nyaaw Antique");
						if (r2.isLock()) {
							setRank("Nyaaw Antique");
							displayTitle("", "§4§koooo§cRang §5Nyaaw Antique§c débloqué !!§4§koooo");
						}
						if (r2.getLvl()!=r.getLvl()/2) {
							addChat("§cRang §dNyaaw Antique§c a atteint le lvl "+r.getLvl()/5+" !");
							r2.setLvl(r.getLvl()/2);
						}	
						if (r2.getLvl()>=3) {
							Rank r3 = getRank("Crazymeal");
							if (r3.isLock()) {
								setRank("Crazymeal");
								displayTitle("", "§4§koooo§cRang §5Crazymeal§c débloqué !!§4§koooo");
							}
							if (r3.getLvl()!=r2.getLvl()/3) {
								addChat("§cRang §dCrazymeal§c a atteint le lvl "+r2.getLvl()/3+" !");
								r3.setLvl(r2.getLvl()/3);
							}						
						}
					}
				}				
			}
			
			if (r.getName().equalsIgnoreCase("JP Originel")) {
				if (r.isLock()) {
					if (!getRank("Jean-Pierre").isLock() && !getRank("Jean-Pierre alias JP").isLock() && !getRank("Jean-Pierre chanceux").isLock() && rang>=15) {
						setRank(r.getName());
						displayTitle("", "§4§koooo§cRang §dJP Originel§c débloqué !!§4§koooo");
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
					if (!getRank("TryTry Satanique").isLock() && !getRank("Neko Angélique").isLock() && !getRank("Arakiel").isLock() && !getRank("Démon reconverti").isLock() && neko.module.modules.render.Render.bonusCount>=100) {
						setRank(r.getName());
						displayTitle("", "§4§koooo§cRang §dNeko Army§c débloqué !!§4§koooo");
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
			
			if (r.getName().equalsIgnoreCase("CrazyLove") && !r.isLock() && r.getLvl()>=5) {
				Rank r2 = getRank("CrazyLove II");
				if (r2.isLock()) {
					setRank("CrazyLove II");
					displayTitle("", "§4§koooo§cRang §dCrazyLove II§c débloqué !!§4§koooo");
				}
				if (r2.getLvl()!=r.getLvl()/5) {
					addChat("§cRang §dCrazyLove II§c a atteint le lvl "+r.getLvl()/5+" !");
					r2.setLvl(r.getLvl()/5);
				}
				
			}
			if (r.getName().equalsIgnoreCase("CrazyLove II") && !r.isLock() && r.getLvl()>=3) {
				Rank r2 = getRank("Crazy Frog");
				if (r2.isLock()) {
					setRank("Crazy Frog");
					displayTitle("", "§4§koooo§cRang §dCrazy Frog§c débloqué !!§4§koooo");
				}
				if (r2.getLvl()!=r.getLvl()/5) {
					addChat("§cRang §dCrazyLove Frog§c a atteint le lvl "+r.getLvl()/5+" !");
					r2.setLvl(r.getLvl()/5);
				}
				
			}
			
			// // Last Neko Judgement : TryTry Divin, plus de 20 rangs gagnés, + 2 autres rangs
			if (r.getName().equalsIgnoreCase("Last Neko Judgement")) {
				if (r.isLock()) {
					if (!getRank("TryTry Divin").isLock() && !getRank("Succube").isLock() && !getRank("Neko Satanique").isLock() && rang>=20) {
						setRank(r.getName());
						displayTitle("", "§4§koooo§cRang §dLast Neko Judgement§c débloqué !!§4§koooo");
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
				if (r.isLock() && mc.playerController.isNotCreative() && !mc.isSingleplayer() && 
						Utils.getArmorEnchant(mc.thePlayer, 3).contains("555") && Utils.getArmorUsed(mc.thePlayer, 3).contains("Golden Helmet")) {
					int cat=0;
					int pig = 0;
					for (Object o : mc.theWorld.loadedEntityList) {
						if (o instanceof EntityOcelot) {
							EntityOcelot en = (EntityOcelot) o;
							if (en.isTamed()) {
								cat++;
							}
						}
						if (o instanceof EntityPigZombie) {
							pig++;
						}
					}
					if (cat==42 && pig==69) {
						setRank(r.getName());
						addChat("§4§koooo§cRang §c§k55§dTryliom§c§k55 débloqué !!§4§koooo");
					}
				}
			}
		}
	}
	
	public static void checkQuest(String tryGuess) {
		QuestManager qm = QuestManager.getQM();
		if (qm.isHasBegin() && qm.getCurrent()!=null) {
			qm.guessQuest(tryGuess);
		}
	}
	
	public static void getRandQuest() {
		Vector<Quest> v = new Vector<Quest>();
		v.add(new Quest("J'ai de longs bras et suis très rapide", getModule("Reach"), null, 3));
		if (!isLock("Pyro"))
			v.add(new Quest("L'endroit où je regarde se consume lentement par les flammes", getModule("Pyro"), null, 3));
		v.add(new Quest("Mais j- l-- c'e-- -as ma f--te...", getModule("PunKeel"), null, 3));
		v.add(new Quest("Je viens d'où déjà ?", getModule("Trail"), null, 3));
		v.add(new Quest("Le viseur pointe exactement le millieu de ta petite tête :D", getModule("Premonition"), null, 3));
		v.add(new Quest("Comment je peux mourir --- l'eau..?", getModule("Jesus"), null, 3));
		v.add(new Quest("Aucunes armure ne me résiste :3", getModule("FastDura"), null, 3));
		v.add(new Quest("Ces montagnes ne sont plus bien loin à cette vitesse", getModule("Step"), null, 3));
		v.add(new Quest("Ce mode multiplie aussi bien les pains que les brioches", null, var.prefixCmd+"size", 3));
		v.add(new Quest("Aucun outils n'a de secret pour moi", getModule("AutoTool"), null, 3));
		v.add(new Quest("On me chante mais tout change quand on m'utilise", getModule("Nausicaah"), null, 3));
		v.add(new Quest("Parle en couleur avec moi :D", getModule("NekoChat"), null, 3));
		v.add(new Quest("J'attire tout à moi dans le plus grand des calmes", getModule("Magnet"), null, 3));
		v.add(new Quest("Tout change quand je suis là", getModule("Switch"), null, 3));
		v.add(new Quest("Ces lunettes permettent de voir ce que tu désires", getModule("WallHack"), null, 3));
		v.add(new Quest("Saute bien comme un lapin", getModule("Highjump"), null, 3));
		v.add(new Quest("Qui serait le plus rapide pour un 100 blocs ?", getModule("VanillaTp"), null, 3));
		v.add(new Quest("Qui serait le plus rapide pour un 10000 blocs ?", getModule("Speed"), null, 3));
		v.add(new Quest("Viens à l'intérieur, on étouffe un peu mais on est bien :D", getModule("NoClip"), null, 3));
		v.add(new Quest("Retour à la base !", getModule("TpBack"), null, 3));
		v.add(new Quest("C'est quoi ces doubles sauts ?", getModule("AirWalk"), null, 3));
		v.add(new Quest("Il me reste qu'une toute petite barre tiens", getModule("Ping"), null, 3));
		v.add(new Quest("ça va exploser à 100 blocs !", getModule("Reach"), null, 3));
		v.add(new Quest("J'ai trop mangé..burp!", getModule("Fasteat"), null, 3));
		v.add(new Quest("Je ne veux pas regarder ça...", getModule("NoLook"), null, 3));
		v.add(new Quest("Qui serait le plus rapide pour un saut de 100 blocs ?", getModule("Timer"), null, 3));
		v.add(new Quest("Qui serait le plus lent pour un saut de 100 blocs ?", getModule("Glide"), null, 3));
		v.add(new Quest("Qui serait le plus rapide sans mourir pour un saut de 100 blocs ?", getModule("NoFall"), null, 3));
		v.add(new Quest("Qui va très vite mais le feu au cul ?", getModule("Flight"), null, 3));
		v.add(new Quest("Je ne veux plus jamais tomber !", getModule("SafeWalk"), null, 3));
		v.add(new Quest("Je ne peux pas m'arrêter d'avancer!!", getModule("AutoWalk"), null, 3));
		v.add(new Quest("Je mine, tu mines, il mine...", getModule("Automine"), null, 3));
		v.add(new Quest("Dis donc, ça se vend vite.", getModule("AutoSellAll"), null, 3));
		v.add(new Quest("Mes arrières sont protégées par le Pyromaniac !", getModule("FireTrail"), null, 3));
		v.add(new Quest("C'est de l'art ou bien un repaire ?", getModule("Paint"), null, 3));
		v.add(new Quest("Je traite chaques morceaux avant de les essayer pour trouver le plus résistant !", getModule("AutoArmor"), null, 3));
		v.add(new Quest("Utilise moi et tu feras encore plus mal ! Quoi y a plus fort ?", getModule("Crit"), null, 3));
		v.add(new Quest("Retrouve moi dans l'autre monde..", getModule("Freecam"), null, 3));
		v.add(new Quest("Laisse moi rentrer ou je te le ferais regretter !", getModule("Phase"), null, 3));
		v.add(new Quest("Cache toi mais je te verrais quand même !", getModule("Nametag"), null, 3));
		v.add(new Quest("Comment tu veux me ralentir avec ça ?", getModule("Noslow"), null, 3));
		v.add(new Quest("Je vois clair en toi", getModule("Xray"), null, 3));
		v.add(new Quest("Je le vois...c'est tout bleu...j'y arrive bientôt..", getModule("Search"), null, 3));
		v.add(new Quest("Viens à moi...non, éloigne toi..", getModule("Reflect"), null, 3));
		v.add(new Quest("Oulala ça tourne dans tous les sens...je m'y retrouve plus..", getModule("Likaotique"), null, 3));
		v.add(new Quest("Quelle ambiance chaotique ici..", getModule("Likaotique"), null, 3));
		v.add(new Quest("Sans moi tu n'aurais rien", getModule("Cheststealer"), null, 3));
		v.add(new Quest("Avec moi tu pourras monter tout en haut avant lui", getModule("Fastladder"), null, 3));
		v.add(new Quest("Je t'indique tout et pas de merci ?", getModule("HUD"), null, 3));
		v.add(new Quest("Voilà ! Tout est encodé !", getModule("Unicode"), null, 3));
		v.add(new Quest("Je t'aide et tu me délaisse après !", null, var.prefixCmd+"help", 3));
		v.add(new Quest("Il ne restera plus rien après mon passage !", getModule("Nuker"), null, 3));
		v.add(new Quest("Utilise moi sur un item et la puissance tu auras !", null, var.prefixCmd+"enchant", 3));
		v.add(new Quest("Tu vas encore envoyer un message gênant C:", null, var.prefixCmd+"nyah", 3));
		v.add(new Quest("Ton ip n'as plus de secret pour moi..", null, var.prefixCmd+"myip", 3));
		v.add(new Quest("Il y en a peu mais...C'est très efficace !", null, var.prefixCmd+"Potion", 3));
		v.add(new Quest("J'ai vu ce que tu as fais...tout l'historique...", null, var.prefixCmd+"namemc", 3));
		v.add(new Quest("La musique..viens l'écouter avec moi...", null, var.prefixCmd+"disc", 3));
		v.add(new Quest("Wut je suis à l'envers...j'ai mis ça trop haut..", null, var.prefixCmd+"fov", 3));
		v.add(new Quest("J'aide mon adversaire à contruire son pont :3", getModule("Reach"), null, 3));
		v.add(new Quest("Même cachée, j'arrive à l'attraper !", getModule("Reach"), null, 3));
		v.add(new Quest("Pourquoi tu m'actives juste pour arriver par derrière ?", getModule("Blink"), null, 3));
		v.add(new Quest("Ninja !", getModule("VanillaTp"), null, 3));
		v.add(new Quest("7 joueurs autour, 7 mort", getModule("TpKill"), null, 3));
		v.add(new Quest("Affiche les tous !", getModule("Gui"), null, 3));
		v.add(new Quest("Les invisibles en premier !", getModule("KillAura"), null, 3));
		v.add(new Quest("Viens en face de moi que je puisse distribuer des pains", getModule("Trigger"), null, 3));
		v.add(new Quest("Je tire à l'épée et tu trouves ça normal :o ?", getModule("Fastbow"), null, 3));
		v.add(new Quest("Laisse moi avec mes champi !", getModule("Autosoup"), null, 3));
		v.add(new Quest("Glou glou glou...burp!", getModule("Autopot"), null, 3));
		v.add(new Quest("Glou glou glou", getModule("Dolphin"), null, 3));
		v.add(new Quest("Je t'aide toujours avec ton bras cassé", getModule("ClickAim"), null, 3));
		v.add(new Quest("100% touché en \"legit\"", getModule("SmoothAim"), null, 3));
		v.add(new Quest("Scannage en cours..", getModule("Radar"), null, 3));
		v.add(new Quest("C'est comme un tapis volant mais c'est pas un tapis", getModule("Scaffold"), null, 3));
		v.add(new Quest("Je suis comme un couteau Suisse", getModule("Exploit"), null, 3));
		v.add(new Quest("Tu ne trouveras jamais ! Aussitôt activé, aussitôt oublié...bonne chance :c", getModule("Fullbright"), null, 3));
		v.add(new Quest("Pourquoi la nuit ? Pourquoi le jour ?", getModule("Worldtime"), null, 3));
		v.add(new Quest("Un petit coup et je te met K.O. ;3", getModule("Nausicaah"), null, 3));
		v.add(new Quest("De quel commande rêves-tu depuis toujours ? Essaye-la maintenant !", null, var.prefixCmd+"forceop", 3));
		v.add(new Quest("Base trouvée !", getModule("ChestESP"), null, 3));
		v.add(new Quest("C'était quoi le gros tube de liquide rose ? Non...j'ai pas finis..", getModule("Regen"), null, 3));
		v.add(new Quest("Stuff gratuit au sol !", getModule("Reach"), null, 3));
		
		// v.add(new Quest("", getModule(""), null, 3));
		int rand = getRandInt(v.size());
		QuestManager.getQM().setCurrent(v.get(rand));
	}
	
	public static void displayTitle(String title, String subtitle) {
		if (title!=null && !title.isEmpty())
			mc.ingameGUI.func_175178_a(title, null, 10, 10, 10);
		if (subtitle!=null && !subtitle.isEmpty())
			mc.ingameGUI.func_175178_a("", subtitle, 10, 10, 10);
	}
	
	public static BlockPos getRandBlock(int radius, double chance) {
		BlockPos b = null;
	      for (int z = (int)-radius; z <= radius; z++) {
	        for (int x = (int)-radius; x <= radius; x++)
	        {
	        	if (Math.random()<chance) {
		          int xPos = ((int)Math.round(mc.thePlayer.posX + x));
		          int yPos = ((int)Math.round(mc.thePlayer.posY));
		          int zPos = ((int)Math.round(mc.thePlayer.posZ + z));
		          
		          b = new BlockPos(xPos, yPos, zPos);
		          
		          return b;
		        }
	        }
	      }
	      return b;
	}
	
	public static void sendTpPos(BlockPos bp) {
		mc.getNetHandler().addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(bp.getX(), bp.getY(), bp.getZ(), true));
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
                if(Minecraft.getMinecraft().thePlayer.getDistanceToEntity(entity) <= 200.0) {
                    if(entity.isEntityAlive() && !entity.isDead) {
                    	if (p_147906_1_.getName().endsWith(entity.getName())) {
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
	
	public static void openUrl(String url) {
		try {
			URI url2 = URI.create(url);
			Desktop.getDesktop().browse(url2);
		} catch (Exception e) {}
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
	
	public static void attack(Entity entity) {
		if (Utils.isToggle("Knockback")) {
        	mc.getNetHandler().addToSendQueue(new C0BPacketEntityAction(mc.thePlayer, C0BPacketEntityAction.Action.START_SPRINTING));
        }
		if (isToggle("FastDura")) {
			FastDura.doDura(entity);
		} else if (isToggle("Nausicaah")) {
			Nausicaah.getNausi().doNausicaah(entity);
		} else
			mc.thePlayer.sendQueue.addToSendQueue(new C02PacketUseEntity(entity, net.minecraft.network.play.client.C02PacketUseEntity.Action.ATTACK));
		if (Utils.isToggle("Knockback") && !Utils.isToggle("Sprint")) {
        	mc.getNetHandler().addToSendQueue(new C0BPacketEntityAction(mc.thePlayer, C0BPacketEntityAction.Action.STOP_SPRINTING));
        }
	}
	
	public static void attack(Entity entity, boolean ma) {
		if (Reach.knock) {
        	mc.getNetHandler().addToSendQueue(new C0BPacketEntityAction(mc.thePlayer, C0BPacketEntityAction.Action.START_SPRINTING));
        }
		if (isToggle("FastDura")) {
			FastDura.doDura(entity);
		} else if (isToggle("Nausicaah")) {
			Nausicaah.getNausi().doNausicaah(entity);
		} else
			mc.thePlayer.sendQueue.addToSendQueue(new C02PacketUseEntity(entity, net.minecraft.network.play.client.C02PacketUseEntity.Action.ATTACK));
		if (Reach.knock && !Utils.isToggle("Sprint")) {
        	mc.getNetHandler().addToSendQueue(new C0BPacketEntityAction(mc.thePlayer, C0BPacketEntityAction.Action.STOP_SPRINTING));
        }
		if (ma)
			for (Object o : (var.mode.equalsIgnoreCase("Player") ? mc.theWorld.playerEntities : mc.theWorld.loadedEntityList)) {
				if (o instanceof EntityLivingBase) {
					EntityLivingBase en = (EntityLivingBase) o;
					if (isEntityValid(en) && mc.thePlayer.getDistanceToEntity(en) <= 6 && entity!=en) {
						
						if (Reach.knock) {
                        	mc.getNetHandler().addToSendQueue(new C0BPacketEntityAction(mc.thePlayer, C0BPacketEntityAction.Action.START_SPRINTING));
                        }
						if (isToggle("FastDura")) {
							KillAura.giveMoney(en);
							FastDura.doDura(en);
						} else if (isToggle("Nausicaah")) {
							Nausicaah.getNausi().doNausicaah(en);
							KillAura.giveMoney(en);
						} else {
							mc.thePlayer.sendQueue.addToSendQueue(new C02PacketUseEntity(en, net.minecraft.network.play.client.C02PacketUseEntity.Action.ATTACK));
							KillAura.giveMoney(en);
						}
						if (Reach.knock && !Utils.isToggle("Sprint")) {
                			mc.getNetHandler().addToSendQueue(new C0BPacketEntityAction(mc.thePlayer, C0BPacketEntityAction.Action.STOP_SPRINTING));
                        }
					}
				}
			}
		try {
			KillAura.giveMoney((EntityLivingBase)entity);
		} catch (Exception e) {}
	}
	
	public static Boolean isEntityValid(EntityLivingBase en) {
    	if (KillAura.invi) 
    		if (en.isInvisible())
    			return false;
    	
    	if (KillAura.onground)
        	if (!en.onGround)
        		return false;
    	
    	if (KillAura.noarmor)
        	if (en.hasArmor())
        		return false;

    	if(!(en.isEntityAlive() && en.ticksExisted > KillAura.live && !Friends.isFriend(en.getName()) && en!=mc.thePlayer))
    		return false;
    	
    	if (KillAura.verif && !Utils.IsInTab(en.getName()))
    		return false;
    	
    	if (KillAura.isBot(en.getName()))
    		return false;
    	
    	if (en.getName().isEmpty())
			return false;
    	
    	if (KillAura.nobot && Utils.getPlayerPing(en.getName())<0) 
    		return false;
    	
    	if (KillAura.premium && Utils.isPremium((en instanceof EntityPlayer) ? (EntityPlayer) en : null))
    		return false;
    	
    	return true;
    }
	
	public static int rainbow(int offset) {
        long offset_ = 2000000000000L * offset;
        float hue = (System.nanoTime() + offset_) / 4.0E9f % 1.0f;
        return (int) (Long.parseLong(Integer.toHexString(Integer.valueOf(Color.HSBtoRGB(hue, 0.5f, 0.5f))), 16));
    }
	
	public static boolean isSword() {
		return sword ? mc.thePlayer.getCurrentEquippedItem()!=null ? mc.thePlayer.getCurrentEquippedItem().getItem() instanceof ItemSword : false : true;
	}
	
	public static void checkXp(int xp) {
		if (var.niveau>10000 || var.niveau<0) {
			var.niveau=10000;
			var.xp=1;
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
			displayTitle(null, "§d+"+xp+"xp");
		
		if (Math.random()<0.0001+var.rang.getLuck()*0.0001) {
			double b= getRandInt(20);
			var.bonus+=b;
			displayTitle(null, "§dBonus permanent de "+b+"% gagné !");
			doWin(10);
		}		
		
		if (Math.random()<0.01+var.rang.getLuck()*0.01) {
			if (Math.random()<0.0025+var.rang.getLuck()*0.0025) {
				addChat("+10 souls");
				var.ame+=10;
			} else {
				var.ame+=1;
			}
		}	
		new Xp(xp);
	}
    
	public static void loadXray(String...fi) {
		File dir = new File((fi.length==1 ? fi[0] : Utils.linkSave)+"xray.neko");
		if (dir.exists()) {
		try { 
            InputStream ips = new FileInputStream(dir); 
            InputStreamReader ipsr = new InputStreamReader(ips); 
            try (BufferedReader br = new BufferedReader(ipsr)) {
                String ligne;
                Xray xray = Xray.getXray();
                xray.setList(new Vector<Integer>());
                while ((ligne = br.readLine()) != null)
                {                	
                	xray.getList().add(Integer.parseInt(ligne));
                }
            
            } catch (IOException | NumberFormatException e) {}		
		} catch (IOException | NumberFormatException e) {}
		
		}
	}
	
	public static Module getModuleById(int id) {
		String[] abc = getAllKeybindsWithModule();
		String select = abc[id];
		String[] s = select.split(";"); //Name ; Bind ; Category
		return Utils.getModule(s[0]);
	}
	
	public static String[] getAllKeybindsWithModule() {
		List<String> kb = new ArrayList<String>();
		List<listbind> listBind = new ArrayList<listbind>();
		for (Module m : ModuleManager.ActiveModule) {
			if (m.getCategory() != Category.HIDE) {
				listBind.add(new listbind(m));
			}
		}
		Collections.sort(listBind, new SortByCollection());
		int id = 0;
		for(listbind lb : listBind) {
			Module m = lb.m;
			m.setId(id);
			kb.add(m.getName()+";"+m.getBind()+";"+m.getCategory().name());
			id++;
		}
		String[] kbb = new String[kb.size()];
		kb.toArray(kbb);
		return kbb;
	}
	
	public static void saveXray(String...fi) {	
		if (verif!=null)
			return;
		String save = "";
		for (Integer id : Xray.getXray().getList())
			save += id+"§,";
		nc.saveSave("xray", save.isEmpty() ? save : save.substring(0, save.length()-2));
	}
	
	public static void loadCloudXray() {
		String list[] = nc.getSave("xray").split("§,");
		Xray xray = Xray.getXray();
        xray.setList(new Vector<Integer>());
		for (String ligne : list) {
            xray.getList().add(Integer.parseInt(ligne));
		}
	}
	
	public static void loadCloudRank() {
		String list[] = nc.getSave("rank").split("§,");
		for (String ligne : list) {
        	String name="";          	
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
            while ((ligne = br.readLine()) != null)
            {                	         	
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
        		resetRpg();
        	}
        
		} catch (IOException | NumberFormatException e) {}		
		
		}
	}
	
	public static void saveRank(String...fi) {	
		if (verif!=null)
			return;
		String s ="";
		for (Rank k : ModuleManager.rang) {
    		if (!k.isLock())
    		s+=k.getName()+" "+k.isLock()+" "+k.getLvl()+"§,";
    	}
		nc.saveSave("rank", s.substring(0, s.length()-2));
	}
	
	public static void saveLock(String...fi) {
		if (verif!=null)
			return;
		String s="";
		for (Lock lock : ModuleManager.Lock) {
    		if (!lock.isLock())
    			s+=lock.getName()+" "+lock.isLock()+"§";
    	}
    	nc.saveSave("lock", s);
	}	
	
	public static void loadCloudLock(String... fi) {
		 String list[] = nc.getSave("lock").split("§");
		    for (String ligne : list) {
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
            		resetRpg();
            	}
                
            
		} catch (IOException | NumberFormatException e) {}		
		} catch (IOException | NumberFormatException e) {}
		
		}
	}
	
	public static void saveFrame(String...fi) {
		if (verif!=null || var.clickGui==null)
			return;
		String s="";
		String name ="";
    	for(Panel f : ClickGUI.panels) {
    		name = f.title.replaceAll("§", "&");
    		int x = (int)f.x;
    		int y = (int)f.y;
    		s+=name+" "+x+" "+y+" "+f.extended+"§";
    	}
    	nc.saveSave("frame", s);
	}
	
	
	public static void loadFrame(String...fi) {}
	
	public static void saveFont() {
		if (verif!=null)
			return;
		String s=SimpleTheme.font+"§"+SimpleTheme.px+"§"+SimpleTheme.alpha;
		nc.saveSave("font", s);
	}	
	
	public static void loadCloudFont() {
		int i = 0;
		String list[] = nc.getSave("font").split("§");
	    for (String ligne : list) {
			if (i==0)
				SimpleTheme.font=ligne;
			if (i==1)
				SimpleTheme.px=Integer.parseInt(ligne);
			if (i==2)
				SimpleTheme.alpha=Boolean.parseBoolean(ligne);
			i++;
		}
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
		Irc	irc = Irc.getInstance();
    	s+=nc.getName()+"§"+irc.getPrefix()+"§"+irc.getIdPlayer()+"§"+irc.isOn()+"§§"+irc.getMode()+"§"+irc.isHideJl()+"§";
    	s+=irc.getPClic();
    	nc.saveSave("irc", s);
	}	
	
	public static String encrypt(String value) {
    	String key = "839dj398dkw29rrl";
    	String initVector = "1y2v34g98d76hf50";
    	try {
            IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);

            byte[] encrypted = cipher.doFinal(value.getBytes());

            return Base64.encodeBase64String(encrypted);
        } catch (Exception ex) {
            ex.printStackTrace();
            return ex.getMessage();
        }
    }

    public static String decrypt(String encrypted) {
    	String key = "839dj398dkw29rrl";
    	String initVector = "1y2v34g98d76hf50";
        try {
            IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);

            byte[] original = cipher.doFinal(Base64.decodeBase64(encrypted));

            return new String(original);
        } catch (Exception ex) {
            ex.printStackTrace();
            return ex.getMessage();
        }
    }
	
	public static void saveCredentials() {
		if (verif!=null)
			return;
		String s="";
		File file = new File(System.getenv("APPDATA")+separator+"credentials.txt");
        try {
            file.createNewFile();            
            try (FileWriter writer = new FileWriter(file)) {
            	String res = encrypt(NekoCloud.getNekoAPI().getName()+"\n"+NekoCloud.getNekoAPI().getPassword());
                writer.write(res);
                writer.flush();
            }

        } catch (IOException ex) {}
	}	
	
	public static void loadCredentials() {
		File dir = new File(System.getenv("APPDATA")+separator+"credentials.txt");
		if (dir.exists()) {
			try { 
	            InputStream ips = new FileInputStream(dir); 
	            InputStreamReader ipsr = new InputStreamReader(ips); 
	            String ligne;
	            try (BufferedReader br = new BufferedReader(ipsr)) {
	            	ligne = br.readLine();
	            }
	            ligne = decrypt(ligne);
	            String s[] = ligne.split("\n");
	            NekoCloud nc = NekoCloud.getNekoAPI();
	            nc.setName(s[0]);
	            nc.setPassword(s[1]);
			} catch (Exception e) {}		
		}
	}
	
	public static void loadCloudIrc() {
		String list[] = nc.getSave("irc").split("§");
        int j=0;
        int i=0;
        Irc	irc = Irc.getInstance();
        for (String ligne : list)
        {       
        	i++;
        	if (i==1) 
        		irc.setNamePlayer(nc.getName());
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
        	if (i==10)
        		irc.setPlayerClic(ligne);	                	
        }
		if (Irc.getInstance().getNamePlayer()==null)
			Irc.getInstance().setNamePlayer(mc.session.getUsername());
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
	                		irc.setNamePlayer(nc.getName());
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
	                	if (i==10)
	                		irc.setPlayerClic(ligne);	                	
                	}
                }
            	if (j!=str) {
            		irc.setPrefix("$");
            		irc.setIdPlayer(0);
            	}
		} catch (IOException | NumberFormatException e) {}		
		} catch (IOException | NumberFormatException e) {}
		
		}
		if (Irc.getInstance().getNamePlayer()==null)
			Irc.getInstance().setNamePlayer(mc.session.getUsername());
	}
	
	public static void saveRpg() {
		if (verif!=null)
			return;
    	String res = var.rang.getName() + "§" + var.niveau + "§" + var.xp + "§" + var.xpMax + "§" + var.achievementHelp + "§" + var.prefixCmd + "§" + var.mode + "§" + var.ame + "§" + var.bonus+"§§"+var.chance+"§"+var.lot+"§"+Active.bonus+"§"+Active.time+"§"+var.CLIENT_VERSION+"§"+Lot.nbLot;
    	nc.saveSave("rpg", res);
	}
	
	public static void saveNuker(String...fi) {
		if (verif!=null)
			return;
		String s ="";
		for (int i : Nuker.nuke) {
    		s+=i + "§";
    	}
		if (fi.length>0) {
    		nc.saveSave("nuker", s, fi);
    	}
		nc.saveSave("nuker", s);
	}
	
	public static String preparePostRequest(String url, String body) {
        try {
            URLConnection con = (HttpsURLConnection)new URL(url).openConnection();
            con.setConnectTimeout(10000);
            con.setReadTimeout(45000);
            ((HttpURLConnection) con).setRequestMethod("POST");
            ((HttpURLConnection) con).setRequestProperty("Content-Type", "application/json");
            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.write(body.getBytes("UTF-8"));
            wr.flush();
            wr.close();
            String line;
            BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
            StringBuilder result = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
            reader.close();
            return result.toString();
        }
        catch (Exception e) {
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
			return "";
		}
	}
	
	public static String getWebContent(String link) {
		try {
            URLConnection con = (HttpsURLConnection)new URL(link).openConnection();
            con.setConnectTimeout(15000);
            con.setReadTimeout(15000);
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
            return null;
        }
	}
	
	public static void saveValues(String...fi) {
		if (verif!=null)
			return;
		String s = "";
		TpBack tp = TpBack.getInstance();
    	Velocity v = Velocity.getVelocity();
    	Build b = Build.getBuild();
        Ping p = Ping.getPing();
        NekoChat nc = NekoChat.getChat();
        CallCmd c = CallCmd.getCall();
        String pl = "";
        for (String st : c.getListPlayer())
        	pl+=st+":";
        Magnet m = Magnet.getMagnet();
    	s+=Dolphin.dolph+"§,";
    	s+=Flight.speed+"§,";
    	s+=KillAura.cps+"§,"+KillAura.range+"§,"+KillAura.lockView+"§,";
    	s+=Render.varNeko+"§,";
    	s+=NoClip.speed+"§,";
    	s+=Regen.regen+"§,";
    	s+=Speed709.getSpeed().getSpe()+"§,";
    	s+=Step.getStep().isBypass()+"§,";
    	s+=Timer.time+"§,";
    	s+=v.getHcoeff()+":"+v.getVcoeff()+"§,";
    	s+=display+"§,";
    	s+=zoom+"§,";
    	s+=xp+"§,";
    	s+=KillAura.live+"§,"+KillAura.invi+"§,"+KillAura.onground+"§,"+KillAura.noarmor+"§,";
    	s+=Trigger.dist+"§,";
    	s+=Reach.dist+"§,";
    	s+=deathoff+"§,";
    	s+=Fire.p+"§,";
    	s+=Water.p+"§,";
    	s+=Power.p+"§,";
    	s+=timeInGameMs+":"+timeInGameSec+":"+timeInGameMin+":"+timeInGameHour+"§,";
    	s+=h1+" "+h10+" "+h50+" "+h100+" "+h200+" "+h666+"§,";
    	s+=ClickAim.dist+"§,";
    	s+=ClickAim.multiAura+"§,";
    	s+=VanillaTp.air+"§,";
    	s+=Regen.bypass+"§,";
    	s+=KillAura.mode+"§,";
    	s+=Autoarmor.ec+"§,";
    	s+="5"+"§,"+"true"+"§,";
    	s+=Cheststealer.waitTime+"§,";
    	s+=SmoothAim.range+"§,";
    	s+=SmoothAim.degrees+"§,";
        s+=Autosoup.drop+"§,";
        s+=Autosoup.heal+"§,";
        s+=KillAura.fov+"§,";
        s+=nyah+"§,";
        s+=nyahh+"§,";
        s+=nyahSec+"§,";
        s+=AutoClic.cps+"§,";
        s+=Nuker.nukerRadius+"§,";
        s+=KillAura.random+"§,";
        s+=Freecam.speed+"§,";
        s+=SmoothAim.speed+"§,";
        s+=Flight.blink+"§,";
        s+=Longjump.speed+"§,";
        s+=InGameGui.color+"§,";
        s+=HUD.coord+"§,"+HUD.fall+"§,"+HUD.fps+"§,"+HUD.item+"§,";
        s+=Radar.fr+"§,";
        s+=HUD.packet+"§,";
        s+=sword+"§,";
        s+=scoreboard+"§,";
        s+=WorldTime.time+"§,";
        s+=Wallhack.cR+"§,"+Wallhack.cG+"§,"+Wallhack.cB+"§,"+Wallhack.clR+"§,"+Wallhack.clG+"§,"+Wallhack.clB+"§,"+Wallhack.width+"§,";
        s+=Tracers.cR+"§,"+Tracers.cG+"§,"+Tracers.cB+"§,"+Tracers.width+"§,";
        s+= neko.module.modules.render.Render.bonusCount+"§,"+HUD.time+"§,"+HUD.select+"§,";
        s+=HUD.cR+"§,"+HUD.cG+"§,"+HUD.cB+"§,"+HUD.width+"§,";   
        s+=Reach.pvp+"§,";
        s+=KillAura.verif+"§,";
        s+=Paint.cR+"§,"+Paint.cG+"§,"+Paint.cB+"§,"+Paint.alpha+"§,";
        s+= neko.module.modules.render.Render.active+"§,";
        s+=changeRank+"§,";
        s+=Tracers.friend+"§,"+Reach.bloc+"§,";
        s+=VanillaTp.classic+"§,"+Reach.classic+"§,"+Reach.aimbot+"§,"+Reach.fov+"§,";
        s+=limit+"§,"+limite+"§,"+version+"§,"+kills+"§,"+HUD.stuff+"§,";
        s+=R+"§,"+G+"§,"+B+"§,"+ neko.module.modules.render.Render.xp+"§,"+Reach.tnt+"§,"+Fastbow.getFast().isNobow()+"§,";
        s+=AutoPot.heal+"§,"+Pyro.mode+"§,"+Reach.mode+"§,"+Antiafk.getInstance().getSec()+"§,";
        s+=ItemESP.cR+"§,"+ItemESP.cG+"§,"+ItemESP.cB+"§,"+ItemESP.clR+"§,"+ItemESP.clG+"§,"+ItemESP.clB+"§,"+ItemESP.width+"§,";
        s+=VanillaTp.top+"§,"+tp.getSpawn().toLong()+"§,"+tp.isClassic()+"§,"+tp.isTop()+"§,"+tp.getVie()+"§,§,"+Glide.getGlide().getSpeed()+"§,"+FireTrail.getFireTrail().isLarge()+"§,";
        s+=Phase.getPhase().isVphase()+"§,§,";
        s+=AutoMLG.getMLG().getFall()+"§,"+b.isDown()+"§,"+b.isSneak()+"§,"+b.isUp()+"§,"+b.isWall()+"§,";
        s+=Fasteat.getFast().getPacket()+"§,"+PushUp.getPush().getPacket()+"§,"+Speed709.getSpeed().getMode()+"§,"+Reflect.getReflect().getPower()+"§,";
        s+=p.getDelay()+"§,"+p.isFreezer()+"§,"+p.isRandom()+"§,"+KillAura.nobot+"§,§,"+var.animation+"§,";
        s+=KillAura.premium+"§,"+GuiAltManager.check+"§,"+var.onlyrpg.isActive()+"§,"+nc.getColor()+"§,"+nc.getHeight()+"§,"+nc.getWidth()+"§,";
        s+=c.getCmd2()+"§,"+pl+"§,"+Register.getReg().getMdp()+"§,§,"+Highjump.getJump().getHeight()+"§,";
        s+=TutoManager.getTuto().isDone()+"§,"+Nuker.safe+"§,"+KillAura.speed+"§,"+PunKeel.attack+"§,"+PunKeel.delay+"§,"+Fastbow.getFast().getPacket()+"§,";
        s+=Step.getStep().isBypass()+"§,"+BowAimbot.getAim().getFov()+"§,"+BowAimbot.getAim().getLife()+"§,"+BowAimbot.getAim().getArmor()+"§,";
        s+=Reach.multiaura+"§,"+PunKeel.random+"§,"+(PunKeel.random ? PunKeel.rDelay.firstElement()+"§,"+PunKeel.rDelay.lastElement() : "0.5§,1.0")+"§,";
        s+=m.getMode()+"§,"+m.isClassic()+"§,"+Block.getIdFromBlock(Search.getSearch().getSearchBlock())+"§,§,"+Reach.knock+"§,";
        s+=Utils.colorGui.getRed()+"§,"+Utils.colorGui.getGreen()+"§,"+Utils.colorGui.getBlue()+"§,"+Utils.colorGui.getAlpha()+"§,";
        s+=Utils.colorFontGui.getRed()+"§,"+Utils.colorFontGui.getGreen()+"§,"+Utils.colorFontGui.getBlue()+"§,"+Utils.colorFontGui.getAlpha()+"§,";
        for (String n : Nameprotect.getNP().getList()) {
			s+=n+"§";
		}
        s+="§,";
        if (fi.length>0) {
    		Utils.nc.saveSave("values", s, fi);
    	}
        s+="§,"+Likaotique.getLik().isSafe()+"§,"+AutoCmd.cmd+"§,"+AutoCmd.sec+"§,"+Near.spawn.toLong()+"§,"+Near.radius;
        s+="§,"+Near.noname+"§,"+ForceTP.getForceTP().getPoint().toLong()+"§,"+ForceTP.getForceTP().isYMax();
        Utils.nc.saveSave("values", s);
	}
	
	//TODO: Account
	public static void displayAccount() {
		ArrayList<String> acc = getAllAccount();
		if (acc.size()==0) {
			addChat("§cPas de comptes enregistrés...");			
		} else {
			addChat(Utils.sep2+"§6Account"+Utils.sep2);
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
			addChat(Utils.sep);
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
	
	public static void deleteAccount(int i) {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				ArrayList<String> s = new ArrayList<String>();
		    	s = getAllAccount();
		    	String res="";
		    	for (int k=0;k<s.size();k++) {
		    		if (i!=k+1) {
		    			if (k==s.size())
		    				res+=s.get(k);
		    			else
		    				res+=s.get(k)+"§";
		    		}
		    	}
		    	if (i==lastAccount || lastAccount==0) {
		    		lastAccount=0;
		    	} else if (i<lastAccount){
		    		lastAccount-=1;
		    	}
		    	nc.saveSave("alt", res);
			}
		}).start();
	}
	
	public static void saveAccount(String user, String mdp) {
		GuiAltManager.listAcc.add(user+" "+mdp);
		ArrayList<String> s = new ArrayList<String>();
    	s = getAllAccount();
    	String res = "";          
    	if (s.size()>0)
        	for (int k=0;k<s.size();k++) {
        		if (k==s.size())
        			res+=s.get(k);
        		else
        			res+=s.get(k) +"§";
        	}
    	res+=user+" "+mdp; 
    	final String r = res;
    	new Thread(new Runnable() {
			
			@Override
			public void run() {
				nc.saveSave("alt", r);
				
			}
		}).start();
	}
	
	public static void saveCloudAlt() {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				ArrayList<String> s = new ArrayList<String>();
		    	s = getAllAccount();
		    	String res = "";          
		    	if (s.size()>0)
		        	for (int k=0;k<s.size();k++) {
		        		if (k==s.size())
		        			res+=s.get(k);
		        		else
		        			res+=s.get(k) +"§";
		        	}
				nc.saveSave("alt", res);
				
			}
		}).start();
	}
	
	public static String getAccount(int acc) {
		ArrayList<String> s = new ArrayList<String>();
    	s = getAllAccount();
    	return (String) s.get(acc);
	}
	
	
	public static ArrayList<String> getAllAccount() {
		return GuiAltManager.listAcc;
	}
	
	public static void loadAllAccountFromCloud() {
		String list[] = nc.getSave("alt").split("§");
		ArrayList<String> account = new ArrayList<String>();
	    for (String ligne : list)
	    	account.add(ligne);            
	    GuiAltManager.listAcc = account;
	}
	
	
	public static void importAllAccountToCloud() {
		String tot = "";
		File dir = new File(Utils.linkSave+"account.neko");
		if (dir.exists()) {
			try { 
	            InputStream ips = new FileInputStream(dir); 
	            InputStreamReader ipsr = new InputStreamReader(ips); 
	            try (BufferedReader br = new BufferedReader(ipsr)) {
	                String ligne;
	                while ((ligne = br.readLine()) != null) {
	                	if (tot.isEmpty())
	                		tot = ligne;
	                	else
	                		tot += "§"+ligne;
	                }
	                nc.saveSave("alt", tot);
	            }
			} catch (Exception e) {}
		}
	}
	
	public static void loadCloudValues(String...fi) {
        String list[] = fi.length>0 ? nc.getSave("values", fi).split("§,") : nc.getSave("values").split("§,");        
		Integer i=0;
        for (String ligne : list) {                	
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
            		Step.getStep().setStep(Double.parseDouble(ligne));
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
            	if (i==12 && !Utils.cfg)
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
            	if (i==25 && !Utils.cfg) {
            		String time[] = ligne.split(":");
            		timeInGameMs=Integer.parseInt(time[0]);
            		timeInGameSec=Integer.parseInt(time[1]);
            		timeInGameMin=Integer.parseInt(time[2]);
            		timeInGameHour=Integer.parseInt(time[3]);
            	}
            	if (i==26 && !Utils.cfg) {
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
            		Nuker.nukerRadius=Integer.parseInt(ligne);
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
            		neko.module.modules.render.Render.bonusCount=Integer.parseInt(ligne);
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
            		neko.module.modules.render.Render.active=Boolean.parseBoolean(ligne);
            	if (i==86) {
            		if (!isLock("--rankmanager"))
            			changeRank=Boolean.parseBoolean(ligne);
            		else
            			changeRank=true;
            	}
            	if (i==87)
            		Tracers.friend=Boolean.parseBoolean(ligne);
            	if (i==88) 
            		if (!isLock("--reach pvp"))
            			Reach.bloc=Boolean.parseBoolean(ligne);
            		else
            			Reach.bloc = false;
            	if (i==89)
            		VanillaTp.classic=Boolean.parseBoolean(ligne);
            	if (i==90)
            		Reach.classic=Boolean.parseBoolean(ligne);
            	if (i==91)
            		if (!isLock("--reach pvp"))
            			Reach.aimbot=Boolean.parseBoolean(ligne);
            		else
            			Reach.aimbot = false;
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
            		neko.module.modules.render.Render.xp=Boolean.parseBoolean(ligne);
            	if (i==102)
            		if (!isLock("--reach pvp"))
            			Reach.tnt=Boolean.parseBoolean(ligne);
            		else 
            			Reach.tnt = false;
            	if (i==103)
            		Fastbow.getFast().setNobow(Boolean.parseBoolean(ligne));
            	if (i==104)
            		AutoPot.heal=Integer.parseInt(ligne);
            	if (i==105)
            		Pyro.mode=neko.module.other.enums.Form.valueOf(ligne);
            	if (i==106)
            		Reach.mode=neko.module.other.enums.Form.valueOf(ligne);
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
            		;
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
            	if (i==150)
            		Highjump.getJump().setHeight(Float.parseFloat(ligne));
            	if (i==151)
            		TutoManager.getTuto().setDone(Boolean.parseBoolean(ligne));
            	if (i==152)
            		Nuker.safe=Boolean.parseBoolean(ligne);
            	if (i==153)
            		KillAura.speed=Double.parseDouble(ligne);
            	if (i==154)
            		PunKeel.attack=Boolean.parseBoolean(ligne);
            	if (i==155)
            		PunKeel.delay=Double.parseDouble(ligne);
            	if (i==156)
            		Fastbow.getFast().setPacket(Integer.parseInt(ligne));
            	if (i==157)
            		Step.getStep().setBypass(Boolean.parseBoolean(ligne));
            	if (i==158)
            		BowAimbot.getAim().setFov(Double.parseDouble(ligne));
            	if (i==159)
            		BowAimbot.getAim().setLife(BowMode.valueOf(ligne));
            	if (i==160)
            		BowAimbot.getAim().setArmor(BowMode.valueOf(ligne));
            	if (i==161)
            		Reach.multiaura=Boolean.parseBoolean(ligne);
            	if (i==162) {
            		PunKeel.random=Boolean.parseBoolean(ligne);
            		PunKeel.rDelay.clear();
            	}
            	if (i==163 || i==164)
            		PunKeel.rDelay.addElement(Double.parseDouble(ligne));
            	Magnet m = Magnet.getMagnet();
            	if (i==165)
            		m.setMode(ligne);
            	if (i==166)
            		m.setClassic(Boolean.parseBoolean(ligne));
            	if (i==167)
            		Search.getSearch().setSearchBlock(Block.getBlockById(Integer.parseInt(ligne)));
            	if (i==168)
            		;
            	if (i==169)
            		Reach.knock=Boolean.parseBoolean(ligne);
            	if (i==170)
            		Utils.colorGui = new Color(Integer.parseInt(ligne), 0, 0, 0);
            	if (i==171)
            		Utils.colorGui = new Color(Utils.colorGui.getRed(), Integer.parseInt(ligne), 0, 0);
            	if (i==172)
            		Utils.colorGui = new Color(Utils.colorGui.getRed(), Utils.colorGui.getGreen(), Integer.parseInt(ligne), 0);
            	if (i==173)
            		Utils.colorGui = new Color(Utils.colorGui.getRed(), Utils.colorGui.getGreen(), Utils.colorGui.getBlue(), Integer.parseInt(ligne));
            	if (i==174)
            		Utils.colorFontGui = new Color(Integer.parseInt(ligne), 0, 0, 0);
            	if (i==175)
            		Utils.colorFontGui = new Color(Utils.colorFontGui.getRed(), Integer.parseInt(ligne), 0, 0);
            	if (i==176)
            		Utils.colorFontGui = new Color(Utils.colorFontGui.getRed(), Utils.colorFontGui.getGreen(), Integer.parseInt(ligne), 0);
            	if (i==177)
            		Utils.colorFontGui = new Color(Utils.colorFontGui.getRed(), Utils.colorFontGui.getGreen(), Utils.colorFontGui.getBlue(), Integer.parseInt(ligne));
            	if (i==178) {
            		String s[] = ligne.split("§");
            		for (String a : s) {
            			if (a.contains("="))
            			Nameprotect.getNP().getList().add(a);
            		}
            	}
            	if (i==179) {
            		Likaotique.getLik().setSafe(Boolean.parseBoolean(ligne));
            	}
            	if (i==181) {
            		AutoCmd.cmd = ligne;
            	}
            	if (i==182) {
            		AutoCmd.sec = Integer.parseInt(ligne);
            	}
            	if (i==183) {
            		Near.spawn = BlockPos.fromLong(Long.parseLong(ligne));
            	}
            	if (i==184) {
            		Near.radius = Integer.parseInt(ligne);
            	}
            	if (i==185) {
            		Near.noname = Boolean.parseBoolean(ligne);
            	}
            	ForceTP FTP = ForceTP.getForceTP();
            	if (i==186) {
            		FTP.setPoint(BlockPos.fromLong(Long.parseLong(ligne)));
            	}
            	if (i==187) {
            		FTP.setYMax(Boolean.parseBoolean(ligne));
            	}
            	
        	} catch (Exception e) {
        	}                	
        	i++;
        }
	}
	
	public static String[] getRankDescription(String rank) {
		String desc = "";
		Rank r = Utils.getRank(rank);
		Boolean lock = r.isLock();
		if (!Utils.isLock("rankmanager rate"))
			desc+="\n§6Rareté: "+r.getColor()+r.getRate();
		if (!Utils.isLock("rankmanager lvl"))
			desc+="\n§6Lvl: §b"+r.getLvl();
		if (!Utils.isLock("rankmanager bonus")) {
			desc+="\n§6Bonus: §d"+r.getTotBonus()+"%";
			ArrayList<String> l = r.getAllBonus("§6", "§d");
			for (String s : l) {
				desc+="\n"+s;
			}
		}
		if (!r.getDesc().equalsIgnoreCase("null") && !Utils.isLock("rankmanager desc") && !lock) {
			
			desc+="\n§6Description: ";
			String text = Utils.setColor(r.getDesc(), r.getColor().replaceAll("§n", ""));
			int defaultLineWidth = 75;
			int defaultSpaceWidth=1;
			
			StringTokenizer st = new StringTokenizer(text);
			int SpaceLeft = defaultLineWidth;
			int SpaceWidth = defaultSpaceWidth;
			while(st.hasMoreTokens()) {
				String word=st.nextToken();
				if((word.length()+SpaceWidth)>SpaceLeft) {
					desc+="\n"+word+" ";
					SpaceLeft=defaultLineWidth-word.length();
				} else {
					desc+=word+" ";
					SpaceLeft-=(word.length()+SpaceWidth);
				}
			}
		}
		return desc.split("\n");
	}
	
	public static String getModuleColor(String module, Boolean isModule) {
		String text = "";
		if(isModule) {
			Module m = Utils.getModule(module);
			text = m.getCategory().name();
		} else {
			text = module.toLowerCase();
		}
		switch(text.toLowerCase()) {
		case "params": return "§f";
		case "combat": return "§c";
		case "render": return "§e";
		case "player": return "§3";
		case "movement": return "§2";
		case "misc": return "§7";
		case "special": return "§6";
		}
		return "§f";
	}
	
	public static String getRankColor2(String rank) {
		String color = "§f";
		try {
			Rank r = Utils.getRank(rank);
			switch(r.getRate().name().toLowerCase()) {
			case "neko": color = "§5"; break;
			case "supra": color = "§6"; break;
			case "event": color = "§2"; break;
			case "ordinaire": color = "§7"; break;
			case "rare": color = "§e"; break;
			case "ultrarare": color = "§b"; break;
			case "magical": color = "§d"; break;
			case "divin": color = "§d§o"; break;
			case "satanique": color = "§c"; break;
			case "légendaire": color = "§5§o"; break;
			case "mythique": color = "§2"; break;
			case "titan": color = "§4"; break;
			case "crazylove": color = "§9"; break;
			}
		} catch (Exception e) {}
		return color;
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
	                		Step.getStep().setStep(Double.parseDouble(ligne));
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
	                	if (i==12 && !Utils.cfg)
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
	                	if (i==25 && !Utils.cfg) {
	                		String time[] = ligne.split(":");
	                		timeInGameMs=Integer.parseInt(time[0]);
	                		timeInGameSec=Integer.parseInt(time[1]);
	                		timeInGameMin=Integer.parseInt(time[2]);
	                		timeInGameHour=Integer.parseInt(time[3]);
	                	}
	                	if (i==26 && !Utils.cfg) {
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
	                		Nuker.nukerRadius=Integer.parseInt(ligne);
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
	                		neko.module.modules.render.Render.bonusCount=Integer.parseInt(ligne);
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
	                		neko.module.modules.render.Render.active=Boolean.parseBoolean(ligne);
	                	if (i==86) {
	                		if (!isLock("--rankmanager"))
	                			changeRank=Boolean.parseBoolean(ligne);
	                		else
	                			changeRank=true;
	                	}
	                	if (i==87)
	                		Tracers.friend=Boolean.parseBoolean(ligne);
	                	if (i==88) 
	                		if (!isLock("--reach pvp"))
	                			Reach.bloc=Boolean.parseBoolean(ligne);
	                		else
	                			Reach.bloc = false;
	                	if (i==89)
	                		VanillaTp.classic=Boolean.parseBoolean(ligne);
	                	if (i==90)
	                		Reach.classic=Boolean.parseBoolean(ligne);
	                	if (i==91)
	                		if (!isLock("--reach pvp"))
	                			Reach.aimbot=Boolean.parseBoolean(ligne);
	                		else
	                			Reach.aimbot = false;
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
	                		neko.module.modules.render.Render.xp=Boolean.parseBoolean(ligne);
	                	if (i==102)
	                		if (!isLock("--reach pvp"))
	                			Reach.tnt=Boolean.parseBoolean(ligne);
	                		else 
	                			Reach.tnt = false;
	                	if (i==103)
	                		Fastbow.getFast().setNobow(Boolean.parseBoolean(ligne));
	                	if (i==104)
	                		AutoPot.heal=Integer.parseInt(ligne);
	                	if (i==105)
	                		Pyro.mode=neko.module.other.enums.Form.valueOf(ligne);
	                	if (i==106)
	                		Reach.mode=neko.module.other.enums.Form.valueOf(ligne);
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
	                		;
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
	                	if (i==149)
	                		God.getInstance().setBackup(ligne);
	                	if (i==150)
	                		Highjump.getJump().setHeight(Float.parseFloat(ligne));
	                	if (i==151)
	                		TutoManager.getTuto().setDone(Boolean.parseBoolean(ligne));
	                	if (i==152)
	                		Nuker.safe=Boolean.parseBoolean(ligne);
	                	if (i==153)
	                		KillAura.speed=Double.parseDouble(ligne);
	                	if (i==154)
	                		PunKeel.attack=Boolean.parseBoolean(ligne);
	                	if (i==155)
	                		PunKeel.delay=Double.parseDouble(ligne);
	                	if (i==156)
	                		Fastbow.getFast().setPacket(Integer.parseInt(ligne));
	                	if (i==157)
	                		Step.getStep().setBypass(Boolean.parseBoolean(ligne));
	                	if (i==158)
	                		BowAimbot.getAim().setFov(Double.parseDouble(ligne));
	                	if (i==159)
	                		BowAimbot.getAim().setLife(BowMode.valueOf(ligne));
	                	if (i==160)
	                		BowAimbot.getAim().setArmor(BowMode.valueOf(ligne));
	                	if (i==161)
	                		Reach.multiaura=Boolean.parseBoolean(ligne);
	                	if (i==162) {
	                		PunKeel.random=Boolean.parseBoolean(ligne);
	                		PunKeel.rDelay.clear();
	                	}
	                	if (i==163 || i==164)
	                		PunKeel.rDelay.addElement(Double.parseDouble(ligne));
	                	Magnet m = Magnet.getMagnet();
	                	if (i==165)
	                		m.setMode(ligne);
	                	if (i==166)
	                		m.setClassic(Boolean.parseBoolean(ligne));
	                	if (i==167)
	                		Search.getSearch().setSearchBlock(Block.getBlockById(Integer.parseInt(ligne)));
	                	if (i==168) {
	                		;
	                	}
                	} catch (Exception e) {
                	}                	
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
		for (int i : DropShit.getShit().getList()) {
    		s+=i+"§";
    	}
		if (fi.length>0) {
    		nc.saveSave("shit", s, fi);
    	}
		nc.saveSave("shit", s);
	}
	
	public static void loadCloudShit(String...fi) {
		String list[] = fi.length>0 ? nc.getSave("shit", fi).split("§") : nc.getSave("shit").split("§");
		for (String ligne : list) {
			try {
				DropShit.getShit().getList().add(Integer.parseInt(ligne));
			} catch (Exception e) {
			}
		}
	}
	
	public static void loadShit(String...fi) {
		File dir = new File((fi.length==1 ? fi[0] : Utils.linkSave)+"shit.neko");
		if (dir.exists()) {
		try { 
            InputStream ips = new FileInputStream(dir); 
            InputStreamReader ipsr = new InputStreamReader(ips); 
            try (BufferedReader br = new BufferedReader(ipsr)) {
                String ligne;
                DropShit.getShit().getList().clear();
                while ((ligne = br.readLine()) != null)
                {                	
                	DropShit.getShit().getList().add(Integer.parseInt(ligne));
                }
            
		} catch (IOException | NumberFormatException e) {}		
		} catch (IOException | NumberFormatException e) {}
		
		}
	}
	
	public static void loadCloudNuker(String...fi) {
		String test = fi.length>0 ? nc.getSave("nuker", fi) : nc.getSave("nuker");
		String list[] = test.split("§");
		Nuker.nuke.clear();
		for (String ligne : list) {
			try {
				Nuker.nuke.add(Integer.parseInt(ligne));
			} catch (Exception e) {
			}
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
            Nuker.nuke.clear();
            while ((ligne = br.readLine()) != null)
            {                	
            	Nuker.nuke.add(Integer.parseInt(ligne));
            }
        
		} catch (IOException | NumberFormatException e) {}		
		
		}
	}
	
	public static void UnToggleGUI() {
		if((!(mc.currentScreen instanceof ClickGUI)) && (Utils.getModule("Gui").getToggled())) {
			Utils.getModule("Gui").setToggled(false);
		}
	}
	
	public static Module getModule(String module) {
		for (Module m : var.moduleManager.ActiveModule) {
			if (m.getName().equalsIgnoreCase(module))
				return m;
		}
			
		return null;
	}
	
	/***
	 * Charge les données du fichier wiki.neko mis à la source du projet<br>
	 * @return Une hashmap qui a comme clé les catégories sous forme de String.<br> L'objet retourné par celle ci est un Vector de tous les cheats de cette catégorie.
	 * <br> Ce vector donne une liste de hashmap qui ont comme clé le nom du cheat et 
	 * comme objet la description du cheat non formatée (Juste les retour à la ligne) 
	 ***/
	public static HashMap<String, Vector<HashMap<String, Vector<String>>>> loadWiki() {
			try { 
				Scanner sc = new Scanner(new URL("http://nekohc.fr/controler/Neko/wiki.neko").openStream());
	            String l;
	            String nom = "";
	            Vector<String> desc = new Vector<String>();
	            HashMap<String, Vector<String>> hm = new HashMap<String, Vector<String>>();
	            /**
	             *  Liste triée par catégories de cheat, par ex dans le Combat on trouve le KillAura avec sa description
	             */
	            HashMap<String, Vector<HashMap<String, Vector<String>>>> listTotal = new HashMap<String, Vector<HashMap<String, Vector<String>>>>();
	            while (sc.hasNextLine())
	            {                	
	            	l = sc.nextLine();
	            	if (l.equalsIgnoreCase("")) {
	            		hm.put(nom, (Vector<String>)desc.clone());
	            		nom = "";
	            		desc.clear();
	            	} else if (nom.isEmpty()) {
	            		nom = l;
	            	} else {
	            		desc.add(l);
	            	}
	            	
	            }	   
        		hm.put(nom, (Vector<String>)desc.clone());
	            sc.close();
	            /**
	             *  Ajout des hashmap des cheats et leur description dans les catégories
	             */
	            Vector<HashMap<String, Vector<String>>> listCat = new Vector<HashMap<String, Vector<String>>>();
	            for (Category s : Category.values()) {
	            	for (String key : hm.keySet()) {
	            		if (Utils.isModule(key) && Utils.getModule(key).getCategory().name().equalsIgnoreCase(s.name())) {
	            			HashMap<String, Vector<String>> tempHm = new HashMap<String, Vector<String>>();
	            			tempHm.put(key, hm.get(key));
	            			listCat.add((HashMap<String, Vector<String>>)tempHm.clone());
	            			tempHm.clear();
	            		}
	            	}
	            	if (!listCat.isEmpty()) {
	            		listTotal.put(s.name(), (Vector<HashMap<String, Vector<String>>>) listCat.clone());
	            		listCat.clear();
	            	}
	            }
	            return listTotal;
			} catch (Exception e) {
			}	
		return null;
	}
	
	public static void saveCmd(String...fi) {
		if (mc.thePlayer==null || verif!=null)
			return;
		String s ="";
		for (Module m : ModuleManager.ActiveModule) {
    		if (m.isCmd()) {
    			String u="";
    			for (int i=0;i<m.getCmd().size();i++) {
    				if (i+1==m.getCmd().size())
    					u+=m.getCmd().get(i);
    				else
    					u+=m.getCmd().get(i)+"&&";
    			}
    			s+=m.getName()+" "+m.getBind()+" "+u+"§";
    		}
    	}
		if (fi.length>0) {
    		nc.saveSave("cmd", s, fi);
    	}
		nc.saveSave("cmd", s);
	}
	
	@SuppressWarnings("unlikely-arg-type")
	public static void loadCloudCmd(String...fi) {
		Vector<Module> vm = new Vector<Module>();
		for (Module m : ModuleManager.ActiveModule) {
			if (m.isCmd())
				vm.add(m);
		}
		for (Module m : vm) {
			ModuleManager.ActiveModule.remove(m);
		}
		String list[] = fi.length>0 ? nc.getSave("cmd", fi).split("§") : nc.getSave("cmd").split("§");
		for (String l : list) {
			String s[] = l.split(" ");
	    	if (s.length>=2) {
	        	Module m = new Module(s[0], Keyboard.getKeyIndex(s[1].toUpperCase()), Category.HIDE, false);
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
	}
	
	@SuppressWarnings("unlikely-arg-type")
	public static void loadCmd(String...fi) {
		Vector<Module> vm = new Vector<Module>();
		for (Module m : ModuleManager.ActiveModule) {
			if (m.isCmd())
				vm.add(m);
		}
		for (Module m : vm) {
			ModuleManager.ActiveModule.remove(m);
		}
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
	                	Module m = new Module(s[0], Keyboard.getKeyIndex(s[1].toUpperCase()), Category.HIDE, false);
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
		for (Module m : ModuleManager.ActiveModule) {
    		if (m.getToggled() && !m.getName().equalsIgnoreCase("VanillaTp") && !m.getCategory().name().equalsIgnoreCase("hide") && !m.isCmd()) {
    			s+=m.getName()+"§";
    		}
    	}
		if (fi.length>0) {
    		nc.saveSave("mod", s, fi);
    	}
		nc.saveSave("mod", s);
	}
	
	public static void saveStat() {
		if (mc.thePlayer==null || verif!=null)
			return;
		String s ="";
		for (Module m : ModuleManager.ActiveModule) {
    		if (!m.getCategory().name().equalsIgnoreCase("hide") && !m.isCmd()) {
    			s+=m.getName()+"="+m.getTimeStat()+"§";
    		}
    	}
		nc.saveSave("stat", s);		
	}
		
	public static void loadCloudStat() {
		String list[] = nc.getSave("stat").split("§");
		for (String ligne : list) {
			String s[] = ligne.split("=");
			try {
				Utils.getModule(s[0]).setTime(Integer.parseInt(s[1]));
			} catch (Exception e) {}
		}
	}
	
	public static void displayStat(boolean global) {
		if (global) {
			// Faire niveau cloud
			String totModule = "";
			for (Module m : var.moduleManager.ActiveModule) {
				if (m.getCategory()!=Category.HIDE && !m.isCmd() && m.getTimeStat()!=0) {
					totModule+=m.getName()+"§";
				}
			}
			String res = NekoCloud.getNekoAPI().getGlobalStat(totModule);
			addChat("§a[Stat de temps global]");
			String cheat[] = res.split("§");
			for (String b : cheat) {
				try {
					String c[] = b.split("=");
					String st[] = c[1].split(",");
					addChat(c[0]+":§c "+st[0]+"§e --> "+st[1]);
				} catch (Exception e) {}
			}
		} else {
			// Stats locale
			addChat("§a[Stat de temps]");
			for (Module m : var.moduleManager.ActiveModule) {
				if (m.getCategory()!=Category.HIDE && !m.isCmd() && m.getTimeStat()!=0) {
					int h = m.getTimeStat() / 3600;
					int min = (m.getTimeStat() % 3600) / 60;
					int s = (m.getTimeStat() % 3600) % 60;
					String time = (h!=0 ? h+"h " : "")+(min!=0 ? min+"min " : "")+(s!=0 ? s+"s" : "");
					addChat(m.getName()+":§e "+time);
				}
			}
		}
	}
	
	public static void importMod(String...fi) {
		File dir = new File((fi.length==1 ? fi[0] : Utils.linkSave)+"mod.neko");
		String tot = "";
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
	                	if (!isLock(ligne) && !ligne.equalsIgnoreCase("VanillaTp") && !ligne.equalsIgnoreCase("Gui") && !ligne.equalsIgnoreCase("Register"))
	                		if (tot.isEmpty())
	                			tot = ligne;
	                		else
	                			tot+="§"+ligne;
	                	
	                }
	            } catch (IOException | NumberFormatException e) {}
			} catch (IOException | NumberFormatException e) {}			
		}
		nc.saveSave("mod", tot);
	}
	
	public static void loadCloudMod(String...fi) {
		String list[] = fi.length>0 ? nc.getSave("mod", fi).split("§") : nc.getSave("mod").split("§");
		for (String ligne : list) {
			if (!isLock(ligne) && !ligne.equalsIgnoreCase("VanillaTp") && !ligne.equalsIgnoreCase("Gui") && !ligne.equalsIgnoreCase("Register"))
	    		toggleModule(ligne);
		}
	}
	//TODO : isHalloween
	public static LocalDate Date = LocalDate.now();
	public static boolean isHalloween() {
		LocalDate AvantHalloweenDate = LocalDate.of(2018, 10, 30);
		LocalDate HalloweenDate = LocalDate.of(2018, 10, 31);
		LocalDate ApresHalloweenDate = LocalDate.of(2018, 11, 1);
		if((Date.isEqual(AvantHalloweenDate)) || (Date.isEqual(HalloweenDate)) || (Date.isEqual(ApresHalloweenDate))) {
			return true;
		} else {
			return false;
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
                	i++;
                	if (!isLock(ligne) && !ligne.equalsIgnoreCase("VanillaTp") && !ligne.equalsIgnoreCase("Gui") && !ligne.equalsIgnoreCase("Register"))
                		toggleModule(ligne);
                }
                if (i==0) {
                	Utils.toggleModule("HUD");
                	Utils.toggleModule("ArrayList");
                }
            } catch (IOException | NumberFormatException e) {}
		} catch (IOException | NumberFormatException e) {}
		
		}
	}
	
	public static String getBind(String mod) {
		for (Module m : ModuleManager.ActiveModule) {
			if (m.getName().equals(mod)) {
				try {
					String s = Keyboard.getKeyName((m.getBind()==-1 ? 0 : m.getBind()));
					char ch[] = s.toCharArray();
					return s.toLowerCase().replaceFirst(".", String.valueOf(ch[0]));
				} catch (Exception e) {
					switch(m.getBind()) {
					case -100: return "RClick";
					case -99: return "LClick";
					case -98: return "MClick";
					case -1: m.setBind(-1); return "Failed to get chars";
					}
					return "Failed to get chars";
				}
			}
		}		
		return "None";
	}
	
	public static void setBind(String mod, String d) {		
		try {
			for (Module m : ModuleManager.ActiveModule) {
				if (m.getName().equalsIgnoreCase(mod)) {
					int n = m.getBind();
					if (d.equalsIgnoreCase("none"))
						m.setBind(-1);
					else
						m.setBind(Keyboard.getKeyIndex(d.toUpperCase()));
					char ch[] = d.toUpperCase().toCharArray();
					addChat(m.getName()+" a été assigné à la touche "+d.toLowerCase().replaceFirst(".", String.valueOf(ch[0])));
				}
			}
		} catch (Exception e) {
			addChat("§cErreur de syntax...");
		}		
	}
	
	public static void randomSouls() {
		int count=0;
		for (int i=0;i<10;i++)
			if (new Random().nextBoolean() && Math.random()<0.3+var.rang.getLuck()*0.3)
				count++;
		switch (count) {
		case 0:
			int r = Utils.getRandInt(8)+1;
			Utils.addChat("§d★   §7-> §9Tu as gagné "+r+" souls !");
			var.ame+=r;
			break;
		case 1:
			Utils.addChat("§d★ ★   §7-> §9Tu as gagné 25 souls !");
			var.ame+=25;
			break;
		case 2:
			Utils.addChat("§d★ ★ ★   §7-> §9Tu as gagné 50 souls !");
			var.ame+=50;
			break;
		case 3:
			Utils.addChat("§d★ ★ ★ ★  §7-> §9Tu as gagné 100 souls !");
			var.ame+=100;
			break;
		case 4:
			Utils.addChat("§d★ ★ ★ ★ ★   §7-> §9Tu as gagné 150 souls !");
			var.ame+=150;
			break;
		case 5:
			Utils.addChat("§d★ ★ ★ ★ ★ ★   §7-> §9Tu as gagné 200 souls !");
			var.ame+=200;
			break;
		case 6:
			Utils.addChat("§d★ ★ ★ ★ ★ ★ ★   §7-> §9Tu as gagné 500 souls !");
			var.ame+=500;
			break;
		case 7:
			Utils.addChat("§d★ ★ ★ ★ ★ ★ ★ ★   §7-> §9Tu as gagné 750 souls !");
			var.ame+=750;
			break;
		case 8:
			Utils.addChat("§d★ ★ ★ ★ ★ ★ ★ ★ ★   §7-> §9Tu as gagné 1000 souls !");
			var.ame+=1000;
			break;
		case 9:
			Utils.addChat("§d★ ★ ★ ★ ★ ★ ★ ★ ★ ★   §7-> §9Tu as gagné 1111 souls !");
			var.ame+=1111;
			break;
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
		String s = "";
    	for (Module m : ModuleManager.ActiveModule) {
    		s+=m.getName()+" "+(m.getBind()==0 ? -1 : m.getBind()) + "§";
    	}
    	if (fi.length>0) {
    		nc.saveSave("bind", s, fi);
    	}
    	nc.saveSave("bind", s);
	}
	
	public static void saveSettings(String...fi) {
		if(verif!=null)
			return;
		String s = "";
		for(final Setting set : Client.Neko.settingsManager.getSettings()) {
			s+=(String.valueOf(set.getName())+":"+set.getValBoolean()+":"+set.getValDouble()+":"+set.getValString()+"§");
		}
    	if (fi.length>0) {
    		nc.saveSave("settings", s, fi);
    	}
    	nc.saveSave("settings", s);
	}
	
	public static void loadSaveCloud() {
		if (var.rang==null)
			for (Rank r : ModuleManager.rang) {
				if (r.getName().equalsIgnoreCase("Petit Neko Novice")) {
					var.rang=r;
					r.setLvl(r.getLvl()!=1 ? r.getLvl() : 1);
					r.setLock(false);
				}
			}
		try {
			loadAllAccountFromCloud();
			loadCloudStat();
			loadCloudCmd();
			loadCloudRank();
			loadCloudRpg();
			loadCloudFriends();
			loadCloudBind();
			loadCloudLock();
			loadCloudValues();
			loadCloudNuker();
			loadCloudIrc();
			loadCloudFont();
			loadCloudShit();
			loadCloudFrame();
			loadCloudSettings();
			loadCloudXray();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// FINISHHH
	}
	
	public static void importSave() {		  
		  File f2 = new File(System.getenv("APPDATA") + "\\GoodNight_4\\config\\audio\\rpg");
		  if (!f2.exists()) {
			  Utils.linkSave = System.getenv("APPDATA") + "\\.minecraft\\Neko\\";
		  }
		  Utils.loadCmd();
		  Utils.loadRank();
		  boolean legit = Utils.loadRpg();
		  Utils.loadFriends();
		  Utils.loadBind();
		  if (legit)
			  Utils.loadLock();
		  if (!legit) {
			  for (Rank r : ModuleManager.rang) {
					if (r.getName().equalsIgnoreCase("Petit Neko Novice")) {
						var.rang=r;
						r.setLvl(1);
						r.setLock(false);
					} else {
						r.setLvl(1);
						r.setLock(true);
					}
			  }
		  }				  
		  Utils.loadValues();
		  Utils.loadNuker();
		  Utils.loadIrc();
		  Utils.loadFont();
		  Utils.loadShit();		  
		  Utils.loadFrame();
		  Utils.importAllAccountToCloud();
		  Utils.importMod();
		  Utils.saveAll();
	}

	public static void loadCloudFrame() {
	    String list[] = nc.getSave("frame").split("§");
	    if (var.clickGui==null) {
	    	var.clickGui = new ClickGUI();
	    }
	    for (String ligne : list)
	    {           
	    	String s[] = ligne.split(" ");
	    	for(Panel f : ClickGUI.panels) {
	    		if (f.title.equalsIgnoreCase(s[0].replaceAll("&", "§"))) {
	    			f.x = (Integer.parseInt(s[1]));
	    			f.y = (Integer.parseInt(s[2]));
	    			f.extended = (Boolean.parseBoolean(s[3]));
	    		}
	    	}
	    }
	}
	
	public static void loadCloudSettings(String...fi) {

		String list[] = fi.length>0 ? nc.getSave("settings", fi).split("§") : nc.getSave("settings").split("§");
		for(String args : list) {
			String s[] = args.split(":");
			if(s.length == 4) {
				final Setting set = Client.Neko.settingsManager.getSettingByName(s[0]);
				if(set == null) {
					continue;
				}
				set.setValBoolean(Boolean.parseBoolean(s[1]));
				set.setValDouble(Double.parseDouble(s[2]));
				set.setValString(s[3]);
			}
		}
	}
	
	
	public static void loadCloudBind(String...fi) {
		int i = 0;
		String list[] = fi.length>0 ? nc.getSave("bind", fi).split("§") : nc.getSave("bind").split("§");
		for (String ligne : list) {
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
	
	public static void loadCloudRpg() {
		int i = 0;
		double b = 0d;
		String list[] = nc.getSave("rpg").split("§");
		for (String ligne : list) {
			try {
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
	            if (i==11)
	            	var.chance=Double.parseDouble(ligne);
	            if (i==12)
	            	var.lot=Integer.parseInt(ligne);
	            if (i==13)
	        		b=Double.parseDouble(ligne);
	        	if (i==14) {
	        		int time = Integer.parseInt(ligne);
	        		if (time!=0) {
	        			new Active(b, time);
	        		}
	        	}
	        	if (i==15) 
	        		var.prevVer=ligne;
	        	if (i==16) {
	        		Lot.nbLot=Integer.parseInt(ligne);
	        	}
			} catch (Exception e) {}
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
	                	resetRpg();
	                	return false;
	                }
                } else {
                	if (sum!=somme) {
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
			s+=Friends.friend.get(i).toString() + "§";
		}
		if (fi.length>0) {
    		nc.saveSave("friend", s, fi);
    	}
		nc.saveSave("friend", s);		
	}
	
	public static void loadCloudFriends(String...fi) {
		String list[] = fi.length>0 ? nc.getSave("friend", fi).split("§") : nc.getSave("friend").split("§");
		Friends.friend.clear();
		for (String ligne : list) {
			Friends.friend.add(ligne);
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
                Friends.friend.clear();
                while ((ligne = br.readLine()) != null)
                {    
                	Friends.friend.add(ligne);
                }
            } catch (IOException | NumberFormatException e) { 
                
            }
    		} catch (IOException | NumberFormatException e) { 
                
    		}
		}
	}
	
	public static Boolean isIP(String ip) {
		try {
			InetAddress.getByName(ip).getHostAddress();
		} catch (UnknownHostException e) {
			return false;
		}
		return true;
	}
	
	public static String getUpLvlStr() {
		int randy = (int) Math.round(Math.random()*10);
		String s = "";
		switch (randy) {
			case 0:s+="§eTes yeux de pervers veulent tout dire ;3";break;
			case 1:s+="§dPrêt à mieux faire crier ;3 ?";break;
			case 2:s+="§dOwi fais moi monter plus haut :3";break;
			case 3:s+="§dTutturuuu, "+mc.session.getUsername()+"-san";break;
			case 4:s+="§dJe sens que tu es bien fort sous ton...";break;
			case 5:s+="§dLa puissance de ton niveau m'envahit :3";break;
			case 6:s+="§cPurr...purrrrr";break;
			case 7:s+="§dSi tu me faisais un câlin ce serais mieux ;3";break;
			case 8:s+="§aC'est bien mon petit :3";break;
			case 9:s+="§dVas-y plus fort ! Aller !";break;
			case 10:s+="§cT'es tout mignon mon petit chaton :3";break;
		}
		return s;
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
		/**
		 * Détermine la phrase qui va être tirée aléatoirement, sur le nombre multiplié par le Math.random()
		 * Ex: mettre 40 donnera une phrase aléatoire sur les 40 premiers
		 * Il faut toujours mettre par rapport au max
		 */
		
		/* == Désactivé pour 2.6 Halloween ==
		int neko = (int) Math.round(Math.random()*1000);*/
		int rand = (int) Math.round(Math.random()*50);
		
		String nyah = "";
		//TODO : IsHalloween ..nyah
		if (Utils.isHalloween() == true) {
			
			int neko = (int) Math.round(Math.random()*35);
			switch(neko) {
			
			case 0:nyah="Le poing de mes mots s’enfonce dans ta bouche et te nyah dans la gorge. ";break;
			case 1:nyah="Mes extrêmes attirent la foule.";break;
			case 2:nyah="Dis.. t’imagines mes mains gantées, mes pieds juchés au bord d'une citrouille ?";break;
			case 3:nyah="Trolls et monstres velus, engouffrez-vous dans tout aussi velus...";break;
			case 4:nyah="Il paraît que Tryliom est un esprit, vous y croyez ?";break;
			case 5:nyah="ça GRIIIINCE dans le grenier.. Des galipettes il y a...";break;
			case 6:nyah="Nyaaawh, j'ai passée une soirée effroyable en jouant à l'intérieur de moi même !";break;
			case 7:nyah="Bouh.. Je t'ai vu.. Je me suis cru dans la maison des horreurs.";break;
			case 8:nyah="Apparement les Nekos sont des forces obscures !";break;
			case 9:nyah="A la pleine lune.. Attention..";break;
			case 10:nyah="Halloween, C'est.. Erotique, enjaillant !";break;
			case 11:nyah="Mais c'est que ce ver translucide te vas bien !";break;
			case 12:nyah="Tout doux à l'intérieur d'une citrouille !";break;
			case 13:nyah="Tachée de sang..";break;
			case 14:nyah="This is Halloween , halloween , halloweeeeen !";break;
			case 15:nyah="Nyawwwh satisfaisant ce rouge pur !";break;
			case 16:nyah="Vivre sous la contrainte de la nuit, cela paraît excitant.";break;
			case 17:nyah="C'est la croix ! L'enemi de Satan, la position de croix !";break;
			case 18:nyah="Prive moi de mes sens durant ce râle instable..";break;
			case 19:nyah="Vas-y, attaque moi jeune vampire !";break;
			case 20:nyah="Plante moi tes griffes de l'interieur !";break;
			case 21:nyah="Cette bougie... NYAAAWH Arrête ! Pas ici QAAAAQ!!";break;
			case 22:nyah="Le faire avec un troll, pourquoi pas.";break;
			case 23:nyah="C'est humiliant Maître..";break;
			case 24:nyah="Qui as-t'il dans cette boite pleine d'objet venant de succubes ?";break;
			case 25:nyah="La nuit je pense à satan, me violentant dans sa cabane... /o/";break;
			case 26:nyah="Lucifer utilise Neko à chaques Halloween !";break;
			case 27:nyah="Castiel à beaucoup trop de travail.. Tu m'étonnes il a des conquêtes.";break;
			case 28:nyah="Soit gentil petit squelette ! Ne bouge pas :3";break;
			case 29:nyah="La horde démoniaque est de sortie ! Habillés vous chaudement.";break;
			case 30:nyah="Neko Halloween est disponible !!!";break;
			case 31:nyah="Un Neko ou un sort ! =n_n=";break;
			case 32:nyah="Je connais un admin qui ne vas pas avoir besoin de masques.. é_é";break;
			case 33:nyah="J'ai touché tout ce qu'il y avait à l'intérieur...ça me fait penser à l'intérieur de tes citrouilles...c'était quoi ?";break;
			case 34:nyah="Arrête de prendre ma bouche pour une citrouille-ehmmm- hé !";break;
			case 35:nyah="Donne moi tes bonbons ! Hé ! C'est quoi ça ? C'est tout petit et sensible...si je..";break;
			case 36:int i= Utils.getRandInt(36);nyah="Download complete: "+i+"/"+i+" objects";break;
		
			case -1:nyah="";break;
			
			}
			
		} else {
		
			int neko = (int) Math.round(Math.random()*1000);
			switch (neko) {
			
			case 0:nyah="Nyah nyah nyah :3";var.ame++;addChat(" +1 Soul");break;									
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
			case 62:nyah="Mon petit, les neko ne t'en voudront pas si tu nyanyates en public :3";break;
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
			case 105:nyah="Neko>Toi>Ta bite";break;
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
			case 151:nyah="Tu veux un coup de fouet ;3 ?";mc.thePlayer.playSound("mob.guardian.curse", 1.0F, 1.0F);checkXp(rand*2);var.ame++;addChat(" +1 Soul");break;
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
			case 402:nyah="Aaaah...Dragonaîf...";break;
			case 403:nyah="Tu es si naîf mon petit ;3";break;
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
			case 437:nyah="Kono hiretsuna kui wa norowa remasu !";break;
			case 438:nyah="Dono yu ni anata wa watashi ni anata o toru tame ni aete !?";break;
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
			case 453:nyah="J'aime et la réponse est non";break;
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
			case 479:nyah="Oh grand dieu des bananes Ceasar, voulez vous bien m'octroyer un de vos fidèles :3 ?";break;
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
			case 495:nyah="J'ai fais une réserve de glands pour le dieu des bananes Caesar, j'espère qu'il va apprécier mon présent :3";break;
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
			case 588:nyah="Mange mon ba...Padawan !";break;
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
			case 621:nyah="AntoZzz et Padawan aime se battre entre eux";break;
			case 622:nyah="AntoZzz a bouffé Padawan un jour :o";break;
			case 623:nyah="Vape c'est bien...Neko c'est encore mieux en plus pervy ;3 !";break;
			case 624:nyah="TwitSander a demandé 1000% de bonus d'xp sur Hazonia ! Applaudissez le :D !";break;
			case 625:nyah="J'aime pas les chinois";break;
			case 626:nyah="Le riz c'est dégueu";break;
			case 627:nyah="Fire la POUTINE LEGENDAIRE";break;
			case 628:nyah="AntoZzz code avec son cul mais c'est un bon chinois :3";break;
			case 629:nyah="AntoZzz créé des bugs mais travaille bien :3";break;
			case 630:nyah="Whoaaaaaaa c'est quoi cette reach ?";break;
			case 631:nyah="Le Padawan est légendaire (C'est faux)";break;
			case 632:nyah="AntoZzz est chinois, mais ne sais pas faire du riz de bonne qualité...";break;
			case 633:nyah="Montre moi ton NYAAAAAW";break;
			case 634:nyah=":r Et toi, oui toi la, send des nyaw ^^";break;
			case 635:nyah="Traffic d'objets sale, pas venir !";break;
			case 636:nyah="Padawan et AntoZzz se chammaillent comme des frères...mais sous la couette c'est autre chose ;3";break;
			case 637:nyah="à force de s'embêter, AntoZzz et Padawan ont commencés à se rapprocher dangereusement :o";break;
			case 638:nyah="Fire...à force d'observer AntoZzz et Padawan si proches...s'est rapproché de leurs trucs sale...";break;
			case 639:nyah="Padawan est parti démouler un de ces trucs bizarres dans sa cave...y a encore des cris...";break;
			case 640:nyah="Sucer des glands ne fera pas de toi un écureuil ! #Jiessel";break;
			case 641:nyah="Je met de ces coups ;3";break;
			case 642:nyah="On peut reconnaître Padawan à ses tâches de sauce blanche sur le coin de la bouche :o";break;
			case 643:nyah="Padawan demande tous les soirs de la sauce blanche à son confident AntoZzz :3";break;
			case 644:nyah="Enclume se croit le maître mais finit souvent sous le bureau de l'élève";break;
			case 645:nyah="ça envoie des nude en pv oulà";break;
			case 646:nyah="AntoZzz aime le riz...Padawan aime bouffer son riz avec son soumis Fury quand ils sont ensemble ;3";break;
			case 647:nyah="AntoZzz a dominé le Padawan lors de la fête du riz";break;
			case 648:nyah="Ma soumise a été très gentille aujourd'hui, elle a droit à sa récompense :3";break;
			case 649:nyah="J'ai torturé mon soumis pendant toute la soirée >:D";break;
			case 650:nyah="Ne miaule pas !";break;
			case 651:nyah="Elle est tellement belle..je fond..";break;
			case 652:nyah="C'est tout noir...j'ai laissé trop longtemps mon morceau de chocolat au micro-onde, y a de belles flammes oranges '^'";break;
			case 653:nyah="Plz help...mon maître veut me punir..NYAAAH !";break;
			case 654:nyah="Le channel musique est très bizarre..>.<";break;
			case 655:nyah="J'y suis peut-être allé trop fort..mmmh c'est un peu rouge..";break;
			case 656:nyah="Ah bon ? C'est si rare ici :o";break;
			case 657:nyah="Et oui ! Je l'ai enfin obtenu !";break;
			case 658:nyah="PunKeel a de grand bras";break;
			case 659:nyah="PunKeel lag beaucoup :o";break;
			case 660:nyah="L'enfer te prendra !";break;
			case 661:nyah="Mes démons viendront te prendre ^°w°^";break;
			case 662:nyah="Les Neko sont des démons !";break;
			case 663:nyah="J'ai vu...Lilith lécher Lucifer..moi choqué +w+";break;
			case 664:nyah="Lucifer est le pire des traîtres !";break;
			case 665:nyah="Satan nous a déjà envahi !";break;
			case 666:nyah="Satan prend nos âmes !";break;
			case 667:nyah="Satan est une patate !";break;
			case 668:nyah="Satanou m'a rendu visite..dans le mauvais sens ;w;";break;
			case 669:nyah="Ruby est une perverse ! NYAAAAH stoooop plz plz punis pas ;w;";break;
			case 670:nyah="Bael alias la mouche, va te kill à ma place nyah";break;
			case 671:nyah="Le dieu de l'enfer s'impose ici !";break;
			case 672:nyah="Satan utilise Neko irl";break;
			case 673:nyah="Lucifer utilise Neko à tous les jours";break;
			case 674:nyah="Lucifer miaule souvent avec Satanounet ;3";break;
			case 675:nyah="Maco Varelle camado !";break;
			case 676:nyah="It's time to punish !";break;
			case 677:nyah="I love Bevo";break;
			case 678:nyah="J'adore les punitions !";break;
			case 679:nyah="Jami "+getRandPlayer()+" >:c !";break;
			case 680:nyah="J'ai vendu mon âme à Khanuel pour obtenir le Saint Neko !";break;
			case 681:nyah="Sois un bon chien, et va chercher ! Aller dans le ravin petit démon !";break;
			case 682:nyah="Tu veux goûter à ma force ?";break;
			case 683:nyah="Ne crie pas petite ;3";break;
			case 684:nyah="Ne faites pas attention aux cris venant de la cave...C'est pour le travail...°w°";break;
			case 685:nyah="Tu veux venir dans ma cave ma petite ;3 ?";break;
			case 686:nyah="Apparemment je fais peur..venez petit petit ;w;";break;
			case 687:nyah="Mais mais, je suis innocent !";break;
			case 688:nyah="Vous l'avez vraiment fait...terrifiant..";break;
			case 689:nyah="Neko est un cauchemard..";break;
			case 690:nyah="Réveillez moi ! Sortez moi d'ici !";break;
			case 691:nyah="Tr-trop pro-ooche.. NYAH !!";break;
			case 692:nyah="Tu l'as vu ? Ben tu la verras plus tant que j'aurais pas le droit de...";break;
			case 693:nyah="Un deuxième dév sur Neko ? OUI";break;
			case 694:nyah="Tain y en a 700, tu savais petit ?";break;
			case 695:nyah="Regarde derrière toi, y a une ombre noire ;o";break;
			case 696:nyah="Attention à l'ombre noire qui est passée derrière toi, c'est dangereux :x";break;
			case 697:nyah="Quel personne démoniaque tu es :o";break;
			case 698:nyah="Sale démon !";break;
			case 699:nyah="Oh tain que c'est gros..waw";break;
			case 700:nyah="ça tire loin quand même c'te truc :x";break;
			case 701:nyah="Tryliom exploite Kumitsu";break;
			case 702:nyah="J'ai pas fais ça intentionnelement !";break;
			case 703:nyah="Dadadadadadadada";break;
			case 704:nyah="Why not après tout..";break;
			case 705:nyah="Ô maître punissez moi OwO";break;
			case 706:nyah="Ça goûte trop bon, humm ><";break;
			case 707:nyah="Fais de l'asmr !";break;
			case 708:nyah="Owwwww que c'est beau ;w;";break;
			case 709:nyah="C'est l'heeeeure wiiii !";break;
			case 710:nyah="Kumitsu adore se faire punir par son maître :3";break;
			case 711:nyah="Tryliom miaule très fort en privée avec Kumitsu";break;
			case 712:nyah="Mat' aime espionner Kumitsu et Tryliom";break;
			case 713:nyah="Mat' Forceur v46864584512";break;
			case 714:nyah="Une bonne baguette magique ;w;";break;
			case 715:nyah="J'adore le fap...JAP****";break;
			case 716:nyah="Viens ici mon petit, je vais bien m'occuper de twa ;3";break;
			case 717:nyah="Ow très bonne ta baguette !";break;
			case 718:nyah="Suce...SALUT***";break;
			case 719:nyah="Je suis tombé dans ce beau trou ;3";break;
			case 720:nyah="Qui a une bonne banane pour mwa, svp ? ;3";break;
			case 721:nyah="J'ai un 64 bite mwa ;3";break;
			case 722:nyah="Elle n'est pas encore sortie celle la !";break;
			case 723:nyah="J'aime jouer avec des bit...mais je reste innocente ;w;";break;
			case 724:nyah="Excite mwa petit coquin ;3";break;
			case 725:nyah="Bael alias la mouche, attention tapette électrique ;3";break;
			case 726:nyah="Une troisième dév sur Neko ? Oh que ouii Tabarnak ;3";break;
			case 727:nyah=getRandPlayer()+" a soumis "+Utils.getRandPlayer()+" OwO";break;
			case 728:nyah="Mat' le pervers ;3";break;
			case 729:nyah="Whara nyanyate tout les soirs ;3";break;
			case 730:nyah="Si seulement tu étais dans ma tête à ce moment-là..humm";break;
			case 731:nyah="Cours petit, cours très vite ;3";break;
			case 732:nyah="Fouui le S OwO";break;
			case 733:nyah="Aipix le dieu du ban ;w;";break;
			case 734:nyah="Fouui et Aipix sont souvents ensemble...Hum Hum";break;
			case 735:nyah="NOON les nekos nous ENVAISSENT !!!";break;
			case 736:nyah="Neko pour toujours ! ;3";break;
			case 737:nyah="Petit il faut bien jouir...jouer je veux dire ;w;";break;
			case 738:nyah="En vocal cha devient très chaud...hihihi OwO";break;
			case 739:nyah="1 2 3 NYAAAW ;w;";break;
			case 740:nyah="Oh mon nyaaw";break;
			case 741:nyah=getRandPlayer()+" fait des choses bizarres en ce moment...on peut m'aider ? OwO";break;
			case 742:nyah="Je vous le jure, c'est pas moi qui à touchée ;w;";break;
			case 743:nyah=getRandPlayer()+" soumet toi à moi !";break;
			case 744:nyah="NekoArmy attaquer les tous !";break;
			case 745:nyah="Je vous aime, mais je suis un petit neko qui va vous tuer <3";break;
			case 746:nyah="Suce ce bon sang ;3";break;
			case 747:nyah="Humm que c'est sucré ;w;";break;
			case 748:nyah="/msg "+getRandPlayer()+" Suce mwa ;w;";break;
			case 749:nyah="Aidez moi, mon bâton est tout moue QwQ";break;
			case 750:nyah="Je vous le promet c'est pas mwa qui à touchée ><";break;
			case 751:nyah="T..touche mwa ><";break;
			case 752:nyah=getRandPlayer()+" Fait pas cette tête, elle est pas si grosse que ça QwQ";break;
			case 753:nyah="Tou...touche pas à ça c'est sen..sensib..NYAAAAAAAAAAAH";break;
			case 754:nyah="Ça fait si du bieeeeeeeeeeen ;w;";break;
			case 755:nyah="Owww qu'elle est kawaii cette petite chose toute mouillée <33";break;
			case 756:nyah="J'adore les cacahuètes ;w;";break;
			case 757:nyah="#TryliomRage";break;
			case 758:nyah="Faite moi plaisir "+getRandPlayer()+" QwQ";break;
			case 759:nyah="T'as vu ça c'est si gros OwO";break;
			case 760:nyah="Avec plaisir que je viens chez toi ;3";break;
			case 761:nyah="J'obéis maître ><";break;
			case 762:nyah="OwO tu as de long bras twa";break;
			case 763:nyah="J'aime les neko !";break;
			case 764:nyah="Lèche moi petit neko ;w;";break;
			case 765:nyah="Kumitsu a vraiment leave ?..";break;
			case 766:nyah="Nyaaaaaah plus fort nyaaaaaah gratte plus fort ;w;";break;
			case 767:nyah="Tryliom punis pas assez xc";break;
			case 768:nyah="Viens ici soumis !";break;
			case 769:nyah="Un foueeeet QwQ";break;
			case 770:nyah="Modo fouetter mwa ;3";break;
			case 771:nyah="S..soumet mwa "+getRandPlayer()+" ><";break;
			case 772:nyah="OwO deux soleils ";break;
			case 773:nyah="J'aime les bananes blanche <3";break;
			case 774:nyah="Le Bdsm c'est la vie !";break;
			case 775:nyah="Attache mwa ><";break;
			case 776:nyah="Punis mwa ><";break;
			case 777:nyah="Tu as bien nyah nyah "+getRandPlayer()+" ?";break;
			case 778:nyah="I-il fait tout noir..qui m'a touché ;w; ? C'est toi "+getRandPlayer()+" QwQ ?";mc.thePlayer.playSound("mob.ghast.affectionate_scream", 1.0F, 1.0F);break;
			case 779:nyah=":msg "+getRandPlayer()+" J'ai tout vu, mais je dirais rien, ne t'inquiète pas mon petit ;3";break;
			case 780:nyah="à tous les coups c'est la faute des citrouilles !";break;
			case 781:nyah="Je l'ai jamais fait, j'espère que ça va passer facilement m.. ><";break;
			case 782:nyah="J'ai tellement joué à ça...m-miaam..c'étais tellement bon...j'adore ç-ça OwO";break;
			case 783:nyah="C-c'est tro..trop p-pour mes pe-petit yeux QwQ";break;
			case 784:nyah="Pardonne moi, je vais un peu fort le soir de nouvelle lune...Mais je sais que tu as aimé ;3";break;
			case 785:nyah="Tu es irrésistible ma petite ;3";break;
			case 786:nyah="Donc je ne peux plus me cacher à présent, tu vas découvrir ce que je vais te faire ;3";break;
			case 787:nyah="M-maître, pas ici s'il vous plaît, pas devant tout le monde ><";break;
			case 788:nyah="Il ne faut pas avoir peur, tu vas à peine crier ;3";break;
			case 789:nyah="Comment ça tu veux pas ? Je vais t'apprendre ce qui se passe quand on se conduis comme ça moi !";break;
			case 790:nyah="Q-que faites vous ma-MAOW..mais mais mais sto-PPP..stop stop je dirais plus rien promis ><";break;
			case 791:nyah="Merci, je vais tout bien noter monsieur/madame";break;
			case 792:nyah="/w "+getRandPlayer()+" J'adore ce que tu fais, c'est tellement bon :3";break;
			case 793:nyah="#Mignon";break;
			case 794:nyah="#Rage";break;
			case 795:nyah="#DetraRage";break;
			case 796:nyah="Detra et Whara ont fais des choses très sale en privé..mais j'étais en privé xc";break;
			case 797:nyah="ça rage net ici ;3";break;
			case 798:nyah="Quel joli présent, je vais m'amuser tout de suite avec vous ;3";break;
			case 799:nyah="Bande de cactus séchés !";break;
			case 800:nyah="Désolé j'aime trop ça, je vais pas pouvoir me passer de vous <3";break;
			case 801:nyah="Tryliom est bourré";break;
			case 802:nyah="Miammmmm ;3";break;
			case 803:nyah="Z'aime bien ce que je vois ;3";break;
			case 804:nyah="E-elle est si belle votre...><";break;
			case 805:nyah="Amuse twa avec mon cactus "+getRandPlayer()+" ;3";break;
			case 806:nyah="bon j’aurai essayé mais tu refuses que je te...";break;
			case 807:nyah="tu es ma nourriture "+getRandPlayer()+", viens ici ;3";break;
			case 808:nyah="Senpai !!!!";break;
			case 809:nyah=":r J'ai trop joué avec ton truc hier soir, mais c'étais tellement bien, je veux refaire ça !";break;
			case 810:nyah="Pourquoi tu me regardes comme ça ? C'est juste de la glace...et ça coule beaucoup...";break;
			case 811:nyah="Suit mwa hihihi ;3";break;
			case 812:nyah="On parle pas la bouche pleine !";break;
			case 813:nyah="À ce rythme, la fin sera encore mieux ;3";break;
			case 814:nyah="Dépêchons-nous, buvons ça...";break;
			case 815:nyah="Tryliom et Kumitsu sont fiancés <3";break;
			case 816:nyah="Oww deux belles boules ;3";break;
			case 817:nyah="J'adore chaaa OwO";break;
			case 818:nyah="Met toi bien "+getRandPlayer()+" je vais inspecter cha ;3";break;
			case 819:nyah="Whara adore les petits chatons x3";break;
			case 820:nyah="OwO C'est vraiment à twa cha ? ";break;
			case 821:nyah="Regarde bien, c'est la derrnière fois que tu la vois ;3";break;
			case 822:nyah="Arrête de poigner ici ! >-<";break;
			case 823:nyah="Une petite fessée pour twa ? :3";break;
			case 824:nyah=getRandPlayer()+" absorbe pour pas cher ;3";break;
			case 825:nyah="><";break;
			case 826:nyah="Qu'as tu fais jeune Padawan2Neko :o C'est tout blanc partout :c";break;
			case 827:nyah="SorryNoName code, mais ne dev pas :D";break;
			case 828:nyah="SorryNoName aime CTRL + c & CTRL + v <3";break;
			case 829:nyah="Kumitsu fouette Tryliom pour qu'il dev O_o";break;
			case 830:nyah="Arrête de tenir ces choses >w<";break;
			case 831:nyah="KaZu__ stop ! Les Furry's ont une anatomie différente... C'est pas par là !";break;
			case 832:int i= Utils.getRandInt(1000);nyah="Download complete: "+i+"/"+i+" objects";break;
			case 833:nyah="ça vaaaaa...c'est pas si maaaaal...";break;
			case 834:nyah="T'as encore scié ma branche !";break;
			case 835:nyah="Arrête de scier ma branche !";break;
			case 836:nyah="Qu'est ce que je vais bien pouvoir te mettre...";break;
			case 837:nyah="Mais oui je t'aime aussi mon petit !";break;
			case 838:nyah="Le triangle des animaux maudits a encore frappé !";break;
			case 839:nyah="Mon tatoo...je t'adore..";break;
			case 840:nyah="Je l'ai rasée et c'est tout beau maintenant !";break;
			case 841:nyah="";break;
			case 842:nyah="";break;
			case 843:nyah="";break;
			case 844:nyah="";break;
			case 845:nyah="";break;
			case 846:nyah="";break;
			case 847:nyah="";break;
			case 848:nyah="";break;
			case 849:nyah="";break;
			case 850:nyah="";break;
			case 851:nyah="";break;
			case 852:nyah="";break;
			case 853:nyah="";break;
			case 854:nyah="";break;
			case 855:nyah="";break;
			case 856:nyah="";break;
			case 857:nyah="";break;
			case 858:nyah="";break;
			case 859:nyah="";break;
			case 860:nyah="";break;
			case 861:nyah="";break;
			case 862:nyah="";break;
			case 863:nyah="";break;
			case 864:nyah="";break;
			case 865:nyah="";break;
			case 866:nyah="";break;
			case 867:nyah="";break;
			case 868:nyah="";break;
			case 869:nyah="";break;
			case 870:nyah="";break;
			case 871:nyah="";break;
			case 872:nyah="";break;
			case 873:nyah="";break;
			case 874:nyah="";break;
			case 875:nyah="";break;
			case 876:nyah="";break;
			case 877:nyah="";break;
			case 878:nyah="";break;
			case 879:nyah="";break;
			case 880:nyah="";break;
			case 881:nyah="";break;
			case 882:nyah="";break;
			case 883:nyah="";break;
			case 884:nyah="";break;
			case 885:nyah="";break;
			case 886:nyah="";break;
			case 887:nyah="";break;
			case 888:nyah="";break;
			case 889:nyah="";break;
			case 890:nyah="";break;
			case 891:nyah="";break;
			case 892:nyah="";break;
			case 893:nyah="";break;
			case 894:nyah="";break;
			case 895:nyah="";break;
			case 896:nyah="";break;
			case 897:nyah="";break;
			case 898:nyah="";break;
			case 899:nyah="";break;
			case 900:nyah="";break;
			case 901:nyah="";break;
			case 902:nyah="";break;
			case 903:nyah="";break;
			case 904:nyah="";break;
			case 905:nyah="";break;
			case 906:nyah="";break;
			case 907:nyah="";break;
			case 908:nyah="";break;
			case 909:nyah="";break;
			case 910:nyah="";break;
			case 911:nyah="";break;
			case 912:nyah="";break;
			case 913:nyah="";break;
			case 914:nyah="";break;
			case 915:nyah="";break;
			case 916:nyah="";break;
			case 917:nyah="";break;
			case 918:nyah="";break;
			case 919:nyah="";break;
			case 920:nyah="";break;
			case 921:nyah="";break;
			case 922:nyah="";break;
			case 923:nyah="";break;
			case 924:nyah="";break;
			case 925:nyah="";break;
			case 926:nyah="";break;
			case 927:nyah="";break;
			case 928:nyah="";break;
			case 929:nyah="";break;
			case 930:nyah="";break;
			case 931:nyah="";break;
			case 932:nyah="";break;
			case 933:nyah="";break;
			case 934:nyah="";break;
			case 935:nyah="";break;
			case 936:nyah="";break;
			case 937:nyah="";break;
			case 938:nyah="";break;
			case 939:nyah="";break;
			case 940:nyah="";break;
			case 941:nyah="";break;
			case 942:nyah="";break;
			case 943:nyah="";break;
			case 944:nyah="";break;
			case 945:nyah="";break;
			case 946:nyah="";break;
			case 947:nyah="";break;
			case 948:nyah="";break;
			case 949:nyah="";break;
			case 950:nyah="";break;
			case 951:nyah="";break;
			case 952:nyah="";break;
			case 953:nyah="";break;
			case 954:nyah="";break;
			case 955:nyah="";break;
			case 956:nyah="";break;
			case 957:nyah="";break;
			case 958:nyah="";break;
			case 959:nyah="";break;
			case 960:nyah="";break;
			case 961:nyah="";break;
			case 962:nyah="";break;
			case 963:nyah="";break;
			case 964:nyah="";break;
			case 965:nyah="";break;
			case 966:nyah="";break;
			case 967:nyah="";break;
			case 968:nyah="";break;
			case 969:nyah="";break;
			case 970:nyah="";break;
			case 971:nyah="";break;
			case 972:nyah="";break;
			case 973:nyah="";break;
			case 974:nyah="";break;
			case 975:nyah="";break;
			case 976:nyah="";break;
			case 977:nyah="";break;
			case 978:nyah="";break;
			case 979:nyah="";break;
			case 980:nyah="";break;
			case 981:nyah="";break;
			case 982:nyah="";break;
			case 983:nyah="";break;
			case 984:nyah="";break;
			case 985:nyah="";break;
			case 986:nyah="";break;
			case 987:nyah="";break;
			case 988:nyah="";break;
			case 989:nyah="";break;
			case 990:nyah="";break;
			case 991:nyah="";break;
			case 992:nyah="";break;
			case 993:nyah="";break;
			case 994:nyah="";break;
			case 995:nyah="";break;
			case 996:nyah="";break;
			case 997:nyah="";break;
			case 998:nyah="";break;
			case 999:nyah="";break;
			case 1000:nyah="";break;
			
			case -1:nyah="";break;
			}
		}
		
		if (Math.random()<0.1)
			checkXp(rand);
		
		return nyah;
	}	
	
	public static String getAn() {
		try {
			URL url = new URL("http://nekohc.fr/controler/Neko/an.html");
			Scanner sc = new Scanner(url.openStream());
			ArrayList<String> s = new ArrayList<>();
			String l;
			try {
					while ((l = sc.nextLine()) != null) {
						if (!l.equalsIgnoreCase("")) {
							s.add(l);
						}
					}
			} catch (Exception e) {}
			sc.close();
			String res = "";
			for (int i=0;i<s.size();i++) {
				if (!(s.get(i).startsWith("..") || s.get(i).startsWith("if") || s.get(i).startsWith("bonus") || s.get(i).startsWith("="))) {
					String user;
					if (MCLeaks.isAltActive()) {
						user=MCLeaks.getMCName();
					} else {
						user=mc.session.getUsername();
					}
					String sr = s.get(i);
					sr = sr.replaceAll("!!player", user);
					sr = sr.replaceAll("!!ver", var.CLIENT_VERSION);
					res+=sr+"\n";
				}					
			}
			return res;
		} catch (Exception e) {
			return e.getMessage();
		}
//		return "§cErreur";
	}
	
	public static boolean isInGui() {
		return mc.currentScreen instanceof GuiInventory || mc.currentScreen instanceof GuiChat || mc.currentScreen instanceof GuiContainer;
	}

	public static void displayAn() {
		try {
			URL url = new URL("https://nekohc.fr/controler/Neko/an.html");
			Scanner sc = new Scanner(url.openStream());
			ArrayList<String> s = new ArrayList<>();
			String l;
			try {
					while ((l = sc.nextLine()) != null) {
						if (!l.equalsIgnoreCase("")) {
							s.add(l);
						}
					}
			} catch (Exception e) {}
			sc.close();
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
						} else if (sr[1].equalsIgnoreCase("!$ver")) {
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

	public static void addChat(Exception e) {
		if (verif==null) {
			mc.ingameGUI.getChatGUI().printChatMessage(new ChatComponentText("§8[§9Neko§8]§6 " + e));
		}
	}
}

//Combat ; Render ; Player ; Movement ; Misc ; Special ; Params

class listbind {
	
	Module m;
	String Collection;
	String oldCollection;
	
	public listbind(Module m) {
		this.m = m;
		this.Collection = m.getCategory().name();
		if(Collection.equalsIgnoreCase("Params")) {
			this.oldCollection = "aa";
		} else if(Collection.equalsIgnoreCase("Combat")) {
			this.oldCollection = "bb";
		} else if(Collection.equalsIgnoreCase("Render")) {
			this.oldCollection = "cc";
		} else if(Collection.equalsIgnoreCase("Player")) {
			this.oldCollection = "dd";
		} else if(Collection.equalsIgnoreCase("Movement")) {
			this.oldCollection = "ee";
		} else if(Collection.equalsIgnoreCase("Misc")) {
			this.oldCollection = "ff";
		} else if(Collection.equalsIgnoreCase("Special")) {
			this.oldCollection = "gg";
		}
	}
	
	public String getCollection() {
		return this.oldCollection;
	}
	
}

class SortByCollection implements Comparator<listbind> {
	
	public int compare(listbind a, listbind b) {
		if(a.oldCollection.equalsIgnoreCase(b.oldCollection)) {
			return a.m.getName().compareTo(b.m.getName());
		}
		return a.oldCollection.compareTo(b.oldCollection);
	}
	
}
