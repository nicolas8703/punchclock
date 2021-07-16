package ch.zli.m223.punchclock.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class UserGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String userGroup;


   // @OneToMany(targetEntity=ApplicationUser.class, mappedBy="userGroup",cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    //private List<ApplicationUser> applicationUsersList = new ArrayList<>();

    public UserGroup(String normal) {
        userGroup = normal;
    }

    public UserGroup() {

    }

  //  public List<ApplicationUser> getApplicationUsersList() {
   //     return applicationUsersList;
   // }

  //  public void setApplicationUsersList(List<ApplicationUser> applicationUsersList) {
  //      this.applicationUsersList = applicationUsersList;
   // }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserGroup() {
        return userGroup;
    }

    public void setUserGroup(String userGroup) {
        this.userGroup = userGroup;
    }
}
