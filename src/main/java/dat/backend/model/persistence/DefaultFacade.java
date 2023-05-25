package dat.backend.model.persistence;

import dat.backend.model.entities.LengthDefault;
import dat.backend.model.entities.TypeDefault;
import dat.backend.model.entities.UnitDefault;
import dat.backend.model.exceptions.DatabaseException;
import java.sql.SQLException;
import java.util.List;

public class DefaultFacade
{
    public static List<UnitDefault> getUnitTypes(ConnectionPool connectionPool) throws SQLException, DatabaseException
    {
        return DefaultMapper.getUnitTypes(connectionPool);
    }

    public static List<LengthDefault> getLengthTypes(ConnectionPool connectionPool) throws SQLException, DatabaseException
    {
        return DefaultMapper.getLengthTypes(connectionPool);
    }

    public static List<TypeDefault> getTypes(ConnectionPool connectionPool) throws SQLException, DatabaseException
    {
        return DefaultMapper.getTypes(connectionPool);
    }
}
