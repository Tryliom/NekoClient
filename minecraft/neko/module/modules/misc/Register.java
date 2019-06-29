package neko.module.modules.misc;

import neko.module.Category;
import neko.module.Module;
import neko.utils.ChatUtils;

public class Register extends Module {
	private static Register instance;
	private String mdp = "neko123";
	private boolean isCommands = true;
	
	public Register() {
		super("Register", -1, Category.MISC, true);
		this.instance=this;
	}
	
	public static Register getReg() {
		return instance;
	}
	
	public void setValues() {
		this.values = "�6Mot de passe:�7 "+mdp;
	}
	
	public void CommandAction() {
		ChatUtils c = new ChatUtils();
		c.doCommand("/register "+mdp+" "+mdp);
		c.doCommand("/login "+mdp);
	}

	public String getMdp() {
		return mdp;
	}

	public void setMdp(String mdp) {
		this.mdp = mdp;
	}
		
	
}
