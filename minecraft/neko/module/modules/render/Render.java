package neko.module.modules.render;

import java.util.ArrayList;

import neko.Client;
import neko.module.Category;
import neko.module.Module;
import neko.module.ModuleManager;
import neko.module.other.Bonus;
import neko.module.other.Rank;
import neko.module.other.enums.Rate;
import neko.utils.RenderUtils;
import neko.utils.Utils;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.chunk.Chunk;

public class Render extends Module {	
	public static ArrayList<Bonus> bon = new ArrayList<>();
	public static float cR=0.33F;
	public static float cG=0.33F;
	public static float cB=0.33F;
	public static int bonusCount=0;
	public static int count=0;
	public static boolean active=true;
	public static boolean xp=true;
	Client var = Client.getNeko();
	
	public Render() {
		super("Render", -1, Category.HIDE);
	}	
	
	public void onRender3DA() {	
		if (Utils.verif!=null)
			return;
		try {
			if (bon.size()!=0 && active) {
				for (Bonus bonus : bon) {
					BlockPos currentBlock=bonus.getB();
					RenderUtils.drawBlockESP(currentBlock.getX() - mc.getRenderManager().renderPosX, currentBlock.getY() - mc.getRenderManager().renderPosY, currentBlock.getZ() - mc.getRenderManager().renderPosZ, bonus.getcR(), bonus.getcG(), bonus.getcB(), 0.15F, bonus.getcR(), bonus.getcG(), bonus.getcB(), 0.15F, 5F);
					double neko = -1+Math.random()*2;									
					double nekoN = -1+Math.random()*2;
					double n = Math.random()-0.25;
					double nekoY = Math.random()*1.15+n;			
					mc.theWorld.spawnParticle(EnumParticleTypes.REDSTONE, currentBlock.getX()+neko, currentBlock.getY()+nekoY, currentBlock.getZ()+nekoN, 0, 0, 0, 0);
				}
			}
		} catch (Exception e) {}
	}
	
	public void onAction() {
		if (Utils.verif!=null)
			return;
		if (Math.random()<0.0000001) {
			u.addChat("§4§krrr§5LES DIEUX VOUS ONT OFFERT 777 AMES DIVINES§4§krrr");
			var.ame+=777;
		}
		if (Math.random()<0.0000001) {
			u.addChat("§4§krrr§5LES DIEUX VOUS ONT OFFERT 1 RANG SATANIQUE§4§krrr");
			u.setRank(u.getRandRank(Rate.Satanique));
		}
		
		try {
			if (Math.random()<0.0004+var.chance+var.rang.getGiftPlus()*0.0004 && !u.isToggle("Timer") && active) {
				for (int k=0;k<60;k++) {
					BlockPos b = new BlockPos(Math.random()*100+mc.thePlayer.posX-50, Math.random()*50+mc.thePlayer.posY-25, Math.random()*100+mc.thePlayer.posZ-50);
					Chunk var2 = mc.theWorld.getChunkFromBlockCoords(b);
			        Block blockId = var2.getBlock(b.getX(), b.getY(), b.getZ());
			        Block id2 = var2.getBlock(b.getX(), b.getY()-1, b.getZ());
			        String gain;
			        if (((blockId.getMaterial()==Material.air || blockId.getMaterial() == Material.water || blockId.getMaterial() == Material.carpet || blockId.getMaterial() == Material.fire) && id2.getMaterial()!=Material.air) && b.getY()>0) {
			        	if (Math.random()<0.2+var.rang.getGiftXp()*0.2) {
			        		gain="xp+";
			        		cR=0.44F;
			        		cG=0.99F;
			        		cB=0.44F;
			        	} else if (Math.random()<0.15+var.rang.getGiftAme()*0.15) {
			        		gain="soul";
			        		cR=0.33F;
			        		cG=0.33F;
			        		cB=0.99F;
			        	} else if (Math.random()<0.11+var.rang.getGiftRang()*0.11) {
			        		gain="rang";
			        		cR=0.88F;
			        		cG=0.22F;
			        		cB=0.22F;
			        	} else if (Math.random()<0.04) {
			        		gain="unlock";
			        		cR=0.88F;
			        		cG=0.22F;
			        		cB=0.88F;
			        	} else {
			        		if (Math.random()<0.8-var.rang.getGiftLotterie()*0.8) {
				        		gain="xp";
				        		cR=0.22F;
				        		cG=0.88F;
				        		cB=0.22F;
			        		} else {
			        			gain="lot";
				        		cR=0.88F;
				        		cG=0.44F;
				        		cB=0.22F;
			        		}
			        	}
			        	if ((!xp && (gain.equals("xp") || gain.equals("xp+")))) {} else
			        		bon.add(new Bonus(b, gain, cR, cG, cB, 0));
			        	if (xp && (gain.equals("xp") || gain.equals("xp+"))) {
			        		u.addChat("§2§oUn pack d'xp a été déposé !");
			        	} else if (gain.equals("lot")) {
			        		u.addChat("§oUn ticket de lotterie a été déposé !");
			        	} else if (gain.equals("soul")) {
			        		u.addChat("§9§oDes souls ont été déposées !");
			        	} else if (gain.equals("rang")) {
			        		u.addChat("§c§oUn rang a été déposé !");
			        	} else if (gain.equals("unlock")) {
			        		u.addChat("§d§oUn coffre a été déposé !");
			        	}
			        	break;
			        } else 
			        	continue;
				}
				
			} else if (bon.size()!=0) {
				for (Bonus bonus : bon) {
					BlockPos currentBlock = bonus.getB();
					if (((Math.round(mc.thePlayer.posZ)==Math.round(currentBlock.getZ()) && Math.round(mc.thePlayer.posX)==Math.round(currentBlock.getX()) && (Math.round(mc.thePlayer.posY)==Math.round(currentBlock.getY()-1) || Math.round(mc.thePlayer.posY)==Math.round(currentBlock.getY()))) || mc.thePlayer.getDistanceSq(currentBlock)<1+var.rang.getRadiusGift())) {
						int xp;
						mc.thePlayer.playSound("random.orb", 1.0F, 1.0F);
						bonusCount++;
						String gain = bonus.getGain();
						switch (gain) {
						case "xp":
							if (Math.random()<0.8)
								xp = u.getRandInt(300)+u.getRandInt(100);
							else
								xp = u.getRandInt(500)+u.getRandInt(200);
							u.addChat("§bGain de "+xp+"xp !");
							u.checkXp(xp);
							break;
						case "xp+":
							if (Math.random()<0.8)
								xp = u.getRandInt(700)+u.getRandInt(400);
							else
								xp = u.getRandInt(1000)+u.getRandInt(600);
							u.addChat("§bGain de "+xp+"xp !");
							u.checkXp(xp);
							break;
						case "soul":
							if (Math.random()<0.8-var.rang.getGiftPlusAme())
								xp = 1+u.getRandInt(u.getRandInt(5));
							else
								xp = 1+u.getRandInt(u.getRandInt(9));
							u.addChat("§9Gain de "+xp+" souls !");
							var.ame+=xp;
							break;
						case "rang":
							double i=Math.random();
							String s="";
							if (i<0.007+0.007*var.rang.getLotRateSatanique()) { // Satanique aléatoire
								s = u.getRandRank(Rate.Satanique);
								if (s!=null) {
									u.setRank(s);
								}
							} else if (i<0.0125+0.0125*var.rang.getLotRateDivin()) { // Divin aléatoire
								s = u.getRandRank(Rate.Divin);
								if (s!=null) {
									u.setRank(s);
								}
							} else if (i<0.025+0.025*var.rang.getLotRateMagical()) { // Magical
								s = u.getRandRank(Rate.Magical);
								if (s!=null) {
									u.setRank(s);
								}
							} else if (i<0.05+0.05*var.rang.getLotRateUltraRare()) { // UltraRare
								s = u.getRandRank(Rate.UltraRare);
								if (s!=null) {
									u.setRank(s);
								}
							} else if (i<0.1+0.1*var.rang.getLotRateRare()) { // Rare
								s = u.getRandRank(Rate.Rare);
								if (s!=null) {
									u.setRank(s);
								}
							} else { // Ordinaire
								s = u.getRandRank(Rate.Ordinaire);
								if (s!=null) {
									u.setRank(s);
								}
							}
														
							u.addChat("§5Vous débloquez le rang "+u.getRankColor(s)+s+"§5 au lvl §7"+u.getRank(s).getColor()+u.getRank(s).getLvl()+"§7 !");
							int rang=0;
							for (Rank r : ModuleManager.rang) {
								if (!r.isLock())
									rang++;
							}							
							if (rang==10 && u.getRank(s).getLvl()==1) {
								u.addChat("§5§k77§c10 rangs débloqués !§5§k88");
								u.addChat("§5§k77§c10 billets de lotterie gagnés !§5§k88");
								var.lot+=10;
							} else if (rang==25 && u.getRank(s).getLvl()==1) {
								u.addChat("§5§k77§c25 rangs débloqués !§5§k88");
								u.addChat("§5§k77§c15 billets de lotterie gagnés !§5§k88");
								var.lot+=15;
							} else if (rang==50 && u.getRank(s).getLvl()==1) {
								u.addChat("§5§k77§c50 rangs débloqués !§5§k88");
								u.addChat("§5§k77§c20 billets de lotterie gagnés !§5§k88");
								var.lot+=20;
							} else if (rang==100 && u.getRank(s).getLvl()==1) {
								u.addChat("§5§k77§c100 rangs débloqués !§5§k88");
								u.addChat("§5§k77§c25 billets de lotterie gagnés !§5§k88");
								var.lot+=25;
							} else if (rang==150 && u.getRank(s).getLvl()==1) {
								u.addChat("§5§k77§c150 rangs débloqués !§5§k88");
								u.addChat("§5§k77§c30 billets de lotterie gagnés !§5§k88");
								var.lot+=30;
							}
							u.saveAll();
							break;
						case "unlock":
							u.getRandUnlock();
							u.saveAll();
							break;
							
						case "lot":
							int lot = u.getRandInt(2)+1;
							u.addChat("Vous gagnez "+lot+" ticket(s) de lotterie !");
							var.lot+=lot;
							break;
						}
						bon.remove(bonus);
					} else {
						bonus.setCount(bonus.getCount()+1);
						if (bonus.getCount()>=80000) {						
							bon.remove(bonus);							
						}
					}
				}
			}
		} catch (Exception e) {}
	}
	
	public static void MeteoreRain() {
		Client var = Client.getNeko();
		Minecraft mc = Minecraft.getMinecraft();
		for (int j=0;j<100;j++)
		for (int k=0;k<100;k++) {
			BlockPos b = new BlockPos(Math.random()*30+mc.thePlayer.posX-15, Math.random()*30+mc.thePlayer.posY-15, Math.random()*30+mc.thePlayer.posZ-15);
			Chunk var2 = mc.theWorld.getChunkFromBlockCoords(b);
	        Block blockId = var2.getBlock(b.getX(), b.getY(), b.getZ());
	        Block id2 = var2.getBlock(b.getX(), b.getY()-1, b.getZ());
	        String gain;
	        if (((blockId.getMaterial()==Material.air || blockId.getMaterial() == Material.water || blockId.getMaterial() == Material.carpet || blockId.getMaterial() == Material.fire) && id2.getMaterial()!=Material.air) && b.getY()>0) {
	        	if (Math.random()<0.2+var.rang.getGiftXp()*0.2) {
	        		gain="xp+";
	        		cR=0.44F;
	        		cG=0.99F;
	        		cB=0.44F;
	        	} else if (Math.random()<0.15+var.rang.getGiftAme()*0.15) {
	        		gain="soul";
	        		cR=0.33F;
	        		cG=0.33F;
	        		cB=0.99F;
	        	} else if (Math.random()<0.11+var.rang.getGiftRang()*0.11) {
	        		gain="rang";
	        		cR=0.88F;
	        		cG=0.22F;
	        		cB=0.22F;
	        	} else if (Math.random()<0.04) {
	        		gain="unlock";
	        		cR=0.88F;
	        		cG=0.22F;
	        		cB=0.88F;
	        	} else {
	        		if (Math.random()<0.8-var.rang.getGiftLotterie()*0.8) {
		        		gain="xp";
		        		cR=0.22F;
		        		cG=0.88F;
		        		cB=0.22F;
	        		} else {
	        			gain="lot";
		        		cR=0.88F;
		        		cG=0.44F;
		        		cB=0.22F;
	        		}
	        	}
	        	if ((!xp && (gain.equals("xp") || gain.equals("xp+")))) {} else
	        		bon.add(new Bonus(b, gain, cR, cG, cB, 0));
	        	break;
	        } else 
	        	continue;
		}
	}
}
