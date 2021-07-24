package one.maistrenko.shop.product;

import one.maistrenko.shop.product.Product;

import java.util.Map;

public interface ProductService {
    public Product createProduct(Product product);
    public Product updateProduct(Product product);
    public Map<Long, Product> showProducts();
    public void removeProduct(long productId);
    public Product getProduct(long productId);
}
