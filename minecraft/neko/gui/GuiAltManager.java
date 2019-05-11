package neko.gui;

import java.awt.Desktop;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.lwjgl.input.Keyboard;

import neko.Client;
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

public class GuiAltManager extends GuiScreen {
	private GuiScreen prevGui;
	private Minecraft mc = Minecraft.getMinecraft();
	private ResourceLocation background = mc.getTextureManager().getDynamicTextureLocation("background",
			GuiMainMenu.viewportTexture);
	public static ArrayList<Account> accounts = new ArrayList<Account>();
	public static ArrayList<String> listAcc = new ArrayList<String>();
	private GuiList list;
	private static String displaytext = "";
	private Client var = Client.getNeko();
	private Thread currentThread;
	private int lastIndex = -1;
	public static boolean check = false;

	public GuiAltManager(GuiScreen gui) {
		this.prevGui = gui;
	}

	public void initGui() {
		this.load();
		this.list = new GuiList(this);
		this.list.registerScrollButtons(7, 8);
		this.list.elementClicked(Utils.lastAccount, false, 0, 0);

		int j = 28;
		this.buttonList.add(new GuiButton(1, this.width / 2 + 4 + 50, this.height - 52, 100, 20, "Ajouter"));
		this.buttonList.add(new GuiButton(2, this.width / 2 - 50, this.height - 28, 100, 20, "Supprimer"));
		this.buttonList.add(new GuiButton(3, this.width / 2 - 154, this.height - 52, 100, 20, "Login"));
		this.buttonList.add(new GuiButton(4, this.width / 2 - 50, this.height - 52, 100, 20, "McLeaks"));
		this.buttonList.add(new GuiButton(5, this.width / 2 - 154, this.height - 28, 100, 20, "Copier la liste d'alt"));
		this.buttonList.add(new GuiButton(0, this.width / 2 + 4 + 50, this.height - 28, 100, 20, "Retour"));
		this.buttonList.add(new GuiButton(6, this.width - 150, 10, 100, 20, (check ? "§a" : "§c") + "Checker"));
	}

	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		ScaledResolution sr = new ScaledResolution(mc);
		this.mc.getTextureManager().bindTexture(this.background);
		Gui.drawScaledCustomSizeModalRect(0, 0, 0.0F, 0.0F, sr.getScaledWidth(), sr.getScaledHeight(),
				sr.getScaledWidth(), sr.getScaledHeight(), sr.getScaledWidth(), sr.getScaledHeight());

		drawDefaultBackground();
		this.list.drawScreen(mouseX, mouseY, partialTicks);

		drawCenteredString(var.NekoFont, "Alt Manager", this.width / 2, 10, 16777215);
		drawCenteredString(var.NekoFont, "§a" + this.displaytext, this.width / 2, 25, 16777215);
		drawString(var.NekoFont, "§7Pseudo: " + this.mc.session.getUsername(), 10, 10, 16777215);
		String var10 = var.strNeko;

		this.drawString(var.NekoFont, var10, 2, this.height - 10, -1);
		String var11 = var.strCreator;
		this.drawString(var.NekoFont, var11, this.width - var.NekoFont.getStringWidth(var11) - 2, this.height - 10, -1);
		((GuiButton) this.buttonList.get(6)).displayString = (check ? "§a" : "§c") + "Checker";
		super.drawScreen(mouseX, mouseY, partialTicks);
	}

	protected void actionPerformed(GuiButton button) throws IOException {
		super.actionPerformed(button);
		switch (button.id) {
		case 0:
			if (currentThread != null ? currentThread.isAlive() : false) {
				currentThread.interrupt();
				currentThread.stop();
			}
			this.mc.displayGuiScreen(this.prevGui);
			break;
		case 1:
			if (currentThread != null ? currentThread.isAlive() : false) {
				currentThread.interrupt();
				currentThread.stop();
			}
			this.mc.displayGuiScreen(new GuiAdd(this));
			break;
		case 2:
			if (this.list.getSelectedSlot() != -1) {
				try {
					Utils.deleteAccount(this.list.getSelectedSlot() + 1);
					this.accounts.remove(this.list.getSelectedSlot());
					this.listAcc.remove(this.list.getSelectedSlot());
					this.displaytext = "Le compte a été supprimé !";
				} catch (Exception e) {
					this.displaytext = ("Erreur\n" + e.getMessage());
					e.printStackTrace();
				}
			} else {
				this.displaytext = "§cChoisissez un compte avant !";
			}
			break;
		case 3:
			if (((this.list.getSelectedSlot() != -1 ? 1 : 0)
					& (this.list.getSelectedSlot() < this.list.getSize() ? 1 : 0)) != 0) {
				this.displaytext = ((Account) this.accounts.get(this.list.getSelectedSlot())).login();
				Utils.lastAccount = this.list.getSelectedSlot();
			} else {
				this.displaytext = "§cSélectionnez un compte avant !";
			}
			break;
		case 4:
			this.mc.displayGuiScreen(new GuiMcleaks(this));
			break;
		case 5:
			String tot = "";		
			if (this.list.selectedSlot==-1 || this.listAcc.size()>=this.list.selectedSlot)
				for (String s : this.listAcc) {
					tot+=s+"\n";
				}
			else
				tot=this.listAcc.get(this.list.selectedSlot);
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(tot), null);
			break;
		case 6:
			check = !check;
			load();
			break;
		}
	}

	protected void keyTyped(char typedChar, int keyCode) throws IOException {
		if (keyCode == 1)
			this.mc.displayGuiScreen(this.prevGui);
		super.keyTyped(typedChar, keyCode);
	}

	public void handleMouseInput() throws IOException {
		super.handleMouseInput();
		this.list.handleMouseInput();
	}

	private void load() {
		this.accounts.clear();
		try {
			for (Object o : Utils.getAllAccount()) {
				String[] s = ((String) o).split(" ");
				String name = s[0];
				String pass = "";
				for (int i = 1; i < s.length; i++) {
					if (i + 1 == s.length) {
						pass += s[i];
					} else {
						pass += s[i] + " ";
					}
				}
				Account alt = null;
				alt = new Account(name, pass);
				this.accounts.add(alt);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if ((currentThread == null || !currentThread.isAlive()) && check) {
			currentThread = new Thread(new Runnable() {

				@Override
				public void run() {
					boolean isFinish = false;
					while (!isFinish)
						try {
							for (Account a : GuiAltManager.accounts) {
								if (a.getUsername() == null) {
									a.setUsername(Utils.getUsername(a.getEmail(), a.getPass()));
									try {
										Thread.sleep(1000);
									} catch (InterruptedException e) {
										break;
									}
								}
							}
							isFinish = true;
						} catch (Exception e) {
						}

				}
			});
			currentThread.start();
		} else if (!check && currentThread != null) {
			currentThread.interrupt();
			currentThread.stop();
		}
		Utils.saveCloudAlt();
	}

	private class GuiMcleaks extends GuiScreen {
		private GuiScreen prevGui;
		private GuiTextField token;
		private String error = "";

		public GuiMcleaks(GuiScreen gui) {
			this.prevGui = gui;
		}

		public void initGui() {
			Keyboard.enableRepeatEvents(true);
			this.buttonList.add(new GuiButton(1, this.width / 2 - 100, this.height / 4 + 96 + 12, "Login"));
			this.buttonList.add(new GuiButton(0, this.width / 2 - 100, this.height / 4 + 120 + 12, "Retour"));
			this.token = new GuiTextField(2, this.fontRendererObj, this.width / 2 - 100, 60, 200, 20);
			this.token.setFocused(true);
		}

		public void drawScreen(int mouseX, int mouseY, float partialTicks) {
			drawDefaultBackground();
			Gui.drawRect(30, 30, this.width - 30, this.height - 30, -2147483648);

			drawCenteredString(var.NekoFont, "Alt Manager", this.width / 2, 34, 16777215);
			drawCenteredString(var.NekoFont, "§c" + this.error, this.width / 2, 140, 16777215);

			this.token.drawTextBox();
			drawCenteredString(var.NekoFont, "§7Entrez une token de connexion", this.width / 2, 100, 16777215);
			super.drawScreen(mouseX, mouseY, partialTicks);
		}

		protected void actionPerformed(GuiButton button) throws IOException {
			super.actionPerformed(button);
			switch (button.id) {
			case 0:
				this.mc.displayGuiScreen(this.prevGui);
				break;
			case 1:
				try {
					String token = this.token.getText();
					if (token.isEmpty()) {
						error = "§cVeuillez entrer une token !";
						return;
					} else
						ModApi.redeem(token, new Callback<Object>() {

							@Override
							public void done(Object o) {
								if (o instanceof String) {
									displaytext = "§cErreur: " + o;
									return;
								}
								if (MCLeaks.savedSession == null) {
									MCLeaks.savedSession = Minecraft.getMinecraft().getSession();
								}
								RedeemResponse response = (RedeemResponse) o;
								MCLeaks.refresh(response.getSession(), response.getMcName());
								displaytext = "§aConnecté en tant que " + MCLeaks.getMCName() + "!";
								if (!Friends.isFriend(MCLeaks.getMCName()))
									Friends.friend.add(MCLeaks.getMCName());
								Utils.lastAccount = 0;
							}
						});
					this.mc.displayGuiScreen(this.prevGui);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		protected void keyTyped(char typedChar, int keyCode) throws IOException {
			if (this.token.isFocused()) {
				this.token.textboxKeyTyped(typedChar, keyCode);
			}
			super.keyTyped(typedChar, keyCode);
		}

		protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
			this.token.mouseClicked(mouseX, mouseY, mouseButton);

			super.mouseClicked(mouseX, mouseY, mouseButton);
		}

		public void onGuiClosed() {
			Keyboard.enableRepeatEvents(false);
			super.onGuiClosed();
		}
	}

	private class GuiAdd extends GuiScreen {
		private GuiScreen prevGui;
		private GuiTextField name_mail;
		private String error = "";

		public GuiAdd(GuiScreen gui) {
			this.prevGui = gui;
		}

		public void initGui() {
			Keyboard.enableRepeatEvents(true);
			this.buttonList.add(new GuiButton(1, this.width / 2 - 100, this.height / 4 + 96 + 12, "Ajouter"));
			this.buttonList.add(new GuiButton(0, this.width / 2 - 100, this.height / 4 + 120 + 12, "Retour"));
			this.name_mail = new GuiTextField(2, this.fontRendererObj, this.width / 2 - 150, 85, 300, 20);
			this.name_mail.setFocused(true);
		}

		public void drawScreen(int mouseX, int mouseY, float partialTicks) {
			drawDefaultBackground();
			Gui.drawRect(30, 30, this.width - 30, this.height - 30, -2147483648);

			drawCenteredString(var.NekoFont, "Alt Manager", this.width / 2, 35, 16777215);
			drawCenteredString(var.NekoFont, "§c" + this.error, this.width / 2, 47, 16777215);
			drawCenteredString(var.NekoFont, "§7Email:Mot de passe (Mettre une virgule pour séparer les comptes)",
					this.width / 2, 67, 16777215);
			this.name_mail.drawTextBox();
			super.drawScreen(mouseX, mouseY, partialTicks);
		}

		protected void actionPerformed(GuiButton button) throws IOException {
			super.actionPerformed(button);
			switch (button.id) {
			case 0:
				this.mc.displayGuiScreen(this.prevGui);
				break;
			case 1:
				try {
					String ms[] = this.name_mail.getText().split(",");
					String fname = "";
					for (String nm : ms) {
						String s[] = nm.split(":");
						String name = s[0];
						String pass = "";
						if (s.length > 1)
							pass = s[1];
						String accountasstring = s[0] + " " + pass;
						boolean isList = false;
						for (Account account : GuiAltManager.this.accounts) {
							if (account.getEmail().equalsIgnoreCase(name)) {
								isList = true;
							}
						}
						if (isList) {
							this.error = "Le compte a déjà été ajouté...";
							return;
						}
						this.error = "";
						Utils.saveAccount(name, pass);
					}
					if (ms.length>1)
						displaytext = "Les comptes ont été ajouté !";
					else
						displaytext = "Le compte " + fname + " a été ajouté !";
					this.mc.displayGuiScreen(this.prevGui);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		protected void keyTyped(char typedChar, int keyCode) throws IOException {
			if (this.name_mail.isFocused()) {
				this.name_mail.textboxKeyTyped(typedChar, keyCode);
				try {
					boolean isList = false;
					for (Account account : GuiAltManager.this.accounts) {
						if (account.getEmail().equalsIgnoreCase(this.name_mail.getText())) {
							isList = true;
						}
					}
					if (isList) {
						this.error = "Le compte a déjà été ajouté...";
					} else {
						this.error = "";
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			super.keyTyped(typedChar, keyCode);
		}

		protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
			this.name_mail.mouseClicked(mouseX, mouseY, mouseButton);

			super.mouseClicked(mouseX, mouseY, mouseButton);
		}

		public void onGuiClosed() {
			Keyboard.enableRepeatEvents(false);
			super.onGuiClosed();
		}
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
			if (this.selectedSlot > GuiAltManager.this.accounts.size()) {
				this.selectedSlot = -1;
			}
			return this.selectedSlot;
		}

		protected int getSize() {
			return GuiAltManager.this.accounts.size();
		}

		protected void elementClicked(int var1, boolean doubleClick, int var3, int var4) {
			this.selectedSlot = var1;
			if (doubleClick) {
				displaytext = ((Account) accounts.get(list.getSelectedSlot())).login();
				Utils.lastAccount = list.getSelectedSlot() + 1;
			}
		}

		protected void drawBackground() {
		}

		protected void drawSlot(int id, int x, int y, int var4, int var5, int var6) {
			try {
				Account alt = accounts.get(id);
				if (alt.getUsername() != null)
					RenderUtils.drawFace(alt.getUsername(), x + 1, y + 1, 24, 24, true);
				var.NekoFont
						.drawString("Email: " + alt.getEmail()
								+ ((alt.getUsername() == null || alt.getUsername().isEmpty())
										? (Utils.lastAccount > 0 && Utils.lastAccount - 1 == id && alt.isValid())
												? " (" + mc.session.getUsername() + ")"
												: ""
										: " (" + alt.getUsername() + ")"),
								x + 31, y + 3, 10526880);
				var.NekoFont
						.drawString(
								alt.isCracked() ? "§cCracké"
										: "§aPremium" + ((Utils.lastAccount > 0 && Utils.lastAccount - 1 == id
												&& alt.isValid()) ? " §7[§aSélectionné§7]" : ""),
								x + 31, y + 15, 10526880);
			} catch (Exception e) {
			}
		}
	}
}
