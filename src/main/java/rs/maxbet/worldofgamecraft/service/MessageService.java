package rs.maxbet.worldofgamecraft.service;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import rs.maxbet.worldofgamecraft.data.transport.CharacterCreationEvent;

import java.util.logging.Logger;

@Service
public class MessageService {

    private final Logger logger = Logger.getLogger(MessageService.class.getName());
    
    private final AmqpTemplate amqpTemplate;

    @Value("${rabbitmq.queue.character.creation}")
    String queueCharacterCreation;

    public MessageService(RabbitTemplate amqpTemplate) {
        this.amqpTemplate = amqpTemplate;
    }

    public void publishCharacterCreation(CharacterCreationEvent characterCreationEvent) {
        logger.info("Publishing character creation event to queue: " + queueCharacterCreation + " with user: " + characterCreationEvent.getCreatedBy());
        amqpTemplate.convertAndSend("", queueCharacterCreation, characterCreationEvent);
    }
}