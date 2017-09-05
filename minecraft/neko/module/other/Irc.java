package neko.module.other;

import java.util.ArrayList;

import neko.Client;
import neko.dtb.RequestThread;
import neko.utils.Utils;
import net.minecraft.client.Minecraft;

public class Irc {
	private static Irc instance;
	private String prefix="$";	
	private boolean isOn=true;
	private int idPlayer=0;
	private String namePlayer;
	private int lastId=-1;
	private String pvPlayer;
	private IrcMode mode=IrcMode.Normal;
	private boolean hideJl=false;
	private String lastMsg="";
	private String playerClic="connect";
		
	public Irc() {
		if (instance==null)
			instance=this;
	}
	
	public static Irc getInstance() {
		if (instance==null)
			instance=new Irc();
		return instance;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public boolean isOn() {
		return isOn;
	}

	public void setOn(boolean isOn) {
		this.isOn = isOn;
	}

	public int getIdPlayer() {
		return idPlayer;
	}

	public void setIdPlayer(int idPlayer) {
		this.idPlayer = idPlayer;
	}

	public String getNamePlayer() {
		return namePlayer;
	}

	public void setNamePlayer(String namePlayer) {
		this.namePlayer = namePlayer;
	}	
	
	public int getLastId() {
		return lastId;
	}

	public void setLastId(int lastId) {
		this.lastId = lastId;
	}	

	public String getPvPlayer() {
		return pvPlayer;
	}

	public void setPvPlayer(String pvPlayer) {
		this.pvPlayer = pvPlayer;
	}

	public String getLastMsg() {
		return lastMsg;
	}

	public void setLastMsg(String lastMsg) {
		this.lastMsg = lastMsg;
	}

	public boolean isHideJl() {
		return hideJl;
	}

	public void setHideJl(boolean hideJl) {
		this.hideJl = hideJl;
	}
	
	public IrcMode getMode() {
		return mode;
	}

	public void setMode(IrcMode mode) {
		this.mode = mode;
	}
	
	public String getPClic() {
		return this.playerClic;
	}	

	public void setPlayerClic(String playerClic) {
		this.playerClic = playerClic;
	}

	public void addChatIrc(String msg) {
		if (this.getNamePlayer().isEmpty() || this.idPlayer<=0) {
			Utils.addChat("§cErreur, veuillez choisir votre pseudo pour le chat !");
		} else {
			Client var = Client.getNeko();
			Minecraft mc = Minecraft.getMinecraft();
			String s="";					
			String player="";
			if (msg.startsWith(this.prefix)) {
				for (int i=0;i<this.prefix.length();i++)
					s+=".";
				msg = msg.replaceFirst(s, "");
			} 
			if (msg.startsWith("//r")) {
				msg = "§§"+msg.replaceFirst("...", "");
				player=this.pvPlayer;
			} else if (msg.startsWith("//w") || msg.startsWith("//m") || msg.startsWith("//msg")) {
				try {
					String r[] = msg.split(" ");
					player=r[1];
					this.setPvPlayer(player);
					s=".";
					for (int i=0;i<r[0].length();i++)
						s+=".";
					for (int i=0;i<r[1].length();i++)
						s+=".";
					msg = "§§"+msg.replaceFirst(s, "");
				} catch (Exception e) {
					Utils.addChat("§cErreur, utilisation correcte: //w <Player>");
				}
			}
			ArrayList<String> list = new ArrayList<>();
			if (msg.startsWith("&")) {
				String t[] = msg.split(" ");
				if (t[0].length()==2)
					msg = Utils.setColor(msg, t[0]);
			}
			list.add(msg);
			if (!player.isEmpty())
				list.add(player);
			new RequestThread("insertmsg", list).start();
			if (msg.startsWith("§§")) {
				Utils.toChat("§6[§9IRC§6]§9 [§cMoi §9-> §e"+player+"§9]§7 "+Utils.setColor(msg.replaceFirst("..", "").replaceAll("&", "§").replaceAll(" § ", " & "), "§7"));
			}
			Utils.saveAll();
		}
	}
	
	public void changePlayerClic() {
		if (this.playerClic.equalsIgnoreCase("connect")) {
			this.playerClic = "msg";
		} else {
			this.playerClic = "connect";
		}
	}
	
	public String getPlayerClic(String name, String serv) {
		String s = "";
		Client var = Client.getNeko();
		if (this.playerClic.equalsIgnoreCase("connect")) {
			s=var.prefixCmd+"connect "+serv;
		} else {
			s=(Irc.getInstance().getMode() == IrcMode.Normal ? Irc.getInstance().getPrefix() : "")+"//w "+name+" ";
		}
		return s;
	}
	
}
