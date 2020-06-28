package exam2020.app.models.binding;

import exam2020.app.models.entity.CategoryName;
import exam2020.app.models.service.CategoryServiceModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class ProductAddBindingModel {

    @Length(min = 3,max = 20,message = "Name length must be between 3 and 20 characters")
    private String name;

    @Length(min = 5,message = "Description min length must be minimum 5")
    private String description;
    @DecimalMin(value = "0.00", message = "Price must be a positive number")
    private BigDecimal price;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @FutureOrPresent(message = "The date can not be in the past!")
    private LocalDateTime neededBefore;

    @NotNull(message = "Category can not be null")
    private CategoryName category;
}
