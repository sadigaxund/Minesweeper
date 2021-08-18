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

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.geom.Rectangle2D;

import javax.swing.border.AbstractBorder;

class CustomBorder extends AbstractBorder {
    /**
     * 
     */
    private static final long serialVersionUID = 2993050158029558689L;

    /**
     * The color of the border
     */
    private Color borderColour;

    /**
     * The gap between inner and outer border
     */
    private int gap;
    /**
     * Width
     */
    private double rectWidth;
    /**
     * Height
     */
    private double rectHeight;

    public CustomBorder(Color colour, int g, double w, double h) {
	borderColour = colour;
	gap = g;
	rectWidth = w;
	rectHeight = h;
    }

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
	super.paintBorder(c, g, x, y, width, height);
	Graphics2D g2d = null;
	if (g instanceof Graphics2D) {
	    g2d = (Graphics2D) g;
	    g2d.setColor(borderColour);
	    // Left Border
	    g2d.fill(new Rectangle2D.Double((double) x + gap, (double) y + gap, rectWidth, rectHeight));
	    g2d.fill(new Rectangle2D.Double((double) x + gap, (double) y + gap + rectHeight, rectHeight, rectWidth));
	    // Right Border
	    g2d.fill(new Rectangle2D.Double((double) width - gap - rectWidth, (double) y + gap, rectWidth, rectHeight));
	    g2d.fill(new Rectangle2D.Double((double) width - gap - rectHeight, (double) y + gap + rectHeight,
		    rectHeight, rectWidth));
	    // Lower Left Border
	    g2d.fill(
		    new Rectangle2D.Double((double) x + gap, (double) height - gap - rectWidth, rectHeight, rectWidth));
	    g2d.fill(new Rectangle2D.Double((double) x + gap, (double) height - gap, rectWidth, rectHeight));
	    // Lower Right Border
	    g2d.fill(new Rectangle2D.Double((double) width - gap - rectHeight, (double) height - gap - rectWidth,
		    rectHeight, rectWidth));
	    g2d.fill(new Rectangle2D.Double((double) width - gap - rectWidth, (double) height - gap, rectWidth,
		    rectHeight));
	}
    }

    @Override
    public Insets getBorderInsets(Component c) {
	return (getBorderInsets(c, new Insets(gap, gap, gap, gap)));
    }

    @Override
    public Insets getBorderInsets(Component c, Insets insets) {
	insets.left = insets.top = insets.right = insets.bottom = gap;
	return insets;
    }

    @Override
    public boolean isBorderOpaque() {
	return true;
    }
}