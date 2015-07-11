package andy.web.mvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import andy.web.mvc.dao.UserDAO;
import andy.web.mvc.model.User;
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO userDao;
	
	public List<User> getUsers() {
		return userDao.getUsers();
	}

	public User getUserById(String userId) {
		return userDao.getUserById(userId);
	}

	public User getUserByName(String name){
		return userDao.getUserByName(name);
	}	
}
