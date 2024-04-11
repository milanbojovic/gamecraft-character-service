package rs.maxbet.worldofgamecraft.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.maxbet.worldofgamecraft.dao.CharacterItemRepository;
import rs.maxbet.worldofgamecraft.dao.CharacterRepository;
import rs.maxbet.worldofgamecraft.dao.ItemRepository;
import rs.maxbet.worldofgamecraft.data.Character;
import rs.maxbet.worldofgamecraft.data.CharacterItem;
import rs.maxbet.worldofgamecraft.data.Item;
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
        Character character = (Character)this.characterRepository.findById((long)characterId).orElseThrow(() -> {
            return new CharacterNotFoundException("Character with id " + characterId + " not found");
        });
        Item item = (Item)this.itemRepository.findById((long)itemId).orElseThrow(() -> {
            return new ItemNotFoundException("Item with id " + itemId + " not found");
        });
        CharacterItem characterItemAssociation = new CharacterItem(character, item);
        this.characterItemRepository.save(characterItemAssociation);
    }

    public void giftItemToCharacter(Long sourceCharacterId, int targetCharacterId, int itemId) {
        Character var10000 = (Character)this.characterRepository.findById(sourceCharacterId).orElseThrow(() -> {
            return new CharacterNotFoundException("Character with id " + sourceCharacterId + " not found");
        });
        Character targetCharacter = (Character)this.characterRepository.findById((long)targetCharacterId).orElseThrow(() -> {
            return new CharacterNotFoundException("Character with id " + targetCharacterId + " not found");
        });
        Item item = (Item)this.itemRepository.findById((long)itemId).orElseThrow(() -> {
            return new ItemNotFoundException("Item with id " + itemId + " not found");
        });
        CharacterItem characterItemAssociation = (CharacterItem)Optional.of(this.characterItemRepository.findByItemId(item.getId())).orElseThrow(() -> {
            return new CharacterItemAssociationNotFoundException("Item with id " + itemId + " not found");
        });
        this.characterItemRepository.delete(characterItemAssociation);
        this.addItemToCharacter(targetCharacter.getId(), item.getId());
    }
}
