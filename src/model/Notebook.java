package model;

import entity.Note;

import java.util.ArrayList;
import java.util.List;

/**
 * Model class (Notebook)
 *
 * @author Bersik (Serhii Kisilchuk)
 */
public class Notebook {
    private List<Note> notebook;

    public Notebook() {
        notebook = new ArrayList<>();
    }

    public void addNote(Note note) {
        notebook.add(note);
    }

    public List<Note> getNotebook() {
        return notebook;
    }

    /**
     * Looking for a record of the people whose name contains text {@code fullName}
     *
     * @param fullName part of name or full name like Surname N. P.
     * @return list with notes about people, whether full name contains {@code fullName}
     */
    public List<Note> findNotesByFullName(String fullName) {
        List<Note> result = new ArrayList<>();
        for (Note note : notebook) {
            if (note.getFullName().toLowerCase().contains(fullName.toLowerCase())) {
                result.add(note);
            }
        }
        return result;
    }

}
