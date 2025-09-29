package com.bank.util;

import java.util.regex.Pattern;

public class ValidationUtil {
    private static final Pattern NAME_PATTERN = Pattern.compile("^[A-Za-z ]+$");
    private static final Pattern PHONE_PATTERN = Pattern.compile("^[6-9][0-9]{9}$");
    private static final Pattern AADHAR_PATTERN = Pattern.compile("^[0-9]{12}$");
    private static final Pattern IFSC_PATTERN = Pattern.compile("^[A-Za-z]{4}0[A-Za-z0-9]{6}$"); // optional IFSC format

    public static boolean isNonBlank(String s) {
        return s != null && !s.trim().isEmpty();
    }

    public static boolean validName(String name) {
        return isNonBlank(name) && NAME_PATTERN.matcher(name).matches();
    }

    public static boolean validPhone(String phone) {
        return isNonBlank(phone) && PHONE_PATTERN.matcher(phone).matches();
    }

    public static boolean validAadhar(String aadhar) {
        return isNonBlank(aadhar) && AADHAR_PATTERN.matcher(aadhar).matches();
    }

    public static boolean validIFSC(String ifsc) {
        if (ifsc == null) return true; // optional
        return IFSC_PATTERN.matcher(ifsc).matches();
    }
}
