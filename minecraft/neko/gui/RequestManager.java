package neko.gui;

public class RequestManager {
	private String token;
	private String tempKey;
	private String name;
	private String pass;
	private static RequestManager instance = null;
	
	public static RequestManager getRequest() {
		return instance == null ? instance = new RequestManager() : instance;
	}	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	
	public boolean connectToNeko() {
		
		return false;
	}
	
	public void sendRequest(String token, String listArgs[]) {		
		
	}
}














