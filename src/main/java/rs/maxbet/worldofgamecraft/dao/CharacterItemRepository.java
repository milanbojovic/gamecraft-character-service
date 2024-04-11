package rs.maxbet.worldofgamecraft.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import rs.maxbet.worldofgamecraft.data.CharacterItem;

public interface CharacterItemRepository extends JpaRepository<CharacterItem, Long> {
    List<CharacterItem> findAllByCharacterId(int characterId);

    CharacterItem findByItemId(int itemId);

    CharacterItem findByCharacterIdAndItemId(int characterId, Long itemId);
}
