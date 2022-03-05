package neko;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import org.lwjgl.opengl.Display;

import neko.event.EventManager;
import neko.guicheat.clickgui.ClickGUI;
import neko.guicheat.clickgui.settings.SettingsManager;
import neko.manager.CommandManager;
import neko.manager.ModuleManager;
import neko.manager.OnlyRpgManager;
import neko.manager.QuestManager;
import neko.module.other.ModeType;
import neko.module.other.Necklace;
import neko.module.other.Rank;
import neko.module.other.enums.Chat;
import neko.utils.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.ResourceLocation;

public class Client {
	public static Client Neko = new Client();

	public SettingsManager settingsManager;
	public EventManager eventManager;
	public ModuleManager moduleManager;
	public CommandManager commandManager;
	public ClickGUI clickGui;
	
	Minecraft mc = Minecraft.getMinecraft();
	public final static String CLIENT_NAME = "Neko";
	public final String CLIENT_AUTHOR = "Tryliom et Marie";

	public static final String CLIENT_VERSION = "final";
	public static String scrollingSpacer = "                   ";
	public String strNeko = "§bNeko version " + CLIENT_VERSION;
	public String strCreator = "§eCréé par §f§lTryliom§e et §f§lMarie";
	public ModeType mode = ModeType.Player;
	public Rank rang;
	public Necklace necklace;
	public int niveau = 1;
	public String prefixCmd = "..";
	public int xp = 1;
	public int xpMax = 300;
	public boolean achievementHelp = false;
	public boolean renderOn = false;
	public int ame = 0;
	public double bonus = 0;
	public String name = "";
	public int n = 0;
	public FontRenderer NekoFont;
	public Timer time = new Timer(1000, new ch());
	public String rec = " ";
	public double chance = 0;
	public int lot = 0;
	public double tempBonus = 0;
	public String prevVer = null;
	public boolean animation = true;
	public static OnlyRpgManager onlyrpg;
	public String ver = "";
	public String changelog = "";
	public boolean firstServDisplay = true;
	// Doesn't check update version if true
	public boolean develop = false;
	
	public void startClient() {
		
		time.start();
		
		NekoFont = new FontRenderer(mc.gameSettings, new ResourceLocation("neko/font/ascii.png"), mc.renderEngine,
				false);
		if (mc.gameSettings.language != null) {
			mc.fontRendererObj.setUnicodeFlag(mc.isUnicode());
			mc.fontRendererObj.setBidiFlag(mc.mcLanguageManager.isCurrentLanguageBidirectional());
		}
		mc.mcResourceManager.registerReloadListener(NekoFont);
		
		n = Utils.getRandInt(13);
		if(Utils.isHalloween() == true) {
			Display.setTitle("THIS IS HALLOWEEN HALLOWEEN HALLOWEEN ! Meow!");
		} else {
			switch (n) {
			case 0:
				Display.setTitle("Tu joues à la dernière version de Neko :3");
				break;
			case 1:
				Display.setTitle("Waw quel joueur expérimenté :o");
				break;
			case 2:
				Display.setTitle("Kaboom !");
				break;
			case 3:
				while (n == 3) {
					try {
						Display.setTitle(Utils.getNyah());
						n = 0;
					} catch (Exception ex) {
						Display.setTitle("Que c'est cringe..");
					}
				}
				break;
			case 4:
				Display.setTitle("Ne touche pas à ça ! NYAAAAAAH");
				break;
			case 5:
				Display.setTitle("Ce jeu est si malsain...");
				break;
			case 6:
				Display.setTitle("Coucou petit neko :3");
				break;
			case 7:
				Display.setTitle("C'est communautaire.");
				break;
			case 8:
				Display.setTitle("Je suis un neko tout innocent :3");
				break;
			case 9:
				Display.setTitle("Je suis un vilain neko ;3");
				break;
			case 10:
				Display.setTitle("Ne viens pas me fouetter dans ma tente !");
				break;
			case 11:
				Display.setTitle("Redresse la tout de suite !");
				break;
			case 12:
				Display.setTitle("Montre moi tout ton potenciel jeune Lycan !");
				break;
			case 13:
				Display.setTitle("Tu t'engraisses bien dans ce domaine >w<");
				break;
			}
		}
		name = CLIENT_NAME + "/vanilla";
		
	}

	public static final Client getNeko() {
		return Neko;
	}

}

class ch implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent arg0) {
		Minecraft mc = Minecraft.getMinecraft();
		Client neko = Client.getNeko();			
		
		if (mc.thePlayer != null && Utils.verif == null) {
			// Random quest
			if (Utils.getRandInt(100)==1 && (QuestManager.getQM().getCurrent()==null && !QuestManager.getQM().isHasBegin())) {
				Utils.getRandQuest();
				Utils.addChat2("§aNouveau défi ! (Voir ici)", neko.prefixCmd+"startquest", "§7Défi: "+QuestManager.getQM().getCurrent().getDesc()+"\n§aCliquez pour accepter le défi (Activer le cheat ou la commande que vous pensez être la bonne)", false, Chat.Click);
			}
		}

	}

}
