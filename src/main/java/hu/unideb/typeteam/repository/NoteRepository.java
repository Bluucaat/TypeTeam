package hu.unideb.typeteam.repository;

import hu.unideb.typeteam.entity.Note;
import hu.unideb.typeteam.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<Note, String> {

    Note findById(int id);

    @Query("SELECT n FROM Note n JOIN n.usersWithAccess u " +
            "WHERE u.userId = :userId AND n.creator.userId <> :userId")
    List<Note> findAccessibleNotesNotCreatedByUser(@Param("userId") String userId);

    @Query("SELECT n FROM Note n JOIN n.usersWithAccess u " +
            "WHERE u.userId = :userId AND n.creator.userId = :userId")
    List<Note> findAccessibleNotesCreatedByUser(@Param("userId") String userId);

}
