package one.maistrenko.shop.product;

import one.maistrenko.shop.basket.Basket;
import one.maistrenko.shop.idGenerator.IdGenerator;
import one.maistrenko.shop.product.Product;
import one.maistrenko.shop.product.ProductService;
import one.maistrenko.shop.user.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class ProductServiceImpl implements ProductService {
    private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);
    private Map<Long, Product> products = new HashMap<>();
    private IdGenerator idGenerator;

    public ProductServiceImpl(IdGenerator generator){
        idGenerator = generator;
    }

    @Override
    public Product createProduct(Product product) {
        long id = idGenerator.generateId();
        Product helpProduct = Product.builder()
                .productId(id)
                .description(product.getDescription())
                .build();
        products.put(id,helpProduct);
        product.setProductId(id);
        logger.info("Product {} was created", products.get(id));
        return product;
    }

    @Override
    public Product updateProduct(Product product) {
        Product helpProduct = products.get(product.getProductId());
        if(null == helpProduct){
            logger.warn("Update product with id {} was failed: product not exists", product.getProductId());
            throw new RuntimeException("There is no product with id =" + product.getProductId());
        }
        helpProduct.setProductId(product.getProductId());
        helpProduct.setDescription(product.getDescription());
        return product;
    }

    public void showProducts(){
        for (Product i: products.values()) {
            System.out.println(i);
        }
    }

    public void removeProduct(long productId){
        if (products.containsKey(productId)){
            products.remove(productId);
            logger.info("product {} successfully removed", productId);
        } else{
            logger.warn("Removing product with id {} was failed : product not exists", productId);
            throw new RuntimeException("There is no product with id=" + productId);
        }
    }

    @Override
    public Product getProduct(long productId) {
        Product helpProduct = products.get(productId);
        if(null == helpProduct){
            logger.warn("Get product with id {} was failed: product not exists", productId);
            throw new RuntimeException("There is no product with id =" + productId);
        }
        Product product = Product.builder()
                .productId(productId)
                .description(helpProduct.getDescription())
                .build();
        return product;

    }
}
