package neko.gui;

import java.awt.Desktop;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

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

public class GuiWikiMenu extends GuiScreen {
	private GuiScreen prevGui;
	private Minecraft mc = Minecraft.getMinecraft();
	public static HashMap<String, Vector<HashMap<String, Vector<String>>>> listWiki = new HashMap<String, Vector<HashMap<String, Vector<String>>>>();
	private Client var = Client.getNeko();
	public static boolean check = false;

	public GuiWikiMenu(GuiScreen gui) {
		this.prevGui = gui;
	}

	public void initGui() {
		load();

		this.buttonList.add(new GuiButton(1, 18 + 50, 18, 100, 20, "Retour"));
		int y = 75;
		int x = 75;
		int nb = 2;
		String color = "";
		for (Category c : Category.values()) {
			if (c.name().equalsIgnoreCase("combat"))
				color = "§c";
			else if (c.name().equalsIgnoreCase("movement"))
				color = "§9";
			else if (c.name().equalsIgnoreCase("player"))
				color = "§e";
			else if (c.name().equalsIgnoreCase("render"))
				color = "§3";
			else if (c.name().equalsIgnoreCase("special"))
				color = "§d";
			else if (c.name().equalsIgnoreCase("misc"))
				color = "§5";
			else
				color = "§6";
			Vector<HashMap<String, Vector<String>>> l1 = new Vector<HashMap<String, Vector<String>>>();
			try {
				l1 = GuiWikiMenu.listWiki.get(c.name());
			} catch (Exception e) {
				System.out.println("Salut je crash");
			}
			if (l1 != null)
				for (HashMap<String, Vector<String>> hm : l1) {
					for (Module m : var.moduleManager.ActiveModule) {
						if (hm.containsKey(m.getName())) {
							this.buttonList.add(new GuiButton(nb, x, y, 100, 11, color + m.getName()));
							nb++;
							y += 15;
							if ((y + 11) >= this.height) {
								x += 104;
								y = 75;
							}
							break;
						}
					}
				}
		}
	}

	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		ScaledResolution sr = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);
		drawDefaultBackground();

		drawCenteredString(var.NekoFont, "§eWiki Neko pour les cheats", this.width / 2, 34, 16777215);
		super.drawScreen(mouseX, mouseY, partialTicks);
	}

	protected void actionPerformed(GuiButton button) throws IOException {
		super.actionPerformed(button);
		switch (button.id) {
		case 1:
			mc.displayGuiScreen(this.prevGui);
			break;
		default:
			mc.displayGuiScreen(new GuiWikiPart(this,
					((GuiButton) this.buttonList.get(button.id - 1)).displayString.replaceAll("§([0-9]|[a-f])", "")));
		}
	}

	protected void keyTyped(char typedChar, int keyCode) throws IOException {
		if (keyCode == 1)
			this.mc.displayGuiScreen(this.prevGui);
		super.keyTyped(typedChar, keyCode);
	}

	public void handleMouseInput() throws IOException {
		super.handleMouseInput();
	}

	private void load() {
		this.listWiki = Utils.loadWiki();
		if (this.listWiki == null)
			mc.displayGuiScreen(this.prevGui);
	}

	public class GuiWikiPart extends GuiScreen {
		private GuiScreen prevGui;
		private String cheat;
		private int nextBtn;

		public GuiWikiPart(GuiScreen gui, String cheat) {
			this.prevGui = gui;
			this.cheat = cheat;
		}

		public void initGui() {
			this.buttonList.add(new GuiButton(1, 18 + 50, 18, 100, 20, "Retour"));
		}

		public void drawScreen(int mouseX, int mouseY, float partialTicks) {
			drawDefaultBackground();

			drawCenteredString(var.NekoFont, "§eWiki: " + cheat, this.width / 2, 34, 16777215);

			/**
			 * Affichage de la description du cheat
			 */
			Vector<HashMap<String, Vector<String>>> l1 = GuiWikiMenu.listWiki
					.get(Utils.getModule(cheat).getCategory().name());
			Vector<String> desc = new Vector<String>();
			for (HashMap hm : l1) {
				if (hm.containsKey(cheat)) {
					desc = (Vector<String>) hm.get(cheat);
					break;
				}
			}
			int y = 64;
			boolean cmd = false;
			for (String d : desc) {
				if (!cmd && d.startsWith("#cmd")) {
					cmd = true;
					y += 15;
					drawString(var.NekoFont, "§c§l§nCommandes", 100, y, 16777215);
					y += 15;
					continue;
				}
				if (!cmd)
					drawString(var.NekoFont, "§9" + d, 100, y, 16777215);
				else {
					d = d.startsWith("..") ? d.replace("..", "§6" + var.prefixCmd).replaceFirst(" : ", " :§9 ")
							: "§9" + d;
					drawString(var.NekoFont, d, 100, y, 16777215);
				}
				y += 15;
			}

			super.drawScreen(mouseX, mouseY, partialTicks);
		}

		protected void actionPerformed(GuiButton button) throws IOException {
			super.actionPerformed(button);
			switch (button.id) {
			case 1:
				this.mc.displayGuiScreen(this.prevGui);
				break;
			}
		}

		protected void keyTyped(char typedChar, int keyCode) throws IOException {
			if (keyCode == 1)
				this.mc.displayGuiScreen(this.prevGui);
			super.keyTyped(typedChar, keyCode);
		}

		protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {

			super.mouseClicked(mouseX, mouseY, mouseButton);
		}

		public void onGuiClosed() {
			super.onGuiClosed();
		}
	}
}
