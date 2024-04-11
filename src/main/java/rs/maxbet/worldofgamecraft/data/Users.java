package rs.maxbet.worldofgamecraft.data;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@Data
public class Users {
    @Id
    private int id;
    private String role;
}
