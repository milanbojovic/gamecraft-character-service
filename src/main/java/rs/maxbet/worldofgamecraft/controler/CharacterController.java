//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package rs.maxbet.worldofgamecraft.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import rs.maxbet.worldofgamecraft.data.Character;
import rs.maxbet.worldofgamecraft.data.Item;
import rs.maxbet.worldofgamecraft.data.Users;
import rs.maxbet.worldofgamecraft.data.representation.CharacterDetailes;
import rs.maxbet.worldofgamecraft.data.representation.CharacterShort;
import rs.maxbet.worldofgamecraft.data.transport.CharacterCreationEvent;
import rs.maxbet.worldofgamecraft.service.CharacterService;
import rs.maxbet.worldofgamecraft.service.MessageService;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping({"/api"})
public class CharacterController {
    @Autowired
    private final CharacterService characterService;
    @Autowired
    private final MessageService messageService;

    public CharacterController(CharacterService characterService, MessageService messageService) {
        this.characterService = characterService;
        this.messageService = messageService;
    }

    @PreAuthorize("hasRole('ROLE_GameMaster')")
    @GetMapping({"/character"})
    public List<CharacterShort> getAllCharacters() {
        return this.characterService.getCharacters().stream().map(CharacterShort::new).toList();
    }

    @PreAuthorize("hasRole('ROLE_GameMaster') || hasRole('ROLE_User')")
    @GetMapping({"/character/{id}"})
    public ResponseEntity<?> getCharacterById(@PathVariable Long id) {
        Optional<Character> optCharacter = this.characterService.getCharacterById(id);
        if (optCharacter.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("error", "Character with id " + id + " not found"));
        } else {
            Users user = (Users)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if ("User".equals(user.getRole()) && user.getId() != (optCharacter.get()).getCreatedBy()) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Collections.singletonMap("error", "You are not the owner of this character"));
            } else {
                Character character = this.characterService.calculateStats(optCharacter.get());
                return ResponseEntity.ok(new CharacterDetailes(character, this.getItems(character)));
            }
        }
    }

    private List<Integer> getItems(Character character) {
        return this.characterService.getItemsForCharacter(character).stream().map(Item::getId).toList();
    }

    @PostMapping({"/character"})
    public void createCharacter(@RequestBody Character character) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object user = authentication.getPrincipal();
        if (user instanceof Users) {
            character.setCreatedBy(((Users)user).getId());
        }

        this.characterService.createCharacter(character);
        this.messageService.publishCharacterCreation(new CharacterCreationEvent(character));
    }

    @GetMapping({"/characters"})
    public List<Character> getAllCharactersFromDb() {
        return this.characterService.getCharacters();
    }
}
