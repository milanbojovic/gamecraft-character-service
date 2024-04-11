package rs.maxbet.worldofgamecraft.data;

import lombok.Data;

import java.io.Serializable;

@Data
public class CharacterItemId implements Serializable {
    private Integer character;
    private Integer item;
}
