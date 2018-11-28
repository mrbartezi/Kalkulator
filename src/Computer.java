import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Computer implements ActionListener {

    private Display display;
    private ButtonsPanel buttonsPanel;
    private StringBuilder sb = new StringBuilder();
    private int temp1 = 0, temp2 = 0, result = 0;
    private String lastOperation;
    private boolean firstDigit = true;

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


        //Buttons from 0-9
        try {
            if ((Integer.valueOf(clickedText) >= 0 && Integer.valueOf(clickedText) <= 9)) {
                if (lastOperation != null && firstDigit) {
                    sb.delete(0, sb.length());
                    firstDigit = false;
                }
                sb.append(clickedText);
                display.addText(sb.toString());
                temp2 = Integer.valueOf(sb.toString());
            }
        } catch (Exception exception) {

        } finally {

            //Rest of the buttons
            switch (clickedText) {
                case "C":
                    display.deleteText();
                    sb.delete(0, sb.length());
                    lastOperation = null;
                    temp1 = temp2 = result = 0;
                    break;
                case "DEL":
                    try{
                        sb.deleteCharAt(sb.length() - 1);
                        display.addText(sb.toString());
                        temp1 = Integer.valueOf(sb.toString());
                        }
                        catch (Exception e1) {

                        }
                    break;
                case "+":
                    firstDigit = true;
                    doLastOperation();
                    temp1 = Integer.valueOf(sb.toString());
                    temp2 = Integer.valueOf(sb.toString());
                    lastOperation = clickedText;
                    break;
                case "-":
                    firstDigit = true;
                    doLastOperation();
                    temp1 = Integer.valueOf(sb.toString());
                    temp2 = Integer.valueOf(sb.toString());
                    lastOperation = clickedText;
                    break;
                case "*":
                    firstDigit = true;
                    doLastOperation();
                    temp1 = Integer.valueOf(sb.toString());
                    temp2 = Integer.valueOf(sb.toString());
                    lastOperation = clickedText;
                    break;
                case "/":
                    display.addText("/ not implemented");
                    /*firstDigit = true;
                    doLastOperation();
                    temp2 = Integer.valueOf(sb.toString());
                    lastOperation = clickedText;*/
                    break;
                case "%":
                    display.addText("%  not implemented");
                    /*firstDigit = true;
                    doLastOperation();
                    temp2 = Integer.valueOf(sb.toString());
                    lastOperation = clickedText;*/
                    break;
                case "=":
                    doLastOperation();
                    lastOperation = "=";
                    break;
            }
        }
    }
    public void doLastOperation() {
        if (lastOperation != null) {
            switch (lastOperation) {
                case "+":
                    temp1 += temp2;
                    break;
                case "-":
                    temp1 -= temp2;
                    break;
                case "*":
                    temp1 *= temp2;
                    break;
                case "/":
                    display.addText("/ not implemented");
                    break;
                case "%":
                    display.addText("% not implemented");
                    break;
                case "=":
                    //temp2 = temp1;
                    break;
            }
            display.deleteText();
            sb.delete(0, sb.length());
            sb.append(temp1);
            if(temp1 < 0) {
                sb.deleteCharAt(0);
                sb.append("-");
                display.addText(sb.toString());
                sb.delete(0, sb.length());
                sb.append(temp1);
            }
            else {
                display.addText(sb.toString());
            }
        }
    }
}