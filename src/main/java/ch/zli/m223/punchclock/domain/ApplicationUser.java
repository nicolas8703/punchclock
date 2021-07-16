package ch.zli.m223.punchclock.domain;

import org.h2.engine.User;

import javax.persistence.*;
import java.util.List;

@Entity
public class ApplicationUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String username;
    private String password;
    //@ManyToOne
    //@JoinColumn(name = "userGroup")
    //private UserGroup userGroup;

    @OneToMany
    @JoinColumn(name = "entries")
    private List<Entry> entries;

    //public UserGroup getUserGroup() {
    //    return userGroup;
    //}

    //public void setUserGroup(UserGroup userGroup) {
    //    this.userGroup = userGroup;
    //}

    public List<Entry> getEntries() {
        return entries;
    }

    public void setEntries(List<Entry> entries) {
        this.entries = entries;
    }

    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}