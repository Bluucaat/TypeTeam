package hu.unideb.typeteam.service;

import hu.unideb.typeteam.entity.Note;
import hu.unideb.typeteam.repository.NoteRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class NoteServiceImpl implements NoteService {

    private final NoteRepository noteRepository;

    @Autowired
    public NoteServiceImpl(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public List<Note> getNotesSharedWithUser(String userId) {
        return noteRepository.findAccessibleNotesNotCreatedByUser(userId);
    }

    public List<Note> getNotesCreatedByUser(String userId) {
        return noteRepository.findAccessibleNotesCreatedByUser(userId);
    }
}
