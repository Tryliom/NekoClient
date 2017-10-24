package neko.module.modules.combat;

import neko.module.Category;
import neko.module.Module;
import neko.module.modules.hide.Friends;
import neko.utils.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;

public class ClickAim extends Module {
	public static float dist=3.8F;
	public static boolean multiAura=false;
	
	
	public ClickAim() {
		super("ClickAim", -1, Category.COMBAT);
	}
	
	public void onEnabled() {
		super.onEnabled();
	}
	
	public void onDisabled() {
		super.onDisabled();
	}
	
	public void onClick() {
		if (Utils.isSword()) {
    		for(Object o : mc.theWorld.playerEntities) {
                EntityPlayer en = (EntityPlayer) o;
                   
                if(Minecraft.getMinecraft().thePlayer.getDistanceToEntity(en) <= ClickAim.dist && en!=mc.thePlayer) {                    	
                    if(en.isEntityAlive() && en.ticksExisted > KillAura.live && Minecraft.getMinecraft().thePlayer.canEntityBeSeen(en) && !Friends.isFriend(en.getName())) {
                    	KillAura.faceEntity(en);
                    	Utils.attack(en);
                    	if (!ClickAim.multiAura)
                    		break;
                    	else 
                    		continue;
                    }
                }
            }
    	}
	}
	
	public static String getMulti() {
		return multiAura ? "on" : "off";
	}

}
