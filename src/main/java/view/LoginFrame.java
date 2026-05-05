package view;

import controller.UserLoader;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;

@Setter
public class LoginFrame extends JFrame
{
    private UserLoader userLoader;

    public LoginFrame()
    {
        instance = this;
        initUI();
    }
    public static LoginFrame instance;
    public static LoginFrame getInstance()
    {

        if (instance == null)
        {
            instance = new LoginFrame();

        }
        return instance;
    }

    public void initUI()
    {
        setTitle("Login");
        setSize(300, 180);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel userLabel = new JLabel("Korisničko ime:");
        JLabel passLabel = new JLabel("Lozinka:");

        JTextField userField = new JTextField(15);
        JPasswordField passField = new JPasswordField(15);

        JButton loginButton = new JButton("Ulogujte se");
        JButton registerButton = new JButton("Registujte se");

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        buttonPanel.add(loginButton);
        buttonPanel.add(registerButton);

        // Username label
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(userLabel, gbc);

        // Username field
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(userField, gbc);

        // Password label
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(passLabel, gbc);

        // Password field
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(passField, gbc);

        // Login buttons
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(buttonPanel, gbc);

        loginButton.addActionListener(e ->
        {
            String username = userField.getText();
            String password = new String(passField.getPassword());
            if (username.isEmpty() || password.isEmpty())
            {
                JOptionPane.showMessageDialog(this, "Polja ne smeju ostati prazna.");
                return;
            }

            if (userLoader.validateLogin(username, password))
            {
                JOptionPane.showMessageDialog(this, "Uspešno logovanje!");
                this.setVisible(false);
                MainFrame.getInstance().setVisible(true);

            }
            else
            {
                JOptionPane.showMessageDialog(this, "Pogrešno korisničko ime ili lozinka!");
            }
        });

        registerButton.addActionListener(e ->
        {
            this.setVisible(false);
            RegisterFrame registerFrame = RegisterFrame.getInstance();
            registerFrame.setUserLoader(userLoader);
            registerFrame.setVisible(true);
        });

        add(panel);
    }
}

