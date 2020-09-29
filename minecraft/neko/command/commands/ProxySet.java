package neko.command.commands;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.util.Properties;

import neko.command.Command;
import neko.command.CommandType;
import neko.utils.Utils;

public class ProxySet extends Command {

	public ProxySet() {
		super("proxy set", "proxy set [IP:Port]", "Connecte au proxy spécifié", 3, CommandType.Other);
	} 
	
	public void onCommand(String[] args) {
		String[] address = args[2].split(":");
		String host = address[0];
		String port = address[1];
		
		Properties props = System.getProperties();
		props.setProperty("proxySet", "true" );
    	props.setProperty("socksProxyHost", host);
    	props.setProperty("socksProxyPort", port);
    	System.setProperties(props);
    	mc.setProxy(new Proxy(Type.SOCKS, new InetSocketAddress(host, Integer.parseInt(port))));
		Utils.addChat("§aVous vous êtes connecté à "+args[2]);
	}
}
