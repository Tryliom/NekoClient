package neko.module.modules.player;

import org.lwjgl.input.Keyboard;

import neko.Client;
import neko.module.Category;
import neko.module.Module;
import neko.utils.RenderUtils;
import neko.utils.Utils;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFireworkSparkFX;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumParticleTypes;

public class Fire extends Module {
	public static int p = 1;

	public Fire() {
		super("Fire", -1, Category.RENDER);
	}

	public void onEnabled() {
		super.onEnabled();
	}

	public void onDisabled() {
		super.onDisabled();
	}

	public void setValues() {
		this.values = "§6Nombre de particules:§7 "+p;
	}

	public void onUpdate() {
		Utils.doFlame(p);
	}
	
}
