package neko.module.other;

import neko.module.other.enums.EventType;

public class Event {
	public static int lastEventId=-1;
	public static String lastEvent=null;
	public static String mdp="";
	private EventType type;
	private String cmd;
	
	public Event(EventType type, String cmd) {
		super();
		this.type = type;
		this.cmd = cmd;
	}

	public EventType getType() {
		return type;
	}

	public void setType(EventType type) {
		this.type = type;
	}

	public String getCmd() {
		return cmd;
	}

	public void setCmd(String cmd) {
		this.cmd = cmd;
	}
}
