package com.joinway.admin.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joinway.admin.bean.domain.AdminUser;
import com.joinway.admin.bean.domain.TreeMenu;

@Repository
public interface AdminMapper {

	AdminUser selectAdminUser(@Param("name") String name, @Param("password") String password);
	
	List<TreeMenu> selectUserTreeMenus(@Param("userId") int userId);
	
}
