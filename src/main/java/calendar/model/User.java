package calendar.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.*;

/**
 * Created by Marat on 16.03.2015.
 */

@Entity
@Table(name = "users_table")
public class User extends BaseEntity {
    @Column(name = "login")
    @NotEmpty
    protected String login;

    @Column(name = "password")
    @NotEmpty
    protected String password;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private Set<CEvent> eventSet;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private Set<UserFile> userFiles;

    public void setLogin(String login) {
        this.login = login;
    }

    public String getLogin() {
        return login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    protected void setEventSetInternal(Set<CEvent> eventSet) {
        this.eventSet = eventSet;
    }

    protected Set<CEvent> getEventSetInternal() {
        if(eventSet == null) {
            eventSet = new HashSet<CEvent>();
        }
        return eventSet;
    }

    public List<CEvent> getEventSet() {
        return new ArrayList<CEvent>(getEventSetInternal());
    }

    public void addEvent(CEvent event) {
        getEventSetInternal().add(event);
        event.setUser(this);
    }

    public CEvent getEvent(int id) {
        for(CEvent e : getEventSetInternal()) {
            if(e.getId() == id) {
                return e;
            }
        }
        return null;
    }

    protected void SetUserFilesInternal(Set<UserFile> userFiles) {
        this.userFiles = userFiles;
    }

    protected Set<UserFile> getUserFilesInternal() {
        if(userFiles == null) {
            userFiles = new HashSet<UserFile>();
        }
        return userFiles;
    }

    protected List<UserFile> getUserFiles() {
        return new ArrayList<UserFile>(getUserFilesInternal());
    }

    public void addUserFile(UserFile userFile) {
        getUserFilesInternal().add(userFile);
        userFile.setUser(this);
    }

    public UserFile getUserFile(int id) {
        for(UserFile uf : getUserFilesInternal()) {
            if(uf.getId() == id) {
                return uf;
            }
        }
        return null;
    }
}
