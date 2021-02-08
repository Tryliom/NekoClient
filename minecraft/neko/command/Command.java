package neko.command;

import neko.Client;
import net.minecraft.client.Minecraft;

public class Command {
	private String name;
	private String description;
	private int minArgs;
	private String help;
	private CommandType type;
	
	protected Client client = Client.getNeko();
	protected Minecraft mc = Minecraft.getMinecraft();
	
	public void onCommand(String[] args) {}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getMinArgs() {
		return minArgs;
	}

	public void setMinArgs(int minArgs) {
		this.minArgs = minArgs;
	}

	public String getHelp() {
		return help;
	}

	public void setHelp(String help) {
		this.help = help;
	}

	public CommandType getType() {
		return type;
	}

	public void setType(CommandType type) {
		this.type = type;
	}

	public Command(String name, String help, String description, int minArgs, CommandType type) {
		super();
		this.name = name;
		this.description = description;
		this.minArgs = minArgs;
		this.help = help;
		this.type = type;
	}

	
}
