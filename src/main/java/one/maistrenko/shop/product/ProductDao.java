package one.maistrenko.shop.product;

import java.util.Map;

public interface ProductDao {
    Product createProduct(Product product);
    Product updateProduct(Product product);
    Map<Long, Product> showProducts();
    void removeProduct(long productId);
    Product getProduct(long productId);
    Product getProductByDescription(String description);
}
