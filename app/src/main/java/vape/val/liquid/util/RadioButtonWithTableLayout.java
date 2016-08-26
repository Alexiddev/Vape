package vape.val.liquid.util;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;

import com.rey.material.widget.RadioButton;

import vape.val.liquid.R;
import vape.val.liquid.model.coil.Coil;
import vape.val.liquid.ui.coil_calculator.coil_fragments.CoilCalculatorFragment;

/**
 * Created by v.aleksandrenko on 22.08.2016.
 */
public class RadioButtonWithTableLayout extends TableLayout {
    public RadioButtonWithTableLayout(Context context) {
        super(context);
    }

    public RadioButtonWithTableLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
}
