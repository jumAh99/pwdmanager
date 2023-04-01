package com.passwordmanager.domain;

import com.passwordmanager.domain.converters.PasswordAttributeConverter;
import com.passwordmanager.domain.converters.WebpageNameAttributeConverter;
import com.passwordmanager.domain.vo.Password;
import com.passwordmanager.domain.vo.WebpageName;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.validation.constraints.NotEmpty;

import org.springframework.format.annotation.DateTimeFormat;

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
    @Column(name = "username", nullable = false)
    private String username;

    /**
     * PASSWORD FOR A WEBSITE
     */
    @NotEmpty(message = "Password is mandatory")
    @Column(name = "password", nullable = false)
    @Convert(converter = PasswordAttributeConverter.class)
    private Password password;

    @Column(name = "originalPassword")
    private String originalPassword;

    /**
     * WHEN THE PASSWORD WAS CREATED
     */
    @Column(name = "creationDate")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime creationDate;

    /**
     * THE WEBSITE NAME
     */
    @NotEmpty(message = "WebsiteName is mandatory")
    @Column(name = "websiteName", nullable = false)
    @Convert(converter = WebpageNameAttributeConverter.class)
    private WebpageName websiteName;

    public User(@NotEmpty(message = "Username is mandatory") String username,
            @NotEmpty(message = "Password is mandatory") Password password,
            @NotEmpty(message = "WebsiteName is mandatory") WebpageName websiteName) {
        this.username = username;
        this.password = password;
        this.creationDate = LocalDateTime.now();
        this.websiteName = websiteName;
    }

    public User(Password p) {
        this.password = p;
        username = "";
        websiteName = new WebpageName();
        this.creationDate = LocalDateTime.now();
    }

    public User(WebpageName wbName) {
        this.websiteName = wbName;
        password = new Password();
        username = "";
        creationDate = LocalDateTime.now();
    }

    public User(String username) {
        this.username = username;
        password = new Password();
        websiteName = new WebpageName();
        creationDate = LocalDateTime.now();
    }

    public User() {
        username = "";
        password = new Password();
        websiteName = new WebpageName();
        creationDate = LocalDateTime.now();
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getOriginalPassword() {
        return originalPassword;
    }

    public void setOriginalPassword(String originalPassword) {
        this.originalPassword = originalPassword;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public WebpageName getWebsiteName() {
        return websiteName;
    }

    public void setWebsiteName(WebpageName websiteName) {
        this.websiteName = websiteName;
    }

    public void setPassword(String password) {
        this.password.setPassword(password);
    }

    public String getPassword() {
        return password.getPassword();
    }

    public String getCreationDate() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        return creationDate.format(format);
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }
}
