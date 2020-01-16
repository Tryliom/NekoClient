package neko.module.modules.misc;

import java.net.InetAddress;
import java.net.UnknownHostException;

import neko.module.Category;
import neko.module.Module;
import neko.utils.ChatUtils;
import neko.utils.Utils;
import net.minecraft.client.Minecraft;

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
		this.values = "§6Mot de passe:§7 "+mdp;
	}
	
	public void CommandAction() {
		ChatUtils c = new ChatUtils();
		// Check server, if ip of rinaorc, add rinaorcVerifNumber
		
		if (Utils.currentIP.startsWith("149.202.88") && !Utils.rinaorcVerifNumber.isEmpty()) {
			c.doCommand("/register "+mdp+" "+Utils.rinaorcVerifNumber);
			c.doCommand("/login "+mdp+" "+Utils.rinaorcVerifNumber);
		} else {
			c.doCommand("/register "+mdp+" "+mdp);
			c.doCommand("/login "+mdp);
		}
		this.setToggled(false);
	}

	public String getMdp() {
		return mdp;
	}

	public void setMdp(String mdp) {
		this.mdp = mdp;
	}
		
	
}
