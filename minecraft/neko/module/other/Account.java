package neko.module.other;

import java.net.Proxy;

import com.mojang.authlib.Agent;
import com.mojang.authlib.UserAuthentication;
import com.mojang.authlib.exceptions.AuthenticationException;
import com.mojang.authlib.yggdrasil.YggdrasilAuthenticationService;

import neko.module.modules.hide.Friends;
import net.mcleaks.MCLeaks;
import net.minecraft.client.Minecraft;
import net.minecraft.util.Session;

public class Account {
	private String Username;
	private String Email;
	private String Pass;
	private boolean valid;
	private Minecraft mc = Minecraft.getMinecraft();
	
	public Account(String email, String pass) {
		Email = email;
		Pass = pass;
	}
	public String getUsername() {
		return Username;
	}
	public void setUsername(String username) {
		Username = username;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getPass() {
		return Pass;
	}
	public void setPass(String pass) {
		Pass = pass;
	}
	
	public boolean isValid() {
		return valid;
	}
	public void setValid(boolean valid) {
		this.valid = valid;
	}
	public boolean isCracked() {
		return Pass==null || Pass.isEmpty();
	}
	
	public String login() {
		YggdrasilAuthenticationService authService = new YggdrasilAuthenticationService(Proxy.NO_PROXY, "");
	    UserAuthentication auth = authService.createUserAuthentication(Agent.MINECRAFT);
	    if (Pass.isEmpty()) {
	    	mc.session = new Session(Email, "", "", "mojang");			
			if (!Friends.isFriend(mc.session.getUsername()))
				Friends.friend.add(mc.session.getUsername());
			if (MCLeaks.isAltActive())
				MCLeaks.remove();
			this.Username=mc.session.getUsername();
			this.setValid(true);
			return "§aConnecté en tant que "+mc.session.getUsername()+" !";			
	    } else {
		    auth.setUsername(Email);
		    auth.setPassword(Pass);
		    try {
				auth.logIn();
				Minecraft.getMinecraft().session = new Session(auth.getSelectedProfile().getName(), 
				auth.getSelectedProfile().getId().toString(), auth.getAuthenticatedToken(), "mojang");
				if (!Friends.isFriend(mc.session.getUsername()))
					Friends.friend.add(mc.session.getUsername());
				if (MCLeaks.isAltActive())
					MCLeaks.remove();
				this.Username=mc.session.getUsername();
				this.setValid(true);
				return "§aConnecté en tant que "+mc.session.getUsername()+" !";
				
			} catch (AuthenticationException e) {
				this.setValid(false);
				return "§cErreur: Email/Username ou mdp incorrect";
			} catch (Exception e) {
				
			}
	    }
	    this.setValid(false);
		return "§cErreur: Email/Username ou mdp incorrect";
	}
}
