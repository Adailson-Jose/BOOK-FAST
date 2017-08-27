package com.projeto.bookfast.bookfast.negocio;

import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

/**
 * classe LimparTela passa um for pelo view e limpa as telas através do clearForm.
 */

public class LimparTela {
    public LimparTela() {
    }

    public static void clearForm(ViewGroup group) {
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
