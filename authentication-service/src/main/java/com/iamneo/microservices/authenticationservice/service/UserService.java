

import com.stackroute.AuthenticatorService.exception.UserNotFoundException;
import com.iamneo.microservices.authenticationservice.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    public void saveUser(User user);
    public User getUserByNameAndPassword(String name, String password) throws UserNotFoundException;
}
