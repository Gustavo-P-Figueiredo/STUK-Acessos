package Gusfigue.example.STUK_Acessos.service;

import Gusfigue.example.STUK_Acessos.entity.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.util.Date;

@Service
public class TokenService {

    @Value("${api.segurity.token.secrety}")
    private String secret;

    public String GerarToken(Usuario usuario) {
        try {
            // Se 'secret' vier nulo por erro de config, o Algorithm.HMAC256 lançará erro aqui
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("login-service")
                    .withSubject(usuario.getEmail())
                    // Usar Instant diretamente é mais seguro no Java moderno
                    .withExpiresAt(Instant.now().plusSeconds(3600))
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Erro ao gerar Token", exception);
        }
    }

    public String ValidarToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("login-service")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException exception) {
            return null; // Token inválido ou expirado
        }
    }
}