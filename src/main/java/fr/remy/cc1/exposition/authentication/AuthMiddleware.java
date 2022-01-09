package fr.remy.cc1.exposition.authentication;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.remy.cc1.domain.user.UserId;
import fr.remy.cc1.exposition.CustomErrorResponse;
import fr.remy.cc1.exposition.exception.ExpositionExceptionsDictionary;
import fr.remy.cc1.exposition.exception.authentication.AuthFailedException;
import fr.remy.cc1.exposition.exception.authentication.AuthRequiredException;
import fr.remy.cc1.infrastructure.exceptions.NoSuchEntityException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
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

        // custom error response class used across my project


        if(s == null) {
            CustomErrorResponse customErrorResponse = new CustomErrorResponse(ExpositionExceptionsDictionary.AUTH_REQUIRED.getErrorCode(), ExpositionExceptionsDictionary.AUTH_REQUIRED.getMessage());

            response.setStatus(HttpStatus.FORBIDDEN.value());
            response.getWriter().write(convertObjectToJson(customErrorResponse));
        }

        String[] tokens = s.split("Bearer ");
        String token = tokens[1];
        Token tokenRequest = Token.of(TokenId.of(token));
        Claims claims = Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET_KEY))
                .parseClaimsJws(token).getBody();

        Object userIdValue = claims.get("userId");

        UserId userId = UserId.of(Integer.parseInt(userIdValue.toString()));
        try {
            Token tokenInDatabase = this.tokens.byUserId(userId);
            if(!tokenInDatabase.equals(tokenRequest)) {
                CustomErrorResponse customErrorResponse = new CustomErrorResponse(ExpositionExceptionsDictionary.AUTH_REQUIRED.getErrorCode(), ExpositionExceptionsDictionary.AUTH_REQUIRED.getMessage());

                response.setStatus(HttpStatus.FORBIDDEN.value());
                response.getWriter().write(convertObjectToJson(customErrorResponse));
                throw new AuthFailedException(ExpositionExceptionsDictionary.AUTH_FAILED.getErrorCode(), ExpositionExceptionsDictionary.AUTH_FAILED.getMessage());
            }
        } catch (NoSuchEntityException e) {
            throw new AuthFailedException(ExpositionExceptionsDictionary.AUTH_FAILED.getErrorCode(), ExpositionExceptionsDictionary.AUTH_FAILED.getMessage());
        }

        filterChain.doFilter(request, response);
    }

    public String convertObjectToJson(Object object) throws JsonProcessingException {
        if (object == null) {
            return null;
        }
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(object);
    }
}
