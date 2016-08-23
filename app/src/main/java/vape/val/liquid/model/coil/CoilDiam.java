package vape.val.liquid.model.coil;

/**
 * Created by v.aleksandrenko on 23.08.2016.
 */
public class CoilDiam {

    private String name;
    private Double value;

    public CoilDiam(String name, Double value) {
        this.name = name;
        this.value = value;
    }

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
        return  name;
    }
}
