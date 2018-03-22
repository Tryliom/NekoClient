package neko.module.other;

import neko.module.other.enums.Rate;
import net.minecraft.util.BlockPos;

public class Bloc {
	private BlockPos bloc;
	private String gain;
	private float r;
	private float g;
	private float b;
	private Rate rate;
	private double bonus;
	private int time;
	
	public Bloc(BlockPos bloc, String gain, float r, float g, float b) {
		this.bloc = bloc;
		this.gain = gain;
		this.r = r;
		this.g = g;
		this.b = b;
	}
	
	

	public Rate getRate() {
		return rate;
	}



	public void setRate(Rate rate) {
		this.rate = rate;
	}



	public double getBonus() {
		return bonus;
	}



	public void setBonus(double bonus) {
		this.bonus = bonus;
	}



	public int getTime() {
		return time;
	}



	public void setTime(int time) {
		this.time = time;
	}

	public BlockPos getBloc() {
		return bloc;
	}

	public void setBloc(BlockPos bloc) {
		this.bloc = bloc;
	}

	public String getGain() {
		return gain;
	}

	public void setGain(String gain) {
		this.gain = gain;
	}

	public float getR() {
		return r;
	}

	public void setR(float r) {
		this.r = r;
	}

	public float getG() {
		return g;
	}

	public void setG(float g) {
		this.g = g;
	}

	public float getB() {
		return b;
	}

	public void setB(float b) {
		this.b = b;
	}
	
	
	
}
