package exam2020.app.services.impl;

import exam2020.app.models.entity.User;
import exam2020.app.models.service.UserServiceModel;
import exam2020.app.repositories.UserRepository;
import exam2020.app.services.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Service
@Transactional
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public UserServiceModel findUserByUsername(String username) {
        return this.userRepository
                .findFirstByUsername(username)
                .map(user -> this.modelMapper.map(user, UserServiceModel.class))
                .orElse(null);
    }

    @Override
    public void registerUser(UserServiceModel userServiceModel) {
        this.userRepository.saveAndFlush(this.modelMapper.map(userServiceModel, User.class));

    }
}
