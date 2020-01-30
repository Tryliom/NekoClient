package neko.gui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Vector;

import org.lwjgl.input.Keyboard;

import neko.Client;
import neko.module.modules.render.Xray;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBeacon;
import net.minecraft.block.BlockMobSpawner;
import net.minecraft.block.BlockOre;
import net.minecraft.block.BlockStaticLiquid;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiSlot;
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

	public GuiXrayManager(GuiScreen gui) {
		this.prevGui = gui;
	}

	public void initGui() {

		this.guiList = new GuiList(this);
		this.guiList.registerScrollButtons(7, 8);
	}

	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		drawDefaultBackground();
		this.buttonList.clear();
		this.buttonList.add(new GuiButton(0, this.width / 2 + 4 + 50, this.height - 52, 100, 20, "Retour"));
		this.buttonList.add(new GuiButton(1, this.width / 4 + 4 + 50, this.height - 52, 100, 20, "Toggle"));
		this.guiList.drawScreen(mouseX, mouseY, partialTicks);
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
		}
	}

	protected void keyTyped(char typedChar, int keyCode) throws IOException {
		
		if (keyCode == Keyboard.KEY_ESCAPE)
			this.mc.displayGuiScreen(this.prevGui);
		super.keyTyped(typedChar, keyCode);
	}

	public void handleMouseInput() throws IOException {
		super.handleMouseInput();
		this.guiList.handleMouseInput();
	}

	private class GuiList extends GuiSlot {
		private int selectedSlot;
		private Vector<Block> list = new Vector<Block>();
		ArrayList<Listblockks> lb = new ArrayList<Listblockks>();

		public GuiList(GuiScreen prevGui) {
			super(Minecraft.getMinecraft(), prevGui.width, prevGui.height, 40, prevGui.height - 56, 30);
			for (Object o : Block.blockRegistry.getKeys()) {
				Block block = (Block) Block.blockRegistry.getObject(o);
				if (!block.getLocalizedName().startsWith("tile.") && (block instanceof Block) || (block instanceof BlockOre || block instanceof BlockMobSpawner || block instanceof BlockBeacon))
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

		int realslot = 0;
		
		protected void drawSlot(int i, int x, int y, int var4, int var5, int var6) {
			try {
				Block block = this.list.get(i);
				boolean selected = Xray.getXray().getList().contains(Block.getIdFromBlock(block));

				ItemStack ia = new ItemStack(block);
				
				var.NekoFont.drawString((selected ? "§a" : "§c") + block.getLocalizedName(), x + 31, y + 3, 10526880);

				RenderHelper.enableGUIStandardItemLighting();
				mc.getRenderItem().renderItemIntoGUI(ia, x, y + 3);
			} catch (Exception e) {
				//System.out.println(e.getLocalizedMessage());
			}
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
			//System.out.println("BlockOre : " + block.getLocalizedName());
			this.BlockType = "aa";
		} else if(block instanceof BlockMobSpawner) {
			//System.out.println("BlockMobSpawner : " + block.getLocalizedName());
			this.BlockType = "bb";
		} else if(block instanceof BlockBeacon) {
			//System.out.println("BlockBeacon : " + block.getLocalizedName());
			this.BlockType = "cc";
		} else if(block.getLocalizedName().startsWith("tile.")) {
			//System.out.println("BlockTile : " + block.getLocalizedName());
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
