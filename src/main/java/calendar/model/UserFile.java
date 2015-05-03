package calendar.model;

import com.sun.istack.NotNull;
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotEmpty;
import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;

/**
 * Created by Marat on 12.04.2015.
 */

@Entity
@Table(name = "files_table")
public class UserFile extends BaseEntity {
    @Column(name = "fname")
    @NotEmpty
    protected String fname;

    @Column(name = "fsize")
    @NotNull
    protected int fsize;

    @Column(name = "upl_date")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    @NotNull
    protected DateTime upl_date;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public int getFsize() {
        return fsize;
    }

    public void setFsize(int fsize) {
        this.fsize = fsize;
    }

    public DateTime getUpl_date() {
        return upl_date;
    }

    public void setUpl_date(DateTime upl_date) {
        this.upl_date = upl_date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
