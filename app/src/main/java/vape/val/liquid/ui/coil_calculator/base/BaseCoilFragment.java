package vape.val.liquid.ui.coil_calculator.base;

import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import vape.val.liquid.R;
import vape.val.liquid.model.coil.Coil;

/**
 * Created by alexiddev on 24.08.16.
 */
public class BaseCoilFragment extends Fragment {

    TextView power;
    TextView recommendedPower;
    TextView resistance;
    TextView lengthWire;
    TextView current;
    TextView surfacePower;
    TextView lengthSpiral;
    TextView numberOfWires;
    TextView pigtail;
    TextView numberOfSpirals;
    TextView spyralType;
    TextView diameterOfWires;
    TextView diameterOfCoil;
    TextView numberOfTurns;
    TextView legsLength;
    TextView typeWair;
    TextView battary;
    TextView windingType;
    TextView windingLength;
    LinearLayout winding;



    public void setValue(Coil coil) {
        setValueFromCoil(power, coil.getPower() + getResources().getString(R.string.watt));
        setValueFromCoil(recommendedPower, coil.getRecommendedPower() + getResources().getString(R.string.watt));
        setValueFromCoil(resistance, coil.getResistance() + " Ω");
        setValueFromCoil(lengthWire, coil.getLengthOfWire() + getResources().getString(R.string.mmx) +
                (coil.getNumberOfWires() * coil.getNumberOfSpirals()));
        setValueFromCoil(current, coil.getCurrent() + getResources().getString(R.string.amp));
        setValueFromCoil(surfacePower, coil.getSurfacePower() + getResources().getString(R.string.wmm));
        setValueFromCoil(lengthSpiral, coil.getLengthOfSpiral() + getResources().getString(R.string.mm));


        setValueFromCoil(numberOfWires, String.valueOf(coil.getNumberOfWires()));
        setValueFromCoil(pigtail, coil.isPigtail() == 0.0 ? "No" : "Yes");
        setValueFromCoil(numberOfSpirals, String.valueOf(coil.getNumberOfSpirals()));
        setValueFromCoil(spyralType, String.valueOf(coil.getSpiralType()));
        setValueFromCoil(diameterOfWires, String.valueOf(coil.getDiameterOfSpirals()));
        setValueFromCoil(diameterOfCoil, String.valueOf(coil.getDiameterOfCoils()));
        setValueFromCoil(numberOfTurns, String.valueOf(coil.getNumberOfTurns()));
        setValueFromCoil(legsLength, String.valueOf(coil.getLegsLength()));
        setValueFromCoil(typeWair, String.valueOf(coil.getTypeWire()));
        setValueFromCoil(battary, String.valueOf(coil.getBattery()));

        if (String.valueOf(coil.getSpiralType()).equals("Clapton")){
            winding.setVisibility(View.VISIBLE);
            setValueFromCoil(windingType, String.valueOf(coil.getWindingType()));
            setValueFromCoil(windingLength, String.valueOf(coil.getWindingDiam()));
        }else{
            winding.setVisibility(View.INVISIBLE);
        }
    }



    private void setValueFromCoil(TextView textview, String coilField) {
        textview.setText(coilField);
    }

    public void initFields(View view) {
        power = (TextView) view.findViewById(R.id.power_value);
        recommendedPower = (TextView) view.findViewById(R.id.recommended_power_value);
        resistance = (TextView) view.findViewById(R.id.resistance_value);
        lengthWire = (TextView) view.findViewById(R.id.length_of_wire_value);
        current = (TextView) view.findViewById(R.id.current_value);
        surfacePower = (TextView) view.findViewById(R.id.surface_power_value);
        lengthSpiral = (TextView) view.findViewById(R.id.length_of_the_spiral_value);

        numberOfWires = (TextView) view.findViewById(R.id.my_number_of_wires);
        pigtail = (TextView) view.findViewById(R.id.my_pigtails);
        numberOfSpirals = (TextView) view.findViewById(R.id.my_number_of_spirals);
        spyralType = (TextView) view.findViewById(R.id.my_spiral_type);
        diameterOfWires = (TextView) view.findViewById(R.id.my_diameter_of_wire);
        diameterOfCoil = (TextView) view.findViewById(R.id.my_the_diameter_of_the_coil);
        numberOfTurns = (TextView) view.findViewById(R.id.my_number_of_turns);
        legsLength = (TextView) view.findViewById(R.id.my_legs_length);
        typeWair = (TextView) view.findViewById(R.id.my_type_wire);
        battary = (TextView) view.findViewById(R.id.my_battery);


        winding = (LinearLayout) view.findViewById(R.id.winding);
        windingType = (TextView) view.findViewById(R.id.my_winding_type);
        windingLength = (TextView) view.findViewById(R.id.my_winding_diameter);
    }

}
