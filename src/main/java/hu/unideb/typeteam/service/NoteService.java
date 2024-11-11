package hu.unideb.typeteam.service;

import hu.unideb.typeteam.entity.Note;

import java.util.List;

public interface NoteService {
    public List<Note> getNotesSharedWithUser(String userId);
    public List<Note> getNotesCreatedByUser(String userId);

}
