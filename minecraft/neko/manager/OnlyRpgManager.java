package neko.manager;

import java.util.ArrayList;

import neko.module.Module;
import net.minecraft.client.Minecraft;

public class OnlyRpgManager {
	private Minecraft mc = Minecraft.getMinecraft();
	private static OnlyRpgManager instance = null;
	private boolean active;
	private int timeSec;
	private ArrayList<String> cmd = new ArrayList<String>();
	private ArrayList<String> Cheat = new ArrayList<String>();
	
	public static OnlyRpgManager getRpg() {
		if (instance==null)
			instance = new OnlyRpgManager();
		return instance;
	}
	
	public OnlyRpgManager() {
		Cheat.add("NekoChat");
		Cheat.add("Paint");
		Cheat.add("HUD");
		Cheat.add("WorldTime");
		Cheat.add("Exploit");
		Cheat.add("Power");
		Cheat.add("Gui");
		//TODO: Faire tous les help après		
		// Autorise que le début des commandes
		cmd.add("myping");
		cmd.add("lag");
		cmd.add("myip");
		cmd.add("save");
		cmd.add("saveall");
		cmd.add("size"); // fov3
		cmd.add("clear");
		cmd.add("prefix");
		cmd.add("namemc");
		cmd.add("fov3");
		cmd.add("reset");
		cmd.add("disc");
		cmd.add("playsound");
		cmd.add("ip");
		cmd.add("update");
		cmd.add("bind");
		cmd.add("shutdown");
		cmd.add("fov");
		cmd.add("power");
		cmd.add("values");
		cmd.add("rm");
		cmd.add("rankmanager");
		cmd.add("trade");
		cmd.add("shop");
		cmd.add("event");
		cmd.add("detector");
		cmd.add("lvlup");
		cmd.add("reload");
		cmd.add("fps");
		cmd.add("worldtime");
		cmd.add("paint");
		cmd.add("hud");
		cmd.add("exploit");
		cmd.add("user");
		cmd.add("list");
		cmd.add("meteore");
		cmd.add("title");
		cmd.add("info");
		cmd.add("cmd");
		cmd.add("unbindall");
		cmd.add("irc");
		cmd.add("nyah");
		cmd.add("gl");
		cmd.add("classement");
		cmd.add("onlyrpg");
		cmd.add("say");
		cmd.add("gui");
		cmd.add("help");
		cmd.add("startevent");
		cmd.add("stopevent");
		cmd.add("v");
		cmd.add("nekochat");
		cmd.add("chat");
		this.timeSec = 0;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public int getTimeSec() {
		return timeSec;
	}
	
	public void addTimer() {
		this.timeSec++;
	}

	public void setTimeSec(int timeSec) {
		this.timeSec = timeSec;
	}

	public ArrayList<String> getCmd() {
		return cmd;
	}

	public void setCmd(ArrayList<String> cmd) {
		this.cmd = cmd;
	}

	public ArrayList<String> getCheat() {
		return Cheat;
	}

	public void setCheat(ArrayList<String> cheat) {
		Cheat = cheat;
	}		
	
	public String getTime() {
		String s = "";
		if (timeSec%3600>=0) {
			s+=timeSec/3600+"h ";
		}
		if (timeSec%60>=0) {
			if (timeSec%3600>=0) {
				s+=timeSec%3600/60+"min ";
			} else {
				s+=timeSec/60+"min ";
			}
		}		
		if (timeSec%3600%60>=0) {
			s+=timeSec%3600%60+"s ";
		} else if (timeSec%60>=0) {
			s+=timeSec%60+"s ";
		} else {
			s+=timeSec+"s ";
		}
		
		return s;
	}
	
}
