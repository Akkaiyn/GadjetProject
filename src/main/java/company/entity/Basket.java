package company.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "baskets")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Basket {
    @Id
    @GeneratedValue(generator = "basket_gen", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "basket_gen",
            sequenceName = "basket_seq",
            allocationSize = 1)
    private Long id;
    @ManyToMany(mappedBy = "basketList",
            cascade = {
                    CascadeType.DETACH,
                    CascadeType.MERGE,
                    CascadeType.REFRESH})
    private List<Product> productList;
    @OneToOne(mappedBy = "basket",
            cascade = {
                    CascadeType.DETACH,
                    CascadeType.MERGE,
                    CascadeType.REFRESH})
    private User user;

}
