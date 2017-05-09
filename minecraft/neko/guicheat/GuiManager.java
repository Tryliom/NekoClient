package neko.guicheat;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

import org.darkstorm.minecraft.gui.AbstractGuiManager;
import org.darkstorm.minecraft.gui.component.Button;
import org.darkstorm.minecraft.gui.component.CheckButton;
import org.darkstorm.minecraft.gui.component.ComboBox;
import org.darkstorm.minecraft.gui.component.Frame;
import org.darkstorm.minecraft.gui.component.SelectableComponent;
import org.darkstorm.minecraft.gui.component.basic.BasicButton;
import org.darkstorm.minecraft.gui.component.basic.BasicCheckButton;
import org.darkstorm.minecraft.gui.component.basic.BasicComboBox;
import org.darkstorm.minecraft.gui.component.basic.BasicFrame;
import org.darkstorm.minecraft.gui.font.UnicodeFontRenderer;
import org.darkstorm.minecraft.gui.layout.GridLayoutManager;
import org.darkstorm.minecraft.gui.layout.GridLayoutManager.HorizontalGridConstraint;
import org.darkstorm.minecraft.gui.listener.ButtonListener;
import org.darkstorm.minecraft.gui.listener.SelectableComponentListener;
import org.darkstorm.minecraft.gui.theme.simple.SimpleTheme;

import neko.Client;
import neko.dtb.Alt;
import neko.dtb.RequestThread;
import neko.module.Category;
import neko.module.Module;
import neko.module.other.Irc;
import neko.utils.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.network.play.client.C03PacketPlayer;

public class GuiManager extends AbstractGuiManager  {
	Client var = Client.getNeko();
	public int backR=30;
	public int backG=30;
	public int backB=80;
	public int backA=220;
	
	// SimpleFrameUI pour régler la largeur et hauteur
		class ModuleFrame extends BasicFrame {
			private ModuleFrame() {
			}

			private ModuleFrame(String title) {
				super(title);
			}
		}

		private final AtomicBoolean setup;

		public GuiManager() {
			setup = new AtomicBoolean();
			
		}

		@Override
		public void setup() {		
			final Minecraft mc = Minecraft.getMinecraft();
			final Map<Category, ModuleFrame> categoryFrames = new HashMap<Category, ModuleFrame>();
			for(final Module m : var.moduleManager.ActiveModule) {
				if (m.getCategory()!=Category.HIDE) {
					ModuleFrame frame = categoryFrames.get(m.getCategory());
					if(frame == null) {
						String name = m.getCategory().toString().toLowerCase();
						name = Character.toUpperCase(name.charAt(0)) + name.substring(1);
						frame = new ModuleFrame(name);
						frame.setTheme(new SimpleTheme());
						frame.setLayoutManager(new GridLayoutManager(1, 0));
						frame.setVisible(true);
						frame.setClosable(false);
						frame.setMinimized(true);
						frame.setPinnable(false);
						
						addFrame(frame);
						categoryFrames.put(m.getCategory(), frame);
					}
					if (var.onlyrpg.isActive()) {
						boolean valid=false;
						for (String cheat : var.onlyrpg.getCheat()) {
							if (cheat.equalsIgnoreCase(m.getName())) {
								valid=true;
							}
						}
						if (valid) {
							Button button = new BasicButton(m.getName()) {
								@Override
								public void update() {
									if (!m.isCmd())
										setForegroundColor(m.getToggled() ? new Color(120, 190, 120, 220) : new Color(120, 120, 190, 220));
								}
							};
							button.addButtonListener(new ButtonListener(){
								@Override
								public void onButtonPress(Button button){
									m.toggleModule();
									if (!m.isCmd())
										button.setForegroundColor(m.getToggled() ? new Color(120, 190, 120, 220) : new Color(120, 120, 190, 220));
								}
							});
							frame.add(button, HorizontalGridConstraint.FILL);
						}
					} else {
						Button button = new BasicButton(m.getName()) {
						@Override
						public void update() {
							if (!m.isCmd())
								setForegroundColor(m.getToggled() ? new Color(120, 190, 120, 220) : new Color(120, 120, 190, 220));
						}
					};
					button.addButtonListener(new ButtonListener(){
						@Override
						public void onButtonPress(Button button){
							m.toggleModule();
							if (!m.isCmd())
								button.setForegroundColor(m.getToggled() ? new Color(120, 190, 120, 220) : new Color(120, 120, 190, 220));
						}
					});
					frame.add(button, HorizontalGridConstraint.FILL);
					}
					
					
				}
			}
			
			
			//TODO: Fenêtre Utils
			Frame f;
			String name = "";
			if (!var.onlyrpg.isActive()) {
				name = "Utils";
				f = new ModuleFrame(name);
				f.setTheme(new SimpleTheme());
				f.setLayoutManager(new GridLayoutManager(1, 0));
				f.setVisible(true);
				f.setClosable(false);
				f.setMinimized(true);	
				f.setPinnable(false);
				
				addFrame(f);		
				
				Button button = new BasicButton("Reload");
				button.addButtonListener(new ButtonListener(){
					@Override
					public void onButtonPress(Button button){						
						// Ce que ça fait					
						Utils.reload();
						
					}
				});
				
				f.add(button, HorizontalGridConstraint.FILL);
				
				button = new BasicButton("Irc");
				button.addButtonListener(new ButtonListener(){
					@Override
					public void onButtonPress(Button button){	
						Irc irc = Irc.getInstance();
						if (irc.isOn()) {
							Utils.addChat("§cIrc désactivé");
							new RequestThread("insertmsgleft", null).start();
							irc.setLastId(-1);
						} else {
							Utils.addChat("§aIrc activé");
							new RequestThread("insertmsgjoin", null).start();
						}
						irc.setOn(!irc.isOn());
					}
				});
				
				f.add(button, HorizontalGridConstraint.FILL);
				
				button = new BasicButton("Clear");
				button.addButtonListener(new ButtonListener(){
					@Override
					public void onButtonPress(Button button){						
						// Ce que ça fait				
						mc.ingameGUI.getChatGUI().clearChatMessages();
					}
				});
				
				f.add(button, HorizontalGridConstraint.FILL);
				
				button = new BasicButton("Nyah");
				button.addButtonListener(new ButtonListener(){
					@Override
					public void onButtonPress(Button button){						
						// Ce que ça fait				
						mc.thePlayer.sendChatMessage(Utils.getNyah());
					}
				});
				
				f.add(button, HorizontalGridConstraint.FILL);
				
				button = new BasicButton("Damage");
				button.addButtonListener(new ButtonListener(){
					@Override
					public void onButtonPress(Button button){						
						// Ce que ça fait				
						for (int i = 0; i < 2; i++) {
						      mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(mc.thePlayer.posX, mc.thePlayer.posY + 3D, mc.thePlayer.posZ, false));
						      mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ, false));
						}
					}
				});
				
				f.add(button, HorizontalGridConstraint.FILL);
				
				button = new BasicButton("Alt");
				button.addButtonListener(new ButtonListener(){
					@Override
					public void onButtonPress(Button button){						
						Alt a = Alt.getAlt();
						if (a.getAltTimer()==0) {
							new RequestThread("loginAlt", null).start();
							a.setAltTimer(15);
						} else {
							Utils.addChat(Utils.setColor("Vous pourrez utiliser cette commande dans "+a.getAltTimer()+" secondes !", "§c"));
						}						
					}
				});
				
				f.add(button, HorizontalGridConstraint.FILL);	
				
				button = new BasicButton("Plugins");
				button.addButtonListener(new ButtonListener(){
					@Override
					public void onButtonPress(Button button){						
						// Ce que ça fait				
						if (Utils.isLock("--plugins")) {
							Utils.addWarn("Plugins");
							mc.thePlayer.playSound("mob.villager.no", 1.0F, 1.0F);
						} else
							Utils.toggleModule("Plugins");
					}
				});
				
				f.add(button, HorizontalGridConstraint.FILL);			
			}
			//TODO: Frame Font
			
			name = "Font";
			f = new ModuleFrame(name);
			f.setTheme(new SimpleTheme());
			f.setLayoutManager(new GridLayoutManager(1, 0));
			f.setVisible(true);
			f.setClosable(false);
			f.setMinimized(true);	
			f.setPinnable(false);
			
			addFrame(f);	
			
			CheckButton cbAlpha = new BasicCheckButton();
			cbAlpha.setText("Transparent");
			cbAlpha.setSelected(SimpleTheme.alpha);
			cbAlpha.addButtonListener(new ButtonListener() {
				
				@Override
				public void onButtonPress(Button c) {
					CheckButton cb = (CheckButton) c;
					if (cb.isSelected()) {
						SimpleTheme.alpha=true;
					} else {
						SimpleTheme.alpha=false;
					}
					Utils.saveFont();
					
				}
			});
			
			f.add(cbAlpha, HorizontalGridConstraint.FILL);			
			
			ComboBox cbox = new BasicComboBox();
			cbox.setElements("12", "14", "16", "18", "20", "22");
			for (int i=0;i<cbox.getElements().length;i++) {
				cbox.setSelectedIndex(i);
				if (Integer.parseInt(cbox.getSelectedElement())==SimpleTheme.px) 
					break;
			}
			cbox.addSelectableComponentListener(new SelectableComponentListener() {
				
				@Override
				public void onSelectedStateChanged(SelectableComponent c) {
					if (!c.isSelected()) {
						ComboBox cbox = (ComboBox) c;					
						SimpleTheme.px=Integer.parseInt(cbox.getSelectedElement());
						Utils.saveFont();
						Utils.saveFrame();	
						var.gui = new GuiManager();
					    var.gui.setTheme(new SimpleTheme());
					    var.gui.setup();
					    Utils.loadFrame();
						for(Frame f: var.gui.getFrames()) {
							f.update();
						}
					}
				}
			});
			
			f.add(cbox, HorizontalGridConstraint.FILL);
			
			ComboBox cb = new BasicComboBox();
			cb.setElements("Wolfram", "Neko", "RulerIt", "Avalanche", "Quantify");
			String font = SimpleTheme.font;
			for (int i=0;i<cb.getElements().length;i++) {
				cb.setSelectedIndex(i);
				if (cb.getSelectedElement().equalsIgnoreCase(font))
					break;
			}
			cb.addSelectableComponentListener(new SelectableComponentListener() {
				
				@Override
				public void onSelectedStateChanged(SelectableComponent c) {
					if (!c.isSelected()) {
						ComboBox b = (ComboBox) c;
						String finalFont = b.getSelectedElement();
						
						for(Frame frame : getFrames()) {						
							SimpleTheme s = (SimpleTheme) frame.getTheme();
							if (finalFont.equalsIgnoreCase("Neko")) {
								s.setFontRenderer(Client.getNeko().NekoFont);
							} else
								try {
									Font ft = Font.createFont(Font.TRUETYPE_FONT, this.getClass().getResourceAsStream("/neko/font/"+finalFont+".ttf"));
									ft = ft.deriveFont(Font.BOLD, SimpleTheme.px);
									s.setFontRenderer(new UnicodeFontRenderer(ft));
								} catch (Exception ex) {
									try {
										Font ft = Font.createFont(Font.TRUETYPE_FONT, this.getClass().getResourceAsStream("/neko/font/"+finalFont+".otf"));
										ft = ft.deriveFont(Font.BOLD, SimpleTheme.px);
										s.setFontRenderer(new UnicodeFontRenderer(ft));
									} catch (Exception exi) {
										s.setFontRenderer(new UnicodeFontRenderer(new Font("Trebuchet MS", Font.BOLD, SimpleTheme.px)));
									}
								}
						}
						SimpleTheme.font=finalFont;
						Utils.saveFont();
						Utils.saveFrame();	
						var.gui = new GuiManager();
					    var.gui.setTheme(new SimpleTheme());
					    var.gui.setup();
					    Utils.loadFrame();
						for(Frame f: var.gui.getFrames()) {
							f.update();
						}
					}
				}
			});
			
			f.add(cb, HorizontalGridConstraint.FILL);
			
			resizeComponents();
			Minecraft minecraft = Minecraft.getMinecraft();
			Dimension maxSize = recalculateSizes();
			int offsetX = 5, offsetY = 5;
			int scale = minecraft.gameSettings.guiScale;
			if(scale == 0)
				scale = 1000;
			int scaleFactor = 0;
			while(scaleFactor < scale && minecraft.displayWidth / (scaleFactor + 1) >= 320 && minecraft.displayHeight / (scaleFactor + 1) >= 240)
				scaleFactor++;
			for(Frame frame : getFrames()) {
				frame.setX(offsetX);
				frame.setY(offsetY);
				offsetX += maxSize.width + 5;
				if(offsetX + maxSize.width + 5 > minecraft.displayWidth / scaleFactor) {
					offsetX = 5;
					offsetY += maxSize.height + 5;
				}
				frame.setBackgroundColor(new Color(backR, backG, backB, backA));				
			}
		}

		
		@Override
		protected void resizeComponents() {			
			recalculateSizes();
		}

		private Dimension recalculateSizes() {
			Frame[] frames = getFrames();
			int maxWidth = 0, maxHeight = 0;
			for(Frame frame : frames) {
				Dimension defaultDimension = frame.getTheme().getUIForComponent(frame).getDefaultSize(frame);
				maxWidth = Math.max(maxWidth, defaultDimension.width);
				frame.setHeight(defaultDimension.height);
				if(frame.isMinimized()) {
					for(Rectangle area : frame.getTheme().getUIForComponent(frame).getInteractableRegions(frame))
						maxHeight = Math.max(maxHeight, area.height);
				} else
					maxHeight = Math.max(maxHeight, defaultDimension.height);
			}
			for(Frame frame : frames) {
				frame.setWidth(maxWidth);
				frame.layoutChildren();
			}
			return new Dimension(maxWidth, maxHeight);
		}

}
