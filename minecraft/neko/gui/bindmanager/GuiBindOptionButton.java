package neko.gui.bindmanager;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.settings.GameSettings;

public class GuiBindOptionButton extends GuiButton {
	
	private int enumoptions;
	
	public GuiBindOptionButton(int a, int z, int e, String r) {
		this(a, z, e, -1, r);
	}
	
	public GuiBindOptionButton(int a, int z, int e, int r, int t, String y)
    {
        super(a, z, e, r, t, y);
        this.enumoptions = -1;
    }
	
	public GuiBindOptionButton(int a, int z, int e, int enumopt, String r) {
		this(a, z, e, 150, 20, r);
		this.enumoptions = enumopt;
	}
	
	public int returnEnumoptions() {
		return this.enumoptions;
	}

}
