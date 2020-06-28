package exam2020.app.services;

import exam2020.app.models.service.UserServiceModel;

public interface UserService {
    UserServiceModel findUserByUsername(String username);

    void registerUser(UserServiceModel map);
}
