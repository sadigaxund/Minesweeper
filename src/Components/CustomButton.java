/***************************************************************************
 *   MIT License
 *   
 *   Copyright (c) 2021 Sadig Akhund
 *   
 *   Permission is hereby granted, free of charge, to any person obtaining a copy
 *   of this software and associated documentation files (the "Software"), to deal
 *   in the Software without restriction, including without limitation the rights
 *   to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *   copies of the Software, and to permit persons to whom the Software is
 *   furnished to do so, subject to the following conditions:
 *   
 *   The above copyright notice and this permission notice shall be included in all
 *   copies or substantial portions of the Software.
 *   
 *   THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *   IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *   FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *   AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *   LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *   OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *   SOFTWARE.
 *
 * 
 **************************************************************************/

package Components;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicButtonUI;

public class CustomButton extends BasicButtonUI {
    public static void main(String argv[]) {
	JFrame f = new JFrame();
	f.setSize(400, 300);
	f.getContentPane().setLayout(new FlowLayout());

	JPanel p = new JPanel();
	JButton bt1 = new JButton("Click Me");
	bt1.setUI(new CustomButton());
	p.add(bt1);
	f.getContentPane().add(p);
	WindowListener wndCloser = new WindowAdapter() {
	    public void windowClosing(WindowEvent e) {
		System.exit(0);
	    }
	};
	f.addWindowListener(wndCloser);
	f.setVisible(true);

    }

    @Override
    public void installUI(JComponent c) {
	super.installUI(c);
	AbstractButton button = (AbstractButton) c;
	button.setOpaque(false);
	button.setBorder(new EmptyBorder(5, 15, 5, 15));
    }

    @Override
    public void paint(Graphics g, JComponent c) {
	AbstractButton b = (AbstractButton) c;
	// paintBackground(g, b, b.getModel().isPressed() ? 2 : 0);

	if (b.getModel().isPressed()) {
	    paintBackground(g, b, 2);

	} else {
	    paintBackground(g, b, 0);
	}

	super.paint(g, c);
    }

    private void paintBackground(Graphics g, JComponent c, int yOffset) {
	Dimension size = c.getSize();
	Graphics2D g2 = (Graphics2D) g;
	g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	g.setColor(c.getParent().getBackground().darker());
	g.fillRoundRect(0, yOffset, size.width, size.height - yOffset, 10, 10);
	g.setColor(c.getParent().getBackground().brighter());
	g.fillRoundRect(0, yOffset, size.width, size.height + yOffset - 5, 10, 10);
    }

}
