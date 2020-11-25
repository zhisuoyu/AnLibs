package org.zsy.test.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import org.zsy.libs.ext.ViewExt;
import org.zsy.libs.framework.app.ApplicationUtils;
import org.zsy.libs.framework.app.BaseActivity;
import org.zsy.test.R;

public class MainAct extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_ll);
        final LinearLayout ll = findViewById(R.id.ll);
        ViewExt.addBtn(ll, "Java", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ApplicationUtils.isVerified()) {
                    startActivity(new Intent(getApplication(), JavaAct.class));
                }
            }
        });
    }
}
