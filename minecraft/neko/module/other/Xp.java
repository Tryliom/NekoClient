package neko.module.other;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import neko.Client;
import neko.lock.Lock;
import neko.module.ModuleManager;
import neko.module.modules.player.Fire;
import neko.utils.Utils;
import net.minecraft.client.Minecraft;

public class Xp {
	public static int xp=0;
	public static Timer t = new Timer(5, new checkXp());
	public static int over3000=0;
	
	
	public Xp(int xp) {
		this.xp+=xp;
		if (this.xp<3000) {
			try {
				t.setDelay(3000/this.xp);	
				over3000=0;
			} catch (Exception e) {}
		} else {
			t.setDelay(1);
			over3000=this.xp/3000;
		}
		if (!t.isRunning())
			t.start();
	}
	
}

class checkXp implements ActionListener {
	Minecraft mc = Minecraft.getMinecraft();
	Client var = Client.getNeko();
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (Xp.xp>0) {
			if (Xp.over3000!=0) {
				var.xp+=Xp.over3000;
				Xp.xp-=Xp.over3000;
			} else {
				var.xp++;
				Xp.xp--;
			}			
			
			if (var.xp>=var.xpMax) {
				//Animation et sons
				Utils.lvl=0;
				if (var.animation)
					Utils.t.start();
				if (var.animation)
					Fire.onLvl=true;
				var.niveau+=1;
				if (Math.random()<0.9) {
					int coin = Utils.getRandInt(30);
					var.ame+=coin;			
					if (coin!=0)
						Utils.addChat("§c+"+coin+" soul"+(coin==1 ? "" : "s"));
				} else {
					Utils.addChat("§c+ 1 ticket de lotterie !");
					var.lot++;
				}
				if (Utils.verif==null) {
					Utils.addChat("§6=§b-§6=§b-§6=§b-§6=§b-§6=§b-§6=§b-§6=§b-§6=§b-§6=§b-§6=");
					Utils.addChat("§dNiveau "+var.niveau+" atteint !");			
					var.xp-=var.xpMax;
					var.xpMax+=Utils.getRandInt(var.niveau*80);
					for (Lock lock : ModuleManager.Lock) {
						if (lock.getUnit().equalsIgnoreCase("Lvl") && lock.getCout()==var.niveau && lock.isLock()) {
							Utils.unlock(lock.getName());
							Utils.addChat("§d"+lock.getType()+" "+lock.getName().replaceAll("--", var.prefixCmd)+" débloqué"+(lock.getType().equalsIgnoreCase("Commande") ? "e" : "" )+" !");
						}
					}
					for (int i=25;i<700;i+=25)
						if (var.niveau==i) {
							Utils.addChat("§5Augmentation de chance d'apparition de §dbonus§5 :3");
							var.chance+=0.0001;
							Utils.addChat("§6=§b-§6=§b-§6=§b-§6=§b-§6=§b-§6=§b-§6=§b-§6=§b-§6=§b-§6=");
						}				
					if (var.niveau==3) {
						Utils.addChat("Nyah nyah nyah :3");
						Utils.addChat("§6=§b-§6=§b-§6=§b-§6=§b-§6=§b-§6=§b-§6=§b-§6=§b-§6=§b-§6=");
					} else if (var.niveau==4) {
						Utils.addChat("Nyah nyah nyah >:3");
						Utils.addChat("§6=§b-§6=§b-§6=§b-§6=§b-§6=§b-§6=§b-§6=§b-§6=§b-§6=§b-§6=");
					} else if (var.niveau==5) {
						Utils.setRank("Petit Neko");
						Rank r = Utils.getRank("Petit Neko");
						Utils.addChat("§dRang de "+r.getColor()+r.getName()+"§d obtenu !");
						Utils.addChat("§6=§b-§6=§b-§6=§b-§6=§b-§6=§b-§6=§b-§6=§b-§6=§b-§6=§b-§6=");
					} else if (var.niveau==7) {
						Utils.addChat("§6=§b-§6=§b-§6=§b-§6=§b-§6=§b-§6=§b-§6=§b-§6=§b-§6=§b-§6=");
					} else if (var.niveau==10) {
						Utils.setRank("Apprenti Neko");
						Rank r = Utils.getRank("Apprenti Neko");
						Utils.addChat("§dRang de "+r.getColor()+r.getName()+"§d obtenu !");
						Utils.addChat("§6=§b-§6=§b-§6=§b-§6=§b-§6=§b-§6=§b-§6=§b-§6=§b-§6=§b-§6=");
					} else if (var.niveau==20) {
						Utils.setRank("Neko");
						Rank r = Utils.getRank("Neko");
						Utils.addChat("§dRang de "+r.getColor()+r.getName()+"§d obtenu !");
						Utils.addChat("§6=§b-§6=§b-§6=§b-§6=§b-§6=§b-§6=§b-§6=§b-§6=§b-§6=§b-§6=");
					} else if (var.niveau==30) {
						Utils.setRank("Neko Aguerri");
						Rank r = Utils.getRank("Neko Aguerri");
						Utils.addChat("§dRang de "+r.getColor()+r.getName()+"§d obtenu !");
						Utils.addChat("§6=§b-§6=§b-§6=§b-§6=§b-§6=§b-§6=§b-§6=§b-§6=§b-§6=§b-§6=");
					} else if (var.niveau==40) {
						Utils.setRank("Sorcier Neko");
						Rank r = Utils.getRank("Sorcier Neko");
						Utils.addChat("§dRang de "+r.getColor()+r.getName()+"§d obtenu !");
						Utils.addChat("§6=§b-§6=§b-§6=§b-§6=§b-§6=§b-§6=§b-§6=§b-§6=§b-§6=§b-§6=");
					} else if (var.niveau==50) {
						Utils.setRank("Neko Pervers");
						Rank r = Utils.getRank("Neko Pervers");
						Utils.addChat("§dRang de "+r.getColor()+r.getName()+"§d obtenu !");
						Utils.addChat("§6=§b-§6=§b-§6=§b-§6=§b-§6=§b-§6=§b-§6=§b-§6=§b-§6=§b-§6=");
					} else if (var.niveau==100) {
						Utils.setRank("Neko Vicieux");
						Rank r = Utils.getRank("Neko Vicieux");
						Utils.addChat("§dRang de "+r.getColor()+r.getName()+"§d obtenu !");
						Utils.addChat("§6=§b-§6=§b-§6=§b-§6=§b-§6=§b-§6=§b-§6=§b-§6=§b-§6=§b-§6=");
					} else if (var.niveau==150) {
						Utils.setRank("Neko Kawaii");
						Rank r = Utils.getRank("Neko Kawaii");
						Utils.addChat("§dRang de "+r.getColor()+r.getName()+"§d obtenu !");
						Utils.addChat("§6=§b-§6=§b-§6=§b-§6=§b-§6=§b-§6=§b-§6=§b-§6=§b-§6=§b-§6=");
					} else if (var.niveau==200) {
						Utils.setRank("Neko Suprême");
						Rank r = Utils.getRank("Neko Suprême");
						Utils.addChat("§dRang de "+r.getColor()+r.getName()+"§d obtenu !");
						Utils.addChat("§6=§b-§6=§b-§6=§b-§6=§b-§6=§b-§6=§b-§6=§b-§6=§b-§6=§b-§6=");
					} else if (var.niveau>=300 && Utils.getRank("Like A Cat").isLock()) {
						Utils.setRank("Like A Cat");
						Rank r = Utils.getRank("Like A Cat");
						Utils.addChat("§dRang de "+r.getColor()+r.getName()+"§d obtenu !");
						Utils.addChat("§6=§b-§6=§b-§6=§b-§6=§b-§6=§b-§6=§b-§6=§b-§6=§b-§6=§b-§6=");
					} else if (var.niveau>=400 && Utils.getRank("NyanCat").isLock()) {
						Utils.setRank("NyanCat");
						Rank r = Utils.getRank("NyanCat");
						Utils.addChat("§dRang de "+r.getColor()+r.getName()+"§d obtenu !");
						Utils.addChat("§6=§b-§6=§b-§6=§b-§6=§b-§6=§b-§6=§b-§6=§b-§6=§b-§6=§b-§6=");
					} else if (var.niveau>=500 && Utils.getRank("Colonel de la Neko Army").isLock()) {
						Utils.setRank("Colonel de la Neko Army");
						Rank r = Utils.getRank("Colonel de la Neko Army");
						Utils.addChat("§dRang de "+r.getColor()+r.getName()+"§d obtenu !");
						Utils.addChat("§6=§b-§6=§b-§6=§b-§6=§b-§6=§b-§6=§b-§6=§b-§6=§b-§6=§b-§6=");
					} else if (var.niveau>=750 && Utils.getRank("Clone d'un Léviathan").isLock()) {
						Utils.setRank("Clone d'un Léviathan");
						Rank r = Utils.getRank("Clone d'un Léviathan");
						Utils.addChat("§dRang de "+r.getColor()+r.getName()+"§d obtenu !");
						Utils.addChat("§6=§b-§6=§b-§6=§b-§6=§b-§6=§b-§6=§b-§6=§b-§6=§b-§6=§b-§6=");
					} else if (var.niveau>=1000 && Utils.getRank("Homme de lettres").isLock()) {
						Utils.setRank("Homme de lettres");
						Rank r = Utils.getRank("Homme de lettres");
						Utils.addChat("§dRang de "+r.getColor()+r.getName()+"§d obtenu !");
						Utils.addChat("§6=§b-§6=§b-§6=§b-§6=§b-§6=§b-§6=§b-§6=§b-§6=§b-§6=§b-§6=");
					} else if (var.niveau>=2000 && Utils.getRank("Cannibal vicieux").isLock()) {
						Utils.setRank("Cannibal vicieux");
						Rank r = Utils.getRank("Cannibal vicieux");
						Utils.addChat("§dRang de "+r.getColor()+r.getName()+"§d obtenu !");
						Utils.addChat("§6=§b-§6=§b-§6=§b-§6=§b-§6=§b-§6=§b-§6=§b-§6=§b-§6=§b-§6=");
					} else if (var.niveau>=3000 && Utils.getRank("^>.<^").isLock()) {
						Utils.setRank("^>.<^");
						Rank r = Utils.getRank("^>.<^");
						Utils.addChat("§dRang de "+r.getColor()+r.getName()+"§d obtenu !");
						Utils.addChat("§6=§b-§6=§b-§6=§b-§6=§b-§6=§b-§6=§b-§6=§b-§6=§b-§6=§b-§6=");
					} else {
						Utils.addChat("§6=§b-§6=§b-§6=§b-§6=§b-§6=§b-§6=§b-§6=§b-§6=§b-§6=§b-§6=");
					}					
				}		
			}
		} else {
			if (var.animation)
				Fire.onLvl=false;
			Xp.t.stop();
		}
		}
	}
