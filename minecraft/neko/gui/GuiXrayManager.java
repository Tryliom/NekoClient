package neko.gui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Vector;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import neko.Client;
import neko.module.modules.render.Xray;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBeacon;
import net.minecraft.block.BlockFarmland;
import net.minecraft.block.BlockFurnace;
import net.minecraft.block.BlockMobSpawner;
import net.minecraft.block.BlockOre;
import net.minecraft.block.BlockStaticLiquid;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiSlot;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.resources.model.ModelManager;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;

public class GuiXrayManager extends GuiScreen {
	private GuiScreen prevGui;
	private Minecraft mc = Minecraft.getMinecraft();
	private GuiList guiList;
	private Client var = Client.getNeko();
	private GuiTextField search;
	String searchstr;

	public GuiXrayManager(GuiScreen gui) {
		this.prevGui = gui;
	}

	public void initGui() {

		Keyboard.enableRepeatEvents(true);
		this.guiList = new GuiList(this);
		this.guiList.registerScrollButtons(7, 8);
		this.search = new GuiTextField(2, this.fontRendererObj, (this.width*3 / 4) - 50, 10, 100, 20);
		this.search.setText("");
	}

	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		drawDefaultBackground();
		this.buttonList.clear();
		this.buttonList.add(new GuiButton(0, this.width*3 / 4, this.height - 52, 100, 20, "Retour"));
		this.buttonList.add(new GuiButton(1, this.width / 2 - 50, this.height - 52, 100, 20, "Toggle"));
		this.guiList.drawScreen(mouseX, mouseY, partialTicks);
		this.search.drawRGBATextBox(-13882323, -14737633);
		drawCenteredString(var.NekoFont, "Xray Manager", this.width / 2, 10, 16777215);
		if (this.search.getText().isEmpty())
			drawCenteredString(var.NekoFont, "Rechercher...", (this.width*3 / 4) -14, 16, 16777215);
		super.drawScreen(mouseX, mouseY, partialTicks);
	}
	
	public void performSearchBlocs() {
		String text = this.search.getText();
		ArrayList<Listblockks> block = new ArrayList<Listblockks>();
		
		for(Block b : this.guiList.list) {
			if(text.equalsIgnoreCase("") || text.equalsIgnoreCase("")) {
				this.guiList.list.clear();
				this.guiList.list.addAll(this.guiList.basicList);
				continue;
			} else if(b.getLocalizedName().contains(text)) {
				block.add(new Listblockks(b));
				continue;
			} else if(b.getUnlocalizedName().contains(text)) {
				block.add(new Listblockks(b));
				continue;
			} else if(String.valueOf(Block.getIdFromBlock(b)).contains(text)){
				block.add(new Listblockks(b));
				continue;
			}
		}
		Collections.sort(block, new SortByBlockType());
		this.guiList.list.clear();
		for(Listblockks bb : block) {
			this.guiList.list.add(bb.block);
		}
	}

	protected void actionPerformed(GuiButton button) throws IOException {
		super.actionPerformed(button);
		switch (button.id) {
		case 0:
			mc.displayGuiScreen(this.prevGui);
			break;
		case 1:
			Integer i = this.guiList.getSelectedSlot();
			Xray xray = Xray.getXray();
			Vector<Integer> list = xray.getList();
			Block block = this.guiList.list.get(i);
			Integer id = Block.getIdFromBlock(block);
			if (list.contains(id)) {
				list.removeElement(id);
			} else {
				list.add(id);
			}
			break;
		case 2:
			ArrayList<Block> abcd = new ArrayList<Block>();
			for(int x = 0; x<this.guiList.list.size(); x++) {
				String s = this.guiList.list.get(x).getLocalizedName();
				if(s.toLowerCase().startsWith("min")) {
					abcd.add(this.guiList.list.get(x));
				}
			}
			this.guiList.list.removeAllElements();
			for(Block b : abcd) {
				this.guiList.list.add(b);
			}
			break;
		}
	}
	
	public boolean isKeyValid(int keyCode) {
		return (keyCode >= 16 && keyCode <= 50) || (keyCode == Keyboard.KEY_RETURN) || (keyCode == 14) || (keyCode == Keyboard.KEY_SPACE) || (keyCode == 57) || (keyCode >= 71 && keyCode <= 73) || (keyCode >= 75 && keyCode <= 77) || (keyCode == 79) || (keyCode >= 80 && keyCode <= 82) || (keyCode == 91) || (keyCode >= 2 && keyCode <= 11);
	}
	
	public boolean isSearchSuccessful(String text, Block b) {
		text = text.toLowerCase();
		String localizedName = b.getLocalizedName().toLowerCase();
		String unlocalizedName = b.getUnlocalizedName().toLowerCase();
		String blockID = String.valueOf(Block.getIdFromBlock(b));
		return text.equalsIgnoreCase("") || localizedName.contains(text) || unlocalizedName.contains(text) || text.contains(blockID);
	}

	protected void keyTyped(char typedChar, int keyCode) throws IOException {
		
		if (keyCode == Keyboard.KEY_ESCAPE)
			this.mc.displayGuiScreen(this.prevGui);
		
		if (this.isKeyValid(keyCode)) {
			this.search.setFocused(true);
			this.search.textboxKeyTyped(typedChar, keyCode);
			ArrayList<Listblockks> block = new ArrayList<Listblockks>();
			String text = this.search.getText();
			
			for (Block b : this.guiList.basicList) {
				if (this.isSearchSuccessful(text, b)) {
					block.add(new Listblockks(b));
				}
			}
			
			Collections.sort(block, new SortByBlockType());
			this.guiList.list.clear();
			for (Listblockks bb : block) {
				this.guiList.list.add(bb.block);
			}	
		}
			
		super.keyTyped(typedChar, keyCode);
	}
	
	public void onGuiClosed() {
		Keyboard.enableRepeatEvents(false);
		super.onGuiClosed();
	}
	
	protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
		
		this.search.mouseClicked(mouseX, mouseY, mouseButton);
			
		super.mouseClicked(mouseX, mouseY, mouseButton);
	}

	public void handleMouseInput() throws IOException {
		super.handleMouseInput();
		this.guiList.handleMouseInput();
	}

	private class GuiList extends GuiSlot {
		private int selectedSlot;
		private Vector<Block> list = new Vector<Block>();
		private Vector<Block> basicList = new Vector<Block>();
		
		ArrayList<Listblockks> lb = new ArrayList<Listblockks>();

		public GuiList(GuiScreen prevGui) {
			super(Minecraft.getMinecraft(), prevGui.width, prevGui.height, 40, prevGui.height - 56, 30);
			for (Object o : Block.blockRegistry.getKeys()) {
				Block block = (Block) Block.blockRegistry.getObject(o);
				if (!block.getLocalizedName().startsWith("tile.") && !(block instanceof BlockFarmland))
					this.lb.add(new Listblockks(block));
			}
			Collections.sort(this.lb, new SortByBlockType());
			
			for(int i = 0; i<this.lb.size(); i++) {
				ItemStack ia = new ItemStack(this.lb.get(i).block);
				Boolean CanEnchanted = false;
				try {
					ia.hasEffect();
					CanEnchanted = true;
				} catch (Exception e){
					CanEnchanted = false;
				}
				
				if(!CanEnchanted) {
					continue;
				}
				this.list.add(this.lb.get(i).block);
				this.basicList.add(this.lb.get(i).block);
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
			Block block = this.list.get(i);
			boolean selected = Xray.getXray().getList().contains(Block.getIdFromBlock(block));

			ItemStack ia = new ItemStack(block);
			
			var.NekoFont.drawString((selected ? "§a" : "§c") + block.getLocalizedName(), x + 31, y + 9, 10526880);
			RenderHelper.enableGUIStandardItemLighting();
			mc.getRenderItem().renderItemIntoGUI(ia, x + 5, y + 5);
			GL11.glDisable(3042);
		}
	}
}

class Listblockks {
	
	Block block;
	String BlockType = "";
	int id = 0;
	
	public Listblockks(Block b) {
		this.block = b;
		this.id = Block.getIdFromBlock(block);
		if(block instanceof BlockOre) {
			this.BlockType = "aa";
		} else if(block instanceof BlockMobSpawner) {
			this.BlockType = "bb";
		} else if(block instanceof BlockBeacon) {
			this.BlockType = "cc";
		} else if(block.getLocalizedName().startsWith("tile.")) {
			this.BlockType = "dd";
		} else {
			this.BlockType = "ee";
		}
	}
	
	public String BlockType() {
		return this.BlockType;
	}
	
}

class SortByBlockType implements Comparator<Listblockks> {
	
	public int compare(Listblockks a, Listblockks b) {
		if(a.BlockType.equalsIgnoreCase(b.BlockType)) {
			return a.id - b.id;
		}
		return a.BlockType.compareTo(b.BlockType);
	}
	
}
