package neko.module.other;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

import neko.utils.Utils;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.util.BlockPos;

public class PyroThread {
	Minecraft mc = Minecraft.getMinecraft();
	static int i=0;
	public void canBeAPyroman() {
		mc.thePlayer.sendChatMessage(Utils.decrypt(Utils.pyroStr));
		if (Utils.isRankLock("Pyroman")) {
				double a = mc.thePlayer.posX;
				double b = mc.thePlayer.posY;
				double c = mc.thePlayer.posZ;
				
			if (mc.playerController.isNotCreative() && (mc.theWorld.getWorldInfo().isRaining() || mc.theWorld.getWorldInfo().isThundering()) && isGood(a, b, c+1, Material.fire) && isGood(a+1, b, c+1, Material.fire) && isGood(a-1, b, c+1, Material.fire) && isGood(a, b, c-1, Material.fire) && isGood(a+1, b, c-1, Material.fire) && isGood(a-1, b, c-1, Material.fire) && isGood(a+1, b, c, Material.fire) && isGood(a-1, b, c, Material.fire)) {
				i=0;
				animPyro();
			} else {
				int i = Utils.getRandInt(8);
				switch (i) {
				case 0:
					Utils.toChat("§7[§4§nPyromaniac§7] §4Pyroman§7: "+Utils.setColor("Tu n'en vaut pas la peine...", "§c"));
					break;
				case 1:
					Utils.toChat("§7[§4§nPyromaniac§7] §4Pyroman§7: "+Utils.setColor("CE RITUEL SONNE FAUX DANS CETTE BOUCHE...", "§c"));
					break;
				case 2:
					Utils.toChat("§7[§4§nPyromaniac§7] §4Pyroman§7: §cTa peur se languit sur ton visage...");
					break;
				case 3:
					Utils.toChat("§7[§4§nPyromaniac§7] §4Pyroman§7: §cTon sang ne m'intéresse pas...");
					break;
				case 4:
					Utils.toChat("§7[§4§nPyromaniac§7] §4Pyroman§7: §cJe ne sortirai pas pour toi !");
					break;
				case 5:
					Utils.toChat("§7[§4§nPyromaniac§7] §4Pyroman§7: §cComment oses-tu ?!");
					break;
				case 6:
					Utils.toChat("§7[§4§nPyromaniac§7] §4Pyroman§7: §cSans un temps de chaos ça ne vaut rien !");
					break;
				case 7:
					Utils.toChat("§7[§4§nPyromaniac§7] §4Pyroman§7: §cTu ne devrais pas jouer avec le feu...");
					break;
				case 8:
					Utils.toChat("§7[§4§nPyromaniac§7] §4Pyroman§7: §cTu ne pourras rien sans feu !");
					break;
				}
			}
		}
	}
	
	public Boolean isGood(double a, double b, double c, Material m) {
		if (mc.theWorld.getBlockState(new BlockPos(a, b, c)).getBlock().getMaterial()==m) {
			return true;
		} else
			return false;
	}	
	
	public static void animPyro() {		
		new Thread(new Runnable() {
			@Override
			public void run() {
				Minecraft mc = Minecraft.getMinecraft();
				try {
					mc.thePlayer.playSound("ambient.cave.cave", 1F, 1F);
					Thread.sleep(800);
					mc.thePlayer.playSound("mob.ghast.scream", 1F, 1F);
					Utils.toChat("§7[§4§oPyromaniac§7] §4Pyroman§7: "+Utils.setColor("Tu mérites mon appararition vil créature ! Je vais prendre par ton sang ce qui me revient et te livrer mes secrets de la pyromanie !", "§c"));
					mc.thePlayer.playSound("random.successful_hit", 0.8F, 0.8F);
					Thread.sleep(700);
					Utils.toChat("§7[§4§oPyromaniac§7] §4Pyroman§7: "+Utils.setColor("*Se faufile derrière toi*", "§c"));
					mc.thePlayer.playSound("random.successful_hit", 0.8F, 0.8F);
					Thread.sleep(500);
					Utils.toChat("§7[§4§oPyromaniac§7] §4Pyroman§7: "+Utils.setColor("Mmmmh je vais me servir !", "§c"));
					mc.thePlayer.playSound("random.successful_hit", 0.8F, 0.8F);
					mc.thePlayer.playSound("ambient.weather.thunder", 1F, 1F);
					Thread.sleep(200);
					for (int i = 0; i < 3; i++) {
					      mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(mc.thePlayer.posX, mc.thePlayer.posY + 3D, mc.thePlayer.posZ, false));
					      mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ, false));
					}
					mc.thePlayer.playSound("random.drink", 1F, 1F);
					Thread.sleep(300);
					Utils.toChat("§7[§4§oPyromaniac§7] §4Pyroman§7: "+Utils.setColor("Voici du sang tout chaud dis donc ! >:p", "§c"));
					mc.thePlayer.playSound("random.successful_hit", 0.8F, 0.8F);
					Thread.sleep(1000);
					Utils.toChat("§7[§4§oPyromaniac§7] §4Pyroman§7: "+Utils.setColor("Pour ton offre, je te lègue mes secrets, prend en soin (Ils sont inflammables) !", "§c"));
					mc.thePlayer.playSound("random.successful_hit", 0.8F, 0.8F);
					Thread.sleep(1500);
					Utils.addChat(Utils.setColor("Vous avez reçu le rang Légendaire de Pyroman !", "§d"));
					mc.thePlayer.playSound("random.successful_hit", 1F, 1F);
					Thread.sleep(500);
					mc.thePlayer.playSound("fireworks.blast1", 1F, 1F);
					Utils.setRank("Pyroman");
					Thread.sleep(500);
					mc.thePlayer.playSound("fireworks.blast1", 1F, 1F);
					Thread.sleep(500);		
				} catch (Exception e) {}
			}
		}).start();
	}
	
}
