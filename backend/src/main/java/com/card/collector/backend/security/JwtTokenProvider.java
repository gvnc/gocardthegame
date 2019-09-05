package com.card.collector.backend.security;

import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import io.jsonwebtoken.*;
import io.jsonwebtoken.impl.DefaultClaims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenProvider {

    private String secretKey="secret-key";

    private long validityInMilliseconds = 3600000; // 1h

    @Autowired
    private AppUserDetails appUserDetails;

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public String createToken(String username, String role) {

        Claims claims = Jwts.claims().setSubject(username);
        claims.put("auth", new SimpleGrantedAuthority(role));

        // make token valid for long interval such as 1 year :)
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, 1);
        Date validity = calendar.getTime();

        Date now = new Date();

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public Authentication getAuthentication(String token) {
        DefaultClaims tokenBody = getTokenBody(token);
        String username = getUsername(tokenBody);
        String role = getRole(tokenBody);
        UserDetails userDetails = appUserDetails.loadUserByUsernameRole(username, role);
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public String getRole(DefaultClaims body) {
        Map auth = (Map)body.get("auth");
        return (String) auth.get("authority");
    }

    public String getUsername(DefaultClaims body) {
        return body.getSubject();
    }

    private DefaultClaims getTokenBody(String token) {
        return (DefaultClaims)Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
    }

    public String resolveToken(HttpServletRequest req) {
        String bearerToken = req.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7, bearerToken.length());
        }
        return null;
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            throw new RuntimeException("Expired or invalid JWT token");
        }
    }

}
