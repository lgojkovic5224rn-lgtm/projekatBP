package view;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainFrame extends JFrame
{

    public MainFrame()
    {
        instance = this;
        initUI();
    }
    public static MainFrame instance;
    public static MainFrame getInstance()
    {

        if (instance == null)
        {
            instance = new MainFrame();

        }
        return instance;
    }

    public void initUI()
    {
        JPanel panel = new JPanel();
        this.setContentPane(panel);
        this.setTitle("Projekat Test 1");
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}