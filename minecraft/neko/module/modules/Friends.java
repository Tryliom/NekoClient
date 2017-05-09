package neko.module.modules;

import java.util.ArrayList;

import neko.module.Category;
import neko.module.Module;
import neko.utils.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.entity.player.EntityPlayer;

public class Friends extends Module {
	public static int delayT=0;	
	public int delay=0;	
	public static boolean remFr=false;
	public static boolean team=false;
	
	public static ArrayList<String> friend = new ArrayList<String>();
	
	public Friends() {
		super("Friends", -1, Category.HIDE);
	}
	
	public void onAction() {
		
		//Team
		delayT++;
		int il=0;
		if (team && delayT > 150 && u.verif==null)
			for (Object theObject : mc.theWorld.playerEntities) {
                EntityPlayer entity = (EntityPlayer) theObject; 	                          
                	
                    if(entity.isEntityAlive()) {
                    	if (mc.thePlayer.getTeam()==entity.getTeam() && !Friends.isFriend(entity.getName())) {
                    		u.addChat("§a"+entity.getName()+"§6 ajouté");
                    		il++;
                    	} else if (mc.thePlayer.getTeam()!=entity.getTeam() && Friends.isFriend(entity.getName())) {
                    		u.addChat("§c"+entity.getName()+"§6 supprimé");
                    		il++;
                    	}	    
                    	if (il!=0) {
                    		Friends.addFriend(entity.getName());
                    		il=0;
                    	}
                        continue;
                    }	                	            
	            delayT=0;
			}
		
		delay++;
		if (org.lwjgl.input.Mouse.isButtonDown(2) && delay > 20 && !(mc.currentScreen instanceof GuiChat) && !(mc.currentScreen instanceof GuiInventory))
			if (mc.pointedEntity!=null)
				if (u.isPlayer(mc.pointedEntity))
					if (isFriend(mc.pointedEntity.getName())) {
			    		for (int i=0;i<friend.size();i++) {
			    			if (friend.get(i).equals(mc.pointedEntity.getName())) {
			    				friend.remove(i);
			    			}
			    		}	                    	
			    		u.addChat("§c"+mc.pointedEntity.getName() + "§6 a été retiré de ta liste d'amis !");
			    		remFr=true;
			    		u.checkXp(1);
			    		remFr=false;
			    		delay=0;
			    	} else {
			        	friend.add(mc.pointedEntity.getName());
			        	u.addChat("§a"+mc.pointedEntity.getName() + "§6 a été ajouté à ta liste d'amis !");
			        	u.checkXp(1);
			        	delay=0;
			    	}
		
		
	}
	
	public static boolean isFriend(String name) {
		for (int i=0;i<friend.size();i++) {
			if (friend.get(i).equals(name)) {
				return true;
			}
		}
		return false;
	}
	
	public static void addFriend(String name) {
		for (int i=0;i<friend.size();i++) {
			if (friend.get(i).equals(name)) {
				friend.remove(i);
				return;
			}
		}
		friend.add(name);
		u.saveFriends();
	}

}
