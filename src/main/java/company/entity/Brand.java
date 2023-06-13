package company.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "brands")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Brand {
    @Id
    @GeneratedValue(generator = "brand_gen", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "brand_gen",
            sequenceName = "brand_seq",
            allocationSize = 1)
    private Long id;
    private String BrandName;
    private String image;
    @OneToMany(mappedBy = "brand",cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.REMOVE
    })
    private List<Product> productList;


}
