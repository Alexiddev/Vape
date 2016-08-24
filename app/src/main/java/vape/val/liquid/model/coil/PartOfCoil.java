package vape.val.liquid.model.coil;

/**
 * Created by alexiddev on 23.08.16.
 */
public class PartOfCoil {

    protected String name;
    protected Double value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return name;
    }
}
