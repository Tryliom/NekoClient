package neko.module.modules.combat;

import neko.module.Category;
import neko.module.Module;
import neko.module.modules.combat.KillAura;
import neko.utils.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemSword;
import net.minecraft.util.MathHelper;

public class SmoothAim extends Module {
	Minecraft mc = Minecraft.getMinecraft();
	public static double speed=5;
	public static double degrees=45;
	public static double range=4.5; 
	private EntityLivingBase target;
	
	public SmoothAim() {
		super("SmoothAim", -1, Category.COMBAT, false);
	}
	
	public void onEnabled() {
		this.target = null;
		super.onEnabled();
	}
	
	public void onDisabled() {
		super.onDisabled();
	}
	
	public void setValues() {
		this.values = "?6Range:?7 "+range+"\n"
				+ "?6Speed:?7 "+speed+"\n"
				+ "?6Fov:?7 "+degrees+"?";
	}
	
	public void onUpdate() {
		for (Object o : mc.theWorld.loadedEntityList) {
	        if ((o instanceof EntityLivingBase))
	        {
	          EntityLivingBase entity = (EntityLivingBase)o;
	          if ((isEntityValid(entity)) && (u.sword ? mc.thePlayer.getCurrentEquippedItem()!=null ? mc.thePlayer.getCurrentEquippedItem().getItem() instanceof ItemSword : false : true)) {
	            this.target = entity;
	          } else {
	            this.target = null;
	          }
	          if (this.target != null)
	          {
	            EntityPlayerSP tmp98_95 = mc.thePlayer;
	            tmp98_95.rotationPitch = ((float)(tmp98_95.rotationPitch + getPitchChange(this.target) / (this.speed+1*Math.random())));
	            EntityPlayerSP tmp125_122 = mc.thePlayer;
	            tmp125_122.rotationYaw = ((float)(tmp125_122.rotationYaw + getYawChange(this.target) / (this.speed+1*Math.random())));
	          }
	        }
	      }
	
	}
	
	 public float getPitchChange(Entity entity)
	  {
	    double deltaX = entity.posX - mc.thePlayer.posX;
	    double deltaZ = entity.posZ - mc.thePlayer.posZ;
	    double deltaY = entity.posY - 2.2D + entity.getEyeHeight() - mc.thePlayer.posY;
	    double distanceXZ = MathHelper.sqrt_double(deltaX * deltaX + deltaZ * deltaZ);
	    double pitchToEntity = -Math.toDegrees(Math.atan(deltaY / distanceXZ));
	    return -MathHelper.wrapAngleTo180_float(mc.thePlayer.rotationPitch - (float)pitchToEntity) - 
	      2.5F;
	  }
	  
	  public float getYawChange(Entity entity)
	  {
	    double deltaX = entity.posX - mc.thePlayer.posX;
	    double deltaZ = entity.posZ - mc.thePlayer.posZ;
	    double yawToEntity = 0.0D;
	    if ((deltaZ < 0.0D) && (deltaX < 0.0D)) {
	      yawToEntity = 90.0D + Math.toDegrees(Math.atan(deltaZ / deltaX));
	    } else if ((deltaZ < 0.0D) && (deltaX > 0.0D)) {
	      yawToEntity = -90.0D + Math.toDegrees(Math.atan(deltaZ / deltaX));
	    } else {
	      yawToEntity = Math.toDegrees(-Math.atan(deltaX / deltaZ));
	    }
	    return MathHelper.wrapAngleTo180_float(-(mc.thePlayer.rotationYaw - (float)yawToEntity));
	  }
	  
	  public boolean isEntityValid(Entity entity) {
		  
		  	if (Utils.isInGui())
		  		return false;
		  
			if ((entity instanceof EntityLivingBase)) {
				EntityLivingBase entityLiving = (EntityLivingBase)entity;
				if (mc.thePlayer.isEntityAlive()) {
					  if (!mc.thePlayer.canEntityBeSeen(entityLiving))
						  return false;
						  	
					  if (mc.thePlayer.getDistanceToEntity(entity) > this.range)
						  return false;	    	         
				    
				      if (!u.isEntityInFov(entity, this.degrees))
				    	  return false;
				      if ((entityLiving instanceof EntityPlayer) && entityLiving!=mc.thePlayer && entityLiving.getName()!=mc.session.getUsername()) {
				    	  EntityPlayer entityPlayer = (EntityPlayer)entityLiving;
				    	  return KillAura.isViable(entityLiving);
				      }
				}		
			}
			return false;
		}	  
	  
	
}
