package neko.module.other.enums;

public enum BowMode {
	Max, Min, D�sactiv�;
	
	static BowMode pass(BowMode b) {
		if (b == Max) {
			b=Min;
		} else if (b == Min) {
			b=D�sactiv�;
		} else if (b == D�sactiv�) {
			b = Max;
		}
		return b;
	}
}
