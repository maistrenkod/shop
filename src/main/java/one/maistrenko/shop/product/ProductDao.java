package one.maistrenko.shop.product;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

public interface ProductDao {
    Product createProduct(Product product) throws ParseException;
    Product updateProduct(Product product);
    List<Product> showProducts();
    void removeProduct(long productId);
    Product getProduct(long productId);
    Product getProductByDescription(String description);
    ProductEntity convertToEntity(Product product)  throws ParseException ;
    Product convertToDto(ProductEntity productEntity);
}
