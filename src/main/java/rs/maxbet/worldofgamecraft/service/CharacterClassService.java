package rs.maxbet.worldofgamecraft.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.maxbet.worldofgamecraft.dao.CharacterClassRepository;
import rs.maxbet.worldofgamecraft.data.CharacterClass;

@Service
public class CharacterClassService {
    @Autowired
    private final CharacterClassRepository characterClassRepository;

    public CharacterClassService(CharacterClassRepository characterClassRepository) {
        this.characterClassRepository = characterClassRepository;
    }

    public void createCharacterClass(CharacterClass characterClass) {
        this.characterClassRepository.save(characterClass);
    }

    public Optional<CharacterClass> getCharacterClassById(Long id) {
        return this.characterClassRepository.findById(id);
    }

    public List<CharacterClass> getCharacterClasses() {
        return this.characterClassRepository.findAll();
    }
}
