package one.maistrenko.shop.basket;

import lombok.extern.slf4j.Slf4j;
import one.maistrenko.shop.product.Product;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;

@Slf4j
@Service("basket-service")
public class BasketServiceImpl implements BasketService {
    private final BasketServicefirst basketServicefirst;

    public BasketServiceImpl(BasketServicefirst basketServicefirst) {
        this.basketServicefirst = basketServicefirst;
    }

    @Override
    public Basket createBasket(Basket basket) throws ParseException {
        if(basket.getProductList().size() > 30){
            System.out.println("Can't create basket. Too many products.");
            log.warn("Can't create basket. Quantity of products is {{}}", basket.getProductList().size());
            return  null;
        } else {
            return basketServicefirst.createBasket(basket);
        }
    }

    @Override
    public void removeBasket(long basketId) {
        basketServicefirst.removeBasket(basketId);
    }

    @Override
    public Basket updateBasket(Basket basket) throws ParseException {
        if(basket.getProductList().size() > 30){
            System.out.println("Can't update basket. Too many products.");
            log.warn("Can't update basket. Quantity of products is {{}}", basket.getProductList().size());
            return  null;
        } else {
            return basketServicefirst.updateBasket(basket);
        }
    }

    @Override
    public List<Basket> showAllBaskets() {
        return basketServicefirst.showAllBaskets();
    }

    @Override
    public Basket getBasket(long basketId) {
        return basketServicefirst.getBasket(basketId);
    }

    @Override
    public void putInBasket(long basketId, Product product) throws ParseException {
        basketServicefirst.putInBasket(basketId, product);
    }

    @Override
    public void removeFromBasket(long basketId, Product product) throws ParseException {
        basketServicefirst.removeFromBasket(basketId, product);
    }

    @Override
    public List<Product> showBasket(long basketId) {
        System.out.println(basketServicefirst.showBasket(basketId));
        return basketServicefirst.showBasket(basketId);
    }
}
