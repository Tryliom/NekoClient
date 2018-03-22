package neko.api;

import java.util.HashMap;

import neko.utils.Utils;

public class NekoCloud {
	private String name;
	private String password;
	private static NekoCloud instance = null;
	private boolean login = false;
	
	public static NekoCloud getNekoAPI() {
		return instance==null ? instance = new NekoCloud() : instance;
	}
	
	public boolean isLogin() {
		return login;
	}

	public void setLogin(boolean login) {
		this.login = login;
	}

	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}
	
	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String parseHashMapToJson(HashMap<String, String> hm) {
		String tot = "";
		for (String key : hm.keySet()) {
			String value = hm.get(key);
			tot+=(tot.isEmpty() ? "{" : ",")+"\""+key+"\":\""+value+"\"";
		}
		tot+="}";
		return tot;
	}
	
	public String loginAccount() {
		String s = Utils.preparePostRequest("https://qy0n81yfr7.execute-api.eu-central-1.amazonaws.com/beta/account/login", this.parseHashMapToJson(this.getBaseBody()));
		return s.replaceAll("\"", "");
	}
	
	public String createAccount() {
		String s = Utils.preparePostRequest("https://qy0n81yfr7.execute-api.eu-central-1.amazonaws.com/beta/account/create", this.parseHashMapToJson(this.getBaseBody()));
		return s.replaceAll("\"", "");
	}
	
	/**
	 * Les save sont load uniquement au début du client, donc il faut même que ça charge tout avant sinon dfbhhjab
	 * @param name
	 * @return
	 */
	public String getSave(String name, String...config) {
		HashMap<String, String> hm = this.getBaseBody();
		if (config.length>0) {
			hm.put("config", config[0]);
		}
		String s = Utils.preparePostRequest("https://qy0n81yfr7.execute-api.eu-central-1.amazonaws.com/beta/save/get/"+name, parseHashMapToJson(hm));
//		System.out.println(s);
		return s.replaceAll("\"", "");
	}
	/**
	 * 
	 * @param name Sans le .neko, juste le nom
	 * @param content	Le contenu du fichier
	 */
	public void saveSave(String name, String content, String...config) {
		HashMap<String, String> hm = this.getBaseBody();
		hm.put("content", content);
		if (config.length>0) {
			hm.put("config", config[0]);
		}
		new Thread(new Runnable() {
			
			@Override
			public void run() {
//				System.out.println(content);
				String s = Utils.preparePostRequest("https://qy0n81yfr7.execute-api.eu-central-1.amazonaws.com/beta/save/update/"+name, NekoCloud.getNekoAPI().parseHashMapToJson(hm));
			}
		}).start();
	}
	
	public HashMap<String, String> getBaseBody() {
		HashMap<String, String> hm = new HashMap<String, String>();
		hm.put("name", this.getName());
		hm.put("password", this.getPassword());
		return hm;
	}
	
}
