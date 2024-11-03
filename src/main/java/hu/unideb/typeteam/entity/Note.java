package hu.unideb.typeteam.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "notes")
public @Data class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "content")
    private String content;

    @Column(name = "user_id")
    private String notesId;
}
