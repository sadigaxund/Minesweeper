/***************************************************************************
 *     Copyright 2021 Sadig Akhund @ https://github.com/sadigaxund
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *          http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
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
