package neko.dtb;

public class Alt {
	private int altTimer=0;
	private static Alt instance=null;
	
	public static Alt getAlt() {
		if (instance==null) {
			instance = new Alt();
		}
		return instance;
	}

	public int getAltTimer() {
		return altTimer;
	}

	public void setAltTimer(int altTimer) {
		this.altTimer = altTimer;
	}
	
	public void decrementAltTimer() {
		this.altTimer--;
	}
}
