package repository.user;

import dto.user.request.UserDtoRequest;
import entity.User;
import repository.BaseRepository;
import service.BaseService;
import service.user.UserService;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends BaseRepository<User, UserDtoRequest> {

    void save(UserDtoRequest request);

    Optional<List<User>> getAll();

    Optional<User> getById(Long id);

    User update(Long id, UserDtoRequest request);

    boolean deleteById(Long id);

    Optional<User> getLastEntity();

    List<User> fetchByFirstName(String firstName);

    List<User> fetchByLastName(String lastName);
}

