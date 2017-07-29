package neko.module.other;

public class AutoReco {
	public static int actuDelay=30;
	public static boolean active = false;
	
	public static String getActive() {
		return active ? "§aAutoReconnect "+actuDelay/20+"sec" : "§cAutoReconnect";
	}
	
	public static boolean canGo() {
		if (active) {
			actuDelay--;
		}
		if (active && actuDelay<=0) {
			actuDelay=80;
			return true;
		}
		return false;
	}
	
	public static void setActive() {
		actuDelay=80;
		active=!active;
	}
}
