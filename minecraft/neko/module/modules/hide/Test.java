package neko.module.modules.hide;

import neko.module.Category;
import neko.module.Module;
import neko.utils.Utils;

public class Test extends Module {
	private static Test instance;
	
	public Test() {
		super("Test", -1, Category.HIDE);
		this.instance=this;
	}
	
	public static Test getTest() {
		return instance;
	}
	
	public void onEnabled() {

        super.onEnabled();
	}
	
	public void onDisabled() {
		super.onDisabled();
	}
	
	public void onAction() {
	}
}		