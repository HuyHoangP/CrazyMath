package com.hhp.crazymath.view.fragment;

import android.view.View;

import com.hhp.crazymath.R;
import com.hhp.crazymath.databinding.FragmentHomeBinding;
import com.hhp.crazymath.view.dialog.OptionsDialog;
import com.hhp.crazymath.viewmodel.HomeFragmentVM;

public class HomeFragment extends BaseFragment<FragmentHomeBinding, HomeFragmentVM> {

    public static final String TAG = HomeFragment.class.getName();
    @Override
    protected void initView() {
        binding.tvStart.setOnClickListener(this);
        binding.tvOptions.setOnClickListener(this);
    }

    @Override
    protected Class<HomeFragmentVM> initViewModel() {
        return HomeFragmentVM.class;
    }

    @Override
    protected FragmentHomeBinding initViewBinding() {
        return FragmentHomeBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void clickView(View v) {
        if(v.getId() == R.id.tv_start){
            callback.showFragment(MainFragment.TAG, null , false);
        }
        if(v.getId() == R.id.tv_options) {
            OptionsDialog dialog = new OptionsDialog(context, callback, viewModel);
            dialog.show();
        }

    }
}
