package one.maistrenko.shop.basket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service("basket-service")
public class BasketServiceImpl implements BasketService {
    private final BasketDao basketDao;

    public BasketServiceImpl(BasketDao basketDao) {
        this.basketDao = basketDao;
    }

    @Override
    public Basket createBasket(Basket basket) {
        if(basket.getProductList().size() > 30){
            System.out.println("Can't create basket. Too many products.");
            log.warn("Can't create basket. Quantity of products is {{}}", basket.getProductList().size());
            return  null;
        } else {
            return basketDao.createBasket(basket);
        }
    }

    @Override
    public void removeBasket(long basketId) {
        basketDao.removeBasket(basketId);
    }

    @Override
    public Basket updateBasket(Basket basket) {
        if(basket.getProductList().size() > 30){
            System.out.println("Can't update basket. Too many products.");
            log.warn("Can't update basket. Quantity of products is {{}}", basket.getProductList().size());
            return  null;
        } else {
            return basketDao.updateBasket(basket);
        }
    }

    @Override
    public void showAllBaskets() {
        basketDao.showAllBaskets();
    }

    @Override
    public Basket getBasket(long basketId) {
        return basketDao.getBasket(basketId);
    }
}
