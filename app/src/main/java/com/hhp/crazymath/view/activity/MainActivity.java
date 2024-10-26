package com.hhp.crazymath.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;

import com.hhp.crazymath.R;
import com.hhp.crazymath.databinding.ActivityMainBinding;
import com.hhp.crazymath.view.dialog.GameOverDialog;
import com.hhp.crazymath.view.fragment.MainFragment;
import com.hhp.crazymath.view.fragment.SplashFragment;
import com.hhp.crazymath.viewmodel.MainActVM;

import java.io.File;

public class MainActivity extends BaseAct<ActivityMainBinding, MainActVM> {

    @Override
    protected void initView() {
        showFragment(SplashFragment.TAG, null, false);
    }

    @Override
    protected Class<MainActVM> initViewModel() {
        return MainActVM.class;
    }

    @Override
    protected ActivityMainBinding initViewBinding() {
        return ActivityMainBinding.inflate(getLayoutInflater());
    }

    @Override
    public void callBack(String key, Object data) {
    }

}