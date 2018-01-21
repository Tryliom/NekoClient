package neko.gui;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;

import org.lwjgl.input.Keyboard;

import neko.Client;
import neko.module.Category;
import neko.module.Module;
import neko.module.modules.hide.Friends;
import neko.module.other.Account;
import neko.utils.RenderUtils;
import neko.utils.Utils;
import net.mcleaks.Callback;
import net.mcleaks.MCLeaks;
import net.mcleaks.ModApi;
import net.mcleaks.RedeemResponse;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiSlot;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.ResourceLocation;

public class GuiBindManager extends GuiScreen {
	private GuiScreen prevGui;
	private Minecraft mc = Minecraft.getMinecraft();
	private ResourceLocation background = mc.getTextureManager().getDynamicTextureLocation("background",
			GuiMainMenu.viewportTexture);
	private static ArrayList<Account> accounts = new ArrayList();
	private GuiList list;
	private Client var = Client.getNeko();
	private int lastIndex = -1;
	private boolean waitKey = false;

	public GuiBindManager(GuiScreen gui) {
		this.prevGui = gui;
	}

	public void initGui() {

		this.list = new GuiList(this);
		this.list.registerScrollButtons(7, 8);

		this.buttonList.add(new GuiButton(1, this.width / 2 - 50, this.height - 52, 100, 20, "Changer"));
		this.buttonList.add(new GuiButton(2, this.width / 2 - 154, this.height - 52, 100, 20, "Supprimer"));
		this.buttonList.add(new GuiButton(0, this.width / 2 + 4 + 50, this.height - 52, 100, 20, "Retour"));
	}

	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		ScaledResolution sr = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);
		this.mc.getTextureManager().bindTexture(this.background);
		Gui.drawScaledCustomSizeModalRect(0, 0, 0.0F, 0.0F, sr.getScaledWidth(), sr.getScaledHeight(),
				sr.getScaledWidth(), sr.getScaledHeight(), sr.getScaledWidth(), sr.getScaledHeight());

		drawDefaultBackground();
		this.list.drawScreen(mouseX, mouseY, partialTicks);
		drawCenteredString(var.NekoFont, "Bind Manager", this.width / 2, 10, 16777215);
		super.drawScreen(mouseX, mouseY, partialTicks);
	}

	protected void actionPerformed(GuiButton button) throws IOException {
		super.actionPerformed(button);
		switch (button.id) {
		case 0:
			mc.displayGuiScreen(this.prevGui);
			break;
		case 1:
			this.waitKey = true;
			break;
		case 2:
			// Supprimer une bind
			Utils.getModuleWithInt(this.list.selectedSlot).setBind(-1);
			break;
		}
	}

	protected void keyTyped(char typedChar, int keyCode) throws IOException {
		
		if (this.waitKey && keyCode != Keyboard.KEY_ESCAPE) {
			Utils.getModuleWithInt(this.list.selectedSlot).setBind(Keyboard.getKeyIndex(Keyboard.getKeyName(keyCode)));
			this.waitKey = false;
		} else if (keyCode == Keyboard.KEY_ESCAPE) {
			Utils.getModuleWithInt(this.list.selectedSlot).setBind(-1);
			this.waitKey = false;
		}
		else if (keyCode == Keyboard.KEY_ESCAPE)
			this.mc.displayGuiScreen(this.prevGui);
		super.keyTyped(typedChar, keyCode);
	}

	public void handleMouseInput() throws IOException {
		super.handleMouseInput();
		this.list.handleMouseInput();
	}

	private class GuiList extends GuiSlot {
		private int selectedSlot;

		public GuiList(GuiScreen prevGui) {
			super(Minecraft.getMinecraft(), prevGui.width, prevGui.height, 40, prevGui.height - 56, 30);
		}

		public void handleMouseInput() {
			super.func_178039_p();
		}

		protected boolean isSelected(int id) {
			return this.selectedSlot == id;
		}

		protected int getSelectedSlot() {
			if (this.selectedSlot > GuiBindManager.this.accounts.size()) {
				this.selectedSlot = -1;
			}
			return this.selectedSlot;
		}

		protected int getSize() {
			return Utils.getTotModule();
		}

		protected void elementClicked(int var1, boolean doubleClick, int var3, int var4) {
			this.selectedSlot = var1;
			if (doubleClick) {
				waitKey = true;
			}
		}

		protected void drawBackground() {
		}

		protected void drawSlot(int id, int x, int y, int var4, int var5, int var6) {
			try {
				Module m = Utils.getModuleWithInt(id);
				var.NekoFont.drawString("�c" + m.getName(), x + 31, y + 3, 10526880);
				var.NekoFont.drawString((waitKey && list.isSelected(id)) ? "�cAppuyez sur une touche" : "�9Touche: " + Utils.getBind(m.getName()), x + 31, y + 15, 10526880);
			} catch (Exception e) {
			}
		}
	}
}