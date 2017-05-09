package neko.module.modules;

import neko.Client;
import neko.module.Category;
import neko.module.Module;
import neko.module.other.Form;
import neko.utils.TpUtils;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemBucket;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.network.play.client.C02PacketUseEntity;
import net.minecraft.network.play.client.C02PacketUseEntity.Action;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.network.play.client.C08PacketPlayerBlockPlacement;
import net.minecraft.util.BlockPos;

public class Reach extends Module {
	Client var = Client.getNeko();
	public static boolean isOn=false;
	public static Form mode = Form.Normal;
	public static float dist=5.0F;
	public static Block currentBlock;
	public static Block lastBlock;
	public static boolean pvp=false;
	public static boolean bloc=false;
	public static boolean classic=false;
	public static boolean aimbot=false;
	public static boolean tnt=false;
	public static double fov=7;
	double entityPosX;				
	double entityPosY;
	double entityPosZ;	
	double lastX;
	double lastY;
	double lastZ;
	int k;

	public Reach() {
		super("Reach", -1, Category.COMBAT);
	}
	
	public void onEnabled() {
		if (u.isLock(this.getName()))
			return;
		if (u.display)
		u.addChat("§a§oReach activée !");
		isOn=true;
		super.onEnabled();
	}
	
	public void onDisabled() {
		if (u.display)
		u.addChat("§c§oReach désactivée !");
		isOn=false;
		super.onDisabled();
	}
	// Réglé dans PlayerControllerMP
	public void onUpdate() {
		if (u.isLock(this.getName())) {
			boolean display = u.display;
			u.display=false;
			this.isToggled=false;
			u.display=display;
			u.addWarn(this.getName());
			return;
		}
		if (!mc.thePlayer.isSneaking()) {
			isOn=true;			
		} else {
			isOn=false;
		}						
	}	
	
	public void onRightClick() {
		if (u.isLock(this.getName())) {
			boolean display = u.display;
			u.display=false;
			this.isToggled=false;
			u.display=display;
			u.addWarn(this.getName());
			return;
		}
		if (mc.thePlayer.isSneaking())
			return;
		if (u.isLock("--reach pvp"))
			return;
		if (tnt && !(getTnt()<0 || getFlint()<0)) {
			// Reach tnt
			int tnt = getTnt();
			int briquet = getFlint();
			int actual = mc.thePlayer.inventory.currentItem;
			if (tnt<0 || briquet<0) {
				return;
			}
			BlockPos bll = mc.objectMouseOver.func_178782_a();
			
			if (bll==null || mc.theWorld.getBlockState(bll).getBlock().getMaterial() == Material.air)
				return;				
			// Transformer le bloc en entité
			EntityWitch en1 = new EntityWitch(mc.theWorld);
			en1.setPosition(bll.getX(), bll.getY()+1, bll.getZ());
			int face = mc.objectMouseOver.field_178784_b.getIndex();
			int a=bll.getX();
			int b=bll.getY();
			int c=bll.getZ();
			// Tp Aller
			String retour = doTpAller(en1);
			// Pose et allume la tnt
			swap(actual, tnt);
			if (mode.equals(Form.Cage)) {
				sendPacket(a, b, c, face);
				sendPacket(a+2, b, c, face);
				sendPacket(a-2, b, c, face);
				sendPacket(a+2, b, c+2, face);
				sendPacket(a+2, b, c-2, face);
				sendPacket(a-2, b, c-2, face);
				sendPacket(a-2, b, c+2, face);
				sendPacket(a, b, c+2, face);
				sendPacket(a, b, c-2, face);
				
				sendPacket(a+1, b, c+2, face);
				sendPacket(a+1, b, c-2, face);
				sendPacket(a-1, b, c-2, face);
				sendPacket(a-1, b, c+2, face);
				
				sendPacket(a+2, b, c+1, face);
				sendPacket(a+2, b, c-1, face);
				sendPacket(a-2, b, c-1, face);
				sendPacket(a-2, b, c+1, face);
			} else {
				sendPacket(a, b, c, face);
			}

			swap(actual, briquet);
			
			mc.getNetHandler().addToSendQueue(new C08PacketPlayerBlockPlacement(new BlockPos(bll.getX()+(face==4 ? -1 : face==5 ? 1 : 0), bll.getY()+(face==1 ? 1 : face==0 ? -1 : 0), bll.getZ()+(face==2 ? -1 : face==3 ? 1 : 0)), face, mc.thePlayer.inventory.getCurrentItem(), bll.getX(), bll.getY()+1, bll.getZ()));
			swap(actual, tnt);
			// Tp Retour
			doTpRetour(retour);
			// Puis on re set notre position initiale
			mc.thePlayer.setPosition(lastX, lastY+0.001*Math.random(), lastZ);
			mc.playerController.syncCurrentPlayItem();
		} else if (bloc) {
			try {
				if (mc.thePlayer.getCurrentEquippedItem().getItem() instanceof ItemSword || mc.thePlayer.getCurrentEquippedItem().getItem() instanceof ItemBow) {
					return;
				}
			} catch (Exception e) {}
			
			BlockPos bll = mc.objectMouseOver.func_178782_a();
			
			if (bll==null || mc.thePlayer.getDistance(bll.getX(), bll.getY(), bll.getZ())<5 || mc.theWorld.getBlockState(new BlockPos(bll.getX(), bll.getY()+2, bll.getZ())).getBlock().getMaterial() != Material.air || mc.theWorld.getBlockState(new BlockPos(bll.getX(), bll.getY()+3, bll.getZ())).getBlock().getMaterial() != Material.air || mc.theWorld.getBlockState(bll).getBlock().getMaterial() == Material.air || mc.thePlayer.getCurrentEquippedItem()==null || (u.isToggle("Pyro") && hasFire()))
				return;					
			
			// Transformer le bloc en entité
			EntityWitch en1 = new EntityWitch(mc.theWorld);
			en1.setPosition(bll.getX(), bll.getY()+2, bll.getZ());
			
			String retour = doTpAller(en1);
			try {				
				if (mc.thePlayer.getCurrentEquippedItem().getItem() instanceof ItemBucket) {
					mc.getNetHandler().addToSendQueue(new C03PacketPlayer.C05PacketPlayerLook(mc.thePlayer.rotationYaw, 95.0F, mc.thePlayer.onGround));	
			        Minecraft.thePlayer.sendQueue.addToSendQueue(new C08PacketPlayerBlockPlacement(Minecraft.thePlayer.inventory.getCurrentItem()));
				}
			} catch (Exception e) {}
			mc.getNetHandler().addToSendQueue(new C08PacketPlayerBlockPlacement(new BlockPos(bll.getX(), bll.getY(), bll.getZ()), mc.objectMouseOver.field_178784_b.getIndex(), mc.thePlayer.inventory.getCurrentItem(), bll.getX(), bll.getY(), bll.getZ()));
			doTpRetour(retour);
			mc.thePlayer.setPosition(lastX, lastY+0.001*Math.random(), lastZ);
		}
	}
	
	public void sendPacket(int a, int b, int c, int face) {
		mc.getNetHandler().addToSendQueue(new C08PacketPlayerBlockPlacement(new BlockPos(a, b, c), face, mc.thePlayer.inventory.getCurrentItem(), a, b, c));
	}
	
	public boolean hasFire() {
		if (mc.thePlayer.getCurrentEquippedItem() != null) {
			ItemStack is = mc.thePlayer.getCurrentEquippedItem();
			if (is!=null) {
				Item item = is.getItem();
				if (Item.getIdFromItem(item) == 259 || Item.getIdFromItem(item) == 51 || Item.getIdFromItem(item) == 385) {
					return true;
				}
			}
		}
		return false;	
	}
	
	public void onClick() {
		if (mc.thePlayer.isSneaking())
			return;
		if (u.limite && u.nbPack>u.limit)
			return;
		if (pvp && !mc.playerController.isSpectatorMode()) {						
			Entity en = mc.pointedEntity;
			if (en!=null && !Friends.isFriend(en.getName()) && mc.thePlayer!=en) {
				if (mc.thePlayer.getDistanceToEntity(en)>5) {
	        		String s = doTpAller(en);
	        		mc.playerController.attackEntity(mc.thePlayer, en);
	        		doTpRetour(s);	        			        			
	        		
	        		// Puis on re set notre position initiale
	            	mc.thePlayer.setPosition(lastX, lastY+0.005*Math.random(), lastZ);
	            	KillAura.giveMoney((EntityLivingBase)en);
				}
			} else if (mc.thePlayer.getCurrentEquippedItem()==null && bloc) {
				BlockPos bll = mc.objectMouseOver.func_178782_a();
				if (bll==null || mc.theWorld.getBlockState(bll).getBlock().getMaterial() == Material.air || mc.thePlayer.getDistance(bll.getX(), bll.getY(), bll.getZ())<4)
					return;				
        		EntityWitch en1 = new EntityWitch(mc.theWorld);
        		en1.setPosition(bll.getX(), bll.getY(), bll.getZ());
        		doTpRetour(doTpAller(en1));
        		// Puis on re set notre position initiale
        		mc.thePlayer.setPosition(lastX, lastY+0.001*Math.random(), lastZ);
			} else if (aimbot) {
				switch (var.mode) {
				case "Player" :
					for (Object theObject : mc.theWorld.playerEntities) {
		                EntityPlayer entity = (EntityPlayer) theObject;
		                if (u.isEntityInFov(entity, fov) && !Friends.isFriend(entity.getName()) && mc.thePlayer!=entity) {
	                        String s = doTpAller(entity);
	                        
	                		if (u.isToggle("FastDura")) {
	            				FastDura.doDura(entity);
	            			} else
	            				mc.thePlayer.sendQueue.addToSendQueue(new C02PacketUseEntity(entity, Action.ATTACK));
	                		doTpRetour(s);
	                		mc.thePlayer.setPosition(lastX, lastY+0.005*Math.random(), lastZ);
	                		KillAura.giveMoney(entity);	
		                	break; 
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
	                                                 
                            if(entity.isEntityAlive() && entity.ticksExisted > 2 && !u.isPlayer(entity) && entity!=mc.thePlayer) {
                            	if (u.isEntityInFov(entity, fov)) {
    	                        	        	                        
        	                        String s = doTpAller(entity);
        	                        
        	                		if (u.isToggle("FastDura")) {
        	            				FastDura.doDura(entity);
        	            			} else
        	            				mc.thePlayer.sendQueue.addToSendQueue(new C02PacketUseEntity(entity, Action.ATTACK));
        	                		doTpRetour(s);
        	                		mc.thePlayer.setPosition(lastX, lastY+0.005*Math.random(), lastZ);;
        	                		KillAura.giveMoney(entity);
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
	                       	
	                        if (u.isEntityInFov(entity, fov) && !Friends.isFriend(entity.getName()) && mc.thePlayer!=entity) {

		                        String s = doTpAller(entity);
		                        
		                		if (u.isToggle("FastDura")) {
		            				FastDura.doDura(entity);
		            			} else
		            				mc.thePlayer.sendQueue.addToSendQueue(new C02PacketUseEntity(entity, Action.ATTACK));
		                		doTpRetour(s);
		                		mc.thePlayer.setPosition(lastX, lastY+0.005*Math.random(), lastZ);
		                		KillAura.giveMoney(entity);
			                	break; 
			                }                      
	        			}
					}
				}
			}
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
		TpUtils tp = new TpUtils();
		return tp.doTpAller(en, entityPosX, entityPosY, entityPosZ, classic, k);
		
	}
	
	public void doTpRetour(String how) {
		TpUtils tp = new TpUtils();
		tp.doTpRetour(how, entityPosX, entityPosY, entityPosZ, k);
	}
	
	public int getTnt() {
		for (int i = 9; i < 45; i++) {
			if (mc.thePlayer.inventoryContainer.getSlot(i) != null) {
				ItemStack is = mc.thePlayer.inventoryContainer.getSlot(i).getStack();
				if (is!=null) {
					Item item = is.getItem();
					if (Item.getIdFromItem(item) == 46) {
						return i;
					}
				}
			}
		}
		return -1;		
	}	
		
	public int getFlint() {
		for (int i = 9; i < 45; i++) {
			if (mc.thePlayer.inventoryContainer.getSlot(i) != null) {
				ItemStack is = mc.thePlayer.inventoryContainer.getSlot(i).getStack();
				if (is!=null) {
					Item item = is.getItem();
					if (Item.getIdFromItem(item) == 259) {
						return i;
					}
				}
			}
		}
		return -1;	
	}
	
	public void swap(int prochainItem, int ancienItem) {
		mc.playerController.windowClick(mc.thePlayer.inventoryContainer.windowId, ancienItem, prochainItem, 2, mc.thePlayer);
	}
	
}
