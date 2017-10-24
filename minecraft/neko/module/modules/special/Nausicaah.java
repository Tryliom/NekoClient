package neko.module.modules.special;

import neko.module.Category;
import neko.module.Module;
import neko.module.modules.combat.AutoSword;
import neko.utils.Utils;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.network.play.client.C02PacketUseEntity;
import net.minecraft.network.play.client.C02PacketUseEntity.Action;

public class Nausicaah extends Module {
	private static Nausicaah instance;
	
	public Nausicaah() {
		super("Nausicaah", -1, Category.Special);
		this.instance=this;
	}
	
	public static Nausicaah getNausi() {
		return instance;
	}
	
	public void onEnabled() {		
		super.onEnabled();
	}
	
	public void onDisabled() {
		super.onDisabled();
	}
	
	public void onClick() {
		try {
			if (mc.pointedEntity!=null) {
				doNausicaah(mc.pointedEntity);
			}
		} catch (Exception e) {}
	}
	
	public void doNausicaah(Entity en) {
		try {
			int i = AutoSword.getSword().getSwordFromInventory();
			int j = mc.thePlayer.inventory.currentItem;
			if (i!=-1) {				
				FastDura.swap(j, i);
				FastDura.attack(en, true);
				FastDura.swap(j, i);
				FastDura.attack(en, false);
			}
		} catch (Exception e) {}
	}
}
