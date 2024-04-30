package rs.maxbet.worldofgamecraft.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import rs.maxbet.worldofgamecraft.data.Users;
import rs.maxbet.worldofgamecraft.service.UserService;

@Component
public class UserRegistrationListener {

    private static final Logger logger = LoggerFactory.getLogger(UserRegistrationListener.class);

    @Autowired
    private final UserService userService;
    @Value("${rabbitmq.queue.character.registration}")
    String queueCharacterUserRegistration;

    public UserRegistrationListener(UserService userService) {
        this.userService = userService;
    }

    @RabbitListener(queues = {"${rabbitmq.queue.character.registration}"})
    public void handleMessage(Users user) {
        logger.info("Received user registration event: " + user);
        this.userService.saveUser(user);
    }
}
