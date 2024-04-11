package rs.maxbet.worldofgamecraft.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import rs.maxbet.worldofgamecraft.data.Users;
import rs.maxbet.worldofgamecraft.service.UserService;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {
    private static final Logger logger = LoggerFactory.getLogger(JwtRequestFilter.class);
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserService userService;

    public JwtRequestFilter() {
    }

    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = request.getHeader("Authorization");
        Long userId = null;
        String role = null;
        String jwt = null;
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwt = authorizationHeader.substring(7);
            userId = this.jwtUtil.extractId(jwt);
            role = this.jwtUtil.extractRole(jwt);
            logger.info("Authentication method JWT called with userId: " + userId + " and role: " + role);
            logger.info("Bearer token: " + jwt);
        }

        if (userId != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            Users users = this.userService.getUserById(userId);
            if (this.jwtUtil.validateToken(jwt)) {
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(users, (Object)null, List.of(new SimpleGrantedAuthority(role)));
                usernamePasswordAuthenticationToken.setDetails((new WebAuthenticationDetailsSource()).buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }

        filterChain.doFilter(request, response);
    }
}
