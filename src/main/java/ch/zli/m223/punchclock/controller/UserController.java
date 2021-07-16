package ch.zli.m223.punchclock.controller;

import ch.zli.m223.punchclock.domain.ApplicationUser;
import ch.zli.m223.punchclock.domain.Entry;
import ch.zli.m223.punchclock.repository.ApplicationUserRepository;
import ch.zli.m223.punchclock.service.ApplicationUserService;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private ApplicationUserRepository applicationUserRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private ApplicationUserService applicationUserService;

    public UserController(ApplicationUserRepository applicationUserRepository,
                          BCryptPasswordEncoder bCryptPasswordEncoder, ApplicationUserService applicationUserService) {
        this.applicationUserRepository = applicationUserRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.applicationUserService = applicationUserService;
    }
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ApplicationUser> getAllApplicationUser() {
        return applicationUserService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApplicationUser createApplicationUser(@Valid @RequestBody ApplicationUser applicationUser) {
        //applicationUser.setPassword(bCryptPasswordEncoder.encode(applicationUser.getPassword()));
        return applicationUserService.createApplicationUser(applicationUser);
    }
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteApplicationUser(@PathVariable("id") long id) {
        applicationUserService.deleteApplicationUser(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ApplicationUser updateApplicationUser(@Valid @RequestBody ApplicationUser applicationUser) {
        //applicationUser.setPassword(bCryptPasswordEncoder.encode(applicationUser.getPassword()));
        return applicationUserService.updateApplicationUser(applicationUser);
    }

    @PostMapping("/sign-up")
    public void signUp(@RequestBody ApplicationUser user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        applicationUserRepository.save(user);
    }

}
