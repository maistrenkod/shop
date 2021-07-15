package one.maistrenko.shop.product;

import one.maistrenko.shop.idGenerator.IdGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class ProductDaoImpl implements ProductDao {
    private static final Logger logger = LoggerFactory.getLogger(ProductDaoImpl.class);
    private final Map<Long, Product> products = new HashMap<>();
    private final IdGenerator idGenerator;

    public ProductDaoImpl(IdGenerator generator){
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
        logger.info("Product {} was updated", helpProduct);
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
            throw new RuntimeException("Product with id =" + productId + "not exists");
        }
        return Product.builder()
                .productId(productId)
                .description(helpProduct.getDescription())
                .build();

    }

    @Override
    public Product getProductByDescription(String description) {
        for (Product i: products.values()) {
            if (description.equals(i.getDescription())){
                logger.info("Product description {} is not available", description);
                return getProduct(i.getProductId());
            }
        }
        logger.info("Product description {} is available", description);
        return null;
    }
}
