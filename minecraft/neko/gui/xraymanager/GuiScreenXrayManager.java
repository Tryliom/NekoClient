package neko.gui.xraymanager;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.lwjgl.input.Keyboard;

import com.google.common.collect.Lists;

import neko.Client;
import neko.module.modules.render.Xray;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBeacon;
import net.minecraft.block.BlockFarmland;
import net.minecraft.block.BlockMobSpawner;
import net.minecraft.block.BlockOre;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.item.ItemStack;
import net.minecraft.util.RegistryNamespacedDefaultedByKey;
import net.minecraft.util.ResourceLocation;

public class GuiScreenXrayManager extends GuiScreen {

    private GuiScreen prevGui;
    private List availableXrayBlocs;
    private List selectedXrayBlocs;
    private GuiXrayManagerAvailable availableXrayBlocsList;
    private GuiXrayManagerSelected selectedXrayBlocsList;
    private List<Block> allXrayBlocs;
    private boolean changed = false;
    private Vector<Block> list = new Vector<Block>();
    private Vector<Block> basiclist = new Vector<Block>();
    private ArrayList<Listblockks> lb = new ArrayList<Listblockks>();
    private ArrayList var2;
    
	Vector<Integer> listcontain;

	private Minecraft mc = Minecraft.getMinecraft();
	private Client var = Client.getNeko();
	private GuiTextField search;
	String searchstr;
    
    public GuiScreenXrayManager(GuiScreen screen) {
    	this.prevGui = screen;
    }
    
    public void initGui() {
    	
    	this.availableXrayBlocs = Lists.newArrayList();
    	this.selectedXrayBlocs = Lists.newArrayList();
    	
    	Xray xray = Xray.getXray();
        listcontain = xray.getList();
    	ArrayList var2 = Lists.newArrayList(Block.blockRegistry.getKeys());
    	Iterator var3 = var2.iterator();
    	Block var4;
    	while(var3.hasNext()) {
    		var4 = (Block)Block.blockRegistry.getObject(var3.next());
    		this.lb.add(new Listblockks(var4));
    	}
    	Collections.sort(this.lb, new SortByBlockType());
    	var2.clear();
    	var2 = Lists.newArrayList();
    	for(Listblockks lbb : this.lb) {
    		var2.add(lbb.block);
		}
    	this.lb.clear();
    	var3 = var2.iterator();
    	while (var3.hasNext()) {
			var4 = (Block) var3.next();
			if (!var4.getLocalizedName().startsWith("tile.") && !(var4 instanceof BlockFarmland)) {
				if(!listcontain.contains(Block.getIdFromBlock(var4))) {
					if(!this.hasXrayAvailableEntry(new XRayListEntryFound(this, var4))) {
						this.availableXrayBlocs.add(new XRayListEntryFound(this, var4));
					}
				} else {
					if(!this.selectedXrayBlocs.contains(new XRayListEntryFound(this, var4)))
						this.selectedXrayBlocs.add(new XRayListEntryFound(this, var4));
				}
			}
		}
		this.availableXrayBlocsList = new GuiXrayManagerAvailable(this.mc, 200, this.height, this.availableXrayBlocs);
        this.availableXrayBlocsList.setSlotXBoundsFromLeft(this.width / 2 - 4 - 200);
        this.availableXrayBlocsList.registerScrollButtons(7, 8);
        this.selectedXrayBlocsList = new GuiXrayManagerSelected(this.mc, 200, this.height, this.selectedXrayBlocs);
        this.selectedXrayBlocsList.setSlotXBoundsFromLeft(this.width / 2 + 4);
        this.selectedXrayBlocsList.registerScrollButtons(7, 8);
    }
    public void CreateLists(Boolean search, String text) {
    	this.availableXrayBlocs = Lists.newArrayList();
		this.selectedXrayBlocs = Lists.newArrayList();
        Xray xray = Xray.getXray();
        listcontain = xray.getList();
		ArrayList var22 = Lists.newArrayList(Block.blockRegistry.getKeys());
		Iterator var33 = var22.iterator();
		Block var44;
		while (var33.hasNext()) {
			var44 = (Block) Block.blockRegistry.getObject(var33.next());
			this.lb.add(new Listblockks(var44));
			this.basiclist.add(var44); }
		Collections.sort(this.lb, new SortByBlockType());
		this.var2 = Lists.newArrayList();
		for(Listblockks lbb : this.lb) {
			/*if(search) {
				if(this.isSearchSuccessful(text, lbb.block)) {
					this.var2.add(lbb.block);
				}
			} else {*/
				this.var2.add(lbb.block);
			//}
		}
		Iterator var3 = var2.iterator();
		Block var4;
		
		while (var3.hasNext()) {
			var4 = (Block) var3.next();
			if (!var4.getLocalizedName().startsWith("tile.") && !(var4 instanceof BlockFarmland)) {
				if(!listcontain.contains(Block.getIdFromBlock(var4))) {
					if(!this.hasXrayAvailableEntry(new XRayListEntryFound(this, var4))) {
						this.availableXrayBlocs.add(new XRayListEntryFound(this, var4));
					}
				} else {
					if(!this.selectedXrayBlocs.contains(new XRayListEntryFound(this, var4)))
						this.selectedXrayBlocs.add(new XRayListEntryFound(this, var4));
				}
			}
		}
    }
    
    public void Update() {
    	this.availableXrayBlocsList = new GuiXrayManagerAvailable(this.mc, 200, this.height, this.availableXrayBlocs);
        this.availableXrayBlocsList.setSlotXBoundsFromLeft(this.width / 2 - 4 - 200);
        this.availableXrayBlocsList.registerScrollButtons(7, 8);
        this.selectedXrayBlocsList = new GuiXrayManagerSelected(this.mc, 200, this.height, this.selectedXrayBlocs);
        this.selectedXrayBlocsList.setSlotXBoundsFromLeft(this.width / 2 + 4);
        this.selectedXrayBlocsList.registerScrollButtons(7, 8);
    }
    
    public void handleMouseInput() throws IOException
    {
        super.handleMouseInput();
        this.selectedXrayBlocsList.func_178039_p(); //handleMouseInput
        this.availableXrayBlocsList.func_178039_p();
    }
    
    protected void mouseReleased(int mouseX, int mouseY, int state)
    {
        super.mouseReleased(mouseX, mouseY, state);
    }
    
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException
    {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        this.availableXrayBlocsList.func_148179_a(mouseX, mouseY, mouseButton);
        this.selectedXrayBlocsList.func_148179_a(mouseX, mouseY, mouseButton);
    }
    
    public void resetLists() {
    	this.availableXrayBlocsList.resetLists();
    	this.selectedXrayBlocsList.resetLists();
    }
    
    public boolean hasXraySelectedEntry(XRayListEntry xRayListEntry) {
    	return this.selectedXrayBlocs.contains(xRayListEntry);
    }
    
    public boolean hasXrayAvailableEntry(XRayListEntry xRayListEntry) {
    	return this.availableXrayBlocs.contains(xRayListEntry);
    }
    
    public List getListContaining(XRayListEntry xRayListEntry) {
    	return this.hasXraySelectedEntry(xRayListEntry) ? this.selectedXrayBlocs : this.availableXrayBlocs;
    }
    
    public void markChanged()
    {
        this.changed = true;
    }
    
    public List getAvailableXrayBlocs() {
    	return this.availableXrayBlocs;
    }
    
    public List getSelectedXrayBlocs() {
    	return this.selectedXrayBlocs;
    }
    
    public List getAllXrayBlocs() {
    	return allXrayBlocs;
    }
    
protected void keyTyped(char typedChar, int keyCode) throws IOException {
		
		if (keyCode == Keyboard.KEY_ESCAPE)
			this.mc.displayGuiScreen(this.prevGui);
		
		/*if (this.isKeyValid(keyCode)) {
			this.search.setFocused(true);
			this.search.textboxKeyTyped(typedChar, keyCode);
			String text = this.search.getText();
			
			resetLists();
			CreateLists(true, text);
			Update();
			
		}*/
			
		super.keyTyped(typedChar, keyCode);
	}
    
    public boolean isKeyValid(int keyCode) {
		return (keyCode >= 16 && keyCode <= 50) || (keyCode == Keyboard.KEY_RETURN) || (keyCode == 14) || (keyCode == Keyboard.KEY_SPACE) || (keyCode == 57) || (keyCode >= 71 && keyCode <= 73) || (keyCode >= 75 && keyCode <= 77) || (keyCode == 79) || (keyCode >= 80 && keyCode <= 82) || (keyCode == 91) || (keyCode >= 2 && keyCode <= 11);
	}
    
    public void onGuiClosed() {
		Keyboard.enableRepeatEvents(false);
		super.onGuiClosed();
	}
	
	public boolean isSearchSuccessful(String text, Block b) {
		text = text.toLowerCase();
		String localizedName = b.getLocalizedName().toLowerCase();
		String unlocalizedName = b.getUnlocalizedName().toLowerCase();
		String blockID = String.valueOf(Block.getIdFromBlock(b));
		return text.equalsIgnoreCase("") || localizedName.contains(text) || unlocalizedName.contains(text) || text.contains(blockID);
	}
	
	public void performSearchBlocs() {
		String text = this.search.getText();
		ArrayList<Listblockks> block = new ArrayList<Listblockks>();
		
		for(Block b : this.allXrayBlocs) {
			if(text.equalsIgnoreCase("") || text.equalsIgnoreCase("")) {
				this.list.clear();
				this.list.addAll(this.basiclist);
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
		this.list.clear();
		for(Listblockks bb : block) {
			this.list.add(bb.block);
		}
	}
	
	protected void actionPerformed(GuiButton button) throws IOException
    {
		if(button.id == 1) {
			this.mc.displayGuiScreen(this.prevGui);
		}
    }
    
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
    	this.drawBackground(0);
    	this.buttonList.clear();
    	this.buttonList.add(new GuiButton(1, this.width/2-100, this.height - 28, "Retour"));
    	
    	this.availableXrayBlocsList.drawScreen(mouseX, mouseY, partialTicks);
    	this.selectedXrayBlocsList.drawScreen(mouseX, mouseY, partialTicks);

		//this.search.drawRGBATextBox(-13882323, -14737633);
		drawCenteredString(var.NekoFont, "Xray Manager", this.width / 2, 10, 16777215);
		/*if (this.search.getText().isEmpty())
			drawCenteredString(var.NekoFont, "Rechercher...", (this.width*3 / 4) -14, 16, 16777215);*/
		super.drawScreen(mouseX, mouseY, partialTicks);
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
