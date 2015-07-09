package andy.webjpa.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import andy.webjpa.model.Area;
import andy.webjpa.repository.AreaRepository;

@Service("areaSrvc")
public class AreaServiceImpl implements AreaService {

	private static final Logger LOGGER=LoggerFactory.getLogger(AreaServiceImpl.class);
	
	@Autowired
	private AreaRepository areaRepository;
	
	@Transactional(readOnly=true)
	public List<Area> findAll() {
		List<Area> list=areaRepository.findAll();
		return list;
	}

	@Transactional(readOnly=true)
	public Area findByArea(String areaseq) {
		return areaRepository.findOne(areaseq);
	}

}
