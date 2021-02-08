package neko.module.modules.movements;

import org.lwjgl.input.Keyboard;

import neko.module.Category;
import neko.module.Module;
import net.minecraft.network.play.client.C03PacketPlayer;

public class NoFall extends Module {

	public NoFall() {
		super("NoFall", Keyboard.KEY_RSHIFT, Category.MOVEMENT, false);
	}
	
	public void onEnabled() {
		super.onEnabled();
	}
	
	public void onDisabled() {
		super.onDisabled();
	}
	
	public void setValues() {
		this.values = "";
	}
	
	public void onUpdate() {
		if (mc.thePlayer.fallDistance >= 2F) {
			mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer(true));
			mc.thePlayer.fallDistance=0.0F;
		}
	}

}
