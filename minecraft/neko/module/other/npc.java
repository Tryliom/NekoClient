package neko.module.other;

import java.util.ArrayList;

import com.mojang.authlib.GameProfile;

public class npc {
	public static ArrayList<fakeplayer> list = new ArrayList<fakeplayer>();	
	
	public static void addNpc(String pseudo, GameProfile g) {
		list.add(new fakeplayer(pseudo,g));
	}
	
	public static ArrayList<fakeplayer> getList() {
		return list;
	}
	
	public static int getNum(String pseudo) {
		for (int i=0;i<list.size();i++) {
			if (list.get(i).equals(pseudo))
				return i;
		}
		return -1;
	}
}
