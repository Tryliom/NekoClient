package neko.utils;

import java.net.Proxy;
import java.net.URL;
import java.util.Scanner;

import com.mojang.authlib.Agent;
import com.mojang.authlib.UserAuthentication;
import com.mojang.authlib.exceptions.AuthenticationException;
import com.mojang.authlib.yggdrasil.YggdrasilAuthenticationService;

import neko.module.modules.Friends;
import net.mcleaks.MCLeaks;
import net.minecraft.client.Minecraft;
import net.minecraft.util.Session;

public class LoginThread extends Thread {
	Minecraft mc = Minecraft.getMinecraft();
	private String user;
	private String mdp;
	private boolean useGui;
	private boolean useBdd;
	private int id;
	
	public LoginThread(String user, String pass) {
		this.user=user;
		this.mdp=pass;
	}
	
	public LoginThread(String user, String pass, boolean useGui) {
		this.user=user;
		this.mdp=pass;
		this.useGui=useGui;
	}
	
	public LoginThread(String user, String pass, boolean useBdd, int id) {
		this.user=user;
		this.mdp=pass;
		this.useBdd=useBdd;
		this.id=id;
	}
	
	public void run() {
		YggdrasilAuthenticationService authService = new YggdrasilAuthenticationService(mc.getProxy(), "");
	    UserAuthentication auth = authService.createUserAuthentication(Agent.MINECRAFT);
	    if (mdp.isEmpty()) {
	    	mc.session = new Session(user, "", "", "mojang");
			Utils.addChat("§aConnecté en tant que "+mc.session.getUsername()+" !");
			Utils.addChat("§aVeuillez deco-reco pour le changement");
			if (!Friends.isFriend(mc.session.getUsername()))
				Friends.friend.add(mc.session.getUsername());
			if (MCLeaks.isAltActive())
				MCLeaks.remove();
	    } else {
		    auth.setUsername(user);
		    auth.setPassword(mdp);
		    try {
				auth.logIn();
				Minecraft.getMinecraft().session = new Session(auth.getSelectedProfile().getName(), 
				auth.getSelectedProfile().getId().toString(), auth.getAuthenticatedToken(), "mojang");
				Utils.addChat("§aConnecté en tant que "+mc.session.getUsername()+" !");
				Utils.addChat("§aVeuillez deco-reco pour le changement");
				if (!Friends.isFriend(mc.session.getUsername()))
					Friends.friend.add(mc.session.getUsername());
				if (MCLeaks.isAltActive())
					MCLeaks.remove();
				
				if (useBdd) {
					try {
						URL url = new URL("http://neko.alwaysdata.net/CommanderSQL/main.php?token=644707c473fed1a0c5b533830c7989ca&args=\""+id+"\"");
						Scanner sc = new Scanner(url.openStream());		
						sc.close();
					} catch (Exception e) {
						Utils.addChat("Connexion avec la base de donnée a échouée...");
					}
				}
			} catch (AuthenticationException e) {
				if (useBdd) {
					// Mettre le compte à 0
					try {
						URL url = new URL("http://neko.alwaysdata.net/CommanderSQL/main.php?token=f7527e287abdc03c8eca4b71fece3179&args=\""+id+"\"");
						Scanner sc = new Scanner(url.openStream());		
						sc.close();
					} catch (Exception ex) {
						Utils.addChat("Connexion avec la base de donnée a échouée...");
					}
				}
				Utils.addChat("§cErreur: Email/Username ou mdp incorrect");
			} catch (Exception e) {
				Utils.addChat(e.getMessage());
			}
	    }
	    // ça s'arrête directement après
	}
}
