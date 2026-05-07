package view;

import controller.UserLoader;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;

@Setter
public class EditFrame extends JFrame
{
    private UserLoader userLoader;

    public EditFrame()
    {
        instance = this;
        initUI();
    }
    public static EditFrame instance;
    public static EditFrame getInstance()
    {
        if (instance == null)
        {
            instance = new EditFrame();

        }
        return instance;
    }

    public void initUI()
    {
        setTitle("Edit account");
        setSize(320, 220);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

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

        JButton editButton = new JButton("Potvrdite promene");
        JButton deleteButton = new JButton("Izbrišite nalog");

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
        panel.add(editButton, gbc);

        // Back button
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        panel.add(deleteButton, gbc);

        editButton.addActionListener(e ->
        {
            String newUsername = userField.getText().trim();
            String newPassword = new String(passField.getPassword());
            String confirmPassword = new String(confirmField.getPassword());

            if (newUsername.isEmpty() || newPassword.isEmpty())
            {
                JOptionPane.showMessageDialog(this, "Sva polja moraju biti popunjena.");
                return;
            }

            if (!newPassword.equals(confirmPassword))
            {
                JOptionPane.showMessageDialog(this, "Lozinke se ne poklapaju.");
                return;
            }

            String oldUsername = MainFrame.getInstance().getUsername();

            boolean success = userLoader.updateUser(oldUsername, newUsername, newPassword);

            if (success)
            {
                MainFrame.getInstance().setUsername(newUsername);
                JOptionPane.showMessageDialog(this, "Podaci uspešno izmenjeni!");
                this.setVisible(false);
            }
            else
            {
                JOptionPane.showMessageDialog(this, "Greška pri izmeni (korisnik možda već postoji).");
            }
        });

        deleteButton.addActionListener(e ->
        {

            JPasswordField passwordField = new JPasswordField();

            int option = JOptionPane.showConfirmDialog(
                    this,
                    passwordField,
                    "Unesite lozinku da bi ste obrisali vaš nalog",
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.WARNING_MESSAGE
            );

            if (option == JOptionPane.OK_OPTION)
            {
                String enteredPassword = new String(passwordField.getPassword());
                String currentUsername = MainFrame.getInstance().getUsername();;

                boolean success = userLoader.deleteUser(currentUsername, enteredPassword);

                if(success)
                {
                    JOptionPane.showMessageDialog(this, "Uspešno obrisan nalog.");
                    this.dispose();
                    MainFrame.getInstance().dispose();
                    LoginFrame.getInstance().setVisible(true);
                }
                else
                {
                    JOptionPane.showMessageDialog(this, "Uneta lozinka nije ispravna.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        add(panel);
    }
}
