package fr.remy.cc1.shared.exposition.authentication;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.remy.cc1.kernel.error.BasicException;
import fr.remy.cc1.shared.domain.UserId;
import fr.remy.cc1.shared.exposition.CustomErrorResponse;
import fr.remy.cc1.shared.exposition.exception.ExpositionExceptionsDictionary;
import fr.remy.cc1.shared.infrastructure.exceptions.NoSuchEntityException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
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


// je voulais faire ça bien mais j'avais plus le temps de me battre avec le système d'erreur des filtres
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
            response = setResponseErrorCustom(ExpositionExceptionsDictionary.AUTH_REQUIRED, ExpositionExceptionsDictionary.AUTH_REQUIRED, response, HttpStatus.FORBIDDEN.value());
            return;
        }

        String[] tokens = s.split("Bearer ");

        if(tokens.length < 2) {
            response = setResponseErrorCustom(ExpositionExceptionsDictionary.AUTH_FAILED, ExpositionExceptionsDictionary.AUTH_FAILED, response, HttpStatus.UNAUTHORIZED.value());
            return;
        }

        String token = tokens[1];
        Token tokenRequest = Token.of(TokenId.of(token));
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET_KEY))
                    .parseClaimsJws(token).getBody();
        }catch (MalformedJwtException exception) {
            response = setResponseErrorCustom(ExpositionExceptionsDictionary.AUTH_FAILED, ExpositionExceptionsDictionary.AUTH_FAILED, response, HttpStatus.UNAUTHORIZED.value());
            return;
        }

        Object userIdValue = claims.get("userId");

        UserId userId = UserId.of(Integer.parseInt(userIdValue.toString()));
        try {
            Token tokenInDatabase = this.tokens.byUserId(userId);
            if(!tokenInDatabase.equals(tokenRequest)) {
                response = setResponseErrorCustom(ExpositionExceptionsDictionary.AUTH_FAILED, ExpositionExceptionsDictionary.AUTH_FAILED, response, HttpStatus.UNAUTHORIZED.value());
                return;
            }
        } catch (NoSuchEntityException e) {
            response = setResponseErrorCustom(ExpositionExceptionsDictionary.AUTH_FAILED, ExpositionExceptionsDictionary.AUTH_FAILED, response, HttpStatus.UNAUTHORIZED.value());
            return;
        }

        filterChain.doFilter(request, response);
    }

    private HttpServletResponse setResponseErrorCustom(BasicException basicException, IOException ioException, HttpServletResponse response, int httpStatusValue) throws IOException {
        HttpServletResponse newResponse = response;
        CustomErrorResponse customErrorResponse = new CustomErrorResponse(basicException.getErrorCode(), ioException.getMessage());

        newResponse.setStatus(httpStatusValue);
        newResponse.setContentType("application/json");
        newResponse.getWriter().write(convertObjectToJson(customErrorResponse));
        return newResponse;
    }

    public String convertObjectToJson(Object object) throws JsonProcessingException {
        if (object == null) {
            return null;
        }
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(object);
    }

}
