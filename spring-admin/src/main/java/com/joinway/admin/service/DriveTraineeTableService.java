package com.joinway.admin.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joinway.appx.bean.view.TableResultView;
import com.joinway.appx.repository.TableRepository;
import com.joinway.appx.service.table.CustomTableService;
import com.joinway.appx.service.table.DefaultTableService;
import com.joinway.console.bean.domain.LoginUser;

@Service
public class DriveTraineeTableService extends DefaultTableService implements CustomTableService {

	@Autowired TableRepository repository;
	
	@Override
	public String getTargetTableName() {
		return "DRIVE_TRAINEE";
	}

	@Override
	public TableResultView saveTable(String table, Map<String, String[]> params) throws Exception {
		int id = super.getDomainKey(params);
		
		if(id == 0){
			LoginUser loginUser = super.buildEntity(params, LoginUser.class);
			
			// TODO set loginUser fields here
			
			repository.save(loginUser);
		}
		
		// update data for DRIVE_TRAINEE
		return super.saveTable(table, params);
	}

	
}

