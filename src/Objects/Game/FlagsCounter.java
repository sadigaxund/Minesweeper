package Objects.Game;

public final class FlagsCounter extends NumberLabel {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public FlagsCounter(int mines) {
	indexes = new byte[3];
	digits = new Digit[3];
	changeLabel(mines);
    }

}
