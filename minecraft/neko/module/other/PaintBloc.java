package neko.module.other;

import net.minecraft.util.BlockPos;

public class PaintBloc {
	private BlockPos b;
	private float cR;
	private float cG;
	private float cB;	
	private float alpha;
	
	public PaintBloc(BlockPos b, float cR, float cG, float cB, float alpha) {
		super();
		this.b = b;
		this.cR = cR;
		this.cG = cG;
		this.cB = cB;
		this.alpha = alpha;
	}

	public BlockPos getB() {
		return b;
	}

	public void setB(BlockPos b) {
		this.b = b;
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

	public float getAlpha() {
		return alpha;
	}

	public void setAlpha(float alpha) {
		this.alpha = alpha;
	}	
	
	
}
