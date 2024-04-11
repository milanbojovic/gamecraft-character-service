package rs.maxbet.worldofgamecraft.controler;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import rs.maxbet.worldofgamecraft.data.Item;
import rs.maxbet.worldofgamecraft.data.representation.ItemShort;
import rs.maxbet.worldofgamecraft.service.ItemService;

@RestController
@RequestMapping({"/api/items"})
public class ItemController {
    @Autowired
    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PreAuthorize("hasRole('ROLE_GameMaster')")
    @GetMapping({""})
    public List<ItemShort> getAllItems() {
        return this.itemService.getItems().stream().map(ItemShort::new).toList();
    }

    @PreAuthorize("hasRole('ROLE_GameMaster')")
    @GetMapping({"/{id}"})
    public ResponseEntity<?> getItemById(@PathVariable Long id) {
        Optional<Item> itemOpt = this.itemService.getItemById(id);
        if (itemOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Item with id " + id + " not found");
        } else {
            Item item = (Item)itemOpt.get();
            String suffix = "";
            int maxStat = Math.max(Math.max(item.getBonusStrength(), item.getBonusAgility()), Math.max(item.getBonusIntelligence(), item.getBonusFaith()));
            if (maxStat == item.getBonusStrength()) {
                suffix = " Of The Bear";
            } else if (maxStat == item.getBonusAgility()) {
                suffix = " Of The Cobra";
            } else if (maxStat == item.getBonusIntelligence()) {
                suffix = " Of The Owl";
            } else if (maxStat == item.getBonusFaith()) {
                suffix = " Of The Unicorn";
            }

            String var10001 = item.getName();
            item.setName(var10001 + suffix);
            return ResponseEntity.ok(item);
        }
    }

    @PostMapping({""})
    public void createItem(@RequestBody Item item) {
        this.itemService.createItem(item);
    }

    @PostMapping({"/grant"})
    public void addItemToCharacter(@RequestParam int characterId, @RequestParam int itemId) {
        this.itemService.addItemToCharacter(characterId, itemId);
    }

    @PostMapping({"/gift"})
    public void moveItemToNewOwner(@RequestParam Long sourceCharacterId, @RequestParam int targetCharacterId, @RequestParam int itemId) {
        this.itemService.giftItemToCharacter(sourceCharacterId, targetCharacterId, itemId);
    }
}
