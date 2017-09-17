package neko.module.modules.player;

import java.util.Iterator;

import neko.module.Category;
import neko.module.Module;
import neko.module.modules.movements.Safewalk;
import net.minecraft.block.BlockAir;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.Vec3;

public class Scaffold extends Module {
	private transient int delay = 0;
	private static Scaffold instance;
	
	public Scaffold() {
		super("Scaffold", -1, Category.PLAYER);
		instance=this;
	}
	
	public static Scaffold getScaffold() {
		return instance;
	}
	
	public void onEnabled() {		
		super.onEnabled();
	}
	
	public void onDisabled() {
		Safewalk.isOn=false;
		super.onDisabled();
	}		
	
	public void onUpdate() {
		BlockPos b = new BlockPos(mc.thePlayer.posX, mc.thePlayer.posY-1, mc.thePlayer.posZ);
		if (!mc.thePlayer.isSneaking()) {			
			try {
				Item i = mc.thePlayer.getCurrentEquippedItem().getItem();
				if (!(i instanceof ItemBlock) || i.isDamageable()) {
					Safewalk.isOn=false;
					return;
				}
			} catch (Exception e) {
				return;
			}
			
         EntityPlayerSP p = mc.thePlayer;
         if(this.delay > 0) {
            --this.delay;
         }

         if(this.delay <= 0) {
        	Safewalk.isOn=true;
        	java.util.ArrayList blockStandOn = this.getSurroundingBlocks();
            java.util.ArrayList collisionBlocks = this.getCollisionBlocks();
            Iterator var5 = collisionBlocks.iterator();

            while(var5.hasNext()) {
               BlockPos posCollision = (BlockPos)var5.next();
               IBlockState blockOnCollision = p.worldObj.getBlockState(posCollision);
               if(blockOnCollision.getBlock() instanceof BlockAir) {
                  Iterator var8 = blockStandOn.iterator();

                  while(var8.hasNext()) {
                     BlockPos posStandOn = (BlockPos)var8.next();
                     IBlockState blockInReach = p.worldObj.getBlockState(posStandOn);
                     if(!(blockInReach.getBlock() instanceof BlockAir) && !posStandOn.equals(posCollision) && posStandOn.offset(p.func_174811_aO()).equals(posCollision)) {
                        p.sendQueue.netManager.sendPacket(new C03PacketPlayer.C05PacketPlayerLook(mc.thePlayer.rotationYaw, 90.0F, true));
                        if(mc.playerController.func_178890_a(mc.thePlayer, mc.theWorld, p.inventory.getCurrentItem(), posStandOn.add(0, 0, 0), p.func_174811_aO(), new Vec3((double)posStandOn.getX(), (double)posStandOn.getY(), (double)posStandOn.getZ()))) {
                           p.swingItem();
                           break;
                        }
                     }
                  }
               }
            }
         }
		}
	}

	   private java.util.ArrayList getCollisionBlocks() {
		   	  java.util.ArrayList collisionBlocks = new java.util.ArrayList();
		      EntityPlayerSP p = mc.thePlayer;
		      BlockPos var1 = new BlockPos(p.getEntityBoundingBox().minX + 0.1D, p.getEntityBoundingBox().minY - 0.001D, p.getEntityBoundingBox().minZ + 0.1D);
		      BlockPos var2 = new BlockPos(p.getEntityBoundingBox().maxX - 0.1D, p.getEntityBoundingBox().maxY + 0.001D, p.getEntityBoundingBox().maxZ - 0.1D);

		      for(int var3 = var1.getX(); var3 <= var2.getX(); ++var3) {
		         for(int var4 = var1.getY(); var4 <= var2.getY(); ++var4) {
		            for(int var5 = var1.getZ(); var5 <= var2.getZ(); ++var5) {
		               BlockPos blockPos = new BlockPos(var3, var4, var5);
		               p.worldObj.getBlockState(blockPos);

		               try {
		                  if((double)var4 > p.posY - 2.0D && (double)var4 <= p.posY - 1.0D) {
		                     collisionBlocks.add(blockPos);
		                  }
		               } catch (Throwable var11) {
		                  ;
		               }
		            }
		         }
		      }

		      return collisionBlocks;
		   }

		   private java.util.ArrayList getSurroundingBlocks() {
			   java.util.ArrayList blocksStandOn = new java.util.ArrayList();
		      EntityPlayerSP p = mc.thePlayer;

		      for(int var3 = (int)p.posX - 1; var3 <= (int)p.posX + 1; ++var3) {
		         for(int var4 = (int)p.posY - 1; var4 <= (int)p.posY; ++var4) {
		            for(int var5 = (int)p.posZ - 1; var5 <= (int)p.posZ + 1; ++var5) {
		               if((double)var3 == Math.floor(p.posX) || (double)var5 == Math.floor(p.posZ)) {
		                  BlockPos blockPos = new BlockPos(var3, var4, var5);

		                  try {
		                     if((double)var4 > p.posY - 2.0D && (double)var4 <= p.posY - 1.0D) {
		                        blocksStandOn.add(blockPos);
		                     }
		                  } catch (Throwable var8) {
		                     ;
		                  }
		               }
		            }
		         }
		      }

		      return blocksStandOn;
		   }
}








