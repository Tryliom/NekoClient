package neko.lock;

public class Lock {
	private String name; // Nom simple ex: ..ka random
	private int cout; // Lvl ou Souls (Si 0 = Obtenable autrement)
	private String unit;
	private String type; // Commande ou Cheat
	private String desc;
	private String raccourcis;
	private boolean lock;
	// Si c'est débloquable par souls
	private String cmdName="";
	private String nameUnlock="";
	
	
	public Lock(String name, int cout, String unit, String type, String desc, String raccourcis, boolean lock) {
		super();
		this.name = name;
		this.cout = cout;
		this.unit = unit;
		this.type = type;
		this.desc = desc;
		this.raccourcis = raccourcis;
		this.lock = lock;
	}
	
	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCout() {
		return cout;
	}
	public void setCout(int cout) {
		this.cout = cout;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public boolean isLock() {
		return lock;
	}
	public void setLock(boolean lock) {
		this.lock = lock;
	}

	public String getCmdName() {
		return cmdName;
	}

	public void setCmdName(String cmdName) {
		this.cmdName = cmdName;
	}

	public String getNameUnlock() {
		return nameUnlock;
	}

	public void setNameUnlock(String nameUnlock) {
		this.nameUnlock = nameUnlock;
	}

	public String getRaccourcis() {
		return raccourcis;
	}

	public void setRaccourcis(String raccourcis) {
		this.raccourcis = raccourcis;
	}
	
	
}
