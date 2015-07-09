package andy.web.mvc.service;

import java.util.List;

import andy.web.mvc.model.User;

public interface UserService {

	public List<User> getUsers();
	public User getUserById(String userId);
	public User getUserByName(String name);	
	
}
