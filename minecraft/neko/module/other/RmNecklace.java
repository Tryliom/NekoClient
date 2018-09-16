package neko.module.other;

public class RmNecklace {
	protected String name;
	protected String necklaceName;
	protected String desc;
	
	public RmNecklace(String name, String necklaceName, String desc) {
		super();
		this.name = name;
		this.necklaceName = necklaceName;
		this.desc = desc;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public String getNecklaceName() {
		return necklaceName;
	}

	public void setNecklaceName(String necklaceName) {
		this.necklaceName = necklaceName;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
}