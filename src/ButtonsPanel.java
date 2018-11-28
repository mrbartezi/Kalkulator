import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ButtonsPanel extends JPanel implements ActionListener {

    private ArrayList<Button> buttons;
    private Computer computer;

    public ButtonsPanel() {

        buttons = new ArrayList<>();

        for(int i = 0; i<20; i++){
            buttons.add(new Button(i+""));
        }

        //Naming buttons
        buttons.get(0).setText("C");
        buttons.get(1).setText("DEL");
        buttons.get(2).setText("%");
        buttons.get(3).setText("/");
        buttons.get(4).setText("7");
        buttons.get(5).setText("8");
        buttons.get(6).setText("9");
        buttons.get(7).setText("*");
        buttons.get(8).setText("4");
        buttons.get(9).setText("5");
        buttons.get(10).setText("6");
        buttons.get(11).setText("-");
        buttons.get(12).setText("1");
        buttons.get(13).setText("2");
        buttons.get(14).setText("3");
        buttons.get(15).setText("+");
        buttons.get(16).setText("EXT");
        buttons.get(17).setText("0");
        buttons.get(18).setText(".");
        buttons.get(19).setText("=");

        //Adding functions to buttons
        buttons.get(12).addActionListener(this);
        buttons.get(13).addActionListener(this);
        buttons.get(14).addActionListener(this);
        buttons.get(17).addActionListener(this);
        buttons.get(4).addActionListener(this);
        buttons.get(5).addActionListener(this);
        buttons.get(6).addActionListener(this);
        buttons.get(8).addActionListener(this);
        buttons.get(9).addActionListener(this);
        buttons.get(10).addActionListener(this);

        buttons.get(0).addActionListener(this); // "C"
        buttons.get(15).addActionListener(this); // "+"
        buttons.get(19).addActionListener(this); // "="


        buttonsLayout();
    }

    public void buttonsLayout() {
        setLayout(new GridBagLayout());

        GridBagConstraints gc = new GridBagConstraints();

        gc.weightx = 0.01;
        gc.weighty = 0.01;
        gc.fill = GridBagConstraints.BOTH;

        int count = 0;
        for(int i = 0; i<5; i++) {
            for(int j = 0; j<4; j++) {
                gc.gridy = i;
                gc.gridx = j;
                add(buttons.get(count), gc);
                count++;
            }
        }


    }
    public void setComputer(Computer computer) {
        this.computer = computer;
    }

    public void actionPerformed(ActionEvent e) {
        computer.actionPerformed(e);
    }
}