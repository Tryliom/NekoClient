package neko.module.other;

import net.minecraft.network.play.client.C00PacketKeepAlive;

public class PacketPing {
	private int delay;
	private C00PacketKeepAlive packet;
	public PacketPing(int delay, C00PacketKeepAlive packet) {
		super();
		this.delay = delay;
		this.packet = packet;
	}
	public int getDelay() {
		return delay;
	}
	public void setDelay(int delay) {
		this.delay = delay;
	}
	public C00PacketKeepAlive getPacket() {
		return packet;
	}
	public void setPacket(C00PacketKeepAlive packet) {
		this.packet = packet;
	}
	
	
}
