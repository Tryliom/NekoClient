package neko.gui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import neko.Client;
import neko.module.Module;
import neko.utils.Utils;
import net.minecraft.block.Block;
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
	private GuiList list;
	private Client var = Client.getNeko();
	private boolean waitKey = false;
	private GuiTextField search;
	String searchstr;

	public GuiBindManager(GuiScreen gui) {
		this.prevGui = gui;
	}

	public void initGui() {

		Keyboard.enableRepeatEvents(true);
		this.list = new GuiList(this);
		this.list.registerScrollButtons(7, 8);
		this.search = new GuiTextField(2, this.fontRendererObj, (this.width*3 / 4) - 50, 10, 100, 20);
		this.search.setText("");
	}

	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		drawDefaultBackground();
		this.buttonList.clear();
		if(isListHasGoodSize()) {
			this.buttonList.add(new GuiButton(1, this.width / 2 - 50, this.height - 52, 100, 20, "Changer"));
			this.buttonList.add(new GuiButton(2, this.width / 2 - 154, this.height - 52, 100, 20, "Supprimer"));
		}
		this.buttonList.add(new GuiButton(0, this.width / 2 + 4 + 50, this.height - 52, 100, 20, "Retour"));
		this.list.drawScreen(mouseX, mouseY, partialTicks);
		this.search.drawRGBATextBox(-13882323, -14737633);
		drawCenteredString(var.NekoFont, "Bind Manager", this.width / 2, 10, 16777215);
		if (this.search.getText().isEmpty())
			drawCenteredString(var.NekoFont, "Rechercher...", (this.width*3 / 4) -14, 16, 16777215);
		super.drawScreen(mouseX, mouseY, partialTicks);
	}

	protected void actionPerformed(GuiButton button) throws IOException {
		super.actionPerformed(button);
		switch (button.id) {
		case 0:
			mc.displayGuiScreen(this.prevGui);
			break;
		case 1:
			if(!isListHasGoodSize()) return;
			this.waitKey = true;
			break;
		case 2:
			// Supprimer une bind
			if(!isListHasGoodSize()) return;
			Utils.getModuleWithInt(this.list.selectedSlot).setBind(-1);
			break;
		}
	}
	
	public boolean isKeyValid(int keyCode) {
		return (keyCode >= 16 && keyCode <= 50) || (keyCode == Keyboard.KEY_RETURN) || (keyCode == 14) || (keyCode == Keyboard.KEY_SPACE) || (keyCode == 57) || (keyCode >= 71 && keyCode <= 73) || (keyCode >= 75 && keyCode <= 77) || (keyCode == 79) || (keyCode >= 80 && keyCode <= 82) || (keyCode == 91) || (keyCode >= 2 && keyCode <= 11);
	}
	
	public boolean isSearchSuccessful(String text, String b) {
		text = text.toLowerCase();
		String localizedName = b.toLowerCase();
		return text.equalsIgnoreCase("") || localizedName.contains(text);
	}

	protected void keyTyped(char typedChar, int keyCode) throws IOException {
		
		if (this.waitKey && keyCode != Keyboard.KEY_ESCAPE) {
			Utils.getModuleWithInt(this.list.selectedSlot).setBind(Keyboard.getKeyIndex(Keyboard.getKeyName(keyCode)));
			this.waitKey = false;
		} else if (this.waitKey && keyCode == Keyboard.KEY_ESCAPE) {
			Utils.getModuleWithInt(this.list.selectedSlot).setBind(-1);
			this.waitKey = false;
		} else if (!this.waitKey && keyCode != Keyboard.KEY_ESCAPE) {
			if (this.isKeyValid(keyCode)){
				this.search.setFocused(true);
				this.search.textboxKeyTyped(typedChar, keyCode);
				String text = this.search.getText();
				//Here
				ArrayList<String> moduleName = new ArrayList<String>();
				for(String m : this.list.basicList) {
					if(this.isSearchSuccessful(text, m)) {
						moduleName.add(m);
					}
				}
				Collections.sort(moduleName);
				this.list.modulesList.clear();
				for(String ss : moduleName) {
					this.list.modulesList.add(ss);
				}
			}
		} else if (keyCode == Keyboard.KEY_ESCAPE)
			this.mc.displayGuiScreen(this.prevGui);
		super.keyTyped(typedChar, keyCode);
	}
	
	public void onGuiClosed() {
		Keyboard.enableRepeatEvents(false);
		super.onGuiClosed();
	}

	public void handleMouseInput() throws IOException {
		super.handleMouseInput();
		this.list.handleMouseInput();
	}
	
	public boolean isListHasGoodSize() {
		if(this.list.modulesList.size() == 0) {
			return false;
		}
		return true;
	}

	private class GuiList extends GuiSlot {
		private int selectedSlot;
		private ArrayList<String> modulesList = new ArrayList<String>();
		private ArrayList<String> basicList = new ArrayList<String>();

		public GuiList(GuiScreen prevGui) {
			super(Minecraft.getMinecraft(), prevGui.width, prevGui.height, 40, prevGui.height - 56, 30);
			for(int z = 0; z<Utils.getTotModule(); z++) {
				Module m = Utils.getModuleWithInt(z);
				modulesList.add(m.getName());
				basicList.add(m.getName());
			}
			Collections.sort(modulesList);
			Collections.sort(basicList);
		}

		public void handleMouseInput() {
			super.func_178039_p();
		}

		protected boolean isSelected(int id) {
			return this.selectedSlot == id;
		}

		protected int getSelectedSlot() {
			return this.selectedSlot;
		}

		protected int getSize() {
			if(this.modulesList.size() == 0) {
				return 1;
			}
			return this.modulesList.size();
		}

		protected void elementClicked(int i, boolean doubleClick, int var3, int var4) {
			this.selectedSlot = i;
			if (doubleClick) {
				waitKey = true;
			}
		}

		protected void drawBackground() {}

		protected void drawSlot(int id, int x, int y, int var4, int var5, int var6) {
			if(this.modulesList.size() == 0 && id == 0) {
				var.NekoFont.drawString("§cAucun bloc trouvé avec ce nom.", x + 31, y + 3, 10526880);
			}
			try {
				Module module = Utils.getModule(this.modulesList.get(id));

				var.NekoFont.drawString("§c" + module.getName(), x + 31, y + 3, 10526880);
				var.NekoFont.drawString((waitKey && list.isSelected(id)) ? "§cAppuyez sur une touche" : "§9Touche: " + Utils.getBind(module.getName()),  x + 31, y + 15, 10526880);		
			} catch(Exception e) {}		
			GL11.glDisable(3042);
		}
	}
}


