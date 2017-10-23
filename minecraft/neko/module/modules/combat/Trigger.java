package neko.module.modules.combat;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import neko.module.Category;
import neko.module.Module;
import neko.module.modules.hide.Friends;
import neko.module.modules.special.FastDura;
import neko.module.modules.special.Nausicaah;
import neko.utils.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemSword;

public class Trigger extends Module {
	public static Minecraft mc = Minecraft.getMinecraft();
	public static float dist=3.8F;
	int delay=20;
	public static int cps=8;
	public static boolean random=false;
	public static javax.swing.Timer timer = new javax.swing.Timer(1000/cps, new attack());

	public Trigger() {
		super("Trigger", -1, Category.COMBAT);
	}
	
	public void onEnabled() {
		timer.start();
		super.onEnabled();
	}
	
	public void onDisabled() {
		timer.stop();
		super.onDisabled();
	}
	
	public void onUpdate() {
		if (random) {
    		int r = (int) Math.round(8-Math.random()*4);
    		timer.setDelay(1000/cps+r);
    	} else {
    		timer.setDelay(1000/cps);
    	}
    	if (!mc.isSingleplayer())
    		if (mc.getCurrentServerData().serverIP.equalsIgnoreCase("mc.erisium.com"))
		    	for (Object theObject : mc.theWorld.playerEntities) {
		            EntityLivingBase entity = (EntityLivingBase) theObject;
		            if ((entity.posY<0 || entity.posY>270) && !KillAura.bot.contains(entity.getName())) {
		            	KillAura.addBot(entity.getName());
		            }
		    	}
	}
	
	public static Boolean isViable(Entity en) {
    	if (mc.playerController.isSpectatorMode())
    	    return false;    	   	
    	
    	if(!(en.isEntityAlive() && !Friends.isFriend(en.getName()) && en!=mc.thePlayer))
    		return false;
    	 
    	if (!(mc.thePlayer.getDistanceToEntity(en) <= dist))
    		return false;
    	
    	if (KillAura.isBot(en.getName()))
    		return false;
    	
    	return true;
    }
	
	
}


class attack implements ActionListener {
	Minecraft mc = Minecraft.getMinecraft();
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (mc.pointedEntity!=null)
			if (Utils.sword ? mc.thePlayer.getCurrentEquippedItem()!=null ? mc.thePlayer.getCurrentEquippedItem().getItem() instanceof ItemSword : false : true) {
				Entity entity = mc.pointedEntity;
				if (Trigger.isViable(entity)) {              	                	                   
		        	
		        	if (Utils.isToggle("Crit"))
		        		Utils.crit();
		        		                			
		        		if (Reach.pvp && Trigger.dist>6) {
		        			try {
								Robot r = new Robot();
								r.mousePress(16);
								r.mouseRelease(16);
							} catch (AWTException e) {}
		        		} else if (Utils.isToggle("FastDura")) {
		    				FastDura.doDura(entity);
		    				mc.thePlayer.swingItem();
		    			} else if (Utils.isToggle("Nausicaah")) {
            				Nausicaah.getNausi().doNausicaah(entity);
            				mc.thePlayer.swingItem();
            			} else {
		    				try {
								Robot r = new Robot();
								r.mousePress(16);
								r.mouseRelease(16);
							} catch (AWTException e) {}
		    			}
				}
			}
	}
		
	}
