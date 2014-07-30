package com.joinway.admin.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joinway.admin.mapper.NoticeMapper;
import com.joinway.db.constant.DBValueConstants;

@Service
public class NoticeService {

	private final static Logger log = LoggerFactory.getLogger(NoticeService.class);
	
//	@Autowired SystemLoggingService loggingService;
	
	@Autowired NoticeMapper mapper;
	
	public void acknowledge(String page, int userId) throws Exception {
		mapper.updateNotice(page, userId, DBValueConstants.YES);
//		AcknowledgeLog accLog = new AcknowledgeLog();
//		
//		accLog.setPage(page);
//		accLog.setUserId(userId);
//		accLog.setAcknowledgeTime(new Date());
		
//		loggingService.log(noticeLog);
		
	}
}


