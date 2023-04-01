package com.passwordmanager.domain.vo;

import org.apache.commons.lang3.Validate;

public class Username {
    private String username;

    public Username(String username) {
        Validate.notNull(username);
        int sz = username.length();
        Validate.inclusiveBetween(5, 100, sz, "The value must be between 0 and 100 characters");
        this.username = username;
    }

    public Username() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Username that = (Username) o;
        return username.equals(that.username);
    }

    @Override
    public int hashCode() {
        return username.hashCode();
    }

    @Override
    public String toString() {
        return username;
    }
}
