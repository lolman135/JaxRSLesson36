package service.user;

import dto.user.request.UserDtoRequest;
import entity.User;
import repository.user.UserRepository;

import java.util.List;
import java.util.Objects;

public class UserServiceImpl implements UserService{

    private UserRepository userRepository;

    @Override
    public User create(UserDtoRequest request) {
        Objects.requireNonNull(request, "Parameter [request] must be not null!");
        userRepository.save(request);

        return userRepository.getLastEntity().orElse(null);
    }

    @Override
    public List<User> getAll() {
        return List.of();
    }

    @Override
    public User getById(Long id) {
        return null;
    }

    @Override
    public User update(Long id, UserDtoRequest request) {
        return null;
    }

    @Override
    public boolean deleteById(Long id) {
        return false;
    }

    @Override
    public List<User> fetchByFirstName(String firstName) {
        return List.of();
    }

    @Override
    public List<User> fetchByLastName(String lastName) {
        return List.of();
    }
}
