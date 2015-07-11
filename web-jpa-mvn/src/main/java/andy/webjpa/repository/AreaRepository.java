package andy.webjpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import andy.webjpa.model.Area;
@Repository("areaRepository")
public interface AreaRepository extends JpaRepository<Area, String> {

}
