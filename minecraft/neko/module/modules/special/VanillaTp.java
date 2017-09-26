package neko.module.modules.special;

import org.lwjgl.input.Keyboard;

import neko.Client;
import neko.module.Category;
import neko.module.Module;
import neko.utils.TpUtils;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.network.play.client.C03PacketPlayer.C04PacketPlayerPosition;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumParticleTypes;

public class VanillaTp extends Module {
	Minecraft mc = Minecraft.getMinecraft();
	public static int k=11;
	public static boolean air=false;
	public static boolean classic=false;
	public static boolean top=false;
	Client var = Client.getNeko();
	
	public VanillaTp() {
		super("VanillaTp", Keyboard.KEY_TAB, Category.Special);
	}
	

    @Override
    public void onToggle() {	
    	this.isToggled=false;
        if (mc.thePlayer.isSneaking()) {
        	return;        	
        }        
        
        BlockPos blockPos = mc.objectMouseOver.func_178782_a();
        if (blockPos==null) {        	
        	if (mc.pointedEntity!=null) {
        		// Tp au joueur
        		if(Minecraft.getMinecraft().thePlayer.getDistanceToEntity(mc.pointedEntity) <= 1) {
                	return;
                } else {                               		                	
                	
            		double entityPosX = mc.pointedEntity.posX-mc.thePlayer.posX;
            		double entityPosY = mc.pointedEntity.posY-mc.thePlayer.posY;
            		double entityPosZ = mc.pointedEntity.posZ-mc.thePlayer.posZ;  
            		for (k=1;u.verif(entityPosX, k) > 4;k++) {}
            		for (;u.verif(entityPosY, k) > 4;k++) {}
            		for (;u.verif(entityPosZ, k) > 4;k++) {} 
                	for (int j=0;j<k;j++) {
                		mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer(true));
                		mc.thePlayer.setPosition(mc.thePlayer.posX+entityPosX/k, 
                				mc.thePlayer.posY+entityPosY/k, mc.thePlayer.posZ+entityPosZ/k);                    		
                		mc.getNetHandler().addToSendQueue(new C04PacketPlayerPosition(mc.thePlayer.posX+entityPosX/k, 
                				mc.thePlayer.posY+entityPosY/k, mc.thePlayer.posZ+entityPosZ/k, false)); 
                		
                	}
                	if (mc.thePlayer.posY % 10 != 0) {
                		Minecraft.getMinecraft().thePlayer.setPosition(mc.thePlayer.posX, 
                				Math.round(mc.thePlayer.posY+0.7*Math.random()), mc.thePlayer.posZ);
                		Minecraft.getMinecraft().thePlayer.sendQueue.addToSendQueue(new C04PacketPlayerPosition(mc.thePlayer.posX, 
                				Math.round(mc.thePlayer.posY+0.7*Math.random()), mc.thePlayer.posZ, false)); 
                		mc.thePlayer.posY = Math.round(mc.thePlayer.posY+0.5*Math.random());
                	}
                	double neko = -1+Math.random()*20.3;									
    				double nekoN = -1+Math.random()*20.6;	
    				double n = Math.random()*5-5.25;
    				double nekoY = Math.random()*1.15+n;
    				mc.theWorld.spawnParticle(EnumParticleTypes.PORTAL, mc.thePlayer.posX+neko, mc.thePlayer.posY+nekoY, mc.thePlayer.posZ+nekoN, 0, 0, 0, 0);
                	mc.thePlayer.playSound("mob.endermen.portal", 1.0F, 1.0F);
                	if (Math.random()<0.2)
                		u.checkXp(4);
                }   
        	} else {
        		return;
        	}
        
        } else {       	        
                if(Minecraft.getMinecraft().thePlayer.getDistanceSq(blockPos) <= 2 || (mc.theWorld.getBlockState(blockPos).getBlock().getMaterial() == Material.air && !air)) {
                	return;
                } else {    
                	int a=blockPos.getX();
            		int b=blockPos.getY();
            		int c=blockPos.getZ();
                	if ((mc.theWorld.getBlockState(new BlockPos(blockPos.getX(), blockPos.getY()+1, blockPos.getZ())).getBlock().getMaterial() != Material.air || mc.theWorld.getBlockState(new BlockPos(blockPos.getX(), blockPos.getY()+2, blockPos.getZ())).getBlock().getMaterial() != Material.air) && top) {
                		for (;;b++) {
                			if (mc.theWorld.getBlockState(new BlockPos(a, b, c)).getBlock().getMaterial() == Material.air && mc.theWorld.getBlockState(new BlockPos(a, b+1, c)).getBlock().getMaterial() == Material.air) {
                				b--;
                				break;
                			}
                		}
                		
                	}
                	BlockPos bll = new BlockPos(a, b, c);
                	
    				double entityPosX = bll.getX()-mc.thePlayer.posX;				
            		double entityPosY = bll.getY()-mc.thePlayer.posY+1.5;
            		double entityPosZ = bll.getZ()-mc.thePlayer.posZ;
            		
            		for (k=1;u.verif(entityPosX, k) > 4;k++) {}
            		for (;u.verif(entityPosY, k) > 4;k++) {}
            		for (;u.verif(entityPosZ, k) > 4;k++) {} 
            		EntityWitch en1 = new EntityWitch(mc.theWorld);
            		en1.setPosition(bll.getX(), bll.getY(), bll.getZ());
            		TpUtils tp = new TpUtils();
            		tp.doTpAller(en1, entityPosX, entityPosY, entityPosZ, classic, k);
            		// Puis on re set notre position finale exact
            		BlockPos actualPos = new BlockPos(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ);
            		mc.thePlayer.setPosition(mc.thePlayer.posX+0.5, mc.thePlayer.posY+0.01*Math.random(), mc.thePlayer.posZ+0.5);
                	mc.thePlayer.playSound("mob.endermen.portal", 1.0F, 1.0F);
                	if (Math.random()<0.2)
                		u.checkXp(u.getRandInt(7)+1);
                }           
        }        
        if (!u.isToggle("Reach")) {
        	u.addChat("§cRappel: La reach doit être active pour se tp plus loin\n§cExemple: "+var.prefixCmd+"reach 100 ou "+var.prefixCmd+"toggle reach");
        }
        
    }
    	
}
