package rs.maxbet.worldofgamecraft.service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import rs.maxbet.worldofgamecraft.dao.CharacterItemRepository;
import rs.maxbet.worldofgamecraft.dao.CharacterRepository;
import rs.maxbet.worldofgamecraft.dao.ItemRepository;
import rs.maxbet.worldofgamecraft.data.Character;
import rs.maxbet.worldofgamecraft.data.CharacterItem;
import rs.maxbet.worldofgamecraft.data.Item;
import rs.maxbet.worldofgamecraft.data.transport.DuelOutcomeEvent;
import rs.maxbet.worldofgamecraft.exception.CharacterItemAssociationNotFoundException;
import rs.maxbet.worldofgamecraft.exception.CharacterNotFoundException;
import rs.maxbet.worldofgamecraft.exception.ItemNotFoundException;

@Service
public class ItemService {
    @Autowired
    private final ItemRepository itemRepository;
    @Autowired
    private final CharacterRepository characterRepository;
    @Autowired
    private final CharacterItemRepository characterItemRepository;

    public ItemService(ItemRepository itemRepository, CharacterRepository characterRepository, CharacterItemRepository characterItemRepository) {
        this.itemRepository = itemRepository;
        this.characterRepository = characterRepository;
        this.characterItemRepository = characterItemRepository;
    }

    public void createItem(Item item) {
        this.itemRepository.save(item);
    }

    public Optional<Item> getItemById(Long id) {
        return this.itemRepository.findById(id);
    }

    public List<Item> getItems() {
        return this.itemRepository.findAll();
    }

    public void addItemToCharacter(int characterId, int itemId) {
        Character character = this.characterRepository.findById((long)characterId).orElseThrow(() -> new CharacterNotFoundException("Character with id " + characterId + " not found"));
        Item item = this.itemRepository.findById((long)itemId).orElseThrow(() -> new ItemNotFoundException("Item with id " + itemId + " not found"));
        CharacterItem characterItemAssociation = new CharacterItem(character, item);
        this.characterItemRepository.save(characterItemAssociation);
        evictCharacterCache((long) characterId);
    }

    public void giftItemToCharacter(Long sourceCharacterId, int targetCharacterId, int itemId) {
        this.characterRepository.findById(sourceCharacterId).orElseThrow(()
                -> new CharacterNotFoundException("Character with id " + sourceCharacterId + " not found"));
        Character targetCharacter = this.characterRepository.findById((long)targetCharacterId).orElseThrow(()
                -> new CharacterNotFoundException("Character with id " + targetCharacterId + " not found"));
        Item item = this.itemRepository.findById((long)itemId).orElseThrow(() -> new ItemNotFoundException("Item with id " + itemId + " not found"));
        CharacterItem characterItemAssociation = Optional.of(this.characterItemRepository.findByItemId(item.getId())).orElseThrow(() -> new CharacterItemAssociationNotFoundException("Item with id " + itemId + " not found"));
        this.characterItemRepository.delete(characterItemAssociation);
        evictCharacterCache(sourceCharacterId);
        this.addItemToCharacter(targetCharacter.getId(), item.getId());
        evictCharacterCache((long)targetCharacter.getId());
    }


    @CacheEvict(value = "character", key = "#id")
    public void evictCharacterCache(Long id) {
    }

    public void combatOutcomeIssueGift(DuelOutcomeEvent duelOutcomeEvent) {
        if (duelOutcomeEvent.getOutcome().equals("CHALLENGER_WON")) {
            Integer giftId = findRandomItemIdFromCharacter(duelOutcomeEvent.getChallengeeId());
            if (giftId == null) return; // No items to gift just exit
            giftItemToCharacter((long)duelOutcomeEvent.getChallengeeId(), duelOutcomeEvent.getChallengerId(), giftId);
        } else if (duelOutcomeEvent.getOutcome().equals("CHALLENGEE_WON")) {
            Integer giftId = findRandomItemIdFromCharacter(duelOutcomeEvent.getChallengerId());
            if (giftId == null) return; // No items to gift just exit
            giftItemToCharacter((long)duelOutcomeEvent.getChallengerId(), duelOutcomeEvent.getChallengeeId(), giftId);
        }
    }

    private Integer findRandomItemIdFromCharacter(Integer characterId) {
        List<Integer> items = characterItemRepository.findAllByCharacterId(characterId)
                .stream()
                .map(CharacterItem::getItem)
                .map(Item::getId)
                .toList();
        if (items.isEmpty()) {
            return null;
        }
        // Generate a random index within the range of the list size
        int randomIndex = new Random().nextInt(items.size());
        // Get a random item from the list
        return items.get(randomIndex);
    }
}
