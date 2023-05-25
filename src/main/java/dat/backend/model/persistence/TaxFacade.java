package dat.backend.model.persistence;

import dat.backend.model.entities.Tax;
import dat.backend.model.exceptions.DatabaseException;

import java.util.List;

public class TaxFacade
{
    public static void updateTaxValue(int taxId, double newTaxValue, ConnectionPool connectionPool) throws DatabaseException
    {
        TaxMapper.updateTaxValue(taxId, newTaxValue, connectionPool);
    }

    public static List<Tax> getTaxList(ConnectionPool connectionPool) throws DatabaseException
    {
        return TaxMapper.getTaxList(connectionPool);
    }
}
