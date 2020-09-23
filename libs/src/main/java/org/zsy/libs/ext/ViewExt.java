package org.zsy.libs.ext;

import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class ViewExt {


    public static int minClickPeriod = 1000;
    private static long lastClickMs = -1L;

    public static void setMinClickPeriod(int minClickPeriod) {
        ViewExt.minClickPeriod = minClickPeriod;
    }

    public static void addBtn(LinearLayout ll, String btnText, View.OnClickListener clickListener) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        Button button = new Button(ll.getContext());
        button.setAllCaps(false);
        button.setText(btnText);
        button.setOnClickListener(clickListener);
        ll.addView(button, params);

    }

    public static void setOnSlowlyClickListener(View view, final View.OnClickListener clickListener) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long currentMs = System.currentTimeMillis();
                if (currentMs - lastClickMs > minClickPeriod) {
                    lastClickMs = currentMs;
                    clickListener.onClick(v);
                }
            }
        });
    }


}
