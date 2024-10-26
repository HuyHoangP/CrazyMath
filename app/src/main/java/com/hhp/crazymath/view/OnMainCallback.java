package com.hhp.crazymath.view;

public interface OnMainCallback {
    void callBack(String key, Object data);
    void showFragment(String tag, Object data, Boolean isBack);
}
