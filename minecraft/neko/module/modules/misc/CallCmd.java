package neko.module.modules.misc;

import java.util.Vector;

import neko.module.Category;
import neko.module.Module;
import neko.utils.ChatUtils;
import neko.utils.Utils;

public class CallCmd extends Module {
	private Vector<String> listPlayer = new Vector<String>();
	private String cmd = "";
	private int actualTime;
	private static CallCmd instance;
	
	public CallCmd() {
		super("CallCmd", -1, Category.OTHER);
		this.instance=this;
	}
	
	public static CallCmd getCall() {
		return instance;
	}
	
	public void onEnabled() {		
		super.onEnabled();
	}
	
	public void onDisabled() {
		super.onDisabled();
	}
	
	public void onUpdate() {		
		if (actualTime>=40) {
			for (String s : this.listPlayer) {
				if (Utils.IsInTab(s) && mc.currentScreen==null) {
					new ChatUtils().doCommand(this.cmd);
					actualTime=0;
					return;
				}
			}
		} else {
			actualTime++;
		}
	}

	public Vector<String> getListPlayer() {
		return listPlayer;
	}

	public String getCmd2() {
		return this.cmd;
	}

	public void setCmd2(String cmd) {
		this.cmd = cmd;
	}
	
}
