package net.slipp.user;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDataIntializer implements InitializingBean {
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public void afterPropertiesSet() throws Exception {
		User user = 
				new User("javajigi", "pass", "name", "javajigi@sk.com", true);
		userRepository.save(user);
	}

}
