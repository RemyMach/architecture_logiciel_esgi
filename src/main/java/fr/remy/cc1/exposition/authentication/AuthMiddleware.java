package fr.remy.cc1.exposition.authentication;

import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthMiddleware extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal( HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String s = request.getHeader("Authorization");
        if(s == null) {
            throw new ServletException();
        }

        System.out.println(s);
        System.out.println(request.getParameter("discountPercentage"));
        System.out.println("on passe dans ce filtre");
        filterChain.doFilter(request, response);
    }
}
