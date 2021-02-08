package neko.command.commands;

import java.net.URL;
import java.util.Scanner;

import neko.command.Command;
import neko.command.CommandType;
import neko.utils.Utils;

public class MyIp extends Command {

	public MyIp() {
		super("myip", "myip", "Affiche votre ip", 1, CommandType.Other);
	}
	
	public void onCommand(String[] args) {
		new Thread(new Runnable() {
			public void run() {
				try {
			URL url = new URL("http://nekohc.fr/controler/Neko/ip.php");
			Scanner sc = new Scanner(url.openStream());
			String l;
			try {
				while ((l = sc.nextLine()) != null) {
					Utils.addChat("Votre adresse IP: "+l);
					break;
				}
			} catch (Exception e) {}
			sc.close();
		} catch (Exception e) {
			Utils.addChat("§cErreur");
		}
			}
		}).start();	
	}
}
