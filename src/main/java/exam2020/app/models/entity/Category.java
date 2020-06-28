package exam2020.app.models.entity;

import com.fasterxml.jackson.databind.ser.Serializers.Base;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "categories")
public class Category extends BaseEntity {
    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private CategoryName category;
    @Column(nullable = false,columnDefinition = "TEXT")
    private String description;
}
