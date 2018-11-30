import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.math.MathContext;

public class Computer implements ActionListener {

    private Display display;
    private StringBuilder sb = new StringBuilder();
    private String lastOperation;
    private boolean firstDigit = true, nextNumberNegative = false;
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
                if(sb.toString().equals("0")) {
                    sb.deleteCharAt(0);
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
                    try {
                        if (sb.length() > 12) {
                            sb.delete(11, sb.length());
                        } else {
                            sb.deleteCharAt(sb.length() - 1);
                        }
                        textToDisplay();
                        btemp1 = BigDecimal.valueOf(Double.valueOf(sb.toString()));
                    } catch (Exception e1) {

                    }
                    break;
                case "+":
                    firstDigit = true;
                    doLastOperation();

                    if(sb.length() > 0) {
                        btemp1 = BigDecimal.valueOf(Double.valueOf(sb.toString()));
                        btemp2 = BigDecimal.valueOf(Double.valueOf(sb.toString()));
                    }
                    lastOperation = clickedText;
                    break;
                case "-":
                    firstDigit = true;
                    doLastOperation();

                    if(sb.length() > 0) {
                        btemp1 = BigDecimal.valueOf(Double.valueOf(sb.toString()));
                        btemp2 = BigDecimal.valueOf(Double.valueOf(sb.toString()));
                    }
                    /*
                    if(sb.length() == 0) {
                        nextNumberNegative = true;
                    }
                    */
                    lastOperation = clickedText;
                    break;
                case "*":
                    firstDigit = true;
                    doLastOperation();

                    if(sb.length() > 0) {
                        btemp1 = BigDecimal.valueOf(Double.valueOf(sb.toString()));
                        btemp2 = BigDecimal.valueOf(Double.valueOf(sb.toString()));
                    }
                    lastOperation = clickedText;
                    break;
                case "/":
                    firstDigit = true;
                    doLastOperation();

                    if(sb.length() > 0) {
                        btemp1 = BigDecimal.valueOf(Double.valueOf(sb.toString()));
                        btemp2 = BigDecimal.valueOf(Double.valueOf(sb.toString()));
                    }
                    lastOperation = clickedText;
                    break;
                case "%":
                    firstDigit = true;
                    doLastOperation();

                    btemp1 = BigDecimal.valueOf(Double.valueOf(sb.toString()));
                    btemp1 = btemp1.divide(BigDecimal.valueOf(100), MathContext.DECIMAL128);
                    sb.delete(0, sb.length());
                    sb.append(btemp1);
                    textToDisplay();

                    if(sb.length() > 0) {
                        btemp1 = BigDecimal.valueOf(Double.valueOf(sb.toString()));
                        btemp2 = BigDecimal.valueOf(Double.valueOf(sb.toString()));
                    }
                    lastOperation = clickedText;
                    break;
                case ".":
                    /*
                    doLastOperation();
                    if(sb.length() > 0) {
                        btemp1 = BigDecimal.valueOf(Double.valueOf(sb.toString()));
                        btemp2 = BigDecimal.valueOf(Double.valueOf(sb.toString()));
                    }
                    lastOperation = clickedText;
                    */
                    break;
                case "=":
                    doLastOperation();

                    if(sb.length() > 0) {
                        btemp1 = BigDecimal.valueOf(Double.valueOf(sb.toString()));
                        btemp2 = BigDecimal.valueOf(Double.valueOf(sb.toString()));
                    }
                    lastOperation = clickedText;
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
                    /*
                    if(nextNumberNegative) {
                        btemp1 = btemp1.subtract(btemp2);
                        nextNumberNegative = false;
                    }
                    */
                    break;
                case "*":
                    btemp1 = btemp1.multiply(btemp2);
                    break;
                case "/":
                    if (btemp2.equals(BigDecimal.valueOf(0.0))) {
                        sb.delete(0, sb.length());
                        lastOperation = null;
                        btemp1 = btemp2 = BigDecimal.valueOf(0);
                    } else {
                        btemp1 = btemp1.divide(btemp2, MathContext.DECIMAL128);
                    }
                    break;
                case ".":
                    /*
                    btemp2 = btemp2.divide(BigDecimal.valueOf(Math.pow(10, decimalCounter)), MathContext.DECIMAL128);
                    btemp1 = btemp1.add(btemp2);
                    break;
                    */
                case "=":
                    break;
            }
            sb.delete(0, sb.length());
            sb.append(btemp1.toPlainString());
            textToDisplay();
        }
    }

    public void textToDisplay() {
        loop1: //Function which deletes .0 if number is an integer.
        for (int i = 0; i < sb.length(); i++) {
            if (sb.charAt(i) == (char) 69) { //(char)69 = "E"
                break;
            }
            if (sb.charAt(i) == (char) 46) { //(char)46 = "."
                if (sb.length() - i > 0) {
                    for (int j = i + 1; j < sb.length(); j++) {
                        if (sb.charAt(j) != (char) 48) { //(char)48 = "0"
                            break loop1;
                        }
                    }
                    sb.delete(i, sb.length());
                }
            }
        }
        loop2: //Function which deletes unnecessary zeros in decimal representation of a fraction
        for (int i = 0; i < sb.length(); i++) {
            if (sb.charAt(i) == (char) 69) { //(char)69 = "E"
                break;
            }
            if (sb.charAt(i) == (char) 46) { //(char)46 = "."
                if (sb.length() - i > 0) {
                    childloop2:
                    for (int j = i + 1; j < sb.length(); j++) {
                        if (sb.charAt(j) == (char) 48) { //(char)48 = "0"
                            for (int k = j + 1; k < sb.length(); k++) {
                                if(sb.charAt(k) != (char) 48) {
                                    continue childloop2;
                                }
                            }
                            sb.delete(j, sb.length());
                        }
                    }
                }
            }
        }
        loop3: //Function which rounds number to integer.
        for (int i = 0; i < sb.length(); i++) {
            if (sb.charAt(i) == (char) 46) { //(char)46 = "."
                if (sb.length() - i > 12) {
                    for (int j = i + 1; j < i + 12; j++) {
                        if (sb.charAt(j) == (char) 69) { //(char)69 = "E"
                            break loop3;
                        }
                        if (sb.charAt(j) != (char) 57) { //(char)57 = "9"
                            break loop3;
                        }
                    }
                    sb.delete(i, sb.length());
                    sb.append(Integer.valueOf(sb.toString()) + 1);
                    sb.delete(0, i);
                } else {
                    break;
                }
            }
        }
        if (sb.length() < 12) {
            display.addText(sb.toString());
        } else {
            display.addText(sb.substring(0, 12));
        }
    }
}