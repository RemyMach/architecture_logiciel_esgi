package fr.remy.cc1.exposition.authentication;

import fr.remy.cc1.domain.user.UserId;
import fr.remy.cc1.infrastructure.exceptions.NoSuchEntityException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;
import java.io.IOException;

public class AuthMiddleware extends OncePerRequestFilter {

    private final Tokens tokens;

    @Value("${JWT_SECRET_KEY}")
    private String SECRET_KEY;

    @Autowired
    public AuthMiddleware(Tokens tokens) {
        this.tokens = tokens;
    }


    @Override
    protected void doFilterInternal( HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String s = request.getHeader("Authorization");

        if(s == null) {
            throw new ServletException();
        }

        String[] tokens = s.split("Bearer ");
        String token = tokens[1];
        Token tokenRequest = Token.of(TokenId.of(token));
        Claims claims = Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET_KEY))
                .parseClaimsJws(token).getBody();

        Object userIdValue = claims.get("userId");

        UserId userId = UserId.of(Integer.parseInt(userIdValue.toString()));
        System.out.println(userId);
        try {
            Token tokenInDatabase = this.tokens.byUserId(userId);
            if(!tokenInDatabase.equals(tokenRequest)) {
                throw new ServletException();
            }
        } catch (NoSuchEntityException e) {
            throw new ServletException();
        }

        filterChain.doFilter(request, response);
    }
}
