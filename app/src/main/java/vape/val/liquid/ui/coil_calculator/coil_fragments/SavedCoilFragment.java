package vape.val.liquid.ui.coil_calculator.coil_fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.j256.ormlite.stmt.DeleteBuilder;
import com.rey.material.widget.Spinner;

import java.sql.SQLException;
import java.util.ArrayList;

import io.github.yavski.fabspeeddial.FabSpeedDial;
import io.github.yavski.fabspeeddial.SimpleMenuListenerAdapter;
import vape.val.liquid.R;
import vape.val.liquid.database.HelperFactory;
import vape.val.liquid.model.coil.Coil;
import vape.val.liquid.model.liquid.Liquid;
import vape.val.liquid.ui.coil_calculator.base.BaseCoilFragment;
import vape.val.liquid.ui.ohms_law_calculator.OhmsFragment;
import vape.val.liquid.util.Util;

public class SavedCoilFragment extends BaseCoilFragment {


    FabSpeedDial fabSpeedDial;
    com.rey.material.widget.Spinner spinner;

    ArrayAdapter<Coil> coilAdapter;
    ArrayList<Coil> coilList;
    View savedFragmentView;
    CardView spinner_card;
    CardView main_card;
    CardView bottom_card;

    Coil coil;

    public static SavedCoilFragment newInstance() {
        SavedCoilFragment savedCoilFragment = new SavedCoilFragment();
        return savedCoilFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        savedFragmentView = inflater.inflate(R.layout.my_coil_fragment, container, false);
        initFields(savedFragmentView);
        return savedFragmentView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initCoilList(savedFragmentView);
    }

    private void initCoilList(View view) {

        spinner = (com.rey.material.widget.Spinner) view.findViewById(R.id.spinner_coil);
        try {
            coilList = (ArrayList<Coil>) HelperFactory.getHelper().getCoilDAO().getAllLCoil();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        coilAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, coilList);

        if (coilList.size() == 0) {
            hideView();
        } else {
            coilAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(coilAdapter);
            coil = coilAdapter.getItem(0);
            setValue(coil);
            spinner.setOnItemSelectedListener((parent, view1, position, id) -> {
                coil = coilAdapter.getItem(position);
                setValue(coil);
            });


            fabSpeedDial.setMenuListener(new SimpleMenuListenerAdapter() {
                @Override
                public boolean onMenuItemSelected(MenuItem menuItem) {

                    switch (menuItem.getItemId()) {
                        case R.id.action_share:
                           Util.share(coil, getActivity());
                            break;
                        case R.id.action_delete:

                            try {
                                DeleteBuilder<Coil, String> deleteBuilder = HelperFactory.getHelper().getCoilDAO().deleteBuilder();
                                deleteBuilder.where().eq("NAME", coil.getName());
                                deleteBuilder.delete();
                                coilList = (ArrayList<Coil>) HelperFactory.getHelper().getCoilDAO().getAllLCoil();
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }

                            if (coilList.size() == 0) {
                                hideView();
                                break;
                            }
                            coilAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, coilList);
                            spinner.setAdapter(coilAdapter);
                            final Coil coil = coilAdapter.getItem(0);
                            spinner.setSelection(0);
                            setValue(coil);
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
        fabSpeedDial = (FabSpeedDial) view.findViewById(R.id.coil_menu);
        spinner_card = (CardView) view.findViewById(R.id.coil_card_spinner);
        main_card = (CardView) view.findViewById(R.id.coil_card_main);
        bottom_card = (CardView) view.findViewById(R.id.coil_card_bottom);
    }

    private void hideView() {
        RelativeLayout mainLayout = (RelativeLayout) savedFragmentView.findViewById(R.id.main_saved_layout);
        spinner.setVisibility(View.GONE);
        fabSpeedDial.setVisibility(View.GONE);
        spinner_card.setVisibility(View.GONE);
        main_card.setVisibility(View.GONE);
        bottom_card.setVisibility(View.GONE);
        Snackbar snackbar = Snackbar
                .make(mainLayout, R.string.no_coils, Snackbar.LENGTH_LONG)
                .setAction(R.string.ok, view -> {
                });
        snackbar.show();
    }
}
