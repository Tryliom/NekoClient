package neko.module.other;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.Timer;

import neko.utils.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.play.server.S38PacketPlayerListItem.AddPlayerData;

public class HackerDetector {
	public static javax.swing.Timer speed = new Timer(1000, new Speed());	
	public static ArrayList<Joueur> listSpeed = new ArrayList<>();
	public static boolean voirAlert=false;
	public static boolean isOn=false;
	
	
	public static void setDetector() {
		if (isOn) {
			speed.stop();
			listSpeed.clear();
			isOn=false;
		} else {
			speed.start();
			isOn=true;
		}
	}			
}

class Speed implements ActionListener {
	Minecraft mc = Minecraft.getMinecraft();
	@Override
	public void actionPerformed(ActionEvent arg0) {					
		
		for (Object o : mc.theWorld.playerEntities) {
			EntityPlayer en = (EntityPlayer) o;
			if (en==mc.thePlayer)
				return;
			boolean t=false;
			for (Joueur j : HackerDetector.listSpeed) {
				if (en==j.getPlayer()) {
					t=true;					
				}
			}
			DecimalFormat formatter = new DecimalFormat("#0.000");
			if (t) {
				for (Joueur j : HackerDetector.listSpeed) {
					if (en==j.getPlayer()) {
						j.setPlayer(en);		
						if (j.isHack()) {
							j.addAlert();		
							if (HackerDetector.voirAlert)
								Utils.addChat("§cAlert: §7 "+j.getPlayer().getName()+" use speed ! ("+formatter.format(j.getSpeed())+")");
						}
						if (j.getAlert()>=7) {
							Utils.addChat("§4>>> §c"+j.getPlayer().getName()+" est trop rapide §4<<<");
							j.resetAlert();
							j.setCount(0);
						}
						
						j.setLastPosX(en.posX);
						j.setLastPosZ(en.posZ);
						
						j.addCount();
						if (j.getCount()>=20) {
							j.setCount(0);
							j.resetAlert();
						}
					}
				}
			} else {
				HackerDetector.listSpeed.add(new Joueur(en, en.posX, en.posZ));
			}
			
		}
		
	}
	
}