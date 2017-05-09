package org.darkstorm.minecraft.gui.theme.simple;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.darkstorm.minecraft.gui.font.UnicodeFontRenderer;
import org.darkstorm.minecraft.gui.theme.AbstractTheme;

import neko.Client;
import neko.utils.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.ResourceLocation;

public class SimpleTheme extends AbstractTheme {
	private FontRenderer fontRenderer;
	Minecraft mc = Minecraft.getMinecraft();
	public static String font="";
	public static int px = 16;
	public static boolean alpha=false;

	public SimpleTheme() {	
		if (font.equalsIgnoreCase("Neko")) {
			fontRenderer = Client.getNeko().NekoFont;
		} else
			try {
				Font ft = Font.createFont(Font.TRUETYPE_FONT, this.getClass().getResourceAsStream("/neko/font/"+(font.isEmpty() ? "Wolfram" : font)+".ttf"));
				ft = ft.deriveFont(Font.BOLD, px);
				fontRenderer = new UnicodeFontRenderer(ft);
			} catch (Exception e) {
				try {
					Font ft = Font.createFont(Font.TRUETYPE_FONT, this.getClass().getResourceAsStream("/neko/font/"+(font.isEmpty() ? "Wolfram" : font)+".otf"));
					ft = ft.deriveFont(Font.BOLD, px);
					fontRenderer = new UnicodeFontRenderer(ft);
				} catch (Exception ex) {
					fontRenderer = new UnicodeFontRenderer(new Font("Trebuchet MS", Font.BOLD, px+2));
				} 
			} 

		
		installUI(new SimpleFrameUI(this));
		installUI(new SimplePanelUI(this));
		installUI(new SimpleLabelUI(this));
		installUI(new SimpleButtonUI(this));
		installUI(new SimpleCheckButtonUI(this));
		installUI(new SimpleComboBoxUI(this));
		installUI(new SimpleSliderUI(this));
		installUI(new SimpleProgressBarUI(this));
	}

	public FontRenderer getFontRenderer() {
		return fontRenderer;
	}
	
	public void setFontRenderer(FontRenderer f) {
		this.fontRenderer=f;
	}
}
