package coop.bancocredicoop.omnited.security.cas;

import com.entrepids.psec.exceptions.PermissionDeniedException;
import com.entrepids.psec.security.client.services.UserContext;
import coop.bancocredicoop.omnited.security.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    public String generateToken(String usuario, UserContext uc) throws PermissionDeniedException {
        //TODO storage
        return jwtTokenUtil.generateToken(usuario, uc);
    }
}