package one.maistrenko.shop.basket;

import one.maistrenko.shop.product.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface BasketRepository extends JpaRepository<BasketEntity, Long> {
    void deleteByBasketId(long basketId);
    List<BasketEntity> findAll();
    BasketEntity findBasketEntityByBasketId(long basketId);
//    BasketEntity findBasketEntityByProductList(List<ProductEntity> productEntities);
//    BasketEntity findBasketEntityByProducts(List<ProductEntity> productEntities);

    @Modifying
    @Query("update BasketEntity b set b.products = ?1 where b.basketId = ?2")
    void updateProduct(List<ProductEntity> products, long basketId);
}
