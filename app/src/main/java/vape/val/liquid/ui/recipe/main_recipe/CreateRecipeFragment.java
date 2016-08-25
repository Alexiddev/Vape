package vape.val.liquid.ui.recipe.main_recipe;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rey.material.widget.Slider;

import vape.val.liquid.R;
import vape.val.liquid.model.liquid.Liquid;
import vape.val.liquid.util.Util;

public class CreateRecipeFragment extends Fragment {
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


    TextView amount_to_make;
    TextView desired_strength;
    TextView nicotine_strength;

    Button addFlavour;
    Button create;
    android.support.v7.widget.CardView flavorLayout_1;
    com.rey.material.widget.Slider flavor_slider_1;
    android.support.v7.widget.CardView flavorLayout_2;
    com.rey.material.widget.Slider flavor_slider_2;
    android.support.v7.widget.CardView flavorLayout_3;
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
    View createRecipeFragment;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createRecipeFragment = inflater.inflate(R.layout.activity_create_recipe, container, false);

        initFields();
        flavorOnClick();

        create.setOnClickListener(view -> calculateRecipe());
        return createRecipeFragment;
    }

    private void flavorOnClick() {
        addFlavour.setOnClickListener(view -> {
            count_flavor++;
            switch (count_flavor) {
                case 1:
                    flavorLayout_1.setVisibility(View.VISIBLE);
                    break;
                case 2:
                    flavorLayout_2.setVisibility(View.VISIBLE);
                    break;
                case 3:
                    flavorLayout_3.setVisibility(View.VISIBLE);
                    addFlavour.setVisibility(View.GONE);
                    break;
                default:
                    break;
            }
        });
    }

    public void calculateRecipe() {
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


        float nicotineJuiceMl = Util.rounded((valueDesiredStrength / valueNicotineStrength * valueAmountToMake * 100) / 100, 2);
        float nicotineGrams = Util.rounded((float) (100 * (valuePgNicotine / 100 * nicotineJuiceMl * 1.036 + valueVgNicotine / 100 * nicotineJuiceMl * 1.261)) / 100, 2);
        float nicotinePercent = Util.rounded((float) ((nicotineJuiceMl / valueAmountToMake * 1E4) / 100), 1);
        liquid.setNicotineJuiceMl(nicotineJuiceMl);
        liquid.setNicotineJuiceGrams(nicotineGrams);
        liquid.setNicotineJuicePercent(nicotinePercent);


        float flavorMl = (valueFlavour_1 + valueFlavour_2 + valueFlavour_3) / 100 * valueAmountToMake;
        float wvml = (valueWater / 100 * valueAmountToMake * 100) / 100;
        float pgMl = Util.rounded((100 * (valueDesiredPg / 100 * (valueAmountToMake - wvml - flavorMl) - valuePgNicotine / 100 * nicotineJuiceMl - 0)) / 100, 2);
        float pgGrams = Util.rounded((float) ((1.036 * pgMl * 100) / 100), 2);
        float pgPercent = Util.rounded((float) ((pgMl / valueAmountToMake * 1E4) / 100), 1);
        liquid.setPropyleneGlycolMl(pgMl);
        liquid.setPropyleneGlycolGrams(pgGrams);
        liquid.setPropyleneGlycolPercent(pgPercent);


        float vgMl = Util.rounded((100 * (valueDesiredVg / 100 * (valueAmountToMake - wvml - flavorMl) - valueVgNicotine / 100 * nicotineJuiceMl - 0)) / 100, 2);
        float vgGrams = Util.rounded((float) ((126.1 * vgMl) / 100), 2);
        float vgPercent = Util.rounded(Math.round(vgMl / valueAmountToMake * 1E4) / 100, 1);
        liquid.setVegetableGlycerinMl(vgMl);
        liquid.setVegetableGlycerinGrams(vgGrams);
        liquid.setVegetableGlycerinPercent(vgPercent);


        float waterDrops = Util.rounded((valueWater / 100 * valueAmountToMake * 100) / 100, 2);
        float waterGrams = Util.rounded((float) ((93.8 * wvml) / 100), 2);
        float waterPercent = Util.rounded(Math.round(wvml / valueAmountToMake * 1E4) / 100, 1);
        liquid.setWaterMl(waterDrops);
        liquid.setWaterGrams(waterGrams);
        liquid.setWaterPercent(waterPercent);

        float flavorMl_1 = Util.rounded((float) ((valueAmountToMake / 100 * valueFlavour_1 * 1E3) / 1E3), 2);
        float flavorDrop_1 = Util.rounded((float) (flavorMl_1 / 1.02), 2);
        float flavorPercent_1 = Util.rounded((float) ((flavorMl_1 / valueAmountToMake * 1E4) / 100), 1);

        liquid.setFlavorName_1(flavorName1);
        liquid.setFlavorMl_1(flavorMl_1);
        liquid.setFlavorGrams_1(flavorDrop_1);
        liquid.setFlavorPercent_1(flavorPercent_1);

        float flavorMl_2 = Util.rounded((float) ((valueAmountToMake / 100 * valueFlavour_2 * 1E3) / 1E3), 2);
        float flavorDrop_2 = Util.rounded((float) (flavorMl_2 / 1.02), 2);
        float flavorPercent_2 = Util.rounded((float) ((flavorMl_2 / valueAmountToMake * 1E4) / 100), 1);
        liquid.setFlavorName_2(flavorName2);
        liquid.setFlavorMl_2(flavorMl_2);
        liquid.setFlavorGrams_2(flavorDrop_2);
        liquid.setFlavorPercent_2(flavorPercent_2);


        float flavorMl_3 = Util.rounded((float) ((valueAmountToMake / 100 * valueFlavour_3 * 1E3) / 1E3), 2);
        float flavorDrop_3 = Util.rounded((float) (flavorMl_3 / 1.02), 2);
        float flavorPercent_3 = Util.rounded((float) ((flavorMl_3 / valueAmountToMake * 1E4) / 100), 1);
        liquid.setFlavorName_3(flavorName3);
        liquid.setFlavorMl_3(flavorMl_3);
        liquid.setFlavorGrams_3(flavorDrop_3);
        liquid.setFlavorPercent_3(flavorPercent_3);


        Fragment fragmentResult = new ResultFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("liquid", liquid);
        fragmentResult.setArguments(bundle);
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.headlines_fragment, fragmentResult);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }


    private void initFields() {
        create = (Button) createRecipeFragment.findViewById(R.id.create);
        amount_to_make = (TextView) createRecipeFragment.findViewById(R.id.amount_to_make_int);
        desired_strength = (TextView) createRecipeFragment.findViewById(R.id.desired_strength_int);
        nicotine_strength = (TextView) createRecipeFragment.findViewById(R.id.nicotine_strength_int);

        water_vodka_pga = (TextView) createRecipeFragment.findViewById(R.id.water_vodka_pga);
        water_vodka_pga_slider = (Slider) createRecipeFragment.findViewById(R.id.slider_water_vodka_pga);

        desired_pg_text = (TextView) createRecipeFragment.findViewById(R.id.desired_pg_text);
        desired_pg_slider = (Slider) createRecipeFragment.findViewById(R.id.desired_pg_slider);

        desired_vg_text = (TextView) createRecipeFragment.findViewById(R.id.desired_vg_text);
        desired_vg_slider = (Slider) createRecipeFragment.findViewById(R.id.desired_vg_slider);

        pg_content_of_nicotine = (TextView) createRecipeFragment.findViewById(R.id.pg_content_of_nicotine);
        pg_content_of_nicotine_slider = (Slider) createRecipeFragment.findViewById(R.id.pg_content_of_nicotine_slider);

        vg_content_of_nicotine = (TextView) createRecipeFragment.findViewById(R.id.vg_content_of_nicotine);
        vg_content_of_nicotine_slider = (Slider) createRecipeFragment.findViewById(R.id.vg_content_of_nicotine_int);

        count_add_flavor_1 = (TextView) createRecipeFragment.findViewById(R.id.count_add_flavor_1);
        count_add_flavor_2 = (TextView) createRecipeFragment.findViewById(R.id.count_add_flavor_2);
        count_add_flavor_3 = (TextView) createRecipeFragment.findViewById(R.id.count_add_flavor_3);
        slider_add_flavor_1 = (Slider) createRecipeFragment.findViewById(R.id.slider_add_flavor_1);
        slider_add_flavor_2 = (Slider) createRecipeFragment.findViewById(R.id.slider_add_flavor_2);
        slider_add_flavor_3 = (Slider) createRecipeFragment.findViewById(R.id.slider_add_flavor_3);

        addFlavour = (Button) createRecipeFragment.findViewById(R.id.add_flavor);
        flavorLayout_1 = (android.support.v7.widget.CardView) createRecipeFragment.findViewById(R.id.first_flavor_card);
        flavor_slider_1 = (Slider) createRecipeFragment.findViewById(R.id.slider_add_flavor_1);
        flavorLayout_2 = (android.support.v7.widget.CardView) createRecipeFragment.findViewById(R.id.second_flavor_card);
        flavor_slider_2 = (Slider) createRecipeFragment.findViewById(R.id.slider_add_flavor_2);
        flavorLayout_3 = (android.support.v7.widget.CardView) createRecipeFragment.findViewById(R.id.third_flavor_card);
        flavor_slider_3 = (Slider) createRecipeFragment.findViewById(R.id.slider_add_flavor_3);

        flavourNameEditText_1 = (EditText) createRecipeFragment.findViewById(R.id.name_add_flavor_1);
        flavourNameEditText_2 = (EditText) createRecipeFragment.findViewById(R.id.name_add_flavor_2);
        flavourNameEditText_3 = (EditText) createRecipeFragment.findViewById(R.id.name_add_flavor_3);

        onPositionChangedForElements(water_vodka_pga_slider, water_vodka_pga, null, null);
        onPositionChangedForElements(slider_add_flavor_1, count_add_flavor_1, null, null);
        onPositionChangedForElements(slider_add_flavor_2, count_add_flavor_2, null, null);
        onPositionChangedForElements(slider_add_flavor_3, count_add_flavor_3, null, null);
        onPositionChangedForElements(desired_pg_slider, desired_pg_text, desired_vg_slider, desired_vg_text);
        onPositionChangedForElements(desired_vg_slider, desired_vg_text, desired_pg_slider, desired_pg_text);
        onPositionChangedForElements(pg_content_of_nicotine_slider, pg_content_of_nicotine, vg_content_of_nicotine_slider, vg_content_of_nicotine);
        onPositionChangedForElements(vg_content_of_nicotine_slider, vg_content_of_nicotine, pg_content_of_nicotine_slider, pg_content_of_nicotine);
    }

    private void onPositionChangedForElements(Slider sliderFirst, final TextView textViewFirst, final Slider sliderSecond, final TextView textViewSecond) {
        sliderFirst.setOnPositionChangeListener((view, fromUser, oldPos, newPos, oldValue, newValue) -> {
            String firstElement = newValue + " %";
            textViewFirst.setText(firstElement);
            if (sliderSecond != null) {
                sliderSecond.setValue(100 - newValue, true);
                String secondElement = (100 - newValue) + " %";
                textViewSecond.setText(secondElement);
            }
        });
    }

       private float getFloatFromField(TextView textView) {
        return Float.valueOf(textView.getText().toString());
    }
}
