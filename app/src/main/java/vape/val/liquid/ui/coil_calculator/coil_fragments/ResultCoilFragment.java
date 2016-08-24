package vape.val.liquid.ui.coil_calculator.coil_fragments;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;

import io.github.yavski.fabspeeddial.FabSpeedDial;
import io.github.yavski.fabspeeddial.SimpleMenuListenerAdapter;
import vape.val.liquid.R;
import vape.val.liquid.model.coil.Coil;
import vape.val.liquid.ui.coil_calculator.base.BaseCoilFragment;
import vape.val.liquid.util.Util;

public class ResultCoilFragment extends BaseCoilFragment {


    FabSpeedDial fabSpeedDial;
    EditText inputName;
    Coil coil;
    View resultCoilFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        resultCoilFragment = inflater.inflate(R.layout.coil_result_fragment, container, false);

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            coil = bundle.getParcelable("coil");
        }

        initFields(resultCoilFragment);
        setValue(coil);

//        fabSpeedDial.setMenuListener(new SimpleMenuListenerAdapter() {
//            @Override
//            public boolean onMenuItemSelected(MenuItem menuItem) {
//                inputName(menuItem.getItemId());
//                return false;
//            }
//        });

        return resultCoilFragment;
    }

//    private void inputName(final int itemId) {
//        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
//        alertDialog.setTitle(getString(R.string.liquid_name));
//        alertDialog.setMessage(getString(R.string.enter_name));
//
//        alertDialog.setPositiveButton(getResources().getString(R.string.yes),
//                (dialog, which) -> {
//                    liquid.setName(inputName.getText().toString());
//                    switch (itemId) {
//                        case R.id.action_share:
//                            Util.share(liquid, getActivity());
//                            break;
//                        case R.id.action_save:
//                            Util.save(liquid, getActivity());
//                            break;
//                    }
//                });
//
//        alertDialog.setNegativeButton(getResources().getString(R.string.no),
//                (dialog, which) -> {
//                    dialog.cancel();
//                });
//
//        inputName = new EditText(getActivity());
//        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
//                LinearLayout.LayoutParams.MATCH_PARENT,
//                LinearLayout.LayoutParams.MATCH_PARENT);
//        inputName.setLayoutParams(lp);
//        alertDialog.setView(inputName);
//        alertDialog.show();
//    }

    @Override
    public void initFields(View view) {
        super.initFields(view);
        fabSpeedDial = (FabSpeedDial) view.findViewById(R.id.recept_menu);
    }
}
