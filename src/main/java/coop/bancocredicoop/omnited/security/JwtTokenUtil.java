package coop.bancocredicoop.omnited.security;

import com.entrepids.psec.exceptions.PermissionDeniedException;
import com.entrepids.psec.security.client.services.UserContext;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

@Component
public class JwtTokenUtil implements Serializable {

    public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;

    @Value("${jwt.secret}")
    private String secret;//Base64

    /**
     * Tries to parse specified String as a JWT token. If successful, returns
     * User object with username, id and role prefilled (extracted from token).
     * If unsuccessful (token is invalid or not containing all required user
     * properties), simply returns null.
     *
     * @param token the JWT token to parse
     * @return the User object extracted from specified token or null if a token
     * is invalid.
     */
    public JwtUser parseToken(String token) {

        JwtUser u = null;
        try {
            Claims body = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
            u = new JwtUser();
            u.setUsername(body.getSubject());
            u.setId((String) body.get("userId"));
            u.setRole((String) body.get("role"));
        } catch (JwtException e) {
            Logger.getLogger(JwtTokenUtil.class).log(Level.FATAL, e);
        }
        return u;
    }

    public String generateToken(String usuario, UserContext uc) throws PermissionDeniedException {
        Map<String, Object> claims = new HashMap<>();
        String role = "";

        if (uc.canPerform("Omnited", "Admin")) {
            role = "Administrador";
        } else if (uc.canPerform("Omnited", "Super")) {
            role = "Supervisor";
        } else if (uc.canPerform("Omnited", "Opera")) {
            role = "Operador";
        }

        claims.put("role", role);
        return doGenerateToken(claims, usuario);
    }

    private String doGenerateToken(Map<String, Object> claims, String subject) {

        return Jwts.builder()
                //.setHeaderParam("typ", "JWT")
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }
}
