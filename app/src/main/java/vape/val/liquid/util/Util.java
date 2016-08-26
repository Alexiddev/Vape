package vape.val.liquid.util;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import java.math.BigDecimal;
import java.math.RoundingMode;

import vape.val.liquid.R;
import vape.val.liquid.model.coil.Coil;
import vape.val.liquid.model.liquid.Liquid;

/**
 * Created by v.aleksandrenko on 12.08.2016.
 */
public class Util {



    public static void share(Liquid liquid, Context context) {
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, liquid.forShareString());
        context.startActivity(Intent.createChooser(sharingIntent, context.getString(R.string.share)));
    }

    public static void share(Coil coil, Context context) {
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, coil.forShareString());
        context.startActivity(Intent.createChooser(sharingIntent, context.getString(R.string.share)));
    }


    public static void shareApp(Context context) {
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, "The best E-Cigarette liquid recipe calculator! https://play.google.com/store/apps/details?id=vape.val.liquid");
        context.startActivity(Intent.createChooser(sharingIntent, context.getString(R.string.share)));
    }

    public static float rounded(float input, int umberOfCharacters) {
        return new BigDecimal(String.valueOf(input)).setScale(umberOfCharacters, RoundingMode.HALF_UP).floatValue();
    }
    public static double rounded(double input, int umberOfCharacters) {
        return new BigDecimal(String.valueOf(input)).setScale(umberOfCharacters, RoundingMode.HALF_UP).doubleValue();
    }


}
