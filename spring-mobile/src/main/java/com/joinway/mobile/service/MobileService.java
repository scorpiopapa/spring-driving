package com.joinway.mobile.service;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joinway.bean.exception.DuplicateDataException;
import com.joinway.bean.exception.ValidationException;
import com.joinway.console.bean.domain.LoginUser;
import com.joinway.console.bean.domain.DriveTrainee;
import com.joinway.db.constant.DBValueConstants;
import com.joinway.db.repository.TableRepository;
import com.joinway.mobile.bean.form.LoginForm;
import com.joinway.mobile.bean.form.LogoutForm;
import com.joinway.mobile.bean.form.RegisterForm;
import com.joinway.mobile.bean.view.LoginView;
import com.joinway.mobile.bean.view.LogoutView;
import com.joinway.mobile.repository.MobileRepository;
import com.joinway.utils.CipherUtils;

@Service
public class MobileService {

	@Autowired MobileRepository mobileRepository;
	
	@Autowired TableRepository tableRepository;
	
	@Transactional(rollbackFor=Throwable.class)
	public LoginView register(RegisterForm form) throws Exception {
		LoginUser loginUser = mobileRepository.findLoginUser(form.getName().toLowerCase());
		if(loginUser != null){
			throw new DuplicateDataException("用户已注册");
		}
		
		/*
		 * 保存用户注册信息
		 */
		loginUser = new LoginUser();
		loginUser.setLoginName(form.getName());
		loginUser.setPassword(CipherUtils.encrypt(form.getPassword()));
		loginUser.setLoginCount(0);
		
		tableRepository.save(loginUser);
		
		/*
		 * 保存用户信息
		 */
		Date today = Calendar.getInstance().getTime();
		DriveTrainee user = new DriveTrainee();
		user.setId(loginUser.getId());
		user.setCellPhone(form.getCellPhone());
		user.setName(form.getUserName());
		user.setRegisterTime(today);
		
		tableRepository.insert(user);
		
		LoginView view = new LoginView();
		
		view.setUserId(loginUser.getId());
		
		return view;
	}
	
	@Transactional(rollbackFor=Throwable.class)
	public LoginView login(LoginForm form) throws Exception {
		LoginUser loginUser = mobileRepository.findLoginUser(form.getName(), CipherUtils.encrypt(form.getPassword()));
		if(loginUser == null){
			throw new ValidationException("用户名或密码错误");
		}
		
		/*
		 * 更新用户登录信息
		 */
		int count = loginUser.getLoginCount();
		Date today = Calendar.getInstance().getTime();
		
		loginUser.setLoginCount(count + 1);
		loginUser.setLastLoginTime(today);
		loginUser.setLoginStatus(DBValueConstants.YES);
		loginUser.setImId(form.getImId());
		loginUser.setCellPhoneType(form.getMobileType());
		tableRepository.save(loginUser);
		
//		DriveTrainee user = tableRepository.find(loginUser.getId(), DriveTrainee.class);
//		tableRepository.save(user);
		
		LoginView view = new LoginView();
		
		view.setUserId(loginUser.getId());
		
		return view;
	}
	
	@Transactional(rollbackFor=Throwable.class)
	public LogoutView logout(LogoutForm form) throws Exception{
		LoginUser loginUser = tableRepository.find(form.getUserId(), LoginUser.class);
		
		if(loginUser == null){
			throw new ValidationException("用户不存在");
		}
		
		/*
		 * 保存用户登出信息
		 */
		loginUser.setLoginStatus(DBValueConstants.NO);
		tableRepository.save(loginUser);
		
		return new LogoutView();
	}
}

