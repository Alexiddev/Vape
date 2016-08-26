package vape.val.liquid;

import android.app.Application;

import vape.val.liquid.database.HelperFactory;

/**
 * Created by v.aleksandrenko on 26.08.2016.
 */
public class VapeApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        HelperFactory.setHelper(getApplicationContext());
    }
    @Override
    public void onTerminate() {
        HelperFactory.releaseHelper();
        super.onTerminate();
    }
}
