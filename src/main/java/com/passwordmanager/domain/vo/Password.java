package com.passwordmanager.domain.vo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.Validate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class Password {
    private String password;
    Matcher matcher;
    private static Logger logger = LogManager.getLogger(Password.class);

    public Password() {
    }

    /**
     * @param p CREATE A PASSWORD
     */
    public Password(String p) {
        Validate.notNull(p);
        checkInvariants(p);
    }

    private void checkInvariants(String pwd) {
        Validate.notNull(pwd, "Password is null!");
        Validate.inclusiveBetween(Properties.PWD_MIN_SIZE, Properties.PWD_MAX_SIZE, pwd.length(),
                "The value must be between %d and %d",
                Properties.PWD_MIN_SIZE, Properties.PWD_MAX_SIZE);
        if (checkPasswordFormat(pwd)) {
            encryptPassword(pwd);
        }
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    private String generateRandomPasswordSalt() {
        return DigestUtils.sha256Hex(String.valueOf(System.nanoTime()));
    }

    public String encryptPassword(String pwd) {
        String p = DigestUtils.sha256Hex(pwd.toString() + generateRandomPasswordSalt());
        return this.password = p;
    }

    public boolean checkPasswordFormat(String password) {
        Pattern pattern = Pattern.compile(Properties.PASSWORD_FORMAT);
        Matcher matcher = pattern.matcher(password);
        if (password == null) {
            return false;
        }
        return matcher.matches();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Password that = (Password) o;
        return password.equals(that.password);
    }

    @Override
    public int hashCode() {
        return password.hashCode();
    }

    @Override
    public String toString() {
        return password;
    }
}
