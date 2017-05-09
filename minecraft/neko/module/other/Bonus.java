package neko.module.other;

import net.minecraft.util.BlockPos;

public class Bonus {
	private BlockPos b;
	private String gain;
	private float cR;
	private float cG;
	private float cB;	
	private int count;

	public Bonus(BlockPos b, String gain, float cR, float cG, float cB, int count) {
		super();
		this.b = b;
		this.gain = gain;
		this.cR = cR;
		this.cG = cG;
		this.cB = cB;
		this.count=count;
	}

	
	
	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public float getcR() {
		return cR;
	}



	public void setcR(float cR) {
		this.cR = cR;
	}



	public float getcG() {
		return cG;
	}



	public void setcG(float cG) {
		this.cG = cG;
	}



	public float getcB() {
		return cB;
	}



	public void setcB(float cB) {
		this.cB = cB;
	}



	public BlockPos getB() {
		return b;
	}

	public void setB(BlockPos b) {
		this.b = b;
	}

	public String getGain() {
		return gain;
	}

	public void setGain(String gain) {
		this.gain = gain;
	}
	
	
	
}
