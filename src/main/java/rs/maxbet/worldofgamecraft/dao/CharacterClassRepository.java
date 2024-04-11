package rs.maxbet.worldofgamecraft.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.maxbet.worldofgamecraft.data.CharacterClass;

public interface CharacterClassRepository extends JpaRepository<CharacterClass, Long> {
    CharacterClass findByName(String name);
}
