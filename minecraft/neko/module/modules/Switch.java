package neko.module.modules;

import neko.module.Category;
import neko.module.Module;
import neko.utils.Utils;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.ItemSword;
import net.minecraft.network.play.client.C09PacketHeldItemChange;

public class Switch extends Module {
	public Switch() {
		super("Switch", -1, Category.OTHER);
	}
	
	public void onEnabled() {		
		if (u.display)
		u.addChat("�a�oSwitch activ� !");
		super.onEnabled();
	}
	
	public void onDisabled() {
		if (u.display)
		u.addChat("�c�oSwitch d�sactiv� !");
		super.onDisabled();
	}
	
	public void onUpdate() {
		mc.thePlayer.inventory.currentItem=(mc.thePlayer.inventory.currentItem==8 ? 0 : mc.thePlayer.inventory.currentItem+1);
		mc.getNetHandler().addToSendQueue(new C09PacketHeldItemChange(mc.thePlayer.inventory.currentItem));
	}
}
