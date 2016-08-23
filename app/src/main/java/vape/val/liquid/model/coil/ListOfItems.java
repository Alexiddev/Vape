package vape.val.liquid.model.coil;

import android.webkit.WebView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by v.aleksandrenko on 23.08.2016.
 */
public class ListOfItems {


    public static List<WireDiam> getWireDiams(){
        List<WireDiam> wireDiams = new ArrayList<>();
        wireDiams.add(new WireDiam("0.10 mm (AWG 38)", 0.10));
        wireDiams.add(new WireDiam("0.11 mm (AWG 37)", 0.11));
        wireDiams.add(new WireDiam("0.12 mm (AWG 36)", 0.12));
        wireDiams.add(new WireDiam("0.14 mm (AWG 35)", 0.14));
        wireDiams.add(new WireDiam("0.15 mm", 0.15));
        wireDiams.add(new WireDiam("0.16 mm (AWG 34)", 0.16));
        wireDiams.add(new WireDiam("0.18 mm (AWG 33)", 0.18));
        wireDiams.add(new WireDiam("0.20 mm (AWG 32)", 0.20));
        wireDiams.add(new WireDiam("0.22 mm (AWG 31)", 0.22));
        wireDiams.add(new WireDiam("0.25 mm (AWG 30)", 0.25));
        wireDiams.add(new WireDiam("0.28 mm (AWG 29)", 0.28));
        wireDiams.add(new WireDiam("0.30 mm", 0.30));
        wireDiams.add(new WireDiam("0.32 mm (AWG 28)", 0.32));
        wireDiams.add(new WireDiam("0.35 mm", 0.35));
        wireDiams.add(new WireDiam("0.36 mm (AWG 27)", 0.36));
        wireDiams.add(new WireDiam("0.38 mm", 0.38));
        wireDiams.add(new WireDiam("0.40 mm (AWG 26)", 0.40));
        wireDiams.add(new WireDiam("0.45 mm (AWG 25)", 0.45));
        wireDiams.add(new WireDiam("0.50 mm", 0.50));
        wireDiams.add(new WireDiam("0.51 mm (AWG 24)", 0.51));
        wireDiams.add(new WireDiam("0.57 mm (AWG 23)", 0.57));
        wireDiams.add(new WireDiam("0.60 mm", 0.60));
        wireDiams.add(new WireDiam("0.63 mm", 0.63));
        wireDiams.add(new WireDiam("0.64 mm (AWG 22)", 0.64));
        wireDiams.add(new WireDiam("0.70 mm", 0.70));
        wireDiams.add(new WireDiam("0.71 mm (AWG 21)", 0.71));
        wireDiams.add(new WireDiam("0.80 mm", 0.80));
        wireDiams.add(new WireDiam("0.81 mm (AWG 20)", 0.81));
        wireDiams.add(new WireDiam("0.90 mm", 0.90));
        wireDiams.add(new WireDiam("0.91 mm (AWG 19)", 0.91));
        wireDiams.add(new WireDiam("1.0 mm", 1.0));
        wireDiams.add(new WireDiam("1.02 mm (AWG 18)", 1.02));
        return wireDiams;
    }

    public static List<CoilDiam> getCoilDiams(){
        List<CoilDiam> coilDiams = new ArrayList<>();
        coilDiams.add(new CoilDiam("0.5 mm", 0.5));
        coilDiams.add(new CoilDiam("0.6 mm", 0.6));
        coilDiams.add(new CoilDiam("0.7 mm", 0.7));
        coilDiams.add(new CoilDiam("0.8 mm", 0.8));
        coilDiams.add(new CoilDiam("0.9 mm", 0.9));
        coilDiams.add(new CoilDiam("1 mm", 1.0));
        coilDiams.add(new CoilDiam("1.25 mm", 1.25));
        coilDiams.add(new CoilDiam("1.5 mm", 1.5));
        coilDiams.add(new CoilDiam("1.75 mm", 1.75));
        coilDiams.add(new CoilDiam("2 mm", 2.0));
        coilDiams.add(new CoilDiam("2.25 mm", 2.25));
        coilDiams.add(new CoilDiam("2.5 mm", 2.5));
        coilDiams.add(new CoilDiam("2.75 mm", 2.75));
        coilDiams.add(new CoilDiam("3 mm", 3.0));
        coilDiams.add(new CoilDiam("3.5 mm", 3.5));
        coilDiams.add(new CoilDiam("4 mm", 4.0));
        return coilDiams;
    }

    public static List<Turns> getTurns(){
        List<Turns> turnses = new ArrayList<>();
        turnses.add(new Turns("1", 1.0));
        turnses.add(new Turns("1.5", 1.5));
        turnses.add(new Turns("2", 2.0));
        turnses.add(new Turns("2.5", 2.5));
        turnses.add(new Turns("3", 3.0));
        turnses.add(new Turns("3.5", 3.5));
        turnses.add(new Turns("4", 4.0));
        turnses.add(new Turns("4.5", 4.5));
        turnses.add(new Turns("5", 5.0));
        turnses.add(new Turns("5.5", 5.5));
        turnses.add(new Turns("6", 6.0));
        turnses.add(new Turns("6.5", 6.5));
        turnses.add(new Turns("7", 7.0));
        turnses.add(new Turns("7.5", 7.5));
        turnses.add(new Turns("8", 8.0));
        turnses.add(new Turns("8.5", 8.5));
        turnses.add(new Turns("9", 9.0));
        turnses.add(new Turns("9.5", 9.5));
        turnses.add(new Turns("10", 10.0));
        turnses.add(new Turns("10.5", 10.5));
        turnses.add(new Turns("11", 11.0));
        turnses.add(new Turns("11.5", 11.5));
        turnses.add(new Turns("12", 12.0));
        turnses.add(new Turns("12.5", 12.5));
        turnses.add(new Turns("13", 13.0));
        turnses.add(new Turns("13.5", 13.5));
        turnses.add(new Turns("14", 14.0));
        turnses.add(new Turns("14.5", 14.5));
        turnses.add(new Turns("15", 15.0));
        turnses.add(new Turns("15.5", 15.5));
        turnses.add(new Turns("16", 16.0));
        turnses.add(new Turns("16.5", 16.5));
        turnses.add(new Turns("17", 17.0));
        turnses.add(new Turns("17.5", 17.5));
        turnses.add(new Turns("18", 18.0));
        turnses.add(new Turns("18.5", 18.5));
        turnses.add(new Turns("19", 19.0));
        turnses.add(new Turns("19.5", 19.5));
        turnses.add(new Turns("20", 20.0));
        return turnses;
    }

    public static List<LegsLength> getLegsLengths(){
        List<LegsLength> legsLengths = new ArrayList<>();
        legsLengths.add(new LegsLength("2x0 mm", 0.0));
        legsLengths.add(new LegsLength("2x0.5 mm", 0.5));
        legsLengths.add(new LegsLength("2x1 mm", 1.0));
        legsLengths.add(new LegsLength("2x1.5 mm", 1.5));
        legsLengths.add(new LegsLength("2x2 mm", 2.0));
        legsLengths.add(new LegsLength("2x2.5 mm", 2.5));
        legsLengths.add(new LegsLength("2x3 mm", 3.0));
        legsLengths.add(new LegsLength("2x4 mm", 4.0));
        legsLengths.add(new LegsLength("2x5 mm", 5.0));
        return legsLengths;
    }

    public static List<TypeWire> getTypeWires(){
        List<TypeWire> typeWires = new ArrayList<>();
        typeWires.add(new TypeWire("NiFe30", 0.24));
        typeWires.add(new TypeWire("Dicodes Resist", 0.28));
        typeWires.add(new TypeWire("NiFe48", 0.36));
        typeWires.add(new TypeWire("Titanium (Ti)", 0.42));
        typeWires.add(new TypeWire("SS AISI 316", 0.74));
        typeWires.add(new TypeWire("SS AISI 304", 0.8));
        typeWires.add(new TypeWire("Nichrome Ni80", 1.08));
        typeWires.add(new TypeWire("Nichrome Ni60", 1.11));
        typeWires.add(new TypeWire("Kanthal 1.39", 1.39));
        typeWires.add(new TypeWire("Kanthal D", 1.35));
        typeWires.add(new TypeWire("Kanthal A1", 1.45));
        return typeWires;
    }

}
