package one.maistrenko.shop.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import one.maistrenko.shop.basket.Basket;
import one.maistrenko.shop.basket.BasketEntity;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Entity
@Table(name ="products", schema = "basic")
public class ProductEntity {
    @Id
    @Column(name = "productId", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long productId;

    @Column(name = "description", nullable = false, columnDefinition = "varchar(800)", unique = true)
    private String description;

    @ManyToMany
//    @JoinColumn(name = "basketId", nullable = false, referencedColumnName = "basketId")
    @JoinTable(name = "productsInBaskets", joinColumns = @JoinColumn(name = "productId"),
            inverseJoinColumns = @JoinColumn(name = "basketId"))
    private List<BasketEntity> baskets;
}
