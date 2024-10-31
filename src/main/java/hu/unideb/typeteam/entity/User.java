package hu.unideb.typeteam.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="users")
public @Data class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private String id;

    @Column(name="notes_id")
    private String user_id;
}
