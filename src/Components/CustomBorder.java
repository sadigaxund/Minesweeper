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