package neko.module.modules.special;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import neko.Client;
import neko.manager.ModuleManager;
import neko.module.Category;
import neko.module.Module;
import neko.module.modules.render.ItemESP;
import neko.module.other.enums.MagnetWay;
import neko.utils.RenderUtils;
import neko.utils.TpUtils;
import neko.utils.Utils;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.BlockPos;

public class Magnet extends Module {
	private boolean classic = false;
	private MagnetWay mode = MagnetWay.Single;
	private static javax.swing.Timer timer = new javax.swing.Timer(500, new check());
	private static Magnet instance;
	
	public Magnet() {
		super("Magnet", -1, Category.Special);
		instance=this;
	}
	
	public static Magnet getMagnet() {
		return instance;
	}
    
	public void onEnabled() {
		timer.start();
		super.onEnabled();
	}
	
	public void onDisabled() {
		timer.stop();
		super.onDisabled();
	}
	
	public void setValues() {
		this.values = "§6Tp classic:§7 "+Utils.displayBool(classic)+"\n"
				+ "§6Mode de ramassage:§7 "+mode.name();
	}
    
	public boolean isClassic() {
		return classic;
	}

	public void setClassic(boolean classic) {
		this.classic = classic;
	}

	public MagnetWay getMode() {
		return mode;
	}

	public void setMode(MagnetWay mode) {
		this.mode = mode;
	}
}

class check implements ActionListener {
	Minecraft mc = Minecraft.getMinecraft();
	Client var = Client.getNeko();
	TpUtils tp = new TpUtils();	
	Magnet m = Magnet.getMagnet();
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		try {
			for (Object o : mc.theWorld.loadedEntityList) {
				if (o instanceof EntityItem && !(o instanceof EntityArmorStand)) {
					EntityItem entity = (EntityItem) o;   
		        	BlockPos bll = entity.getPosition();
		        	if (mc.theWorld.getBlockState(bll).getBlock().getMaterial() != Material.air ||
		        			mc.theWorld.getBlockState(new BlockPos(bll.getX(), bll.getY()+1, bll.getZ())).getBlock().getMaterial() != Material.air || 
		        			!mc.thePlayer.canEntityBeSeen(entity)) {
						continue;
		        	}
		        	Double b1 = mc.thePlayer.posX;
		        	Double b2 = mc.thePlayer.posY;
		        	Double b3 = mc.thePlayer.posZ;
		        	String how = tp.doTpAller(entity, tp.getTargetInPos(bll).get(0)+0.5, tp.getTargetInPos(bll).get(1), tp.getTargetInPos(bll).get(2)+0.5, Magnet.getMagnet().isClassic(), tp.getK(bll));
		        	tp.doTpRetour(how, tp.getTargetInPos(bll).get(0), tp.getTargetInPos(bll).get(1), tp.getTargetInPos(bll).get(2), tp.getK(bll));
	        		mc.thePlayer.setPosition(b1, b2+0.001*Math.random(), b3);
		        	if (m.getMode()==MagnetWay.Single)
		        		break;
				}
	        } 
		} catch (Exception e) {}
	}	
	
}
