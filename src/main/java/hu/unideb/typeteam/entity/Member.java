package hu.unideb.typeteam.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="members")
public @Data class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private String userId;

    @Column(name="pw")
    private String password;
}
