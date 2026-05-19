package controller;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoadLabs
{
    public static void loadLabs(JTable table)
    {
        String sql = "SELECT * FROM laboratorija";
        try
        {
            Connection conn = Connect.connect();
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            model.setRowCount(0);

            while (rs.next())
            {

                Object[] row = {rs.getInt("id_laboratorije"), rs.getString("naziv"), rs.getString("lokacija")};

                model.addRow(row);
            }
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }

    }
}
