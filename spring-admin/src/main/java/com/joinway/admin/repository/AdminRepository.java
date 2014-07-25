package com.joinway.admin.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.joinway.admin.bean.domain.AdminUser;
import com.joinway.admin.mapper.AdminMapper;

@Repository
public class AdminRepository {

	private final static Logger log = LoggerFactory.getLogger(AdminRepository.class);
	
	@Autowired AdminMapper mapper;
	
	public AdminUser findAdminUser(String name, String password){
		return mapper.selectAdminUser(name, password);
	}
	
//	public List<TreeMenu> findUserTreeMenus(int userId){
//		return mapper.selectUserTreeMenus(userId);
//	}
	
	public AdminUser findAdminUser(String name){
		return mapper.selectAdminUser(name, null);
	}
}
