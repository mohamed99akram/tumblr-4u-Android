package com.example.tumblr4u.GeneralPurpose;

import android.text.InputFilter;
import android.text.Spanned;

public class InputMinMaxFilter implements InputFilter {
    private int mMin;
    private int mMax;

    public InputMinMaxFilter(int min, int max){
        this.mMin = min;
        this.mMax = max;
        int temp = min;
        if(min > max){
            min = max;
            max = temp;
        }
    }
    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
        if(source.toString().equals("")) {return "";}
        if(!Character.isDigit(source.toString().charAt(0))) {return "";}
        int input = Integer.parseInt(dest.toString() + source.toString());
        if(isInRange(mMin, mMax, input)) {return null;}
        return "";
    }

    public Boolean isInRange(int min, int max, int input){
        return input >= min && input <= max;
    }
}
