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
import java.util.Objects;

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

    @GetMapping("/editNote/{id}")
    public String editNotePage(@PathVariable("id") int id, Model model) {
        Note note = noteServiceImpl.findById(id);
        User currentUser = userRepository.findByUserId(SecurityContextHolder.getContext().getAuthentication().getName());
        if (!currentUser.getAccessibleNotes().contains(note)) {
            return "redirect:/notes";
        }
        model.addAttribute("note", note);
        return "/edit-note";
    }

    @PostMapping("/editNote/{id}")
    public String updateNote(@PathVariable("id") int id, @RequestParam String title, @RequestParam String content) {
        Note note = noteServiceImpl.findById(id);
        if (note != null) {
            note.setTitle(title);
            note.setContent(content);
            noteServiceImpl.save(note);
        }
        return "redirect:/notes";
    }


    @PostMapping("/deleteNote/{id}")
    public String deleteNote(@PathVariable("id") int id) {
        Note note = noteServiceImpl.findById(id);
        String currentUserId = SecurityContextHolder.getContext().getAuthentication().getName();
        if (note != null && Objects.equals(note.getCreator().getUserId(), currentUserId)) {
            noteServiceImpl.delete(note);
        }
        return "redirect:/notes";
    }
}
