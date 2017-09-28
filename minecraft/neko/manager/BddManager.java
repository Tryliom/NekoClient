package neko.manager;

public class BddManager {
	private String user;
	private String pass;
	private boolean remember=false;
	private static BddManager instance=null;
	
	public static BddManager getBdd() {
		if (instance==null) {
			instance=new BddManager();
		}
		return instance;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public boolean isRemember() {
		return remember;
	}

	public void setRemember(boolean remember) {
		this.remember = remember;
	}
	
	
}
