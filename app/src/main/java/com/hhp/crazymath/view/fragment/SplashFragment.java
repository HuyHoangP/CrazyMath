package com.hhp.crazymath.view.fragment;

import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.hhp.crazymath.R;
import com.hhp.crazymath.databinding.FragmentSplashBinding;
import com.hhp.crazymath.viewmodel.CommonVM;

public class SplashFragment extends BaseFragment<FragmentSplashBinding, CommonVM> {

    public static final String TAG = SplashFragment.class.getName();
    @Override
    protected void initView() {
        Animation splashAnim = AnimationUtils.loadAnimation(context, R.anim.rotate_alpha);
        binding.ivCircle.startAnimation(splashAnim);
        splashAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                callback.showFragment(HomeFragment.TAG, null, false);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }

    @Override
    protected Class<CommonVM> initViewModel() {
        return CommonVM.class;
    }

    @Override
    protected FragmentSplashBinding initViewBinding() {
        return FragmentSplashBinding.inflate(getLayoutInflater());
    }
}
