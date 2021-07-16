package ch.zli.m223.punchclock.controller;

import ch.zli.m223.punchclock.domain.ApplicationUser;
import ch.zli.m223.punchclock.domain.Entry;
import ch.zli.m223.punchclock.service.ApplicationUserService;
import ch.zli.m223.punchclock.service.EntryService;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

/*
    Hier ist der Startpunkt
    Alles was darunter geschrieben wird erreicht man mit dem /entries
    Es wird so geregelt in der Applikation das nur der Nutzer der eine Entry erstellt hat diese auch bearbeiten, löschen kann
*/

@RestController
@RequestMapping("/entries")
public class EntryController {
    private EntryService entryService;
    private ApplicationUserService applicationUserService;

    public EntryController(EntryService entryService, ApplicationUserService applicationUserService) {
        this.entryService = entryService;
        this.applicationUserService = applicationUserService;
    }

/*
    Hier werden alle Entiries geholt. Es wird zuerst geprüft ob der Entry überhaupt zum User passt.
    Falls das nicht der Fall ist wird der Entry gelöscht und nicht angezeigt
*/

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Entry> getAllEntries(Principal principal) {
        List<Entry> temp;
        temp = entryService.findAll();
        for(int i = 0; i < temp.size(); i++) {
            if(!temp.get(i).getApplicationUser().getUsername().equals(principal.getName())){
                temp.remove(i);
            }
        }

        return temp;
    }

    /*
    Hier werden alle Entiries erstellt. Dabei wird der Enrtry auch gleich dem User zugeordnet.
*/

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Entry createEntry(@Valid @RequestBody Entry entry, Principal principal) {
        entry.setApplicationUser(applicationUserService.findbyUsername(principal.getName()));
        return entryService.createEntry(entry);
    }

       /*
    Hier werden alle Entiries gelöscht. Das kann nur der User der diesen erstellt hat
*/

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEntries(@PathVariable("id") long id, Principal principal) {
        entryService.deleteEntry(id);
        //kucken ob es seine ist
       //applicationUserService.findbyUsername(principal.getName()).deleteEntry(entryService.findEntrybyID(id));
    }
    /*
    Hier werden alle Entiries geupdatet. Das kann nur der User der diesen erstellt hat
*/

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Entry updateEntry(@Valid @RequestBody Entry entry, Principal principal) {
        entry.setApplicationUser(applicationUserService.findbyUsername(principal.getName()));
        return entryService.updateEntry(entry);
    }
}