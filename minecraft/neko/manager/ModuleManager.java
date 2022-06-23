package neko.manager;

import java.util.ArrayList;
import java.util.HashMap;

import neko.lock.Lock;
import neko.module.Module;
import neko.module.modules.combat.AutoClic;
import neko.module.modules.combat.AutoPot;
import neko.module.modules.combat.AutoSword;
import neko.module.modules.combat.Autoblock;
import neko.module.modules.combat.Autosoup;
import neko.module.modules.combat.BowAimbot;
import neko.module.modules.combat.ClickAim;
import neko.module.modules.combat.Crit;
import neko.module.modules.combat.Exploit;
import neko.module.modules.combat.Fastbow;
import neko.module.modules.combat.KillAura;
import neko.module.modules.combat.Knockback;
import neko.module.modules.combat.Reach;
import neko.module.modules.combat.Regen;
import neko.module.modules.combat.SmoothAim;
import neko.module.modules.combat.TpKill;
import neko.module.modules.combat.Trigger;
import neko.module.modules.hide.Friends;
import neko.module.modules.hide.God;
import neko.module.modules.hide.Lot;
import neko.module.modules.hide.Plugins;
import neko.module.modules.hide.Test;
import neko.module.modules.misc.Antiafk;
import neko.module.modules.misc.AutoCmd;
import neko.module.modules.misc.AutoMLG;
import neko.module.modules.misc.Autonyah;
import neko.module.modules.misc.CallCmd;
import neko.module.modules.misc.Crasher;
import neko.module.modules.misc.HeadRoll;
import neko.module.modules.misc.Nameprotect;
import neko.module.modules.misc.Phase;
import neko.module.modules.misc.Ping;
import neko.module.modules.misc.PlaceAndBreak;
import neko.module.modules.misc.Register;
import neko.module.modules.misc.ReplyNyah;
import neko.module.modules.misc.Switch;
import neko.module.modules.misc.Timer;
import neko.module.modules.movements.AirWalk;
import neko.module.modules.movements.AutoWalk;
import neko.module.modules.movements.Blink;
import neko.module.modules.movements.Dolphin;
import neko.module.modules.movements.Fastladder;
import neko.module.modules.movements.Flash;
import neko.module.modules.movements.Flight;
import neko.module.modules.movements.Freecam;
import neko.module.modules.movements.Glide;
import neko.module.modules.movements.Highjump;
import neko.module.modules.movements.InstantPortal;
import neko.module.modules.movements.InventoryMove;
import neko.module.modules.movements.Jetpack;
import neko.module.modules.movements.Longjump;
import neko.module.modules.movements.NoClip;
import neko.module.modules.movements.NoFall;
import neko.module.modules.movements.Safewalk;
import neko.module.modules.movements.Speed709;
import neko.module.modules.movements.Sprint;
import neko.module.modules.movements.Step;
import neko.module.modules.params.Gui;
import neko.module.modules.params.HUD;
import neko.module.modules.player.AntiFire;
import neko.module.modules.player.AutoCraft;
import neko.module.modules.player.AutoTool;
import neko.module.modules.player.Autoarmor;
import neko.module.modules.player.Autoeat;
import neko.module.modules.player.Automine;
import neko.module.modules.player.Autorespawn;
import neko.module.modules.player.Build;
import neko.module.modules.player.Cheststealer;
import neko.module.modules.player.Eagle;
import neko.module.modules.player.FastPlace;
import neko.module.modules.player.Fastbreak;
import neko.module.modules.player.Fasteat;
import neko.module.modules.player.Fire;
import neko.module.modules.player.Jesus;
import neko.module.modules.player.Noslow;
import neko.module.modules.player.Nuker;
import neko.module.modules.player.PotionSaver;
import neko.module.modules.player.PushUp;
import neko.module.modules.player.Scaffold;
import neko.module.modules.player.Sneak;
import neko.module.modules.player.Velocity;
import neko.module.modules.render.ArmorHUD;
import neko.module.modules.render.ChestESP;
import neko.module.modules.render.Fullbright;
import neko.module.modules.render.ItemESP;
import neko.module.modules.render.Nametag;
import neko.module.modules.render.NekoChat;
import neko.module.modules.render.NoBlind;
import neko.module.modules.render.Paint;
import neko.module.modules.render.PotionEffect;
import neko.module.modules.render.Power;
import neko.module.modules.render.Premonition;
import neko.module.modules.render.Radar;
import neko.module.modules.render.Render;
import neko.module.modules.render.Rotator;
import neko.module.modules.render.Search;
import neko.module.modules.render.Tracers;
import neko.module.modules.render.Trail;
import neko.module.modules.render.Wallhack;
import neko.module.modules.render.Water;
import neko.module.modules.render.WorldTime;
import neko.module.modules.render.Xray;
import neko.module.modules.special.DropShit;
import neko.module.modules.special.FastDura;
import neko.module.modules.special.FireTrail;
import neko.module.modules.special.ForceTP;
import neko.module.modules.special.Likaotique;
import neko.module.modules.special.Limit;
import neko.module.modules.special.Magnet;
import neko.module.modules.special.Nausicaah;
import neko.module.modules.special.Near;
import neko.module.modules.special.NoAnim;
import neko.module.modules.special.NoLook;
import neko.module.modules.special.PunKeel;
import neko.module.modules.special.Pyro;
import neko.module.modules.special.Reflect;
import neko.module.modules.special.TpBack;
import neko.module.modules.special.Unicode;
import neko.module.modules.special.VanillaTp;
import neko.module.other.Conditions;
import neko.module.other.Necklace;
import neko.module.other.Rank;
import neko.module.other.enums.Rate;
import neko.utils.Utils;

public class ModuleManager {
	public static ArrayList<Module> ActiveModule = new ArrayList<Module>();
	public static ArrayList<Lock> Lock = new ArrayList<Lock>();
	public static ArrayList<Rank> rang = new ArrayList<Rank>();
	public static ArrayList<Necklace> necklace = new ArrayList<Necklace>();
	public static ArrayList<String> values = new ArrayList<String>();
	public static HashMap<Module, String> link = new HashMap<Module, String>();
	
	public Xray xrayModule;

	public ModuleManager() {	

		this.ActiveModule.clear();
		this.Lock.clear();
		this.rang.clear();
		this.values.clear();

		Combat(); Render(); Player(); Movement(); Misc(); Hide(); Special();
		
		// Ajouter les modules
		ActiveModule.add(new InstantPortal());
		ActiveModule.add(new Sprint()); // Key K
		ActiveModule.add(new KillAura()); // Key V
		ActiveModule.add(new Fullbright()); // Key B
		ActiveModule.add(new Step()); // Key I
		ActiveModule.add(new FastPlace()); // Key U
		ActiveModule.add(this.xrayModule = new Xray()); // Key X
		ActiveModule.add(new NoFall()); // Key RSHIFT
		ActiveModule.add(new Speed709()); // Key M
		ActiveModule.add(new NoClip()); // Key N
		ActiveModule.add(new Regen()); // Key G
		ActiveModule.add(new Flight()); // Key R
		//ActiveModule.add(new UnclaimFinder());
		ActiveModule.add(new Autoarmor()); // Key NONE
		ActiveModule.add(new Automine()); //Key NONE
		ActiveModule.add(new AutoCmd()); //Key NONE
		ActiveModule.add(new Dolphin()); // Key J
		ActiveModule.add(new VanillaTp()); // Key TAB
		ActiveModule.add(new Timer()); // Key Y
		ActiveModule.add(new Friends()); // Key NONE | Btn 3 pour add et remove
		ActiveModule.add(new TpKill()); // Key F
		ActiveModule.add(new Velocity()); // Key P
		ActiveModule.add(new Nametag()); // Key NONE
		ActiveModule.add(new Noslow()); // Key NONE
		ActiveModule.add(new Safewalk()); // Key NONE
		ActiveModule.add(new AutoWalk()); // Key NONE
		ActiveModule.add(new Phase()); // Key NONE
		ActiveModule.add(new God()); // Key NONE HIDE
		ActiveModule.add(new Reach()); // Key NONE
		ActiveModule.add(new Trigger()); // Key NONE
		ActiveModule.add(new Fire()); // Key NONE
		ActiveModule.add(new Water()); // Key NONE
		ActiveModule.add(new Power()); // Key NONE
		ActiveModule.add(new ClickAim()); // Key NONE
		ActiveModule.add(new Cheststealer()); // Key NONE
		ActiveModule.add(new Fasteat()); // Key NONE
		ActiveModule.add(new Autoeat()); // Key NONE
		ActiveModule.add(new InventoryMove()); // Key NONE
		ActiveModule.add(new Autorespawn()); // Key NONE
		ActiveModule.add(new Crit()); // Key NONE
		ActiveModule.add(new Autosoup()); // Key NONE
		ActiveModule.add(new SmoothAim()); // Key NONE
		ActiveModule.add(new NoBlind()); // Key NONE
		ActiveModule.add(new Longjump()); // Key NONE
		ActiveModule.add(new Freecam()); // Key L
		ActiveModule.add(new Blink()); // Key NONE
		ActiveModule.add(new Nuker()); // Key NONE
		ActiveModule.add(new Fastladder()); // Key NONE
		ActiveModule.add(new Fastbow()); // Key NONE
		ActiveModule.add(new AutoClic()); // Key NONE
		ActiveModule.add(new Unicode()); // Key NONE
		ActiveModule.add(new Autonyah()); // Key NONE
		ActiveModule.add(new Plugins()); // Key NONE
		ActiveModule.add(new neko.module.modules.params.ArrayList()); // Key NONE
		ActiveModule.add(new HUD()); // Key NONE
		ActiveModule.add(new Radar()); // Key NONE
		ActiveModule.add(new Sneak()); // Key NONE
		ActiveModule.add(new Jetpack()); // Key NONE
		ActiveModule.add(new AntiFire()); // Key NONE
		ActiveModule.add(new WorldTime()); // Key NONE
		ActiveModule.add(new Tracers()); // Key NONE
		ActiveModule.add(new Wallhack()); // Key NONE
		ActiveModule.add(new Render()); // Key NONE
		ActiveModule.add(new Trail()); // Key NONE
		ActiveModule.add(new Autoblock()); // Key NONE
		ActiveModule.add(new ChestESP()); // Key NONE
		ActiveModule.add(new Lot()); // Key NONE
		ActiveModule.add(new Paint()); // Key NONE
		ActiveModule.add(new FastDura()); // Key NONE
		ActiveModule.add(new Conditions()); // Key NONE
		ActiveModule.add(new AutoPot()); // Key NONE
		ActiveModule.add(new Pyro()); // Key NONE
		ActiveModule.add(new AutoSword()); // Key NONE
		ActiveModule.add(new Antiafk()); // Key NONE
		ActiveModule.add(new ItemESP()); // Key NONE
		ActiveModule.add(new TpBack()); // Key NONE
		ActiveModule.add(new Glide()); // Key NONE
		ActiveModule.add(new Gui()); // Key NONE
		ActiveModule.add(new Scaffold()); // Key NONE
		ActiveModule.add(new FireTrail()); // Key NONE
		ActiveModule.add(new AutoMLG()); // Key NONE
		ActiveModule.add(new Jesus()); // Key NONE
		ActiveModule.add(new HeadRoll()); // Key NONE
		ActiveModule.add(new AirWalk()); // Key NONE
		ActiveModule.add(new Build()); // Key NONE
		ActiveModule.add(new Switch()); // Key NONE
		ActiveModule.add(new DropShit()); // Key NONE
		ActiveModule.add(new PushUp()); // Key NONE
		ActiveModule.add(new NoLook()); // Key NONE
		ActiveModule.add(new Exploit()); // Key NONE
		ActiveModule.add(new Eagle()); // Key NONE
		ActiveModule.add(new Ping()); // Key NONE
		ActiveModule.add(new Reflect()); // Key NONE
		ActiveModule.add(new Fastbreak()); // Key NONE
		ActiveModule.add(new Test()); // Key NONE
		ActiveModule.add(new NekoChat()); // Key NONE
		ActiveModule.add(new Premonition()); // Key NONE
		ActiveModule.add(new Highjump()); // Key NONE
		ActiveModule.add(new NoAnim()); // Key NONE
		ActiveModule.add(new CallCmd()); // Key NONE
		ActiveModule.add(new Register()); // Key NONE
		ActiveModule.add(new Flash()); // Key NONE
		ActiveModule.add(new PunKeel()); // Key NONE
		ActiveModule.add(new Crasher()); // Key NONE
		ActiveModule.add(new BowAimbot()); // Key NONE
		ActiveModule.add(new AutoTool()); // Key NONE
		ActiveModule.add(new Nausicaah()); // Key NONE
		ActiveModule.add(new Magnet()); // Key NONE
		ActiveModule.add(new Search()); // Key NONE
		ActiveModule.add(new Likaotique()); // Key NONE
		ActiveModule.add(new PotionEffect()); // Key NONE
		ActiveModule.add(new ArmorHUD()); // Key NONE
		ActiveModule.add(new Knockback()); // Key NONE
		ActiveModule.add(new ReplyNyah()); // Key NONE
		ActiveModule.add(new Nameprotect()); // Key NONE
		ActiveModule.add(new Near()); // Key NONE
		ActiveModule.add(new ForceTP()); // Key NONE
		ActiveModule.add(new PotionSaver()); // Key NONE
		ActiveModule.add(Rotator.getRotator());
		ActiveModule.add(new AutoCraft());
		ActiveModule.add(new PlaceAndBreak());
		ActiveModule.add(new Limit());
		
		this.link.put(Utils.getModule("KillAura"), "ka");
		this.link.put(Utils.getModule("FastBow"), "fb");
		this.link.put(Utils.getModule("Knockback"), "kb");
		this.link.put(Utils.getModule("VanillaTp"), "vtp");
		this.link.put(Utils.getModule("TpBack"), "tpb");
		this.link.put(Utils.getModule("Velocity"), "velo");
		this.link.put(Utils.getModule("PunKeel"), "pk");
		this.link.put(Utils.getModule("nekochat"), "chat");
		this.link.put(Utils.getModule("BowAimbot"), "bowaim");
		this.link.put(Utils.getModule("Wallhack"), "wh");
		this.link.put(Utils.getModule("Nameprotect"), "np");
		this.link.put(Utils.getModule("AutoCraft"), "ac");
		
		LockManager lm = LockManager.getManager();
		// Ajouter les locks | -- = ..;
		// Add le nom de la commande dans le ..trade quand on add un lock qui se débloque avec des souls
		this.Lock.add(new Lock("--ka random", 100, "souls", "Commande", "Active le mode Random du Kill Aura", "", true));
		lm.setLock("--ka random", "random", "Kill Aura Random");
		this.Lock.add(new Lock("Wallhack", 7, "Lvl", "Cheat", "Permet de voir les joueurs ou mobs à travers les murs", "wh", true));
		this.Lock.add(new Lock("TpKill", 10, "Lvl", "Cheat", "Vous tp sur les joueurs et les tuent à la chaîne", "", true));
		this.Lock.add(new Lock("VanillaTp", 2, "Lvl", "Cheat", "Permet de se tp sur l'endroit visé", "vtp", true));
		this.Lock.add(new Lock("Reach", 2, "Lvl", "Cheat", "Permet d'atteindre les blocs depuis plus loin", "", true));
		this.Lock.add(new Lock("--nyah", 3, "Lvl", "Commande", "Envoie une phrase mignonne aléatoire :3", "", true));
		this.Lock.add(new Lock("--autonyah", 4, "Lvl", "Commande", "Amélioration du --nyah en nyah automatique :3", "an", true));
		this.Lock.add(new Lock("--rankmanager", 10, "Lvl", "Commande", "Permet de gérer ses rangs", "rm", true));
		this.Lock.add(new Lock("--fps", 0, "???", "Commande", "Permet d'augmenter ses fps", "", true));
		this.Lock.add(new Lock("--tppos", 0, "???", "Commande", "Permet de se tp à des coordonnées précises", "", true));
		this.Lock.add(new Lock("Trail", 0, "???", "Cheat", "Laisse une trainée bleue derrière vous sur les blocs que vous touchez", "", true));
		this.Lock.add(new Lock("--sword", 75, "souls", "Commande", "Active le mode sword qui vous empêche de taper une entité avec Kill Aura, Trigger etc... sans avoir une épée dans la main", "", true));
		lm.setLock("--sword", "sword", "Sword");
		this.Lock.add(new Lock("Unicode", 100, "souls", "Cheat", "Permet d'écrire avec des caractères Unicode qui bypass les anti-insutle", "", true));
		lm.setLock("Unicode", "unicode", "Unicode");
		this.Lock.add(new Lock("--plugins", 50, "souls", "Commande", "Affiche les plugins du serveur", "pl", true));
		lm.setLock("--plugins", "plugins", "Plugins");
		this.Lock.add(new Lock("--ka noarmor", 50, "souls", "Commande", "Active/désactive le mode NoArmor du Kill Aura", "", true));
		lm.setLock("--ka noarmor", "noarmor", "Kill Aura NoArmor");
		this.Lock.add(new Lock("--HUD select", 75, "souls", "Cheat", "Personnalise votre séléction de blocs", "", true));
		lm.setLock("--HUD select", "select", "HUD Select");
		this.Lock.add(new Lock("--trade", 15, "Lvl", "Commande", "Permet de jouer à la lotterie, payer des options contre des souls", "shop", true));
		this.Lock.add(new Lock("--ka onground", 40, "Lvl", "Commande", "Active/désactive le mode OnGround du Kill Aura", "", true));
		this.Lock.add(new Lock("FastBow", 12, "Lvl", "Cheat", "Permet de tirer des flèches instantannement [Bypass AAC]", "fb", true));
		this.Lock.add(new Lock("Sneak", 27, "Lvl", "Cheat", "Les autres joueurs vous voient Sneak sans que ça vous ralentissent", "", true));
		this.Lock.add(new Lock("--server", 5, "Lvl", "Commande", "Affiche des serveurs bien pour cheat voté par les joueurs", "", true));
		this.Lock.add(new Lock("--vote", 5, "Lvl", "Commande", "Vous permet de voter pour différent serveurs, max 1 fois par jours", "", true));
		this.Lock.add(new Lock("rankmanager info", 100, "souls", "Commande", "Option RankManager qui affiche les informations concernant les rangs s'ils en ont", "", true));
		this.Lock.add(new Lock("rankmanager lvl", 150, "souls", "Commande", "Option RankManager qui affiche le lvl des rangs", "", true));
		this.Lock.add(new Lock("rankmanager rate", 200, "souls", "Commande", "Option RankManager qui affiche la rareté des rangs", "", true));
		this.Lock.add(new Lock("rankmanager bonus", 250, "souls", "Commande", "Option RankManager qui affiche les bonus des rangs", "", true));
		this.Lock.add(new Lock("fastdura", 150, "souls", "Cheat", "Permet de détruire l'armure très rapidement", "", true));
		lm.setLock("fastdura", "fastdura", "FastDura");
		this.Lock.add(new Lock("--reach pvp", 0, "rang", "Commande", "Permet l'activation de la reach pvp", "", true));
		this.Lock.add(new Lock("Pyro", 0, "rang", "Cheat", "Enflamme une zone depuis où l'ont vise", "", true));
		this.Lock.add(new Lock("TpBack", 50, "souls", "Cheat", "Vous tp sur un bloc choisi quand vous arrivez en dessous du seuil de vie définit", "tpb", true));
		lm.setLock("TpBack", "tpback", "TpBack");
		this.Lock.add(new Lock("Flash", 0, "???", "Cheat", "Vous retp sur la position set à l'activation", "", true));
		this.Lock.add(new Lock("PunKeel", 0, "???", "Cheat", "Génére un véritable lag suivant vos désirs", "pk", true));
		this.Lock.add(new Lock("Reflect", 4, "Lvl", "Cheat", "Vouis fait rebondir, accèlerer ou vous coller à l'entité près de vous", "", true));
		this.Lock.add(new Lock("--size", 6, "Lvl", "Commande", "Choisis le nombre d'item que vous avez dans la main", "", true));
		this.Lock.add(new Lock("--wear", 6, "Lvl", "Commande", "Met l'item en main sur votre tête", "", true));
		this.Lock.add(new Lock("--give", 6, "Lvl", "Commande", "Vous donne un objet", "", true));
		this.Lock.add(new Lock("--gm", 7, "Lvl", "Commande", "Vous met dans le gamemode choisis", "", true));
		this.Lock.add(new Lock("--enchant", 7, "Lvl", "Commande", "Enchante à l'enchantement choisis l'item que vous avez dans la main", "", true));		
		this.Lock.add(new Lock("PotionEffect", 8, "Lvl", "Cheat", "HUD pour l'effet des potions sur l'écran", "", true));
		this.Lock.add(new Lock("ArmorHUD", 9, "Lvl", "Cheat", "HUD pour voir ses points d'armure restant et arme tenue", "", true));
		
		// Utils.getNecklace("").setDesc("");
		//TODO : Necklace
		
		//CrazyLove
		necklace.add(new Necklace("Dentelle", 3888.5, Rate.CrazyLove, "§9", true, 0));
		Utils.getNecklace("Dentelle").setDesc("Hé bien.. Nous voyons tout à travers Héhé");
		Utils.getNecklace("Dentelle").setRadiusGift(50);
		Utils.getNecklace("Dentelle").setGiftPlus(5);
		Utils.getNecklace("Dentelle").setGiftPlusAme(1.5);
		Utils.getNecklace("Dentelle").setLuck(1.5);
		
		// Supra
		
		necklace.add(new Necklace("Supra Dominant", 2500, Rate.Supra, "§6", true, 0));
		Utils.getNecklace("Supra Dominant").setDesc("QAQ, ce pouvoir ! Non .. ! Il te domine !");
		Utils.getNecklace("Supra Dominant").setLotNecklace(0.25);
		Utils.getNecklace("Supra Dominant").setMeteoreRain(2.5);
		
		// Titan
		
		necklace.add(new Necklace("Dominant", 2000, Rate.Titan, "§4§o", true, 0));
		Utils.getNecklace("Dominant").setDesc("Accepte.. Respecte.. Laisse-toi guider par ton pouvoir en toutes circonstances !");
		Utils.getNecklace("Dominant").setRadiusGift(10);
		Utils.getNecklace("Dominant").setGiftPlus(0.5);
		Utils.getNecklace("Dominant").setGiftPlusAme(1.5);
		Utils.getNecklace("Dominant").setLuck(1.5);
		
		// Mythique
		
		necklace.add(new Necklace("Protection", 1000, Rate.Mythique, "§2§n", true, 0));
		Utils.getNecklace("Protection").setDesc("À moi, ton intégrité est à moi, simplement. À bas les chasseurs, tu es ma propriété.");
		Utils.getNecklace("Protection").setRadiusGift(5);
		Utils.getNecklace("Protection").setGiftNecklace(0.25);
		Utils.getNecklace("Protection").setLotRateSatanique(0.5);
		Utils.getNecklace("Protection").setLotRateDivin(0.5);
		
		// Légendaire
		
		necklace.add(new Necklace("Travail", 750, Rate.Légendaire, "§5§o", true, 0));
		Utils.getNecklace("Travail").setDesc("À moi, ton intégrité est à moi, simplement. À bas les chasseurs, tu es ma propriété.");
		Utils.getNecklace("Travail").setGiftAme(0.25);
		Utils.getNecklace("Travail").setGiftPlusAme(0.25);
		Utils.getNecklace("Travail").setGiftNecklace(0.05);
		Utils.getNecklace("Travail").setLotRateSatanique(0.25);
		Utils.getNecklace("Travail").setGiftPlus(2.5);
		
		
		// Utils.getRank("").setDesc("");
		//TODO : Rank
		
		// CrazyLove
		rang.add(new Rank("Crazymeal", 7777, Rate.CrazyLove, "§9", true, 0));
		Utils.getRank("Crazymeal").setDesc("Miam miam, il a tout dévoré de 100 par 100");
		Utils.getRank("Crazymeal").setRadiusGift(100);
		Utils.getRank("Crazymeal").setGiftPlus(10);
		Utils.getRank("Crazymeal").setGiftPlusAme(3);
		Utils.getRank("Crazymeal").setLuck(3);
		
		// Titan
		rang.add(new Rank("Supra Titan", 5000, Rate.Supra, "§6", true, 0));
		Utils.getRank("Supra Titan").setDesc("Etre titanesque, on raconte qu'il peut faire pleuvoir des météores");
		Utils.getRank("Supra Titan").setLotRang(0.5);
		Utils.getRank("Supra Titan").setMeteoreRain(5);
		rang.add(new Rank("Tryliom", 4000, Rate.Titan, "§4§o", true, 0));
		Utils.getRank("Tryliom").setDesc("Le créateur suprême, dieu des Neko. Déclenche des tempêtes et explosions lorsqu'il est invoqué");
		Utils.getRank("Tryliom").setRadiusGift(20);
		Utils.getRank("Tryliom").setGiftRang(1);
		Utils.getRank("Tryliom").setLotRateSatanique(3);
		Utils.getRank("Tryliom").setLotRateDivin(3);

		rang.add(new Rank("Rhéa", 3500, Rate.Titan, "§4§o", true, 0));
		Utils.getRank("Rhéa").setDesc("Mère des dieux grecs, Titanide aux côtés de Cronos.");
		Utils.getRank("Rhéa").setGiftPlusAme(1);
		Utils.getRank("Rhéa").setGiftPlus(1);
		Utils.getRank("Rhéa").setGiftRang(1);
		rang.add(new Rank("Cronos", 3500, Rate.Titan, "§4§o", true, 0));
		Utils.getRank("Cronos").setDesc("Père des dieux grecs, Titan aux côtés de Rhéa");
		Utils.getRank("Cronos").setLotRang(1);
		Utils.getRank("Cronos").setLotRateSatanique(3);
		Utils.getRank("Cronos").setLotRateDivin(2);
		Utils.getRank("Cronos").setLotRateMagical(1);
		rang.add(new Rank("Gardienne des Héros", 3200, Rate.Titan, "§4§o", true, 0));
		Utils.getRank("Gardienne des Héros").setDesc("Protectrice divine des Héros et de leur descendance. L'un d'eux est connu pour s'être allié au Last Neko Judgement");
		Utils.getRank("Gardienne des Héros").setRadiusGift(12);
		Utils.getRank("Gardienne des Héros").setLotRateDivin(4);
		rang.add(new Rank("Pyromaniac", 3000, Rate.Titan, "§4§o", true, 0));
		Utils.getRank("Pyromaniac").setDesc("Le seul et l'unique, le Pyroman en est fier, ce rang obtenu par le feu et le sang, fait rêver cet être incendiaire !");
		Utils.getRank("Pyromaniac").setGiftAme(0.5);
		Utils.getRank("Pyromaniac").setGiftLotterie(0.5);
		Utils.getRank("Pyromaniac").setGiftPlusAme(0.5);
		Utils.getRank("Pyromaniac").setGiftPlus(7);
		rang.add(new Rank("Ambassadeur de l'Armagedon", 3500, Rate.Titan, "§4§o", true, 0));
		Utils.getRank("Ambassadeur de l'Armagedon").setDesc("Qui est le seul qui puisse déclancher cette catastrophe sans nul précédent ? Qui guidera cette destruction massive ? Où est le souffle de désespoir de ce monde dévasté ? L'Armagedon approche à grand pas...Fuyez...");
		Utils.getRank("Ambassadeur de l'Armagedon").setGiftAme(0.5);
		Utils.getRank("Ambassadeur de l'Armagedon").setGiftLotterie(0.6);
		Utils.getRank("Ambassadeur de l'Armagedon").setGiftPlusAme(2);
		Utils.getRank("Ambassadeur de l'Armagedon").setGiftPlus(5);
		rang.add(new Rank("Gardien de la Déesse du neko", 3400, Rate.Titan, "§4§o", true, 0));
		Utils.getRank("Gardien de la Déesse du neko").setDesc("Protège sa Déesse de tout déesarroi. Il la respecte par la loyauté et la soumission, passe le plus grand de son temps à ses côtés ;3");
		Utils.getRank("Gardien de la Déesse du neko").setLotRateTitan(1.5);
		Utils.getRank("Gardien de la Déesse du neko").setLotRateSatanique(0.2);
		Utils.getRank("Gardien de la Déesse du neko").setRadiusGift(10);
		rang.add(new Rank("Dévloppeur full stack", 3000, Rate.Titan, "§4§o", true, 0));
		Utils.getRank("Dévloppeur full stack").setDesc("Bosse de tout dans tout en traitant n'importe quoi. L'utilité suprême. Prend vos âmes trois par trois...");
		Utils.getRank("Dévloppeur full stack").setGiftPlusAme(3);
		Utils.getRank("Dévloppeur full stack").setRadiusGift(20);
		rang.add(new Rank("Crazy Frog", 3400, Rate.Titan, "§2", true, 0));
		Utils.getRank("Crazy Frog").setDesc("Il a enfin finaliser son ascension par cet acte, il est maintenant prêt à CRCRCR...");
		Utils.getRank("Crazy Frog").setGiftPlus(5);
		Utils.getRank("Crazy Frog").setRadiusGift(25);
		rang.add(new Rank("Nyaaw Antique", 4500, Rate.Titan, "§2§n", true, 0));
		Utils.getRank("Nyaaw Antique").setDesc("Il a évolué de manière mythique depuis sa dernière invocation, il peut atteindre la puissance des Titans");
		Utils.getRank("Nyaaw Antique").setRadiusGift(20);
		Utils.getRank("Nyaaw Antique").setGiftRang(0.7);
		Utils.getRank("Nyaaw Antique").setLotRateTitan(2);
		rang.add(new Rank("Steins;Gate", 5000, Rate.Titan, "§4§n", true, 0));
		Utils.getRank("Steins;Gate").setDesc("Cette ligne mystique où tout peut se décider, cette ligne de miracle que les promus suivent...");
		Utils.getRank("Steins;Gate").setRadiusGift(40);
		Utils.getRank("Steins;Gate").setGiftRang(0.8);
		Utils.getRank("Steins;Gate").setLotRateTitan(4);
		
		
		
		// Mythique
		// Nyaaw Légendaire : Les 3 grades légendaires et plus de 66 rangs obtenus
		rang.add(new Rank("Nyaaw Mythique", 2000, Rate.Mythique, "§2§n", true, 0));
		Utils.getRank("Nyaaw Mythique").setDesc("Certainement le plus puissant Neko invoqué par les forces divines soutenue par les Légendaires");
		Utils.getRank("Nyaaw Mythique").setRadiusGift(10);
		Utils.getRank("Nyaaw Mythique").setGiftRang(0.5);
		Utils.getRank("Nyaaw Mythique").setLotRateSatanique(1);
		Utils.getRank("Nyaaw Mythique").setLotRateDivin(1);
		// JP Légendaire : Tous les JP + plus de 15 rangs
		rang.add(new Rank("Supra Légendaire", 2000, Rate.Supra, "§6", true, 0));
		Utils.getRank("Supra Légendaire").setDesc("Etre légendaire surpassant les légendes elles-mêmes");
		Utils.getRank("Supra Légendaire").setLotRateTitan(2);
		Utils.getRank("Supra Légendaire").setLotRang(0.5);
		Utils.getRank("Supra Légendaire").setMeteoreRain(2);
		rang.add(new Rank("JP Originel", 1500, Rate.Légendaire, "§5§o", true, 0));
		Utils.getRank("JP Originel").setDesc("Jean-Pierre l'originel, ou Kali pour les intimes. L'ancêtre de la reach...");
		Utils.getRank("JP Originel").setGiftAme(0.2);
		Utils.getRank("JP Originel").setGiftPlusAme(0.8);
		Utils.getRank("JP Originel").setGiftRang(0.08);
		Utils.getRank("JP Originel").setLotRateSatanique(0.4);
		Utils.getRank("JP Originel").setLotRateDivin(0.7);
		Utils.getRank("JP Originel").setGiftPlus(2);
		
		// Neko Army : TryTry Satanique, plus de 100 bonus ramassés, Neko Angélique, Arakiel, Démon reconverti
		rang.add(new Rank("Neko Army", 1500, Rate.Légendaire, "§5§o", true, 0));
		Utils.getRank("Neko Army").setDesc("L'armée ultime de Neko crée en 1069 avant J.C. par Tryliom << D'une puissance inestimable >>");
		Utils.getRank("Neko Army").setGiftAme(0.5);
		Utils.getRank("Neko Army").setGiftPlusAme(0.5);
		Utils.getRank("Neko Army").setGiftRang(0.1);
		Utils.getRank("Neko Army").setLotRateSatanique(0.5);
		Utils.getRank("Neko Army").setGiftPlus(5);
		
		// Last Neko Judgement : TryTry Divin, plus de 20 rangs gagnés, + 2 autres rangs
		rang.add(new Rank("Last Neko Judgement", 1500, Rate.Légendaire, "§5§o", true, 0));
		Utils.getRank("Last Neko Judgement").setDesc("Secte du jugement dernier rendu par un Neko divin connu sous le nom de Nyaaw Légendaire, pouvant être invoqué en réunissant les..CRCRCR - La suite a été effacée.");
		Utils.getRank("Last Neko Judgement").setGiftAme(0.5);
		Utils.getRank("Last Neko Judgement").setGiftPlusAme(0.5);
		Utils.getRank("Last Neko Judgement").setGiftRang(0.1);
		Utils.getRank("Last Neko Judgement").setLotRateSatanique(0.8);
		Utils.getRank("Last Neko Judgement").setGiftPlus(5);
		
		rang.add(new Rank("Pyroman", 800, Rate.Légendaire, "§4§n", true, 0));
		Utils.getRank("Pyroman").setDesc("Possède la volonté suprême de détruire tout autour de soit sur des distances inconcevables, un maître des flammes. On raconte qu'une fois il a réussi à faire fuir le fou en tutu rose paillette de sa forêt...par le feu.");
		Utils.getRank("Pyroman").setGiftPlusAme(0.5);
		
		rang.add(new Rank("Disciple de la Déesse de la Choumission", 1150, Rate.Légendaire, "§d§n", true, 0));
		Utils.getRank("Disciple de la Déesse de la Choumission").setDesc("Disciple de notre Déesse de la Choumission à tous, les épreuves ont été passées avec brios, un très bon petit oiseau choumis dans sa cage, fidèle à sa Déesse, avec sa laisse et son lait, ils passent des jours fous et des nuits folles!");
		Utils.getRank("Disciple de la Déesse de la Choumission").setGiftPlusAme(0.5);
		Utils.getRank("Disciple de la Déesse de la Choumission").setRadiusGift(6);
		Utils.getRank("Disciple de la Déesse de la Choumission").setGiftRang(20);
		Utils.getRank("Disciple de la Déesse de la Choumission").setMeteoreRain(2);
		
		rang.add(new Rank("CrazyLove II", 1700, Rate.Légendaire, "§4", true, 0));
		Utils.getRank("CrazyLove II").setDesc("Cela fait déjà longtemps qu'il attend de rejoindre ce qu'il était, il ne va pas attendre plus longtemps avant d'y arriver..");
		Utils.getRank("CrazyLove II").setLotRateTitan(1.5);
		Utils.getRank("CrazyLove II").setRadiusGift(10);
		Utils.getRank("CrazyLove II").setLuck(1);
		
		// Satanique <1000
		rang.add(new Rank("Supra Satanique", 1000, Rate.Supra, "§6", true, 0));
		Utils.getRank("Supra Satanique").setDesc("Etre suprême parmis les Sataniques qui veut atteindre les Titans");
		Utils.getRank("Supra Satanique").setLotRateTitan(1.5);
		Utils.getRank("Supra Satanique").setLotRang(0.8);
		rang.add(new Rank("TryTry Satanique", 999, Rate.Satanique, "§c", true, 0));
		Utils.getRank("TryTry Satanique").setDesc("Un Tryliom Satanique pouvant à tout moment appeller sa Neko Army démoniaque sur le champs de bataille");
		Utils.getRank("TryTry Satanique").setLotRang(0.5);
		Utils.getRank("TryTry Satanique").setLotRateUltraRare(0.4);
		Utils.getRank("TryTry Satanique").setLotRateMagical(0.6);
		Utils.getRank("TryTry Satanique").setLotRateDivin(1);
		Utils.getRank("TryTry Satanique").setGiftPlus(3);
		Utils.getRank("TryTry Satanique").setLuck(1);
		Utils.getRank("TryTry Satanique").setMeteoreRain(1);
		rang.add(new Rank("Nightmare", 1500, Rate.Satanique, "§9", true, 0));
		Utils.getRank("Nightmare").setDesc("Il a hérité de sombre pouvoir, il évolue très vite et se retrouve supérieur à tous de son rang");
		Utils.getRank("Nightmare").setGiftPlusAme(0.8);
		Utils.getRank("Nightmare").setGiftPlus(0.8);
		Utils.getRank("Nightmare").setGiftAme(0.8);
		Utils.getRank("Nightmare").setLuck(1);
		Utils.getRank("Nightmare").setMeteoreRain(1);
		rang.add(new Rank("Satan", 666, Rate.Satanique, "§c§n", true, 0));
		Utils.getRank("Satan").setDesc("Le roi de l'Enfer, il possède 18 enfants");
		rang.add(new Rank("Maître Satanique", 900, Rate.Satanique, "§c", true, 0));
		Utils.getRank("Maître Satanique").setDesc("Punis le Soumis dans des rites sataniques terrifiants");
		Utils.getRank("Maître Satanique").setLotRateTitan(1);
		rang.add(new Rank("Déesse du neko", 800, Rate.Satanique, "§d§n", true, 0));
		Utils.getRank("Déesse du neko").setDesc("Venant par milliers, ils recherchent tous une chose qu'il ne peuvent trouver qu'ici. Elle est là, trônant en ces lieux avec son Gardien, apaisant ces petits êtres, les nekos.");
		Utils.getRank("Déesse du neko").setLotRateDivin(0.5);
		rang.add(new Rank("Maître des bananes", 888, Rate.Satanique, "§c", true, 0));
		Utils.getRank("Maître des bananes").setDesc("Une banane, deux bananes, trois bananes... provenant d'un seul et unique Maître, "
				+ "elles sont destinées à sa Choumise quand elle est bébé, attention aux orifices, les bananes en action ont été crée pour remplir le corps de jus des insoumis");
		Utils.getRank("Maître des bananes").setLotRateDivin(0.8);
		Utils.getRank("Maître des bananes").setLotRang(0.8);
			rang.add(new Rank("Halloween 2018", 666, Rate.Event, "§6", true, 0));
			Utils.getRank("Halloween 2018").setDesc("Mais.. Non... Que ce passe-t'il ? Des zom.. des zom.. des HORDES DE ZOMBIES .. NYAAAAAWH... Halloween..2018.. C'est.. l'invas........meow.");
			Utils.getRank("Halloween 2018").setRadiusGift(2);
			Utils.getRank("Halloween 2018").setGiftRang(0.2);
			Utils.getRank("Halloween 2018").setLotRateSatanique(0.1);
			Utils.getRank("Halloween 2018").setLotRateMagical(0.2);
			Utils.getRank("Halloween 2018").setLotRateRare(0.5);
			Utils.getRank("TryTry Satanique").setMeteoreRain(80);
		rang.add(new Rank("Choumise", 8000, Rate.Satanique, "§c", true, 0));
		Utils.getRank("Choumise").setRadiusGift(3000);
		Utils.getRank("Choumise").setGiftRang(40);
		Utils.getRank("Choumise").setGiftAme(30);
		Utils.getRank("Choumise").setGiftPlusAme(30);
		Utils.getRank("Choumise").setLotRateSatanique(80);
		Utils.getRank("Choumise").setLotRateDivin(50);
		Utils.getRank("Choumise").setLotRateMagical(50);
		Utils.getRank("Choumise").setLotRateRare(50);
		Utils.getRank("Choumise").setMeteoreRain(99.9);
		Utils.getRank("Choumise").setDesc("Gentille, docile, douce, à l'écoute après la punition, la Choumise sauras respecter le contrat crée par son Maître, la soumission, l'humiliation, elle servira son Maître dans tout les cas présents, surtout après la punition, dans tout ce qu'il désire au plus profond de son être ! Bien entendu, avec quelques récompenses à la clef et les bonnes bananes du Maître Héhé !");
		rang.add(new Rank("Lucifer", 632, Rate.Satanique, "§c", true, 0));
		Utils.getRank("Lucifer").setDesc("7ème esprit démoniaque, enfant de Satan, pure Neko pervy sataniste et porteur de la lumière écarlate. Un esprit qui peut vous tuer en 2 secondes !");
		Utils.getRank("Lucifer").setLotRateDivin(0.08);
		rang.add(new Rank("Cavalier de l'Apocalypse: La Guerre", 666, Rate.Satanique, "§4§o", true, 0));
		Utils.getRank("Cavalier de l'Apocalypse: La Guerre").setDesc("La Guerre, cavalier de l'Apocalypse apportant discorde dans le monde, le désordre instauré par Lucifer sur Terre");
		Utils.getRank("Cavalier de l'Apocalypse: La Guerre").setGiftAme(0.8);
		rang.add(new Rank("Cavalier de l'Apocalypse: La Faim", 666, Rate.Satanique, "§4§o", true, 0));
		Utils.getRank("Cavalier de l'Apocalypse: La Faim").setDesc("La Faim, cavalier de l'Apocalypse apportant discorde dans le monde, le désordre instauré par Lucifer sur Terre");
		Utils.getRank("Cavalier de l'Apocalypse: La Faim").setGiftLotterie(0.8);
		rang.add(new Rank("Cavalier de l'Apocalypse: La Maladie", 666, Rate.Satanique, "§4§o", true, 0));
		Utils.getRank("Cavalier de l'Apocalypse: La Maladie").setDesc("La Maladie, cavalier de l'Apocalypse apportant discorde dans le monde, le désordre instauré par Lucifer sur Terre");
		Utils.getRank("Cavalier de l'Apocalypse: La Maladie").setRadiusGift(4);
		rang.add(new Rank("Cavalier de l'Apocalypse: La Mort", 666, Rate.Satanique, "§4§o", true, 0));
		Utils.getRank("Cavalier de l'Apocalypse: La Mort").setDesc("La Mort, cavalier de l'Apocalypse apportant discorde dans le monde, le désordre instauré par Lucifer sur Terre");
		Utils.getRank("Cavalier de l'Apocalypse: La Mort").setGiftPlus(2);
		rang.add(new Rank("La faux de la Mort", 666, Rate.Satanique, "§c§o", true, 0));
		Utils.getRank("La faux de la Mort").setDesc("Utilisé par la Mort pour ôter des vies. Artéfact légendaire dans l'au-delà");
		Utils.getRank("La faux de la Mort").setLotRang(0.5);
		rang.add(new Rank("Chevalier de l'Enfer: Caïn", 950, Rate.Satanique, "§c§o", true, 0));
		Utils.getRank("Chevalier de l'Enfer: Caïn").setDesc("Caïn, appelé le Père du Meurtre, ayant ôté la vie d'Abel pour l'envoyer au Paradis, devient le premier chevalier de l'Enfer, via la une marque transmise de Lucifer: La marque de Caïn. Elle renferme une puissance obscure mortellement puissante");
		Utils.getRank("Chevalier de l'Enfer: Caïn").setLotRateSatanique(0.2);
		rang.add(new Rank("Prince de l'Enfer: Abaddon", 950, Rate.Satanique, "§c§o", true, 0));
		Utils.getRank("Prince de l'Enfer: Abaddon").setDesc("Lucifer a créé ce chevalier de l'Enfer par le sang. Aussi appelé Apollyon, l'ange exterminateur de l'abîme dans l'Apocalypse, l'ange des ténèbres");
		Utils.getRank("Prince de l'Enfer: Abaddon").setRadiusGift(4);
		rang.add(new Rank("Prince de l'Enfer: Ramiel", 975, Rate.Satanique, "§c§o", true, 0));
		Utils.getRank("Prince de l'Enfer: Ramiel").setDesc("Enfant de Satan, ils ont assez de pouvoir pour pouvoir rêgner sur tout l'Enfer quand il n'y a plus de Roi... On raconte que tous les Princes vivent maintenant à l'écart des autres démons et de la société Divine");
		Utils.getRank("Prince de l'Enfer: Ramiel").setGiftRang(0.5);
		rang.add(new Rank("Prince de l'Enfer: Daegon", 975, Rate.Satanique, "§c§o", true, 0));
		Utils.getRank("Prince de l'Enfer: Daegon").setDesc("Enfant de Satan, ils ont assez de pouvoir pour pouvoir rêgner sur tout l'Enfer quand il n'y a plus de Roi... On raconte que tous les Princes vivent maintenant à l'écart des autres démons et de la société Divine");
		Utils.getRank("Prince de l'Enfer: Daegon").setGiftLotterie(0.5);
		rang.add(new Rank("Unel", 950, Rate.Satanique, "§c§o", true, 0));
		Utils.getRank("Unel").setDesc("Enfant de Satan, le plus vieux et est plus puissant que Satan lui-même");
		Utils.getRank("Unel").setRadiusGift(4);
		rang.add(new Rank("Khanuel", 900, Rate.Satanique, "§c§o", true, 0));
		Utils.getRank("Khanuel").setDesc("Enfant de Satan, terriblement sadomasochiste, il est dit si on tend l'oreille, on peut l'entre crier dans les donjons...");
		Utils.getRank("Khanuel").setGiftAme(0.5);
		rang.add(new Rank("Varelle", 920, Rate.Satanique, "§c§o", true, 0));
		Utils.getRank("Varelle").setDesc("Enfant de Satan, n'a jamais subit de punition avant ce jour terrible...Depuis il reste enfermé dans une pièce sombre...");
		Utils.getRank("Varelle").setLotUnlock(0.3);
		rang.add(new Rank("Neko Army Démoniaque", 999, Rate.Satanique, "§c", true, 0));
		Utils.getRank("Neko Army Démoniaque").setDesc("Armée de Neko maintenue par TryTry Satanique, moins puissante que la CRCRCR mais grandiose quand même");
		rang.add(new Rank("Empereur du Chaos", 500, Rate.Satanique, "§c", true, 0));
		Utils.getRank("Empereur du Chaos").setDesc("Sème le chaos là où il passe, rien ne survit dans cet Enfer.");
		rang.add(new Rank("The Devil", 111, Rate.Satanique, "§c", true, 0));
		Utils.getRank("The Devil").setDesc("Le devil vous permet de gagner les cadeaux depuis plus loin ;3");
		Utils.getRank("The Devil").setRadiusGift(5);
		rang.add(new Rank("Azazel", 222, Rate.Satanique, "§c", true, 0));
		Utils.getRank("Azazel").setDesc("Ancien démon surpuissant qui possède plus de chance de gagner des rangs !");
		Utils.getRank("Azazel").setLotRang(0.1);
		rang.add(new Rank("The Lord", 534, Rate.Satanique, "§c", true, 0));
		Utils.getRank("The Lord").setDesc("The Lord of everythings");
		rang.add(new Rank("Dark Vador", 483, Rate.Satanique, "§c", true, 0));
		Utils.getRank("Dark Vador").setDesc("Jeeeee suiiiiis ton pèèèère...");
		rang.add(new Rank("Démon", 293, Rate.Satanique, "§c", true, 0));
		Utils.getRank("Démon").setDesc("Démon arborant le rôle d'accorder plus de chance afin de débloquer certaines choses");
		Utils.getRank("Démon").setLotUnlock(0.1);
		rang.add(new Rank("Diablotin", 205, Rate.Satanique, "§c", true, 0));
		Utils.getRank("Diablotin").setDesc("Possède un talent inné pour la récupération de tickets de lotteries");
		Utils.getRank("Diablotin").setGiftLotterie(0.5);
		rang.add(new Rank("Bouc Satanique", 328, Rate.Satanique, "§c", true, 0));
		Utils.getRank("Bouc Satanique").setDesc("Bouc enragé et violent lors des nuit de lune de sang");
		rang.add(new Rank("Sadomasochiste", 314, Rate.Satanique, "§c", true, 0));
		Utils.getRank("Sadomasochiste").setDesc("On raconte qu'il aime se faire volontairement punir par la Neko Army...");
		rang.add(new Rank("Neko Satanique", 777, Rate.Satanique, "§c", true, 0));
		Utils.getRank("Neko Satanique").setDesc("Il paraît qu'il est gentil si vous lui donnez des cookies ;3");
		rang.add(new Rank("JP le sataniste", 111, Rate.Satanique, "§c", true, 0));
		Utils.getRank("JP le sataniste").setDesc("Version finale de Jean-Pierre avec sa reach infinie et inestimable !");
		rang.add(new Rank("Motherlode", 723, Rate.Satanique, "§c", true, 0));
		Utils.getRank("Motherlode").setDesc("$$$ Money money money $$$");
		Utils.getRank("Motherlode").setGiftPlusAme(0.8);
		rang.add(new Rank("Lilith", 327, Rate.Satanique, "§c", true, 0));
		Utils.getRank("Lilith").setDesc("Première démone aux côtés de Lucifer dans sa chasses intensive aux souls");
		Utils.getRank("Lilith").setGiftAme(0.8);
		rang.add(new Rank("Nyaaw Sataniste", 227, Rate.Satanique, "§c", true, 0));
		Utils.getRank("Nyaaw Sataniste").setDesc("Petit chaton très méchant si vous l'énérvez ! Il vous laisse plus de chance de gagner des rangs si vous le câlinez :3");
		Utils.getRank("Nyaaw Sataniste").setGiftRang(0.2);
		rang.add(new Rank("Détratan", 731, Rate.Satanique, "§c", true, 0));
		Utils.getRank("Détratan").setDesc("Créature mythique et dangereuse pour quiconque l'approcherais");
		rang.add(new Rank("Succube", 435, Rate.Satanique, "§c", true, 0));
		Utils.getRank("Succube").setDesc("Ensorceleuse érotique que tous les petits pervers aimerait avoir ;3 Elle adore vous faire miroiter leur magie devant vous...");
		Utils.getRank("Succube").setLotRateMagical(0.08);
		rang.add(new Rank("Petite Succube", 335, Rate.Satanique, "§c", true, 0));
		Utils.getRank("Petite Succube").setDesc("La Succube version loli, plus mignonne que sa cousine qui possède d'autres avantages érotiques ;3");
		Utils.getRank("Petite Succube").setLotRateUltraRare(0.1);
		rang.add(new Rank("Chekaviah", 328, Rate.Satanique, "§c", true, 0));
		Utils.getRank("Chekaviah").setDesc("Impitoyable dictateur de notre serveur favori qu'on adore taquiner c:");
		rang.add(new Rank("Seme", 82, Rate.Satanique, "§d", true, 0));
		Utils.getRank("Seme").setDesc("Dominateur torturant les âmes de l'Enfer, son âme s'assombrit le long de sa peine. On raconte que vous pouvez écouter son rire vous hantez si vous frôlez la mort avec de mauvaises intentions...");
		rang.add(new Rank("Dullahan", 142, Rate.Satanique, "§5", true, 0));
		Utils.getRank("Dullahan").setDesc("Créature sans tête souvent à cheval étouffant sa soif de sang en prenant au piège de vils étrangers. Gardien de l'Antre de la Neko Army Démoniaque");
		rang.add(new Rank("CrazyLove", 750, Rate.Satanique, "§5", true, 0));
		Utils.getRank("CrazyLove").setDesc("Après des milliers d'années dans le sommeil, il est redevenu à votre niveau mais grandit rapidement..qui sait ce qui pourrait arriver s'il évolue..");
		Utils.getRank("CrazyLove").setLotRateTitan(1);
		Utils.getRank("CrazyLove").setRadiusGift(5);
		
		// Divin 100-400
		rang.add(new Rank("Supra Divin", 500, Rate.Supra, "§6", true, 0));
		Utils.getRank("Supra Divin").setDesc("Etre suprême parmis les créatures Divines");
		Utils.getRank("Supra Divin").setLotRateTitan(0.5);
		Utils.getRank("Supra Divin").setLotRang(0.5);
		rang.add(new Rank("TryTry Divin", 200, Rate.Divin, "§d§n", true, 0));
		Utils.getRank("TryTry Divin").setDesc("Un Tryliom version divine humble et lumineux de ses idéaux, préside le divin Last Neko Judgement");
		Utils.getRank("TryTry Divin").setLotRang(0.5);
		Utils.getRank("TryTry Divin").setLotRateUltraRare(0.6);
		Utils.getRank("TryTry Divin").setLotRateMagical(0.7);
		Utils.getRank("TryTry Divin").setLotRateDivin(0.8);
		Utils.getRank("TryTry Divin").setGiftPlus(0.0005);
		Utils.getRank("TryTry Divin").setLuck(0.5);
		Utils.getRank("TryTry Divin").setMeteoreRain(0.5);
		rang.add(new Rank("Abel aux flammes purifiées", 500, Rate.Divin, "§d§o", true, 0));
		Utils.getRank("Abel aux flammes purifiées").setDesc("Abel, tué par son grand frère Caïn et envoyé au Paradis, il cherche à venger l'acte fratricide de celui-ci. On chuchote qu'il se serait allié avec un chevalier de l'Enfer...");
		rang.add(new Rank("AntoZzz", 600, Rate.Divin, "§d", true, 0));
		Utils.getRank("AntoZzz").setDesc("Après sa mort, il s'est allié aux Neko et devînt ainsi leur chinois domestique");
		rang.add(new Rank("Tuturuuu", 1500, Rate.Divin, "§d", true, 0));
		Utils.getRank("Tuturuuu").setDesc("Chose précieuse et indispensable qu'est un 'Tuturuuu Okarin' chaque matin de votre petite vie");
		Utils.getRank("Tuturuuu").setGiftAme(0.7);
		rang.add(new Rank("La Sainte Reach", 380, Rate.Divin, "§d§n", true, 0));
		Utils.getRank("La Sainte Reach").setDesc("Il est écrit qu'elle se montre une fois tous les millénaires et que seul un Neko pur peut la voir. Unique et d'une rareté si divine");
		rang.add(new Rank("Jean-Pierre alias JP", 255, Rate.Divin, "§d§n", true, 0));
		Utils.getRank("Jean-Pierre alias JP").setDesc("Notre saint Jean-Pierre de retour avec sa reach divine !");
		rang.add(new Rank("???", 355, Rate.Divin, "§d§n", true, 0));
		Utils.getRank("???").setDesc("Perdu...dans la pénombre...??? se contruit CRCRCR - Le reste du message a été effacé.");
		rang.add(new Rank("StarLord", 142, Rate.Divin, "§d§n", true, 0));
		Utils.getRank("StarLord").setDesc("On raconte qu'il vit dans les étoiles...");
		Utils.getRank("StarLord").setLotRateDivin(0.5);
		rang.add(new Rank("Neko Angélique", 177, Rate.Divin, "§d§n", true, 0));
		Utils.getRank("Neko Angélique").setDesc("Partisant divin principal de la légendaire Neko Army...On raconte qu'il s'est fait pervertir...");
		rang.add(new Rank("PunKeel", 155, Rate.Divin, "§d§n", true, 0));
		Utils.getRank("PunKeel").setDesc("On croirait un Saint tombé du ciel...Oh mais il tombe vraiment en fait...AAAAAH AU FEU AU FEU...Mince, il a cramé :o");
		rang.add(new Rank("SkyLouna", 133, Rate.Divin, "§d§n", true, 0));
		Utils.getRank("SkyLouna").setDesc("On peut apercevoir ce specimen rarement faisant des choses bizarres avec des moutons rose fluo...");
		Utils.getRank("SkyLouna").setGiftXp(0.5);
		rang.add(new Rank("Loli-chan", 255, Rate.Divin, "§d§o", true, 0));
		Utils.getRank("Loli-chan").setDesc("Il n'y a pas plus chou et mignon dans cette univers que notre chère Loli-chan <3");
		rang.add(new Rank("Shazam", 173, Rate.Divin, "§d§n", true, 0));
		Utils.getRank("Shazam").setDesc("SHAZAM !");
		rang.add(new Rank("Nyaaw Divin", 134, Rate.Divin, "§d§n", true, 0));
		Utils.getRank("Nyaaw Divin").setDesc("Connu divinement pour sa pelotte de laine magique, il passe ses journées à dormir enrouler dedans...kawaii <3");
		Utils.getRank("Nyaaw Divin").setRadiusGift(3.5);
		rang.add(new Rank("Michel", 192, Rate.Divin, "§d§n", true, 0));
		Utils.getRank("Michel").setDesc("Bonjour Michel ! Comment allez-vous Michel ? Bien Michel et vous Michel ? Super...");
		rang.add(new Rank("Ayzoh", 127, Rate.Divin, "§d§n", true, 0));
		Utils.getRank("Ayzoh").setDesc("Épieur divin PCM, soutire plus facilement des tickets de lotteries lors de ses soirées bizarres");
		Utils.getRank("Ayzoh").setGiftLotterie(0.2);
		rang.add(new Rank("Arakiel", 139, Rate.Divin, "§d§n", true, 0));
		Utils.getRank("Arakiel").setDesc("Archange porteur de la parôle de certaines lumières de la Neko Army");
		Utils.getRank("Arakiel").setLotRateRare(0.4);
		rang.add(new Rank("Gadrasmi", 166, Rate.Divin, "§d§n", true, 0));
		Utils.getRank("Gadrasmi").setDesc("On ne connaît rien de lui à part son nom bizarre, mais on pense qu'il aura une grande importance plus tard...");	
		rang.add(new Rank("Saint Meow", 182, Rate.Divin, "§d§n", true, 0));
		Utils.getRank("Saint Meow").setDesc("Meow mew mew mooow, meoow \"nyah\" desu. Meooow meo meow mow mow mew !");
		rang.add(new Rank("Deus Ex Machina", 183, Rate.Divin, "§d§n", true, 0));
		Utils.getRank("Deus Ex Machina").setDesc("Le dieu de toute choses, contrôle tout mais est toutefois contrôlé hélas par une force mystérieuse et dangereuse...");
		Utils.getRank("Deus Ex Machina").setRadiusGift(3);
		rang.add(new Rank("Démon reconverti", 145, Rate.Divin, "§d§n", true, 0));
		Utils.getRank("Démon reconverti").setDesc("Il essuie les critiques et porte fermement ses attributs mi-démon mi-ange...Les rumeurs disent qu'il continue son activité autrement...");
		Utils.getRank("Démon reconverti").setGiftLotterie(0.5);
		rang.add(new Rank("Castiel", 175, Rate.Divin, "§d§n", true, 0));
		Utils.getRank("Castiel").setDesc("Ange déchu porte-parôle des esprits (encore) Saints");
		rang.add(new Rank("Ange déchu", 166, Rate.Divin, "§d§n", true, 0)); // Subit un effet constant de blindness Fait
		Utils.getRank("Ange déchu").setDesc("Encore en examen après avoir sombré dans les ténèbres, il sera jugé le 24.12.2017");
		Utils.getRank("Ange déchu").setLotRateDivin(0.6);
		rang.add(new Rank("Akyles", 166, Rate.Divin, "§d§o", true, 0));
		Utils.getRank("Akyles").setDesc("Vieil ange ennuyeux à entendre se plaindre");
		Utils.getRank("Akyles").setGiftXp(0.5);
		rang.add(new Rank("Archange Gabriel", 255, Rate.Divin, "§d§o", true, 0));
		Utils.getRank("Archange Gabriel").setDesc("Archange de la création supérieur aux autres anges avec ses confrères St-Michel, Raphaël et Lucifer");
		Utils.getRank("Archange Gabriel").setGiftPlus(0.5);
		rang.add(new Rank("Archange Raphaël", 255, Rate.Divin, "§d§o", true, 0));
		Utils.getRank("Archange Raphaël").setDesc("Archange de la création supérieur aux autres anges avec ses confrères St-Michel, Gabriel et Lucifer");
		Utils.getRank("Archange Raphaël").setGiftAme(0.5);
		rang.add(new Rank("Ezekiel", 255, Rate.Divin, "§d§o", true, 0));
		Utils.getRank("Ezekiel").setDesc("Prophète divin protégé par l'Archange Raphaël");
		rang.add(new Rank("Metatron", 255, Rate.Divin, "§d§o", true, 0));
		Utils.getRank("Metatron").setDesc("Prophète rebelle de dieu, il est dit qu'il a enflammé le Paradis suite à un choas divin");
		rang.add(new Rank("Enfant de Titan", 255, Rate.Divin, "§d§o", true, 0));
		Utils.getRank("Enfant de Titan").setDesc("Progéniture divine d'un Titan, il recherche ses progéniteurs depuis son plus jeune âge, sa chance d'en trouver est ainsi augmentée");
		Utils.getRank("Enfant de Titan").setLotRateTitan(0.5);
		rang.add(new Rank("Le sage", 260, Rate.Divin, "§d§o", true, 0));
		Utils.getRank("Le sage").setDesc("Sois sage mon enfant <3");
		Utils.getRank("Le sage").setGiftXp(0.5);
		rang.add(new Rank("Kitsune à banane", 400, Rate.Divin, "§d§o", true, 0));
		Utils.getRank("Kitsune à banane").setDesc("Sainte kitsune aux neuf queues à bananes, elle vit dans la forêt de ProMagicalHacks, elle le fouette avec ses queues quand elle le voit se balader en tutu rose tout seul dans les bois...Lorsque la Choumise débarque dans son territoire, elle n'hésite pas à aller torturer ce petit bébé avec ses 9 queues à bananes juteuses...");
		
		// Magical 50-100
		rang.add(new Rank("Supra Magical", 500, Rate.Supra, "§6", true, 0));		
		Utils.getRank("Supra Magical").setDesc("Etre Magique suprême utilisant un niveau de magie ultra puissant");
		Utils.getRank("Supra Magical").setLotRateDivin(0.5);
		Utils.getRank("Supra Magical").setLotRang(0.4);
		rang.add(new Rank("Leviathan", 100, Rate.Magical, "§d", true, 0));
		Utils.getRank("Leviathan").setDesc("Autrefois ces créatures démoniaque à tentacules étaient crainte mais aujourd'hui le Pervers prend un plaisir fou à s'en servir pour son plaisir personnel...");
		Utils.getRank("Leviathan").setRadiusGift(3);
		rang.add(new Rank("Magicochon", 100, Rate.Magical, "§d", true, 0));
		Utils.getRank("Magicochon").setDesc("Cochon magique aux pouvoirs surnaturelles ! On peut l'apercevoir voler avec des licornes le soir quand vous avez un peu trop consommé");
		rang.add(new Rank("Samanta", 75, Rate.Magical, "§d", true, 0));	
		Utils.getRank("Samanta").setDesc("La petite Samanta s'était perdue en forêt lorsqu'elle vis un homme habillé d'un habit rose paillete, ceci détruisit complétement son esprit sain...");
		Utils.getRank("Samanta").setRadiusGift(1);
		rang.add(new Rank("Pédale", 66.6, Rate.Magical, "§d", true, 0));
		Utils.getRank("Pédale").setDesc("La pédale est un être qui a des idées bizarres et choque les esprits encore sains...");
		rang.add(new Rank("Daru", 69, Rate.Magical, "§d", true, 0));
		Utils.getRank("Daru").setDesc("Répète ça: 'Ta banane est devenu toute molle...''");
		rang.add(new Rank("Mouton", 80, Rate.Magical, "§d", true, 0));
		Utils.getRank("Mouton").setDesc("Bêêêêêêêêêh !");
		rang.add(new Rank("Nyaaw", 70, Rate.Magical, "§d", true, 0));
		Utils.getRank("Nyaaw").setDesc("Un être magique et dangereux qui n'hésite pas à se servir de sa pelotte de laine magique pour assouvir ses fantasmes...");
		Utils.getRank("Nyaaw").setRadiusGift(1.8);
		rang.add(new Rank("NekoTix", 85, Rate.Magical, "§d", true, 0));
		Utils.getRank("NekoTix").setDesc("Il est raconté que ce specimen Neko adore jouer avec un fouet tard le soir et l'utiliser à des fins personnelles...");
		Utils.getRank("NekoTix").setLotRateMagical(0.2);
		rang.add(new Rank("Detra", 66, Rate.Magical, "§d", true, 0));
		Utils.getRank("Detra").setDesc("Petit chaton câlin orné d'un esprit corrompu par la Neko Army Démoniaque");
		rang.add(new Rank("Perverse", 69, Rate.Magical, "§d", true, 0));
		Utils.getRank("Perverse").setDesc("Perverse assoifée d'érotique et de pensées sales...On peut l'apercevoir vous observant en bavant faire des choses bizarres...");
		rang.add(new Rank("Pervers", 69, Rate.Magical, "§d", true, 0));
		Utils.getRank("Pervers").setDesc("Pervers vous regardant caché dans la pénombre en se faisant des choses malsaines...");
		rang.add(new Rank("Republic", 88, Rate.Magical, "§d", true, 0));
		Utils.getRank("Republic").setDesc("Dirigeant habillé rose fluo contrôlant les Magic Neko de la Neko Army Démoniaque");
		rang.add(new Rank("Last", 99, Rate.Magical, "§d", true, 0));
		Utils.getRank("Last").setDesc("Le dernier membre des Pédales, tente toute sa vie de voler des souls via la lotterie");
		Utils.getRank("Last").setGiftLotterie(0.5);
		rang.add(new Rank("Little Girl", 52, Rate.Magical, "§d", true, 0));
		Utils.getRank("Little Girl").setDesc("Petite fille toute gentille qui ensorcelle tous les humains qui viennent vers elle afin de les offrir en sacrifice à la Perverse");
		rang.add(new Rank("Endermen", 61, Rate.Magical, "§d", true, 0));
		Utils.getRank("Endermen").setDesc("Le pouvoir ultime de la téléportation lui a été attribué depuis son enfance. Terriblement mystérieux");
		rang.add(new Rank("ProMagicalHacks", 82, Rate.Magical, "§d", true, 0));
		Utils.getRank("ProMagicalHacks").setDesc("Imaginons notre cher ProMcHacks recouvert d'un vêtement rose paillete et d'une mine à la my little poney...");
		Utils.getRank("ProMagicalHacks").setLotRateUltraRare(0.5);
		rang.add(new Rank("Totoro", 70, Rate.Magical, "§d", true, 0));
		Utils.getRank("Totoro").setDesc("Totoro, gros polochon !");
		rang.add(new Rank("Orphée", 101, Rate.Magical, "§d", true, 0));
		Utils.getRank("Orphée").setDesc("Maître des rêves, vois des choses bizarres et malsaines dans ceux du Pervers et de la Perverse...");
		rang.add(new Rank("Morpheus", 95, Rate.Magical, "§d", true, 0));
		Utils.getRank("Morpheus").setDesc("Satsumaimo wa kaosu no kono sekai no ?dearu... Gy?retsu ga fuhen-tekidesu");
		rang.add(new Rank("Magical Drugs", 88, Rate.Magical, "§d", true, 0));
		Utils.getRank("Magical Drugs").setDesc("Elle est magique et tellement plus puissante et hallucinogène que l'original. Elle est destinée aux Magical et très utilisée");
		rang.add(new Rank("Maître Gourou", 79, Rate.Magical, "§d", true, 0));
		Utils.getRank("Maître Gourou").setDesc("Meilleur consommateur de la Magical Drugs qui a des effets extraordinaire sur celui-ci...Qui peut vous transphormer en chat en jours d'orages...");
		rang.add(new Rank("Uke", 82, Rate.Magical, "§d", true, 0));
		Utils.getRank("Uke").setDesc("Uke appartenant à TryTry Satanique, il est régulièrement violenté les jours de pluie. Il pleut souvent ces temps...");
		rang.add(new Rank("Diversity", 99, Rate.Magical, "§d", true, 0));
		Utils.getRank("Diversity").setDesc("Maître dans la musique, berceur et drogueur du créateur dans son code... °Q° Magicaaaaal °Q°");
		rang.add(new Rank("Hayliox", 111, Rate.Magical, "§d", true, 0));
		Utils.getRank("Hayliox").setDesc("The dead guy");
		rang.add(new Rank("OlimaR", 111, Rate.Magical, "§d", true, 0));
		Utils.getRank("OlimaR").setDesc("Possède le MEME Power en lui, l'utilise afin de dominer le pauvre Hayliox");
		rang.add(new Rank("Pokéfan", 88, Rate.Magical, "§d", true, 0));
		Utils.getRank("Pokéfan").setDesc("Pikachu ! Attaque queue de fer sur le NekoTix ennemi ! Attaque inéfficasse, le NekoTix ennemi adore ça...");
		rang.add(new Rank("Pedofan", 69, Rate.Magical, "§d", true, 0));
		Utils.getRank("Pedofan").setDesc("Il fait si sombre par ici, se dit Loli-chan, ce n'est pas très rassurant. Oh une lumière, qui sait ce qui va lui arriver de l'autre côté ;3");
		rang.add(new Rank("LeRoiCancer", 99, Rate.Magical, "§d", true, 0));
		Utils.getRank("LeRoiCancer").setDesc("Le plus gros cancer around the world ! It's fun but i guess it's cancer ~Blu");
		rang.add(new Rank("Fahkor L'insomniaque", 99, Rate.Magical, "§d", true, 0));
		Utils.getRank("Fahkor L'insomniaque").setDesc("Depuis cette nuit où il réfléchisit à cette légende, ce n'est plus le même Neko...");
		rang.add(new Rank("Fwiiki", 110, Rate.Magical, "§d", true, 0));
		Utils.getRank("Fwiiki").setDesc("Maître d'un petit bébé qui lui rend la vie pleine de douceur et de torture");
		
		// UltraRare 30-70
		rang.add(new Rank("Supra UltraRare", 70, Rate.Supra, "§6", true, 0));
		Utils.getRank("Supra UltraRare").setDesc("Etre suprême parmis les créatures Divines");
		Utils.getRank("Supra UltraRare").setMeteoreRain(0.2);
		rang.add(new Rank("Mr. Smile", 50, Rate.UltraRare, "§b", true, 0));
		Utils.getRank("Mr. Smile").setDesc("Esprit frappeur de niveau moyen, defeat by Mob");
		rang.add(new Rank("Goku", 70, Rate.UltraRare, "§b", true, 0));
		Utils.getRank("Goku").setDesc("Neko-j? de mottomo ky?ryokude wa nai to iu Goku-san");
		rang.add(new Rank("SirJava", 60, Rate.UltraRare, "§b", true, 0));
		Utils.getRank("SirJava").setDesc("Connu pour ses clients, le jeune prodige a succombé un jour de la naissance d'un neko encore plus puissant que tous les autres");
		rang.add(new Rank("EpicVip", 60, Rate.UltraRare, "§b", true, 0));
		Utils.getRank("EpicVip").setDesc("Au sommet depuis tout ce temps, mais maintenant surpassé par une copie...Le triste destin de l'EpicVip nous a boulversé");
		rang.add(new Rank("Amoureux du Fouet", 55, Rate.UltraRare, "§b", true, 0));
		Utils.getRank("Amoureux du Fouet").setDesc("Principal fournisseur de fouet en cuir de la sphère Neko, son meilleur client est surnommé NekoTix");
		rang.add(new Rank("Aexos", 57, Rate.UltraRare, "§b", true, 0));
		Utils.getRank("Aexos").setDesc("Homme priant afin de devenir divin, ce qui lui permis à force d'augmenter ses chances divines");
		Utils.getRank("Aexos").setLotRateDivin(0.2);
		rang.add(new Rank("Ultime Chanceux", 99, Rate.UltraRare, "§b", true, 0));
		Utils.getRank("Ultime Chanceux").setDesc("Tout es dit !");
		Utils.getRank("Ultime Chanceux").setGiftLotterie(0.5);
		Utils.getRank("Ultime Chanceux").setLuck(0.5);
		rang.add(new Rank("Pikachu", 62, Rate.UltraRare, "§e§n", true, 0));
		Utils.getRank("Pikachu").setDesc("Pika pika pikachuuuuu !");
		rang.add(new Rank("Shyrogan", 68, Rate.UltraRare, "§b", true, 0));
		Utils.getRank("Shyrogan").setDesc(".^.");
		rang.add(new Rank("The Life", 42, Rate.UltraRare, "§b", true, 0));
		Utils.getRank("The life").setDesc("Accorde la vie aux Neko et la mort aux SunShine");
		rang.add(new Rank("You are a little byte !", 35, Rate.UltraRare, "§b", true, 0));
		Utils.getRank("You are a little byte !").setDesc("Il aime les grosses bytes");
		rang.add(new Rank("Jean-Pierre chanceux", 44, Rate.UltraRare, "§b", true, 0));
		Utils.getRank("Jean-Pierre chanceux").setDesc("La chance y est mise pour celui-là, il vous autorise à utiliser sa reach si rare");
		Utils.getRank("Jean-Pierre chanceux").setLuck(0.5);
		rang.add(new Rank("My life is Potato", 59, Rate.UltraRare, "§b", true, 0));
		Utils.getRank("My life is Potato").setDesc("Les patates c'est la vie.");
		rang.add(new Rank("Hentai", 69, Rate.UltraRare, "§b", true, 0));
		Utils.getRank("Hentai").setDesc("Ya-Yamete kudasai...Haaaan no-nooo yamete ! HAAAAAAAAN...sugoi");
		rang.add(new Rank("Maître du casino !", 70, Rate.UltraRare, "§b", true, 0));
		Utils.getRank("Maître du casino !").setDesc("Ses chances de ramasser vos souls est excellente !");
		Utils.getRank("Maître du casino !").setGiftAme(0.5);
		
		// Rare 0-30
		rang.add(new Rank("Supra Rare", 30, Rate.Supra, "§6", true, 0));
		Utils.getRank("Supra Rare").setDesc("Etre suprême de la rareté");
		rang.add(new Rank("Chanceux", 7, Rate.Rare, "§e", true, 0));
		Utils.getRank("Chanceux").setDesc("Si chanceux que votre prochain rang sera plus facile à obtenir");
		Utils.getRank("Chanceux").setLotRateUltraRare(0.3);
		Utils.getRank("Chanceux").setLuck(0.3);
		rang.add(new Rank("Bananya", 22, Rate.Rare, "§e", true, 0));
		Utils.getRank("Bananya").setDesc("Avez-vous vu déjà des neko dans des bananes ? >> Bananya");
		rang.add(new Rank("atoZ", 20, Rate.Rare, "§e", true, 0));
		Utils.getRank("atoZ").setDesc("Dans ton fion !");
		rang.add(new Rank("Geek", 25, Rate.Rare, "§e", true, 0));
		Utils.getRank("Geek").setDesc("l'exemple parfait serais le créateur qui passe ses journées h24 à coder Neko c:");
		rang.add(new Rank("Extraordinary", 30, Rate.Rare, "§e", true, 0));
		Utils.getRank("Extraordinary").setDesc("Rien à ajouter ;x;");
		rang.add(new Rank("H4ckeur", 30, Rate.Rare, "§e", true, 0));
		Utils.getRank("H4ckeur").setDesc("SunShine n'est rien face à Neko c:");
		rang.add(new Rank("Cheateur", 23, Rate.Rare, "§e", true, 0));
		Utils.getRank("Cheateur").setDesc("Cheateur lambda que vous avez l'habitude de massacrer avec Neko ;3");
		rang.add(new Rank("Pika Pika", 21, Rate.Rare, "§e", true, 0));
		Utils.getRank("Pika Pika").setDesc("Pikachuuuu !");
		rang.add(new Rank("Jean-Pierre", 20, Rate.Rare, "§e", true, 0));
		Utils.getRank("Jean-Pierre").setDesc("La reach est un beau présent pour ce noël :3");
		rang.add(new Rank("Picsou", 10, Rate.Rare, "§e", true, 0));
		Utils.getRank("Picsou").setDesc("Nage dans l'argent");
		rang.add(new Rank("HACKED", 13, Rate.Rare, "§e", true, 0));
		Utils.getRank("HACKED").setDesc("Attention, hacker détecté !");
		rang.add(new Rank("Just a pig", 15, Rate.Rare, "§e", true, 0));
		Utils.getRank("Just a pig").setDesc("You are just a little piggy ;3");
		rang.add(new Rank("Clicker", 9, Rate.Rare, "§e", true, 0));
		Utils.getRank("Clicker").setDesc("ça clic vite par ici...");
		rang.add(new Rank("Mr. X", 16, Rate.Rare, "§e", true, 0));
		Utils.getRank("Mr. X").setDesc("HAAAAAAAAAAAANW");
		rang.add(new Rank("Roi d'or", 22, Rate.Rare, "§e", true, 0));
		Utils.getRank("Roi d'or").setDesc("Connu pour aimer l'or");
		rang.add(new Rank("Frite", 16, Rate.Rare, "§e", true, 0));
		Utils.getRank("Frite").setDesc("Pas de frites sans moules c:");
		rang.add(new Rank("ADC", 25, Rate.Rare, "§e", true, 0));
		Utils.getRank("ADC").setDesc("Petite fillette se faisant ******** par une bande de grosses *****");
		rang.add(new Rank("Link", 28, Rate.Rare, "§2", true, 0));
		Utils.getRank("Link").setDesc("Ya ! YA YA OUYA !");
		
		// Ordinaire 0-10
		rang.add(new Rank("Supra Ordinaire", 10, Rate.Supra, "§6", true, 0));		
		Utils.getRank("Supra Ordinaire").setDesc("Etre suprême tout à fait ordinaire qui voudrait se dresser contre une plus haute rareté");
		Utils.getRank("Supra Ordinaire").setLotRateRare(0.3);
		rang.add(new Rank("Bovin", 2, Rate.Ordinaire, "§7", true, 0));
		Utils.getRank("Bovin").setDesc("Homme pauvre");
		rang.add(new Rank("Ecuyer", 1.5, Rate.Ordinaire, "§7", true, 0));
		Utils.getRank("Ecuyer").setDesc("Ecuyer de Link");
		rang.add(new Rank("SDF", 3.5, Rate.Ordinaire, "§7", true, 0));
		Utils.getRank("SDF").setDesc("Sacrement Défoncé au Futa");
		rang.add(new Rank("Jean-Michel", 9, Rate.Ordinaire, "§7", true, 0));
		Utils.getRank("Jean-Michel").setDesc("On aime tous les Michel c:");
		rang.add(new Rank("Plouf", 2, Rate.Ordinaire, "§7", true, 0));
		Utils.getRank("Plouf").setDesc("ça fait plouf");
		rang.add(new Rank("Kevin", 1, Rate.Ordinaire, "§7", true, 0));
		Utils.getRank("Kevin").setDesc("Jean-Eugène-Kevin est un Kevin");
		rang.add(new Rank("Oza", 2.5, Rate.Ordinaire, "§7", true, 0));
		Utils.getRank("Oza").setDesc("Oza...Alalalal");
		rang.add(new Rank("Blorg", 2, Rate.Ordinaire, "§7", true, 0));
		Utils.getRank("Blorg").setDesc("Blorg - BLOOOORG - Blorg");
		rang.add(new Rank("Patrick", 5, Rate.Ordinaire, "§7", true, 0));
		Utils.getRank("Patrick").setDesc("PATOUUU");
		rang.add(new Rank("Débutant", 4, Rate.Ordinaire, "§7", true, 0));
		Utils.getRank("Débutant").setDesc("Vide");
		rang.add(new Rank("Débutant Avancé", 8, Rate.Ordinaire, "§7", true, 0));
		Utils.getRank("Débutant Avancé").setDesc("Vide Avancé");
		rang.add(new Rank("SDF Avancé", 5, Rate.Ordinaire, "§7", true, 0));
		Utils.getRank("SDF Avancé").setDesc("Vide Très Avancé");
		rang.add(new Rank("Fermier", 7, Rate.Ordinaire, "§7", true, 0));
		Utils.getRank("Fermier").setDesc("Élève secretement de Neko chez lui");
		rang.add(new Rank("Jardinier", 1, Rate.Ordinaire, "§7", true, 0));
		Utils.getRank("Jardinier").setDesc("A des choses bizarres dans son jardin, on les appelles \"légumes\"");
		rang.add(new Rank("Conan", 4.5, Rate.Ordinaire, "§7", true, 0));
		Utils.getRank("Conan").setDesc("Conan le détective mais oui !");
		rang.add(new Rank("Petit pois", 6, Rate.Ordinaire, "§7", true, 0));
		Utils.getRank("Petit pois").setDesc("Délicieux à dévorer");
		rang.add(new Rank("Pro Ordinaire", 3, Rate.Ordinaire, "§7", true, 0));
		Utils.getRank("Pro Ordinaire").setDesc("");
		rang.add(new Rank("Gamin", 5, Rate.Ordinaire, "§7", true, 0));
		Utils.getRank("Gamin").setDesc("Gamin");
		rang.add(new Rank("Batman et Robin", 10, Rate.Ordinaire, "§7", true, 0));
		Utils.getRank("Batman et Robin").setDesc("baaaaat");
		
		// Event
		rang.add(new Rank("Vide Intersidéral", 600, Rate.Event, "§2", true, 0));
		Utils.getRank("Vide Intersidéral").setDesc("Je suis le vide...J'avale tout ce qui passe vers moi...Miam miam...");
		rang.add(new Rank("L'Univers", 600, Rate.Event, "§2", true, 0));
		Utils.getRank("L'Univers").setDesc("Je te contient et tu m'appartiens...");
		rang.add(new Rank("Soumis", 500, Rate.Event, "§2", true, 0));
		Utils.getRank("Soumis").setDesc("J'obéis au Maître");
		rang.add(new Rank("Maître Pervy", 700, Rate.Event, "§2", true, 0));
		Utils.getRank("Maître Pervy").setDesc("Alerte maître pervers..Il m'attache souvent.. :c (Chut...mais j'adore cha cx )");
		rang.add(new Rank("Maître Sadique", 700, Rate.Event, "§2", true, 0));
		Utils.getRank("Maître Sadique").setDesc("Alerte Tryliom sadique..NON STOP AVEC LE FOUET NAAAAAAAAAAAN..");
		rang.add(new Rank("Maître Neko", 700, Rate.Event, "§2", true, 0));
		Utils.getRank("Maître Neko").setDesc("Alerte N-n-nyaaaah..mais mais...j'ai rien fait...me punissez pas maître je faisais que vous décrire..NYAAAAH vous êtes cruel maître ! dkjsfhbdbfshj..d'accord d'accord...ze me calme maître..");
		rang.add(new Rank("Maître Méchant", 700, Rate.Event, "§2", true, 0));
		Utils.getRank("Maître Méchant").setDesc("Mon Maître est méchant...help plz..AIIIE CA FAIT MAL MAÎTRE :c");
		rang.add(new Rank("AntoZzz x IAM", 600, Rate.Event, "§2", true, 0));
		Utils.getRank("AntoZzz x IAM").setDesc("Ils se disputent très souvent mais au fond, ce ne sont que des querelles de couple..Vous n'imaginez pas comment ça dérape en privé..;3");
		rang.add(new Rank("Coucou", 500, Rate.Event, "§2", true, 0));
		Utils.getRank("Coucou").setDesc("Coucou");
		
		// Neko
		rang.add(new Rank("Supra Neko", 1500, Rate.Supra, "§6", true, 0));
		Utils.getRank("Supra Neko").setDesc("Etre suprême pur et Neko");
		Utils.getRank("Supra Neko").setLotRateTitan(2);
		rang.add(new Rank("Petit Neko Novice", 0, Rate.Neko, "§5", false, 0));
		Utils.getRank("Petit Neko Novice").setDesc("Neko de base");
		Utils.getRank("Petit Neko Novice").setGiftXp(0.1);
		rang.add(new Rank("Petit Neko", 50, Rate.Neko, "§5", true, 0));
		Utils.getRank("Petit Neko").setDesc("Petit mais espiègle !");
		Utils.getRank("Petit Neko").setGiftXp(0.3);
		rang.add(new Rank("Apprenti Neko", 100, Rate.Neko, "§5", true, 0));
		Utils.getRank("Apprenti Neko").setDesc("Deviendra un Neko puissant");
		Utils.getRank("Apprenti Neko").setGiftXp(0.5);
		rang.add(new Rank("Neko", 150, Rate.Neko, "§5", true, 0));
		Utils.getRank("Neko").setDesc("Neko au sang pur, souillage à venir c:");
		Utils.getRank("Neko").setGiftXp(1);
		rang.add(new Rank("Neko Aguerri", 200, Rate.Neko, "§5", true, 0));
		Utils.getRank("Neko Aguerri").setDesc("Prêt pour la bagarre ?!");
		Utils.getRank("Neko Aguerri").setGiftPlus(0.5);
		rang.add(new Rank("Sorcier Neko", 250, Rate.Neko, "§5", true, 0));
		Utils.getRank("Sorcier Neko").setDesc("Manipule le temps");
		Utils.getRank("Sorcier Neko").setGiftPlus(1);
		rang.add(new Rank("Neko Pervers", 300, Rate.Neko, "§5", true, 0));
		Utils.getRank("Neko Pervers").setDesc("Chéri par la Perverse lors des jours de pluie");
		rang.add(new Rank("Neko Vicieux", 350, Rate.Neko, "§5", true, 0));
		Utils.getRank("Neko Vicieux").setDesc("Comme son cousin très pervers, le fait cocu avec la Perverse");
		rang.add(new Rank("Neko Kawaii", 400, Rate.Neko, "§5", true, 0));
		Utils.getRank("Neko Kawaii").setDesc("Petit Neko tellement mignon que le CRCRCR Légendaire lui a même remis un bisous !");
		rang.add(new Rank("Neko Suprême", 500, Rate.Neko, "§5", true, 0));
		Utils.getRank("Neko Suprême").setDesc(">>IMPERATUM<<");
		rang.add(new Rank("Like A Cat", 800, Rate.Neko, "§5", true, 0));
		Utils.getRank("Like A Cat").setDesc("Everyone were cat. Everyone speak like a cat. Everyone fuck like a cat");
		rang.add(new Rank("NyanCat", 900, Rate.Neko, "§5", true, 0));
		Utils.getRank("NyanCat").setDesc("Je doute que quelqu'un arrive un jour à ce lvl mais si ça arrivais: Nyan-sama va vous ***** la ****** avec la ***** :3");
		rang.add(new Rank("Colonel de la Neko Army", 1000, Rate.Neko, "§5", true, 0));
		Utils.getRank("Colonel de la Neko Army").setDesc("Aux commandes depuis des millénaire à cette tâche, un des principaux acteurs de sa division");
		rang.add(new Rank("Clone d'un Léviathan", 1200, Rate.Neko, "§5", true, 0));
		Utils.getRank("Clone d'un Léviathan").setDesc("Cloné autrefois et béni pour sa puissance démoniaque, ce Léviathan a été éduqué par la secte du Last Neko Judgement afin de devenir un soldat surpuissant");
		rang.add(new Rank("Homme de lettres", 1300, Rate.Neko, "§5", true, 0));
		Utils.getRank("Homme de lettres").setDesc("Homme étudiant toute sorte de magie, connais de puissants sorts et créature comme le Légendaire Pyroman, pour l'invoquer il faut s'entourer de flammes pendant un orage la nuit et lui dire : \"Pyroman des abîmes, je t'invoque en t'offrant mon sang comme présent\"");
	}

	public void Combat() {
		
	}
	public void Render() {
		
	}
	public void Player() {
		
	}
	public void Movement() {
		
	}
	public void Misc() {
		
	}
	public void Hide() {
		
	}
	public void Special() {
		
	}
	
	public ArrayList<Module> getModules(){ return this.ActiveModule; }
	
	public Module getModuleByName(Class<?> name) { return this.ActiveModule.stream().filter(ActiveModule -> name.isInstance(ActiveModule)).findFirst().orElse(null); }
}
