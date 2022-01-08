package com.technikum.rezeptemanagement.Entity;

import org.hibernate.annotations.Nationalized;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "rm_user")
public class UserEntity extends AbstractEntity {

    @Nationalized
    @Column(name = "password")
    private String password;

    @Nationalized
    @Column(name = "email")
    private String email;

    @Nationalized
    @Column(name = "username", unique = true)
    private String username;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
