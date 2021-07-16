package ch.zli.m223.punchclock.seeder;

import ch.zli.m223.punchclock.domain.ApplicationUser;
import ch.zli.m223.punchclock.domain.Entry;
import ch.zli.m223.punchclock.domain.UserGroup;
import ch.zli.m223.punchclock.repository.ApplicationUserRepository;
import ch.zli.m223.punchclock.repository.EntryRepository;
import ch.zli.m223.punchclock.repository.UserGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.format.datetime.joda.LocalDateTimeParser;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DatabaseSeeder {

    private ApplicationUserRepository applicationUserRepository;
    private EntryRepository entryRepository;
    private UserGroupRepository userGroupRepository;
    ApplicationUser applicationUser1 = new ApplicationUser();

    @Autowired
    public DatabaseSeeder(ApplicationUserRepository applicationUserRepository, EntryRepository entryRepository, UserGroupRepository userGroupRepository) {
        this.applicationUserRepository = applicationUserRepository;
        this.entryRepository = entryRepository;
        this.userGroupRepository = userGroupRepository;
    }

    @EventListener
    public void seed(ContextRefreshedEvent event) {
        seedUsersTable();
        seedEntryTable();
        seedUserGroupTable();
    }

    private void seedUsersTable() {
        applicationUser1.setUsername("admin1");
        applicationUser1.setPassword(new BCryptPasswordEncoder().encode("pw1"));
        ApplicationUser applicationUser2 = new ApplicationUser();
        applicationUser2.setUsername("user1");
        applicationUser2.setPassword(new BCryptPasswordEncoder().encode("pw1"));
        applicationUserRepository.save(applicationUser1);
        applicationUserRepository.save(applicationUser2);
    }

    private void seedEntryTable() {
        LocalDateTime time1 = LocalDateTime.of(2017, 2, 13, 15, 56);
        LocalDateTime time2 = LocalDateTime.of(2018, 3, 10, 10, 55);
        LocalDateTime time3 = LocalDateTime.of(2019, 4, 11, 11, 54);
        LocalDateTime time4 = LocalDateTime.of(2020, 5, 12, 12, 53);
        Entry entry1 = new Entry();
        entry1.setCheckIn(time1);
        entry1.setCheckOut(time2);
        Entry entry2 = new Entry();
        entry2.setCheckIn(time3);
        entry2.setCheckOut(time4);
        entry1.setApplicationUser(applicationUser1);
        entry2.setApplicationUser(applicationUser1);
        entryRepository.save(entry1);
        entryRepository.save(entry2);
    }
    private void seedUserGroupTable() {
        UserGroup userGroup1 = new UserGroup();
        UserGroup userGroup2 = new UserGroup();
        userGroup1.setUserGroup("Administratoren");
        userGroup2.setUserGroup("SuperSpecial Administratoren");
        userGroupRepository.save(userGroup1);
        userGroupRepository.save(userGroup2);
    }

}
