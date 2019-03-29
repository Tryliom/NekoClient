package neko.gui;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;

import org.lwjgl.input.Keyboard;

import neko.Client;
import neko.dtb.RequestThread;
import neko.manager.SoundManager;
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

public class GuiMusicManager extends GuiScreen {
	private GuiScreen prevGui;
	private Minecraft mc = Minecraft.getMinecraft();
	private ResourceLocation background = new ResourceLocation("textures/gui/GuiAccount/background.png");
	private GuiList list;
	private Client var = Client.getNeko();
	private int lastIndex = -1;

	public GuiMusicManager(GuiScreen gui) {
		this.prevGui = gui;
		SoundManager.getSM().searchMusicList();
	}

	public void initGui() {

		this.list = new GuiList(this);
		this.list.registerScrollButtons(7, 8);
		this.list.selectedSlot = Utils.getIdMusicByPath(SoundManager.getSM().currPath);
	}

	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		
		ScaledResolution sr = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);
		this.mc.getTextureManager().bindTexture(this.background);
		
		Gui.drawScaledCustomSizeModalRect(0, 0, 0.0F, 0.0F, sr.getScaledWidth(), sr.getScaledHeight(),
				sr.getScaledWidth(), sr.getScaledHeight(), sr.getScaledWidth(), sr.getScaledHeight());
		
		this.buttonList.clear();
		if (SoundManager.getSM().canStart) {
			if (this.list.selectedSlot!=-1)
				this.buttonList.add(new GuiButton(1, this.width / 2 - 50, this.height - 52, 100, 20, "§aJouer"));
			this.buttonList.add(new GuiButton(2, this.width / 2 - 154, this.height - 52, 100, 20, "Mode: §a"+SoundManager.getSM().mm.name()));
		}
		this.buttonList.add(new GuiButton(0, this.width / 2 + 4 + 50, this.height - 52, 100, 20, "Retour"));
		if (SoundManager.getSM().canStart || Utils.haveInternet())
			this.buttonList.add(new GuiButton(665, this.width-110, 10, 100, 20, SoundManager.getSM().isActive() ? "♫ Stop ♫" : "♪ Start Music ♪"));
		else
			this.buttonList.add(new GuiButton(665, this.width-110, 10, 100, 20, "Music loading..."));
		//drawDefaultBackground();
		this.list.drawScreen(mouseX, mouseY, partialTicks);
		drawCenteredString(var.NekoFont, "Music Manager", this.width / 2, 10, 16777215);
		super.drawScreen(mouseX, mouseY, partialTicks);
	}

	protected void actionPerformed(GuiButton button) throws IOException {
		super.actionPerformed(button);
		switch (button.id) {
		case 0:
			mc.displayGuiScreen(this.prevGui);
			break;
		case 1:
			if (this.list.selectedSlot>=0) {
				SoundManager.getSM().currPath = SoundManager.getSM().getList().get(this.list.selectedSlot).getPath();
				SoundManager.getSM().restartMusic(" ");
			}
			break;
		case 2:
			// MusicMode à changer
			SoundManager.getSM().changeMode.accept(SoundManager.getSM().mm);
			break;
		case 665:
			if ((button.displayString.equals("♪ Start Music ♪")) && !button.displayString.equals("Music loading...")) {
    			if(SoundManager.getSM().getList().size() != 0) {
    				SoundManager.getSM().currPath = SoundManager.getSM().getList().get(0).getPath();
    				SoundManager.getSM().startNewMusic();

        			button.displayString =  "♫ Stop ♫";
        			return;
    			}
    		}
    		if (SoundManager.getSM().isActive() && !button.displayString.equals("Music loading..."))
    			SoundManager.getSM().stopMusic();
    		else if (!button.displayString.equals("Music loading...")) {
    			SoundManager.getSM().restartMusic();
    		} else
    			button.displayString = SoundManager.getSM().isActive() ? "♫ Stop ♫" : "♪ Restart ♪";
    		mc.displayGuiScreen(this);
    		break;
		}
	}

	protected void keyTyped(char typedChar, int keyCode) throws IOException {
		
		if (keyCode == Keyboard.KEY_ESCAPE)
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
			return this.selectedSlot;
		}

		protected int getSize() {
			return SoundManager.getSM().getList().size();
		}

		protected void elementClicked(int var1, boolean doubleClick, int var3, int var4) {
			this.selectedSlot = var1;
			if (doubleClick && SoundManager.getSM().canStart) {
				SoundManager.getSM().currPath = SoundManager.getSM().getList().get(list.selectedSlot).getPath();
				SoundManager.getSM().restartMusic("");
			}
		}

		protected void drawBackground() {
		}

		protected void drawSlot(int id, int x, int y, int var4, int var5, int var6) {
			try {
				var.NekoFont.drawString("§c" + SoundManager.getSM().getList().get(id).getName(), x + 31, y + 3, 10526880);
			} catch (Exception e) {
			}
		}
	}
}
