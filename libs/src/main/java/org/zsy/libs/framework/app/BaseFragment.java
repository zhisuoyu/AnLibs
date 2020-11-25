package org.zsy.libs.framework.app;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.zsy.libs.lg.Lg;

public abstract class BaseFragment extends Fragment {

    public final String TAG = getClass().getSimpleName();
    private View rootView;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Lg.v(TAG,"onAttach");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Lg.v(TAG,"onCreate");
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Lg.v(TAG,"onCreateView");
        rootView = inflater.inflate(getLayoutId(), container, false);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Lg.v(TAG,"onActivityCreated");
    }

    @Override
    public void onStart() {
        super.onStart();
        Lg.v(TAG,"onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Lg.v(TAG,"onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Lg.v(TAG,"onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Lg.v(TAG,"onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Lg.v(TAG,"onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Lg.v(TAG,"onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Lg.v(TAG,"onDetach");
    }

    //    @LayoutRes
    protected abstract int getLayoutId();


    protected final <T extends View> T findViewById(@IdRes int id) {
        return rootView.findViewById(id);
    }

    protected void back() {
        if (getFragmentManager() != null) {
            getFragmentManager().popBackStack();
        }
    }

}
