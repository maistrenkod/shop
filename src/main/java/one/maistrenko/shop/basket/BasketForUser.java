package one.maistrenko.shop.basket;

import one.maistrenko.shop.product.Product;

public interface BasketForUser {
    public void putInBasket(Product product);
    public void removeFromBasket (Product product);
    public void showBasket();
}
