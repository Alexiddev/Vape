package vape.val.liquid.ui.coil_calculator.base;

import android.support.v4.app.Fragment;
import android.view.View;
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



    public void setValue(Coil coil) {
        setValueFromCoil(power, coil.getPower() + getResources().getString(R.string.watt));
        setValueFromCoil(recommendedPower, coil.getRecommendedPower() + getResources().getString(R.string.watt));
        setValueFromCoil(resistance, coil.getResistance() + " Ω");
        setValueFromCoil(lengthWire, coil.getLengthOfWire() + getResources().getString(R.string.mmx) +
                (coil.getNumberOfWires() * coil.getNumberOfSpirals()));
        setValueFromCoil(current, coil.getCurrent() + getResources().getString(R.string.amp));
        setValueFromCoil(surfacePower, coil.getSurfacePower() + getResources().getString(R.string.wmm));
        setValueFromCoil(lengthSpiral, coil.getLengthOfSpiral() + getResources().getString(R.string.mm));
    }

    private void setValueFromCoil(TextView textview, double coilField) {
        textview.setText(String.valueOf(coilField));
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
    }

}
