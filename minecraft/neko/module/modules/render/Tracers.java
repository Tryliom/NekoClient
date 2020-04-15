package neko.module.modules.render;

import neko.Client;
import neko.guicheat.clickgui.settings.Setting;
import neko.manager.ModuleManager;
import neko.module.Category;
import neko.module.Module;
import neko.module.modules.hide.Friends;
import neko.utils.RenderUtils;
import neko.utils.Utils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.INpc;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;

public class Tracers extends Module {
	public static float cR=0.99F;
	public static float cG=0.33F;
	public static float cB=0.33F;
	public static float width=2F;
	public static boolean friend=true;
	Client var = Client.getNeko();
	

	public static Float[] friends = Utils.getFloatColorFromInt(2090765); 
    public static Float[] enemies = Utils.getFloatColorFromInt(15278085); 
	public static boolean BoolENEMIES = true;
    public static Float[] mobs = Utils.getFloatColorFromInt(15165453); 
	public static boolean BoolMOBS = true;
    public static Float[] animals = Utils.getFloatColorFromInt(6473157); 
	public static boolean BoolANIMALS = true;
    public static Float[] golem = Utils.getFloatColorFromInt(10856100); 
	public static boolean BoolGOLEM = true;
    public static Float[] npc = Utils.getFloatColorFromInt(15557860);
	public static boolean boolNPC = true;
	
	public Tracers() {
		super("Tracers", -1, Category.RENDER, false);
	}
	
	public void onEnabled() {		
		super.onEnabled();
	}
	
	public void onDisabled() {
		super.onDisabled();
	}
	
	public void setValues() {
		this.values = "§6Epaisseur de ligne:§7 "+Tracers.width+"\n"
		+ "§6Friend: "+(Tracers.friend ? "§aAffiché" : "§cCaché");
	}
	
	@Override
	public void setup() {
		Client.getNeko().settingsManager.rSetting(new Setting("Tracers_Friends", this, this.friend));
		Client.getNeko().settingsManager.rSetting(new Setting("Tracers_Enemies", this, this.BoolENEMIES));
		Client.getNeko().settingsManager.rSetting(new Setting("Tracers_Mobs", this, this.BoolMOBS));
		Client.getNeko().settingsManager.rSetting(new Setting("Tracers_Animals", this, this.BoolANIMALS));
		Client.getNeko().settingsManager.rSetting(new Setting("Tracers_Golem", this, this.BoolGOLEM));
		Client.getNeko().settingsManager.rSetting(new Setting("Tracers_NPC", this, this.boolNPC));
		Client.getNeko().settingsManager.rSetting(new Setting("Tracers_Width", this, this.width, 1, 50, true));
	}
	
	public void onRender3D() {
		boolean bob = mc.gameSettings.viewBobbing;
		mc.gameSettings.viewBobbing = false;
		for (Entity en : mc.theWorld.loadedEntityList) { 
	        /*if (!entity.isDead && entity!=mc.thePlayer && entity.getDistanceToEntity(mc.thePlayer)<200 && !entity.getName().isEmpty() && (friend ? true : !Friends.isFriend(entity.getName())) && (var.mode.equals("Mob") ? !u.isPlayer(entity) : true)) {
	        	RenderUtils.drawTracerLine(u.getX(entity), u.getY(entity) + 1F, u.getZ(entity), cR, cG, cB, 0.5F, width);
	        }*/
			if (!(en instanceof EntityLivingBase)) { continue; }
			if (en instanceof EntityArmorStand) { continue; }
			if (!en.isDead && en!=mc.thePlayer && en.getDistanceToEntity(mc.thePlayer)<200) {
				if (en instanceof EntityPlayer) {
					if (Utils.IsInTab(((EntityPlayer) en).getName())) {
						if(Friends.isFriend(((EntityPlayer) en).getName())) {
							RenderUtils.drawTracerLine(u.getX(en), u.getY(en) + 1F, u.getZ(en), 
									friends[0], friends[1], friends[2], friend ? 0.5f : 0f, width);
						} else {
							RenderUtils.drawTracerLine(u.getX(en), u.getY(en) + 1F, u.getZ(en), 
									enemies[0], enemies[1], enemies[2], BoolENEMIES ? 0.5f : 0f, width);
						}
					} else {
						RenderUtils.drawTracerLine(u.getX(en), u.getY(en) + 1F, u.getZ(en), 
								npc[0], npc[1], npc[2], boolNPC ? 0.5f : 0f, width);
					}
				}
				if (en instanceof IMob) {
					RenderUtils.drawTracerLine(u.getX(en), u.getY(en) + 1F, u.getZ(en), 
							mobs[0], mobs[1], mobs[2], BoolMOBS ? 0.5f : 0f, width);
				}
				if (en instanceof EntityAnimal) {
					RenderUtils.drawTracerLine(u.getX(en), u.getY(en) + 1F, u.getZ(en), 
							animals[0], animals[1], animals[2], BoolANIMALS ? 0.5f : 0f, width);
				}
				if (en instanceof INpc || en instanceof EntityIronGolem) {
					RenderUtils.drawTracerLine(u.getX(en), u.getY(en) + 1F, u.getZ(en), 
							golem[0], golem[1], golem[2], BoolGOLEM ? 0.5f : 0f, width);
				}
			}
        }
		mc.gameSettings.viewBobbing = bob;
	}
}
