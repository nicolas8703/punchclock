package ch.zli.m223.punchclock.controller;

import ch.zli.m223.punchclock.domain.Entry;
import ch.zli.m223.punchclock.domain.UserGroup;
import ch.zli.m223.punchclock.service.EntryService;
import ch.zli.m223.punchclock.service.UserGroupService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/usergroup")
public class UserGroupController {
    private UserGroupService userGroupService;

    public UserGroupController(UserGroupService userGroupService) {
        this.userGroupService = userGroupService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<UserGroup> getAllUserGroups() {
        return userGroupService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserGroup createUserGroup(@Valid @RequestBody UserGroup userGroup) {
        return userGroupService.createUserGroup(userGroup);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUserGroup(@PathVariable("id") long id) {
        userGroupService.deleteUserGroup(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserGroup updateEntry(@Valid @RequestBody UserGroup userGroup) {
        return userGroupService.updateUserGroup(userGroup);
    }
}