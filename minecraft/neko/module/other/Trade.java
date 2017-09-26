package neko.module.other;

import neko.module.other.enums.Rate;

public class Trade {
	private String gain;
	private int count;
	private int prix;
	private Rate rate;
	private int num;
	private double bonus;
	private int time;
	public Trade(String gain, int count) {
		super();
		this.gain = gain;
		this.count = count;
		this.prix=2500;
	}
	public String getGain() {
		return gain;
	}
	public void setGain(String gain) {
		this.gain = gain;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getPrix() {
		return prix;
	}
	public void setPrix(int prix) {
		this.prix = prix;
	}
	public Rate getRate() {
		return rate;
	}
	public void setRate(Rate rate) {
		this.rate = rate;
	}
	
	public void addCount() {
		this.count++;
	}
	
	public boolean isNotNull() {
		return this.count>0;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
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
	
	
	
}
