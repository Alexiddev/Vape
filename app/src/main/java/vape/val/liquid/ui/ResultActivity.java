package vape.val.liquid.ui;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import io.github.yavski.fabspeeddial.FabSpeedDial;
import io.github.yavski.fabspeeddial.SimpleMenuListenerAdapter;
import vape.val.liquid.R;
import vape.val.liquid.database.SQLiteHelper;
import vape.val.liquid.model.Liquid;
import vape.val.liquid.util.Util;

public class ResultActivity extends Fragment {

    private AdView mAdView;

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

    EditText inputName;
    Context context;

    Liquid liquid;
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        view = inflater.inflate(R.layout.activity_result, container, false);

         Bundle bundle = this.getArguments();
        if (bundle != null) {
            liquid = bundle.getParcelable("liquid");
        }
        init();
        setValue();

        fabSpeedDial.setMenuListener(new SimpleMenuListenerAdapter() {
            @Override
            public boolean onMenuItemSelected(MenuItem menuItem) {

                inputName(menuItem.getItemId());

                return false;
            }
        });

        return view;
    }

    private void inputName(final int itemId) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
        alertDialog.setTitle(getString(R.string.liquid_name));
        alertDialog.setMessage(getString(R.string.enter_name));

        alertDialog.setPositiveButton("YES",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        liquid.setName(inputName.getText().toString());
                        switch (itemId){
                            case R.id.action_share:
                                Util.share(liquid, getActivity());
                                break;
                            case R.id.action_save:
                                Util.save(liquid, getActivity());
                                break;
                        }
                    }
                });

        alertDialog.setNegativeButton("NO",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

        inputName = new EditText(getActivity());
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        inputName.setLayoutParams(lp);
        alertDialog.setView(inputName);

        alertDialog.show();


    }

    private void setValue() {
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

    private void init() {
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

}
