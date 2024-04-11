package rs.maxbet.worldofgamecraft.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.maxbet.worldofgamecraft.data.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {
    Item findByName(String name);
}
