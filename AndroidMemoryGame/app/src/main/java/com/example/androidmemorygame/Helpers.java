package com.example.androidmemorygame;

import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class Helpers  {
    public static  <T> ArrayList<ArrayList<T>> Arr2DToList(T[][] arr2D) {
        ArrayList<ArrayList<T>> res = new ArrayList<>();

        for (T[] arr : arr2D) {
            res.add(new ArrayList<T>(Arrays.asList(arr)));
        }

        return res;
    }

    static void typeAnimation(TextView view, String text, long charDiff) throws InterruptedException {

        String msg = "";

        for (char letter : text.toCharArray()) {
            msg += letter;
            view.setText(msg);
            Thread.sleep(charDiff);
        }
    }

    public  static void typeAnimation(TextView view, String msg) throws InterruptedException {
        typeAnimation(view, msg, 300);
    }
}