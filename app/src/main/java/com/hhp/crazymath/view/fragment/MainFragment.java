package com.hhp.crazymath.view.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.res.AssetManager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.hhp.crazymath.App;
import com.hhp.crazymath.R;
import com.hhp.crazymath.databinding.FragmentMainBinding;
import com.hhp.crazymath.view.dialog.GameOverDialog;
import com.hhp.crazymath.viewmodel.MainFragmentVM;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainFragment extends BaseFragment<FragmentMainBinding, MainFragmentVM> {

    public static final String TAG = MainFragment.class.getName();

    @Override
    protected void initView() {

        initQuestion(true);

        binding.tvKeyA.setOnClickListener(this);
        binding.tvKeyB.setOnClickListener(this);
        binding.tvKeyC.setOnClickListener(this);
        binding.tvKeyD.setOnClickListener(this);
        binding.tvBestScore.setOnClickListener(this);

    }

    private void countDown() {
        viewModel.startCountDown();
        viewModel.getTimer().observe(this, timer -> {
            ((Activity) context).runOnUiThread(() -> {
                if (timer < 0) return;
                binding.tvTime.setText(timer.toString());
                if (timer == 0) {
                    gameOver();
                }
            });
        });
    }

    @SuppressLint("CheckResult")
    private void initQuestion(boolean resetScore) {
        countDown();
        int level = App.getInstance().getStorage().difficulty.getLevel();
        viewModel.initExpression(level, resetScore)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::updateUI).isDisposed();
        if (level < 7) {
            binding.frKeyD.setVisibility(View.GONE);
        } else {
            binding.frKeyD.setVisibility(View.VISIBLE);
        }

    }

    private void updateUI(Object[] objects) {
        binding.tvQuestion.setText((CharSequence) objects[0]);
        binding.tvKeyA.setText(objects[1].toString());
        binding.tvKeyB.setText(objects[2].toString());
        binding.tvKeyC.setText(objects[3].toString());
        binding.tvKeyD.setText(objects[4].toString());
        binding.tvScore.setText(String.format("Score: %s", objects[5].toString()));
        binding.tvBestScore.setText(String.format("Best: %s", objects[6].toString()));
    }

    @Override
    protected void clickView(View v) {
        if (v.getId() == R.id.tv_key_a || v.getId() == R.id.tv_key_b || v.getId() == R.id.tv_key_c || v.getId() == R.id.tv_key_d) {
            Single<Object[]> result = viewModel.checkAnswer(((TextView) v).getText().toString());
            if (result == null) {
                gameOver();
            } else {
                initQuestion(false);
            }
        }
    }

    private void gameOver() {
        viewModel.gameOver();
        binding.tvBestScore.setText("Best: " + viewModel.getBest());
        GameOverDialog dialog = new GameOverDialog(context, v -> {
            if (v.getId() == R.id.tv_play_again) {
                initQuestion(true);
            }
        }, callback);
        dialog.show();
    }


    @Override
    protected Class<MainFragmentVM> initViewModel() {
        return MainFragmentVM.class;
    }

    @Override
    protected FragmentMainBinding initViewBinding() {
        return FragmentMainBinding.inflate(getLayoutInflater());
    }

    @Override
    public void onDestroy() {
        viewModel.getThread().interrupt();
        super.onDestroy();
    }
}
