package ch.zli.m223.punchclock.service;

import ch.zli.m223.punchclock.domain.ApplicationUser;
import ch.zli.m223.punchclock.domain.Entry;
import ch.zli.m223.punchclock.domain.UserGroup;
import ch.zli.m223.punchclock.repository.ApplicationUserRepository;
import ch.zli.m223.punchclock.repository.EntryRepository;
import ch.zli.m223.punchclock.repository.UserGroupRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Collections.emptyList;

@Service
public class UserGroupService {
    private UserGroupRepository userGroupRepository;

    public UserGroupService(UserGroupRepository userGroupRepository) {
        this.userGroupRepository = userGroupRepository;
    }


    public UserGroup createUserGroup(UserGroup userGroup) {
        return userGroupRepository.saveAndFlush(userGroup);
    }

    public List<UserGroup> findAll() {
        return userGroupRepository.findAll();
    }

    public void deleteUserGroup(long id) {
        userGroupRepository.deleteById(id);
    }
    public UserGroup updateUserGroup(UserGroup userGroup) {
        return userGroupRepository.save(userGroup);
    }
}