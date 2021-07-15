package one.maistrenko.shop.basket;

import one.maistrenko.shop.idGenerator.IdGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BasketServiceImpl implements BasketService {
    private static final Logger logger = LoggerFactory.getLogger(BasketDaoImpl.class);
    private final BasketDao basketDao;

    public BasketServiceImpl(IdGenerator generator) {
        basketDao = new BasketDaoImpl(generator);
    }

    @Override
    public Basket createBasket(Basket basket) {
        if(basket.getProductList().size() > 30){
            System.out.println("Can't create basket. Too many products.");
            logger.warn("Can't create basket. Quantity of products is {}", basket.getProductList().size());
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
    public void showAllBaskets() {
        basketDao.showAllBaskets();
    }

    @Override
    public Basket getBasket(long basketId) {
        return basketDao.getBasket(basketId);
    }
}
