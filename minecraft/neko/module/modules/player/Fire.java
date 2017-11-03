package neko.module.modules.player;

import org.lwjgl.input.Keyboard;

import neko.Client;
import neko.module.Category;
import neko.module.Module;
import neko.utils.RenderUtils;
import neko.utils.Utils;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFireworkSparkFX;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumParticleTypes;

public class Fire extends Module {
	public static boolean onLvl=false;
	public static int count=0;
	public static int p=1;
	public static int i=0;
	public static java.util.ArrayList<BlockPos> list = new java.util.ArrayList<>();
	public static boolean signPlus=false;
	public static double large=0.5;

	public Fire() {
		super("Fire", -1, Category.RENDER);
	}
	
	public void onEnabled() {
		super.onEnabled();
	}
	
	public void onDisabled() {
		super.onDisabled();
	}
	
	public void setValues() {
		this.values = "";
	}
	
	public void onRender3DA() {
		if (list.size()!=0 && !onLvl) {
			list.clear();
			i=0;
		}
		if (onLvl) {
			if (!Client.getNeko().animation)
				Utils.t.stop();
			i++;			
			BlockPos bl = new BlockPos(mc.thePlayer.posX+u.getRandInt(20)-10, mc.thePlayer.posY+u.getRandInt(10)-5, mc.thePlayer.posZ+u.getRandInt(20)-10);
			boolean a=true;
			if (mc.theWorld.getBlockState(bl).getBlock().getMaterial() != Material.air)
				a=false;			
			if (a) {
				large=Math.random();
				list.add(bl);
			}
			
			if (Math.random()<0.01) {			
				mc.theWorld.playSound(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ, "random.pop", 0.5F, 0.5F, true);
			}
			

			mc.theWorld.spawnEntityInWorld(new EntityFireworkSparkFX(mc.theWorld, mc.thePlayer.posX, mc.thePlayer.posY+2, mc.thePlayer.posZ, Math.random()*0.1, 0.4*Math.random(), Math.random()*0.1, mc.effectRenderer));
			mc.theWorld.spawnEntityInWorld(new EntityFireworkSparkFX(mc.theWorld, mc.thePlayer.posX, mc.thePlayer.posY+2, mc.thePlayer.posZ, Math.random()*0.2, 0.4*Math.random(), Math.random()*0.1, mc.effectRenderer));			
				
			if (signPlus)
				large+=0.007;
			else
				large-=0.007;
			if (!list.isEmpty() && Math.random()<0.2) {
				for (BlockPos b : list) {
					BlockPos currentBlock=b;
					RenderUtils.drawOutlinedBlockESP(currentBlock.getX() - mc.getRenderManager().renderPosX+Math.random()*2-Math.random(), currentBlock.getY() - mc.getRenderManager().renderPosY, currentBlock.getZ() - mc.getRenderManager().renderPosZ+Math.random()*2-Math.random(), 0.22F, 0.77F, 0.55F, 1F, 2F, large, large, large);
					double neko = -1+Math.random()*2;									
					double nekoN = -1+Math.random()*2;
					double n = Math.random()-0.25;
					double nekoY = Math.random()*1.15+n;		
					mc.theWorld.spawnParticle(EnumParticleTypes.REDSTONE, currentBlock.getX()+neko, currentBlock.getY()+nekoY, currentBlock.getZ()+nekoN, 0, 0, 0, 0);
				}
			}
			if (large>=0.9 && signPlus) {
				signPlus=false;
			} else if (large<=0 && !signPlus) {
				signPlus=true;
			}
			
		}
			
		}
	}






