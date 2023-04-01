package com.passwordmanager.domain.vo;

public final class Properties {
    public static final int PWD_MIN_SIZE = 10;
    public static final int PWD_MAX_SIZE = 100;
    public static final String PASSWORD_FORMAT = "^(?=.*[0-9])"
            + "(?=.*[a-z])(?=.*[A-Z])"
            + "(?=.*[@#$%^&+=])"
            + "(?=\\S+$).{8,20}$";
}
