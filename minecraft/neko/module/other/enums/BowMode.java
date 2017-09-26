package neko.module.other.enums;

public enum BowMode {
	Max, Min, Désactivé;
	
	static BowMode pass(BowMode b) {
		if (b == Max) {
			b=Min;
		} else if (b == Min) {
			b=Désactivé;
		} else if (b == Désactivé) {
			b = Max;
		}
		return b;
	}
}
