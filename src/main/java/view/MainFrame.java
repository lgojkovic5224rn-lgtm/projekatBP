package view;

import controller.Connect;
import controller.LoadLabs;
import controller.LoadResearcher;
import controller.UserLoader;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

@Getter
@Setter
public class MainFrame extends JFrame
{
    private String username;
    private String password;
    private UserLoader userLoader;
    private JTable table1;
    private JTable table2;

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
        JButton searchButton = new JButton("Search");

        searchPanel.add(searchField);
        searchPanel.add(searchButton);

        JPanel accountPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton editAccountButton = new JButton("Edit Account");

        accountPanel.add(editAccountButton);

        topPanel.add(searchPanel, BorderLayout.WEST);
        topPanel.add(accountPanel, BorderLayout.EAST);

        String[] columns1 = {"ID", "Naziv", "Lokacija"};
        String[] columns2 = {"ID", "Ime", "Prezime", "Datum Rodjenja", "Pol", "Stepen Obrazovanja", "Uloga"};

        table1 = new JTable(new DefaultTableModel(columns1, 0));
        table2 = new JTable(new DefaultTableModel(columns2, 0));
        LoadLabs.loadLabs(table1);

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

        table1.getSelectionModel().addListSelectionListener(e ->
        {
            if(!e.getValueIsAdjusting())
            {
                int row = table1.getSelectedRow();
                if(row != -1)
                {
                    int LabID = Integer.parseInt(table1.getValueAt(row, 0).toString());
                    LoadResearcher.LoadResearcher(table2,LabID);
                }
            }
        });

        this.setTitle("Projekat Test 1");
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}