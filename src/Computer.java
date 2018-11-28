import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Computer implements ActionListener {

    private Display display;
    private ButtonsPanel buttonsPanel;
    private StringBuilder sb = new StringBuilder();
    private int temp1 = 0, temp2 = 0, result = 0;
    private String lastOperation;

    public Computer() {

    }

    public void setDisplay(Display display) {
        this.display = display;
    }

    public void setButtonsPanel(ButtonsPanel buttonsPanel) {
        this.buttonsPanel = buttonsPanel;
    }


    public void actionPerformed(ActionEvent e) {
        JButton clicked = (JButton) e.getSource();
        String clickedText = clicked.getText();

        try {
            if ((Integer.valueOf(clickedText) >= 0 && Integer.valueOf(clickedText) <= 9)) {
                if (lastOperation != null) {
                    sb.delete(0, sb.length());
                }
                sb.append(clickedText);
                display.addText(sb.toString());
                temp2 = Integer.valueOf(sb.toString());
            }
        } catch (Exception exception) {

        } finally {
            switch (clickedText) {
                case "C":
                    display.deleteText();
                    sb.delete(0, sb.length());
                    lastOperation = null;
                    temp1 = temp2 = result = 0;
                    break;
                case "+":
                    doLastOperation();
                    temp1 = Integer.valueOf(sb.toString());
                    lastOperation = clickedText;
                    temp2 = Integer.valueOf(sb.toString());
                    break;
                case "=":
                    result = temp1 + temp2;
                    temp1 = result;
                    display.addText(result + "");
                    break;
            }
        }
    }
    public void doLastOperation() {
        if (lastOperation != null && lastOperation != "") {
            if (lastOperation.equals("+")) {
                temp1 += temp2;
            }
            display.deleteText();
            sb.delete(0, sb.length());
            sb.append(temp1);
            display.addText(sb.toString());
        }
        lastOperation = null;
    }
}