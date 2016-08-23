package vape.val.liquid.util;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;

import com.rey.material.widget.RadioButton;
import com.rey.material.widget.Switch;

import vape.val.liquid.R;
import vape.val.liquid.ui.coil_calculator.CoilCalculatorFragment;

/**
 * Created by v.aleksandrenko on 22.08.2016.
 */
public class RadioButtonWithTableLayout extends TableLayout implements
        View.OnClickListener {

    private RadioButton mBtnCurrentRadio;
    public static int wires = 1;
    public static int spirals = 1;
    public static int spirals_type = 1;

    public RadioButtonWithTableLayout(Context context) {
        super(context);
    }

    public RadioButtonWithTableLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void onClick(View v) {
        final RadioButton mBtnRadio = (RadioButton) v;
        if (mBtnCurrentRadio != null) {
            mBtnCurrentRadio.setChecked(false);
        }
        RadioButton first = (RadioButton) findViewById(R.id.radio_1);
        first.setChecked(false);
        mBtnRadio.setChecked(true);


        String type = (String) mBtnRadio.getText();


        switch (((View) v.getParent()).getId()){
            case R.id.tablerow_wires:
                CoilCalculatorFragment.pigtailCheck(Integer.parseInt(type));
                wires = Integer.parseInt(type);
                break;
            case R.id.tablerow_spirals:
                spirals = Integer.parseInt(type);
                break;
            case R.id.tablerow_type_spirals:
                if (type.equals("Normal")) type = "1";
                else if (type.equals("Micro")) type = "2";
                else if (type.equals("Clapton")) type = "3";
                spirals_type = Integer.parseInt(type);
                CoilCalculatorFragment.windingCheck(spirals_type);
                break;
        }
        mBtnCurrentRadio = mBtnRadio;
    }

    @Override
    public void addView(View child, int index,
                        android.view.ViewGroup.LayoutParams params) {
        super.addView(child, index, params);
        setChildrenOnClickListener((TableRow) child);
    }

    @Override
    public void addView(View child, android.view.ViewGroup.LayoutParams params) {
        super.addView(child, params);
        setChildrenOnClickListener((TableRow) child);
    }

    private void setChildrenOnClickListener(TableRow tr) {
        final int c = tr.getChildCount();
        for (int i = 0; i < c; i++) {
            final View v = tr.getChildAt(i);
            if (v instanceof RadioButton) {
                v.setOnClickListener(this);
            }
        }
    }

}
