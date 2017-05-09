package neko.module.other;

import com.mojang.authlib.GameProfile;

public class Spoof {
	private String name;
	private GameProfile g;
	
	public Spoof(String name, GameProfile g) {
		super();
		this.name = name;
		this.g = g;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public GameProfile getG() {
		return g;
	}
	public void setG(GameProfile g) {
		this.g = g;
	}
	
	
}
