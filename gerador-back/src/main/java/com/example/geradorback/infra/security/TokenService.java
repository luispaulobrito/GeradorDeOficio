package com.example.geradorback.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.example.geradorback.domain.User;
import com.example.geradorback.services.error.ConstantesUtil;
import com.example.geradorback.services.error.NegocioException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;

@Service
public class TokenService {
    @Value("${api.security.token.secret}")
    private String secret;

    @Value("${api.security.token.expiration-short}")
    private int expirationShort;

    @Value("${api.security.token.expiration-long}")
    private int expirationLong;

    public String generateToken(User user, boolean rememberMe){
        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);
            int expirationHours = rememberMe ? expirationLong : expirationShort;
            String token = JWT.create()
                    .withIssuer("auth-api")
                    .withSubject(user.getLogin())
                    .withExpiresAt(genExpirationDate(expirationHours))
                    .sign(algorithm);
            return token;
        }catch (JWTCreationException exception){
            throw new NegocioException(ConstantesUtil.ERROR_TITLE, ConstantesUtil.ERRO_GERAR_TOKEN);
        }
    }

    public String validateToken(String token){
        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("auth-api")
                    .build()
                    .verify(token)
                    .getSubject();
        }catch (JWTCreationException exception){
            throw new NegocioException(ConstantesUtil.ERROR_TITLE, ConstantesUtil.TOKEN_INVALIDO);
        }
    }

    private Date genExpirationDate(int expirationHours) {
        return Date.from(Instant.now().plusSeconds(expirationHours * 3600));
    }
}
