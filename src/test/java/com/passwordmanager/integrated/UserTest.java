package com.passwordmanager.integrated;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.passwordmanager.domain.User;
import com.passwordmanager.domain.vo.Password;
import com.passwordmanager.domain.vo.WebpageName;

public class UserTest {
    @Test
    public void itCreatesBasicUser() {
        User user = new User();
        assertNotNull(user);
    }

    @Test
    public void itCreatesUserByName() {
        User user = new User("jumma");
        assertNotNull(user);
    }

    @Test
    public void itCreatesUserByPassword() {
        Password p = new Password("password123");
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
    public void checkPasswordHash() {
        Password password = new Password("Password12@30");
        User user = new User(password);
        user.setOriginalPassword(password.toString());
        assertTrue(password.toString().equals(user.getOriginalPassword()));
    }

    @Test
    public void itCreatesUserWithTimeDate() {
        User user = new User();
        assertNotNull(user.getCreationDate());
    }
}
