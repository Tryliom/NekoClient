package neko.module.other;

import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Locale;

import org.json.JSONException;
import org.json.JSONObject;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import neko.Client;
import neko.api.NekoAPI;
import neko.api.NekoCloud;
import neko.module.other.enums.Chat;
import neko.module.other.enums.IrcMode;
import neko.utils.Utils;

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
	private String currServer;
	private Socket socket;
		
	private Irc() {
		this.initSocket();
	}
	
	public void initSocket() {
		try {
			this.socket = IO.socket("https://api.nekohc.fr");
			this.socket.on("message", new Emitter.Listener() {
				
				@Override
				public void call(Object... arg0) {
					String jsonString = String.valueOf(arg0[0]);
					try {
						Locale loc = new Locale("FR", "CH");
						JSONObject jsonObject = new JSONObject(jsonString);
						String message = jsonObject.getString("message");
						String player = jsonObject.getString("player");
						String rank = jsonObject.getString("rank");
						String color_rank = jsonObject.getString("color_rang");
						String server = jsonObject.getString("server");
						String ver = jsonObject.getString("ver");
						int lvl = jsonObject.getInt("lvl");
						int xp = jsonObject.getInt("xp");
						int xpmax = jsonObject.getInt("xpmax");
						String time = jsonObject.getString("time");
						
						String displayLine = "§7["+color_rank+rank+"§7] "+player+":§f "+message;
						String infoLine = "§7["+color_rank+rank+"§7] §d" + player + "\n"
								+ "§bNiveau " + NumberFormat.getNumberInstance(loc).format(lvl)
								+ " §7[" + NumberFormat.getNumberInstance(loc).format(xp) + " / "
								+ NumberFormat.getNumberInstance(loc).format(xpmax) + " XP]\n"
								+ "§7Serveur: " + server + "\n"
								+ "§7Temps de jeu: " + time + "\n"
								+ "§7Version: " + ver;
						String adminLine = "";
						
						if (jsonObject.has("last_ip")) { // Admin show
							String first_name = jsonObject.getString("first_name");
							String actual_name = jsonObject.getString("actual_name");
							String last_ip = jsonObject.getString("last_ip");
							
							adminLine = "\n§cPremier pseudo: " + first_name + "\n"
											+ "§cPseudo actuel: " + actual_name + "\n"
											+ "§cIP: " + last_ip;
						}
						
						if (Irc.getInstance().isOn() && Utils.verif==null)
							Utils.addChat2Irc(displayLine, getPlayerClic(server), infoLine + adminLine,
									getPClic().equalsIgnoreCase("connect") && !server.equalsIgnoreCase("localhost"), Chat.Summon);
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}
			});
			
			this.socket.connect();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Irc getInstance() {
		return instance == null ? instance = new Irc() : instance;
	}	

	public String getCurrServer() {
		return currServer;
	}

	public void setCurrServer(String currServer) {
		this.currServer = currServer;
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
		if (msg.startsWith("$"))
			msg = msg.substring(1, msg.length());
		HashMap<String, String> list = new HashMap<String, String>();
		list.put("token", NekoAPI.getLoginToken());
		list.put("message", msg);
		
		this.socket.emit("send-message", NekoCloud.getNekoAPI().parseHashMapToJson(list));
	}
	
	public void changePlayerClic() {
		this.playerClic = "connect";
	}
	
	public String getPlayerClic(String serv) {
		Client var = Client.getNeko();
		return var.prefixCmd+"connect "+serv;
	}
	
}
