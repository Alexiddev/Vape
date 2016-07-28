package vape.val.liquid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rey.material.widget.Slider;

public class MainActivity extends AppCompatActivity {

    private int count_flavor = 0;
    private float value_amount_to_make;
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


    TextView amount_to_make;
    TextView desired_strength;
    TextView nicotine_strength;


    Button addFlavour;
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




    }

    private void init(){


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
        value_amount_to_make = getFloatfromField(amount_to_make);
        value_desired_strength = getFloatfromField(desired_strength);
    }

    private float getFloatfromField(TextView textView){
        return Float.valueOf(textView.getText().toString());
    }
}
