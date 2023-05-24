package dat.backend.model.entities;

public class LengthDefault
{
    private int lengthId;
    private float length;

    public LengthDefault(int lengthId, float length){
        this.lengthId = lengthId;
        this.length = length;
    }

    public int getLengthId()
    {
        return lengthId;
    }

    public float getLength()
    {
        return length;
    }
}
