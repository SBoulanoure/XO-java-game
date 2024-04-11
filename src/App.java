import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class App {
    public static void main(String[] args) throws Exception {

                SwingUtilities.invokeLater(() -> {
        JFrame jframe = new JFrame();
        jframe.setVisible(true);
        jframe.setSize(620, 640);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setTitle("2Game");
        jframe.setResizable(false); 
        jframe.setLocationRelativeTo(null);
        Game game = new Game();
        jframe.add(game); 
        jframe.addMouseListener(game); 
        });
    }
}
