package service.user;

import dto.user.request.UserDtoRequest;
import entity.User;
import jakarta.inject.Inject;
import repository.user.UserRepository;

import java.util.List;
import java.util.Objects;

public class UserServiceImpl implements UserService {

    @Inject
    private UserRepository userRepository;

    @Override
    public User create(UserDtoRequest request) {
        Objects.requireNonNull(request, "Parameter [request] must be not null!");
        userRepository.save(request);

        return userRepository.getLastEntity().orElse(null);
    }

    @Override
    public List<User> getAll() {
        return userRepository.getAll().orElse(null);
    }

    @Override
    public User getById(Long id) {
        return userRepository.getById(id).orElse(null);
    }

    @Override
    public User update(Long id, UserDtoRequest request) {
        return userRepository.update(id, request);
    }

    @Override
    public boolean deleteById(Long id) {
        return userRepository.deleteById(id);
    }

    @Override
    public List<User> fetchByFirstName(String firstName) {
        return userRepository.fetchByFirstName(firstName);
    }

    @Override
    public List<User> fetchByLastName(String lastName) {
        return userRepository.fetchByLastName(lastName);
    }
}