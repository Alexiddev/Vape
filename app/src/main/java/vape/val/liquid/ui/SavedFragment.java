package vape.val.liquid.ui;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.rey.material.widget.Spinner;

import java.util.ArrayList;

import io.github.yavski.fabspeeddial.FabSpeedDial;
import io.github.yavski.fabspeeddial.SimpleMenuListenerAdapter;
import vape.val.liquid.R;
import vape.val.liquid.database.SQLiteHelper;
import vape.val.liquid.model.Liquid;
import vape.val.liquid.util.Util;


public class SavedFragment extends Fragment {

    TextView nicotineJuiceMl;
    TextView nicotineJuiceGrams;
    TextView nicotineJuicePercent;

    TextView pgMl;
    TextView pgGrams;
    TextView pgPercent;

    TextView vgMl;
    TextView vgGrams;
    TextView vgPercent;

    TextView waterMl;
    TextView waterGrams;
    TextView waterPercent;

    TextView flavorMl_1;
    TextView flavorGrams_1;
    TextView flavorPercent_1;
    TextView flavorName_1;

    TextView flavorMl_2;
    TextView flavorGrams_2;
    TextView flavorPercent_2;
    TextView flavorName_2;

    TextView flavorMl_3;
    TextView flavorGrams_3;
    TextView flavorPercent_3;
    TextView flavorName_3;

    LinearLayout flavorLayout_1;
    LinearLayout flavorLayout_2;
    LinearLayout flavorLayout_3;
    FabSpeedDial fabSpeedDial;
    LinearLayout headLayout;
    Liquid liquid;


    com.rey.material.widget.Spinner spinner;
    private View view;
    private ScrollView scrollView;
    ArrayAdapter<Liquid> liquidAdapter;
    ArrayList<Liquid> liquidList;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_saved, container, false);
        initFields();
        init(view);

        return view;
    }

    private void init(View view) {

        spinner = (com.rey.material.widget.Spinner) view.findViewById(R.id.spinner);
        final SQLiteHelper sQLiteHelper = new SQLiteHelper(getActivity());


       liquidList = sQLiteHelper.getAllRecords();


        liquidAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, liquidList);

        if (liquidList.size() == 0){
            haventRecipe();
        }else {
            liquidAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(liquidAdapter);
            liquid = liquidAdapter.getItem(0);
            fieldValue(liquid);

            spinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {

                @Override
                public void onItemSelected(Spinner parent, View view, int position, long id) {
                    liquid = liquidAdapter.getItem(position);
                    fieldValue(liquid);
                }
            });


        fabSpeedDial.setMenuListener(new SimpleMenuListenerAdapter() {
            @Override
            public boolean onMenuItemSelected(MenuItem menuItem) {

                switch (menuItem.getItemId()){
                    case R.id.action_share:
                        Util.share(liquid, getActivity());
                        break;
                    case R.id.action_delete:
                        Util.delete((Liquid) spinner.getSelectedItem(), getActivity());
                        liquidList = sQLiteHelper.getAllRecords();

                        if (liquidList.size() == 0){
                            haventRecipe();
                            break;
                        }
                        spinner.setVisibility(View.INVISIBLE);
                        spinner.setVisibility(View.VISIBLE);
                        liquidAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, liquidList);
                        spinner.setAdapter(liquidAdapter);
                        liquidAdapter.notifyDataSetChanged();
                        final Liquid liquid = liquidAdapter.getItem(0);
                        spinner.setSelection(0);
                        fieldValue(liquid);
                        break;
                }

                return false;
            }
        });

        }
    }


    private void initFields() {

        headLayout = (LinearLayout) view.findViewById(R.id.headLayout);
        scrollView = (ScrollView) view.findViewById(R.id.scrollView);
        nicotineJuiceMl = (TextView) view.findViewById(R.id.nicotine_juice_ml);
        nicotineJuiceGrams = (TextView) view.findViewById(R.id.nicotine_juice_gramm);
        nicotineJuicePercent = (TextView) view.findViewById(R.id.nicotine_juice_per);
        pgMl = (TextView) view.findViewById(R.id.pg_dilutant_ml);
        pgGrams = (TextView) view.findViewById(R.id.pg_dilutant_gramm);
        pgPercent = (TextView) view.findViewById(R.id.pg_dilutant_per);
        vgMl = (TextView) view.findViewById(R.id.vg_dilutant_ml);
        vgGrams = (TextView) view.findViewById(R.id.vg_dilutant_gramm);
        vgPercent = (TextView) view.findViewById(R.id.vg_dilutant_per);
        waterMl = (TextView) view.findViewById(R.id.wvg_ml);
        waterGrams = (TextView) view.findViewById(R.id.wvg_gramm);
        waterPercent = (TextView) view.findViewById(R.id.wvg_per);
        flavorMl_1 = (TextView) view.findViewById(R.id.flavor_1_ml);
        flavorGrams_1 = (TextView) view.findViewById(R.id.flavor_1_gramm);
        flavorPercent_1 = (TextView) view.findViewById(R.id.flavor_1_per);
        flavorName_1 = (TextView) view.findViewById(R.id.aroma_1);
        flavorMl_2 = (TextView) view.findViewById(R.id.flavor_2_ml);
        flavorGrams_2 = (TextView) view.findViewById(R.id.flavor_2_gramm);
        flavorPercent_2 = (TextView) view.findViewById(R.id.flavor_2_per);
        flavorName_2 = (TextView) view.findViewById(R.id.aroma_2);
        flavorMl_3 = (TextView) view.findViewById(R.id.flavor_3_ml);
        flavorGrams_3 = (TextView) view.findViewById(R.id.flavor_3_gramm);
        flavorPercent_3 = (TextView) view.findViewById(R.id.flavor_3_per);
        flavorName_3 = (TextView) view.findViewById(R.id.aroma_3);
        flavorLayout_1 = (LinearLayout) view.findViewById(R.id.layout_aroma_1);
        flavorLayout_2 = (LinearLayout) view.findViewById(R.id.layout_aroma_2);
        flavorLayout_3 = (LinearLayout) view.findViewById(R.id.layout_aroma_3);
        fabSpeedDial = (FabSpeedDial) view.findViewById(R.id.recept_menu);
    }

    private void fieldValue(Liquid liquid){
        setValueFromLiquid(nicotineJuiceMl, liquid.getNicotineJuiceMl());
        setValueFromLiquid(nicotineJuiceGrams, liquid.getNicotineJuiceGrams());
        setValueFromLiquid(nicotineJuicePercent, liquid.getNicotineJuicePercent());

        setValueFromLiquid(pgMl, liquid.getPropyleneGlycolMl());
        setValueFromLiquid(pgGrams, liquid.getPropyleneGlycolGrams());
        setValueFromLiquid(pgPercent, liquid.getPropyleneGlycolPercent());

        setValueFromLiquid(vgMl, liquid.getVegetableGlycerinMl());
        setValueFromLiquid(vgGrams, liquid.getVegetableGlycerinGrams());
        setValueFromLiquid(vgPercent, liquid.getVegetableGlycerinPercent());

        setValueFromLiquid(waterMl, liquid.getWaterMl());
        setValueFromLiquid(waterGrams, liquid.getWaterGrams());
        setValueFromLiquid(waterPercent, liquid.getWaterPercent());

        if (!(liquid.getFlavorMl_1() == 0))
            flavorLayout_1.setVisibility(View.VISIBLE);
        setValueFromLiquid(flavorName_1, liquid.getFlavorName_1());
        setValueFromLiquid(flavorMl_1, liquid.getFlavorMl_1());
        setValueFromLiquid(flavorGrams_1, liquid.getFlavorGrams_1());
        setValueFromLiquid(flavorPercent_1, liquid.getFlavorPercent_1());

        if (!(liquid.getFlavorMl_2() == 0))
            flavorLayout_2.setVisibility(View.VISIBLE);
        setValueFromLiquid(flavorName_2, liquid.getFlavorName_2());
        setValueFromLiquid(flavorMl_2, liquid.getFlavorMl_2());
        setValueFromLiquid(flavorGrams_2, liquid.getFlavorGrams_2());
        setValueFromLiquid(flavorPercent_2, liquid.getFlavorPercent_2());

        if (!(liquid.getFlavorMl_3() == 0))
            flavorLayout_3.setVisibility(View.VISIBLE);
        setValueFromLiquid(flavorName_3, liquid.getFlavorName_3());
        setValueFromLiquid(flavorMl_3, liquid.getFlavorMl_3());
        setValueFromLiquid(flavorGrams_3, liquid.getFlavorGrams_3());
        setValueFromLiquid(flavorPercent_3, liquid.getFlavorPercent_3());
    }

    private void setValueFromLiquid(TextView textview, float liquidField) {
        textview.setText(String.valueOf(liquidField));
    }

    private void setValueFromLiquid(TextView textview, String liquidField) {
        textview.setText(liquidField);
    }

    private void haventRecipe(){
        RelativeLayout mainLayout = (RelativeLayout) view.findViewById(R.id.main_saved_layout);
        spinner.setVisibility(View.GONE);
        scrollView.setVisibility(View.GONE);
        fabSpeedDial.setVisibility(View.GONE);
        headLayout.setVisibility(View.GONE);
        try {
            Snackbar snackbar = Snackbar
                    .make(mainLayout, R.string.no_recipe, Snackbar.LENGTH_LONG)
                    .setAction("OK", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                        }
                    });

            snackbar.show();
        }catch (NullPointerException e){
            Toast.makeText(getActivity(), R.string.no_recipe, Toast.LENGTH_LONG).show();
        }

    }
}
