package neko.module.modules;

import java.net.InetAddress;
import java.util.UUID;

import com.mojang.authlib.GameProfile;

import neko.module.Category;
import neko.module.Module;
import neko.utils.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ServerAddress;
import net.minecraft.client.network.NetHandlerLoginClient;
import net.minecraft.network.EnumConnectionState;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.handshake.client.C00Handshake;
import net.minecraft.network.login.client.C00PacketLoginStart;

public class SpamBot extends Module {
	private int delay=0;	
	private String pseudo;
	private static SpamBot instance;
	
	public SpamBot() {
		super("SpamBot", -1, Category.Special);
		instance=this;
		this.pseudo="Neko";
	}
	
	public static SpamBot getBot() {
		return instance;
	}
	
	public void onEnabled() {		
		super.onEnabled();
	}
	
	public void onDisabled() {
		super.onDisabled();
	}
	
	public void onUpdate() {
		if (mc.isSingleplayer())
			return;
		if (delay>5) {
			delay=0;
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					try {					
						ServerAddress var4 = ServerAddress.func_78860_a(mc.getCurrentServerData().serverIP);
						InetAddress var1 = InetAddress.getByName(mc.getCurrentServerData().serverIP);
	                    NetworkManager n = NetworkManager.provideLanClient(var1, var4.getPort());
	                    n.setNetHandler(new NetHandlerLoginClient(n, null, null));
	                    n.sendPacket(new C00Handshake(47, mc.getCurrentServerData().serverIP, var4.getPort(), EnumConnectionState.LOGIN));
	                    n.sendPacket(new C00PacketLoginStart(new GameProfile(UUID.randomUUID(), Math.random()<0.5 ? Utils.getRandPlayer() : SpamBot.getBot().pseudo+System.currentTimeMillis()*Utils.getRandInt(5))));
					} catch (Exception e) {}							
				}
			}).start();
		} else 
			delay++;
	}

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}
	
	
	
}
