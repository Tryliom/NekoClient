package neko.utils;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.UUID;
import java.util.Vector;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;

import com.mojang.authlib.GameProfile;

import neko.Client;
import neko.guicheat.clickgui.util.SettingsUtil;
import neko.lock.Lock;
import neko.manager.LockManager;
import neko.manager.ModuleManager;
import neko.manager.QuestManager;
import neko.module.Category;
import neko.module.Module;
import neko.module.modules.combat.AutoClic;
import neko.module.modules.combat.AutoPot;
import neko.module.modules.combat.Autosoup;
import neko.module.modules.combat.BowAimbot;
import neko.module.modules.combat.ClickAim;
import neko.module.modules.combat.Fastbow;
import neko.module.modules.combat.KillAura;
import neko.module.modules.combat.Reach;
import neko.module.modules.combat.Regen;
import neko.module.modules.combat.SmoothAim;
import neko.module.modules.combat.Trigger;
import neko.module.modules.hide.Friends;
import neko.module.modules.hide.Lot;
import neko.module.modules.misc.Antiafk;
import neko.module.modules.misc.AutoCmd;
import neko.module.modules.misc.AutoMLG;
import neko.module.modules.misc.CallCmd;
import neko.module.modules.misc.Crasher;
import neko.module.modules.misc.Nameprotect;
import neko.module.modules.misc.Phase;
import neko.module.modules.misc.Register;
import neko.module.modules.misc.Timer;
import neko.module.modules.movements.Dolphin;
import neko.module.modules.movements.Flight;
import neko.module.modules.movements.Freecam;
import neko.module.modules.movements.Glide;
import neko.module.modules.movements.Highjump;
import neko.module.modules.movements.Longjump;
import neko.module.modules.movements.NoClip;
import neko.module.modules.movements.Speed709;
import neko.module.modules.movements.Step;
import neko.module.modules.params.HUD;
import neko.module.modules.player.Autoarmor;
import neko.module.modules.player.Build;
import neko.module.modules.player.Cheststealer;
import neko.module.modules.player.Fasteat;
import neko.module.modules.player.Fire;
import neko.module.modules.player.Nuker;
import neko.module.modules.player.PushUp;
import neko.module.modules.render.ChestESP;
import neko.module.modules.render.ItemESP;
import neko.module.modules.render.NekoChat;
import neko.module.modules.render.Paint;
import neko.module.modules.render.Power;
import neko.module.modules.render.Radar;
import neko.module.modules.render.Search;
import neko.module.modules.render.Tracers;
import neko.module.modules.render.Wallhack;
import neko.module.modules.render.Water;
import neko.module.modules.render.WorldTime;
import neko.module.modules.special.DropShit;
import neko.module.modules.special.FireTrail;
import neko.module.modules.special.ForceTP;
import neko.module.modules.special.Likaotique;
import neko.module.modules.special.Magnet;
import neko.module.modules.special.Near;
import neko.module.modules.special.PunKeel;
import neko.module.modules.special.Pyro;
import neko.module.modules.special.Reflect;
import neko.module.modules.special.TpBack;
import neko.module.modules.special.VanillaTp;
import neko.module.other.Active;
import neko.module.other.Bloc;
import neko.module.other.DiscThread;
import neko.module.other.Event;
import neko.module.other.PyroThread;
import neko.module.other.Rank;
import neko.module.other.RmRank;
import neko.module.other.Trade;
import neko.module.other.enums.Chat;
import neko.module.other.enums.Form;
import neko.module.other.enums.Rate;
import neko.module.other.enums.SpeedEnum;
import net.mcleaks.Callback;
import net.mcleaks.MCLeaks;
import net.mcleaks.ModApi;
import net.mcleaks.RedeemResponse;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiNewChat;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.multiplayer.ServerAddress;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.network.NetHandlerLoginClient;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.EnumConnectionState;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.handshake.client.C00Handshake;
import net.minecraft.network.login.client.C00PacketLoginStart;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.network.play.client.C03PacketPlayer.C04PacketPlayerPosition;
import net.minecraft.network.play.client.C09PacketHeldItemChange;
import net.minecraft.network.play.client.C10PacketCreativeInventoryAction;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Session;
import net.minecraft.world.WorldSettings.GameType;

public class ChatUtils {
	Minecraft mc = Minecraft.getMinecraft();
	Client var = Client.getNeko();
	String var3;
	String args[];
	int xp = 0;
	String error;
	String err = "??c??lErreur, valeur incorrecte";
	String discord = "??dhttps://discord.gg/Dt4npbR";
	
	public ChatUtils() {}
	
	public void doCommand(String var1) {	
		var3 = var1;
		if (Utils.xptime==60) {
			xp = Utils.getRandInt(5);
			Utils.xptime=0;
		}
		error = "??c??lErreur: Essayez "+var.prefixCmd+"help";
		
		if (var.onlyrpg.isActive()) {
			boolean valid=false;
			for (String cmd : var.onlyrpg.getCmd()) {
				if (var3.startsWith(var.prefixCmd+cmd) || !var3.startsWith(var.prefixCmd)) {
					valid=true;
				}
			}
			if (!valid) {
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
				mc.displayGuiScreen((GuiScreen)null);
				return;
			}
		}
		
		if (var3.startsWith(var.prefixCmd) && Utils.verif==null) {
			// New command manager
			Utils.onCommand(var3.replaceFirst(var.prefixCmd, ""));
			
			// Old code
			args = var3.split(" ");

			if (!var3.equalsIgnoreCase(var.prefixCmd+"startquest"))
				Utils.checkQuest(var3);
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"help")) {
				// Afficher la liste des commandes non simplifi??es
				if (args.length==1) {
					Utils.displayHelp(1);						
					Utils.checkXp(xp);
					mc.ingameGUI.getChatGUI().addToSentMessages(var3);
					int rand = (int) Math.round(Math.random()*1000);
					if (!var.achievementHelp) {
						var.achievementHelp=true;
						Utils.addChat("??dAchievement Help get !??b +"+rand+"xp !");								
						Utils.checkXp(rand);
					}

				} else if (args[1].equalsIgnoreCase("disc")) {
					Utils.addChat(Utils.sep);
					Utils.addChat2("??6"+var.prefixCmd+"Disc <String>", var.prefixCmd+"disc ", "??7Joue le disc choisit", false, Chat.Summon);
					Utils.addChat2("??6"+var.prefixCmd+"Disc <String> <Double>", var.prefixCmd+"disc ", "??7Joue le disc et modifie le volume sur la distance", false, Chat.Summon);
					Utils.checkXp(xp);
					mc.ingameGUI.getChatGUI().addToSentMessages(var3);
				} else if (args[1].equalsIgnoreCase("magnet")) {
					Utils.addChat(Utils.sep);
					Utils.addChat2("??6"+var.prefixCmd+"Magnet classic", var.prefixCmd+"magnet classic", "??7Active/d??sactive le tp par diagonale", false, Chat.Summon);
					Utils.addChat2("??6"+var.prefixCmd+"Magnet Mode <Single:Multi>", var.prefixCmd+"magnet mode ", "??7Choisis entre:\n??7Prendre les items un ?? un (Envoie moins de paquets)\n??7Prend tous les items en m??me temps (Envoie plus de paquets)", false, Chat.Summon);
					Utils.checkXp(xp);
					mc.ingameGUI.getChatGUI().addToSentMessages(var3);
				} else if (args[1].equalsIgnoreCase("radar")) {
					Utils.addChat(Utils.sep);
					Utils.addChat2("??6"+var.prefixCmd+"radar map/minimap", var.prefixCmd+"radar mp", "??7Affiche le radar sous forme de Map", false, Chat.Summon);
					Utils.addChat2("??6"+var.prefixCmd+"radar text/textuel", var.prefixCmd+"radar text", "??7Affiche le radar sous forme textuel", false, Chat.Summon);
					Utils.addChat2("??6"+var.prefixCmd+"radar fr/friends/friend", var.prefixCmd+"radar fr", "??7Affiche les amis dans les radars (Vert)", false, Chat.Summon);
					Utils.addChat2("??6"+var.prefixCmd+"radar joueurs/joueurs/player", var.prefixCmd+"radar joueurs", "??7Affiche les joueurs non ami dans le radar (Rouge)", false, Chat.Summon);
					Utils.addChat2("??6"+var.prefixCmd+"radar mobs/monstres", var.prefixCmd+"radar mobs", "??7Affiche les mobs actifs (monstres) dans le radar (Orange)", false, Chat.Summon);
					Utils.addChat2("??6"+var.prefixCmd+"radar animals/animaux", var.prefixCmd+"radar animals", "??7Affiche les mobs passifs (animaux) dans le radar (Bleu)", false, Chat.Summon);
					Utils.addChat2("??6"+var.prefixCmd+"radar golem", var.prefixCmd+"radar golem", "??7Affiche les Golems dans le radar (Gris)", false, Chat.Summon);
					Utils.addChat2("??6"+var.prefixCmd+"radar npc/pnj", var.prefixCmd+"radar npc", "??7Affiche les NPC / PNJ dans le radar (Rose)", false, Chat.Summon);
					Utils.checkXp(xp);
					mc.ingameGUI.getChatGUI().addToSentMessages(var3);
				} else if (args[1].equalsIgnoreCase("arraylist")) {
					Utils.addChat(Utils.sep);
					Utils.addChat2("??6"+var.prefixCmd+"ArrayList UniColor <Red> <Green> <Blue> (0-255)", var.prefixCmd+"arraylist unicolor 100 100 100", "??7Modifie la couleur du mode UniColor de l'ArrayList", false, Chat.Summon);
					Utils.addChat2("??6"+var.prefixCmd+"ArrayList Ordre <Alphab??tique:Taille>", var.prefixCmd+"ArrayList ordre Alphab??tique", "??7Modifie l'ordre de l'ArrayList (Alphab??tique ou Taille de noms)", false, Chat.Summon);
					Utils.addChat2("??6"+var.prefixCmd+"ArrayList Rangement <Module:M??langer>", var.prefixCmd+"ArrayList rangement M??langer", "??7Modifie le type de rangement de l'ArrayList (Par modules ou m??langer)", false, Chat.Summon);
					Utils.addChat2("??6"+var.prefixCmd+"ArrayList Inverser <Oui:Non>", var.prefixCmd+"ArrayList Inverser non", "??7Inverse l'ArrayList ou non (Punkeel-HUD-Autonyah) deviendrait (Autonyah-HUD-Punkeel)", false, Chat.Summon);
					Utils.addChat2("??6"+var.prefixCmd+"ArrayList Box <Neko:Name:Sans>", var.prefixCmd+"ArrayList box name", "??7Affiche ou non des Shadow Box derri??re les noms de Modules. (Neko=Grande box, Name=Box par modules, sans=sans box)", false, Chat.Summon);
					Utils.addChat2("??6"+var.prefixCmd+"ArrayList color <Rainbow:UniColor:Basique>", var.prefixCmd+"ArrayList color Basique", "??7Modifie la couleur utilis??e dans l'arraylist (Rainbow, UniColor ou Basique)", false, Chat.Summon);
					Utils.checkXp(xp);
					mc.ingameGUI.getChatGUI().addToSentMessages(var3);
				} else if (args[1].equalsIgnoreCase("clickaim")) {
					Utils.addChat(Utils.sep);
					Utils.addChat2("??6"+var.prefixCmd+"ClickAim multiaura", var.prefixCmd+"clickaim ", "??7Change le mode multiaura au singleaura et inversement", false, Chat.Summon);
					Utils.addChat2("??6"+var.prefixCmd+"ClickAim <Double>", var.prefixCmd+"clickaim", "??7Modifie la port??e du ClickAim", false, Chat.Summon);
					Utils.checkXp(xp);
					mc.ingameGUI.getChatGUI().addToSentMessages(var3);
				} else if (args[1].equalsIgnoreCase("firetrail")) {
					Utils.addChat(Utils.sep);
					Utils.addChat2("??6"+var.prefixCmd+"FireTrail large", var.prefixCmd+"firetrail large", "??7Change l'??paisseur de la train??e de plus large ?? plus fine", false, Chat.Summon);
					Utils.checkXp(xp);
					mc.ingameGUI.getChatGUI().addToSentMessages(var3);
				} else if (args[1].equalsIgnoreCase("Build")) {
					Utils.addChat(Utils.sep);
					Utils.addChat2("??6"+var.prefixCmd+"Build", var.prefixCmd+"Build", "??7Permet de poser des blocs format 3x3 autour de vous, ces options sont disponibles", false, Chat.Summon);
					Utils.addChat2("??6"+var.prefixCmd+"Build Down", var.prefixCmd+"Build down", "??7Pose les blocs format 3x3 en dessous de vous", false, Chat.Summon);
					Utils.addChat2("??6"+var.prefixCmd+"Build Up", var.prefixCmd+"Build up", "??7Pose les blocs format 3x3 au dessus de vous (1 bloc d'espace au dessus de votre t??te)", false, Chat.Summon);
					Utils.addChat2("??6"+var.prefixCmd+"Build Wall", var.prefixCmd+"Build wall", "??7Cr???? un mur devant vous comme une protection 3x3", false, Chat.Summon);
					Utils.addChat2("??6"+var.prefixCmd+"Build Sneak", var.prefixCmd+"Build sneak", "??7Pour le Wall: Si activ??, cr???? un mur quand vous ??tes en sneak, si d??sactiv??, cr???? un mur en sneak ou non", false, Chat.Summon);
					Utils.checkXp(xp);
					mc.ingameGUI.getChatGUI().addToSentMessages(var3);
				} else if (args[1].equalsIgnoreCase("phase")) {
					Utils.addChat(Utils.sep);
					Utils.addChat2("??6"+var.prefixCmd+"Phase vphase", var.prefixCmd+"phase vphase", "??7Change le phase pour essayer de passer ?? travers le sol", false, Chat.Summon);
					Utils.checkXp(xp);
					mc.ingameGUI.getChatGUI().addToSentMessages(var3);
				} else if (args[1].equalsIgnoreCase("nametag")) {
					Utils.addChat(Utils.sep);
					Utils.addChat2("??6"+var.prefixCmd+"Nametag <Double>", var.prefixCmd+"nametag ", "??7Change la taille des pseudos, de base sur 6", false, Chat.Summon);
					Utils.checkXp(xp);
					mc.ingameGUI.getChatGUI().addToSentMessages(var3);
				} else if (args[1].equalsIgnoreCase("prefix")) {
					Utils.addChat(Utils.sep);
					Utils.addChat2("??6"+var.prefixCmd+"Prefix <String>", var.prefixCmd+"prefix ", "??7Change le preifx des commandes", false, Chat.Summon);
					Utils.checkXp(xp);
					mc.ingameGUI.getChatGUI().addToSentMessages(var3);
				} else if (args[1].equalsIgnoreCase("tppos")) {
					Utils.addChat(Utils.sep);
					Utils.addChat2("??6"+var.prefixCmd+"Tppos <X> <Y> <Z>", var.prefixCmd+"tppos ", "??7T??l??porte par voie des air aux coordonn??es indiqu??es", false, Chat.Summon);
					Utils.checkXp(xp);
					mc.ingameGUI.getChatGUI().addToSentMessages(var3);
				} else if (args[1].equalsIgnoreCase("phantom")) {
					Utils.addChat(Utils.sep);
					Utils.addChat2("??6"+var.prefixCmd+"Phantom <X> <Y> <Z>", var.prefixCmd+"phantom ", "??7T??l??porte par voie de la terre aux coordonn??es indiqu??es", false, Chat.Summon);
					Utils.addChat2("??6"+var.prefixCmd+"Phantom <[X,Y,Z]>", var.prefixCmd+"phantom ", "??7T??l??porte par voie de la terre aux coordonn??es indiqu??es au format du Near copy", false, Chat.Summon);
					Utils.checkXp(xp);
					mc.ingameGUI.getChatGUI().addToSentMessages(var3);
				} else if (args[1].equalsIgnoreCase("velocity") || args[1].equalsIgnoreCase("velo")) {
					Utils.addChat(Utils.sep);
					Utils.addChat2("??6"+var.prefixCmd+"Velocity <Double>", var.prefixCmd+"velo ", "??7Change le coefficient de knockback en horizontal et vertical de base", false, Chat.Summon);
					Utils.addChat2("??6"+var.prefixCmd+"Velocity <Horizontal:Hor> <Double>", var.prefixCmd+"velo hor ", "??7Change le coefficient de knockback en horizontal", false, Chat.Summon);
					Utils.addChat2("??6"+var.prefixCmd+"Velocity <Vertical:Ver> <Double>", var.prefixCmd+"velo ver ", "??7Change le coefficient de knockback en vertical", false, Chat.Summon);
					Utils.checkXp(xp);
					mc.ingameGUI.getChatGUI().addToSentMessages(var3);
				} else if (args[1].equalsIgnoreCase("friend") || args[1].equalsIgnoreCase("fr")) {
					Utils.addChat(Utils.sep);
					Utils.addChat2("??6"+var.prefixCmd+"Friend <Player>", var.prefixCmd+"friend ", "??7Ajoute/supprime un joueur de votre liste", false, Chat.Summon);
					Utils.addChat2("??6"+var.prefixCmd+"Friend list", var.prefixCmd+"friend list", "??7Affiche la liste de vos amis", false, Chat.Summon);
					Utils.addChat2("??6"+var.prefixCmd+"Friend clear", var.prefixCmd+"friend clear", "??7Vide votre liste d'amis", false, Chat.Summon);
					Utils.addChat2("??6"+var.prefixCmd+"Friend radius <Double>", var.prefixCmd+"friend radius ", "??7Ajoute/supprime les joueurs dans un rayon de x blocs de votre liste", false, Chat.Summon);
					Utils.addChat2("??6"+var.prefixCmd+"Friend team", var.prefixCmd+"ft", "??7Active/d??sactive l'ajout automatique d'amis qui sont dans votre team", false, Chat.Summon);
					Utils.checkXp(xp);
					mc.ingameGUI.getChatGUI().addToSentMessages(var3);
				} else if (args[1].equalsIgnoreCase("smoothaim")) {
					Utils.addChat(Utils.sep);
					Utils.addChat2("??6"+var.prefixCmd+"SmoothAim range <Double>", var.prefixCmd+"smoothaim range ", "??7Change la port??e du SmoothAim", false, Chat.Summon);
					Utils.addChat2("??6"+var.prefixCmd+"SmoothAim fov <Double>", var.prefixCmd+"smoothaim fov ", "??7Change le fov du SmoothAim", false, Chat.Summon);
					Utils.addChat2("??6"+var.prefixCmd+"SmoothAim speed <Double>", var.prefixCmd+"smoothaim speed ", "??7Change la vitesse de la t??te pour le SmoothAim", false, Chat.Summon);
					Utils.checkXp(xp);
					mc.ingameGUI.getChatGUI().addToSentMessages(var3);
				} else if (args[1].equalsIgnoreCase("autosoup")) {
					Utils.addChat(Utils.sep);
					Utils.addChat2("??6"+var.prefixCmd+"Autosoup heal <Int>", var.prefixCmd+"autosoup heal ", "??7Change le seuil de vie", false, Chat.Summon);
					Utils.addChat2("??6"+var.prefixCmd+"Autosoup drop", var.prefixCmd+"autosoup drop", "??7Drop ou non les bols vides de l'inventaire", false, Chat.Summon);
					Utils.checkXp(xp);
					mc.ingameGUI.getChatGUI().addToSentMessages(var3);
				} else if (args[1].equalsIgnoreCase("autonyah")) {
					Utils.addChat(Utils.sep);
					Utils.addChat2("??6"+var.prefixCmd+"Autonyah prefix <String>", var.prefixCmd+"autonyah prefix ", "??7Change le prefix avant les Nyah", false, Chat.Summon);
					Utils.addChat2("??6"+var.prefixCmd+"Autonyah speed <Double>", var.prefixCmd+"Autonyah speed ", "??7Change la vitesse de Nyah par secondes", false, Chat.Summon);
					Utils.checkXp(xp);
					mc.ingameGUI.getChatGUI().addToSentMessages(var3);
				} else if (args[1].equalsIgnoreCase("likaotique") || args[1].equalsIgnoreCase("lik")) {
					Utils.addChat(Utils.sep);
					Utils.addChat2("??6"+var.prefixCmd+"Likaotique delay <Secondes>", var.prefixCmd+"lik delay ", "??7Change le delais entre les tp", false, Chat.Summon);
					Utils.addChat2("??6"+var.prefixCmd+"Likaotique radius <Int>", var.prefixCmd+"Likaotique radius ", "??7Change l'aura autour du joueur maximal", false, Chat.Summon);
					Utils.addChat2("??6"+var.prefixCmd+"Likaotique safe", var.prefixCmd+"Likaotique safe", "??7Option qui tp le faux joueur uniquement lorsqu'un autre joueur est proche de plus de 4.5 blocs", false, Chat.Summon);
					Utils.checkXp(xp);
					mc.ingameGUI.getChatGUI().addToSentMessages(var3);
				} else if (args[1].equalsIgnoreCase("Nameprotect") || args[1].equalsIgnoreCase("np")) {
					Utils.addChat(Utils.sep);
					Utils.addChat2("??6"+var.prefixCmd+"Nameprotect name <nom1> <nom2>", var.prefixCmd+"np name ", "??7Change le nom1 en nom2 dans le chat et tab\n??7Pour les charact??res du genre: . et ?, il faut les escape, c'est ?? dire mettre un \\ avant", false, Chat.Summon);
					Utils.addChat2("??6"+var.prefixCmd+"Nameprotect delete <nom1>", var.prefixCmd+"np del ", "??7Supprime le nom1 existant", false, Chat.Summon);
					Utils.checkXp(xp);
					mc.ingameGUI.getChatGUI().addToSentMessages(var3);
				} else if (args[1].equalsIgnoreCase("glide")) {
					Utils.addChat(Utils.sep);
					Utils.addChat2("??6"+var.prefixCmd+"Glide <Double>", var.prefixCmd+"glide ", "??7Change la vitesse du Glide, de base ?? -0.125", false, Chat.Summon);
					Utils.checkXp(xp);
					mc.ingameGUI.getChatGUI().addToSentMessages(var3);
				} else if (args[1].equalsIgnoreCase("reach")) {
					Utils.addChat(Utils.sep);
					Utils.addChat2("??6Reach pvp [info]", var.prefixCmd+"", "??7Toutes les sous commandes ci-dessous sont d??bloqu??es avec la reach pvp.\n??7Si vous voulez en savoir plus, veuillez vous renseignez sur le Guide Neko", true, Chat.Summon);
					Utils.addChat2("??6"+var.prefixCmd+"Reach <Double>", var.prefixCmd+"reach ", "??7Change la distance de la reach", false, Chat.Summon);
					Utils.addChat2("??6"+var.prefixCmd+"Reach pvp", var.prefixCmd+"reach pvp", "??7Permet de frapper des entit??s et joueurs ?? distance.\n??7La Reach aimbot vous aidera ?? mieux viser.", false, Chat.Summon);
					Utils.addChat2("??6"+var.prefixCmd+"Reach bloc", var.prefixCmd+"reach bloc", "??7Clic droit: Pose des blocs ?? distance.\n??7Clic gauche: Si rien dans la main, vous t??l??porte sur le bloc taper puis reviens.\n??7Id??al pour ramasser du stuff perdUtils.", false, Chat.Summon);
					Utils.addChat2("??6"+var.prefixCmd+"Reach aimbot", var.prefixCmd+"reach aimbot", "??7Selon le fov ci-dessous, de frapper une cible si elle se trouve dedans, si on est un peu ?? c??t??", false, Chat.Summon);
					Utils.addChat2("??6"+var.prefixCmd+"Reach fov <Double>", var.prefixCmd+"reach fov ", "??7Change le fov pour la Reach aimbot", false, Chat.Summon);
					Utils.addChat2("??6"+var.prefixCmd+"Reach tnt", var.prefixCmd+"reach tnt", "??7Permet de poser et allumer de la tnt ?? distance avec le clic droit.\n??7Le Reach tnt <Mode> permet de changer de mode de posage.\n??7Fa??tes Reach tnt list pour la liste des modes", false, Chat.Summon);
					Utils.addChat2("??6"+var.prefixCmd+"Reach multiaura", var.prefixCmd+"reach multiaura", "??7Permet de frapper toutes les entit??s autour du joueur frapp?? ?? distance", false, Chat.Summon);
					Utils.addChat2("??6"+var.prefixCmd+"Reach knockback", var.prefixCmd+"reach kb", "??7Permet d'appliquer un knockback au joueur tap?? dans la direction o?? vous frappezu", false, Chat.Summon);
					Utils.checkXp(xp);
					mc.ingameGUI.getChatGUI().addToSentMessages(var3);
				} else if (args[1].equalsIgnoreCase("trigger")) {
					Utils.addChat(Utils.sep);
					Utils.addChat2("??6"+var.prefixCmd+"Trigger <Double>", var.prefixCmd+"trigger ", "??7Change la port??e du Trigger", false, Chat.Summon);
					Utils.addChat2("??6"+var.prefixCmd+"Trigger cps <Int>", var.prefixCmd+"trigger cps ", "??7Change les coups/secondes du Trigger", false, Chat.Summon);
					Utils.addChat2("??6"+var.prefixCmd+"Trigger random", var.prefixCmd+"trigger random", "??7Active ou non les cps al??atoire pour le Trigger", false, Chat.Summon);
					Utils.checkXp(xp);
					mc.ingameGUI.getChatGUI().addToSentMessages(var3);
				} else if (args[1].equalsIgnoreCase("gui")) {
					Utils.addChat(Utils.sep);
					Utils.addChat2("??6"+var.prefixCmd+"Gui color <R> <G> <B> <Alpha> (0-255)", var.prefixCmd+"gui color ", "??7Change la couleur du fond du Gui", false, Chat.Summon);
					Utils.addChat2("??6"+var.prefixCmd+"Gui font <R> <G> <B> <Alpha> (0-255)", var.prefixCmd+"gui font ", "??7Change la couleur de la police du Gui", false, Chat.Summon);
					Utils.checkXp(xp);
					mc.ingameGUI.getChatGUI().addToSentMessages(var3);
				} else if (args[1].equalsIgnoreCase("autocmd")) {
					Utils.addChat(Utils.sep);
					Utils.addChat2("??6"+var.prefixCmd+"AutoCmd delay <secondes>", var.prefixCmd+"autocmd delay ", "??7D??fini le nombre de seconde entre chaque commandes", false, Chat.Summon);
					Utils.addChat2("??6"+var.prefixCmd+"AutoCmd cmd <Commande>", var.prefixCmd+"autocmd cmd ", "??7D??fini la commande ?? executer toutes les x secondes", false, Chat.Summon);
					Utils.checkXp(xp);
					mc.ingameGUI.getChatGUI().addToSentMessages(var3);
				} else if (args[1].equalsIgnoreCase("chest") || args[1].equalsIgnoreCase("chestesp")) {
					Utils.addChat(Utils.sep);
					Utils.addChat2("??6"+var.prefixCmd+"Chest dispenser", var.prefixCmd+"chest dispenser", "??7Affiche les dispensers et dropper avec le ChestESP", false, Chat.Summon);					
					Utils.addChat2("??6"+var.prefixCmd+"Chest tracers", var.prefixCmd+"chest tracers", "??7Affiche un tracers jusqu'aux container du ChestESP", false, Chat.Summon);
					Utils.checkXp(xp);
					mc.ingameGUI.getChatGUI().addToSentMessages(var3);
				} else if (args[1].equalsIgnoreCase("near")) {
					Utils.addChat(Utils.sep);
					Utils.addChat2("??6"+var.prefixCmd+"Near list", var.prefixCmd+"near list", "??7Affiche les joueurs avec leurs coordonn??es et distance de vous derni??rement enregistr??s", false, Chat.Summon);
					Utils.addChat2("??6"+var.prefixCmd+"Near list <Int>", var.prefixCmd+"near list ", "??7Affiche les joueurs avec leurs coordonn??es et distance de vous derni??rement \n??7enregistr??s uniquement si ?? une distance plus grande que celle sp??cifi??e", false, Chat.Summon);
					Utils.addChat2("??6"+var.prefixCmd+"Near copy", var.prefixCmd+"near copy", "??7Copie la liste des joueurs du Near avec leurs coordonn??es", false, Chat.Summon);
					Utils.addChat2("??6"+var.prefixCmd+"Near icopy", var.prefixCmd+"near icopy", "??7Copie la liste des joueurs du Near avec leurs coordonn??es en respectant la r??gle du ignore", false, Chat.Summon);
					Utils.addChat2("??6"+var.prefixCmd+"Near copy <player>", var.prefixCmd+"near copy ", "??7Copie les coordonn??es du joueur sp??cifi?? du Near", false, Chat.Summon);
					Utils.addChat2("??6"+var.prefixCmd+"Near say", var.prefixCmd+"near say", "??7Dis dans le chat avec une phrase la position de x joueur et sa vie", false, Chat.Summon);
					Utils.addChat2("??6"+var.prefixCmd+"Near say <player>", var.prefixCmd+"near say ", "??7Dis dans le chat avec une phrase la position du joueur sp??cifi??", false, Chat.Summon);
					Utils.addChat2("??6"+var.prefixCmd+"Near ignore <Point de d??part [X,Y,Z]> <Radius>", var.prefixCmd+"near ignore ", "??7Param??tres pour le near qui permet\n??7 d'ignorer les messages de coordonn??es de joueurs qui sont d??tect?? autour d'une certaine position, comme le spawn.\n??7Exemple de commande: Near ignore [0,70,0] 300", false, Chat.Summon);
					Utils.addChat2("??6"+var.prefixCmd+"Near noname", var.prefixCmd+"near noname", "??7Si activ??, quand on utilise le near copy, ??a ne copie pas le nom du joueur", false, Chat.Summon);
					Utils.checkXp(xp);
					mc.ingameGUI.getChatGUI().addToSentMessages(var3);
				} else if (args[1].equalsIgnoreCase("forcetp") || args[1].equalsIgnoreCase("ftp")) {
					Utils.addChat(Utils.sep);
					Utils.addChat2("??6"+var.prefixCmd+"ForceTP <[X,Y,Z]>", var.prefixCmd+"ftp ", "??7D??fini la cible ?? laquelle se TP", false, Chat.Summon);
					Utils.addChat2("??6"+var.prefixCmd+"ForceTP YMax", var.prefixCmd+"ftp ymax", "??7Force la hauteur en Y ?? ??tre ?? 255 qu'importe la hauteur Y \n??7sp??cifi?? dans la target", false, Chat.Summon);
					Utils.checkXp(xp);
					mc.ingameGUI.getChatGUI().addToSentMessages(var3);
				} else if (args[1].equalsIgnoreCase("nametag")) {
					Utils.addChat(Utils.sep);
					Utils.addChat2("??6"+var.prefixCmd+"Nametag <Taille>", var.prefixCmd+"nametag ", "??7D??fini la taille des nametags en nombre entier", false, Chat.Summon);
					Utils.checkXp(xp);
					mc.ingameGUI.getChatGUI().addToSentMessages(var3);
				} else if (args[1].equalsIgnoreCase("highjump")) {
					Utils.addChat(Utils.sep);
					Utils.addChat2("??6"+var.prefixCmd+"HighJump <Hauteur>", var.prefixCmd+"highjump ", "??7D??fini la hauteur du HighJump", false, Chat.Summon);
					Utils.checkXp(xp);
					mc.ingameGUI.getChatGUI().addToSentMessages(var3);
				} else if (args[1].equalsIgnoreCase("callcmd")) {
					Utils.addChat(Utils.sep);
					Utils.addChat2("??6"+var.prefixCmd+"CallCmd player <Player>", var.prefixCmd+"callcmd player ", "??7Ajoute un joueur ?? la liste", false, Chat.Summon);
					Utils.addChat2("??6"+var.prefixCmd+"CallCmd cmd <Cmd>", var.prefixCmd+"callcmd cmd ", "??7Change la commande faite si un joueur est d??tect??", false, Chat.Summon);
					Utils.addChat2("??6"+var.prefixCmd+"CallCmd list", var.prefixCmd+"callcmd list", "??7Affiche la liste des joueurs et la commande faite", false, Chat.Summon);
					Utils.addChat2("??6"+var.prefixCmd+"CallCmd clear", var.prefixCmd+"callcmd clear", "??7Clear la liste des joueurs", false, Chat.Summon);
					Utils.checkXp(xp);
					mc.ingameGUI.getChatGUI().addToSentMessages(var3);
				} else if (args[1].equalsIgnoreCase("nekochat") || args[1].equalsIgnoreCase("chat")) {
					Utils.addChat(Utils.sep);
					Utils.addChat2("??6"+var.prefixCmd+"Chat color <R> <G> <B> <Alpha optionel>", var.prefixCmd+"chat color ", "??7Change la couleur du chat en fond", false, Chat.Summon);
					Utils.addChat2("??6"+var.prefixCmd+"Chat height <Double>", var.prefixCmd+"chat height ", "??7Change la hauteur du chat", false, Chat.Summon);
					Utils.addChat2("??6"+var.prefixCmd+"Chat width <Double>", var.prefixCmd+"chat width ", "??7Change la largeur du chat", false, Chat.Summon);
					Utils.checkXp(xp);
					mc.ingameGUI.getChatGUI().addToSentMessages(var3);
				} else if (args[1].equalsIgnoreCase("proxy")) {
					Utils.addChat(Utils.sep);
					Utils.addChat2("??6"+var.prefixCmd+"Proxy <HostIP> <Port>", var.prefixCmd+"proxy ", "??7Vous connecte ?? un proxy", false, Chat.Summon);
					Utils.addChat2("??6"+var.prefixCmd+"Proxy reset", var.prefixCmd+"proxy reset ", "??7Vous d??connecte de votre proxy", false, Chat.Summon);
					Utils.checkXp(xp);
					mc.ingameGUI.getChatGUI().addToSentMessages(var3);
				} else if (args[1].equalsIgnoreCase("fastbow") || args[1].equalsIgnoreCase("fb")) {
					Utils.addChat(Utils.sep);
					Utils.addChat2("??6"+var.prefixCmd+"Fastbow NoBow", var.prefixCmd+"fastbow nobow", "??7Permet de tirer des fl??ches si l'on poss??de au moins un arc + fl??che dans l'inventaire.\n??7Pour les joueurs vous gardez votre item actuel dans la main en tirant, have fun.", false, Chat.Summon);
					Utils.addChat2("??6"+var.prefixCmd+"Fastbow Packet <Int>", var.prefixCmd+"fastbow packet ", "??7Modifie le nombre de paquet envoy?? par le fastbow par fl??che, de base ?? 20", false, Chat.Summon);
					Utils.checkXp(xp);
					mc.ingameGUI.getChatGUI().addToSentMessages(var3);
				} else if (args[1].equalsIgnoreCase("punkeel") || args[1].equalsIgnoreCase("pk")) {
					Utils.addChat(Utils.sep);
					Utils.addChat2("??6"+var.prefixCmd+"Punkeel Delay <Double>", var.prefixCmd+"punkeel delay ", "??7Modifie le delay entre les tp, par ex 0.5 donne un VRAI lag de 500ms, les joueurs vous voit vous tp tous les 0.5sec", false, Chat.Summon);
					Utils.addChat2("??6"+var.prefixCmd+"Punkeel Attack", var.prefixCmd+"punkeel attack", "??7Fais que quand on frappe le packet d'attaque s'envoie tout de suite au lieu des ms normaux", false, Chat.Summon);
					Utils.addChat2("??6"+var.prefixCmd+"Punkeel Random", var.prefixCmd+"punkeel random", "??7Active/d??sactive le punkeel random", false, Chat.Summon);
					Utils.addChat2("??6"+var.prefixCmd+"Punkeel Random <Delay min> <Delay max>", var.prefixCmd+"punkeel random ", "??7Active/d??sactive le punkeel Random et Met un delay al??atoire entre 2 valeurs min et max entre chaque tp", false, Chat.Summon);
					Utils.checkXp(xp);
					mc.ingameGUI.getChatGUI().addToSentMessages(var3);
				} else if (args[1].equalsIgnoreCase("automlg") || args[1].equalsIgnoreCase("mlg")) {
					Utils.addChat(Utils.sep);
					Utils.addChat2("??6"+var.prefixCmd+"Mlg <Double>", var.prefixCmd+"mlg ", "??7Change la distance de chute minimale", false, Chat.Summon);
					Utils.checkXp(xp);
					mc.ingameGUI.getChatGUI().addToSentMessages(var3);
				} else if (args[1].equalsIgnoreCase("vanillatp") || args[1].equalsIgnoreCase("vtp")) {
					Utils.addChat(Utils.sep);
					Utils.addChat2("??6"+var.prefixCmd+"Vtp air", var.prefixCmd+"vtp air", "??7Permet de pouvoir se t??l??porter au loin sans forc??ment viser un bloc (L'air)", false, Chat.Summon);
					Utils.addChat2("??6"+var.prefixCmd+"Vtp classic", var.prefixCmd+"vtp classic", "??7Force ?? utiliser le mode de tp classique qui est en diagonale au lieu de passer par d'autres endroits", false, Chat.Summon);
					Utils.addChat2("??6"+var.prefixCmd+"Vtp top", var.prefixCmd+"vtp top", "??7Permet quand on vise un bloc de se tp en haut de celui-ci si les blocs au dessus sont pleins (Ex: un mur)", false, Chat.Summon);
					Utils.checkXp(xp);
					mc.ingameGUI.getChatGUI().addToSentMessages(var3);
				} else if (args[1].equalsIgnoreCase("scaffold")) {
					Utils.addChat(Utils.sep);
					Utils.addChat2("??6"+var.prefixCmd+"Scaffold down", var.prefixCmd+"scaffold down", "??7Permet de pouvoir poser les blocs en dessous de vous en escalier descendant", false, Chat.Summon);
					Utils.checkXp(xp);
					mc.ingameGUI.getChatGUI().addToSentMessages(var3);
				} else if (args[1].equalsIgnoreCase("tpback") || args[1].equalsIgnoreCase("tpb")) {
					Utils.addChat(Utils.sep);
					Utils.addChat2("??6"+var.prefixCmd+"Tpback top", var.prefixCmd+"tpback top", "??7Permet de vous t??l??porter sur le plus haut bloc si votre point de tp est bouch??", false, Chat.Summon);
					Utils.addChat2("??6"+var.prefixCmd+"Tpback classic", var.prefixCmd+"tpback classic", "??7Force ?? utiliser le mode de tp classique qui est en diagonale au lieu de passer par d'autres endroits", false, Chat.Summon);
					Utils.addChat2("??6"+var.prefixCmd+"Tpback <Int>", var.prefixCmd+"tpback ", "??7Change le seuil de vie", false, Chat.Summon);
					Utils.addChat2("??6"+var.prefixCmd+"Tpback set:spawn:setspawn", var.prefixCmd+"tpback setspawn", "??7Change le point de tp ?? l?? o?? vous vous trouvez", false, Chat.Summon);
					Utils.addChat2("??6"+var.prefixCmd+"Tpback set:spawn:setspawn <X> <Y> <Z>", var.prefixCmd+"tpback setspawn ", "??7Change le point de tp aux coordonn??es entr??es", false, Chat.Summon);
					Utils.checkXp(xp);
					mc.ingameGUI.getChatGUI().addToSentMessages(var3);
				} else if (args[1].equalsIgnoreCase("limit")) {
					Utils.addChat(Utils.sep);
					Utils.addChat2("??6"+var.prefixCmd+"Limit", var.prefixCmd+"limit", "??7Active/d??sactive le limit.\n??7Limite le nombre de paquets/secondes envoy??s.", false, Chat.Summon);
					Utils.addChat2("??6"+var.prefixCmd+"Limit <Int>", var.prefixCmd+"limit ", "??7Modifie la limite de paquet", false, Chat.Summon);
					Utils.checkXp(xp);
					mc.ingameGUI.getChatGUI().addToSentMessages(var3);
				} else if (args[1].equalsIgnoreCase("reflect")) {
					Utils.addChat(Utils.sep);
					Utils.addChat2("??6"+var.prefixCmd+"Reflect <Double>", var.prefixCmd+"reflect ", "??7Modifie la puissance du reflect, \n??7entre -15 et 15 ??a vous fait rentrer dans le joueur", false, Chat.Summon);
					Utils.checkXp(xp);
					mc.ingameGUI.getChatGUI().addToSentMessages(var3);
				} else if (args[1].equalsIgnoreCase("ping")) {
					Utils.addChat(Utils.sep);
					Utils.addChat2("??6"+var.prefixCmd+"Ping <Int>", var.prefixCmd+"ping ", "??7Change le delai, le lag que vous voulez faire voir pour les autres", false, Chat.Summon);
					Utils.addChat2("??6"+var.prefixCmd+"Ping Random", var.prefixCmd+"ping random", "??7Active les ms al??atoire", false, Chat.Summon);
					Utils.addChat2("??6"+var.prefixCmd+"Ping Freezer", var.prefixCmd+"ping freezer", "??7Freeze le ping actuel", false, Chat.Summon);
					Utils.checkXp(xp);
					mc.ingameGUI.getChatGUI().addToSentMessages(var3);
				} else if (args[1].equalsIgnoreCase("antiafk")) {
					Utils.addChat(Utils.sep);
					Utils.addChat2("??6"+var.prefixCmd+"AntiAfk <Int>", var.prefixCmd+"antiafk ", "??7Modifie le temps entre chaques saut", false, Chat.Summon);
					Utils.checkXp(xp);
					mc.ingameGUI.getChatGUI().addToSentMessages(var3);
				} else if (args[1].equalsIgnoreCase("ka") || args[1].equalsIgnoreCase("killaura")) {
					Utils.addChat(Utils.sep);
					Utils.addChat2("??6"+var.prefixCmd+"Ka mode <Multi:Single>", var.prefixCmd+"ka mode ", "??7Change entre les modes Single et Multi", false, Chat.Summon);
					Utils.addChat2("??6"+var.prefixCmd+"Ka cps <Int>", var.prefixCmd+"ka cps ", "??7Change les coups/secondes du Kill Aura", false, Chat.Summon);
					Utils.addChat2("??6"+var.prefixCmd+"Ka range <Double>", var.prefixCmd+"ka range ", "??7Change la port??e des coups", false, Chat.Summon);
					Utils.addChat2("??6"+var.prefixCmd+"Ka fov <Double>", var.prefixCmd+"ka fov ", "??7Change l'angle d'attaque du Kill Aura", false, Chat.Summon);
					Utils.addChat2("??6"+var.prefixCmd+"Ka lockview", var.prefixCmd+"ka lockview", "??7Permet ou non de fixer l'entit?? en attaquant", false, Chat.Summon);
					Utils.addChat2("??6"+var.prefixCmd+"Ka live <Int>", var.prefixCmd+"ka live ", "??7Change le temps avant de taper une nouvelle entit??", false, Chat.Summon);
					Utils.addChat2("??6"+var.prefixCmd+"Ka invi", var.prefixCmd+"ka invi", "??7Tape ou non les entit??s invisibles", false, Chat.Summon);
					Utils.addChat2("??6"+var.prefixCmd+"Ka random", var.prefixCmd+"ka random", "??7Change les cps pour qu'ils soient al??atoire", false, Chat.Summon);
					Utils.addChat2("??6"+var.prefixCmd+"Ka verif", var.prefixCmd+"ka verif", "??7Effectue une double v??rification anti-bot ou non", false, Chat.Summon);
					Utils.addChat2("??6"+var.prefixCmd+"Ka onground", var.prefixCmd+"ka onground", "??7Frappe ou non les joueurs dans les air", false, Chat.Summon);				
					Utils.addChat2("??6"+var.prefixCmd+"Ka noarmor", var.prefixCmd+"ka noarmor", "??7Frappe ou non les joueurs sans armures", false, Chat.Summon);
					Utils.addChat2("??6"+var.prefixCmd+"Ka nobot", var.prefixCmd+"ka nobot", "??7Anti-bot avanc??", false, Chat.Summon);
					Utils.addChat2("??6"+var.prefixCmd+"Ka premium", var.prefixCmd+"ka premium", "??7Activ??: Ne frappe que les joueurs en premium", false, Chat.Summon);
					Utils.addChat2("??6"+var.prefixCmd+"Ka speed <Double>", var.prefixCmd+"ka speed ", "??7Modifie la vitesse ?? laquelle on tourne la t??te avec le lockview", false, Chat.Summon);
					Utils.checkXp(xp);
					mc.ingameGUI.getChatGUI().addToSentMessages(var3);
				} else if (args[1].equalsIgnoreCase("verif")) {
					Utils.addChat(Utils.sep);
					Utils.addChat2("??6"+var.prefixCmd+"Verif <String>", var.prefixCmd+"verif ", "??7D??sactive tout le client\n??7Le String est la commande que vous devez faire ??7pour tout r??activer,\n??7tout sera d??sactiv?? jusqu'?? ce que vous le fassiez\n??7Exemple: "+var.prefixCmd+"verif nyah, vous aurez besoin de faire "+var.prefixCmd+"nyah pour ??7tout r??activer ;)\n??7Pour info, tous les cheats activ??s avant vont se r??activer ainsi que toutes les options etc...\n??7De quoi vous permettre de rester legit un instant et repartir en ??7cheat d'un autre ;)", false, Chat.Summon);
					Utils.checkXp(xp);
					mc.ingameGUI.getChatGUI().addToSentMessages(var3);
				} else if (args[1].equalsIgnoreCase("nyah")) {
					Utils.addChat(Utils.sep);
					Utils.addChat2("??6"+var.prefixCmd+"Nyah", var.prefixCmd+"nyah", "??7Permet d'envoyer une phrase kawaii ?? base de neko et d'al??atoire", false, Chat.Summon);
					Utils.addChat2("??6"+var.prefixCmd+"Nyah <Prefix>", var.prefixCmd+"nyah ", "??7Pareil mais avec un prefix devant", false, Chat.Summon);
					mc.ingameGUI.getChatGUI().addToSentMessages(var3);
				} else if (args[1].equalsIgnoreCase("autoarmor") || args[1].equalsIgnoreCase("aa")) {
					Utils.addChat(Utils.sep);
					Utils.addChat2("??6"+var.prefixCmd+"AutoArmor ec", var.prefixCmd+"autoarmor ec", "??7Active ou non sur ??picube", false, Chat.Summon);
					mc.ingameGUI.getChatGUI().addToSentMessages(var3);
				} else if (args[1].equalsIgnoreCase("dolphin")) {
					Utils.addChat(Utils.sep);
					Utils.addChat2("??6"+var.prefixCmd+"Dolphin <Double>", var.prefixCmd+"dolphin ", "??7Change la vitesse du Dolphin", false, Chat.Summon);
					Utils.checkXp(xp);
					mc.ingameGUI.getChatGUI().addToSentMessages(var3);
				} else if (args[1].equalsIgnoreCase("noclip")) {
					Utils.addChat(Utils.sep);
					Utils.addChat2("??6"+var.prefixCmd+"Noclip <Double>", var.prefixCmd+"noclip ", "??7Change la vitesse du Noclip", false, Chat.Summon);
					Utils.checkXp(xp);
					mc.ingameGUI.getChatGUI().addToSentMessages(var3);
				} else if (args[1].equalsIgnoreCase("hud")) {
					Utils.addChat(Utils.sep);
					Utils.addChat2("??6"+var.prefixCmd+"Hud ", var.prefixCmd+"hud", "??7Affiche l'Hud", false, Chat.Summon);
					Utils.addChat2("??6"+var.prefixCmd+"Hud coord", var.prefixCmd+"hud coord", "??7Affiche les coordonn??es", false, Chat.Summon);
					Utils.addChat2("??6"+var.prefixCmd+"Hud fps", var.prefixCmd+"hud fps", "??7Affiche les fps", false, Chat.Summon);
					Utils.addChat2("??6"+var.prefixCmd+"Hud xp", var.prefixCmd+"hud xp", "??7Affiche l'exp??rience et votre niveau", false, Chat.Summon);
					Utils.addChat2("??6"+var.prefixCmd+"Hud packet", var.prefixCmd+"hud packet", "??7Affiche les paquets/secondes envoy??s", false, Chat.Summon);
					Utils.addChat2("??6"+var.prefixCmd+"Hud ms", var.prefixCmd+"hud ms", "??7Affiche votre ping actuel", false, Chat.Summon);
					Utils.addChat2("??6"+var.prefixCmd+"Hud time", var.prefixCmd+"hud time", "??7Affiche le temps de jeu ou le temps de bonus restant", false, Chat.Summon);
					Utils.addChat2("??6"+var.prefixCmd+"Hud stuff", var.prefixCmd+"hud stuff", "??7Affiche le stuff des joueurs vis??s", false, Chat.Summon);
					Utils.addChat2("??6"+var.prefixCmd+"Hud select", var.prefixCmd+"hud select", "??7Rend le bloc vis?? d'une certaine couleur choisie ci-dessous", false, Chat.Summon);
					Utils.addChat2("??6"+var.prefixCmd+"Hud color <R> <G> <B>", var.prefixCmd+"hud color ", "??7Red, Green, Blue, valeurs de 0 ?? 255, change la couleur pour le Hud select", false, Chat.Summon);
					Utils.checkXp(xp);
					mc.ingameGUI.getChatGUI().addToSentMessages(var3);
				} else if (args[1].equalsIgnoreCase("wallhack") || args[1].equalsIgnoreCase("wh")) {
					Utils.addChat(Utils.sep);
					Utils.addChat2("??6"+var.prefixCmd+"WallHack color <R> <G> <B>", var.prefixCmd+"wallhack color ", "??7Change la couleur du fond (Red Green Blue = taux de couleur, de 0 ?? 255)", false, Chat.Summon);
					Utils.addChat2("??6"+var.prefixCmd+"WallHack linecolor <R> <G> <B>", var.prefixCmd+"wallhack linecolor ", "??7Pareil mais pour les lignes", false, Chat.Summon);
					Utils.addChat2("??6"+var.prefixCmd+"WallHack width <Double>", var.prefixCmd+"wallhack width ", "??7Change l'??paisseur des lignes", false, Chat.Summon);
					Utils.checkXp(xp);
					mc.ingameGUI.getChatGUI().addToSentMessages(var3);
				} else if (args[1].equalsIgnoreCase("itemesp") || args[1].equalsIgnoreCase("item")) {
					Utils.addChat(Utils.sep);
					Utils.addChat2("??6"+var.prefixCmd+"ItemESP color <R> <G> <B>", var.prefixCmd+"itemesp color ", "??7Change la couleur", false, Chat.Summon);
					Utils.addChat2("??6"+var.prefixCmd+"ItemESP linecolor <R> <G> <B>", var.prefixCmd+"itemesp linecolor ", "??7pareil mais pour les lignes", false, Chat.Summon);
					Utils.addChat2("??6"+var.prefixCmd+"ItemESP width <Double>", var.prefixCmd+"itemesp width ", "??7Change l'??paisseur de ligne", false, Chat.Summon);
					Utils.checkXp(xp);
					mc.ingameGUI.getChatGUI().addToSentMessages(var3);
				} else if (args[1].equalsIgnoreCase("register") || args[1].equalsIgnoreCase("reg")) {
					Utils.addChat(Utils.sep);
					Utils.addChat2("??6"+var.prefixCmd+"Register mdp <Mot de passe>", var.prefixCmd+"register mdp ", "??7Change le mot de passe par d??faut", false, Chat.Summon);
					Utils.checkXp(xp);
					mc.ingameGUI.getChatGUI().addToSentMessages(var3);
				} else if (args[1].equalsIgnoreCase("enchant")) {
					Utils.addChat(Utils.sep);
					Utils.addChat2("??6"+var.prefixCmd+"Enchant", var.prefixCmd+"enchant", "??7Enchante l'item avec tous les enchantements disponible au niveau 127", false, Chat.Summon);
					Utils.addChat2("??6"+var.prefixCmd+"Enchant list", var.prefixCmd+"enchant list", "??7Affiche la liste des enchantements disponible", false, Chat.Summon);
					Utils.addChat2("??6"+var.prefixCmd+"Enchant <Enchant>", var.prefixCmd+"enchant ", "??7Enchante l'item dans votre main dans l'enchantement choisis au niveau 127", false, Chat.Summon);
					Utils.addChat2("??6"+var.prefixCmd+"Enchant <Enchant> <Level>", var.prefixCmd+"enchant ", "??7Enchante l'item dans votre main dans l'enchantement choisis au niveau choisis", false, Chat.Summon);
					Utils.checkXp(xp);
					mc.ingameGUI.getChatGUI().addToSentMessages(var3);
				} else if (args[1].equalsIgnoreCase("paint")) {
					Utils.addChat(Utils.sep);
					Utils.addChat2("??6"+var.prefixCmd+"Paint color <R> <G> <B>", var.prefixCmd+"paint color ", "??7Change la couleur des blocs (Red Green Blue = taux de couleur, de 0 ?? 100)", false, Chat.Summon);
					Utils.addChat2("??6"+var.prefixCmd+"Paint alpha <Double>", var.prefixCmd+"paint alpha ", "??7Change la transparence, de 0 ?? 1", false, Chat.Summon);
					Utils.addChat2("??6"+var.prefixCmd+"Paint clear", var.prefixCmd+"paint clear", "??7Enl??ve tout ce que vous avez fait", false, Chat.Summon);
					Utils.checkXp(xp);
					mc.ingameGUI.getChatGUI().addToSentMessages(var3);
				} else if (args[1].equalsIgnoreCase("tracers")) {
					Utils.addChat(Utils.sep);
					Utils.addChat2("??6"+var.prefixCmd+"Tracers width <nombre>", var.prefixCmd+"Tracers width 2", "??7Change la taille des traits.", false, Chat.Summon);
					Utils.addChat2("??6"+var.prefixCmd+"Tracers fr/friends/friend", var.prefixCmd+"Tracers fr", "??7Affiche une trace vers les amis (Vert)", false, Chat.Summon);
					Utils.addChat2("??6"+var.prefixCmd+"Tracers joueurs/joueurs/player", var.prefixCmd+"Tracers joueurs", "??7Affiche une trace vers les joueurs non amis (Rouge)", false, Chat.Summon);
					Utils.addChat2("??6"+var.prefixCmd+"Tracers mobs/monstres", var.prefixCmd+"Tracers mobs", "??7Affiche une trace vers les les mobs actifs (monstres) (Orange)", false, Chat.Summon);
					Utils.addChat2("??6"+var.prefixCmd+"Tracers animals/animaux", var.prefixCmd+"Tracers animals", "??7Affiche une trace vers les mobs passifs (animaux) (Bleu)", false, Chat.Summon);
					Utils.addChat2("??6"+var.prefixCmd+"Tracers golem", var.prefixCmd+"Tracers golem", "??7Affiche une trace vers les Golems (Gris)", false, Chat.Summon);
					Utils.addChat2("??6"+var.prefixCmd+"Tracers npc/pnj", var.prefixCmd+"Tracers npc", "??7Affiche une trace vers les NPC / PNJ (Rose)", false, Chat.Summon);
					Utils.checkXp(xp);
					mc.ingameGUI.getChatGUI().addToSentMessages(var3);
				} else if (args[1].equalsIgnoreCase("freecam")) {
					Utils.addChat(Utils.sep);
					Utils.addChat2("??6"+var.prefixCmd+"Freecam <Speed>", var.prefixCmd+"freecam ", "??7Modifie la vitesse du freecam (De base ?? 1)", false, Chat.Summon);
					Utils.checkXp(xp);
					mc.ingameGUI.getChatGUI().addToSentMessages(var3);
				} else if (args[1].equalsIgnoreCase("vclip")) {
					Utils.addChat(Utils.sep);
					Utils.addChat2("??6"+var.prefixCmd+"VClip <Int>", var.prefixCmd+"vclip ", "??7Vous tp en vertical selon le nombre donn??, jusqu'?? 10 blocs", false, Chat.Summon);
					Utils.addChat2("??6"+var.prefixCmd+"VClip -", var.prefixCmd+"vclip +", "??7Vous tp en vertical en hauteur en calculant\n??7la position o?? aller suivant l'espace de libre", false, Chat.Summon);
					Utils.addChat2("??6"+var.prefixCmd+"VClip +", var.prefixCmd+"vclip -", "??7Vous tp en vertical en descente en calculant\n??7la position o?? aller suivant l'espace de libre", false, Chat.Summon);
					Utils.checkXp(xp);
					mc.ingameGUI.getChatGUI().addToSentMessages(var3);
				} else if (args[1].equalsIgnoreCase("search")) {
					Utils.addChat(Utils.sep);
					Utils.addChat2("??6"+var.prefixCmd+"Search add <ID du bloc>", var.prefixCmd+"search add ", "??7Ajoute le bloc et le cherche", false, Chat.Summon);
					Utils.addChat2("??6"+var.prefixCmd+"Search add <Nom du bloc>", var.prefixCmd+"search add ", "??7Ajoute le bloc et le cherche", false, Chat.Summon);
					Utils.addChat2("??6"+var.prefixCmd+"Search clear", var.prefixCmd+"search clear", "??7Clear le bloc cherch??", false, Chat.Summon);
					Utils.checkXp(xp);
					mc.ingameGUI.getChatGUI().addToSentMessages(var3);
				} else if (args[1].equalsIgnoreCase("nuker")) {
					Utils.addChat(Utils.sep);
					Utils.addChat2("??6"+var.prefixCmd+"Nuker add <Bloc>", var.prefixCmd+"nuker add ", "??7Ajoute un bloc ?? votre liste", false, Chat.Summon);
					Utils.addChat2("??6"+var.prefixCmd+"Nuker rem <Bloc>", var.prefixCmd+"nuker rem ", "??7Supprime un bloc de votre liste", false, Chat.Summon);
					Utils.addChat2("??6"+var.prefixCmd+"Nuker range <double>", var.prefixCmd+"nuker range ", "??7Change la distance d'atteinte des blocs", false, Chat.Summon);
					Utils.addChat2("??6"+var.prefixCmd+"Nuker onehit", var.prefixCmd+"nuker onehit", "??7D??truit en 1 coup les blocs", false, Chat.Summon);
					Utils.addChat2("??6"+var.prefixCmd+"Nuker clear", var.prefixCmd+"nuker clear", "??7Vide votre liste", false, Chat.Summon);
					Utils.addChat2("??6"+var.prefixCmd+"Nuker list", var.prefixCmd+"nuker list", "??7Affiche la liste", false, Chat.Summon);
					Utils.addChat2("??6"+var.prefixCmd+"Nuker safe", var.prefixCmd+"nuker safe", "??7Ne casse pas le bloc en dessous de vous", false, Chat.Summon);
					Utils.checkXp(xp);
					mc.ingameGUI.getChatGUI().addToSentMessages(var3);
				} else if (args[1].equalsIgnoreCase("step")) {
					Utils.addChat(Utils.sep);
					Utils.addChat2("??6"+var.prefixCmd+"Step <Double>", var.prefixCmd+"step ", "??7Change le maximum de hauteur de bloc grimpable avec le Step", false, Chat.Summon);
					Utils.addChat2("??6"+var.prefixCmd+"Step bypass", var.prefixCmd+"step bypass", "??7Met le step en mode bypass", false, Chat.Summon);
					Utils.checkXp(xp);
					mc.ingameGUI.getChatGUI().addToSentMessages(var3);
					
					
				} else if (args[1].equalsIgnoreCase("findclaim")) {
					Utils.addChat(Utils.sep);
					Utils.addChat2("??6"+var.prefixCmd+"FindClaim", var.prefixCmd+"findclaim ", "??7Cherche les coordonn??es de claim dans une zone de chunk donn??e", false, Chat.Summon);
					Utils.addChat2("??6"+var.prefixCmd+"FindClaim stop", var.prefixCmd+"findclaim stop", "??7Arr??te le claim finder", false, Chat.Summon);
					Utils.addChat("Ces options sont diff??rentes par rapport ?? celles des autres commandes de Neko.\n??6Elles doivent ??tre mise sur la m??me ligne de commande pour ??tre active:\n??6"
							+ "??6"+var.prefixCmd+"findclaim basechunk=50 maxchunk=100 packetsec=60 ignore=wilderness par exemple.");
					Utils.addChat2("??6basechunk=<Chunks>", "", "??7D??finit les chunks de base ?? ignorer, de base ?? 0.", true, Chat.Summon);
					Utils.addChat2("??6maxchunk=<Chunks> ou maxchunk=<Chunks X,Chunks Z>", "", "??7D??finit la zone de chunks ?? analyser, de base ?? 100.\n??7"
							+ "Vous pouvez d??finir aussi la valeur de X et Z s??paremment, pour par exemple scanner de -20k ?? +20k en z mais sur une largeur de 1 en x.", true, Chat.Summon);
					Utils.addChat2("??6packetsec=<Nombre de paquets>", "", "??7D??finit le nombre de paquet ?? utiliser par secondes, de base ?? 60. Plus on en met, plus ??a va vite.", true, Chat.Summon);
					Utils.addChat2("??6ignore=<Texte>", "", "??7D??finit le nom de faction de base ?? ignorer dans les r??sultats, comme wilderness.\n??7"
							+ "Cela concerne uniquement les messages d'entr??e de faction qui sont affich??es par des titre qui viennent sur l'??cran.", true, Chat.Summon);
					Utils.addChat2("??6chat=<Texte>", "", "??7Active la d??tection d'entr??e de faction par le chat et \n??7"
							+ "le texte d??finit le d??but du message d'entr??e dans la faction pour d??tecter tout en rempla??ant les espaces par des _\n??7"
							+ "les messages. Par exemple: chat=Vous_venez_de_quitter", true, Chat.Summon);
					Utils.addChat2("??6startchunk=<Chunks X,Chunks Z>", "", "??7D??finit les chunks du point de d??part du scan, de base ?? X:0 et Z:0.\n"
							+ "??7Exemple: startchunk=50,-100", true, Chat.Summon);
					Utils.checkXp(xp);
					mc.ingameGUI.getChatGUI().addToSentMessages(var3);
				} else if (args[1].equalsIgnoreCase("speed")) {
					Utils.addChat(Utils.sep);
					Utils.addChat2("??6"+var.prefixCmd+"Speed <Double>", var.prefixCmd+"speed ", "??7Change la vitesse du Speed", false, Chat.Summon);
					Utils.addChat2("??6"+var.prefixCmd+"Speed Mode <Air:Ground:Motion>", var.prefixCmd+"speed mode ", "??7Change le mode du Speed", false, Chat.Summon);
					Utils.checkXp(xp);
					mc.ingameGUI.getChatGUI().addToSentMessages(var3);
				} else if (args[1].equalsIgnoreCase("autoclic")) {
					Utils.addChat(Utils.sep);
					Utils.addChat2("??6"+var.prefixCmd+"Autoclic <Int>", var.prefixCmd+"autoclic", "??7Modifie les coups/sec de l'Autoclic", false, Chat.Summon);
					Utils.checkXp(xp);
					mc.ingameGUI.getChatGUI().addToSentMessages(var3);
				} else if (args[1].equalsIgnoreCase("worldtime")) {
					Utils.addChat(Utils.sep);
					Utils.addChat2("??6"+var.prefixCmd+"WorldTime <Int>", var.prefixCmd+"worldtime ", "??7Change l'heure du monde", false, Chat.Summon);
					Utils.checkXp(xp);
					mc.ingameGUI.getChatGUI().addToSentMessages(var3);
				} else if (args[1].equalsIgnoreCase("irc")) {
					Utils.addChat(Utils.sep);
					Utils.addChat("??7Vous pouvez ??crire en couleur dans l'Irc.");
					Utils.addChat2("??6"+var.prefixCmd+"Irc", var.prefixCmd+"irc", "??7Active/d??sactive l'Irc", false, Chat.Summon);
					Utils.addChat2("??6"+var.prefixCmd+"Irc name <Pseudo>", var.prefixCmd+"irc name ", "??7Change votre pseudo dans l'Irc", false, Chat.Summon);
					Utils.addChat2("??6"+var.prefixCmd+"Irc prefix <Prefix>", var.prefixCmd+"irc ", "??7Change le prefix pour l'irc", false, Chat.Summon);
					Utils.addChat2("??6"+var.prefixCmd+"Irc mode <Hybride:Normal:Only>", var.prefixCmd+"irc mode ", "??7Change le mode de l'irc", false, Chat.Summon);
					Utils.addChat2("??6"+var.prefixCmd+"Irc list", var.prefixCmd+"irc list", "??7Affiche la liste des joueurs connect??s sur Neko et l'IRC", false, Chat.Summon);
					Utils.addChat2("??6"+var.prefixCmd+"Irc hide", var.prefixCmd+"irc hide", "??7Cache les messages de join/left", false, Chat.Summon);
					Utils.addChat2("??6"+var.prefixCmd+"Irc playerclic", var.prefixCmd+"irc playerclic", "??7Change entre la connexion au serveur du joueur ou le contacter par message priv?? quand on clic dessus", false, Chat.Summon);
					Utils.checkXp(xp);
					mc.ingameGUI.getChatGUI().addToSentMessages(var3);
				} else if (args[1].equalsIgnoreCase("dropshit")) {
					Utils.addChat(Utils.sep);
					Utils.addChat2("??6"+var.prefixCmd+"DropShit", "", "??7Sert ?? drop les items dans votre inventaire qui sont dans votre liste noire (BlackList), vous pouvez les ajouter/supprimer avec les commandes suivantes:", true, Chat.Summon);
					Utils.addChat2("??6"+var.prefixCmd+"DropShit add <Bloc>", var.prefixCmd+"dropshit add ", "??7Ajoute un item ?? votre liste", false, Chat.Summon);
					Utils.addChat2("??6"+var.prefixCmd+"DropShit rem <Bloc>", var.prefixCmd+"dropshit rem ", "??7Supprime un item de votre liste", false, Chat.Summon);
					Utils.addChat2("??6"+var.prefixCmd+"DropShit clear", var.prefixCmd+"dropshit clear", "??7Vide votre liste", false, Chat.Summon);
					Utils.addChat2("??6"+var.prefixCmd+"DropShit list", var.prefixCmd+"dropshit list", "??7Affiche la liste", false, Chat.Summon);
					Utils.checkXp(xp);
					mc.ingameGUI.getChatGUI().addToSentMessages(var3);
				} else if (args[1].equalsIgnoreCase("regen")) {
					Utils.addChat(Utils.sep);
					Utils.addChat2("??6"+var.prefixCmd+"Regen <Int>", var.prefixCmd+"regen ", "??7Change le nombre de paquets envoy??s par secondes", false, Chat.Summon);
					Utils.addChat2("??6"+var.prefixCmd+"Regen bypass", var.prefixCmd+"regen bypass", "??7Active seulement la regen avec un effet de potion de regen", false, Chat.Summon);
					Utils.checkXp(xp);
					mc.ingameGUI.getChatGUI().addToSentMessages(var3);
				} else if (args[1].equalsIgnoreCase("timer")) {
					Utils.addChat(Utils.sep);
					Utils.addChat2("??6"+var.prefixCmd+"Timer <Double>", var.prefixCmd+"timer ", "??7Change la vitesse du Timer", false, Chat.Summon);	
					Utils.checkXp(xp);
					mc.ingameGUI.getChatGUI().addToSentMessages(var3);
				} else if (args[1].equalsIgnoreCase("fasteat")) {
					Utils.addChat(Utils.sep);
					Utils.addChat2("??6"+var.prefixCmd+"Fasteat <Int>", var.prefixCmd+"fasteat ", "??7Change la vitesse quand on mange dans le Fasteat (0 ?? 25)", false, Chat.Summon);	
					Utils.checkXp(xp);
					mc.ingameGUI.getChatGUI().addToSentMessages(var3);
				} else if (args[1].equalsIgnoreCase("pushup")) {
					Utils.addChat(Utils.sep);
					Utils.addChat2("??6"+var.prefixCmd+"Pushup <Int>", var.prefixCmd+"pushup ", "??7Change le nombre de paquet envoy??s", false, Chat.Summon);	
					Utils.checkXp(xp);
					mc.ingameGUI.getChatGUI().addToSentMessages(var3);
				} else if (args[1].equalsIgnoreCase("flight")) {
					Utils.addChat(Utils.sep);
					Utils.addChat2("??6"+var.prefixCmd+"Flight <Double>", var.prefixCmd+"flight ", "??7Change la vitesse du Flight", false, Chat.Summon);
					Utils.addChat2("??6"+var.prefixCmd+"Flight blink", var.prefixCmd+"flight blink", "??7Permet d'activer automatiquement le Blink quand on active le Flight", false, Chat.Summon);
					Utils.checkXp(xp);
					mc.ingameGUI.getChatGUI().addToSentMessages(var3);
				} else if (args[1].equalsIgnoreCase("bowaimbot")) {
					Utils.addChat(Utils.sep);
					Utils.addChat2("??6"+var.prefixCmd+"BowAimbot Fov <Double>", var.prefixCmd+"bowaimbot fov ", "??7Change le fov du bowaimbot", false, Chat.Summon);
					Utils.addChat2("??6"+var.prefixCmd+"BowAimbot Life", var.prefixCmd+"bowaimbot life", "??7Change le mode life, ?? viser la personne le Min de vie, Max de vie ou d??sactiv??", false, Chat.Summon);
					Utils.addChat2("??6"+var.prefixCmd+"BowAimbot Armor", var.prefixCmd+"bowaimbot armor", "??7Change le mode armor, ?? viser la personne le Min d'armure, Max d'armure ou d??sactiv??", false, Chat.Summon);
					Utils.checkXp(xp);
					mc.ingameGUI.getChatGUI().addToSentMessages(var3);
				} else if (args[1].equalsIgnoreCase("longjump")) {
					Utils.addChat(Utils.sep);
					Utils.addChat2("??6"+var.prefixCmd+"Longjump speed <Double>", var.prefixCmd+"longjump speed ", "??7Change la vitesse du Longjump", false, Chat.Summon);
					Utils.checkXp(xp);
					mc.ingameGUI.getChatGUI().addToSentMessages(var3);
				} else if (args[1].equalsIgnoreCase("option")) {
					Utils.addChat(Utils.sep);
					Utils.addChat("Concernant ces commandes, elles n'affichent pas si elles ??6ont ??t?? activ??es ou non");
					Utils.addChat2("??6"+var.prefixCmd+"Option display", var.prefixCmd+"option ", "??7Affiche le Activ??/d??sactiv?? des cheats", false, Chat.Summon);
					Utils.addChat2("??6"+var.prefixCmd+"Option zoom", var.prefixCmd+"option ", "??7Permet un zoom en tirant ?? l'arc", false, Chat.Summon);
					Utils.addChat2("??6"+var.prefixCmd+"Option xp", var.prefixCmd+"option ", "??7Affiche l'xp gagn??", false, Chat.Summon);
					Utils.addChat2("??6"+var.prefixCmd+"Option deathOff", var.prefixCmd+"option ", "??7D??sactive le Kill Aura et Trigger ?? la mort", false, Chat.Summon);
					Utils.addChat2("??6"+var.prefixCmd+"Option scoreboard", var.prefixCmd+"option ", "??7D??sactive les scoreboards", false, Chat.Summon);
					Utils.checkXp(xp);
					mc.ingameGUI.getChatGUI().addToSentMessages(var3);
				} else {
					try {
						Utils.displayHelp(Integer.parseInt(args[1]));
					} catch (Exception e) {Utils.addChat(error);}
					Utils.checkXp(xp);
					mc.ingameGUI.getChatGUI().addToSentMessages(var3);
				}
				
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"save")) {
				Utils.saveAll();
				Utils.addChat("??aTout a ??t?? sauv??");
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"fov3")) {
				if (args.length==1) {
					Utils.addChat(err);
				} else {
					try {
						EntityRenderer.thirdPersonDistance=Float.parseFloat(args[1]);
						Utils.addChat("??aDistance ?? la 3??me personne mis ?? "+args[1]+" blocs");
					} catch (Exception e) {
						Utils.addChat(err);
					}
				}
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"callcmd")) {
				try {
					CallCmd c = CallCmd.getCall();
					if (args.length==1) {
						Utils.addChat(err);
					} else if (args[1].equalsIgnoreCase("player")) {
						if (!c.getListPlayer().contains(args[2])) {
							c.getListPlayer().add(args[2]);
							Utils.addChat("??aJoueur "+args[2]+" ajout?? ?? la blacklist !");
						} else {
							c.getListPlayer().removeElement(args[2]);
							Utils.addChat("??cLe joueur "+args[2]+" a ??t?? supprim?? de la blacklist !");
						}
					} else if (args[1].equalsIgnoreCase("cmd")) {
						c.setCmd2(args[2]);
						Utils.addChat("??aCommande mise ?? jour");
					} else if (args[1].equalsIgnoreCase("clear")) {
						c.getListPlayer().clear();
						Utils.addChat("??aListe des joueurs clear");
						if (c.getListPlayer().size()==0) {
							Utils.addChat("Pas de joueurs blacklist??s");
						} else {
							Utils.addChat("Liste des joueurs blacklist??s");						
							for (String s : c.getListPlayer()) {
								Utils.addChat("??7- "+s);
							}
						}
						Utils.addChat("Commande: ??7"+c.getCmd2());
					}
				} catch (Exception e) {
					Utils.addChat(err);
				}
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"register") || args[0].equalsIgnoreCase(var.prefixCmd+"reg")) {
				try {
					Register r = Register.getReg();
					if (args.length==1) {
						Utils.addChat(err);
					} else if (args[1].equalsIgnoreCase("mdp")) {
						r.setMdp(args[2]);
						Utils.addChat("??aMot de passe par d??faut chang?? !");
					}
				} catch (Exception e) {
					Utils.addChat(err);
				}
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"size")) {
				if (args.length==1) {
					Utils.addChat(err);
				} else {
					if (Utils.isInteger(args[1])) {
						int size = Integer.parseInt(args[1]);
						ItemStack item = mc.thePlayer.getCurrentEquippedItem();
						if (item!=null) {
							item.stackSize=size;
							Minecraft.thePlayer.sendQueue.addToSendQueue(new C09PacketHeldItemChange(mc.thePlayer.inventory.currentItem));
						} else {
							Utils.addChat("??cAucun item en main");
						}
					}
				}
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"give")) {
				if (args.length==1) {
					Utils.addChat(err);
				} else {
					try {
						String id = args[1];
						Item item = Item.getByNameOrId(id);
						int amount = 1;
						if (args.length>=3 && Utils.isInteger(args[2])) {
							amount = Integer.parseInt(args[2]);
						}
						mc.thePlayer.inventory.addItemStackToInventory(new ItemStack(item, amount));
						mc.thePlayer.inventoryContainer.detectAndSendChanges();
						Utils.addChat("??aItem ajout?? dans l'inventaire !");
					} catch (Exception e) {
						Utils.addError(err);
					}
				}
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}

			if (args[0].equalsIgnoreCase(var.prefixCmd+"gm")) {
				if (args.length==1) {
					Utils.addChat(err);
				} else {
					if (Utils.isInteger(args[1])) {
						int id = Integer.parseInt(args[1]);
						try {
							mc.playerController.setGameType(GameType.getByID(id));
							mc.thePlayer.sendChatMessage("/gamemode "+id);
						} catch (Exception e) {
							Utils.addChat(err);
						}
					} else {
						mc.playerController.setGameType(GameType.getByName(args[1]));
						mc.thePlayer.sendChatMessage("/gamemode "+args[1]);
					}
				}
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"gmf")) {
				if (args.length==1) {
					Utils.addChat(err);
				} else {
					if (Utils.isInteger(args[1])) {
						int id = Integer.parseInt(args[1]);
						try {
							mc.playerController.setGameType(GameType.getByID(id));
						} catch (Exception e) {
							Utils.addChat(err);
						}
					} else {
						mc.playerController.setGameType(GameType.getByName(args[1]));
					}
				}
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"autopot")) {
				try {
					AutoPot.heal=Integer.parseInt(args[1]);
					Utils.addChat("??aSeuil de vie mis ?? "+args[1]+" !");
				} catch (Exception e) {
					Utils.addChat(err);
				}

				Utils.checkXp(xp);
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"glide")) {
				try {
					Glide.getGlide().setSpeed(Double.parseDouble(args[1]));
					Utils.addChat("??aVitesse du glide chang?? ?? "+args[1]+" !");
				} catch (Exception e) {
					Utils.addChat(err);
				}
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"wear")) {
				try {					
					if (mc.thePlayer.getCurrentEquippedItem()!=null) {
						mc.thePlayer.inventory.setInventorySlotContents(39, mc.thePlayer.getCurrentEquippedItem());
						mc.thePlayer.inventory.markDirty();
					}
				} catch (Exception e) {}
			}
			
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"pyro")) {
				if (args.length==1) {
					
				} else if (args[1].equalsIgnoreCase("list")) {
					String modes="";
					int i=0;
					for (Form f : Form.values()) {
						i++;
						if (Form.values().length!=i)
							modes+=f+"??7, ??a";
						else
							modes+=f;
					}
					Utils.addChat("Modes disponibles: ??a"+modes);		
				} else if (args.length>=2){
					try {
						String mode = args[1].toLowerCase();
						mode = mode.replaceFirst(".", (mode.charAt(0)+"").toUpperCase());
						
						Pyro.mode=Form.valueOf(mode);
						Utils.addChat("??aPyro pass?? en mode "+mode+" !");
					} catch (Exception e) {
						Utils.addChat("??cCe mode n'existe pas. Essayez "+var.prefixCmd+"pyro list");
					}
				}
				Utils.checkXp(xp);
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}	
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"prefix")) {
				if (args.length==1) {
					Utils.addChat(error);
				} else {
					var.prefixCmd=args[1];
				}
				Utils.checkXp(xp);
				Utils.addChat("??aPr??fix chang?? !");
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}	
			if (args[0].equalsIgnoreCase(var.prefixCmd+"onlyrpg")) {
				if (args.length==1) {
					if (var.onlyrpg.isActive()) {						
						Utils.addChat("??cOnlyRpg d??sactiv?? !");
						var.onlyrpg.setTimeSec(0);
					} else {						
						Utils.addChat("??aOnlyRpg activ?? !");						
					}
					var.onlyrpg.setActive(!var.onlyrpg.isActive());
					if (var.onlyrpg.isActive()) {
						for (Module m : ModuleManager.ActiveModule) {
							boolean v = false;
							for (String c : var.onlyrpg.getCheat()) {
								if (c.equalsIgnoreCase(m.getName()))
									v = true;
							}
							if (!v) {
								if (m.isToggled()) {
									boolean bl = Utils.display;
									Utils.display = false;
									m.onDisabled();
									Utils.display = bl;
								}
								m.setWithoutToggle(false);
							}							
						}	
					}
					Utils.loadFrame();
					var.onlyrpg.setTimeSec(0);
				}
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"build")) {
				Build b = Build.getBuild();
				if (args.length==1) {
				} else if (args[1].equalsIgnoreCase("Down")) {
					if (b.isDown()) {
						Utils.addChat("??cMode Down d??sactiv??");
					} else {
						Utils.addChat("??aMode Down activ??");
					}
					b.setDown(!b.isDown());
				} else if (args[1].equalsIgnoreCase("Up")) {
					if (b.isUp()) {
						Utils.addChat("??cMode Up d??sactiv??");
					} else {
						Utils.addChat("??aMode Up activ??");
					}
					b.setUp(!b.isUp());
				} else if (args[1].equalsIgnoreCase("Wall")) {
					if (b.isWall()) {
						Utils.addChat("??cMode Wall d??sactiv??");
					} else {
						Utils.addChat("??aMode Wall activ??");
					}
					b.setWall(!b.isWall());
				} else if (args[1].equalsIgnoreCase("Sneak")) {
					if (b.isSneak()) {
						Utils.addChat("??cMode Sneak d??sactiv??");
					} else {
						Utils.addChat("??aMode Sneak activ??");
					}
					b.setSneak(!b.isSneak());
				}				
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}	
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"potion")) {
				try {
					if (!mc.thePlayer.capabilities.isCreativeMode) {
				      Utils.addChat("??cVous n'??tes pas en Cr??atif !");
				      return;
				    }
					if (mc.thePlayer.getCurrentEquippedItem()==null) {
						Utils.addChat("??cLe slot sur lequel vous ??tes doit ??tre libre !");
					}
				} catch (Exception e) {Utils.addChat("??cErreur...");return;}
			    ItemStack stack = new ItemStack(Items.potionitem);
			    stack.setItemDamage(16384);
			    NBTTagList effects = new NBTTagList();
			    NBTTagCompound effect = new NBTTagCompound();
			    effect.setInteger("Amplifier", 125);
			    effect.setInteger("Duration", 2000);
			    effect.setInteger("Id", 6);
			    effects.appendTag(effect);
			    stack.setTagInfo("CustomPotionEffects", effects);
			    stack.setStackDisplayName("??d??lKawaii Neko Potion :3");
			    
			    mc.thePlayer.sendQueue.addToSendQueue(new C10PacketCreativeInventoryAction(mc.thePlayer.inventory.currentItem, stack));
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}	
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"namemc") || args[0].equalsIgnoreCase(var.prefixCmd+"name")) {
				if (args.length==1) {
					Utils.addChat("??cErreur, pas de pseudos donn??s");
				} else {
					new Thread(new Runnable() {
						public void run() {
							String s = Utils.getOldNames(args[1]);
							if (s.isEmpty()) {
								Utils.addChat("??cAucun pseudos trouv??s");
							} else {
								Utils.addChat("??7[??aDerniers pseudos??7]");
								String list[] = s.split(" ");
								for (int i = list.length-1;i!=-1;i--)
									Utils.addChat("??7>>> ??a"+list[i]);
							}
						}
					}).start();
				}
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}	
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"gui")) {
				if (args.length>=6 && args[1].equalsIgnoreCase("color")) {
					try {
						Utils.colorGui = new Color(Integer.parseInt(args[2]), Integer.parseInt(args[3]), Integer.parseInt(args[4]), Integer.parseInt(args[5]));
						Utils.addChat("??aCouleur du gui chang??e !");
					} catch (Exception e) {
						Utils.addError("Syntaxe correcte: "+var.prefixCmd+"gui color <R> <G> <B> <Alpha> en nombre entier, 0-255");
					}
				} else if (args.length>=6 && args[1].equalsIgnoreCase("font")) {
					try {
						Utils.colorFontGui = new Color(Integer.parseInt(args[2]), Integer.parseInt(args[3]), Integer.parseInt(args[4]), Integer.parseInt(args[5]));
						Utils.addChat("??aCouleur du font du gui chang??e !");
					} catch (Exception e) {
						Utils.addError("Syntaxe correcte: "+var.prefixCmd+"gui font <R> <G> <B> <Alpha> en nombre entier, 0-255");
					}
				} else {
					Utils.addError("Syntaxe correcte: "+var.prefixCmd+"gui color <R> <G> <B> <Alpha> en nombre entier, 0-255");
				}
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}					
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"fastbow") || args[0].equalsIgnoreCase(var.prefixCmd+"fb")) {
				if (args[1].equalsIgnoreCase("nobow")) {
					if (Fastbow.getFast().isNobow()) {
						Utils.addChat("??cFastbow nobow d??sactiv??");
					} else {
						Utils.addChat("??aFastbow nobow activ??");
					}
					Fastbow.getFast().setNobow(!Fastbow.getFast().isNobow());
				} else if (args[1].equalsIgnoreCase("packet") && args.length>=3) {
					if (Utils.isInteger(args[2])) {
						Fastbow.getFast().setPacket(Integer.parseInt(args[2]));
						Utils.addChat("??aLes paquets envoy??s sont mis ?? "+args[2]+" !");
					} else {
						Utils.addChat(err);
					}
				}
				Utils.checkXp(xp);
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"bowaimbot") || args[0].equalsIgnoreCase(var.prefixCmd+"bowaim")) {
				BowAimbot b = BowAimbot.getAim();
				if (args[1].equalsIgnoreCase("fov")) {
					try {
						b.setFov(Double.parseDouble(args[2]));
						Utils.addChat("??aFov du BowAimbot mis ?? "+b.getFov()+"?? !");
					} catch (Exception e) {
						Utils.addChat(err);
					}
				} else if (args[1].equalsIgnoreCase("life")) {
					b.setLife(Utils.pass(b.getLife()));
					Utils.addChat("??aMode life mis ?? "+b.getLife()+" !");
				} else if (args[1].equalsIgnoreCase("armor")) {
					b.setArmor(Utils.pass(b.getArmor()));
					Utils.addChat("??aMode armor mis ?? "+b.getArmor()+" !");
				}
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"disc")) {
				if (args.length==1) {
					Utils.addChat(error);
				} else {
					if (args.length==3) {
						try {
							mc.thePlayer.playSound("records."+args[1], Float.parseFloat(args[2]), 1.0F);
							Utils.addChat("??aDisque "+args[1]+" est jou?? !");
						} catch (Exception e) {
							Utils.addChat(err);
						}
					} else {
						mc.thePlayer.playSound("records."+args[1], 1.0F, 1.0F);
						Utils.addChat("??aDisque "+args[1]+" est jou?? !");
					}
				}
				Utils.checkXp(xp);
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"playsound")) {
				if (args.length==1) {
					Utils.addChat(error);
				} else {
					if (args.length==3) {
						try {
							mc.thePlayer.playSound(args[1], Float.parseFloat(args[2]), 1.0F);
							Utils.addChat("??aLe son "+args[1]+" est jou?? !");
						} catch (Exception e) {
							Utils.addChat(err);
						}
					} else {
						mc.thePlayer.playSound(args[1], 1.0F, 1.0F);
						Utils.addChat("??aLe son "+args[1]+" est jou?? !");
					}
				}
				Utils.checkXp(xp);
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"near")) {
				int blocs = 0;
				if (args.length==3 && args[1].equalsIgnoreCase("list") && Utils.isInteger(args[2])) {
					blocs = Integer.parseInt(args[2]);
				}
				
				if (args.length==4 && args[1].equalsIgnoreCase("ignore") && args[2].startsWith("[") && args[2].endsWith("]") && Utils.isInteger(args[3])) {
					String[] l = args[2].replaceAll("\\[", "").replaceAll("\\]", "").split(",");
					int x = Integer.parseInt(l[0]);
					int y = Integer.parseInt(l[1]);
					int z = Integer.parseInt(l[2]);
					Near.spawn = new BlockPos(x, y, z);
					Near.radius = Integer.parseInt(args[3]);
					Utils.addChat(Utils.setColor("??aLes coordonn??es re??ues autour de "+args[2]+" jusqu'?? "+Near.radius+" blocs seront ignor??es !", "??a"));
				}
				
				if (args.length>=2 && args[1].equalsIgnoreCase("say")) {
					if (args.length==3) {
						EntityPlayer entity = Utils.getPlayer(args[2]);
						BlockPos bp = entity.getPosition();
						mc.thePlayer.sendChatMessage(entity.getName()+" a ??t?? aper??u en "+bp.getX()+", "+bp.getY()+", "+bp.getZ()+" un jour de grand soleil");
					} else {
						if (Near.say) {
							Utils.addChat(Utils.setColor("Near d??sactiv?? en continu pour envoyer les messages de tp dans le chat", "??c"));
						} else {
							Utils.addChat(Utils.setColor("??aNear activ?? en continu pour envoyer les messages de tp dans le chat", "1a"));
						}
						Near.say = !Near.say;
					}
				}
				
				if (args.length>=2 && args[1].equalsIgnoreCase("noname")) {
					if (Near.noname) {
						Utils.addChat(Utils.setColor("??aLa copie des coordonn??es donnent aussi le pseudo", "??a"));
					} else {
						Utils.addChat(Utils.setColor("??aLa copie des coordonn??es exclus le nom du joueur", "??a"));
					}
					Near.noname = !Near.noname;
				}
				
				if (args.length>=2 && args[1].equalsIgnoreCase("copy")) {
					ArrayList<EntityPlayer> en = Utils.getAllPlayer();
					String list = "";
					String player = "";
					if (args.length==3)
						player = args[2];
					for (EntityPlayer entity : en) {
						BlockPos bp = entity.getPosition();
						if (player.isEmpty() ? true : entity.getName().equalsIgnoreCase(player))
							if (list.isEmpty())
								list=(!Near.noname ? entity.getName()+"=" : "")+"["+bp.getX()+","+bp.getY()+","+bp.getZ()+"]";
							else
								list+=", "+(!Near.noname ? entity.getName()+"=" : "")+"["+bp.getX()+","+bp.getY()+","+bp.getZ()+"]";
					}
					Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
					clipboard.setContents(new StringSelection(list), null);
					if (player.isEmpty())
						Utils.addChat("??aListe des joueurs copi??es !");
					else
						Utils.addChat("??aJoueur copi?? !");
				}
				
				if (args.length>=2 && args[1].equalsIgnoreCase("icopy")) {
					ArrayList<EntityPlayer> en = Utils.getAllPlayer();
					String list = "";
					for (EntityPlayer entity : en) {
						BlockPos bp = entity.getPosition();
						EntityWitch ignore = new EntityWitch(mc.theWorld);
						ignore.setPosition(Near.spawn.getX(), Near.spawn.getY(), Near.spawn.getZ());
						if (entity.getDistanceToEntity(ignore)>=Near.radius)
							if (list.isEmpty())
								list=(!Near.noname ? entity.getName()+"=" : "")+"["+bp.getX()+","+bp.getY()+","+bp.getZ()+"]";
							else
								list+=", "+(!Near.noname ? entity.getName()+"=" : "")+"["+bp.getX()+","+bp.getY()+","+bp.getZ()+"]";
					}
					Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
					clipboard.setContents(new StringSelection(list), null);
					Utils.addChat("??aListe des joueurs copi??es !");
				}
				
				if (args.length==2 && args[1].equalsIgnoreCase("list")) {
					ArrayList<EntityPlayer> en = Utils.getAllPlayer();
					Utils.addChat("Joueurs proches:");
					for (EntityPlayer entity : en) {
						BlockPos bp = entity.getPosition();
						if (entity.getDistanceToEntity(mc.thePlayer)>blocs) {
							Utils.addChat("??d"+entity.getName()+"??8:??6 "+bp.getX()+", "+bp.getY()+", "+bp.getZ()+" ??8(??2"+Math.round(entity.getDistanceToEntity(mc.thePlayer))+"m??8)");
						}
					}
				}
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"forcetp") || args[0].equalsIgnoreCase(var.prefixCmd+"ftp")) {
				
				if (args.length==2 && args[1].startsWith("[") && args[1].endsWith("]")) {
					String[] l = args[1].replaceAll("\\[", "").replaceAll("\\]", "").split(",");
					int x = Integer.parseInt(l[0]);
					int y = Integer.parseInt(l[1]);
					int z = Integer.parseInt(l[2]);
					ForceTP.getForceTP().setPoint(new BlockPos(x, y, z));
					Utils.addChat(Utils.setColor("??aLes coordonn??es "+args[1]+" sont enregistr??s comme cible du TP !", "??a"));
				}
				
				if (args.length==2 && args[1].equalsIgnoreCase("ymax")) {
					ForceTP f = ForceTP.getForceTP();
					if (f.isYMax()) {
						Utils.addChat(Utils.setColor("Hauteur maximal de Y d??sactiv??e pour les TP (??a prendra la valeur Y de la target)", "??c"));
					} else {
						Utils.addChat(Utils.setColor("Hauteur maximal de Y activ??e pour les TP (255)", "??a"));
					}
					f.setYMax(!f.isYMax());
				}
				
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"crasher")) {
				Crasher crasher = Crasher.getInstance();
				
				if (args.length==2 && args[1].equalsIgnoreCase("wave")) {
					crasher.setWave(!crasher.isWave());
					if (crasher.isWave())
						Utils.addChat(Utils.setColor("Mode wave du crasher activ?? !", "??a"));
					else
						Utils.addChat(Utils.setColor("Mode wave du crasher d??sactiv?? !", "??c"));
				}
				
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"option")) {
				if (args.length==1) {
					Utils.addChat("??cErreur, essayez "+var.prefixCmd+"help option");
				} else if (args[1].equalsIgnoreCase("zoom")) {
					if (Utils.zoom) {
						Utils.zoom=false;
						Utils.addChat("??cZoom d??sactiv??");
					} else {
						Utils.zoom=true;
						Utils.addChat("??aZoom activ??");
					}
				} else if (args[1].equalsIgnoreCase("display")) {
					if (Utils.display) {
						Utils.display=false;
						Utils.addChat("??cDisplay d??sactiv??");
					} else {
						Utils.display=true;
						Utils.addChat("??aDisplay activ??");
					}
				} else if (args[1].equalsIgnoreCase("xp")) {
					if (Utils.xp) {
						Utils.xp=false;
						Utils.addChat("??cAffichage d'xp d??sactiv??");
					} else {
						Utils.xp=true;
						Utils.addChat("??aAffichage d'xp activ??");
					}
				} else if (args[1].equalsIgnoreCase("scoreboard") || args[1].equalsIgnoreCase("sb")) {
					if (Utils.scoreboard) {
						Utils.scoreboard=false;
						Utils.addChat("??cScoreboard d??sactiv??");
					} else {
						Utils.scoreboard=true;
						Utils.addChat("??aScoreboard activ??");
					}
				} else if (args[1].equalsIgnoreCase("deathoff")) {
					if (Utils.deathoff) {
						Utils.deathoff=false;
						Utils.addChat("??cDeathOff d??sactiv??");
					} else {
						Utils.deathoff=true;
						Utils.addChat("??aDeathOff activ??");
					}
				}
				Utils.checkXp(xp);
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"ip")) {
				Utils.checkXp(xp);	
				if (!mc.isSingleplayer()) {
					Utils.addChat("??aVous ??tes connect?? sur "+mc.getCurrentServerData().serverIP);
					try {
						Utils.addChat("??7(??a"+InetAddress.getByName(mc.getCurrentServerData().serverIP).getHostAddress()+"??7)");
					} catch (Exception e) {}
				} else 
					Utils.addChat("??aVous ??tes connect?? sur un monde solo");
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"join")) {
				if (args.length==1 || mc.isSingleplayer()) {
					Utils.addChat(Utils.setColor("Erreur, utilisation correcte: "+var.prefixCmd+"join <NomJoueur>", "??c"));
				} else {
					new Thread(new Runnable() {
						
						@Override
						public void run() {
							try {
								ServerAddress var4 = ServerAddress.func_78860_a(mc.getCurrentServerData().serverIP);
								InetAddress var1 = InetAddress.getByName(mc.getCurrentServerData().serverIP);
			                    NetworkManager n = NetworkManager.provideLanClient(var1, var4.getPort());
			                    n.setNetHandler(new NetHandlerLoginClient(n, null, null));
			                    n.sendPacket(new C00Handshake(47, mc.getCurrentServerData().serverIP, var4.getPort(), EnumConnectionState.LOGIN));
			                    n.sendPacket(new C00PacketLoginStart(new GameProfile(UUID.randomUUID(), args[1])));
			                    n.checkDisconnected();
							} catch (Exception e) {}							
						}
					}).start();
				}
				
				
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
			if (args[0].equalsIgnoreCase(var.prefixCmd+"end")) {
				Utils.dim=true;
				Utils.checkXp(xp);												
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"likaotique") || args[0].equalsIgnoreCase(var.prefixCmd+"lik")) {
				Likaotique lik = Likaotique.getLik();
				if (args[1].equalsIgnoreCase("delay")) {
					if (Utils.isDouble(args[2])) {
						if (Double.parseDouble(args[2])==0d)
							args[2] = "1";
						lik.getTimer().setDelay((int) Double.parseDouble(args[2])*1000);
						lik.setDelay((int) Math.round(Double.parseDouble(args[2])*1000));
						Utils.addChat("??aDelay du Likaotique chang?? ?? "+args[2]+"sec !");
					}											
				} else if (args[1].equalsIgnoreCase("radius")) {
					if (Utils.isInteger(args[2])) {
						lik.setRadius(Integer.parseInt(args[2]));
						Utils.addChat("??aRadius du Likaotique chang?? ?? "+args[2]+" !");
					}
				} else if (args[1].equalsIgnoreCase("safe")) {
					if (!lik.isSafe()) {
						Utils.addChat("??aMode safe du Likaotique activ?? !");
					} else {
						Utils.addChat("??cMode safe du Likaotique desactiv?? !");
					}
					lik.setSafe(!lik.isSafe());
				}
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"search")) {
				if (args[1].equalsIgnoreCase("add")) {
					try {
						if (Utils.isInteger(args[2])) {
							int x = Integer.parseInt(args[2]);						
							Block b = Block.getBlockById(x);
							
							if (b==null) {
								Utils.addChat("??cCe bloc n'existe pas !");			
							} else if (b!=Search.getSearch().getSearchBlock()) {
								Search.getSearch().setSearchBlock(b);
								Utils.addChat("??aLe bloc "+b.getLocalizedName()+" est cherch?? !");
								Search.getSearch().refresh();
							} else 
								Utils.addChat("??cLe bloc "+b.getLocalizedName()+" est d??j?? cherch?? !");	
						} else if (Utils.isABlock(args[2])) {					
							Block b = Block.getBlockFromName(args[2]);
							if (b==null) {
								Utils.addChat("??cCe bloc n'existe pas !");			
							} else if (b!=Search.getSearch().getSearchBlock()) {
								Search.getSearch().setSearchBlock(b);
								Utils.addChat("??aLe bloc "+b.getLocalizedName()+" est cherch?? !");
								Search.getSearch().refresh();
							} else 
								Utils.addChat("??cLe bloc "+b.getLocalizedName()+" est d??j?? cherch?? !");	
						} else {
							Utils.addError("Ceci n'est pas un bloc !\n??aSyntaxe correcte: "+var.prefixCmd+"search <ID de bloc> ou <nom du bloc>");
						}
						
					} catch (Exception e) {
						Utils.addChat(err);
					}												
				} else if (args[1].equalsIgnoreCase("clear")) {
					Utils.addChat("??aBloc cherch?? clear");
					Search.getSearch().setSearchBlock(null);
					Search.getSearch().refresh();
				}
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"nuker")) {
				if (args[1].equalsIgnoreCase("add")) {
					try {
						if (Utils.isInteger(args[2])) {
							int x = Integer.parseInt(args[2]);						
							int c=0;
							for (int i=0;i<Nuker.nuke.size();i++) {
								if (Nuker.nuke.get(i)==x) {
									c++;
								}
							}
							if (c!=0) {
								Utils.addChat("??cLe bloc "+Block.getBlockById(x).getLocalizedName()+" a d??j?? ??t?? ajout?? !");									
							} else {
								Nuker.nuke.add(x);
								Utils.addChat("??aLe bloc "+Block.getBlockById(x).getLocalizedName()+" a ??t?? ajout?? !");
							}
						} else {					
							int c=0;
							if (Utils.isABlock(args[2])) {
								for (int i=0;i<Nuker.nuke.size();i++) {
									if (Block.getBlockById(Nuker.nuke.get(i)).getLocalizedName()==Block.getBlockFromName(args[2]).getLocalizedName()) {
										c++;
									}
								}
								if (c!=0) {
									Utils.addChat("??cLe bloc "+Block.getBlockById(Block.getIdFromBlock(Block.getBlockFromName(args[2]))).getLocalizedName()+" a d??j?? ??t?? ajout?? !");									
								} else {
									Nuker.nuke.add(Block.getIdFromBlock(Block.getBlockFromName(args[2])));
									Utils.addChat("??aLe bloc "+Block.getBlockById(Block.getIdFromBlock(Block.getBlockFromName(args[2]))).getLocalizedName()+" a ??t?? ajout?? !");
								}
							} else {
								Utils.addChat("??cCe bloc n'existe pas !");
							}
						}
						
					} catch (Exception e) {
						Utils.addChat(err);
					}
					Utils.saveNuker();												
			} else if (args[1].equalsIgnoreCase("rem")) {
				try {
					if (Utils.isInteger(args[2])) {
						int c=0;
						int x = Integer.parseInt(args[2]);
						for (int j=0;j<Nuker.nuke.size();j++) {
							if (Nuker.nuke.get(j)==x) {
								Nuker.nuke.remove(j);
								c++;									
							}
						}
						if (c!=0) {
							Utils.addChat("??aLe bloc "+Block.getBlockById(x).getLocalizedName()+" a ??t?? retir?? !");
						} else {
							Utils.addChat("??cLe bloc "+Block.getBlockById(x).getLocalizedName()+" n'est pas dans la liste !");
						}
					} else {
						int c=0;
						for (int j=0;j<Nuker.nuke.size();j++) {
							if (Block.getBlockById(Nuker.nuke.get(j)).getLocalizedName()==Block.getBlockFromName(args[2]).getLocalizedName()) {
								c++;
								Nuker.nuke.remove(j);
							}									
							
						}
						if (c!=0) {
							Utils.addChat("??aLe bloc "+Block.getBlockById(Block.getIdFromBlock(Block.getBlockFromName(args[2]))).getLocalizedName()+" a ??t?? retir?? !");
						} else {
							Utils.addChat("??cLe bloc "+Block.getBlockById(Block.getIdFromBlock(Block.getBlockFromName(args[2]))).getLocalizedName()+" n'est pas dans la liste !");
						}
					}
				} catch (Exception e) {
					Utils.addChat(err);
				}
				Utils.saveNuker();	
			} else if (args[1].equalsIgnoreCase("range")) {
				try {
					if(Double.parseDouble(args[2]) > 6) {
						Utils.addChat("??cLa range doit ??tre entre 1 et 6.");
						return;
					}
					Nuker.nukerRadius=Double.parseDouble(args[2]);
					SettingsUtil.setNukerRadius(Double.parseDouble(args[2]));
					Utils.addChat("??aLa range du Nuker a ??t?? chang??e ?? "+args[2]+" !");
				} catch (Exception e) {
					Utils.addChat(err);
				}
			} else if (args[1].equalsIgnoreCase("clear")) {
				try {
					Nuker.nuke.clear();
					Utils.addChat("??aListe vid??e !");
				} catch (Exception e) {
					Utils.addChat(err);
				}
			} else if (args[1].equalsIgnoreCase("safe")) {
				if (Nuker.safe) {
					Utils.addChat("??cNuker safe d??sactiv?? !");
				} else {
					Utils.addChat("??aNuker safe activ?? !");
				}
				Nuker.safe=!Nuker.safe;
				SettingsUtil.setNukerSafe(Nuker.safe);
			} else if (args[1].equalsIgnoreCase("oneHit")) {
				if (Nuker.onehit) {
					Utils.addChat("??cNuker OneHit d??sactiv?? !");
				} else {
					Utils.addChat("??aNuker OneHit Activ?? !");
				}
				Nuker.onehit=!Nuker.onehit;
				SettingsUtil.setNukerOneHit(Nuker.onehit);
			} else if (args[1].equalsIgnoreCase("list")) {
				try {
					if (Nuker.nuke.size()==0) {
						Utils.addChat("??cAucun blocs ajout??s");
					} else {
						Collections.sort(Nuker.nuke);
						Utils.addChat(Utils.sep2+"??6Nuker"+Utils.sep2);
						for (int j=0;j<Nuker.nuke.size();j++) {
							Utils.addChat("Block : "+Block.getBlockById(Nuker.nuke.get(j)).getLocalizedName());
						}
						Utils.addChat(Utils.sep);
					}
				} catch (Exception e) {
					Utils.addChat(err);
				}
			}
				Utils.checkXp(xp);
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"dropshit") || args[0].equalsIgnoreCase(var.prefixCmd+"drop")) {
				DropShit sh = DropShit.getShit();
				if (args.length==1) {
				} else if (args[1].equalsIgnoreCase("add")) {
					try {
						if (Utils.isInteger(args[2])) {
							int x = Integer.parseInt(args[2]);						
							int c=0;
							for (int i=0;i<sh.getList().size();i++) {
								if (sh.getList().get(i)==x) {
									c++;
								}
							}
							if (c!=0) {
								Utils.addChat("??cL'item "+Item.getItemById(x).getUnlocalizedName().replaceFirst("tile.", "")+" a d??j?? ??t?? ajout?? !");									
							} else {
								DropShit.getShit().getList().add(x);
								Utils.addChat("??aL'item "+Item.getItemById(x).getUnlocalizedName().replaceFirst("tile.", "")+" a ??t?? ajout?? !");
							}
						} else {					
							int c=0;
							if (Utils.isAItem(args[2])) {
								for (int i=0;i<sh.getList().size();i++) {
									if (Item.getItemById(sh.getList().get(i))==Item.getByNameOrId(args[2])) {
										c++;
									}
								}
								if (c!=0) {
									Utils.addChat("??cL'item "+Item.getItemById(Item.getIdFromItem(Item.getByNameOrId(args[2]))).getUnlocalizedName().replaceFirst("tile.", "")+" a d??j?? ??t?? ajout?? !");									
								} else {
									sh.getList().add(Item.getIdFromItem(Item.getByNameOrId(args[2])));
									Utils.addChat("??aL'item "+Item.getItemById(Item.getIdFromItem(Item.getByNameOrId(args[2]))).getUnlocalizedName().replaceFirst("tile.", "")+" a ??t?? ajout?? !");
								}
							} else {
								Utils.addChat("??cCet item n'existe pas !");
							}
						}
						
					} catch (Exception e) {
						Utils.addChat(err);
					}											
			} else if (args[1].equalsIgnoreCase("clear")) {
				try {
					sh.getList().clear();
					Utils.addChat("??aListe vid??e !");
				} catch (Exception e) {
					Utils.addChat(err);
				}
			} else if (args[1].equalsIgnoreCase("all")) {
				for (int i = 0; i < 45; i++) {
					if (mc.thePlayer.inventoryContainer.getSlot(i).getStack() != null) {
						mc.playerController.windowClick(0, i, 0, 0, mc.thePlayer);
			        	mc.playerController.windowClick(0, -999, 0, 0, mc.thePlayer);
					}
				}
			} else if (args[1].equalsIgnoreCase("rem") || args[1].equalsIgnoreCase("rm") || args[1].equalsIgnoreCase("del")) {
				try {
					if (Utils.isInteger(args[2])) {
						int c=0;
						int x = Integer.parseInt(args[2]);
						for (int j=0;j<sh.getList().size();j++) {
							if (sh.getList().get(j)==x) {
								sh.getList().remove(j);
								c++;									
							}
						}
						if (c!=0) {
							Utils.addChat("??aL'item "+Item.getItemById(x).getUnlocalizedName().replaceFirst("tile.", "")+" a ??t?? retir?? !");
						} else {
							Utils.addChat("??cL'item "+Item.getItemById(x).getUnlocalizedName().replaceFirst("tile.", "")+" n'est pas dans la liste !");
						}
					} else {
						int c=0;
						for (int j=0;j<sh.getList().size();j++) {
							if (Item.getItemById(sh.getList().get(j))==Item.getByNameOrId(args[2])) {
								c++;
								sh.getList().remove(j);
							}									
							
						}
						if (c!=0) {
							Utils.addChat("??aL'item "+Item.getItemById(Item.getIdFromItem(Item.getByNameOrId(args[2]))).getUnlocalizedName().replaceFirst("tile.", "")+" a ??t?? retir?? !");
						} else {
							Utils.addChat("??cL'item "+Item.getItemById(Item.getIdFromItem(Item.getByNameOrId(args[2]))).getUnlocalizedName().replaceFirst("tile.", "")+" n'est pas dans la liste !");
						}
					}
				} catch (Exception e) {
					Utils.addChat(err);
				}
			} else if (args[1].equalsIgnoreCase("list")) {
				try {
					if (sh.getList().size()==0) {
						Utils.addChat("Aucun items ajout??s");
					} else {
						Utils.addChat(Utils.sep2+"??6DropShit"+Utils.sep2);
						for (int j=0;j<sh.getList().size();j++) {
							Utils.addChat("Item "+(j+1)+" : "+Item.getItemById(sh.getList().get(j)).getUnlocalizedName().replaceFirst("tile.", ""));
						}
						Utils.addChat(Utils.sep);
					}
				} catch (Exception e) {
					Utils.addChat(err);
				}
			}
				Utils.checkXp(xp);
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"bind")) {
				if (args.length==1) {
					Utils.addChat("??cUtilisation correcte: "+var.prefixCmd+"bind <cheat> <touche>");
					mc.thePlayer.playSound("mob.villager.haggle", 1.0F, 1.0F);
				} else if (args.length==2) {
					Utils.addChat("??cUtilisation correcte: "+var.prefixCmd+"bind <cheat> <touche>");
					mc.thePlayer.playSound("mob.villager.haggle", 1.0F, 1.0F);
				} else {
					try {
						Utils.setBind(args[1], args[2].toUpperCase());
						Utils.saveBind();
					} catch (Exception e) {
                        Utils.addChat(err);
                    }
				}
				Utils.checkXp(xp);
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"warn")) {
				if (args.length==1) {
					if (Utils.warn) {
						Utils.warn=false;
						Utils.addChat("??cWarn d??sactiv??");
					} else {
						Utils.warn=true;
						Utils.addChat("??aWarn activ?? ?? "+Utils.warnB+" blocs");
					}
				} else {
					try {
						Utils.warnB=Double.parseDouble(args[1]);
					
						if (Utils.warn) {
							Utils.warn=false;
							Utils.addChat("??cWarn d??sactiv??");
						} else {
							Utils.warn=true;
							Utils.addChat("??aWarn activ?? ?? "+Utils.warnB+" blocs");
						}
					} catch (Exception e) {
                        Utils.addChat(err);
                    }
				}
				Utils.checkXp(xp);						
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"radar") || args[0].equalsIgnoreCase(var.prefixCmd+"r")) {
				if (args.length==1) {}
				else if (args[1].equalsIgnoreCase("fr") || args[1].equalsIgnoreCase("friend") || args[1].equalsIgnoreCase("friends")) {
					if (Radar.fr) {
						Radar.fr=false;
						SettingsUtil.setRadarFriends(false);
						Utils.addChat("??cAffichage des friends dans le radar d??sactiv??");
					} else {
						Radar.fr=true;
						SettingsUtil.setRadarFriends(true);
						Utils.addChat("??aAffichage des friends dans le radar activ??");
					}
				}
				else if (args[1].equalsIgnoreCase("map") || args[1].equalsIgnoreCase("minimap")) {
					if (Radar.radarMap) {
						Radar.radarMap = false;
						SettingsUtil.setRadarMinimap(false);
						Utils.addChat("??cAffichage du Radar Map d??sactiv??");
					} else {
						Radar.radarMap = true;
						SettingsUtil.setRadarMinimap(true);
						Utils.addChat("??cAffichage du Radar Map activ??");
					}
				}
				else if (args[1].equalsIgnoreCase("text") || args[1].equalsIgnoreCase("textuel")) {
					if (Radar.radarText) {
						Radar.radarText = false;
						SettingsUtil.setRadarText(false);
						Utils.addChat("??cAffichage du Radar textuel d??sactiv??");
					} else {
						Radar.radarText = true;
						SettingsUtil.setRadarText(true);
						Utils.addChat("??cAffichage du Radar textuel activ??");
					}
				}
				else if (args[1].equalsIgnoreCase("joueurs") || args[1].equalsIgnoreCase("player") || args[1].equalsIgnoreCase("joueur")) {
					if (Radar.BoolENEMIES) {
						Radar.BoolENEMIES = false;
						SettingsUtil.setRadar_Enemies(false);
						Utils.addChat("??cAffichage des joueurs d??sactiv?? sur le radar");
					} else {
						Radar.BoolENEMIES = true;
						SettingsUtil.setRadar_Enemies(true);
						Utils.addChat("??cAffichage des joueurs activ?? sur le radar");
					}
				}
				else if (args[1].equalsIgnoreCase("mobs")  || args[1].equalsIgnoreCase("monstres")) {
					if (Radar.BoolMOBS) {
						Radar.BoolMOBS = false;
						SettingsUtil.setRadar_Mobs(false);
						Utils.addChat("??cAffichage des mobs d??sactiv?? sur le radar");
					} else {
						Radar.BoolMOBS = true;
						SettingsUtil.setRadar_Mobs(true);
						Utils.addChat("??cAffichage des mobs activ?? sur le radar");
					}
				}
				else if (args[1].equalsIgnoreCase("animals")  || args[1].equalsIgnoreCase("animaux")) {
					if (Radar.BoolANIMALS) {
						Radar.BoolANIMALS = false;
						SettingsUtil.setRadar_Animals(false);
						Utils.addChat("??cAffichage des animaux d??sactiv?? sur le radar");
					} else {
						Radar.BoolANIMALS = true;
						SettingsUtil.setRadar_Animals(true);
						Utils.addChat("??cAffichage des animaux activ?? sur le radar");
					}
				}
				else if (args[1].equalsIgnoreCase("Inpc")  || args[1].equalsIgnoreCase("golem")) {
					if (Radar.BoolGOLEM) {
						Radar.BoolGOLEM = false;
						SettingsUtil.setRadar_Golem(false);
						Utils.addChat("??cAffichage des Inpc et Golem d??sactiv??s sur le radar");
					} else {
						Radar.BoolGOLEM = true;
						SettingsUtil.setRadar_Golem(true);
						Utils.addChat("??cAffichage des Inpc et Golem activ??s sur le radar");
					}
				}
				else if (args[1].equalsIgnoreCase("NPC")  || args[1].equalsIgnoreCase("PNJ")) {
					if (Radar.boolNPC) {
						Radar.boolNPC = false;
						SettingsUtil.setRadar_NPC(false);
						Utils.addChat("??cAffichage des NPC d??sactiv?? sur le radar");
					} else {
						Radar.boolNPC = true;
						SettingsUtil.setRadar_NPC(true);
						Utils.addChat("??cAffichage des NPC activ?? sur le radar");
					}
				}
				Utils.checkXp(xp);						
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"nametag")) {
				if (args.length==1) {
				} else {
					try {
						Render.varNeko=Float.parseFloat(args[1]);
						Utils.addChat("??aLe nametag a ??t?? modifi?? ?? "+args[1]+" !");
					} catch (Exception e) {
                        Utils.addChat(err);
                    }
				}
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"shutdown")) {
				mc.shutdown();
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"rip")) {
				Utils.getRank("JP le sataniste").setLock(true);
				Utils.getLock2("--reach pvp").setLock(true);;
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"sword")) {
				if (Utils.isLock("--sword")) {
					Utils.addWarn("Sword");
					mc.thePlayer.playSound("mob.villager.no", 1.0F, 1.0F);
				} else if (Utils.sword) {
					Utils.sword=false;
					Utils.addChat("??cSword d??sactiv??");
				} else {
					Utils.sword=true;
					Utils.addChat("??aSword activ??");
				}
				Utils.checkXp(xp);
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"l") || args[0].equalsIgnoreCase(var.prefixCmd+"arraylist")) {
				if (args.length==1) {
				} else {
					try {
						if(args[1].equalsIgnoreCase("unicolor")) {
							if(args.length == 5) {
							int r = Integer.parseInt(args[2]),g = Integer.parseInt(args[3]),b=Integer.parseInt(args[4]);
							if((r > 255) || (r<0) || (g > 255) || (g<0) || (r > 255) || (r<0)) {
								Utils.addChat("??cLes nombres du ..araylist unicolor doivent ??tre situ??s entre 0 et 255.");
								return;
							}
							java.awt.Color c = new java.awt.Color(r, g, b);
							SettingsUtil.setArrayRed(c.getRed());
							SettingsUtil.setArrayGreen(c.getGreen());
							SettingsUtil.setArrayBlue(c.getBlue());
							Utils.addChat("??aCouleur de l'UniColor de l'ArrayList modifi??e en R:"+c.getRed()+" G:"+c.getGreen()+" B:"+c.getBlue());
							} else {
								Utils.addChat("??cUtilisation de la commande : ??6..arraylist UniColor Red Green Blue ??c(0-255)");
							}
						}
						else if(args[1].equalsIgnoreCase("ordre")) {
							if((args.length == 3) && ((args[2].equalsIgnoreCase("Alphab??tique") || (args[2].equalsIgnoreCase("Taille"))))) {
								SettingsUtil.setArrayOrdre(args[2].equalsIgnoreCase("Alphab??tique")?"Alphab??tique":"Taille");
							} else {
								Utils.addChat("??cUtilisation de la commande : ??6..arraylist ordre Alphab??tique/Taille");
							}
						}
						else if(args[1].equalsIgnoreCase("rangement")) {
							if((args.length == 3) && ((args[2].equalsIgnoreCase("Module") || (args[2].equalsIgnoreCase("M??langer"))))) {
								SettingsUtil.setArrayRangement(args[2].equalsIgnoreCase("Module")?"Module":"M??langer");
							} else {
								Utils.addChat("??cUtilisation de la commande : ??6..arraylist rangement Module/M??langer");
							}
						}
						else if(args[1].equalsIgnoreCase("inverser")) {
							if((args.length == 3) && ((args[2].equalsIgnoreCase("Oui") || (args[2].equalsIgnoreCase("Non"))))) {
								SettingsUtil.setArrayInvsers??(args[2].equalsIgnoreCase("Oui")?"Oui":"Non");
							} else {
								Utils.addChat("??cUtilisation de la commande : ??6..arraylist inverser Oui/Non");
							}
						}
						else if(args[1].equalsIgnoreCase("box")) {
							if((args.length == 3) && ((args[2].equalsIgnoreCase("Neko") || (args[2].equalsIgnoreCase("Name") || (args[2].equalsIgnoreCase("Sans")))))) {
								if(args[2].equalsIgnoreCase("Neko")) {
									SettingsUtil.setArrayShadowBox("Neko Box");
								} else {
									SettingsUtil.setArrayShadowBox(args[2].equalsIgnoreCase("Name")?"Name Box":"Sans Box");
								}
							} else {
								Utils.addChat("??cUtilisation de la commande : ??6..arraylist box Neko/Name/Sans");
							}
						}
						else if(args[1].equalsIgnoreCase("color")) {
							if((args.length == 3) && ((args[2].equalsIgnoreCase("rainbow") || (args[2].equalsIgnoreCase("unicolor") || (args[2].equalsIgnoreCase("basique")))))) {
								if(args[2].equalsIgnoreCase("Rainbow")) {
									SettingsUtil.setColorArray("Rainbow");
								} else {
									SettingsUtil.setColorArray(args[2].equalsIgnoreCase("unicolor")?"UniColor":"Basique");
								}
							} else {
								Utils.addChat("??cUtilisation de la commande : ??6..arraylist color Rainbow/UniColor/Basique");
							}
						} else {
							Utils.addChat("??cLa commande: ??6..arraylist "+args[1]+ "??c n'existe pas. Voici les commandes disponibles.");
							Utils.addChat(Utils.sep);
							Utils.addChat2("??6"+var.prefixCmd+"ArrayList UniColor <Red> <Green> <Blue> (0-255)", var.prefixCmd+"arraylist unicolor 100 100 100", "??7Modifie la couleur du mode UniColor de l'ArrayList", false, Chat.Summon);
							Utils.addChat2("??6"+var.prefixCmd+"ArrayList Ordre <Alphab??tique:Taille>", var.prefixCmd+"ArrayList ordre Alphab??tique", "??7Modifie l'ordre de l'ArrayList (Alphab??tique ou Taille de noms)", false, Chat.Summon);
							Utils.addChat2("??6"+var.prefixCmd+"ArrayList Rangement <Module:M??langer>", var.prefixCmd+"ArrayList rangement M??langer", "??7Modifie le type de rangement de l'ArrayList (Par modules ou m??langer)", false, Chat.Summon);
							Utils.addChat2("??6"+var.prefixCmd+"ArrayList Inverser <Oui:Non>", var.prefixCmd+"ArrayList Inverser non", "??7Inverse l'ArrayList ou non (Punkeel-HUD-Autonyah) deviendrait (Autonyah-HUD-Punkeel)", false, Chat.Summon);
							Utils.addChat2("??6"+var.prefixCmd+"ArrayList Box <Neko:Name:Sans>", var.prefixCmd+"ArrayList box name", "??7Affiche ou non des Shadow Box derri??re les noms de Modules. (Neko=Grande box, Name=Box par modules, sans=sans box)", false, Chat.Summon);
							Utils.addChat2("??6"+var.prefixCmd+"ArrayList color <Rainbow:UniColor:Basique>", var.prefixCmd+"ArrayList color Basique", "??7Modifie la couleur utilis??e dans l'arraylist (Rainbow, UniColor ou Basique)", false, Chat.Summon);
							Utils.checkXp(xp);
							mc.ingameGUI.getChatGUI().addToSentMessages(var3);
						}
					} catch (Exception e) {
                        Utils.addChat(err);
                    }
				}
				Utils.checkXp(xp);
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"autoarmor") || args[0].equalsIgnoreCase(var.prefixCmd+"aa")) {
				if (args.length==1) {
				} else if (args[1].equalsIgnoreCase("ec")) {
						if (Autoarmor.ec) {
							Autoarmor.ec=!Autoarmor.ec;
							Utils.addChat("??cL'autoarmor est d??sactiv?? sur Epicube !");
						} else {
							Autoarmor.ec=!Autoarmor.ec;
							Utils.addChat("??aL'autoarmor est activ?? sur Epicube !");
						}
				}
				Utils.checkXp(xp);
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
		}
		doCommand2();
	}
		public void doCommand2() {
			if (var3.startsWith(var.prefixCmd) && Utils.verif==null) {
			if (args[0].equalsIgnoreCase(var.prefixCmd+"highjump")) {
				if (Utils.isFloat(args[1])) {
					try {
						Highjump.getJump().setHeight(Float.parseFloat(args[1]));
						Utils.addChat("??aLe height du Highjump a ??t?? mise ?? "+args[1]+" !");
					} catch (Exception e) {
                        Utils.addChat(err);
                    }
				}
				Utils.checkXp(xp);
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"kick")) {
				String s = "Kick?? par Neko";
				if (args.length>=2) {
					s = args[1];
					for (int i=2;i<args.length;i++)
						s+=" "+args[i];
				}
				if (!mc.isSingleplayer())
					mc.theWorld.sendQuittingDisconnectingPacket(s);
				else
					mc.displayGuiScreen(new GuiMainMenu());
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"startquest")) {
				if (QuestManager.getQM().getCurrent()!=null && !QuestManager.getQM().isHasBegin()) {
					QuestManager.getQM().setHasBegin(true);
					Utils.addChat("??aD??fi accept?? !");
				}
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"clickaim")) {
				if (args.length==1) {
				} else if (Utils.isDouble(args[1])) {
					try {
						ClickAim.dist=Float.parseFloat(args[1]);
						Utils.addChat("??aLa port??e du ClickAim a ??t?? mise ?? "+args[1]+" !");
					} catch (Exception e) {
                        Utils.addChat(err);
                    }
				} else if (args[1].equalsIgnoreCase("multi") || args[1].equalsIgnoreCase("multiaura") || args[1].equalsIgnoreCase("ma")) {
					if (ClickAim.multiAura) {
						Utils.addChat("??cMultiAura d??sactiv?? !");
					} else {
						Utils.addChat("??aMultiAura sur le ClickAim activ?? !");
					}
					ClickAim.multiAura=!ClickAim.multiAura;
				}
				Utils.checkXp(xp);
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"clear")) {
				mc.ingameGUI.getChatGUI().clearChatMessages();
				Utils.checkXp(xp);						
			}
			

			if (args[0].equalsIgnoreCase(var.prefixCmd+"mcleaks") || args[0].equalsIgnoreCase(var.prefixCmd+"mcleak") || args[0].equalsIgnoreCase(var.prefixCmd+"mcl")) {
				if (args.length==1) {
					Utils.addChat("??cErreur, essayez "+var.prefixCmd+"mcleaks <token>");
					mc.thePlayer.playSound("mob.villager.haggle", 1.0F, 1.0F);
				} else {
					String token = args[1];
					 ModApi.redeem(token, new Callback<Object>(){
						 
				            @Override
				            public void done(Object o) {
				                if (o instanceof String) {
				                    Utils.addChat("??cErreur: " + o);
				                    return;
				                }
				                if (MCLeaks.savedSession == null) {
				                    MCLeaks.savedSession = Minecraft.getMinecraft().getSession();
				                }
				                RedeemResponse response = (RedeemResponse)o;
				                MCLeaks.refresh(response.getSession(), response.getMcName());
				                Utils.addChat("??aConnect?? en tant que "+MCLeaks.getMCName()+"!");
				                if (!Friends.isFriend(MCLeaks.getMCName()))
				    				Friends.friend.add(MCLeaks.getMCName());
				                Utils.lastAccount=0;
				            }
				        });
				}
				Utils.checkXp(xp);
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"login")) {
				if (args.length==1) {
					Utils.addChat(error);
				} else if (args.length==2) {
					if (MCLeaks.isAltActive()) {
						MCLeaks.remove();
					}						
					try {
						mc.session = new Session(args[1], "", "", "mojang");
						Utils.addChat("??aConnect?? en tant que "+mc.session.getUsername()+" !");
						Utils.addChat("??aVeuillez deco-reco pour le changement");						
						Utils.lastAccount=0;
					} catch (Exception e) {
						Utils.addChat(error);
					}
				} else if (args.length>=3) {
					if (MCLeaks.isAltActive()) {
						MCLeaks.remove();
					}
					String mdp="";
					if (args.length>3) {
						for (int o=2;o<args.length;o++) {
							if (o!=args.length-1)
								mdp+=args[o] + " ";
							else
								mdp+=args[o];
						}
					} else {
						mdp=args[2];
					}
					LoginThread l = new LoginThread(args[1], mdp);
					l.start();
					Utils.lastAccount=0;
				}
				Utils.checkXp(xp);
			}		
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"log")) {
				if (args.length==1) {
					Utils.addChat(error);
				} else if (args[1].equalsIgnoreCase("add")) {
					try {
						String user = args[2];
						String mdp="";
						if (args.length>3) {
							for (int o=3;o<args.length;o++) {
								if (o!=args.length-1)
									mdp+=args[o] + " ";
								else
									mdp+=args[o];
							}
						} else if (args.length==4) {
							mdp=args[3];
						}
						if (args.length==3 && args[2].contains(":")) {
							String str[] = args[2].split(":");
							user = str[0];
							mdp= str[1];
						}
												
						Utils.saveAccount(user, mdp);
						ArrayList<String> s = Utils.getAllAccount();
						Utils.addChat("??aCompte N??"+s.size()+" ajout?? !");
					} catch (Exception e) {Utils.addChat("??cErreur d'ajout de compte");}							
				} else if (args[1].equalsIgnoreCase("list")) {
					try {
						Utils.displayAccount();
					} catch (Exception e) {Utils.addChat("??cErreur, pas r??ussi ?? afficher les comptes");}
				} else if (args[1].equalsIgnoreCase("delete") || args[1].equalsIgnoreCase("remove") || args[1].equalsIgnoreCase("del") || args[1].equalsIgnoreCase("rm")) {
					if (args.length==3) {
						try {
						Utils.deleteAccount(Integer.parseInt(args[2]));
						Utils.addChat("??aVous avez supprim?? le compte N??"+args[2]);
						} catch (Exception e) {
							Utils.addChat("??cErreur");
						}
					}							
				} else {
					if (MCLeaks.isAltActive()) {
						MCLeaks.remove();
					}
					try {
						String t = Utils.getAccount(Integer.parseInt(args[1]));
						String [] tab = t.split(" ");
						String mdp="";
						if (tab.length>2) {
							for (int k=1;k<tab.length;k++) {
								if (k!=(tab.length-1)) {
									mdp+=tab[k]+" ";
								} else {
									mdp+=tab[k];
								}
							}
						} else if (tab.length==2) {
							mdp = tab[1];
						}
						LoginThread l = new LoginThread(tab[0], mdp);
						l.start();
						Utils.lastAccount=Integer.parseInt(args[1]);
					} catch (Exception e) {Utils.addChat("??cErreur, ce compte n'est pas disponible");}
				}
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"tppos")) {
				if (Utils.isLock("--tppos")) {
					Utils.addWarn("tppos");
					mc.thePlayer.playSound("mob.villager.no", 1.0F, 1.0F);
				} else if (args.length<=3) {
					Utils.addChat(error);
				} else {
					Minecraft.getMinecraft().thePlayer.posY+=1.0F;
					try {
						double x = (args[1].contains("~") ? mc.thePlayer.posX+(args[1].replace("~", "").equals("") ? 0.0 : Double.parseDouble(args[1].replace("~", ""))) : Double.parseDouble(args[1]));
						double y = (args[2].contains("~") ? mc.thePlayer.posY+(args[2].replace("~", "").equals("") ? 0.0 : Double.parseDouble(args[2].replace("~", ""))) : Double.parseDouble(args[2]));
						double z = (args[3].contains("~") ? mc.thePlayer.posZ+(args[3].replace("~", "").equals("") ? 0.0 : Double.parseDouble(args[3].replace("~", ""))) : Double.parseDouble(args[3]));						
						EntityWitch en = new EntityWitch(mc.theWorld);
						en.setPosition(x, y, z);
                		TpUtils tp = new TpUtils();
                		Vector<Double> vl = tp.getTargetInPos(new BlockPos(en));
                		tp.doTpAller(en, vl.get(0), vl.get(1), vl.get(2), true, tp.getK(new BlockPos(en)));
                    	Utils.addChat("??aVous vous ??tes tppos !");
					} catch (Exception e) {
                        Utils.addChat(err);
                    }
                	Utils.checkXp(xp);
				}
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"checkpos")) {
				int x = 0;
				int z = 0;
				boolean valid = false;
				if (args.length==2) {
					try {
						if (args[1].startsWith("[") && args[1].endsWith("]")) {
							String[] l = args[1].replaceAll("\\[", "").replaceAll("\\]", "").split(",");
							x = Integer.parseInt(l[0]);
							z = Integer.parseInt(l[1]);
							valid = true;
						}
					} catch (Exception e) {
						Utils.addError("Si vous utilisez 2 arguments seulement, vous devez ??crire comme ceci: "+var.prefixCmd+"checkpos [x,z]");
					}
				}
				if (args.length==4 && Utils.isInteger(args[1]) && Utils.isInteger(args[2])) {
					x = Integer.parseInt(args[1]);
					z = Integer.parseInt(args[2]);
					valid = true;
				}
				mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(x, mc.thePlayer.getPosition().getY(), z, true));
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"findclaim")) {
				int baseChunk = 0;
				int maxChunkX = 100;
				int maxChunkZ = 100;
				int packetSec = 60;
				Utils.ignoreFaction = "";
				Utils.claimLog = "";
				Utils.chatRegex = "";
				Utils.chatCC = false;
				int startpointX = 0;
				int startpointZ = 0;
				
				for (int in=1;in<args.length;in++) {
					String a = args[in];
					
					if (Utils.currentClaimFinder!=null && a.equalsIgnoreCase("stop")) {
						Utils.currentClaimFinder.interrupt();
						Utils.addChat("??aClaim finder stopp?? !");
						mc.ingameGUI.getChatGUI().addToSentMessages(var3);
						Utils.claimLog = "";
						Utils.claimFinderBar = "";						
						return;
					}
					
					if (a.startsWith("basechunk=") && Utils.isInteger(a.replaceFirst("basechunk=", ""))) {
						baseChunk = Integer.parseInt(a.replaceFirst("basechunk=", ""));
					}
					
					if (a.startsWith("maxchunk=")) {
						if (Utils.isInteger(a.replaceFirst("maxchunk=", ""))) {
							maxChunkX = Integer.parseInt(a.replaceFirst("maxchunk=", ""));
							maxChunkZ = Integer.parseInt(a.replaceFirst("maxchunk=", ""));
						}
						if (a.replaceFirst("maxchunk=", "").contains(",")) {
							String [] str2 = a.replaceFirst("maxchunk=", "").split(",");
							maxChunkX = Integer.parseInt(str2[0]);
							maxChunkZ = Integer.parseInt(str2[1]);
						}
					}
					
					if (a.startsWith("packetsec=") && Utils.isInteger(a.replaceFirst("packetsec=", ""))) {
						packetSec = Integer.parseInt(a.replaceFirst("packetsec=", ""));
					}
					
					if (a.startsWith("ignore=")) {
						Utils.ignoreFaction = a.replaceFirst("ignore=", "");
					}
					
					if (a.startsWith("chat=")) {
						Utils.chatCC = true;
						Utils.chatRegex = a.replaceFirst("chat=", "").replaceAll("_", " ");
					}
					
					if (a.startsWith("startchunk=")) {
						try {
						String [] str2 = a.replaceFirst("startchunk=", "").split(",");
						startpointX = Integer.parseInt(str2[0])*16;
						startpointZ = Integer.parseInt(str2[1])*16;
						} catch (Exception e) {
							Utils.addError("Erreur, le format doit ??tre: startchunk=Chunk X, Chunk Z");
						}
					}
				}
				
				int bc = baseChunk;
				int mchX = maxChunkX;
				int mchZ = maxChunkZ;
				int ps = packetSec;
				int spX = startpointX;
				int spZ = startpointZ;
				
				Utils.currentClaimFinder = new Thread(new Runnable() {

					@Override
					public void run() {
						try {
							int x = 16*bc+spX;
							int z = 16*bc+spZ;
							int xmin = -16*bc+spX;
							int zmin = -16*bc+spZ;
							
							int xtmp = x;
							for (int i=0;i<=mchX;i++) {
								xtmp+=16;
								
								
								Utils.claimFinderBar = "??6Scan 1/"+(bc==0 ? "2" : "4")+": ??a"+Math.round((i/((float) (mchX))*100))+"%";
								
								int ztmp = spZ+Math.round(-16*(mchZ+bc/2));
								for (int j=0;j<=(mchZ*2+bc);j++) {
									ztmp+=16;
									Thread.sleep((long) Math.round(1000/ps));
									mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(xtmp, mc.thePlayer.getPosition().getY(), ztmp, true));
									Utils.claimLog += "["+xtmp+","+ztmp+"]\n";
								}
							}
							
							int xtmp2 = xmin;
							for (int i=0;i<=mchX;i++) {
								xtmp2-=16;
								
								
								Utils.claimFinderBar = "??6Scan 2/"+(bc==0 ? "2" : "4")+": ??a"+Math.round((i/((float) (mchX))*100))+"%";
								
								int ztmp = spZ+Math.round(-16*(mchZ+bc/2));
								for (int j=0;j<=(mchZ*2+bc);j++) {
									ztmp+=16;
									Thread.sleep((long) Math.round(1000/ps));
									mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(xtmp2, mc.thePlayer.getPosition().getY(), ztmp, true));
									Utils.claimLog += "["+xtmp2+","+ztmp+"]\n";
								}
							}		
							
							if (bc>0) {
							
								int ztmp = z;
								for (int i=0;i<=mchZ;i++) {
									ztmp+=16;
									
									
									Utils.claimFinderBar = "??6Scan 3/4: ??a"+Math.round((i/((float) (mchZ))*100))+"%";
									
									int xtmp3 = spX-16*(bc/2);
									for (int j=0;j<=bc;j++) {
										xtmp3+=16;
										
										Thread.sleep((long) Math.round(1000/ps));
										mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(xtmp3, mc.thePlayer.getPosition().getY(), ztmp, true));
										Utils.claimLog += "["+xtmp3+","+ztmp+"]\n";
									}
								}
								
								ztmp = zmin;
								for (int i=0;i<=mchZ;i++) {
									ztmp-=16;
									
									
									Utils.claimFinderBar = "??6Scan 4/4: ??a"+Math.round((i/((float) (mchZ))*100))+"%";
									
									int xtmp4 = spX-16*(bc/2);
									for (int j=0;j<=bc;j++) {
										xtmp4+=16;
										
										Thread.sleep((long) Math.round(1000/ps));
										mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(xtmp4, mc.thePlayer.getPosition().getY(), ztmp, true));
										Utils.claimLog += "["+xtmp4+","+ztmp+"]\n";
									}
								}
							}
							
							String [] scratch = Utils.claimLog.split("\n");
							String filter = "";
							
							for (int i=0;i<scratch.length;i++) {
								String line = scratch[i];								
								
								if (line.startsWith("faction:")) {
									String tot = line+": ";
									for (int j=6;j<9;j++) {
										if (i-j>=0) {
											tot+=scratch[i-j]+" ";
										}
									}
									filter += tot+"\n";
								}
							}							
							
							Utils.addChat("Check claim copi?? dans le presse-papier !");
							Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection("Filter list:\n"+filter+"Raw list:\n"+Utils.claimLog), null);
							Utils.claimLog = "";
							Utils.claimFinderBar = "";
							
						} catch (Exception e) {}
					}
					
				});
				
				Utils.currentClaimFinder.start();
				
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"packet")) {
				if (args.length==2 && Utils.isInteger(args[1])) {
					int nb = Integer.parseInt(args[1]);
					for (int i=0;i<nb;i++)
						mc.thePlayer.sendQueue.addToSendQueue(new C04PacketPlayerPosition());
				}
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"phantom") || args[0].equalsIgnoreCase(var.prefixCmd+"ph")) {
				int posY = 1;
				int x = 0;
				int y = 0;
				int z = 0;
				boolean valid = false;
				if (args.length==2) {
					try {
						if (args[1].startsWith("[") && args[1].endsWith("]")) {
							String[] l = args[1].replaceAll("\\[", "").replaceAll("\\]", "").split(",");
							x = Integer.parseInt(l[0]);
							y = Integer.parseInt(l[1]);
							z = Integer.parseInt(l[2]);
							valid = true;
						}
					} catch (Exception e) {
						Utils.addError("Si vous utilisez 2 arguments seulement, vous devez ??crire comme ceci: "+var.prefixCmd+"phantom [x,y,z]");
					}
				}
				if (args.length==4 && Utils.isInteger(args[1]) && Utils.isInteger(args[2]) && Utils.isInteger(args[3])) {
					x = Integer.parseInt(args[1]);
					y = Integer.parseInt(args[2]);
					z = Integer.parseInt(args[3]);
					valid = true;
				}
				if (valid) {
					boolean nc = false;
					if (!Utils.isToggle("NoClip")) {
						Utils.toggleModule("NoClip");
						nc = true;
					}
					BlockPos bp = mc.thePlayer.getPosition();
					int pX = bp.getX();
					int pZ = bp.getZ();
					
					// Tp in y=3
					EntityWitch en = new EntityWitch(mc.theWorld);
					en.setPosition(pX, posY, pZ);
            		TpUtils tp = new TpUtils();
            		Vector<Double> vl = tp.getTargetInPos(new BlockPos(en));
            		tp.doTpAller(en, vl.get(0), vl.get(1), vl.get(2), true, tp.getK(new BlockPos(en)));
            		
            		// Tp in pos given
            		BlockPos target = new BlockPos(x, y, z);
            		int tX = target.getX();
            		int tY = target.getY();
            		int tZ = target.getZ();
					en.setPosition(tX, posY, tZ);
            		vl = tp.getTargetInPos(new BlockPos(en));
            		tp.doTpAller(en, vl.get(0), vl.get(1), vl.get(2), true, tp.getK(new BlockPos(en)));
            		
            		// Tp in pos given with Y
            		en.setPosition(tX, tY, tZ);
            		vl = tp.getTargetInPos(new BlockPos(en));
            		tp.doTpAller(en, vl.get(0), vl.get(1), vl.get(2), true, tp.getK(new BlockPos(en)));
            		
            		// Desactivate NoClip if not toggled
            		if (nc)
            			Utils.toggleModule("NoClip");
				}
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"fov")) {
				if (args.length==1) {
					Utils.addChat(error);
				} else {
					try {
						GameSettings.fovSetting = Float.parseFloat(args[1]);
						Utils.addChat("??aVous avez mis votre fov ?? "+args[1]);
					} catch (Exception e) {
                        Utils.addChat(err);
                    }
				}
				Utils.checkXp(xp);
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}			
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"freecam")) {
				if (args.length==1) {
				} else {
					try {
						Freecam.speed=Float.parseFloat(args[1]);
						Utils.addChat("??aSpeed du Freecam mis ?? "+args[1]);
					} catch (Exception e) {
                        Utils.addChat(err);
                    }
				}
				Utils.checkXp(xp);
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"plugins") || args[0].equalsIgnoreCase(var.prefixCmd+"pl")) {
				if (Utils.isLock("--plugins")) {
					Utils.addWarn("Plugins");
					mc.thePlayer.playSound("mob.villager.no", 1.0F, 1.0F);
				} else
					Utils.toggleModule("Plugins");
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"longjump")) {
				if (args.length==1) {
				} else if (args[1].equalsIgnoreCase("speed")) {
					try {
						Longjump.speed=Float.parseFloat(args[2]);
						Utils.addChat("??aSpeed du longjump mis ?? "+args[2]);
					} catch (Exception e) {
                        Utils.addChat(err);
                    }
				} else if (Utils.isFloat(args[1])) {
					try {
						Longjump.speed=Float.parseFloat(args[1]);
						Utils.addChat("??aSpeed du longjump mis ?? "+args[1]);
					} catch (Exception e) {
						
					}
				}
				Utils.checkXp(xp);
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"smoothaim")) {
				if (args.length==1) {
				} else if (args[1].equalsIgnoreCase("range")) {
					try {
						SmoothAim.range=Double.parseDouble(args[2]);
						Utils.addChat("??aRange du SmoothAim mise ?? "+args[2]);
					} catch (Exception e) {
                        Utils.addChat(err);
                    }
				} else if (args[1].equalsIgnoreCase("speed")) {
					try {
						SmoothAim.speed=(Double.parseDouble(args[2])<=0 ? 0.5 : Double.parseDouble(args[2]));
						Utils.addChat("??aSpeed du SmoothAim mise ?? "+args[2]);
					} catch (Exception e) {
                        Utils.addChat(err);
                    }
				} else if (args[1].equalsIgnoreCase("fov")) {
					try {
						SmoothAim.degrees=Double.parseDouble(args[2]);
						Utils.addChat("??aFov du SmoothAim mis ?? "+args[2]);
					} catch (Exception e) {
                        Utils.addChat(err);
                    }
				}
				Utils.checkXp(xp);
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"autosoup")) {
				if (args.length==1) {
				} else if (args[1].equalsIgnoreCase("heal")) {
					try {
						Autosoup.heal=Integer.parseInt(args[2]);
						Utils.addChat("??aHeal mis ?? "+args[2]);
					} catch (Exception e) {
                        Utils.addChat(err);
                    }
				} else if (args[1].equalsIgnoreCase("drop")) {
					if (Autosoup.drop) {
						Autosoup.drop=false;
						Utils.addChat("??cDrop de l'autosoup d??sactiv??");
					} else {
						Autosoup.drop=true;
						Utils.addChat("??aDrop de l'autosoup activ??");
					}
				}
				Utils.checkXp(xp);
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
			if(args[0].equalsIgnoreCase(var.prefixCmd+"tp")) {
				if(args.length<3) {
					Utils.addChat("??6..tp x y z");
				} else if (Utils.isDouble(args[1]) && Utils.isDouble(args[2]) && Utils.isDouble(args[3])) {
					try {
						Double x = Double.parseDouble(args[1]);
						Double y = Double.parseDouble(args[2]);
						Double z = Double.parseDouble(args[3]);
						mc.thePlayer.setPosition(x, y, z);
						Minecraft.getMinecraft().thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(x, y, z, true));
						mc.thePlayer.sendChatMessage("/sethome");
					} catch (Exception e) {
                        Utils.addChat(err);
                    }
				}
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"vclip")) {
				if (args.length==1) {
					Utils.addChat(error);
				} else if (Utils.isDouble(args[1])) {
					try {
						Double b = Double.parseDouble(args[1]);
						mc.thePlayer.setPosition(Minecraft.thePlayer.posX, Minecraft.thePlayer.posY+b, Minecraft.thePlayer.posZ);								
					} catch (Exception e) {
                        Utils.addChat(err);
                    }
				} else if (args[1].equalsIgnoreCase("+")) {
					BlockPos curr = mc.thePlayer.getPosition();
					curr = new BlockPos(curr.getX(), curr.getY()+2, curr.getZ());
					boolean firstBlock = false;
					for (int i = 2;i<14;i++) {
						Material m = mc.theWorld.getBlockState(curr).getBlock().getMaterial();
						if (!m.isReplaceable() && m != Material.air) {
							firstBlock = true;
						} else if (firstBlock) {
							m = mc.theWorld.getBlockState(new BlockPos(curr.getX(), curr.getY()+1, curr.getZ())).getBlock().getMaterial();
							if (m.isReplaceable() || m == Material.air) {
								mc.thePlayer.setPosition(Minecraft.thePlayer.posX, Minecraft.thePlayer.posY+i, Minecraft.thePlayer.posZ);
								break;
							}
						}
						curr = new BlockPos(curr.getX(), curr.getY()+1, curr.getZ());
					}
					if (!firstBlock) {
						mc.thePlayer.setPosition(Minecraft.thePlayer.posX, Minecraft.thePlayer.posY+13, Minecraft.thePlayer.posZ);
					}
				} else if (args[1].equalsIgnoreCase("-")) {
					BlockPos curr = mc.thePlayer.getPosition();
					curr = new BlockPos(curr.getX(), curr.getY()-1, curr.getZ());
					boolean firstBlock = false;
					boolean finish = false;
					for (int i = 2;i<14;i++) {
						Material m = mc.theWorld.getBlockState(curr).getBlock().getMaterial();
						if (!m.isReplaceable() && m != Material.air) {
							firstBlock = true;
						} else if (firstBlock) {
							m = mc.theWorld.getBlockState(new BlockPos(curr.getX(), curr.getY()-1, curr.getZ())).getBlock().getMaterial();
							if (m.isReplaceable() || m == Material.air) {
								for (int j = i;j<14;j++) {
									m = mc.theWorld.getBlockState(new BlockPos(curr.getX(), curr.getY()-1, curr.getZ())).getBlock().getMaterial();
									if (!m.isReplaceable() && m != Material.air) {
										mc.thePlayer.setPosition(Minecraft.thePlayer.posX, Minecraft.thePlayer.posY-j+1, Minecraft.thePlayer.posZ);
										finish = true;
										break;
									}
									curr = new BlockPos(curr.getX(), curr.getY()-1, curr.getZ());
								}
								if (!finish)
									mc.thePlayer.setPosition(Minecraft.thePlayer.posX, Minecraft.thePlayer.posY-i, Minecraft.thePlayer.posZ);
								finish = true;
								break;
							}
						}
						curr = new BlockPos(curr.getX(), curr.getY()-1, curr.getZ());						
					}
					if (!firstBlock) {
						mc.thePlayer.setPosition(Minecraft.thePlayer.posX, Minecraft.thePlayer.posY-13, Minecraft.thePlayer.posZ);
					}
				}
				Utils.checkXp(xp);
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"reflect")) {
				Reflect r = Reflect.getReflect();
				try {
					r.setPower(Float.parseFloat(args[1]));
					Utils.addChat("??aPuissance du Reflect mise ?? "+args[1]+" !");
				} catch (Exception e) {
					Utils.addChat(err);
				}
				Utils.checkXp(xp);
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"antiafk") || args[0].equalsIgnoreCase(var.prefixCmd+"afk")) {
				if (args.length==1) {
				} else {
					try {
						Integer b = Integer.parseInt(args[1]);
						Antiafk.getInstance().setSec(b);	
						Utils.addChat("Antiafk time mis ?? "+args[1]+" !");
					} catch (Exception e) {
                        Utils.addChat(err);
                    }
				}
				Utils.checkXp(xp);
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"autonyah") || args[0].equalsIgnoreCase(var.prefixCmd+"an")) {
				if (Utils.isLock("--autonyah")) {
					Utils.addWarn("Autonyah");
					mc.thePlayer.playSound("mob.villager.no", 1.0F, 1.0F);
				} else if (args.length==1) {
					if (Utils.nyah) {
						Utils.nyah=false;
						Utils.addChat("??cAutoNyah d??sactiv?? !");
					} else {
						Utils.nyah=true;
						Utils.addChat("??aAutoNyah activ?? !");
					}					
				} else if (args[1].equalsIgnoreCase("prefix") || args[1].equalsIgnoreCase("pre")){
					if (args.length>=3)
						if (args[2].equalsIgnoreCase("reset")) {
							Utils.nyahh="";
							Utils.addChat("Prefix de l'autonyah reset");
						} else {
							String n="";
							if (args.length>3) {
								for (int k=2;k<args.length;k++) {
									n+=args[k]+" ";
								}
							} else {
								n=args[2];
							}
							try {
								Utils.nyahh=n;
								Utils.addChat("??aPrefix du nyah mis ?? '"+n+"' !");
							} catch (Exception e) {
								Utils.addChat(err);
							}
						}
				} else if (args[1].equalsIgnoreCase("speed")) {							
					try {
						Utils.nyahSec=Double.parseDouble(args[2]);
						Utils.addChat("??aTemps entre chaque nyah mis ?? "+args[2]+"s !");
					} catch (Exception e) {
						Utils.addChat(err);
					}
				}
				Utils.checkXp(xp);
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"fire")) {
				if (args.length==1) {
				} else {
					try {
						Fire.p=Integer.parseInt(args[1]);
					} catch (Exception e) {
                        Utils.addChat(err);
                    }
				}
				Utils.checkXp(xp);
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"Water")) {
				if (args.length==1) {
				} else {
					try {
						Water.p=Integer.parseInt(args[1]);
					} catch (Exception e) {
                        Utils.addChat(err);
                    }
				}
				
				Utils.checkXp(xp);
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"autoclic") || args[0].equalsIgnoreCase(var.prefixCmd+"auto")) {
				if (args.length==1) {
				} else {
					try {		
						if (Integer.parseInt(args[1])<=0)
							args[1] = "1";
						AutoClic.cps=Integer.parseInt(args[1]);
						Utils.addChat("??aAutoClic mis ?? "+args[1]+" cps");
					} catch (Exception e) {
                        Utils.addChat(err);
                    }
				}
				
				Utils.checkXp(xp);
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			} 
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"push") || args[0].equalsIgnoreCase(var.prefixCmd+"pushup")) {
				if (args.length==1) {
				} else {
					try {								
						PushUp.getPush().setPacket(Integer.parseInt(args[1]));
						Utils.addChat("??aPacket PushUp mis ?? "+args[1]+" !");
					} catch (Exception e) {
                        Utils.addChat(err);
                    }
				}
				
				Utils.checkXp(xp);
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"Power")) {
				if (args.length==1) {
					Utils.toggleModule("Power");
				} else {
					try {		
						Power.p=Integer.parseInt(args[1]);
					} catch (Exception e) {
                        Utils.addChat(err);
                    }
				}
				
				Utils.checkXp(xp);
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"values") || args[0].equalsIgnoreCase(var.prefixCmd+"v")) {
				if (args.length<=1) {
					Utils.displayValues(null);
				} else {
					String s = args[1];
					String f = "";
					for (Module m : ModuleManager.ActiveModule) {
						boolean link = false;
						HashMap<Module, String> hm = ModuleManager.link;
						if (hm.containsKey(m)) {
							if (hm.get(m).contains(",")) {
								for (String str : hm.get(m).split(",")) {
									if (str.equalsIgnoreCase(s))
										link = true;
								}
							} else
								if (hm.get(m).equalsIgnoreCase(s))
									link = true;
						}
						if (link || m.getName().toLowerCase().equalsIgnoreCase(s)) {
							link = true;
							f = m.getName();
						}
					}
					if (args.length>1 && Utils.isModule(args[1]))
						Utils.displayValues(args[1]);
				}
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"nekochat") || args[0].equalsIgnoreCase(var.prefixCmd+"chat")) {
				if (args.length==1) {
				} else if (args[1].equalsIgnoreCase("color")) {
					try {
						int color = NekoChat.getChat().getColor();
						if (args.length>=6) {
							java.awt.Color c = new java.awt.Color(Integer.parseInt(args[2]), Integer.parseInt(args[3]), Integer.parseInt(args[4]), Integer.parseInt(args[5]));
							color = c.getRGB();
						} else if (args.length==5) {
							java.awt.Color c = new java.awt.Color(Integer.parseInt(args[2]), Integer.parseInt(args[3]), Integer.parseInt(args[4]));
							color = c.getRGB();
						}
						if (args.length==2) {
							Utils.addChat("??cErreur, syntaxe correcte: \n"+Utils.setColor(var.prefixCmd+"chat color <Rouge 0-255> <Vert 0-255> <Bleu 0-255> <Transparence 0-255> Si la transparence n'est pas mise elle est par d??faut ?? 255", "??c"));
						} else {
							NekoChat.getChat().setColor(color);
							Utils.addChat("??aCouleur du NekoChat mis ?? "+color+" !");
							GuiNewChat chat = new GuiNewChat(mc);
						}
					} catch (Exception e) {
						Utils.addChat(err);
					}
				} else if (args[1].equalsIgnoreCase("height") && args.length>=3) {
					try {
						NekoChat.getChat().setHeight(Float.parseFloat(args[2]));
						Utils.addChat("??aHauteur du NekoChat mis ?? "+args[2]+" !");
					} catch (Exception e) {
						Utils.addChat(err);
					}
				} else if (args[1].equalsIgnoreCase("width") && args.length>=3) {
					try {
						NekoChat.getChat().setWidth(Float.parseFloat(args[2]));
						Utils.addChat("??aLargeur du NekoChat mis ?? "+args[2]+" !");
					} catch (Exception e) {
						Utils.addChat(err);
					}
				}
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"rankmanager") || args[0].equalsIgnoreCase(var.prefixCmd+"rm")) {
				if (Utils.isLock("--rankmanager")) {
					Utils.addWarn("RankManager");
				} else if (args.length==1) {
					Utils.addChat("??6=??b-??6=??b-??6=??b-??7 RankManager ??b-??6=??b-??6=??b-??6=");
					Utils.addChat("??cRangs d??bloqu??s: ??7"+Utils.getNbRankUnlock()+"??8/??7"+ModuleManager.rang.size());
					Utils.addChat(var.prefixCmd+"rankmanager list:??7 Liste de vos rangs obtenus");
					Utils.addChat(var.prefixCmd+"rankmanager choose:??7 "+Utils.setColor("Choisis un rang obtenus dans votre liste", "??7"));
					Utils.addChat(var.prefixCmd+"rankmanager dochange:??7 "+Utils.setColor("Active/d??sactive le changement de rang quand vous en obtenez un, de base ??aactif", "??7"));
					Utils.addChat(var.prefixCmd+"rankmanager all:??7 "+Utils.setColor("Affiche tous les rangs d??bloqu??s par raret??", "??7"));
				} else if (args[1].equalsIgnoreCase("dochange")) {
					if (Utils.changeRank) {
						Utils.addChat("??cChangement de rang automatique d??sactiv?? !");
					} else {
						Utils.addChat("??aChangement de rang automatique activ?? !");
					}
					Utils.changeRank=!Utils.changeRank;
				}  else if (args[1].equalsIgnoreCase("all")) {
					Utils.addChat("??cRangs d??bloqu??s: ??8[??7"+Utils.getNbRankUnlock()+"??8/??7"+ModuleManager.rang.size()+"??8]");
					Utils.addChat("??5Neko: ??8[??7"+Utils.getTotRankRateUnlock(Rate.Neko)+"??8/??7"+Utils.getTotRankRate(Rate.Neko)+"??8]");
					Utils.addChat("??6Supra: ??8[??7"+Utils.getTotRankRateUnlock(Rate.Supra)+"??8/??7"+Utils.getTotRankRate(Rate.Supra)+"??8]");
					Utils.addChat("??2Event: ??8[??7"+Utils.getTotRankRateUnlock(Rate.Event)+"??8/??7"+Utils.getTotRankRate(Rate.Event)+"??8]");
					Utils.addChat("??7Ordinaire: ??8[??7"+Utils.getTotRankRateUnlock(Rate.Ordinaire)+"??8/??7"+Utils.getTotRankRate(Rate.Ordinaire)+"??8]");
					Utils.addChat("??eRare: ??8[??7"+Utils.getTotRankRateUnlock(Rate.Rare)+"??8/??7"+Utils.getTotRankRate(Rate.Rare)+"??8]");
					Utils.addChat("??bUltraRare: ??8[??7"+Utils.getTotRankRateUnlock(Rate.UltraRare)+"??8/??7"+Utils.getTotRankRate(Rate.UltraRare)+"??8]");
					Utils.addChat("??dMagical: ??8[??7"+Utils.getTotRankRateUnlock(Rate.Magical)+"??8/??7"+Utils.getTotRankRate(Rate.Magical)+"??8]");
					Utils.addChat("??d??oDivin: ??8[??7"+Utils.getTotRankRateUnlock(Rate.Divin)+"??8/??7"+Utils.getTotRankRate(Rate.Divin)+"??8]");
					Utils.addChat("??cSatanique: ??8[??7"+Utils.getTotRankRateUnlock(Rate.Satanique)+"??8/??7"+Utils.getTotRankRate(Rate.Satanique)+"??8]");
					Utils.addChat("??5??oL??gendaire: ??8[??7"+Utils.getTotRankRateUnlock(Rate.L??gendaire)+"??8/??7"+Utils.getTotRankRate(Rate.L??gendaire)+"??8]");
					Utils.addChat("??2Mythique: ??8[??7"+Utils.getTotRankRateUnlock(Rate.Mythique)+"??8/??7"+Utils.getTotRankRate(Rate.Mythique)+"??8]");
					Utils.addChat("??4Titan: ??8[??7"+Utils.getTotRankRateUnlock(Rate.Titan)+"??8/??7"+Utils.getTotRankRate(Rate.Titan)+"??8]");
					Utils.addChat("??9CrazyLove: ??8[??7"+Utils.getTotRankRateUnlock(Rate.CrazyLove)+"??8/??7"+Utils.getTotRankRate(Rate.CrazyLove)+"??8]");
				} else if (args[1].equalsIgnoreCase("list")) {
						int j=0;
						ArrayList<RmRank> list = new ArrayList<>();
						for (Rank r : ModuleManager.rang) {
							if (!r.isLock()) {
								String desc="";
								desc+="??6S??lectionn??: "+(r.getName().equalsIgnoreCase(var.rang.getName()) ? "??aS??lectionn??" : "??cNon");
								if (!Utils.isLock("rankmanager rate"))
									desc+="\n??6Raret??: "+r.getColor()+r.getRate();
								if (!Utils.isLock("rankmanager lvl"))
									desc+="\n??6Lvl: ??b"+r.getLvl();
								if (!Utils.isLock("rankmanager bonus")) {
									desc+="\n??6Bonus: ??d"+r.getTotBonus()+"%";
									ArrayList<String> l = r.getAllBonus("??6", "??d");
									for (String s : l) {
										desc+="\n"+s;
									}
								}
								if (!r.getDesc().equalsIgnoreCase("null") && !Utils.isLock("rankmanager desc"))
									desc+="\n??6Description: "+Utils.setColor(r.getDesc(), r.getColor().replaceAll("??n", ""));
								list.add(new RmRank("??b-"+r.getColor()+r.getName()+"??b-", r.getName(), desc));
								j++;
							}
						}
						int page;
						if (args.length==2) {
							page=1;
						} else {
							if (Utils.isInteger(args[2])) {
								page = Integer.parseInt(args[2]);
							} else
								page=1;
						}
						
						int l=0;
						int k=0;
						int i=8;
						Utils.addChat("??cRangs d??bloqu??s: ??7"+Utils.getNbRankUnlock()+"??8/??7"+ModuleManager.rang.size());
						for (RmRank rm : list) {
							if ((page-1)*i<=l && (page)*i>l) {
								String s[] = rm.getDesc().split(" ");
								String r="";
								for (int o=0;o<s.length;o++) {
									r+=s[o]+" ";
									if (o%15==0) {
										r+="\n"+Utils.getRank(rm.getRankName()).getColor().replaceAll("??n", "");
									}
								}					
								Utils.addChat2(rm.getName(), var.prefixCmd+"rm choose "+rm.getRankName(), r, false, Chat.Click);
								Utils.toChat(" ");
								k++;
							}
							l++;
						}
						if (k==i) {
							Utils.addChat2("??aPage suivante ->", var.prefixCmd+"rankmanager list "+(page+1), "??7Clique pour afficher la pahe suivante !", false, Chat.Click);
						}
						
						if (j==0)
							Utils.addChat("??cAucun rangs disponibles...");
					
				} else if (args[1].equalsIgnoreCase("choose")) {
					if (args.length<3) {
						Utils.addChat(err);
					} else {
						String choose="";
						for (int i=2;i<args.length;i++) {
							if (i!=args.length-1)
								choose+=args[i] + " ";
							else 
								choose+=args[i];
						}
						
						if (!Utils.isRankLock(choose)) {
						    if(choose.equalsIgnoreCase("Choumise")) {
                               Utils.addChat("??cBien essay??, ce rang est r??serv??.");
                               return;
                            }
							if (!Utils.changeRank(choose)) {
								Utils.addChat("??cCe rang n'existe pas !");
							} else {
								Utils.addChat("??aVous avez chang?? de rang !"); }
						} else {
							Utils.addChat("??cVous n'avez pas d??bloqu?? ce rang !");
						}
					}
				}
				
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}		
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"stat")) {
				if (args.length>1 && (args[1].equalsIgnoreCase("global") || args[1].equalsIgnoreCase("all")))
					new Thread(new Runnable() {
						
						@Override
						public void run() {
							Utils.displayStat(true);
							
						}
					}).start();
				else
					Utils.displayStat(false);
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}	
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"lvlup")) {
				if (var.animation) {
					Utils.addChat("??cAnimation du lvl d??sactiv??");
				} else {
					Utils.addChat("??aAnimation du lvl activ??");
				}
				var.animation=!var.animation;
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"trade") || args[0].equalsIgnoreCase(var.prefixCmd+"shop")) {
				if (args.length==1) {
					Utils.addChat(Utils.sep);
					Utils.addChat("??lListe des gains:");
					Utils.addChat2("??7Votre solde ??7[??cici??7]", "", "??7Souls: ??b"+var.ame+"\n??7Tickets de loteries:??6 "+var.lot, true, Chat.Click);
					
					Utils.addChat2("??6"+var.prefixCmd+"trade lot", var.prefixCmd+"trade lot", "??7Tire des lots al??atoires !\n??cCo??t:??c 1 ticket de loterie", false, Chat.Summon);
					Utils.addChat2("??6"+var.prefixCmd+"trade ticket", var.prefixCmd+"trade ticket", "??7Re??ois un ticket de lotterie !\n??cCo??t:??c 150 souls", false, Chat.Summon);
					Utils.addChat2("??6"+var.prefixCmd+"trade lotplus", var.prefixCmd+"trade lotplus", "??7Augmente les lots qui apparaissent de 1 de plus\n??cCo??t: ??c"+(100+(Lot.nbLot-3)*75)+" souls", false, Chat.Summon);
					for (Lock lock : ModuleManager.Lock) {
						if (lock.isLock() && lock.getUnit().equalsIgnoreCase("souls") && !lock.getName().startsWith("rankmanager")) {
							Utils.addChat2("??6"+var.prefixCmd+"trade "+lock.getCmdName(), var.prefixCmd+"trade "+lock.getCmdName(), "??7D??bloque d??finitivement le ??c"+lock.getNameUnlock()+"\n??cCo??t: "+lock.getCout(), false, Chat.Summon);
						}
					}
										
					if (!Utils.isLock("--rankmanager")) {
						if (Utils.isLock("rankmanager info")) {
							Utils.addChat2("??6"+var.prefixCmd+"trade info", var.prefixCmd+"trade info", "??7D??bloque d??finitivement l'option Info dans le RankManager\n??cCo??t: 100 souls", false , Chat.Summon);
						} else
							if (Utils.isLock("rankmanager lvl")) {
								Utils.addChat2("??6"+var.prefixCmd+"trade lvl", var.prefixCmd+"trade lvl", "??7D??bloque d??finitivement l'option Lvl dans le RankManager\n??cCo??t: 150 souls", false, Chat.Summon);
							} else
								if (Utils.isLock("rankmanager rate")) {
									Utils.addChat2("??6"+var.prefixCmd+"trade rate", var.prefixCmd+"trade rate", "??7D??bloque d??finitivement l'option Raret?? dans le RankManager\n??cCo??t: 200 souls", false, Chat.Summon);
								} else
									if (Utils.isLock("rankmanager bonus")) {
										Utils.addChat2("??6"+var.prefixCmd+"trade bonus", var.prefixCmd+"trade bonus", "??7D??bloque d??finitivement l'option Lvl dans le RankManager\n??cCo??t: 250 souls", false, Chat.Summon);
									}						
					}				
					if (!Lot.list2.isEmpty()) {
						int prix=0;
						Trade xp2 = new Trade("xp", 0);
						Trade malus = new Trade("malus", 0);
						Trade bonus = new Trade("bonus", 0);
						Trade unlock = new Trade("unlock", 0);
						Trade souls = new Trade("souls", 0);
						// Raret?? rang
						Trade Ordinaire = new Trade("Rang", 0);
						Trade Rare = new Trade("Rang", 0);
						Trade UltraRare = new Trade("Rang", 0);
						Trade Magical = new Trade("Rang", 0);
						Trade Divin = new Trade("Rang", 0);
						Trade Satanique = new Trade("Rang", 0);
						Trade L??gendaire = new Trade("Rang", 0);
						Trade Mythique = new Trade("Rang", 0);
						Trade Titan = new Trade("Rang", 0);
						Trade Neko = new Trade("Rang", 0);
						for (Bloc b : Lot.list2) {
							String s = b.getGain();
							// rang, unlock, bonus, malus ou xp
							if (s.equalsIgnoreCase("rang")) {
								if (b.getRate().equals(Rate.Satanique)) {
									Satanique.setPrix(500);
									Satanique.setRate(Rate.Satanique);
								} else if (b.getRate().equals(Rate.Divin)) {
									Divin.setPrix(300);
									Divin.setRate(Rate.Divin);
								} else if (b.getRate().equals(Rate.Magical)) {
									Magical.setPrix(200);
									Magical.setRate(Rate.Magical);
								} else if (b.getRate().equals(Rate.UltraRare)) {
									UltraRare.setPrix(100);
									UltraRare.setRate(Rate.UltraRare);
								} else if (b.getRate().equals(Rate.Rare)) {
									Rare.setPrix(50);
									Rare.setRate(Rate.Rare);
								} else if (b.getRate().equals(Rate.Ordinaire)) {
									Ordinaire.setPrix(20);
									Ordinaire.setRate(Rate.Ordinaire);
								} else if (b.getRate().equals(Rate.Titan)) {
									Titan.setPrix(50000);
									Titan.setRate(Rate.Titan);
								} else if (b.getRate().equals(Rate.Mythique)) {
									Mythique.setPrix(30000);
									Mythique.setRate(Rate.Mythique);
								} else if (b.getRate().equals(Rate.L??gendaire)) {
									L??gendaire.setPrix(20000);
									L??gendaire.setRate(Rate.L??gendaire);
								}
							} else if (s.equalsIgnoreCase("unlock")) {
								unlock.setPrix(150);
							} else if (s.equalsIgnoreCase("bonus")) {
								bonus.setPrix(50);
							} else if (s.equalsIgnoreCase("malus")) {
								malus.setPrix(20);
							} else if (s.equalsIgnoreCase("xp")) {
								xp2.setPrix(30);
							} 				
							String g = b.getGain();
							switch (g) {
							case "xp":
								xp2.addCount();
								break;
							case "malus":
								malus.addCount();
								break;
							case "bonus":
								bonus.addCount();
								break;
							case "unlock":
								unlock.addCount();
								break;
							case "souls":
								souls.addCount();
								break;
							case "rang":
								Rate r = b.getRate();
								switch (r.name()) {
								case "Ordinaire":
									Ordinaire.addCount();
									break;
								case "Rare":
									Rare.addCount();
									break;
								case "UltraRare":
									UltraRare.addCount();
									break;
								case "Magical":
									Magical.addCount();
									break;
								case "Divin":
									Divin.addCount();
									break;
								case "Satanique":
									Satanique.addCount();
									break;
								case "L??gendaire":
									L??gendaire.addCount();
									break;
								case "Mythique":
									Mythique.addCount();
									break;
								case "Titan":
									Titan.addCount();
									break;
								case "Neko":
									Neko.addCount();
									break;
								}
								break;
							}
						}
						int i = 1;
						
						ArrayList<Trade> list = new ArrayList<>();
						list.add(xp2);
						list.add(malus);
						list.add(bonus);
						list.add(unlock);
						list.add(souls);
						list.add(Ordinaire);
						list.add(Rare);
						list.add(UltraRare);
						list.add(Magical);
						list.add(Divin);
						list.add(Satanique);
						list.add(L??gendaire);
						list.add(Mythique);
						list.add(Titan);
						list.add(Neko);
						
						for (Trade t : list) {
							if (t.isNotNull()) {
								Utils.addChat2("??6"+var.prefixCmd+"trade "+i+": "+Utils.setColor("==> R??cup??ration ??c"+t.getGain()+" "+(t.getGain().equalsIgnoreCase("Rang") ? "??c"+t.getRate().name()+" " : "")+"[??a"+t.getCount()+"??7] contre ??c"+t.getPrix()+" souls", "??7"), var.prefixCmd+"trade "+i, "??7Cliquez pour obtenir ce lot !", false, Chat.Click);
								t.setNum(i);
								i++;
							}
						}
						Utils.addChat2("??6"+var.prefixCmd+"trade all: ??7"+Utils.setColor("Ach??te dans l'ordre tous les lots perdus jusqu'?? avoir tout acheter ou plus assez de souls", "??7"), var.prefixCmd+"trade all", "??7Cliquer pour tout acheter !", false, Chat.Click);
					}								
				} else if (args[1].equalsIgnoreCase("lot")) {
					int totLot=1;
					if (args.length==3 && Utils.isInteger(args[2])) {
						totLot=Integer.parseInt(args[2]);
					}
					if (var.lot<1 || var.lot-totLot<0) {
						Utils.addChat("??cD??sol?? mais vous n'avez pas de ticket de lotterie...");
					} else {
						
						var.lot-=totLot;
						ArrayList<Bloc> list = new ArrayList<>();
						for (int l=0;l<Lot.nbLot*totLot;l++)
							for (int k=0;k<50;k++) {
								BlockPos b = new BlockPos(mc.thePlayer.posX-5+Utils.getRandInt(10), mc.thePlayer.posY, mc.thePlayer.posZ-5+Utils.getRandInt(10));
								if (mc.theWorld.getBlockState(b).getBlock().getMaterial() == Material.air && mc.thePlayer.getDistanceSq(b)>2) {
									String gain;
									Rate r=Rate.Ordinaire;
									double bonus=0;
									int time=0;
									float re=0.88F;
									float g=0.44F;
									float bl=0.88F;
									if (Math.random()<0.01) {
										gain="bonus";
										bonus=(double) Math.round(Math.random()*25+var.rang.getLuck()*25);
										time=Utils.getRandInt(700);
									} else if (Math.random()<0.05) {										
										gain="malus";
										bonus=(double) Math.round(Math.random()*-10);
										time=Utils.getRandInt(1200);										
									} else if (Math.random()<0.3+var.rang.getLotRang()) {
										if (Math.random()<0.0000001+0.0000001*var.rang.getLotRateTitan()) {
											r=Rate.Titan;
										} else if (Math.random()<0.0007+0.0007*var.rang.getLotRateSatanique()) {
											r=Rate.Satanique;
										} else if (Math.random()<0.001+0.001*var.rang.getLotRateDivin()) {
											r=Rate.Divin;
										} else if (Math.random()<0.01+0.01*var.rang.getLotRateMagical()) {
											r=Rate.Magical;
										} else if (Math.random()<0.05+0.05*var.rang.getLotRateUltraRare()) {
											r=Rate.UltraRare;
										} else if (Math.random()<0.15+0.15*var.rang.getLotRateRare()) {
											r=Rate.Rare;
										} else 
											r=Rate.Ordinaire;
										gain="rang";
										if (Math.random()<0.2+var.rang.getLuck()*0.2) {
											re=0.88F;
											g=0.22F;
											bl=0.33F;
										}
									} else if (Math.random()<0.05+var.rang.getLotUnlock()) {
										gain="unlock";
										if (Math.random()<0.1+var.rang.getLuck()*0.1) {
											re=0.22F;
											g=0.22F;
											bl=0.99F;
										}
									} else if (Math.random()<0.05+var.rang.getGiftAme()) {
										gain="souls";
										if (Math.random()<0.1+var.rang.getLuck()*0.1) {
											re=0.44F;
											g=0.44F;
											bl=0.99F;
										}
									} else {
										gain="xp";
									}
									Bloc bloc = new Bloc(b, gain, re, g, bl);
									if (gain.equalsIgnoreCase("rang")) {
										bloc.setRate(r);
									} else if (gain.equalsIgnoreCase("bonus") || gain.equalsIgnoreCase("malus")) {
										bloc.setBonus(bonus);
										bloc.setTime(time);
									}
									list.add(bloc);
									k=50;
								}
							}
						Lot.init(list);
						Utils.addChat("??5Choisissez un des "+Lot.nbLot*totLot+" lots en allant dessous !");
					}
					
				} else if (LockManager.getManager().isALockCmdLocked(args[1])) {
					LockManager lm = LockManager.getManager();
					Lock curr = lm.getLockByCmdName(args[1]);
					if (var.ame<curr.getCout()) {
						Utils.addChat("??cD??sol?? mais vous n'avez pas assez de souls...");
					} else {
						var.ame-=curr.getCout();
						Utils.unlock(curr.getName());
						Utils.addChat("??c"+curr.getNameUnlock()+"??7 d??bloqu?? !");
					}
					
				} else if (args[1].equalsIgnoreCase("ticket")) {
					if (var.ame<150) {
						Utils.addChat("??cD??sol?? mais vous n'avez pas assez de souls...");
					} else {
						var.ame-=150;
						var.lot++;
						Utils.addChat("??a+ 1 ticket de lotterie !");
					}
					
				} else if (args[1].equalsIgnoreCase("info") && !Utils.isLock("--rankmanager") && Utils.isLock("rankmanager info")) {
					if (var.ame<100) {
						Utils.addChat("??cD??sol?? mais vous n'avez pas assez de souls...");
					} else {
						var.ame-=100;
						Utils.unlock("rankmanager info");
						Utils.addChat("??cRankManager Info??7 d??bloqu?? !");
					}
					
				} else if (args[1].equalsIgnoreCase("lvl") && !Utils.isLock("--rankmanager") && Utils.isLock("rankmanager lvl") && !Utils.isLock("rankmanager info")) {
					if (var.ame<150) {
						Utils.addChat("??cD??sol?? mais vous n'avez pas assez de souls...");
					} else {
						var.ame-=150;
						Utils.unlock("rankmanager lvl");
						Utils.addChat("??cRankManager Lvl??7 d??bloqu?? !");
					}
					
				} else if (args[1].equalsIgnoreCase("rate") && !Utils.isLock("--rankmanager") && !Utils.isLock("rankmanager lvl") && Utils.isLock("rankmanager rate") && !Utils.isLock("rankmanager info")) {
					if (var.ame<200) {
						Utils.addChat("??cD??sol?? mais vous n'avez pas assez de souls...");
					} else {
						var.ame-=200;
						Utils.unlock("RankManager rate");
						Utils.addChat("??cRankManager Rate??7 d??bloqu?? !");
					}
					
				} else if (args[1].equalsIgnoreCase("bonus") && !Utils.isLock("--rankmanager") && !Utils.isLock("rankmanager lvl") && !Utils.isLock("rankmanager rate") && !Utils.isLock("rankmanager info") && Utils.isLock("rankmanager bonus")) {
					if (var.ame<250) {
						Utils.addChat("??cD??sol?? mais vous n'avez pas assez de souls...");
					} else {
						var.ame-=250;
						Utils.unlock("RankManager bonus");
						Utils.addChat("??cRankManager Bonus??7 d??bloqu?? !");
					}
					
				} else if (args[1].equalsIgnoreCase("lotplus")) {
					if (var.ame<(100+(Lot.nbLot-3)*75)) {
						Utils.addChat("??cD??sol?? mais vous n'avez pas assez de souls...");
					} else {
						var.ame-=(100+(Lot.nbLot-3)*75);
						Lot.nbLot++;
						Utils.addChat("??bAugmentation de 1 du nombre de lot max ! (Total : "+Lot.nbLot+")");
					}
					
				} else if ((Utils.isInteger(args[1]) ? Integer.parseInt(args[1])-1<Lot.list2.size() && Integer.parseInt(args[1])>0 : false) || (args[1].equalsIgnoreCase("all") && !Lot.list2.isEmpty())) {
					if (!Lot.list2.isEmpty()) {
						int prix=0;
						Trade xp2 = new Trade("xp", 0);
						Trade malus = new Trade("malus", 0);
						Trade bonus = new Trade("bonus", 0);
						Trade unlock = new Trade("unlock", 0);
						Trade souls = new Trade("souls", 0);
						// Raret?? rang
						Trade Ordinaire = new Trade("Rang", 0);
						Trade Rare = new Trade("Rang", 0);
						Trade UltraRare = new Trade("Rang", 0);
						Trade Magical = new Trade("Rang", 0);
						Trade Divin = new Trade("Rang", 0);
						Trade Satanique = new Trade("Rang", 0);
						Trade L??gendaire = new Trade("Rang", 0);
						Trade Mythique = new Trade("Rang", 0);
						Trade Titan = new Trade("Rang", 0);
						Trade Neko = new Trade("Rang", 0);
						for (Bloc b : Lot.list2) {
							String s = b.getGain();
							// rang, unlock, bonus, malus ou xp
							if (s.equalsIgnoreCase("rang")) {
								if (b.getRate().equals(Rate.Satanique)) {
									Satanique.setPrix(500);
									Satanique.setRate(Rate.Satanique);
								} else if (b.getRate().equals(Rate.Divin)) {
									Divin.setPrix(300);
									Divin.setRate(Rate.Divin);
								} else if (b.getRate().equals(Rate.Magical)) {
									Magical.setPrix(200);
									Magical.setRate(Rate.Magical);
								} else if (b.getRate().equals(Rate.UltraRare)) {
									UltraRare.setPrix(100);
									UltraRare.setRate(Rate.UltraRare);
								} else if (b.getRate().equals(Rate.Rare)) {
									Rare.setPrix(50);
									Rare.setRate(Rate.Rare);
								} else if (b.getRate().equals(Rate.Ordinaire)) {
									Ordinaire.setPrix(20);
									Ordinaire.setRate(Rate.Ordinaire);
								} else if (b.getRate().equals(Rate.Titan)) {
									Titan.setPrix(50000);
									Titan.setRate(Rate.Titan);
								} else if (b.getRate().equals(Rate.Mythique)) {
									Mythique.setPrix(30000);
									Mythique.setRate(Rate.Mythique);
								} else if (b.getRate().equals(Rate.L??gendaire)) {
									L??gendaire.setPrix(20000);
									L??gendaire.setRate(Rate.L??gendaire);
								}
							} else if (s.equalsIgnoreCase("unlock")) {
								unlock.setPrix(150);
							} else if (s.equalsIgnoreCase("bonus")) {
								bonus.setPrix(50);
								bonus.setTime(b.getTime());
								bonus.setBonus(b.getBonus());
							} else if (s.equalsIgnoreCase("malus")) {
								malus.setPrix(20);
								malus.setTime(b.getTime());
								malus.setBonus(b.getBonus());
							} else if (s.equalsIgnoreCase("xp")) {
								xp2.setPrix(30);
							} 				
							String g = b.getGain();
							switch (g) {
							case "xp":
								xp2.addCount();
								break;
							case "malus":
								malus.addCount();
								break;
							case "bonus":
								bonus.addCount();
								break;
							case "unlock":
								unlock.addCount();
								break;
							case "souls":
								souls.addCount();
								break;
							case "rang":
								Rate r = b.getRate();
								switch (r.name()) {
								case "Ordinaire":
									Ordinaire.addCount();
									break;
								case "Rare":
									Rare.addCount();
									break;
								case "UltraRare":
									UltraRare.addCount();
									break;
								case "Magical":
									Magical.addCount();
									break;
								case "Divin":
									Divin.addCount();
									break;
								case "Satanique":
									Satanique.addCount();
									break;
								case "L??gendaire":
									L??gendaire.addCount();
									break;
								case "Mythique":
									Mythique.addCount();
									break;
								case "Titan":
									Titan.addCount();
									break;
								case "Neko":
									Neko.addCount();
									break;
								}
								break;
							}
						}
						int i = 1;
						
						ArrayList<Trade> list = new ArrayList<>();
						list.add(xp2);
						list.add(malus);
						list.add(bonus);
						list.add(unlock);
						list.add(souls);
						list.add(Ordinaire);
						list.add(Rare);
						list.add(UltraRare);
						list.add(Magical);
						list.add(Divin);
						list.add(Satanique);
						list.add(L??gendaire);
						list.add(Mythique);
						list.add(Titan);
						list.add(Neko);
						
						for (Trade t : list) {
							if (t.isNotNull()) {							
								if (args[1].equalsIgnoreCase("all") || i==Integer.parseInt(args[1])) {
									if (var.ame<t.getPrix()) {
										Utils.addChat("??cD??sol?? mais vous n'avez pas assez de souls...");
									} else {
										var.ame-=t.getPrix();
										if (t.getGain().equalsIgnoreCase("rang")) {
											String sr = Utils.getRandRank(t.getRate());
											Utils.setRank(sr);
											Utils.addChat("??5Vous d??bloquez le rang "+Utils.getRankColor(sr)+sr+"??5 !");
											int rang=0;
											for (Rank r : ModuleManager.rang) {
												if (!r.isLock())
													rang++;
											}							
											if (rang==10 && var.rang.getLvl()==1) {
												Utils.addChat("??5??k77??c10 rangs d??bloqu??s !??5??k88");
												Utils.addChat("??5??k77??c10 billets de lotterie gagn??s !??5??k88");
												var.lot+=10;
											} else if (rang==25 && var.rang.getLvl()==1) {
												Utils.addChat("??5??k77??c25 rangs d??bloqu??s !??5??k88");
												Utils.addChat("??5??k77??c15 billets de lotterie gagn??s !??5??k88");
												var.lot+=15;
											} else if (rang==50 && var.rang.getLvl()==1) {
												Utils.addChat("??5??k77??c50 rangs d??bloqu??s !??5??k88");
												Utils.addChat("??5??k77??c20 billets de lotterie gagn??s !??5??k88");
												var.lot+=20;
											} else if (rang==100 && var.rang.getLvl()==1) {
												Utils.addChat("??5??k77??c100 rangs d??bloqu??s !??5??k88");
												Utils.addChat("??5??k77??c25 billets de lotterie gagn??s !??5??k88");
												var.lot+=25;
											} else if (rang==150 && var.rang.getLvl()==1) {
												Utils.addChat("??5??k77??c150 rangs d??bloqu??s !??5??k88");
												Utils.addChat("??5??k77??c30 billets de lotterie gagn??s !??5??k88");
												var.lot+=30;
											}
										} else if (t.getGain().equalsIgnoreCase("unlock")) {
											Utils.getRandUnlock();
										} else if (t.getGain().equalsIgnoreCase("bonus")) {
											if (Active.time==0) {
												Active a1 = new Active(t.getBonus(), t.getTime());
												Utils.addChat("??5Vous avez re??u un bonus de ??d"+a1.getBonus()+"%\n??aActif pendant encore "+a1.getTime()/60+" minutes");
											} else {
												Utils.addChat("??aVotre temps et bonus s'additionnent ?? celui d??j?? actif !");
												Active.bonus+=t.getBonus();
												Active.time+=t.getTime();
											}
										} else if (t.getGain().equalsIgnoreCase("malus")) {
											if (Active.time==0) {
												Active a1 = new Active(t.getBonus(), t.getTime());
												Utils.addChat("??cVous avez re??u un imp??t de ??d"+a1.getBonus()+"%\n??aActif pendant encore "+a1.getTime()/60+" minutes");
											} else {
												Utils.addChat("??cVotre temps et bonus s'additionnent ?? celui d??j?? actif !");
												Active.bonus+=t.getBonus();
												Active.time+=t.getTime();
											}
										} else if (t.getGain().equalsIgnoreCase("xp")) {
											Utils.addChat(Utils.setColor("Xp re??u !", "??b??o"));
											Utils.checkXp(Utils.getRandInt((int) Math.round(700+700*var.rang.getGiftXp())));
										}
										for (Bloc b : Lot.list2) {
											if (b.getGain().equalsIgnoreCase(t.getGain())) {
												Lot.list2.remove(b);
												break;
											}
										}
									}
								}
								i++;	
							}
						}
						
					}
				} else {
					Utils.addChat("??cErreur, l'offre n'existe pas !");
					mc.thePlayer.playSound("mob.villager.haggle", 1.0F, 1.0F);
				}
				Utils.saveRpg();
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}			
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"fps")) {
				if (Utils.isLock("--fps")) {
					Utils.addWarn("fps");
					mc.thePlayer.playSound("mob.villager.no", 1.0F, 1.0F);
				} else if (args.length==1) {
					Utils.addChat(error);
				} else {
					try {
						Minecraft.fps=Integer.parseInt(args[1]);
						Utils.addChat("Ajout de "+args[1]+"fps !");
					} catch (Exception e) {
                        Utils.addChat(err);
                    }
				}
				Utils.checkXp(xp);
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"worldtime") || args[0].equalsIgnoreCase(var.prefixCmd+"time")) {
				if (args.length==1) {				
				} else {
					try {
						WorldTime.time=Long.parseLong(args[1]);
						Utils.addChat("Heure du monde chang?? ?? "+args[1]+" !");
					} catch (Exception e) {
						Utils.addChat(err);
					}
				}
				Utils.checkXp(xp);
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}						
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"reach")) {
				if (args.length==1 || Utils.isLock("reach")) {
					if (Utils.isLock("reach")) {
						Utils.addWarn("Reach");
					}
				} else if (args[1].equalsIgnoreCase("pvp")) {
					if (!Utils.isLock("--reach pvp")) {
						if (Reach.pvp) {
							Utils.addChat("??cReach pvp d??sactiv??e !");
						} else {
							Utils.addChat("??aReach pvp activ??e !");
						}
						Reach.pvp=!Reach.pvp;
						SettingsUtil.setReachPvp(Reach.pvp);
					} else
						Utils.addWarn("reach pvp");
				} else if (args[1].equalsIgnoreCase("knockback") || args[1].equalsIgnoreCase("knock") || args[1].equalsIgnoreCase("kb")) {
					if (!Utils.isLock("--reach pvp")) {
						if (Reach.knock) {
							Utils.addChat("??cReach knockback d??sactiv??e !");
						} else {
							Utils.addChat("??aReach knockback activ??e !");
						}
						Reach.knock=!Reach.knock;
						SettingsUtil.setReachKB(Reach.knock);
					} else
						Utils.addWarn("reach pvp");
				} else if (args[1].equalsIgnoreCase("multi") || args[1].equalsIgnoreCase("ma") || args[1].equalsIgnoreCase("multiaura")) {
					if (!Utils.isLock("--reach pvp")) {
						if (Reach.multiaura) {
							Utils.addChat("??cReach multiaura d??sactiv??e !");
						} else {
							Utils.addChat("??aReach multiaura activ??e !");
						}
						Reach.multiaura=!Reach.multiaura;
						SettingsUtil.setReachMA(Reach.multiaura);
					} else
						Utils.addWarn("reach pvp");
				} else if (args[1].equalsIgnoreCase("tnt")) {
					if (!Utils.isLock("--reach pvp")) {
						if (args.length==2) {
							if (Reach.tnt) {
								Utils.addChat("??cReach tnt d??sactiv??e !");
							} else {
								Utils.addChat("??aReach tnt activ??e !");
							}
							Reach.tnt=!Reach.tnt;
							SettingsUtil.setReachTnT(Reach.tnt);
						} else if (args[2].equalsIgnoreCase("list")) {
							Utils.addChat("Modes disponibles: ??aNormal??7, ??aCage");
						} else {
							try {
								String mode = args[2].toLowerCase();
								mode = mode.replaceFirst(".", (mode.charAt(0)+"").toUpperCase());
								Reach.mode=Form.valueOf(mode);
								SettingsUtil.setReachMode(mode);
								Utils.addChat("??aMode de la reach tnt mise sur "+args[2]);
							} catch (Exception e) {
								Utils.addChat("??cErreur, ce mode n'existe pas");
							}
						}
					} else
						Utils.addWarn("reach pvp");
				} else if (args[1].equalsIgnoreCase("aimbot")) {
					if (!Utils.isLock("--reach pvp")) {
						if (Reach.aimbot) {
							Utils.addChat("??cReach aimbot d??sactiv??e !");
						} else {
							Utils.addChat("??aReach aimbot activ??e !");
						}
						Reach.aimbot=!Reach.aimbot;
						SettingsUtil.setReachAimbot(Reach.aimbot);
					} else
						Utils.addWarn("reach pvp");
				} else if (args[1].equalsIgnoreCase("fov")) {
					if (!Utils.isLock("--reach pvp")) {
						if (args.length==2) {
							Utils.addChat(err);
						} else {
							try {
								Reach.fov=Double.parseDouble(args[2]);
								SettingsUtil.setReachFOV(Reach.fov);
								Utils.addChat("??aFov de la reach chang?? ?? "+args[2]+" !");
							} catch (Exception e) {
								Utils.addChat(err);
							}
						}
					} else
						Utils.addWarn("reach pvp");
				} else if (args[1].equalsIgnoreCase("classic")) {
					if (!Utils.isLock("--reach pvp")) {
						if (Reach.classic) {
							Utils.addChat("??cReach classic d??sactiv??e !");
						} else {
							Utils.addChat("??aReach classic activ??e !");
						}
						Reach.classic=!Reach.classic;
						SettingsUtil.setReachTPCLassic(Reach.classic);
					} else
						Utils.addWarn("reach pvp");
				} else if (args[1].equalsIgnoreCase("bloc") || args[1].equalsIgnoreCase("block")) {
					if (!Utils.isLock("--reach pvp")) {
						if (Reach.bloc) {
							Utils.addChat("??cReach sur bloc d??sactiv??e !");
						} else {
							Utils.addChat("??aReach sur bloc activ??e !");
						}
						Reach.bloc=!Reach.bloc;
						SettingsUtil.setReachBloc(Reach.bloc);
					} else
						Utils.addWarn("reach pvp");
				} else {
					try {
						Float f = Float.parseFloat(args[1]);
						if (f>1000)
							f = 1000f; 
						Reach.dist=f;
						SettingsUtil.setReachDistance(f);
						Utils.addChat("??aReach augment??e ?? "+f+" !");
					} catch (Exception e) {
                        Utils.addChat(err);
                    }
				}
				Utils.checkXp(xp);
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"itemesp") || args[0].equalsIgnoreCase(var.prefixCmd+"item")) {
				if (args.length==1) {					
				} else if (args.length>=3) {
					try {
						if (args[1].equalsIgnoreCase("color") || args[1].equalsIgnoreCase("c")) {
							ItemESP.cR=Float.parseFloat(args[2])/100;
							ItemESP.cG=Float.parseFloat(args[3])/100;
							ItemESP.cB=Float.parseFloat(args[4])/100;
							Utils.addChat("??aCouleurs du ItemESP ?? "+args[2]+"r, "+args[3]+"g et "+args[4]+"b !");
						} else if (args[1].equalsIgnoreCase("linecolor") || args[1].equalsIgnoreCase("lc")) {
							ItemESP.clR=Float.parseFloat(args[2])/100;
							ItemESP.clG=Float.parseFloat(args[3])/100;
							ItemESP.clB=Float.parseFloat(args[4])/100;
							Utils.addChat("??aCouleur des contours du ItemESP ?? "+args[2]+"r, "+args[3]+"g et "+args[4]+"b !");							
						} else if (args[1].equalsIgnoreCase("width")) {
							ItemESP.width=Float.parseFloat(args[2]);
							Utils.addChat("??aGrosseur des contours mis ?? "+ItemESP.width+" !");
						}
					} catch (Exception e) {
						Utils.addChat(err);
					}
				} else {
					Utils.addChat(err);
				}
				Utils.checkXp(xp);
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"wallhack") || args[0].equalsIgnoreCase(var.prefixCmd+"wh")) {
				if (args.length==1 || Utils.isLock("Wallhack")) {
				} else if (args.length>=3) {
					try {
						if (args[1].equalsIgnoreCase("color") || args[1].equalsIgnoreCase("c")) {
							Wallhack.cR=Float.parseFloat(args[2])/100;
							Wallhack.cG=Float.parseFloat(args[3])/100;
							Wallhack.cB=Float.parseFloat(args[4])/100;
							Utils.addChat("??aCouleurs du Wallhack ?? "+args[2]+"r, "+args[3]+"g et "+args[4]+"b !");
						} else if (args[1].equalsIgnoreCase("linecolor") || args[1].equalsIgnoreCase("lc")) {
							Wallhack.clR=Float.parseFloat(args[2])/100;
							Wallhack.clG=Float.parseFloat(args[3])/100;
							Wallhack.clB=Float.parseFloat(args[4])/100;
							Utils.addChat("??aCouleur des contours du Wallhack ?? "+args[2]+"r, "+args[3]+"g et "+args[4]+"b !");							
						} else if (args[1].equalsIgnoreCase("width")) {
							Wallhack.width=Float.parseFloat(args[2]);
							Utils.addChat("??aGrosseur des contours mis ?? "+Wallhack.width+" !");
						}
					} catch (Exception e) {
						Utils.addChat(err);
					}
				} else {
					Utils.addChat(err);
				}
				Utils.checkXp(xp);
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"tracers")) {
				if (args.length==1) {
				} else if (args[1].equalsIgnoreCase("fr") || args[1].equalsIgnoreCase("friend") || args[1].equalsIgnoreCase("friends")) {
					if (Tracers.friend) {
						Tracers.friend=false;
						SettingsUtil.setTracers_Friends(false);
						Utils.addChat("??cAffichage des amis sur le tracers d??sactiv??");
					} else {
						Tracers.friend=true;
						SettingsUtil.setTracers_Friends(true);
						Utils.addChat("??aAffichage des amis sur le tracers activ??");
					}
				}
				else if (args[1].equalsIgnoreCase("joueurs") || args[1].equalsIgnoreCase("player") || args[1].equalsIgnoreCase("joueur")) {
					if (Tracers.BoolENEMIES) {
						Tracers.BoolENEMIES = false;
						SettingsUtil.setTracers_Enemies(false);
						Utils.addChat("??cAffichage des joueurs d??sactiv?? sur le tracers");
					} else {
						Tracers.BoolENEMIES = true;
						SettingsUtil.setTracers_Enemies(true);
						Utils.addChat("??cAffichage des joueurs activ?? sur le tracers");
					}
				}
				else if (args[1].equalsIgnoreCase("mobs")  || args[1].equalsIgnoreCase("monstres")) {
					if (Tracers.BoolMOBS) {
						Tracers.BoolMOBS = false;
						SettingsUtil.setTracers_Mobs(false);
						Utils.addChat("??cAffichage des mobs d??sactiv?? sur le tracers");
					} else {
						Tracers.BoolMOBS = true;
						SettingsUtil.setTracers_Mobs(true);
						Utils.addChat("??cAffichage des mobs activ?? sur le tracers");
					}
				}
				else if (args[1].equalsIgnoreCase("animals")  || args[1].equalsIgnoreCase("animaux")) {
					if (Tracers.BoolANIMALS) {
						Tracers.BoolANIMALS = false;
						SettingsUtil.setTracers_Animals(false);
						Utils.addChat("??cAffichage des animaux d??sactiv?? sur le tracers");
					} else {
						Tracers.BoolANIMALS = true;
						SettingsUtil.setTracers_Animals(true);
						Utils.addChat("??cAffichage des animaux activ?? sur le tracers");
					}
				}
				else if (args[1].equalsIgnoreCase("Inpc")  || args[1].equalsIgnoreCase("golem")) {
					if (Tracers.BoolGOLEM) {
						Tracers.BoolGOLEM = false;
						SettingsUtil.setTracers_Golem(false);
						Utils.addChat("??cAffichage des Inpc et Golem d??sactiv??s sur le tracers");
					} else {
						Tracers.BoolGOLEM = true;
						SettingsUtil.setTracers_Golem(true);
						Utils.addChat("??cAffichage des Inpc et Golem activ??s sur le tracers");
					}
				}
				else if (args[1].equalsIgnoreCase("NPC")  || args[1].equalsIgnoreCase("PNJ")) {
					if (Tracers.boolNPC) {
						Tracers.boolNPC = false;
						SettingsUtil.setTracers_NPC(false);
						Utils.addChat("??cAffichage des NPC d??sactiv?? sur le tracers");
					} else {
						Tracers.boolNPC = true;
						SettingsUtil.setTracers_NPC(true);
						Utils.addChat("??cAffichage des NPC activ?? sur le tracers");
					}
				} else if (args.length>=3) {				
					try {
						if (args[1].equalsIgnoreCase("width")) {
							Tracers.width=Float.parseFloat(args[2]);
							Utils.addChat("??aEpaisseur de la ligne mis ?? "+Tracers.width+" !");
						}
					} catch (Exception e) {}
				}
				Utils.checkXp(xp);
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"trigger")) {
				if (args.length==1) {
				} else if (args[1].equalsIgnoreCase("cps")) {
					try {
						Trigger.cps=Integer.parseInt(args[2]);
						Utils.addChat("??aTrigger mis ?? "+args[2]+" cps !");
					} catch (Exception e) {
                        Utils.addChat(err);
                    }
				} else if (args[1].equalsIgnoreCase("random")) {
					if (Trigger.random) {
						Utils.addChat("??cTrigger random d??sactiv??");
					} else {
						Utils.addChat("??aTrigger random activ??");
					}
					
					Trigger.random=!Trigger.random;
				} else {
					try {
						Trigger.dist=Float.parseFloat(args[1]);
						Utils.addChat("??aTrigger mis ?? "+args[1]+" blocs !");
					} catch (Exception e) {
                        Utils.addChat(err);
                    }
				}
				Utils.checkXp(xp);
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"keybind")) {
				Utils.addChat("Commande plus utilis??e, voir le "+var.prefixCmd+"list");
				Utils.checkXp(xp);
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"locklvl")) {
				Utils.addChat(Utils.sep);
				for (Lock lock : ModuleManager.Lock) {
					if (lock.isLock())
						Utils.getLock(lock.getName());
				}
				Utils.checkXp(xp);
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"autocmd")) {
				if (args.length>=3) {
					if (args[1].equalsIgnoreCase("delay")) {
						if (Utils.isDouble(args[2])) {
							AutoCmd.sec = Double.parseDouble(args[2]);
							Utils.addChat("??aLe d??lai des commandes du AutoCmd a ??t?? mis ?? "+args[2]+" !");
						} else {
							Utils.addError("Les secondes doivent ??tre un nombre entier !");
						}
					}
					if (args[1].equalsIgnoreCase("cmd")) {
						String cmd = "";
						for (int i=2;i<args.length;i++) {
							if (cmd.isEmpty())
								cmd = args[i];
							else
								cmd += " "+args[i];
						}
						AutoCmd.cmd = cmd;
						Utils.addChat("??aLa commande du AutoCmd a ??t?? mis ?? "+cmd+" !");
					}
				}
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"tochat")) {				
				String s = ".......";
				for (int i=0;i<var.prefixCmd.length();i++)
					s+=".";
				Utils.toChat(var3.replaceFirst(s, "").replaceAll("&", "??").replaceAll(" ?? ", " & "));
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"meteore")) {
				if (args.length==1) {
					if (neko.module.modules.render.Render.active) {
						neko.module.modules.render.Render.active=false;
						Utils.addChat(Utils.setColor("Affichage et apparition des m??t??ores d??sactiv??s", "??c"));
					} else if (!neko.module.modules.render.Render.active) {
						neko.module.modules.render.Render.active=true;
						Utils.addChat(Utils.setColor("Affichage et apparition des m??t??ores activ??s", "??a"));
					}
				} else if (args[1].equalsIgnoreCase("xp")) {
					if (neko.module.modules.render.Render.xp) {
						Utils.addChat(Utils.setColor("Affichage et apparition des m??t??ores d'xp d??sactiv??s", "??c"));
					} else {
						Utils.addChat(Utils.setColor("Affichage et apparition des m??t??ores d'xp activ??s", "??a"));
					}
					neko.module.modules.render.Render.xp=!neko.module.modules.render.Render.xp;
				}
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"verif")) {
				if (args.length==1) {
					Utils.addChat(error);
				} else {
					if (args[1]!=null)
						Utils.verif=args[1];
					
					Event.lastEventId=-1;
					Utils.vDisplay=Utils.display;
					Utils.display=false;
					Utils.vXp=Utils.xp;
					Utils.xp=false;
					Utils.vZoom=Utils.zoom;
					Utils.zoom=false;
					Utils.vMod=Utils.getVMod();
					Utils.panic();
					Display.setTitle("Minecraft 1.8");
					var.name=Utils.version.equalsIgnoreCase("Neko") ? "1.8/vanilla" : Utils.version+"/vanilla";
					mc.ingameGUI.getChatGUI().clearChatMessages();	
					mc.displayGuiScreen((GuiScreen)null);
					return;			
				}
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"f3")) {
				if (args.length==1) {
					Utils.addChat(error);
				} else {
					if (args.length>=2) {
						String title="";
						for (int k=1;k<args.length;k++) {
							if (k+1==args.length)
								title+=args[k];
							else
								title+=args[k]+" ";
						}
						var.name=title;
						Utils.addChat("??aVersion chang??e en "+title+" !");
					} else {
						Utils.addChat(err);
					}
					
					mc.ingameGUI.getChatGUI().addToSentMessages(var3);					
				}
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"damage")) {
				Minecraft mc = Minecraft.getMinecraft();
				for (int i = 0; i < 1+(args.length==1 ? 1 : Utils.isInteger(args[1]) ? Integer.parseInt(args[1]) : 1); i++) {
				      mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(mc.thePlayer.posX, mc.thePlayer.posY + 3D, mc.thePlayer.posZ, false));
				      mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ, false));
				}
			    mc.ingameGUI.getChatGUI().addToSentMessages(var3);											
			}

			if (args[0].equalsIgnoreCase(var.prefixCmd+"nameprotect") || args[0].equalsIgnoreCase(var.prefixCmd+"np")) {
				if (args[1].equalsIgnoreCase("name")) {
					try {
						if (args[2].contains("=") || args[3].contains("=")) 
							Utils.addError("Caract??re invalide: =");
						else {	
							String nom2 = "";
							for (int i=3;i<args.length;i++) {
								if (i+1!=args.length) {
									nom2+=args[i]+" ";
								} else
									nom2+=args[i];
							}
							Nameprotect.getNP().addName(args[2], nom2);
							Utils.addChat("??a"+args[2]+" chang?? en "+args[3]);
						}
					} catch (Exception e) {
						Utils.addChat(err);
					}
				} else if (args[1].equalsIgnoreCase("delete") || args[1].equalsIgnoreCase("del") || args[1].equalsIgnoreCase("remove") || args[1].equalsIgnoreCase("rm")) {
					try {
						if (Nameprotect.getNP().deleteName(args[2])) {
							Utils.addChat("??aNom "+args[2]+" supprim?? !");
						} else {
							Utils.addError("Le nom n'existe pas");
						}
					} catch (Exception e) {
						Utils.addChat(err);
					}
				}
			}
			
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"paint")) {
				if (args.length==1) {
				} else if (args[1].equalsIgnoreCase("color")) {
					try {
						Paint.cR=Float.parseFloat(args[2])/100;
						Paint.cG=Float.parseFloat(args[3])/100;
						Paint.cB=Float.parseFloat(args[4])/100;
						Utils.addChat("??aCouleur du Paint chang??e !");
					} catch (Exception e) {
						Utils.addChat(err);
					}
				} else if (args[1].equalsIgnoreCase("alpha")) {
					try {
						Paint.alpha=Float.parseFloat(args[2]);					
						Utils.addChat("??aTransparence du Paint chang??e !");
					} catch (Exception e) {
						Utils.addChat(err);
					}
				} else if (args[1].equalsIgnoreCase("clear")) {
					Paint.pain.clear();
					Utils.addChat("??aPeinture clear !");
				}
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"title")) {
				if (args.length==1) {
					Utils.addChat(error);
				} else {
					if (args.length>=2) {
						String title="";
						for (int k=1;k<args.length;k++) {
							title+=args[k]+" ";
						}
						Display.setTitle(title);
						Utils.addChat("??aTitle chang?? en "+title+" !");
					} else {
						Utils.addChat(err);
					}
					
					mc.ingameGUI.getChatGUI().addToSentMessages(var3);					
				}
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"invsee")) {
				if (args.length==1) {
					Utils.addError(err);
				} else {
					try {
						EntityPlayer en = Utils.getPlayer(args[1]);	
						mc.ingameGUI.getChatGUI().addToSentMessages(var3);
						mc.displayGuiScreen((GuiScreen)null);
						mc.displayGuiScreen(new GuiInventory(en));
						return;
					} catch (Exception e) {
						Utils.addChat("??cErreur, ce joueur est introuvable");
					}
					
				}
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);	
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"cheststealer") || args[0].equalsIgnoreCase(var.prefixCmd+"cs")) {
				if (args.length==1) {
				} else {
					try {
						Cheststealer.waitTime=Integer.parseInt(args[1]);
						Utils.addChat("??aTick du ChestStealer mis ?? "+args[1]+" !");
					} catch (Exception e) {
						Utils.addChat(err);
					}
					
				}
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);	
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"info") || args[0].equalsIgnoreCase(var.prefixCmd+"i")) {
				if (args.length==1) {
					Utils.addChat(Utils.sep);
					Utils.addChat("Listes des caract??ristiques du Client :");
					Utils.addChat("Cheat:??7 Neko");
					Utils.addChat("Version:??7 " + var.CLIENT_VERSION);
					Utils.addChat("Auteur:??d Tryliom");
					Utils.addChat("Pr??fix de commande:??7 " + var.prefixCmd);
					Utils.addChat("Rang: "+var.rang.getColor()+ var.rang.getName());
					Utils.addChat("Mode:??7 " + var.mode);
					Utils.addChat("Niveau:??7 " + var.niveau);
					Utils.addChat("Xp actuel:??b " + var.xp + "xp/"+var.xpMax+"xp");
					Utils.addChat("Souls:??6 " + var.ame+" souls");
					Utils.addChat("Bonus:??d " + Math.round(Utils.getTotBonus()*100)+"%");
					Utils.addChat("Temps de jeu:??7 "+Utils.timeInGameHour+"h "+Utils.timeInGameMin+"min "+Utils.timeInGameSec+"s");
					Utils.addChat("OnlyRpg:??7 "+(var.onlyrpg.isActive() ? "??aActiv??" : "??cD??sactiv??"));
					Utils.addChat("OnlyRpg activ?? depuis "+var.onlyrpg.getTime());
				} else if (args.length==2) {
					if (Utils.getPlayer(args[1])==null) {
						Utils.addChat("??cErreur: Ce joueur est introuvable ou non visible");
					} else {
						EntityPlayer p = Utils.getPlayer(args[1]);
						Utils.addChat(Utils.sep);
						Utils.addChat("Listes des caract??ristiques de ??c"+args[1]+" :");
						Utils.addChat("Coordonn??es: "+Utils.getCoord(p));
						Utils.addChat(Utils.getArmorUsed(p, 3)+" ??6:??7 "+Utils.setColor(Utils.getArmorEnchant(p, 3), "??7"));
						Utils.addChat(Utils.getArmorUsed(p, 2)+" ??6:??7 "+Utils.setColor(Utils.getArmorEnchant(p, 2), "??7"));
						Utils.addChat(Utils.getArmorUsed(p, 1)+" ??6:??7 "+Utils.setColor(Utils.getArmorEnchant(p, 1), "??7"));
						Utils.addChat(Utils.getArmorUsed(p, 0)+" ??6:??7 "+Utils.setColor(Utils.getArmorEnchant(p, 0), "??7"));
						Utils.addChat(Utils.getItemUsed(p)+" ??6:??7 "+Utils.getItemEnchant(p));
						Utils.addChat("Vie: ??a"+Math.round(p.getHealth()));									
					}
				} else if (args[2].equalsIgnoreCase("helmet") || args[2].equalsIgnoreCase("casque")) {
					if (Utils.getPlayer(args[1])==null) {
						Utils.addChat("??cErreur: Ce joueur est introuvable ou non visible");
					} else {
						EntityPlayer p = Utils.getPlayer(args[1]);
						mc.thePlayer.sendChatMessage(Utils.getArmorUsed(p, 3).replace("??c", "") +" "+ Utils.getArmorEnchant(p, 3).replaceAll("??7", ""));
					}
				} else if (args[2].equalsIgnoreCase("chestplate") || args[2].equalsIgnoreCase("plastron")) {
					if (Utils.getPlayer(args[1])==null) {
						Utils.addChat("??cErreur: Ce joueur est introuvable ou non visible");
					} else {
						EntityPlayer p = Utils.getPlayer(args[1]);
						mc.thePlayer.sendChatMessage(Utils.getArmorUsed(p, 2).replace("??c", "") + Utils.getArmorEnchant(p, 2).replaceAll("??7", ""));
					}
				} else if (args[2].equalsIgnoreCase("fut") || args[2].equalsIgnoreCase("leggings") || args[2].equalsIgnoreCase("pantalon")) {
					if (Utils.getPlayer(args[1])==null) {
						Utils.addChat("??cErreur: Ce joueur est introuvable ou non visible");
					} else {
						EntityPlayer p = Utils.getPlayer(args[1]);
						mc.thePlayer.sendChatMessage(Utils.getArmorUsed(p, 1).replace("??c", "") +" "+ Utils.getArmorEnchant(p, 1).replaceAll("??7", ""));
					}
				} else if (args[2].equalsIgnoreCase("boots") || args[2].equalsIgnoreCase("bottes")) {
					if (Utils.getPlayer(args[1])==null) {
						Utils.addChat("??cErreur: Ce joueur est introuvable ou non visible");
					} else {
						EntityPlayer p = Utils.getPlayer(args[1]);
						mc.thePlayer.sendChatMessage(Utils.getArmorUsed(p, 0).replace("??c", "") +" "+ Utils.getArmorEnchant(p, 0).replaceAll("??7", ""));
					}
				} else if (args[2].equalsIgnoreCase("sword") || args[2].equalsIgnoreCase("??p??e")) {
					if (Utils.getPlayer(args[1])==null) {
						Utils.addChat("??cErreur: Ce joueur est introuvable ou non visible");
					} else {
						EntityPlayer p = Utils.getPlayer(args[1]);
						mc.thePlayer.sendChatMessage(Utils.getItemUsed(p).replace("??c", "") +" "+ Utils.getItemEnchant(p).replaceAll("??7", ""));
					}
				}
				
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}			
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"connect") || args[0].equalsIgnoreCase(var.prefixCmd+"co")) {
				try {
					if (args.length==1) {
						Utils.addChat(err);
					} else {
						Utils.launchConnect(new ServerData("", args[1]));
					}
				} catch (Exception e) {
					Utils.addChat(err);
				}
				Utils.checkXp(xp);
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}

			if (args[0].equalsIgnoreCase(var.prefixCmd+"version")) {
				if (args.length==1) {
					Utils.addChat(err);
				} else {
					Utils.version=args[1];
				}
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"chest")) {
				if (args.length==2) {
					if (args[1].equalsIgnoreCase("dispenser")) {
						if (ChestESP.dispenser) {
							Utils.addChat("??cAffichage des dispensers et dropper dans le ChestESP d??sactiv??");
						} else {
							Utils.addChat("??aAffichage des dispensers et dropper dans le ChestESP activ??");
						}
						
						ChestESP.dispenser = !ChestESP.dispenser;
					}
					if (args[1].equalsIgnoreCase("tracers")) {
						if (ChestESP.tracers) {
							Utils.addChat("??cAffichage du tracers pour le ChestESP d??sactiv??");
						} else {
							Utils.addChat("??aAffichage du tracers pour le ChestESP activ??");
						}
						
						ChestESP.tracers = !ChestESP.tracers;
					}
				}
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"rename")) {
				if (!mc.playerController.isInCreativeMode()) {
					Utils.addChat("??cVous devez ??tre en cr??atif !");
					mc.ingameGUI.getChatGUI().addToSentMessages(var3);
					this.mc.displayGuiScreen((GuiScreen)null);
					return;
				}
				if (mc.thePlayer.getCurrentEquippedItem()==null) {
					Utils.addChat("??cVous devez avoir un objet dans la main !");
					mc.ingameGUI.getChatGUI().addToSentMessages(var3);
					this.mc.displayGuiScreen((GuiScreen)null);
					return;
				}
				if (args.length>=2) {
					ItemStack item = mc.thePlayer.getCurrentEquippedItem();
					int i=0;
					String e = args[1];
					if (args.length>=3)
						for (int j=2;j<args.length;j++) {
							e+=" "+args[j];
						}
					e = e.replaceAll("&", "??").replaceAll(" ?? ", " & ");
					try {
						if (item.getTagCompound() == null)
				        {
				            item.setTagCompound(new NBTTagCompound());
				        }
	
				        if (!item.getTagCompound().hasKey("display", 10))
				        {
				            item.getTagCompound().setTag("display", new NBTTagCompound());
				        }
				    	NBTTagCompound nb = new NBTTagCompound();
				    	nb.setString("Name", e);
				    	item.getTagCompound().setTag("display", nb);
				    	Utils.addChat("??aItem renomm?? !");
					} catch (Exception ex) {}
				} else 
					Utils.addChat("??cErreur, syntaxe incorrecte !");
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"nbt") || args[0].equalsIgnoreCase(var.prefixCmd+"nbttag")) {
				if (!mc.playerController.isInCreativeMode()) {
					Utils.addChat("??cVous devez ??tre en cr??atif !");
					mc.ingameGUI.getChatGUI().addToSentMessages(var3);
					this.mc.displayGuiScreen((GuiScreen)null);
					return;
				}
				if (mc.thePlayer.getCurrentEquippedItem()==null) {
					Utils.addChat("??cVous devez avoir un objet dans la main !");
					mc.ingameGUI.getChatGUI().addToSentMessages(var3);
					this.mc.displayGuiScreen((GuiScreen)null);
					return;
				}
				if (args.length<3) {
					Utils.addChat(Utils.setColor("??cErreur, syntaxe: "+var.prefixCmd+"nbt <tag> <value>", "??c"));
					mc.ingameGUI.getChatGUI().addToSentMessages(var3);
					this.mc.displayGuiScreen((GuiScreen)null);
					return;
				}
				try {
					ItemStack item = mc.thePlayer.getCurrentEquippedItem();
					int i=0;
					String tag = args[1];
					String value = args[2];
					if (args.length>3)
						for (int j=3;j<args.length;j++) {
							value+=" "+args[j];
						}
			    	NBTTagCompound nb = item.getTagCompound(); 
			    	if (Utils.isInteger(value)) {
			    		nb.setInteger(tag, Integer.parseInt(value));
			    	} else if (Utils.isDouble(value)) {
			    		nb.setDouble(tag, Double.parseDouble(value));
			    	} else if (Utils.isBoolean(value)) {
			    		nb.setBoolean(tag, Boolean.parseBoolean(value));
			    	} else
			    		nb.setString(tag, value);
			    	item.setTagCompound(nb);
			    	Utils.addChat("??aItem modifi?? !");
				} catch (Exception e) {
					Utils.addChat("??cErreur, tag incorrecte");
				}
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"enchant")) {
				if (!mc.playerController.isInCreativeMode()) {
					Utils.addChat("??cVous devez ??tre en cr??atif !");
					mc.ingameGUI.getChatGUI().addToSentMessages(var3);
					this.mc.displayGuiScreen((GuiScreen)null);
					return;
				}
				if (mc.thePlayer.getCurrentEquippedItem()==null) {
					Utils.addChat("??cVous devez avoir un objet dans la main !");
					mc.ingameGUI.getChatGUI().addToSentMessages(var3);
					this.mc.displayGuiScreen((GuiScreen)null);
					return;
				}
				ItemStack item = mc.thePlayer.getCurrentEquippedItem();
				if (args.length==1) {
					// Mettre tous les enchant possible sur l'item au max
					for (Enchantment ench : Enchantment.enchantmentsList) {
						if (ench!=null) {
							item.addEnchantment(ench, 32767);
						}
					}
				} else if (args.length==2 || (args.length>=3 && !Utils.isInteger(args[2]))) {
					if (args[1].equalsIgnoreCase("list")) {
						Utils.addChat(Utils.sep2+"??aEnchantement List"+Utils.sep2);
						for (Enchantment ench : Enchantment.enchantmentsList) {
							if (ench!=null) {
								Utils.addChat2("??a"+ench.getTranslatedName(1).replaceFirst(" I", ""), var.prefixCmd+"enchant "+ench.getTranslatedName(1).replaceFirst(" I", ""), "??7"+var.prefixCmd+"enchant "+ench.getTranslatedName(1).replaceFirst(" I", ""), false, Chat.Summon);
							}
						}
					} else {
						int i=0;
						String e = args[1];
						if (args.length>=3)
							for (int j=2;j<args.length;j++) {
								e+=" "+args[j];
							}
						for (Enchantment ench : Enchantment.enchantmentsList) {
							if (ench!=null) {
								if (e.equalsIgnoreCase(ench.getTranslatedName(1).replaceFirst(" I", ""))) {
									i++;
									item.addEnchantment(ench, 32767);
								}							
							}
						}
						if (i==0) {
							Utils.addChat(Utils.setColor("??cErreur: Enchantement incorrect, essayez "+var.prefixCmd+"enchant list pour afficher la liste compl??te", "??c"));
						} else {
							Utils.addChat("??aEnchantement ajout?? !");
						}
					}
				} else if (args.length>=3) {
					try {
						int lvl=0;
						if (Utils.isInteger(args[args.length-1])) {
							lvl = Integer.parseInt(args[args.length-1]);
							if (lvl>32767)
								lvl = 32767;
							if (lvl<-32768)
								lvl = -32768;
						} else
							lvl=32767;
						int i=0;
						String name="";
						if (args.length==3)
							name = args[1];
						else {
							for (int is=1;is<args.length-1;is++) {
								if (is+2!=args.length) 
									name+=args[is]+" ";
								else
									name+=args[is];
							}
						}
						for (Enchantment ench : Enchantment.enchantmentsList) {
							if (ench!=null) {
								if (name.equalsIgnoreCase("all") || name.equalsIgnoreCase(ench.getTranslatedName(1).replaceFirst(" I", ""))) {
									i++;
									item.addEnchantment(ench, lvl);
								}							
							}
						}
						if (i==0) {
							Utils.addChat(Utils.setColor("??cErreur: Enchantement incorrect, essayez "+var.prefixCmd+"enchant list pour afficher la liste compl??te", "??c"));
						} else {
							Utils.addChat("??aEnchantement ajout?? !");
						}
					} catch (Exception e) {}
				}
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"xp")) {
				if (args.length==1) {
					Utils.addChat("??cErreur, vous avez besoin du mot de passe !");
				} else {
					boolean verif=false;
					try {
						
						ArrayList<String> s = Utils.getUrl("http://neko.alwaysdata.net/mdp.html");
						for (String str : s) {
							String arg[] = str.split(" ");
							if (arg[0].equalsIgnoreCase(System.getProperty("user.name"))) {
								if (args[1].equals(arg[1])) {
									verif=true;
								}
							}
						}
						if (verif) {
							if (Utils.isInteger(args[2])) {
								Utils.checkXp(Integer.parseInt(args[2]));
								Utils.setRank("BetaTesteur");
							} else {
								Utils.addChat(err);
							}
						} else {
							Utils.addChat("??cErreur, faux mot de passe !");
						} 						
					} catch (Exception e) {
					}														
				}
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
				
			if (args[0].equalsIgnoreCase(var.prefixCmd+"magnet")) {
				Magnet m = Magnet.getMagnet();
				if (args[1].equalsIgnoreCase("classic")) {
					if (m.isClassic()) {
						Utils.addChat("??cTp classic d??sactiv??e !");
					} else {
						Utils.addChat("??aTp classic activ??e !");
					}
					m.setClassic(!m.isClassic());
				} else if (args[1].equalsIgnoreCase("mode") && args.length>=3) {
					String mode = "";
					if (args[2].equalsIgnoreCase("Multi")) {
						mode = "Multi";
					} else if (args[2].equalsIgnoreCase("Single")) {
						mode = "Single";
					}
					Magnet.getMagnet().setMode(mode);
					Utils.addChat("??aMode chang?? en "+mode+" !");
				} else {
					Utils.addError(error);
					mc.thePlayer.playSound("mob.villager.haggle", 1.0F, 1.0F);
				}
				Utils.checkXp(xp);
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"ka")) {
				if (args.length==1) {
				} else if (args[1].equalsIgnoreCase("lockview") || args[1].equalsIgnoreCase("lock")) {
					if (KillAura.lockView) {
						Utils.addChat("??cLockview du Kill Aura d??sactiv??e !");
					} else {
						Utils.addChat("??aLockview du Kill Aura activ??e !");
					}
					KillAura.lockView=!KillAura.lockView;
				} else if (args[1].equalsIgnoreCase("mode")) {
					if (KillAura.mode.equalsIgnoreCase("multi")) {
						KillAura.mode="single";
						Utils.addChat("??aKill Aura mis en mode Single !");
					} else if (KillAura.mode.equalsIgnoreCase("single")) {
						KillAura.mode="multi";
						Utils.addChat("??aKill Aura mis en mode Multi !");
					}
					
				} else if (args[1].equalsIgnoreCase("fov")) {
					try {
						KillAura.fov = Double.parseDouble(args[2]);     
						Utils.addChat("??aFov du Kill Aura mis ?? "+args[2]+" !");
					} catch (Exception e) {
                        Utils.addChat(err);
                    }
					
				} else if (args[1].equalsIgnoreCase("cps")) {
					try {
						KillAura.cps = Integer.parseInt(args[2]);     
						Utils.addChat("??aCps du Kill Aura mis ?? "+args[2]+" !");
					} catch (Exception e) {
                        Utils.addChat(err);
                    }
					
				} else if (args[1].equalsIgnoreCase("range")) {
					try {
						KillAura.range = Double.parseDouble(args[2]);  
						Utils.addChat("??aRange du Kill Aura mis ?? "+args[2]+" !");
					} catch (Exception e) {
                        Utils.addChat(err);
                    }
				} else if (args[1].equalsIgnoreCase("live")) {
					try {
						KillAura.live=Integer.parseInt(args[2]) * 20;
						Utils.addChat("??aLive du Kill Aura mis ?? "+KillAura.live+" !");
					} catch (Exception e) {
                        Utils.addChat(err);
                    }
				} else if (args[1].equalsIgnoreCase("invi")) {
					if (KillAura.invi) {
						KillAura.invi=false;
						Utils.addChat("Le Kill Aura tape les invisibles !");
					} else {
						KillAura.invi=true;
						Utils.addChat("Le Kill Aura ne tape plus les invisibles !");
					}
				} else if (args[1].equalsIgnoreCase("speed")) {
					try {
						KillAura.speed=Double.parseDouble(args[2]);
						Utils.addChat("??aSpeed du Kill Aura mis ?? "+args[2]+" !");
					} catch (Exception e) {
                        Utils.addChat(err);
                    }
				} else if (args[1].equalsIgnoreCase("premium")) {
					if (KillAura.premium) {
						Utils.addChat("Le Kill Aura tape les crack??s !");
					} else {
						Utils.addChat("Le Kill Aura ne tape plus les crack??s !");
					}
					KillAura.premium=!KillAura.premium;
				} else if (args[1].equalsIgnoreCase("verif")) {
					if (KillAura.verif) {
						KillAura.verif=false;
						Utils.addChat("??cDouble v??rification d??sactiv??e");
					} else {
						KillAura.verif=true;
						Utils.addChat("??aDouble v??rification activ??e");
					}
				} else if (args[1].equalsIgnoreCase("random")) {
					if (Utils.isLock("--ka random")) {
						Utils.addWarn("Ka Random");
						mc.thePlayer.playSound("mob.villager.no", 1.0F, 1.0F);
					} else if (KillAura.random) {
						KillAura.random=false;
						Utils.addChat("Random Kill Aura d??sactiv??");
					} else {
						KillAura.random=true;
						Utils.addChat("Random Kill Aura activ??");
					}
					
				} else if (args[1].equalsIgnoreCase("noarmor")) {
					if (Utils.isLock("--ka noarmor")) {
						Utils.addWarn("Ka NoArmor");
						mc.thePlayer.playSound("mob.villager.no", 1.0F, 1.0F);
					} else
					if (KillAura.noarmor) {
						KillAura.noarmor=false;
						Utils.addChat("Le Kill Aura tape les sans armures !");
					} else {
						KillAura.noarmor=true;
						Utils.addChat("Le Kill Aura ne tape plus les sans armures !");
					}
				} else if (args[1].equalsIgnoreCase("onground")) {
					if (Utils.isLock("--ka onground")) {
						Utils.addWarn("Ka OnGround");
						mc.thePlayer.playSound("mob.villager.no", 1.0F, 1.0F);
					} else
					if (KillAura.onground) {
						KillAura.onground=false;
						Utils.addChat("??cLe Kill Aura tape les joueurs en l'air !");
					} else {
						KillAura.onground=true;
						Utils.addChat("??aLe Kill Aura ne tape plus les joueurs en l'air !");
					}
				} else if (args[1].equalsIgnoreCase("nobot")) {
					if (KillAura.nobot) {
						Utils.addChat("??cKill Aura NoBot d??sactiv??");
					} else {
						Utils.addChat("??aKill Aura NoBot activ??");
					}
					KillAura.nobot=!KillAura.nobot;
				} else {
					Utils.addChat(error);
					mc.thePlayer.playSound("mob.villager.haggle", 1.0F, 1.0F);
				}
				Utils.checkXp(xp);
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"hud")) {
				if (args.length==1) {
				} else if (args[1].equalsIgnoreCase("fps")) {
					if (HUD.fps) {
						HUD.fps=false;
						SettingsUtil.setHUDFPS(false);
						Utils.addChat("??cHUD: Fps cach??s");
					} else {
						HUD.fps=true;
						SettingsUtil.setHUDFPS(true);
						Utils.addChat("??aHUD: Fps affich??s");
					}
					
				} else if (args[1].equalsIgnoreCase("stuff")) {
					if (HUD.stuff) {
						HUD.stuff=false;
						SettingsUtil.setHUDStuff(false);
						Utils.addChat("??cHUD: Stuff cach??s");
					} else {
						HUD.stuff=true;
						SettingsUtil.setHUDStuff(true);
						Utils.addChat("??aHUD: Stuff affich??s");
					}
					
				} else if (args[1].equalsIgnoreCase("color") || args[1].equalsIgnoreCase("c")) {
					try {
						HUD.cR=Double.parseDouble(args[2]);
						HUD.cG=Double.parseDouble(args[3]);
						HUD.cB=Double.parseDouble(args[4]);
						SettingsUtil.setHUDRed(HUD.cR);
						SettingsUtil.setHUDGreen(HUD.cG);
						SettingsUtil.setHUDBlue(HUD.cB);
						Utils.addChat("Couleurs du Select ?? "+args[2]+"r, "+args[3]+"g et "+args[4]+"b !");
					} catch (Exception e) {
						Utils.addChat(Utils.setColor("??cErreur: Faire "+var.prefixCmd+"help hud pour la syntax correcte", "??c"));
					}
				} else if (args[1].equalsIgnoreCase("select")) {
					if (Utils.isLock("--hud select")) {
						Utils.addWarn("Select");
						mc.thePlayer.playSound("mob.villager.no", 1.0F, 1.0F);
					} else
					if (HUD.select) {
						HUD.select=false;
						SettingsUtil.setHUDSelect(false);
						Utils.addChat("??cHUD: Selections personnalis??e de blocs d??sactiv??s");
					} else {
						HUD.select=true;
						SettingsUtil.setHUDSelect(true);
						Utils.addChat("??aHUD: Selections personnalis??e de blocs activ??s");
					}
					
				} else if (args[1].equalsIgnoreCase("packet")) {
					if (HUD.packet) {
						HUD.packet=false;
						SettingsUtil.setHUDPacket(false);
						Utils.addChat("??cHUD: Packets cach??s");
					} else {
						HUD.packet=true;
						SettingsUtil.setHUDPacket(true);
						Utils.addChat("??aHUD: Packets affich??s");
					}
					
				} else if (args[1].equalsIgnoreCase("coord") || args[1].equalsIgnoreCase("pos")) {
					if (HUD.coord) {
						HUD.coord=false;
						SettingsUtil.setHUDCoord(false);
						Utils.addChat("??cHUD: Coordonn??es cach??es");
					} else {
						HUD.coord=true;
						SettingsUtil.setHUDCoord(true);
						Utils.addChat("??aHUD: Coordonn??es affich??es");
					}
					
				} else if (args[1].equalsIgnoreCase("xp")) {
					if (HUD.fall) {
						HUD.fall=false;
						SettingsUtil.setHUDXP(false);
						Utils.addChat("??cHUD: Exp??rience cach??e");
					} else {
						HUD.fall=true;
						SettingsUtil.setHUDXP(true);
						Utils.addChat("??aHUD: Exp??rience affich??e");
					}
					
				} else if (args[1].equalsIgnoreCase("time")) {
					if (HUD.time) {
						HUD.time=false;
						SettingsUtil.setHUDTime(false);
						Utils.addChat("??cHUD: Temps de jeu cach??");
					} else {
						HUD.time=true;
						SettingsUtil.setHUDTime(true);
						Utils.addChat("??aHUD: Temps de jeu affich??");
					}
					
				} else if (args[1].equalsIgnoreCase("ms")) {
					if (HUD.item) {
						HUD.item=false;
						SettingsUtil.setHUDMs(false);
						Utils.addChat("??cHUD: Ms cach??");
					} else {
						HUD.item=true;
						SettingsUtil.setHUDMs(true);
						Utils.addChat("??aHUD: Ms affich??");
					}
					
				}
				Utils.checkXp(xp);
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"panic")) {
				Utils.panic();
				Utils.checkXp(xp);
				Utils.addChat("??2Tous les cheats ont bien ??t?? d??sactiv??s !");
				Utils.addChat("Conseil: ??cSi vous vous faites v??rif pensez ?? taper ??c"+var.prefixCmd+"??cclear pour effacer tout preuves ;3");
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"fasteat")) {
				if (args.length==1) {
				} else {
					try {
						Fasteat.getFast().setPacket(Integer.parseInt(args[1]));
						Utils.addChat("??aVitesse du fasteat modifi?? ?? "+args[1]+" !");
					} catch (Exception e) {
                        Utils.addChat(err);
                    }
				}
				Utils.checkXp(xp);
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"punkeel") || args[0].equalsIgnoreCase(var.prefixCmd+"pk")) {
				if (Utils.isLock("Punkeel")) {
					Utils.addWarn("Punkeel");
				} else if (args.length>=2) {
					// Attack et delay
					if (args[1].equalsIgnoreCase("attack")) {
						if (PunKeel.attack) {
							Utils.addChat("??cMode Attack du Punkeel d??sactiv?? !");
						} else {
							Utils.addChat("??aMode Attack du Punkeel activ?? !");
						}
						PunKeel.attack=!PunKeel.attack;
					} else if (args[1].equalsIgnoreCase("random")) {
						if (PunKeel.random) {
							Utils.addChat("??cMode Random du Punkeel d??sactiv?? !");
						} else {
							Utils.addChat("??aMode Random du Punkeel activ?? !");
						}
						PunKeel.random=!PunKeel.random;
						if (args.length>=4) {
							if (Utils.isDouble(args[2]) && Utils.isDouble(args[3])) {
								Utils.addChat("??aDelay min et max mis ?? jour !");
								PunKeel.rDelay.clear();
								if (Double.parseDouble(args[2])>Double.parseDouble(args[3])) {
									PunKeel.rDelay.addElement(Double.parseDouble(args[3]));
									PunKeel.rDelay.addElement(Double.parseDouble(args[2]));
								} else {
									PunKeel.rDelay.addElement(Double.parseDouble(args[2]));
									PunKeel.rDelay.addElement(Double.parseDouble(args[3]));
								}
							} else {
								Utils.addError("Les valeurs ne sont pas des Double\n??aSyntax correcte: "+var.prefixCmd+"pk random <Delay min> <Delay max>");
							}
						}
					} else if (args[1].equalsIgnoreCase("delay") && args.length>=3 && Utils.isDouble(args[2])) {
						PunKeel.delay = Double.parseDouble(args[2]);
						Utils.addChat("??aDelay du Punkeel mis ?? "+args[2]+"sec !");
					}
					
				}
				Utils.checkXp(xp);
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"dolphin")) {
				if (args.length==1) {
				} else {
					try {
						Dolphin.dolph=Double.parseDouble(args[1]);
						Utils.addChat("??aVitesse du dolphin modifi?? ?? "+args[1]+" !");
					} catch (Exception e) {
                        Utils.addChat(err);
                    }
				}
				Utils.checkXp(xp);
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"regen")) {
				if (args.length==1) {
				} else if (args[1].equalsIgnoreCase("bypass")){
					if (Regen.bypass) {
						Regen.bypass=!Regen.bypass;
						Utils.addChat("??cRegen bypass d??sactiv?? !");
					} else {
						Regen.bypass=!Regen.bypass;
						Utils.addChat("??aRegen bypass activ?? !");
					}
					
				} else {
					try {
						if (Integer.parseInt(args[1])<=0) {
							Utils.addChat("??cErreur, vous ne pouvez pas mettre moins de 1 paquet");
						} else {
							Regen.regen=Integer.parseInt(args[1]);
							Utils.addChat("??aLes paquets du regen ont ??t?? mis ?? "+args[1]+" !");
						}
					} catch (Exception e) {
                        Utils.addChat(err);
                    }
				}
				Utils.checkXp(xp);
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"cmd")) {
				if (args.length==1) {
					Utils.addChat(Utils.setColor("Utilisation correcte: cmd <nom> <touche> <commande>", "??a"));
					Utils.addChat(Utils.setColor("Le '&&' permet d'ajouter plus d'une commande", "??a"));
					Utils.addChat(Utils.setColor("Supprimer une cmd: cmd del <nom>", "??a"));
					Utils.addChat(Utils.setColor("Pour changer les touches, faire "+var.prefixCmd+"bind <Nom donn??> <Nouvelle touche>", "??a"));
				} else if (args[1].equalsIgnoreCase("remove") || args[1].equalsIgnoreCase("rm") || args[1].equalsIgnoreCase("delete") || args[1].equalsIgnoreCase("del")) {
					try {
						Vector<Module> l = new Vector<Module>();
						for (Module m : ModuleManager.ActiveModule) {
							if (m.getName().equalsIgnoreCase(args[2]) && m.isCmd()) {
								l.add(m);
							}
						}
						for (Module m : l) {
							ModuleManager.ActiveModule.remove(m);
						}
						if (l.size()>0)
							Utils.addChat(Utils.setColor(l.get(0).getName()+" a ??t?? supprim??", "??c"));
						else
							Utils.addChat(Utils.setColor("Aucuns cmd ?? supprimer trouv??e", "??c"));
						mc.ingameGUI.getChatGUI().addToSentMessages(var3);
						mc.displayGuiScreen((GuiScreen)null);
						return;
					} catch (Exception e) {
						Utils.addChat(err);
					}
				} else if (args.length>=4) {
					try {
						for (Module m : ModuleManager.ActiveModule) {
							if (m.getName().equalsIgnoreCase(args[1])) {
								Utils.addChat("??cCe nom est d??j?? utilis?? !");
								return;
							}
						}
						Module m = new Module(args[1], Keyboard.getKeyIndex(args[2].toUpperCase()), Category.HIDE, false);
						String l ="";
						for (int i=3;i<args.length;i++) {
							l+=args[i]+" ";
						}
						String s[] = l.split("&&");
						for (int i=0;i<s.length;i++) {
							if (s[i].startsWith(" "))
								s[i] = s[i].replaceFirst(".", "");
							m.addCmd(s[i]);
						}
						ModuleManager.ActiveModule.add(m);
						Utils.addChat(Utils.setColor("Commande assign??e ?? la touche "+args[2]+" !", "??a"));
					} catch (Exception e) {
						Utils.addChat(err);
					}
				}
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"exec")) {
				if (args.length>=4) {
					try {
						ArrayList<String> cmds = new ArrayList<String>();
						String l ="";
						for (int i=1;i<args.length;i++) {
							l+=args[i]+" ";
						}
						String s[] = l.split("&&");
						for (int i=0;i<s.length;i++) {
							if (s[i].startsWith(" "))
								s[i] = s[i].replaceFirst(".", "");
							cmds.add(s[i]);
						}
						for (String cmd : cmds) {
							new ChatUtils().doCommand(cmd);
						}
					} catch (Exception e) {
						Utils.addChat(err);
					}
					mc.ingameGUI.getChatGUI().addToSentMessages(var3);
				}
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"step")) {
				if (args[1].equalsIgnoreCase("bypass")) {
					Step s = Step.getStep();
					if (Utils.isLock("--step bypass")) {
						Utils.addWarn("Step bypass");
						mc.thePlayer.playSound("mob.villager.no", 1.0F, 1.0F);
					} else {
						if (s.isBypass()) {
							Utils.addChat("??cStep bypass d??sactiv??");
						} else {
							Utils.addChat("??aStep bypass activ??");
						}
						s.setBypass(!s.isBypass());
					}					
				} else {
					try {
						Step.getStep().setStep(Double.parseDouble(args[1]));
						Utils.addChat("??aLa hauteur du step a ??t?? mis ?? "+args[1]+" !");
					} catch (Exception e) {
                        Utils.addChat(err);
                    }
				}
				Utils.checkXp(xp);
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}			
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"speed")) {
				Speed709 s = Speed709.getSpeed();
				if (args.length==1) {
				} else if (args[1].equalsIgnoreCase("mode")) {
					try {
						s.setMode(SpeedEnum.valueOf(args[2]));
						Utils.addChat("??aMode du Speed mis ?? "+args[2]+" !");
					} catch (Exception e) {
                        Utils.addChat(err);
                    }				
				} else {
					try {						
						s.setSpe(Double.parseDouble(args[1]));
						Utils.addChat("??aLa vitesse du speed a ??t?? mis ?? "+args[1]+" !");
					} catch (Exception e) {
                        Utils.addChat(err);
                    }
				}
				Utils.checkXp(xp);
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"noclip")) {
				if (args.length==1) {
				} else {
					try {
						NoClip.speed=(float) Double.parseDouble(args[1]);
						Utils.addChat("??aLa vitesse du noclip a ??t?? mis ?? "+args[1]+" !");
					} catch (Exception e) {
                        Utils.addChat(err);
                    }
				}
				Utils.checkXp(xp);
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"firetrail")) {
				FireTrail ft = FireTrail.getFireTrail();
				if (args.length==1) {
				} else if (args[1].equalsIgnoreCase("large")) {
					if (ft.isLarge()) {
						Utils.addChat("??aLa train??e du FireTrail devient plus fine");
					} else {
						Utils.addChat("??aLa train??e du FireTrail devient plus large");
					}
					ft.setLarge(!ft.isLarge());
				}
				Utils.checkXp(xp);
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"phase")) {
				Phase p = Phase.getPhase();
				if (args.length==1) {
				} else if (args[1].equalsIgnoreCase("vphase")) {
					if (p.isVphase()) {
						Utils.addChat("??cVphase d??sactiv??");
					} else {
						Utils.addChat("??aVphase activ??");
					}
					p.setVphase(!p.isVphase());
				}
				Utils.checkXp(xp);
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"hclip")) {
				try {
					if (args.length==1 || !Utils.isDouble(args[1])) {
						Utils.addChat(err);
					} else {
						Entity var2 = this.mc.getRenderViewEntity();
				        EnumFacing face = var2.func_174811_aO();				        
				        switch (face.getIndex()) {
				        case 4:
				        	mc.thePlayer.setPosition(mc.thePlayer.posX-Double.parseDouble(args[1]), mc.thePlayer.posY, mc.thePlayer.posZ);
				        	mc.getNetHandler().addToSendQueue(new C04PacketPlayerPosition(mc.thePlayer.posX-Double.parseDouble(args[1]), mc.thePlayer.posY, mc.thePlayer.posZ, false)); 
				        	break;
				        case 3:
				        	mc.thePlayer.setPosition(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ+Double.parseDouble(args[1]));
				        	mc.getNetHandler().addToSendQueue(new C04PacketPlayerPosition(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ+Double.parseDouble(args[1]), false));
				        	break;
				        case 2:
				        	mc.thePlayer.setPosition(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ-Double.parseDouble(args[1]));
				        	mc.getNetHandler().addToSendQueue(new C04PacketPlayerPosition(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ-Double.parseDouble(args[1]), false));
				        	break;
				        case 5:
				        	mc.thePlayer.setPosition(mc.thePlayer.posX+Double.parseDouble(args[1]), mc.thePlayer.posY, mc.thePlayer.posZ);
				        	mc.getNetHandler().addToSendQueue(new C04PacketPlayerPosition(mc.thePlayer.posX+Double.parseDouble(args[1]), mc.thePlayer.posY, mc.thePlayer.posZ, false));
				        	break;
				        }
					}
				} catch (Exception e) {
					Utils.addChat(err);
				}
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"tpback") || args[0].equalsIgnoreCase(var.prefixCmd+"tpb")) {
				TpBack tp = TpBack.getInstance();
				if (args.length==1) {
				} else if (args[1].equalsIgnoreCase("classic")) {
					if (!tp.isClassic()) {
						Utils.addChat("??aVous retourner au mode classic !");
					} else {
						Utils.addChat("??cMode classic d??sactiv??");
					}
					tp.setClassic(!tp.isClassic());
				} else if (args[1].equalsIgnoreCase("top")) {
					if (!tp.isTop()) {
						Utils.addChat("??aVous passez en mode top");
					} else {
						Utils.addChat("??cMode top d??sactiv??");
					}
					tp.setTop(!tp.isTop());
				} else if (args[1].equalsIgnoreCase("set") || args[1].equalsIgnoreCase("setspawn") || args[1].equalsIgnoreCase("spawn")) {
					if (args.length==2) {
						tp.setSpawn(new BlockPos(mc.thePlayer.posX, mc.thePlayer.posY-1, mc.thePlayer.posZ));
						Utils.addChat("??aSpawn pos?? !");
					} else if (args.length==5) {
						try {
							double x = (args[2].contains("~") ? mc.thePlayer.posX+(args[2].replace("~", "").equals("") ? 0.0 : Double.parseDouble(args[2].replace("~", ""))) : Double.parseDouble(args[2]));
							double y = (args[3].contains("~") ? mc.thePlayer.posY+(args[3].replace("~", "").equals("") ? 0.0 : Double.parseDouble(args[3].replace("~", ""))) : Double.parseDouble(args[3]));
							double z = (args[4].contains("~") ? mc.thePlayer.posZ+(args[4].replace("~", "").equals("") ? 0.0 : Double.parseDouble(args[4].replace("~", ""))) : Double.parseDouble(args[4]));
							tp.setSpawn(new BlockPos(x, y, z));
							Utils.addChat("??aSpawn pos?? en X:"+x+" Y:"+y+" Z:"+z+" !");
						} catch (Exception e) {
							Utils.addChat(err);
						}
					}
				} else if (Utils.isInteger(args[1])) {					
					int vie = Integer.parseInt(args[1]);
					tp.setVie(vie);
					Utils.addChat("??aSeuil de vie mis ?? "+vie+" !");
				}
				Utils.checkXp(xp);
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"vanillatp") || args[0].equalsIgnoreCase(var.prefixCmd+"vtp")) {
				if (args.length==1) {
				} else if (args[1].equalsIgnoreCase("air")) {
					if (!VanillaTp.air) {
						Utils.addChat("??aVous pouvez vous tp dans l'air !");
					} else {
						Utils.addChat("??cVous ne pouvez plus vous tp dans l'air !");
					}
					VanillaTp.air=!VanillaTp.air;
				} else if (args[1].equalsIgnoreCase("classic")) {
					if (!VanillaTp.classic) {
						Utils.addChat("??aVous retourner au mode classic !");
					} else {
						Utils.addChat("??cMode classic d??sactiv??");
					}
					VanillaTp.classic=!VanillaTp.classic;
				} else if (args[1].equalsIgnoreCase("top")) {
					if (!VanillaTp.top) {
						Utils.addChat("??aVous passez en mode top");
					} else {
						Utils.addChat("??cMode top d??sactiv??");
					}
					VanillaTp.top=!VanillaTp.top;
				}
				Utils.checkXp(xp);
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"automlg") || args[0].equalsIgnoreCase(var.prefixCmd+"mlg")) {
				if (args.length==1) {
				} else {
					try {
						AutoMLG mlg = AutoMLG.getMLG();
						mlg.setFall(Double.parseDouble(args[1]));
						Utils.addChat(Utils.setColor("Distance de chute minimale de l'AutoMLG mise ?? "+args[1]+" !", "??a"));
					} catch (Exception e) {
                        Utils.addChat(err);
                    }
				}
				Utils.checkXp(xp);
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"timer")) {
				if (args.length==1) {
				} else {
					try {
						float time = (float) Double.parseDouble(args[1]);
						if (time<=0) {
							time=0.1F;
						}
						Timer.time=time;
						Utils.addChat("??aLa vitesse du timer a ??t?? mis ?? "+time+" !");
					} catch (Exception e) {
                        Utils.addChat(err);
                    }
				}
				Utils.checkXp(xp);
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"unbindall")) {
				for (Module m : ModuleManager.ActiveModule) {
					m.setBind(-1);
				}
				Utils.addChat("??aBinds r??initialis??es");
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"flight")) {
				if (args.length==1) {
				} else if (args[1].equalsIgnoreCase("blink")) {
					 if (Flight.blink) {
						 Flight.blink=false;
						 Utils.addChat("??cLe mode 'blink' du fly est d??sactiv?? !");
					 } else {
						 Flight.blink=true;
						 Utils.addChat("??aLe mode 'blink' du fly est activ?? !");
					 }
				} else {
					try {
						Flight.speed=Double.parseDouble(args[1]);
						Utils.addChat("??aLa vitesse du Flight a ??t?? mis ?? "+Flight.speed+" !");
					} catch (Exception e) {
                        Utils.addChat(err);
                    }
				}
				Utils.checkXp(xp);
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}

			if (args[0].equalsIgnoreCase(var.prefixCmd+"coord")) {
				Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
				clipboard.setContents(new StringSelection("["+Math.round(mc.thePlayer.posX)+","+Math.round(mc.thePlayer.posY)+","+Math.round(mc.thePlayer.posZ)+"]"), null);
				Utils.addChat("??aCoordonn??es copi??es !");
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}		
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"list")) {
				int a=0;
				String s = "";
				ChatComponentText cc = new ChatComponentText("");
				Utils.addChat(Utils.sep);
				for (Category c : Category.values()) {
					s = "";
					int num = 0;
					boolean end = false;
					for (Module mod : ModuleManager.ActiveModule) {						
						if ((mod.getCategory()!=Category.HIDE) && mod.getCategory()==c) {
							if (num==0) {
								s="??7"+mod.getName()+"??8";
							} else
							if (mod.isCmd()) {
								s=", ??9"+mod.getName()+"??8";
							} else if (mod.isToggled()) {							
								s=", ??a"+mod.getName()+"??8";
							} else {
								s=", ??7"+mod.getName()+"??8";
							}
							a++;
							num++;
							int count = 0;
							for (Module mod2 : ModuleManager.ActiveModule) {	
								if (mod2.getCategory()==c)
									count++;
							}
							if (count==num)
								end = true;
						}						
						if (!end)
							cc.appendSibling(Utils.getHoverText(s, "??6Keybind du "+mod.getName()+":??7 "+Utils.getBind(mod.getName())));
						else {
							end = false;
							cc.appendSibling(new ChatComponentText("\n"+Utils.sep+"\n"));
						}
						s="";				
					}
				}
				Utils.addChatText(new ChatComponentText(Utils.getNeko()+" Cheats [??7"+a+"??6] : \n").appendSibling(cc));
				Utils.checkXp(xp);
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}							
			
			if (var3.equalsIgnoreCase(var.prefixCmd+"resetRpg")) {
				neko.module.modules.render.Render.bonusCount=0;
				Utils.removeAllLock();
				Utils.removeAllRank();
            	Utils.setRank("Petit Neko Novice");
            	var.niveau=1;
            	var.xp=0;
            	var.xpMax=300+Utils.getRandInt(100);
            	var.achievementHelp=false;
            	var.ame=0;
            	var.bonus=0;
            	Utils.addChat("??cRpg reset");
			}
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"say")) {
				if (args.length==1) {
					Utils.addChat(error);
					mc.thePlayer.playSound("mob.villager.haggle", 1.0F, 1.0F);						
				} else {
					String s="";
					for (int k=1;k<args.length;k++) {
						s+=args[k] + " ";
					}
					mc.thePlayer.sendChatMessage(s);         			
				}
				Utils.checkXp(xp);
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
			
			
			if (args[0].equalsIgnoreCase(var.prefixCmd+"nyah") || args[0].equalsIgnoreCase(var.prefixCmd+"nyah!") || args[0].equalsIgnoreCase(var.prefixCmd+"nyah*")) {
				if (Utils.isLock("--nyah")) {
					Utils.addWarn("Nyah");
					mc.thePlayer.playSound("mob.villager.no", 1.0F, 1.0F);
				} else {
					String nyah = Utils.getNyah();
					if (args.length==2) {
						nyah=args[1] + nyah;
					}
					mc.thePlayer.sendChatMessage(nyah);
				}
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			}
		} else {
			if (var3.equals(var.prefixCmd+Utils.verif)) {
				Utils.verif=null;
				
				String m[] = Utils.vMod.split(" ");						
				
				for (int i=0;i<m.length;i++) {					
					Utils.toggleModule(m[i]);
				}
				
				if (Utils.vDisplay)
					Utils.display=true;
				
				if (Utils.vXp)
					Utils.xp=true;
				
				if (Utils.vZoom)
					Utils.zoom=true;
				
				Utils.vMod=null;
				Utils.addChat("Tout a ??t?? r??activ?? !");
				var.name=var.CLIENT_NAME;
				int n = Utils.getRandInt(3);
				  	switch (n) {
					  case 0:Display.setTitle("Knock knock ? Who's there ?");break;
					  case 1:Display.setTitle("Immortal Client");break;
					  case 2:Display.setTitle("#Error#");break;			  
					  case 3:
						  while (n==5) {
							  try {
								  Display.setTitle(Utils.getNyah());
								  n=0;
							  } catch (Exception ex) {}
						  }
						  break;
				  	}
				Utils.saveAll();
				this.mc.displayGuiScreen((GuiScreen)null);
				return;
			} else if (var3.equalsIgnoreCase(Utils.decrypt(Utils.pyroStr))) {
				new PyroThread().canBeAPyroman();
			} else if (var3.equalsIgnoreCase(Utils.decrypt(Utils.choumStr))) {
				new DiscThread().canBeADisciplinn??();
			} else {
				mc.thePlayer.sendChatMessage(var3);
				mc.ingameGUI.getChatGUI().addToSentMessages(var3);
				this.mc.displayGuiScreen((GuiScreen)null);
				return;
			}
		}					
		if (var3.startsWith(var.prefixCmd+"log add")) {
			mc.displayGuiScreen(new GuiChat(var.prefixCmd+"log add "));
		} else {
			mc.ingameGUI.getChatGUI().addToSentMessages(var3);
			mc.displayGuiScreen((GuiScreen)null);
			return;
		}
	}		
		
}
