package neko.module.modules.combat;

import neko.module.modules.combat.KillAura;
import neko.module.modules.hide.Friends;
import neko.module.modules.special.FastDura;
import neko.module.modules.special.Nausicaah;
import neko.utils.Utils;

import org.lwjgl.input.Keyboard;

import neko.Client;
import neko.module.Category;
import neko.module.Module;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemSword;
import net.minecraft.network.play.client.C02PacketUseEntity;
import net.minecraft.network.play.client.C02PacketUseEntity.Action;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.network.play.client.C03PacketPlayer.C04PacketPlayerPosition;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;

public class TpKill extends Module {
	private int delay=0;
	Minecraft mc = Minecraft.getMinecraft();
	public static int k=1;
	double entityPosX;				
	double entityPosY;
	double entityPosZ;	
	double lastX;
	double lastY;
	double lastZ;
	Client var = Client.getNeko();
	
	public TpKill() {
		super("TpKill", Keyboard.KEY_F, Category.Special);
		
	}
	
	public void onEnabled() {
		super.onEnabled();
	}
	
	public void onDisabled() {
		super.onDisabled();
	}
	
    public void onUpdate() {
		if (!mc.isSingleplayer())
    		if (mc.getCurrentServerData().serverIP.equalsIgnoreCase("mc.erisium.com"))
		    	for (Object theObject : mc.theWorld.playerEntities) {
		            EntityLivingBase entity = (EntityLivingBase) theObject;
		            if ((entity.posY<0 || entity.posY>270) && !KillAura.bot.contains(entity.getName())) {
		            	KillAura.addBot(entity.getName());
		            }
		    	}
		if (delay>=3) {
			if (u.sword ? mc.thePlayer.getCurrentEquippedItem()!=null ? mc.thePlayer.getCurrentEquippedItem().getItem() instanceof ItemSword : false : true) {
				delay=0;
				switch (var.mode) {
				case "Player" :
					for (Object theObject : mc.theWorld.playerEntities) {
		                EntityLivingBase entity = (EntityLivingBase) theObject;
		                if(isViable(entity)) {              	                	                   
		                	
		                	if (u.isToggle("Crit"))
		                		u.crit();
		                		                			
	                		Minecraft.getMinecraft().thePlayer.swingItem();
	                		Utils.attack(entity);                	
		                }
		        	}
					break;
					
				case "Mob" :
					if (u.warn) {
	        			if (u.isPlayerInRange(u.warnB))
	        				return;
	        		}
	        		for (Object theObject : mc.theWorld.loadedEntityList) {
	        			if (theObject instanceof EntityLivingBase) {
	                    	EntityLivingBase entity = (EntityLivingBase) theObject;
	                                                 
	                    	if(isViable(entity)) {              	                	                   
    	                	
    	                	if (u.isToggle("Crit"))
    	                		u.crit();
    	                		                			
                    		mc.thePlayer.swingItem();
                    		Utils.attack(entity);	                	
	    	                return;
	                    	}
	        			}     
	                }
					break;
					
				case "All" :
					for (Object theObject : mc.theWorld.loadedEntityList) {
	        			if (theObject instanceof EntityLivingBase) {
	                        EntityLivingBase entity = (EntityLivingBase) theObject;
	                       
	                        if(isViable(entity)) {              	                	                   
	    	                	
	    	                	if (u.isToggle("Crit"))
	    	                		u.crit();
    	                		                			
	                    		mc.thePlayer.swingItem();
	                    		Utils.attack(entity);
	                        }
	        			}
	                }
					break;
				}
			}
		} else {
			delay++;
		}
        
        
    }
    
    public String doTpAller(Entity en) {
		entityPosX = en.posX-mc.thePlayer.posX;				
		entityPosY = en.posY-mc.thePlayer.posY+1;
		entityPosZ = en.posZ-mc.thePlayer.posZ;
		
		lastX = mc.thePlayer.posX;
		lastY = mc.thePlayer.posY;
		lastZ = mc.thePlayer.posZ;
		for (k=1;u.verif(entityPosX, k) > 4;k++) {}
		for (;u.verif(entityPosY, k) > 4;k++) {}
		for (;u.verif(entityPosZ, k) > 4;k++) {} 
		
		if (entityPosY<0) {
			// On avance d'abord
			for (int j=0;j<k;j++) {          
        		Minecraft.getMinecraft().thePlayer.sendQueue.addToSendQueue(new C04PacketPlayerPosition(mc.thePlayer.posX+entityPosX/k, 
        				mc.thePlayer.posY, mc.thePlayer.posZ+entityPosZ/k, false));    
        		Minecraft.getMinecraft().thePlayer.setPosition(mc.thePlayer.posX+entityPosX/k, 
        				mc.thePlayer.posY, mc.thePlayer.posZ+entityPosZ/k); 
        	}	
			// Ensuite on descend
			for (int j=0;j<k;j++) {          
        		mc.thePlayer.fallDistance=0;
        		mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer(true));
        		Minecraft.getMinecraft().thePlayer.sendQueue.addToSendQueue(new C04PacketPlayerPosition(mc.thePlayer.posX, 
        				mc.thePlayer.posY+entityPosY/k, mc.thePlayer.posZ, false));    
        		Minecraft.getMinecraft().thePlayer.setPosition(mc.thePlayer.posX, 
        				mc.thePlayer.posY+entityPosY/k, mc.thePlayer.posZ); 
        	}
			return "down";
		} else if (entityPosY>0) {
			boolean marche=true;
			for (int i=1;entityPosY-i>0;i++) {
				BlockPos bl = new BlockPos(mc.thePlayer.posX, mc.thePlayer.posY+i, mc.thePlayer.posZ);
				if (!mc.theWorld.getBlockState(bl).getBlock().isBlockSolid(mc.theWorld, bl, EnumFacing.DOWN) || mc.theWorld.getBlockState(bl).getBlock().getMaterial() == Material.air || mc.theWorld.getBlockState(bl).getBlock().getMaterial() == Material.water || mc.theWorld.getBlockState(bl).getBlock().getMaterial() == Material.web || mc.theWorld.getBlockState(bl).getBlock().getMaterial() == Material.lava || mc.theWorld.getBlockState(bl).getBlock().getMaterial() == Material.portal || mc.theWorld.getBlockState(bl).getBlock().getMaterial() == Material.redstoneLight || mc.theWorld.getBlockState(bl).getBlock().getMaterial() == Material.vine) {
				} else {
					marche=false;
				}
			}
			if (marche) {
				// On monte d'abord
    			for (int j=0;j<k;j++) {          
            		Minecraft.getMinecraft().thePlayer.sendQueue.addToSendQueue(new C04PacketPlayerPosition(mc.thePlayer.posX, 
            				mc.thePlayer.posY+entityPosY/k, mc.thePlayer.posZ, false));    
            		Minecraft.getMinecraft().thePlayer.setPosition(mc.thePlayer.posX, 
            				mc.thePlayer.posY+entityPosY/k, mc.thePlayer.posZ); 
            	}
    			
    			// Ensuite on avance
    			for (int j=0;j<k;j++) {          ;
            		Minecraft.getMinecraft().thePlayer.sendQueue.addToSendQueue(new C04PacketPlayerPosition(mc.thePlayer.posX+entityPosX/k, 
            				mc.thePlayer.posY, mc.thePlayer.posZ+entityPosZ/k, false));    
            		Minecraft.getMinecraft().thePlayer.setPosition(mc.thePlayer.posX+entityPosX/k, 
            				mc.thePlayer.posY, mc.thePlayer.posZ+entityPosZ/k); 
            	}
    			return "up";
			} else {
				for (int j=0;j<k;j++) {          
            		mc.thePlayer.fallDistance=0;
            		mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer(true));
            		Minecraft.getMinecraft().thePlayer.sendQueue.addToSendQueue(new C04PacketPlayerPosition(mc.thePlayer.posX+entityPosX/k, 
            				mc.thePlayer.posY+entityPosY/k, mc.thePlayer.posZ+entityPosZ/k, false));    
            		Minecraft.getMinecraft().thePlayer.setPosition(mc.thePlayer.posX+entityPosX/k, 
            				mc.thePlayer.posY+entityPosY/k, mc.thePlayer.posZ+entityPosZ/k); 
            	}
				return "classic";
			}
		} else {
			for (int j=0;j<k;j++) {          
        		mc.thePlayer.fallDistance=0;
        		mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer(true));
        		Minecraft.getMinecraft().thePlayer.sendQueue.addToSendQueue(new C04PacketPlayerPosition(mc.thePlayer.posX+entityPosX/k, 
        				mc.thePlayer.posY+entityPosY/k, mc.thePlayer.posZ+entityPosZ/k, false));    
        		Minecraft.getMinecraft().thePlayer.setPosition(mc.thePlayer.posX+entityPosX/k, 
        				mc.thePlayer.posY+entityPosY/k, mc.thePlayer.posZ+entityPosZ/k); 
        	}	    
			return "classic";
		}			
	}
    
    public Boolean isViable(EntityLivingBase en) {
    	if (mc.playerController.isSpectatorMode())
    	       return false;    	    	
    	
    	if(!(en.isEntityAlive() && !Friends.isFriend(en.getName()) && en!=mc.thePlayer))
    		return false;

    	if (KillAura.isBot(en.getName()))
    		return false;
    	
    	if (KillAura.verif)
	    	if (KillAura.list.size()!=0) {
	    		int l=0;
	    		for (int j=0;j<KillAura.list.size();j++) {
	    			if ((en.getName().equalsIgnoreCase(KillAura.list.get(j)))) {
	    				l=1;
	    			}    			
	    		}
	    		if (l==0) {
	    			return false;
	    		}
	    	}
    	
    	if (mc.thePlayer.canEntityBeSeen(en) && mc.thePlayer.getDistanceToEntity(en)>6) {
    		doTpAller(en);
    	}
    	if (!mc.thePlayer.canEntityBeSeen(en) && mc.thePlayer.getDistanceToEntity(en)>6) {
    		return false;
    	}
    	
    	return true;
    }

}
