package com.wontak.boilerplate.utils;

public class StringUtil {

    public static boolean isNullOrEmpty(CharSequence charSequence) {
        return (charSequence == null) || (charSequence.length() <= 0);
    }
}
