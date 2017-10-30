package com.wontak.sample.utils;

public class StringUtil {

    public static boolean isNullOrEmpty(CharSequence charSequence) {
        return (charSequence == null) || (charSequence.length() <= 0);
    }
}
