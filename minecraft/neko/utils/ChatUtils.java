package neko.utils;

import java.awt.Desktop;
import java.io.File;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Properties;
import java.util.Scanner;
import java.util.UUID;
import java.util.Vector;

import org.darkstorm.minecraft.gui.theme.simple.SimpleTheme;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;

import com.mojang.authlib.GameProfile;

import neko.Client;
import neko.dtb.Alt;
import neko.dtb.RequestThread;
import neko.gui.InGameGui;
import neko.guicheat.GuiManager;
import neko.lock.Lock;
import neko.module.Category;
import neko.module.Module;
import neko.module.ModuleManager;
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
import neko.module.modules.misc.Antiafk;
import neko.module.modules.misc.AutoMLG;
import neko.module.modules.misc.CallCmd;
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
import neko.module.modules.player.Autoarmor;
import neko.module.modules.player.Build;
import neko.module.modules.player.Cheststealer;
import neko.module.modules.player.Fasteat;
import neko.module.modules.player.Fire;
import neko.module.modules.player.Nuker;
import neko.module.modules.player.PushUp;
import neko.module.modules.player.Velocity;
import neko.module.modules.render.HUD;
import neko.module.modules.render.ItemESP;
import neko.module.modules.render.NekoChat;
import neko.module.modules.render.Paint;
import neko.module.modules.render.Power;
import neko.module.modules.render.Radar;
import neko.module.modules.render.Tracers;
import neko.module.modules.render.Wallhack;
import neko.module.modules.render.Water;
import neko.module.modules.render.WorldTime;
import neko.module.modules.render.Xray;
import neko.module.modules.special.DropShit;
import neko.module.modules.special.FireTrail;
import neko.module.modules.special.PunKeel;
import neko.module.modules.special.Pyro;
import neko.module.modules.special.Reflect;
import neko.module.modules.special.SpamBot;
import neko.module.modules.special.TpBack;
import neko.module.modules.special.VanillaTp;
import neko.module.other.Active;
import neko.module.other.Bloc;
import neko.module.other.Event;
import neko.module.other.HackerDetector;
import neko.module.other.Irc;
import neko.module.other.PyroThread;
import neko.module.other.Rank;
import neko.module.other.RmRank;
import neko.module.other.Trade;
import neko.module.other.enums.BowMode;
import neko.module.other.enums.Chat;
import neko.module.other.enums.EventType;
import neko.module.other.enums.Form;
import neko.module.other.enums.IrcMode;
import neko.module.other.enums.Rate;
import neko.module.other.enums.SpeedEnum;
import net.mcleaks.Callback;
import net.mcleaks.MCLeaks;
import net.mcleaks.ModApi;
import net.mcleaks.RedeemResponse;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiNewChat;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.multiplayer.ServerAddress;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.network.NetHandlerLoginClient;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.EnumConnectionState;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.handshake.client.C00Handshake;
import net.minecraft.network.login.client.C00PacketLoginStart;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.network.play.client.C03PacketPlayer.C04PacketPlayerPosition;
import net.minecraft.network.play.client.C09PacketHeldItemChange;
import net.minecraft.network.play.client.C10PacketCreativeInventoryAction;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Session;
import net.minecraft.world.WorldSettings.GameType;

public class ChatUtils {
	Minecraft mc = Minecraft.getMinecraft();
	Client var = Client.getNeko();
	String var3;
	String args[];
	int xp;
	String error;
	String err = "§c§lErreur, valeur incorrecte";
	Irc irc;
	
	public ChatUtils() {}
	
	public void doCommand(String var1) {	
		var3 = var1;
		xp = Utils.getRandInt(5);
		error = "§c§lErreur: Essayez "+var.prefixCmd+"help";
		irc = Irc.getInstance();
		
		if (var.onlyrpg.isActive()) {
			boolean valid=false;
			for (String cmd : var.onlyrpg.getCmd()) {
				if (var3.startsWith(var.prefixCmd+cmd) || !var3.startsWith(var.prefixCmd) || var3.startsWith(irc.getPrefix())) {
					valid=true;
				}
			}
			if (!valid) {
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
				mc.displayGuiScreen((GuiScreen)null);
				return;
			}
		}
		if (irc.isOn() && !var3.startsWith(Client.getNeko().prefixCmd)) {
			boolean inIrc=false;	
			
			if (irc.getMode()==IrcMode.Only && (!var3.startsWith("/") || var3.startsWith("//r") || var3.startsWith("//w") || var3.startsWith("//m") || var3.startsWith("//msg"))) {
				if (!(var3.startsWith("//r") || var3.startsWith("//w") || var3.startsWith("//m") || var3.startsWith("//msg")))
					var3=Irc.getInstance().getPrefix()+var3;
				inIrc=true;			
			}
			if (irc.getMode()!=IrcMode.Only && (irc.getMode()==IrcMode.Hybride && !var3.startsWith(irc.getPrefix()) || irc.getMode()!=IrcMode.Hybride && var3.startsWith(irc.getPrefix())) && !var3.startsWith(Client.getNeko().prefixCmd) && (!var3.startsWith("/") || var3.startsWith("//r") || var3.startsWith("//w") || var3.startsWith("//m") || var3.startsWith("//msg"))) {
				var3=Irc.getInstance().getPrefix()+var3;
				inIrc=true;
			}
			// Chat normal sans only
			if (irc.getMode()==IrcMode.Normal && var3.startsWith(irc.getPrefix())) {
				inIrc=true;
				String s ="";
				for (int i=0;i<Irc.getInstance().getPrefix().length();i++)
					s+=".";
				
				var3 = var3.replaceFirst(s, "");
			}
			if (irc.getMode()==IrcMode.Hybride && var3.startsWith(irc.getPrefix()) && (!var3.startsWith("/") || var3.startsWith("//r") || var3.startsWith("//w") || var3.startsWith("//m") || var3.startsWith("//msg"))) {
				String s ="";
				for (int i=0;i<Irc.getInstance().getPrefix().length();i++)
					s+=".";
				
				var3 = var3.replaceFirst(s, "");
			}			
			if (inIrc && Utils.verif==null) {
				irc.addChatIrc(var3);
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
				mc.displayGuiScreen((GuiScreen)null);
				return;
			}
		}	
		
		if (var3.startsWith(var.prefixCmd) && Utils.verif==null) {
			args = var3.split(" ");
			if (args[0].equalsIgnoreCase(var.prefixCmd)) {
				Utils.addChat(error); 
				Utils.checkXp(xp);
			}
			
			if (args.length==1) {
				String s = args[0].replaceFirst(var.prefixCmd, "").toLowerCase();		
				for (Module m : ModuleManager.ActiveModule) {
					if (m.getName().toLowerCase().equalsIgnoreCase(s) && !Utils.isLock(m.getName())) {
						Utils.toggleModule(s);
						Utils.checkXp(xp);
						mc.ingameGUI.getChatGUI().addToSentMessages(var3);
						this.mc.displayGuiScreen((GuiScreen)null);
						return;
					} else if (m.getName().toLowerCase().equalsIgnoreCase(s) && Utils.isLock(m.getName())) {
						Utils.addWarn(m.getName());
					}
				}
				
			}
			
			for (Lock l : ModuleManager.Lock) {
				String s = l.getName();
				if (var3.startsWith(s.replace("--", var.prefixCmd)) && Utils.isLock(s)) {
					Utils.addWarn(s);
					return;
				}
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd + "mode")) {
				if (args.length==1) {							
					Utils.addChat(error);
				} else {
					if (args[1].equalsIgnoreCase("player")) {
						var.mode="Player";
						Utils.addChat("§aLe mode "+args[1]+" a été activé !");
					} else if (args[1].equalsIgnoreCase("mob")) {
						var.mode="Mob";
						Utils.addChat("§aLe mode "+args[1]+" a été activé !");
					} else if (args[1].equalsIgnoreCase("all")) {
						var.mode="All";
						Utils.addChat("§aLe mode "+args[1]+" a été activé !");
					} else {
						Utils.addChat(error);
					}
					
				}
				Utils.checkXp(xp);
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}

			if (args[0].equalsIgnoreCase(var.prefixCmd + "velocity") || args[0].equalsIgnoreCase(var.prefixCmd + "velo")) {
				Velocity v = Velocity.getVelocity();
				if (args.length==1) {
					Utils.toggleModule("Velocity");
				} else if (args[1].equalsIgnoreCase("horizontal") || args[1].equalsIgnoreCase("hor")) {
					try {
						double d = Double.parseDouble(args[2]);
						v.setHcoeff(d);            		
						Utils.addChat(Utils.setColor("Le coefficient de knockback en horizontal a été changé en "+args[2]+" !", "§a"));
					} catch (Exception e) {
						Utils.addChat(err);
					}
				} else if (args[1].equalsIgnoreCase("vertical") || args[1].equalsIgnoreCase("ver")) {
					try {
						double d = Double.parseDouble(args[2]);
						v.setVcoeff(d);           		
						Utils.addChat(Utils.setColor("Le coefficient de knockback en vertical a été changé en "+args[2]+" !", "§a"));
					} catch (Exception e) {
						Utils.addChat(err);
					}
				} else {
					try {
						double d = Double.parseDouble(args[1]);
						v.setHcoeff(d);
						v.setVcoeff(d);
						Utils.addChat(Utils.setColor("Le coefficient de knockback a été changé en "+args[1]+" !", "§a"));
					} catch (Exception e) {
						Utils.addChat(err);
					}
				}
				Utils.checkXp(xp);
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"myping") || args[0].equalsIgnoreCase(var.prefixCmd+"lag")) {
				for (Object o : mc.theWorld.playerEntities) {
					if (o instanceof EntityPlayer) {
						int ping=-1;
						EntityPlayer en = (EntityPlayer) o;
						try {
							NetworkPlayerInfo npi = (NetworkPlayerInfo) mc.getNetHandler().getPlayerInfoMap().get(en.getGameProfile().getId());
							ping = npi.getResponseTime();
						} catch (Exception e) {						
							
						}
						boolean isMe=false;
						String s = en.getName();
						if (s.equalsIgnoreCase(mc.session.getUsername()))
							isMe=true;
						if (MCLeaks.isAltActive())
							if (MCLeaks.getMCName().equalsIgnoreCase(s))
								isMe=true;
						if (isMe) {
							Utils.addChat("§7Votre ping [§c"+en.getName()+"§7]: §c"+ping+"§7ms");
							break;
						}
					}
				}
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
			
			if (var3.startsWith(var.prefixCmd+"myip")) {
				new Thread(new Runnable() {
					public void run() {
						try {
					URL url = new URL("http://nekohc.fr/controler/Neko/ip.php");
					Scanner sc = new Scanner(url.openStream());
					String l;
					try {
						while ((l = sc.nextLine()) != null) {
							Utils.addChat("Votre adresse IP: "+l);
							break;
						}
					} catch (Exception e) {}
					sc.close();
				} catch (Exception e) {
					Utils.addChat("§cErreur");
				}
					}
				}).start();				
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
			//TODO: BAN
			if (var3.startsWith(var.prefixCmd+"ban")) {
				if (args.length>=3 && !Event.mdp.isEmpty()) {
					ArrayList<String> list = new ArrayList<>();
					list.add(args[1]);
					String s = args[2];
					if (args.length>3) {
						for (int i=3;i<args.length;i++)
							s+=" "+args[i];
					}
					list.add(s);
					new RequestThread("ban", list).start();
				} else {
					if (Event.mdp.isEmpty()) {
						Utils.addChat(Utils.setColor("Erreur, pas de mot de passe entré", "§c"));
					} else
						Utils.addChat(Utils.setColor("Erreur de syntaxe: "+var.prefixCmd+"ban <Nom du joueur> <Raison>", "§c"));
				}
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
			
			if (var3.startsWith(var.prefixCmd+"mute")) {
				if (args.length>=3 && !Event.mdp.isEmpty()) {
					ArrayList<String> list = new ArrayList<>();
					list.add(args[1]);
					String s = args[2];
					if (args.length>3) {
						for (int i=3;i<args.length;i++)
							s+=" "+args[i];
					}
					list.add(s);
					new RequestThread("mute", list).start();
				} else {
					if (Event.mdp.isEmpty()) {
						Utils.addChat(Utils.setColor("Erreur, pas de mot de passe entré", "§c"));
					} else
						Utils.addChat(Utils.setColor("Erreur de syntaxe: "+var.prefixCmd+"mute <Nom du joueur> <Raison>", "§c"));
				}
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
			
			if (var3.startsWith(var.prefixCmd+"unmute")) {
				if (args.length==2 && !Event.mdp.isEmpty()) {
					ArrayList<String> list = new ArrayList<>();
					list.add(args[1]);
					new RequestThread("unmute", list).start();
				} else {
					if (Event.mdp.isEmpty()) {
						Utils.addChat(Utils.setColor("Erreur, pas de mot de passe entré", "§c"));
					} else
						Utils.addChat(Utils.setColor("Erreur de syntaxe: "+var.prefixCmd+"unmute <Nom du joueur>", "§c"));
				}
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
			
			if (var3.startsWith(var.prefixCmd+"proxy")) {
				if (args.length==1) {
					Utils.addChat("§cErreur, syntaxe correcte: "+Utils.setColor(var.prefixCmd+"proxy <HostIP> <Port>", "§c"));
				} else if (args[1].equalsIgnoreCase("reset")) {
					Properties props = System.getProperties();
					props.setProperty("proxySet", "false" );
			    	props.setProperty("socksProxyHost", "");
			    	props.setProperty("socksProxyPort", "");
			    	System.setProperties(props);
					Utils.addChat("§aVous vous êtes déconnecté du proxy");
				} else {
					String host = args[1];
					String port = "1080";
					if (args.length==3) {
						port = args[2];
					}
					Properties props = System.getProperties();
					props.setProperty("proxySet", "true" );
			    	props.setProperty("socksProxyHost", host);
			    	props.setProperty("socksProxyPort", port);
			    	System.setProperties(props);
			    	mc.setProxy(new Proxy(Type.SOCKS, new InetSocketAddress(host, Integer.parseInt(port))));
					Utils.addChat("§aVous vous êtes connecté à "+host+":"+port);
				}
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"listping")) {
				for (Object o : mc.theWorld.playerEntities) {
					if (o instanceof EntityPlayer) {
						int ping=-1;
						EntityPlayer en = (EntityPlayer) o;
						try {
							NetworkPlayerInfo npi = (NetworkPlayerInfo) mc.getNetHandler().getPlayerInfoMap().get(en.getGameProfile().getId());
							ping = npi.getResponseTime();
						} catch (Exception e) {						
							
						}
						Utils.addChat("§7Ping's §c"+en.getName()+"§7: §a"+ping+"ms");
					}
				}
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd + "ping")) {
				Ping p = Ping.getPing();
				if (args[1].equalsIgnoreCase("delay")) {
					try {
						Ping.getPing().setDelay(Integer.parseInt(args[1])<0 ? 0 : Integer.parseInt(args[1]));
						Utils.addChat("§aPing mis à "+Ping.getPing().getDelay()+" !");
					} catch (Exception e) {
						Utils.addChat(err);
					}
				} else if (args[1].equalsIgnoreCase("random")) {
					if (p.isRandom()) {
						Utils.addChat("§cPing Random désactivé");
					} else {
						Utils.addChat("§aPing Random activé");
					}
					p.setRandom(!p.isRandom());
				} else if (args[1].equalsIgnoreCase("freezer")) {
					if (p.isFreezer()) {
						Utils.addChat("§cPing Freezer désactivé");
					} else {
						Utils.addChat("§aPing Freezer activé");
					}
					p.setFreezer(!p.isFreezer());
				} else {
					try {
						Ping.getPing().setDelay(Integer.parseInt(args[1])<0 ? 0 : Integer.parseInt(args[1]));
						Utils.addChat("§aPing mis à "+Ping.getPing().getDelay()+" !");
					} catch (Exception e) {
						Utils.addChat(err);
					}
				}
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd + "tp")) {
				if (args.length==1) {
					Utils.addChat(error);
				} else {
					TpUtils tp = new TpUtils();
					EntityPlayer en = Utils.getPlayer(args[1]);
					if (en!=null)
						tp.doTpAller(en, en.posX, en.posY, en.posZ, false, 1);
					else
						Utils.addChat("§cErreur, ce joueur n'existe pas");
					mc.ingameGUI.getChatGUI().addToSentMessages(var3);						    			            		
				}
			}
			if (args[0].equalsIgnoreCase(var.prefixCmd+"friend") || args[0].equalsIgnoreCase(var.prefixCmd + "fr") || args[0].equalsIgnoreCase(var.prefixCmd + "ft")) {						
				int n=0;
				if (args.length==1) {
					if (args[0].equalsIgnoreCase(var.prefixCmd + "ft")) {
						if (!Friends.team) {
							Utils.addChat("§aAjout auto de player dans votre team activé !");
							Friends.team=true;
						} else if (Friends.team) {
							Utils.addChat("§cAjout auto de player dans votre team désactivé !");
							Friends.team=false;
						}
					} else
					Utils.addChat(error);
				} else if (args[1].equalsIgnoreCase("clear")) {
					Utils.addChat("§aTa liste d'amis a été clear !");
					Friends.friend.clear();
					Utils.saveFriends();
				} else if (args[1].equalsIgnoreCase("list")) {
					for (int i=0;i<Friends.friend.size();i++) {
						Utils.addChat(Friends.friend.get(i));
						n++;
					}
					if (n==0) {
						Utils.addChat("§cDésolé, tu n'as pas d'amis...gentil "+var.rang.getName()+" :3");
					}
				} else if (args[1].equalsIgnoreCase("radius")) {
					int l=0;
					if (args.length==2) {
						Utils.addChat(error);
					} else {
						for(Iterator<Object> entities = Minecraft.getMinecraft().theWorld.playerEntities.iterator(); entities.hasNext();) {
				            Object theObject = entities.next();
				            if(theObject instanceof EntityLivingBase) {
				                EntityLivingBase entity = (EntityLivingBase) theObject;
				               
				                if(entity instanceof EntityPlayerSP) continue;
				                if(Minecraft.getMinecraft().thePlayer.getDistanceToEntity(entity) <= Double.parseDouble(args[2])) {
				                    if(entity.isEntityAlive()) {
			                    			Friends.addFriend(entity.getName());	
			                    			l++;						                    		
				                    		Utils.checkXp(xp);
				                    }
				                }
				            }
						}
						Utils.addChat(l+" joueurs ajoutés/retirés !");
					}
				} else if (args[1].equalsIgnoreCase("team")) {
						if (!Friends.team) {
							Utils.addChat("§aAjout auto de player dans votre team activé !");
							Friends.team=true;
						} else if (Friends.team) {
							Utils.addChat("§cAjout auto de player dans votre team désactivé !");
							Friends.team=false;
						}
						
				} else if (Friends.isFriend(args[1])) {
					Utils.addChat("§5"+args[1] + "§c a été retiré de ta liste d'amis !");
					Friends.addFriend(args[1]);
					Utils.checkXp(xp);
				} else {
					Utils.addChat("§5"+args[1] + "§a a été ajouté à ta liste d'amis !"); 	
					Friends.addFriend(args[1]);
					Utils.checkXp(xp);
				}
				Utils.checkXp(xp);
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
			
			//TODO: Help
			if (args[0].equalsIgnoreCase(var.prefixCmd+"help")) {
				// Afficher la liste des commandes non simplifiées
				if (args.length==1) {
					Utils.displayHelp(1);							
					Utils.checkXp(xp);
					mc.ingameGUI.getChatGUI().addToSentMessages(var3);
					int rand = (int) Math.round(Math.random()*1000);
					if (!var.achievementHelp) {
						var.achievementHelp=true;
						Utils.addChat("§dAchievement Help get !§b +"+rand+"xp !");								
						Utils.checkXp(rand);
					}

				} else if (args[1].equalsIgnoreCase("disc")) {
					Utils.addChat("========================================");
					Utils.addChat2("§6"+var.prefixCmd+"Disc <String>", var.prefixCmd+"disc ", "§7Joue le disc choisit", false, Chat.Summon);
					Utils.addChat2("§6"+var.prefixCmd+"Disc <String> <Double>", var.prefixCmd+"disc ", "§7Joue le disc et modifie le volume sur la distance", false, Chat.Summon);
					Utils.checkXp(xp);
					mc.ingameGUI.getChatGUI().addToSentMessages(var3);
				} else if (args[1].equalsIgnoreCase("clickaim")) {
					Utils.addChat("========================================");
					Utils.addChat2("§6"+var.prefixCmd+"ClickAim multiaura", var.prefixCmd+"clickaim ", "§7Change le mode multiaura au singleaura et inversement", false, Chat.Summon);
					Utils.addChat2("§6"+var.prefixCmd+"ClickAim <Double>", var.prefixCmd+"clickaim", "§7Modifie la portée du ClickAim", false, Chat.Summon);
					Utils.checkXp(xp);
					mc.ingameGUI.getChatGUI().addToSentMessages(var3);
				} else if (args[1].equalsIgnoreCase("firetrail")) {
					Utils.addChat("========================================");
					Utils.addChat2("§6"+var.prefixCmd+"FireTrail large", var.prefixCmd+"firetrail large", "§7Change l'épaisseur de la trainée de plus large à plus fine", false, Chat.Summon);
					Utils.checkXp(xp);
					mc.ingameGUI.getChatGUI().addToSentMessages(var3);
				} else if (args[1].equalsIgnoreCase("Build")) {
					Utils.addChat("========================================");
					Utils.addChat2("§6"+var.prefixCmd+"Build", var.prefixCmd+"Build", "§7Permet de poser des blocs format 3x3 autour de vous, ces options sont disponibles", false, Chat.Summon);
					Utils.addChat2("§6"+var.prefixCmd+"Build Down", var.prefixCmd+"Build down", "§7Pose les blocs format 3x3 en dessous de vous", false, Chat.Summon);
					Utils.addChat2("§6"+var.prefixCmd+"Build Up", var.prefixCmd+"Build up", "§7Pose les blocs format 3x3 au dessus de vous (1 bloc d'espace au dessus de votre tête)", false, Chat.Summon);
					Utils.addChat2("§6"+var.prefixCmd+"Build Wall", var.prefixCmd+"Build wall", "§7Créé un mur devant vous comme une protection 3x3", false, Chat.Summon);
					Utils.addChat2("§6"+var.prefixCmd+"Build Sneak", var.prefixCmd+"Build sneak", "§7Pour le Wall: Si activé, créé un mur quand vous êtes en sneak, si désactivé, créé un mur en sneak ou non", false, Chat.Summon);
					Utils.checkXp(xp);
					mc.ingameGUI.getChatGUI().addToSentMessages(var3);
				} else if (args[1].equalsIgnoreCase("phase")) {
					Utils.addChat("========================================");
					Utils.addChat2("§6"+var.prefixCmd+"Phase vphase", var.prefixCmd+"phase vphase", "§7Change le phase pour essayer de passer à travers le sol", false, Chat.Summon);
					Utils.checkXp(xp);
					mc.ingameGUI.getChatGUI().addToSentMessages(var3);
				} else if (args[1].equalsIgnoreCase("nametag")) {
					Utils.addChat("========================================");
					Utils.addChat(var.prefixCmd+"nametag <double> :§7 Change la taille des pseudo\n §7Base: 6");
					Utils.addChat2("§6"+var.prefixCmd+"Nametag <Double>", var.prefixCmd+"nametag ", "§7Change la taille des pseudos, de base sur 6", false, Chat.Summon);
					Utils.checkXp(xp);
					mc.ingameGUI.getChatGUI().addToSentMessages(var3);
				} else if (args[1].equalsIgnoreCase("prefix")) {
					Utils.addChat("========================================");
					Utils.addChat2("§6"+var.prefixCmd+"Prefix <String>", var.prefixCmd+"prefix ", "§7Change le preifx des commandes", false, Chat.Summon);
					Utils.checkXp(xp);
					mc.ingameGUI.getChatGUI().addToSentMessages(var3);
				} else if (args[1].equalsIgnoreCase("velocity") || args[1].equalsIgnoreCase("velo")) {
					Utils.addChat("========================================");
					Utils.addChat2("§6"+var.prefixCmd+"Velocity <Double>", var.prefixCmd+"velo ", "§7Change le coefficient de knockback en horizontal et vertical de base", false, Chat.Summon);
					Utils.addChat2("§6"+var.prefixCmd+"Velocity <Horizontal:Hor> <Double>", var.prefixCmd+"velo hor ", "§7Change le coefficient de knockback en horizontal", false, Chat.Summon);
					Utils.addChat2("§6"+var.prefixCmd+"Velocity <Vertical:Ver> <Double>", var.prefixCmd+"velo ver ", "§7Change le coefficient de knockback en vertical", false, Chat.Summon);
					Utils.checkXp(xp);
					mc.ingameGUI.getChatGUI().addToSentMessages(var3);
				} else if (args[1].equalsIgnoreCase("friend") || args[1].equalsIgnoreCase("fr")) {
					Utils.addChat("========================================");
					Utils.addChat2("§6"+var.prefixCmd+"Friend <Player>", var.prefixCmd+"friend ", "§7Ajoute/supprime un joueur de votre liste", false, Chat.Summon);
					Utils.addChat2("§6"+var.prefixCmd+"Friend list", var.prefixCmd+"friend list", "§7Affiche la liste de vos amis", false, Chat.Summon);
					Utils.addChat2("§6"+var.prefixCmd+"Friend clear", var.prefixCmd+"friend clear", "§7Vide votre liste d'amis", false, Chat.Summon);
					Utils.addChat2("§6"+var.prefixCmd+"Friend radius <Double>", var.prefixCmd+"friend radius ", "§7Ajoute/supprime les joueurs dans un rayon de x blocs de votre liste", false, Chat.Summon);
					Utils.addChat2("§6"+var.prefixCmd+"Friend team", var.prefixCmd+"ft", "§7Active/désactive l'ajout automatique d'amis qui sont dans votre team", false, Chat.Summon);
					Utils.checkXp(xp);
					mc.ingameGUI.getChatGUI().addToSentMessages(var3);
				} else if (args[1].equalsIgnoreCase("smoothaim")) {
					Utils.addChat("========================================");
					Utils.addChat2("§6"+var.prefixCmd+"SmoothAim range <Double>", var.prefixCmd+"smoothaim range ", "§7Change la portée du SmoothAim", false, Chat.Summon);
					Utils.addChat2("§6"+var.prefixCmd+"SmoothAim fov <Double>", var.prefixCmd+"smoothaim fov ", "§7Change le fov du SmoothAim", false, Chat.Summon);
					Utils.addChat2("§6"+var.prefixCmd+"SmoothAim speed <Double>", var.prefixCmd+"smoothaim speed ", "§7Change la vitesse de la tête pour le SmoothAim", false, Chat.Summon);
					Utils.checkXp(xp);
					mc.ingameGUI.getChatGUI().addToSentMessages(var3);
				} else if (args[1].equalsIgnoreCase("autosoup")) {
					Utils.addChat("========================================");
					Utils.addChat2("§6"+var.prefixCmd+"Autosoup heal <Int>", var.prefixCmd+"autosoup heal ", "§7Change le seuil de vie", false, Chat.Summon);
					Utils.addChat2("§6"+var.prefixCmd+"Autosoup drop", var.prefixCmd+"autosoup drop", "§7Drop ou non les bols vides de l'inventaire", false, Chat.Summon);
					Utils.checkXp(xp);
					mc.ingameGUI.getChatGUI().addToSentMessages(var3);
				} else if (args[1].equalsIgnoreCase("autonyah")) {
					Utils.addChat("========================================");
					Utils.addChat2("§6"+var.prefixCmd+"Autonyah prefix <String>", var.prefixCmd+"autonyah prefix ", "§7Change le prefix avant les Nyah", false, Chat.Summon);
					Utils.addChat2("§6"+var.prefixCmd+"Autonyah speed <Double>", var.prefixCmd+"Autonyah speed ", "§7Change la vitesse de Nyah par secondes", false, Chat.Summon);
					Utils.checkXp(xp);
					mc.ingameGUI.getChatGUI().addToSentMessages(var3);
				} else if (args[1].equalsIgnoreCase("glide")) {
					Utils.addChat("========================================");
					Utils.addChat2("§6"+var.prefixCmd+"Glide <Double>", var.prefixCmd+"glide ", "§7Change la vitesse du Glide, de base à 0.125", false, Chat.Summon);
					Utils.checkXp(xp);
					mc.ingameGUI.getChatGUI().addToSentMessages(var3);
				} else if (args[1].equalsIgnoreCase("log")) {
					Utils.addChat("========================================");
					Utils.addChat2("§6"+var.prefixCmd+"Log add <Email/Username> <Mdp>", var.prefixCmd+"log add ", "§7Ajoute un compte à la liste", false, Chat.Summon);
					Utils.addChat2("§6"+var.prefixCmd+"Log remove <Int>", var.prefixCmd+"log remove ", "§7Supprime un compte de la liste selon son numéro dans le Log list", false, Chat.Summon);
					Utils.addChat2("§6"+var.prefixCmd+"Log clear", var.prefixCmd+"log clear", "§7Vide la liste de comptes", false, Chat.Summon);
					Utils.addChat2("§6"+var.prefixCmd+"Log list", var.prefixCmd+"log list", "§7Dresse une liste de tous les comptes en cachant le mot de passe", false, Chat.Summon);
					Utils.checkXp(xp);
					mc.ingameGUI.getChatGUI().addToSentMessages(var3);
				} else if (args[1].equalsIgnoreCase("reach")) {
					Utils.addChat("========================================");
					Utils.addChat2("§6Reach pvp [info]", var.prefixCmd+"", "§7Toutes les sous commandes ci-dessous sont débloquées avec la reach pvp.\n§7Si vous voulez en savoir plus, veuillez vous renseignez sur le Guide Neko", true, Chat.Summon);
					Utils.addChat2("§6"+var.prefixCmd+"Reach <Double>", var.prefixCmd+"reach ", "§7Change la distance de la reach", false, Chat.Summon);
					Utils.addChat2("§6"+var.prefixCmd+"Reach pvp", var.prefixCmd+"reach pvp", "§7Permet de frapper des entités et joueurs à distance.\n§7La Reach aimbot vous aidera à mieux viser.", false, Chat.Summon);
					Utils.addChat2("§6"+var.prefixCmd+"Reach bloc", var.prefixCmd+"reach bloc", "§7Clic droit: Pose des blocs à distance.\n§7Clic gauche: Si rien dans la main, vous téléporte sur le bloc taper puis reviens.\n§7Idéal pour ramasser du stuff perdUtils.", false, Chat.Summon);
					Utils.addChat2("§6"+var.prefixCmd+"Reach aimbot", var.prefixCmd+"reach aimbot", "§7Selon le fov ci-dessous, de frapper une cible si elle se trouve dedans, si on est un peu à côté", false, Chat.Summon);
					Utils.addChat2("§6"+var.prefixCmd+"Reach fov <Double>", var.prefixCmd+"reach fov ", "§7Change le fov pour la Reach aimbot", false, Chat.Summon);
					Utils.addChat2("§6"+var.prefixCmd+"Reach tnt", var.prefixCmd+"reach tnt", "§7Permet de poser et allumer de la tnt à distance avec le clic droit.\n§7Le Reach tnt <Mode> permet de changer de mode de posage.\n§7Faîtes Reach tnt list pour la liste des modes", false, Chat.Summon);
					Utils.checkXp(xp);
					mc.ingameGUI.getChatGUI().addToSentMessages(var3);
				} else if (args[1].equalsIgnoreCase("trigger")) {
					Utils.addChat("========================================");
					Utils.addChat2("§6"+var.prefixCmd+"Trigger <Double>", var.prefixCmd+"trigger ", "§7Change la portée du Trigger", false, Chat.Summon);
					Utils.addChat2("§6"+var.prefixCmd+"Trigger cps <Int>", var.prefixCmd+"trigger cps ", "§7Change les coups/secondes du Trigger", false, Chat.Summon);
					Utils.addChat2("§6"+var.prefixCmd+"Trigger random", var.prefixCmd+"trigger random", "§7Active ou non les cps aléatoire pour le Trigger", false, Chat.Summon);
					Utils.checkXp(xp);
					mc.ingameGUI.getChatGUI().addToSentMessages(var3);
				} else if (args[1].equalsIgnoreCase("callcmd")) {
					Utils.addChat("========================================");
					Utils.addChat2("§6"+var.prefixCmd+"CallCmd player <Player>", var.prefixCmd+"callcmd player ", "§7Ajoute un joueur à la liste", false, Chat.Summon);
					Utils.addChat2("§6"+var.prefixCmd+"CallCmd cmd <Cmd>", var.prefixCmd+"callcmd cmd ", "§7Change la commande faite si un joueur est détecté", false, Chat.Summon);
					Utils.addChat2("§6"+var.prefixCmd+"CallCmd list", var.prefixCmd+"callcmd list", "§7Affiche la liste des joueurs et la commande faite", false, Chat.Summon);
					Utils.addChat2("§6"+var.prefixCmd+"CallCmd clear", var.prefixCmd+"callcmd clear", "§7Clear la liste des joueurs", false, Chat.Summon);
					Utils.checkXp(xp);
					mc.ingameGUI.getChatGUI().addToSentMessages(var3);
				} else if (args[1].equalsIgnoreCase("nekochat") || args[1].equalsIgnoreCase("chat")) {
					Utils.addChat("========================================");
					Utils.addChat2("§6"+var.prefixCmd+"Chat color <R> <G> <B> <Alpha optionel>", var.prefixCmd+"chat color ", "§7Change la couleur du chat en fond", false, Chat.Summon);
					Utils.addChat2("§6"+var.prefixCmd+"Chat height <Double>", var.prefixCmd+"chat height ", "§7Change la hauteur du chat", false, Chat.Summon);
					Utils.addChat2("§6"+var.prefixCmd+"Chat width <Double>", var.prefixCmd+"chat width ", "§7Change la largeur du chat", false, Chat.Summon);
					Utils.checkXp(xp);
					mc.ingameGUI.getChatGUI().addToSentMessages(var3);
				} else if (args[1].equalsIgnoreCase("proxy")) {
					Utils.addChat("========================================");
					Utils.addChat2("§6"+var.prefixCmd+"Proxy <HostIP> <Port>", var.prefixCmd+"proxy ", "§7Vous connecte à un proxy", false, Chat.Summon);
					Utils.addChat2("§6"+var.prefixCmd+"Proxy reset", var.prefixCmd+"proxy reset ", "§7Vous déconnecte de votre proxy", false, Chat.Summon);
					Utils.checkXp(xp);
					mc.ingameGUI.getChatGUI().addToSentMessages(var3);
				} else if (args[1].equalsIgnoreCase("fastbow") || args[1].equalsIgnoreCase("fb")) {
					Utils.addChat("========================================");
					Utils.addChat2("§6"+var.prefixCmd+"Fastbow NoBow", var.prefixCmd+"fastbow nobow", "§7Permet de tirer des flèches si l'on possède au moins un arc + flèche dans l'inventaire.\n§7Pour les joueurs vous gardez votre item actuel dans la main en tirant, have fun.", false, Chat.Summon);
					Utils.addChat2("§6"+var.prefixCmd+"Fastbow Packet <Int>", var.prefixCmd+"fastbow packet ", "§7Modifie le nombre de paquet envoyé par le fastbow par flèche, de base à 20", false, Chat.Summon);
					Utils.checkXp(xp);
					mc.ingameGUI.getChatGUI().addToSentMessages(var3);
				} else if (args[1].equalsIgnoreCase("punkeel")) {
					Utils.addChat("========================================");
					Utils.addChat2("§6"+var.prefixCmd+"Punkeel Delay <Double>", var.prefixCmd+"punkeel delay ", "§7Modifie le delay entre les tp, par ex 0.5 donne un VRAI lag de 500ms, les joueurs vous voit vous tp tous les 0.5sec", false, Chat.Summon);
					Utils.addChat2("§6"+var.prefixCmd+"Punkeel Attack", var.prefixCmd+"punkeel attack ", "§7Fais que quand on frappe le packet d'attaque s'envoie tout de suite au lieu des ms normaux", false, Chat.Summon);
					Utils.checkXp(xp);
					mc.ingameGUI.getChatGUI().addToSentMessages(var3);
				} else if (args[1].equalsIgnoreCase("automlg") || args[1].equalsIgnoreCase("mlg")) {
					Utils.addChat("========================================");
					Utils.addChat2("§6"+var.prefixCmd+"Mlg <Double>", var.prefixCmd+"mlg ", "§7Change la distance de chute minimale", false, Chat.Summon);
					Utils.checkXp(xp);
					mc.ingameGUI.getChatGUI().addToSentMessages(var3);
				} else if (args[1].equalsIgnoreCase("vanillatp") || args[1].equalsIgnoreCase("vtp")) {
					Utils.addChat("========================================");
					Utils.addChat2("§6"+var.prefixCmd+"Vtp air", var.prefixCmd+"vtp air", "§7Permet de pouvoir se téléporter au loin sans forcément viser un bloc (L'air)", false, Chat.Summon);
					Utils.addChat2("§6"+var.prefixCmd+"Vtp classic", var.prefixCmd+"vtp classic", "§7Force à utiliser le mode de tp classique qui est en diagonale au lieu de passer par d'autres endroits", false, Chat.Summon);
					Utils.addChat2("§6"+var.prefixCmd+"Vtp top", var.prefixCmd+"vtp top", "§7Permet quand on vise un bloc de se tp en haut de celui-ci si les blocs au dessus sont pleins (Ex: un mur)", false, Chat.Summon);
					Utils.checkXp(xp);
					mc.ingameGUI.getChatGUI().addToSentMessages(var3);
				} else if (args[1].equalsIgnoreCase("scaffold")) {
					Utils.addChat("========================================");
					Utils.addChat2("§6"+var.prefixCmd+"Scaffold down", var.prefixCmd+"scaffold down", "§7Permet de pouvoir poser les blocs en dessous de vous en escalier descendant", false, Chat.Summon);
					Utils.checkXp(xp);
					mc.ingameGUI.getChatGUI().addToSentMessages(var3);
				} else if (args[1].equalsIgnoreCase("tpback") || args[1].equalsIgnoreCase("tpb")) {
					Utils.addChat("========================================");
					Utils.addChat2("§6"+var.prefixCmd+"Tpback top", var.prefixCmd+"tpback top", "§7Permet de vous téléporter sur le plus haut bloc si votre point de tp est bouché", false, Chat.Summon);
					Utils.addChat2("§6"+var.prefixCmd+"Tpback classic", var.prefixCmd+"tpback classic", "§7Force à utiliser le mode de tp classique qui est en diagonale au lieu de passer par d'autres endroits", false, Chat.Summon);
					Utils.addChat2("§6"+var.prefixCmd+"Tpback <Int>", var.prefixCmd+"tpback ", "§7Change le seuil de vie", false, Chat.Summon);
					Utils.addChat2("§6"+var.prefixCmd+"Tpback set:spawn:setspawn", var.prefixCmd+"tpback setspawn", "§7Change le point de tp à là où vous vous trouvez", false, Chat.Summon);
					Utils.addChat2("§6"+var.prefixCmd+"Tpback set:spawn:setspawn <X> <Y> <Z>", var.prefixCmd+"tpback setspawn ", "§7Change le point de tp aux coordonnées entrées", false, Chat.Summon);
					Utils.checkXp(xp);
					mc.ingameGUI.getChatGUI().addToSentMessages(var3);
				} else if (args[1].equalsIgnoreCase("limit")) {
					Utils.addChat("========================================");
					Utils.addChat2("§6"+var.prefixCmd+"Limit", var.prefixCmd+"limit", "§7Active/désactive le limit.\n§7Limite le nombre de paquets/secondes envoyés.", false, Chat.Summon);
					Utils.addChat2("§6"+var.prefixCmd+"Limit <Int>", var.prefixCmd+"limit ", "§7Modifie la limite de paquet", false, Chat.Summon);
					Utils.checkXp(xp);
					mc.ingameGUI.getChatGUI().addToSentMessages(var3);
				} else if (args[1].equalsIgnoreCase("ka") || args[1].equalsIgnoreCase("killaura")) {
					Utils.addChat("========================================");
					Utils.addChat2("§6"+var.prefixCmd+"Ka mode <Multi:Single>", var.prefixCmd+"ka mode ", "§7Change entre les modes Single et Multi", false, Chat.Summon);
					Utils.addChat2("§6"+var.prefixCmd+"Ka cps <Int>", var.prefixCmd+"ka cps ", "§7Change les coups/secondes du Kill Aura", false, Chat.Summon);
					Utils.addChat2("§6"+var.prefixCmd+"Ka range <Double>", var.prefixCmd+"ka range ", "§7Change la portée des coups", false, Chat.Summon);
					Utils.addChat2("§6"+var.prefixCmd+"Ka fov <Double>", var.prefixCmd+"ka fov ", "§7Change l'angle d'attaque du Kill Aura", false, Chat.Summon);
					Utils.addChat2("§6"+var.prefixCmd+"Ka lockview", var.prefixCmd+"ka lockview", "§7Permet ou non de fixer l'entité en attaquant", false, Chat.Summon);
					Utils.addChat2("§6"+var.prefixCmd+"Ka live <Int>", var.prefixCmd+"ka live ", "§7Change le temps avant de taper une nouvelle entité", false, Chat.Summon);
					Utils.addChat2("§6"+var.prefixCmd+"Ka invi", var.prefixCmd+"ka invi", "§7Tape ou non les entités invisibles", false, Chat.Summon);
					Utils.addChat2("§6"+var.prefixCmd+"Ka random", var.prefixCmd+"ka random", "§7Change les cps pour qu'ils soient aléatoire", false, Chat.Summon);
					Utils.addChat2("§6"+var.prefixCmd+"Ka verif", var.prefixCmd+"ka verif", "§7Effectue une double vérification anti-bot ou non", false, Chat.Summon);
					Utils.addChat2("§6"+var.prefixCmd+"Ka onground", var.prefixCmd+"ka onground", "§7Frappe ou non les joueurs dans les air", false, Chat.Summon);				
					Utils.addChat2("§6"+var.prefixCmd+"Ka noarmor", var.prefixCmd+"ka noarmor", "§7Frappe ou non les joueurs sans armures", false, Chat.Summon);
					Utils.addChat2("§6"+var.prefixCmd+"Ka nobot", var.prefixCmd+"ka nobot", "§7Anti-bot avancé", false, Chat.Summon);
					Utils.addChat2("§6"+var.prefixCmd+"Ka premium", var.prefixCmd+"ka premium", "§7Activé: Ne frappe que les joueurs en premium", false, Chat.Summon);
					Utils.addChat2("§6"+var.prefixCmd+"Ka speed <Double>", var.prefixCmd+"ka speed ", "§7Modifie la vitesse à laquelle on tourne la tête avec le lockview", false, Chat.Summon);
					Utils.checkXp(xp);
					mc.ingameGUI.getChatGUI().addToSentMessages(var3);
				} else if (args[1].equalsIgnoreCase("verif")) {
					Utils.addChat("========================================");
					Utils.addChat2("§6"+var.prefixCmd+"Verif <String>", var.prefixCmd+"verif ", "§7Désactive tout le client\n§7Le String est la commande que vous devez faire §7pour tout réactiver,\n§7tout sera désactivé jusqu'à ce que vous le fassiez\n§7Exemple: "+var.prefixCmd+"verif nyah, vous aurez besoin de faire "+var.prefixCmd+"nyah pour §7tout réactiver ;)\n§7Pour info, tous les cheats activés avant vont se réactiver ainsi que toutes les options etc...\n§7De quoi vous permettre de rester legit un instant et repartir en §7cheat d'un autre ;)", false, Chat.Summon);
					Utils.checkXp(xp);
					mc.ingameGUI.getChatGUI().addToSentMessages(var3);
				} else if (args[1].equalsIgnoreCase("nyah")) {
					Utils.addChat("========================================");
					Utils.addChat2("§6"+var.prefixCmd+"Nyah", var.prefixCmd+"nyah", "§7Permet d'envoyer une phrase kawaii à base de neko et d'aléatoire", false, Chat.Summon);
					Utils.addChat2("§6"+var.prefixCmd+"Nyah <Prefix>", var.prefixCmd+"nyah ", "§7Pareil mais avec un prefix devant", false, Chat.Summon);
					mc.ingameGUI.getChatGUI().addToSentMessages(var3);
				} else if (args[1].equalsIgnoreCase("autoarmor") || args[1].equalsIgnoreCase("aa")) {
					Utils.addChat("========================================");
					Utils.addChat2("§6"+var.prefixCmd+"AutoArmor ec", var.prefixCmd+"autoarmor ec", "§7Active ou non sur épicube", false, Chat.Summon);
					mc.ingameGUI.getChatGUI().addToSentMessages(var3);
				} else if (args[1].equalsIgnoreCase("dolphin")) {
					Utils.addChat("========================================");
					Utils.addChat2("§6"+var.prefixCmd+"Dolphin <Double>", var.prefixCmd+"dolphin ", "§7Change la vitesse du Dolphin", false, Chat.Summon);
					Utils.checkXp(xp);
					mc.ingameGUI.getChatGUI().addToSentMessages(var3);
				} else if (args[1].equalsIgnoreCase("noclip")) {
					Utils.addChat("========================================");
					Utils.addChat2("§6"+var.prefixCmd+"Noclip <Double>", var.prefixCmd+"noclip ", "§7Change la vitesse du Noclip", false, Chat.Summon);
					Utils.checkXp(xp);
					mc.ingameGUI.getChatGUI().addToSentMessages(var3);
				} else if (args[1].equalsIgnoreCase("hud")) {
					Utils.addChat("========================================");
					Utils.addChat2("§6"+var.prefixCmd+"Hud ", var.prefixCmd+"hud", "§7Affiche l'Hud", false, Chat.Summon);
					Utils.addChat2("§6"+var.prefixCmd+"Hud coord", var.prefixCmd+"hud coord", "§7Affiche les coordonnées", false, Chat.Summon);
					Utils.addChat2("§6"+var.prefixCmd+"Hud fps", var.prefixCmd+"hud fps", "§7Affiche les fps", false, Chat.Summon);
					Utils.addChat2("§6"+var.prefixCmd+"Hud xp", var.prefixCmd+"hud xp", "§7Affiche l'expérience et votre niveau", false, Chat.Summon);
					Utils.addChat2("§6"+var.prefixCmd+"Hud packet", var.prefixCmd+"hud packet", "§7Affiche les paquets/secondes envoyés", false, Chat.Summon);
					Utils.addChat2("§6"+var.prefixCmd+"Hud item", var.prefixCmd+"hud item", "§7Affiche l'item en main", false, Chat.Summon);
					Utils.addChat2("§6"+var.prefixCmd+"Hud time", var.prefixCmd+"hud time", "§7Affiche le temps de jeu ou le temps de bonus restant", false, Chat.Summon);
					Utils.addChat2("§6"+var.prefixCmd+"Hud stuff", var.prefixCmd+"hud stuff", "§7Affiche le stuff des joueurs visés", false, Chat.Summon);
					Utils.addChat2("§6"+var.prefixCmd+"Hud select", var.prefixCmd+"hud select", "§7Rend le bloc visé d'une certaine couleur choisie ci-dessous", false, Chat.Summon);
					Utils.addChat2("§6"+var.prefixCmd+"Hud color <R> <G> <B>", var.prefixCmd+"hud color ", "§7Red, Green, Blue, valeurs de 0 à 255, change la couleur pour le Hud select", false, Chat.Summon);
					Utils.checkXp(xp);
					mc.ingameGUI.getChatGUI().addToSentMessages(var3);
				} else if (args[1].equalsIgnoreCase("wallhack") || args[1].equalsIgnoreCase("wh")) {
					Utils.addChat("========================================");
					Utils.addChat2("§6"+var.prefixCmd+"WallHack color <R> <G> <B>", var.prefixCmd+"wallhack color ", "§7Change la couleur du fond (Red Green Blue = taux de couleur, de 0 à 255)", false, Chat.Summon);
					Utils.addChat2("§6"+var.prefixCmd+"WallHack linecolor <R> <G> <B>", var.prefixCmd+"wallhack linecolor ", "§7Pareil mais pour les lignes", false, Chat.Summon);
					Utils.addChat2("§6"+var.prefixCmd+"WallHack width <Double>", var.prefixCmd+"wallhack width ", "§7Change l'épaisseur des lignes", false, Chat.Summon);
					Utils.checkXp(xp);
					mc.ingameGUI.getChatGUI().addToSentMessages(var3);
				} else if (args[1].equalsIgnoreCase("itemesp") || args[1].equalsIgnoreCase("item")) {
					Utils.addChat("========================================");
					Utils.addChat2("§6"+var.prefixCmd+"ItemESP color <R> <G> <B>", var.prefixCmd+"itemesp color ", "§7Change la couleur", false, Chat.Summon);
					Utils.addChat2("§6"+var.prefixCmd+"ItemESP linecolor <R> <G> <B>", var.prefixCmd+"itemesp linecolor ", "§7pareil mais pour les lignes", false, Chat.Summon);
					Utils.addChat2("§6"+var.prefixCmd+"ItemESP width <Double>", var.prefixCmd+"itemesp width ", "§7Change l'épaisseur de ligne", false, Chat.Summon);
					Utils.checkXp(xp);
					mc.ingameGUI.getChatGUI().addToSentMessages(var3);
				} else if (args[1].equalsIgnoreCase("register") || args[1].equalsIgnoreCase("reg")) {
					Utils.addChat("========================================");
					Utils.addChat2("§6"+var.prefixCmd+"Register mdp <Mot de passe>", var.prefixCmd+"register mdp ", "§7Change le mot de passe par défaut", false, Chat.Summon);
					Utils.checkXp(xp);
					mc.ingameGUI.getChatGUI().addToSentMessages(var3);
				} else if (args[1].equalsIgnoreCase("enchant")) {
					Utils.addChat("========================================");
					Utils.addChat2("§6"+var.prefixCmd+"Enchant", var.prefixCmd+"enchant", "§7Enchante l'item avec tous les enchantements disponible au niveau 127", false, Chat.Summon);
					Utils.addChat2("§6"+var.prefixCmd+"Enchant list", var.prefixCmd+"enchant list", "§7Affiche la liste des enchantements disponible", false, Chat.Summon);
					Utils.addChat2("§6"+var.prefixCmd+"Enchant <Enchant>", var.prefixCmd+"enchant ", "§7Enchante l'item dans votre main dans l'enchantement choisis au niveau 127", false, Chat.Summon);
					Utils.addChat2("§6"+var.prefixCmd+"Enchant <Enchant> <Level>", var.prefixCmd+"enchant ", "§7Enchante l'item dans votre main dans l'enchantement choisis au niveau choisis", false, Chat.Summon);
					Utils.checkXp(xp);
					mc.ingameGUI.getChatGUI().addToSentMessages(var3);
				} else if (args[1].equalsIgnoreCase("paint")) {
					Utils.addChat("========================================");
					Utils.addChat2("§6"+var.prefixCmd+"Paint color <R> <G> <B>", var.prefixCmd+"paint color ", "§7Change la couleur des blocs (Red Green Blue = taux de couleur, de 0 à 100)", false, Chat.Summon);
					Utils.addChat2("§6"+var.prefixCmd+"Paint alpha <Double>", var.prefixCmd+"paint alpha ", "§7Change la transparence, de 0 à 1", false, Chat.Summon);
					Utils.addChat2("§6"+var.prefixCmd+"Paint clear", var.prefixCmd+"paint clear", "§7Enlève tout ce que vous avez fait", false, Chat.Summon);
					Utils.checkXp(xp);
					mc.ingameGUI.getChatGUI().addToSentMessages(var3);
				} else if (args[1].equalsIgnoreCase("tracers")) {
					Utils.addChat("========================================");
					Utils.addChat2("§6"+var.prefixCmd+"Tracers color <R> <G> <B>", var.prefixCmd+"tracers color ", "§7Change la couleur de la ligne (Red Green Blue = taux de couleur, de 0 à 100)", false, Chat.Summon);
					Utils.addChat2("§6"+var.prefixCmd+"Tracers width <Double>", var.prefixCmd+"tracers width ", "§7Change l'épaisseur de la ligne", false, Chat.Summon);
					Utils.addChat2("§6"+var.prefixCmd+"Tracers friend", var.prefixCmd+"tracers friend", "§7Affiche ou non une ligne sur les friends", false, Chat.Summon);
					Utils.checkXp(xp);
					mc.ingameGUI.getChatGUI().addToSentMessages(var3);
				} else if (args[1].equalsIgnoreCase("freecam")) {
					Utils.addChat("========================================");
					Utils.addChat2("§6"+var.prefixCmd+"Freecam <Speed>", var.prefixCmd+"freecam ", "§7Modifie la vitesse du freecam (De base à 1)", false, Chat.Summon);
					Utils.checkXp(xp);
					mc.ingameGUI.getChatGUI().addToSentMessages(var3);
				} else if (args[1].equalsIgnoreCase("nuker")) {
					Utils.addChat("========================================");
					Utils.addChat2("§6"+var.prefixCmd+"Nuker add <Bloc>", var.prefixCmd+"nuker add ", "§7Ajoute un bloc à votre liste", false, Chat.Summon);
					Utils.addChat2("§6"+var.prefixCmd+"Nuker rem <Bloc>", var.prefixCmd+"nuker rem ", "§7Supprime un bloc de votre liste", false, Chat.Summon);
					Utils.addChat2("§6"+var.prefixCmd+"Nuker range <double>", var.prefixCmd+"nuker range ", "§7Change la distance d'atteinte des blocs", false, Chat.Summon);
					Utils.addChat2("§6"+var.prefixCmd+"Nuker clear", var.prefixCmd+"nuker clear", "§7Vide votre liste", false, Chat.Summon);
					Utils.addChat2("§6"+var.prefixCmd+"Nuker list", var.prefixCmd+"nuker list", "§7Affiche la liste", false, Chat.Summon);
					Utils.addChat2("§6"+var.prefixCmd+"Nuker safe", var.prefixCmd+"nuker safe", "§7Ne casse pas le bloc en dessous de vous", false, Chat.Summon);
					Utils.checkXp(xp);
					mc.ingameGUI.getChatGUI().addToSentMessages(var3);
				} else if (args[1].equalsIgnoreCase("step")) {
					Utils.addChat("========================================");
					Utils.addChat2("§6"+var.prefixCmd+"Step <Double>", var.prefixCmd+"step ", "§7Change le maximum de hauteur de bloc grimpable avec le Step", false, Chat.Summon);
					Utils.addChat2("§6"+var.prefixCmd+"Step bypass", var.prefixCmd+"step bypass", "§7Met le step en mode bypass", false, Chat.Summon);
					Utils.checkXp(xp);
					mc.ingameGUI.getChatGUI().addToSentMessages(var3);
				} else if (args[1].equalsIgnoreCase("speed")) {
					Utils.addChat("========================================");
					Utils.addChat2("§6"+var.prefixCmd+"Speed <Double>", var.prefixCmd+"speed ", "§7Change la vitesse du Speed", false, Chat.Summon);
					Utils.addChat2("§6"+var.prefixCmd+"Speed Mode <Air:Ground:Motion>", var.prefixCmd+"speed mode ", "§7Change le mode du Speed", false, Chat.Summon);
					Utils.checkXp(xp);
					mc.ingameGUI.getChatGUI().addToSentMessages(var3);
				} else if (args[1].equalsIgnoreCase("autoclic")) {
					Utils.addChat("========================================");
					Utils.addChat2("§6"+var.prefixCmd+"Autoclic <Int>", var.prefixCmd+"autoclic", "§7Modifie les coups/sec de l'Autoclic", false, Chat.Summon);
					Utils.checkXp(xp);
					mc.ingameGUI.getChatGUI().addToSentMessages(var3);
				} else if (args[1].equalsIgnoreCase("worldtime")) {
					Utils.addChat("========================================");
					Utils.addChat2("§6"+var.prefixCmd+"WorldTime <Int>", var.prefixCmd+"worldtime ", "§7Change l'heure du monde", false, Chat.Summon);
					Utils.checkXp(xp);
					mc.ingameGUI.getChatGUI().addToSentMessages(var3);
				} else if (args[1].equalsIgnoreCase("irc")) {
					Utils.addChat("========================================");
					Utils.addChat("§7Vous pouvez écrire en couleur dans l'Irc.");
					Utils.addChat2("§6"+var.prefixCmd+"Irc", var.prefixCmd+"irc", "§7Active/désactive l'Irc", false, Chat.Summon);
					Utils.addChat2("§6"+var.prefixCmd+"Irc name <Pseudo>", var.prefixCmd+"irc name ", "§7Change votre pseudo dans l'Irc", false, Chat.Summon);
					Utils.addChat2("§6"+var.prefixCmd+"Irc prefix <Prefix>", var.prefixCmd+"irc ", "§7Change le prefix pour l'irc", false, Chat.Summon);
					Utils.addChat2("§6"+var.prefixCmd+"Irc mode <Hybride:Normal:Only>", var.prefixCmd+"irc mode ", "§7Change le mode de l'irc", false, Chat.Summon);
					Utils.addChat2("§6"+var.prefixCmd+"Irc list", var.prefixCmd+"irc list", "§7Affiche la liste des joueurs connectés sur Neko et l'IRC", false, Chat.Summon);
					Utils.addChat2("§6"+var.prefixCmd+"Irc hide", var.prefixCmd+"irc hide", "§7Cache les messages de join/left", false, Chat.Summon);
					Utils.addChat2("§6"+var.prefixCmd+"Irc playerclic", var.prefixCmd+"irc playerclic", "§7Change entre la connexion au serveur du joueur ou le contacter par message privé quand on clic dessus", false, Chat.Summon);
					Utils.checkXp(xp);
					mc.ingameGUI.getChatGUI().addToSentMessages(var3);
				} else if (args[1].equalsIgnoreCase("xray")) {
					Utils.addChat("========================================");
					Utils.addChat2("§6"+var.prefixCmd+"Xray add <Bloc>", var.prefixCmd+"xray add ", "§7Ajoute un bloc à votre liste", false, Chat.Summon);
					Utils.addChat2("§6"+var.prefixCmd+"Xray rem <Bloc>", var.prefixCmd+"xray rem ", "§7Supprime un bloc de votre liste", false, Chat.Summon);
					Utils.addChat2("§6"+var.prefixCmd+"Xray clear", var.prefixCmd+"xray clear", "§7Vide votre liste", false, Chat.Summon);
					Utils.addChat2("§6"+var.prefixCmd+"Xray list", var.prefixCmd+"xray list", "§7Affiche la liste", false, Chat.Summon);
					Utils.checkXp(xp);
					mc.ingameGUI.getChatGUI().addToSentMessages(var3);
				} else if (args[1].equalsIgnoreCase("dropshit")) {
					Utils.addChat("========================================");
					Utils.addChat2("§6"+var.prefixCmd+"DropShit", "", "§7Sert à drop les items dans votre inventaire qui sont dans votre liste noire (BlackList), vous pouvez les ajouter/supprimer avec les commandes suivantes:", true, Chat.Summon);
					Utils.addChat2("§6"+var.prefixCmd+"DropShit add <Bloc>", var.prefixCmd+"dropshit add ", "§7Ajoute un item à votre liste", false, Chat.Summon);
					Utils.addChat2("§6"+var.prefixCmd+"DropShit rem <Bloc>", var.prefixCmd+"dropshit rem ", "§7Supprime un item de votre liste", false, Chat.Summon);
					Utils.addChat2("§6"+var.prefixCmd+"DropShit clear", var.prefixCmd+"dropshit clear", "§7Vide votre liste", false, Chat.Summon);
					Utils.addChat2("§6"+var.prefixCmd+"DropShit list", var.prefixCmd+"dropshit list", "§7Affiche la liste", false, Chat.Summon);
					Utils.checkXp(xp);
					mc.ingameGUI.getChatGUI().addToSentMessages(var3);
				} else if (args[1].equalsIgnoreCase("regen")) {
					Utils.addChat("========================================");
					Utils.addChat2("§6"+var.prefixCmd+"Regen <Int>", var.prefixCmd+"regen ", "§7Change le nombre de paquets envoyés par secondes", false, Chat.Summon);
					Utils.addChat2("§6"+var.prefixCmd+"Regen bypass", var.prefixCmd+"regen bypass", "§7Active seulement la regen avec un effet de potion de regen", false, Chat.Summon);
					Utils.checkXp(xp);
					mc.ingameGUI.getChatGUI().addToSentMessages(var3);
				} else if (args[1].equalsIgnoreCase("timer")) {
					Utils.addChat("========================================");
					Utils.addChat2("§6"+var.prefixCmd+"Timer <Double>", var.prefixCmd+"timer ", "§7Change la vitesse du Timer", false, Chat.Summon);	
					Utils.checkXp(xp);
					mc.ingameGUI.getChatGUI().addToSentMessages(var3);
				} else if (args[1].equalsIgnoreCase("fasteat")) {
					Utils.addChat("========================================");
					Utils.addChat2("§6"+var.prefixCmd+"Fasteat <Int>", var.prefixCmd+"fasteat ", "§7Change la vitesse quand on mange dans le Fasteat (0 à 25)", false, Chat.Summon);	
					Utils.checkXp(xp);
					mc.ingameGUI.getChatGUI().addToSentMessages(var3);
				} else if (args[1].equalsIgnoreCase("pushup")) {
					Utils.addChat("========================================");
					Utils.addChat2("§6"+var.prefixCmd+"Pushup <Int>", var.prefixCmd+"pushup ", "§7Change le nombre de paquet envoyés", false, Chat.Summon);	
					Utils.checkXp(xp);
					mc.ingameGUI.getChatGUI().addToSentMessages(var3);
				} else if (args[1].equalsIgnoreCase("flight")) {
					Utils.addChat("========================================");
					Utils.addChat2("§6"+var.prefixCmd+"Flight <Double>", var.prefixCmd+"flight ", "§7Change la vitesse du Flight", false, Chat.Summon);
					Utils.addChat2("§6"+var.prefixCmd+"Flight blink", var.prefixCmd+"flight blink", "§7Permet d'activer automatiquement le Blink quand on active le Flight", false, Chat.Summon);
					Utils.checkXp(xp);
					mc.ingameGUI.getChatGUI().addToSentMessages(var3);
				} else if (args[1].equalsIgnoreCase("bowaimbot")) {
					Utils.addChat("========================================");
					Utils.addChat2("§6"+var.prefixCmd+"BowAimbot Fov <Double>", var.prefixCmd+"bowaimbot fov ", "§7Change le fov du bowaimbot", false, Chat.Summon);
					Utils.addChat2("§6"+var.prefixCmd+"BowAimbot Life", var.prefixCmd+"bowaimbot life", "§7Change le mode life, à viser la personne le Min de vie, Max de vie ou désactivé", false, Chat.Summon);
					Utils.addChat2("§6"+var.prefixCmd+"BowAimbot Armor", var.prefixCmd+"bowaimbot armor", "§7Change le mode armor, à viser la personne le Min d'armure, Max d'armure ou désactivé", false, Chat.Summon);
					Utils.checkXp(xp);
					mc.ingameGUI.getChatGUI().addToSentMessages(var3);
				} else if (args[1].equalsIgnoreCase("longjump")) {
					Utils.addChat("========================================");
					Utils.addChat2("§6"+var.prefixCmd+"Longjump speed <Double>", var.prefixCmd+"longjump speed ", "§7Change la vitesse du Longjump", false, Chat.Summon);
					Utils.checkXp(xp);
					mc.ingameGUI.getChatGUI().addToSentMessages(var3);
				} else if (args[1].equalsIgnoreCase("option")) {
					Utils.addChat("========================================");
					Utils.addChat("Concernant ces commandes, elles n'affichent pas si elles §6ont été activées ou non");
					Utils.addChat2("§6"+var.prefixCmd+"Option display", var.prefixCmd+"option ", "§7Affiche le Activé/désactivé des cheats", false, Chat.Summon);
					Utils.addChat2("§6"+var.prefixCmd+"Option zoom", var.prefixCmd+"option ", "§7Permet un zoom en tirant à l'arc", false, Chat.Summon);
					Utils.addChat2("§6"+var.prefixCmd+"Option xp", var.prefixCmd+"option ", "§7Affiche l'xp gagné", false, Chat.Summon);
					Utils.addChat2("§6"+var.prefixCmd+"Option deathOff", var.prefixCmd+"option ", "§7Désactive le Kill Aura et Trigger à la mort", false, Chat.Summon);
					Utils.addChat2("§6"+var.prefixCmd+"Option scoreboard", var.prefixCmd+"option ", "§7Désactive les scoreboards", false, Chat.Summon);
					Utils.checkXp(xp);
					mc.ingameGUI.getChatGUI().addToSentMessages(var3);
				} else {
					try {
						Utils.displayHelp(Integer.parseInt(args[1]));
					} catch (Exception e) {Utils.addChat(error);}
					Utils.checkXp(xp);
					mc.ingameGUI.getChatGUI().addToSentMessages(var3);
				}
				
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"backup") || args[0].equalsIgnoreCase(var.prefixCmd+"bip")) {
				if (args.length==1) {
					String name = "";
					if (args.length>1) {
						for (int i=1;i<args.length;i++) {
							if (i+1==args.length)
								name+=args[i];
							else 
								name+=args[i]+" ";
						}
					} else {
						name = "NekoBackup";
					}
					String s = Utils.linkSave;
					Utils.linkSave=System.getenv("APPDATA") + "\\.minecraft\\"+name+"\\";
					if (!new File(Utils.linkSave).exists())
						try {
							new File(Utils.linkSave).mkdirs();
						} catch (Exception e) {
							e.printStackTrace();
						}
					Utils.saveAll();
					Utils.linkSave=s;
					Utils.addChat("§aBackup crée !");
				} else if (args[1].equalsIgnoreCase("set")) {
					String name = args[2];
					for (int i=3;i<args.length;i++) {
							name+=" "+args[i];
					}
					God.getInstance().setBackup(args[2]);
					Utils.addChat("§aLien de backup automatique changée !");
				}
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"saveall") || args[0].equalsIgnoreCase(var.prefixCmd+"save")) {
				Utils.saveAll();
				Utils.addChat("§aTout a été sauvé");
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"fov3")) {
				if (args.length==1) {
					Utils.addChat(err);
				} else {
					try {
						EntityRenderer.thirdPersonDistance=Float.parseFloat(args[1]);
						Utils.addChat("§aDistance à la 3ème personne mis à "+args[1]+" blocs");
					} catch (Exception e) {
						Utils.addChat(err);
					}
				}
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"callcmd")) {
				try {
					CallCmd c = CallCmd.getCall();
					if (args.length==1) {
						Utils.addChat(err);
					} else if (args[1].equalsIgnoreCase("player")) {
						if (!c.getListPlayer().contains(args[2])) {
							c.getListPlayer().add(args[2]);
							Utils.addChat("§aJoueur "+args[2]+" ajouté à la blacklist !");
						} else {
							c.getListPlayer().removeElement(args[2]);
							Utils.addChat("§cLe joueur "+args[2]+" a été supprimé de la blacklist !");
						}
					} else if (args[1].equalsIgnoreCase("cmd")) {
						c.setCmd2(args[2]);
						Utils.addChat("§aCommande mise à jour");
					} else if (args[1].equalsIgnoreCase("clear")) {
						c.getListPlayer().clear();
						Utils.addChat("§aListe des joueurs clear");
					} else if (args[1].equalsIgnoreCase("list")) {
						if (c.getListPlayer().size()==0) {
							Utils.addChat("Pas de joueurs blacklistés");
						} else {
							Utils.addChat("Liste des joueurs blacklistés");						
							for (String s : c.getListPlayer()) {
								Utils.addChat("§7- "+s);
							}
						}
						Utils.addChat("Commande: §7"+c.getCmd2());
					}
				} catch (Exception e) {
					Utils.addChat(err);
				}
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"register") || args[0].equalsIgnoreCase(var.prefixCmd+"reg")) {
				try {
					Register r = Register.getReg();
					if (args.length==1) {
						Utils.addChat(err);
					} else if (args[1].equalsIgnoreCase("mdp")) {
						r.setMdp(args[2]);
						Utils.addChat("§aMot de passe par défaut changé !");
					}
				} catch (Exception e) {
					Utils.addChat(err);
				}
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			} 	
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"give")) {
				if (args.length==1) {
					Utils.addChat(err);
				} else {
					try {
						EntityPlayer en = Utils.getPlayer(args[1]);
						if (Utils.isPlayer(en)) {
							if (en.getCurrentEquippedItem()!=null) {
								try {
									ItemStack items = en.getCurrentEquippedItem();
									mc.thePlayer.inventory.addItemStackToInventory(items);
								} catch (Exception e) {
									Utils.addChat(err);
								}
							}
							if (en.getCurrentArmor(0)!=null) {
								try {
									ItemStack items = en.getCurrentArmor(0);
									mc.thePlayer.inventory.addItemStackToInventory(items);
								} catch (Exception e) {
									Utils.addChat(err);
								}
							}
							if (en.getCurrentArmor(1)!=null) {
								try {
									ItemStack items = en.getCurrentArmor(1);
									mc.thePlayer.inventory.addItemStackToInventory(items);
								} catch (Exception e) {
									Utils.addChat(err);
								}
							}
							if (en.getCurrentArmor(2)!=null) {
								try {
									ItemStack items = en.getCurrentArmor(2);
									mc.thePlayer.inventory.addItemStackToInventory(items);
								} catch (Exception e) {
									Utils.addChat(err);
								}
							}
							if (en.getCurrentArmor(3)!=null) {
								try {
									ItemStack items = en.getCurrentArmor(3);
									mc.thePlayer.inventory.addItemStackToInventory(items);
								} catch (Exception e) {
									Utils.addChat(err);
								}
							}
						}
					} catch (Exception e) {
						Utils.addChat("§cErreur: Le joueur n'existe pas");
					}
				}
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"size")) {
				if (args.length==1) {
					Utils.addChat(err);
				} else {
					if (Utils.isInteger(args[1])) {
						int size = Integer.parseInt(args[1]);
						ItemStack item = mc.thePlayer.getCurrentEquippedItem();
						if (item!=null) {
							item.stackSize=size;
							mc.getNetHandler().addToSendQueue(new C10PacketCreativeInventoryAction(mc.thePlayer.inventory.currentItem, item));
							Minecraft.thePlayer.sendQueue.addToSendQueue(new C09PacketHeldItemChange(mc.thePlayer.inventory.currentItem));
						} else {
							Utils.addChat("§cAucun item en main");
						}
					}
				}
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}

			if (args[0].equalsIgnoreCase(var.prefixCmd+"gm")) {
				if (args.length==1) {
					Utils.addChat(err);
				} else {
					if (Utils.isInteger(args[1])) {
						int id = Integer.parseInt(args[1]);
						try {
							mc.playerController.setGameType(GameType.getByID(id));
							mc.thePlayer.sendChatMessage("/gamemode "+id);
						} catch (Exception e) {
							Utils.addChat(err);
						}
					} else {
						mc.playerController.setGameType(GameType.getByName(args[1]));
						mc.thePlayer.sendChatMessage("/gamemode "+args[1]);
					}
				}
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"autopot")) {
				if (args.length==1) {
					Utils.toggleModule("AutoPot");
				} else {
					try {
						AutoPot.heal=Integer.parseInt(args[1]);
						Utils.addChat("§aSeuil de vie mis à "+args[1]+" !");
					} catch (Exception e) {
						Utils.addChat(err);
					}
				}
				Utils.checkXp(xp);
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"glide")) {
				try {
					Glide.getGlide().setSpeed(Double.parseDouble(args[1])*-1);
					Utils.addChat("§aVitesse du glide changé à "+args[1]+" !");
				} catch (Exception e) {
					Utils.addChat(err);
				}
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"pyro")) {
				if (args.length==1) {
					Utils.toggleModule("Pyro");
				} else if (args[1].equalsIgnoreCase("list")) {
					String modes="";
					int i=0;
					for (Form f : Form.values()) {
						i++;
						if (Form.values().length!=i)
							modes+=f+"§7, §a";
						else
							modes+=f;
					}
					Utils.addChat("Modes disponibles: §a"+modes);		
				} else if (args.length>=2){
					try {
						String mode = args[1].toLowerCase();
						mode = mode.replaceFirst(".", (mode.charAt(0)+"").toUpperCase());
						
						Pyro.mode=Form.valueOf(mode);
						Utils.addChat("§aPyro passé en mode "+mode+" !");
					} catch (Exception e) {
						Utils.addChat("§cCe mode n'existe pas. Essayez "+var.prefixCmd+"pyro list");
					}
				}
				Utils.checkXp(xp);
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}	
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"server")) {
				String page = "1";
				if (args.length>=2)
					page = args[1];
				if (Utils.isLock("--server")) {
					Utils.addWarn("Server");
					mc.thePlayer.playSound("mob.villager.no", 1.0F, 1.0F);
				} else if (Utils.isInteger(page)){		
					int p = Integer.parseInt(page);
					page = ""+p*2;
					ArrayList<String> list = new ArrayList<>();
					list.add(page);
					new RequestThread("getServer", list).start();
					Utils.checkXp(xp);						
					mc.ingameGUI.getChatGUI().addToSentMessages(var3);
				} else {
					Utils.addChat("§cErreur");
				}
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"prefix")) {
				if (args.length==1) {
					Utils.addChat(error);
				} else {
					var.prefixCmd=args[1];
				}
				Utils.checkXp(xp);
				Utils.addChat("§aPréfix changé !");
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}	
			//TODO: OnlyRpg
			if (args[0].equalsIgnoreCase(var.prefixCmd+"onlyrpg")) {
				if (args.length==1) {
					if (var.onlyrpg.isActive()) {						
						Utils.addChat("§cOnlyRpg désactivé !");
						var.onlyrpg.setTimeSec(0);
					} else {						
						Utils.addChat("§aOnlyRpg activé !");						
					}
					var.onlyrpg.setActive(!var.onlyrpg.isActive());
					if (var.onlyrpg.isActive()) {
						for (Module m : ModuleManager.ActiveModule) {
							boolean v = false;
							for (String c : var.onlyrpg.getCheat()) {
								if (c.equalsIgnoreCase(m.getName()))
									v = true;
							}
							if (!v) {
								if (m.getToggled()) {
									boolean bl = Utils.display;
									Utils.display = false;
									m.onDisabled();
									Utils.display = bl;
								}
								m.setWithoutToggle(false);
							}							
						}	
					}
					var.gui = new GuiManager();
					var.gui.setTheme(new SimpleTheme());
					var.gui.setup();
					Utils.loadFrame();
					var.onlyrpg.setTimeSec(0);
				}
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"build")) {
				Build b = Build.getBuild();
				if (args.length==1) {
					Utils.toggleModule("Build");					
				} else if (args[1].equalsIgnoreCase("Down")) {
					if (b.isDown()) {
						Utils.addChat("§cMode Down désactivé");
					} else {
						Utils.addChat("§aMode Down activé");
					}
					b.setDown(!b.isDown());
				} else if (args[1].equalsIgnoreCase("Up")) {
					if (b.isUp()) {
						Utils.addChat("§cMode Up désactivé");
					} else {
						Utils.addChat("§aMode Up activé");
					}
					b.setUp(!b.isUp());
				} else if (args[1].equalsIgnoreCase("Wall")) {
					if (b.isWall()) {
						Utils.addChat("§cMode Wall désactivé");
					} else {
						Utils.addChat("§aMode Wall activé");
					}
					b.setWall(!b.isWall());
				} else if (args[1].equalsIgnoreCase("Sneak")) {
					if (b.isSneak()) {
						Utils.addChat("§cMode Sneak désactivé");
					} else {
						Utils.addChat("§aMode Sneak activé");
					}
					b.setSneak(!b.isSneak());
				}				
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}	
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"potion")) {
				try {
					if (!mc.thePlayer.capabilities.isCreativeMode) {
				      Utils.addChat("§cVous n'êtes pas en Créatif !");
				      return;
				    }
					if (mc.thePlayer.getCurrentEquippedItem()==null) {
						Utils.addChat("§cLe slot sur lequel vous êtes doit être libre !");
					}
				} catch (Exception e) {Utils.addChat("§cErreur...");return;}
			    ItemStack stack = new ItemStack(Items.potionitem);
			    stack.setItemDamage(16384);
			    NBTTagList effects = new NBTTagList();
			    NBTTagCompound effect = new NBTTagCompound();
			    effect.setInteger("Amplifier", 125);
			    effect.setInteger("Duration", 2000);
			    effect.setInteger("Id", 6);
			    effects.appendTag(effect);
			    stack.setTagInfo("CustomPotionEffects", effects);
			    stack.setStackDisplayName("§d§lKawaii Neko Potion :3");
			    
			    mc.thePlayer.sendQueue.addToSendQueue(new C10PacketCreativeInventoryAction(mc.thePlayer.inventory.currentItem, stack));
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}	
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"namemc") || args[0].equalsIgnoreCase(var.prefixCmd+"name")) {
				if (args.length==1) {
					Utils.addChat("§cErreur, pas de pseudos donnés");
				} else {
					new Thread(new Runnable() {
						public void run() {
							String s = Utils.getOldNames(args[1]);
							if (s.isEmpty()) {
								Utils.addChat("§cAucun pseudos trouvés");
							} else {
								Utils.addChat("§7[§aDerniers pseudos§7]");
								String list[] = s.split(" ");
								for (int i = list.length-1;i!=-1;i--)
									Utils.addChat("§7+§a"+list[i]);
							}
						}
					}).start();
				}
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}	
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"config") || args[0].equalsIgnoreCase(var.prefixCmd+"cfg")) {
				if (args.length==1) {
					Utils.addChat(Utils.setColor("Permet de charger, sauver et supprimer des config définies", "§a"));
					Utils.addChat(Utils.setColor("En sauvant les config, ça prend vos réglages actuelles et les stocks", "§a"));
					Utils.addChat(var.prefixCmd+"config save <Nom>: "+Utils.setColor("Sauve une config sous un nom choisit", "§7"));
					Utils.addChat(var.prefixCmd+"config <delete:del:remove:rem:rm> <Nom>: "+Utils.setColor("Supprime une config", "§7"));
					Utils.addChat(var.prefixCmd+"config load <Nom>: "+Utils.setColor("Charge une config existante", "§7"));
					Utils.addChat(var.prefixCmd+"config list: "+Utils.setColor("Affiche la liste des configs disponibles", "§7"));
				} else if (args[1].equalsIgnoreCase("save") && args.length>=3) {
					String fi = Utils.linkSave+(mc.isRunningOnMac ? "/Config/"+args[2]+"/" : "\\Config\\"+args[2]+"\\");
					new File(fi).mkdirs();
					Utils.saveBind(fi);
					Utils.saveCmd(fi);
					Utils.saveFriends(fi);
					Utils.saveMod(fi);
					Utils.saveNuker(fi);
					Utils.saveShit(fi);
					Utils.saveValues(fi);
					Utils.saveXray(fi);
					Utils.addChat("§aConfig "+args[2]+" crée !");
				} else if (args[1].equalsIgnoreCase("load") && args.length>=3) {
					String fi = Utils.linkSave+(mc.isRunningOnMac ? "/Config/"+args[2]+"/" : "\\Config\\"+args[2]+"\\");
					if (new File(fi).exists()) {	
						boolean dis = Utils.display;
						Utils.display = false;
						Utils.cfg=true;
						Utils.panic();
						Utils.loadCmd(fi);
						Utils.loadBind(fi);						
						Utils.loadFriends(fi);
						Utils.loadMod(fi);
						Utils.loadNuker(fi);
						Utils.loadShit(fi);
						Utils.loadValues(fi);
						Utils.loadXray(fi);
						Utils.cfg=false;
						Utils.display = dis;
						Utils.addChat("§aConfig "+args[2]+" chargée !");
					} else {
						Utils.addChat("§cErreur, la config n'existe pas...");
					}
				} else if ((args[1].equalsIgnoreCase("remove") || args[1].equalsIgnoreCase("rem") || args[1].equalsIgnoreCase("rm") || args[1].equalsIgnoreCase("delete") || args[1].equalsIgnoreCase("del")) && args.length>=3) {
					String fi = Utils.linkSave+(mc.isRunningOnMac ? "/Config/"+args[2] : "\\Config\\"+args[2]);
					File f = new File(fi);
					if (f.exists()) {
						Utils.deleteDirectory(f);
						Utils.addChat("§aConfig supprimée !");
					} else {
						Utils.addChat("§cErreur, la config n'existe pas...");
					}
				} else if (args[1].equalsIgnoreCase("list")) {
					try {
						String fi = Utils.linkSave+(mc.isRunningOnMac ? "/Config/" : "\\Config\\");
						String [] list; 
						String totConfig="";
						int i; 
						list=new File(fi).list(); 					
						for(i=0;i<list.length;i++) {
							if (!new File(fi+list[i]).isFile())
								totConfig+=list[i]+", ";
						}
						if (totConfig.isEmpty()) {
							Utils.addChat("§cAucunes config disponibles...");
						} else {
							Utils.addChat("Configs disponibles: "+Utils.setColor(totConfig.substring(0,totConfig.length()-2), "§7"));
						}
					} catch (Exception e) {
						Utils.addChat("§cAucunes config disponibles...");
					}
					
				}
				
				
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"reset")) {
				Utils.resetValues();
				Utils.addChat("§aValues reset !");
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}						
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"fastbow") || args[0].equalsIgnoreCase(var.prefixCmd+"fb")) {
				if (args[1].equalsIgnoreCase("nobow")) {
					if (Fastbow.getFast().isNobow()) {
						Utils.addChat("§cFastbow nobow désactivé");
					} else {
						Utils.addChat("§aFastbow nobow activé");
					}
					Fastbow.getFast().setNobow(!Fastbow.getFast().isNobow());
				} else if (args[1].equalsIgnoreCase("packet") && args.length>=3) {
					if (Utils.isInteger(args[2])) {
						Fastbow.getFast().setPacket(Integer.parseInt(args[2]));
						Utils.addChat("§aLes paquets envoyés sont mis à "+args[2]+" !");
					} else {
						Utils.addChat(err);
					}
				}
				Utils.checkXp(xp);
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"bowaimbot") || args[0].equalsIgnoreCase(var.prefixCmd+"bowaim")) {
				BowAimbot b = BowAimbot.getAim();
				if (args[1].equalsIgnoreCase("fov")) {
					try {
						b.setFov(Double.parseDouble(args[2]));
						Utils.addChat("§aFov du BowAimbot mis à "+b.getFov()+"° !");
					} catch (Exception e) {
						Utils.addChat(err);
					}
				} else if (args[1].equalsIgnoreCase("life")) {
					b.setLife(Utils.pass(b.getLife()));
					Utils.addChat("§aMode life mis à "+b.getLife()+" !");
				} else if (args[1].equalsIgnoreCase("armor")) {
					b.setArmor(Utils.pass(b.getArmor()));
					Utils.addChat("§aMode armor mis à "+b.getArmor()+" !");
				}
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"disc")) {
				if (args.length==1) {
					Utils.addChat(error);
				} else {
					if (args.length==3) {
						try {
							mc.thePlayer.playSound("records."+args[1], Float.parseFloat(args[2]), 1.0F);
							Utils.addChat("§aDisque "+args[1]+" est joué !");
						} catch (Exception e) {
							Utils.addChat(err);
						}
					} else {
						mc.thePlayer.playSound("records."+args[1], 1.0F, 1.0F);
						Utils.addChat("§aDisque "+args[1]+" est joué !");
					}
				}
				Utils.checkXp(xp);
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"playsound")) {
				if (args.length==1) {
					Utils.addChat(error);
				} else {
					if (args.length==3) {
						try {
							mc.thePlayer.playSound(args[1], Float.parseFloat(args[2]), 1.0F);
							Utils.addChat("§aLe son "+args[1]+" est joué !");
						} catch (Exception e) {
							Utils.addChat(err);
						}
					} else {
						mc.thePlayer.playSound(args[1], 1.0F, 1.0F);
						Utils.addChat("§aLe son "+args[1]+" est joué !");
					}
				}
				Utils.checkXp(xp);
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}			
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"option")) {
				if (args.length==1) {
					Utils.addChat("§cErreur, essayez "+var.prefixCmd+"help option");
				} else if (args[1].equalsIgnoreCase("zoom")) {
					if (Utils.zoom) {
						Utils.zoom=false;
						Utils.addChat("§cZoom désactivé");
					} else {
						Utils.zoom=true;
						Utils.addChat("§aZoom activé");
					}
				} else if (args[1].equalsIgnoreCase("display")) {
					if (Utils.display) {
						Utils.display=false;
						Utils.addChat("§cDisplay désactivé");
					} else {
						Utils.display=true;
						Utils.addChat("§aDisplay activé");
					}
				} else if (args[1].equalsIgnoreCase("xp")) {
					if (Utils.xp) {
						Utils.xp=false;
						Utils.addChat("§cAffichage d'xp désactivé");
					} else {
						Utils.xp=true;
						Utils.addChat("§aAffichage d'xp activé");
					}
				} else if (args[1].equalsIgnoreCase("scoreboard") || args[1].equalsIgnoreCase("sb")) {
					if (Utils.scoreboard) {
						Utils.scoreboard=false;
						Utils.addChat("§cScoreboard désactivé");
					} else {
						Utils.scoreboard=true;
						Utils.addChat("§aScoreboard activé");
					}
				} else if (args[1].equalsIgnoreCase("deathoff")) {
					if (Utils.deathoff) {
						Utils.deathoff=false;
						Utils.addChat("§cDeathOff désactivé");
					} else {
						Utils.deathoff=true;
						Utils.addChat("§aDeathOff activé");
					}
				}
				Utils.checkXp(xp);
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"ip")) {
				Utils.checkXp(xp);	
				if (!mc.isSingleplayer()) {
					Utils.addChat("§aVous êtes connecté sur "+mc.getCurrentServerData().serverIP);
					try {
						Utils.addChat("("+InetAddress.getByName(mc.getCurrentServerData().serverIP).getHostAddress()+")");
					} catch (Exception e) {}
				} else 
					Utils.addChat("§aVous êtes connecté sur un monde solo");
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"join")) {
				if (args.length==1 || mc.isSingleplayer()) {
					Utils.addChat(Utils.setColor("Erreur, utilisation correcte: "+var.prefixCmd+"join <NomJoueur>", "§c"));
				} else {
					new Thread(new Runnable() {
						
						@Override
						public void run() {
							try {
								ServerAddress var4 = ServerAddress.func_78860_a(mc.getCurrentServerData().serverIP);
								InetAddress var1 = InetAddress.getByName(mc.getCurrentServerData().serverIP);
			                    NetworkManager n = NetworkManager.provideLanClient(var1, var4.getPort());
			                    n.setNetHandler(new NetHandlerLoginClient(n, null, null));
			                    n.sendPacket(new C00Handshake(47, mc.getCurrentServerData().serverIP, var4.getPort(), EnumConnectionState.LOGIN));
			                    n.sendPacket(new C00PacketLoginStart(new GameProfile(UUID.randomUUID(), args[1])));
			                    n.checkDisconnected();
							} catch (Exception e) {}							
						}
					}).start();
				}
				
				
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"update")) {				
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
							Client.getNeko().ver=s.get(0);
							Client.getNeko().changelog=s.get(1);
							try {
								URI url1 = URI.create("https://nekohc.fr");
								Desktop.getDesktop().browse(url1);
								mc.displayGuiScreen(new GuiMainMenu());
							} catch (Exception e) {}
						} else {
							Utils.addChat("§dVersion à jour !");
						}
						sc.close();
					} catch (Exception e) {
						Utils.addChat("§cAdresse inateignable :c");
					}
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"end")) {
				Utils.dim=true;
				Utils.checkXp(xp);												
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
			
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"nuker")) {
				if (args.length==1) {
					Utils.toggleModule("Nuker");
				} else if (args[1].equalsIgnoreCase("add")) {
					try {
						if (Utils.isInteger(args[2])) {
							int x = Integer.parseInt(args[2]);						
							int c=0;
							for (int i=0;i<Nuker.nuke.size();i++) {
								if (Nuker.nuke.get(i)==x) {
									c++;
								}
							}
							if (c!=0) {
								Utils.addChat("§cLe bloc "+Block.getBlockById(x).getLocalizedName()+" a déjà été ajouté !");									
							} else {
								Nuker.nuke.add(x);
								Utils.addChat("§aLe bloc "+Block.getBlockById(x).getLocalizedName()+" a été ajouté !");
							}
						} else {					
							int c=0;
							if (Utils.isABlock(args[2])) {
								for (int i=0;i<Nuker.nuke.size();i++) {
									if (Block.getBlockById(Nuker.nuke.get(i)).getLocalizedName()==Block.getBlockFromName(args[2]).getLocalizedName()) {
										c++;
									}
								}
								if (c!=0) {
									Utils.addChat("§cLe bloc "+Block.getBlockById(Block.getIdFromBlock(Block.getBlockFromName(args[2]))).getLocalizedName()+" a déjà été ajouté !");									
								} else {
									Nuker.nuke.add(Block.getIdFromBlock(Block.getBlockFromName(args[2])));
									Utils.addChat("§aLe bloc "+Block.getBlockById(Block.getIdFromBlock(Block.getBlockFromName(args[2]))).getLocalizedName()+" a été ajouté !");
								}
							} else {
								Utils.addChat("§cCe bloc n'existe pas !");
							}
						}
						
					} catch (Exception e) {
						Utils.addChat(err);
					}
					Utils.saveNuker();												
			} else if (args[1].equalsIgnoreCase("rem")) {
				try {
					if (Utils.isInteger(args[2])) {
						int c=0;
						int x = Integer.parseInt(args[2]);
						for (int j=0;j<Nuker.nuke.size();j++) {
							if (Nuker.nuke.get(j)==x) {
								Nuker.nuke.remove(j);
								c++;									
							}
						}
						if (c!=0) {
							Utils.addChat("§aLe bloc "+Block.getBlockById(x).getLocalizedName()+" a été retiré !");
						} else {
							Utils.addChat("§cLe bloc "+Block.getBlockById(x).getLocalizedName()+" n'est pas dans la liste !");
						}
					} else {
						int c=0;
						for (int j=0;j<Nuker.nuke.size();j++) {
							if (Block.getBlockById(Nuker.nuke.get(j)).getLocalizedName()==Block.getBlockFromName(args[2]).getLocalizedName()) {
								c++;
								Nuker.nuke.remove(j);
							}									
							
						}
						if (c!=0) {
							Utils.addChat("§aLe bloc "+Block.getBlockById(Block.getIdFromBlock(Block.getBlockFromName(args[2]))).getLocalizedName()+" a été retiré !");
						} else {
							Utils.addChat("§cLe bloc "+Block.getBlockById(Block.getIdFromBlock(Block.getBlockFromName(args[2]))).getLocalizedName()+" n'est pas dans la liste !");
						}
					}
				} catch (Exception e) {
					Utils.addChat(err);
				}
				Utils.saveNuker();	
			} else if (args[1].equalsIgnoreCase("range")) {
				try {
					Nuker.nukerRadius=Double.parseDouble(args[2]);
					Utils.addChat("§aLa range du Nuker a été changée à "+args[2]+" !");
				} catch (Exception e) {
					Utils.addChat(err);
				}
			} else if (args[1].equalsIgnoreCase("clear")) {
				try {
					Nuker.nuke.clear();
					Utils.addChat("§aListe vidée !");
				} catch (Exception e) {
					Utils.addChat(err);
				}
			} else if (args[1].equalsIgnoreCase("safe")) {
				if (Nuker.safe) {
					Utils.addChat("§cNuker safe désactivé !");
				} else {
					Utils.addChat("§aNuker safe activé !");
				}
				Nuker.safe=!Nuker.safe;
			} else if (args[1].equalsIgnoreCase("list")) {
				try {
					if (Nuker.nuke.size()==0) {
						Utils.addChat("§cAucun blocs ajoutés");
					} else {
						Collections.sort(Nuker.nuke);
						Utils.addChat("=========Nuker=========");
						for (int j=0;j<Nuker.nuke.size();j++) {
							Utils.addChat("Block : "+Block.getBlockById(Nuker.nuke.get(j)).getLocalizedName());
						}
						Utils.addChat("=======================");
					}
				} catch (Exception e) {
					Utils.addChat(err);
				}
			}
				Utils.checkXp(xp);
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"dropshit") || args[0].equalsIgnoreCase(var.prefixCmd+"drop")) {
				DropShit sh = DropShit.getShit();
				if (args.length==1) {
					Utils.toggleModule("DropShit");
				} else if (args[1].equalsIgnoreCase("add")) {
					try {
						if (Utils.isInteger(args[2])) {
							int x = Integer.parseInt(args[2]);						
							int c=0;
							for (int i=0;i<sh.getList().size();i++) {
								if (sh.getList().get(i)==x) {
									c++;
								}
							}
							if (c!=0) {
								Utils.addChat("§cL'item "+Item.getItemById(x).getUnlocalizedName().replaceFirst("tile.", "")+" a déjà été ajouté !");									
							} else {
								DropShit.getShit().getList().add(x);
								Utils.addChat("§aL'item "+Item.getItemById(x).getUnlocalizedName().replaceFirst("tile.", "")+" a été ajouté !");
							}
						} else {					
							int c=0;
							if (Utils.isAItem(args[2])) {
								for (int i=0;i<sh.getList().size();i++) {
									if (Item.getItemById(sh.getList().get(i))==Item.getByNameOrId(args[2])) {
										c++;
									}
								}
								if (c!=0) {
									Utils.addChat("§cL'item "+Item.getItemById(Item.getIdFromItem(Item.getByNameOrId(args[2]))).getUnlocalizedName().replaceFirst("tile.", "")+" a déjà été ajouté !");									
								} else {
									sh.getList().add(Item.getIdFromItem(Item.getByNameOrId(args[2])));
									Utils.addChat("§aL'item "+Item.getItemById(Item.getIdFromItem(Item.getByNameOrId(args[2]))).getUnlocalizedName().replaceFirst("tile.", "")+" a été ajouté !");
								}
							} else {
								Utils.addChat("§cCet item n'existe pas !");
							}
						}
						
					} catch (Exception e) {
						Utils.addChat(err);
					}
					Utils.saveXray();												
			} else if (args[1].equalsIgnoreCase("clear")) {
				try {
					sh.getList().clear();
					Utils.addChat("§aListe vidée !");
				} catch (Exception e) {
					Utils.addChat(err);
				}
			} else if (args[1].equalsIgnoreCase("all")) {
				for (int i = 0; i < 45; i++) {
					if (mc.thePlayer.inventoryContainer.getSlot(i).getStack() != null) {
						ItemStack is = mc.thePlayer.inventoryContainer.getSlot(i).getStack();
						Item item = is.getItem();
						mc.playerController.windowClick(0, i, 0, 0, mc.thePlayer);
			        	mc.playerController.windowClick(0, -999, 0, 0, mc.thePlayer);
					}
				}
			} else if (args[1].equalsIgnoreCase("rem") || args[1].equalsIgnoreCase("rm") || args[1].equalsIgnoreCase("del")) {
				try {
					if (Utils.isInteger(args[2])) {
						int c=0;
						int x = Integer.parseInt(args[2]);
						for (int j=0;j<sh.getList().size();j++) {
							if (sh.getList().get(j)==x) {
								sh.getList().remove(j);
								c++;									
							}
						}
						if (c!=0) {
							Utils.addChat("§aL'item "+Item.getItemById(x).getUnlocalizedName().replaceFirst("tile.", "")+" a été retiré !");
						} else {
							Utils.addChat("§cL'item "+Item.getItemById(x).getUnlocalizedName().replaceFirst("tile.", "")+" n'est pas dans la liste !");
						}
					} else {
						int c=0;
						for (int j=0;j<sh.getList().size();j++) {
							if (Item.getItemById(sh.getList().get(j))==Item.getByNameOrId(args[2])) {
								c++;
								sh.getList().remove(j);
							}									
							
						}
						if (c!=0) {
							Utils.addChat("§aL'item "+Item.getItemById(Item.getIdFromItem(Item.getByNameOrId(args[2]))).getUnlocalizedName().replaceFirst("tile.", "")+" a été retiré !");
						} else {
							Utils.addChat("§cL'item "+Item.getItemById(Item.getIdFromItem(Item.getByNameOrId(args[2]))).getUnlocalizedName().replaceFirst("tile.", "")+" n'est pas dans la liste !");
						}
					}
				} catch (Exception e) {
					Utils.addChat(err);
				}
				Utils.saveXray();
			} else if (args[1].equalsIgnoreCase("list")) {
				try {
					if (sh.getList().size()==0) {
						Utils.addChat("Aucun items ajoutés");
					} else {
						Utils.addChat("=========DropShit=========");
						for (int j=0;j<sh.getList().size();j++) {
							Utils.addChat("Item "+(j+1)+" : "+Item.getItemById(sh.getList().get(j)).getUnlocalizedName().replaceFirst("tile.", ""));
						}
						Utils.addChat("======================");
					}
				} catch (Exception e) {
					Utils.addChat(err);
				}
			}
				Utils.checkXp(xp);
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"xray")) {
				if (args.length==1) {
					Utils.toggleModule("Xray");
				} else if (args[1].equalsIgnoreCase("add")) {
					try {
						if (Utils.isInteger(args[2])) {
							int x = Integer.parseInt(args[2]);						
							int c=0;
							for (int i=0;i<Xray.xray.size();i++) {
								if (Xray.xray.get(i)==x) {
									c++;
								}
							}
							if (c!=0) {
								Utils.addChat("§cLe bloc "+Block.getBlockById(x).getLocalizedName()+" a déjà été ajouté !");									
							} else {
								Xray.xray.add(x);
								Utils.addChat("§aLe bloc "+Block.getBlockById(x).getLocalizedName()+" a été ajouté !");
							}
						} else {					
							int c=0;
							if (Utils.isABlock(args[2])) {
								for (int i=0;i<Xray.xray.size();i++) {
									if (Block.getBlockById(Xray.xray.get(i)).getLocalizedName()==Block.getBlockFromName(args[2]).getLocalizedName()) {
										c++;
									}
								}
								if (c!=0) {
									Utils.addChat("§cLe bloc "+Block.getBlockById(Block.getIdFromBlock(Block.getBlockFromName(args[2]))).getLocalizedName()+" a déjà été ajouté !");									
								} else {
									Xray.xray.add(Block.getIdFromBlock(Block.getBlockFromName(args[2])));
									Utils.addChat("§aLe bloc "+Block.getBlockById(Block.getIdFromBlock(Block.getBlockFromName(args[2]))).getLocalizedName()+" a été ajouté !");
								}
							} else {
								Utils.addChat("§cCe bloc n'existe pas !");
							}
						}
						
					} catch (Exception e) {
						Utils.addChat(err);
					}
					Utils.saveXray();												
			} else if (args[1].equalsIgnoreCase("clear")) {
				try {
					Xray.xray.clear();
					Utils.addChat("§aListe vidée !");
				} catch (Exception e) {
					Utils.addChat(err);
				}
			} else if (args[1].equalsIgnoreCase("rem")) {
				try {
					if (Utils.isInteger(args[2])) {
						int c=0;
						int x = Integer.parseInt(args[2]);
						for (int j=0;j<Xray.xray.size();j++) {
							if (Xray.xray.get(j)==x) {
								Xray.xray.remove(j);
								c++;									
							}
						}
						if (c!=0) {
							Utils.addChat("§aLe bloc "+Block.getBlockById(x).getLocalizedName()+" a été retiré !");
						} else {
							Utils.addChat("§cLe bloc "+Block.getBlockById(x).getLocalizedName()+" n'est pas dans la liste !");
						}
					} else {
						int c=0;
						for (int j=0;j<Xray.xray.size();j++) {
							if (Block.getBlockById(Xray.xray.get(j)).getLocalizedName()==Block.getBlockFromName(args[2]).getLocalizedName()) {
								c++;
								Xray.xray.remove(j);
							}									
							
						}
						if (c!=0) {
							Utils.addChat("§aLe bloc "+Block.getBlockById(Block.getIdFromBlock(Block.getBlockFromName(args[2]))).getLocalizedName()+" a été retiré !");
						} else {
							Utils.addChat("§cLe bloc "+Block.getBlockById(Block.getIdFromBlock(Block.getBlockFromName(args[2]))).getLocalizedName()+" n'est pas dans la liste !");
						}
					}
				} catch (Exception e) {
					Utils.addChat(err);
				}
				Utils.saveXray();
			} else if (args[1].equalsIgnoreCase("list")) {
				try {
					if (Xray.xray.size()==0) {
						Utils.addChat("Aucun blocs ajoutés");
					} else {
						Utils.addChat("=========Xray=========");
						for (int j=0;j<Xray.xray.size();j++) {
							Utils.addChat("Block : "+Block.getBlockById(Xray.xray.get(j)).getLocalizedName());
						}
						Utils.addChat("======================");
					}
				} catch (Exception e) {
					Utils.addChat(err);
				}
			}
				Utils.checkXp(xp);
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"bind")) {
				if (args.length==1) {
					Utils.addChat("§cUtilisation correcte: "+var.prefixCmd+"bind <cheat> <touche>");
					mc.thePlayer.playSound("mob.villager.haggle", 1.0F, 1.0F);
				} else if (args.length==2) {
					Utils.addChat("§cUtilisation correcte: "+var.prefixCmd+"bind <cheat> <touche>");
					mc.thePlayer.playSound("mob.villager.haggle", 1.0F, 1.0F);
				} else {
					try {
						Utils.setBind(args[1], args[2].toUpperCase());
						Utils.saveBind();
					} catch (Exception e) {
                        Utils.addChat(err);
                    }
				}
				Utils.checkXp(xp);
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"vote")) {
				if (args.length==1) {
					Utils.addChat("§cUtilisation correcte: "+var.prefixCmd+"vote <Ip de serveur>");
					mc.thePlayer.playSound("mob.villager.haggle", 1.0F, 1.0F);
				} else if (args.length>=2) {
					String ip = args[1];
					// vérif que c'est bien un serveur					
					if (!ip.equalsIgnoreCase("Localhost") && !ip.equalsIgnoreCase("127.0.0.1") && !Utils.ipVote.contains(ip) && Utils.isIP(ip) && Utils.ipVote.size()<=10) {
						ArrayList<String> list = new ArrayList<>();
						list.add(ip);
						new RequestThread("vote", list).start();
						Utils.ipVote.add(ip);
					} else {
						Utils.addChat("§cErreur, le serveur a déjà été voté ou ip invalide..");
					}
				}
				Utils.checkXp(xp);
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"warn")) {
				if (args.length==1) {
					if (Utils.warn) {
						Utils.warn=false;
						Utils.addChat("§cWarn désactivé");
					} else {
						Utils.warn=true;
						Utils.addChat("§aWarn activé à "+Utils.warnB+" blocs");
					}
				} else {
					try {
						Utils.warnB=Double.parseDouble(args[1]);
					
						if (Utils.warn) {
							Utils.warn=false;
							Utils.addChat("§cWarn désactivé");
						} else {
							Utils.warn=true;
							Utils.addChat("§aWarn activé à "+Utils.warnB+" blocs");
						}
					} catch (Exception e) {
                        Utils.addChat(err);
                    }
				}
				Utils.checkXp(xp);						
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"radar") || args[0].equalsIgnoreCase(var.prefixCmd+"r")) {
				if (args.length==1)
					Utils.toggleModule("Radar");
				else if (args[1].equalsIgnoreCase("fr") || args[1].equalsIgnoreCase("friend") || args[1].equalsIgnoreCase("friends")) {
					if (Radar.fr) {
						Radar.fr=false;
						Utils.addChat("§cAffichage des friends dans le radar désactivé");
					} else {
						Radar.fr=true;
						Utils.addChat("§aAffichage des friends dans le radar activé");
					}
				}
				Utils.checkXp(xp);						
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"nametag")) {
				if (args.length==1) {
					Utils.toggleModule("Nametag");
				} else {
					try {
						Render.varNeko=Float.parseFloat(args[1]);
						Utils.addChat("§aLe nametag a été modifié à "+args[1]+" !");
					} catch (Exception e) {
                        Utils.addChat(err);
                    }
				}
				Utils.checkXp(xp);
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"shutdown")) {
				mc.shutdown();
			}
			
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"sword")) {
				if (Utils.isLock("--sword")) {
					Utils.addWarn("Sword");
					mc.thePlayer.playSound("mob.villager.no", 1.0F, 1.0F);
				} else if (Utils.sword) {
					Utils.sword=false;
					Utils.addChat("§cSword désactivé");
				} else {
					Utils.sword=true;
					Utils.addChat("§aSword activé");
				}
				Utils.checkXp(xp);
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"l") || args[0].equalsIgnoreCase(var.prefixCmd+"arraylist")) {
				if (args.length==1) {
					Utils.toggleModule("ArrayList");
				} else {
					try {
						if (args[1].contains("&")) {
							InGameGui.color=args[1].replaceAll("&", "§");
							Utils.addChat(InGameGui.color+"Couleur de l'ArrayList changée");
						} else if (Utils.isInteger(args[1])) {
							InGameGui.color="§"+args[1];
							Utils.addChat(InGameGui.color+"Couleur de l'ArrayList changée");
						} else {
							Utils.addChat(err);
						}
					} catch (Exception e) {
                        Utils.addChat(err);
                    }
				}
				Utils.checkXp(xp);
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"autoarmor") || args[0].equalsIgnoreCase(var.prefixCmd+"aa")) {
				if (args.length==1) {
					Utils.toggleModule("Autoarmor");
				} else if (args[1].equalsIgnoreCase("ec")) {
						if (Autoarmor.ec) {
							Autoarmor.ec=!Autoarmor.ec;
							Utils.addChat("§cL'autoarmor est désactivé sur Epicube !");
						} else {
							Autoarmor.ec=!Autoarmor.ec;
							Utils.addChat("§aL'autoarmor est activé sur Epicube !");
						}
				}
				Utils.checkXp(xp);
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"bc") || args[0].equalsIgnoreCase(var.prefixCmd+"broadcast")) {
				Utils.displayAn();
				Utils.checkXp(xp);
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}

			if (args[0].equalsIgnoreCase(var.prefixCmd+"yt")) {
				if (args.length==1)
					mc.thePlayer.sendChatMessage("-[Ma chaîne YouTube: Tryliom]- ==> Vidéos de cheat et tuto dév Java");
				else if (args[1].equalsIgnoreCase("prefix") && args.length>=3) {
					String prefix = "";
					if (args.length>1) {
						for (int i=1;i<args.length;i++)
							prefix=args[i]+" ";
					}
					mc.thePlayer.sendChatMessage(prefix+"-[Ma chaîne YouTube: Tryliom]- ={Vidéos de cheat et tuto dév Java}=");
				} else
					mc.thePlayer.sendChatMessage("-[Ma chaîne YouTube: "+args[1]+"]-");
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"limit")) {
				if (args.length==1) {
					if (Utils.limite)
						Utils.addChat("§cLimite désactivé !");
					else
						Utils.addChat("§aLimite activé !");
					Utils.limite=!Utils.limite;
				} else {
					if (Utils.isInteger(args[1])) {
						Utils.limit=Integer.parseInt(args[1]);
						Utils.addChat("§aLimite changée à "+args[1]+" !");
					} else 
						Utils.addChat(err);
				}
				
				
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
			
		}
		doCommand2();
	}
		public void doCommand2() {
			if (var3.startsWith(var.prefixCmd) && Utils.verif==null) {
			if (args[0].equalsIgnoreCase(var.prefixCmd+"highjump")) {
				if (args[1].equalsIgnoreCase("height")) {
					try {
						Highjump.getJump().setHeight(Float.parseFloat(args[2]));
						Utils.addChat("§aLe height du Highjump a été mise à "+args[2]+" !");
					} catch (Exception e) {
                        Utils.addChat(err);
                    }
				}
				Utils.checkXp(xp);
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"kick")) {
				String s = "Kické par Neko";
				if (args.length>=2) {
					s = args[1];
					for (int i=2;i<args.length;i++)
						s+=" "+args[i];
				}
				if (!mc.isSingleplayer())
					mc.theWorld.sendQuittingDisconnectingPacket(s);
				else
					mc.displayGuiScreen(new GuiMainMenu());
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"clickaim")) {
				if (args.length==1) {
					Utils.toggleModule("ClickAim");
				} else if (Utils.isDouble(args[1])) {
					try {
						ClickAim.dist=Float.parseFloat(args[1]);
						Utils.addChat("§aLa portée du ClickAim a été mise à "+args[1]+" !");
					} catch (Exception e) {
                        Utils.addChat(err);
                    }
				} else if (args[1].equalsIgnoreCase("multi") || args[1].equalsIgnoreCase("multiaura") || args[1].equalsIgnoreCase("ma")) {
					if (ClickAim.multiAura) {
						Utils.addChat("§cMultiAura désactivé !");
					} else {
						Utils.addChat("§aMultiAura sur le ClickAim activé !");
					}
					ClickAim.multiAura=!ClickAim.multiAura;
				}
				Utils.checkXp(xp);
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"clear")) {
				mc.ingameGUI.getChatGUI().clearChatMessages();
				Utils.checkXp(xp);						
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"t") || args[0].equalsIgnoreCase(var.prefixCmd+"toggle")) {
				if (args.length==1) {
					Utils.addChat(error);
				} else {
					Utils.toggleModule(args[1]);
					mc.ingameGUI.getChatGUI().addToSentMessages(var3);
				}
			}
			
			//TODO: Mcleaks
			if (args[0].equalsIgnoreCase(var.prefixCmd+"mcleaks") || args[0].equalsIgnoreCase(var.prefixCmd+"mcleak") || args[0].equalsIgnoreCase(var.prefixCmd+"mcl")) {
				if (args.length==1) {
					Utils.addChat("§cErreur, essayez "+var.prefixCmd+"mcleaks <token>");
					mc.thePlayer.playSound("mob.villager.haggle", 1.0F, 1.0F);
				} else {
					String token = args[1];
					 ModApi.redeem(token, new Callback<Object>(){
						 
				            @Override
				            public void done(Object o) {
				                if (o instanceof String) {
				                    Utils.addChat("§cErreur: " + o);
				                    return;
				                }
				                if (MCLeaks.savedSession == null) {
				                    MCLeaks.savedSession = Minecraft.getMinecraft().getSession();
				                }
				                RedeemResponse response = (RedeemResponse)o;
				                MCLeaks.refresh(response.getSession(), response.getMcName());
				                Utils.addChat("§aConnecté en tant que "+MCLeaks.getMCName()+"!");
				                if (!Friends.isFriend(MCLeaks.getMCName()))
				    				Friends.friend.add(MCLeaks.getMCName());
				                Utils.lastAccount=0;
				            }
				        });
				}
				Utils.checkXp(xp);
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"login")) {
				if (args.length==1) {
					Utils.addChat(error);
				} else if (args.length==2) {
					if (MCLeaks.isAltActive()) {
						MCLeaks.remove();
					}						
					try {
						mc.session = new Session(args[1], "", "", "mojang");
						Utils.addChat("§aConnecté en tant que "+mc.session.getUsername()+" !");
						Utils.addChat("§aVeuillez deco-reco pour le changement");						
						Utils.lastAccount=0;
					} catch (Exception e) {
						Utils.addChat(error);
					}
				} else if (args.length>=3) {
					if (MCLeaks.isAltActive()) {
						MCLeaks.remove();
					}
					String mdp="";
					if (args.length>3) {
						for (int o=2;o<args.length;o++) {
							if (o!=args.length-1)
								mdp+=args[o] + " ";
							else
								mdp+=args[o];
						}
					} else {
						mdp=args[2];
					}
					LoginThread l = new LoginThread(args[1], mdp);
					l.start();
					Utils.lastAccount=0;
				}
				Utils.checkXp(xp);
			}		
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"log")) {
				if (args.length==1) {
					Utils.addChat(error);
				} else if (args[1].equalsIgnoreCase("add")) {
					try {
						String user = args[2];
						String mdp="";
						if (args.length>3) {
							for (int o=3;o<args.length;o++) {
								if (o!=args.length-1)
									mdp+=args[o] + " ";
								else
									mdp+=args[o];
							}
						} else if (args.length==4) {
							mdp=args[3];
						}
						if (args.length==3 && args[2].contains(":")) {
							String str[] = args[2].split(":");
							user = str[0];
							mdp= str[1];
						}
												
						Utils.saveAccount(user, mdp);
						ArrayList s = Utils.getAllAccount();
						Utils.addChat("§aCompte N°"+s.size()+" ajouté !");
					} catch (Exception e) {Utils.addChat("§cErreur d'ajout de compte");}							
				} else if (args[1].equalsIgnoreCase("list")) {
					try {
						Utils.displayAccount();
					} catch (Exception e) {Utils.addChat("§cErreur, pas réussi à afficher les comptes");}
				} else if (args[1].equalsIgnoreCase("delete") || args[1].equalsIgnoreCase("remove") || args[1].equalsIgnoreCase("del") || args[1].equalsIgnoreCase("rm")) {
					if (args.length==3) {
						try {
						Utils.deleteAccount(Integer.parseInt(args[2]));
						Utils.addChat("§aVous avez supprimé le compte N°"+args[2]);
						} catch (Exception e) {
							Utils.addChat("§cErreur");
						}
					}							
				} else if (args[1].equalsIgnoreCase("clear")) {
					try {
						Utils.clearAccount();
						Utils.addChat("Comptes clear !");
					} catch (Exception e) {Utils.addChat("§cErreur, pas réussi à clear les comptes");}
				} else {
					if (MCLeaks.isAltActive()) {
						MCLeaks.remove();
					}
					try {
						String t = Utils.getAccount(Integer.parseInt(args[1]));
						String [] tab = t.split(" ");
						String mdp="";
						if (tab.length>2) {
							for (int k=1;k<tab.length;k++) {
								if (k!=(tab.length-1)) {
									mdp+=tab[k]+" ";
								} else {
									mdp+=tab[k];
								}
							}
						} else if (tab.length==2) {
							mdp = tab[1];
						}
						LoginThread l = new LoginThread(tab[0], mdp);
						l.start();
						Utils.lastAccount=Integer.parseInt(args[1]);
					} catch (Exception e) {Utils.addChat("§cErreur, ce compte n'est pas disponible");}
				}
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"tppos")) {
				if (Utils.isLock("--tppos")) {
					Utils.addWarn("tppos");
					mc.thePlayer.playSound("mob.villager.no", 1.0F, 1.0F);
				} else if (args.length==1 || args.length==2 || args.length==3) {
					Utils.addChat(error);
				} else {
					Minecraft.getMinecraft().thePlayer.posY+=1.0F;
					try {
						if (Utils.limite && Utils.nbPack>Utils.limit)
                			return;
						double x = (args[1].contains("~") ? mc.thePlayer.posX+(args[1].replace("~", "").equals("") ? 0.0 : Double.parseDouble(args[1].replace("~", ""))) : Double.parseDouble(args[1]));
						double y = (args[2].contains("~") ? mc.thePlayer.posY+(args[2].replace("~", "").equals("") ? 0.0 : Double.parseDouble(args[2].replace("~", ""))) : Double.parseDouble(args[2]));
						double z = (args[3].contains("~") ? mc.thePlayer.posZ+(args[3].replace("~", "").equals("") ? 0.0 : Double.parseDouble(args[3].replace("~", ""))) : Double.parseDouble(args[3]));						
						EntityWitch en = new EntityWitch(mc.theWorld);
						en.setPosition(x, y, z);
                		TpUtils tp = new TpUtils();
                		tp.doTpAller(en, x, y, z, false, 1);
                    	Utils.addChat("§aVous vous êtes tppos !");
					} catch (Exception e) {
                        Utils.addChat(err);
                    }
                	Utils.checkXp(xp);
				}
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"fov")) {
				if (args.length==1) {
					Utils.addChat(error);
				} else {
					try {
						GameSettings.fovSetting = Float.parseFloat(args[1]);
						Utils.addChat("§aVous avez mis votre fov à "+args[1]);
					} catch (Exception e) {
                        Utils.addChat(err);
                    }
				}
				Utils.checkXp(xp);
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"freecam")) {
				if (args.length==1) {
					Utils.toggleModule("Freecam");
				} else {
					try {
						Freecam.speed=Float.parseFloat(args[1]);
						Utils.addChat("§aSpeed du Freecam mis à "+args[1]);
					} catch (Exception e) {
                        Utils.addChat(err);
                    }
				}
				Utils.checkXp(xp);
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"plugins") || args[0].equalsIgnoreCase(var.prefixCmd+"pl")) {
				if (Utils.isLock("--plugins")) {
					Utils.addWarn("Plugins");
					mc.thePlayer.playSound("mob.villager.no", 1.0F, 1.0F);
				} else
					Utils.toggleModule("Plugins");
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"longjump")) {
				if (args.length==1) {
					Utils.toggleModule("longjump");
				} else if (args[1].equalsIgnoreCase("speed")) {
					try {
						Longjump.speed=Float.parseFloat(args[2]);
						Utils.addChat("§aSpeed du longjump mis à "+args[2]);
					} catch (Exception e) {
                        Utils.addChat(err);
                    }
				} else if (Utils.isFloat(args[1])) {
					try {
						Longjump.speed=Float.parseFloat(args[1]);
						Utils.addChat("§aSpeed du longjump mis à "+args[1]);
					} catch (Exception e) {
						
					}
				}
				Utils.checkXp(xp);
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"smoothaim")) {
				if (args.length==1) {
					Utils.toggleModule("SmoothAim");
				} else if (args[1].equalsIgnoreCase("range")) {
					try {
						SmoothAim.range=Double.parseDouble(args[2]);
						Utils.addChat("§aRange du SmoothAim mise à "+args[2]);
					} catch (Exception e) {
                        Utils.addChat(err);
                    }
				} else if (args[1].equalsIgnoreCase("speed")) {
					try {
						SmoothAim.speed=(Double.parseDouble(args[2])<=0 ? 0.5 : Double.parseDouble(args[2]));
						Utils.addChat("§aSpeed du SmoothAim mise à "+args[2]);
					} catch (Exception e) {
                        Utils.addChat(err);
                    }
				} else if (args[1].equalsIgnoreCase("fov")) {
					try {
						SmoothAim.degrees=Double.parseDouble(args[2]);
						Utils.addChat("§aFov du SmoothAim mis à "+args[2]);
					} catch (Exception e) {
                        Utils.addChat(err);
                    }
				}
				Utils.checkXp(xp);
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"autosoup")) {
				if (args.length==1) {
					Utils.toggleModule("autosoup");
				} else if (args[1].equalsIgnoreCase("heal")) {
					try {
						Autosoup.heal=Integer.parseInt(args[2]);
						Utils.addChat("§aHeal mis à "+args[2]);
					} catch (Exception e) {
                        Utils.addChat(err);
                    }
				} else if (args[1].equalsIgnoreCase("drop")) {
					if (Autosoup.drop) {
						Autosoup.drop=false;
						Utils.addChat("§cDrop de l'autosoup désactivé");
					} else {
						Autosoup.drop=true;
						Utils.addChat("§aDrop de l'autosoup activé");
					}
				}
				Utils.checkXp(xp);
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"vclip")) {
				if (args.length==1) {
					Utils.addChat(error);
				} else {
					try {
						Double b = Double.parseDouble(args[1]);
						mc.thePlayer.setPosition(Minecraft.thePlayer.posX, Minecraft.thePlayer.posY+b, Minecraft.thePlayer.posZ);								
					} catch (Exception e) {
                        Utils.addChat(err);
                    }
				}
				Utils.checkXp(xp);
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"reflect")) {
				Reflect r = Reflect.getReflect();
				if (args.length==1) {
					Utils.toggleModule("Reflect");
				} else {
					try {
					r.setPower(Float.parseFloat(args[1]));
					Utils.addChat("§aPuissance du Reflect mise à "+args[1]+" !");
					} catch (Exception e) {
						Utils.addChat(err);
					}
				}
				Utils.checkXp(xp);
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"afk")) {
				if (args.length==1) {
					Utils.toggleModule("Antiafk");
				} else {
					try {
						Integer b = Integer.parseInt(args[1]);
						Antiafk.getInstance().setSec(b);	
						Utils.addChat("Antiafk time mis à "+args[1]+" !");
					} catch (Exception e) {
                        Utils.addChat(err);
                    }
				}
				Utils.checkXp(xp);
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"autonyah") || args[0].equalsIgnoreCase(var.prefixCmd+"an")) {
				if (Utils.isLock("--autonyah")) {
					Utils.addWarn("Autonyah");
					mc.thePlayer.playSound("mob.villager.no", 1.0F, 1.0F);
				} else if (args.length==1) {
					if (Utils.nyah) {
						Utils.nyah=false;
						Utils.addChat("§cAutoNyah désactivé !");
					} else {
						Utils.nyah=true;
						Utils.addChat("§aAutoNyah activé !");
					}					
				} else if (args[1].equalsIgnoreCase("prefix") || args[1].equalsIgnoreCase("pre")){
					if (args.length>=3)
						if (args[2].equalsIgnoreCase("reset")) {
							Utils.nyahh="";
							Utils.addChat("Prefix de l'autonyah reset");
						} else {
							String n="";
							if (args.length>3) {
								for (int k=2;k<args.length;k++) {
									n+=args[k]+" ";
								}
							} else {
								n=args[2];
							}
							try {
								Utils.nyahh=n;
								Utils.addChat("§aPrefix du nyah mis à '"+n+"' !");
							} catch (Exception e) {
								Utils.addChat(err);
							}
						}
				} else if (args[1].equalsIgnoreCase("speed")) {							
					try {
						Utils.nyahSec=Double.parseDouble(args[2]);
						Utils.addChat("§aTemps entre chaque nyah mis à "+args[2]+"s !");
					} catch (Exception e) {
						Utils.addChat(err);
					}
				}
				Utils.checkXp(xp);
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"checkec")) {
				if (args.length==1) {
					String user ="";
					if (MCLeaks.isAltActive()) {
						user = MCLeaks.getMCName();
					} else {
						user = mc.session.getUsername();
					}
					Utils.checkEC(user);
				} else {
					for (int i=1;i<args.length;i++)
					Utils.checkEC(args[i]);
				}
				Utils.checkXp(xp);
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
								
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"lvl")) {
				mc.thePlayer.sendChatMessage("Je suis niveau "+var.niveau+" sur Neko :3 !");
				Utils.checkXp(xp);
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"fire")) {
				if (args.length==1) {
					Utils.toggleModule("Fire");
				} else {
					try {
						Fire.p=Integer.parseInt(args[1]);
					} catch (Exception e) {
                        Utils.addChat(err);
                    }
				}
				Utils.checkXp(xp);
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"Water")) {
				if (args.length==1) {
					Utils.toggleModule("Water");
				} else {
					try {
						Water.p=Integer.parseInt(args[1]);
					} catch (Exception e) {
                        Utils.addChat(err);
                    }
				}
				
				Utils.checkXp(xp);
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"autoclic") || args[0].equalsIgnoreCase(var.prefixCmd+"auto")) {
				if (args.length==1) {
					Utils.toggleModule("AutoClic");
				} else {
					try {		
						if (Integer.parseInt(args[1])<=0)
							args[1] = "1";
						AutoClic.cps=Integer.parseInt(args[1]);
						Utils.addChat("§aAutoClic mis à "+args[1]+" cps");
					} catch (Exception e) {
                        Utils.addChat(err);
                    }
				}
				
				Utils.checkXp(xp);
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"sign")) {
				if (args.length==1) {
					Utils.addChat(Utils.setColor("§cErreur, utilisation correcte: "+var.prefixCmd+"sign <Commande>", "§c"));
				} else {
					try {								
						String command = "";
					    for (int i = 1; i < args.length; i++) {
					      command+=args[i]+" ";
					    }
					    ItemStack item = new ItemStack(Items.sign);
					    
					    NBTTagCompound nbtEntity = new NBTTagCompound();
					    item.setTagInfo("BlockEntityTag", nbtEntity);
					    NBTTagCompound nbtBefehl = new NBTTagCompound();
					    nbtBefehl.setString("Text1", "{text:\"\",clickEvent:{action:run_command,value:\"" + command + "\"}}");
					    nbtBefehl.setString("CustomName", "Neko Sign");
					    
					    item.setTagInfo("BlockEntityTag", nbtBefehl);
					    mc.thePlayer.inventory.addItemStackToInventory(item);
					    for (int i = 0; i < mc.thePlayer.inventoryContainer.getInventory().size(); i++) {
					        mc.playerController.sendSlotPacket((ItemStack)mc.thePlayer.inventoryContainer.getInventory().get(i), i);
					    }
					} catch (Exception e) {
                        Utils.addChat(err);
                    }
				}
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"push") || args[0].equalsIgnoreCase(var.prefixCmd+"pushup")) {
				if (args.length==1) {
					Utils.toggleModule("PushUp");
				} else {
					try {								
						PushUp.getPush().setPacket(Integer.parseInt(args[1]));
						Utils.addChat("§aPacket PushUp mis à "+args[1]+" !");
					} catch (Exception e) {
                        Utils.addChat(err);
                    }
				}
				
				Utils.checkXp(xp);
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"Power")) {
				if (args.length==1) {
					Utils.toggleModule("Power");
				} else {
					try {		
						Power.p=Integer.parseInt(args[1]);
					} catch (Exception e) {
                        Utils.addChat(err);
                    }
				}
				
				Utils.checkXp(xp);
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"values") || args[0].equalsIgnoreCase(var.prefixCmd+"v")) {
				Utils.displayValues();
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"nekochat") || args[0].equalsIgnoreCase(var.prefixCmd+"chat")) {
				if (args.length==1) {
					Utils.toggleModule("NekoChat");
				} else if (args[1].equalsIgnoreCase("color")) {
					try {
						int color = NekoChat.getChat().getColor();
						if (args.length>=6) {
							java.awt.Color c = new java.awt.Color(Integer.parseInt(args[2]), Integer.parseInt(args[3]), Integer.parseInt(args[4]), Integer.parseInt(args[5]));
							color = c.getRGB();
						} else if (args.length==5) {
							java.awt.Color c = new java.awt.Color(Integer.parseInt(args[2]), Integer.parseInt(args[3]), Integer.parseInt(args[4]));
							color = c.getRGB();
						}
						if (args.length==2) {
							Utils.addChat("§cErreur, syntaxe correcte: \n"+Utils.setColor(var.prefixCmd+"chat color <Rouge 0-255> <Vert 0-255> <Bleu 0-255> <Transparence 0-255> Si la transparence n'est pas mise elle est par défaut à 255", "§c"));
						} else {
							NekoChat.getChat().setColor(color);
							Utils.addChat("§aCouleur du NekoChat mis à "+color+" !");
							GuiNewChat chat = new GuiNewChat(mc);
						}
					} catch (Exception e) {
						Utils.addChat(err);
					}
				} else if (args[1].equalsIgnoreCase("height") && args.length>=3) {
					try {
						NekoChat.getChat().setHeight(Float.parseFloat(args[2]));
						Utils.addChat("§aHauteur du NekoChat mis à "+args[2]+" !");
					} catch (Exception e) {
						Utils.addChat(err);
					}
				} else if (args[1].equalsIgnoreCase("width") && args.length>=3) {
					try {
						NekoChat.getChat().setWidth(Float.parseFloat(args[2]));
						Utils.addChat("§aLargeur du NekoChat mis à "+args[2]+" !");
					} catch (Exception e) {
						Utils.addChat(err);
					}
				}
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"rankmanager") || args[0].equalsIgnoreCase(var.prefixCmd+"rm")) {
				if (Utils.isLock("--rankmanager")) {
					Utils.addWarn("RankManager");
				} else if (args.length==1) {
					Utils.addChat("§6=§b-§6=§b-§6=§b-§7 RankManager §b-§6=§b-§6=§b-§6=");
					Utils.addChat("§cRangs débloqués: §7"+Utils.getNbRankUnlock()+"§8/§7"+ModuleManager.rang.size());
					Utils.addChat(var.prefixCmd+"rankmanager list:§7 Liste de vos rangs obtenus");
					Utils.addChat(var.prefixCmd+"rankmanager choose:§7 "+Utils.setColor("Choisis un rang obtenus dans votre liste", "§7"));
					Utils.addChat(var.prefixCmd+"rankmanager dochange:§7 "+Utils.setColor("Active/désactive le changement de rang quand vous en obtenez un, de base §aactif", "§7"));
					Utils.addChat(var.prefixCmd+"rankmanager all:§7 "+Utils.setColor("Affiche tous les rangs débloqués par rareté", "§7"));
				} else if (args[1].equalsIgnoreCase("dochange")) {
					if (Utils.changeRank) {
						Utils.addChat("§cChangement de rang automatique désactivé !");
					} else {
						Utils.addChat("§aChangement de rang automatique activé !");
					}
					Utils.changeRank=!Utils.changeRank;
				}  else if (args[1].equalsIgnoreCase("all")) {
					Utils.addChat("§cRangs débloqués: §8[§7"+Utils.getNbRankUnlock()+"§8/§7"+ModuleManager.rang.size()+"§8]");
					Utils.addChat("§5Neko: §8[§7"+Utils.getTotRankRateUnlock(Rate.Neko)+"§8/§7"+Utils.getTotRankRate(Rate.Neko)+"§8]");
					Utils.addChat("§6Supra: §8[§7"+Utils.getTotRankRateUnlock(Rate.Supra)+"§8/§7"+Utils.getTotRankRate(Rate.Supra)+"§8]");
					Utils.addChat("§2Event: §8[§7"+Utils.getTotRankRateUnlock(Rate.Event)+"§8/§7"+Utils.getTotRankRate(Rate.Event)+"§8]");
					Utils.addChat("§7Ordinaire: §8[§7"+Utils.getTotRankRateUnlock(Rate.Ordinaire)+"§8/§7"+Utils.getTotRankRate(Rate.Ordinaire)+"§8]");
					Utils.addChat("§eRare: §8[§7"+Utils.getTotRankRateUnlock(Rate.Rare)+"§8/§7"+Utils.getTotRankRate(Rate.Rare)+"§8]");
					Utils.addChat("§bUltraRare: §8[§7"+Utils.getTotRankRateUnlock(Rate.UltraRare)+"§8/§7"+Utils.getTotRankRate(Rate.UltraRare)+"§8]");
					Utils.addChat("§dMagical: §8[§7"+Utils.getTotRankRateUnlock(Rate.Magical)+"§8/§7"+Utils.getTotRankRate(Rate.Magical)+"§8]");
					Utils.addChat("§d§oDivin: §8[§7"+Utils.getTotRankRateUnlock(Rate.Divin)+"§8/§7"+Utils.getTotRankRate(Rate.Divin)+"§8]");
					Utils.addChat("§cSatanique: §8[§7"+Utils.getTotRankRateUnlock(Rate.Satanique)+"§8/§7"+Utils.getTotRankRate(Rate.Satanique)+"§8]");
					Utils.addChat("§5§oLégendaire: §8[§7"+Utils.getTotRankRateUnlock(Rate.Légendaire)+"§8/§7"+Utils.getTotRankRate(Rate.Légendaire)+"§8]");
					Utils.addChat("§2Mythique: §8[§7"+Utils.getTotRankRateUnlock(Rate.Mythique)+"§8/§7"+Utils.getTotRankRate(Rate.Mythique)+"§8]");
					Utils.addChat("§4Titan: §8[§7"+Utils.getTotRankRateUnlock(Rate.Titan)+"§8/§7"+Utils.getTotRankRate(Rate.Titan)+"§8]");
					Utils.addChat("§9CrazyLove: §8[§7"+Utils.getTotRankRateUnlock(Rate.CrazyLove)+"§8/§7"+Utils.getTotRankRate(Rate.CrazyLove)+"§8]");
				} else if (args[1].equalsIgnoreCase("list")) {
						int j=0;
						ArrayList<RmRank> list = new ArrayList<>();
						for (Rank r : ModuleManager.rang) {
							if (!r.isLock()) {
								String desc="";
								desc+="\n§6Sélectionné: "+(r.getName().equalsIgnoreCase(var.rang.getName()) ? "§aSélectioné" : "§cNon");
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
								if (!r.getDesc().equalsIgnoreCase("null") && !Utils.isLock("rankmanager desc"))
									desc+="\n§6Description: "+Utils.setColor(r.getDesc(), r.getColor().replaceAll("§n", ""));
								list.add(new RmRank("§b-"+r.getColor()+r.getName()+"§b-", r.getName(), desc));
								j++;
							}
						}
						int page;
						if (args.length==2) {
							page=1;
						} else {
							if (Utils.isInteger(args[2])) {
								page = Integer.parseInt(args[2]);
							} else
								page=1;
						}
						
						int l=0;
						int k=0;
						int i=8;
						Utils.addChat("§cRangs débloqués: §7"+Utils.getNbRankUnlock()+"§8/§7"+ModuleManager.rang.size());
						for (RmRank rm : list) {
							if ((page-1)*i<=l && (page)*i>l) {
								String s[] = rm.getDesc().split(" ");
								String r="";
								for (int o=0;o<s.length;o++) {
									r+=s[o]+" ";
									if (o%15==0) {
										r+="\n"+Utils.getRank(rm.getRankName()).getColor().replaceAll("§n", "");
									}
								}					
								Utils.addChat2(rm.getName(), var.prefixCmd+"rm choose "+rm.getRankName(), r, false, Chat.Click);
								Utils.toChat(" ");
								k++;
							}
							l++;
						}
						if (k==i) {
							Utils.addChat2("§aPage suivante ->", var.prefixCmd+"rankmanager list "+(page+1), "§7Clique pour afficher la pahe suivante !", false, Chat.Click);
						}
						
						if (j==0)
							Utils.addChat("§cAucun rangs disponibles...");
					
				} else if (args[1].equalsIgnoreCase("choose")) {
					if (args.length<3) {
						Utils.addChat(err);
					} else {
						String choose="";
						for (int i=2;i<args.length;i++) {
							if (i!=args.length-1)
								choose+=args[i] + " ";
							else 
								choose+=args[i];
						}
						
						if (!Utils.isRankLock(choose)) {														
							if (!Utils.changeRank(choose)) {
								Utils.addChat("§cCe rang n'existe pas !");
							} else
								Utils.addChat("§aVous avez changé de rang !");
						} else {
							Utils.addChat("§cVous n'avez pas débloqué ce rang !");
						}
					}
				}
				
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}			
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"startevent")) {
				if (Event.mdp.isEmpty()) {
					Utils.addChat("§cErreur, vous n'avez pas le mot de passe...");
				} else
				if (args.length<=2) {
					Utils.addChat("§cErreur, syntaxe correcte: "+var.prefixCmd+"startevent <Nom> <Description>");
				} else {
					ArrayList<String> list = new ArrayList<>();
					String desc = "";
					if (args.length>3) {
						for (int i = 2;i<args.length;i++) {
							desc+=args[i]+" ";
						}
					} else {
						desc = args[2];
					}
					list.add(args[1]);
					list.add(desc);
					new RequestThread("startevent", list).start();
				}
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"stopevent")) {
				if (Event.mdp.isEmpty()) {
					Utils.addChat("§cErreur, vous n'avez pas le mot de passe...");
				} else
				new RequestThread("stopevent", null).start();
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"event")) {
				if (args.length==2) {
					Event.mdp=args[1];
					Utils.addChat("§aMot de passe entré !");
				} else if (Event.mdp.isEmpty()) {
					Utils.addChat("§cErreur, mot de passe manquant...");
				} else if (args.length==1) {
					Utils.addChat(Utils.setColor("Utilisation correcte: "+var.prefixCmd+"event <player:all> <server:all> <ver:all> <Type> <cmd>", "§c"));
					Utils.addChat(Utils.setColor("Type: Unlock, RandUnlock, Rang, RangRate, Cmd, Msg, Xp, Lvl et MeteoreRain", "§c"));
				} else if (args.length>=6) {
					boolean isValid = true;
					try {
						EventType.valueOf(args[4]);
					} catch (Exception e) {
						isValid=false;
						Utils.addChat(Utils.setColor("Utilisation correcte: "+var.prefixCmd+"event <player:all> <server:all> <ver:all> <Type> <cmd>", "§c"));
						Utils.addChat(Utils.setColor("Type: Unlock, RandUnlock, Rang, RangRate, Cmd, Msg, Xp, Lvl et MeteoreRain", "§c"));
					}
					if (isValid) {
						ArrayList<String> list = new ArrayList<>();
						list.add(args[1]);
						list.add(args[2]);
						list.add(args[3]);
						list.add(args[4]);
						String cmd="";
							for (int i=5;i<args.length;i++) {
								if (i+1==args.length)
									cmd+=args[i];
								else
									cmd+=args[i]+" ";
							}
							if (cmd.startsWith("&")) {
								String t[] = cmd.split(" ");
								if (t[0].length()==2)
									cmd = Utils.setColor(cmd, t[0]);
							}
						list.add(cmd.replaceAll("&", "§").replaceAll(" § ", " & ").replaceAll("§§", "&&").replaceAll("\"", "'"));
						list.add(Event.mdp);
						new RequestThread("insertEvent", list).start();
					}
				} else {
					Utils.addChat(Utils.setColor("Utilisation correcte: "+var.prefixCmd+"event <player:all> <server:all> <ver:all> <Type> <cmd>", "§c"));
					Utils.addChat(Utils.setColor("Type: Unlock, RandUnlock, Rang, RangRate, Cmd, Msg, Xp et Lvl", "§c"));
				}
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"detector")) {
				if (args.length==1) {
					HackerDetector.setDetector();
					if (HackerDetector.isOn) {
						Utils.addChat("§aHacker Detector activé !");
					} else {
						Utils.addChat("§cHacker Detector désactivé !");
					}
				} else if (args[1].equalsIgnoreCase("alert")) {
					if (HackerDetector.voirAlert) {
						Utils.addChat("§cAlertes désactivées");
					} else {
						Utils.addChat("§aAlertes activées");
					}
					HackerDetector.voirAlert=!HackerDetector.voirAlert;
				}
				
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}			
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"lvlup")) {
				if (var.animation) {
					Utils.addChat("§cAnimation du lvl désactivé");
				} else {
					Utils.addChat("§aAnimation du lvl activé");
				}
				var.animation=!var.animation;
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"reload")) {
				Utils.reload();
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"trade") || args[0].equalsIgnoreCase(var.prefixCmd+"shop")) {
				if (Utils.isLock("--trade")) {
				 	Utils.addWarn("Trade");
				 	mc.thePlayer.playSound("mob.villager.no", 1.0F, 1.0F);
				 	mc.ingameGUI.getChatGUI().addToSentMessages(var3);
				 	this.mc.displayGuiScreen((GuiScreen)null); 
				 	return;
				}
								
				if (args.length==1) {
					//Affiche la table des prix
					Utils.addChat("========================================");
					Utils.addChat("§lListe des gains:");
					Utils.addChat("Votre solde: "+var.ame+" souls et "+var.lot+" "+(var.lot<2 ? "ticket de lotterie" : "tickets de lotterie"));
					
					Utils.addChat(var.prefixCmd+"trade lot:§7 Tire des lots aléatoires !\n§6Coût:§c 1 ticket de lotterie");
					Utils.addChat(var.prefixCmd+"trade ticket:§7 Reçois un ticket de lotterie !\n§6Coût:§c 150 souls");
					for (Lock lock : ModuleManager.Lock) {
						if (lock.isLock() && lock.getUnit().equalsIgnoreCase("souls") && !lock.getName().startsWith("rankmanager")) {
							Utils.addChat(var.prefixCmd+"trade "+lock.getName().replaceFirst("--ka ", "").replaceFirst("--HUD ", "").replaceFirst("--", "")+
									":§7 Débloque définitivement le §c"+var.prefixCmd+lock.getName().replaceFirst("--", "")+
									"\n§6Coût:§c "+lock.getCout());
						}
					}
										
					if (!Utils.isLock("--rankmanager")) {
						if (Utils.isLock("rankmanager info")) {
							Utils.addChat(var.prefixCmd+"trade info:§7 Débloque définitivement l'option Info dans le §7RankManager\n§6Coût:§c 100 souls");
						} else
							if (Utils.isLock("rankmanager lvl")) {
								Utils.addChat(var.prefixCmd+"trade lvl:§7 Débloque définitivement l'option Lvl dans le §7RankManager\n§6Coût:§c 150 souls");
							} else
								if (Utils.isLock("rankmanager rate")) {
									Utils.addChat(var.prefixCmd+"trade rate:§7 Débloque définitivement l'option Rareté dans le §7RankManager\n§6Coût:§c 200 souls");
								} else
									if (Utils.isLock("rankmanager bonus")) {
										Utils.addChat(var.prefixCmd+"trade bonus:§7 Débloque définitivement l'option Lvl dans le §7RankManager\n§6Coût:§c 250 souls");
									}						
					}				
					if (!Lot.list2.isEmpty()) {
						int prix=0;
						Trade xp2 = new Trade("xp", 0);
						Trade malus = new Trade("malus", 0);
						Trade bonus = new Trade("bonus", 0);
						Trade unlock = new Trade("unlock", 0);
						Trade souls = new Trade("souls", 0);
						// Rareté rang
						Trade Ordinaire = new Trade("Rang", 0);
						Trade Rare = new Trade("Rang", 0);
						Trade UltraRare = new Trade("Rang", 0);
						Trade Magical = new Trade("Rang", 0);
						Trade Divin = new Trade("Rang", 0);
						Trade Satanique = new Trade("Rang", 0);
						Trade Légendaire = new Trade("Rang", 0);
						Trade Mythique = new Trade("Rang", 0);
						Trade Titan = new Trade("Rang", 0);
						Trade Neko = new Trade("Rang", 0);
						for (Bloc b : Lot.list2) {
							String s = b.getGain();
							// rang, unlock, bonus, malus ou xp
							if (s.equalsIgnoreCase("rang")) {
								if (b.getRate().equals(Rate.Satanique)) {
									Satanique.setPrix(500);
									Satanique.setRate(Rate.Satanique);
								} else if (b.getRate().equals(Rate.Divin)) {
									Divin.setPrix(300);
									Divin.setRate(Rate.Divin);
								} else if (b.getRate().equals(Rate.Magical)) {
									Magical.setPrix(200);
									Magical.setRate(Rate.Magical);
								} else if (b.getRate().equals(Rate.UltraRare)) {
									UltraRare.setPrix(100);
									UltraRare.setRate(Rate.UltraRare);
								} else if (b.getRate().equals(Rate.Rare)) {
									Rare.setPrix(50);
									Rare.setRate(Rate.Rare);
								} else if (b.getRate().equals(Rate.Ordinaire)) {
									Ordinaire.setPrix(20);
									Ordinaire.setRate(Rate.Ordinaire);
								} else if (b.getRate().equals(Rate.Titan)) {
									Titan.setPrix(50000);
									Titan.setRate(Rate.Titan);
								} else if (b.getRate().equals(Rate.Mythique)) {
									Mythique.setPrix(30000);
									Mythique.setRate(Rate.Mythique);
								} else if (b.getRate().equals(Rate.Légendaire)) {
									Légendaire.setPrix(20000);
									Légendaire.setRate(Rate.Légendaire);
								}
							} else if (s.equalsIgnoreCase("unlock")) {
								unlock.setPrix(150);
							} else if (s.equalsIgnoreCase("bonus")) {
								bonus.setPrix(50);
							} else if (s.equalsIgnoreCase("malus")) {
								malus.setPrix(20);
							} else if (s.equalsIgnoreCase("xp")) {
								xp2.setPrix(30);
							} 				
							String g = b.getGain();
							switch (g) {
							case "xp":
								xp2.addCount();
								break;
							case "malus":
								malus.addCount();
								break;
							case "bonus":
								bonus.addCount();
								break;
							case "unlock":
								unlock.addCount();
								break;
							case "souls":
								souls.addCount();
								break;
							case "rang":
								Rate r = b.getRate();
								switch (r.name()) {
								case "Ordinaire":
									Ordinaire.addCount();
									break;
								case "Rare":
									Rare.addCount();
									break;
								case "UltraRare":
									UltraRare.addCount();
									break;
								case "Magical":
									Magical.addCount();
									break;
								case "Divin":
									Divin.addCount();
									break;
								case "Satanique":
									Satanique.addCount();
									break;
								case "Légendaire":
									Légendaire.addCount();
									break;
								case "Mythique":
									Mythique.addCount();
									break;
								case "Titan":
									Titan.addCount();
									break;
								case "Neko":
									Neko.addCount();
									break;
								}
								break;
							}
						}
						int i = 1;
						
						ArrayList<Trade> list = new ArrayList<>();
						list.add(xp2);
						list.add(malus);
						list.add(bonus);
						list.add(unlock);
						list.add(souls);
						list.add(Ordinaire);
						list.add(Rare);
						list.add(UltraRare);
						list.add(Magical);
						list.add(Divin);
						list.add(Satanique);
						list.add(Légendaire);
						list.add(Mythique);
						list.add(Titan);
						list.add(Neko);
						
						for (Trade t : list) {
							if (t.isNotNull()) {
								Utils.addChat2("§6"+var.prefixCmd+"trade "+i+": "+Utils.setColor("==> Récupération §c"+t.getGain()+" "+(t.getGain().equalsIgnoreCase("Rang") ? "§c"+t.getRate().name()+" " : "")+"[§a"+t.getCount()+"§7] contre §c"+t.getPrix()+" souls", "§7"), var.prefixCmd+"trade "+i, "§7Cliquez pour obtenir ce lot !", false, Chat.Click);
								t.setNum(i);
								i++;
							}
						}
						Utils.addChat2("§6"+var.prefixCmd+"trade all: §7"+Utils.setColor("Achète dans l'ordre tous les lots perdus jusqu'à avoir tout acheter ou plus assez de souls", "§7"), var.prefixCmd+"trade all", "§7Cliquer pour tout acheter !", false, Chat.Click);
					}
					
					Utils.addChat(var.prefixCmd+"trade lotplus: §7"+Utils.setColor("Augmente les lots qui apparaissent de 1 de plus. Coût: §c"+(100+(Lot.nbLot-3)*75)+" souls", "§7"));
				} else if (args[1].equalsIgnoreCase("lot")) {
					int totLot=1;
					if (args.length==3 && Utils.isInteger(args[2])) {
						totLot=Integer.parseInt(args[2]);
					}
					if (var.lot<1 || var.lot-totLot<0) {
						Utils.addChat("§cDésolé mais vous n'avez pas de ticket de lotterie...");
					} else {
						
						var.lot-=totLot;
						ArrayList<Bloc> list = new ArrayList<>();
						for (int l=0;l<Lot.nbLot*totLot;l++)
							for (int k=0;k<50;k++) {
								BlockPos b = new BlockPos(mc.thePlayer.posX-5+Utils.getRandInt(10), mc.thePlayer.posY, mc.thePlayer.posZ-5+Utils.getRandInt(10));
								if (mc.theWorld.getBlockState(b).getBlock().getMaterial() == Material.air && mc.thePlayer.getDistanceSq(b)>2) {
									String gain;
									Rate r=Rate.Ordinaire;
									double bonus=0;
									int time=0;
									float re=0.88F;
									float g=0.44F;
									float bl=0.88F;
									if (Math.random()<0.1) {
										gain="bonus";
										bonus=(double) Math.round(Math.random()*25);
										time=Utils.getRandInt(700);
									} else if (Math.random()<0.04) {
										
										gain="malus";
										bonus=(double) Math.round(Math.random()*-10*10/10);
										time=Utils.getRandInt(1200);										
									} else if (Math.random()<0.3+var.rang.getLotRang()) {
										if (Utils.getRandInt((int) (5000000-Math.round(1000000*var.rang.getLotRateTitan())))==4) {
											r=Rate.Titan;
										} else if (Math.random()<0.007+0.007*var.rang.getLotRateSatanique()) {
											r=Rate.Satanique;
										} else if (Math.random()<0.01+0.01*var.rang.getLotRateDivin()) {
											r=Rate.Divin;
										} else if (Math.random()<0.05+0.05*var.rang.getLotRateMagical()) {
											r=Rate.Magical;
										} else if (Math.random()<0.1+0.1*var.rang.getLotRateUltraRare()) {
											r=Rate.UltraRare;
										} else if (Math.random()<0.15+0.15*var.rang.getLotRateRare()) {
											r=Rate.Rare;
										} else 
											r=Rate.Ordinaire;
										gain="rang";
										if (Math.random()<0.2) {
											re=0.88F;
											g=0.22F;
											bl=0.33F;
										}
									} else if (Math.random()<0.05+var.rang.getLotUnlock()) {
										gain="unlock";
										if (Math.random()<0.1) {
											re=0.22F;
											g=0.22F;
											bl=0.99F;
										}
									} else if (Math.random()<0.05+var.rang.getGiftAme()) {
										gain="souls";
										if (Math.random()<0.1) {
											re=0.44F;
											g=0.44F;
											bl=0.99F;
										}
									} else {
										gain="xp";
									}
									Bloc bloc = new Bloc(b, gain, re, g, bl);
									if (gain.equalsIgnoreCase("rang")) {
										bloc.setRate(r);
									} else if (gain.equalsIgnoreCase("bonus") || gain.equalsIgnoreCase("malus")) {
										bloc.setBonus(bonus);
										bloc.setTime(time);
									}
									list.add(bloc);
									k=50;
								}
							}
						Lot.init(list);
						Utils.addChat("§5Choisissez un des "+Lot.nbLot*totLot+" lots en allant dessous !");
					}
					
				} else if (args[1].equalsIgnoreCase("noarmor") && Utils.isLock("--ka noarmor")) {
					if (var.ame<50) {
						Utils.addChat("§cDésolé mais vous n'avez pas assez de souls...");
					} else {
						var.ame-=50;
						Utils.unlock("--ka noarmor");
						Utils.addChat("§cKa NoArmor§7 débloqué !");
					}
					
				} else if (args[1].equalsIgnoreCase("ticket")) {
					if (var.ame<150) {
						Utils.addChat("§cDésolé mais vous n'avez pas assez de souls...");
					} else {
						var.ame-=150;
						var.lot++;
						Utils.addChat("§a+ 1 ticket de lotterie !");
					}
					
				} else if (args[1].equalsIgnoreCase("random") && Utils.isLock("--ka random")) {
					if (var.ame<100) {
						Utils.addChat("§cDésolé mais vous n'avez pas assez de souls...");
					} else {
						var.ame-=100;
						Utils.unlock("--ka random");
						Utils.addChat("§cKa Random§7 débloqué !");
					}
					
				} else if (args[1].equalsIgnoreCase("plugins") && Utils.isLock("--plugins")) {
					if (var.ame<50) {
						Utils.addChat("§cDésolé mais vous n'avez pas assez de souls...");
					} else {
						var.ame-=50;
						Utils.unlock("--plugins");
						Utils.addChat("§cPlugins§7 débloqué !");
					}
					
				} else if (args[1].equalsIgnoreCase("sword") && Utils.isLock("--sword")) {
					if (var.ame<75) {
						Utils.addChat("§cDésolé mais vous n'avez pas assez de souls...");
					} else {
						var.ame-=75;
						Utils.unlock("--sword");
						Utils.addChat("§cSword§7 débloqué !");
					}
					
				} else if (args[1].equalsIgnoreCase("unicode") && Utils.isLock("unicode")) {
					if (var.ame<100) {
						Utils.addChat("§cDésolé mais vous n'avez pas assez de souls...");
					} else {
						var.ame-=100;
						Utils.unlock("unicode");
						Utils.addChat("§cUnicode§7 débloqué !");
					}
					
				} else if (args[1].equalsIgnoreCase("fastdura") && Utils.isLock("fastdura")) {
					if (var.ame<150) {
						Utils.addChat("§cDésolé mais vous n'avez pas assez de souls...");
					} else {
						var.ame-=150;
						Utils.unlock("fastdura");
						Utils.addChat("§cFastDura§7 débloqué !");
					}
					
				} else if (args[1].equalsIgnoreCase("select") && Utils.isLock("--hud select")) {
					if (var.ame<75) {
						Utils.addChat("§cDésolé mais vous n'avez pas assez de souls...");
					} else {
						var.ame-=75;
						Utils.unlock("--hud select");
						Utils.addChat("§cHUD Select§7 débloqué !");
					}
					
				} else if (args[1].equalsIgnoreCase("TpBack") && Utils.isLock("TpBack")) {
					if (var.ame<50) {
						Utils.addChat("§cDésolé mais vous n'avez pas assez de souls...");
					} else {
						var.ame-=50;
						Utils.unlock("TpBack");
						Utils.addChat("§cTpBack§7 débloqué !");
					}
					
				} else if (args[1].equalsIgnoreCase("info") && !Utils.isLock("--rankmanager") && Utils.isLock("rankmanager info")) {
					if (var.ame<100) {
						Utils.addChat("§cDésolé mais vous n'avez pas assez de souls...");
					} else {
						var.ame-=100;
						Utils.unlock("rankmanager info");
						Utils.addChat("§cRankManager Info§7 débloqué !");
					}
					
				} else if (args[1].equalsIgnoreCase("lvl") && !Utils.isLock("--rankmanager") && Utils.isLock("rankmanager lvl") && !Utils.isLock("rankmanager info")) {
					if (var.ame<150) {
						Utils.addChat("§cDésolé mais vous n'avez pas assez de souls...");
					} else {
						var.ame-=150;
						Utils.unlock("rankmanager lvl");
						Utils.addChat("§cRankManager Lvl§7 débloqué !");
					}
					
				} else if (args[1].equalsIgnoreCase("rate") && !Utils.isLock("--rankmanager") && !Utils.isLock("rankmanager lvl") && Utils.isLock("rankmanager rate") && !Utils.isLock("rankmanager info")) {
					if (var.ame<200) {
						Utils.addChat("§cDésolé mais vous n'avez pas assez de souls...");
					} else {
						var.ame-=200;
						Utils.unlock("RankManager rate");
						Utils.addChat("§cRankManager Rate§7 débloqué !");
					}
					
				} else if (args[1].equalsIgnoreCase("bonus") && !Utils.isLock("--rankmanager") && !Utils.isLock("rankmanager lvl") && !Utils.isLock("rankmanager rate") && !Utils.isLock("rankmanager info") && Utils.isLock("rankmanager bonus")) {
					if (var.ame<250) {
						Utils.addChat("§cDésolé mais vous n'avez pas assez de souls...");
					} else {
						var.ame-=250;
						Utils.unlock("RankManager bonus");
						Utils.addChat("§cRankManager Bonus§7 débloqué !");
					}
					
				} else if (args[1].equalsIgnoreCase("lotplus")) {
					if (var.ame<(100+(Lot.nbLot-3)*75)) {
						Utils.addChat("§cDésolé mais vous n'avez pas assez de souls...");
					} else {
						var.ame-=(100+(Lot.nbLot-3)*75);
						Lot.nbLot++;
						Utils.addChat("§bAugmentation de 1 du nombre de lot max ! (Total : "+Lot.nbLot+")");
					}
					
				} else if ((Utils.isInteger(args[1]) ? Integer.parseInt(args[1])-1<Lot.list2.size() && Integer.parseInt(args[1])>0 : false) || (args[1].equalsIgnoreCase("all") && !Lot.list2.isEmpty())) {
					if (!Lot.list2.isEmpty()) {
						int prix=0;
						Trade xp2 = new Trade("xp", 0);
						Trade malus = new Trade("malus", 0);
						Trade bonus = new Trade("bonus", 0);
						Trade unlock = new Trade("unlock", 0);
						Trade souls = new Trade("souls", 0);
						// Rareté rang
						Trade Ordinaire = new Trade("Rang", 0);
						Trade Rare = new Trade("Rang", 0);
						Trade UltraRare = new Trade("Rang", 0);
						Trade Magical = new Trade("Rang", 0);
						Trade Divin = new Trade("Rang", 0);
						Trade Satanique = new Trade("Rang", 0);
						Trade Légendaire = new Trade("Rang", 0);
						Trade Mythique = new Trade("Rang", 0);
						Trade Titan = new Trade("Rang", 0);
						Trade Neko = new Trade("Rang", 0);
						for (Bloc b : Lot.list2) {
							String s = b.getGain();
							// rang, unlock, bonus, malus ou xp
							if (s.equalsIgnoreCase("rang")) {
								if (b.getRate().equals(Rate.Satanique)) {
									Satanique.setPrix(500);
									Satanique.setRate(Rate.Satanique);
								} else if (b.getRate().equals(Rate.Divin)) {
									Divin.setPrix(300);
									Divin.setRate(Rate.Divin);
								} else if (b.getRate().equals(Rate.Magical)) {
									Magical.setPrix(200);
									Magical.setRate(Rate.Magical);
								} else if (b.getRate().equals(Rate.UltraRare)) {
									UltraRare.setPrix(100);
									UltraRare.setRate(Rate.UltraRare);
								} else if (b.getRate().equals(Rate.Rare)) {
									Rare.setPrix(50);
									Rare.setRate(Rate.Rare);
								} else if (b.getRate().equals(Rate.Ordinaire)) {
									Ordinaire.setPrix(20);
									Ordinaire.setRate(Rate.Ordinaire);
								} else if (b.getRate().equals(Rate.Titan)) {
									Titan.setPrix(50000);
									Titan.setRate(Rate.Titan);
								} else if (b.getRate().equals(Rate.Mythique)) {
									Mythique.setPrix(30000);
									Mythique.setRate(Rate.Mythique);
								} else if (b.getRate().equals(Rate.Légendaire)) {
									Légendaire.setPrix(20000);
									Légendaire.setRate(Rate.Légendaire);
								}
							} else if (s.equalsIgnoreCase("unlock")) {
								unlock.setPrix(150);
							} else if (s.equalsIgnoreCase("bonus")) {
								bonus.setPrix(50);
								bonus.setTime(b.getTime());
								bonus.setBonus(b.getBonus());
							} else if (s.equalsIgnoreCase("malus")) {
								malus.setPrix(20);
								malus.setTime(b.getTime());
								malus.setBonus(b.getBonus());
							} else if (s.equalsIgnoreCase("xp")) {
								xp2.setPrix(30);
							} 				
							String g = b.getGain();
							switch (g) {
							case "xp":
								xp2.addCount();
								break;
							case "malus":
								malus.addCount();
								break;
							case "bonus":
								bonus.addCount();
								break;
							case "unlock":
								unlock.addCount();
								break;
							case "souls":
								souls.addCount();
								break;
							case "rang":
								Rate r = b.getRate();
								switch (r.name()) {
								case "Ordinaire":
									Ordinaire.addCount();
									break;
								case "Rare":
									Rare.addCount();
									break;
								case "UltraRare":
									UltraRare.addCount();
									break;
								case "Magical":
									Magical.addCount();
									break;
								case "Divin":
									Divin.addCount();
									break;
								case "Satanique":
									Satanique.addCount();
									break;
								case "Légendaire":
									Légendaire.addCount();
									break;
								case "Mythique":
									Mythique.addCount();
									break;
								case "Titan":
									Titan.addCount();
									break;
								case "Neko":
									Neko.addCount();
									break;
								}
								break;
							}
						}
						int i = 1;
						
						ArrayList<Trade> list = new ArrayList<>();
						list.add(xp2);
						list.add(malus);
						list.add(bonus);
						list.add(unlock);
						list.add(souls);
						list.add(Ordinaire);
						list.add(Rare);
						list.add(UltraRare);
						list.add(Magical);
						list.add(Divin);
						list.add(Satanique);
						list.add(Légendaire);
						list.add(Mythique);
						list.add(Titan);
						list.add(Neko);
						
						for (Trade t : list) {
							if (t.isNotNull()) {							
								if (args[1].equalsIgnoreCase("all") || i==Integer.parseInt(args[1])) {
									if (var.ame<t.getPrix()) {
										Utils.addChat("§cDésolé mais vous n'avez pas assez de souls...");
									} else {
										var.ame-=t.getPrix();
										if (t.getGain().equalsIgnoreCase("rang")) {
											String sr = Utils.getRandRank(t.getRate());
											Utils.setRank(sr);
											Utils.addChat("§5Vous débloquez le rang "+Utils.getRankColor(sr)+sr+"§5 !");
											int rang=0;
											for (Rank r : ModuleManager.rang) {
												if (!r.isLock())
													rang++;
											}							
											if (rang==10 && var.rang.getLvl()==1) {
												Utils.addChat("§5§k77§c10 rangs débloqués !§5§k88");
												Utils.addChat("§5§k77§c10 billets de lotterie gagnés !§5§k88");
												var.lot+=10;
											} else if (rang==25 && var.rang.getLvl()==1) {
												Utils.addChat("§5§k77§c25 rangs débloqués !§5§k88");
												Utils.addChat("§5§k77§c15 billets de lotterie gagnés !§5§k88");
												var.lot+=15;
											} else if (rang==50 && var.rang.getLvl()==1) {
												Utils.addChat("§5§k77§c50 rangs débloqués !§5§k88");
												Utils.addChat("§5§k77§c20 billets de lotterie gagnés !§5§k88");
												var.lot+=20;
											} else if (rang==100 && var.rang.getLvl()==1) {
												Utils.addChat("§5§k77§c100 rangs débloqués !§5§k88");
												Utils.addChat("§5§k77§c25 billets de lotterie gagnés !§5§k88");
												var.lot+=25;
											} else if (rang==150 && var.rang.getLvl()==1) {
												Utils.addChat("§5§k77§c150 rangs débloqués !§5§k88");
												Utils.addChat("§5§k77§c30 billets de lotterie gagnés !§5§k88");
												var.lot+=30;
											}
										} else if (t.getGain().equalsIgnoreCase("unlock")) {
											Utils.getRandUnlock();
										} else if (t.getGain().equalsIgnoreCase("bonus")) {
											if (Active.time==0) {
												Active a1 = new Active(t.getBonus(), t.getTime());
												Utils.addChat("§5Vous avez reçu un bonus de §d"+a1.getBonus()+"%\n§aActif pendant encore "+a1.getTime()/60+" minutes");
											} else {
												Utils.addChat("§aVotre temps et bonus s'additionnent à celui déjà actif !");
												Active.bonus+=t.getBonus();
												Active.time+=t.getTime();
											}
										} else if (t.getGain().equalsIgnoreCase("malus")) {
											if (Active.time==0) {
												Active a1 = new Active(t.getBonus(), t.getTime());
												Utils.addChat("§cVous avez reçu un impôt de §d"+a1.getBonus()+"%\n§aActif pendant encore "+a1.getTime()/60+" minutes");
											} else {
												Utils.addChat("§cVotre temps et bonus s'additionnent à celui déjà actif !");
												Active.bonus+=t.getBonus();
												Active.time+=t.getTime();
											}
										} else if (t.getGain().equalsIgnoreCase("xp")) {
											Utils.addChat(Utils.setColor("Xp reçu !", "§b§o"));
											Utils.checkXp(Utils.getRandInt((int) Math.round(700+700*var.rang.getGiftXp())));
										}
										for (Bloc b : Lot.list2) {
											if (b.getGain().equalsIgnoreCase(t.getGain())) {
												Lot.list2.remove(b);
												break;
											}
										}
									}
								}
								i++;	
							}
						}
						
					}
				} else {
					Utils.addChat("§cErreur, l'offre n'existe pas !");
					mc.thePlayer.playSound("mob.villager.haggle", 1.0F, 1.0F);
				}
				Utils.saveRpg();
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}			
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"fps")) {
				if (Utils.isLock("--fps")) {
					Utils.addWarn("fps");
					mc.thePlayer.playSound("mob.villager.no", 1.0F, 1.0F);
				} else if (args.length==1) {
					Utils.addChat(error);
				} else {
					try {
						Minecraft.fps=Integer.parseInt(args[1]);
						Utils.addChat("Ajout de "+args[1]+"fps !");
					} catch (Exception e) {
                        Utils.addChat(err);
                    }
				}
				Utils.checkXp(xp);
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"worldtime") || args[0].equalsIgnoreCase(var.prefixCmd+"time")) {
				if (args.length==1) {
					Utils.toggleModule("WorldTime");					
				} else {
					try {
						WorldTime.time=Long.parseLong(args[1]);
						Utils.addChat("Heure du monde changé à "+args[1]+" !");
					} catch (Exception e) {
						Utils.addChat(err);
					}
				}
				Utils.checkXp(xp);
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}						
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"user")) {
				Utils.addChat("Votre nom d'utilisateur:§c "+System.getProperty("user.name"));
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"reach")) {
				if (args.length==1 || Utils.isLock("reach")) {
					if (Utils.isLock("reach")) {
						Utils.addWarn("Reach");
					} else
						Utils.toggleModule("reach");
				} else if (args[1].equalsIgnoreCase("pvp")) {
					if (!Utils.isLock("--reach pvp")) {
						if (Reach.pvp) {
							Utils.addChat("§cReach pvp désactivée !");
						} else {
							Utils.addChat("§aReach pvp activée !");
						}
						Reach.pvp=!Reach.pvp;
					} else
						Utils.addWarn("reach pvp");
				} else if (args[1].equalsIgnoreCase("tnt")) {
					if (!Utils.isLock("--reach pvp")) {
						if (args.length==2) {
							if (Reach.tnt) {
								Utils.addChat("§cReach tnt désactivée !");
							} else {
								Utils.addChat("§aReach tnt activée !");
							}
							Reach.tnt=!Reach.tnt;
						} else if (args[2].equalsIgnoreCase("list")) {
							Utils.addChat("Modes disponibles: §aNormal§7, §aCage");
						} else {
							try {
								String mode = args[2].toLowerCase();
								mode = mode.replaceFirst(".", (mode.charAt(0)+"").toUpperCase());
								Reach.mode=Form.valueOf(mode);
								Utils.addChat("§aMode de la reach tnt mise sur "+args[2]);
							} catch (Exception e) {
								Utils.addChat("§cErreur, ce mode n'existe pas");
							}
						}
					} else
						Utils.addWarn("reach pvp");
				} else if (args[1].equalsIgnoreCase("aimbot")) {
					if (!Utils.isLock("--reach pvp")) {
						if (Reach.aimbot) {
							Utils.addChat("§cReach aimbot désactivée !");
						} else {
							Utils.addChat("§aReach aimbot activée !");
						}
						Reach.aimbot=!Reach.aimbot;
					} else
						Utils.addWarn("reach pvp");
				} else if (args[1].equalsIgnoreCase("fov")) {
					if (!Utils.isLock("--reach pvp")) {
						if (args.length==2) {
							Utils.addChat(err);
						} else {
							try {
								Reach.fov=Double.parseDouble(args[2]);
								Utils.addChat("§aFov de la reach changé à "+args[2]+" !");
							} catch (Exception e) {
								Utils.addChat(err);
							}
						}
					} else
						Utils.addWarn("reach pvp");
				} else if (args[1].equalsIgnoreCase("classic")) {
					if (!Utils.isLock("--reach pvp")) {
						if (Reach.classic) {
							Utils.addChat("§cReach classic désactivée !");
						} else {
							Utils.addChat("§aReach classic activée !");
						}
						Reach.classic=!Reach.classic;
					} else
						Utils.addWarn("reach pvp");
				} else if (args[1].equalsIgnoreCase("bloc") || args[1].equalsIgnoreCase("block")) {
					if (!Utils.isLock("--reach pvp")) {
						if (Reach.bloc) {
							Utils.addChat("§cReach sur bloc désactivée !");
						} else {
							Utils.addChat("§aReach sur bloc activée !");
						}
						Reach.bloc=!Reach.bloc;
					} else
						Utils.addWarn("reach pvp");
				} else {
					try {
						Reach.dist=Float.parseFloat(args[1]);
						Utils.addChat("§aReach augmentée à "+args[1]+" !");
					} catch (Exception e) {
                        Utils.addChat(err);
                    }
				}
				Utils.checkXp(xp);
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"itemesp") || args[0].equalsIgnoreCase(var.prefixCmd+"item")) {
				if (args.length==1) {					
					Utils.toggleModule("ItemESP");
				} else if (args.length>=3) {
					try {
						if (args[1].equalsIgnoreCase("color") || args[1].equalsIgnoreCase("c")) {
							ItemESP.cR=Float.parseFloat(args[2])/100;
							ItemESP.cG=Float.parseFloat(args[3])/100;
							ItemESP.cB=Float.parseFloat(args[4])/100;
							Utils.addChat("§aCouleurs du ItemESP à "+args[2]+"r, "+args[3]+"g et "+args[4]+"b !");
						} else if (args[1].equalsIgnoreCase("linecolor") || args[1].equalsIgnoreCase("lc")) {
							ItemESP.clR=Float.parseFloat(args[2])/100;
							ItemESP.clG=Float.parseFloat(args[3])/100;
							ItemESP.clB=Float.parseFloat(args[4])/100;
							Utils.addChat("§aCouleur des contours du ItemESP à "+args[2]+"r, "+args[3]+"g et "+args[4]+"b !");							
						} else if (args[1].equalsIgnoreCase("width")) {
							ItemESP.width=Float.parseFloat(args[2]);
							Utils.addChat("§aGrosseur des contours mis à "+ItemESP.width+" !");
						}
					} catch (Exception e) {
						Utils.addChat(err);
					}
				} else {
					Utils.addChat(err);
				}
				Utils.checkXp(xp);
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"spambot") || args[0].equalsIgnoreCase(var.prefixCmd+"bot")) {
				if (args.length==1) {
					Utils.toggleModule("SpamBot");
				} else if (args[1].length()<13) {
					SpamBot.getBot().setPseudo(args[1]);
					Utils.addChat("§aPseudo du SpamBot changé en §c"+args[1]+"§a !");
				} else if (args[1].length()>13) {
					Utils.addChat("§cErreur, Pseudo trop long !");
				}
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"wallhack") || args[0].equalsIgnoreCase(var.prefixCmd+"wh")) {
				if (args.length==1 || Utils.isLock("Wallhack")) {
					if (Utils.isLock("Wallhack")) {
						Utils.addWarn("Wallhack");
					} else
						Utils.toggleModule("Wallhack");
				} else if (args.length>=3) {
					try {
						if (args[1].equalsIgnoreCase("color") || args[1].equalsIgnoreCase("c")) {
							Wallhack.cR=Float.parseFloat(args[2])/100;
							Wallhack.cG=Float.parseFloat(args[3])/100;
							Wallhack.cB=Float.parseFloat(args[4])/100;
							Utils.addChat("§aCouleurs du Wallhack à "+args[2]+"r, "+args[3]+"g et "+args[4]+"b !");
						} else if (args[1].equalsIgnoreCase("linecolor") || args[1].equalsIgnoreCase("lc")) {
							Wallhack.clR=Float.parseFloat(args[2])/100;
							Wallhack.clG=Float.parseFloat(args[3])/100;
							Wallhack.clB=Float.parseFloat(args[4])/100;
							Utils.addChat("§aCouleur des contours du Wallhack à "+args[2]+"r, "+args[3]+"g et "+args[4]+"b !");							
						} else if (args[1].equalsIgnoreCase("width")) {
							Wallhack.width=Float.parseFloat(args[2]);
							Utils.addChat("§aGrosseur des contours mis à "+Wallhack.width+" !");
						}
					} catch (Exception e) {
						Utils.addChat(err);
					}
				} else {
					Utils.addChat(err);
				}
				Utils.checkXp(xp);
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"tracers")) {
				if (args.length==1) {
					Utils.toggleModule("tracers");
				} else if (args[1].equalsIgnoreCase("friend") || args[1].equalsIgnoreCase("fr")) {
					if (Tracers.friend) {
						Utils.addChat(Utils.setColor("Affichage des friends sur le Tracers désactivé !", "§c"));
					} else {
						Utils.addChat(Utils.setColor("Affichage des friends sur le Tracers activé !", "§a"));
					}
					Tracers.friend=!Tracers.friend;
				}else if (args.length>=3) {				
					try {
						if (args[1].equalsIgnoreCase("color") || args[1].equalsIgnoreCase("c")) {
							Tracers.cR=Float.parseFloat(args[2])/100;
							Tracers.cG=Float.parseFloat(args[3])/100;
							Tracers.cB=Float.parseFloat(args[4])/100;
							Utils.addChat("§aCouleurs du Tracers à "+args[2]+"r, "+args[3]+"g et "+args[4]+"b !");
						} else if (args[1].equalsIgnoreCase("width")) {
							Tracers.width=Float.parseFloat(args[2]);
							Utils.addChat("§aEpaisseur de la ligne mis à "+Tracers.width+" !");
						}
					} catch (Exception e) {}
				}
				Utils.checkXp(xp);
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"trigger")) {
				if (args.length==1) {
					Utils.toggleModule("Trigger");
				} else if (args[1].equalsIgnoreCase("cps")) {
					try {
						Trigger.cps=Integer.parseInt(args[2]);
						Utils.addChat("§aTrigger mis à "+args[2]+" cps !");
					} catch (Exception e) {
                        Utils.addChat(err);
                    }
				} else if (args[1].equalsIgnoreCase("random")) {
					if (Trigger.random) {
						Utils.addChat("§cTrigger random désactivé");
					} else {
						Utils.addChat("§aTrigger random activé");
					}
					
					Trigger.random=!Trigger.random;
				} else {
					try {
						Trigger.dist=Float.parseFloat(args[1]);
						Utils.addChat("§aTrigger mis à "+args[1]+" blocs !");
					} catch (Exception e) {
                        Utils.addChat(err);
                    }
				}
				Utils.checkXp(xp);
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"keybind")) {
				Utils.addChat("Commande plus utilisée, voir le "+var.prefixCmd+"list");
				Utils.checkXp(xp);
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"locklvl")) {
				Utils.addChat("========================================");
				for (Lock lock : ModuleManager.Lock) {
					if (lock.isLock())
						Utils.getLock(lock.getName());
				}
				Utils.checkXp(xp);
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"tochat")) {
				String s = ".......";
				for (int i=0;i<var.prefixCmd.length();i++)
					s+=".";
				Utils.toChat(var3.replaceFirst(s, "").replaceAll("&", "§").replaceAll(" § ", " & "));
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"meteore")) {
				if (args.length==1) {
					if (neko.module.modules.render.Render.active) {
						neko.module.modules.render.Render.active=false;
						Utils.addChat(Utils.setColor("Affichage et apparition des météores désactivés", "§c"));
					} else if (!neko.module.modules.render.Render.active) {
						neko.module.modules.render.Render.active=true;
						Utils.addChat(Utils.setColor("Affichage et apparition des météores activés", "§a"));
					}
				} else if (args[1].equalsIgnoreCase("xp")) {
					if (neko.module.modules.render.Render.xp) {
						Utils.addChat(Utils.setColor("Affichage et apparition des météores d'xp désactivés", "§c"));
					} else {
						Utils.addChat(Utils.setColor("Affichage et apparition des météores d'xp activés", "§a"));
					}
					neko.module.modules.render.Render.xp=!neko.module.modules.render.Render.xp;
				}
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"verif")) {
				if (args.length==1) {
					Utils.addChat(error);
				} else {
					if (args[1]!=null)
						Utils.verif=args[1];
					
					//TODO: Verif
					Event.lastEventId=-1;
					Irc.getInstance().setLastId(-1);
					Irc.getInstance().setLastMsg("");
					Utils.vDisplay=Utils.display;
					Utils.display=false;
					Utils.vXp=Utils.xp;
					Utils.xp=false;
					Utils.vZoom=Utils.zoom;
					Utils.zoom=false;
					Utils.vMod=Utils.getVMod();
					Utils.panic();
					Display.setTitle("Minecraft 1.8");
					var.name=Utils.version.equalsIgnoreCase("Neko") ? "1.8/vanilla" : Utils.version+"/vanilla";
					mc.ingameGUI.getChatGUI().clearChatMessages();	
					mc.displayGuiScreen((GuiScreen)null);
					return;			
				}
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"f3")) {
				if (args.length==1) {
					Utils.addChat(error);
				} else {
					if (args.length>=2) {
						String title="";
						for (int k=1;k<args.length;k++) {
							if (k+1==args.length)
								title+=args[k];
							else
								title+=args[k]+" ";
						}
						var.name=title;
						Utils.addChat("§aVersion changée en "+title+" !");
					} else {
						Utils.addChat(err);
					}
					
					mc.ingameGUI.getChatGUI().addToSentMessages(var3);					
				}
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"damage")) {
				Minecraft mc = Minecraft.getMinecraft();
				for (int i = 0; i < 1+(args.length==1 ? 1 : Utils.isInteger(args[1]) ? Integer.parseInt(args[1]) : 1); i++) {
				      mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(mc.thePlayer.posX, mc.thePlayer.posY + 3D, mc.thePlayer.posZ, false));
				      mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ, false));
				}
			    mc.ingameGUI.getChatGUI().addToSentMessages(var3);											
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"pub")) {
				String prefix = "";
				if (args.length>1) {
					for (int i=1;i<args.length;i++)
						prefix=args[i]+" ";
				}
				mc.thePlayer.sendChatMessage(prefix+"Mon Client est Neko et est disponible sur mon site http://nekohc.fr");
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"paint")) {
				if (args.length==1) {
					Utils.toggleModule("Paint");
				} else if (args[1].equalsIgnoreCase("color")) {
					try {
						Paint.cR=Float.parseFloat(args[2])/100;
						Paint.cG=Float.parseFloat(args[3])/100;
						Paint.cB=Float.parseFloat(args[4])/100;
						Utils.addChat("§aCouleur du Paint changée !");
					} catch (Exception e) {
						Utils.addChat(err);
					}
				} else if (args[1].equalsIgnoreCase("alpha")) {
					try {
						Paint.alpha=Float.parseFloat(args[2]);					
						Utils.addChat("§aTransparence du Paint changée !");
					} catch (Exception e) {
						Utils.addChat(err);
					}
				} else if (args[1].equalsIgnoreCase("clear")) {
					Paint.pain.clear();
					Utils.addChat("§aPeinture clear !");
				}
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"title")) {
				if (args.length==1) {
					Utils.addChat(error);
				} else {
					if (args.length>=2) {
						String title="";
						for (int k=1;k<args.length;k++) {
							title+=args[k]+" ";
						}
						Display.setTitle(title);
						Utils.addChat("§aTitle changé en "+title+" !");
					} else {
						Utils.addChat(err);
					}
					
					mc.ingameGUI.getChatGUI().addToSentMessages(var3);					
				}
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"invsee")) {
				if (args.length==1) {
					Utils.toggleModule(err);
				} else {
					try {
						EntityPlayer en = Utils.getPlayer(args[1]);	
						mc.ingameGUI.getChatGUI().addToSentMessages(var3);
						mc.displayGuiScreen((GuiScreen)null);
						mc.displayGuiScreen(new GuiInventory(en));
						return;
					} catch (Exception e) {
						Utils.addChat("§cErreur, ce joueur est introuvable");
					}
					
				}
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);	
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"cheststealer") || args[0].equalsIgnoreCase(var.prefixCmd+"cs")) {
				if (args.length==1) {
					Utils.toggleModule("Cheststealer");
				} else {
					try {
						Cheststealer.waitTime=Integer.parseInt(args[1]);
						Utils.addChat("§aTick du ChestStealer mis à "+args[1]+" !");
					} catch (Exception e) {
						Utils.addChat(err);
					}
					
				}
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);	
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"info") || args[0].equalsIgnoreCase(var.prefixCmd+"i")) {
				if (args.length==1) {
					Utils.addChat("========================================");
					Utils.addChat("Listes des caractéristiques du Client :");
					Utils.addChat("Cheat:§7 Neko");
					Utils.addChat("Version:§7 v" + var.CLIENT_VERSION);
					Utils.addChat("Auteur:§d Tryliom");
					Utils.addChat("Préfix de commande:§7 " + var.prefixCmd);
					Utils.addChat("Rang: "+var.rang.getColor()+ var.rang.getName());
					Utils.addChat("Mode:§7 " + var.mode);
					Utils.addChat("Niveau:§7 " + var.niveau);
					Utils.addChat("Xp actuel:§b " + var.xp + "xp/"+var.xpMax+"xp");
					Utils.addChat("Souls:§6 " + var.ame+" souls");
					Utils.addChat("Bonus:§d " + Math.round(Utils.getTotBonus()*100)+"%");
					Utils.addChat("Temps de jeu:§7 "+Utils.timeInGameHour+"h "+Utils.timeInGameMin+"min "+Utils.timeInGameSec+"s");
					Utils.addChat("OnlyRpg:§7 "+(var.onlyrpg.isActive() ? "§aActivé" : "§cDésactivé"));
					Utils.addChat("OnlyRpg activé depuis "+var.onlyrpg.getTime());
				} else if (args.length==2) {
					if (Utils.getPlayer(args[1])==null) {
						Utils.addChat("§cErreur: Ce joueur est introuvable ou non visible");
					} else {
						EntityPlayer p = Utils.getPlayer(args[1]);
						Utils.addChat("========================================");
						Utils.addChat("Listes des caractéristiques de §c"+args[1]+" :");
						Utils.addChat("Coordonnées: "+Utils.getCoord(p));
						Utils.addChat(Utils.getArmorUsed(p, 3)+" §6:§7 "+Utils.setColor(Utils.getArmorEnchant(p, 3), "§7"));
						Utils.addChat(Utils.getArmorUsed(p, 2)+" §6:§7 "+Utils.setColor(Utils.getArmorEnchant(p, 2), "§7"));
						Utils.addChat(Utils.getArmorUsed(p, 1)+" §6:§7 "+Utils.setColor(Utils.getArmorEnchant(p, 1), "§7"));
						Utils.addChat(Utils.getArmorUsed(p, 0)+" §6:§7 "+Utils.setColor(Utils.getArmorEnchant(p, 0), "§7"));
						Utils.addChat(Utils.getItemUsed(p)+" §6:§7 "+Utils.getItemEnchant(p));
						Utils.addChat("Vie: §a"+Math.round(p.getHealth()));									
					}
				} else if (args[2].equalsIgnoreCase("helmet") || args[2].equalsIgnoreCase("casque")) {
					if (Utils.getPlayer(args[1])==null) {
						Utils.addChat("§cErreur: Ce joueur est introuvable ou non visible");
					} else {
						EntityPlayer p = Utils.getPlayer(args[1]);
						mc.thePlayer.sendChatMessage(Utils.getArmorUsed(p, 3).replace("§c", "") +" "+ Utils.getArmorEnchant(p, 3).replaceAll("§7", ""));
					}
				} else if (args[2].equalsIgnoreCase("chestplate") || args[2].equalsIgnoreCase("plastron")) {
					if (Utils.getPlayer(args[1])==null) {
						Utils.addChat("§cErreur: Ce joueur est introuvable ou non visible");
					} else {
						EntityPlayer p = Utils.getPlayer(args[1]);
						mc.thePlayer.sendChatMessage(Utils.getArmorUsed(p, 2).replace("§c", "") + Utils.getArmorEnchant(p, 2).replaceAll("§7", ""));
					}
				} else if (args[2].equalsIgnoreCase("fut") || args[2].equalsIgnoreCase("leggings") || args[2].equalsIgnoreCase("pantalon")) {
					if (Utils.getPlayer(args[1])==null) {
						Utils.addChat("§cErreur: Ce joueur est introuvable ou non visible");
					} else {
						EntityPlayer p = Utils.getPlayer(args[1]);
						mc.thePlayer.sendChatMessage(Utils.getArmorUsed(p, 1).replace("§c", "") +" "+ Utils.getArmorEnchant(p, 1).replaceAll("§7", ""));
					}
				} else if (args[2].equalsIgnoreCase("boots") || args[2].equalsIgnoreCase("bottes")) {
					if (Utils.getPlayer(args[1])==null) {
						Utils.addChat("§cErreur: Ce joueur est introuvable ou non visible");
					} else {
						EntityPlayer p = Utils.getPlayer(args[1]);
						mc.thePlayer.sendChatMessage(Utils.getArmorUsed(p, 0).replace("§c", "") +" "+ Utils.getArmorEnchant(p, 0).replaceAll("§7", ""));
					}
				} else if (args[2].equalsIgnoreCase("sword") || args[2].equalsIgnoreCase("épée")) {
					if (Utils.getPlayer(args[1])==null) {
						Utils.addChat("§cErreur: Ce joueur est introuvable ou non visible");
					} else {
						EntityPlayer p = Utils.getPlayer(args[1]);
						mc.thePlayer.sendChatMessage(Utils.getItemUsed(p).replace("§c", "") +" "+ Utils.getItemEnchant(p).replaceAll("§7", ""));
					}
				}
				
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}	
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"listserver")) {
				new RequestThread("listserver", null).start();
			}			
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"connect") || args[0].equalsIgnoreCase(var.prefixCmd+"co")) {
				try {
					if (args.length==1) {
						Utils.addChat(err);
					} else {
						Utils.launchConnect(new ServerData("", args[1]));
					}
				} catch (Exception e) {
					Utils.addChat(err);
				}
				Utils.checkXp(xp);
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"unlock")) {
				if (args.length==1) {
					Utils.addChat("§cErreur, vous avez besoin du mot de passe !");
				} else {
					boolean verif=false;
					try {
						ArrayList<String> s = Utils.getUrl("http://neko.alwaysdata.net/mdp.html");
						for (String str : s) {
							String arg[] = str.split(" ");
							if (arg[0].equalsIgnoreCase(System.getProperty("user.name"))) {
								if (args[1].equals(arg[1])) {
									verif=true;
								}
							}
						}					
						if (verif) {
							if (args.length==3) {
								Utils.unlock(args[2]);
							} else {
								String str = "";
								for (int i=2;i<args.length;i++) {
									if (i+1!=args.length)
										str+=args[i]+" ";
									else
										str+=args[i];
								}
								Utils.unlock(str);
							}
						} else {
							Utils.addChat("§cErreur, faux mot de passe !");
						} 
					
					} catch (Exception e) {
						System.out.println("§cErreur");
					}														
				}
			}

			if (args[0].equalsIgnoreCase(var.prefixCmd+"version")) {
				if (args.length==1) {
					Utils.addChat(err);
				} else {
					Utils.version=args[1];
				}
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"enchant")) {
				if (!mc.playerController.isInCreativeMode()) {
					Utils.addChat("§cVous devez être en créatif !");
					mc.ingameGUI.getChatGUI().addToSentMessages(var3);
					this.mc.displayGuiScreen((GuiScreen)null);
					return;
				}
				if (mc.thePlayer.getCurrentEquippedItem()==null) {
					Utils.addChat("§cVous devez avoir un objet dans la main !");
					mc.ingameGUI.getChatGUI().addToSentMessages(var3);
					this.mc.displayGuiScreen((GuiScreen)null);
					return;
				}
				ItemStack item = mc.thePlayer.getCurrentEquippedItem();
				if (args.length==1) {
					// Mettre tous les enchant possible sur l'item au max
					for (Enchantment ench : Enchantment.enchantmentsList) {
						if (ench!=null) {
							item.addEnchantment(ench, 127);
							mc.getNetHandler().addToSendQueue(new C10PacketCreativeInventoryAction(mc.thePlayer.inventory.currentItem, item));
						}
					}
				} else if (args.length==2 || (args.length>=3 && !Utils.isInteger(args[2]))) {
					if (args[1].equalsIgnoreCase("list")) {
						Utils.addChat("=============§aEnchantement List§6=============");
						for (Enchantment ench : Enchantment.enchantmentsList) {
							if (ench!=null) {
								Utils.addChat2("§a"+ench.getTranslatedName(1).replaceFirst(" I", ""), var.prefixCmd+"enchant "+ench.getTranslatedName(1).replaceFirst(" I", ""), "§7"+var.prefixCmd+"enchant "+ench.getTranslatedName(1).replaceFirst(" I", ""), false, Chat.Summon);
							}
						}
					} else {
						int i=0;
						String e = args[1];
						if (args.length>=3)
							for (int j=2;j<args.length;j++) {
								e+=" "+args[j];
							}
						for (Enchantment ench : Enchantment.enchantmentsList) {
							if (ench!=null) {
								if (e.equalsIgnoreCase(ench.getTranslatedName(1).replaceFirst(" I", ""))) {
									i++;
									item.addEnchantment(ench, 127);
									mc.getNetHandler().addToSendQueue(new C10PacketCreativeInventoryAction(mc.thePlayer.inventory.currentItem, item));
								}							
							}
						}
						if (i==0) {
							Utils.addChat(Utils.setColor("§cErreur: Enchantement incorrect, essayez "+var.prefixCmd+"enchant list pour afficher la liste complète", "§c"));
						} else {
							Utils.addChat("§aEnchantement ajouté !");
						}
					}
				} else if (args.length>=3) {
					try {
						int lvl=0;
						if (Utils.isInteger(args[args.length-1])) {
							lvl = Integer.parseInt(args[args.length-1]);
							if (lvl>127)
								lvl=127;
							else if (lvl<-127)
								lvl=-127;
						} else
							lvl=127;
						int i=0;
						String name="";
						if (args.length==3)
							name = args[1];
						else {
							for (int is=1;is<args.length-1;is++) {
								if (is+2!=args.length) 
									name+=args[is]+" ";
								else
									name+=args[is];
							}
						}
						for (Enchantment ench : Enchantment.enchantmentsList) {
							if (ench!=null) {
								if (name.equalsIgnoreCase(ench.getTranslatedName(1).replaceFirst(" I", ""))) {
									i++;
									item.addEnchantment(ench, lvl);
									mc.getNetHandler().addToSendQueue(new C10PacketCreativeInventoryAction(mc.thePlayer.inventory.currentItem, item));
								}							
							}
						}
						if (i==0) {
							Utils.addChat(Utils.setColor("§cErreur: Enchantement incorrect, essayez "+var.prefixCmd+"enchant list pour afficher la liste complète", "§c"));
						} else {
							Utils.addChat("§aEnchantement ajouté !");
						}
					} catch (Exception e) {}
				}
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"xp")) {
				if (args.length==1) {
					Utils.addChat("§cErreur, vous avez besoin du mot de passe !");
				} else {
					boolean verif=false;
					try {
						
						ArrayList<String> s = Utils.getUrl("http://neko.alwaysdata.net/mdp.html");
						for (String str : s) {
							String arg[] = str.split(" ");
							if (arg[0].equalsIgnoreCase(System.getProperty("user.name"))) {
								if (args[1].equals(arg[1])) {
									verif=true;
								}
							}
						}
						if (verif) {
							if (Utils.isInteger(args[2])) {
								Utils.checkXp(Integer.parseInt(args[2]));
								Utils.setRank("BetaTesteur");
							} else {
								Utils.addChat(err);
							}
						} else {
							Utils.addChat("§cErreur, faux mot de passe !");
						} 						
					} catch (Exception e) {
						System.out.println("§cErreur");
					}														
				}
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
								
			if (args[0].equalsIgnoreCase(var.prefixCmd+"ka")) {
				if (args.length==1) {
					Utils.toggleModule("KillAura");
				} else if (args[1].equalsIgnoreCase("lockview") || args[1].equalsIgnoreCase("lock")) {
					if (KillAura.lockView) {
						Utils.addChat("§cLockview du Kill Aura désactivée !");
					} else {
						Utils.addChat("§aLockview du Kill Aura activée !");
					}
					KillAura.lockView=!KillAura.lockView;
				} else if (args[1].equalsIgnoreCase("mode")) {
					if (KillAura.mode.equalsIgnoreCase("multi")) {
						KillAura.mode="single";
						Utils.addChat("§aKill Aura mis en mode Single !");
					} else if (KillAura.mode.equalsIgnoreCase("single")) {
						KillAura.mode="multi";
						Utils.addChat("§aKill Aura mis en mode Multi !");
					}
					
				} else if (args[1].equalsIgnoreCase("fov")) {
					try {
						KillAura.fov = Double.parseDouble(args[2]);     
						Utils.addChat("§aFov du Kill Aura mis à "+args[2]+" !");
					} catch (Exception e) {
                        Utils.addChat(err);
                    }
					
				} else if (args[1].equalsIgnoreCase("cps")) {
					try {
						KillAura.cps = Integer.parseInt(args[2]);     
						Utils.addChat("§aCps du Kill Aura mis à "+args[2]+" !");
					} catch (Exception e) {
                        Utils.addChat(err);
                    }
					
				} else if (args[1].equalsIgnoreCase("range")) {
					try {
						KillAura.range = Double.parseDouble(args[2]);  
						Utils.addChat("§aRange du Kill Aura mis à "+args[2]+" !");
					} catch (Exception e) {
                        Utils.addChat(err);
                    }
				} else if (args[1].equalsIgnoreCase("live")) {
					try {
						KillAura.live=Integer.parseInt(args[2]);
						Utils.addChat("§aLive du Kill Aura mis à "+args[2]+" !");
					} catch (Exception e) {
                        Utils.addChat(err);
                    }
				} else if (args[1].equalsIgnoreCase("invi")) {
					if (KillAura.invi) {
						KillAura.invi=false;
						Utils.addChat("Le Kill Aura tape les invisibles !");
					} else {
						KillAura.invi=true;
						Utils.addChat("Le Kill Aura ne tape plus les invisibles !");
					}
				} else if (args[1].equalsIgnoreCase("speed")) {
					try {
						KillAura.speed=Double.parseDouble(args[2]);
						Utils.addChat("§aSpeed du Kill Aura mis à "+args[2]+" !");
					} catch (Exception e) {
                        Utils.addChat(err);
                    }
				} else if (args[1].equalsIgnoreCase("premium")) {
					if (KillAura.premium) {
						Utils.addChat("Le Kill Aura tape les crackés !");
					} else {
						Utils.addChat("Le Kill Aura ne tape plus les crackés !");
					}
					KillAura.premium=!KillAura.premium;
				} else if (args[1].equalsIgnoreCase("verif")) {
					if (KillAura.verif) {
						KillAura.verif=false;
						Utils.addChat("§cDouble vérification désactivée");
					} else {
						KillAura.verif=true;
						Utils.addChat("§aDouble vérification activée");
					}
				} else if (args[1].equalsIgnoreCase("random")) {
					if (Utils.isLock("--ka random")) {
						Utils.addWarn("Ka Random");
						mc.thePlayer.playSound("mob.villager.no", 1.0F, 1.0F);
					} else if (KillAura.random) {
						KillAura.random=false;
						Utils.addChat("Random Kill Aura désactivé");
					} else {
						KillAura.random=true;
						Utils.addChat("Random Kill Aura activé");
					}
					
				} else if (args[1].equalsIgnoreCase("noarmor")) {
					if (Utils.isLock("--ka noarmor")) {
						Utils.addWarn("Ka NoArmor");
						mc.thePlayer.playSound("mob.villager.no", 1.0F, 1.0F);
					} else
					if (KillAura.noarmor) {
						KillAura.noarmor=false;
						Utils.addChat("Le Kill Aura tape les sans armures !");
					} else {
						KillAura.noarmor=true;
						Utils.addChat("Le Kill Aura ne tape plus les sans armures !");
					}
				} else if (args[1].equalsIgnoreCase("onground")) {
					if (Utils.isLock("--ka onground")) {
						Utils.addWarn("Ka OnGround");
						mc.thePlayer.playSound("mob.villager.no", 1.0F, 1.0F);
					} else
					if (KillAura.onground) {
						KillAura.onground=false;
						Utils.addChat("§cLe Kill Aura tape les joueurs en l'air !");
					} else {
						KillAura.onground=true;
						Utils.addChat("§aLe Kill Aura ne tape plus les joueurs en l'air !");
					}
				} else if (args[1].equalsIgnoreCase("nobot")) {
					if (KillAura.nobot) {
						Utils.addChat("§cKill Aura NoBot désactivé");
					} else {
						Utils.addChat("§aKill Aura NoBot activé");
					}
					KillAura.nobot=!KillAura.nobot;
				} else {
					Utils.addChat(error);
					mc.thePlayer.playSound("mob.villager.haggle", 1.0F, 1.0F);
				}
				Utils.checkXp(xp);
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"hud")) {
				if (args.length==1) {
					Utils.toggleModule("HUD");
				} else if (args[1].equalsIgnoreCase("fps")) {
					if (HUD.fps) {
						HUD.fps=false;
						Utils.addChat("§cHUD: Fps cachés");
					} else {
						HUD.fps=true;
						Utils.addChat("§aHUD: Fps affichés");
					}
					
				} else if (args[1].equalsIgnoreCase("stuff")) {
					if (HUD.stuff) {
						HUD.stuff=false;
						Utils.addChat("§cHUD: Stuff cachés");
					} else {
						HUD.stuff=true;
						Utils.addChat("§aHUD: Stuff affichés");
					}
					
				} else if (args[1].equalsIgnoreCase("color") || args[1].equalsIgnoreCase("c")) {
					try {
						HUD.cR=Float.parseFloat(args[2])/100;
						HUD.cG=Float.parseFloat(args[3])/100;
						HUD.cB=Float.parseFloat(args[4])/100;
						Utils.addChat("Couleurs du Select à "+args[2]+"r, "+args[3]+"g et "+args[4]+"b !");
					} catch (Exception e) {
						Utils.addChat(Utils.setColor("§cErreur: Faire "+var.prefixCmd+"help hud pour la syntax correcte", "§c"));
					}
				} else if (args[1].equalsIgnoreCase("select")) {
					if (Utils.isLock("--hud select")) {
						Utils.addWarn("Select");
						mc.thePlayer.playSound("mob.villager.no", 1.0F, 1.0F);
					} else
					if (HUD.select) {
						HUD.select=false;
						Utils.addChat("§cHUD: Selections personnalisée de blocs désactivés");
					} else {
						HUD.select=true;
						Utils.addChat("§aHUD: Selections personnalisée de blocs activés");
					}
					
				} else if (args[1].equalsIgnoreCase("packet")) {
					if (HUD.packet) {
						HUD.packet=false;
						Utils.addChat("§cHUD: Packets cachés");
					} else {
						HUD.packet=true;
						Utils.addChat("§aHUD: Packets affichés");
					}
					
				} else if (args[1].equalsIgnoreCase("coord") || args[1].equalsIgnoreCase("pos")) {
					if (HUD.coord) {
						HUD.coord=false;
						Utils.addChat("§cHUD: Coordonnées cachées");
					} else {
						HUD.coord=true;
						Utils.addChat("§aHUD: Coordonnées affichées");
					}
					
				} else if (args[1].equalsIgnoreCase("xp")) {
					if (HUD.fall) {
						HUD.fall=false;
						Utils.addChat("§cHUD: Expérience cachée");
					} else {
						HUD.fall=true;
						Utils.addChat("§aHUD: Expérience affichée");
					}
					
				} else if (args[1].equalsIgnoreCase("time")) {
					if (HUD.time) {
						HUD.time=false;
						Utils.addChat("§cHUD: Temps de jeu caché");
					} else {
						HUD.time=true;
						Utils.addChat("§aHUD: Temps de jeu affiché");
					}
					
				} else if (args[1].equalsIgnoreCase("item")) {
					if (HUD.item) {
						HUD.item=false;
						Utils.addChat("§cHUD: Item caché");
					} else {
						HUD.item=true;
						Utils.addChat("§aHUD: Item affiché");
					}
					
				}
				Utils.checkXp(xp);
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"panic")) {
				Utils.panic();
				Utils.checkXp(xp);
				Utils.addChat("§2Tous les cheats ont bien été désactivés !");
				Utils.addChat("Conseil: §cSi vous vous faites vérif pensez à taper §c"+var.prefixCmd+"§cclear pour effacer tout preuves ;3");
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"fasteat")) {
				if (args.length==1) {
					Utils.toggleModule("Fasteat");
				} else {
					try {
						Fasteat.getFast().setPacket(Integer.parseInt(args[1]));
						Utils.addChat("§aVitesse du fasteat modifié à "+args[1]+" !");
					} catch (Exception e) {
                        Utils.addChat(err);
                    }
				}
				Utils.checkXp(xp);
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"punkeel")) {
				if (Utils.isLock("Punkeel")) {
					Utils.addWarn("Punkeel");
				} else if (args.length>=2) {
					// Attack et delay
					if (args[1].equalsIgnoreCase("attack")) {
						if (PunKeel.attack) {
							Utils.addChat("§cMode Attack du Punkeel désactivé !");
						} else {
							Utils.addChat("§aMode Attack du Punkeel activé !");
						}
						PunKeel.attack=!PunKeel.attack;
					} else if (args[1].equalsIgnoreCase("delay") && args.length>=3 && Utils.isDouble(args[2])) {
						PunKeel.delay = Double.parseDouble(args[2]);
						Utils.addChat("§aDelay du Punkeel mis à "+args[2]+"sec !");
					}
					
				}
				Utils.checkXp(xp);
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"dolphin")) {
				if (args.length==1) {
					Utils.toggleModule("Dolphin");
				} else {
					try {
						Dolphin.dolph=Double.parseDouble(args[1]);
						Utils.addChat("§aVitesse du dolphin modifié à "+args[1]+" !");
					} catch (Exception e) {
                        Utils.addChat(err);
                    }
				}
				Utils.checkXp(xp);
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"regen")) {
				if (args.length==1) {
					Utils.toggleModule("Regen");
				} else if (args[1].equalsIgnoreCase("bypass")){
					if (Regen.bypass) {
						Regen.bypass=!Regen.bypass;
						Utils.addChat("§cRegen bypass désactivé !");
					} else {
						Regen.bypass=!Regen.bypass;
						Utils.addChat("§aRegen bypass activé !");
					}
					
				} else {
					try {
						if (Integer.parseInt(args[1])<=0) {
							Utils.addChat("§cErreur, vous ne pouvez pas mettre moins de 1 paquet");
						} else {
							Regen.regen=Integer.parseInt(args[1]);
							Utils.addChat("§aLes paquets du regen ont été mis à "+args[1]+" !");
						}
					} catch (Exception e) {
                        Utils.addChat(err);
                    }
				}
				Utils.checkXp(xp);
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"cmd")) {
				if (args.length==1) {
					Utils.addChat(Utils.setColor("Utilisation correcte: cmd <nom> <touche> <commande>\nLe '&&' permet d'ajouter plus d'une commande", "§a"));
					Utils.addChat(Utils.setColor("Pour changer les touches, faire "+var.prefixCmd+"bind <Nom donné> <Nouvelle touche>", "§a"));
				} else if (args[1].equalsIgnoreCase("remove") || args[1].equalsIgnoreCase("rm") || args[1].equalsIgnoreCase("delete") || args[1].equalsIgnoreCase("del")) {
					try {
						Vector<Module> l = new Vector<Module>();
						for (Module m : ModuleManager.ActiveModule) {
							if (m.getName().equalsIgnoreCase(args[2]) && m.isCmd()) {
								l.add(m);
							}
						}
						for (Module m : l) {
							ModuleManager.ActiveModule.remove(m);
						}
						if (l.size()>0)
							Utils.addChat(Utils.setColor(l.get(0).getName()+" a été supprimé", "§c"));
						else
							Utils.addChat(Utils.setColor("Aucuns cmd à supprimer trouvée", "§c"));
						mc.ingameGUI.getChatGUI().addToSentMessages(var3);
						mc.displayGuiScreen((GuiScreen)null);
						return;
					} catch (Exception e) {
						Utils.addChat(err);
					}
				} else if (args.length>=4) {
					try {
						for (Module m : ModuleManager.ActiveModule) {
							if (m.getName().equalsIgnoreCase(args[1])) {
								Utils.addChat("§cCe nom est déjà utilisé !");
								return;
							}
						}
						Module m = new Module(args[1], Keyboard.getKeyIndex(args[2].toUpperCase()), Category.HIDE);
						String l ="";
						for (int i=3;i<args.length;i++) {
							l+=args[i]+" ";
						}
						String s[] = l.split("&&");
						for (int i=0;i<s.length;i++) {
							if (s[i].startsWith(" "))
								s[i] = s[i].replaceFirst(".", "");
							m.addCmd(s[i]);
						}
						ModuleManager.ActiveModule.add(m);
						Utils.addChat(Utils.setColor("Commande assignée à la touche "+args[2]+" !", "§a"));
					} catch (Exception e) {
						Utils.addChat(err);
					}
				}
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"step")) {
				if (args[1].equalsIgnoreCase("bypass")) {
					Step s = Step.getStep();
					if (Utils.isLock("--step bypass")) {
						Utils.addWarn("Step bypass");
						mc.thePlayer.playSound("mob.villager.no", 1.0F, 1.0F);
					} else {
						if (s.isBypass()) {
							Utils.addChat("§cStep bypass désactivé");
						} else {
							Utils.addChat("§aStep bypass activé");
						}
						s.setBypass(!s.isBypass());
					}					
				} else {
					try {
						Step.getStep().setStep(Double.parseDouble(args[1]));
						Utils.addChat("§aLa hauteur du step a été mis à "+args[1]+" !");
					} catch (Exception e) {
                        Utils.addChat(err);
                    }
				}
				Utils.checkXp(xp);
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}			
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"speed")) {
				Speed709 s = Speed709.getSpeed();
				if (args.length==1) {
					Utils.toggleModule("Speed");
				} else if (args[1].equalsIgnoreCase("mode")) {
					try {
						s.setMode(SpeedEnum.valueOf(args[2]));
						Utils.addChat("§aMode du Speed mis à "+args[2]+" !");
					} catch (Exception e) {
                        Utils.addChat(err);
                    }				
				} else {
					try {						
						s.setSpe(Double.parseDouble(args[1]));
						Utils.addChat("§aLa vitesse du speed a été mis à "+args[1]+" !");
					} catch (Exception e) {
                        Utils.addChat(err);
                    }
				}
				Utils.checkXp(xp);
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"noclip")) {
				if (args.length==1) {
					Utils.toggleModule("NoClip");
				} else {
					try {
						NoClip.speed=(float) Double.parseDouble(args[1]);
						Utils.addChat("§aLa vitesse du noclip a été mis à "+args[1]+" !");
					} catch (Exception e) {
                        Utils.addChat(err);
                    }
				}
				Utils.checkXp(xp);
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"firetrail")) {
				FireTrail ft = FireTrail.getFireTrail();
				if (args.length==1) {
					Utils.toggleModule("FireTrail");
				} else if (args[1].equalsIgnoreCase("large")) {
					if (ft.isLarge()) {
						Utils.addChat("§aLa trainée du FireTrail devient plus fine");
					} else {
						Utils.addChat("§aLa trainée du FireTrail devient plus large");
					}
					ft.setLarge(!ft.isLarge());
				}
				Utils.checkXp(xp);
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"phase")) {
				Phase p = Phase.getPhase();
				if (args.length==1) {
					Utils.toggleModule("Phase");
				} else if (args[1].equalsIgnoreCase("vphase")) {
					if (p.isVphase()) {
						Utils.addChat("§cVphase désactivé");
					} else {
						Utils.addChat("§aVphase activé");
					}
					p.setVphase(!p.isVphase());
				}
				Utils.checkXp(xp);
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"hclip")) {
				try {
					if (args.length==1 || !Utils.isDouble(args[1])) {
						Utils.addChat(err);
					} else {
						Entity var2 = this.mc.func_175606_aa();
				        EnumFacing face = var2.func_174811_aO();				        
				        switch (face.getIndex()) {
				        case 4:
				        	mc.thePlayer.setPosition(mc.thePlayer.posX-Double.parseDouble(args[1]), mc.thePlayer.posY, mc.thePlayer.posZ);
				        	mc.getNetHandler().addToSendQueue(new C04PacketPlayerPosition(mc.thePlayer.posX-Double.parseDouble(args[1]), mc.thePlayer.posY, mc.thePlayer.posZ, false)); 
				        	break;
				        case 3:
				        	mc.thePlayer.setPosition(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ+Double.parseDouble(args[1]));
				        	mc.getNetHandler().addToSendQueue(new C04PacketPlayerPosition(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ+Double.parseDouble(args[1]), false));
				        	break;
				        case 2:
				        	mc.thePlayer.setPosition(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ-Double.parseDouble(args[1]));
				        	mc.getNetHandler().addToSendQueue(new C04PacketPlayerPosition(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ-Double.parseDouble(args[1]), false));
				        	break;
				        case 5:
				        	mc.thePlayer.setPosition(mc.thePlayer.posX+Double.parseDouble(args[1]), mc.thePlayer.posY, mc.thePlayer.posZ);
				        	mc.getNetHandler().addToSendQueue(new C04PacketPlayerPosition(mc.thePlayer.posX+Double.parseDouble(args[1]), mc.thePlayer.posY, mc.thePlayer.posZ, false));
				        	break;
				        }
					}
				} catch (Exception e) {
					Utils.addChat(err);
				}
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"tpback") || args[0].equalsIgnoreCase(var.prefixCmd+"tpb")) {
				TpBack tp = TpBack.getInstance();
				if (args.length==1) {
					Utils.toggleModule("TpBack");
				} else if (args[1].equalsIgnoreCase("classic")) {
					if (!tp.isClassic()) {
						Utils.addChat("§aVous retourner au mode classic !");
					} else {
						Utils.addChat("§cMode classic désactivé");
					}
					tp.setClassic(!tp.isClassic());
				} else if (args[1].equalsIgnoreCase("top")) {
					if (!tp.isTop()) {
						Utils.addChat("§aVous passez en mode top");
					} else {
						Utils.addChat("§cMode top désactivé");
					}
					tp.setTop(!tp.isTop());
				} else if (args[1].equalsIgnoreCase("set") || args[1].equalsIgnoreCase("setspawn") || args[1].equalsIgnoreCase("spawn")) {
					if (args.length==2) {
						tp.setSpawn(new BlockPos(mc.thePlayer.posX, mc.thePlayer.posY-1, mc.thePlayer.posZ));
						Utils.addChat("§aSpawn posé !");
					} else if (args.length==5) {
						try {
							double x = (args[2].contains("~") ? mc.thePlayer.posX+(args[2].replace("~", "").equals("") ? 0.0 : Double.parseDouble(args[2].replace("~", ""))) : Double.parseDouble(args[2]));
							double y = (args[3].contains("~") ? mc.thePlayer.posY+(args[3].replace("~", "").equals("") ? 0.0 : Double.parseDouble(args[3].replace("~", ""))) : Double.parseDouble(args[3]));
							double z = (args[4].contains("~") ? mc.thePlayer.posZ+(args[4].replace("~", "").equals("") ? 0.0 : Double.parseDouble(args[4].replace("~", ""))) : Double.parseDouble(args[4]));
							tp.setSpawn(new BlockPos(x, y, z));
							Utils.addChat("§aSpawn posé en X:"+x+" Y:"+y+" Z:"+z+" !");
						} catch (Exception e) {
							Utils.addChat(err);
						}
					}
				} else if (Utils.isInteger(args[1])) {					
					int vie = Integer.parseInt(args[1]);
					tp.setVie(vie);
					Utils.addChat("§aSeuil de vie mis à "+vie+" !");
				}
				Utils.checkXp(xp);
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"vanillatp") || args[0].equalsIgnoreCase(var.prefixCmd+"vtp")) {
				if (args.length==1) {
					Utils.toggleModule("VanillaTp");
				} else if (args[1].equalsIgnoreCase("air")) {
					if (!VanillaTp.air) {
						Utils.addChat("§aVous pouvez vous tp dans l'air !");
					} else {
						Utils.addChat("§cVous ne pouvez plus vous tp dans l'air !");
					}
					VanillaTp.air=!VanillaTp.air;
				} else if (args[1].equalsIgnoreCase("classic")) {
					if (!VanillaTp.classic) {
						Utils.addChat("§aVous retourner au mode classic !");
					} else {
						Utils.addChat("§cMode classic désactivé");
					}
					VanillaTp.classic=!VanillaTp.classic;
				} else if (args[1].equalsIgnoreCase("top")) {
					if (!VanillaTp.top) {
						Utils.addChat("§aVous passez en mode top");
					} else {
						Utils.addChat("§cMode top désactivé");
					}
					VanillaTp.top=!VanillaTp.top;
				}
				Utils.checkXp(xp);
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"automlg") || args[0].equalsIgnoreCase(var.prefixCmd+"mlg")) {
				if (args.length==1) {
					Utils.toggleModule("AutoMLG");
				} else {
					try {
						AutoMLG mlg = AutoMLG.getMLG();
						mlg.setFall(Double.parseDouble(args[1]));
						Utils.addChat(Utils.setColor("Distance de chute minimale de l'AutoMLG mise à "+args[1]+" !", "§a"));
					} catch (Exception e) {
                        Utils.addChat(err);
                    }
				}
				Utils.checkXp(xp);
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"timer")) {
				if (args.length==1) {
					Utils.toggleModule("Timer");
				} else {
					try {
						float time = (float) Double.parseDouble(args[1]);
						if (time<=0) {
							time=0.1F;
						}
						Timer.time=time;
						Utils.addChat("§aLa vitesse du timer a été mis à "+time+" !");
					} catch (Exception e) {
                        Utils.addChat(err);
                    }
				}
				Utils.checkXp(xp);
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"unbindall")) {
				for (Module m : ModuleManager.ActiveModule) {
					m.setBind(-1);
				}
				Utils.addChat("§aBinds réinitialisées");
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"flight")) {
				if (args.length==1) {
					Utils.toggleModule("Flight");
				} else if (args[1].equalsIgnoreCase("blink")) {
					 if (Flight.blink) {
						 Flight.blink=false;
						 Utils.addChat("§cLe mode 'blink' du fly est désactivé !");
					 } else {
						 Flight.blink=true;
						 Utils.addChat("§aLe mode 'blink' du fly est activé !");
					 }
				} else {
					try {
						Flight.speed=Double.parseDouble(args[1]);
						Utils.addChat("§aLa vitesse du Flight a été mis à "+Flight.speed+" !");
					} catch (Exception e) {
                        Utils.addChat(err);
                    }
				}
				Utils.checkXp(xp);
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"irc")) {			
				if (args.length==1) {
					if (irc.isOn()) {
						Utils.addChat("§cIrc désactivé");
						new RequestThread("insertmsgleft", null).start();
						irc.setLastId(-1);					
					} else {
						Utils.addChat("§aIrc activé");
						new RequestThread("insertmsgjoin", null).start();
						Event.lastEventId=-1;
					}
					irc.setOn(!irc.isOn());
				} else if (args[1].equalsIgnoreCase("name") && args.length>=3) {
					ArrayList<String> list = new ArrayList<>();
					list.add(args[2]);					
					new RequestThread("nameisfree", list).start();				
				} else if (args[1].equalsIgnoreCase("playerclic") || args[1].equalsIgnoreCase("pc")) {
					irc.changePlayerClic();
					Utils.addChat("§aPlayerClic changé en "+irc.getPClic()+" !");
				} else if (args[1].equalsIgnoreCase("hide")) {
					if (irc.isHideJl()) {
						Utils.addChat("§aMessage join/left affichés");
					} else {
						Utils.addChat("§aMessage join/left masqués");
					}
					irc.setHideJl(!irc.isHideJl());
				} else if (args[1].equalsIgnoreCase("mode")) {
					try {
						IrcMode mode = IrcMode.valueOf(args[2]);
						Irc.getInstance().setMode(mode);
						Utils.addChat("§aIrc mode changé à "+args[2]+" !");
					} catch (Exception e) {
						Utils.addChat("§cErreur, modes disponibles:");
						Utils.addChat(Utils.setColor("Normal: Vous devez mettre le prefix de l'irc pour parler dans l'irc", "§c"));
						Utils.addChat(Utils.setColor("Hybride: Vous devez mettre le prefix de l'irc pour parler dans le chat normal", "§c"));
						Utils.addChat(Utils.setColor("Only: Vous n'avez que l'irc et ne pouvez que parler dedans, aucun message du chat normal n'est affiché", "§c"));
					}
				} else if (args[1].equalsIgnoreCase("list")) {
					new RequestThread("displaylist", null).start();
				} else if (args[1].equalsIgnoreCase("prefix")) {
					irc.setPrefix(args[2]);
					Utils.addChat("§aPrefix de l'irc changé !");
				}
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}

			if (args[0].equalsIgnoreCase(var.prefixCmd+"coord")) {
				Utils.addChat("Vos coordonnées: X:"+Math.round(mc.thePlayer.posX)+" Y:"+Math.round(mc.thePlayer.posY)+" Z:"+Math.round(mc.thePlayer.posZ));
				Utils.checkXp(xp);
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}		
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"classement") || args[0].equalsIgnoreCase(var.prefixCmd+"gl")) {
				new RequestThread("displaygl", null).start();
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"alt")) {
				Alt a = Alt.getAlt();
				if (args.length==1) {
					if (a.getAltTimer()==0) {
						new RequestThread("loginAlt", null).start();
						a.setAltTimer(15);
					} else {
						Utils.addChat(Utils.setColor("Vous pourrez utiliser cette commande dans "+a.getAltTimer()+" secondes !", "§c"));
					}
				} else if (args[1].equalsIgnoreCase("list")) {
					new RequestThread("totAlt", null).start();
				} else if (Utils.isInteger(args[1])) {
					ArrayList<String> l = new ArrayList<>();
					new RequestThread("loginAlt", l).start();
				}			
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"list")) {
				int a=0;
				String s="";
				ChatComponentText cc = new ChatComponentText("");
				Utils.addChat("========================================");
				for (Module mod : ModuleManager.ActiveModule) {
					if (a==0)
						s="§f"+mod.getName()+"§8";
					
					if ((mod.getCategory()!=Category.HIDE || mod.isCmd()) && a!=0)
						if (mod.isCmd()) {
							s=", §9"+mod.getName()+"§8";
						} else if (mod.getToggled()) {							
							s=", §a"+mod.getName()+"§8";
						} else {
							s=", §f"+mod.getName()+"§8";
						}
					cc.appendSibling(Utils.getHoverText(s, "§6Keybind du "+mod.getName()+":§7 "+Utils.getBind(mod.getName())));
					s="";
					a++;					
				}
				Utils.addChatText(new ChatComponentText(Utils.getNeko()+" Cheats [§7"+a+"§6] : ").appendSibling(cc));
				Utils.checkXp(xp);
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}							
			
			if (var3.equalsIgnoreCase(var.prefixCmd+"resetRpg")) {
				neko.module.modules.render.Render.bonusCount=0;
				Utils.removeAllLock();
				Utils.removeAllRank();
            	Utils.setRank("Petit Neko Novice");
            	var.niveau=1;
            	var.xp=0;
            	var.xpMax=300+Utils.getRandInt(100);
            	var.achievementHelp=false;
            	var.ame=0;
            	var.bonus=0;
            	Utils.addChat("§cRpg reset");
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"say")) {
				if (args.length==1) {
					Utils.addChat(error);
					mc.thePlayer.playSound("mob.villager.haggle", 1.0F, 1.0F);						
				} else {
					String s="";
					for (int k=1;k<args.length;k++) {
						s+=args[k] + " ";
					}
					mc.thePlayer.sendChatMessage(s);         			
				}
				Utils.checkXp(xp);
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
			
			//TODO: Nyah
			if (args[0].equalsIgnoreCase(var.prefixCmd+"nyah") || args[0].equalsIgnoreCase(var.prefixCmd+"nyah!") || args[0].equalsIgnoreCase(var.prefixCmd+"nyah*")) {
				if (Utils.isLock("--nyah")) {
					Utils.addWarn("Nyah");
					mc.thePlayer.playSound("mob.villager.no", 1.0F, 1.0F);
				} else {
					String nyah = Utils.getNyah();
					if (args.length==2) {
						nyah=args[1] + nyah;
					}
					mc.thePlayer.sendChatMessage(nyah);
				}
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
		} else {
			if (var3.equals(var.prefixCmd+Utils.verif)) {
				Utils.verif=null;
				
				String m[] = Utils.vMod.split(" ");						
				
				for (int i=0;i<m.length;i++) {					
					Utils.toggleModule(m[i]);
				}
				
				if (Utils.vDisplay)
					Utils.display=true;
				
				if (Utils.vXp)
					Utils.xp=true;
				
				if (Utils.vZoom)
					Utils.zoom=true;
				
				Utils.vMod=null;
				Utils.addChat("Tout a été réactivé !");
				var.name=var.CLIENT_NAME;
				int n = Utils.getRandInt(3);
				  	switch (n) {
					  case 0:Display.setTitle("Tu joues à la version satanique de Neko >:3");break;
					  case 1:Display.setTitle("Waw quel joueur inexpérimenté :o");break;
					  case 2:Display.setTitle("C'est tout un concept d'être libre de droit...");break;			  
					  case 3:
						  while (n==5) {
							  try {
								  Display.setTitle(Utils.getNyah());
								  n=0;
							  } catch (Exception ex) {}
						  }
						  break;
				  	}
				Utils.saveAll();
				this.mc.displayGuiScreen((GuiScreen)null); 
				return;
			} else if (var3.equalsIgnoreCase("Pyroman des abîmes, je t'invoque en t'offrant mon sang comme présent")) {
				new PyroThread().canBeAPyroman();
			} else {
				Utils.saveAll();
				mc.thePlayer.sendChatMessage(var3);
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
				this.mc.displayGuiScreen((GuiScreen)null);
				return;
			}
		}					
		Utils.saveAll();
		if (var3.startsWith(var.prefixCmd+"log add")) {
			mc.displayGuiScreen(new GuiChat(var.prefixCmd+"log add "));
		} else {
			mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			mc.displayGuiScreen((GuiScreen)null);
			return;
		}
	}		
		
}
