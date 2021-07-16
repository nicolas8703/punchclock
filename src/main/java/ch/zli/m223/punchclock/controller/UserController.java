package ch.zli.m223.punchclock.controller;

import ch.zli.m223.punchclock.domain.ApplicationUser;
import ch.zli.m223.punchclock.domain.Entry;
import ch.zli.m223.punchclock.domain.UserGroup;
import ch.zli.m223.punchclock.repository.ApplicationUserRepository;
import ch.zli.m223.punchclock.repository.UserGroupRepository;
import ch.zli.m223.punchclock.service.ApplicationUserService;
import ch.zli.m223.punchclock.service.UserGroupService;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

      /*
        Hier ist der Startpunkt
    Alles was darunter geschrieben wird erreicht man mit dem /users
    Hier ist alles normal und es hat keine grossen besonderheiten.
*/

@RestController
@RequestMapping("/users")
public class UserController {

    private ApplicationUserRepository applicationUserRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private ApplicationUserService applicationUserService;
    private UserGroupService userGroupService;

    public UserController(ApplicationUserRepository applicationUserRepository,
                          BCryptPasswordEncoder bCryptPasswordEncoder, ApplicationUserService applicationUserService, UserGroupService userGroupService) {
        this.applicationUserRepository = applicationUserRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.applicationUserService = applicationUserService;
        this.userGroupService = userGroupService;
    }


    /*
Alle werden gehollt.

*/
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ApplicationUser> getAllApplicationUser() {
        List<ApplicationUser> temp = applicationUserRepository.findByNameEndsWith("n");
        for (int i = 0; i < temp.size(); i++) {
            System.out.println(temp.get(i).getUsername());
        }
        return applicationUserService.findAll();
    }

    /*
Alle werden Sachen erstellt.

*/
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApplicationUser createApplicationUser(@Valid @RequestBody ApplicationUser applicationUser) {
        applicationUser.setPassword(bCryptPasswordEncoder.encode(applicationUser.getPassword()));
        //if (!userGroupService.findAll().contains(applicationUser.getUserGroup())){
         //   userGroupService.createUserGroup(new UserGroup(applicationUser.getUserGroup().getUserGroup()));
        //}
        return applicationUserService.createApplicationUser(applicationUser);
    }

/*
delete

*/
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteApplicationUser(@PathVariable("id") long id) {
        applicationUserService.deleteApplicationUser(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ApplicationUser updateApplicationUser(@Valid @RequestBody ApplicationUser applicationUser) {
        applicationUser.setPassword(bCryptPasswordEncoder.encode(applicationUser.getPassword()));
        return applicationUserService.updateApplicationUser(applicationUser);
    }
    /*
   sign up klasse hier wird das einloggen gemacht der Passwort wird speziell verschlüsselt

    */
    @PostMapping("/sign-up")
    public void signUp(@RequestBody ApplicationUser user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        applicationUserRepository.save(user);
    }

}
