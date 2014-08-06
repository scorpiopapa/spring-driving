package com.joinway.admin.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joinway.appx.bean.view.TableResultView;
import com.joinway.appx.repository.TableRepository;
import com.joinway.appx.service.table.CustomTableService;
import com.joinway.appx.service.table.DefaultTableService;
import com.joinway.console.bean.domain.DriveTrainee;
import com.joinway.console.bean.domain.LoginUser;

@Service
public class DriveTraineeTableService extends DefaultTableService implements CustomTableService {

	@Autowired TableRepository repository;
	
	@Override
	public String getTargetTableName() {
		return "DRIVE_TRAINEE";
	}

	@Transactional(rollbackFor=Throwable.class)
	public TableResultView saveTable(String table, Map<String, String[]> params) throws Exception {
		int id = super.getDomainKey(params);
		if(id == 0){
			LoginUser loginUser = super.buildEntity(params, LoginUser.class);
			if(loginUser.getPassword().equals("")){
				loginUser.setPassword("888888");
			}
			loginUser.setCreateTime(new Date());
			repository.save(loginUser);
			DriveTrainee driveTrainee = new DriveTrainee();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			driveTrainee.setAddress(((String[])params.get("address"))[0]);
			driveTrainee.setCellPhone(((String[])params.get("cellPhone"))[0]);
			driveTrainee.setBirthday(sdf.parse(((String[])params.get("birthday"))[0]));
			driveTrainee.setCarType(((String[])params.get("carType"))[0]);
			driveTrainee.setCoach(((String[])params.get("coach"))[0]);
			driveTrainee.setComment(((String[])params.get("comment"))[0]);
			driveTrainee.setCompany(((String[])params.get("company"))[0]);
			driveTrainee.setGender(((String[])params.get("gender"))[0]);
			driveTrainee.setIdCard(((String[])params.get("idCard"))[0]);
			driveTrainee.setIdCardAddress(((String[])params.get("idCardAddress"))[0]);
			driveTrainee.setIdCardType(((String[])params.get("idCardType"))[0]);
			driveTrainee.setIntroducer(((String[])params.get("introducer"))[0]);
			driveTrainee.setName(((String[])params.get("name"))[0]);
			driveTrainee.setNationality(((String[])params.get("nationality"))[0]);
			driveTrainee.setOrigCarType(((String[])params.get("origCarType"))[0]);
			driveTrainee.setPhone(((String[])params.get("phone"))[0]);
			driveTrainee.setPostAddress(((String[])params.get("postAddress"))[0]);
			driveTrainee.setPostCode(((String[])params.get("postCode"))[0]);
			driveTrainee.setRegisterDate(sdf.parse(((String[])params.get("registerDate"))[0]));
			driveTrainee.setSource(((String[])params.get("source"))[0]);
			driveTrainee.setTempCardId(((String[])params.get("tempCardId"))[0]);
			driveTrainee.setTestCarType(((String[])params.get("testCarType"))[0]);
			driveTrainee.setTrainClass(((String[])params.get("trainClass"))[0]);
			driveTrainee.setTrainType(((String[])params.get("trainType"))[0]);
			driveTrainee.setZone(((String[])params.get("zone"))[0]);
			driveTrainee.setId(loginUser.getId());
			repository.insert(driveTrainee);
		}

		TableResultView view = new TableResultView();
		view.setId(id);
		return view;
	}

	
}

