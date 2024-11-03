package hu.unideb.typeteam.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "roles")
public @Data class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private int roleId;

    @Column(name = "role")
    private String role;
}
