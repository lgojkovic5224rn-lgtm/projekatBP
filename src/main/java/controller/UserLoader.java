package controller;

import lombok.Getter;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

@Getter
public class UserLoader
{

    private Map<String, String> users = new HashMap<>();
    private String filePath;

    public UserLoader(String filePath)
    {
        this.filePath = filePath;
    }

    public void loadFromFile() throws IOException
    {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;

        while ((line = reader.readLine()) != null)
        {
            if (line.trim().isEmpty()) continue;
            String[] parts = line.split(":", 2);
            if (parts.length == 2)
            {
                users.put(parts[0].trim(), parts[1].trim());
            }
        }

        reader.close();
    }

    public boolean validateLogin(String username, String password)
    {
        return users.containsKey(username) && users.get(username).equals(password);
    }

    public boolean userExists(String username)
    {
        return users.containsKey(username);
    }

    public boolean addUser(String username, String password)
    {
        if (users.containsKey(username)) return false;

        users.put(username, password);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true)))
        {
            writer.write(username + ":" + password);
            writer.newLine();
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public boolean updateUser(String oldUsername, String newUsername, String newPassword)
    {
        if (!users.containsKey(oldUsername)) return false;

        if (!oldUsername.equals(newUsername) && users.containsKey(newUsername)) return false;

        users.remove(oldUsername);

        users.put(newUsername, newPassword);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath)))
        {
            for (Map.Entry<String, String> entry : users.entrySet())
            {
                writer.write(entry.getKey() + ":" + entry.getValue());
                writer.newLine();
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public boolean deleteUser(String username, String password)
    {
        if (!validateLogin(username, password)) return false;

        users.remove(username);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath)))
        {
            for (Map.Entry<String, String> entry : users.entrySet())
            {
                writer.write(entry.getKey() + ":" + entry.getValue());
                writer.newLine();
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return false;
        }

        return true;
    }
}
