package io.febEuropa.service;

import io.febEuropa.exception.UserAlreadyExistsException;
import io.febEuropa.repository.UserRepository;
import io.febEuropa.entity.User;
import io.febEuropa.exception.UserNotFoundException;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserServiceImpl implements UserService {


	@Autowired
	private UserRepository dao;
	
	public List<User> findAllUsers() {
		return dao.findAllUsers();
	}

	public User findUserById(String id) throws UserNotFoundException {
		User user =  dao.findUserById(id);
		if(user == null) {
			throw new UserNotFoundException();
		}
		else {
			return user;
		}
	}

	public User findUserByEmail(String email) throws UserNotFoundException {
		User user = dao.findUserByEmail(email);
		if(user == null) {
			throw new UserNotFoundException();
		}
		else {
			return user;
		}
	}

	public User createUser(User user) throws UserAlreadyExistsException {
		User existing =  dao.findUserByEmail(user.getEmail());
		if(existing == null) {
			return dao.createUser(user);
		}
		else {
			throw new UserAlreadyExistsException();
		}
	}

	public User updateUser(String id, User user) throws UserNotFoundException {
		User existing =  dao.findUserById(id);
		if(existing == null) {
			throw new UserNotFoundException();
		}
		else {
			return dao.updateUser(user);
		}
	}

	public void deleteUser(String id) throws UserNotFoundException {
		User existing =  dao.findUserById(id);
		if(existing == null) {
			throw new UserNotFoundException();
		}
		else {
			dao.deleteUser(existing);
		}
	}
	
	
}
