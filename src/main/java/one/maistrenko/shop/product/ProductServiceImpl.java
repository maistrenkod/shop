package one.maistrenko.shop.product;

import one.maistrenko.shop.idGenerator.IdGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ProductServiceImpl implements ProductService {
    private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);
    private final ProductDao productDao;

    public ProductServiceImpl(IdGenerator generator){
        productDao = new ProductDaoImpl(generator);
    }


    @Override
    public Product createProduct(Product product) {
        if(product.getDescription().length() < 5 || product.getDescription().length() > 50){
            System.out.println("Can't create product. Description is not available");
            logger.warn("Can't create product. Description's length is {}", product.getDescription().length());
            return null;
        }
        Product helpProduct = productDao.getProductByDescription(product.getDescription());
        if(!(null == helpProduct)){
            System.out.println("Can't create product. Product with equal description already exists.");
            logger.warn("Can't create product. This description:{} already exists", product.getDescription().length());
            return null;
        }
        return productDao.createProduct(product);
    }

    @Override
    public Product updateProduct(Product product) {
        if(product.getDescription().length() < 5 || product.getDescription().length() > 50) {
            System.out.println("Can't update product. Description is not available");
            logger.warn("Can't update product. Description's length is {}", product.getDescription().length());
            return null;
        }
        Product helpProduct = productDao.getProductByDescription(product.getDescription());
        if(!(null == helpProduct)){
            System.out.println("Can't create product. Product with equal description already exists.");
            logger.warn("Can't create product. This description:{} already exists", product.getDescription().length());
            return null;
        }
        return productDao.updateProduct(product);

    }

    @Override
    public void showProducts() {
        productDao.showProducts();
    }

    @Override
    public void removeProduct(long productId) {
        productDao.removeProduct(productId);
    }

    @Override
    public Product getProduct(long productId) {
        return productDao.getProduct(productId);
    }
}
