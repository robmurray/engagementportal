        package com.ys.eportal.infra.domain;
        import javax.persistence.*;
        import java.io.Serializable;
        import java.util.Date;

/**
 * Created by rob on 4/8/15.
 */

@MappedSuperclass
public class AbstractDomainBase  implements Serializable{

    @Temporal(TemporalType.TIMESTAMP)
    @Basic(fetch = FetchType.EAGER)
    @Column(name = "createDate", nullable = false)
    private Date createDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Basic(fetch = FetchType.EAGER)
    @Column(name = "modifiedDate", nullable = false)
    private Date modifiedDate;

    @PrePersist
    protected void onCreate() {
        modifiedDate = createDate = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        modifiedDate = new Date();
    }
}
