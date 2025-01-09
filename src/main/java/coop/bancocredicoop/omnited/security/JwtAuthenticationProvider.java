package coop.bancocredicoop.omnited.security;

import com.entrepids.psec.exceptions.PermissionDeniedException;
import com.entrepids.psec.exceptions.UserModuleException;
import com.entrepids.psec.security.client.services.UserContext;
import com.entrepids.psec.security.client.services.UserContextManager;
import coop.bancocredicoop.omnited.model.AuthenticatedUser;
import coop.bancocredicoop.omnited.model.JwtAuthenticationToken;
import io.jsonwebtoken.MalformedJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import java.util.List;

@Component
public class JwtAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    public boolean supports(Class<?> authentication) {
        return (JwtAuthenticationToken.class.isAssignableFrom(authentication));
    }

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
    }

    @Override
    protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        JwtAuthenticationToken jwtAuthenticationToken = (JwtAuthenticationToken) authentication;
        String token = jwtAuthenticationToken.getToken();

        JwtUser parsedUser = jwtTokenUtil.parseToken(token);

        if (parsedUser == null) {
            throw new MalformedJwtException("JWT token invalid");
        }
        String role = parsedUser.getRole();

        try {
            if (role.equals("USER")) {
                UserContext uc = UserContextManager.getInstance().getUserContext(parsedUser.getUsername(), "SGDT");
                if (uc.canPerform("SgdtGuiaTramites", "INGR")) {
                    role = role + ",INGRESANTE";
                }
            }
        } catch (UserModuleException | PermissionDeniedException e) {
            Logger.getLogger(JwtAuthenticationProvider.class).log(Level.FATAL, e);
        }

        List<GrantedAuthority> authorityList = AuthorityUtils.commaSeparatedStringToAuthorityList(role);

        return new AuthenticatedUser(parsedUser.getId(), parsedUser.getUsername(), token, authorityList);
    }
}
