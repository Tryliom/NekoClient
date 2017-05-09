package neko.module.other;

import java.util.ArrayList;

import com.mojang.authlib.GameProfile;

public class fakeplayer {	
	private String pseudo;
	private GameProfile g;
	
	
	public fakeplayer(String pseudo, GameProfile g) {
		this.pseudo=pseudo;
		this.g=g;
	}

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public GameProfile getG() {
		return g;
	}

	public void setG(GameProfile g) {
		this.g = g;
	}
	
	

	
	
}
