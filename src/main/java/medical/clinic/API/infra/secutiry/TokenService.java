package medical.clinic.API.infra.secutiry;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import medical.clinic.API.entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    public String generateToken(User user){
        try {
            var algorithm = Algorithm.HMAC256(secret);
            return  JWT.create()
                    .withIssuer("API Medical Clinic")
                    .withSubject(user.getLogin())
                    .withExpiresAt(dataExpiration())
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            throw new RuntimeException("Error generating jwt token", exception);
        }
    }

    public String getSubject(String tokenJWT){
        try {
            var algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("API Medical Clinic")
                    .build()
                    .verify(tokenJWT)
                    .getSubject();

        } catch (JWTVerificationException exception){
            throw new RuntimeException("Invalid or expired token");
        }
    }
    private Instant dataExpiration(){

        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
