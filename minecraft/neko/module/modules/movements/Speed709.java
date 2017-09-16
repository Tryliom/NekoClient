package neko.module.modules.movements;

import java.util.ArrayList;

import org.lwjgl.input.Keyboard;

import neko.module.Category;
import neko.module.Module;
import neko.module.other.SpeedEnum;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;

public class Speed709 extends Module {	
	private double spe=0.5;
	private SpeedEnum mode=SpeedEnum.Air;
	private ArrayList<Block> list = new ArrayList<>();
	private static Speed709 instance;
	
	public Speed709() {
		super("Speed", Keyboard.KEY_M, Category.MOVEMENT);
		instance=this;
	}
	
	public static Speed709 getSpeed() {
		return instance;
	}
	
	public void onEnabled() {
		super.onEnabled();
	}
	
	public void onDisabled() {
		mc.thePlayer.speedInAir=0.02F;
		for (Block b : list) {
			b.slipperiness=0.6F;
		}
		list.clear();
		super.onDisabled();
	}
	
	public void onUpdate() {
		if (this.mode.equals(SpeedEnum.Air)) {
			if (mc.thePlayer.onGround) {
				mc.thePlayer.motionY=0.00000000000000000000000000000000000000000000000000000000000000000000000000000000000001D;
				mc.thePlayer.speedInAir=(float) spe;			
			}
		} else if (this.mode.equals(SpeedEnum.Ground)) {
			if (mc.thePlayer.onGround) {
				mc.thePlayer.speedInAir=0.02F;
				BlockPos bp = new BlockPos(mc.thePlayer.posX, mc.thePlayer.posY-1, mc.thePlayer.posZ);
				Block b = mc.theWorld.getBlockState(bp).getBlock();
				if (!list.contains(b))
					list.add(b);
				if (!b.equals(Blocks.air))
					b.slipperiness=1.01F;					
			}
		}
	}

	public SpeedEnum getMode() {
		return mode;
	}

	public void setMode(SpeedEnum mode) {
		this.mode = mode;
	}

	public double getSpe() {
		return spe;
	}

	public void setSpe(double spe) {
		this.spe = spe;
	}
}
