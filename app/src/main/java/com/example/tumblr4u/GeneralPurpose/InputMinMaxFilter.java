package com.example.tumblr4u.GeneralPurpose;

import android.text.InputFilter;
import android.text.Spanned;

/**
 * Responsible for filtering Edit Text input w.r.t min & max values
 * @author Omar Ahmed
 * @version 1.0
 * */
public class InputMinMaxFilter implements InputFilter {
    private int mMin;
    private int mMax;

    /**
     * Initialize the allowable min and max values
     * @param min minimum allowable value
     * @param max maximum allowable value
     * */
    public InputMinMaxFilter(int min, int max) {
        this.mMin = min;
        this.mMax = max;
        int temp = min;
        if (min > max) {
            min = max;
            max = temp;
        }
    }

    /**
     * Allow the upcoming character if it matches specific characteristics determined by user
     * @param source The new entered character
     * @param start The starting index of the new character
     * @param end The ending index of the new character
     * @param dest The current existing input
     * @param dstart The starting index of the dest
     * @param dend The ending index of the dest
     * @return null if the new input matches the constraints so don't do anything or String to
     * replace the new character
     * */
    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {

        // Return back space if the input is back space
        if (source.toString().equals("")) { return "";}

        // Return back space if the input is not digit to delete it
        if (!Character.isDigit(source.toString().charAt(0))) { return "";}

        // Return null to do nothing and let the input in if it's in range
        int input = Integer.parseInt(dest.toString() + source.toString());
        if (isInRange(mMin, mMax, input)) {
            return null;
        }
        return "";
    }

    /**
     * Checks if the input number is in range or not
     * @param min The minimum allowable value
     * @param max The maximum allowable value
     * @param input The input to be tested
     * @return (true) if the input in range and (false) otherwise
     * */
    public Boolean isInRange(int min, int max, int input) {
        return input >= min && input <= max;
    }
}
