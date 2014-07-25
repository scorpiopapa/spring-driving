package com.joinway.admin.service;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joinway.admin.bean.domain.AcknowledgeLog;
import com.joinway.appx.service.SystemLoggingService;

@Service
public class NoticeService {

	private final static Logger log = LoggerFactory.getLogger(NoticeService.class);
	
	@Autowired SystemLoggingService loggingService;
	
	public void acknowledge(String page, int userId) throws Exception {
		AcknowledgeLog accLog = new AcknowledgeLog();
		
		accLog.setPage(page);
		accLog.setUserId(userId);
		accLog.setAcknowledgeTime(new Date());
		
		loggingService.log(accLog);
		
	}
}

