package neko.module.modules.render;

import neko.gui.InGameGui;
import neko.manager.ModuleManager;
import neko.module.Category;
import neko.module.Module;
import neko.utils.RenderUtils;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.util.BlockPos;

public class ArmorHUD extends Module {

	public ArmorHUD() {
		super("ArmorHUD", -1, Category.RENDER);
	}
	
	public void onEnabled() {		
		super.onEnabled();
	}
	
	public void onDisabled() {
		super.onDisabled();
	}
}
