package neko.module.modules.misc;

import java.util.Vector;

import neko.module.Category;
import neko.module.Module;

public class Nameprotect extends Module {
	private static Nameprotect nm;
	private Vector<String> list = new Vector<String>();
	
	public Nameprotect() {
		super("Nameprotect", -1, Category.MISC);
		this.nm = this;
	}
	
	public static Nameprotect getNP() {
		return nm;
	}
	
	public void onEnabled() {		
		super.onEnabled();
	}
	
	public void onDisabled() {
		super.onDisabled();
	}
	
	public void setValues() {
		String s = "§cAucun";
		try {
			if (this.list.size()>0) {
				s = "";
				for (String n : this.list) {
					System.out.println(n);
					String m[] = n.split("=");
					s+="§6"+m[0]+" > "+m[1]+"\n";
				}
				s = s.substring(0, s.length()-2);
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.values = s;
	}

	public Vector<String> getList() {
		return list;
	}

	public void setList(Vector<String> list) {
		this.list = list;
	}
	
	public String changeName(String s) {
		try {
			for (String n : this.list) {
				String m[] = n.split("=");
				s = s.replaceAll(m[0], m[1]);
			}
		} catch (Exception e) {}
		return s;
	}
	
	public void addName(String s1, String s2) {
		try {
			int i = 0;
			for (String n : this.list) {
				String m[] = n.split("=");
				if (m[0].equalsIgnoreCase(s1)) {
					this.list.remove(i);
					this.list.add(m[0]+"="+s2);
					return;
				}
				i++;
			}
			this.list.add(s1+"="+s2);
		} catch (Exception e) {}
	}
	
	public boolean deleteName(String s1) {
		try {
			int i = 0;
			for (String n : this.list) {
				String m[] = n.split("=");
				if (m[0].equalsIgnoreCase(s1)) {
					this.list.remove(i);
					return true;
				}
				i++;
			}
		} catch (Exception e) {}
		return false;
	}
	
	
}
