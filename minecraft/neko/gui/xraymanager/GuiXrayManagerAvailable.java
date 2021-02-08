package neko.gui.xraymanager;

import java.util.List;

import net.minecraft.client.Minecraft;

public class GuiXrayManagerAvailable extends GuiXrayManagerList {
	
	public GuiXrayManagerAvailable(Minecraft mcIn, int p_i45054_2_, int p_i45054_3_, List p_i45054_4_)
    {
        super(mcIn, p_i45054_2_, p_i45054_3_, p_i45054_4_);
    }

    protected String getListHeader()
    {
        return "§cBlocs non séléctionnés";
    }

}
