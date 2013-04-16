package at.andiwand.commons.swing;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class JFrameUtil {

    public static void centerFrame(JFrame frame) {
	centerFrame(frame, Toolkit.getDefaultToolkit().getScreenSize());
    }

    public static void centerFrame(JFrame frame, Dimension dimension) {
	centerFrame(frame, new Rectangle(dimension));
    }

    public static void centerFrame(JFrame frame, Rectangle rectangle) {
	Dimension parentSize = rectangle.getSize();
	Dimension frameSize = frame.getSize();

	int x = rectangle.x + ((parentSize.width - frameSize.width) >> 1);
	int y = rectangle.y + ((parentSize.height - frameSize.height) >> 1);

	frame.setLocation(x, y);
    }

    public static void centerFrame(JFrame frame, Component component) {
	centerFrame(frame, component.getBounds());
    }

    private JFrameUtil() {
    }

}