import java.awt.Font;

public class FunctionFormat {

    TEditor teditor;
    Font arial, tnr;
    String selectedFont;

    public FunctionFormat(TEditor teditor) {
        this.teditor = teditor;
    }

    public void wordwrap() {
        if (teditor.wordWrapOn == false) {
            teditor.wordWrapOn = true;
            teditor.textArea.setLineWrap(true);
            teditor.textArea.setWrapStyleWord(true);
            teditor.itemWrap.setText("Word Wrap : On");
        } else if (teditor.wordWrapOn == true) {
            teditor.wordWrapOn = false;
            teditor.textArea.setLineWrap(false);
            teditor.textArea.setWrapStyleWord(false);
            teditor.itemWrap.setText("Word Wrap : Off");

        }
    }

    public void createFont(int fontSize) {

        arial = new Font("Arial", Font.PLAIN, fontSize);
        tnr = new Font("Times New Roman", Font.PLAIN, fontSize);

        setFont(selectedFont);

    }

    public void setFont(String font) {

        selectedFont = font;

        switch (selectedFont) {
            case "Arial":
                teditor.textArea.setFont(arial);
                break;
            case "TNR":
                teditor.textArea.setFont(tnr);
                break;
        }

    }

}
