package one.maistrenko.shop.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Order(451)
@Component("authentication-filter")
public class AuthenticationFilter extends HttpFilter {
    private UserService userService;

    public  AuthenticationFilter(UserService userService){
        this.userService = userService;
    }
    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        if(request.getRequestURI().startsWith("/api/v1/user/")) {
            User user = userService.getUserByName(AuthorizationFilter.getUsername());

            if (!"admin".equals(user.getUsername())){
                response.getWriter().write("You're not admin");
                response.setStatus(403);
                return;
            }
        }
        log.debug("All filters are completed");
        chain.doFilter(request, response);
    }
}
