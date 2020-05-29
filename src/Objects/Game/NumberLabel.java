package Objects.Game;

import acm.graphics.GCompound;

public abstract class NumberLabel extends GCompound {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /** index for identifying which digit will be added */
    protected byte[] indexes;

    /** The 3 digits of the label */
    protected Digit[] digits;

    @Override
    public double getWidth() {
	return new Digit(0).getWidth() * 3;
    }

    @Override
    public double getHeight() {
	return new Digit(0).getHeight();
    }

    /**
     * Method that changes the label
     * 
     * @param number4Label
     *            the number for label, to be displayed
     */
    public void changeLabel(int number4Label) {
	String timeStr = "" + number4Label;

	int length = timeStr.length();

	/* Making string so that it will fit to the "###" or 3 digit format */
	for (int i = 0; i < 3 - length; i++) {
	    timeStr = "0" + timeStr;
	}

	for (int i = 0; i < 3; i++) {
	    try {
		/* Defining the digit */
		indexes[i] = (byte) Integer.parseInt("" + timeStr.charAt(i));
	    } catch (NumberFormatException e) {
		return;
	    }
	    /* if previous digit exists remove that and release the memory */
	    if (digits[i] != null) {
		remove(digits[i]);
		digits[i] = null;
	    }

	    /* Instantiate new Digit */
	    digits[i] = new Digit(indexes[i]);
	    add(digits[i], i * digits[i].getWidth(), 0);
	}
    }
}
