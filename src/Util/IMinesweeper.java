package Util;
import java.util.Iterator;

import acm.graphics.GCompound;
import acm.graphics.GObject;

public interface IMinesweeper {

    /** Repository of the not pressed tile image */
    public static String NOTPRESSED_TILE = "./img/block11.png";

    /** Repository of the pressed tile image */
    public static String PRESSED_TILE = "./img/block12.png";

    /** Repository of the triggered mine tile image */
    public static String DETONATED_MINE = "./img/block17.png";

    /** Repository of the mine tile image */
    public static String MINED = "./img/block16.png";

    /** Repository of the catched mine tile image */
    public static String NOT_MINED = "./img/block18.png";

    /** Repository of the empty tile image */
    public static String EMPTY = "./img/block12.png";

    /** Repository of the flag image */
    public static String FLAG = "./img/block13.png";

    /** Repository of the number images */
    public static String[] NUMBER_TILES = { //
	    new String("./img/1.png"), // 1 - position: 0
	    new String("./img/2.png"), // 2 - position: 1
	    new String("./img/3.png"), // 3 - position: 2
	    new String("./img/4.png"), // 4 - position: 3
	    new String("./img/5.png"), // 5 - position: 4
	    new String("./img/6.png"), // 6 - position: 5
	    new String("./img/7.png"), // 7 - position: 6
	    new String("./img/8.png"), // 8 - position: 7
    };
    /** Repository of the digit number images for the timer */
    public static String[] DIGITS = { //
	    new String("./img/nums/0.png"), // 0 - position: 0
	    new String("./img/nums/1.png"), // 1 - position: 1
	    new String("./img/nums/2.png"), // 2 - position: 2
	    new String("./img/nums/3.png"), // 3 - position: 3
	    new String("./img/nums/4.png"), // 4 - position: 4
	    new String("./img/nums/5.png"), // 5 - position: 5
	    new String("./img/nums/6.png"), // 6 - position: 6
	    new String("./img/nums/7.png"), // 7 - position: 7
	    new String("./img/nums/8.png"), // 8 - position: 8
	    new String("./img/nums/9.png"), // 9 - position: 9
    };

    /** Defines the content of a tile */
    public static enum Content {
	EMPTY, NUMBER, MINE, END;
    }

    /** Defines the Complexity of the game */
    public static enum Complexity {
	BEGINNER, MEDIUM, EXPERT, CUSTOM
    }

    /**
     * Method for calculating how many elements does the given object have
     * 
     * @param obj
     *            - the given object
     * @return - the number of elements and sub-elements
     */
    public static int calculateMemoryUsage(GObject obj) {
	System.out.println(obj);
	/*
	 * if the object can not have any object inside then return 0, why not 1 because
	 * the amount of this object has been considered one recursion before
	 */
	if (!(obj instanceof GCompound)) {
	    return 0;
	}
	/* Taking the number of contents */
	int sum = ((GCompound) obj).getElementCount();

	/* Taking contents */
	Iterator<GObject> iter = ((GCompound) obj).iterator();

	/* Iterating through contents */
	while (iter.hasNext()) {
	    try {
		sum += calculateMemoryUsage(iter.next());
	    } catch (IndexOutOfBoundsException e) {
		/* Sometimes this exception occurs */
	    }
	}
	iter = null;
	return sum;
    }
}