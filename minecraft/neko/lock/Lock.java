package neko.lock;

public class Lock {
	protected String name; // Nom simple ex: ..ka random
	protected int cout; // Lvl ou NekoDollar (Si 0 = Obtenable autrement)
	protected String unit;
	protected String type; // Commande ou Cheat
	protected String desc;
	protected boolean lock;
	
	
	public Lock(String name, int cout, String unit, String type, String desc, boolean lock) {
		super();
		this.name = name;
		this.cout = cout;
		this.unit = unit;
		this.type = type;
		this.desc = desc;
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
	
	
}
