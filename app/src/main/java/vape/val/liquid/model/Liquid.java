package vape.val.liquid.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by v.aleksandrenko on 01.08.2016.
 */
public class Liquid implements Parcelable {

    private String name;
    private float nicotineJuiceMl;
    private float nicotineJuiceGrams;
    private float nicotineJuicePercent;

    private float propyleneGlycolMl;
    private float propyleneGlycolGrams;
    private float propyleneGlycolPercent;

    private float vegetableGlycerinMl;
    private float vegetableGlycerinGrams;
    private float vegetableGlycerinPercent;

    private float waterMl;
    private float waterGrams;
    private float waterPercent;

    private float flavorMl_1;
    private float flavorGrams_1;
    private float flavorPercent_1;
    private String flavorName_1;

    private float flavorMl_2;
    private float flavorGrams_2;
    private float flavorPercent_2;
    private String flavorName_2;

    private float flavorMl_3;
    private float flavorGrams_3;
    private float flavorPercent_3;
    private String flavorName_3;

    public Liquid() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public float getNicotineJuiceMl() {
        return nicotineJuiceMl;
    }

    public void setNicotineJuiceMl(float nicotineJuiceMl) {
        this.nicotineJuiceMl = nicotineJuiceMl;
    }

    public float getNicotineJuiceGrams() {
        return nicotineJuiceGrams;
    }

    public void setNicotineJuiceGrams(float nicotineJuiceGrams) {
        this.nicotineJuiceGrams = nicotineJuiceGrams;
    }

    public float getNicotineJuicePercent() {
        return nicotineJuicePercent;
    }

    public void setNicotineJuicePercent(float nicotineJuicePercent) {
        this.nicotineJuicePercent = nicotineJuicePercent;
    }

    public float getPropyleneGlycolMl() {
        return propyleneGlycolMl;
    }

    public void setPropyleneGlycolMl(float propyleneGlycolMl) {
        this.propyleneGlycolMl = propyleneGlycolMl;
    }

    public float getPropyleneGlycolGrams() {
        return propyleneGlycolGrams;
    }

    public void setPropyleneGlycolGrams(float propyleneGlycolGrams) {
        this.propyleneGlycolGrams = propyleneGlycolGrams;
    }

    public float getPropyleneGlycolPercent() {
        return propyleneGlycolPercent;
    }

    public void setPropyleneGlycolPercent(float propyleneGlycolPercent) {
        this.propyleneGlycolPercent = propyleneGlycolPercent;
    }

    public float getVegetableGlycerinMl() {
        return vegetableGlycerinMl;
    }

    public void setVegetableGlycerinMl(float vegetableGlycerinMl) {
        this.vegetableGlycerinMl = vegetableGlycerinMl;
    }

    public float getVegetableGlycerinGrams() {
        return vegetableGlycerinGrams;
    }

    public void setVegetableGlycerinGrams(float vegetableGlycerinGrams) {
        this.vegetableGlycerinGrams = vegetableGlycerinGrams;
    }

    public float getVegetableGlycerinPercent() {
        return vegetableGlycerinPercent;
    }

    public void setVegetableGlycerinPercent(float vegetableGlycerinPercent) {
        this.vegetableGlycerinPercent = vegetableGlycerinPercent;
    }

    public float getWaterMl() {
        return waterMl;
    }

    public void setWaterMl(float waterMl) {
        this.waterMl = waterMl;
    }

    public float getWaterGrams() {
        return waterGrams;
    }

    public void setWaterGrams(float waterGrams) {
        this.waterGrams = waterGrams;
    }

    public float getWaterPercent() {
        return waterPercent;
    }

    public void setWaterPercent(float waterPercent) {
        this.waterPercent = waterPercent;
    }

    public float getFlavorMl_1() {
        return flavorMl_1;
    }

    public void setFlavorMl_1(float flavorMl_1) {
        this.flavorMl_1 = flavorMl_1;
    }

    public float getFlavorGrams_1() {
        return flavorGrams_1;
    }

    public void setFlavorGrams_1(float flavorGrams_1) {
        this.flavorGrams_1 = flavorGrams_1;
    }

    public float getFlavorPercent_1() {
        return flavorPercent_1;
    }

    public void setFlavorPercent_1(float flavorPercent_1) {
        this.flavorPercent_1 = flavorPercent_1;
    }

    public String getFlavorName_1() {
        return flavorName_1;
    }

    public void setFlavorName_1(String flavorName_1) {
        this.flavorName_1 = flavorName_1;
    }

    public float getFlavorMl_2() {
        return flavorMl_2;
    }

    public void setFlavorMl_2(float flavorMl_2) {
        this.flavorMl_2 = flavorMl_2;
    }

    public float getFlavorGrams_2() {
        return flavorGrams_2;
    }

    public void setFlavorGrams_2(float flavorGrams_2) {
        this.flavorGrams_2 = flavorGrams_2;
    }

    public float getFlavorPercent_2() {
        return flavorPercent_2;
    }

    public void setFlavorPercent_2(float flavorPercent_2) {
        this.flavorPercent_2 = flavorPercent_2;
    }

    public String getFlavorName_2() {
        return flavorName_2;
    }

    public void setFlavorName_2(String flavorName_2) {
        this.flavorName_2 = flavorName_2;
    }

    public float getFlavorMl_3() {
        return flavorMl_3;
    }

    public void setFlavorMl_3(float flavorMl_3) {
        this.flavorMl_3 = flavorMl_3;
    }

    public float getFlavorGrams_3() {
        return flavorGrams_3;
    }

    public void setFlavorGrams_3(float flavorGrams_3) {
        this.flavorGrams_3 = flavorGrams_3;
    }

    public float getFlavorPercent_3() {
        return flavorPercent_3;
    }

    public void setFlavorPercent_3(float flavorPercent_3) {
        this.flavorPercent_3 = flavorPercent_3;
    }

    public String getFlavorName_3() {
        return flavorName_3;
    }

    public void setFlavorName_3(String flavorName_3) {
        this.flavorName_3 = flavorName_3;
    }

    @Override
    public String toString() {

        String forShare = "Liquid " + name +
                ", Nicotine juice (ml=" + nicotineJuiceMl +
                ",  grams=" + nicotineJuiceGrams +
                ",  percent=" + nicotineJuicePercent + ')' +
                ", Propylene Glycol (ml=" + propyleneGlycolMl +
                ", grams=" + propyleneGlycolGrams +
                ", percent=" + propyleneGlycolPercent + ')' +
                ", Vegetable Glycerin (ml=" + vegetableGlycerinMl +
                ", grams=" + vegetableGlycerinGrams +
                ", percent=" + vegetableGlycerinPercent + ')' +
                ", Water/Vodka/PGA (ml=" + waterMl +
                ", grams=" + waterGrams +
                ", percent=" + waterPercent + ')';



        if (flavorMl_1 != 0.0){
            forShare +=  ", Flavor " + getFlavorName_1() +
                    " (ml=" + flavorMl_1 +
                    ", grams=" + flavorGrams_1 +
                    ", percent=" + flavorPercent_1 + ')';
        }

        if (flavorMl_2 != 0.0){
            forShare += ", Flavor " + getFlavorName_2() +
                    " (ml=" + flavorMl_2 +
                    ", grams=" + flavorGrams_2 +
                    ", percent=" + flavorPercent_2 + ')';
        }

        if (flavorMl_3 != 0.0){
            forShare +=  ", Flavor " + getFlavorName_3() +
                    " (ml=" + flavorMl_3 +
                    ", grams=" + flavorGrams_3 +
                    ", percent=" + flavorPercent_3;
        }


        forShare += "  https://play.google.com/store/apps/details?id=vape.val.liquid";
        return forShare;
    }

    @Override
    public int describeContents() {
        return 0;
    }


    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeString(name);
        out.writeFloat(nicotineJuiceMl);
        out.writeFloat(nicotineJuiceGrams);
        out.writeFloat(nicotineJuicePercent);
        out.writeFloat(propyleneGlycolMl);
        out.writeFloat(propyleneGlycolGrams);
        out.writeFloat(propyleneGlycolPercent);
        out.writeFloat(vegetableGlycerinMl);
        out.writeFloat(vegetableGlycerinGrams);
        out.writeFloat(vegetableGlycerinPercent);
        out.writeFloat(waterMl);
        out.writeFloat(waterGrams);
        out.writeFloat(waterPercent);
        out.writeFloat(flavorMl_1);
        out.writeFloat(flavorGrams_1);
        out.writeFloat(flavorPercent_1);
        out.writeString(flavorName_1);
        out.writeFloat(flavorMl_2);
        out.writeFloat(flavorGrams_2);
        out.writeFloat(flavorPercent_2);
        out.writeString(flavorName_2);
        out.writeFloat(flavorMl_3);
        out.writeFloat(flavorGrams_3);
        out.writeFloat(flavorPercent_3);
        out.writeString(flavorName_3);
    }

    public static final Parcelable.Creator<Liquid> CREATOR = new Parcelable.Creator<Liquid>() {
        public Liquid createFromParcel(Parcel in) {
            return new Liquid(in);
        }

        public Liquid[] newArray(int size) {
            return new Liquid[size];
        }
    };


    public Liquid(Parcel in) {
        name = in.readString();
        nicotineJuiceMl = in.readFloat();
        nicotineJuiceGrams = in.readFloat();
        nicotineJuicePercent = in.readFloat();
        propyleneGlycolMl = in.readFloat();
        propyleneGlycolGrams = in.readFloat();
        propyleneGlycolPercent = in.readFloat();
        vegetableGlycerinMl = in.readFloat();
        vegetableGlycerinGrams = in.readFloat();
        vegetableGlycerinPercent = in.readFloat();
        waterMl = in.readFloat();
        waterGrams = in.readFloat();
        waterPercent = in.readFloat();
        flavorMl_1 = in.readFloat();
        flavorGrams_1 = in.readFloat();
        flavorPercent_1 = in.readFloat();
        flavorName_1 = in.readString();
        flavorMl_2 = in.readFloat();
        flavorGrams_2 = in.readFloat();
        flavorPercent_2 = in.readFloat();
        flavorName_2 = in.readString();
        flavorMl_3 = in.readFloat();
        flavorGrams_3 = in.readFloat();
        flavorPercent_3 = in.readFloat();
        flavorName_3 = in.readString();
    }
}
