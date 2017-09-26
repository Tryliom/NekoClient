package neko.module.modules.combat;

import neko.module.Category;
import neko.module.Module;
import neko.module.modules.hide.Friends;
import neko.utils.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemFood;

public class BowAimbot extends Module {
	private EntityPlayer currEn=null;
	
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
		if (!mc.thePlayer.isUsingItem())
			currEn=null;
	}
	
	public void onRightClick() {
		for(Object o : mc.theWorld.playerEntities) {
            EntityPlayer en = (EntityPlayer) o;
               // test si il est à 30 de fov si oui viser lui
            try {
				Item i = mc.thePlayer.getCurrentEquippedItem().getItem();
				if ((i instanceof ItemBlock) || ((i instanceof ItemFood) && mc.thePlayer.getFoodStats().needFood()))
					return;
			} catch (Exception e) {
				return;
			}
            if(currEn==null ? Utils.isInFov(en, 30) : en==currEn && mc.thePlayer.isUsingItem() && Friends.isFriend(en.getName()) && mc.thePlayer!=en &&
            		Item.getIdFromItem(mc.thePlayer.getCurrentEquippedItem().getItem())==261) {  
            	currEn=en;
            	Utils.faceBowEntityClient(en);
            }
        }
	}

}
