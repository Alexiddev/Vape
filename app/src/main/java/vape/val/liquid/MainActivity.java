package vape.val.liquid;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
import java.util.Map;

public class MainActivity extends AppCompatActivity {
//http://e-liquid-recipes.com/create
    //http://www.danstools.com/javascript-beautify/
    private int count_flavor = 0;
    private float valueAmountToMake;
    private float value_desired_strength;
    private float value_water_vodka_pga;
    private float value_desired_pg;
    private float value_desired_vg;
    private float value_nicotine_sterngth;
    private float value_pg_content_of_nicotine;
    private float value_vg_content_of_nicotine;
    private float value_falvour_1;
    private float value_falvour_2;
    private float value_falvour_3;

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

    private void init(){

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

    private void onPositionChangedForTwoElements(Slider sliderFirst, final TextView textViewFirst, final Slider sliderSecond, final TextView textViewSecond ){
        sliderFirst.setOnPositionChangeListener(new Slider.OnPositionChangeListener() {
            @Override
            public void onPositionChanged(Slider view, boolean fromUser, float oldPos, float newPos, int oldValue, int newValue) {
                String firstElement = newValue + " %";
                textViewFirst.setText(firstElement);

                if (sliderSecond != null){
                    sliderSecond.setValue(100-newValue, true);
                    String secondElement = (100-newValue) + " %";
                    textViewSecond.setText(secondElement);
                }

            }
        });
    }

    private void flavorOnClick(){
        addFlavour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count_flavor++;
                switch (count_flavor){
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
                    default: break;
                }
            }
        });

    }

    public void create(){

        result = new HashMap<String, Float>();


        valueAmountToMake = getFloatfromField(amount_to_make);
        value_desired_strength = getFloatfromField(desired_strength);
        value_water_vodka_pga = water_vodka_pga_slider.getValue();
        value_desired_pg = desired_pg_slider.getValue();
        value_desired_vg = desired_vg_slider.getValue();
        value_nicotine_sterngth = getFloatfromField(nicotine_strength);
        value_pg_content_of_nicotine = pg_content_of_nicotine_slider.getValue();
        value_vg_content_of_nicotine = vg_content_of_nicotine_slider.getValue();
        value_falvour_1 = flavor_slider_1.getValue();
        value_falvour_2 = flavor_slider_2.getValue();
        value_falvour_3 = flavor_slider_3.getValue();

        float flml_1 = (float) ((valueAmountToMake / 100 * value_falvour_1 * 1E3) / 1E3);
        float fldr_1 = (float) (flml_1 / 1.02);
        float flper_1 = (float) ((flml_1 / valueAmountToMake * 1E4) / 100);
        flml_1 = new BigDecimal(String.valueOf(flml_1)).setScale(2, RoundingMode.HALF_UP).floatValue();
        fldr_1 = new BigDecimal(String.valueOf(fldr_1)).setScale(2, RoundingMode.HALF_UP).floatValue();
        flper_1 = new BigDecimal(String.valueOf(flper_1)).setScale(1, RoundingMode.HALF_UP).floatValue();
        result.put("flml_1", flml_1);
        result.put("fldr_1", fldr_1);
        result.put("flper_1", flper_1);

        float flml_2 = (float) ((valueAmountToMake / 100 * value_falvour_2 * 1E3) / 1E3);
        float fldr_2 = (float) (flml_2 / 1.02);
        float flper_2 = (float) ((flml_2 / valueAmountToMake * 1E4) / 100);
        flml_2 = new BigDecimal(String.valueOf(flml_2)).setScale(2, RoundingMode.HALF_UP).floatValue();
        fldr_2 = new BigDecimal(String.valueOf(fldr_2)).setScale(2, RoundingMode.HALF_UP).floatValue();
        flper_2 = new BigDecimal(String.valueOf(flper_2)).setScale(1, RoundingMode.HALF_UP).floatValue();
        result.put("flml_2", flml_2);
        result.put("fldr_2", fldr_2);
        result.put("flper_2", flper_2);


        float flml_3 = (float) ((valueAmountToMake / 100 * value_falvour_3 * 1E3) / 1E3);
        float fldr_3 = (float) (flml_3 / 1.02);
        float flper_3 = (float) ((flml_3 / valueAmountToMake * 1E4) / 100);
        flml_3 = new BigDecimal(String.valueOf(flml_3)).setScale(2, RoundingMode.HALF_UP).floatValue();
        fldr_3 = new BigDecimal(String.valueOf(fldr_3)).setScale(2, RoundingMode.HALF_UP).floatValue();
        flper_3 = new BigDecimal(String.valueOf(flper_3)).setScale(1, RoundingMode.HALF_UP).floatValue();
        result.put("flml_3", flml_3);
        result.put("fldr_3", fldr_3);
        result.put("flper_3", flper_3);


        float nicotine_juice = (value_desired_strength/value_nicotine_sterngth *valueAmountToMake *100)/100;
        float nicgrams = (float) (100  * (value_pg_content_of_nicotine / 100 * nicotine_juice * 1.036 + value_vg_content_of_nicotine / 100 * nicotine_juice * 1.261))/100;
        float nicper = (float) ((nicotine_juice / valueAmountToMake * 1E4) / 100);

        nicotine_juice = new BigDecimal(String.valueOf(nicotine_juice)).setScale(2, RoundingMode.HALF_UP).floatValue();
        nicgrams = new BigDecimal(String.valueOf(nicgrams)).setScale(2, RoundingMode.HALF_UP).floatValue();
        nicper = new BigDecimal(String.valueOf(nicper)).setScale(1, RoundingMode.HALF_UP).floatValue();

        Log.i("Main ", "all_value " + nicotine_juice + " " + nicgrams + " " + nicper);

        result.put("nicotine_juice", nicotine_juice);
        result.put("nicgrams", nicgrams);
        result.put("nicper", nicper);




        float foml = (value_falvour_1 + value_falvour_2 + value_falvour_3)/100*valueAmountToMake;
        float wvml = (value_water_vodka_pga/100*valueAmountToMake*100)/100;
        float xpgml = (100 * (value_desired_pg / 100 * (valueAmountToMake - wvml - foml) - value_pg_content_of_nicotine / 100 * nicotine_juice - 0)) / 100;
        float xpggrams = (float) ((1.036 * xpgml * 100) / 100);
        float xpgper = (float) ((xpgml / valueAmountToMake * 1E4) / 100);

        xpgml = new BigDecimal(String.valueOf(xpgml)).setScale(2, RoundingMode.HALF_UP).floatValue();
        xpggrams = new BigDecimal(String.valueOf(xpggrams)).setScale(2, RoundingMode.HALF_UP).floatValue();
        xpgper = new BigDecimal(String.valueOf(xpgper)).setScale(1, RoundingMode.HALF_UP).floatValue();

        result.put("xpgml", xpgml);
        result.put("xpggrams", xpggrams);
        result.put("xpgper", xpgper);




        Log.i("Main ", "all_value " + xpgml + " " + xpggrams + " " + xpgper);

        float   xvgml = (100 * (value_desired_vg / 100 * (valueAmountToMake - wvml - foml) - value_vg_content_of_nicotine / 100 * nicotine_juice - 0)) / 100;

        float xvggrams = (float) ((126.1 * xvgml) / 100);
        float  xvgper = Math.round(xvgml / valueAmountToMake * 1E4) / 100;

        xvgml = new BigDecimal(String.valueOf(xvgml)).setScale(2, RoundingMode.HALF_UP).floatValue();
        xvggrams = new BigDecimal(String.valueOf(xvggrams)).setScale(2, RoundingMode.HALF_UP).floatValue();
        xvgper = new BigDecimal(String.valueOf(xvgper)).setScale(1, RoundingMode.HALF_UP).floatValue();

        result.put("xvgml", xvgml);
        result.put("xvggrams", xvggrams);
        result.put("xvgper", xvgper);


        Log.i("Main ", "all_value " + xvgml + " " + xvggrams + " " + xvgper);



        float wvdrops = (value_water_vodka_pga / 100 * valueAmountToMake * 100) / 100;
        float wvgrams = (float) ((93.8 * wvml) / 100);
        float wvper = Math.round(wvml / valueAmountToMake * 1E4) / 100;

        wvdrops = new BigDecimal(String.valueOf(wvdrops)).setScale(2, RoundingMode.HALF_UP).floatValue();
        wvgrams = new BigDecimal(String.valueOf(wvgrams)).setScale(2, RoundingMode.HALF_UP).floatValue();
        wvper = new BigDecimal(String.valueOf(wvper)).setScale(1, RoundingMode.HALF_UP).floatValue();

        result.put("wvdrops", wvdrops);
        result.put("wvgrams", wvgrams);
        result.put("wvper", wvper);

        Log.i("Main ", "all_value " + wvml + " " + wvgrams + " " + wvper);



        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra("resultMap", result);
        startActivity(intent);
    }

    private float getFloatfromField(TextView textView){
        return Float.valueOf(textView.getText().toString());
    }
}
