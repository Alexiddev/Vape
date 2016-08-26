package vape.val.liquid.model.coil;

import android.os.Parcel;
import android.os.Parcelable;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by alexiddev on 24.08.16.
 */

@DatabaseTable(tableName = "coil")
public class Coil implements Parcelable {

    public static final String NAME = "name";
    public static final String NUMBER_OF_WIRES = "number_of_wires";
    public static final String PIGTAIL_COLUMN = "pigtail_column";
    public static final String NUMBER_OFS_PIRALS = "number_ofs_pirals";
    public static final String SPIRAL_TYPE = "spiral_type";
    public static final String DIAMETER_OF_SPIRALS = "diameter_of_spirals";
    public static final String DIAMETER_OF_COILS = "diameter_of_coils";
    public static final String NUMBER_OF_TURNS = "number_of_turns";
    public static final String LEGS_LENGTH = "legs_length";
    public static final String TYPE_WIRE = "type_wire";
    public static final String WINDING_DIAM = "winding_diam";
    public static final String WINDING_TYPE = "winding_type";
    public static final String BATTERY_COLUMN = "battery_column";
    public static final String POWER_COLUMN = "power_column";
    public static final String RECOMMENDED_POWER = "recommended_power";
    public static final String RESISTANCE_COLUMN = "resistance_column";
    public static final String LENGTH_OF_WIRE = "length_of_wire";
    public static final String CURRENT_COLUMN = "current_column";
    public static final String SURFACE_POWER = "surface_power";
    public static final String LENGTH_OF_SPIRAL = "length_of_spiral";


    @DatabaseField(columnName = NAME)
    private String name;

    @DatabaseField(columnName = NUMBER_OF_WIRES)
    private double numberOfWires;

    @DatabaseField(columnName = PIGTAIL_COLUMN)
    private double pigtail;

    @DatabaseField(columnName = NUMBER_OFS_PIRALS)
    private double numberOfSpirals;

    @DatabaseField(columnName = SPIRAL_TYPE)
    private String spiralType;

    @DatabaseField(columnName = DIAMETER_OF_SPIRALS)
    private String diameterOfSpirals;

    @DatabaseField(columnName = DIAMETER_OF_COILS)
    private String diameterOfCoils;

    @DatabaseField(columnName = NUMBER_OF_TURNS)
    private double numberOfTurns;

    @DatabaseField(columnName = LEGS_LENGTH)
    private String legsLength;

    @DatabaseField(columnName = TYPE_WIRE)
    private String typeWire;

    @DatabaseField(columnName = WINDING_DIAM)
    private String windingDiam;

    @DatabaseField(columnName = WINDING_TYPE)
    private String windingType;

    @DatabaseField(columnName = BATTERY_COLUMN)
    private double battery;

    @DatabaseField(columnName = POWER_COLUMN)
    private double power;

    @DatabaseField(columnName = RECOMMENDED_POWER)
    private double recommendedPower;

    @DatabaseField(columnName = RESISTANCE_COLUMN)
    private double resistance;

    @DatabaseField(columnName = LENGTH_OF_WIRE)
    private double lengthOfWire;

    @DatabaseField(columnName = CURRENT_COLUMN)
    private double current;

    @DatabaseField(columnName = SURFACE_POWER)
    private double surfacePower;

    @DatabaseField(columnName = LENGTH_OF_SPIRAL)
    private double lengthOfSpiral;


    public Coil() {
    }

    public Coil(double numberOfWires, double pigtail, double numberOfSpirals,
                String spiralType, String diameterOfSpirals, String diameterOfCoils,
                double numberOfTurns, String legsLength, String typeWire,
                String windingDiam, String windingType, double battery, double power,
                double recommendedPower, double resistance, double lengthOfWire,
                double current, double surfacePower, double lengthOfSpiral) {
        this.numberOfWires = numberOfWires;
        this.pigtail = pigtail;
        this.numberOfSpirals = numberOfSpirals;
        this.spiralType = spiralType;
        this.diameterOfSpirals = diameterOfSpirals;
        this.diameterOfCoils = diameterOfCoils;
        this.numberOfTurns = numberOfTurns;
        this.legsLength = legsLength;
        this.typeWire = typeWire;
        this.windingDiam = windingDiam;
        this.windingType = windingType;
        this.battery = battery;
        this.power = power;
        this.recommendedPower = recommendedPower;
        this.resistance = resistance;
        this.lengthOfWire = lengthOfWire;
        this.current = current;
        this.surfacePower = surfacePower;
        this.lengthOfSpiral = lengthOfSpiral;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getNumberOfWires() {
        return numberOfWires;
    }

    public void setNumberOfWires(double numberOfWires) {
        this.numberOfWires = numberOfWires;
    }

    public double isPigtail() {
        return pigtail;
    }

    public void setPigtail(double pigtail) {
        this.pigtail = pigtail;
    }

    public double getNumberOfSpirals() {
        return numberOfSpirals;
    }

    public void setNumberOfSpirals(double numberOfSpirals) {
        this.numberOfSpirals = numberOfSpirals;
    }

    public String getSpiralType() {
        return spiralType;
    }

    public void setSpiralType(String spiralType) {
        this.spiralType = spiralType;
    }

    public String getDiameterOfSpirals() {
        return diameterOfSpirals;
    }

    public void setDiameterOfSpirals(String diameterOfSpirals) {
        this.diameterOfSpirals = diameterOfSpirals;
    }

    public String getDiameterOfCoils() {
        return diameterOfCoils;
    }

    public void setDiameterOfCoils(String diameterOfCoils) {
        this.diameterOfCoils = diameterOfCoils;
    }

    public double getNumberOfTurns() {
        return numberOfTurns;
    }

    public void setNumberOfTurns(double numberOfTurns) {
        this.numberOfTurns = numberOfTurns;
    }

    public String getLegsLength() {
        return legsLength;
    }

    public void setLegsLength(String legsLength) {
        this.legsLength = legsLength;
    }

    public String getTypeWire() {
        return typeWire;
    }

    public void setTypeWire(String typeWire) {
        this.typeWire = typeWire;
    }

    public String getWindingDiam() {
        return windingDiam;
    }

    public void setWindingDiam(String windingDiam) {
        this.windingDiam = windingDiam;
    }

    public String getWindingType() {
        return windingType;
    }

    public void setWindingType(String windingType) {
        this.windingType = windingType;
    }

    public double getBattery() {
        return battery;
    }

    public void setBattery(double battery) {
        this.battery = battery;
    }

    public double getPower() {
        return power;
    }

    public void setPower(double power) {
        this.power = power;
    }

    public double getRecommendedPower() {
        return recommendedPower;
    }

    public void setRecommendedPower(double recommendedPower) {
        this.recommendedPower = recommendedPower;
    }

    public double getResistance() {
        return resistance;
    }

    public void setResistance(double resistance) {
        this.resistance = resistance;
    }

    public double getLengthOfWire() {
        return lengthOfWire;
    }

    public void setLengthOfWire(double lengthOfWire) {
        this.lengthOfWire = lengthOfWire;
    }

    public double getCurrent() {
        return current;
    }

    public void setCurrent(double current) {
        this.current = current;
    }

    public double getSurfacePower() {
        return surfacePower;
    }

    public void setSurfacePower(double surfacePower) {
        this.surfacePower = surfacePower;
    }

    public double getLengthOfSpiral() {
        return lengthOfSpiral;
    }

    public void setLengthOfSpiral(double lengthOfSpiral) {
        this.lengthOfSpiral = lengthOfSpiral;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {

        out.writeDouble(numberOfWires);
        out.writeDouble(pigtail);
        out.writeDouble(numberOfSpirals);
        out.writeString(spiralType);
        out.writeString(diameterOfSpirals);
        out.writeString(diameterOfCoils);
        out.writeDouble(numberOfTurns);
        out.writeString(legsLength);
        out.writeString(typeWire);
        out.writeString(windingDiam);
        out.writeString(windingType);
        out.writeDouble(battery);
        out.writeDouble(power);
        out.writeDouble(recommendedPower);
        out.writeDouble(resistance);
        out.writeDouble(lengthOfWire);
        out.writeDouble(current);
        out.writeDouble(surfacePower);
        out.writeDouble(lengthOfSpiral);
    }

    public static final Parcelable.Creator<Coil> CREATOR = new Parcelable.Creator<Coil>() {
        public Coil createFromParcel(Parcel in) {
            return new Coil(in);
        }

        public Coil[] newArray(int size) {
            return new Coil[size];
        }
    };


    public Coil(Parcel in) {

        numberOfWires = in.readDouble();
        pigtail = in.readDouble();
        numberOfSpirals = in.readDouble();
        spiralType = in.readString();
        diameterOfSpirals = in.readString();
        diameterOfCoils = in.readString();
        numberOfTurns = in.readDouble();
        legsLength = in.readString();
        typeWire = in.readString();
        windingDiam = in.readString();
        windingType = in.readString();
        battery = in.readDouble();
        power = in.readDouble();
        recommendedPower = in.readDouble();
        resistance = in.readDouble();
        lengthOfWire = in.readDouble();
        current = in.readDouble();
        surfacePower = in.readDouble();
        lengthOfSpiral = in.readDouble();
    }

    @Override
    public String toString() {
        return  name;
    }




    public String forShareString() {
        String forShare =  "Coil (" +
                "name " + name +
                ", numberOfWires " + numberOfWires +
                ", pigtail=" + pigtail +
                ", numberOfSpirals=" + numberOfSpirals +
                ", spiralType " + spiralType +
                ", diameterOfSpirals "  + diameterOfSpirals +
                ", diameterOfCoils " + diameterOfCoils +
                ", numberOfTurns " + numberOfTurns +
                ", legsLength " + legsLength +
                ", typeWire " + typeWire +
                ", battery " + battery +
                ", power " + power +
                ", recommendedPower " + recommendedPower +
                ", resistance " + resistance +
                ", lengthOfWire " + lengthOfWire +
                ", current " + current +
                ", surfacePower " + surfacePower +
                ", lengthOfSpiral " + lengthOfSpiral;

        if (spiralType.equals("Clapton")){
            forShare +=  ", Winding (windingDiam " + windingDiam +
                    ", windingType " + windingType  + ')';
        }

        forShare += "  https://play.google.com/store/apps/details?id=vape.val.liquid";
        return forShare;
    }
}
