package exam2020.app.models.binding;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;

@Getter
@Setter
@NoArgsConstructor
public class UserLoginBindingModel {


    @Length(min = 3,max = 20,message = "Username length must be between 3 and 20 characters")
    private String username;

    @Length(min = 3,max = 20,message = "Password length must be between 3 and 20 characters")
    private String password;

    //TODO more fields
}
