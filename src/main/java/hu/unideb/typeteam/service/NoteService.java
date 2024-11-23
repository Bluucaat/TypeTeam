package hu.unideb.typeteam.service;

import hu.unideb.typeteam.entity.Note;

import java.util.List;

public interface NoteService {
    List<Note> getNotesSharedWithUser(String userId);
    List<Note> getNotesCreatedByUser(String userId);
    Note findById(int noteId);
    void save(Note note);
    void delete(Note note);

}
