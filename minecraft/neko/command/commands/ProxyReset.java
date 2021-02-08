package neko.command.commands;

import java.util.Properties;

import neko.command.Command;
import neko.command.CommandType;
import neko.utils.Utils;

public class ProxyReset extends Command {

	public ProxyReset() {
		super("proxy reset", "proxy reset", "Déconnecte du proxy", 2, CommandType.Other);
	} 
	
	public void onCommand(String[] args) {
		Properties props = System.getProperties();
		props.setProperty("proxySet", "false" );
		System.clearProperty("socksProxyHost");
    	System.setProperties(props);
    	mc.setProxy(null);
		Utils.addChat("§aVous vous êtes déconnecté du proxy");
	}
}
