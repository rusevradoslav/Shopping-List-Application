package exam2020.app.models.view;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class ProductViewModel {
    private String id;
    private String name;
    private BigDecimal price;
}
