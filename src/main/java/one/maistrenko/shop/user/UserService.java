package one.maistrenko.shop.user;

import one.maistrenko.shop.product.Product;

public interface UserService {
    public void removeUser(long userId);
    public void showUsers();
    public User createUser(User user);
    public User updateUser(User user);
    public void putInBasket(long userId, Product product);
    public void removeFromBasket (long userId, Product product);
    public void showBasket(long userId);

}
