package coop.bancocredicoop.omnited.controller;

import com.entrepids.psec.exceptions.PermissionDeniedException;
import com.entrepids.psec.exceptions.UserModuleException;
import com.entrepids.psec.security.client.services.UserContext;
import com.entrepids.psec.security.client.services.UserContextManager;
import coop.bancocredicoop.omnited.security.cas.CasService;
import coop.bancocredicoop.omnited.security.cas.TokenService;
import coop.bancocredicoop.omnited.domain.LoginRequest;
import coop.bancocredicoop.omnited.domain.LoginResponse;
import coop.bancocredicoop.omnited.domain.ServiceResponse;
import java.util.ArrayList;
import java.util.Collection;
import javax.xml.bind.JAXBException;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author mgiannini
 */
@RestController
//@RequestMapping("/cas")
public class CasController {
    @Autowired
    private CasService casService;

    @Autowired
    private TokenService tokenService;

    @PostMapping(value = "/login", consumes = "application/json")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest ticket) {
        final String responseStr = casService.validateTicket(ticket.getTicket());
        Collection roles = new ArrayList<>();
        
        System.out.println("Llego un intento de logueo: " + ticket.getTicket());
        
        if (responseStr != null) {
            try {
                final ServiceResponse sr = casService.getServiceResponse(responseStr);
                if (sr.success()) {
                    final String usr = sr.getAuthenticationSuccess().getUser();
                    try {
                        UserContext uc = UserContextManager.getInstance().getUserContext(usr, "OMNI");
                        if (
                                uc.canPerform("Omnited", "Admin") || 
                                uc.canPerform("Omnited", "Super") ||
                                uc.canPerform("Omnited", "Opera")
                                ) {
                            System.out.println("AUTORIZADO");
                            final String token = tokenService.generateToken(usr, uc);
                            return ResponseEntity.ok(new LoginResponse(token));
                        }
                        System.out.println("DESAUTORIZADO");
                        Logger.getLogger(CasController.class).log(Level.ERROR, ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
                        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
                    } catch (UserModuleException | PermissionDeniedException e) {
                        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
                    }
                }
            } catch (JAXBException e) {
                Logger.getLogger(CasController.class).log(Level.ERROR, e);
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @GetMapping(value = "/url", produces = "text/plain")
    public ResponseEntity<String> url() {
        return ResponseEntity.ok(casService.casLoginUrl());
    }
}
