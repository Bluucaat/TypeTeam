package hu.unideb.typeteam.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "notes")
public @Data class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name="title")
    private String title;

    @Column(name="content")
    private String content;

    @ManyToOne
    @JoinColumn(name = "creator_user_id", nullable = false)
    private User creator;

    @ManyToMany(mappedBy = "accessibleNotes")
    private Set<User> usersWithAccess = new HashSet<>();
}
