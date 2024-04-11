package rs.maxbet.worldofgamecraft.controler;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.maxbet.worldofgamecraft.data.CharacterClass;
import rs.maxbet.worldofgamecraft.service.CharacterClassService;

@RestController
@RequestMapping({"/api"})
public class CharacterClassController {
    @Autowired
    private final CharacterClassService characterClassService;

    public CharacterClassController(CharacterClassService characterClassService) {
        this.characterClassService = characterClassService;
    }

    @GetMapping({"/class"})
    public List<CharacterClass> getAllCharacterClasses() {
        return this.characterClassService.getCharacterClasses();
    }

    @GetMapping({"/class/{id}"})
    public ResponseEntity<?> getCharacterClassById(@PathVariable Long id) {
        Optional<CharacterClass> characterClass = this.characterClassService.getCharacterClassById(id);
        return characterClass.isEmpty() ? ResponseEntity.status(HttpStatus.NOT_FOUND).body("Character class with id " + id + " not found") : ResponseEntity.ok((CharacterClass)characterClass.get());
    }

    @PostMapping({"/class"})
    public void createCharacterClass(@RequestBody CharacterClass characterClass) {
        this.characterClassService.createCharacterClass(characterClass);
    }
}
