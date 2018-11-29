import javax.swing.*;
import java.math.BigDecimal;
import java.math.MathContext;

public class Main {
    public static void main(String[] args) {


        /*StringBuilder sb = new StringBuilder();

        BigDecimal bg = BigDecimal.valueOf(Double.valueOf("123" + "."));
        System.out.println(bg);
        System.out.println(Double.valueOf("123" + "."));
        sb.append(bg);
        System.out.println(sb.length() + " sb.append to string");*/

        try
        {
            for(UIManager.LookAndFeelInfo info:UIManager.getInstalledLookAndFeels()) {
                if(info.getName() == "Windows") {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        }
        catch(Exception e) {
        }

        SwingUtilities.invokeLater(() -> {
            new MainFrame();
        });
    }
}