package timezra.hibernate.callbacks.domain;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.hibernate.validator.NotNull;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = Author.NAME_ATTRIBUTE))
public class Author implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final String NAME_ATTRIBUTE = "name";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @NotNull
    private String name;
    private Date dateOfBirth;
    @Transient
    private Integer age;
    @NotNull
    private Date dateCreated;
    @NotNull
    private Date lastUpdated;

    @PrePersist
    void prePersist() {
        dateCreated = lastUpdated = new Date();
    }

    @PreUpdate
    void preUpdate() {
        lastUpdated = new Date();
    }

    @PostLoad
    void postLoad() {
        if (dateOfBirth != null) {
            final Calendar now = Calendar.getInstance(Locale.getDefault());
            final int thisYear = now.get(Calendar.YEAR);
            final int thisDay = now.get(Calendar.DAY_OF_YEAR);
            now.setTime(dateOfBirth);
            final int birthYear = now.get(Calendar.YEAR);
            final int birthDay = now.get(Calendar.DAY_OF_YEAR);
            age = thisYear - birthYear - (birthDay > thisDay ? 1 : 0);
        }
    }

    public Long getId() {
        return id;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public Integer getAge() {
        return age;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(final Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }
}
