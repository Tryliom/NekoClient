package neko.module.other;

import neko.Client;
import neko.module.Category;
import neko.module.Module;
import neko.utils.Utils;

public class Conditions extends Module {	
	private String serv;
	private int sec;
	private double bonus;
	private boolean isActive=false;
	private String activeServer;
	private int count=0;
	private static Conditions instance=null;
	Client var = Client.getNeko();
	
	public Conditions() {
		super("Conditions", -1, Category.HIDE);
		if (instance==null) 
			instance=this;
	}
	
	public static Conditions getInstance() {
		return instance;
	}
	
	public void newActif(String serv, int sec, double bonus) {
		this.serv=serv;
		this.sec=sec;
		this.bonus=bonus;
		this.isActive=true;
	}
	
	public void onAction() {
		if (count>=20 && this.isActive) {
			count=0;
			if (!mc.isSingleplayer()) {
				if (mc.getCurrentServerData().serverIP.equalsIgnoreCase(this.serv) && !this.serv.equalsIgnoreCase(this.activeServer)) {
					var.tempBonus=this.bonus;
					TempBon t = new TempBon(sec);
					this.activeServer=this.serv;
					if (this.bonus>0)
						u.addChat("§aBonus cadeau de §d"+Math.round(bonus)+"% §aajouté :3 !");
					else 
						u.addChat("§cMalus cadeau de §d"+Math.round(bonus)+"% §cajouté >:3 !");
				}
			}
			
			
			
		} else
			count++;
		
	}
	
}
