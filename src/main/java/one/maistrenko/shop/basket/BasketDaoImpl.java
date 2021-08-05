package one.maistrenko.shop.basket;

import lombok.extern.slf4j.Slf4j;
import one.maistrenko.shop.product.Product;
import one.maistrenko.shop.product.ProductDao;
import one.maistrenko.shop.product.ProductEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service("basket-dao")
public class BasketDaoImpl implements BasketDao {
//    @Autowired
//    private IdGenerator idGenerator;
    @Autowired
    private BasketRepository basketRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ProductDao productDao;


//    public BasketDaoImpl(IdGenerator generator){
//        idGenerator = generator;
//    }


    @Override
    public Basket createBasket(Basket basket) throws ParseException {
        //long id = idGenerator.generateId();
        Basket helpBasket = Basket.builder()
                .basketId(1)
                .productList(basket.getProductList())
                .build();
//        baskets.put(id,helpBasket);
        basketRepository.saveAndFlush(convertToEntity(helpBasket));
        List<ProductEntity> list = new ArrayList<>();
        for (int i = 0; i < basket.getProductList().size(); i++) {
            list.add(productDao.convertToEntity(basket.getProductList().get(i)));
        }
//        basket.setBasketId(basketRepository.findBasketEntityByProducts(list).getBasketId());
        log.info("Basket {{}} was created", basket);
        return basket;
    }

    @Override
    public Basket updateBasket(Basket basket) throws ParseException {
        BasketEntity basketEntity = basketRepository.findBasketEntityByBasketId(basket.getBasketId());
//        Basket helpBasket = baskets.get(basket.getBasketId());
        if(null == basketEntity){
            log.warn("Update basket with id {{}} was failed: basket not exists", basket.getBasketId());
            throw new RuntimeException("There is no basket with id =" + basket.getBasketId());
        }
        List<ProductEntity> list = new ArrayList<>();
        for (int i = 0; i < basket.getProductList().size(); i++) {
            list.add(productDao.convertToEntity(basket.getProductList().get(i)));
        }
        basketRepository.updateProduct(list, basket.getBasketId());
//        helpBasket.setProductList(basket.getProductList());
        log.info("Basket {{}} was updated", basket);
        return basket;
    }

    @Override
    public void removeBasket(long basketId) {
        if(null != basketRepository.findBasketEntityByBasketId(basketId)) {
            basketRepository.deleteByBasketId(basketId);
//            baskets.remove(basketId);
            log.info("basket with id {{}} successfully removed", basketId);
        } else{
            log.warn("Removing basket with id {{}} was failed : basket not exists", basketId);
            throw new RuntimeException("There is no basket with id=" + basketId);
        }
    }

    @Override
    public List<Basket> showAllBaskets() {
        List<Basket> baskets = new ArrayList<>();
        for (int i = 0; i < basketRepository.findAll().size(); i++) {
            baskets.add(convertToDto(basketRepository.findAll().get(i)));
        }
//        for (Basket i: baskets.values()) {
//            System.out.println(i);
//        }
        return baskets;
    }

    @Override
    public Basket getBasket(long basketId) {
        BasketEntity basketEntity= basketRepository.findBasketEntityByBasketId(basketId);
//        Basket helpBasket = baskets.get(basketId);
        if(null == basketEntity){
            log.warn("Get basket with id {{}} was failed: basket not exists", basketId);
            throw new RuntimeException("There is no basket with id =" + basketId);
        }
//        return Basket.builder()
//                .basketId(basketId)
//                .productList(helpBasket.getProductList())
//                .build();
        return convertToDto(basketEntity);
    }

    @Override
    public void putInBasket(long basketId, Product product) throws ParseException {
        Basket helpBasket = getBasket(basketId);
        ArrayList<Product> list = new ArrayList<>();
        list.addAll(0, helpBasket.getProductList());
        list.add(product);
        helpBasket.setProductList(list);
        updateBasket(helpBasket);
        log.info("product {{}} was put to basket with id {{}}", product, basketId);
        log.info("basket with id {{}} has list {{}}", basketId, helpBasket.getProductList());
    }

    @Override
    public void removeFromBasket(long basketId, Product product) throws ParseException {
        Basket helpBasket = getBasket(basketId);
        ArrayList<Product> list = new ArrayList<>();
        list.addAll(0, helpBasket.getProductList());
        list.remove(product);
        helpBasket.setProductList(list);
        updateBasket(helpBasket);
        log.info("product {{}} was removed from basket with id {{}}", product, basketId);
        log.info("basket with id {{}} has list {{}}", basketId, helpBasket.getProductList());
    }

    @Override
    public List<Product> showBasket(long basketId) {
        Basket helpBasket = getBasket(basketId);
        return helpBasket.getProductList();
    }

    private Basket convertToDto(BasketEntity basketEntity) {
        Basket basket = modelMapper.map(basketEntity, Basket.class);
        return basket;
    }

    private BasketEntity convertToEntity(Basket basket) throws ParseException {
        BasketEntity basketEntity = modelMapper.map(basket, BasketEntity.class);
        return basketEntity;
    }
}
