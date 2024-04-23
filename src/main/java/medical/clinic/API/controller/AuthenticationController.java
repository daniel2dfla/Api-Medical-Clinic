package medical.clinic.API.controller;

import jakarta.validation.Valid;
import medical.clinic.API.dto.user.AuthenticationDataDTO;
import medical.clinic.API.entity.User;
import medical.clinic.API.infra.secutiry.DataTokenDTO;
import medical.clinic.API.infra.secutiry.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity login(@RequestBody @Valid AuthenticationDataDTO data){

        try{
            var authenticationToken = new UsernamePasswordAuthenticationToken(data.login(), data.password());
            var authentication = manager.authenticate(authenticationToken);

            var tokenJWT = tokenService.generateToken((User) authentication.getPrincipal());

            return ResponseEntity.ok(new DataTokenDTO(tokenJWT));

        } catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }
}
