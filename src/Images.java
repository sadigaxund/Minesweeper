
public class Images {

    private static final String rootRepo = ".//img";
    private static final String FORMAT = ".png";

    public static final String MINE = rootRepo + "//tiles//mine1" + FORMAT;
    public static final String MINE_BLOWN = rootRepo + "//tiles//mine2" + FORMAT;
    public static final String MINE_DEFUSED = rootRepo + "//tiles//mine3" + FORMAT;

    public static final String SMILE_OOO = rootRepo + "//smile//3" + FORMAT;
    public static final String SMILE_DEAD = rootRepo + "//smile//2" + FORMAT;
    public static final String SMILE_COOL = rootRepo + "//smile//1" + FORMAT;
    public static final String SMILE_HAPPY = rootRepo + "//smile//4" + FORMAT;
    public static final String SMILE_HAPPY_PRESS = rootRepo + "//smile//5" + FORMAT;

    public static final String TILE_NOT_PRESSED = rootRepo + "//tiles//tile1" + FORMAT;
    public static final String TILE_PRESSED = rootRepo + "//tiles//tile2" + FORMAT;
    public static final String FLAG = rootRepo + "//tiles//flag" + FORMAT;

    public static String getHexDigit(int num) {
	if (!Tools.isDigit(num))
	    throw new BadInputException("Entered number must be in range [0; 9]");

	return rootRepo + "//hex//" + num + FORMAT;
    }

    public static String getTileDigit(int num) {
	if (!Tools.isDigit(num) && num != 9)
	    throw new BadInputException("Entered number must be in range [0; 8]");

	return rootRepo + "//tiles//" + num + FORMAT;
    }

}
