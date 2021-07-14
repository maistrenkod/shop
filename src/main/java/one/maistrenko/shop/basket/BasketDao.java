package one.maistrenko.shop.basket;

import one.maistrenko.shop.product.Product;

public interface BasketDao {
    public void putInBasket(long basketId, Product product);
    public void removeFromBasket (long basketId, Product product);
    public void showBasket(long basketId);
    public Basket createBasket(Basket basket);
    public void removeBasket(long basketId);
    public void showAllBaskets();
}
