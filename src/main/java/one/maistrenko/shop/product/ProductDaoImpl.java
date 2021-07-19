package one.maistrenko.shop.product;

import lombok.extern.slf4j.Slf4j;
import one.maistrenko.shop.idGenerator.IdGenerator;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service("product-dao")
public class ProductDaoImpl implements ProductDao {
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
        log.info("Product {{}} was created", products.get(id));
        return product;
    }

    @Override
    public Product updateProduct(Product product) {
        Product helpProduct = products.get(product.getProductId());
        if(null == helpProduct){
            log.warn("Update product with id {{}} was failed: product not exists", product.getProductId());
            throw new RuntimeException("There is no product with id =" + product.getProductId());
        }
        helpProduct.setProductId(product.getProductId());
        helpProduct.setDescription(product.getDescription());
        log.info("Product {{}} was updated", helpProduct);
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
            log.info("product {{}} successfully removed", productId);
        } else{
            log.warn("Removing product with id {{}} was failed : product not exists", productId);
            throw new RuntimeException("There is no product with id=" + productId);
        }
    }

    @Override
    public Product getProduct(long productId) {
        Product helpProduct = products.get(productId);
        if(null == helpProduct){
            log.warn("Get product with id {{}} was failed: product not exists", productId);
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
                log.info("Product description {{}} is not available", description);
                return getProduct(i.getProductId());
            }
        }
        log.info("Product description: {{}} is available", description);
        return null;
    }
}
