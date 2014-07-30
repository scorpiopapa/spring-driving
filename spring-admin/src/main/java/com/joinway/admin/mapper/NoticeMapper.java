package com.joinway.admin.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface NoticeMapper {

	void updateNotice(@Param("page") String page, @Param("userId") int userId, @Param("status") String status);
	
}

