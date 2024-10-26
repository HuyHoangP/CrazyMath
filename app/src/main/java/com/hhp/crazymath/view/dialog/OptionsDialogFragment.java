package com.hhp.crazymath.view.dialog;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.lifecycle.ViewModelProvider;

import com.hhp.crazymath.R;
import com.hhp.crazymath.databinding.DialogOptionsBinding;
import com.hhp.crazymath.view.OnMainCallback;
import com.hhp.crazymath.viewmodel.OptionsDialogFragmentVM;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class OptionsDialogFragment extends AppCompatDialogFragment implements View.OnClickListener {
    private DialogOptionsBinding binding;
    private Context context;
    private OnMainCallback callback;
    private OptionsDialogFragmentVM dialogVM;

    public static final String TAG = OptionsDialogFragment.class.getName();

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DialogOptionsBinding.inflate(inflater, container, false);

        dialogVM = new ViewModelProvider(this).get(OptionsDialogFragmentVM.class);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    private void initView() {
        setCancelable(false);
//        setCanceledOnTouchOutside(false);

        binding.tvApply.setOnClickListener(this);
        binding.tvCancel.setOnClickListener(this);
        binding.tvNextTimer.setOnClickListener(this);
        binding.tvPrevTimer.setOnClickListener(this);

//        initTimer();

    }

    private void initTimer() {
        dialogVM.setTimer()
                .observeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer timer) throws Throwable {
                        binding.tvTimer.setText("J");
                    }
                });
    }

    @Override
    public void onClick(View v) {
        v.startAnimation(AnimationUtils.loadAnimation(getContext(), androidx.appcompat.R.anim.abc_fade_in));
        if(v.getId() == R.id.tv_apply){
            dialogVM.applyChanges();
            dismiss();
        }
        if(v.getId() == R.id.tv_next_timer){
            dialogVM.increaseTimer();
        }
        if(v.getId() == R.id.tv_prev_timer){
            dialogVM.decreaseTimer();
        }

    }
}
