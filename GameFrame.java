import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {

    GameFrame()
    {
        setTitle("Pong Game");
        getContentPane().setBackground(Color.black);

        GamePanel panel = new GamePanel();
        add(panel);

        pack();//take the size of pannel
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);

    }
}
