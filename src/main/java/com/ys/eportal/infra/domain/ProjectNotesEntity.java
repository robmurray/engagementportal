package com.ys.eportal.infra.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by rob on 4/8/15.
 */
@Entity
@Table(name = "ep_ProjectNotes")
public class ProjectNotesEntity extends AbstractDomainBase {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "noteId")
    private long noteId;

    @ManyToOne
    @JoinColumn(name = "projectId", nullable = false)
    private ProjectEntity project;

    @Lob
    @Basic(fetch = FetchType.EAGER)
    @Column(name = "notes", columnDefinition = "TEXT")
    private String notes;


    public long getNoteId() {
        return noteId;
    }

    public void setNoteId(long noteId) {
        this.noteId = noteId;
    }

    public ProjectEntity getProject() {
        return project;
    }

    public void setProject(ProjectEntity project) {
        this.project = project;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProjectNotesEntity)) return false;

        ProjectNotesEntity that = (ProjectNotesEntity) o;

        if (noteId != that.noteId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return (int) (noteId ^ (noteId >>> 32));
    }
}
