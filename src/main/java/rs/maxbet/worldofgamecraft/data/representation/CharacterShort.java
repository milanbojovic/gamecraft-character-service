package rs.maxbet.worldofgamecraft.data.representation;

import lombok.Data;
import rs.maxbet.worldofgamecraft.data.Character;

@Data
public class CharacterShort {
    private String name;
    private int health;
    private int mana;

    public CharacterShort(Character character) {
        this.name = character.getName();
        this.health = character.getHealth();
        this.mana = character.getMana();
    }
}
