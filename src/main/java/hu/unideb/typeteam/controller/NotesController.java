package hu.unideb.typeteam.controller;

import hu.unideb.typeteam.entity.Note;
import hu.unideb.typeteam.service.NoteServiceImpl;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class NotesController {

    private final NoteServiceImpl noteServiceImpl;

    public NotesController(NoteServiceImpl noteServiceImpl) {
        this.noteServiceImpl = noteServiceImpl;
    }

    @GetMapping("/notes")
    public String showNotes() {
        return "notes";
    }

    @GetMapping("/notes/shared")
    public List<Note> getSharedNotes() {
        String currentUserId = SecurityContextHolder.getContext().getAuthentication().getName();
        return noteServiceImpl.getNotesSharedWithUser(currentUserId);
    }

    @GetMapping("/notes/own")
    public List<Note> getUserNotes(){
        String currentUserId = SecurityContextHolder.getContext().getAuthentication().getName();
        return noteServiceImpl.getNotesCreatedByUser(currentUserId);
    }
}
