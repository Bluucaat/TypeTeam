package hu.unideb.typeteam.controller;

import hu.unideb.typeteam.entity.Note;
import hu.unideb.typeteam.entity.User;
import hu.unideb.typeteam.repository.NoteRepository;
import hu.unideb.typeteam.repository.UserRepository;
import hu.unideb.typeteam.service.NoteServiceImpl;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;


import java.util.List;

@Controller
public class NotesController {

    private final NoteServiceImpl noteServiceImpl;
    private final NoteRepository noteRepository;
    private final UserRepository userRepository;

    public NotesController(NoteServiceImpl noteServiceImpl, NoteRepository noteRepository, UserRepository userRepository) {
        this.noteServiceImpl = noteServiceImpl;
        this.noteRepository = noteRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/notes")
    public String showNotes(Model model) {
        String currentUserId = SecurityContextHolder.getContext().getAuthentication().getName();
        List<Note> sharedNotelist = noteServiceImpl.getNotesSharedWithUser(currentUserId);
        List<Note> ownUserNotes = noteServiceImpl.getNotesCreatedByUser(currentUserId);
        model.addAttribute("sharedNotes", sharedNotelist);
        model.addAttribute("ownNotes", ownUserNotes);

        model.addAttribute("note", new Note());
        return "notes";
    }

    @PostMapping("/notes")
    public String createNote(@RequestParam String title) {
        String currentUserId = SecurityContextHolder.getContext().getAuthentication().getName();
        User currentUser = userRepository.findByUserId(currentUserId);
        Note newNote = new Note();
        newNote.setTitle(title);
        newNote.setContent("");
        newNote.setCreator(currentUser);
        currentUser.getAccessibleNotes().add(newNote);
        noteRepository.save(newNote);
        noteServiceImpl.save(newNote);
        userRepository.save(currentUser);
        return "redirect:/notes";
    }

    @PostMapping("/deleteNote/{id}")
    public String deleteNote(@PathVariable("id") int id) {
        Note note = noteServiceImpl.findById(id);
        if (note != null) {
            noteServiceImpl.delete(note);
        }
        return "redirect:/notes";
    }
}
