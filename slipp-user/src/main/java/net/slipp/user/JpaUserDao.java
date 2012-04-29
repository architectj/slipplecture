package net.slipp.user;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

@Repository
public class JpaUserDao implements UserDao {
	@PersistenceContext
	private EntityManager em;
	
	public void setEntityManager(EntityManager em) {
        this.em = em;
    }
	
	@Override
	public void create(User user) throws SQLException {
		em.persist(user);
	}

	@Override
	public void update(User user) throws SQLException {
		em.merge(user);
	}

	@Override
	public void remove(String userId) throws SQLException {
		em.remove(findUser(userId));
	}

	@Override
	public User findUser(String userId) throws SQLException {
		return em.find(User.class, userId);
	}

	@Override
	public List<User> findUserList() throws SQLException {
		TypedQuery<User> query =
				em.createQuery("select u from User u", User.class);
		return query.getResultList();
	}

	@Override
	public int countAdminUser() throws SQLException {
		String hql = 
				"select count(u.userId) from User u where isAdmin=true";
		TypedQuery<Long> query =
				em.createQuery(hql, Long.class);
		return query.getSingleResult().intValue();
	}
	
	
}
