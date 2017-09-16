package neko.module.modules;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import neko.dtb.Alt;
import neko.dtb.RequestThread;
import neko.gui.InGameGui;
import neko.module.Category;
import neko.module.Module;
import neko.module.other.Irc;
import neko.utils.Utils;

public class God extends Module {
	public static int count=0;
	int Tcount=0;
	int delay=0;
	private static God instance=null;
	private int killedBabyPigByFire = 0;
	private int killedChickenByFire = 0;
	private int killedHorseByFire = 0;
	private RequestThread currentMsg=null;
	private RequestThread currentEvent=null;
	private String backup = System.getenv("APPDATA") + "\\.minecraft\\NekoBackup\\";

	public God() {
		super("God", -1, Category.HIDE);
		instance=this;
	}
	
	public static God getInstance() {
		return instance;
	}
	
	public void onEnabled() {		
		if (u.display)
		u.addChat("§a§oGod activé !");
		super.onEnabled();
	}
	
	public void onDisabled() {
		if (u.display)
		u.addChat("§c§oGod désactivé !");
		super.onDisabled();
	}		
	
	public void onAction() {		
		if (new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()).equalsIgnoreCase("00:00:00"))
			Utils.ipVote.clear();
		if (u.isToggle("Timer")) {
			
			if (count>=Math.round(Timer.time)) {			
				u.timeInGameMs+=50;
				count=0;
			} else {
				count++;
			}
		} else {
			u.timeInGameMs+=50;
		}
		
		if (u.timeInGameSec%u.nyahSec==0 && delay>19) {
			if (u.nyah) {
				mc.thePlayer.sendChatMessage(u.nyahh + u.getNyah());
				delay=0;
			}
		} else
			delay++;
		try {
			if (u.timeInGameMs>=1000) {
				if (var.onlyrpg.isActive())
					var.onlyrpg.addTimer();
				u.timeInGameSec+=1;
				u.timeInGameMs=0;
				InGameGui.p=u.nbPack;
				u.nbPack=0;
				u.checkRang();
				if (Irc.getInstance().isOn() && u.verif==null && (currentMsg!=null ? !currentMsg.isAlive() : true)) {				
					currentMsg = new RequestThread("displaymsg", null);
					currentMsg.start();
				} else if (!Irc.getInstance().isOn() && (currentMsg!=null ? currentMsg.isAlive() : true)) {
					currentMsg.stop();
				}
				if (u.timeInGameSec%2==0 && u.verif==null && (currentEvent!=null ? !currentEvent.isAlive() : true)) {
					currentEvent = new RequestThread("displayEvent", null);
					currentEvent.start();
				} else if (u.verif!=null)
					currentEvent.stop();
				Alt b = Alt.getAlt();
				if (b.getAltTimer()>0)
					b.decrementAltTimer();
			}
		} catch (Exception e) {}
		
		if (u.timeInGameSec>=60) {
			u.timeInGameMin+=1;
			u.timeInGameSec=0;
			Utils.saveAll();
		}
		if (u.timeInGameMin>=60) {
			if (var.onlyrpg.isActive())
				var.bonus+=Utils.getRandInt(10);
			u.timeInGameHour+=1;
			if (u.timeInGameMin%10==0) {
				String s = Utils.linkSave;
				Utils.linkSave=this.backup;
				if (!new File(Utils.linkSave).exists())
					try {
						new File(Utils.linkSave).mkdirs();
					} catch (Exception e) {
						e.printStackTrace();
					}
				Utils.saveAll();
				Utils.linkSave=s;
			}
			u.timeInGameMin=0;
			
		}
		
		int rand = (int) Math.round(Math.random()*5000);
		
		if (u.timeInGameHour==1 && u.h1) {
			u.addChat("§dVous avez passés 1 heure en jeu !");
			u.checkXp(rand);
			u.h1=false;
		}
		
		if (u.timeInGameHour==10 && u.h10) {
			u.addChat("§dVous avez passés 10 heures en jeu !");
			u.checkXp(rand*2);
			u.h10=false;
		}
		
		if (u.timeInGameHour==50 && u.h50) {
			u.addChat("§dVous avez passés 50 heures en jeu !");
			u.checkXp(rand*3);
			u.h50=false;
		}
		
		if (u.timeInGameHour==100 && u.h100) {
			u.addChat("§dVous avez passés 100 heures en jeu !");
			u.checkXp(rand*4);
			u.h100=false;
		}
		
		if (u.timeInGameHour==200 && u.h200) {
			u.addChat("§dVous avez passés 200 heures en jeu !");
			u.checkXp(rand*5);
			u.h200=false;
		}
		
		if (u.timeInGameHour==666 && u.h666) {
			u.addChat("§dVous avez passés 666 heures en jeu !");
			u.checkXp(rand*30);
			u.h666=false;
		}
		
	}

	public String getBackup() {
		return backup;
	}

	public void setBackup(String backup) {
		this.backup = backup;
	}			
	
}
