package one.maistrenko.shop.user.basketforuser;

import one.maistrenko.shop.basket.Basket;
import one.maistrenko.shop.product.Product;

public interface BasketForUser {
    void putInBasket(Product product);
    void removeFromBasket (Product product);
    void showBasket();
    Basket getBasket();
}
