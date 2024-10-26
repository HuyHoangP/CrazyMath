package com.hhp.crazymath.view.widget;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

import com.hhp.crazymath.App;

public class CrazyTextView extends AppCompatTextView {


    public CrazyTextView(@NonNull Context context) {
        super(context);
        initView();
    }

    public CrazyTextView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public CrazyTextView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        setTypeface(Typeface.createFromAsset(App.getInstance().getAssets(), "font/Rowdies-Bold.ttf"));
    }
}
