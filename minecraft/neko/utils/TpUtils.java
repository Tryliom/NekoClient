package neko.utils;

import java.util.Vector;

import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.network.play.client.C03PacketPlayer.C04PacketPlayerPosition;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;

public class TpUtils {
	Minecraft mc = Minecraft.getMinecraft();		
	
	/**
	 * <div style="font-size: 17px; margin-bottom: 15px;">Sert ? se tp de mani?re intelligente ? une entit?/cible</div><hr style="width: 80%; color: rgba(0, 88, 180, 0.5);" />
	 * @param en			Entit? sur laquelle on se tp
	 * @param entityPosX	Coord X de l'entit? apr?s d?duction des coord du joueur
	 * @param entityPosY	Coord Y de l'entit? apr?s d?duction des coord du joueur
	 * @param entityPosZ	Coord Z de l'entit? apr?s d?duction des coord du joueur
	 * @param classic		Boolean si le tp est de mani?re classique ou sinon suivant la d?tection du terrain
	 * @param k				Nombre par lequel sont divis?s les coord<hr />
	 * @return				String de la mani?re de comment on se tp, utilis? par le doTpRetour
	 */
	public String doTpAller(Entity en, double entityPosX, double entityPosY, double entityPosZ, boolean classic, int k) {
		if (entityPosY<0 && !classic) {
			// Check du chemin et de tous les blocs dessus, si false, faire le chemin classic
			boolean classical=false;
			int posx=(int) Math.round(en.posX);
			int posz=(int) Math.round(en.posZ);
			int posy=(int) Math.round(en.posY);
			boolean pX=true;
			boolean pZ=true;
			for (;(pX || pZ) && (Utils.verif(posx+1, 1)<Utils.verif(en.posX+1, 1) && Utils.verif(posz+1, 1)<Utils.verif(en.posZ+1, 1));) {
				if (posx==Math.round(en.posX) && pX)
					pX=false;
				if (posz==Math.round(en.posZ) && pZ)
					pZ=false;
				// Vu que je m'embrouille avec le entityPosX*-1 : On divise un - par un - pour ensuite diviser un -/+ (-/ - ? -*- : +)
				posx+=entityPosX/(entityPosX<0 ? entityPosX*-1 : entityPosX);
				posz+=entityPosZ/(entityPosZ<0 ? entityPosZ*-1 : entityPosZ);
				BlockPos bl = new BlockPos(posx+1, mc.thePlayer.posY, posz+1);	
				if (!(mc.theWorld.getBlockState(bl).getBlock().getMaterial() == Material.air || mc.theWorld.getBlockState(bl).getBlock().getMaterial() == Material.water || mc.theWorld.getBlockState(bl).getBlock().getMaterial() == Material.web || mc.theWorld.getBlockState(bl).getBlock().getMaterial() == Material.lava || mc.theWorld.getBlockState(bl).getBlock().getMaterial() == Material.portal || mc.theWorld.getBlockState(bl).getBlock().getMaterial() == Material.redstoneLight || mc.theWorld.getBlockState(bl).getBlock().getMaterial() == Material.vine)) {
					classical=true;
					break;
				}
			}
			for (int i=1;posy<mc.thePlayer.posY+2-i;i++) { // +3 : 2 pour taille joueur + 1 pour la taille bloc | On compte depuis le haut jusqu'en bas
				BlockPos bl = new BlockPos(posx+1, mc.thePlayer.posY+2-i, posz+1);
				if (!mc.theWorld.getBlockState(bl).getBlock().isBlockSolid(mc.theWorld, bl, EnumFacing.DOWN) || mc.theWorld.getBlockState(bl).getBlock().getMaterial() == Material.air || mc.theWorld.getBlockState(bl).getBlock().getMaterial() == Material.water || mc.theWorld.getBlockState(bl).getBlock().getMaterial() == Material.web || mc.theWorld.getBlockState(bl).getBlock().getMaterial() == Material.lava || mc.theWorld.getBlockState(bl).getBlock().getMaterial() == Material.portal || mc.theWorld.getBlockState(bl).getBlock().getMaterial() == Material.redstoneLight || mc.theWorld.getBlockState(bl).getBlock().getMaterial() == Material.vine) {
				} else {
					classical=true;
					break;
				}
			}
			
			if (classical) {
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
		} else if (entityPosY>0 && !classic) {
			boolean marche=true;
			int posx=(int) Math.round(en.posX);
			int posz=(int) Math.round(en.posZ);
			int posy=(int) Math.round(en.posY);
			for (int i=1;en.posY+1-i>0;i++) { // +1 car on fait 2 blocs | On compte depuis le haut jusqu'en bas
				BlockPos bl = new BlockPos(mc.thePlayer.posX, mc.thePlayer.posY+i, mc.thePlayer.posZ);
				if (!mc.theWorld.getBlockState(bl).getBlock().isBlockSolid(mc.theWorld, bl, EnumFacing.DOWN) || mc.theWorld.getBlockState(bl).getBlock().getMaterial() == Material.air || mc.theWorld.getBlockState(bl).getBlock().getMaterial() == Material.water || mc.theWorld.getBlockState(bl).getBlock().getMaterial() == Material.web || mc.theWorld.getBlockState(bl).getBlock().getMaterial() == Material.lava || mc.theWorld.getBlockState(bl).getBlock().getMaterial() == Material.portal || mc.theWorld.getBlockState(bl).getBlock().getMaterial() == Material.redstoneLight || mc.theWorld.getBlockState(bl).getBlock().getMaterial() == Material.vine) {
				} else {
					marche=false;
					break;
				}
			}
			boolean pX=true;
			boolean pZ=true;
			for (;pX && pZ;) {
				if (posx==Math.round(mc.thePlayer.posX+entityPosX) && pX)
					pX=false;
				if (posz!=Math.round(mc.thePlayer.posZ+entityPosZ) && pZ)
					pZ=false;
				// Vu que je m'embrouille avec le entityPosX*-1 : On divise un - par un - pour ensuite diviser un -/+ (-/ - ? -*- : +)
				posx+=entityPosX/(entityPosX<0 ? entityPosX*-1 : entityPosX);
				posz+=entityPosZ/(entityPosZ<0 ? entityPosZ*-1 : entityPosZ);
				BlockPos bl = new BlockPos(mc.thePlayer.posX, posy, mc.thePlayer.posZ);
				if (!(mc.theWorld.getBlockState(bl).getBlock().getMaterial() == Material.air || mc.theWorld.getBlockState(bl).getBlock().getMaterial() == Material.water || mc.theWorld.getBlockState(bl).getBlock().getMaterial() == Material.web || mc.theWorld.getBlockState(bl).getBlock().getMaterial() == Material.lava || mc.theWorld.getBlockState(bl).getBlock().getMaterial() == Material.portal || mc.theWorld.getBlockState(bl).getBlock().getMaterial() == Material.redstoneLight || mc.theWorld.getBlockState(bl).getBlock().getMaterial() == Material.vine)) {
					marche=false;
					break;
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
	
	/**
	 * 
	 * @param how			Mani?re de se t?l?porter
	 * @param entityPosX	Coord X de l'entit? apr?s d?duction des coord du joueur
	 * @param entityPosY	Coord Y de l'entit? apr?s d?duction des coord du joueur
	 * @param entityPosZ	Coord Z de l'entit? apr?s d?duction des coord du joueur
	 * @param k				Nombre par lequel sont divis?s les coord
	 */
	public void doTpRetour(String how, double entityPosX, double entityPosY, double entityPosZ, int k) {
		switch (how) {
		case "down" :
			for (int j=0;j<k;j++) {          
        		Minecraft.getMinecraft().thePlayer.sendQueue.addToSendQueue(new C04PacketPlayerPosition(mc.thePlayer.posX, 
        				mc.thePlayer.posY-entityPosY/k, mc.thePlayer.posZ, false));    
        		Minecraft.getMinecraft().thePlayer.setPosition(mc.thePlayer.posX, 
        				mc.thePlayer.posY-entityPosY/k, mc.thePlayer.posZ); 
        	}
			
			for (int j=0;j<k;j++) {          
        		Minecraft.getMinecraft().thePlayer.sendQueue.addToSendQueue(new C04PacketPlayerPosition(mc.thePlayer.posX-entityPosX/k, 
        				mc.thePlayer.posY, mc.thePlayer.posZ-entityPosZ/k, false));    
        		Minecraft.getMinecraft().thePlayer.setPosition(mc.thePlayer.posX-entityPosX/k, 
        				mc.thePlayer.posY, mc.thePlayer.posZ-entityPosZ/k); 
        	}
			Minecraft.getMinecraft().thePlayer.sendQueue.addToSendQueue(new C04PacketPlayerPosition(mc.thePlayer.posX+0.5, mc.thePlayer.posY, mc.thePlayer.posZ+0.5, false));    
    		Minecraft.getMinecraft().thePlayer.setPosition(mc.thePlayer.posX+0.5, mc.thePlayer.posY, mc.thePlayer.posZ+0.5);
			break;
			
		case "up":
			for (int j=0;j<k;j++) {          
        		Minecraft.getMinecraft().thePlayer.sendQueue.addToSendQueue(new C04PacketPlayerPosition(mc.thePlayer.posX-entityPosX/k, 
        				mc.thePlayer.posY, mc.thePlayer.posZ-entityPosZ/k, false));    
        		Minecraft.getMinecraft().thePlayer.setPosition(mc.thePlayer.posX-entityPosX/k, 
        				mc.thePlayer.posY, mc.thePlayer.posZ-entityPosZ/k); 
        	}
			
			for (int j=0;j<k;j++) {          
        		mc.thePlayer.fallDistance=0;
        		mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer(true));
        		Minecraft.getMinecraft().thePlayer.sendQueue.addToSendQueue(new C04PacketPlayerPosition(mc.thePlayer.posX, 
        				mc.thePlayer.posY-entityPosY/k, mc.thePlayer.posZ, false));    
        		Minecraft.getMinecraft().thePlayer.setPosition(mc.thePlayer.posX, 
        				mc.thePlayer.posY-entityPosY/k, mc.thePlayer.posZ); 
        	}
			Minecraft.getMinecraft().thePlayer.sendQueue.addToSendQueue(new C04PacketPlayerPosition(mc.thePlayer.posX+0.5, mc.thePlayer.posY, mc.thePlayer.posZ+0.5, false));    
    		Minecraft.getMinecraft().thePlayer.setPosition(mc.thePlayer.posX+0.5, mc.thePlayer.posY, mc.thePlayer.posZ+0.5);
			break;
			
		case "classic":
			for (int j=0;j<k;j++) {       
				mc.thePlayer.fallDistance=0;
        		mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer(true));
        		Minecraft.getMinecraft().thePlayer.sendQueue.addToSendQueue(new C04PacketPlayerPosition(mc.thePlayer.posX-entityPosX/k, 
        				mc.thePlayer.posY-entityPosY/k, mc.thePlayer.posZ-entityPosZ/k, false));   
        		Minecraft.getMinecraft().thePlayer.setPosition(mc.thePlayer.posX-entityPosX/k, 
        				mc.thePlayer.posY-entityPosY/k, mc.thePlayer.posZ-entityPosZ/k); 
        	}
			Minecraft.getMinecraft().thePlayer.sendQueue.addToSendQueue(new C04PacketPlayerPosition(mc.thePlayer.posX+0.5, mc.thePlayer.posY, mc.thePlayer.posZ+0.5, false));    
    		Minecraft.getMinecraft().thePlayer.setPosition(mc.thePlayer.posX+0.5, mc.thePlayer.posY, mc.thePlayer.posZ+0.5);
			break;
		}
	}
	
	/**
	 * Retourne automatiquement le nombre qui divise les coord de pos, le K 
	 * @param target	La cible sans les d?ductions par coord du joueur, + 1.5 au Y
	 * @return			K
	 */
	public int getK(BlockPos target) {
		int k;
		double entityPosX = target.getX()-mc.thePlayer.posX;				
		double entityPosY = target.getY()-mc.thePlayer.posY+1.5;
		double entityPosZ = target.getZ()-mc.thePlayer.posZ;
		for (k=1;Utils.verif(entityPosX, k) > 4;k++) {}
		for (;Utils.verif(entityPosY, k) > 4;k++) {}
		for (;Utils.verif(entityPosZ, k) > 4;k++) {} 
		return k;
	}
	
	public int getK(BlockPos target, BlockPos lastTarget) {
		int k;
		double entityPosX = target.getX()-lastTarget.getX();				
		double entityPosY = target.getY()-lastTarget.getY();
		double entityPosZ = target.getZ()-lastTarget.getZ();
		for (k=1;Utils.verif(entityPosX, k) > 4;k++) {}
		for (;Utils.verif(entityPosY, k) > 4;k++) {}
		for (;Utils.verif(entityPosZ, k) > 4;k++) {} 
		return k;
	}
	
	/**
	 * Retourne la mani?re de se tp
	 * @param target	Cible vis?e
	 * @return			String de la mani?re utilis?e
	 */
	public String getHow(BlockPos target) {
		BlockPos player = mc.thePlayer.getPosition();
		String s="classic";
		if (target.getY()>player.getY())
			s="up";
		else if (target.getY()<player.getY())
			s="down";
		return s;
	}
	
	/**
	 * Retourne une liste de Double en Vector des coord finales apr?s d?duction des coord joueur
	 * @param coordTarget	Cible
	 * @return				Vector de Double, 3 coord de X Y et Z
	 */
	public Vector<Double> getTargetInPos(BlockPos coordTarget) {
		Vector<Double> v = new Vector<Double>();
		if (mc.thePlayer!=null) {
			v.add(coordTarget.getX()-mc.thePlayer.posX);				
			v.add(coordTarget.getY()-mc.thePlayer.posY);
			v.add(coordTarget.getZ()-mc.thePlayer.posZ);
		} else {
			v.add(1.0);
			v.add(1.0);
			v.add(1.0);
		}
		return v;
	}
	
	public Vector<Double> getTargetInPos(BlockPos coordTarget, BlockPos lastTarget) {
		Vector<Double> v = new Vector<Double>();
		if (mc.thePlayer!=null) {
			v.add((double) (coordTarget.getX()-lastTarget.getX()));				
			v.add((double) (coordTarget.getY()-lastTarget.getY()));
			v.add((double) (coordTarget.getZ()-lastTarget.getZ()));
		} else {
			v.add(1.0);
			v.add(1.0);
			v.add(1.0);
		}
		return v;
	}


}
