package org.zsy.test.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.zsy.libs.dialog.Dg;
import org.zsy.libs.dialog.IDg;
import org.zsy.libs.ext.ViewExt;
import org.zsy.libs.filter.TimeFilter;
import org.zsy.libs.lg.Lg;
import org.zsy.libs.utils.reference.ToastUtils;
import org.zsy.test.R;

public class JavaAct extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_ll);
        LinearLayout ll = findViewById(R.id.ll);

        ViewExt.addBtn(ll, "Lg", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Lg.v("Lg.v msg");
                Lg.v("TagV", "Lg.v msg");
                Lg.d("Lg.d msg");
                Lg.d("TagD", "Lg.d msg");
                Lg.i("Lg.i msg");
                Lg.i("TagI", "Lg.i msg");
                Lg.w("Lg.w msg");
                Lg.w("TagW", "Lg.w msg");
                Lg.e("Lg.e msg");
                Lg.e("TagE", "Lg.e msg");
            }
        });

        ViewExt.addBtn(ll, "Dg Load", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dg.showLoadDg(JavaAct.this, "des", new IDg.OnKeyListener() {
                    @Override
                    public boolean onKey(IDg dialog, int keyCode, KeyEvent event) {
                        Lg.i("onKey:" + keyCode);
                        switch (keyCode) {
                            case KeyEvent.KEYCODE_BACK:
                                dialog.cancel();
                                break;
                        }
                        return true;
                    }
                });
            }
        });


        ViewExt.addBtn(ll, "DgConfirm", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dg.showConfirmDg(JavaAct.this, "des", "positive", new IDg.OnClickListener() {
                    @Override
                    public void onClick(IDg dialog, int which) {
                        Lg.i("positive");
                    }
                }, "negative", new IDg.OnClickListener() {
                    @Override
                    public void onClick(IDg dialog, int which) {
                        Lg.i("negative");
                    }
                });
            }
        });

        ViewExt.addBtn(ll, "Long", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.showLong("Long");
            }
        });

        ViewExt.addBtn(ll, "Short", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.showShort("Short");
            }
        });

        ViewExt.addBtn(ll, "CustomToast", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.setToastCreator(new ToastUtils.ToastCreator() {
                    @Override
                    public Toast create(Context context, CharSequence text, int duration) {
                        Toast toast = Toast.makeText(context, null, duration);
                        toast.setText(text);
                        toast.setGravity(Gravity.TOP, 0, 0);
                        return toast;
                    }
                });
                ToastUtils.showLong("CustomToast");
            }
        });

        ViewExt.addBtn(ll, "Center", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.setToastCreator(new ToastUtils.ToastCreator() {
                    @Override
                    public Toast create(Context context, CharSequence text, int duration) {
                        Toast toast = Toast.makeText(context, null, duration);
                        toast.setText(text);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        return toast;
                    }
                });
                ToastUtils.showLong("CustomToast");
            }
        });

        final TimeFilter filter = new TimeFilter(10000);
        ViewExt.addBtn(ll, "Filter", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (filter.isVerified()) {
                    ToastUtils.showShort("111111");
                }
            }
        });

        ViewExt.addBtn(ll, "ShortFilter", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (filter.isVerifiedAndNext(1000)) {
                    ToastUtils.showShort("2222");
                }
            }
        });


//        ViewExt.setOnSlowlyClickListener();
    }
}
