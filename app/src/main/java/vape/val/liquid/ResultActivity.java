package vape.val.liquid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.HashMap;

public class ResultActivity extends AppCompatActivity {

    private AdView mAdView;

    TextView nicotine_juice_ml;
    TextView nicotine_juice_gramm;
    TextView nicotine_juice_per;

    TextView pg_dilutant_ml;
    TextView pg_dilutant_gramm;
    TextView pg_dilutant_per;

    TextView vg_dilutant_ml;
    TextView vg_dilutant_gramm;
    TextView vg_dilutant_per;

    TextView wvg_ml;
    TextView wvg_gramm;
    TextView wvg_per;

    TextView flavor_1_ml;
    TextView flavor_1_gramm;
    TextView flavor_1_per;

    TextView flavor_2_ml;
    TextView flavor_2_gramm;
    TextView flavor_2_per;

    TextView flavor_3_ml;
    TextView flavor_3_gramm;
    TextView flavor_3_per;

    LinearLayout lavour_1;
    LinearLayout lavour_2;
    LinearLayout lavour_3;

    HashMap<String, Float> hashMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        mAdView = (AdView) findViewById(R.id.adView_result);
        AdRequest adRequest = new AdRequest.Builder()
                .build();
        mAdView.loadAd(adRequest);

        Intent intent = getIntent();
        hashMap = (HashMap<String, Float>)intent.getSerializableExtra("resultMap");

        init();
        setValue();

    }

    private void setValue() {
        setValueFromMap(nicotine_juice_ml, "nicotine_juice");
        setValueFromMap(nicotine_juice_gramm, "nicgrams");
        setValueFromMap(nicotine_juice_per, "nicper");

        setValueFromMap(pg_dilutant_ml, "xpgml");
        setValueFromMap(pg_dilutant_gramm, "xpggrams");
        setValueFromMap(pg_dilutant_per, "xpgper");

        setValueFromMap(vg_dilutant_ml, "xvgml");
        setValueFromMap(vg_dilutant_gramm, "xvggrams");
        setValueFromMap(vg_dilutant_per, "xvgper");


        setValueFromMap(wvg_ml, "wvdrops");
        setValueFromMap(wvg_gramm, "wvgrams");
        setValueFromMap(wvg_per, "wvper");


        if (!hashMap.get("flml_1").toString().equals("0.0")) lavour_1.setVisibility(View.VISIBLE);
        setValueFromMap(flavor_1_ml, "flml_1");
        setValueFromMap(flavor_1_gramm, "fldr_1");
        setValueFromMap(flavor_1_per, "flper_1");


        if (!hashMap.get("flml_2").toString().equals("0.0")) lavour_2.setVisibility(View.VISIBLE);
        setValueFromMap(flavor_2_ml, "flml_2");
        setValueFromMap(flavor_2_gramm, "fldr_2");
        setValueFromMap(flavor_2_per, "flper_2");



        if (!hashMap.get("flml_3").toString().equals("0.0")) lavour_3.setVisibility(View.VISIBLE);
        setValueFromMap(flavor_3_ml, "flml_3");
        setValueFromMap(flavor_3_gramm, "fldr_3");
        setValueFromMap(flavor_3_per, "flper_3");

    }

    private void setValueFromMap(TextView textview, String string) {
        textview.setText(hashMap.get(string).toString());
    }

    private void init() {

        nicotine_juice_ml = (TextView) findViewById(R.id.nicotine_juice_ml);
        nicotine_juice_gramm = (TextView) findViewById(R.id.nicotine_juice_gramm);
        nicotine_juice_per = (TextView) findViewById(R.id.nicotine_juice_per);
        pg_dilutant_ml = (TextView) findViewById(R.id.pg_dilutant_ml);
        pg_dilutant_gramm = (TextView) findViewById(R.id.pg_dilutant_gramm);
        pg_dilutant_per = (TextView) findViewById(R.id.pg_dilutant_per);
        vg_dilutant_ml = (TextView) findViewById(R.id.vg_dilutant_ml);
        vg_dilutant_gramm = (TextView) findViewById(R.id.vg_dilutant_gramm);
        vg_dilutant_per = (TextView) findViewById(R.id.vg_dilutant_per);
        wvg_ml = (TextView) findViewById(R.id.wvg_ml);
        wvg_gramm = (TextView) findViewById(R.id.wvg_gramm);
        wvg_per = (TextView) findViewById(R.id.wvg_per);
        flavor_1_ml = (TextView) findViewById(R.id.flavor_1_ml);
        flavor_1_gramm = (TextView) findViewById(R.id.flavor_1_gramm);
        flavor_1_per = (TextView) findViewById(R.id.flavor_1_per);
        flavor_2_ml = (TextView) findViewById(R.id.flavor_2_ml);
        flavor_2_gramm = (TextView) findViewById(R.id.flavor_2_gramm);
        flavor_2_per = (TextView) findViewById(R.id.flavor_2_per);
        flavor_3_ml = (TextView) findViewById(R.id.flavor_3_ml);
        flavor_3_gramm = (TextView) findViewById(R.id.flavor_3_gramm);
        flavor_3_per = (TextView) findViewById(R.id.flavor_3_per);

        lavour_1 = (LinearLayout) findViewById(R.id.layout_aroma_1);
        lavour_2 = (LinearLayout) findViewById(R.id.layout_aroma_2);
        lavour_3 = (LinearLayout) findViewById(R.id.layout_aroma_3);


    }
}
