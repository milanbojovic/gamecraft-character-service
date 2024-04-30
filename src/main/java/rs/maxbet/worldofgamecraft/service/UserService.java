package rs.maxbet.worldofgamecraft.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.maxbet.worldofgamecraft.dao.UsersRepository;
import rs.maxbet.worldofgamecraft.data.Users;

@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private final UsersRepository usersRepository;

    public UserService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public Users getUserById(Long id) {
        logger.debug("Getting user by id: " + id);
        return this.usersRepository.findById(id).orElse(null);
    }

    public List<Users> getAllUsers() {
        logger.debug("Getting all users");
        return this.usersRepository.findAll();
    }

    public Users saveUser(Users user) {
        logger.info("Saving user: " + user);
        return this.usersRepository.save(user);
    }
}
