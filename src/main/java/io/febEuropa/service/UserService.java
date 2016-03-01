package io.febEuropa.service;
import io.febEuropa.exception.UserNotFoundException;


import java.util.List;

import io.febEuropa.exception.UserAlreadyExistsException;
import io.febEuropa.entity.User;
public interface UserService {


	List<User> findAllUsers();

	User findUserById(String id) throws UserNotFoundException;

	User findUserByEmail(String email) throws UserNotFoundException;

	User createUser(User user) throws UserAlreadyExistsException;

	User updateUser(String id, User user) throws UserNotFoundException;

	void deleteUser(String id) throws UserNotFoundException;


}
