package org.zsy.test.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.jetbrains.anko.ToastsKt;
import org.zsy.libs.framework.widget.TopBar;
import org.zsy.libs.lg.Lg;
import org.zsy.test.R;

public class TopbarAct extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_topbar);
        TopBar tb1 = findViewById(R.id.tb1);
        TopBar tb2 = findViewById(R.id.tb2);
        tb1.setOnSubClickListener(new TopBar.OnSubClickListener() {
            @Override
            public void onLeftClick(View view) {
                Lg.i("11111", "left");
            }

            @Override
            public void onRightClick(View view) {
                Lg.i("11111", "right");
            }
        });

        tb2.setOnSubClickListener(new TopBar.OnSubClickListener() {
            @Override
            public void onLeftClick(View view) {
                Lg.i("2222", "left");

            }

            @Override
            public void onRightClick(View view) {
                Lg.i("2222", "right");

            }
        });
    }
}
