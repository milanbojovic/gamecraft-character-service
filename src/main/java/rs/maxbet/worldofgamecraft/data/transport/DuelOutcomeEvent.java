package rs.maxbet.worldofgamecraft.data.transport;

import lombok.Data;

@Data
public class DuelOutcomeEvent {
    private int id;
    private int challengerId;
    private int challengeeId;
    private String outcome;
}
