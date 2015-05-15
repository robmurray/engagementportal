package com.ys.eportal.infra.domain;

import javax.persistence.*;

/**
 * Created by rob on 4/8/15.
 */
@Entity
@Table(name = "ep_ProjectCredit")
public class ProjectCreditEntity extends AbstractDomainBase {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "creditId")
    private long creditId;

    @OneToOne
    @JoinColumn(name = "project_id", nullable = false)
    private ProjectEntity project;


    @Basic(fetch = FetchType.EAGER)
    @Column(name = "startingCredits")
    private long startingCredits;

    @Basic(fetch = FetchType.EAGER)
    @Column(name = "creditsUsed")
    private long creditsUsed;

    public ProjectCreditEntity() {
    }


    public ProjectCreditEntity(ProjectEntity project, long startingCredits) {
        this.project = project;
        this.startingCredits = startingCredits;
    }

    public void useCredits(long numOfCreditsToUse){
        this.creditsUsed+=numOfCreditsToUse;
    }
    public long retrieveCreditsRemaining(){
        return this.startingCredits-this.creditsUsed;
    }

    public long getCreditId() {
        return creditId;
    }

    public void setCreditId(long creditId) {
        this.creditId = creditId;
    }

    public ProjectEntity getProject() {
        return project;
    }

    public void setProject(ProjectEntity project) {
        this.project = project;
    }

    public long getStartingCredits() {
        return startingCredits;
    }

    public void setStartingCredits(long startingCredits) {
        this.startingCredits = startingCredits;
    }

    public long getCreditsUsed() {
        return creditsUsed;
    }

    public void setCreditsUsed(long creditsUsed) {
        this.creditsUsed = creditsUsed;
    }
}
