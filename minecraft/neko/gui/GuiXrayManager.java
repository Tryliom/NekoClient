package neko.gui;

import java.io.IOException;
import java.util.Vector;

import org.lwjgl.input.Keyboard;

import neko.Client;
import neko.module.modules.movements.AirWalk;
import neko.module.modules.render.Xray;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialLiquid;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiSlot;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.ResourceLocation;

public class GuiXrayManager extends GuiScreen {
	private GuiScreen prevGui;
	private Minecraft mc = Minecraft.getMinecraft();
	private ResourceLocation background = new ResourceLocation("textures/gui/GuiAccount/background.png");
	private GuiList list;
	private Client var = Client.getNeko();
	private int lastIndex = -1;

	public GuiXrayManager(GuiScreen gui) {
		this.prevGui = gui;
	}

	public void initGui() {

		this.list = new GuiList(this);
		this.list.registerScrollButtons(7, 8);
	}

	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		
		ScaledResolution sr = new ScaledResolution(mc);
		this.mc.getTextureManager().bindTexture(this.background);
		
		Gui.drawScaledCustomSizeModalRect(0, 0, 0.0F, 0.0F, sr.getScaledWidth(), sr.getScaledHeight(),
				sr.getScaledWidth(), sr.getScaledHeight(), sr.getScaledWidth(), sr.getScaledHeight());
		
		this.buttonList.clear();
		this.buttonList.add(new GuiButton(0, this.width / 2 + 4 + 50, this.height - 52, 100, 20, "Retour"));
		this.list.drawScreen(mouseX, mouseY, partialTicks);
		drawCenteredString(var.NekoFont, "Xray Manager", this.width / 2, 10, 16777215);
		super.drawScreen(mouseX, mouseY, partialTicks);
	}

	protected void actionPerformed(GuiButton button) throws IOException {
		super.actionPerformed(button);
		switch (button.id) {
		case 0:
			mc.displayGuiScreen(this.prevGui);
			break;
		case 1:
			
			break;
		case 2:

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
		private Vector<Block> list = new Vector<Block>();

		public GuiList(GuiScreen prevGui) {
			super(Minecraft.getMinecraft(), prevGui.width, prevGui.height, 40, prevGui.height - 56, 30);
			for (Object o : Block.blockRegistry.getKeys()) {
				Block block = (Block) Block.blockRegistry.getObject(o);
				if (!block.getLocalizedName().startsWith("tile."))
					this.list.add(block);
			}
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
			return this.list.size();
		}

		protected void elementClicked(int i, boolean doubleClick, int var3, int var4) {
			this.selectedSlot = i;
			Xray xray = Xray.getXray();
			Vector<Integer> list = xray.getList();
			Block block = this.list.get(i);
			Integer id = Block.getIdFromBlock(block);
			if (doubleClick) {
				if (list.contains(id)) {
					list.removeElement(id);
				} else {
					list.add(id);
				}
			}
		}

		protected void drawBackground() {}

		protected void drawSlot(int i, int x, int y, int var4, int var5, int var6) {
			try {
				Block block = this.list.get(i);
				boolean selected = Xray.getXray().getList().contains(Block.getIdFromBlock(block));
				var.NekoFont.drawString((selected ? "§a" : "§c") + block.getLocalizedName(), x + 31, y + 3, 10526880);
				
			} catch (Exception e) {
			}
		}
	}
}
