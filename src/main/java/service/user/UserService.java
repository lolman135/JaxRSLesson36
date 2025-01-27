package service.user;

import dto.user.request.UserDtoRequest;
import entity.User;
import service.BaseService;

import java.util.List;

public interface UserService extends BaseService<User, UserDtoRequest> {

    User create(UserDtoRequest request);

    List<User> getAll();

    User getById(Long id);

    User update(Long id, UserDtoRequest request);

    boolean deleteById(Long id);

    List<User> fetchByFirstName(String firstName);
    List<User> fetchByLstName(String lastName);
}
