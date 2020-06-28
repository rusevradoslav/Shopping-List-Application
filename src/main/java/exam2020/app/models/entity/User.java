package exam2020.app.models.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Email;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User extends BaseEntity{

    @Column(nullable = false, unique = true)
    @Length(min = 3,max = 20,message = "Username length must be between 3 and 20 characters")
    private String username;

    @Column(unique = true,nullable = false)
    private String email;

    @Column(nullable = false)
    @Length(min = 3,max = 20,message = "Password length must be between 3 and 20 characters")
    private String password;
    //TODO more fields
}
