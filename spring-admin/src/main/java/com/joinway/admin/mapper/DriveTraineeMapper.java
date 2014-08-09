package com.joinway.admin.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joinway.console.bean.domain.DriveTrainee;

@Repository
public interface DriveTraineeMapper {

	void updateLoginUser(@Param("loginName") String loginName, @Param("password") String password,  @Param("id") int id);
	
	
	void updateDriveTrainee(DriveTrainee driveTrainee);
}
