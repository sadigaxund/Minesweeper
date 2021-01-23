import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.DocumentFilter;

public class IntFilter extends DocumentFilter {

    private boolean test(String text) {
	try {
	    Integer.parseInt(text);
	    return true;
	} catch (NumberFormatException e) {
	    return false;
	}
    }

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