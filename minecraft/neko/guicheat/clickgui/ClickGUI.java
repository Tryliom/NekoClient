package neko.guicheat.clickgui;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import neko.guicheat.clickgui.elements.Element;
import neko.guicheat.clickgui.elements.ModuleButton;
import neko.guicheat.clickgui.elements.menu.ElementSlider;
import neko.guicheat.clickgui.util.ColorUtil;
import neko.guicheat.clickgui.util.FontUtil;
import neko.guicheat.clickgui.util.SettingsUtil;
import neko.manager.ModuleManager;
import neko.guicheat.clickgui.settings.SettingsManager;
import neko.Client;
import neko.module.Category;
import neko.module.Module;
import neko.utils.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

 /**
  *  Made by HeroCode
  *  it's free to use
  *  but you have to credit me
  *
  *  @author HeroCode
  */
public class ClickGUI extends GuiScreen {
	public static ArrayList<Panel> panels;
	public static ArrayList<Panel> rpanels;
	private ModuleButton mb = null;
	public SettingsManager setmgr;
	
	//public double px;
	//public double py;
	
	/*
	 * Konstrukor sollte nur einmal aufgerufen werden => in der MainMethode des eigenen Codes
	 * hier Client.startClient()
	 * das GUI wird dann so geffnet: 
	 * 		mc.displayGuiScreen(Client.clickgui);
	 * 		this.setToggled(false);
	 * das Module wird sofort wieder beendet damit
	 * nchstes mal nicht 2mal der z.B. 'RSHIFT' Knopf gedrckt
	 * werden muss
	 */
	public ClickGUI() {
		setmgr = Client.Neko.settingsManager;
		
		FontUtil.setupFontUtils();
		panels = new ArrayList<>();
		double pwidth = 80;
		double pheight = 15;
		double px = 10;
		double py = 10;
		double pyplus = pheight + 10;
		double pxplus = pwidth + 10;
		
		/*
		 * Zum Sortieren der Panels einfach die Reihenfolge im Enum ndern ;)
		 */
		
		ArrayList<String> combatModule = new ArrayList<String>(),
				  renderModule = new ArrayList<String>(),playerModule = new ArrayList<String>(),
				  movementModule = new ArrayList<String>(),paramsModule = new ArrayList<String>(),
				  miscModule = new ArrayList<String>(),specialModule = new ArrayList<String>();
		
		for(Module module : Client.Neko.moduleManager.getModules()) {
		    if(module.getCategory() != Category.HIDE && !module.isCmd()) {
		    	if(module.getCategory() == Category.COMBAT) {  combatModule.add(module.getName()); } //Regen
		    	if(module.getCategory() == Category.RENDER) {  renderModule.add(module.getName());}
		    	if(module.getCategory() == Category.PLAYER) {  playerModule.add(module.getName());}
		    	if(module.getCategory() == Category.MOVEMENT) {  movementModule.add(module.getName());} //Blink
		    	if(module.getCategory() == Category.PARAMS) {  paramsModule.add(module.getName());}
		    	if(module.getCategory() == Category.MISC) {  miscModule.add(module.getName());}
		    	if(module.getCategory() == Category.Special) {  specialModule.add(module.getName());}
		    }
	  }
		  Collections.sort(combatModule); Collections.sort(renderModule); Collections.sort(playerModule); Collections.sort(movementModule);
		  Collections.sort(paramsModule); Collections.sort(miscModule); Collections.sort(specialModule);
		
			for (Category c : Category.values()) {
				if(c != Category.HIDE) {
					String title = "";//Character.toUpperCase(c.name().toLowerCase().charAt(0)) + c.name().toLowerCase().substring(1);
					if(c.name().equalsIgnoreCase("Combat")) {
						title = "c"+Character.toUpperCase(c.name().toLowerCase().charAt(0)) + c.name().toLowerCase().substring(1);
					}
					if(c.name().equalsIgnoreCase("Render")) {
						title = "e"+Character.toUpperCase(c.name().toLowerCase().charAt(0)) + c.name().toLowerCase().substring(1);
					}
					if(c.name().equalsIgnoreCase("Player")) {
						title = "3"+Character.toUpperCase(c.name().toLowerCase().charAt(0)) + c.name().toLowerCase().substring(1);
					}
					if(c.name().equalsIgnoreCase("Movement")) {
						title = "2"+Character.toUpperCase(c.name().toLowerCase().charAt(0)) + c.name().toLowerCase().substring(1);
					}
					if(c.name().equalsIgnoreCase("Misc")) {
						title = "7"+Character.toUpperCase(c.name().toLowerCase().charAt(0)) + c.name().toLowerCase().substring(1);
					}
					if(c.name().equalsIgnoreCase("Special")) {
						title = "6"+Character.toUpperCase(c.name().toLowerCase().charAt(0)) + c.name().toLowerCase().substring(1);
					}
					if(c.name().equalsIgnoreCase("Params")) {
						title = "f"+Character.toUpperCase(c.name().toLowerCase().charAt(0)) + c.name().toLowerCase().substring(1);
					}
					//title = Character.toUpperCase(c.name().toLowerCase().charAt(0)) + c.name().toLowerCase().substring(1);
					ClickGUI.panels.add(new Panel(title, px, py, pwidth, pheight, false, this) {
								@Override
								public void setup() {
									for(String s : combatModule) {
										Module m = Utils.getModule(s);
										if (!m.getCategory().equals(c))continue;
										this.Elements.add(new ModuleButton(m, this));
									}
									for(String s : renderModule) {
										Module m = Utils.getModule(s);
										if (!m.getCategory().equals(c))continue;
										this.Elements.add(new ModuleButton(m, this));
									}
									for(String s : playerModule) {
										Module m = Utils.getModule(s);
										if (!m.getCategory().equals(c))continue;
										this.Elements.add(new ModuleButton(m, this));
									}
									for(String s : movementModule) {
										Module m = Utils.getModule(s);
										if (!m.getCategory().equals(c))continue;
										this.Elements.add(new ModuleButton(m, this));
									}
									for(String s : paramsModule) {
										Module m = Utils.getModule(s);
										if (!m.getCategory().equals(c))continue;
										this.Elements.add(new ModuleButton(m, this));
									}
									for(String s : miscModule) {
										Module m = Utils.getModule(s);
										if (!m.getCategory().equals(c))continue;
										this.Elements.add(new ModuleButton(m, this));
									}
									for(String s : specialModule) {
										Module m = Utils.getModule(s);
										if (!m.getCategory().equals(c))continue;
										this.Elements.add(new ModuleButton(m, this));
									}
								}
					});
					//py += pyplus;
					px += pxplus;
				}
			}
		
		/*
		 * Wieso nicht einfach
		 * 		rpanels = panels;
		 * 		Collections.reverse(rpanels);
		 * Ganz eifach:
		 * 		durch diese Zuweisung wird rpanels einfach nur eine Weiterleitung
		 * 		zu panels, was mit 'Collections.reverse(rpanels);' nicht ganz 
		 * 		funktionieren wrde. Und da die Elemente nur 'rberkopiert' werden
		 * 		gibt es keine Probleme ;)
		 */
		rpanels = new ArrayList<Panel>();
		for (Panel p : panels) {
			rpanels.add(p);
		}
		Collections.reverse(rpanels);

	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		/*
		 * Panels und damit auch Buttons rendern.
		 * panels wird NUR hier im Code verwendet, da das
		 * zuletzt gerenderte Panel ganz oben ist 
		 * Auch wenn es manchmal egal wre ob panels/rpanels
		 * benutzt wird habe ich mich einfach mal dazu entschieden,
		 * einfach weil es einfacher ist nur einmal panels zu benutzen
		 */
		for (Panel p : panels) {
			p.drawScreen(mouseX, mouseY, partialTicks);
		}

		
		/*															*/ ScaledResolution s = new ScaledResolution(mc);
  		/* DO NOT REMOVE											*/ GL11.glPushMatrix();
		/* copyright HeroCode 2017									*/ GL11.glTranslated(s.getScaledWidth(), s.getScaledHeight(), 0);GL11.glScaled(0.5, 0.5, 0.5);
		/* https://www.youtube.com/channel/UCJum3PIbnYvIfIEu05GL_yQ	*/ //FontUtil.drawStringWithShadow("b"+"y"+ "H"+"e"+"r"+"o"+"C"+"o"+"d"+"e", -Minecraft.getMinecraft().fontRendererObj.getStringWidth("b"+"y"+ "H"+"e"+"r"+"o"+"C"+"o"+"d"+"e"), -Minecraft.getMinecraft().fontRendererObj.FONT_HEIGHT, 0xff11F86B);
		/*															*/ GL11.glPopMatrix();
		
		mb = null;
		/*
		 * berprfen ob ein Button listening == true hat, wenn
		 * ja, dann soll nicht mehr gesucht werden, nicht dass 
		 * 1+ auf listening steht...
		 */
		listen:
		for (Panel p : panels) {
			if (p != null && p.visible && p.extended && p.Elements != null
					&& p.Elements.size() > 0) {
				for (ModuleButton e : p.Elements) {
					if (e.listening) {
						mb = e;
						break listen;
					}
				}
			}
		}
		
		/*
		 * Settings rendern. Da Settings ber alles gerendert werden soll,
		 * abgesehen vom ListeningOverlay werden die Elements von hier aus
		 * fast am Schluss gerendert
		 */
		for (Panel panel : panels) {
			if (panel.extended && panel.visible && panel.Elements != null) {
				for (ModuleButton b : panel.Elements) {
					if (b.extended && b.menuelements != null && !b.menuelements.isEmpty()) {
						double off = 0;
						Color temp;
						int outlineColor;
						
						if(SettingsUtil.getRainbowGui()) {

							temp = ColorUtil.rainbowEffekt(1L, 1.0f);
							outlineColor = new Color(temp.getRed(), temp.getGreen(), temp.getBlue(), 170).getRGB();
							
						} else {

							temp = ColorUtil.getClickGUIColor();
							outlineColor = new Color(temp.getRed(), temp.getGreen(), temp.getBlue(), 170).getRGB();
							
						}
						//Color red = new Color(255,0,255); Color green = new Color(26,0,255); Color blue = new Color(42,0,255);
						//int outlineColor = new Color(255, 26, 42, 170).getRGB();
						for (Element e : b.menuelements) {
							e.offset = off;
							e.update();
							if(Client.Neko.settingsManager.getSettingByName("Design").getValString().equalsIgnoreCase("New")){
								Gui.drawRect(e.x, e.y, e.x + e.width + 2, e.y + e.height, outlineColor);
							}
							e.drawScreen(mouseX, mouseY, partialTicks);
							off += e.height;
						}
					}
				}
			}

		}
		
		/*
		 * Wenn mb != null ist => ein Button listening == true
		 * dann wird das Overlay gerendert mit ein paar Informationen.
		 */
		if(mb != null){
			drawRect(0, 0, this.width, this.height, 0x88101010);
			GL11.glPushMatrix();
			GL11.glTranslatef(s.getScaledWidth() / 2, s.getScaledHeight() / 2, 0.0F);
			GL11.glScalef(4.0F, 4.0F, 0F);
			FontUtil.drawTotalCenteredStringWithShadow("["+mb.mod.getName()+"] (Touche:"+(mb.mod.getBind() > -1 ? Keyboard.getKeyName(mb.mod.getBind())+")": "Aucune)"), 0, -10, 0xffffffff);
			GL11.glScalef(0.5F, 0.5F, 0F);
			FontUtil.drawTotalCenteredStringWithShadow("Appuyez sur une touche pour modifier", 0, 0, 0xffffffff);
			FontUtil.drawTotalCenteredStringWithShadow("Appuyez sur Echap pour supprimer", 0, 20, 0xffffffff);
			GL11.glScalef(0.45F, 0.45F, 0F);
			FontUtil.drawTotalCenteredStringWithShadow("OU", 0, 20, 0xffffffff);
			GL11.glPopMatrix();
		}
		
		/*
		 * Nicht bentigt, aber es ist so einfach sauberer ;)
		 * Und ohne diesen call knnen keine GUIButtons/andere Elemente
		 * gerendert werden
		 */
		super.drawScreen(mouseX, mouseY, partialTicks);
	}

	@Override
	public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
		/*
		 * Damit man nicht nochmal den Listeningmode aktivieren kann,
		 * wenn er schon aktiviert ist
		 */
		if(mb != null)return;
		
		/*
		 * Bentigt damit auch mit Elements interagiert werden kann
		 * besonders zu beachten ist dabei, dass zum einen rpanels aufgerufen
		 * wird welche eine Eigenstndige Kopie von panels ist, genauer oben erklrt
		 * Also rpanels damit zuerst das panel 'untersucht' wird, dass als letztes
		 * gerendert wurde => Ganz oben ist!
		 * sodass der Nutzer nicht mit dem Unteren interagiern kann, weil er es wohl
		 * nicht will. Und damit nicht einfach mit Panels  anstatt Elements interagiert wird
		 * werden hier nur die Settings untersucht. Und wenn wirklich interagiert wurde, dann
		 * endet diese Methode hier.
		 * Das ist auch in anderen Loops zu beobachten
		 */
		for (Panel panel : rpanels) {
			if (panel.extended && panel.visible && panel.Elements != null) {
				for (ModuleButton b : panel.Elements) {
					if (b.extended) {
						for (Element e : b.menuelements) {
							if (e.mouseClicked(mouseX, mouseY, mouseButton))
								return;
						}
					}
				}
			}
		}

		/*
		 * Bentigt damit mit ModuleButtons interagiert werden kann
		 * und Panels 'gegriffen' werden knnen
		 */
		for (Panel p : rpanels) {
			if (p.mouseClicked(mouseX, mouseY, mouseButton))
				return;
		}
		
		/*
		 * Nicht bentigt, aber es ist so einfach sauberer ;)
		 */
		try {
			super.mouseClicked(mouseX, mouseY, mouseButton);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void mouseReleased(int mouseX, int mouseY, int state) {
		/*
		 * Damit man nicht nochmal den Listeningmode aktivieren kann,
		 * wenn er schon aktiviert ist
		 */
		if(mb != null)return;
		
		/*
		 * Eigentlich nur fr die Slider bentigt, aber
		 * durch diesen Call erfhrt jedes Element, wenn
		 * z.B. Rechtsklick losgelassen wurde
		 */
		for (Panel panel : rpanels) {
			if (panel.extended && panel.visible && panel.Elements != null) {
				for (ModuleButton b : panel.Elements) {
					if (b.extended) {
						for (Element e : b.menuelements) {
							e.mouseReleased(mouseX, mouseY, state);
						}
					}
				}
			}
		}
		
		/*
		 * Bentigt damit Slider auch losgelassen werden knnen und nicht
		 * immer an der Maus 'festkleben' :>
		 */
		for (Panel p : rpanels) {
			p.mouseReleased(mouseX, mouseY, state);
		}
		
		/*
		 * Nicht bentigt, aber es ist so einfach sauberer ;)
		 */
		super.mouseReleased(mouseX, mouseY, state);
	}

	@Override
	protected void keyTyped(char typedChar, int keyCode) {
		/*
		 * Bentigt fr die Keybindfunktion
		 */
		for (Panel p : rpanels) {
			if (p != null && p.visible && p.extended && p.Elements != null && p.Elements.size() > 0) {
				for (ModuleButton e : p.Elements) {
					try {
						if (e.keyTyped(typedChar, keyCode))return;
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		}
		
		if(mc.currentScreen == Client.Neko.clickGui) {
			if(keyCode == Client.Neko.moduleManager.getModuleByName("Gui").getBind() || keyCode == Keyboard.KEY_ESCAPE) {
				Client.Neko.moduleManager.getModuleByName("Gui").toggleModule();
				SettingsUtil.SaveGuiSettings();
			}
		}

		/*
		 * keyTyped in GuiScreen MUSS aufgerufen werden, damit 
		 * man mit z.B. ESCAPE aus dem GUI gehen kann
		 */
		try {
			super.keyTyped(typedChar, keyCode);
		} catch (IOException e2) {
			e2.printStackTrace();
		}
	}

	@Override
	public void initGui() {
		/*
		 * Start blur
		 */
		if (OpenGlHelper.shadersSupported && mc.getRenderViewEntity() instanceof EntityPlayer) {
			if (mc.entityRenderer.theShaderGroup != null) {
				mc.entityRenderer.theShaderGroup.deleteShaderGroup();
			}
			mc.entityRenderer.loadShader(new ResourceLocation("shaders/post/blur.json"));
		}
	}

	@Override
	public void onGuiClosed() {
		/*
		 * End blur 
		 */
		if (mc.entityRenderer.theShaderGroup != null) {
			mc.entityRenderer.theShaderGroup.deleteShaderGroup();
			mc.entityRenderer.theShaderGroup = null;
		}
		/*
		 * Sliderfix
		 */
		for (Panel panel : ClickGUI.rpanels) {
			if (panel.extended && panel.visible && panel.Elements != null) {
				for (ModuleButton b : panel.Elements) {
					if (b.extended) {
						for (Element e : b.menuelements) {
							if(e instanceof ElementSlider){
								((ElementSlider)e).dragging = false;
							}
						}
					}
				}
			}
		}
	}

	public void closeAllSettings() {
		for (Panel p : rpanels) {
			if (p != null && p.visible && p.extended && p.Elements != null
					&& p.Elements.size() > 0) {
				for (ModuleButton e : p.Elements) {
					//e.extended = false;
				}
			}
		}
	}
}
