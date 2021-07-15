package com.idat.webservices.util.JWT;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTUtil {
     // key necesaria para la encriptacion
     private static final String KEY = "@1m4c3n_202105";

     public String generateToken(UserDetails userDetails) {
          /*
           * subject : es el usuario issuedat: dia de creacion expiration: dia de
           * expiracion ejem: 10 horas logueo con algoritmo de encriptacion y la key
           * compact: convierte en string
           */
          Integer hours = 10; // 10 horas de validez
		return Jwts.builder().setSubject(userDetails.getUsername())
					.setIssuedAt(Date.from(Instant.now()))
                    .setExpiration(Date.from(Instant.now().plusSeconds(3600 * hours)))
                    .signWith(SignatureAlgorithm.HS256, KEY).compact();
     }

     public boolean validateToken(String token, UserDetails userDetails) {
          // verifica si el username es correcto y no ha caducado
          return userDetails.getUsername().equals(extractUsername(token)) && !isTokenExpired(token);
     }

     public String extractUsername(String token) {
          // obtiene usuario del jwt
          return getClaims(token).getSubject();
     }

     public boolean isTokenExpired(String token) {
          // verifica si la fecha de expiracion es antes del dia
          return getClaims(token).getExpiration().before(Date.from(Instant.now()));
     }

     public Claims getClaims(String token) {
          // obtiene los datos del jwt
          return Jwts.parser().setSigningKey(KEY).parseClaimsJws(token).getBody();
     }

}