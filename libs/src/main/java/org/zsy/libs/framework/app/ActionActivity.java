package org.zsy.libs.framework.app;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;


import org.zsy.libs.R;
import org.zsy.libs.ext.ViewExt;

public abstract class ActionActivity extends BaseActivity {

    private LinearLayout ll;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action);
        ll = findViewById(R.id.ll);
        initActionBarTitle();
        onActionCreate();
    }

    protected void initActionBarTitle() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(getClass().getSimpleName());
        }
    }

    protected void addAction(String btnText, View.OnClickListener listener) {
        ViewExt.addBtn(ll, btnText, listener);
    }

    protected abstract void onActionCreate();
}
