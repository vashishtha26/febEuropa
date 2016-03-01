package io.febEuropa.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import io.febEuropa.entity.User;

@Repository
public class UserRepositoryImpl implements UserRepository {

	@PersistenceContext
	private EntityManager em;
	
	public List<User> findAllUsers() {
		TypedQuery<User> query = em.createQuery("SELECT u FROM User u ORDER BY u.email ASC", User.class);
    	List<User> users = query.getResultList();
    	return users;
	}

	public User findUserById(String id) {
		User user = em.find(User.class, id);
		return user;
	}

	public User findUserByEmail(String email) {
		TypedQuery<User> query = em.createNamedQuery("User.findByEmail", User.class);
    	query.setParameter("pEmail", email);
    	List<User> users = query.getResultList();
    	if(users != null && users.size() == 1) {
    		return users.get(0);
    	}
    	else {
    		return null;
    	}
	}

	public User createUser(User user) {
		em.persist(user);
		return user;
	}

	public User updateUser(User user) {
		return em.merge(user);
	}

	public void deleteUser (User user) {
		em.remove(user);
	}


}
