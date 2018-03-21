package neko.manager;

import neko.module.Module;
import neko.module.other.Quest;
import neko.utils.Utils;

public class QuestManager {
	private static QuestManager instance;
	private Quest current;
	private boolean hasBegin;

	public static QuestManager getQM() {
		return instance == null ? instance = new QuestManager() : instance;
	}

	public QuestManager() {
		this.current = null;
		this.hasBegin = false;
	}
	
	public void stopQuest() {
		Utils.addChat(Utils.setColor("Défi annulé", "§c"));
		instance = null;
	}

	private void finishQuest() {
		// gagne un truc random via Utils
		Utils.addChat(Utils.setColor("Défi réussit en "+(3-current.getTrials())+" essais !", "§d"));
		Utils.checkXp(Utils.getRandInt(500));
		instance = null;
	}

	private void failedQuest() {
		Utils.addChat(Utils.setColor("Tu n'as pas réussi à réaliser le défi dans le nombre d'essais définis !", "§c"));
		instance = null;
	}

	public void guessQuest(String cmdOrCheat) {
		if (hasBegin)
			if (current.getResponseCheat() == null) {
				if (cmdOrCheat.equalsIgnoreCase(current.getResponseCmd())) {
					this.finishQuest();
				} else
					failed();
			} else {
				if (Utils.getModule(cmdOrCheat).getName().equalsIgnoreCase(current.getResponseCheat().getName())) {
					this.finishQuest();
				} else
					failed();
			}
	}

	private void failed() {
		current.setTrials(current.getTrials() - 1);
		if (current.getTrials() == 0) {
			this.failedQuest();
		}
	}

	public Quest getCurrent() {
		return current;
	}

	public void setCurrent(Quest current) {
		this.current = current;
	}

	public boolean isHasBegin() {
		return hasBegin;
	}

	public void setHasBegin(boolean hasBegin) {
		this.hasBegin = hasBegin;
	}
	
	

}
