package neko.module.other;

public class TutoManager {
	private boolean done = false;
	private int part=1;
	private int totPart=10;
	private static TutoManager instance = null;
	
	public static TutoManager getTuto() {
		if (instance==null)
			instance = new TutoManager();
		return instance;
	}
	
	public String getPart(int num) {
		String s = "�cTuto partie n�"+num+":\n�e";
		if (num==1) {
			return "Neko est un Hacked Client qui inclue un syst�me RPG.\n"
					+ "�eC'est � dire que vous avez un syst�me de niveau avec l'exp�rience\n"
					+ "�equi servent � d�bloquer de nouveaux cheat ou commandes.\n"
					+ "�eVous pouvez d�bloquer des cheats et commandes aussi avec des souls ou par des rangs.\n"
					+ "�eVous pouvez voir votre niveau, vos souls, votre bonus d'xp et l'xp dans le �dHUD";
		}
		
		
		return "Fin";
	}

	public boolean isDone() {
		return done;
	}

	public void setDone(boolean done) {
		this.done = done;
	}

	public int getPart() {
		return part;
	}

	public void setPart(int part) {
		this.part = part;
	}

	public int getTotPart() {
		return totPart;
	}

	public void setTotPart(int totPart) {
		this.totPart = totPart;
	}
	
}
