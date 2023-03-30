package com.passwordmanager.domain;

import com.passwordmanager.domain.converters.PasswordAttributeConverter;
import com.passwordmanager.domain.validate.Password;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import javax.validation.constraints.NotEmpty;

//DEFINE MAIN ENTITY
@Entity
@Table(name = "users")
public class User {

    /**
     * IDENTIFIABLE UNIT TO DISTINGUISH
     * USERS FROM ONE ANOTHER
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * USERNAME FOR A WEBSITE
     */
    @NotEmpty(message = "Username is mandatory")
    @Column(name = "name", nullable = false)
    private String name;

    /**
     * PASSWORD FOR A WEBSITE
     */
    @NotEmpty(message = "Password is mandatory")
    @Column(name = "password", nullable = false)
    @Convert(converter = PasswordAttributeConverter.class)
    private Password password;

    /**
     * THE WEBSITE NAME
     */
    @NotEmpty(message = "WebsiteName is mandatory")
    @Column(name = "websiteName", nullable = false)
    private String websiteName;

    public User(int id, @NotEmpty(message = "Username is mandatory") String name,
            @NotEmpty(message = "Password is mandatory") Password password,
            @NotEmpty(message = "WebsiteName is mandatory") String websiteName) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.websiteName = websiteName;
    }

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Password getPassword() {
        return password;
    }

    public void setPassword(Password password) {
        this.password = password;
    }

    public String getWebsiteName() {
        return websiteName;
    }

    public void setWebsiteName(String websiteName) {
        this.websiteName = websiteName;
    }
}
