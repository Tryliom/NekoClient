package neko.module.other;

import neko.utils.Utils;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.util.BlockPos;

public class Joueur {
	Minecraft mc = Minecraft.getMinecraft();
	private EntityPlayer player;
	private double lastPosX;
	private double lastPosZ;
	private int nbAlert;
	private int count;
	
	public Joueur(EntityPlayer player, double lastPosX, double lastPosZ) {
		super();
		this.player = player;
		this.lastPosX = lastPosX;
		this.lastPosZ = lastPosZ;
		this.nbAlert=0;
		this.count=0;
	}

	public EntityPlayer getPlayer() {
		return player;
	}

	public void setPlayer(EntityPlayer player) {
		this.player = player;
	}

	public double getLastPosX() {
		return lastPosX;
	}

	public void setLastPosX(double lastPosX) {
		this.lastPosX = lastPosX;
	}

	public double getLastPosZ() {
		return lastPosZ;
	}

	public void setLastPosZ(double lastPosZ) {
		this.lastPosZ = lastPosZ;
	}
	
	public void addAlert() {
		this.nbAlert++;
	}
	
	public void resetAlert() {
		this.nbAlert=0;
	}
	
	public int getAlert() {
		return this.nbAlert;
	}	
	
	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public void addCount() {
		this.count++;
	}
	
	public double getSpeed() {
		return this.player.getDistance(this.lastPosX, this.player.posY, this.lastPosZ);
	}
	
	public boolean isHack() {
		boolean var1=false;			
		
		// Pas oublier check si le joueur s'est fait frapper, dans ce cas calculer le kb que fais l'item Ou bien ignorer et le mettre pour la velocity
		if (this.getPlayer().isRiding() || this.getPlayer().hurtTime!=0) {
			Utils.addChat("§cLast Attacker:§7 "+(this.getPlayer().getLastAttacker()!=null ? this.getPlayer().getLastAttacker().getName() : this.getPlayer().getLastAttacker()));
			Utils.addChat("§cHurt Time: §7 "+this.getPlayer().hurtTime);
			return false;
		}
		
		// Check si le joueur est entre 2 blocs
		if (mc.theWorld.getBlockState(new BlockPos(this.player.posX,this.player.posY-1,this.player.posZ)).getBlock().getMaterial() != Material.air && mc.theWorld.getBlockState(new BlockPos(this.player.posX,this.player.posY+2,this.player.posZ)).getBlock().getMaterial() != Material.air) {
			var1=true;
		}
		
		// Check si le joueur a un effet de popo
		if (this.getPlayer().getActivePotionEffect(Potion.moveSpeed) != null) {
			int lvl = this.getPlayer().getActivePotionEffect(Potion.moveSpeed).getAmplifier();
			if (lvl==0) {
				if (var1) {
					if (this.getSpeed()>15)
						return true;
				} else {
					if (this.getSpeed()>7.6)
						return true;
				}
			} else if (lvl==1) {
				if (var1) {
					if (this.getSpeed()>18)
						return true;
				} else {
					if (this.getSpeed()>7.9)
						return true;
				}
			}						
			
		} else if (this.getPlayer().getActivePotionEffect(Potion.moveSlowdown) != null) {	
			int lvl = this.getPlayer().getActivePotionEffect(Potion.moveSlowdown).getAmplifier();
			if (lvl==0) {
				if (var1) {
					if (this.getSpeed()>15)
						return true;
				} else {
					if (this.getSpeed()>7.3)
						return true;
				}
			}						
		} else if (var1) {
			if (this.getSpeed()>10)
				return true;
		} else {
			if (this.getSpeed()>7.252725124359131)
				return true;
		}
		
		return false;
	}
}
