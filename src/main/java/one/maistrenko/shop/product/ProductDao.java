package one.maistrenko.shop.product;

public interface ProductDao {
    Product createProduct(Product product);
    Product updateProduct(Product product);
    void showProducts();
    void removeProduct(long productId);
    Product getProduct(long productId);
    Product getProductByDescription(String description);
}
