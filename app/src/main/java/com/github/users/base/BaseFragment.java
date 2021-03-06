package com.github.users.base;

import android.content.Context;
import androidx.appcompat.app.AppCompatActivity;

import dagger.android.support.DaggerFragment;

public abstract class BaseFragment extends DaggerFragment {

    private AppCompatActivity activity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (AppCompatActivity) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        activity = null;
    }

    public AppCompatActivity getBaseActivity() {
        return activity;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}