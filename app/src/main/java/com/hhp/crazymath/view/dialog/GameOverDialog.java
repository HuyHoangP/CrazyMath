package com.hhp.crazymath.view.dialog;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDialog;

import com.hhp.crazymath.App;
import com.hhp.crazymath.R;
import com.hhp.crazymath.databinding.DialogGameoverBinding;
import com.hhp.crazymath.view.OnMainCallback;
import com.hhp.crazymath.view.fragment.HomeFragment;

public class GameOverDialog extends AppCompatDialog {
    private DialogGameoverBinding binding;
    private OnMainCallback callback;
    private final View.OnClickListener event;


    public GameOverDialog(@NonNull Context context, View.OnClickListener event, OnMainCallback callback) {
        super(context, R.style.Dialog_FullScreen);
        this.event = event;
        this.callback = callback;
        binding = DialogGameoverBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initView();
    }


    private void initView() {
        setCancelable(false);
        setCanceledOnTouchOutside(false);

        binding.tvScore.setText("Your score: " + App.getInstance().getStorage().score);
        binding.tvBestScore.setText("Your best: " + App.getInstance().getStorage().best);
        binding.tvPlayAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(AnimationUtils.loadAnimation(getContext(), androidx.appcompat.R.anim.abc_fade_in));
                event.onClick(v);
                dismiss();
            }
        });
        binding.tvBackToMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(AnimationUtils.loadAnimation(getContext(), androidx.appcompat.R.anim.abc_fade_in));
                callback.showFragment(HomeFragment.TAG, null, false);
                dismiss();
            }
        });
    }


    private void playAgain() {

    }
}
