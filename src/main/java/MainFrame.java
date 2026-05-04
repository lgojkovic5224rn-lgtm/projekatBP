
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainFrame extends JFrame {

    public MainFrame() {
        // konstruktor može ostati prazan ili za inicijalne stvari
    }

    public void initUI() {
        // Root panel (prazan)
        JPanel panel = new JPanel();

        // Dodavanje panela u frame
        this.setContentPane(panel);

        // Osnovna podešavanja prozora
        this.setTitle("Projekat Test 1");
        this.setSize(800, 600);
        this.setLocationRelativeTo(null); // centriranje
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Prikaz
        this.setVisible(true);
    }
}