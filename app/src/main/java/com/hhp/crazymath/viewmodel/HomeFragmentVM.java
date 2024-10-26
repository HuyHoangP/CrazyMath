package com.hhp.crazymath.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.hhp.crazymath.App;
import com.hhp.crazymath.model.Difficulty;

public class HomeFragmentVM extends ViewModel {
//    private int time = App.getInstance().getStorage().timer;

    public MutableLiveData<Integer> getTimer() {
        return timer;
    }

    private final MutableLiveData<Integer> timer = new MutableLiveData<>(App.getInstance().getStorage().timer);

    public MutableLiveData<Integer> getLevel() {
        return level;
    }

    private final MutableLiveData<Integer> level = new MutableLiveData<>(App.getInstance().getStorage().difficulty.getLevel());

//    public int getTime() {
//        return time;
//    }

//    public Single<Integer> setTime(){
//        return Single.create(new SingleOnSubscribe<Integer>() {
//            @Override
//            public void subscribe(@NonNull SingleEmitter<Integer> emitter) throws Throwable {
//                emitter.onSuccess(time);
//            }
//        });
//    }




    public void increaseTimer() {
        timer.setValue(timer.getValue() + 5);
        if(timer.getValue() > 20){
            timer.setValue(5);
        }
    }

    public void decreaseTimer() {
        timer.setValue(timer.getValue() - 5);
        if(timer.getValue() < 5){
            timer.setValue(20);
        }
    }

    public void applyChanges() {
        App.getInstance().getStorage().timer = timer.getValue();
        App.getInstance().getStorage().difficulty = new Difficulty(level.getValue());
    }

    public void cancelChanges(){
        timer.setValue(App.getInstance().getStorage().timer);
        level.setValue(App.getInstance().getStorage().difficulty.getLevel());
    }

    public void increaseDiff() {
        level.setValue(level.getValue() + 1);
    }

    public void decreaseDiff() {
        level.setValue(level.getValue() - 1);
    }
}
