package vape.val.liquid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.HashMap;

public class ResultActivity extends AppCompatActivity {

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

    TextView flavorMl_2;
    TextView flavorGrams_2;
    TextView flavorPercent_2;

    TextView flavorMl_3;
    TextView flavorGrams_3;
    TextView flavorPercent_3;

    LinearLayout flavorLayout_1;
    LinearLayout flavorLayout_2;
    LinearLayout flavorLayout_3;

    HashMap<String, Float> recept;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        mAdView = (AdView) findViewById(R.id.adView_result);
        AdRequest adRequest = new AdRequest.Builder()
                .build();
        mAdView.loadAd(adRequest);

        Intent intent = getIntent();
        recept = (HashMap<String, Float>) intent.getSerializableExtra("resultMap");

        init();
        setValue();

    }

    private void setValue() {
        setValueFromMap(nicotineJuiceMl, "nicotineJuiceMl");
        setValueFromMap(nicotineJuiceGrams, "nicotineGrams");
        setValueFromMap(nicotineJuicePercent, "nicotinePercent");

        setValueFromMap(pgMl, "pgMl");
        setValueFromMap(pgGrams, "pgGrams");
        setValueFromMap(pgPercent, "pgPercent");

        setValueFromMap(vgMl, "vgMl");
        setValueFromMap(vgGrams, "vgGrams");
        setValueFromMap(vgPercent, "vgPercent");

        setValueFromMap(waterMl, "waterDrops");
        setValueFromMap(waterGrams, "waterGrams");
        setValueFromMap(waterPercent, "waterPercent");

        if (!recept.get("flavorMl_1").toString().equals("0.0"))
            flavorLayout_1.setVisibility(View.VISIBLE);
        setValueFromMap(flavorMl_1, "flavorMl_1");
        setValueFromMap(flavorGrams_1, "flavorDrop_1");
        setValueFromMap(flavorPercent_1, "flavorPercent_1");

        if (!recept.get("flavorMl_2").toString().equals("0.0"))
            flavorLayout_2.setVisibility(View.VISIBLE);
        setValueFromMap(flavorMl_2, "flavorMl_2");
        setValueFromMap(flavorGrams_2, "flavorDrop_2");
        setValueFromMap(flavorPercent_2, "flavorPercent_2");

        if (!recept.get("flavorMl_3").toString().equals("0.0"))
            flavorLayout_3.setVisibility(View.VISIBLE);
        setValueFromMap(flavorMl_3, "flavorMl_3");
        setValueFromMap(flavorGrams_3, "flavorDrop_3");
        setValueFromMap(flavorPercent_3, "flavorPercent_3");

    }

    private void setValueFromMap(TextView textview, String string) {
        textview.setText(recept.get(string).toString());
    }

    private void init() {

        nicotineJuiceMl = (TextView) findViewById(R.id.nicotine_juice_ml);
        nicotineJuiceGrams = (TextView) findViewById(R.id.nicotine_juice_gramm);
        nicotineJuicePercent = (TextView) findViewById(R.id.nicotine_juice_per);
        pgMl = (TextView) findViewById(R.id.pg_dilutant_ml);
        pgGrams = (TextView) findViewById(R.id.pg_dilutant_gramm);
        pgPercent = (TextView) findViewById(R.id.pg_dilutant_per);
        vgMl = (TextView) findViewById(R.id.vg_dilutant_ml);
        vgGrams = (TextView) findViewById(R.id.vg_dilutant_gramm);
        vgPercent = (TextView) findViewById(R.id.vg_dilutant_per);
        waterMl = (TextView) findViewById(R.id.wvg_ml);
        waterGrams = (TextView) findViewById(R.id.wvg_gramm);
        waterPercent = (TextView) findViewById(R.id.wvg_per);
        flavorMl_1 = (TextView) findViewById(R.id.flavor_1_ml);
        flavorGrams_1 = (TextView) findViewById(R.id.flavor_1_gramm);
        flavorPercent_1 = (TextView) findViewById(R.id.flavor_1_per);
        flavorMl_2 = (TextView) findViewById(R.id.flavor_2_ml);
        flavorGrams_2 = (TextView) findViewById(R.id.flavor_2_gramm);
        flavorPercent_2 = (TextView) findViewById(R.id.flavor_2_per);
        flavorMl_3 = (TextView) findViewById(R.id.flavor_3_ml);
        flavorGrams_3 = (TextView) findViewById(R.id.flavor_3_gramm);
        flavorPercent_3 = (TextView) findViewById(R.id.flavor_3_per);

        flavorLayout_1 = (LinearLayout) findViewById(R.id.layout_aroma_1);
        flavorLayout_2 = (LinearLayout) findViewById(R.id.layout_aroma_2);
        flavorLayout_3 = (LinearLayout) findViewById(R.id.layout_aroma_3);

    }
}
