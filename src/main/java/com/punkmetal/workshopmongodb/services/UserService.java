package com.punkmetal.workshopmongodb.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.punkmetal.workshopmongodb.domain.User;
import com.punkmetal.workshopmongodb.dto.UserDTO;
import com.punkmetal.workshopmongodb.repository.UserRepository;
import com.punkmetal.workshopmongodb.services.exception.ObjectNotFoundException;

@Service
public class UserService {
	//Injeção de depedência automatica
	@Autowired
	private UserRepository repository;
	
	public List<User> findAll() {
		return repository.findAll();
	}
	
	public User findById(String id) {
		return repository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Usuário não encontrado!"));
	}
	
	public User insert(User user) {
		return repository.insert(user);
	}
	
	public void deleteById(String id) {
		findById(id);
		repository.deleteById(id);
	}
	
	public User update(User obj) {
		User newObj = findById(obj.getId());
		updateData(obj, newObj);
		return repository.save(newObj);
	}
	
	public void updateData(User obj, User newObj) {
		newObj.setName(obj.getName());
		newObj.setEmail(obj.getEmail());
	}
	
	public User fromDTO(UserDTO dto) {
		return new User(dto.getId(), dto.getName(), dto.getEmail());
	}
}
