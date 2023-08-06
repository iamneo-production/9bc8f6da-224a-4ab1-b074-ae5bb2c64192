
import java.util.Map;
import com.iamneo.microservices.authenticationservice.entity.User;
public interface JwtGeneratorInterface {
    Map<String, String> generateToken(User user);
}
