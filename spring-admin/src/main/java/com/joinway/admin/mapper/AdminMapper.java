package com.joinway.admin.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joinway.admin.bean.domain.AdminUser;

@Repository
public interface AdminMapper {

	AdminUser selectAdminUser(@Param("name") String name, @Param("password") String password);
	
}
