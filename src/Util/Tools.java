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

public class Tools {

    /**
     * Method used for delays while executing
     * 
     * @param millisecs
     *                      the amount of time in milliseconds to wait for
     */
    public static void Wait(long millisecs) {
	try {
	    Thread.sleep(millisecs);
	} catch (InterruptedException e) {
	}
    }

    /**
     * Method that returns the number of digits in given number
     * 
     * @param number
     *                   the given number
     * @return the number of digits
     */
    public static int numOfDigits(int number) {
	return (int) (Math.log10(number) + 1);
    }

    /**
     * The method for fetching the <i>n</i>th digit of the given number.<br>
     * Note: The first digit starts with 1<br>
     * 
     * @param number
     *                     the given number
     * @param fromLeft
     *                     flag used for determining whether user wants to get
     *                     <i>n</i>th digit from the right of from the left. Digit
     *                     is from the left if True, right otherwise.
     * 
     * @param n
     *                     'th digit
     */
    public static int getDigit(int number, int n, boolean fromLeft) {

	if (number < 0)
	    number *= -1;

	int len = numOfDigits(number);

	if (n > len)
	    return -1;
	if (fromLeft)
	    return (int) (number / Math.pow(10, len - n)) % 10;
	else
	    return (int) (number / Math.pow(10, n - 1)) % 10;
    }

    /**
     * The method for fetching the <i>n</i>th digit of the given number starting
     * from the left.<br>
     * Note: The first digit starts with 1<br>
     * 
     * @param number
     *                   the given number
     * 
     * 
     * @param n
     *                   'th digit
     */
    public static int getDigit(int number, int n) {
	return getDigit(number, n, true);
    }

    /**
     * Method for testing whether the giving number is a digit. <br>
     * A digit is a number in this range: [0, 9]
     */
    public static boolean isDigit(int n) {
	return n >= 0 && n <= 9;
    }

    /**
     * Method that tests the equality of 2 string
     * 
     * @param str1
     *                 String 1
     * @param str2
     *                 String 2
     * @return True if the strings are equivalent
     */
    public static boolean equals(String str1, String str2) {
	return str1.equals(str2);
    }

    /**
     * Method that tests the equality of 2 string, however without considering the
     * letter case.
     * 
     * @param str1
     *                 String 1
     * @param str2
     *                 String 2
     * @return True if the strings are equivalent
     */
    public static boolean equalsNoCase(String str1, String str2) {
	return str1.toLowerCase().equals(str2.toLowerCase());
    }

    /**
     * Method for testing whether the given number is within the given range
     * 
     * @param number
     *                              the given number
     * @param includedFromLeft
     *                              if true include the left boundary, otherwise
     *                              exclude
     * @param leftBound
     *                              left boundary
     * @param rightBound
     *                              right boundary
     * @param includedFromRight
     *                              if true include the right boundary, otherwise
     *                              exclude
     * @return true if the number is in range
     * 
     *         <br>
     *         <br>
     *         <ol>
     *         <h3>Possible Cases:</h3>
     *         <li>(n, true, a, b, true) -> test( <b>n in [a, b]</b> )<br>
     *         <li>(n, true, a, b, false) -> test( <b>n in [a, b)</b> )<br>
     *         <li>(n, false, a, b, true) -> test( <b>n in (a, b]</b> )<br>
     *         <li>(n, false, a, b, false) -> test( <b>n in (a, b)</b> )<br>
     *         </li>
     *         </ol>
     */
    public static boolean isInRange(int number, boolean includedFromLeft, int leftBound, int rightBound,
	    boolean includedFromRight) {

	if (number > leftBound && number < rightBound) // strictly in range
	    return true;

	if (includedFromLeft && number == leftBound) // left boundary is the number
	    return true;

	if (includedFromRight && number == rightBound) // right boundary is the number
	    return true;

	return false; // not in range
    }

}
