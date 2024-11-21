package hu.unideb.typeteam.service;

import hu.unideb.typeteam.entity.Note;
import hu.unideb.typeteam.entity.User;
import hu.unideb.typeteam.repository.NoteRepository;
import hu.unideb.typeteam.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.beans.Transient;
import java.util.List;

@Service
public class NoteServiceImpl implements NoteService {

    private final NoteRepository noteRepository;
    private final UserRepository userRepository;

    @Autowired
    public NoteServiceImpl(NoteRepository noteRepository, UserRepository userRepository) {
        this.noteRepository = noteRepository;
        this.userRepository = userRepository;
    }

    public List<Note> getNotesSharedWithUser(String userId) {
        return noteRepository.findAccessibleNotesNotCreatedByUser(userId);
    }

    public List<Note> getNotesCreatedByUser(String userId) {
        return noteRepository.findAccessibleNotesCreatedByUser(userId);
    }

    @Override
    public Note findById(int noteId) {
        return noteRepository.findById(noteId);
    }

    @Override
    public void save(Note note) {
        noteRepository.save(note);
    }

    @Override
    @Transactional
    public void delete(Note note) {
        noteRepository.delete(note);
    }
}
