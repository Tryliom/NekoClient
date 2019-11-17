package neko.gui.button;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

public class ButtonDiscord extends GuiButton {
	
	private static final ResourceLocation DiscordIcon = new ResourceLocation("neko/button/DiscordIcon.png");
	private static final ResourceLocation DiscordHoverIcon = new ResourceLocation("neko/button/DiscordHoverIcon.png");
	
	public ButtonDiscord(int buttonId, int x, int y, int WidthIn, int HeightIn) {
		super(buttonId, x, y, WidthIn, HeightIn, ""); //ID, posX, posY, Largeur, Hauteur, Texte affiché (Ici, jamais de texte)
	}
	
	public void drawButton(Minecraft mc, int mouseX, int mouseY) {
		if(this.visible) {
			boolean mouseHover = mouseX >= this.xPosition && mouseY >= this.yPosition &&
					mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.height;
			if(mouseHover) {
				mc.getTextureManager().bindTexture(DiscordHoverIcon);
			} else {
				mc.getTextureManager().bindTexture(DiscordIcon);
			}
			GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
			Gui.drawScaledCustomSizeModalRect(this.xPosition+(width/2)-10, this.yPosition+(height/2)-10, 0, 0, 128, 128, 20, 20, 128, 128);
		}
	}

}
