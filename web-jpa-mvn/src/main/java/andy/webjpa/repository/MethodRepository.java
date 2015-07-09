package andy.webjpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import andy.webjpa.model.Method;
@Repository("methodRepository")
public interface MethodRepository extends JpaRepository<Method, String> {

}
