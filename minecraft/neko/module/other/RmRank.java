package neko.module.other;

public class RmRank {
	protected String name;
	protected String rankName;
	protected String desc;
	
	public RmRank(String name, String rankName, String desc) {
		super();
		this.name = name;
		this.rankName = rankName;
		this.desc = desc;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRankName() {
		return rankName;
	}

	public void setRankName(String rankName) {
		this.rankName = rankName;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
		
}
