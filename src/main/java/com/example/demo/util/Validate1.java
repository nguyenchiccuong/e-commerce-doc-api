package com.example.demo.util;

import java.util.regex.Pattern;

public class Validate1 {
    public static boolean checkNameFile(String name) {
        String pattern = "^[\\w,\\s-]+";
        return Pattern.matches(pattern, name);
    }

    public static boolean checkNameDir(String name) {
        String pattern = "(\\\\?([^\\/]*[\\/])*)([^\\/]+)$";
        return Pattern.matches(pattern, name);
    }
}
