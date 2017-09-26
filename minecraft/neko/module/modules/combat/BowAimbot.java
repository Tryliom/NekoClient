package neko.module.modules.combat;

import java.util.Vector;

import neko.module.Category;
import neko.module.Module;
import neko.module.modules.hide.Friends;
import neko.module.other.enums.BowMode;
import neko.utils.Utils;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemFood;

public class BowAimbot extends Module {
	private EntityLivingBase currEn=null;
	private Vector<EntityLivingBase> list = new Vector<EntityLivingBase>();
	private Double fov=30.0;
	private BowMode life=BowMode.Désactivé;
	private BowMode armor=BowMode.Désactivé;
	private BowMode distance=BowMode.Désactivé;
	private static BowAimbot instance = null;
	
	
	public BowAimbot() {
		super("BowAimbot", -1, Category.COMBAT);
		this.instance=this;
	}
	
	public static BowAimbot getAim() {
		return instance;
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
		for (Object o : (var.mode.equalsIgnoreCase("Player") ? mc.theWorld.playerEntities : mc.theWorld.loadedEntityList)) {
			if (o instanceof EntityLivingBase) {
				EntityLivingBase en = (EntityLivingBase) o;           
	            // Checks
				list.add(en);
			}
        }
		for (EntityLivingBase en : this.list) {          
            if (this.isValid(en)) {
            	if (currEn!=null && currEn==en)
            		valid=true;
            	else if (currEn==null)
            		valid=true;
            	currEn=en;
            	Utils.faceBowEntityClient(en);
            }
        }
		list.clear();
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
		if (!this.list.contains(en))
			return false;
		return true;
	}
	
	private void sortEntity() {
		Vector<EntityLivingBase> del = new Vector<EntityLivingBase>();
		for (EntityLivingBase en : list) {
			// checks
		}
		for (EntityLivingBase en : del) {
			list.remove(en);
		}
		del.clear();
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

	public Double getFov() {
		return fov;
	}

	public void setFov(Double fov) {
		this.fov = fov;
	}

	public BowMode getLife() {
		return life;
	}

	public void setLife(BowMode life) {
		this.life = life;
	}

	public BowMode getArmor() {
		return armor;
	}

	public void setArmor(BowMode armor) {
		this.armor = armor;
	}

	public BowMode getDistance() {
		return distance;
	}

	public void setDistance(BowMode distance) {
		this.distance = distance;
	}

}
