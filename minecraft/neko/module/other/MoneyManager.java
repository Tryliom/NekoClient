package neko.module.other;

public class MoneyManager {
	private int souls;
	private int ticket;
	private int money;
	private static MoneyManager instance;
	
	public static MoneyManager getMoney() {
		if (instance==null)
			instance = new MoneyManager();
		return instance;
	}

	public int getSouls() {
		return souls;
	}

	public void setSouls(int souls) {
		this.souls = souls;
	}

	public int getTicket() {
		return ticket;
	}

	public void setTicket(int ticket) {
		this.ticket = ticket;
	}

	public void setMoney(int money) {
		this.money = money;
	}
	
	
}
