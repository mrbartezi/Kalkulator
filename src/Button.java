import javax.swing.*;
import java.awt.*;

public class Button extends JButton {
    public Button(String text) {
        setText(text);
        Font font = new Font("Tahoma", Font.PLAIN,16);
        setFont(font);
    }
}