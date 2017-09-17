package neko.module;

import java.util.ArrayList;

import neko.Client;
import neko.event.UpdateEvent;
import neko.gui.InGameGui;
import neko.module.modules.hide.Friends;
import neko.module.modules.hide.Gui;
import neko.module.modules.hide.Lot;
import neko.module.modules.hide.Plugins;
import neko.module.modules.misc.Register;
import neko.module.modules.player.Fire;
import neko.module.modules.render.Power;
import neko.module.modules.render.Render;
import neko.module.modules.render.Water;
import neko.module.modules.special.VanillaTp;
import neko.utils.ChatUtils;
import neko.utils.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;

public class Module 
{
	protected Minecraft mc = Minecraft.getMinecraft();
	protected static Utils u;
	protected Client var = Client.getNeko();
	protected InGameGui g;
	protected String moduleName;
	protected int moduleBind;
	protected Category moduleCategory;
	protected boolean isToggled;
	protected ScaledResolution scaled = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);
	protected ArrayList<String> cmd = new ArrayList<>();
	  
	  public Module(String moduleName, int moduleBind, Category moduleCategory)
	  {
	    this.moduleName = moduleName;
	    this.moduleBind = moduleBind;
	    this.moduleCategory = moduleCategory;
	  }
	  
	  public String getName()
	  {
	    return this.moduleName;
	  }
	  
	  public int getBind()
	  {
	    return this.moduleBind;
	  }
	  
	  public Category getCategory()
	  {
	    return this.moduleCategory;
	  }
	  
	  public boolean getToggled()
	  {
	    return this.isToggled;
	  }
	  
	  public void setToggled(boolean shouldToggle)
	  {		  
	    onToggle();
	    if (shouldToggle)
	    {
	      onEnabled();
	      this.isToggled = true;
	    }
	    else
	    {
	      onDisabled();
	      this.isToggled = false;
	    }
	    if (this.cmd.size()!=0) {
	    	for (String s : this.cmd) {
	    		Boolean b = false;
	    		if (s.startsWith(var.prefixCmd)) {
	    			s = s.substring(0, s.length()-1);
	    			String name = s.replaceFirst(var.prefixCmd, "");
	    			for (Module m : ModuleManager.ActiveModule) {
	    				if (m.getName().equalsIgnoreCase(name) && s.equalsIgnoreCase(var.prefixCmd+name)) 
	    					b = true;
	    			}
	    		}
	    		if (b) {
	    			Utils.toggleModule(s.replaceFirst(var.prefixCmd, ""));
	    		} else
	    			new ChatUtils().doCommand(s);
	    	}
	    }
	  }
	  
	  public void toggleModule()
	  {
		  if (var.onlyrpg.isActive()) {
				boolean valid=false;
				for (String cheat : var.onlyrpg.getCheat()) {
					if (this.getName().equalsIgnoreCase(cheat)) {
						valid=true;
					}
				}
				if (!valid) {
					return;
				}
		  }
	    setToggled(!getToggled());
	  }
	  
	  public void setWithoutToggle(boolean isToggle) {
		  this.isToggled=isToggle;
	  }
	  
	  public void setBind(int newBind)
	  {
	    this.moduleBind = newBind;
	  }
	  
	  public void addCmd(String s) {
		  this.cmd.add(s);
	  }
	  
	  public Boolean isCmd() {
		  return this.cmd.size()==0 ? false : true;
	  }
	  
	  public ArrayList<String> getCmd() {
		  return this.cmd;
	  }
	  
	  public void onToggle() {}

	public void onEnabled() {

		if(Utils.shouldChat(this))
			Utils.addChat("§a§o" + getName() + " activé !");

	}

	public void onDisabled() {

		if(Utils.shouldChat(this))
			Utils.addChat("§c§o" + getName() + " désactivé !");

	}

	  public void onUpdate() {}
	  
	  public void onAction() {}
	  
	  public void onRender3D() {}
	  
	  public void onRender3DA() {}
	  
	  public void onRender2D() {}
	  
	  public void onRender2DA() {}
	  
	  public void onClick() {}
	  
	  public void onRightClick() {}
	  
	  public void onUpdate(UpdateEvent event) {}

}
