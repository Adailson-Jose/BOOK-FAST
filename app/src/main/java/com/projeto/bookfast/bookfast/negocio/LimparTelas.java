package com.projeto.bookfast.bookfast.negocio;

import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

/**
 * Created by oi on 27/07/2017.
 */

public class LimparTelas {

    public LimparTelas(ViewGroup group) {

    }

    public void clearForm(ViewGroup group) {
        for (int i = 0, count = group.getChildCount(); i < count; ++i) {
            View view = group.getChildAt(i);
            if (view instanceof EditText) {
                ((EditText) view).setText("");
            }

            if (view instanceof ViewGroup && (((ViewGroup) view).getChildCount() > 0))
                clearForm((ViewGroup) view);
        }
    }
}
