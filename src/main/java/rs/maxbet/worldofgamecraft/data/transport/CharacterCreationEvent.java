package rs.maxbet.worldofgamecraft.data.transport;

import lombok.Data;
import rs.maxbet.worldofgamecraft.data.Character;
import rs.maxbet.worldofgamecraft.data.CharacterClass;

@Data
public class CharacterCreationEvent {
    private int id;
    private String name;
    private int health;
    private int mana;
    private int baseStrength;
    private int baseAgility;
    private int baseIntelligence;
    private int baseFaith;
    private CharacterClass characterClass;
    private int createdBy;

    public CharacterCreationEvent(Character character) {
        this.id = character.getId();
        this.name = character.getName();
        this.health = character.getHealth();
        this.mana = character.getMana();
        this.baseStrength = character.getBaseStrength();
        this.baseAgility = character.getBaseAgility();
        this.baseIntelligence = character.getBaseIntelligence();
        this.baseFaith = character.getBaseFaith();
    }
}
