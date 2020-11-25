package org.zsy.libs.framework.app;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.inputmethod.InputMethodManager;


import org.zsy.libs.filter.TimeFilter;

import static android.content.Context.INPUT_METHOD_SERVICE;


public class ApplicationUtils {

    public static final long PERIOD_ACT = 500;
    public static final long PERIOD_FT = 250;

    public static final TimeFilter TimeFilter = new TimeFilter(PERIOD_ACT);

    private static Application appContext;
    private static Handler mainHandler;

    private ApplicationUtils() {
    }

    public static void init(Application application) {
        ApplicationUtils.appContext = application;
        mainHandler = new Handler(Looper.getMainLooper());
    }

    public static Application getAppContext() {
        return appContext;
    }

    public static void postOnMain(Runnable runnable) {
        postOnMainDelay(runnable, 0);
    }

    public static void postOnMainDelay(Runnable runnable, long delay) {
        if (delay == 0 && isMainThread()) {
            runnable.run();
        } else {
            mainHandler.postDelayed(runnable, delay);
        }
    }

    public static void removeRunnable(Runnable runnable) {
        mainHandler.removeCallbacks(runnable);
    }

    public static boolean isMainThread() {
        return Looper.getMainLooper() == Looper.myLooper();
    }


    public static boolean isVerified() {
        return TimeFilter.isVerified();
    }

    public static boolean isVerifiedAndFt() {
        return isVerifiedAndNext(PERIOD_FT);
    }

    public static boolean isVerifiedAndNext(long period) {
        return TimeFilter.isVerifiedAndNext(period);
    }

    public static Intent getStartIntent(Context packageContext, Class<?> cls) {
        Intent intent = new Intent(packageContext, cls);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        return intent;
    }


    public static void startAct(Context context, Class<?> cls) {
        context.startActivity(getStartIntent(context, cls));
    }

    public static void startApp(Context context) {
        String packageName = context.getPackageName();
        //启动应用
        Intent launchIntent = context.getPackageManager().
                getLaunchIntentForPackage(packageName);
        if (launchIntent != null) {
            launchIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
            context.startActivity(launchIntent);
        }
    }


    public static void home(Activity activity) {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addCategory(Intent.CATEGORY_HOME);
        activity.startActivity(intent);
    }

//    public static void overridePendingTransitionEnter(Activity activity) {
//        activity.overridePendingTransition(R.anim.enter_translate_right, R.anim.exit_left);
//    }
//
//    public static void overridePendingTransitionExit(Activity activity) {
//        activity.overridePendingTransition(R.anim.enter_left, R.anim.exit_translate_right);
//    }


    public static void hideKeyboard(View view) {
        InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public static void showKeyboard(View view) {
        InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(INPUT_METHOD_SERVICE);
        imm.showSoftInput(view, 0);
    }

}
