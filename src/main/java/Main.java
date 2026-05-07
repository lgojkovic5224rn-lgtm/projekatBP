import controller.UserLoader;
import view.LoginFrame;

import javax.swing.SwingUtilities;

public class Main
{

    public static void main(String[] args)
    {
        UserLoader userLoader = new UserLoader("src/main/resources/users.txt");
        try
        {
            userLoader.loadFromFile();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() ->
        {
            LoginFrame frame = LoginFrame.getInstance();
            frame.setUserLoader(userLoader);
            frame.setVisible(true);
        });
    }
}