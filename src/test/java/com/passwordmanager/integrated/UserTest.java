package com.passwordmanager.integrated;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import com.passwordmanager.domain.User;
import com.passwordmanager.domain.valueObjects.Password;
import com.passwordmanager.domain.valueObjects.Username;
import com.passwordmanager.domain.valueObjects.WebpageName;

public class UserTest {
    @Test
    public void itCreatesBasicUser() {
        User user = new User();
        assertNotNull(user);
    }

    @Test
    public void itCreatesUserByName() {
        Username username = new Username("jumma");
        User user = new User(username);
        assertNotNull(user);
    }

    @Test
    public void itCreatesUserByPassword() {
        Password p = new Password("password12309");
        User user = new User(p);
        assertNotNull(user);
    }

    @Test
    public void itCreatesUserByWebpageName() {
        WebpageName wbName = new WebpageName("facebook.com");

        User user = new User(wbName);
        assertNotNull(user);
    }

    @Test
    public void itCreatesUserWithTimeDate() {
        User user = new User();
        assertNotNull(user.getCreationDate());
    }
}
