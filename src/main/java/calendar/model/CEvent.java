package calendar.model;

import com.sun.istack.NotNull;
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotEmpty;
import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;

/**
 * Created by Marat on 16.03.2015.
 */
@Entity
@Table(name = "events_table")
public class CEvent extends BaseEntity {
    @Column(name = "theme")
    @NotEmpty
    protected String theme;

    @Column(name = "descr")
    @NotEmpty
    protected String descr;

    @Column(name="cr_date")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    @NotNull
    protected DateTime cr_date;

    @ManyToOne
    @JoinColumn(name = "linked_user_id")
    private User user;

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public DateTime getCr_date() {
        return cr_date;
    }

    public void setCr_date(DateTime cr_date) {
        this.cr_date = cr_date;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
