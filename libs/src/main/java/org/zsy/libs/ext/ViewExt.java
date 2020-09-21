package org.zsy.libs.ext;

import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class ViewExt {

    public static void addBtn(LinearLayout ll, String btnText, View.OnClickListener clickListener) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        Button button = new Button(ll.getContext());
        button.setText(btnText);
        button.setOnClickListener(clickListener);
        ll.addView(button, params);
    }
}
