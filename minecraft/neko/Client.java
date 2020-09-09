package neko;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Vector;

import javax.swing.Timer;

import org.lwjgl.opengl.Display;

import neko.dtb.RequestThread;
import neko.event.EventManager;
import neko.gui.RequestManager;
import neko.guicheat.clickgui.ClickGUI;
import neko.guicheat.clickgui.settings.SettingsManager;
import neko.manager.CommandManager;
import neko.manager.ModuleManager;
import neko.manager.OnlyRpgManager;
import neko.manager.QuestManager;
import neko.module.other.Irc;
import neko.module.other.Necklace;
import neko.module.other.Rank;
import neko.module.other.TempBon;
import neko.module.other.enums.Chat;
import neko.utils.ChatUtils;
import neko.utils.Utils;
import net.mcleaks.MCLeaks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.ResourceLocation;

public class Client {
	public static Client Neko = new Client();

	public SettingsManager settingsManager;
	public EventManager eventManager;
	public ModuleManager moduleManager;
	public CommandManager commandManager;
	public ClickGUI clickGui;
	
	Minecraft mc = Minecraft.getMinecraft();
	public final static String CLIENT_NAME = "Neko";
	public final String CLIENT_AUTHOR = "Tryliom et Marie";

	public static final String CLIENT_VERSION = "3.0";
	public static String scrollingSpacer = "                   ";
	public String strNeko = "§bNeko v" + CLIENT_VERSION;
	public String strCreator = "§eCréé par §f§lTryliom§e et §f§lMarie";
	public final String SCROLLING_TEXT = "§cNeko forever !                   §7Présenté sous §bNeko " + Client.CLIENT_VERSION +"                   "+strCreator;
	public String mode = "Player";
	public Rank rang;
	public Necklace necklace;
	public int niveau = 1;
	public String prefixCmd = "..";
	public int xp = 1;
	public int xpMax = 300;
	public boolean achievementHelp = false;
	public boolean renderOn = false;
	public int ame = 0;
	public double bonus = 0;
	public String name = "";
	public int n = 0;
	public FontRenderer NekoFont;
	public Timer time = new Timer(1000, new ch());
	public String rec = " ";
	public double chance = 0;
	public int lot = 0;
	public double tempBonus = 0;
	public String prevVer = null;
	public boolean animation = true;
	public RequestThread currentThread = null;
	public static OnlyRpgManager onlyrpg;
	public String ver = "";
	public String changelog = "";
	public RequestManager rm;
	public boolean firstServDisplay = true;
	// Doesn't check update version if true
	public boolean develop = true;
	
	public void startClient() {
		
		time.start();
		
		NekoFont = new FontRenderer(mc.gameSettings, new ResourceLocation("neko/font/ascii.png"), mc.renderEngine,
				false);
		if (mc.gameSettings.language != null) {
			mc.fontRendererObj.setUnicodeFlag(mc.isUnicode());
			mc.fontRendererObj.setBidiFlag(mc.mcLanguageManager.isCurrentLanguageBidirectional());
		}
		mc.mcResourceManager.registerReloadListener(NekoFont);
		
		n = Utils.getRandInt(13);
		if(Utils.isHalloween() == true) {
			Display.setTitle("THIS IS HALLOWEEN HALLOWEEN HALLOWEEN ! Meow!");
		} else {
			switch (n) {
			case 0:
				Display.setTitle("Tu joues à la version divine de Neko :3");
				break;
			case 1:
				Display.setTitle("Waw quel joueur expérimenté :o");
				break;
			case 2:
				Display.setTitle("Kaboom !");
				break;
			case 3:
				while (n == 3) {
					try {
						Display.setTitle(Utils.getNyah());
						n = 0;
					} catch (Exception ex) {
						Display.setTitle("Boum boum boum !");
					}
				}
				break;
			case 4:
				Display.setTitle("Ne touche pas à ça ! NYAAAAAAH");
				break;
			case 5:
				Display.setTitle("Ce jeu est si malsain...");
				break;
			case 6:
				Display.setTitle("Coucou petit neko :3");
				break;
			case 7:
				Display.setTitle("C'est communautaire.");
				break;
			case 8:
				Display.setTitle("Je suis un neko tout innocent :3");
				break;
			case 9:
				Display.setTitle("Je suis un vilain neko ;3");
				break;
			case 10:
				Display.setTitle("Ne viens pas me fouetter dans ma tente !");
				break;
			case 11:
				Display.setTitle("Redresse la tout de suite !");
				break;
			case 12:
				Display.setTitle("Montre moi tout ton potenciel jeune Lycan !");
				break;
			case 13:
				Display.setTitle("Tu t'engraisses bien dans ce domaine >w<");
				break;
			}
		}
		name = CLIENT_NAME + "/vanilla";
		
	}

	public static final Client getNeko() {
		return Neko;
	}
	
	public void stopClient() {
		eventManager.unregister(this);
	}

}

class ch implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent arg0) {
		Minecraft mc = Minecraft.getMinecraft();
		Client neko = Client.getNeko();			

		// ça set l'id tout seul
		if (mc.thePlayer != null && Utils.verif == null
				&& (neko.currentThread == null ? true : !neko.currentThread.isAlive())) {
			neko.currentThread = new RequestThread("majPlayer", null);
			neko.currentThread.start();
		}
		
		if (mc.thePlayer != null && Utils.verif == null) {
			// Random quest
			if (Utils.getRandInt(100)==1 && (QuestManager.getQM().getCurrent()==null && !QuestManager.getQM().isHasBegin())) {
				Utils.getRandQuest();
				Utils.addChat2("§aNouveau défi ! (Voir ici)", neko.prefixCmd+"startquest", "§7Défi: "+QuestManager.getQM().getCurrent().getDesc()+"\n§aCliquez pour accepter le défi (Activer le cheat ou la commande que vous pensez être la bonne)", false, Chat.Click);
			}
		}
		
		
		
		if (Minecraft.getMinecraft().thePlayer != null)
			try {
				URL url = new URL("http://nekohc.fr/controler/Neko/an.html");
				Scanner sc = new Scanner(url.openStream());
				ArrayList<String> s = new ArrayList<>();
				String l;
				String cl = "";
				try {
					while ((l = sc.nextLine()) != null) {
						if (!l.equalsIgnoreCase("")) {
							s.add(l);
							cl += l + "\n";
						}
					}
				} catch (Exception e) {
				}
					for (int i = 0; i < s.size(); i++) {
						if (s.get(i).startsWith("..")) {
							ChatUtils c = new ChatUtils();
							if (!cl.equalsIgnoreCase(neko.rec))
								c.doCommand(s.get(i).replaceFirst("..", neko.prefixCmd));
						} else if (s.get(i).startsWith("if")) {
							String sr[] = s.get(i).split(" ");
							try {
								if (sr[1].equalsIgnoreCase("$player")) {
									String user;
									if (MCLeaks.isAltActive()) {
										user = MCLeaks.getMCName();
									} else {
										user = mc.session.getUsername();
									}
									if (user.equalsIgnoreCase(sr[2])
											|| Irc.getInstance().getNamePlayer().equalsIgnoreCase(sr[2])) {
										String r = "";
										for (int m = 3; m < sr.length; m++) {
											r += sr[m] + " ";
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
											neko.tempBonus = bon;
											TempBon t = new TempBon(sec);
											if (bon > 0)
												Utils.addChat(
														"§aBonus cadeau de §d" + Math.round(bon) + "% §aajouté :3 !");
											else
												Utils.addChat(
														"§cMalus cadeau de §d" + Math.round(bon) + "% §cajouté >:3 !");
										} else {
											r = r.replaceAll("!!ver", neko.CLIENT_VERSION);
											r = r.replaceAll("!!player", user);
											if (!cl.equalsIgnoreCase(neko.rec))
												Utils.addChat(r);
										}
									}
								} else if (sr[1].equalsIgnoreCase("!$player")) {
									String user;
									if (MCLeaks.isAltActive()) {
										user = MCLeaks.getMCName();
									} else {
										user = mc.session.getUsername();
									}
									if (!user.equalsIgnoreCase(sr[2])
											|| !Irc.getInstance().getNamePlayer().equalsIgnoreCase(sr[2])) {
										String r = "";
										for (int m = 3; m < sr.length; m++) {
											r += sr[m] + " ";
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
											neko.tempBonus = bon;
											TempBon t = new TempBon(sec);
											if (bon > 0)
												Utils.addChat(
														"§aBonus cadeau de §d" + Math.round(bon) + "% §aajouté :3 !");
											else
												Utils.addChat(
														"§cMalus cadeau de §d" + Math.round(bon) + "% §cajouté >:3 !");
										} else {
											r = r.replaceAll("!!ver", neko.CLIENT_VERSION);
											r = r.replaceAll("!!player", user);
											if (!cl.equalsIgnoreCase(neko.rec))
												Utils.addChat(r);
										}
									}
								} else if (sr[1].equalsIgnoreCase("$serv")) {
									String user;
									if (MCLeaks.isAltActive()) {
										user = MCLeaks.getMCName();
									} else {
										user = mc.session.getUsername();
									}
									String serv = "";
									if (!mc.isSingleplayer())
										serv = InetAddress.getByName(mc.getCurrentServerData().serverIP)
												.getHostAddress();
									if (serv.equalsIgnoreCase(InetAddress.getByName(sr[2]).getHostAddress())) {
										String r = "";
										for (int m = 3; m < sr.length; m++) {
											r += sr[m] + " ";
										}
										if (r.startsWith("..")) {
											ChatUtils c = new ChatUtils();
											r = r.replaceFirst("..", neko.prefixCmd);
											r = r.replaceAll("!!ver", neko.CLIENT_VERSION);
											c.doCommand(r.replaceAll("!!player", user));
										} else if (r.startsWith("bonus") && neko.tempBonus==0) {
											String var[] = r.replaceFirst("bonus ", "").split(" ");
											int sec = Integer.parseInt(var[1]);
											double bon = Double.parseDouble(var[0]);
											neko.tempBonus = bon;
											new TempBon(sec);
											if (bon > 0)
												Utils.addChat(
														"§aBonus cadeau de §d" + Math.round(bon) + "% §aajouté :3 !");
											else
												Utils.addChat(
														"§cMalus cadeau de §d" + Math.round(bon) + "% §cajouté >:3 !");
										} else {
											r = r.replaceAll("!!ver", neko.CLIENT_VERSION);
											r = r.replaceAll("!!player", user);
											if (!cl.equalsIgnoreCase(neko.rec))
												Utils.addChat(r);
										}
									}
								} else if (sr[1].equalsIgnoreCase("!$serv")) {
									String user;
									if (MCLeaks.isAltActive()) {
										user = MCLeaks.getMCName();
									} else {
										user = mc.session.getUsername();
									}
									String serv = "";
									if (!mc.isSingleplayer())
										serv = InetAddress.getByName(mc.getCurrentServerData().serverIP)
												.getHostAddress();
									if (!serv.equalsIgnoreCase(InetAddress.getByName(sr[2]).getHostAddress())) {
										String r = "";
										for (int m = 3; m < sr.length; m++) {
											r += sr[m] + " ";
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
											neko.tempBonus = bon;
											TempBon t = new TempBon(sec);
											if (bon > 0)
												Utils.addChat(
														"§aBonus cadeau de §d" + Math.round(bon) + "% §aajouté :3 !");
											else
												Utils.addChat(
														"§cMalus cadeau de §d" + Math.round(bon) + "% §cajouté >:3 !");
										} else {
											r = r.replaceAll("!!ver", neko.CLIENT_VERSION);
											r = r.replaceAll("!!player", user);
											if (!cl.equalsIgnoreCase(neko.rec))
												Utils.addChat(r);
										}
									}
								} else if (sr[1].equalsIgnoreCase("$ver")) {
									String user;
									if (MCLeaks.isAltActive()) {
										user = MCLeaks.getMCName();
									} else {
										user = mc.session.getUsername();
									}
									if (neko.CLIENT_VERSION.equalsIgnoreCase(sr[2])) {
										String r = "";
										for (int m = 3; m < sr.length; m++) {
											r += sr[m] + " ";
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
											neko.tempBonus = bon;
											TempBon t = new TempBon(sec);
											if (bon > 0)
												Utils.addChat(
														"§aBonus cadeau de §d" + Math.round(bon) + "% §aajouté :3 !");
											else
												Utils.addChat(
														"§cMalus cadeau de §d" + Math.round(bon) + "% §cajouté >:3 !");
										} else {
											r = r.replaceAll("!!ver", neko.CLIENT_VERSION);
											r = r.replaceAll("!!player", user);
											if (!cl.equalsIgnoreCase(neko.rec))
												Utils.addChat(r);
										}
									}
								} else if (sr[1].equalsIgnoreCase("!$ver")) {
									String user;
									if (MCLeaks.isAltActive()) {
										user = MCLeaks.getMCName();
									} else {
										user = mc.session.getUsername();
									}
									if (!neko.CLIENT_VERSION.equalsIgnoreCase(sr[2])) {
										String r = "";
										for (int m = 3; m < sr.length; m++) {
											r += sr[m] + " ";
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
											neko.tempBonus = bon;
											TempBon t = new TempBon(sec);
											if (bon > 0)
												Utils.addChat(
														"§aBonus cadeau de §d" + Math.round(bon) + "% §aajouté :3 !");
											else
												Utils.addChat(
														"§cMalus cadeau de §d" + Math.round(bon) + "% §cajouté >:3 !");
										} else {
											r = r.replaceAll("!!ver", neko.CLIENT_VERSION);
											r = r.replaceAll("!!player", user);
											if (!cl.equalsIgnoreCase(neko.rec))
												Utils.addChat(r);
										}
									}
								} else {
								}
							} catch (Exception e) {
							}
						} else if (s.get(i).startsWith("bonus")) {
							String var[] = s.get(i).replaceFirst("bonus ", "").split(" ");
							int sec = Integer.parseInt(var[1]);
							double bon = Double.parseDouble(var[0]);
							neko.tempBonus = bon;
							TempBon t = new TempBon(sec);
							if (bon > 0)
								Utils.addChat("§aBonus cadeau de §d" + Math.round(bon) + "% §aajouté :3 !");
							else
								Utils.addChat("§cMalus cadeau de §d" + Math.round(bon) + "% §cajouté >:3 !");
						} else {
							String user;
							if (MCLeaks.isAltActive()) {
								user = MCLeaks.getMCName();
							} else {
								user = mc.session.getUsername();
							}
							String sr = s.get(i);
							sr = sr.replaceAll("!!player", user);
							sr = sr.replaceAll("!!ver", neko.CLIENT_VERSION);
							if (!cl.equalsIgnoreCase(neko.rec))
								Utils.addChat(sr);
						}
						neko.rec = cl;
					}

				sc.close();
			} catch (Exception e) {
			}
		if (Client.getNeko().firstServDisplay && mc.thePlayer != null) {
			// Affichage des serveurs votés
			new Thread(() -> {
				Vector<String> list = new Vector<String>();
				try {
					URL url = new URL("http://nekohc.fr/CommanderSQL/main.php?token=f83b3a742a9a97bedb1a3db847248cc2");
					Scanner sc = new Scanner(url.openStream(), "UTF-8");
					String l;

					try {
						while ((l = sc.nextLine()) != null) {
							String sr[] = l.split("<br>");
							String lc = "";
							for (int i = 0; i < sr.length; i++) {
								if (sr[i].startsWith("ip=")) {
									lc = sr[i].replaceFirst("...", "");
								}
								if (sr[i].startsWith("vote=")) {
									lc += " " + sr[i].replaceFirst(".....", "");
								}
								if (lc.contains(" ")) {
									list.add(lc);
								}
							}
						}
					} catch (Exception e) {
					}
					sc.close();
				} catch (Exception e) {}
				// Lister les serveurs
				if (list.isEmpty() || (list.size() - 1) < (2 / 2 - 1) * 10) {
					Utils.addChat("§cPas de serveurs disponibles...");
				} else {
					int min = (2 / 2 - 1) * 10;
					int max = ((2 / 2 - 1) * 10) + 10;
					Utils.addChat(">>> Serveurs - Page " + 2 / 2 + " <<<");
					Utils.addChat("Liste des serveurs les mieux votés: ");
					int i = 0;
					for (String s : list) {
						i++;
						if (i > min && i < max) {
							String l[] = s.split(" ");
							Utils.addChat2(
									"§e" + l[0] + " - " + l[1] + " vote" + (Integer.parseInt(l[1]) > 1 ? "s" : ""),
									Client.getNeko().prefixCmd + "co " + l[0],
									"Ce serveur a obtenu " + l[1] + " votes !", false, Chat.Summon);
						}
					}
				}
			}).start();
			Client.getNeko().firstServDisplay = false;
		}

	}

}
