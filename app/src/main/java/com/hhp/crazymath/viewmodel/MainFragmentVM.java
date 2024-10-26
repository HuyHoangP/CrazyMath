package com.hhp.crazymath.viewmodel;

import android.content.SharedPreferences;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.hhp.crazymath.App;
import com.hhp.crazymath.model.Difficulty;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleEmitter;
import io.reactivex.rxjava3.core.SingleOnSubscribe;

public class MainFragmentVM extends ViewModel {
    private int time;
    private MutableLiveData<Integer> timer = new MutableLiveData<>(10);
    private String expression;
    private int keyA, keyB, keyC, keyD;
    private int answer, best;
    private Thread thread;
    private int score;


    public MutableLiveData<Integer> getTimer() {
        return timer;
    }

    public Thread getThread() {
        return thread;
    }

    public int getScore() {
        return score;
    }

    public int getBest() {
        return best;
    }


    public Single<Object[]> initExpression(int level, boolean resetScore) {
        Difficulty difficulty = new Difficulty(level);
        Random rd = new Random();
//        time = 10;
        if (resetScore) score = 0;
        timer.setValue(App.getInstance().getStorage().timer);

        int b = rd.nextInt(difficulty.getRange()) + 1;
        int c = rd.nextInt(difficulty.getRange()) + 1;
        int d = rd.nextInt(difficulty.getRange()) + 1;

        String sign1 = rd.nextInt(2) == 0 ? "+" : "-";
        int a1 = sign1.equals("+") ? b + c : b - c;

        String sign2 = rd.nextInt(2) == 0 ? "+" : "-";
        int a2 = sign2.equals("+") ? a1 + d : a1 - d;

        if (difficulty.getNumNum() == 2) {
            answer = a1;
            expression = String.format("%s%s%s = ?", b, sign1, c);
        } else {
            answer = a2;
            expression = String.format("%s%s%s%s%s = ?", b, sign1, c, sign2, d);
        }

        int wrong1 = answer + rd.nextInt(difficulty.getWrongRange()) + 1;
        int wrong2 = answer - rd.nextInt(difficulty.getWrongRange()) - 1;
        int wrong3 = (a1 + a2 + wrong1 + wrong2) / (rd.nextInt(2) + 3);
        List<Integer> listKey = new ArrayList<>(Arrays.asList(answer, wrong1, wrong2));
        if (difficulty.getNumKey() == 4) {
            listKey.add(wrong3);
        }
        Collections.shuffle(listKey);
        keyA = listKey.get(0);
        keyB = listKey.get(1);
        keyC = listKey.get(2);
        try {
            keyD = listKey.get(3);
        } catch (IndexOutOfBoundsException e) {
            keyD = -11111;
        }
        return Single.create(new SingleOnSubscribe<Object[]>() {
            @Override
            public void subscribe(@NonNull SingleEmitter<Object[]> emitter) throws Throwable {
                emitter.onSuccess(new Object[]{expression, keyA, keyB, keyC, keyD, score, best});
            }
        });
    }

    public void startCountDown() {
        timer = new MutableLiveData<>(10);
        if (thread == null || !thread.isAlive()) {
            thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (timer.getValue() != null && timer.getValue() > 0) {
                        try {
                            Thread.sleep(1000);
                            timer.postValue(timer.getValue() - 1);
                        } catch (Exception e) {
                            e.printStackTrace();
                            return;
                        }
                    }
//                    timer.postValue(0);
                }
            });
            thread.start();
        }
    }

    public Single<Integer> countDown(){
        return Single.create(new SingleOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull SingleEmitter<Integer> emitter) throws Throwable {
                while (time > 0){
                    try{
                        Thread.sleep(1000);
                        time--;
                        emitter.onSuccess(time);
                    } catch (Exception e){
                        e.printStackTrace();
                        return;
                    }
                }
            }
        });
    }


    public Single<Object[]> checkAnswer(String key) {
        int answer = Integer.parseInt(key);
        if (answer == this.answer) {
            score += 1;
            return initExpression(App.getInstance().getStorage().difficulty.getLevel(), false);
        }
        return null;
    }

    public void gameOver() {
        thread.interrupt();
        if (score > best) best = score;
        App.getInstance().getStorage().score = score;
        App.getInstance().getStorage().best = best;
    }
}
