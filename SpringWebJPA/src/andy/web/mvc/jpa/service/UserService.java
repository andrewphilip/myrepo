package andy.web.mvc.jpa.service;

import java.util.List;

import andy.web.mvc.jpa.model.User;

public interface UserService {

		public List<User> findAll();
		
		public User findByUserid(String userid);
}
