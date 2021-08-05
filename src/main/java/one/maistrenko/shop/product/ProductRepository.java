package one.maistrenko.shop.product;

import one.maistrenko.shop.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    void deleteByProductId(long productId);
    List<ProductEntity> findAll();
    ProductEntity findProductEntityByDescription(String description);
    ProductEntity findProductEntityByProductId(long productId);
    void deleteProductEntityByProductId(long productId);
    void removeByProductId(long productId);

    @Modifying
    @Query("update ProductEntity p set p.description = ?1 where p.productId = ?2")
    void updateProduct(String description, long productId);
}
