package vape.val.liquid.ui;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import vape.val.liquid.R;
import vape.val.liquid.util.Util;

public class MainActivity extends ActionBarActivity
implements NavigationView.OnNavigationItemSelectedListener {

    FragmentManager manager;
    Fragment fragment;
    FragmentTransaction fragmentTransaction;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        manager=this.getSupportFragmentManager();
context = this;
        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .build();
        mAdView.loadAd(adRequest);


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
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
        // Handle navigation view item clicks here.

        int id = item.getItemId();

        if (id == R.id.nav_create_new_recipe) {
            fragment = new CreateRecipe();
        } else if (id == R.id.nav_saved) {
            fragment = new SavedFragment();
        } else if (id == R.id.nav_share) {
            Util.shareApp(context);
        }

        if (id != R.id.nav_share) {
            manager = this.getSupportFragmentManager();
            fragmentTransaction = manager.beginTransaction();
            fragmentTransaction.replace(R.id.headlines_fragment, fragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
            onBackPressed();
        }
        return true;
    }
}
