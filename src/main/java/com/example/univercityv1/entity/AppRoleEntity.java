package com.example.univercityv1.entity;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "app_role")
public class AppRoleEntity implements GrantedAuthority {
    public AppRoleEntity() {
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "app_role_title")
    private String title;
    @ManyToMany(mappedBy = "appRoles")
    private List<AppUserEntity> appUserEntities;

    public Long getId() {
        return id;
    }

    public AppRoleEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public AppRoleEntity setTitle(String title) {
        this.title = title;
        return this;
    }

    public List<AppUserEntity> getAppUserEntities() {
        return appUserEntities;
    }

    public AppRoleEntity setAppUserEntities(List<AppUserEntity> appUserEntities) {
        this.appUserEntities = appUserEntities;
        return this;
    }

    @Override
    public String getAuthority() {
        return "ROLE_" + this.title;
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        AppRoleEntity userRole = (AppRoleEntity) obj;
        return title.equals(userRole.title);
    }
}
