package andy.webjpa.service;

import java.util.List;

import andy.webjpa.model.Method;

public interface MethodService {

	public Method findByMethod(String method);
	
	public List<Method> findAll();
	
}
