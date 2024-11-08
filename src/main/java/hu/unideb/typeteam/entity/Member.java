package hu.unideb.typeteam.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "members")
public @Data class Member {
    @Id
    @Column(name = "user_id")
    @NotNull(message = "required field")
    private String userId;

    @Column(name = "pw")
    @NotNull(message = "required field")
    private String password;

    @Column(name = "email")
    @NotNull(message = "required field")
    private String email;

    @Column(name = "active")
    private boolean active;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "members_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles = new ArrayList<>();
}

