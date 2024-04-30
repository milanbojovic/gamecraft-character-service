package rs.maxbet.worldofgamecraft.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import rs.maxbet.worldofgamecraft.data.transport.DuelOutcomeEvent;
import rs.maxbet.worldofgamecraft.service.ItemService;

@Component
public class CombatOutcomeListener {

    private static final Logger logger = LoggerFactory.getLogger(CombatOutcomeListener.class);

    @Autowired
    private final ItemService itemService;
    @Value("${rabbitmq.queue.combat.outcome}")
    String queueCombatOutcome;

    public CombatOutcomeListener(ItemService itemService) {
        this.itemService = itemService;
    }


    @RabbitListener(queues = "${rabbitmq.queue.combat.outcome}")
    public void handleMessage(DuelOutcomeEvent duelOutcomeEvent) {
        logger.info("Received combat outcome event: " + duelOutcomeEvent);
        itemService.combatOutcomeIssueGift(duelOutcomeEvent);
    }
}
