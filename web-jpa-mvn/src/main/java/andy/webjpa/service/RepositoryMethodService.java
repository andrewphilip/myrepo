package andy.webjpa.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import andy.webjpa.model.Method;
import andy.webjpa.repository.MethodRepository;
import javax.annotation.*;
@Service("methodService")
public class RepositoryMethodService implements MethodService {
	
	
	private static final Logger LOGGER=LoggerFactory.getLogger(RepositoryMethodService.class);
	
	@Autowired
	private MethodRepository methodRepository;
	
	@Transactional(readOnly=true)
	public Method findByMethod(String method) {
		return methodRepository.findOne(method);
	}

	@Transactional(readOnly=true)
	public List<Method> findAll() {
		return methodRepository.findAll();
	}
	
	public void setMethodRepository(MethodRepository methodRepository) {
		this.methodRepository = methodRepository;
	}

//	protected void setMethodRepository(MethodRepository methodRepo){
//		this.methodRepository=methodRepo;
//	}
	
}
