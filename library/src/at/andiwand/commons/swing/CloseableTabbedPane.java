package at.andiwand.commons.swing;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;

public class CloseableTabbedPane extends JTabbedPane {

    private static final long serialVersionUID = -5464687282253704817L;

    private static final Dimension CLOSE_BUTTON_DIMENSION = new Dimension(15,
	    15);

    private static class CloseButton extends JButton {
	private static final long serialVersionUID = -5676070536771878602L;

	public CloseButton() {
	    setContentAreaFilled(false);
	    setFocusable(false);
	    setBorder(BorderFactory.createEtchedBorder());
	    setBorderPainted(false);
	    setRolloverEnabled(true);

	    addMouseListener(new MouseAdapter() {
		public void mouseEntered(MouseEvent e) {
		    Component component = e.getComponent();
		    if (component instanceof AbstractButton) {
			AbstractButton button = (AbstractButton) component;
			button.setBorderPainted(true);
		    }
		}

		public void mouseExited(MouseEvent e) {
		    Component component = e.getComponent();
		    if (component instanceof AbstractButton) {
			AbstractButton button = (AbstractButton) component;
			button.setBorderPainted(false);
		    }
		}
	    });
	}

	protected void paintComponent(Graphics g) {
	    super.paintComponent(g);
	    Graphics2D g2 = (Graphics2D) g.create();

	    if (getModel().isPressed())
		g2.translate(1, 1);

	    g2.setStroke(new BasicStroke(2));
	    g2.setColor(Color.BLACK);

	    if (getModel().isRollover())
		g2.setColor(Color.GRAY);

	    int delta = (int) Math.sqrt(getWidth() + 2);
	    g2.drawLine(delta, delta, getWidth() - delta - 1, getHeight()
		    - delta - 1);
	    g2.drawLine(getWidth() - delta - 1, delta, delta, getHeight()
		    - delta - 1);
	    g2.dispose();
	}
    }

    private class TabComponent extends JComponent implements ActionListener {
	private static final long serialVersionUID = 5794456221719406818L;

	public TabComponent(String title) {
	    setLayout(new BorderLayout());
	    setName(title);
	    setBorder(BorderFactory.createEmptyBorder(1, 0, 1, 0));

	    JLabel label = new JLabel(title);
	    label.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 5));
	    CloseButton close = new CloseButton();
	    close.setPreferredSize(CLOSE_BUTTON_DIMENSION);

	    add(label, BorderLayout.CENTER);
	    add(close, BorderLayout.EAST);

	    close.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
	    int index = indexOfTabComponent(this);
	    if (index != -1)
		CloseableTabbedPane.this.remove(index);
	}
    }

    @Override
    public void insertTab(String title, Icon icon, Component component,
	    String tip, int index) {
	super.insertTab(null, icon, component, tip, index);

	setTabComponentAt(index, new TabComponent(title));
    }

    @Override
    public String getTitleAt(int index) {
	Component component = getTabComponentAt(index);

	if (component == null)
	    return null;

	return getTabComponentAt(index).getName();
    }

}