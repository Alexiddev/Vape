package vape.val.liquid.ui;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.rey.material.widget.Slider;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

import vape.val.liquid.R;
import vape.val.liquid.database.SQLiteHelper;
import vape.val.liquid.model.Liquid;
import vape.val.liquid.util.RateThisApp;

public class CreateRecipe extends Fragment {
    //http://e-liquid-recipes.com/create
    //http://www.danstools.com/javascript-beautify/
    private int count_flavor;
    private float valueAmountToMake;
    private float valueDesiredStrength;
    private float valueWater;
    private float valueDesiredPg;
    private float valueDesiredVg;
    private float valueNicotineStrength;
    private float valuePgNicotine;
    private float valueVgNicotine;
    private float valueFlavour_1;
    private float valueFlavour_2;
    private float valueFlavour_3;
    private Liquid liquid;

    private String flavorName1;
    private String flavorName2;
    private String flavorName3;

    private AdView mAdView;


    TextView amount_to_make;
    TextView desired_strength;
    TextView nicotine_strength;

    Button addFlavour;
    Button create;
    LinearLayout flavorLayout_1;
    com.rey.material.widget.Slider flavor_slider_1;
    LinearLayout flavorLayout_2;
    com.rey.material.widget.Slider flavor_slider_2;
    LinearLayout flavorLayout_3;
    com.rey.material.widget.Slider flavor_slider_3;

    TextView water_vodka_pga;
    com.rey.material.widget.Slider water_vodka_pga_slider;
    TextView desired_pg_text;
    com.rey.material.widget.Slider desired_pg_slider;
    TextView desired_vg_text;
    com.rey.material.widget.Slider desired_vg_slider;

    TextView pg_content_of_nicotine;
    com.rey.material.widget.Slider pg_content_of_nicotine_slider;

    TextView vg_content_of_nicotine;
    com.rey.material.widget.Slider vg_content_of_nicotine_slider;

    TextView count_add_flavor_1;
    TextView count_add_flavor_2;
    TextView count_add_flavor_3;
    EditText flavourNameEditText_1;
    EditText flavourNameEditText_2;
    EditText flavourNameEditText_3;
    com.rey.material.widget.Slider slider_add_flavor_1;
    com.rey.material.widget.Slider slider_add_flavor_2;
    com.rey.material.widget.Slider slider_add_flavor_3;
    View view;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = inflater.inflate(R.layout.activity_create_recipe, container, false);


        init();
        flavorOnClick();

        RateThisApp.Config config = new RateThisApp.Config(3, 5);
        RateThisApp.init(config);
        RateThisApp.onStart(getActivity());
        RateThisApp.showRateDialogIfNeeded(getActivity());

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                create();
            }
        });





        return view;
    }


    private void flavorOnClick() {
        addFlavour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count_flavor++;
                switch (count_flavor) {
                    case 1:
                        flavorLayout_1.setVisibility(View.VISIBLE);
                        flavor_slider_1.setVisibility(View.VISIBLE);
                        break;
                    case 2:
                        flavorLayout_2.setVisibility(View.VISIBLE);
                        flavor_slider_2.setVisibility(View.VISIBLE);
                        break;
                    case 3:
                        flavorLayout_3.setVisibility(View.VISIBLE);
                        flavor_slider_3.setVisibility(View.VISIBLE);
                        addFlavour.setVisibility(View.GONE);
                        break;
                    default:
                        break;
                }
            }
        });

    }

    public void create() {

        liquid = new Liquid();


        valueAmountToMake = getFloatFromField(amount_to_make);
        valueDesiredStrength = getFloatFromField(desired_strength);
        valueWater = water_vodka_pga_slider.getValue();
        valueDesiredPg = desired_pg_slider.getValue();
        valueDesiredVg = desired_vg_slider.getValue();
        valueNicotineStrength = getFloatFromField(nicotine_strength);
        valuePgNicotine = pg_content_of_nicotine_slider.getValue();
        valueVgNicotine = vg_content_of_nicotine_slider.getValue();
        valueFlavour_1 = flavor_slider_1.getValue();
        valueFlavour_2 = flavor_slider_2.getValue();
        valueFlavour_3 = flavor_slider_3.getValue();
        flavorName1 = flavourNameEditText_1.getText().toString();
        flavorName2 = flavourNameEditText_2.getText().toString();
        flavorName3 = flavourNameEditText_3.getText().toString();
        if (flavorName1.equals("")) flavorName1 = getResources().getString(R.string.flavor_1);
        if (flavorName2.equals("")) flavorName2 = getResources().getString(R.string.flavor_2);
        if (flavorName3.equals("")) flavorName3 = getResources().getString(R.string.flavor_3);

        liquid.setName("My");


        float nicotineJuiceMl = rounded((valueDesiredStrength / valueNicotineStrength * valueAmountToMake * 100) / 100, 2);
        float nicotineGrams = rounded((float) (100 * (valuePgNicotine / 100 * nicotineJuiceMl * 1.036 + valueVgNicotine / 100 * nicotineJuiceMl * 1.261)) / 100, 2);
        float nicotinePercent = rounded((float) ((nicotineJuiceMl / valueAmountToMake * 1E4) / 100), 1);
        liquid.setNicotineJuiceMl(nicotineJuiceMl);
        liquid.setNicotineJuiceGrams(nicotineGrams);
        liquid.setNicotineJuicePercent(nicotinePercent);


        float flavorMl = (valueFlavour_1 + valueFlavour_2 + valueFlavour_3) / 100 * valueAmountToMake;
        float wvml = (valueWater / 100 * valueAmountToMake * 100) / 100;
        float pgMl = rounded((100 * (valueDesiredPg / 100 * (valueAmountToMake - wvml - flavorMl) - valuePgNicotine / 100 * nicotineJuiceMl - 0)) / 100, 2);
        float pgGrams = rounded((float) ((1.036 * pgMl * 100) / 100), 2);
        float pgPercent = rounded((float) ((pgMl / valueAmountToMake * 1E4) / 100), 1);
        liquid.setPropyleneGlycolMl(pgMl);
        liquid.setPropyleneGlycolGrams(pgGrams);
        liquid.setPropyleneGlycolPercent(pgPercent);


        float vgMl = rounded((100 * (valueDesiredVg / 100 * (valueAmountToMake - wvml - flavorMl) - valueVgNicotine / 100 * nicotineJuiceMl - 0)) / 100, 2);
        float vgGrams = rounded((float) ((126.1 * vgMl) / 100), 2);
        float vgPercent = rounded(Math.round(vgMl / valueAmountToMake * 1E4) / 100, 1);
        liquid.setVegetableGlycerinMl(vgMl);
        liquid.setVegetableGlycerinGrams(vgGrams);
        liquid.setVegetableGlycerinPercent(vgPercent);


        float waterDrops = rounded((valueWater / 100 * valueAmountToMake * 100) / 100, 2);
        float waterGrams = rounded((float) ((93.8 * wvml) / 100), 2);
        float waterPercent = rounded(Math.round(wvml / valueAmountToMake * 1E4) / 100, 1);
        liquid.setWaterMl(waterDrops);
        liquid.setWaterGrams(waterGrams);
        liquid.setWaterPercent(waterPercent);

        float flavorMl_1 = rounded((float) ((valueAmountToMake / 100 * valueFlavour_1 * 1E3) / 1E3), 2);
        float flavorDrop_1 = rounded((float) (flavorMl_1 / 1.02), 2);
        float flavorPercent_1 = rounded((float) ((flavorMl_1 / valueAmountToMake * 1E4) / 100), 1);

        liquid.setFlavorName_1(flavorName1);
        liquid.setFlavorMl_1(flavorMl_1);
        liquid.setFlavorGrams_1(flavorDrop_1);
        liquid.setFlavorPercent_1(flavorPercent_1);

        float flavorMl_2 = rounded((float) ((valueAmountToMake / 100 * valueFlavour_2 * 1E3) / 1E3), 2);
        float flavorDrop_2 = rounded((float) (flavorMl_2 / 1.02), 2);
        float flavorPercent_2 = rounded((float) ((flavorMl_2 / valueAmountToMake * 1E4) / 100), 1);
        liquid.setFlavorName_2(flavorName2);
        liquid.setFlavorMl_2(flavorMl_2);
        liquid.setFlavorGrams_2(flavorDrop_2);
        liquid.setFlavorPercent_2(flavorPercent_2);


        float flavorMl_3 = rounded((float) ((valueAmountToMake / 100 * valueFlavour_3 * 1E3) / 1E3), 2);
        float flavorDrop_3 = rounded((float) (flavorMl_3 / 1.02), 2);
        float flavorPercent_3 = rounded((float) ((flavorMl_3 / valueAmountToMake * 1E4) / 100), 1);
        liquid.setFlavorName_3(flavorName3);
        liquid.setFlavorMl_3(flavorMl_3);
        liquid.setFlavorGrams_3(flavorDrop_3);
        liquid.setFlavorPercent_3(flavorPercent_3);


        Fragment fragmentResult = new ResultActivity();
        Bundle bundle = new Bundle();
        bundle.putParcelable("liquid", liquid);
        fragmentResult.setArguments(bundle);
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.headlines_fragment, fragmentResult);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    private float rounded(float input, int umberOfCharacters) {
        return new BigDecimal(String.valueOf(input)).setScale(umberOfCharacters, RoundingMode.HALF_UP).floatValue();
    }

    private float getFloatFromField(TextView textView) {
        return Float.valueOf(textView.getText().toString());
    }

    private void init() {

        create = (Button) view.findViewById(R.id.create);
        amount_to_make = (TextView) view.findViewById(R.id.amount_to_make_int);
        desired_strength = (TextView) view.findViewById(R.id.desired_strength_int);
        nicotine_strength = (TextView) view.findViewById(R.id.nicotine_strength_int);

        water_vodka_pga = (TextView) view.findViewById(R.id.water_vodka_pga);
        water_vodka_pga_slider = (Slider) view.findViewById(R.id.slider_water_vodka_pga);

        desired_pg_text = (TextView) view.findViewById(R.id.desired_pg_text);
        desired_pg_slider = (Slider) view.findViewById(R.id.desired_pg_slider);

        desired_vg_text = (TextView) view.findViewById(R.id.desired_vg_text);
        desired_vg_slider = (Slider) view.findViewById(R.id.desired_vg_slider);

        pg_content_of_nicotine = (TextView) view.findViewById(R.id.pg_content_of_nicotine);
        pg_content_of_nicotine_slider = (Slider) view.findViewById(R.id.pg_content_of_nicotine_slider);

        vg_content_of_nicotine = (TextView) view.findViewById(R.id.vg_content_of_nicotine);
        vg_content_of_nicotine_slider = (Slider) view.findViewById(R.id.vg_content_of_nicotine_int);

        count_add_flavor_1 = (TextView) view.findViewById(R.id.count_add_flavor_1);
        count_add_flavor_2 = (TextView) view.findViewById(R.id.count_add_flavor_2);
        count_add_flavor_3 = (TextView) view.findViewById(R.id.count_add_flavor_3);
        slider_add_flavor_1 = (Slider) view.findViewById(R.id.slider_add_flavor_1);
        slider_add_flavor_2 = (Slider) view.findViewById(R.id.slider_add_flavor_2);
        slider_add_flavor_3 = (Slider) view.findViewById(R.id.slider_add_flavor_3);


        addFlavour = (Button) view.findViewById(R.id.add_flavor);
        flavorLayout_1 = (LinearLayout) view.findViewById(R.id.add_flavor_layout_1);
        flavor_slider_1 = (Slider) view.findViewById(R.id.slider_add_flavor_1);
        flavorLayout_2 = (LinearLayout) view.findViewById(R.id.add_flavor_layout_2);
        flavor_slider_2 = (Slider) view.findViewById(R.id.slider_add_flavor_2);
        flavorLayout_3 = (LinearLayout) view.findViewById(R.id.add_flavor_layout_3);
        flavor_slider_3 = (Slider) view.findViewById(R.id.slider_add_flavor_3);

        flavourNameEditText_1 = (EditText) view.findViewById(R.id.name_add_flavor_1);
        flavourNameEditText_2 = (EditText) view.findViewById(R.id.name_add_flavor_2);
        flavourNameEditText_3 = (EditText) view.findViewById(R.id.name_add_flavor_3);


        onPositionChangedForTwoElements(water_vodka_pga_slider, water_vodka_pga, null, null);
        onPositionChangedForTwoElements(slider_add_flavor_1, count_add_flavor_1, null, null);
        onPositionChangedForTwoElements(slider_add_flavor_2, count_add_flavor_2, null, null);
        onPositionChangedForTwoElements(slider_add_flavor_3, count_add_flavor_3, null, null);
        onPositionChangedForTwoElements(desired_pg_slider, desired_pg_text, desired_vg_slider, desired_vg_text);
        onPositionChangedForTwoElements(desired_vg_slider, desired_vg_text, desired_pg_slider, desired_pg_text);
        onPositionChangedForTwoElements(pg_content_of_nicotine_slider, pg_content_of_nicotine, vg_content_of_nicotine_slider, vg_content_of_nicotine);
        onPositionChangedForTwoElements(vg_content_of_nicotine_slider, vg_content_of_nicotine, pg_content_of_nicotine_slider, pg_content_of_nicotine);
    }

    private void onPositionChangedForTwoElements(Slider sliderFirst, final TextView textViewFirst, final Slider sliderSecond, final TextView textViewSecond) {
        sliderFirst.setOnPositionChangeListener(new Slider.OnPositionChangeListener() {
            @Override
            public void onPositionChanged(Slider view, boolean fromUser, float oldPos, float newPos, int oldValue, int newValue) {
                String firstElement = newValue + " %";
                textViewFirst.setText(firstElement);

                if (sliderSecond != null) {
                    sliderSecond.setValue(100 - newValue, true);
                    String secondElement = (100 - newValue) + " %";
                    textViewSecond.setText(secondElement);
                }

            }
        });
    }

}
