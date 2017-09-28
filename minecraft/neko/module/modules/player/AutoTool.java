package neko.module.modules.player;

import neko.module.Category;
import neko.module.Module;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.BlockPos;

public class AutoTool extends Module {

	public AutoTool() {
		super("AutoTool", -1, Category.PLAYER);
	}

	public void onEnabled() {
		super.onEnabled();
	}

	public void onDisabled() {
		super.onDisabled();
	}

	public void onUpdate() {
		try {
			if ((mc.playerController.curBlockDamageMP > 0.0D) && (mc.objectMouseOver.func_178782_a() != null)) {
				setSlot(mc.objectMouseOver.func_178782_a());
			}
		} catch (Exception e) {}
	}	

	private void setSlot(BlockPos blockPos) {
		float bestSpeed = 1.0F;
		int bestSlot = -1;
		Block block = mc.theWorld.getBlockState(blockPos).getBlock();
		for (int i = 0; i < 9; i++) {
			ItemStack item = this.mc.thePlayer.inventory.getStackInSlot(i);
			if (item != null) {
				float speed = item.getStrVsBlock(block);
				if (speed > bestSpeed) {
					bestSpeed = speed;
					bestSlot = i;
				}
			}
		}
		if (bestSlot != -1) {
			mc.thePlayer.inventory.currentItem = bestSlot;
		}
	}
}
