//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package rs.maxbet.worldofgamecraft;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import java.io.Serializable;

@SpringBootApplication
@EnableCaching
public class WorldOfGamecraftCharacterService implements Serializable {

    public static void main(String[] args) {
        SpringApplication.run(WorldOfGamecraftCharacterService.class, args);
    }
}
