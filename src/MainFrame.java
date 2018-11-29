import javax.swing.*;
import java.awt.*;


public class MainFrame extends JFrame {

    private Display display;
    private ButtonsPanel buttonsPanel;
    private Computer computer;

    public MainFrame() {

        //Basic commands
        setLayout(new BorderLayout());
        setLocation(760,340);
        setResizable(false);
        setSize(300,300);
        setTitle("Kalkulator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);


        //Components
        display = new Display();
        buttonsPanel = new ButtonsPanel();
        computer = new Computer();

        buttonsPanel.setComputer(computer);
        computer.setDisplay(display);

        //Adding components
        add(display, BorderLayout.NORTH);
        add(buttonsPanel, BorderLayout.CENTER);


    }
}