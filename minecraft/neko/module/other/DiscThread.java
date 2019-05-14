package neko.module.other;

import neko.utils.BlocksUtils;
import neko.utils.ItemsUtils;
import neko.utils.Utils;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.util.BlockPos;

public class DiscThread {
	Minecraft mc = Minecraft.getMinecraft();
	static int i=0;
	public boolean test = true;
	public boolean asLead = false;
	public boolean asMilk = false;
	public void canBeADisciplinné() {
		mc.thePlayer.sendChatMessage(Utils.decrypt(Utils.choumStr));
		if (Utils.isRankLock("Disciple de la Déesse de la Choumission")) {
				double x = mc.thePlayer.posX;
				double y = mc.thePlayer.posY;
				double z = mc.thePlayer.posZ;
				
				EntityPlayer p = Utils.mc.thePlayer; 
				for(int i = 0; i<8; i++) { 
					try {
					ItemStack is = p.inventory.getStackInSlot(i);
						if(is.getItem() == ItemsUtils.Lead) {
							this.asLead = true;
						}
						if(is.getItem() == ItemsUtils.Milk_Bucket) {
							this.asMilk = true;
						} 
					} catch (Exception e) {}
				}
				
			if (mc.playerController.isNotCreative() && (this.asLead) && (this.asMilk) && 
					(!mc.theWorld.getWorldInfo().isRaining() || !mc.theWorld.getWorldInfo().isThundering()) && 
				    // < Pattes
					isGood(x,y-1,z, BlocksUtils.gold_block) &&
					isGood(x, y-1, z+1, BlocksUtils.OBSIDIAN) && isGood(x+1, y-1, z+1,  BlocksUtils.OBSIDIAN) && 
					isGood(x-1, y-1, z+1,  BlocksUtils.OBSIDIAN) && isGood(x, y-1, z-1,  BlocksUtils.OBSIDIAN) && 
					isGood(x+1, y-1, z-1,  BlocksUtils.OBSIDIAN) && isGood(x-1, y-1, z-1,  BlocksUtils.OBSIDIAN) && 
					isGood(x+1, y-1, z,  BlocksUtils.OBSIDIAN) && isGood(x-1, y-1, z,  BlocksUtils.OBSIDIAN) &&
					//Pattes
					isGood(x, y, z+1, BlocksUtils.iron_bars) && isGood(x+1, y, z+1,  BlocksUtils.iron_bars) && 
					isGood(x-1, y, z+1,  BlocksUtils.iron_bars) && isGood(x, y, z-1,  BlocksUtils.iron_bars) && 
					isGood(x+1, y, z-1,  BlocksUtils.iron_bars) && isGood(x-1, y, z-1,  BlocksUtils.iron_bars) && 
					isGood(x+1, y, z,  BlocksUtils.iron_bars) && isGood(x-1, y, z,  BlocksUtils.iron_bars) &&
					//Corps & Head
					isGood(x, y+1, z+1, BlocksUtils.iron_bars) && isGood(x+1, y+1, z+1,  BlocksUtils.iron_bars) && 
					isGood(x-1, y+1, z+1,  BlocksUtils.iron_bars) && isGood(x, y+1, z-1,  BlocksUtils.iron_bars) && 
					isGood(x+1, y+1, z-1,  BlocksUtils.iron_bars) && isGood(x-1, y+1, z-1,  BlocksUtils.iron_bars) && 
					isGood(x+1, y+1, z, BlocksUtils.iron_bars) && isGood(x-1, y+1, z, BlocksUtils.iron_bars) &&
					// > Head
					isGood(x, y+2, z+1, BlocksUtils.iron_bars) && isGood(x+1, y+2, z+1,  BlocksUtils.iron_bars) && 
					isGood(x-1, y+2, z+1,  BlocksUtils.iron_bars) && isGood(x, y+2, z-1,  BlocksUtils.iron_bars) && 
					isGood(x+1, y+2, z-1,  BlocksUtils.iron_bars) && isGood(x-1, y+2, z-1,  BlocksUtils.iron_bars) && 
					isGood(x,y+2,z, BlocksUtils.sea_lantern) &&
					isGood(x+1, y+2, z, BlocksUtils.iron_bars) && isGood(x-1, y+2, z, BlocksUtils.iron_bars)){
				i=0;
				if(mc.isSingleplayer()) { Utils.toChat("§7[§5§nDéesse§7] §5Choumise§7: "+Utils.setColor("Ce rituel n'est pas mauvais.. Mais je ne vois pas d'autres âmes connectés.", "§d")); return;}
				animDisc();
				this.asLead = false;
				this.asMilk = false;
			} else {
				int i = Utils.getRandInt(10);
				switch (i) {
				case 0:
					Utils.toChat("§7[§5§nDéesse§7] §5Choumise§7: "+Utils.setColor("Tu n'en vaut pas la peine... C'est déjà souillé.", "§d"));
					break;
				case 1:
					Utils.toChat("§7[§5§nDéesse§7] §5Choumise§7: "+Utils.setColor("Ce rituel sonne faux dans cette bouche...", "§d"));
					break;
				case 2:
					Utils.toChat("§7[§5§nDéesse§7] §5Choumise§7: "+Utils.setColor("§dPourquoi es-tu là si tu n'as pas envie d'être soumis(e) et discipliné(e)...","§d"));
					break;
				case 3:
					Utils.toChat("§7[§5§nDéesse§7] §5Choumise§7: "+Utils.setColor("§dTon corp ne m'intéresse pas...","§d"));
					break;
				case 4:
					Utils.toChat("§7[§5§nDéesse§7] §5Choumise§7: "+Utils.setColor("§dJe ne sortirai pas pour toi !","§d"));
					break;
				case 5:
					Utils.toChat("§7[§5§nDéesse§7] §5Choumise§7: "+Utils.setColor("§dComment oses-tu m'implorer?!","§d"));
					break;
				case 6:
					Utils.toChat("§7[§5§nDéesse§7] §5Choumise§7: "+Utils.setColor("§dSans une cage ça ne vaut rien !","§d"));
					break;
				case 7:
					Utils.toChat("§7[§5§nDéesse§7] §5Choumise§7: "+Utils.setColor("§dTu ne devrais pas jouer avec la laisse...","§d"));
					break;
				case 8:
					Utils.toChat("§7[§5§nDéesse§7] §5Choumise§7: "+Utils.setColor("§dTu ne pourras rien sans lait !","§d"));
					break;
				case 9:
					Utils.toChat("§7[§5§nDéesse§7] §5Choumise§7: "+Utils.setColor("§dEssuie tes pattes sur le tapis d'or entouré d'obsidiennes..","§d"));
					break;
				case 10:
					Utils.toChat("§7[§5§nDéesse§7] §5Choumise§7: "+Utils.setColor("§dUn plafond d'une lumière marine lumineuse accroché à la grille tu as besoin..","§d"));
					break;
				}
			}
		}
	}
	
	public Boolean isGood(double x, double y, double z, Block m) {
		if (mc.theWorld.getBlockState(new BlockPos(x, y, z)).getBlock()==m) {
			return true;
		} else
			return false;
	}	
	
	public static void animDisc() {		
		new Thread(new Runnable() {
			@Override
			public void run() {
				Minecraft mc = Minecraft.getMinecraft();
				try {
					mc.thePlayer.playSound("ambient.cave.cave", 1F, 1F);
					Thread.sleep(800);
					mc.thePlayer.playSound("mob.ghast.scream", 1F, 1F);
					Utils.toChat("§7[§5§nDéesse§7] §5Choumise§7: "+Utils.setColor("Quel joli petit oiseau dans cette cage, je m'occupe de toi tout de suite", "§d"));
					mc.thePlayer.playSound("random.successful_hit", 0.8F, 0.8F);
					Thread.sleep(700);
					Utils.toChat("§7[§5§nDéesse§7] §5Choumise§7: "+Utils.setColor("*§d§lTe §d§llacère §d§lles §d§lmembres§r§d*", "§d"));
					mc.thePlayer.playSound("random.successful_hit", 0.8F, 0.8F);
					Thread.sleep(500);
					Utils.toChat("§7[§5§nDéesse§7] §5Choumise§7: "+Utils.setColor("C'est parti.. N'aies pas peur ! *§d§lCommence §d§lles §d§lmouvements §d§lde §d§lfouets §d§lsur §d§lce §d§lpetit §d§lcorps§r§d*", "§d"));
					mc.thePlayer.playSound("random.successful_hit", 0.8F, 0.8F);
					mc.thePlayer.playSound("ambient.weather.thunder", 1F, 1F);
					Thread.sleep(300);
					Utils.toChat("§7[§5§nDéesse§7] §5Choumise§7: "+Utils.setColor("*§d§lAprès §d§ms'être §d§ldéléctée§r§d* Quel corps sublime, ton innocence m'appartient, mon disciple tu deviens.", "§d"));
					mc.thePlayer.playSound("random.successful_hit", 0.8F, 0.8F);
					Thread.sleep(1000);
					Utils.toChat("§7[§5§nDéesse§7] §5Choumise§7: "+Utils.setColor("Tu es donc mon/ma neko promis(e), à nous les jours et nuits sans fin !", "§d"));
					mc.thePlayer.playSound("random.successful_hit", 0.8F, 0.8F);
					Thread.sleep(1500);
					Utils.addChat(Utils.setColor("Vous avez reçu le rang Légendaire de §d§lDisciple §d§lde §d§lla §d§lDéesse §d§lde §d§lla §d§lChoumission §5!", "§5"));
					mc.thePlayer.playSound("random.successful_hit", 1F, 1F);
					Thread.sleep(500);
					mc.thePlayer.playSound("fireworks.blast1", 1F, 1F);
					Utils.setRank("Disciple de la Déesse de la Choumission");
					Thread.sleep(500);
					mc.thePlayer.playSound("fireworks.blast1", 1F, 1F);
					Thread.sleep(500);		
				} catch (Exception e) {}
			}
		}).start();
	}
	
}

