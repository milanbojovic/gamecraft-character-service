package rs.maxbet.worldofgamecraft.data.representation;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import rs.maxbet.worldofgamecraft.data.Item;

@Entity
@Table
@Data
@NoArgsConstructor
public class ItemShort {
    @Id
    private int id;
    private String name;
    private String description;

    public ItemShort(Item item) {
        this.id = item.getId();
        this.name = item.getName();
        this.description = item.getDescription();
    }
}
