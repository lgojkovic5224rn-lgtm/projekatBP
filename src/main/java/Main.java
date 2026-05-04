
import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {
        // Pokretanje UI-a na EDT (bitno za Swing)
        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame();
            frame.initUI();
        });
    }
}