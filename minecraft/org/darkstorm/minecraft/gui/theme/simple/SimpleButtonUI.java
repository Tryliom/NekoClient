package org.darkstorm.minecraft.gui.theme.simple;

import static org.lwjgl.opengl.GL11.*;

import java.awt.*;

import org.lwjgl.input.Mouse;

import neko.utils.Utils;

import org.darkstorm.minecraft.gui.component.Button;
import org.darkstorm.minecraft.gui.component.Component;
import org.darkstorm.minecraft.gui.theme.AbstractComponentUI;
import org.darkstorm.minecraft.gui.util.RenderUtil;

public class SimpleButtonUI extends AbstractComponentUI<Button> {
	private final SimpleTheme theme;

	SimpleButtonUI(SimpleTheme theme) {
		super(Button.class);
		this.theme = theme;

		foreground = new Color(180, 100, 100);
		background = null;
	}

	@Override
	protected void renderComponent(Button button) {
		translateComponent(button, false);
		Rectangle area = button.getArea();
		glEnable(GL_BLEND);
		glDisable(GL_CULL_FACE);

		glDisable(GL_TEXTURE_2D);
		Point mouse = RenderUtil.calculateMouseLocation();
		Component parent = button.getParent();
		while(parent != null) {
			mouse.x -= parent.getX();
			mouse.y -= parent.getY();
			parent = parent.getParent();
		}
		if(area.contains(mouse)) {
			RenderUtil.setColor(new Color(Utils.colorGui.getRed(), Utils.colorGui.getGreen(), Utils.colorGui.getBlue(), Mouse.isButtonDown(0) ? 150 : 90));
			glBegin(GL_QUADS);
			{
				glVertex2d(0, 0);
				glVertex2d(area.width, 0);
				glVertex2d(area.width, area.height);
				glVertex2d(0, area.height);
			}
			glEnd();
		}
		glEnable(GL_TEXTURE_2D);
		String text = button.getText();
		theme.getFontRenderer().drawString(
				text,
				area.width / 2 - theme.getFontRenderer().getStringWidth(text)
						/ 2,
				area.height / 2 - theme.getFontRenderer().FONT_HEIGHT / 2,
				RenderUtil.toRGBA(button.getForegroundColor()));

		glEnable(GL_CULL_FACE);
		glDisable(GL_BLEND);
		translateComponent(button, true);
	}

	@Override
	protected Dimension getDefaultComponentSize(Button component) {
		return new Dimension(theme.getFontRenderer().getStringWidth(
				component.getText()) + 4,
				theme.getFontRenderer().FONT_HEIGHT + 4);
	}

	@Override
	protected Rectangle[] getInteractableComponentRegions(Button component) {
		return new Rectangle[] { new Rectangle(0, 0, component.getWidth(),
				component.getHeight()) };
	}

	@Override
	protected void handleComponentInteraction(Button component, Point location,
			int button) {
		Point mouse = RenderUtil.calculateMouseLocation();
		Rectangle area = component.getArea();
		if(location.x <= component.getWidth() && location.y <= component.getHeight() && button == 0)
			component.press();
	}
}