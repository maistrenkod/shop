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
import java.nio.charset.StandardCharsets;
import java.util.Base64;


@Component("authorization-filter")
@Slf4j
@Order(450)
public class AuthorizationFilter extends HttpFilter {
    private UserService userService;
    private static final ThreadLocal<String> usernameThr = new ThreadLocal<>();

    public static String getUsername(){
        return usernameThr.get();
    }

    public  AuthorizationFilter(UserService userService){
        this.userService = userService;
    }

    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String authorization = request.getHeader("Authorization");
        String base64 = authorization.substring(6);

        String list[] = (new String(Base64.getDecoder().decode(base64))).split(":");
        String username = list[0];
        String password = list[1];
        User user = userService.getUserByName(username);

        if(null == user.getUsername()){
            response.setStatus(401);
            return;
        }
        if(!password.equals(user.getPassword())){
            response.setStatus(401);
        }
        usernameThr.set(username);
        log.debug("Authorization is completed for user with name {{}}", getUsername());
        super.doFilter(request, response, chain);

    }
}
