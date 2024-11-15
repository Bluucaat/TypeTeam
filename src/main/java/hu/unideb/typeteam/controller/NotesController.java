package hu.unideb.typeteam.controller;

import hu.unideb.typeteam.entity.Note;
import hu.unideb.typeteam.repository.NoteRepository;
import hu.unideb.typeteam.service.NoteServiceImpl;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.ui.Model;


import java.util.List;

@Controller
public class NotesController {

    private final NoteServiceImpl noteServiceImpl;
    private final NoteRepository noteRepository;

    public NotesController(NoteServiceImpl noteServiceImpl, NoteRepository noteRepository) {
        this.noteServiceImpl = noteServiceImpl;
        this.noteRepository = noteRepository;
    }

    @GetMapping("/notes")
    public String showNotes(Model model) {
        String currentUserId = SecurityContextHolder.getContext().getAuthentication().getName();
        List<Note> sharedNotelist = noteServiceImpl.getNotesSharedWithUser(currentUserId);
        List<Note> ownUserNotes = noteServiceImpl.getNotesCreatedByUser(currentUserId);
        model.addAttribute("sharedNotes", sharedNotelist);
        model.addAttribute("ownNotes", ownUserNotes);
        return "notes";
    }
}
