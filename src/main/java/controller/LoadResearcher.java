package controller;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoadResearcher
{
    public static void LoadResearcher(JTable table, int labID)
    {
        String sql = "SELECT i.id_istrazivaca, i.ime, i.prezime, i.datum_rodjenja, i.pol, i.stepen_obrazovanja, il.opis_uloge\n" +
                "FROM istrazivac i\n" +
                "JOIN istrazivac_laboratorija il\n" +
                "ON i.id_istrazivaca = il.id_istrazivaca\n" +
                "WHERE il.id_laboratorije = ?";
        try
        {
            Connection conn = Connect.connect();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, labID);
            ResultSet rs = statement.executeQuery();
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            model.setRowCount(0);
            while (rs.next())
            {
                Object[] row = {
                        rs.getInt("id_istrazivaca"),
                        rs.getString("ime"),
                        rs.getString("prezime"),
                        rs.getString("datum_rodjenja"),
                        rs.getString("pol"),
                        rs.getInt("stepen_obrazovanja"),
                        rs.getString("il.opis_uloge")
                };
                model.addRow(row);
            }
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }
}
