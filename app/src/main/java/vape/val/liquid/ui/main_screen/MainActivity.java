package vape.val.liquid.ui.main_screen;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import vape.val.liquid.R;
import vape.val.liquid.ui.coil_calculator.coil_fragments.CoilCalculatorFragment;
import vape.val.liquid.ui.coil_calculator.coil_fragments.SavedCoilFragment;
import vape.val.liquid.ui.ohms_law_calculator.OhmsFragment;
import vape.val.liquid.ui.recipe.main_recipe.CreateRecipeFragment;
import vape.val.liquid.ui.recipe.main_recipe.SavedFragment;
import vape.val.liquid.util.RateThisApp;
import vape.val.liquid.util.Util;


public class MainActivity extends ActionBarActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    FragmentManager manager;
    Fragment fragment;
    FragmentTransaction fragmentTransaction;
    Context context;

    ActionBarDrawerToggle mDrawerToggle;
    DrawerLayout mDrawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        manager = this.getSupportFragmentManager();
        context = this;
//        AdView mAdView = (AdView) findViewById(R.id.adView);
//        AdRequest adRequest = new AdRequest.Builder()
//                .build();
//        mAdView.loadAd(adRequest);

        RateThisApp.Config config = new RateThisApp.Config(3, 5);
        RateThisApp.init(config);
        RateThisApp.onStart(context);
        RateThisApp.showRateDialogIfNeeded(context);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getResources().getString(R.string.create_new_recipe));

        mDrawerToggle = new ActionBarDrawerToggle(this,mDrawer,R.string.app_name,R.string.app_name){
            @Override
            public void onDrawerClosed(View drawerView) {
            }

            @Override
            public void onDrawerOpened(View drawerView) {
            }
        };
        mDrawer.addDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.nav_create_new_recipe) {
            fragment = CreateRecipeFragment.newInstance();
            getSupportActionBar().setTitle(getResources().getString(R.string.create_new_recipe));
        } else if (id == R.id.nav_saved) {
            fragment = SavedFragment.newInstance();
            getSupportActionBar().setTitle(getResources().getString(R.string.my_recipes));
        } else if (id == R.id.nav_voltage) {
            fragment = OhmsFragment.newInstance();
            getSupportActionBar().setTitle(getResources().getString(R.string.ohms_law_calculator));
        } else if (id == R.id.nav_coil) {
            fragment = CoilCalculatorFragment.newInstance();
            getSupportActionBar().setTitle(getResources().getString(R.string.coil_calculator));
        }else if (id == R.id.nav_my_coil) {
            fragment = SavedCoilFragment.newInstance();
            getSupportActionBar().setTitle(getResources().getString(R.string.my_coils));
        } else if (id == R.id.nav_share) {
            Util.shareApp(context);
        }

        if (id != R.id.nav_share) {
            manager = this.getSupportFragmentManager();
            fragmentTransaction = manager.beginTransaction();
            for (int i = 0; i < manager.getBackStackEntryCount(); ++i) manager.popBackStack();
            fragmentTransaction.replace(R.id.headlines_fragment, fragment);
            //fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
            onBackPressed();
        }
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
