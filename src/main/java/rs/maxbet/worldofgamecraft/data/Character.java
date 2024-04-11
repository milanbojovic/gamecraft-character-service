package rs.maxbet.worldofgamecraft.data;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table
public class Character {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private int id;
    private String name;
    private int health;
    private int mana;
    private int baseStrength;
    private int baseAgility;
    private int baseIntelligence;
    private int baseFaith;
    @ManyToOne
    @JoinColumn(
            name = "character_class_id"
    )
    private CharacterClass characterClass;
    private int createdBy;

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public int getHealth() {
        return this.health;
    }

    public int getMana() {
        return this.mana;
    }

    public int getBaseStrength() {
        return this.baseStrength;
    }

    public int getBaseAgility() {
        return this.baseAgility;
    }

    public int getBaseIntelligence() {
        return this.baseIntelligence;
    }

    public int getBaseFaith() {
        return this.baseFaith;
    }

    public CharacterClass getCharacterClass() {
        return this.characterClass;
    }

    public int getCreatedBy() {
        return this.createdBy;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public void setHealth(final int health) {
        this.health = health;
    }

    public void setMana(final int mana) {
        this.mana = mana;
    }

    public void setBaseStrength(final int baseStrength) {
        this.baseStrength = baseStrength;
    }

    public void setBaseAgility(final int baseAgility) {
        this.baseAgility = baseAgility;
    }

    public void setBaseIntelligence(final int baseIntelligence) {
        this.baseIntelligence = baseIntelligence;
    }

    public void setBaseFaith(final int baseFaith) {
        this.baseFaith = baseFaith;
    }

    public void setCharacterClass(final CharacterClass characterClass) {
        this.characterClass = characterClass;
    }

    public void setCreatedBy(final int createdBy) {
        this.createdBy = createdBy;
    }

    public Character() {
    }

    public Character(final int id, final String name, final int health, final int mana, final int baseStrength, final int baseAgility, final int baseIntelligence, final int baseFaith, final CharacterClass characterClass, final int createdBy) {
        this.id = id;
        this.name = name;
        this.health = health;
        this.mana = mana;
        this.baseStrength = baseStrength;
        this.baseAgility = baseAgility;
        this.baseIntelligence = baseIntelligence;
        this.baseFaith = baseFaith;
        this.characterClass = characterClass;
        this.createdBy = createdBy;
    }
}
