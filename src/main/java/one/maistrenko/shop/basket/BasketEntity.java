package one.maistrenko.shop.basket;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import one.maistrenko.shop.product.Product;
import one.maistrenko.shop.product.ProductEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Entity
@Table(name ="baskets", schema = "basic")
public class BasketEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "basketId", nullable = false, unique = true)
    private long basketId;

    @ManyToMany(mappedBy = "baskets")
    private List<ProductEntity> products = new ArrayList<>();
}
