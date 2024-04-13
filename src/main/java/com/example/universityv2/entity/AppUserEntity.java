package com.example.universityv2.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity
@Table(schema = "public", name = "app_user")
public class AppUserEntity implements UserDetails {
    public AppUserEntity() {
        this.appRoles = new ArrayList<>();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_name")
    private String name;
    @Column(name = "user_surname")
    private String surname;
    @Column(name = "user_login")
    private String login;
    @Column(name = "user_password")
    private String password;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "m2m_user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "app_role_id"))
    private List<AppRoleEntity> appRoles;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "m2m_user_message",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "message_id"))
    private List<MessageEntity> messageEntities;


    @Column(name = "is_deleted")
    private Boolean isDeleted;

    public Long getId() {
        return id;
    }

    public AppUserEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public AppUserEntity setName(String name) {
        this.name = name;
        return this;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public AppUserEntity setDeleted(Boolean deleted) {
        isDeleted = deleted;
        return this;
    }

    public String getSurname() {
        return surname;
    }

    public AppUserEntity setSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public String getLogin() {
        return login;
    }

    public AppUserEntity setLogin(String login) {
        this.login = login;
        return this;
    }

    public List<AppRoleEntity> getAppRoles() {
        return appRoles;
    }

    public AppUserEntity setAppRoles(List<AppRoleEntity> appRoles) {
        this.appRoles = appRoles;
        return this;
    }

    public List<MessageEntity> getMessageEntities() {
        return messageEntities;
    }

    public AppUserEntity setMessageEntities(List<MessageEntity> messageEntities) {
        this.messageEntities = messageEntities;
        return this;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.appRoles;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public AppUserEntity setPassword(String password) {
        this.password = password;
        return this;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AppUserEntity that = (AppUserEntity) o;
        return Objects.equals(login, that.login) &&
                Objects.equals(appRoles, that.appRoles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, appRoles);
    }

    public void removeAppRole(AppRoleEntity appRoleEntity) {
        appRoles.remove(appRoleEntity);
        appRoleEntity.getAppUserEntities().remove(this);
    }
}
