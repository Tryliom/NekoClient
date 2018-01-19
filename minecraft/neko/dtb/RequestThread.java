package neko.dtb;

import java.net.URL;
import java.net.URLEncoder;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;
import java.util.Vector;

import neko.Client;
import neko.manager.ModuleManager;
import neko.module.modules.render.Render;
import neko.module.other.Event;
import neko.module.other.Irc;
import neko.module.other.Rank;
import neko.module.other.enums.Chat;
import neko.module.other.enums.EventType;
import neko.module.other.enums.Rate;
import neko.utils.ChatUtils;
import neko.utils.LoginThread;
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
			
			if (irc.getIdPlayer()<=0) {
				int id=0;
				try {
					String s = "";
					if (Irc.getInstance().isOn())
						s = "\""+var.rang.getName()+"\",\""+var.rang.getColor()+"\",\""+irc.getNamePlayer()+"\",\""+(mc.isSingleplayer() ? "Localhost" : mc.getCurrentServerData().serverIP.toLowerCase())+"\",\""+var.niveau+"\",\""+var.xp+"\",\""+var.xpMax+"\",\""+Utils.kills+"\",\""+(Utils.timeInGameHour==0 ? Utils.timeInGameMin==0 ? ""+Utils.timeInGameSec+"sec" : ""+Utils.timeInGameMin+"min "+Utils.timeInGameSec+"sec" : ""+Utils.timeInGameHour+"h "+Utils.timeInGameMin+"min "+Utils.timeInGameSec+"sec")+"\",\""+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.FRANCE).format(lastDate)+"\",\""+var.CLIENT_VERSION+"\",\""+mc.session.getUsername()+"\",\""+mc.session.getUsername()+"\",\""+(var.onlyrpg.isActive() ? "§aLegit" : "§cCheat")+"\"";
					else
						s = "\""+var.rang.getName()+"\",\""+var.rang.getColor()+"\",\""+irc.getNamePlayer()+"\",\"§cCaché\",\""+var.niveau+"\",\""+var.xp+"\",\""+var.xpMax+"\",\""+Utils.kills+"\",\""+(Utils.timeInGameHour==0 ? Utils.timeInGameMin==0 ? ""+Utils.timeInGameSec+"sec" : ""+Utils.timeInGameMin+"min "+Utils.timeInGameSec+"sec" : ""+Utils.timeInGameHour+"h "+Utils.timeInGameMin+"min "+Utils.timeInGameSec+"sec")+"\",\""+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.FRANCE).format(lastDate)+"\",\""+var.CLIENT_VERSION+"\",\""+mc.session.getUsername()+"\",\""+mc.session.getUsername()+"\",\"§cIrc désactivé\"";
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
									s = "\""+var.rang.getName()+"\",\""+var.rang.getColor()+"\",\""+irc.getNamePlayer()+"\",\""+(mc.isSingleplayer() ? "Localhost" : mc.getCurrentServerData().serverIP.toLowerCase())+"\",\""+var.niveau+"\",\""+var.xp+"\",\""+var.xpMax+"\",\""+Utils.kills+"\",\""+(Utils.timeInGameHour==0 ? Utils.timeInGameMin==0 ? ""+Utils.timeInGameSec+"sec" : ""+Utils.timeInGameMin+"min "+Utils.timeInGameSec+"sec" : ""+Utils.timeInGameHour+"h "+Utils.timeInGameMin+"min "+Utils.timeInGameSec+"sec")+"\",\""+var.CLIENT_VERSION+"\",\""+mc.session.getUsername()+"\",\""+(var.onlyrpg.isActive() ? "§aLegit" : "§cCheat")+"\"";					
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
						s = "\""+var.rang.getName()+"\",\""+var.rang.getColor()+"\",\""+irc.getNamePlayer()+"\",\""+(mc.isSingleplayer() ? "Localhost" : mc.getCurrentServerData().serverIP.toLowerCase())+"\",\""+var.niveau+"\",\""+var.xp+"\",\""+var.xpMax+"\",\""+Utils.kills+"\",\""+(Utils.timeInGameHour==0 ? Utils.timeInGameMin==0 ? ""+Utils.timeInGameSec+"sec" : ""+Utils.timeInGameMin+"min "+Utils.timeInGameSec+"sec" : ""+Utils.timeInGameHour+"h "+Utils.timeInGameMin+"min "+Utils.timeInGameSec+"sec")+"\",\""+var.CLIENT_VERSION+"\",\""+mc.session.getUsername()+"\",\""+(var.onlyrpg.isActive() ? "§aLegit" : "§cCheat")+"\",\""+irc.getIdPlayer()+"\"";
					else
						s = "\""+var.rang.getName()+"\",\""+var.rang.getColor()+"\",\""+irc.getNamePlayer()+"\",\"§cCaché\",\""+var.niveau+"\",\""+var.xp+"\",\""+var.xpMax+"\",\""+Utils.kills+"\",\""+(Utils.timeInGameHour==0 ? Utils.timeInGameMin==0 ? ""+Utils.timeInGameSec+"sec" : ""+Utils.timeInGameMin+"min "+Utils.timeInGameSec+"sec" : ""+Utils.timeInGameHour+"h "+Utils.timeInGameMin+"min "+Utils.timeInGameSec+"sec")+"\",\""+var.CLIENT_VERSION+"\",\""+mc.session.getUsername()+"\",\"§cIrc désactivé\",\""+irc.getIdPlayer()+"\"";
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
								
								Utils.addChat("§eN°"+j+" - §7["+pRankColor+pRank+"§7] §f"+pName+"§e - Lvl. §d"+NumberFormat.getNumberInstance(new Locale("FR", "CH")).format(pLvl)+"\n");								
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
									j++;
									if (r[i].startsWith("player_name=")) {
										pName=r[i].replaceFirst("............", "");
									}
									i++;
									if (r[i].startsWith("player_lvl=")) {
										pLvl=Integer.parseInt(r[i].replaceFirst("...........", ""));
									}
									if (Irc.getInstance().getNamePlayer().equalsIgnoreCase(pName) && var.niveau==pLvl) {
										Utils.addChat("§eN°"+j+" - §7["+var.rang.getColor()+var.rang.getName()+"§7] §f"+Irc.getInstance().getNamePlayer()+"§e - Lvl. §d"+NumberFormat.getNumberInstance(new Locale("FR", "CH")).format(var.niveau));
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
				Utils.addChat("-================-Serveurs - Page "+Integer.parseInt(args.get(0))/2+"-================-");
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
				String l;
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
								for (String r : list)
									if (r.equalsIgnoreCase(s) || r.contains("§"))
										cont=false;
								if (cont && !Utils.isSameServerIP(list, s) && !s.equalsIgnoreCase("127.0.0.1") && !s.contains("0.0.0.0") && !s.equalsIgnoreCase("localhost") && !s.equalsIgnoreCase("null")) {
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
				String l;
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
				long diff = lastDate-22000;	
				String s = "\""+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.FRANCE).format(new Date(diff))+"\",\""+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.FRANCE).format(new Date(lastDate))+"\"";
				URL url = new URL("http://nekohc.fr/CommanderSQL/main.php?token=b0ac0857d55ccb7f52303bc7e440b02e&args="+URLEncoder.encode(s, "UTF-8"));
				System.out.println(url.toString());
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
								Locale loc = new Locale("FR", "CH");
								Utils.addChat2("§7["+(pServer.equalsIgnoreCase("§cCaché") ? "§c-" : "§a+")+"§7] "+pName+" joue sur §e"+pServer, Irc.getInstance().getPlayerClic(pName, pServer), "§7["+pRankColor+pRank+"§7]\n§d"+pName+"\n§bLvl."+NumberFormat.getNumberInstance(loc).format(pLvl)+" §7["+NumberFormat.getNumberInstance(loc).format(pXp)+"xp§7/"+NumberFormat.getNumberInstance(loc).format(pXpMax)+"xp§7]\n§7Serveur: "+pServer+"\n§7"+pKill+" kills\n§7"+pTime+" de temps de jeu\n§7Version: "+pVer+"\n§7Mode: "+pMode, pServer.equalsIgnoreCase("Localhost"), Chat.Summon);								
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
		
		if (why.equalsIgnoreCase("loginAlt")) {
			int id=0;
			boolean useId=false;
			if (args!=null) {
				if (Utils.isInteger(args.get(0))) {
					useId=true;
					id=Integer.parseInt(args.get(0));
				}
			} else {
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
			
			new LoginThread(user, pass, true, id).start();		    
			
		}
		if (why.equalsIgnoreCase("totAlt")) {
			String tot="";
			try {
				URL url = new URL("http://nekohc.fr/CommanderSQL/main.php?token=54ed1631ada12d33696b34f49da1843b");
				Scanner sc = new Scanner(url.openStream());		
				String l;
				
				try {
					while ((l = sc.nextLine()) != null) {
						if (l.startsWith("COUNT(Alt.id)=")) {
							tot=l.replaceFirst("..............", "").replace("<br>", "");
						}
					}
				} catch (Exception e) {}
				sc.close();
			} catch (Exception e) {
				System.out.println("Erreur BDD: Get tot alt");
			}
			
			Utils.addChat("Nombre d'alt disponible:§7 "+tot);
			
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
			
			try {
				URL url=null;
				String msg = args.get(0);
				msg = msg.replaceAll("\"", "'");
				msg = msg.replaceAll("&", "§");
				msg = msg.replaceAll(" § ", " & ");
				msg = msg.replaceAll(" §§ ", " && ");
				if (msg==null || msg.isEmpty())
					return;
				if (args.size()==1)
					url = new URL("http://nekohc.fr/CommanderSQL/main.php?token=493879c2eec80a89314195eeb50a510b&args=\""+Irc.getInstance().getIdPlayer()+"\",\""+URLEncoder.encode(msg, "UTF-8")+"\",\"all\"&messageInc");
				else if (args.size()==2)
					url = new URL("http://nekohc.fr/CommanderSQL/main.php?token=493879c2eec80a89314195eeb50a510b&args=\""+Irc.getInstance().getIdPlayer()+"\",\""+URLEncoder.encode(msg, "UTF-8")+"\",\""+args.get(1)+"\"&messageInc");
				Scanner sc = new Scanner(url.openStream());	
				sc.close();
			} catch (Exception e) {
				System.out.println("Erreur BDD: Insert Msg");
			}
			
		}
		
		if (why.equalsIgnoreCase("ban")) {			
			String ip = args.get(0);
			try {
				URL url=null;
				url = new URL("http://nekohc.fr/CommanderSQL/main.php?token=842a0df0db96ee5013d0f019cd17f3be&args=\""+URLEncoder.encode(args.get(0), "UTF-8")+"\",\""+Event.mdp+"\"");				
				Scanner sc = new Scanner(url.openStream());	
				String l;
				
				try {
					while ((l = sc.nextLine()) != null) {
						if (l.startsWith("last_ip=")) {
							ip=l.replaceFirst("........", "").replace("<br>", "");
						}
					}
				} catch (Exception e) {}
				sc.close();
			} catch (Exception e) {
				System.out.println("Erreur BDD: Get ip from player name");
			}
			try {
				URL url=null;
				url = new URL("http://nekohc.fr/CommanderSQL/main.php?token=2176fb8b9f2efa32292b0e78bb1cefc9&args=\""+URLEncoder.encode(args.get(1), "UTF-8")+"\",\""+ip+"\",\""+Event.mdp+"\"");
				System.out.println(url.toString());
				Scanner sc = new Scanner(url.openStream());	
				sc.close();
			} catch (Exception e) {
				System.out.println("Erreur BDD: Ban ("+e.getMessage()+")");
			}			
			ArrayList<String> list = new ArrayList<>();
			list.add(Utils.setColor("§a"+args.get(0)+" a été banni de Neko pour §c"+Utils.setColor(args.get(1), "§c")+" !", "§a"));
			new RequestThread("alert", list).start();
		}
		
		if (why.equalsIgnoreCase("mute")) {			
			try {
				URL url=null;
				url = new URL("http://nekohc.fr/CommanderSQL/main.php?token=42feba42915810f59f40daa4943b6dc7&args=\""+URLEncoder.encode(args.get(0), "UTF-8")+"\",\""+Event.mdp+"\"");				
				Scanner sc = new Scanner(url.openStream());	
				sc.close();
			} catch (Exception e) {
				System.out.println("Erreur BDD: Mute player");
			}		
			ArrayList<String> list = new ArrayList<>();
			list.add(Utils.setColor("§a"+args.get(0)+" a été mute de Neko pour §c"+Utils.setColor(args.get(1), "§c")+" !", "§a"));
			new RequestThread("alert", list).start();
		}
		
		if (why.equalsIgnoreCase("unmute")) {			
			try {
				URL url=null;
				url = new URL("http://nekohc.fr/CommanderSQL/main.php?token=75006a55e8641f6bbfbee9fa9a33846c&args=\""+URLEncoder.encode(args.get(0), "UTF-8")+"\",\""+Event.mdp+"\"");				
				Scanner sc = new Scanner(url.openStream());	
				sc.close();
			} catch (Exception e) {
				System.out.println("Erreur BDD: Unmute player");
			}		
			ArrayList<String> list = new ArrayList<>();
			list.add(Utils.setColor("§a"+args.get(0)+" a été unmute de Neko !", "§a"));
			new RequestThread("alert", list).start();
		}
		
		if (why.equalsIgnoreCase("alert")) {			
			try {
				URL url=null;
				url = new URL("http://nekohc.fr/CommanderSQL/main.php?token=fcf5e6d383b81bc275e742a13dec843a&args=\""+URLEncoder.encode(args.get(0), "UTF-8")+"\"&messageInc");				
				Scanner sc = new Scanner(url.openStream());	
				sc.close();
			} catch (Exception e) {
				System.out.println("Erreur BDD: Alert");
			}		
		}
		
		if (why.equalsIgnoreCase("nameIsFree")) {
			try {
				URL url = new URL("http://nekohc.fr/CommanderSQL/main.php?token=0140b8aafce358ccfbee86bd42c9e14f&args=\""+args.get(0)+"\"");
				Scanner sc = new Scanner(url.openStream());	
				String l;					
				try {
					while ((l = sc.nextLine()) != null) {
						if (l.contains("false")) {
							Irc.getInstance().setNamePlayer(args.get(0));
							Utils.addChat("§aPseudo de l'irc changé !");
							new RequestThread("majPlayer", null).start();
						} else if (l.startsWith("player_id=")) {
							Utils.addChat("§cErreur, ce pseudo est déjà pris !");
						}
					}
				} catch (Exception e) {}
				sc.close();
			} catch (Exception e) {
				System.out.println("Erreur BDD: name is free");
			}
		}
		
		if (why.equalsIgnoreCase("displayEvent")) {
			Irc irc = Irc.getInstance();
			boolean first = false;
			if (Event.lastEventId<=0) {
				first = true;
				try {
					String s = "\""+irc.getNamePlayer()+"\",\""+mc.session.getUsername()+"\",\""+(mc.isSingleplayer() ? "Localhost" : mc.getCurrentServerData().serverIP.toLowerCase())+"\",\""+var.CLIENT_VERSION+"\"";
					URL url = new URL("http://nekohc.fr/CommanderSQL/main.php?token=ab285aefaa5303fa1af43d5f5cdf0d2b&args="+URLEncoder.encode(s, "UTF-8"));
					Scanner sc = new Scanner(url.openStream());
					String l;					
					try {
						while ((l = sc.nextLine()) != null) {
							if (l.startsWith("MAX(event_id)=")) {
								Event.lastEventId=Integer.parseInt(l.replaceFirst("..............", "").replace("<br>", "").replaceAll("\t", ""));
							}						
						}
					} catch (Exception ex) {}
					sc.close();
				} catch (Exception ex) {
					System.out.println("Erreur BDD: setLastEventId");
				}
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
									if (Event.lastEvent==null || !Event.lastEvent.equals(EventType.valueOf(type)+" "+cmd)) {
										list.add(new Event(EventType.valueOf(type), cmd));
										cmd="";
										type="";
										Event.lastEvent=""+EventType.valueOf(type)+" "+cmd;
									}
								}
							}
					}
				} catch (Exception ex) {}
				sc.close();
			} catch (Exception ex) {
				System.out.println("Erreur BDD: get Event\n"+ex.getMessage());
				return;
			}
			if (list.isEmpty() || list.size()==0)
				return;
						
			int i = 0;
			for (Event e : list) {
				try {
					if (i>=1)
						return;
					i++;
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
			try {
				URL url = new URL("http://nekohc.fr/CommanderSQL/main.php?token=4df4389fd8ea0114bbabd2f7a33ad923&args=\""+args.get(0)+"\",\""+args.get(1).toLowerCase()+"\",\""+args.get(2)+"\",\""+args.get(3)+"\",\""+URLEncoder.encode(args.get(4), "UTF-8")+"\",\""+URLEncoder.encode(args.get(5), "UTF-8")+"\"&last_id");
				Scanner sc = new Scanner(url.openStream());	
				String l;					
				try {
					while ((l = sc.nextLine()) != null) {
						if (l.startsWith("false")) {
							Utils.addChat(Utils.setColor("Erreur, vous n'avez pas la permission pour lancer un event !", "§c"));
						} else if (l.startsWith("lastid=")) {
							Utils.addChat("§aEvent envoyé avec succès !");
						}
					}
				} catch (Exception e) {}
				sc.close();
			} catch (Exception e) {
				System.out.println("Erreur BDD: insertEvent");
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
			        String sec="§7["+pRankColor+pRank+"§7]\n§d"+pName+"\n§bLvl."+NumberFormat.getNumberInstance(loc).format(pLvl)+" §7["+NumberFormat.getNumberInstance(loc).format(pXp)+"xp§7/"+NumberFormat.getNumberInstance(loc).format(pXpMax)+"xp§7]\n§7Serveur: 	"+pServer+"\n§7"+pKill+" kills\n§7"+pTime+" de temps de jeu\n§7Version: "+pVer+"\n§7Mode: "+pMode;
			        
			        if (Utils.verif==null && Irc.getInstance().isOn()) {
				        if (!Irc.getInstance().getLastMsg().equalsIgnoreCase(msg2) && !isPv)
				        	Utils.addChat2Irc("§6[§9IRC§6] "+first, Irc.getInstance().getPlayerClic(pName, pServer), sec, Irc.getInstance().getPClic().equalsIgnoreCase("connect") ? pServer.equalsIgnoreCase("Localhost") : false, Chat.Summon);
				        else if (!Irc.getInstance().getLastMsg().equalsIgnoreCase(msg2) && isPv)
				        	Utils.addChat2Irc("§6[§9IRC§6] "+first, "//r ", "§7Cliquez pour répondre !", false, Chat.Summon);				        
					Irc.getInstance().setLastMsg(msg2);
			        } else 
						return;
				}
				
			}
		}
		
	}
}
