package neko.module.modules.player;

import java.util.ArrayList;

import neko.module.Category;
import neko.module.Module;
import neko.utils.RenderUtils;
import neko.utils.Utils;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.init.Blocks;
import net.minecraft.network.play.client.C07PacketPlayerDigging;
import net.minecraft.network.play.client.C07PacketPlayerDigging.Action;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.chunk.Chunk;

public class Nuker extends Module {
	Minecraft mc = Minecraft.getMinecraft();
	int delay =0;
	public int xPos = -1;
	  public int yPos = -1;
	  public int zPos = -1;
	  public static double nukerRadius = 5;
	  public float nukerR = 1.75F;
	  public float nukerG = 0.2F;
	  public float nukerB = 0.15F;
	  public static ArrayList<Integer> nuke = new ArrayList<Integer>();
	  public static boolean safe = true;

	private int delayNuker=0;
	
	public Nuker() {
		super("Nuker", -1, Category.PLAYER);
	}
	
	public void onEnabled() {		
		super.onEnabled();
	}
	
	public void onDisabled() {
		super.onDisabled();
	}	
	
	public void onUpdate() {	
		
		if (mc.gameSettings.keyBindPickBlock.getIsKeyPressed() && delay > 20 && mc.objectMouseOver.func_178782_a()!=null && !(mc.currentScreen instanceof GuiChat) && !(mc.currentScreen instanceof GuiInventory)) {
			BlockPos bl = mc.objectMouseOver.func_178782_a();
			int id = Block.getIdFromBlock(mc.theWorld.getBlockState(bl).getBlock());
			if (nuke.contains(id)) {
				for (int i=0;i<nuke.size();i++) {
					if (nuke.get(i)==id) {
						nuke.remove(i);
					}						
				}
				u.addChat("�cLe bloc "+Block.getBlockById(id).getLocalizedName()+" a �t� supprim� !");
			} else {
				nuke.add(id);
				u.addChat("�aLe bloc "+Block.getBlockById(id).getLocalizedName()+" a �t� ajout� !");
			}
			
			delay=0;
		} else
			delay++;
			
		
		if (this.delayNuker > 0) {
		      this.delayNuker -= 1;
		      return;
		}
				
		
		if (mc.thePlayer.capabilities.isCreativeMode) {
		    for (int y = (int)nukerRadius; y >= (int)-nukerRadius; y--) {
		      for (int z = (int)-nukerRadius; z <= nukerRadius; z++) {
		        for (int x = (int)-nukerRadius; x <= nukerRadius; x++)
		        {
		          this.xPos = ((int)Math.round(this.mc.thePlayer.posX + x));
		          this.yPos = ((int)Math.round(this.mc.thePlayer.posY + y));
		          this.zPos = ((int)Math.round(this.mc.thePlayer.posZ + z));
		          
		          BlockPos b = new BlockPos(this.xPos, this.yPos, this.zPos);
		          Chunk var2 = mc.theWorld.getChunkFromBlockCoords(b);
		          Block blockId = var2.getBlock(this.xPos, this.yPos, this.zPos);		          
		          
		          if ((blockId != Blocks.air) && (blockId != Blocks.flowing_water) && (blockId != Blocks.water) && (blockId != Blocks.flowing_lava) && (blockId != Blocks.lava)) {
		            Minecraft.getMinecraft().thePlayer.sendQueue.addToSendQueue(new C07PacketPlayerDigging(Action.START_DESTROY_BLOCK, b, EnumFacing.NORTH));
		            Minecraft.getMinecraft().thePlayer.sendQueue.addToSendQueue(new C07PacketPlayerDigging(Action.STOP_DESTROY_BLOCK, b, EnumFacing.NORTH));
		          }
		        }
		      }
		    }
		} else if (!mc.playerController.isSpectatorMode()) {
			// Survival Nuker
		    for (int y = (int)this.nukerRadius; y >= (int)-this.nukerRadius; y--) {
		      for (int z = (int)-this.nukerRadius; z <= this.nukerRadius; z++) {
		        for (int x = (int)-this.nukerRadius; x <= this.nukerRadius; x++)
		        {
		        	if (safe && x>=-1 && x<=1 && y==-1 && z>=-1 && z<=1)
			        	  continue;
		          this.xPos = ((int)Math.round(this.mc.thePlayer.posX + x));
		          this.yPos = ((int)Math.round(this.mc.thePlayer.posY + y));
		          this.zPos = ((int)Math.round(this.mc.thePlayer.posZ + z));
		          BlockPos b = new BlockPos(this.xPos, this.yPos, this.zPos);
		          Chunk var2 = mc.theWorld.getChunkFromBlockCoords(b);
		          Block blockId = var2.getBlock(this.xPos, this.yPos, this.zPos);		          
		          if ((blockId != Blocks.air) && (blockId != Blocks.bedrock) && (blockId != Blocks.flowing_water) && (blockId != Blocks.water) && (blockId != Blocks.flowing_lava) && (blockId != Blocks.lava)) {
		        	  if (u.verifBlock(blockId)) {
			        	  Minecraft.getMinecraft().thePlayer.sendQueue.addToSendQueue(new C07PacketPlayerDigging(Action.START_DESTROY_BLOCK, b, EnumFacing.NORTH));			        	  
			        	  Minecraft.getMinecraft().thePlayer.sendQueue.addToSendQueue(new C07PacketPlayerDigging(Action.STOP_DESTROY_BLOCK, b, EnumFacing.NORTH));
			        	  return;
		        	  }		        	  
		          }
		        }
		      }
		    }
		    this.delayNuker = 4;
		}	
	}	
	
	public void onRender3D() {
		if (this.delayNuker > 0) {
		      return;
		}
				
		
		if (mc.thePlayer.capabilities.isCreativeMode) {
		    for (int y = (int)nukerRadius; y >= (int)-nukerRadius; y--) {
		      for (int z = (int)-nukerRadius; z <= nukerRadius; z++) {
		        for (int x = (int)-nukerRadius; x <= nukerRadius; x++)
		        {
		        	if (safe && x>=-1 && x<=1 && y==-1 && z>=-1 && z<=1)
			        	  continue;
		          this.xPos = ((int)Math.round(this.mc.thePlayer.posX + x));
		          this.yPos = ((int)Math.round(this.mc.thePlayer.posY + y));
		          this.zPos = ((int)Math.round(this.mc.thePlayer.posZ + z));
		          	          
		          BlockPos b = new BlockPos(this.xPos, this.yPos, this.zPos);
		          Chunk var2 = mc.theWorld.getChunkFromBlockCoords(b);
		          Block blockId = var2.getBlock(this.xPos, this.yPos, this.zPos);		          
		          
		          if ((blockId != Blocks.air) && (blockId != Blocks.flowing_water) && (blockId != Blocks.water) && (blockId != Blocks.flowing_lava) && (blockId != Blocks.lava)) {
		            RenderUtils.drawOutlinedBlockESP(b.getX() - mc.getRenderManager().renderPosX, b.getY() - mc.getRenderManager().renderPosY, b.getZ() - mc.getRenderManager().renderPosZ, 0.99F, 0.33F, 0.33F, 0.2F, 5F, 1D, 1D, 1D);
		          }
		        }
		      }
		    }
		} else if (!mc.playerController.isSpectatorMode()) {
			// Survival Nuker
		    for (int y = (int)this.nukerRadius; y >= (int)-this.nukerRadius; y--) {
		      for (int z = (int)-this.nukerRadius; z <= this.nukerRadius; z++) {
		        for (int x = (int)-this.nukerRadius; x <= this.nukerRadius; x++)
		        {
		          this.xPos = ((int)Math.round(this.mc.thePlayer.posX + x));
		          this.yPos = ((int)Math.round(this.mc.thePlayer.posY + y));
		          this.zPos = ((int)Math.round(this.mc.thePlayer.posZ + z));
		          	          
		          BlockPos b = new BlockPos(this.xPos, this.yPos, this.zPos);
		          Chunk var2 = mc.theWorld.getChunkFromBlockCoords(b);
		          Block blockId = var2.getBlock(this.xPos, this.yPos, this.zPos);		          
		          if ((blockId != Blocks.air) && (blockId != Blocks.bedrock) && (blockId != Blocks.flowing_water) && (blockId != Blocks.water) && (blockId != Blocks.flowing_lava) && (blockId != Blocks.lava)) {
		        	  if (u.verifBlock(blockId)) {
		        		  RenderUtils.drawOutlinedBlockESP(b.getX() - mc.getRenderManager().renderPosX, b.getY() - mc.getRenderManager().renderPosY, b.getZ() - mc.getRenderManager().renderPosZ, 0.99F, 0.33F, 0.33F, 0.2F, 5F, 1D, 1D, 1D);
		        	  }		        	  
		          }
		        }
		      }
		    }
		}	
	}
	
	}