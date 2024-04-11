package rs.maxbet.worldofgamecraft.data;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "character_item")
@IdClass(CharacterItemId.class)
public class CharacterItem {
    @Id
    @ManyToOne
    @JoinColumn(name = "character_id")
    private Character character;
    @Id
    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;
}
