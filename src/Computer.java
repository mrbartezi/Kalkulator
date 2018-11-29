import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.math.MathContext;

public class Computer implements ActionListener {

    private Display display;
    private StringBuilder sb = new StringBuilder();
    private String lastOperation;
    private boolean firstDigit = true;
    private BigDecimal btemp1 = BigDecimal.valueOf(0), btemp2 = BigDecimal.valueOf(0);

    public Computer() {

    }

    public void setDisplay(Display display) {
        this.display = display;
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
                textToDisplay();
                btemp2 = BigDecimal.valueOf(Double.valueOf(sb.toString()));
            }
        } catch (Exception exception) {

        } finally {

            //Rest of the buttons
            switch (clickedText) {
                case "C":
                    display.deleteText();
                    sb.delete(0, sb.length());
                    lastOperation = null;
                    btemp1 = btemp2 = BigDecimal.valueOf(0);
                    break;
                case "DEL":
                    try{
                        if(sb.length() >12 ) {
                            sb.delete(11,sb.length());
                        }
                        else {
                            sb.deleteCharAt(sb.length() - 1);
                        }
                        textToDisplay();
                        btemp1 = BigDecimal.valueOf(Double.valueOf(sb.toString()));
                        }
                        catch (Exception e1) {

                        }
                    break;
                case "+":
                    firstDigit = true;
                    doLastOperation();
                    btemp1 = BigDecimal.valueOf(Double.valueOf(sb.toString()));
                    btemp2 = BigDecimal.valueOf(Double.valueOf(sb.toString()));
                    lastOperation = clickedText;
                    break;
                case "-":
                    firstDigit = true;
                    doLastOperation();
                    btemp1 = BigDecimal.valueOf(Double.valueOf(sb.toString()));
                    btemp2 = BigDecimal.valueOf(Double.valueOf(sb.toString()));
                    lastOperation = clickedText;
                    break;
                case "*":
                    firstDigit = true;
                    doLastOperation();
                    btemp1 = BigDecimal.valueOf(Double.valueOf(sb.toString()));
                    btemp2 = BigDecimal.valueOf(Double.valueOf(sb.toString()));
                    lastOperation = clickedText;
                    break;
                case "/":
                    firstDigit = true;
                    doLastOperation();
                    btemp1 = BigDecimal.valueOf(Double.valueOf(sb.toString()));
                    btemp2 = BigDecimal.valueOf(Double.valueOf(sb.toString()));
                    lastOperation = clickedText;
                    break;
                case "%":
                    display.addText("%  not implemented");
                    /*firstDigit = true;
                    doLastOperation();
                    temp1 = Double.valueOf(sb.toString());
                    temp2 = Double.valueOf(sb.toString());
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
                    btemp1 = btemp1.add(btemp2);
                    break;
                case "-":
                    btemp1 = btemp1.subtract(btemp2);
                    break;
                case "*":
                    btemp1 = btemp1.multiply(btemp2);
                    break;
                case "/":
                    if(btemp2.equals(BigDecimal.valueOf(0.0))) {
                        sb.delete(0, sb.length());
                        lastOperation = null;
                        btemp1 = btemp2 = BigDecimal.valueOf(0);
                    }
                    else {
                        btemp1 = btemp1.divide(btemp2, MathContext.DECIMAL128);
                    }
                    break;
                case "%":
                    display.addText("% not implemented");
                    break;
            }
            sb.delete(0, sb.length());
            sb.append(btemp1);
            textToDisplay();
        }
    }

    public void textToDisplay() {
        loop1: //Loop which deletes .0 if number is an integer.
        for(int i = 0; i < sb.length(); i++) {
            if(sb.charAt(i) == (char)69) {
                break loop1;
            }
            if(sb.charAt(i) == (char)46) {
                if(sb.length() - i > 12) {
                    for (int j = i + 1; j < i+12; j++) {
                        if(sb.charAt(j) != (char) 48) {
                            break loop1;
                        }
                    }
                    sb.delete(i, sb.length());
                }
            }
        }
        loop2: //Loop which rounds number to integer.
        for(int i = 0; i < sb.length(); i++) {
            if (sb.charAt(i) == (char) 46) {
                if (sb.length() - i > 12) {
                    for (int j = i + 1; j < i+12; j++) {
                        if (sb.charAt(i) == (char)69) {
                            System.out.println(sb.charAt(i));
                            break loop2;
                        }
                        if(sb.charAt(i + 1) != (char) 57) {
                            break loop2;
                        }
                        sb.delete(i, sb.length());
                        sb.append(Integer.valueOf(sb.toString()) + 1);
                        sb.delete(0, i);
                    }
                } else {
                    break loop2;
                }
            }
        }

        if(sb.length() < 12) {
            if(btemp1.signum() == -1) {
                display.addText(sb.substring(1, sb.length()) + "-");
            }
            else {
                display.addText(sb.toString());
            }
        }
        else {
            if(btemp1.signum() == -1) {
                display.addText(sb.substring(1,13) + "-");
            }
            else {
                display.addText(sb.substring(0,12));
            }
        }

    }
}