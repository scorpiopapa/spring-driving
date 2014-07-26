package com.joinway.mobile.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joinway.bean.exception.ValidationException;
import com.joinway.console.bean.domain.User;
import com.joinway.db.repository.TableRepository;
import com.joinway.mobile.bean.view.UserView;

@Service
public class UserService {

	@Autowired TableRepository repository;
	
	public UserView getUser(int userId) throws Exception {
		User user = repository.find(userId, User.class);
		
		if(user == null){
			throw new ValidationException("用户不存在");
		}
		
		UserView view = new UserView();
		view.setCellPhone(user.getCellPhone());
		view.setCoach(user.getCoach());
		view.setIdCard(user.getIdCard());
		view.setName(user.getName());
		view.setTestStatus(user.getTestStatus());
		
		return view;
	}
}
