package view;

import controller.UserLoader;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

@Getter
@Setter
public class MainFrame extends JFrame
{
    private String username;
    private String password;
    private UserLoader userLoader;

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
        JPanel mainPanel = new JPanel(new BorderLayout());
        this.setContentPane(mainPanel);

        JPanel topPanel = new JPanel(new BorderLayout());

        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JTextField searchField = new JTextField(20);
        JButton searchButton = new JButton("Pretraga");

        searchPanel.add(searchField);
        searchPanel.add(searchButton);

        JPanel accountPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton editAccountButton = new JButton("Izmenite nalog");
        JButton logoutButton = new JButton("Izlogujte se");

        accountPanel.add(editAccountButton);
        accountPanel.add(logoutButton);

        topPanel.add(searchPanel, BorderLayout.WEST);
        topPanel.add(accountPanel, BorderLayout.EAST);

        String[] columns = {"Column 1", "Column 2", "Column 3"};

        JTable table1 = new JTable(new DefaultTableModel(columns, 0));
        JTable table2 = new JTable(new DefaultTableModel(columns, 0));

        JScrollPane scrollPane1 = new JScrollPane(table1);
        JScrollPane scrollPane2 = new JScrollPane(table2);

        JSplitPane splitPane = new JSplitPane(
                JSplitPane.HORIZONTAL_SPLIT,
                scrollPane1,
                scrollPane2
        );
        splitPane.setDividerLocation(400);

        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(splitPane, BorderLayout.CENTER);

        editAccountButton.addActionListener(e ->
        {
            EditFrame.getInstance().setUserLoader(userLoader);
            EditFrame.getInstance().setVisible(true);
        });

        logoutButton.addActionListener(e ->
        {
            this.dispose();
            LoginFrame.getInstance().setVisible(true);
        });

        this.setTitle("Projekat Test 1");
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}