package one.maistrenko.shop.user.basketforuser;

import one.maistrenko.shop.basket.Basket;
import one.maistrenko.shop.product.Product;

public interface BasketForUser {
    public void putInBasket(Product product);
    public void removeFromBasket (Product product);
    public void showBasket();
    public Basket getBasket();
}
