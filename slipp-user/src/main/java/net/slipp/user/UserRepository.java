package net.slipp.user;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


public interface UserRepository extends CrudRepository<User, String>{
	@Query("select count(u.userId) from User u where isAdmin=true")
	long countAdminUser();

	List<User> findByName(String name);
}
