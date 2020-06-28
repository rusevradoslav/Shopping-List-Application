package exam2020.app.models.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Product extends BaseEntity {

    @Column(nullable = false, unique = true)
    @Length(min = 3,max = 20,message = "Name length must be between 3 and 20 characters")
    private String name;
    @Column(nullable = false,columnDefinition = "TEXT")
    @Length(min = 5,message = "Description min length must be minimum 5")
    private String description;
    @Column(nullable = false)
    @DecimalMin(value = "0.00", message = "Price must be a positive number")
    private BigDecimal price;
    @Column(nullable = false)
    private LocalDateTime neededBefore;
    @ManyToOne
    private Category category;

}
