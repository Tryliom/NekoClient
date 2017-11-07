package neko.module.modules.special;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import neko.Client;
import neko.manager.ModuleManager;
import neko.module.Category;
import neko.module.Module;
import neko.module.other.enums.MagnetWay;
import neko.utils.RenderUtils;
import neko.utils.TpUtils;
import neko.utils.Utils;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemSword;
import net.minecraft.util.BlockPos;

public class Likaotique extends Module {
	private static javax.swing.Timer timer = new javax.swing.Timer(300, new tptimer());
	private BlockPos currPos = null;
	private static Likaotique instance;
	
	public Likaotique() {
		super("Likaotique", -1, Category.Special);
		instance=this;
	}
	
	public static Likaotique getLik() {
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
		this.values = "";
	}
	
	public BlockPos getCurrPos() {
		return currPos;
	}

	public void setCurrPos(BlockPos currPos) {
		this.currPos = currPos;
	}

	public Boolean isPositionValid(BlockPos bp) {
		if (mc.theWorld.getBlockState(bp).getBlock().isSolidFullCube() || mc.theWorld.getBlockState(new BlockPos(bp.getX(), bp.getY(), bp.getZ())).getBlock().isSolidFullCube())
			return false;
		return true;
	}
}

class tptimer implements ActionListener {
	Minecraft mc = Minecraft.getMinecraft();
	Client var = Client.getNeko();
	TpUtils tp = new TpUtils();	
	Likaotique m = Likaotique.getLik();
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		try {
			boolean find = false;
			BlockPos b = null;
			for (int i=0;i<5;i++) {
				b = Utils.getRandBlock(5, 10/100);
				if (!Likaotique.getLik().isPositionValid(b)) {
					if (Likaotique.getLik().isPositionValid(new BlockPos(b.getX(), b.getY()+1, b.getZ()))) {
						b = new BlockPos(b.getX(), b.getY()+1, b.getZ());
						find = true;
					} else
						continue;
				} else
					find = true;
			}
			if (!find) {
				Likaotique.getLik().setCurrPos(b);
			} else {
				Likaotique.getLik().setCurrPos(b);
				// Diviser par 2 la dist entre le point b et le joueur et envoyer radius/4 paquet de tp vers le point b si dist + de 4 
			}
			
		} catch (Exception e) {}
	}	
	
}














