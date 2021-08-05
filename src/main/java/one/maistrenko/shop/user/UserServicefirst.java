package one.maistrenko.shop.user;

import java.text.ParseException;
import java.util.List;

public interface UserServicefirst {
    void removeUser(long userId);
    List<User> showUsers();
    User createUser(User user) throws ParseException;
    User updateUser(User user);
//    void putInBasket(long userId, Product product);
//    void removeFromBasket (long userId, Product product);
//    List<Product> showBasket(long userId);
    User getUserById(long userId);
    User getUserByName(String username);
}
