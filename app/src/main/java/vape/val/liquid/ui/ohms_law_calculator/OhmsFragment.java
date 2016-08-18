package vape.val.liquid.ui.ohms_law_calculator;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import vape.val.liquid.R;


/**
 * Created by v.aleksandrenko on 18.08.2016.
 */
public class OhmsFragment extends Fragment {

    View ohmsFragmentView;
    EditText ohms_text;
    EditText amps_text;
    EditText watts_text;
    EditText vplts_text;
    Button calculate;

    private float volts;
    private float ohms;
    private float amps;
    private float watts;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

       return ohmsFragmentView = inflater.inflate(R.layout.law_fragment, container, false);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init(ohmsFragmentView);
        calculate.setOnClickListener(view -> vapeCalculator());

    }

    private void init(View view){

        ohms_text = (EditText) view.findViewById(R.id.edit_omhs);
        amps_text = (EditText) view.findViewById(R.id.edit_amps);
        watts_text = (EditText) view.findViewById(R.id.edit_watts);
        vplts_text = (EditText) view.findViewById(R.id.edit_volts);
        calculate = (Button) view.findViewById(R.id.calculate);
    }

    private void vapeCalculator(){


      volts = getFloat(volts, vplts_text);
      amps = getFloat(amps, amps_text);
      watts =  getFloat(watts, watts_text);
      ohms = getFloat(ohms, ohms_text);


      if (volts > 0 && ohms > 0) { amps =volts/ohms; watts=volts*amps;}
      if (volts > 0 && amps > 0) {ohms =volts/amps; watts =volts*amps;}
      if (volts > 0 && watts > 0){amps =watts/volts; ohms=volts/amps;}
      if (ohms > 0 && amps > 0)  { volts =amps*ohms; watts=amps*amps*ohms;}
      if (amps > 0 && watts > 0) { volts =watts/amps; ohms=volts/amps;}
      if (ohms > 0 && watts > 0) { amps= (float) Math.sqrt((watts/ohms)); volts=amps*ohms;}

        ohms_text.setText(Float.toString(ohms));
        amps_text.setText(Float.toString(amps));
        watts_text.setText(Float.toString(watts));
        vplts_text.setText(Float.toString(volts));

    }

    private float getFloat(float f, EditText view){
        try {
         return f = Float.parseFloat(String.valueOf(view.getText()));
        }catch (NumberFormatException e){
            return f = 0.0f;
        }
    }
}



