package one.maistrenko.shop.basket;

public interface BasketService {
    public Basket createBasket(Basket basket);

    public void removeBasket(long basketId);

    public void showAllBaskets();

    public Basket getBasket(long basketId);
}
