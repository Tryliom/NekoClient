package neko.module.modules.special;

import neko.module.Category;
import neko.module.Module;
import neko.utils.Utils;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.util.BlockPos;

public class ForceTP extends Module {
	private static ForceTP instance;
	private BlockPos point = new BlockPos(0,0,0);
	private boolean YMax = true;
	
	public ForceTP() {
		super("ForceTP", -1, Category.Special);
		this.instance=this;
	}
	
	public static ForceTP getForceTP() {
		return instance;
	}
	
	public void onEnabled() {		
		super.onEnabled();
	}
	
	public void onDisabled() {
		super.onDisabled();
	}
	
	public void setValues() {
		this.values = "§6Coordonées pour tp:§7 "+point.getX()+", "+point.getY()+", "+point.getZ()+"\n"
				+ "§6Se tp toujours au max de Y (255):§7 "+Utils.displayBool(YMax);
	}
	
	public BlockPos getPoint() {
		return point;
	}

	public void setPoint(BlockPos point) {
		this.point = point;
	}

	public boolean isYMax() {
		return YMax;
	}

	public void setYMax(boolean yMax) {
		YMax = yMax;
	}

	public void onUpdate() {
		BlockPos bp = new BlockPos(this.point.getX(), (this.YMax ? 255 : this.point.getY()), this.point.getZ());
		if (Math.random()<0.3)
			for (int i=0;i<10;i++)
				mc.getNetHandler().addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(bp.getX()+Math.random()*5,bp.getY(),bp.getZ()+Math.random()*5, true));
		super.onUpdate();
	}
}
