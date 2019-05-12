package neko.module;

import java.util.ArrayList;

import neko.Client;
import neko.event.UpdateEvent;
import neko.gui.InGameGui;
import neko.manager.ModuleManager;
import neko.utils.ChatUtils;
import neko.utils.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.network.play.client.C03PacketPlayer;

public class Module {
	protected Minecraft mc = Minecraft.getMinecraft();
	
	protected static Utils u;
	protected Client var = Client.getNeko();
	protected InGameGui g;
	private String moduleName;
	private int moduleBind;
	private Category moduleCategory;
	protected boolean isToggled;
	protected ScaledResolution scaled = new ScaledResolution(mc);
	protected ArrayList<String> cmd = new ArrayList<>();
	protected String values;
	protected int time;
	public int toggleTime = 0;

	public Module(String moduleName, int moduleBind, Category moduleCategory) {
		this.moduleName = moduleName;
		this.moduleBind = moduleBind;
		this.moduleCategory = moduleCategory;
		setup();
	}
	
	public void setup() {}

	public String getName() {
		return this.moduleName;
	}
	
	public void setName(String modulename) {
		this.moduleName = modulename;
	}

	public int getBind() {
		return this.moduleBind;
	}

	public Category getCategory() {
		return this.moduleCategory;
	}
	
	public void setCategory(Category category) {
		this.moduleCategory = category;
	}

	public boolean getToggled() {
		return this.isToggled;
	}
	
	public void toggle() {
		if (this.getToggled()) {
			this.setToggled(false);
		} else {
			this.setToggled(true);
		}
	}

	public void incrementTime() {
		this.time++;
	}
	
	public int getTimeStat() {
		return this.time;
	}
	
	public void setTime(int time) {
		this.time = time;
	}
	
	public void setToggled(boolean shouldToggle) {
		onToggle();
		if (shouldToggle) {
			Utils.checkQuest(this.getName());
			onEnabled();
			this.isToggled = true;
		} else {
			onDisabled();
			this.isToggled = false;
		}
		if (this.cmd.size() != 0) {
			for (String s : this.cmd) {
				Boolean b = false;
				if (s.startsWith(var.prefixCmd)) {
					s = s.substring(0, s.length() - 1);
					String name = s.replaceFirst(var.prefixCmd, "");
					for (Module m : ModuleManager.ActiveModule) {
						if (m.getName().equalsIgnoreCase(name) && s.equalsIgnoreCase(var.prefixCmd + name))
							b = true;
					}
				}
				if (b) {
					Utils.toggleModule(s.replaceFirst(var.prefixCmd, ""));
				} else {
					new ChatUtils().doCommand(s);
				}
			}
		}
	}

	public void toggleModule() {
		if (var.onlyrpg.isActive()) {
			boolean valid = false;
			for (String cheat : var.onlyrpg.getCheat()) {
				if (this.getName().equalsIgnoreCase(cheat)) {
					valid = true;
				}
			}
			if (!valid) {
				return;
			}
		}
		setToggled(!getToggled());
	}

	public void setWithoutToggle(boolean isToggle) {
		this.isToggled = isToggle;
	}

	public void setBind(int newBind) {
		this.moduleBind = newBind;
	}

	public void addCmd(String s) {
		this.cmd.add(s);
	}

	public Boolean isCmd() {
		return this.cmd.size() == 0 ? false : true;
	}

	public ArrayList<String> getCmd() {
		return this.cmd;
	}

	public void onToggle() {
		toggleTime = 0;
	}

	public void onEnabled() {

		if (Utils.shouldChat(this))
			Utils.addChat("§a§o" + getName() + " activé !");
		Client.getNeko().eventManager.register(this);

	}

	public void onDisabled() {

		if (Utils.shouldChat(this))
			Utils.addChat("§c§o" + getName() + " désactivé !");
		Client.getNeko().eventManager.unregister(this);

	}

	public String getValues() {
		this.setValues();
		return this.values;
	}

	/**
	 * Est appelée chaques tick (20/sec)
	 */
	public void onUpdate() {
		toggleTime++;
	}

	/**
	 * Est appelée chaques tick (20/sec) mais pas besoin que le module soit activé
	 * pour qu'il soit exec
	 */
	public void onAction() {
	}
	
	public void onLateUpdate() {

	}

	public void onRender() {

	}

	public void onRender3D() {
	}

	public void onRender3DA() {
	}

	public void onRender2D() {
	}

	public void onRender2DA() {
	}

	public void onClick() {
	}

	public void onRightClick() {
	}

	public void onUpdate(UpdateEvent event) {
	}

	public void setValues() {
	}
}
