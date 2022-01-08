package fr.remy.cc1.exposition.interceptor;

import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Scanner;

public class MyMiddleware extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal( HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        if ("POST".equalsIgnoreCase(request.getMethod()))
        {
            Scanner s = new Scanner(request.getInputStream(), "UTF-8").useDelimiter("\\A");
            String pomm = s.hasNext() ? s.next() : "";
            System.out.println(pomm);
            System.out.println(request.getParameter("discountPercentage"));
            System.out.println("on passe dans ce filtre");
            /*StringBuilder stringBuilder = new StringBuilder();
            BufferedReader bufferedReader = null;

            try {
                InputStream inputStream = request.getInputStream();

                if (inputStream != null) {
                    bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

                    char[] charBuffer = new char[128];
                    int bytesRead = -1;

                    while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
                        stringBuilder.append(charBuffer, 0, bytesRead);
                    }
                } else {
                    stringBuilder.append("");
                }
            } catch (IOException ex) {
                logger.error("Error reading the request body...");
            } finally {
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (IOException ex) {
                        logger.error("Error closing bufferedReader...");
                    }
                }
            }

            String body = stringBuilder.toString();
            System.out.println(body);*/
            /*String test = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
            ObjectMapper mapper = new ObjectMapper();
            SubscriptionRequest re;
            try {
                re = mapper.readValue(request.getInputStream(), SubscriptionRequest.class);
                System.out.println(re);
            } catch (IOException e) {
                e.printStackTrace();
            }*/

            //System.out.println(re);
        }
        filterChain.doFilter(request, response);
    }
}
