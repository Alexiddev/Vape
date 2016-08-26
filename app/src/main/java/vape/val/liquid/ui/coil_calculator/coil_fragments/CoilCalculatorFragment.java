package vape.val.liquid.ui.coil_calculator.coil_fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.rey.material.widget.RadioButton;
import com.rey.material.widget.Slider;
import com.rey.material.widget.Spinner;
import com.rey.material.widget.Switch;

import java.sql.SQLException;
import java.util.List;

import io.github.yavski.fabspeeddial.FabSpeedDial;
import io.github.yavski.fabspeeddial.SimpleMenuListenerAdapter;
import vape.val.liquid.R;
import vape.val.liquid.database.HelperFactory;
import vape.val.liquid.model.coil.Coil;
import vape.val.liquid.model.coil.CoilDiam;
import vape.val.liquid.model.coil.LegsLength;
import vape.val.liquid.model.coil.ListOfItems;
import vape.val.liquid.model.coil.Turns;
import vape.val.liquid.model.coil.TypeWire;
import vape.val.liquid.model.coil.WireDiam;
import vape.val.liquid.util.Util;


/**
 * Created by v.aleksandrenko on 18.08.2016.
 */
public class CoilCalculatorFragment extends Fragment implements View.OnClickListener {


    EditText inputName;
    View coilFragmentView;
    com.rey.material.widget.Spinner spinnerWireDiam;
    com.rey.material.widget.Spinner spinnerCoilDiam;
    com.rey.material.widget.Spinner spinnerTurns;
    com.rey.material.widget.Spinner spinnerLegs;
    com.rey.material.widget.Spinner spinnerTypeWire;
    com.rey.material.widget.Spinner spinnerWindingDiam;
    com.rey.material.widget.Spinner spinnerWindingType;
    static com.rey.material.widget.Switch switchPigtail;
    static android.support.v7.widget.CardView windingLayout;
    Slider battery_slider;
    android.widget.TextView battery_value;
    Spinner coilDiamSpinner;
    Spinner legLengthmSpinner;
    Spinner turnsSpinner;
    Spinner typeWireSpinner;
    Spinner wireDiamSpinner;
    Spinner windingDiamSpinner;
    Spinner windingTypeSpinner;
    ScrollView scrollView;
    TextView power;
    TextView recommendedPower;
    TextView resistance;
    TextView lengthWire;
    TextView current;
    TextView surfacePower;
    TextView lengthSpiral;
    TableRow wiresTable;
    FabSpeedDial fabSpeedDial;

    Coil coil;

    private RadioButton mBtnCurrentWireRadio;
    private RadioButton mBtnCurrentSpiralRadio;
    private RadioButton mBtnCurrentSpiralTypeRadio;
    public static int wires = 1;
    public static int spirals = 1;
    public static int spirals_type = 2;
    TableRow spiralsTable;
    TableRow typeSpiralsTable;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        coilFragmentView = inflater.inflate(R.layout.coil_fragment, container, false);


        return coilFragmentView;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init(coilFragmentView);
        battery_slider.setOnPositionChangeListener((view1, fromUser, oldPos, newPos, oldValue, newValue) -> {
            String batteryValue = (float) newValue / 1000 + " v";
            battery_value.setText(batteryValue);
            calculateCoil();
            switchPigtail.setOnClickListener(view -> calculateCoil());
        });

        fabSpeedDial.setMenuListener(new SimpleMenuListenerAdapter() {
            @Override
            public boolean onMenuItemSelected(MenuItem menuItem) {
                inputName(menuItem.getItemId());
                return false;
            }
        });

        calculateCoil();
    }

    public void calculateCoil() {

        coil = new Coil();

        double numberOfWires = wires;
        coil.setNumberOfWires(numberOfWires);

        double pigtail = switchPigtail.isChecked() ? 1.0 : 0.0;
        coil.setPigtail(pigtail);

        double numberOfSpirals = spirals;
        coil.setNumberOfSpirals(numberOfSpirals);

        double spiralsType = spirals_type;
        String spiral = null;
        if (spiralsType == 2) spiral = "Normal";
        else if (spiralsType == 1) spiral = "Micro";
        else if (spiralsType == 3) spiral = "Clapton";
        coil.setSpiralType(spiral);

        double wireDiam = ((WireDiam) wireDiamSpinner.getSelectedItem()).getValue();
        coil.setDiameterOfSpirals(((WireDiam) wireDiamSpinner.getSelectedItem()).getName());

        double coilDiam = ((CoilDiam) coilDiamSpinner.getSelectedItem()).getValue();
        coil.setDiameterOfCoils(((CoilDiam) coilDiamSpinner.getSelectedItem()).getName());

        double turns = ((Turns) turnsSpinner.getSelectedItem()).getValue();
        coil.setNumberOfTurns(turns);

        double legsLength = ((LegsLength) legLengthmSpinner.getSelectedItem()).getValue();
        coil.setLegsLength(((LegsLength) legLengthmSpinner.getSelectedItem()).getName());

        double typeWire = ((TypeWire) typeWireSpinner.getSelectedItem()).getValue();
        coil.setTypeWire(((TypeWire) typeWireSpinner.getSelectedItem()).getName());

        double windingDiam = ((WireDiam) windingDiamSpinner.getSelectedItem()).getValue();
        coil.setWindingDiam(((WireDiam) windingDiamSpinner.getSelectedItem()).getName());

        double windingType = ((TypeWire) windingTypeSpinner.getSelectedItem()).getValue();
        coil.setWindingType(((TypeWire) windingTypeSpinner.getSelectedItem()).getName());

        double battery = (double) battery_slider.getValue() / 1000;
        coil.setBattery(battery);
        //  double werst = "ohmcorrection";

        double pi = 3.14159265359;
        double ohmCorrect = 1.1;


        double rdArea = pi * (Math.pow((wireDiam / 2), 2));
        double avDiam = wireDiam + coilDiam;
        double wireLength = (Math.sqrt(Math.pow((pi * avDiam), 2) +
                Math.pow(wireDiam * numberOfWires * spiralsType, 2))) * turns + (legsLength * 2);

        if (pigtail == 1.0 && numberOfWires >= 2) {
            wireLength = wireLength * 1.2;
        }

        double clpWireLength = Math.round(wireDiam * pi * (wireLength / windingDiam) *
                (numberOfWires / 10 * 6 + 0.4));

        double clpArea = pi * (Math.pow((windingDiam / 2), 2));
        double clpResist = (windingType * clpWireLength / clpArea / 100);

        double coilResist = (typeWire * (wireLength + (numberOfWires * wireDiam)) / rdArea / 1000) /
                (numberOfSpirals * numberOfWires);
        //     if (werst == 1) { var r_resist = r_resist * ohm_correct; }

        if (spiralsType == 3) {
            coilResist = (coilResist * clpResist) / (coilResist + clpResist);
        }

        double coilPower = Math.pow((battery), 2) / coilResist;
        double coilCurent = battery / coilResist;
        double widthSpiral = numberOfWires * (wireDiam * spiralsType) * turns;

        double surfPower = coilPower / ((pi * 2) * ((avDiam / 2) *
                ((wireDiam * 2 * (numberOfSpirals * numberOfWires * 1.8)) * turns)));

        double mmRas = (((pi * 2) * ((avDiam / 2) * ((wireDiam * 2 * (numberOfSpirals * numberOfWires * 1.8))
                * turns)))) * 0.3;

        double koef = (43 - mmRas) / 100;
        if (koef <= 0.2) {
            koef = 0.2;
        }

        double denKon = koef;
        double coilOptPower = ((pi * 2) * ((avDiam / 2) * ((wireDiam * 2 * (numberOfSpirals * numberOfWires * 1.8)) * turns))) * denKon;
        double a_resist = (typeWire * (wireLength + (numberOfWires * wireDiam)) / rdArea / 1000) / (numberOfWires);
        //   if (c10 == 1) { var a_resist = a_resist * ohm_correct; }


        coil.setPower(Util.rounded(coilPower, 2));
        coil.setRecommendedPower(Util.rounded(coilOptPower, 2));
        coil.setResistance(Util.rounded(coilResist, 2));
        coil.setLengthOfWire(Util.rounded(wireLength, 2));
        coil.setCurrent(Util.rounded(coilCurent, 2));
        coil.setSurfacePower(Util.rounded(surfPower, 2));
        coil.setLengthOfSpiral(Util.rounded(widthSpiral, 2));
        setValueCoil(coil);
    }


    private void setListenerForTableRow(TableRow table) {
        int c = table.getChildCount();
        for (int i = 0; i < c; i++) {
            final View v = table.getChildAt(i);
            if (v instanceof RadioButton) {
                v.setOnClickListener(this);
            }
        }
    }

    public static void pigtailCheck(int wires) {
        if (wires == 1) {
            switchPigtail.setChecked(false);
            switchPigtail.setEnabled(false);
        } else switchPigtail.setEnabled(true);
    }


    public static void windingCheck(int types) {
        if (types == 3) {
            windingLayout.setVisibility(View.VISIBLE);
        } else windingLayout.setVisibility(View.INVISIBLE);
    }


    public void setValueCoil(Coil coil) {
        setValueFromCoil(power, coil.getPower() + getResources().getString(R.string.watt));
        setValueFromCoil(recommendedPower, coil.getRecommendedPower() + getResources().getString(R.string.watt));
        setValueFromCoil(resistance, coil.getResistance() + " Ω");
        setValueFromCoil(lengthWire, coil.getLengthOfWire() + getResources().getString(R.string.mmx) +
                (coil.getNumberOfWires() * coil.getNumberOfSpirals()));
        setValueFromCoil(current, coil.getCurrent() + getResources().getString(R.string.amp));
        setValueFromCoil(surfacePower, coil.getSurfacePower() + getResources().getString(R.string.wmm));
        setValueFromCoil(lengthSpiral, coil.getLengthOfSpiral() + getResources().getString(R.string.mm));
    }


    private void setValueFromCoil(TextView textview, String coilField) {
        textview.setText(coilField);
    }


    @Override
    public void onClick(View view) {
        final RadioButton mBtnRadio = (RadioButton) view;
        RadioButton first;
        String type = (String) mBtnRadio.getText();


        switch (((View) view.getParent()).getId()) {
            case R.id.tablerow_wires:
                if (mBtnCurrentWireRadio != null) {
                    mBtnCurrentWireRadio.setChecked(false);
                }
                first = (RadioButton) coilFragmentView.findViewById(R.id.tablerow_wires).findViewById(R.id.radio_1);
                first.setChecked(false);
                mBtnRadio.setChecked(true);
                CoilCalculatorFragment.pigtailCheck(Integer.parseInt(type));
                wires = Integer.parseInt(type);
                calculateCoil();
                mBtnCurrentWireRadio = mBtnRadio;
                break;
            case R.id.tablerow_spirals:
                if (mBtnCurrentSpiralRadio != null) {
                    mBtnCurrentSpiralRadio.setChecked(false);
                }
                first = (RadioButton) coilFragmentView.findViewById(R.id.tablerow_spirals).findViewById(R.id.radio_1);
                first.setChecked(false);
                mBtnRadio.setChecked(true);
                spirals = Integer.parseInt(type);
                calculateCoil();
                mBtnCurrentSpiralRadio = mBtnRadio;
                break;
            case R.id.tablerow_type_spirals:
                if (mBtnCurrentSpiralTypeRadio != null) {
                    mBtnCurrentSpiralTypeRadio.setChecked(false);
                }
                first = (RadioButton) coilFragmentView.findViewById(R.id.tablerow_type_spirals).findViewById(R.id.radio_1);
                first.setChecked(false);
                mBtnRadio.setChecked(true);
                if (type.equals("Normal")) type = "2";
                else if (type.equals("Micro")) type = "1";
                else if (type.equals("Clapton")) type = "3";
                spirals_type = Integer.parseInt(type);
                CoilCalculatorFragment.windingCheck(spirals_type);
                calculateCoil();
                mBtnCurrentSpiralTypeRadio = mBtnRadio;
                break;
        }

    }

    private void init(View view) {

        fabSpeedDial = (FabSpeedDial) view.findViewById(R.id.coil_menu);
        scrollView = (ScrollView) view.findViewById(R.id.scrollView);
        switchPigtail = (Switch) view.findViewById(R.id.pigtailSwitch);
        switchPigtail.setEnabled(false);
        wireDiamSpinner = initSpinner(view, spinnerWireDiam, R.id.spinnerWareDiam, ListOfItems.getWireDiams());
        coilDiamSpinner = initSpinner(view, spinnerCoilDiam, R.id.spinnerCoilDiam, ListOfItems.getCoilDiams());
        turnsSpinner = initSpinner(view, spinnerTurns, R.id.spinnerTurns, ListOfItems.getTurns());
        legLengthmSpinner = initSpinner(view, spinnerLegs, R.id.spinnerLegs, ListOfItems.getLegsLengths());
        typeWireSpinner = initSpinner(view, spinnerTypeWire, R.id.spinnerTypeWire, ListOfItems.getTypeWires());
        windingDiamSpinner = initSpinner(view, spinnerWindingDiam, R.id.winding_diam, ListOfItems.getWireDiams());
        windingTypeSpinner = initSpinner(view, spinnerWindingType, R.id.winding_type, ListOfItems.getTypeWires());
        windingLayout = (android.support.v7.widget.CardView) view.findViewById(R.id.winding_layout);
        battery_slider = (Slider) view.findViewById(R.id.slider_battery);
        battery_value = (android.widget.TextView) view.findViewById(R.id.battery_text);
        wiresTable = (TableRow) view.findViewById(R.id.tablerow_wires);
        spiralsTable = (TableRow) view.findViewById(R.id.tablerow_spirals);
        typeSpiralsTable = (TableRow) view.findViewById(R.id.tablerow_type_spirals);

        power = (TextView) view.findViewById(R.id.power_value);
        recommendedPower = (TextView) view.findViewById(R.id.recommended_power_value);
        resistance = (TextView) view.findViewById(R.id.resistance_value);
        lengthWire = (TextView) view.findViewById(R.id.length_of_wire_value);
        current = (TextView) view.findViewById(R.id.current_value);
        surfacePower = (TextView) view.findViewById(R.id.surface_power_value);
        lengthSpiral = (TextView) view.findViewById(R.id.length_of_the_spiral_value);


        setListenerForTableRow(wiresTable);
        setListenerForTableRow(spiralsTable);
        setListenerForTableRow(typeSpiralsTable);
    }

    private Spinner initSpinner(View view, Spinner spinner, int idSpinner, List items) {
        spinner = (Spinner) view.findViewById(idSpinner);
        ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener((parent, view1, position, id) -> calculateCoil());
        return spinner;
    }

    private void inputName(final int itemId) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
        alertDialog.setTitle(getString(R.string.liquid_name));
        alertDialog.setMessage(getString(R.string.enter_name));

        alertDialog.setPositiveButton(getResources().getString(R.string.yes),
                (dialog, which) -> {
                    coil.setName(inputName.getText().toString());
                    switch (itemId) {
                        case R.id.action_share:
                            Util.share(coil, getActivity());
                            break;
                        case R.id.action_save:
                            try {
                                HelperFactory.getHelper().getCoilDAO().create(coil);
                                Toast.makeText(getActivity(), getString(R.string.saved), Toast.LENGTH_SHORT).show();
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }

                            break;
                    }
                });

        alertDialog.setNegativeButton(getResources().getString(R.string.no),
                (dialog, which) -> {
                    dialog.cancel();
                });

        inputName = new EditText(getActivity());
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        inputName.setLayoutParams(lp);
        alertDialog.setView(inputName);
        alertDialog.show();
    }
}



