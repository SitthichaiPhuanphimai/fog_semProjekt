package dat.backend.model.persistence;

import dat.backend.model.entities.Tax;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TaxMapper
{
    public static List<Tax> getTaxList()
    {
            List<Tax> taxList = new ArrayList<>();
            ConnectionPool connectionPool = new ConnectionPool();
            String query = "SELECT * FROM fog.taxes";

            try(Connection connection = connectionPool.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery())
            {
                while (resultSet.next())
                {
                    int id = resultSet.getInt("id");
                    String taxName = resultSet.getString("tax_name");
                    float taxValue = resultSet.getFloat("tax_value");

                    Tax tax = new Tax(id, taxValue, taxName);
                    taxList.add(tax);
                }


            } catch (SQLException sqlException)
            {
                sqlException.printStackTrace();
            }

            return taxList;
    }

    public static void updateTaxValue(int taxId, double newValue)
    {
        String query = "UPDATE fog.taxes SET tax_value = ? WHERE id = ?";
        ConnectionPool connectionPool = new ConnectionPool();

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query))
        {

            preparedStatement.setDouble(1, newValue);
            preparedStatement.setInt(2, taxId);
            preparedStatement.executeUpdate();

        } catch (SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
    }

}


