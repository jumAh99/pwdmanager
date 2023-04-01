package com.passwordmanager.domain.valueObjects;

import org.apache.commons.lang3.Validate;

public class Username {
    private String username;

    public Username() {

    }

    public Username(String username) {
        Validate.notNull(username);
        int sz = username.length();
        Validate.inclusiveBetween(0, 100, sz, "The value must be between 0 and 100 characters");
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((username == null) ? 0 : username.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Username other = (Username) obj;
        if (username == null) {
            if (other.username != null)
                return false;
        } else if (!username.equals(other.username))
            return false;
        return true;
    }
}
