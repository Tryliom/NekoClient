package neko.module.modules;

import java.util.ArrayList;
import java.util.Random;

import neko.Client;
import neko.module.Category;
import neko.module.Module;
import neko.module.ModuleManager;
import neko.module.other.Active;
import neko.module.other.Bloc;
import neko.module.other.Rank;
import neko.module.other.Rate;
import neko.utils.RenderUtils;
import neko.utils.Utils;
import net.minecraft.client.particle.EntityFireworkSparkFX;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumParticleTypes;

public class Lot extends Module {
	public static ArrayList<Bloc> list=new ArrayList<>();
	public static ArrayList<Bloc> list2=new ArrayList<>();
	public static double large=0.2;
	public static boolean signPlus=true;
	public static int nbLot=3;
	Client var = Client.getNeko();
	
	public Lot() {
		super("Lot", -1, Category.HIDE);
	}	
	
	public static void init(ArrayList<Bloc> l) {
		list.clear();
		list=l;
	}
	
	public void onRender3DA() {
		if (Utils.verif!=null)
			return;
		if (signPlus)
			large+=0.009;
		else
			large-=0.009;
		if (!list.isEmpty()) {
			for (Bloc b : list) {
				BlockPos currentBlock=b.getBloc();
				for (Bloc br : list) {
					if (currentBlock.equals(br.getBloc()) && !list.contains(b))
						return;
				}
				RenderUtils.drawOutlinedBlockESP(currentBlock.getX() - mc.getRenderManager().renderPosX+0.47, currentBlock.getY() - mc.getRenderManager().renderPosY, currentBlock.getZ() - mc.getRenderManager().renderPosZ+0.47, b.getR(), b.getG(), b.getB(), 1F, 2F, -large/10, 5, large/10);
				double neko = -1+Math.random()*2;									
				double nekoN = -1+Math.random()*2;
				double n = Math.random()-0.25;
				double nekoY = Math.random()*1.15+n;		
				mc.theWorld.spawnParticle(EnumParticleTypes.REDSTONE, currentBlock.getX()+neko, currentBlock.getY()+nekoY, currentBlock.getZ()+nekoN, 0, 0, 0, 0);
			}
		}
		if (large>=0.3 && signPlus) {
			signPlus=false;
		} else if (large<=0.15 && !signPlus) {
			signPlus=true;
		}
		
	}
	
	public void onAction() {
		if (Utils.verif!=null)
			return;
		boolean a=true;
		Bloc current = null;
		if (!list.isEmpty())
		for (Bloc b : list) {
			if (mc.thePlayer.getPosition().equals(new BlockPos(b.getBloc().getX(), mc.thePlayer.posY, b.getBloc().getZ()))) {
				String gain = b.getGain();
				if (gain.equalsIgnoreCase("bonus")) {
					if (Active.time==0) {
						Active a1 = new Active(b.getBonus(), b.getTime());
						u.addChat("§5Vous avez reçu un bonus de §d"+a1.getBonus()+"%\n§aActif pendant encore "+a1.getTime()/60+" minutes");
					} else {
						u.addChat("§aVotre temps et bonus s'additionnent à celui d�jà actif !");
						Active.bonus+=b.getBonus();
						Active.time+=b.getTime();
					}										
				} else if (gain.equalsIgnoreCase("malus")) {
					if (Active.time==0) {
						Active a1 = new Active(b.getBonus(), b.getTime());
						u.addChat("§cVous avez reçu un impôt de §d"+a1.getBonus()+"%\n§aActif pendant encore "+a1.getTime()/60+" minutes");
					} else {
						u.addChat("§cVotre temps et bonus s'additionnent à celui d�jà actif !");
						Active.bonus+=b.getBonus();
						Active.time+=b.getTime();
					}	
				} else if (gain.equalsIgnoreCase("rang")) {
					String s = u.getRandRank(b.getRate());
					u.setRank(s);
					u.addChat("§5Vous d�bloquez le rang "+u.getRankColor(s)+s+"§5 au lvl "+u.getRankColor(s)+u.getRank(s).getLvl()+"§5 !");
					int rang=0;
					for (Rank r : ModuleManager.rang) {
						if (!r.isLock())
							rang++;
					}							
					if (rang==10 && Utils.getRank(s).getLvl()==1) {
						u.addChat("§5§k77§c10 rangs d�bloqu�s !§5§k88");
						u.addChat("§5§k77§c10 billets de lotterie gagn�s !§5§k88");
						var.lot+=10;
					} else if (rang==25 && Utils.getRank(s).getLvl()==1) {
						u.addChat("§5§k77§c25 rangs d�bloqu�s !§5§k88");
						u.addChat("§5§k77§c15 billets de lotterie gagn�s !§5§k88");
						var.lot+=15;
					} else if (rang==50 && Utils.getRank(s).getLvl()==1) {
						u.addChat("§5§k77§c50 rangs d�bloqu�s !§5§k88");
						u.addChat("§5§k77§c20 billets de lotterie gagn�s !§5§k88");
						var.lot+=20;
					} else if (rang==100 && Utils.getRank(s).getLvl()==1) {
						u.addChat("§5§k77§c100 rangs d�bloqu�s !§5§k88");
						u.addChat("§5§k77§c25 billets de lotterie gagn�s !§5§k88");
						var.lot+=25;
					} else if (rang==150 && Utils.getRank(s).getLvl()==1) {
						u.addChat("§5§k77§c150 rangs d�bloqu�s !§5§k88");
						u.addChat("§5§k77§c30 billets de lotterie gagn�s !§5§k88");
						var.lot+=30;
					}
				} else if (gain.equalsIgnoreCase("unlock")) {					
					u.getRandUnlock();
				} else if (gain.equalsIgnoreCase("souls")) {					
					int count=0;
					for (int i=0;i<10;i++)
						if (new Random().nextBoolean() && Math.random()<0.5)
							count++;
					switch (count) {
					case 0:
						int r = Utils.getRandInt(8)+1;
						Utils.addChat("§d★  §7-> §9Tu as gagn� "+r+" souls !");
						var.ame+=r;
						break;
					case 1:
						Utils.addChat("§d★★  §7-> §9Tu as gagn� 25 souls !");
						var.ame+=25;
						break;
					case 2:
						Utils.addChat("§d★★★  §7-> §9Tu as gagn� 50 souls !");
						var.ame+=50;
						break;
					case 3:
						Utils.addChat("§d★★★★ §7-> §9Tu as gagn� 100 souls !");
						var.ame+=100;
						break;
					case 4:
						Utils.addChat("§d★★★★★  §7-> §9Tu as gagn� 500 souls !");
						var.ame+=500;
						break;
					case 5:
						Utils.addChat("§d★★★★★★  §7-> §9Tu as gagn� 750 souls !");
						var.ame+=750;
						break;
					case 6:
						Utils.addChat("§d★★★★★★★  §7-> §9Tu as gagn� 1000 souls !");
						var.ame+=1000;
						break;
					case 7:
						Utils.addChat("§d★★★★★★★★  §7-> §9Tu as gagn� 1500 souls !");
						var.ame+=1500;
						break;
					case 8:
						Utils.addChat("§d★★★★★★★★★  §7-> §9Tu as gagn� 2000 souls !");
						var.ame+=2000;
						break;
					case 9:
						Utils.addChat("§d★★★★★★★★★★  §7-> §9Tu as gagn� 2500 souls !");
						var.ame+=2500;
						break;
					}
					
				} else {
					u.addChat(u.setColor("Les �cureuils vous livre un pack d'xp !", "§c§o"));
					u.checkXp(u.getRandInt((int) Math.round(700+700*var.rang.getGiftXp())));
				}								
				a=false;			
				current=b;
				for (int i=0;i<5;i++) {
					mc.theWorld.spawnEntityInWorld(new EntityFireworkSparkFX(mc.theWorld, mc.thePlayer.posX, mc.thePlayer.posY+2, mc.thePlayer.posZ, Math.random()*0.1, 0.4, Math.random()*0.1, mc.effectRenderer));
					mc.theWorld.spawnEntityInWorld(new EntityFireworkSparkFX(mc.theWorld, mc.thePlayer.posX, mc.thePlayer.posY+2, mc.thePlayer.posZ, Math.random()*0.2, 0.4, Math.random()*0.1, mc.effectRenderer));
					mc.theWorld.spawnEntityInWorld(new EntityFireworkSparkFX(mc.theWorld, mc.thePlayer.posX, mc.thePlayer.posY+2, mc.thePlayer.posZ, Math.random()*0.2, 0.4, Math.random()*0.2, mc.effectRenderer));
					mc.theWorld.spawnEntityInWorld(new EntityFireworkSparkFX(mc.theWorld, mc.thePlayer.posX, mc.thePlayer.posY+2, mc.thePlayer.posZ, Math.random()*0.1, 0.4, Math.random()*0.2, mc.effectRenderer));
					mc.theWorld.spawnEntityInWorld(new EntityFireworkSparkFX(mc.theWorld, mc.thePlayer.posX, mc.thePlayer.posY+2, mc.thePlayer.posZ, Math.random()*0.1, 0.4, Math.random()*0.1, mc.effectRenderer));
				}
			}
		}
		if (!a) {
			String var="";
			int xp=0;
			int malus=0;
			int bonus=0;
			int unlock=0;
			int souls=0;
			// Raret� rang
			int Ordinaire=0;
			int Rare=0;
			int UltraRare=0;
			int Magical=0;
			int Divin=0;
			int Satanique=0;
			int L�gendaire=0;
			int Mythique=0;
			int Titan=0;
			int Neko=0;
			list.remove(current);
			for (Bloc b : list) {				
				String g = b.getGain();
				switch (g) {
				case "xp":
					xp++;
					break;
				case "malus":
					malus++;
					break;
				case "bonus":
					bonus++;
					break;
				case "unlock":
					unlock++;
					break;
				case "souls":
					souls++;
					break;
				case "rang":
					Rate r = b.getRate();
					switch (r.name()) {
					case "Ordinaire":
						Ordinaire++;
						break;
					case "Rare":
						Rare++;
						break;
					case "UltraRare":
						UltraRare++;
						break;
					case "Magical":
						Magical++;
						break;
					case "Divin":
						Divin++;
						break;
					case "Satanique":
						Satanique++;
						break;
					case "L�gendaire":
						L�gendaire++;
						break;
					case "Mythique":
						Mythique++;
						break;
					case "Titan":
						Titan++;
						break;
					case "Neko":
						Neko++;
						break;
					}
					break;
				}
			}
			String c1="§a";
			String c2="§7";
			String c3="§a";
			Utils.addChat("§6Lots rat�s:");
			if (xp>0)
				Utils.addChat(c1+"-> §aXp"+c2+" ["+c3+xp+c2+"]");
			if (malus>0)
				Utils.addChat(c1+"-> §cMalus"+c2+" ["+c3+malus+c2+"]");
			if (bonus>0)
				Utils.addChat(c1+"-> §aBonus"+c2+" ["+c3+bonus+c2+"]"); //  
			if (unlock>0)
				Utils.addChat(c1+"-> §dUnlock"+c2+" ["+c3+unlock+c2+"]");
			if (souls>0)
				Utils.addChat(c1+"-> §9Souls"+c2+" ["+c3+souls+c2+"]");
			if (Ordinaire>0)
				Utils.addChat(c1+"-> §7Rang Ordinaire"+c2+" ["+c3+Ordinaire+c2+"]");
			if (Rare>0)
				Utils.addChat(c1+"-> §eRang Rare"+c2+" ["+c3+Rare+c2+"]");
			if (UltraRare>0)
				Utils.addChat(c1+"-> §bRang UltraRare"+c2+" ["+c3+UltraRare+c2+"]");
			if (Magical>0)
				Utils.addChat(c1+"-> §dRang Magical"+c2+" ["+c3+Magical+c2+"]");
			if (Divin>0)
				Utils.addChat(c1+"-> §d§oRang Divin"+c2+" ["+c3+Divin+c2+"]");
			if (Satanique>0)
				Utils.addChat(c1+"-> §cRang Satanique"+c2+" ["+c3+Satanique+c2+"]");
			if (L�gendaire>0)
				Utils.addChat(c1+"-> §5Rang L�gendaire"+c2+" ["+c3+L�gendaire+c2+"]");
			if (Mythique>0)
				Utils.addChat(c1+"-> §2Rang Mythique"+c2+" ["+c3+Mythique+c2+"]");
			if (Titan>0)
				Utils.addChat(c1+"-> §4§oRang Titan"+c2+" ["+c3+Titan+c2+"]");
			if (Neko>0)
				Utils.addChat(c1+"-> §5Rang Neko"+c2+" ["+c3+Neko+c2+"]");
			list2=(ArrayList<Bloc>) list.clone();
			list.clear();
		}
	}

	

}
