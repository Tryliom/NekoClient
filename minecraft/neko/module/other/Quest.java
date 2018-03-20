package neko.module.other;

import neko.module.Module;

public class Quest {
	private String desc;
	private Module responseCheat;
	private String responseCmd;
	private int trials;	
	
	public Quest(String desc, Module responseCheat, String responseCmd, int trials) {
		super();
		this.desc = desc;
		this.responseCheat = responseCheat;
		this.responseCmd = responseCmd;
		this.trials = trials;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public Module getResponseCheat() {
		return responseCheat;
	}
	public void setResponseCheat(Module responseCheat) {
		this.responseCheat = responseCheat;
	}
	public String getResponseCmd() {
		return responseCmd;
	}
	public void setResponseCmd(String responseCmd) {
		this.responseCmd = responseCmd;
	}
	public int getTrials() {
		return trials;
	}
	public void setTrials(int trials) {
		this.trials = trials;
	}
	
		
}
