package com.example.springsecuity.model;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
public class Role implements GrantedAuthority {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer id;

private String role_name;

@ManyToOne(cascade = CascadeType.ALL)
@JoinColumn()
private CustomUser customUser;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }

    public CustomUser getCustomUser() {
        return customUser;
    }

    public void setCustomUser(CustomUser customUser) {
        this.customUser = customUser;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", role_name='" + role_name + '\'' +
                '}';
    }

    public Role() {
    }

    @Override
    public String getAuthority() {
        return role_name;
    }
}
