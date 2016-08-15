package vape.val.liquid.ui.recipe.main_recipe;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import java.util.ArrayList;

import io.github.yavski.fabspeeddial.FabSpeedDial;
import io.github.yavski.fabspeeddial.SimpleMenuListenerAdapter;
import vape.val.liquid.R;
import vape.val.liquid.database.SQLiteHelper;
import vape.val.liquid.model.Liquid;
import vape.val.liquid.ui.recipe.base.BaseFragment;
import vape.val.liquid.util.Util;


public class SavedFragment extends BaseFragment {

    FabSpeedDial fabSpeedDial;
    LinearLayout headLayout;
    Liquid liquid;
    com.rey.material.widget.Spinner spinner;
    View savedFragmentView;
    ScrollView scrollView;
    ArrayAdapter<Liquid> liquidAdapter;
    ArrayList<Liquid> liquidList;
    SQLiteHelper sQLiteHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        savedFragmentView = inflater.inflate(R.layout.fragment_saved, container, false);
        initFields(savedFragmentView);
        return savedFragmentView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initLiquidList(savedFragmentView);
    }

    private void initLiquidList(View view) {

        spinner = (com.rey.material.widget.Spinner) view.findViewById(R.id.spinner);
        sQLiteHelper = new SQLiteHelper(getActivity());
        liquidList = sQLiteHelper.getAllRecords();
        liquidAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, liquidList);

        if (liquidList.size() == 0) {
            hideView();
        } else {
            liquidAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(liquidAdapter);
            liquid = liquidAdapter.getItem(0);
            setValue(liquid);
            spinner.setOnItemSelectedListener((parent, view1, position, id) -> {
                liquid = liquidAdapter.getItem(position);
                setValue(liquid);
            });


            fabSpeedDial.setMenuListener(new SimpleMenuListenerAdapter() {
                @Override
                public boolean onMenuItemSelected(MenuItem menuItem) {

                    switch (menuItem.getItemId()) {
                        case R.id.action_share:
                            Util.share(liquid, getActivity());
                            break;
                        case R.id.action_delete:
                            Util.delete((Liquid) spinner.getSelectedItem(), getActivity());
                            liquidList = sQLiteHelper.getAllRecords();
                            if (liquidList.size() == 0) {
                                hideView();
                                break;
                            }
                            liquidAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, liquidList);
                            spinner.setAdapter(liquidAdapter);
                            final Liquid liquid = liquidAdapter.getItem(0);
                            spinner.setSelection(0);
                            setValue(liquid);
                            break;
                    }

                    return false;
                }
            });

        }
    }

    @Override
    public void initFields(View view) {
        super.initFields(view);
        headLayout = (LinearLayout) view.findViewById(R.id.headLayout);
        scrollView = (ScrollView) view.findViewById(R.id.scrollView);
        fabSpeedDial = (FabSpeedDial) view.findViewById(R.id.recept_menu);
    }

    private void hideView() {
        RelativeLayout mainLayout = (RelativeLayout) savedFragmentView.findViewById(R.id.main_saved_layout);
        spinner.setVisibility(View.GONE);
        scrollView.setVisibility(View.GONE);
        fabSpeedDial.setVisibility(View.GONE);
        headLayout.setVisibility(View.GONE);
        Snackbar snackbar = Snackbar
                .make(mainLayout, R.string.no_recipe, Snackbar.LENGTH_LONG)
                .setAction(R.string.ok, view -> {
                });
        snackbar.show();
    }
}
