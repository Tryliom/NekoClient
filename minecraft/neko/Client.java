package neko;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.InetAddress;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.Timer;

import org.darkstorm.minecraft.gui.theme.simple.SimpleTheme;
import org.lwjgl.opengl.Display;

import neko.dtb.RequestThread;
import neko.gui.RequestManager;
import neko.manager.GuiManager;
import neko.manager.ModuleManager;
import neko.manager.OnlyRpgManager;
import neko.module.other.Conditions;
import neko.module.other.Irc;
import neko.module.other.Rank;
import neko.module.other.TempBon;
import neko.utils.ChatUtils;
import neko.utils.Utils;
import net.mcleaks.MCLeaks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.ResourceLocation;

public class Client {
	  Minecraft mc = Minecraft.getMinecraft();	
	  public final String CLIENT_NAME = "Neko";
	  public final String CLIENT_AUTHOR = "Tryliom";
	  public ModuleManager moduleManager;
	  public GuiManager gui;
	  public final String CLIENT_VERSION = "2.0";
	  private static final Client Neko = new Client();
	  public String mode="Player";
	  public Rank rang;
	  public int niveau=1;
	  public String prefixCmd="..";
	  public int xp=0;
	  public int xpMax=300;
	  public boolean achievementHelp=false; 
	  public boolean renderOn=false;
	  public int ame=0;
	  public double bonus=0;
	  public String name="";
	  public int n=0;
	  public FontRenderer NekoFont;
	  public Timer time = new Timer(5000, new ch());
	  public String rec=" ";
	  public double chance=0;
	  public int lot=0;
	  public double tempBonus = 0;
	  public String prevVer=null;
	  public boolean animation=true;
	  public RequestThread currentThread = null;
	  public static OnlyRpgManager onlyrpg;
	  public String ver = "";
	  public String changelog = "";
	  public RequestManager rm;
	  
	  public void startClient() {
		  time.start();
		  try {
			URL url = new URL("http://nekohc.fr/ver.html");
			Scanner sc = new Scanner(url.openStream());
			ArrayList<String> s = new ArrayList<>();
			String l;
			try {
				while ((l = sc.nextLine()) != null) {
					s.add(l);
				}
			} catch (Exception e) {}
			
			if (!s.get(0).equals(CLIENT_VERSION)) {
				ver=s.get(0);
				changelog=s.get(1);
			} else {
				System.out.println("Version à jour !");
			}
			sc.close();
		} catch (Exception e) {
			System.out.println("Adresse inateignable :c");
		}
		  NekoFont = new FontRenderer(mc.gameSettings, new ResourceLocation("neko/font/ascii.png"), mc.renderEngine, false);
		    if (mc.gameSettings.language != null)
		    {
		      mc.fontRendererObj.setUnicodeFlag(mc.isUnicode());
		      mc.fontRendererObj.setBidiFlag(mc.mcLanguageManager.isCurrentLanguageBidirectional());
		    }
		    mc.mcResourceManager.registerReloadListener(NekoFont);
		  this.moduleManager = new ModuleManager();
		  Utils.loadCmd();
		  System.out.println("Neko démarre doucement... :3");	
		  File f = new File(System.getenv("APPDATA") + "\\.minecraft\\Neko");
		  if (!f.exists())
			  f.mkdirs();
		  Utils.loadRank();
		  for (Rank r : ModuleManager.rang) {
				if (r.getName().equalsIgnoreCase("Petit Neko Novice")) {
					rang=r;
					r.setLvl(r.getLvl()!=1 ? r.getLvl() : 1);
					r.setLock(false);
				}
		  }
		  boolean legit = Utils.loadRpg();
		  Utils.loadFriends();
		  Utils.loadBind();
		  Utils.loadXray();
		  if (legit)
			  Utils.loadLock();
		  if (!legit) {
			  for (Rank r : ModuleManager.rang) {
					if (r.getName().equalsIgnoreCase("Petit Neko Novice")) {
						rang=r;
						r.setLvl(1);
						r.setLock(false);
					} else {
						r.setLvl(1);
						r.setLock(true);
					}
			  }
		  }				
		  onlyrpg = OnlyRpgManager.getRpg();	  
		  Utils.loadValues();
		  Utils.loadNuker();
		  Utils.loadIrc();
		  Utils.loadFont();
		  Utils.loadShit();		  
		  this.gui = new GuiManager();
		  gui.setTheme(new SimpleTheme());
		  gui.setup();
		  Utils.loadFrame();
		  n = Utils.getRandInt(11);
		  switch (n) {
			  case 0:Display.setTitle("Tu joues à la version divine de Neko :3");break;
			  case 1:Display.setTitle("Waw quel joueur expérimenté :o");break;
			  case 2:Display.setTitle("C'est tout un concept d'être enfermé là dedans...");break;			  
			  case 3:
				  while (n==5) {
					  try {
						  Display.setTitle(Utils.getNyah());
						  n=0;
					  } catch (Exception ex) {
						  Display.setTitle("Boum boum boum !");
					  }
				  }
				  break;
			  case 4:Display.setTitle("Ne touche pas à ça ! NYAAAAAAH");break;
			  case 5:Display.setTitle("Ce jeu est si malsain...");break;
			  case 6:Display.setTitle("Coucou petit neko :3");break;
			  case 7:Display.setTitle("Merci Brwserv pour les boutons reconnect");break;
			  case 8:Display.setTitle("Je suis un neko tout innocent :3");break;
			  case 9:Display.setTitle("Je suis un vilain neko ;3");break;
			  case 10:Display.setTitle("Fire et Enclume nyanyatent ensemble souvent ;3");break;
			  case 11:Display.setTitle("Github > Gitlab :3");break;
		  }
		  name=CLIENT_NAME+"/vanilla";  
	  }
	  public static final Client getNeko() {
		  return Neko;
	  }
	  
	}
class ch implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent arg0) {		
		Minecraft mc = Minecraft.getMinecraft();
		Client neko = Client.getNeko();
		
		// ça set l'id tout seul
		if (mc.thePlayer!=null && Utils.verif==null && (neko.currentThread==null ? true : !neko.currentThread.isAlive())) {
			neko.currentThread = new RequestThread("majPlayer", null);
			neko.currentThread.start();
		}
		
		
		if (Minecraft.getMinecraft().thePlayer!=null)
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
				if (!cl.equalsIgnoreCase(neko.rec))
					for (int i=0;i<s.size();i++) {
						if (s.get(i).startsWith("..")) {
							ChatUtils c = new ChatUtils();
							c.doCommand(s.get(i).replaceFirst("..", neko.prefixCmd));
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
											r = r.replaceFirst("..", neko.prefixCmd);
											r = r.replaceAll("!!ver", neko.CLIENT_VERSION);
											c.doCommand(r.replaceAll("!!player", user));
										} else if (r.startsWith("bonus")) {
											String var[] = r.replaceFirst("bonus ", "").split(" ");
											int sec = Integer.parseInt(var[1]);
											double bon = Double.parseDouble(var[0]);
											neko.tempBonus=bon;
											TempBon t = new TempBon(sec);	
											if (bon>0)
												Utils.addChat("§aBonus cadeau de §d"+Math.round(bon)+"% §aajouté :3 !");
											else 
												Utils.addChat("§cMalus cadeau de §d"+Math.round(bon)+"% §cajouté >:3 !");
										} else {
											r = r.replaceAll("!!ver", neko.CLIENT_VERSION);
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
											r = r.replaceFirst("..", neko.prefixCmd);
											r = r.replaceAll("!!ver", neko.CLIENT_VERSION);
											c.doCommand(r.replaceAll("!!player", user));
										} else if (r.startsWith("bonus")) {
											String var[] = r.replaceFirst("bonus ", "").split(" ");
											int sec = Integer.parseInt(var[1]);
											double bon = Double.parseDouble(var[0]);
											neko.tempBonus=bon;
											TempBon t = new TempBon(sec);
											if (bon>0)
												Utils.addChat("§aBonus cadeau de §d"+Math.round(bon)+"% §aajouté :3 !");
											else 
												Utils.addChat("§cMalus cadeau de §d"+Math.round(bon)+"% §cajouté >:3 !");
										} else {
											r = r.replaceAll("!!ver", neko.CLIENT_VERSION);
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
											r = r.replaceFirst("..", neko.prefixCmd);
											r = r.replaceAll("!!ver", neko.CLIENT_VERSION);
											c.doCommand(r.replaceAll("!!player", user));
										} else if (r.startsWith("bonus")) {
											String var[] = r.replaceFirst("bonus ", "").split(" ");
											int sec = Integer.parseInt(var[1]);
											double bon = Double.parseDouble(var[0]);
											neko.tempBonus=bon;
											TempBon t = new TempBon(sec);
											if (bon>0)
												Utils.addChat("§aBonus cadeau de §d"+Math.round(bon)+"% §aajouté :3 !");
											else 
												Utils.addChat("§cMalus cadeau de §d"+Math.round(bon)+"% §cajouté >:3 !");
										} else {
											r = r.replaceAll("!!ver", neko.CLIENT_VERSION);
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
											r = r.replaceFirst("..", neko.prefixCmd);
											r = r.replaceAll("!!ver", neko.CLIENT_VERSION);
											c.doCommand(r.replaceAll("!!player", user));
										} else if (r.startsWith("bonus")) {
											String var[] = r.replaceFirst("bonus ", "").split(" ");
											int sec = Integer.parseInt(var[1]);
											double bon = Double.parseDouble(var[0]);
											neko.tempBonus=bon;
											TempBon t = new TempBon(sec);
											if (bon>0)
												Utils.addChat("§aBonus cadeau de §d"+Math.round(bon)+"% §aajouté :3 !");
											else 
												Utils.addChat("§cMalus cadeau de §d"+Math.round(bon)+"% §cajouté >:3 !");
										} else {
											r = r.replaceAll("!!ver", neko.CLIENT_VERSION);
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
									if (neko.CLIENT_VERSION.equalsIgnoreCase(sr[2])) {
										String r="";
										for (int m=3;m<sr.length;m++) {
											r+=sr[m] + " ";
										}
										if (r.startsWith("..")) {
											ChatUtils c = new ChatUtils();
											r = r.replaceFirst("..", neko.prefixCmd);
											r = r.replaceAll("!!player", user);
											c.doCommand(r.replaceAll("!!ver", neko.CLIENT_VERSION));
										} else if (r.startsWith("bonus")) {
											String var[] = r.replaceFirst("bonus ", "").split(" ");
											int sec = Integer.parseInt(var[1]);
											double bon = Double.parseDouble(var[0]);
											neko.tempBonus=bon;
											TempBon t = new TempBon(sec);
											if (bon>0)
												Utils.addChat("§aBonus cadeau de §d"+Math.round(bon)+"% §aajouté :3 !");
											else 
												Utils.addChat("§cMalus cadeau de §d"+Math.round(bon)+"% §cajouté >:3 !");
										} else {
											r = r.replaceAll("!!ver", neko.CLIENT_VERSION);
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
									if (!neko.CLIENT_VERSION.equalsIgnoreCase(sr[2])) {
										String r="";
										for (int m=3;m<sr.length;m++) {
											r+=sr[m] + " ";
										}
										if (r.startsWith("..")) {
											ChatUtils c = new ChatUtils();
											r = r.replaceFirst("..", neko.prefixCmd);
											r = r.replaceAll("!!player", user);
											c.doCommand(r.replaceAll("!!ver", neko.CLIENT_VERSION));
										} else if (r.startsWith("bonus")) {
											String var[] = r.replaceFirst("bonus ", "").split(" ");
											int sec = Integer.parseInt(var[1]);
											double bon = Double.parseDouble(var[0]);
											neko.tempBonus=bon;
											TempBon t = new TempBon(sec);
											if (bon>0)
												Utils.addChat("§aBonus cadeau de §d"+Math.round(bon)+"% §aajouté :3 !");
											else 
												Utils.addChat("§cMalus cadeau de §d"+Math.round(bon)+"% §cajouté >:3 !");
										} else {
											r = r.replaceAll("!!ver", neko.CLIENT_VERSION);
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
							neko.tempBonus=bon;
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
							sr = sr.replaceAll("!!ver", neko.CLIENT_VERSION);
							Utils.addChat(sr);
						}
						neko.rec=cl;
					}
				
				sc.close();
			} catch (Exception e) {}
	}
	
}
