package com.maggessibernardo.webserviceproject.services;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import java.security.Key;
import java.util.Date;
import java.util.function.Function;

/**
 * Serviço responsável por gerar e validar tokens JWT.
 */
@Service
public class JwtService {

    // Chave secreta para assinar o token (deve ter pelo menos 32 bytes para HMAC-SHA)
    private static final String SECRET_KEY = "SEU_SEGREDO_SUPER_SEGURO_AQUI_SECRETO_32_BYTES";
    
    // Tempo de expiração do token (1 dia em milissegundos)
    private static final long EXPIRATION_TIME = 86400000; 

    /**
     * Retorna a chave de assinatura usada para criptografar o token.
     */
    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    /**
     * Gera um token JWT baseado nos detalhes do usuário.
     * @param userDetails - Detalhes do usuário autenticado.
     * @return Token JWT gerado.
     */
    public String generateToken(UserDetails userDetails) {
        return Jwts.builder()
                .setSubject(userDetails.getUsername()) // Define o usuário como "dono" do token
                .setIssuedAt(new Date()) // Define a data de criação do token
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) // Expiração do token
                .signWith(getSigningKey(), SignatureAlgorithm.HS256) // Assinatura com algoritmo HMAC-SHA256
                .compact();
    }

    /**
     * Extrai o nome de usuário (subject) do token JWT.
     * @param token - Token JWT recebido.
     * @return Nome de usuário associado ao token.
     */
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * Extrai um dado específico do token JWT.
     * @param token - Token JWT recebido.
     * @param claimsResolver - Função para extrair um dado específico.
     * @return Dado extraído do token.
     */
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    /**
     * Extrai todos os dados do token JWT.
     * @param token - Token JWT recebido.
     * @return Objeto Claims com todos os dados do token.
     */
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey()) // Configura a chave de assinatura para validar o token
                .build()
                .parseClaimsJws(token) // Decodifica o token
                .getBody();
    }

    /**
     * Verifica se um token é válido para um determinado usuário.
     * @param token - Token JWT recebido.
     * @param userDetails - Detalhes do usuário autenticado.
     * @return true se o token for válido, false caso contrário.
     */
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    /**
     * Verifica se um token já expirou.
     * @param token - Token JWT recebido.
     * @return true se o token estiver expirado, false caso contrário.
     */
    private boolean isTokenExpired(String token) {
        return extractClaim(token, Claims::getExpiration).before(new Date());
    }
}
