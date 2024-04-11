package rs.maxbet.worldofgamecraft.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.maxbet.worldofgamecraft.dao.CharacterItemRepository;
import rs.maxbet.worldofgamecraft.dao.CharacterRepository;
import rs.maxbet.worldofgamecraft.data.Character;
import rs.maxbet.worldofgamecraft.data.CharacterItem;
import rs.maxbet.worldofgamecraft.data.Item;

@Service
public class CharacterService {
    @Autowired
    private final CharacterRepository characterRepository;
    @Autowired
    private CharacterItemRepository characterItemRepository;

    public CharacterService(CharacterRepository characterRepository) {
        this.characterRepository = characterRepository;
    }

    public void createCharacter(Character character) {
        this.characterRepository.save(character);
        System.out.println("Character created: " + character);
    }

    public Optional<Character> getCharacterById(Long id) {
        return this.characterRepository.findById(id);
    }

    public List<Character> getCharacters() {
        return this.characterRepository.findAll();
    }

    public List<Item> getItemsForCharacter(Character character) {
        return this.characterItemRepository.findAllByCharacterId(character.getId()).stream().map(CharacterItem::getItem).toList();
    }

    public Character calculateStats(Character character) {
        List<Item> items = this.getItemsForCharacter(character);
        int bonusStrengthSum = items.stream().mapToInt(Item::getBonusStrength).sum();
        int bonusAgilitySum = items.stream().mapToInt(Item::getBonusAgility).sum();
        int bonusIntelligenceSum = items.stream().mapToInt(Item::getBonusIntelligence).sum();
        int bonusFaithSum = items.stream().mapToInt(Item::getBonusFaith).sum();
        character.setBaseStrength(character.getBaseStrength() + bonusStrengthSum);
        character.setBaseAgility(character.getBaseAgility() + bonusAgilitySum);
        character.setBaseIntelligence(character.getBaseIntelligence() + bonusIntelligenceSum);
        character.setBaseFaith(character.getBaseFaith() + bonusFaithSum);
        return character;
    }
}
