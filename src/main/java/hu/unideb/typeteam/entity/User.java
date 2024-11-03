package hu.unideb.typeteam.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "users")
public @Data class User {
    @Id
    @Column(name = "id", unique = true)
    private String id;

    @Column(name = "notes_id")
    private String user_id;

}
