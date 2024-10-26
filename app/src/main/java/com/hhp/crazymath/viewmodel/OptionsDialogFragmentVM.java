package com.hhp.crazymath.viewmodel;

import androidx.lifecycle.ViewModel;

import com.hhp.crazymath.App;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleEmitter;
import io.reactivex.rxjava3.core.SingleOnSubscribe;

public class OptionsDialogFragmentVM extends ViewModel {
    private int timer = App.getInstance().getStorage().timer;

    public int getTimer() {
        return timer;
    }

    public Single<Integer> setTimer(){
        return Single.create(new SingleOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull SingleEmitter<Integer> emitter) throws Throwable {
                emitter.onSuccess(timer);
            }
        });
    }

    public void increaseTimer() {

    }

    public void decreaseTimer() {
    }

    public void applyChanges() {
    }
}
