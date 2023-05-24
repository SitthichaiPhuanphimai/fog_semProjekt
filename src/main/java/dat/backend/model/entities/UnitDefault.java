package dat.backend.model.entities;

public class UnitDefault
{
    private int unitId;
    private String unitName;

    public UnitDefault(int unitId, String unitName)
    {
        this.unitId = unitId;
        this.unitName = unitName;
    }

    public int getUnitId()
    {
        return unitId;
    }

    public String getUnitName()
    {
        return unitName;
    }

    @Override
    public String toString()
    {
        return "UnitDefault{" +
                "unitId=" + unitId +
                ", unitName='" + unitName + '\'' +
                '}';
    }
}
