package com.example.tumblr4u.GeneralPurpose;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import android.text.Spanned;
import android.text.SpannedString;

import org.junit.Before;
import org.junit.Test;

public class GeneralPurposeClassesTests {
    @Test
    public void filterFunctionNullTest(){
        InputMinMaxFilter minMaxFilter = new InputMinMaxFilter(1, 30);
        Spanned dest = new SpannedString("");
        assertEquals(minMaxFilter.filter("", 0, 0, dest, 0, 0), "");
    }

    @Test
    public void filterFunctionInRangeNumberTest(){
        InputMinMaxFilter minMaxFilter = new InputMinMaxFilter(1, 30);
        Spanned dest = new SpannedString("");
        assertNull(minMaxFilter.filter("20", 0, 0, dest, 0, 0));

        assertNull(minMaxFilter.filter("1", 0, 0, dest, 0, 0));

        assertNull(minMaxFilter.filter("30", 0, 0, dest, 0, 0));
    }

    @Test
    public void filterFunctionOutRangeNumberTest(){
        InputMinMaxFilter minMaxFilter = new InputMinMaxFilter(1, 30);
        Spanned dest = new SpannedString("");
        assertEquals(minMaxFilter.filter("40", 0, 0, dest, 0, 0), "");
        assertEquals(minMaxFilter.filter("100", 0, 0, dest, 0, 0), "");
        assertEquals(minMaxFilter.filter("-1", 0, 0, dest, 0, 0), "");
    }

    @Test
    public void filterFunctionCharacterTest(){
        InputMinMaxFilter minMaxFilter = new InputMinMaxFilter(1, 30);
        Spanned dest = new SpannedString("");
        assertEquals(minMaxFilter.filter("c", 0, 0, dest, 0, 0), "");
        assertEquals(minMaxFilter.filter("+", 0, 0, dest, 0, 0), "");
        assertEquals(minMaxFilter.filter("=", 0, 0, dest, 0, 0), "");
    }


}
