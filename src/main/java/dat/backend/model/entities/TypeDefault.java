package dat.backend.model.entities;

public class TypeDefault
{
    private int typeId;
    private String typeName;

    public TypeDefault(int typeId, String typeName)
    {
        this.typeId = typeId;
        this.typeName = typeName;
    }

    public int getTypeId()
    {
        return typeId;
    }

    public String getTypeName()
    {
        return typeName;
    }
}
