package hu.unideb.typeteam.service;

import hu.unideb.typeteam.entity.Note;
import hu.unideb.typeteam.entity.User;

import java.util.List;

public interface NoteService {
    public List<Note> getNotesSharedWithUser(String userId);
    public List<Note> getNotesCreatedByUser(String userId);
    Note findById(int noteId);
    public void save(Note note);

}
