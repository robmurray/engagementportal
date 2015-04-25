package com.ys.eportal.model;

/**
 * Created by rob on 4/25/15.
 */
public class ProjectNote extends AbstractModelBase implements NoteInterface{

    private long noteId;
    private String note;

    public ProjectNote() {
    }

    public ProjectNote(String note) {

        this.note = note;
    }

    public long getNoteId() {
        return noteId;
    }

    public void setNoteId(long noteId) {
        this.noteId = noteId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProjectNote)) return false;

        ProjectNote that = (ProjectNote) o;

        if (noteId != that.noteId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return (int) (noteId ^ (noteId >>> 32));
    }
}
