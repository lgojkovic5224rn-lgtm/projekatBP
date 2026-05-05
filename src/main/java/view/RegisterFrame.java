package view;

import controller.UserLoader;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;

@Setter
public class RegisterFrame extends JFrame
{
    private UserLoader userLoader;
    public RegisterFrame()
    {
        instance = this;
        initUI();
    }
    public static RegisterFrame instance;
    public static RegisterFrame getInstance()
    {

        if (instance == null)
        {
            instance = new RegisterFrame();

        }
        return instance;
    }

    public void initUI() {
        setTitle("Register");
        setSize(320, 220);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel userLabel = new JLabel("Korisničko ime:");
        JLabel passLabel = new JLabel("Lozinka:");
        JLabel confirmLabel = new JLabel("Potvrdi Lozinku:");

        JTextField userField = new JTextField(15);
        JPasswordField passField = new JPasswordField(15);
        JPasswordField confirmField = new JPasswordField(15);

        JButton registerButton = new JButton("Registrujte se");
        JButton backButton = new JButton("Nazad");

        // Username
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(userLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(userField, gbc);

        // Password
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(passLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(passField, gbc);

        // Confirm Password
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(confirmLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(confirmField, gbc);

        // Register button
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(registerButton, gbc);

        // Back button
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        panel.add(backButton, gbc);

        registerButton.addActionListener(e ->
        {
            String username = userField.getText().trim();
            String password = new String(passField.getPassword());
            String confirmPassword = new String(confirmField.getPassword());

            if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty())
            {
                JOptionPane.showMessageDialog(this, "Polja ne smeju ostati prazna.");
                return;
            }

            if (!password.equals(confirmPassword))
            {
                JOptionPane.showMessageDialog(this, "Lozinke se ne poklapaju.");
                return;
            }

            if (userLoader.userExists(username))
            {
                JOptionPane.showMessageDialog(this, "Ovo korisničko ime je već zauzeto.");
                return;
            }

            if (userLoader.addUser(username, password))
            {
                JOptionPane.showMessageDialog(this, "Uspešna registracija!");
                this.setVisible(false);
                LoginFrame loginFrame = LoginFrame.getInstance();
                loginFrame.setUserLoader(userLoader);
                loginFrame.setVisible(true);
            }
            else
            {
                JOptionPane.showMessageDialog(this, "Greška pri registraciji.");
            }
        });

        backButton.addActionListener(e ->
        {
            this.setVisible(false);
            LoginFrame.getInstance().setVisible(true);
        });

        add(panel);
    }
}
