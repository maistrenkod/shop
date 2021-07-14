package one.maistrenko.shop.basket;

import one.maistrenko.shop.idGenerator.IdGenerator;
import one.maistrenko.shop.product.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

public class BasketForUserImpl implements BasketForUser {
    private static final Logger logger = LoggerFactory.getLogger(BasketForUserImpl.class);
    private Basket basket;
    private BasketDao basketDao;

    public BasketForUserImpl(IdGenerator generator){
        basketDao = new BasketDaoImpl(generator);
        basketDao.createBasket(new Basket());
        basket = new Basket();
    }

    @Override
    public void putInBasket( Product product) {
        ArrayList<Product> helplist = new ArrayList<>();
        helplist.addAll(0, basket.getProductList());
        helplist.add(product);
        basket.setProductList(helplist);
        logger.debug("basket {} have list {}", basket.getBasketId(), basket.getProductList());
        logger.info("product {} put in basket {}", product, basket.getBasketId());
    }

    @Override
    public void removeFromBasket(Product product) {
        ArrayList<Product> helplist = new ArrayList<>();
        helplist.addAll(0, basket.getProductList());
        if (!helplist.remove(product)) {
            logger.debug("this basket with id {} doesn\'t contain product with id {}",basket.getBasketId(), product.getProductId());
        }
        basket.setProductList(helplist);
        logger.debug("basket {} have list {}", basket.getBasketId(), basket.getProductList());
        logger.info("product {} removed from basket {}", product, basket.getBasketId());
    }

    @Override
    public void showBasket() {
        System.out.println(basket.getProductList());
    }

}
