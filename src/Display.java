import javax.swing.*;
import java.awt.*;

public class Display extends JTextPane {

    public Display() {

        setBorder(BorderFactory.createEtchedBorder());

        Font font = new Font("Tahoma", Font.PLAIN,26);
        setFont(font);


        setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

        Dimension dim = getPreferredSize();
        dim.height = 50;
        setPreferredSize(dim);

        setEditable(false);

    }

    public void addText(String text){

        if(Double.valueOf(text) < 0) {
            setText(text.substring(1) + "-");
        }
        else{
            setText(text);
        }
    }

    public void deleteText() {
        setText("");
    }
}