package vape.val.liquid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.rey.material.widget.Slider;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
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

    private AdView mAdView;
    private HashMap<String, Float> result;


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
    com.rey.material.widget.Slider slider_add_flavor_1;
    com.rey.material.widget.Slider slider_add_flavor_2;
    com.rey.material.widget.Slider slider_add_flavor_3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        flavorOnClick();

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                create();
            }
        });

        mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .build();
        mAdView.loadAd(adRequest);


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

        result = new HashMap<String, Float>();


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


        float nicotineJuiceMl = rounded((valueDesiredStrength / valueNicotineStrength * valueAmountToMake * 100) / 100, 2);
        float nicotineGrams = rounded((float) (100 * (valuePgNicotine / 100 * nicotineJuiceMl * 1.036 + valueVgNicotine / 100 * nicotineJuiceMl * 1.261)) / 100, 2);
        float nicotinePercent = rounded((float) ((nicotineJuiceMl / valueAmountToMake * 1E4) / 100), 1);
        result.put("nicotineJuiceMl", nicotineJuiceMl);
        result.put("nicotineGrams", nicotineGrams);
        result.put("nicotinePercent", nicotinePercent);


        float flavorMl = (valueFlavour_1 + valueFlavour_2 + valueFlavour_3) / 100 * valueAmountToMake;
        float wvml = (valueWater / 100 * valueAmountToMake * 100) / 100;
        float pgMl = rounded((100 * (valueDesiredPg / 100 * (valueAmountToMake - wvml - flavorMl) - valuePgNicotine / 100 * nicotineJuiceMl - 0)) / 100, 2);
        float pgGrams = rounded((float) ((1.036 * pgMl * 100) / 100), 2);
        float pgPercent = rounded((float) ((pgMl / valueAmountToMake * 1E4) / 100), 1);
        result.put("pgMl", pgMl);
        result.put("pgGrams", pgGrams);
        result.put("pgPercent", pgPercent);


        float vgMl = rounded((100 * (valueDesiredVg / 100 * (valueAmountToMake - wvml - flavorMl) - valueVgNicotine / 100 * nicotineJuiceMl - 0)) / 100, 2);
        float vgGrams = rounded((float) ((126.1 * vgMl) / 100), 2);
        float vgPercent = rounded(Math.round(vgMl / valueAmountToMake * 1E4) / 100, 1);
        result.put("vgMl", vgMl);
        result.put("vgGrams", vgGrams);
        result.put("vgPercent", vgPercent);


        float waterDrops = rounded((valueWater / 100 * valueAmountToMake * 100) / 100, 2);
        float waterGrams = rounded((float) ((93.8 * wvml) / 100), 2);
        float waterPercent = rounded(Math.round(wvml / valueAmountToMake * 1E4) / 100, 1);

        result.put("waterDrops", waterDrops);
        result.put("waterGrams", waterGrams);
        result.put("waterPercent", waterPercent);

        float flavorMl_1 = rounded((float) ((valueAmountToMake / 100 * valueFlavour_1 * 1E3) / 1E3), 2);
        float flavorDrop_1 = rounded((float) (flavorMl_1 / 1.02), 2);
        float flavorPercent_1 = rounded((float) ((flavorMl_1 / valueAmountToMake * 1E4) / 100), 1);
        result.put("flavorMl_1", flavorMl_1);
        result.put("flavorDrop_1", flavorDrop_1);
        result.put("flavorPercent_1", flavorPercent_1);

        float flavorMl_2 = rounded((float) ((valueAmountToMake / 100 * valueFlavour_2 * 1E3) / 1E3), 2);
        float flavorDrop_2 = rounded((float) (flavorMl_2 / 1.02), 2);
        float flavorPercent_2 = rounded((float) ((flavorMl_2 / valueAmountToMake * 1E4) / 100), 1);
        result.put("flavorMl_2", flavorMl_2);
        result.put("flavorDrop_2", flavorDrop_2);
        result.put("flavorPercent_2", flavorPercent_2);


        float flavorMl_3 = rounded((float) ((valueAmountToMake / 100 * valueFlavour_3 * 1E3) / 1E3), 2);
        float flavorDrop_3 = rounded((float) (flavorMl_3 / 1.02), 2);
        float flavorPercent_3 = rounded((float) ((flavorMl_3 / valueAmountToMake * 1E4) / 100), 1);
        result.put("flavorMl_3", flavorMl_3);
        result.put("flavorDrop_3", flavorDrop_3);
        result.put("flavorPercent_3", flavorPercent_3);

        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra("resultMap", result);
        startActivity(intent);
    }

    private float rounded(float input, int umberOfCharacters) {
        return new BigDecimal(String.valueOf(input)).setScale(umberOfCharacters, RoundingMode.HALF_UP).floatValue();
    }

    private float getFloatFromField(TextView textView) {
        return Float.valueOf(textView.getText().toString());
    }

    private void init() {

        create = (Button) findViewById(R.id.create);
        amount_to_make = (TextView) findViewById(R.id.amount_to_make_int);
        desired_strength = (TextView) findViewById(R.id.desired_strength_int);
        nicotine_strength = (TextView) findViewById(R.id.nicotine_strength_int);

        water_vodka_pga = (TextView) findViewById(R.id.water_vodka_pga);
        water_vodka_pga_slider = (Slider) findViewById(R.id.slider_water_vodka_pga);

        desired_pg_text = (TextView) findViewById(R.id.desired_pg_text);
        desired_pg_slider = (Slider) findViewById(R.id.desired_pg_slider);

        desired_vg_text = (TextView) findViewById(R.id.desired_vg_text);
        desired_vg_slider = (Slider) findViewById(R.id.desired_vg_slider);

        pg_content_of_nicotine = (TextView) findViewById(R.id.pg_content_of_nicotine);
        pg_content_of_nicotine_slider = (Slider) findViewById(R.id.pg_content_of_nicotine_slider);

        vg_content_of_nicotine = (TextView) findViewById(R.id.vg_content_of_nicotine);
        vg_content_of_nicotine_slider = (Slider) findViewById(R.id.vg_content_of_nicotine_int);

        count_add_flavor_1 = (TextView) findViewById(R.id.count_add_flavor_1);
        count_add_flavor_2 = (TextView) findViewById(R.id.count_add_flavor_2);
        count_add_flavor_3 = (TextView) findViewById(R.id.count_add_flavor_3);
        slider_add_flavor_1 = (Slider) findViewById(R.id.slider_add_flavor_1);
        slider_add_flavor_2 = (Slider) findViewById(R.id.slider_add_flavor_2);
        slider_add_flavor_3 = (Slider) findViewById(R.id.slider_add_flavor_3);


        addFlavour = (Button) findViewById(R.id.add_flavor);
        flavorLayout_1 = (LinearLayout) findViewById(R.id.add_flavor_layout_1);
        flavor_slider_1 = (Slider) findViewById(R.id.slider_add_flavor_1);
        flavorLayout_2 = (LinearLayout) findViewById(R.id.add_flavor_layout_2);
        flavor_slider_2 = (Slider) findViewById(R.id.slider_add_flavor_2);
        flavorLayout_3 = (LinearLayout) findViewById(R.id.add_flavor_layout_3);
        flavor_slider_3 = (Slider) findViewById(R.id.slider_add_flavor_3);


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
