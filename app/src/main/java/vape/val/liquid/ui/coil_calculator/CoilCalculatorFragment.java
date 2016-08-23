package vape.val.liquid.ui.coil_calculator;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Toast;

import com.rey.material.widget.Slider;
import com.rey.material.widget.Spinner;
import com.rey.material.widget.Switch;
import com.rey.material.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import vape.val.liquid.R;
import vape.val.liquid.model.coil.CoilDiam;
import vape.val.liquid.model.coil.LegsLength;
import vape.val.liquid.model.coil.ListOfItems;
import vape.val.liquid.model.coil.Turns;
import vape.val.liquid.model.coil.TypeWire;
import vape.val.liquid.model.coil.WireDiam;
import vape.val.liquid.util.RadioButtonWithTableLayout;


/**
 * Created by v.aleksandrenko on 18.08.2016.
 */
public class CoilCalculatorFragment extends Fragment {

    View coilFragmentView;
    Button button;
    com.rey.material.widget.Spinner spinnerWireDiam;
    com.rey.material.widget.Spinner spinnerCoilDiam;
    com.rey.material.widget.Spinner spinnerTurns;
    com.rey.material.widget.Spinner spinnerLegs;
    com.rey.material.widget.Spinner spinnerTypeWire;
    com.rey.material.widget.Spinner spinnerWindingDiam;
    com.rey.material.widget.Spinner spinnerWindingType;
    static com.rey.material.widget.Switch switchPigtail;
    static LinearLayout windingLayout;
    Slider battery_slider;
    android.widget.TextView battery_value;

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
            String batteryValue = (float) newValue/1000 + " v";
            battery_value.setText(batteryValue);
        });

        button.setOnClickListener(view -> calculateCoil());
    }

    public void calculateCoil(){
        double c1 = RadioButtonWithTableLayout.wires;
        double c2 = RadioButtonWithTableLayout.spirals;
        double c3 =  ((WireDiam) spinnerWireDiam.getSelectedItem()).getValue();
        double c4 =  ((CoilDiam) spinnerCoilDiam.getSelectedItem()).getValue();
        double c5 = ((Turns) spinnerTurns.getSelectedItem()).getValue();
        double c6 = (float) battery_slider.getValue()/1000;

        double c61 = 1; // СЂРµР·РµСЂРІ

        double c7 = ((LegsLength) spinnerLegs.getSelectedItem()).getValue();
        double c8 = ((TypeWire) spinnerTypeWire.getSelectedItem()).getValue();
      //  double c9 = Number($("input[name=ct]:checked").val());
      //  double c10 = Number(document.getElementById("ohmcorrection").checked);
        boolean c11 = switchPigtail.isChecked();
        double pi = 3.14159265359;
        double ohm_correct = 1.1;

        double cldiam = ((WireDiam) spinnerWindingDiam.getSelectedItem()).getValue();
        double cltype = ((TypeWire) spinnerWindingType.getSelectedItem()).getValue();


        Log.i("", " cvcvcv " + c1 + " " + c2 + " " + c3 + " " + c4 + " " + c5 + " " + c6 + " " + c7 + " " + c8 + " " +  " " + c11 + " " + cldiam + " " +cltype);
    }

    private void init(View view) {
         button = (Button) view.findViewById(R.id.button);
        switchPigtail = (Switch) view.findViewById(R.id.pigtailSwitch);
        switchPigtail.setEnabled(false);
        initSpinner(view, spinnerWireDiam, R.id.spinnerWareDiam, ListOfItems.getWireDiams());
        initSpinner(view, spinnerCoilDiam, R.id.spinnerCoilDiam, ListOfItems.getCoilDiams());
        initSpinner(view, spinnerTurns, R.id.spinnerTurns, ListOfItems.getTurns());
        initSpinner(view, spinnerLegs, R.id.spinnerLegs, ListOfItems.getLegsLengths());
        initSpinner(view, spinnerTypeWire, R.id.spinnerTypeWire, ListOfItems.getTypeWires());
        initSpinner(view, spinnerWindingDiam, R.id.winding_diam, ListOfItems.getWireDiams());
        initSpinner(view, spinnerWindingType, R.id.winding_type, ListOfItems.getTypeWires());
        windingLayout = (LinearLayout) view.findViewById(R.id.winding_layout);
        battery_slider = (Slider) view.findViewById(R.id.slider_battery);
        battery_value = (android.widget.TextView) view.findViewById(R.id.battery_text);


//        switchPigtail.setOnClickListener(view1 -> {
//            Toast.makeText(getActivity(), RadioButtonWithTableLayout.wires + ""
//                    + RadioButtonWithTableLayout.spirals + ""
//                    + RadioButtonWithTableLayout.spirals_type + "", Toast.LENGTH_SHORT).show();
//        });
    }

    private void initSpinner(View view, Spinner spinner, int idSpinner, List items) {
        spinner = (Spinner) view.findViewById(idSpinner);
        ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        final Spinner finalSpinner = spinner;
        spinner.setOnItemSelectedListener((parent, view1, position, id) ->
                Toast.makeText(getActivity(), "" + finalSpinner.getSelectedItem(), Toast.LENGTH_SHORT).show());
    }

    public static void pigtailCheck(int wires) {
        if (wires == 1){
            switchPigtail.setChecked(false);
            switchPigtail.setEnabled(false);
        }else switchPigtail.setEnabled(true);
    }

    public static void windingCheck(int types) {
        if (types == 3){
            windingLayout.setVisibility(View.VISIBLE);
        }else windingLayout.setVisibility(View.INVISIBLE);
    }


}



