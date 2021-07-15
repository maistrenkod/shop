package one.maistrenko.shop.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import one.maistrenko.shop.user.basketforuser.BasketForUser;

@Data
@AllArgsConstructor
@Builder
public class User {
    private long userid;
    private String username;
    private String password;
    private BasketForUser basket;
}
