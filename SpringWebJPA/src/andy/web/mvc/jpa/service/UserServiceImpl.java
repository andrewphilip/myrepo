package andy.web.mvc.jpa.service;

import java.util.List;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import andy.web.mvc.jpa.model.User;
import andy.web.mvc.jpa.repo.UserRepository;
@Service("userSrvc")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	public List<User> findAll() {
		List<User> list=userRepository.findAll();
		return list;
	}

	public User findByUserid(String userid) {
		return userRepository.findOne(userid);
	}

}
