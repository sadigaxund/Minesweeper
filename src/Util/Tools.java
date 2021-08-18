package Util;

public class Tools {

    public static void Wait(long millisecs) {
	try {
	    Thread.sleep(millisecs);
	} catch (InterruptedException e) {
	}
    }

    public static int numOfDigits(int number) {
	return (int) (Math.log10(number) + 1);
    }

    /**
     * index{ 1 = first, length = last}
     * 
     * @param number
     * @param n
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

    public static boolean isDigit(int n) {
	return n >= 0 && n <= 9;
    }

    public static boolean equals(String str1, String str2) {
	return str1.equals(str2);
    }

    public static boolean equalsNoCase(String str1, String str2) {
	return str1.toLowerCase().equals(str2.toLowerCase());
    }

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

    public static void main(String[] str) {
	System.out.println(isInRange(1, true, 1, 45, false));
    }
}
