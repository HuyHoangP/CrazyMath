package com.hhp.crazymath.view.dialog;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.animation.AnimationUtils;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDialog;
import androidx.lifecycle.Observer;

import com.hhp.crazymath.App;
import com.hhp.crazymath.R;
import com.hhp.crazymath.databinding.DialogOptionsBinding;
import com.hhp.crazymath.view.OnMainCallback;
import com.hhp.crazymath.viewmodel.HomeFragmentVM;

public class OptionsDialog extends AppCompatDialog implements View.OnClickListener {
    private final DialogOptionsBinding binding;
    private OnMainCallback callback;
    private final HomeFragmentVM dialogVM;


    public OptionsDialog(@NonNull Context context, OnMainCallback callback, HomeFragmentVM dialogVM) {
        super(context, R.style.Dialog_FullScreen);
        this.callback = callback;
        this.dialogVM = dialogVM;
        binding = DialogOptionsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initView();
    }


    private void initView() {
        setCancelable(false);
        setCanceledOnTouchOutside(false);


        binding.tvApply.setOnClickListener(this);
        binding.tvCancel.setOnClickListener(this);
        binding.tvNextTimer.setOnClickListener(this);
        binding.tvPrevTimer.setOnClickListener(this);
        binding.tvNextDiff.setOnClickListener(this);
        binding.tvPrevDiff.setOnClickListener(this);

        initSettings();

    }

    private void initSettings() {
        dialogVM.getTimer().observe(this, timer -> binding.tvTimer.setText(timer.toString()));
        dialogVM.getLevel().observe(this, this::initDifficulty);
    }

    private void initDifficulty(Integer level) {
        if(level == 1) {
            binding.tvPrevDiff.setClickable(false);
            binding.tvPrevDiff.setColorFilter(Color.GRAY);
        } else {
            binding.tvPrevDiff.setClickable(true);
            binding.tvPrevDiff.setColorFilter(Color.BLACK);
        }
        if(level == 7){
            binding.tvNextDiff.setClickable(false);
            binding.tvNextDiff.setColorFilter(Color.GRAY);
        } else {
            binding.tvNextDiff.setClickable(true);
            binding.tvNextDiff.setColorFilter(Color.BLACK);
        }

        for (int i = 1; i < binding.trDiff.getChildCount() - 1; i++) {
            View star = binding.trDiff.getChildAt(i);
            if(level >= i){
                star.setVisibility(View.VISIBLE);
            } else {
                star.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public void onClick(View v) {
        v.startAnimation(AnimationUtils.loadAnimation(getContext(), androidx.appcompat.R.anim.abc_fade_in));
        if(v.getId() == R.id.tv_apply){
            dialogVM.applyChanges();
            dismiss();
        }
        if(v.getId() == R.id.tv_cancel){
            dialogVM.cancelChanges();
            dismiss();
        }
        if(v.getId() == R.id.tv_next_timer){
            dialogVM.increaseTimer();
        }
        if(v.getId() == R.id.tv_prev_timer){
            dialogVM.decreaseTimer();
        }
        if(v.getId() == R.id.tv_next_diff){
            dialogVM.increaseDiff();
        }
        if(v.getId() == R.id.tv_prev_diff){
            dialogVM.decreaseDiff();
        }


    }
}
