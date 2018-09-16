package neko.module.other;
	
import java.util.ArrayList;

import neko.module.other.enums.Rate;
import neko.utils.Utils;

public class Necklace {
	private String name;
	private double bonus;
	private Rate rate;
	private String color;
	private boolean lock;
	private int lvl;
	
	//Les bonus modifiable via les getters/setters
	private double radiusGift = 0;
	private double meteoreRain = 0;
	private double lotNecklace = 0;
	private double lotUnlock = 0;
	private double giftNecklace = 0;
	private double giftXp = 0;
	private double giftLotterie = 0;
	private double giftAme = 0;
	private double giftPlusAme = 0;
	private double giftPlus = 0;
	private double luck = 0;
	private String desc="null";
	
	// Pour les rate, combien de chance * (ex 1.5 fois plus de chance d'en gagner, mettre 0.5 vu que le calcul est de x + 0.5*x) Fait	
	private double lotRateTitan = 0;
	private double lotRateSatanique = 0;
	private double lotRateDivin = 0;
	private double lotRateMagical = 0;
	private double lotRateUltraRare = 0;
	private double lotRateRare = 0;
	
	public Necklace(String name, double bonus, Rate rate, String color, boolean lock, int lvl) {
		this.name = name;
		this.bonus = bonus;
		this.rate = rate;
		this.color = color;
		this.lock = lock;
		this.lvl = lvl;
	}
	
	public double getTotBonus() {
		return this.bonus + this.bonus*lvl*0.1;
	}
	
	public double getMeteoreRain() {
		return meteoreRain;
	}

	public void setMeteoreRain(double meteoreRain) {
		this.meteoreRain = meteoreRain;
	}

	public int getLvl() {
		return lvl;
	}
	
	public void setLvl(int lvl) {
		if(lvl > 5) {
			Utils.addChat("§cLe niveau du Necklace ne peut pas dépasser 5");
			return;
		}
		this.lvl = lvl;
	}
	
	public void addLvl() {
		if(lvl == 5) {
			Utils.addChat("§cLe niveau du Necklace ne peut pas dépasser 5");
			return;
		}
		this.lvl++;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getBonus() {
		return bonus;
	}

	public void setBonus(double bonus) {
		this.bonus = bonus;
	}

	public Rate getRate() {
		return rate;
	}

	public void setRate(Rate rate) {
		this.rate = rate;
	}
	
	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public boolean isLock() {
		return lock;
	}

	public void setLock(boolean lock) {
		this.lock = lock;
	}

	public double getRadiusGift() {
		return radiusGift;
	}

	public void setRadiusGift(double radiusGift) {
		this.radiusGift = radiusGift;
	}
	
	public double getLotNecklace() {
		return lotNecklace;
	}

	public void setLotNecklace(double lotNecklace) {
		this.lotNecklace = lotNecklace;
	}
	
	public double getLotUnlock() {
		return lotUnlock;
	}

	public void setLotUnlock(double lotUnlock) {
		this.lotUnlock = lotUnlock;
	}

	public double getGiftNecklace() {
		return giftNecklace;
	}

	public void setGiftNecklace(double giftNecklace) {
		this.giftNecklace = giftNecklace;
	}
	
	public double getGiftXp() {
		return giftXp;
	}

	public void setGiftXp(double giftXp) {
		this.giftXp = giftXp;
	}

	public double getGiftLotterie() {
		return giftLotterie;
	}

	public void setGiftLotterie(double giftLotterie) {
		this.giftLotterie = giftLotterie;
	}

	public double getGiftAme() {
		return giftAme;
	}

	public void setGiftAme(double giftAme) {
		this.giftAme = giftAme;
	}
	
	public double getGiftPlusAme() {
		return giftPlusAme;
	}

	public void setGiftPlusAme(double giftPlusAme) {
		this.giftPlusAme = giftPlusAme;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public double getLotRateSatanique() {
		return lotRateSatanique;
	}
	
	public void setLotRateSatanique(double lotRateSatanique) {
		this.lotRateSatanique = lotRateSatanique;
	}

	public double getLotRateDivin() {
		return lotRateDivin;
	}

	public void setLotRateDivin(double lotRateDivin) {
		this.lotRateDivin = lotRateDivin;
	}

	public double getLotRateMagical() {
		return lotRateMagical;
	}

	public void setLotRateMagical(double lotRateMagical) {
		this.lotRateMagical = lotRateMagical;
	}

	public double getLotRateUltraRare() {
		return lotRateUltraRare;
	}
	
	public void setLotRateUltraRare(double lotRateUltraRare) {
		this.lotRateUltraRare = lotRateUltraRare;
	}

	public double getLotRateRare() {
		return lotRateRare;
	}

	public void setLotRateRare(double lotRateRare) {
		this.lotRateRare = lotRateRare;
	}

	public double getGiftPlus() {
		return giftPlus;
	}

	public void setGiftPlus(double giftPlus) {
		this.giftPlus = giftPlus;
	}

	public double getLotRateTitan() {
		return lotRateTitan;
	}
	
	public void setLotRateTitan(double lotRateTitan) {
		this.lotRateTitan = lotRateTitan;
	}		
	
	public double getLuck() {
		return luck;
	}

	public void setLuck(double luck) {
		this.luck = luck;
	}
	
	public ArrayList<String> getAllBonus(String color1, String color2) {
		ArrayList<String> list = new ArrayList<>();
		if (this.giftAme>0) {
			list.add(color1+"+Météores de souls : "+color2+this.giftAme*100+"%");
		}
		if (this.giftLotterie>0) {
			list.add(color1+"+Météores de billet de loterie : "+color2+this.giftLotterie*100+"%");
		}		
		if (this.giftPlus>0) {
			list.add(color1+"+Météores : "+color2+this.giftPlus*100+"%");
		}
		if (this.giftPlusAme>0) {
			list.add(color1+"+Souls sur les météores : "+color2+this.giftPlusAme*100+"%");
		}
		if (this.giftNecklace>0) {
			list.add(color1+"+Necklace(Collier) sur les météores : "+color2+this.giftNecklace*100+"%");
		}
		if (this.giftXp>0) {
			list.add(color1+"+Météores d'xp : "+color2+this.giftXp*100+"%");
		}
		if (this.radiusGift>0) {
			list.add(color1+"+Radius pour prendre les météores : "+color2+this.radiusGift*100+"%");
		}
		if (this.lotNecklace>0) {
			list.add(color1+"+Necklace(Collier) dans les lots : "+color2+this.lotNecklace*100+"%");
		}
		if (this.lotRateTitan>0) {
			list.add(color1+"+Necklace(Collier) rareté Titan dans les lots : "+color2+this.lotRateTitan*100+"%");
		}
		if (this.lotRateSatanique>0) {
			list.add(color1+"+Necklace(Collier) rareté Satanique dans les lots : "+color2+this.lotRateSatanique*100+"%");
		}		
		if (this.lotRateDivin>0) {
			list.add(color1+"+Necklace(Collier) rareté Divin dans les lots : "+color2+this.lotRateDivin*100+"%");
		}
		if (this.lotRateMagical>0) {
			list.add(color1+"+Necklace(Collier) rareté Magical dans les lots : "+color2+this.lotRateMagical*100+"%");
		}
		if (this.lotRateUltraRare>0) {
			list.add(color1+"+Necklace(Collier) rareté UltraRare dans les lots : "+color2+this.lotRateUltraRare*100+"%");
		}		
		if (this.lotRateRare>0) {
			list.add(color1+"+Necklace(Collier) rareté Rare dans les lots : "+color2+this.lotRateRare*100+"%");
		}
		if (this.lotUnlock>0) {
			list.add(color1+"+Unlock dans les lots : "+color2+this.lotUnlock*100+"%");
		}	
		if (this.luck>0) {
			list.add(color1+"+Chance : "+color2+this.luck*100+"%");
		}	
		return list;
	}
}