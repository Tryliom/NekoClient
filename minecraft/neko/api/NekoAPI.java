package neko.api;

import neko.utils.Utils;

public class NekoAPI {
	
	public static String getLoginToken() {
		String token = Utils.getResultOfPostRequest("https://api.nekohc.fr/auth/token", NekoCloud.getNekoAPI().parseHashMapToJson(NekoCloud.getNekoAPI().getBaseBody()));
		
		return token;
	}
	
	public static void renewToken() {
		Utils.getResultOfRequestWithAuthToken("https://api.nekohc.fr/auth/renew", "", "POST", NekoCloud.getNekoAPI().getToken());
	}
}
