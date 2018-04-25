package neko.dtb;

import java.net.URL;
import java.net.URLEncoder;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Scanner;
import java.util.TimeZone;
import java.util.Vector;

import neko.Client;
import neko.api.NekoCloud;
import neko.manager.ModuleManager;
import neko.module.modules.render.Render;
import neko.module.other.Event;
import neko.module.other.Irc;
import neko.module.other.Rank;
import neko.module.other.enums.Chat;
import neko.module.other.enums.EventType;
import neko.module.other.enums.Rate;
import neko.utils.ChatUtils;
import neko.utils.Utils;
import net.minecraft.client.Minecraft;

public class RequestThread extends Thread {
	private ArrayList<String> args = new ArrayList<>();
	private String why;
	Minecraft mc = Minecraft.getMinecraft();
	Client var = Client.getNeko();
	
	public RequestThread(String why, ArrayList<String> args) {
		this.args=args;
		this.why=why;
	}
	
	// Certaines choses vont passer en args et d'autres sont get directement
	
	public void run() {
		if (why.equalsIgnoreCase("majPlayer")) {
			Irc irc = Irc.getInstance();
			long lastDate=new Date().getTime();
			try {					
				URL url = new URL("http://nekohc.fr/CommanderSQL/main.php?token=2da98670bbc841c7db76dbed8efedf54");
				Scanner sc = new Scanner(url.openStream());		
				String l;
				try {
					while ((l = sc.nextLine()) != null) {
						if (l.startsWith("NOW()=")) {
							lastDate=Long.parseLong(l.replaceFirst("......", ""));
						}
					}
				} catch (Exception e) {}
				
				sc.close();
			} catch (Exception e) {
				System.out.println("Erreur BDD: NOW()");
			}
			try {
				irc.setCurrServer((mc.isSingleplayer() ? "Solitaire" : mc.getCurrentServerData().serverIP.toLowerCase()));
			} catch (Exception e) {}
			if (irc.getIdPlayer()<=0) {
				int id=0;
				try {
					String s = "";
					if (Irc.getInstance().isOn())
						s = "\""+var.rang.getName()+"\",\""+var.rang.getColor()+"\",\""+irc.getNamePlayer()+"\",\""+irc.getCurrServer()+"\",\""+var.niveau+"\",\""+var.xp+"\",\""+var.xpMax+"\",\""+Utils.kills+"\",\""+(Utils.timeInGameHour==0 ? Utils.timeInGameMin==0 ? ""+Utils.timeInGameSec+"sec" : ""+Utils.timeInGameMin+"min "+Utils.timeInGameSec+"sec" : ""+Utils.timeInGameHour+"h "+Utils.timeInGameMin+"min "+Utils.timeInGameSec+"sec")+"\",\""+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.FRANCE).format(lastDate)+"\",\""+var.CLIENT_VERSION+"\",\""+mc.session.getUsername()+"\",\""+mc.session.getUsername()+"\",\""+(var.onlyrpg.isActive() ? "§aLegit" : "§cCheat")+"\"";
					else
						s = "\""+var.rang.getName()+"\",\""+var.rang.getColor()+"\",\""+irc.getNamePlayer()+"\",\""+irc.getCurrServer()+"\",\""+var.niveau+"\",\""+var.xp+"\",\""+var.xpMax+"\",\""+Utils.kills+"\",\""+(Utils.timeInGameHour==0 ? Utils.timeInGameMin==0 ? ""+Utils.timeInGameSec+"sec" : ""+Utils.timeInGameMin+"min "+Utils.timeInGameSec+"sec" : ""+Utils.timeInGameHour+"h "+Utils.timeInGameMin+"min "+Utils.timeInGameSec+"sec")+"\",\""+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.FRANCE).format(lastDate)+"\",\""+var.CLIENT_VERSION+"\",\""+mc.session.getUsername()+"\",\""+mc.session.getUsername()+"\",\""+"§cIRC Off"+"\"";
					URL url = new URL("http://nekohc.fr/CommanderSQL/main.php?token=9f2239355a3a86156e6e189ae44687af&args="+URLEncoder.encode(s, "UTF-8")+"&playerInc&last_id");					
					Scanner sc = new Scanner(url.openStream());		
					String l;
					try {
						while ((l = sc.nextLine()) != null) {
							if (l.startsWith("lastid=")) {
								id=Integer.parseInt(l.replaceFirst(".......", "").replace("<br>", ""));
							}
						}
					} catch (Exception e) {}
					
					sc.close();
				} catch (Exception e) {
					System.out.println("Erreur BDD: MajPlayer getLastId");
				}
				irc.setIdPlayer(id);
			} else {
				try {
					String s = "\""+irc.getIdPlayer()+"\"";					
					URL url = new URL("http://nekohc.fr/CommanderSQL/main.php?token=29fa5039dbe9fa1ca51932347ac62031&args="+URLEncoder.encode(s, "UTF-8"));
					Scanner sc = new Scanner(url.openStream());		
					String l;
					try {
						while ((l = sc.nextLine()) != null) {
							if (l.equalsIgnoreCase("false")) {
								int id=0;
								try {
									s = "\""+var.rang.getName()+"\",\""+var.rang.getColor()+"\",\""+irc.getNamePlayer()+"\",\""+(mc.isSingleplayer() ? "Solitaire" : mc.getCurrentServerData().serverIP.toLowerCase())+"\",\""+var.niveau+"\",\""+var.xp+"\",\""+var.xpMax+"\",\""+Utils.kills+"\",\""+(Utils.timeInGameHour==0 ? Utils.timeInGameMin==0 ? ""+Utils.timeInGameSec+"sec" : ""+Utils.timeInGameMin+"min "+Utils.timeInGameSec+"sec" : ""+Utils.timeInGameHour+"h "+Utils.timeInGameMin+"min "+Utils.timeInGameSec+"sec")+"\",\""+var.CLIENT_VERSION+"\",\""+mc.session.getUsername()+"\",\""+(var.onlyrpg.isActive() ? "§aLegit" : "§cCheat")+"\"";					
									url = new URL("http://nekohc.fr/CommanderSQL/main.php?token=3239bd0714e36f08fb95406bc18639e5&args="+URLEncoder.encode(s, "UTF-8")+"&playerInc&last_id");
									sc = new Scanner(url.openStream());
									try {
										while ((l = sc.nextLine()) != null) {
											if (l.startsWith("lastid=")) {
												id=Integer.parseInt(l.replaceFirst(".......", "").replace("<br>", ""));
											}
										}
									} catch (Exception e) {}
									
									sc.close();
								} catch (Exception e) {
									System.out.println("Erreur BDD: MajPlayer getLastId");
								}
								irc.setIdPlayer(id);
							}
						}
					} catch (Exception e) {}
					
					sc.close();
				} catch (Exception e) {
					System.out.println("Erreur BDD: MajPlayer getLastId");
				}
				try {
					String s = "";
					if (Irc.getInstance().isOn())
						s = "\""+var.rang.getName()+"\",\""+var.rang.getColor()+"\",\""+irc.getNamePlayer()+"\",\""+(mc.isSingleplayer() ? "Solitaire" : mc.getCurrentServerData().serverIP.toLowerCase())+"\",\""+var.niveau+"\",\""+var.xp+"\",\""+var.xpMax+"\",\""+Utils.kills+"\",\""+(Utils.timeInGameHour==0 ? Utils.timeInGameMin==0 ? ""+Utils.timeInGameSec+"sec" : ""+Utils.timeInGameMin+"min "+Utils.timeInGameSec+"sec" : ""+Utils.timeInGameHour+"h "+Utils.timeInGameMin+"min "+Utils.timeInGameSec+"sec")+"\",\""+var.CLIENT_VERSION+"\",\""+mc.session.getUsername()+"\",\""+(var.onlyrpg.isActive() ? "§aLegit" : "§cCheat")+"\",\""+irc.getIdPlayer()+"\"";
					else
						s = "\""+var.rang.getName()+"\",\""+var.rang.getColor()+"\",\""+irc.getNamePlayer()+"\",\""+(mc.isSingleplayer() ? "Solitaire" : mc.getCurrentServerData().serverIP.toLowerCase())+"\",\""+var.niveau+"\",\""+var.xp+"\",\""+var.xpMax+"\",\""+Utils.kills+"\",\""+(Utils.timeInGameHour==0 ? Utils.timeInGameMin==0 ? ""+Utils.timeInGameSec+"sec" : ""+Utils.timeInGameMin+"min "+Utils.timeInGameSec+"sec" : ""+Utils.timeInGameHour+"h "+Utils.timeInGameMin+"min "+Utils.timeInGameSec+"sec")+"\",\""+var.CLIENT_VERSION+"\",\""+mc.session.getUsername()+"\",\""+"§cIRC Off"+"\",\""+irc.getIdPlayer()+"\"";
					URL url = new URL("http://nekohc.fr/CommanderSQL/main.php?token=bbfee1f4f27b1be7fd757963452fb84e&args="+URLEncoder.encode(s, "UTF-8")+"&playerInc");
					Scanner sc = new Scanner(url.openStream());		
					sc.close();
				} catch (Exception e) {
					System.out.println("Erreur BDD: MajPlayer Insert");
				}
			}
			new RequestThread("insertRank", null).start();
		}
		
		if (why.equalsIgnoreCase("displaygl")) {
			String pRank="";
			String pRankColor="";
			String pName="";
			int pLvl=0;
			try {
				URL url = new URL("http://nekohc.fr/CommanderSQL/main.php?token=5fae61cb8045379e89cbe92e28741dbd");
				Scanner sc = new Scanner(url.openStream(), "UTF-8");		
				String l;
				Utils.addChat("§7--[§aMeilleurs joueurs (Lvl)§7]--\n");
				try {
					while ((l = sc.nextLine()) != null) {
						String r[] = l.split("<br>");
						int j = 0;
						if (r.length>1) {
							for (int i=0;i<r.length;i++) {	
								j++;
								if (r[i].startsWith("player_rank=")) {
									pRank=r[i].replaceFirst("............", "");
								}
								i++;
								if (r[i].startsWith("player_color_rang=")) {
									pRankColor=r[i].replaceFirst("..................", "").replaceAll("Â", "");
								}
								i++;
								if (r[i].startsWith("player_name=")) {
									pName=r[i].replaceFirst("............", "");
								}
								i++;
								if (r[i].startsWith("player_lvl=")) {
									pLvl=Integer.parseInt(r[i].replaceFirst("...........", ""));
								}
								
								Utils.addChat("§eN°"+j+" §7["+pRankColor+pRank+"§7] "+pRankColor+pName+"§e (Lvl "+NumberFormat.getNumberInstance(new Locale("FR", "CH")).format(pLvl)+")\n");								
							}
						}
					}
				} catch (Exception e) {}
				sc.close();
				// Afficher N° du joueur
				pName="";
				pLvl=0;
				try {
					url = new URL("http://nekohc.fr/CommanderSQL/main.php?token=c3b38eb4aaee1d40ea8a30a007df2f3d");
					sc = new Scanner(url.openStream());		
					try {
						while ((l = sc.nextLine()) != null) {
							String r[] = l.split("<br>");
							int j = 0;
							if (r.length>1) {
								for (int i=0;i<r.length;i++) {	
									if (r[i].startsWith("player_name=")) {
										pName=r[i].replaceFirst("............", "");
										j++;
									}
									i++;
									if (r[i].startsWith("player_lvl=")) {
										pLvl=Integer.parseInt(r[i].replaceFirst("...........", ""));
									}
									if (Irc.getInstance().getNamePlayer().equalsIgnoreCase(pName) && var.niveau==pLvl) {
										if (j>=11)
											Utils.addChat("§eN°"+j+" §7["+var.rang.getColor()+var.rang.getName()+"§7] "+var.rang.getColor()+Irc.getInstance().getNamePlayer()+"§e (Lvl "+NumberFormat.getNumberInstance(new Locale("FR", "CH")).format(var.niveau)+")");
										sc.close();
										return;
									}
								}
							}
						}
					} catch (Exception e) {}
					sc.close();
					
				} catch (Exception e) {
					System.out.println("Erreur BDD: DisplayGl2");
				}		

			} catch (Exception e) {
				System.out.println("Erreur BDD: DisplayGl");
			}		
		}
		
		if (why.equalsIgnoreCase("getServer")) {
			Vector<String> list = new Vector<String>();
			try {
				URL url = new URL("http://nekohc.fr/CommanderSQL/main.php?token=f83b3a742a9a97bedb1a3db847248cc2");
				Scanner sc = new Scanner(url.openStream(), "UTF-8");		
				String l;
				
				try {
					while ((l = sc.nextLine()) != null) {
						String sr[] = l.split("<br>");
						String lc = "";
						for (int i = 0;i<sr.length;i++) {
							if (sr[i].startsWith("ip=")) {
								lc=sr[i].replaceFirst("...", "");
							}
							if (sr[i].startsWith("vote=")) {
								lc+=" "+sr[i].replaceFirst(".....", "");
							}
							if (lc.contains(" ")) {
								list.add(lc);
							}
						}
					}
				} catch (Exception e) {}
				sc.close();
			} catch (Exception e) {
				System.out.println("Erreur BDD: Get BestServ");
			}			
			// Lister les serveurs
			if (list.isEmpty() || (list.size()-1)<(Integer.parseInt(args.get(0))/2-1)*10) {
				Utils.addChat("§cPas de serveurs disponibles...");
			} else {
				int min = (Integer.parseInt(args.get(0))/2-1)*10;
				int max = ((Integer.parseInt(args.get(0))/2-1)*10)+10;
				Utils.addChat(">>> Serveurs - Page "+Integer.parseInt(args.get(0))/2+" <<<");
				Utils.addChat("Liste des serveurs les mieux votés: ");
				int i=0;
				for (String s : list) {
					i++;
					if (i>min && i<max) {
						String l[] = s.split(" ");
						Utils.addChat2("§e"+l[0]+" - "+l[1]+" vote"+(Integer.parseInt(l[1])>1 ? "s" : ""), var.prefixCmd+"co "+l[0], "Ce serveur a obtenu "+l[1]+" votes !", false, Chat.Summon);
					}
				}
			}
			
		}
		
		if (why.equalsIgnoreCase("vote")) {
			// Arg: ip du serveur
			int vote = 1;
			try {
				String s = "\""+args.get(0)+"\"";					
				URL url = new URL("http://nekohc.fr/CommanderSQL/main.php?token=381d315388f23cb8d6de57378b22987d&args="+URLEncoder.encode(s, "UTF-8"));
				System.out.println(url.toString());
				Scanner sc = new Scanner(url.openStream(), "UTF-8");		
				String l;
				try {
					while ((l = sc.nextLine()) != null) {
						String sr[] = l.split("<br>");
						if (sr[0].startsWith("vote=")) {
							vote+=Integer.parseInt(sr[0].replaceFirst(".....", ""));
							break;
						}
					}
				} catch (Exception e) {}
				sc.close();
			} catch (Exception e) {
				System.out.println("Erreur BDD: Get NbVote");
			}
			if (vote==1) {
				try {
					String s = "\""+args.get(0)+"\",\""+vote+"\"";					
					URL url = new URL("http://nekohc.fr/CommanderSQL/main.php?token=07ee80cc6502bc8e0bd48ceb777e1af2&args="+URLEncoder.encode(s, "UTF-8"));
					Scanner sc = new Scanner(url.openStream(), "UTF-8");		
					sc.close();
				} catch (Exception e) {
					System.out.println("Erreur BDD: Insert ServerIP & NbVote");
				}
			} else {
				try {
					String s = "\""+vote+"\",\""+args.get(0)+"\"";				
					URL url = new URL("http://nekohc.fr/CommanderSQL/main.php?token=dcd725110b9866d2292b1389f04bb5e6&args="+URLEncoder.encode(s, "UTF-8"));
					Scanner sc = new Scanner(url.openStream(), "UTF-8");		
					sc.close();
				} catch (Exception e) {
					System.out.println("Erreur BDD: Update NbVote");
				}
			}
			ArrayList<String> list = new ArrayList<>();
			list.add(Utils.setColor("§a"+Irc.getInstance().getNamePlayer()+" a voté pour le serveur §c"+Utils.setColor(args.get(0), "§c")+" !", "§a"));
			new RequestThread("alert", list).start();			
		}
		
		if (why.equalsIgnoreCase("startEvent")) {
			try {
				String s = "\""+args.get(0)+"\",\""+args.get(1)+"\",\""+Event.mdp+"\"";					
				URL url = new URL("http://nekohc.fr/CommanderSQL/main.php?token=9c49ef4c5819643a1806314dec166237&args="+URLEncoder.encode(s, "UTF-8"));
				Scanner sc = new Scanner(url.openStream());		
				sc.close();
			} catch (Exception e) {
				System.out.println("Erreur BDD: Start Event");
			}
		}
		
		if (why.equalsIgnoreCase("listServer")) {
			Vector<String> list = new Vector<String>();
			try {				
				URL url = new URL("http://nekohc.fr/CommanderSQL/main.php?token=fc6b407325ab1bf677ea2e0f048e415d");
				Scanner sc = new Scanner(url.openStream());
				String l;
				try {
					l = sc.nextLine();
					if ((l = sc.nextLine()) != null) {
						String ls[] = l.split("<br>");
						for (int i=0;i<ls.length;i++) {
							String s = ls[i];
							if (s.startsWith("server=")) {
								s = s.replaceFirst(".......", "");
								boolean cont = true;
								if (list.contains(s))
									cont=false;
								if (cont && !s.equalsIgnoreCase("127.0.0.1") && !s.contains("0.0.0.0") && !s.equalsIgnoreCase("null") && !s.contains("§")) {
									list.add(s.toLowerCase());
								}
							}
						}
					}
				} catch (Exception e) {}
				sc.close();
			} catch (Exception e) {
				System.out.println("Erreur BDD: List Server");
			}
			int page = -1;
			if (args!=null) {
				page = Integer.parseInt(args.get(0))<=0 ? 1 : Integer.parseInt(args.get(0));
			}
			Utils.addChat(Utils.sep);
			Utils.addChat("Listes des serveurs utilisés par les Neko:\n");
			int i = 0;
			if (page==-1)
				for (String s : list) {
					i++;
					Utils.addChat2("§8[§e"+i+"§8]§7 "+s, var.prefixCmd+"co "+s, "§aCliquer pour se connecter !", false, Chat.Summon);
				}
			else {
				if ((list.size()/50)<page)
					page = list.size()/50;
				Utils.addChat("Page n°"+page);
				for (String s : list) {
					i++;
					if (i>=(page-1)*50 && i<=page*50)
						Utils.addChat2("§8[§e"+i+"§8]§7 "+s, var.prefixCmd+"co "+s, "§aCliquer pour se connecter !", false, Chat.Summon);
				}
			}
			
		}		
		
		if (why.equalsIgnoreCase("stopEvent")) {
			try {
				String s = "\""+Event.mdp+"\"";					
				URL url = new URL("http://nekohc.fr/CommanderSQL/main.php?token=8ab5fda7e220db3dc80dffe6ad3bf216&args="+URLEncoder.encode(s, "UTF-8"));
				Scanner sc = new Scanner(url.openStream());		
				sc.close();
			} catch (Exception e) {
				System.out.println("Erreur BDD: Stop Event");
			}
		}
		
		if (why.equalsIgnoreCase("displaylist")) {
			
			String pRank="";
			String pRankColor="";
			String pName="";
			String pServer="";
			int pLvl=0;
			int pXp=0;
			int pXpMax=0;
			int pKill=0;
			String pTime="";
			String pVer="";
			String pMode="";
			try {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				sdf.setTimeZone(TimeZone.getTimeZone("Europe/Paris"));	
				String s = "\""+sdf.format(new Date().getTime()-10000)+"\",\""+sdf.format(new Date().getTime())+"\"";
				System.out.println(s);
				URL url = new URL("http://nekohc.fr/CommanderSQL/main.php?token=b0ac0857d55ccb7f52303bc7e440b02e&args="+URLEncoder.encode(s, "UTF-8"));
				Scanner sc = new Scanner(url.openStream(), "UTF-8");		
				String l;
				Utils.addChat("§7--[§aJoueurs connectés§7]--");
				try {
					while ((l = sc.nextLine()) != null) {
						String r[] = l.split("<br>");
						if (r.length>1) {
							for (int i=0;i<r.length;i++) {		
								if (r[i].startsWith("player_rank=")) {
									pRank=r[i].replaceFirst("............", "");
								}
								i++;
								if (r[i].startsWith("player_color_rang=")) {
									pRankColor=r[i].replaceFirst("..................", "").replaceAll("Â", "");
								}
								i++;
								if (r[i].startsWith("player_name=")) {
									pName=r[i].replaceFirst("............", "");
								}
								i++;
								if (r[i].startsWith("player_server=")) {
									pServer=r[i].replaceFirst("..............", "");
								}
								i++;
								if (r[i].startsWith("player_lvl=")) {
									pLvl=Integer.parseInt(r[i].replaceFirst("...........", ""));
								}
								i++;
								if (r[i].startsWith("player_xp=")) {
									pXp=Integer.parseInt(r[i].replaceFirst("..........", ""));
								}
								i++;
								if (r[i].startsWith("player_xpmax=")) {
									pXpMax=Integer.parseInt(r[i].replaceFirst(".............", ""));
								}
								i++;
								if (r[i].startsWith("player_kill=")) {
									pKill=Integer.parseInt(r[i].replaceFirst("............", "").equalsIgnoreCase("NULL") ? "0" : r[i].replaceFirst("............", ""));
								}
								i++;
								if (r[i].startsWith("player_time=")) {
									pTime=r[i].replaceFirst("............", "");
								}	
								i++;
								if (r[i].startsWith("player_ver=")) {
									pVer=r[i].replaceFirst("...........", "");
								}
								i++;
								if (r[i].startsWith("player_onlyrpg=")) {
									pMode=r[i].replaceFirst("...............", "").replaceAll("Â", "");
								}
								// Afficher ici
								HashMap<String, String> hm = NekoCloud.getNekoAPI().getBaseBody();
						        hm.put("player_name", pName);
						        String realname = "";
						        if (Utils.admin) {
									realname = Utils.preparePostRequest("https://qy0n81yfr7.execute-api.eu-central-1.amazonaws.com/beta/admin/access/realname", NekoCloud.getNekoAPI().parseHashMapToJson(hm)).replaceAll("\"", "");
									if (realname.startsWith(pName+"=")) {
										realname = realname.replaceFirst(pName+"=", "");
									}
								}
								Locale loc = new Locale("FR", "CH");
								Utils.addChat2("§7["+(pMode.equalsIgnoreCase("§cIrc désactivé") ? "§c-" : "§a+")+"§7] "+pName+" joue sur §e"+pServer, Irc.getInstance().getPlayerClic(pName, pServer), "§7["+pRankColor+pRank+"§7]\n§d"+pName+"\n"+(realname.isEmpty() ? "" : "§cNom en jeu: "+realname+"\n")+"§bLvl."+NumberFormat.getNumberInstance(loc).format(pLvl)+" §7["+NumberFormat.getNumberInstance(loc).format(pXp)+"xp§7/"+NumberFormat.getNumberInstance(loc).format(pXpMax)+"xp§7]\n§7Serveur: "+pServer+"\n§7"+pKill+" kills\n§7"+pTime+" de temps de jeu\n§7Version: "+pVer+"\n§7Mode: "+pMode, pServer.equalsIgnoreCase("Localhost"), Chat.Summon);								
							}
						}
					}
				} catch (Exception e) {
					
				}
				
				sc.close();
			} catch (Exception e) {
				System.out.println("Erreur BDD: DisplayList");
			}
			Utils.addChat("§7--------------------");
			
		}
		
		if (why.equalsIgnoreCase("insertMsgJoin")) {
			
			try {				
				URL url = new URL("http://nekohc.fr/CommanderSQL/main.php?token=9243428aa1d9cea7c2e0eceb9d64853f&args=\""+URLEncoder.encode(Irc.getInstance().getNamePlayer(), "UTF-8")+"\"&messageInc");
				Scanner sc = new Scanner(url.openStream());		
				sc.close();
			} catch (Exception e) {
				System.out.println("Erreur BDD: MsgJoin");
			}
			
		}
		
		if (why.equalsIgnoreCase("insertMsgLeft")) {
			
			try {				
				URL url = new URL("http://nekohc.fr/CommanderSQL/main.php?token=48fb95e6bdc35e7390ad3e1e6e4fe5d1&args=\""+URLEncoder.encode(Irc.getInstance().getNamePlayer(), "UTF-8")+"\"&messageInc");
				Scanner sc = new Scanner(url.openStream());		
				sc.close();
			} catch (Exception e) {
				System.out.println("Erreur BDD: MsgLeft");				
			}
			
		}
		
		if (why.equalsIgnoreCase("insertMsg")) {
			String msg = args.get(0); 				
			HashMap<String, String> hm = NekoCloud.getNekoAPI().getBaseBody();
			hm.put("message", msg.replaceAll("\"", "'"));
			hm.put("message_type", args.size()==2 ? args.get(1) : "all");
			Utils.preparePostRequest("https://qy0n81yfr7.execute-api.eu-central-1.amazonaws.com/beta/irc/message/send", NekoCloud.getNekoAPI().parseHashMapToJson(hm));
		}
		
		if (why.equalsIgnoreCase("ban")) {
			HashMap<String, String> hm = NekoCloud.getNekoAPI().getBaseBody();
			hm.put("player_name", args.get(0));
			hm.put("reason", args.get(1));
			String res = Utils.preparePostRequest("https://qy0n81yfr7.execute-api.eu-central-1.amazonaws.com/beta/admin/command/ban", NekoCloud.getNekoAPI().parseHashMapToJson(hm)).replaceAll("\"", "");
			if (!res.equalsIgnoreCase("success")) {
				Utils.addError(res);
			}
		}
		
		if (why.equalsIgnoreCase("mute")) {			
			HashMap<String, String> hm = NekoCloud.getNekoAPI().getBaseBody();
			hm.put("player_name", args.get(0));
			hm.put("reason", args.get(1));
			String res = Utils.preparePostRequest("https://qy0n81yfr7.execute-api.eu-central-1.amazonaws.com/beta/admin/command/mute", NekoCloud.getNekoAPI().parseHashMapToJson(hm)).replaceAll("\"", "");
			if (!res.equalsIgnoreCase("success")) {
				Utils.addError(res);
			}
		}
		
		if (why.equalsIgnoreCase("unmute")) {			
			HashMap<String, String> hm = NekoCloud.getNekoAPI().getBaseBody();
			hm.put("player_name", args.get(0));
			String res = Utils.preparePostRequest("https://qy0n81yfr7.execute-api.eu-central-1.amazonaws.com/beta/admin/command/unmute", NekoCloud.getNekoAPI().parseHashMapToJson(hm)).replaceAll("\"", "");
			if (!res.equalsIgnoreCase("success")) {
				Utils.addError(res);
			}
		}
		
		if (why.equalsIgnoreCase("displayEvent")) {
			Irc irc = Irc.getInstance();
			int lastEventID = 0;
			try {
				String s = "\""+irc.getNamePlayer()+"\",\""+mc.session.getUsername()+"\",\""+(mc.isSingleplayer() ? "Localhost" : mc.getCurrentServerData().serverIP.toLowerCase())+"\",\""+var.CLIENT_VERSION+"\"";
				URL url = new URL("http://nekohc.fr/CommanderSQL/main.php?token=ab285aefaa5303fa1af43d5f5cdf0d2b&args="+URLEncoder.encode(s, "UTF-8"));
				Scanner sc = new Scanner(url.openStream());
				String l;					
				try {
					while ((l = sc.nextLine()) != null) {
						if (l.startsWith("MAX(event_id)=")) {
							lastEventID=Integer.parseInt(l.replaceFirst("..............", "").replace("<br>", ""));
						}						
					}
				} catch (Exception ex) {}
				sc.close();
			} catch (Exception ex) {
				System.out.println("Erreur BDD: setLastEventId");
			}
			if (Event.lastEventId<=0) {
				Event.lastEventId=lastEventID;
				return;
			}
			if (Event.lastEventId==lastEventID) {
				return;
			}
			ArrayList<Event> list = new ArrayList<>();
			try {								
				String st = "\""+irc.getNamePlayer()+"\",\""+mc.session.getUsername()+"\",\""+(mc.isSingleplayer() ? "Localhost" : mc.getCurrentServerData().serverIP.toLowerCase())+"\",\""+var.CLIENT_VERSION+"\"";
				URL url = new URL("http://nekohc.fr/CommanderSQL/main.php?token=2ac1e061620b58d6c882d80c92d3a759&args=\""+Event.lastEventId+"\","+URLEncoder.encode(st, "UTF-8"));
				Scanner sc = new Scanner(url.openStream(), "UTF-8");	
				String l;
				String type ="";
				String cmd ="";
				try {
					while ((l = sc.nextLine()) != null) {						
						String s[] = l.split("<br>");
						if (s.length>1)
							for (int i=0;i<s.length;i++) {								
								if (s[i].startsWith("event_type=")) {
									type=s[i].replaceFirst("...........", "");
								} 
								i++;
								if (s[i].startsWith("event_cmd=")) {
									cmd=s[i].replaceFirst("..........", "");
								} 
								if (!cmd.isEmpty() && !type.isEmpty()) {									
									Event.lastEventId++;								
									list.add(new Event(EventType.valueOf(type), cmd));
									cmd = "";
									type = "";
								}
							}
					}
				} catch (Exception ex) {}
				sc.close();
			} catch (Exception ex) {
				System.out.println("Erreur BDD: get Event\n"+ex.getMessage());
				return;
			}			
			if (Event.lastEventId<lastEventID) {
				Event.lastEventId=lastEventID;
			}
			if (list.isEmpty() || list.size()==0) {
				return;
			}		
			for (Event e : list) {
				try {
					EventType et = e.getType();
					String cmd = e.getCmd();
					
					if (et.equals(EventType.Xp)) {
						Utils.checkXp(Integer.parseInt(cmd));
						Utils.addChat("§dEvent: "+Utils.setColor("Gain de "+cmd+" xp !", "§9"));
					}
					if (et.equals(EventType.Lvl)) {
						int lvl = Integer.parseInt(cmd);						
						Utils.addChat("§dEvent: Mis au lvl §7"+lvl);
						var.niveau=lvl;
						var.xp=1;
						int r = Utils.getRandInt(1000);
						var.xpMax=300+(r<200 ? 200+r : r)*(lvl);
						var.chance=0.0001*lvl/25;
					}
					if (et.equals(EventType.Unlock)) {
						Utils.unlock(cmd);
						Utils.addChat("§dEvent: "+Utils.setColor("Unlock du "+Utils.getUnlock(cmd).getName()+" !", "§9"));
					}
					if (et.equals(EventType.RandUnlock)) {
						Utils.getRandUnlock();
						Utils.addChat("§dEvent: "+Utils.setColor("Unlock aléatoire gagné !", "§9"));
					}
					if (et.equals(EventType.Rang)) {
						Utils.setRank(cmd);
						Utils.addChat("§dEvent: "+Utils.setColor("Rang "+Utils.getRank(cmd).getColor()+Utils.getRank(cmd).getName()+" gagné !", "§9"));
					}
					if (et.equals(EventType.RangRate)) {
						cmd = cmd.toLowerCase();
						cmd = cmd.replaceFirst(".", (cmd.charAt(0)+"").toUpperCase());	
						if (cmd.equalsIgnoreCase("ultrarare"))
							cmd="UltraRare";
						Rank r = Utils.getRank(Utils.getRandRank(Rate.valueOf(cmd)));
						Utils.setRank(r.getName());
						Utils.addChat("§dEvent: §9Rang "+r.getColor()+r.getName()+"§9 gagné !");
					}
					if (et.equals(EventType.Msg)) {
						if (cmd.startsWith("&")) {
							String t[] = cmd.split(" ");
							if (t[0].length()==2)
								cmd = Utils.setColor(cmd, t[0]);
						}
						Utils.addChat(cmd);
					}
					
					if (et.equals(EventType.MeteoreRain)) {
						Utils.addChat("§dEvent: §9Pluie de météores !");
						Render.MeteoreRain();
					}
					
					if (et.equals(EventType.Cmd)) {
						if (cmd.startsWith("..")) {
							cmd = cmd.replaceFirst("..", var.prefixCmd);
						}
						if (cmd.contains(" && ")) {
							String m[] = cmd.split(" && ");
							for (String ms : m) {
								new ChatUtils().doCommand(ms);
							}
						} else if (cmd.contains("&&")) {
							String m[] = cmd.split("&&");
							for (String ms : m) {
								new ChatUtils().doCommand(ms);
							}
						} else
							new ChatUtils().doCommand(cmd);
						
					}
				} catch (Exception ec) {}
			}
			
		}
		
		if (why.equalsIgnoreCase("insertRank")) {
			boolean isValid = true;
			// Check si les rangs ont déjà été inséré
			for (Rank r : ModuleManager.rang) {
				if (!r.isLock()) {
					try {
						URL url = new URL("http://nekohc.fr/CommanderSQL/main.php?token=f50c8e137fbd30c888a13585577e1cd6&args=\""+Irc.getInstance().getIdPlayer()+"\",\""+URLEncoder.encode(""+r.getName()+"", "UTF-8")+"\"");
						Scanner sc = new Scanner(url.openStream());	
						String l;					
						try {
							while ((l = sc.nextLine()) != null) {
								if (l.contains("true")) {
									isValid = false;
								}
							}
						} catch (Exception e) {}
						sc.close();
					} catch (Exception e) {
						System.out.println("Erreur BDD: checkRank");
					}
					
					// Insertion des données
					String rate=r.getRate().name();
					String name=r.getName();
					String color=r.getColor();
					double bonus=r.getBonus();
					String desc=r.getDesc();
					if (isValid)
					try {
						URL url = new URL("http://nekohc.fr/CommanderSQL/main.php?token=e20acbb3ba58477b08bbd2a22d1e5b33&args=\""+URLEncoder.encode(""+Irc.getInstance().getIdPlayer()+"\",\""+rate+"\",\""+name+"\",\""+color+"\",\""+bonus+"\",\""+desc+"\"", "UTF-8"));
						Scanner sc = new Scanner(url.openStream());	
						sc.close();
					} catch (Exception e) {
						System.out.println("Erreur BDD: insertRank");
					}
				}
			}
		}
		
		if (why.equalsIgnoreCase("insertEvent")) {
			HashMap<String, String> hm = NekoCloud.getNekoAPI().getBaseBody();
			hm.put("player_name", args.get(0));
			hm.put("server", args.get(1).toLowerCase());
			hm.put("version", args.get(2));
			hm.put("type", args.get(3));
			hm.put("cmd", args.get(4));
			String res = Utils.preparePostRequest("https://qy0n81yfr7.execute-api.eu-central-1.amazonaws.com/beta/admin/command/event", NekoCloud.getNekoAPI().parseHashMapToJson(hm)).replaceAll("\"", "");
			if (!res.equalsIgnoreCase("success")) {
				Utils.addError(res);
			}
		}
		
		if (why.equalsIgnoreCase("displayMsg")) {
			if (Irc.getInstance().getLastId()<=0) {
				try {
					URL url = new URL("http://nekohc.fr/CommanderSQL/main.php?token=3dba9e35afdf13e5fef3f9471c80cf15&args=\""+Irc.getInstance().getNamePlayer()+"\"");
					Scanner sc = new Scanner(url.openStream());	
					String l;					
					try {
						while ((l = sc.nextLine()) != null) {
							if (l.startsWith("MAX(msg_id)=")) {
								Irc.getInstance().setLastId(Integer.parseInt(l.replaceFirst("............", "").replace("<br>", "").replaceAll("\t", "")));
							}						
						}
					} catch (Exception e) {}
					sc.close();
				} catch (Exception e) {
					System.out.println("Erreur BDD: setLastMsgId");
				}
			}
			ArrayList<String> list = new ArrayList<>();
			int playerId=0;
			
			String msg = "";
			if (Irc.getInstance().getLastId()<0)
				return;
			try {
				URL url = new URL("http://nekohc.fr/CommanderSQL/main.php?token=f2400d205e10f2b0220d285fbf0f2b0e&args=\""+Irc.getInstance().getLastId()+"\",\""+URLEncoder.encode(Irc.getInstance().getNamePlayer(), "UTF-8")+"\"");
				Scanner sc = new Scanner(url.openStream(), "UTF-8");	
				String l;
				
				try {
					while ((l = sc.nextLine()) != null) {						
						String s[] = l.split("<br>");
						if (s.length>1)
							for (int i=0;i<s.length;i++) {
								if (s[i].startsWith("player_id=")) {
									playerId=Integer.parseInt(s[i].replaceFirst("..........", ""));
								} 
								i++;
								if (s[i].startsWith("msg=")) {
									msg=s[i].replaceFirst("....", "");
								} 
								if (!msg.isEmpty() && playerId!=0 && !msg.equalsIgnoreCase(Irc.getInstance().getLastMsg())) {									
									list.add(""+playerId+" "+msg);
									playerId=0;
									msg="";
									Irc.getInstance().setLastId(Irc.getInstance().getLastId()+1);
								}
							}
					}
				} catch (Exception e) {}
				sc.close();
			} catch (Exception e) {
				System.out.println("Erreur BDD: get Msg & PlayerId\n"+e.getMessage());
				return;
			}
			
			if (list.isEmpty() || list.size()==0)
				return;
			for (String str : list) {
				String s[] = str.split(" ");
				// Player ID
				int pid = Integer.parseInt(s[0]);
				// Message
				String m="";
				if (s.length==2) {
					m=s[1];
				} else if (s.length>2) {
					for (int i=1;i<s.length;i++) {
						if (i+1==s.length) {
							m+=s[i];
						} else
							m+=s[i]+" ";
					}
				}
				boolean cont = false;
				try {
					URL url = new URL("http://nekohc.fr/CommanderSQL/main.php?token=6f3444869bb758fa613c914a3620f6de&args=\""+pid+"\"");
					Scanner sc = new Scanner(url.openStream(), "UTF-8");	
					String l;
					try {
						while ((l = sc.nextLine()) != null) {
							if (l.equalsIgnoreCase("muted=muted<br>")) {
								cont = true;
							} 
						}
					} catch (Exception e) {}
					sc.close();
				} catch (Exception e) {
					System.out.println("Erreur BDD: Is Muted\n"+e.getMessage());
					return;
				}
				
				if (pid==1 && !Irc.getInstance().isHideJl()) {
					Utils.addChat2Irc(m, "", "§7"+m, true, Chat.Summon);
				} else if (!cont && (pid==1 ? !Irc.getInstance().isHideJl() : true)) {					
					String pRank="";
					String pRankColor="";
					String pName="";
					String pServer="";
					int pLvl=0;
					int pXp=0;
					int pXpMax=0;
					int pKill=0;
					String pTime="";
					String pVer="";
					String pMode="";
					try {
						URL url = new URL("http://nekohc.fr/CommanderSQL/main.php?token=2d29d2cf2a70b4b6112b2afef9276829&args=\""+pid+"\"");
						Scanner sc = new Scanner(url.openStream(), "UTF-8");	
						String l;					
						try {
							while ((l = sc.nextLine()) != null) {
								int i=0;
								String r[] = l.split("<br>");
								if (r.length>1) {
									if (r[i].startsWith("player_rank=")) {
										pRank=r[i].replaceFirst("............", "");
									}
									i++;
									if (r[i].startsWith("player_color_rang=")) {
										pRankColor=r[i].replaceFirst("..................", "").replaceAll("Â", "");
									}
									i++;
									if (r[i].startsWith("player_name=")) {
										pName=r[i].replaceFirst("............", "");
									}
									i++;
									if (r[i].startsWith("player_server=")) {
										pServer=r[i].replaceFirst("..............", "");
									}
									i++;
									if (r[i].startsWith("player_lvl=")) {
										pLvl=Integer.parseInt(r[i].replaceFirst("...........", ""));
									}
									i++;
									if (r[i].startsWith("player_xp=")) {
										pXp=Integer.parseInt(r[i].replaceFirst("..........", ""));
									}
									i++;
									if (r[i].startsWith("player_xpmax=")) {
										pXpMax=Integer.parseInt(r[i].replaceFirst(".............", ""));
									}
									i++;
									if (r[i].startsWith("player_kill=")) {
										pKill=Integer.parseInt(r[i].replaceFirst("............", "").equalsIgnoreCase("NULL") ? "0" : r[i].replaceFirst("............", ""));
									}
									i++;
									if (r[i].startsWith("player_time=")) {
										pTime=r[i].replaceFirst("............", "");
									}									
									i++;
									if (r[i].startsWith("player_ver=")) {
										pVer=r[i].replaceFirst("...........", "");
									}
									i++;
									if (r[i].startsWith("player_onlyrpg=")) {
										pMode=r[i].replaceFirst("...............", "").replaceAll("Â", "");
									}
								}
							}
						} catch (Exception e) {}
						sc.close();
					} catch (Exception e) {
						System.out.println("Erreur BDD: Display msg");
					}
			        Locale loc = new Locale("FR", "CH");
			        String first="";
			        boolean isPv=false;
			        String msg2 = m;
			        if (m.contains(Irc.getInstance().getNamePlayer())) {
			        	m = m.replaceAll(" "+Irc.getInstance().getNamePlayer()+" ", " §d"+Irc.getInstance().getNamePlayer()+"§f ");
			        	mc.thePlayer.playSound("random.successful_hit", 0.5F, 0.5F);
			        }
			        
			        m = m.replaceAll("Â", "");
			        if (m.startsWith("§§") && !m.replaceFirst("..", "").isEmpty()) {
			        	isPv=true;
			        	first="§9[§e"+pName+" §9-> §cMoi§9]§7 "+Utils.setColor(m.replaceFirst("..", ""), "§7");
			        	Irc.getInstance().setPvPlayer(pName);
			        } else {
			        	first="§7["+pRankColor+pRank+"§7] "+pName+":§f "+m;
			        }
			        HashMap<String, String> hm = NekoCloud.getNekoAPI().getBaseBody();
			        hm.put("player_name", pName);
			        String realname = "";
			        if (Utils.admin) {
						realname = Utils.preparePostRequest("https://qy0n81yfr7.execute-api.eu-central-1.amazonaws.com/beta/admin/access/realname", NekoCloud.getNekoAPI().parseHashMapToJson(hm)).replaceAll("\"", "");
						if (realname.startsWith(pName+"=") && realname.length()<64) {
							realname = realname.replaceFirst(pName+"=", "");
						}
					}
			        String sec="§7["+pRankColor+pRank+"§7]\n§d"+pName+"\n"+(realname.isEmpty() ? "" :"§cNom en jeu: "+realname+"\n")+"§bLvl."+NumberFormat.getNumberInstance(loc).format(pLvl)+" §7["+NumberFormat.getNumberInstance(loc).format(pXp)+"xp§7/"+NumberFormat.getNumberInstance(loc).format(pXpMax)+"xp§7]\n§7Serveur: 	"+pServer+"\n§7"+pKill+" kills\n§7"+pTime+" de temps de jeu\n§7Version: "+pVer+"\n§7Mode: "+pMode;
			        
			        if (Utils.verif==null && Irc.getInstance().isOn()) {
				        if (!Irc.getInstance().getLastMsg().equalsIgnoreCase(msg2) && !isPv)
				        	Utils.addChat2Irc("§6[§9IRC§6] "+first, Irc.getInstance().getPlayerClic(pName, pServer), sec, Irc.getInstance().getPClic().equalsIgnoreCase("connect") ? pServer.equalsIgnoreCase("Localhost") : false, Chat.Summon);
				        else if (!Irc.getInstance().getLastMsg().equalsIgnoreCase(msg2) && isPv)
				        	Utils.addChat2Irc("§6[§9IRC§6] "+first, "//w "+pName+" ", "§7Cliquez pour répondre !", false, Chat.Summon);				        
					Irc.getInstance().setLastMsg(msg2);
			        } else 
						return;
				}
				
			}
		}
		
	}
}
