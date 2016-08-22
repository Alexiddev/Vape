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
import android.widget.Toast;

import com.rey.material.widget.Spinner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import vape.val.liquid.R;
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



    }

    private void init(View view) {
     //   button = (Button) view.findViewById(R.id.button);

        initSpinner(view, spinnerWireDiam, R.id.spinnerWareDiam, R.array.wire_diam);
        initSpinner(view, spinnerCoilDiam, R.id.spinnerCoilDiam, R.array.coil_diam);
        initSpinner(view, spinnerTurns, R.id.spinnerTurns, R.array.numbers_off_turns);
        initSpinner(view, spinnerLegs, R.id.spinnerLegs, R.array.legs_length);
        initSpinner(view, spinnerTypeWire, R.id.spinnerTypeWire, R.array.type_wire);
    }

    private void initSpinner(View view, Spinner spinner, int idSpinner, int arrayId){
        spinner = (Spinner) view.findViewById(idSpinner);
        String arr[] = getResources().getStringArray(arrayId);
        List<String> countList=  Arrays.asList(arr);
        ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, countList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        final Spinner finalSpinner = spinner;
        spinner.setOnItemSelectedListener((parent, view1, position, id) ->
                Toast.makeText(getActivity(), "" + finalSpinner.getSelectedItem(), Toast.LENGTH_SHORT).show());
    }

}



