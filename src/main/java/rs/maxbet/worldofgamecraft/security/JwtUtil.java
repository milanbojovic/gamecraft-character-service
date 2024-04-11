package rs.maxbet.worldofgamecraft.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rs.maxbet.worldofgamecraft.data.Users;
import rs.maxbet.worldofgamecraft.service.UserService;

@Component
public class JwtUtil {
    @Autowired
    private UserService userService;
    private static final String SECRET = "accounts-service-secret-key";

    public JwtUtil() {
    }

    public Long extractId(String token) {
        return (Long)((Claims)Jwts.parser().setSigningKey("accounts-service-secret-key").parseClaimsJws(token).getBody()).get("id", Long.class);
    }

    public String extractRole(String token) {
        return (String)((Claims)Jwts.parser().setSigningKey("accounts-service-secret-key").parseClaimsJws(token).getBody()).get("role", String.class);
    }

    public boolean validateToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey("accounts-service-secret-key").parseClaimsJws(token);
            Users user = this.userService.getUserById(this.extractId(token));
            return user != null && !((Claims)claims.getBody()).getExpiration().before(new Date());
        } catch (IllegalArgumentException | JwtException var4) {
            return false;
        }
    }
}
