package neko.module.modules.combat;

import neko.module.Category;
import neko.module.Module;
import neko.module.modules.hide.Friends;
import neko.utils.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemFood;

public class BowAimbot extends Module {
	private EntityLivingBase currEn=null;
	private Double fov=30.0;
	// Plus proche/éloignée
	// + de vie / - de vie
	// Celui qui a le moins d'enchant/ le moins d'enchant projectile prot
	
	
	
	public BowAimbot() {
		super("BowAimbot", -1, Category.COMBAT);
	}
	
	public void onEnabled() {
		super.onEnabled();
	}
	
	public void onDisabled() {
		super.onDisabled();
	}
	
	public void onUpdate() {
		if (!this.haveBow())
			return;
		boolean valid = false;
		for(Object o : (var.mode.equalsIgnoreCase("Player") ? mc.theWorld.playerEntities : mc.theWorld.loadedEntityList)) {
			if (o instanceof EntityLivingBase) {
				EntityLivingBase en = (EntityLivingBase) o;           
	            if (this.isValid(en)) {
	            	if (currEn!=null && currEn==en)
	            		valid=true;
	            	else if (currEn==null)
	            		valid=true;
	            	currEn=en;
	            	Utils.faceBowEntityClient(en);
	            }
			}
        }
		if (!valid)
			currEn=null;
	}
	
	private boolean isValid(EntityLivingBase en) {
		if (!this.haveBow())
			return false;
		if (!(currEn==null ? Utils.isInFov(en, fov) : en==currEn))
			return false;
		if (Friends.isFriend(en.getName()))
			return false;
		if (Utils.isPlayer(en))
			return false;
		if (Item.getIdFromItem(mc.thePlayer.getCurrentEquippedItem().getItem())!=261)
			return false;
		if (var.mode.equalsIgnoreCase("Mob") ? Utils.isPlayer(en) : false)
			return false;
		
		
		return true;
	}
	
	private boolean haveBow() {
		try {
			Item i = mc.thePlayer.getCurrentEquippedItem().getItem();
			if ((i instanceof ItemBlock) || ((i instanceof ItemFood) && mc.thePlayer.getFoodStats().needFood()))
				return false;
		} catch (Exception e) {
			return false;
		}
		if (!mc.thePlayer.isUsingItem()) {
			currEn=null;
			return false;
		}
		return true;
	}

}
