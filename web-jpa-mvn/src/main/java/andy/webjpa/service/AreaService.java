package andy.webjpa.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import andy.webjpa.model.Area;


public interface AreaService {

	public List<Area> findAll();
	

	public Area findByArea(String areaseq);
}
