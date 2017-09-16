package neko.module.modules;

import org.lwjgl.input.Keyboard;

import neko.module.Category;
import neko.module.Module;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;

public class Eagle extends Module {
	private static Eagle instance;
	
	public Eagle() {
		super("Eagle", -1, Category.PLAYER);
		instance=this;
	}
	
	public static Eagle getEagle() {
		return instance;
	}
	
	public void onEnabled() {		
		super.onEnabled();
	}
	
	public void onDisabled() {
		if ((mc.gameSettings.keyBindSneak.pressed & !Keyboard.isKeyDown(this.mc.gameSettings.keyBindSneak.getKeyCode()))) {
			mc.gameSettings.keyBindSneak.pressed = false;
		}
		super.onDisabled();
	}		
	
	public void onUpdate() {	
		 BlockPos bp = new BlockPos(this.mc.thePlayer.posX, this.mc.thePlayer.posY - 1.0D, this.mc.thePlayer.posZ);
		    if (mc.thePlayer.fallDistance <= 4.0F) {
			      if (mc.theWorld.getBlockState(bp).getBlock() != Blocks.air) {
			    	  mc.gameSettings.keyBindSneak.pressed = false;
			      } else {
			    	  mc.gameSettings.keyBindSneak.pressed = true;
			      }
		    }
	}
}








