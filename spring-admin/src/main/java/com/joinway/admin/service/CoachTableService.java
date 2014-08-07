package com.joinway.admin.service;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joinway.appx.bean.view.TableResultView;
import com.joinway.appx.repository.TableRepository;
import com.joinway.appx.service.table.CustomTableService;
import com.joinway.appx.service.table.DefaultTableService;
import com.joinway.bean.logging.annotation.InputLog;
import com.joinway.bean.logging.annotation.OutputLog;
import com.joinway.console.bean.domain.Coach;
import com.joinway.console.bean.domain.LoginUser;
import com.joinway.utils.CipherUtils;

@Service
public class CoachTableService extends DefaultTableService implements CustomTableService {

	@Autowired TableRepository repository;
	
	@Override
	public String getTargetTableName() {
		return "COACH";
	}

	@InputLog
	@OutputLog
	@Transactional(rollbackFor=Throwable.class)
	public TableResultView saveTable(String table, Map<String, String[]> params) throws Exception {
		int id = super.getDomainKey(params);
		
		if(id == 0){
			LoginUser loginUser = super.buildEntity(params, LoginUser.class);
			loginUser.setPassword(CipherUtils.encrypt(loginUser.getPassword()));
			
			loginUser.setCreateTime(new Date());
			repository.save(loginUser);
			
			Coach coach = super.buildEntity(params, Coach.class);
			coach.setId(loginUser.getId());
			repository.save(coach);
			
		}

		TableResultView view = new TableResultView();
		view.setId(id);
		return view;
	}

	
}


