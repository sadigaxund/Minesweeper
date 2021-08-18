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
package Util;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.DocumentFilter;

/**
 * Class used to filter the input fields.<br>
 * Filters:<br>
 * <ul>
 * <li>Entered input must contain only digits</li>
 * <li>Entered input must be less than 3 digits long</li>
 * </ul>
 * 
 *
 * 
 */
public class IntFilter extends DocumentFilter {

    /**
     * Filter: Entered input must contain only digits
     * 
     * @param text
     *                 the entered input
     * @return true if there is no other character than digits, false otherwise
     */
    private boolean test(String text) {
	try {
	    Integer.parseInt(text);
	    return true;
	} catch (NumberFormatException e) {
	    return false;
	}
    }

    /**
     * Filter: Entered input must be less than 3 digits long
     * 
     */
    @Override
    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
	    throws BadLocationException {

	Document doc = fb.getDocument();
	StringBuilder sb = new StringBuilder();
	sb.append(doc.getText(0, doc.getLength()));
	sb.replace(offset, offset + length, text);

	if (test(sb.toString()) && doc.getLength() < 3) {
	    super.replace(fb, offset, length, text, attrs);
	} else {
	    // warn the user and don't allow the insert
	}

    }

}