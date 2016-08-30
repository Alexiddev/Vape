package vape.val.liquid.ui.recipe.main_recipe;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;

import java.sql.SQLException;
import java.util.ArrayList;

import io.github.yavski.fabspeeddial.FabSpeedDial;
import io.github.yavski.fabspeeddial.SimpleMenuListenerAdapter;
import vape.val.liquid.R;
import vape.val.liquid.database.DatabaseHelper;
import vape.val.liquid.database.HelperFactory;
import vape.val.liquid.model.liquid.Liquid;
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

    public static SavedFragment newInstance() {
        SavedFragment savedFragment = new SavedFragment();
        return savedFragment;
    }

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
        try {
            liquidList = (ArrayList<Liquid>) HelperFactory.getHelper().getLiquidDAO().getAllLiquid();
        } catch (SQLException e) {
            e.printStackTrace();
        }
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

                            try {
                                DeleteBuilder<Liquid, String> deleteBuilder = HelperFactory.getHelper().getLiquidDAO().deleteBuilder();
                                deleteBuilder.where().eq("NAME", liquid.getName());
                                deleteBuilder.delete();
                                liquidList = (ArrayList<Liquid>) HelperFactory.getHelper().getLiquidDAO().getAllLiquid();
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }

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
        CardView spinnerCard = (CardView) savedFragmentView.findViewById(R.id.recipe_card_spinner);
        CardView headCard = (CardView) savedFragmentView.findViewById(R.id.table_card);
        spinnerCard.setVisibility(View.GONE);
        headCard.setVisibility(View.GONE);
        scrollView.setVisibility(View.GONE);
        fabSpeedDial.setVisibility(View.GONE);
        Snackbar snackbar = Snackbar
                .make(mainLayout, R.string.no_recipe, Snackbar.LENGTH_LONG)
                .setAction(R.string.ok, view -> {
                });
        snackbar.show();
    }
}
