package neko.manager;

import neko.Client;

public class TutoManager {
	private boolean done = false;
	private int part=1;
	private int totPart=10;
	private Client var = Client.getNeko();
	private static TutoManager instance = null;
	
	public static TutoManager getTuto() {
		if (instance==null)
			instance = new TutoManager();
		return instance;
	}
	
	public String getPart(int num) {
		String s = "�cTuto partie n�"+num+":\n�e";
		if (num==1) {
			s+= "Neko est un Hacked Client qui inclue un syst�me RPG.\n"
					+ "�eC'est-�-dire que vous avez un syst�me de niveau avec l'exp�rience\n"
					+ "�equi sert � d�bloquer de nouveaux cheats ou commandes.\n"
					+ "�eVous pouvez d�bloquer des cheats et commandes aussi avec des souls ou par des rangs.\n"
					+ "�eVous pouvez voir votre niveau, vos souls, votre bonus d'xp et l'xp dans le �dHUD\n"
					+ "�eEt voir vos cheats activ�s avec l'�dArrayList !";
		}
		if (num==2) {
			s+= "Les Rangs, il y en a des centaines, que vous pouvez d�bloquer par plusieurs mani�res,\n"
					+ "�evous pouvez les voir � gauche de votre xp dans le �dHUD�e qui est actuellement: "+(var.rang.getColor()+var.rang.getName())+"�e.\n"
					+ "�eActuellement, � chaque rang d�bloqu� vous le 'mettez' tout de suite, pour les organiser il y aura plus tard\n"
					+ "�ele RankManager, vous pourez voir l'aide avec le �d"+var.prefixCmd+"rankmanager�e\n"
					+ "�eLes rangs vous offrent des bonus en tout genre, principalement un certain bonus d'xp en plus\n"
					+ "�eensuite certains sp�ciaux, comme avoir plus de chance d'avoir des souls dans les m�t�ores.\n"
					+ "�ePlus vous �tes haut dans les raret�s plus le bonus sera important\n"
					+ "�eListe des raret�s ci-dessous (Du - au + rare) et ici ceux sp�ciaux: �5Neko�e, �fSupra�e et �5Event�e.\n"
					+ "�7Ordinaire�e, �eRare�e, �bUltraRare�e, �dMagical�e, �d�o�nDivin�e, �cSatanique�e,\n"
					+ "�5�oL�gendaire�e, �2Mythique�e, �4Titan�e et �9CrazyLove�e !";
		}
		if (num==3) {
			s+= "Les commandes, sur Neko il y en a des centaines, pour modifier les param�tres des cheats ou juste utiles,\n"
					+ "�ecommencez par un "+var.prefixCmd+"help puis pour conna�tre les commandes de n'importe quel cheat\n"
					+ "�etapez seulement "+var.prefixCmd+"help <Cheat>, par exemple le "+var.prefixCmd+"help ka,\n"
					+ "�equi vous donne toutes les commandes pour configurer le Kill Aura.\n"
					+ "�eVous n'aurez qu'� d�couvrir, tout y est marqu� ;)";
		}
		if (num==4) {
			s+= "Les m�t�ores, ce sont des petits cubes de couleurs qui appara�ssent au sol que vous\n"
					+ "�epouvez rammasser en passant dedans, ils vous donnent plusieurs choses diff�rentes:\n"
					+ "�eLes vertes et vertes fonc�es:\n�a De l'xp et les fonc�es un peu plus que les clairs\n"
					+ "�eLes bleues:\n�9 Des souls, une monnaie pour d�bloquer des cheats, commandes et autres\n"
					+ "�eLes roses:\n�d Rares, d�bloquent des cheats ou commandes d�bloquables seulement comme �a\n"
					+ "�eLes oranges:\n�6 Donnent des tickets de loterie, qui serviront avec le "+var.prefixCmd+"trade plus tard\n"
					+ "�eLes rouges:\n�c Donnent des rangs al�atoires";
		}
		if (num==5) {
			s+= "L'IRC, autrement dit un chat entre tous les utilisateurs de Neko, \n�evous pouvez parler dedans de base avec le prefix $.\n"
					+ "�eBien s�r, vous pouvez ensuite le changer, faites "+var.prefixCmd+"help irc pour toutes les commandes.\n"
					+ "�eVous avez 3 modes, Normal, Only et Hybride:\n"
					+ "�eLe mode Normal, vous avez besoin de mettre le prefix irc pour parler dans l'irc\n"
					+ "�eLe mode Only, vous n'avez pas besoin du prefix irc, vous n'avez pas le chat normal\n"
					+ "�eLe mode Hybride, vous n'avez pas besoin du prefix irc sauf pour parler dans le chat normal.";
		}
		if (num==6) {
			s+= "Instant FAQ, les questions �ctr�s tr�s tr�s�e souvent pos�es (on tourne autour des 1000 fois �.�)\n-=-\n"
					+ "�cComment obtenir la reach pvp ?\n�d-> Il faut obtenir un rang qui contient les mots �cJP�d ou �cJean-Pierre\n"
					+ "�dTips: Si vous voulez mettre sur une touche une commande: �c"+var.prefixCmd+"help cmd, �a vous aidera ;)\n"
					+ "�e";
		}
		
		return s;
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
