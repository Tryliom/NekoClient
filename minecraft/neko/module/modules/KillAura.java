package neko.module.modules;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import org.lwjgl.input.Keyboard;

import neko.Client;
import neko.module.Category;
import neko.module.Module;
import neko.utils.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemSword;
import net.minecraft.network.play.client.C02PacketUseEntity;
import net.minecraft.network.play.client.C02PacketUseEntity.Action;
import net.minecraft.network.play.client.C14PacketTabComplete;
import net.minecraft.util.MathHelper;

public class KillAura extends Module {
	EntityPlayer Player;
	public static Minecraft mc = Minecraft.getMinecraft();
	public static Client var = Client.getNeko();
	public int delay=0;
	public static int cps=6;
	public static boolean lockView=false;
	public static double range=6.2173613;
	public static int live=75;
	public static boolean invi=false;
	public static boolean noarmor=false;
	public static boolean onground=false;
	public static String mode="multi";
	public static double fov=360;
	public static boolean random=false;
	public static boolean waitTab=false;
	public static boolean verif=true;
	public static boolean nobot=true;
	public static boolean premium=false;
	public static ArrayList<String> list = new ArrayList<>();
	public static ArrayList<String> bot = new ArrayList<>();
	public static String tab="  ";
	public static javax.swing.Timer timer = new javax.swing.Timer(1000/cps, new cps());
	
	
	public KillAura() {
		super("KillAura", Keyboard.KEY_V, Category.COMBAT);
	}
	
	public void onEnabled() {
		if (Utils.display)
			Utils.addChat("§a§oKill Aura activé !");
		if (cps!=0)
			timer.setDelay(1000/cps);
		else
			timer.setDelay(1000);
		timer.start();
		super.onEnabled();
	}
	
	public void onDisabled() {
		if (Utils.display)
			Utils.addChat("§c§oKill Aura désactivé !");
		list.clear();
		timer.setDelay(1000/cps);
		timer.stop();
		super.onDisabled();
	}
	
	public static void clearBot() {
		bot.clear();
	}
	
	public static void addBot(String name) {
		for (String s : bot) {
			if (s.equalsIgnoreCase(name))
				return;
		}
		bot.add(name);
	}
	
	public static boolean isBot(String name) {
		for (String s : bot) {
			if (s.equalsIgnoreCase(name))
				return true;
		}
		return false;
	}
	
	
    public void onUpdate() {    
    	if (random) {
    		int r = (int) Math.round(8-Math.random()*4);
    		if (cps+r!=0)
    			timer.setDelay(1000/cps+r);
    	} else {
    		if (cps!=0)
    			timer.setDelay(1000/cps);
    	}    	
    }
    
    public static Boolean isViable(EntityLivingBase en) {
    	if (!Utils.getPlayerGameType(en.getName()).isSurvivalOrAdventure())
	    	return false;    	
    	
    	if (KillAura.invi) 
    		if (en.isInvisible())
    			return false;
    	
    	if (KillAura.onground)
        	if (!en.onGround)
        		return false;
    	
    	if (KillAura.noarmor)
        	if (en.hasArmor())
        		return false;
    	
    	if (!Utils.isInFov(en, fov))
    		return false;
    	
    	if(!(en.isEntityAlive() && en.ticksExisted > live && !Friends.isFriend(en.getName()) && en!=mc.thePlayer))
    		return false;
    	 
    	if (!(Minecraft.getMinecraft().thePlayer.getDistanceToEntity(en) <= range))
			return false;
    	
    	if (isBot(en.getName()))
			return false;
    	
    	if (en.getName().isEmpty())
			return false;
    	
    	if (verif && !Utils.IsInTab(en.getName()))
	    	return false;
    	
    	if (nobot && Utils.getPlayerPing(en.getName())<0) 
    		return false;
    	
    	if (premium && Utils.isPremium((en instanceof EntityPlayer) ? (EntityPlayer) en : null))
    		return false;
    	
    	return true;
    }
    
    public static void giveMoney(EntityLivingBase entity) {
    	if (KillAura.cps<15)
	    	if (entity.getHealth()<entity.getMaxHealth()/2) {
	    		Utils.checkXp(1);
	    	} else if (entity.getHealth()<entity.getMaxHealth()/4) {
	    		Utils.checkXp(3);
	    	}
    }
    
    public static synchronized void faceEntity(EntityLivingBase entity) {
		final float[] rotations = getRotationsNeeded(entity);

		if (rotations != null) {			
			mc.thePlayer.rotationYaw = (float) (rotations[0] + Math.random() + 0.3);
			mc.thePlayer.rotationPitch = (float) (rotations[1] + Math.random() + 0.3);
		}
	}

	public static float[] getRotationsNeeded(Entity entity) {
		if (entity == null) {
			return null;
		}

		final double diffX = entity.posX - mc.thePlayer.posX;
		final double diffZ = entity.posZ - mc.thePlayer.posZ;
		double diffY;

		if (entity instanceof EntityLivingBase) {
			final EntityLivingBase entityLivingBase = (EntityLivingBase) entity;
			diffY = entityLivingBase.posY + entityLivingBase.getEyeHeight() - (Minecraft.getMinecraft().thePlayer.posY + Minecraft.getMinecraft().thePlayer.getEyeHeight());
		} else {
			diffY = (entity.boundingBox.minY + entity.boundingBox.maxY) / 2.0D - (Minecraft.getMinecraft().thePlayer.posY + Minecraft.getMinecraft().thePlayer.getEyeHeight());
		}

		final double dist = MathHelper.sqrt_double(diffX * diffX + diffZ * diffZ);
		final float yaw = (float) (Math.atan2(diffZ, diffX) * 180.0D / Math.PI) - 90.0F;
		final float pitch = (float) -(Math.atan2(diffY, dist) * 180.0D / Math.PI);
		return new float[] { Minecraft.getMinecraft().thePlayer.rotationYaw + MathHelper.wrapAngleTo180_float(yaw - Minecraft.getMinecraft().thePlayer.rotationYaw), Minecraft.getMinecraft().thePlayer.rotationPitch + MathHelper.wrapAngleTo180_float(pitch - Minecraft.getMinecraft().thePlayer.rotationPitch) };
	}
	
	
    }

class cps implements ActionListener {
	Minecraft mc = Minecraft.getMinecraft();
	Client var = Client.getNeko();
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (Utils.sword ? mc.thePlayer.getCurrentEquippedItem()!=null ? mc.thePlayer.getCurrentEquippedItem().getItem() instanceof ItemSword : false : true) {
			switch (var.mode) {
			case "Player" :
				for (Object theObject : mc.theWorld.playerEntities) {
	                EntityLivingBase entity = (EntityLivingBase) theObject;
	                if(KillAura.isViable(entity)) {              	                	                   
	                	if (KillAura.lockView)
	                		KillAura.faceEntity(entity);
	                	
	                	if (Utils.isToggle("Crit"))
	                		Utils.crit();
	                		                			
                		Minecraft.getMinecraft().thePlayer.swingItem();
                		if (Utils.isToggle("FastDura")) {
            				FastDura.doDura(entity);
            			} else
            				mc.thePlayer.sendQueue.addToSendQueue(new C02PacketUseEntity(entity, Action.ATTACK));

	            		KillAura.giveMoney(entity);
	                	
	                	if (KillAura.mode.equalsIgnoreCase("Multi"))
	                		continue;
	                	else
	                		break;
	                }
	        	}
				break;
				
			case "Mob" :
				if (Utils.warn) {
        			if (Utils.isPlayerInRange(Utils.warnB))
        				return;
        		}
        		for (Object theObject : mc.theWorld.loadedEntityList) {
        			if (theObject instanceof EntityLivingBase) {
                    	EntityLivingBase entity = (EntityLivingBase) theObject;
                                                 
                        if(Minecraft.getMinecraft().thePlayer.getDistanceToEntity(entity) <= KillAura.range) {
                            if(entity.isEntityAlive() && entity.ticksExisted > 2 && !Utils.isPlayer(entity) && entity!=mc.thePlayer) {
                            	if (KillAura.lockView)
                            		KillAura.faceEntity(entity);
                            	if (Utils.isToggle("Crit"))
                            		Utils.crit();
                            	if (!Utils.isInFov(entity, KillAura.fov))
                            		return;
                            	
    	                		if (Utils.isToggle("FastDura")) {
    	            				FastDura.doDura(entity);
    	            			} else
    	            				mc.thePlayer.sendQueue.addToSendQueue(new C02PacketUseEntity(entity, Action.ATTACK));
    	                		Minecraft.getMinecraft().thePlayer.swingItem();
                            	
                            	if (entity.getHealth()<2) {
                            		Utils.checkXp(4);
                            	}
                            	if (KillAura.mode.equalsIgnoreCase("Multi"))
        	                		continue;
        	                	else
        	                		break;
                            }
                        }
        			}                            
                }
				break;
				
			case "All" :
				for (Object theObject : mc.theWorld.loadedEntityList) {
        			if (theObject instanceof EntityLivingBase) {
                        EntityLivingBase entity = (EntityLivingBase) theObject;
                       
                        if(KillAura.isViable(entity)) {                        	                           
                        	if (KillAura.lockView)
                        		KillAura.faceEntity(entity);
                        	if (Utils.isToggle("Crit"))
                        		Utils.crit();
                        	
	                		if (Utils.isToggle("FastDura")) {
	            				FastDura.doDura(entity);
	            			} else
	            				mc.thePlayer.sendQueue.addToSendQueue(new C02PacketUseEntity(entity, Action.ATTACK));
	                		Minecraft.getMinecraft().thePlayer.swingItem();
                        	
                        	KillAura.giveMoney(entity);
                        	if (KillAura.mode.equalsIgnoreCase("Multi"))
    	                		continue;
    	                	else
    	                		break;                       
                        }
        			}
                }
				break;
			}
		}
		
	}
	
}