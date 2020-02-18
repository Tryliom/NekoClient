package neko.gui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Vector;

import javax.swing.JTextArea;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import neko.Client;
import neko.manager.ModuleManager;
import neko.module.modules.render.Xray;
import neko.module.other.Rank;
import neko.utils.Utils;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiSlot;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.renderer.RenderHelper;

public class GuiRankManager extends GuiScreen {
	
	private GuiScreen prevGui;
	private Minecraft mc = Minecraft.getMinecraft();
	private GuiList guiList;
	private Client var = Client.getNeko();
	private GuiTextField search;
	String searchstr;
	
	public static String Message;
	public static String Informations;
	
	public static String ActualRankName;
	public static String SelectedRankName = "";
	public static String[] ActualDesc;
	public static String[] SelectedDesc = {"",""};
	public static String act = "§fRang actuel :";
	public static String sel = "§fRang séléctionné :";
	
	public GuiRankManager(GuiScreen gui) {
		this.prevGui = gui;
	}
	
	public void initGui() {
		Keyboard.enableRepeatEvents(true);
		this.guiList = new GuiList(this);
		this.guiList.registerScrollButtons(7, 8);
		this.search = new GuiTextField(2, this.fontRendererObj, (this.width*3/4) - 50, 10, 100, 20);
		this.search.setText("");
		this.Message = "§fRang actuel : " + Utils.getRankColor2(var.rang.getName()) + var.rang.getName();
		this.Informations = "";
		this.ActualRankName = Utils.getRankColor2(var.rang.getName()) + var.rang.getName();
		this.ActualDesc = Utils.getRankDescription(var.rang.getName());
	}
	
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		drawDefaultBackground();
		this.buttonList.clear();
		this.buttonList.add(new GuiButton(0, this.width*3 / 4, this.height - 52, 100, 20, "Retour"));
		if(this.Informations.equalsIgnoreCase("")) {
			this.buttonList.add(new GuiButton(1, this.width*3 / 4, this.height - 82, 100, 20, "Toggle"));
		}
		this.guiList.drawScreen(mouseX, mouseY, partialTicks);
		this.search.drawRGBATextBox(-13882323, -14737633);
		drawCenteredString(var.NekoFont, Message, 10 + var.NekoFont.getStringWidth(Message)/2, 20, 16777215);
		drawCenteredString(this.fontRendererObj, act, 10 + this.fontRendererObj.getStringWidth(act)/2, 42, 16777215);
		drawCenteredString(this.fontRendererObj, ActualRankName, 10 + this.fontRendererObj.getStringWidth(ActualRankName)/2, 50, 16777215);
		int xx = 60;
		for(String s : ActualDesc) {
			drawCenteredString(this.fontRendererObj, s, 10 + this.fontRendererObj.getStringWidth(s)/2, xx, 16777215);
			xx += 8;
		}
		xx+=8;
		drawHorizontalLine(10, 50, xx, 0x99ffffff);
		xx+=8;
		drawCenteredString(this.fontRendererObj, sel, 10 + this.fontRendererObj.getStringWidth(sel)/2, xx, 16777215);
		xx+=8;
		drawCenteredString(this.fontRendererObj, Utils.getRankColor2(SelectedRankName) + SelectedRankName, 10 + this.fontRendererObj.getStringWidth(SelectedRankName)/2, xx, 16777215);
		xx += 10;
		for(String s : SelectedDesc) {
			drawCenteredString(this.fontRendererObj, s, 10 + this.fontRendererObj.getStringWidth(s)/2, xx, 16777215);
			xx += 8;
		}
		drawCenteredString(var.NekoFont, this.Informations, this.width/2, this.height-20, 16777215);
		drawCenteredString(var.NekoFont, "Rank Manager", this.width / 2, 10, 16777215);
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
			int i = this.guiList.getSelectedSlot();
			Rank r = this.guiList.list.get(i);
			if(!r.isLock() && !r.getName().equalsIgnoreCase("Choumise")) {
				if(Utils.changeRank(r.getName())) {
					GuiRankManager.this.Message = "§fRang actuel : " + Utils.getRankColor2(var.rang.getName()) + var.rang.getName();
					GuiRankManager.this.ActualRankName = Utils.getRankColor2(var.rang.getName()) + var.rang.getName();
					GuiRankManager.this.ActualDesc = Utils.getRankDescription(var.rang.getName());
				}
			} else {
				this.Informations = "§cVous ne pouvez pas séléctionner ce rang, il est bloqué !";
			}
			break;
		}
	}
	
	public boolean isKeyValid(int keyCode) {
		return (keyCode >= 16 && keyCode <= 50) || (keyCode == Keyboard.KEY_RETURN) || (keyCode == 14) || (keyCode == Keyboard.KEY_SPACE) || (keyCode == 57) || (keyCode >= 71 && keyCode <= 73) || (keyCode >= 75 && keyCode <= 77) || (keyCode == 79) || (keyCode >= 80 && keyCode <= 82) || (keyCode == 91) || (keyCode >= 2 && keyCode <= 11);
	}
	
	public boolean isSearchSuccessful(String text, Rank r) {
		text = text.toLowerCase();
		String RankName = r.getName();
		String RankRate = r.getRate().name();
		return text.equalsIgnoreCase("") || RankName.contains(text) || RankRate.contains(text);
	}
	
	protected void keyTyped(char typedChar, int keyCode) throws IOException {
		
		if (keyCode == Keyboard.KEY_ESCAPE)
			this.mc.displayGuiScreen(this.prevGui);
		
		if (this.isKeyValid(keyCode)) {
			this.search.setFocused(true);
			this.search.textboxKeyTyped(typedChar, keyCode);
			ArrayList<ListRankks> ranks = new ArrayList<ListRankks>();
			String text = this.search.getText();
			
			for (Rank b : this.guiList.basicList) {
				if (this.isSearchSuccessful(text, b)) {
					ranks.add(new ListRankks(b));
				}
			}
			Collections.sort(ranks, new SortByRankTypeAndAlphabetic());
			this.guiList.list.clear();
			for (ListRankks bb : ranks) {
				this.guiList.list.add(bb.rank);
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
		private Vector<Rank> list = new Vector<Rank>();
		private Vector<Rank> basicList = new Vector<Rank>();
		
		ArrayList<ListRankks> lr = new ArrayList<ListRankks>();
		
		public GuiList(GuiScreen prevGui) {
			super(Minecraft.getMinecraft(), prevGui.width, prevGui.height, 40, prevGui.height - 26, 30);
			for(Rank r : ModuleManager.rang) {
				if(!r.isLock()) {
					this.lr.add(new ListRankks(r));
				}
			}
			Collections.sort(this.lr, new SortByRankTypeAndAlphabetic());
			for(int i = 0; i<this.lr.size(); i++) {
				this.list.add(this.lr.get(i).rank);
				this.basicList.add(this.lr.get(i).rank);
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
			if(this.list.size() == 0) {
				return 1;
			}
			return this.list.size();
		}
		
		protected void elementClicked(int i, boolean doubleClick, int var3, int var4) {
			this.selectedSlot = i;
			Rank r = this.list.get(i);
			if(doubleClick) {
				if(!r.isLock() && !r.getName().equalsIgnoreCase("Choumise")) {
					if(Utils.changeRank(r.getName())) {
						GuiRankManager.this.Message = "§fRang actuel : " + Utils.getRankColor2(var.rang.getName()) + var.rang.getName();
						GuiRankManager.this.ActualRankName = Utils.getRankColor2(var.rang.getName()) + var.rang.getName();
						GuiRankManager.this.ActualDesc = Utils.getRankDescription(var.rang.getName());
					}
				} else {
					GuiRankManager.Informations = "§cVous ne pouvez pas séléctionner ce rang, il est bloqué !";
				}
			}
			if(r.isLock() || r.getName().equalsIgnoreCase("Choumise")) {
				GuiRankManager.this.Informations = "§cVous ne pouvez pas séléctionner ce rang, il est bloqué !";
			} else {
				GuiRankManager.this.Informations = "";
			}
			GuiRankManager.this.SelectedRankName = r.getName();
			GuiRankManager.this.SelectedDesc = Utils.getRankDescription(r.getName());
		}
		
		protected void drawBackground() {}

		
		protected void drawSlot(int i, int x, int y, int prevGuiWidth, int mouseX, int MouseY) {
			if(this.list.size() == 0 && i == 0) {
				var.NekoFont.drawString("§cAucun Rangs trouvé avec ce nom.", x + 31, y + 3, 10526880);
				return;
			}
			Rank r = this.list.get(i);
			boolean selected = (var.rang == r) ? true : false;
            int var12 = this.left + this.width / 2 + this.getListWidth() / 2;
            
            int middle = x + this.getListWidth() / 2;
			int nameY = y + this.slotHeight/2 - 12;
			int rateY = y + this.slotHeight/2;
			
			String color = Utils.getRankColor2(r.getName());
            
			if(!r.isLock()) {
				drawCenteredString(var.NekoFont, (selected ? "§a" : "§7") + r.getName(), middle, nameY, 10526880);
				drawCenteredString(var.NekoFont, color + r.getRate().name(), middle, rateY, 10526880);
			} else {
				int Chars = r.getName().length();
				String blbl = "";
				for(int ii = 1; i<Chars; i++) {
					blbl = blbl + "?";
				}
				drawCenteredString(var.NekoFont, "§0" + blbl, middle, nameY, 10526880);
				drawCenteredString(var.NekoFont, color + r.getRate().name(), middle, rateY, 10526880);
			}
		    int LineColor = 0x99ffffff;
			drawHorizontalLine(x + this.getListWidth() / 2 - 20, x + this.getListWidth() / 2 + 20, y + this.slotHeight - 3, LineColor);
			drawHorizontalLine(x + this.getListWidth() / 2 - 20, x + this.getListWidth() / 2 + 20, y - this.slotHeight - 2, LineColor);
			GL11.glDisable(3042);
		}
		
	}

}

class ListRankks {
	Rank rank;
	String rankRate;
	String rankSort;
	String rankName;
	
	public ListRankks(Rank r) {
		this.rank = r;
		this.rankName = r.getName();
		this.rankRate = r.getRate().name();
		switch(this.rankRate.toLowerCase()) {
			case "event": this.rankSort = "aa"; break;
			case "supra": this.rankSort = "bb"; break;
			case "neko": this.rankSort = "cc"; break;
			case "crazylove": this.rankSort = "dd"; break;
			case "titan": this.rankSort = "ee"; break;
			case "mythique": this.rankSort = "ff"; break;
			case "légendaire": this.rankSort = "gg"; break;
			case "satanique": this.rankSort = "hh"; break;
			case "divin": this.rankSort = "ii"; break;
			case "magical": this.rankSort = "jj"; break;
			case "ultrarare": this.rankSort = "kk"; break;
			case "rare": this.rankSort = "ll"; break;
			case "ordinaire": this.rankSort = "mm"; break;
		}
	}
	
	public String RankSort() {
		return this.rankSort;
	}
	
}

class SortByRankTypeAndAlphabetic implements Comparator<ListRankks> {
	
	public int compare(ListRankks a, ListRankks b) {
		if(a.rankSort.equalsIgnoreCase(b.rankSort)) {
			return a.rankName.compareTo(b.rankName);
		}
		return a.rankSort.compareTo(b.rankSort);
	}
	
}
