package br.com.fiap.medsave.ProjectMedSave.insfrastructure.config;

import br.com.fiap.medsave.ProjectMedSave.domainmodel.UserSys;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Optional;

@Component
public class TokenConfig {

    private String secretKey = "secret";

    public String generateToken(UserSys userSys) {

        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        return JWT.create()
                .withClaim("userId", userSys.getId())
                .withSubject(userSys.getEmail())
                .withExpiresAt(Instant.now().plusSeconds(86400)) // valido por 24h
                .withIssuedAt(Instant.now())
                .sign(algorithm);
    }

    public Optional<JWTUserData> validateToken(String token) {

        try {
            Algorithm algorithm = Algorithm.HMAC256(secretKey);
            DecodedJWT decode = JWT.require(algorithm)
                    .build().verify(token);

            return Optional.of(JWTUserData.builder()
                    .userId(decode.getClaim("userId").asLong())
                    .email(decode.getSubject())
                    .build());
        } catch (JWTVerificationException exception) {
                return Optional.empty();
        }
    }
}
