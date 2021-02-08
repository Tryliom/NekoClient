package neko.gui.xraymanager;

import java.util.ArrayList;
import java.util.List;

import neko.Client;
import neko.utils.BlocksUtils;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class XRayListEntryFound extends XRayListEntry {
	
	private final Block b;
	
	public XRayListEntryFound(GuiScreenXrayManager guiList, Block bb) {
		super(guiList);
		this.b = bb;
	}
	
	protected Block getBlock() {
		return this.b;
	}

	@Override
	protected String XrayBlockDescription() {
		return this.getBlock().getUnlocalizedName();
	}

	@Override
	protected String XrayBlockName() {
		return this.getBlock().getLocalizedName();
	}

	@Override
	protected void BindIcon() {
		Block block = this.getBlock();
		TextureAtlasSprite sprite = Minecraft.getMinecraft().getBlockRendererDispatcher().getBlockModelShapes().getTexture(block.getDefaultState());
		if(sprite != null) {
			String iconName = sprite.getIconName();
			String[] strs = iconName.split(":");
			if(strs.length > 1) {
				String resource = strs[0] + ":textures/" + strs[1] + ".png";
	            ResourceLocation r = new ResourceLocation(resource);
	            this.mc.getTextureManager().bindTexture(r);
	        }
		}
	}

}
