package one.maistrenko.shop.basket;

public interface BasketService {
    Basket createBasket(Basket basket);

    void removeBasket(long basketId);

    Basket updateBasket(Basket basket);

    void showAllBaskets();

    Basket getBasket(long basketId);
}
