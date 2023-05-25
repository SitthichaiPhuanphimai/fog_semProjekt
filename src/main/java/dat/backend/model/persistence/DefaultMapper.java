package dat.backend.model.persistence;

import dat.backend.model.entities.LengthDefault;
import dat.backend.model.entities.TypeDefault;
import dat.backend.model.entities.UnitDefault;
import dat.backend.model.exceptions.DatabaseException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DefaultMapper
{

    public static List<UnitDefault> getUnitTypes(ConnectionPool connectionPool) throws SQLException, DatabaseException
    {
        String sql = "SELECT * from unit";
        List<UnitDefault> unitDefaults = new ArrayList<>();

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql))
        {

            try (ResultSet rs = ps.executeQuery())
            {
                while (rs.next())
                {
                    int id = rs.getInt("id");
                    String type = rs.getString("unit");

                    UnitDefault unitDefault = new UnitDefault(id, type);
                    unitDefaults.add(unitDefault);
                }
            } catch (SQLException e)
            {
                throw new DatabaseException(e, "Couldn't retrieve units from the unit table");
            }
        }
        return unitDefaults;
    }
    public static List<LengthDefault> getLengthTypes(ConnectionPool connectionPool) throws SQLException, DatabaseException
    {
        String query= "SELECT * FROM fog.material_length";

        List<LengthDefault>lengthDefaults = new ArrayList<>();

        try(Connection connection1 = connectionPool.getConnection();
            PreparedStatement preparedStatement = connection1.prepareStatement(query);
            ResultSet resultSet1 = preparedStatement.executeQuery())
        {
            while(resultSet1.next())
            {
                int lengthId = resultSet1.getInt("id");
                float length = resultSet1.getFloat("length");

                LengthDefault lengthDefault = new LengthDefault(lengthId, length);

                lengthDefaults.add(lengthDefault);

            }
        }catch (SQLException e)
        {
            e.printStackTrace();
            throw new DatabaseException(e, "Could not get length from material_length table in database");
        }
        return lengthDefaults;
    }

    public static List<TypeDefault> getTypes(ConnectionPool connectionPool) throws SQLException, DatabaseException
    {
        String sql = "SELECT * from material_type";
        List<TypeDefault> typeDefaults = new ArrayList<>();

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql))
        {

            try (ResultSet rs = ps.executeQuery())
            {
                while (rs.next())
                {
                    int id = rs.getInt("id");
                    String type = rs.getString("type");

                    TypeDefault typeDefault = new TypeDefault(id,type);
                    typeDefaults.add(typeDefault);
                }
            } catch (SQLException e){
                throw new DatabaseException(e, "Couldn't retrieve types from the material_type table");
            }
        }
        return typeDefaults;
    }
}
