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
	private static BowAimbot instance = null;
	
	
	public BowAimbot() {
		super("BowAimbot", -1, Category.COMBAT, false);
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
	
	public void setValues() {
		this.values = "§6Fov:§7 "+getFov()+"°\n"
				+ "§6Mode vie:§7 "+getLife()+"\n"
				+ "§6Mode armure:§7 "+getArmor();
	}
	
	public void onUpdate() {
		try {
			Item i = mc.thePlayer.getCurrentEquippedItem().getItem();
			if ((i instanceof ItemBlock) || ((i instanceof ItemFood) && mc.thePlayer.getFoodStats().needFood()))
				return;
		} catch (Exception e) {
			return;
		}
		if (!this.haveBow())
			return;
		this.aimBow();
	}
	
	private boolean isValid(EntityLivingBase en) {
		if ((Utils.isToggle("Fastbow") && Fastbow.getFast().isNobow()) ? false : !this.haveBow())
			return false;
		if (!(currEn==null ? Utils.isInFov(en, fov) : en==currEn))
			return false;
		if (Friends.isFriend(en.getName()))
			return false;
		if (mc.thePlayer.getName().equalsIgnoreCase(en.getName()) || en.getName().isEmpty())
			return false;
		if (var.mode.equalsIgnoreCase("Mob") ? Utils.isPlayer(en) : false)
			return false;
		if (!mc.thePlayer.canEntityBeSeen(en))
			return false;
		return true;
	}
	
	public boolean haveBow() {
		try {
			Item i = mc.thePlayer.getCurrentEquippedItem().getItem();
			if ((i instanceof ItemBlock) || ((i instanceof ItemFood) && mc.thePlayer.getFoodStats().needFood()))
				return false;
		} catch (Exception e) {
			return false;
		}
		if (!mc.thePlayer.isUsingItem()) {
			return false;
		}
		return true;
	}
	
	public void aimBow() {
		EntityLivingBase best = null;
		boolean valid = false;
		for (Object o : (var.mode.equalsIgnoreCase("Player") ? mc.theWorld.playerEntities : mc.theWorld.loadedEntityList)) {
			if (o instanceof EntityLivingBase) {
				EntityLivingBase en = (EntityLivingBase) o;           
				if (!this.isValid(en)) {
					continue;
				}
				if (best==null) {
					best=en;
					continue;
				}
				boolean heal = !this.life.name().equalsIgnoreCase("Désactivé");
				boolean arm = !this.armor.name().equalsIgnoreCase("Désactivé");
				if (heal) {
					if (this.life==BowMode.Max ? best.getHealth()<en.getHealth() : best.getHealth()>en.getHealth()) {
						best = en;
					} else if (this.armor!=BowMode.Désactivé) {
						if (this.armor==BowMode.Max ? best.getTotalArmorValue()<en.getTotalArmorValue() : best.getTotalArmorValue()>en.getTotalArmorValue()) {
							best = en;
						}
					}
				} else if (arm) {
					if (this.armor==BowMode.Max ? best.getTotalArmorValue()<en.getTotalArmorValue() : best.getTotalArmorValue()>en.getTotalArmorValue()) {
						best = en;
					}
				}
			}
        }
    	currEn=best;
    	if (currEn!=null)
    		Utils.faceBowEntityClient(best);
		currEn=null;
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
}
