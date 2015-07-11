package andy.web.mvc.jpa.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import andy.web.mvc.jpa.model.User;

@Repository("userRepository")
public interface UserRepository  extends JpaRepository<User, String>{
}
