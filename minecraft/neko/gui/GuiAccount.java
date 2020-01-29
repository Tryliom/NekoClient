package neko.gui;

import java.io.IOException;

import org.lwjgl.input.Keyboard;

import neko.Client;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.ResourceLocation;

public class GuiAccount extends GuiScreen {
	private GuiScreen prevGui;
	private Minecraft mc = Minecraft.getMinecraft();
	private ResourceLocation background = mc.getTextureManager().getDynamicTextureLocation("background",
			GuiMainMenu.viewportTexture);
	private Client var = Client.getNeko();
	private String error = "";

	public GuiAccount(GuiScreen gui) {
		this.prevGui = gui;
	}

	public void initGui() {
		this.buttonList.clear();
		this.buttonList.add(new GuiButton(1, this.width / 2 - 25, this.height / 3 + 98, 50, 20, "Retour"));
		this.buttonList.add(new GuiButton(4, this.width / 2 -50, this.height / 3 + 74, 100, 20, "§cSe déconnecter"));
		this.buttonList.add(new GuiButton(5, this.width-110, 10, 100, 20, "Music Manager"));
	}

	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		ScaledResolution sr = new ScaledResolution(mc);
		this.mc.getTextureManager().bindTexture(this.background);
		Gui.drawScaledCustomSizeModalRect(0, 0, 0.0F, 0.0F, sr.getScaledWidth(), sr.getScaledHeight(),
				sr.getScaledWidth(), sr.getScaledHeight(), sr.getScaledWidth(), sr.getScaledHeight());

		drawDefaultBackground();

		drawCenteredString(var.NekoFont, "§e§nVotre compte Neko", this.width / 2, 10, 16777215);
		drawCenteredString(var.NekoFont, "§c" + this.error, this.width / 2, 130, 16777215);
		String var11 = Client.getNeko().strCreator;
		this.drawString(var.NekoFont, var11, this.width - var.NekoFont.getStringWidth(var11) - 2, this.height - 10, -1);
		super.drawScreen(mouseX, mouseY, partialTicks);
	}

	protected void actionPerformed(GuiButton button) throws IOException {
		super.actionPerformed(button);
		switch (button.id) {
		case 1:
			mc.displayGuiScreen(this.prevGui);
			break;
		case 4:
			this.mc.displayGuiScreen(new GuiConnect(new GuiMainMenu(), 1));
			break;
		case 5:
			this.mc.displayGuiScreen(new GuiXrayManager(this));
			break;
		}
	}

	protected void keyTyped(char typedChar, int keyCode) throws IOException {
		super.keyTyped(typedChar, keyCode);
	}

	public void handleMouseInput() throws IOException {
		super.handleMouseInput();
	}

	protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
		super.mouseClicked(mouseX, mouseY, mouseButton);
	}

	public void onGuiClosed() {
		Keyboard.enableRepeatEvents(false);
		super.onGuiClosed();
	}

}
