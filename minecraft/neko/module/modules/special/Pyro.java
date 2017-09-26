package neko.module.modules.special;

import neko.module.Category;
import neko.module.Module;
import neko.module.other.Form;
import neko.utils.TpUtils;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.client.C08PacketPlayerBlockPlacement;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;

public class Pyro extends Module {
	public boolean isOn=false;
	public static Form mode = Form.Carre;
	double entityPosX;				
	double entityPosY;
	double entityPosZ;	
	double lastX;
	double lastY;
	double lastZ;
	int k;

	public Pyro() {
		super("Pyro", -1, Category.Special);
	}
	
	public void onEnabled() {
		isOn=true;
		super.onEnabled();
	}
	
	public void onDisabled() {
		isOn=false;
		super.onDisabled();
	}
	
	public void onUpdate() {
		if (!mc.thePlayer.isSneaking()) {
			isOn=true;			
		} else if (mc.thePlayer.isSneaking()) {
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
		// Vérif
		if (!hasFire()) 
			return;
		BlockPos bll = mc.objectMouseOver.func_178782_a();
		
		if (bll==null || mc.theWorld.getBlockState(bll).getBlock().getMaterial() == Material.air || mc.thePlayer.isSneaking() || mc.theWorld.getBlockState(new BlockPos(bll.getX(), bll.getY()+2, bll.getZ())).getBlock().getMaterial() != Material.air || mc.theWorld.getBlockState(new BlockPos(bll.getX(), bll.getY()+3, bll.getZ())).getBlock().getMaterial() != Material.air) {
			if (bll==null && mc.pointedEntity==null) {
				return;
			}			
		}	
		EntityWitch en1 = new EntityWitch(mc.theWorld);
		int a;
		int b;
		int c;
		if (bll==null && mc.pointedEntity!=null) {
			en1.setPosition(mc.pointedEntity.posX, mc.pointedEntity.posY+2, mc.pointedEntity.posZ);
			a=(int) Math.round(mc.pointedEntity.posX);
			b=(int) Math.round(mc.pointedEntity.posY-1);
			c=(int) Math.round(mc.pointedEntity.posZ);
		} else {
		// Transformer le bloc en entité
			en1.setPosition(bll.getX(), bll.getY()+2, bll.getZ());
			a=bll.getX();
			b=bll.getY();
			c=bll.getZ();
		}
		// Tp Aller
		String retour = doTpAller(en1);
		// Switch
		
		if (mode==Form.Carre) {
			sendPacket(a+1, b, c);
			sendPacket(a+1, b, c+1);
			sendPacket(a+1, b, c-1);
			sendPacket(a-1, b, c-1);
			sendPacket(a-1, b, c+1);
			sendPacket(a-1, b, c);
			sendPacket(a, b, c+1);
			sendPacket(a, b, c-1);
			sendPacket(a+2, b, c+2);
			sendPacket(a-2, b, c-2);
			sendPacket(a-2, b, c+2);
			sendPacket(a+2, b, c-2);
			sendPacket(a, b, c);
		} else if (mode==Form.Cage) {
			sendPacket(a+2, b, c);
			sendPacket(a-2, b, c);
			sendPacket(a+2, b, c+2);
			sendPacket(a+2, b, c-2);
			sendPacket(a-2, b, c-2);
			sendPacket(a-2, b, c+2);
			sendPacket(a, b, c+2);
			sendPacket(a, b, c-2);
			
			sendPacket(a+1, b, c+2);
			sendPacket(a+1, b, c-2);
			sendPacket(a-1, b, c-2);
			sendPacket(a-1, b, c+2);
			
			sendPacket(a+2, b, c+1);
			sendPacket(a+2, b, c-1);
			sendPacket(a-2, b, c-1);
			sendPacket(a-2, b, c+1);
		} else if (mode==Form.Random) {
			int r = u.getRandInt(13)+2;
			for (int i=0;i<r;i++) 
			sendPacket(a+u.getRandInt(3), b, c+u.getRandInt(3));
		} else if (mode==Form.Jesus) {
			int r = u.getRandInt(3);
			if (r==0) {
				sendPacket(a, b, c);
				sendPacket(a, b, c+1);
				sendPacket(a-1, b, c);
				sendPacket(a+1, b, c);
				sendPacket(a, b, c-1);
				sendPacket(a, b, c-2);				
			} else if (r==1) {
				sendPacket(a, b, c);
				sendPacket(a, b, c+1);
				sendPacket(a, b, c-1);
				sendPacket(a-1, b, c);
				sendPacket(a+1, b, c);
				sendPacket(a+2, b, c);
			} else if (r==2) {
				sendPacket(a, b, c);
				sendPacket(a-1, b, c);
				sendPacket(a+1, b, c);
				sendPacket(a, b, c-1);
				sendPacket(a, b, c+1);
				sendPacket(a, b, c+2);
			} else if (r==3) {
				sendPacket(a, b, c);
				sendPacket(a, b, c+1);
				sendPacket(a, b, c-1);
				sendPacket(a+1, b, c);
				sendPacket(a-1, b, c);
				sendPacket(a-2, b, c);
			}
		} else if (mode==Form.Normal) {
			sendPacket(a, b, c);
		} else if (mode==Form.Nazi) {
			sendPacket(a, b, c);
			sendPacket(a, b, c+1);
			sendPacket(a, b, c-1);
			sendPacket(a+1, b, c);
			sendPacket(a-1, b, c);
			sendPacket(a, b, c+2);
			sendPacket(a, b, c-2);
			sendPacket(a+2, b, c);
			sendPacket(a-2, b, c);
			
			sendPacket(a+1, b, c+2);
			sendPacket(a-1, b, c-2);
			sendPacket(a+2, b, c-1);
			sendPacket(a-2, b, c+1);
			
			sendPacket(a+2, b, c+2);
			sendPacket(a-2, b, c-2);
			sendPacket(a+2, b, c-2);
			sendPacket(a-2, b, c+2);
			sendPacket(a-2, b, c+2);
			sendPacket(a+2, b, c-2);
		} else if (mode==Form.Huit) {
			sendPacket(a, b, c);
			sendPacket(a, b, c+1);
			sendPacket(a, b, c-1);
			sendPacket(a+1, b, c);
			sendPacket(a-1, b, c);
			sendPacket(a, b, c+2);
			sendPacket(a, b, c-2);
			sendPacket(a+2, b, c);
			sendPacket(a-2, b, c);
			
			sendPacket(a+1, b, c+2);
			sendPacket(a-1, b, c-2);
			sendPacket(a+2, b, c+1);
			sendPacket(a-2, b, c-1);
			
			sendPacket(a+2, b, c+2);
			sendPacket(a-1, b, c-2);
			sendPacket(a+2, b, c+2);
			sendPacket(a-2, b, c-2);
		}
		
		// Tp Retour
		doTpRetour(retour);
		// Puis on re set notre position initiale
		mc.thePlayer.setPosition(lastX, lastY+0.001*Math.random(), lastZ);
	}	
	
	private void sendPacket(int a, int b, int c) {
		mc.getNetHandler().addToSendQueue(new C08PacketPlayerBlockPlacement(new BlockPos(a, b, c), EnumFacing.UP.getIndex(), 
				mc.thePlayer.inventory.getCurrentItem(), a, b, c));
	}		
	
	private String doTpAller(Entity en) {
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
		return tp.doTpAller(en, entityPosX, entityPosY, entityPosZ, false, k);
		
	}
	
	private void doTpRetour(String how) {
		TpUtils tp = new TpUtils();
		tp.doTpRetour(how, entityPosX, entityPosY, entityPosZ, k);
	}	
		
	private boolean hasFire() {
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
	
	private void swap(int prochainItem, int ancienItem) {
		mc.playerController.windowClick(mc.thePlayer.inventoryContainer.windowId, ancienItem, prochainItem, 2, mc.thePlayer);
	}	
	
}
