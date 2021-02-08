package neko.gui.xraymanager;

import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;

public class GuiXrayManagerSelected extends GuiXrayManagerList {
	
	public GuiXrayManagerSelected(Minecraft mcIn, int p_i45056_2_, int p_i45056_3_, List p_i45056_4_)
    {
        super(mcIn, p_i45056_2_, p_i45056_3_, p_i45056_4_);
    }

    protected String getListHeader()
    {
        return "§aBlocs séléctionnés";
    }

}
