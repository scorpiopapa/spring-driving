package com.joinway.admin.service;

import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.joinway.admin.bean.domain.AdminUser;
import com.joinway.admin.bean.domain.TreeMenu;
import com.joinway.admin.bean.form.LoginForm;
import com.joinway.admin.bean.form.PushAllForm;
import com.joinway.admin.bean.form.RegisterForm;
import com.joinway.admin.bean.view.LoginView;
import com.joinway.admin.bean.view.LogoutView;
import com.joinway.admin.bean.view.Menu;
import com.joinway.admin.bean.view.PushView;
import com.joinway.admin.bean.view.TreeMenuView;
import com.joinway.admin.repository.AdminRepository;
import com.joinway.admin.utils.UIHelper;
import com.joinway.appx.repository.SystemRepository;
import com.joinway.appx.service.MessagePushService;
import com.joinway.bean.exception.DuplicateDataException;
import com.joinway.bean.exception.ValidationException;
import com.joinway.bean.logging.annotation.InputLog;
import com.joinway.db.repository.TableRepository;
import com.joinway.utils.CipherUtils;
import com.joinway.web.utils.FrameworkHelper;

@Service
public class AdminService {

	private final static Logger log = LoggerFactory.getLogger(AdminService.class);

	@Autowired AdminRepository repository;

	@Autowired TableRepository tableRepository;

	@Autowired SystemRepository systemRepository;
	
	@Autowired MessagePushService pushService;
	
	@Transactional(rollbackFor=Throwable.class)
	public LoginView register(RegisterForm form) throws Exception {
		AdminUser adminUser = repository.findAdminUser(form.getName());

		if (adminUser == null) {
			Date currentTime = Calendar.getInstance().getTime();
			adminUser = new AdminUser();
			adminUser.setLoginName(form.getName().toLowerCase());
			adminUser.setPassword(CipherUtils.encrypt(form.getPassword()));
			adminUser.setLastLoginTime(currentTime);
			adminUser.setCreateTime(currentTime);
			adminUser.setLoginCount(1);
		} else {
			throw new DuplicateDataException("user " + form.getName() + " exists");
		}

		tableRepository.save(adminUser);
		LoginForm loginForm = new LoginForm();
		loginForm.setName(form.getName());
		loginForm.setPassword(form.getPassword());

		return createLoginView(adminUser);
	}

	@Transactional(rollbackFor=Throwable.class)
	public LoginView login(LoginForm form) throws Exception {
		AdminUser adminUser = repository.findAdminUser(form.getName(), CipherUtils.encrypt(form.getPassword()));

		if (adminUser == null) {
			throw new ValidationException("user " + form.getName() + " doesn't exits");
		}

		adminUser.setLastLoginTime(Calendar.getInstance().getTime());
		adminUser.setLoginCount(adminUser.getLoginCount() + 1);

		// repository.save(loginUser);
		tableRepository.save(adminUser);
		return createLoginView(adminUser);
	}

	public LogoutView logout() {
		return new LogoutView();
	}

	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	@InputLog
	public TreeMenuView getNavigatorMenus(int userId) throws Exception {
		List<TreeMenu> trees = null;

		trees = tableRepository.find(TreeMenu.class);
//		if (isSuperUser(userId)) {
//			trees = tableRepository.find(TreeMenu.class);
//			trees = Filter.create(trees, Condition.create("status", DBConstants.YES)).result();
//		} else {
//			trees = repository.findUserTreeMenus(userId);
//		}

		TreeMenuView view = new TreeMenuView();

		List<Menu> menus = UIHelper.convert(trees);
		view.setMenus(menus);

		return view;
	}

	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public PushView push(PushAllForm form) throws Exception {
		AdminUser au = tableRepository.find(form.getUserId(), AdminUser.class);
		
		pushService.broadcast(form.getText(), au.getLoginName());

		return new PushView();
	}
	
//	String createHtmlMessageDir(){
//		String pushDir = FrameworkHelper.getHttpServletRequest().getServletContext().getRealPath("/") + "push";
//		File dir = new File(pushDir);
//		
//		if(!dir.exists()){
//			log.info("directory {} doesn't exist, create a new one", dir);
//			dir.mkdirs();
//		}
//		
//		return pushDir;
//	}
	
	LoginView createLoginView(AdminUser adminUser) {
		LoginView view = new LoginView();
		view.setUserName(adminUser.getLoginName().toLowerCase());
		view.setLastLoginTime(adminUser.getLastLoginTime());
		view.setLoginCount(adminUser.getLoginCount());
		view.setUserId(String.valueOf(adminUser.getId()));

		return view;

	}

//	boolean isSuperUser(int userId) throws Exception {
//		boolean ret = false;
//
//		List<UserRole> userRoles = tableRepository.find(UserRole.class);
//		List<UserRole> loginUserRoles = Filter.create(userRoles, Condition.create("userId", userId)).result();
//
//		if (CollectionUtils.isNotEmpty(loginUserRoles)) {
//			List<Integer> roleIds = ReflectionUtils.getCollection(loginUserRoles, "roleId", Integer.class);
//			List<Role> roles = tableRepository.find(Role.class);
//			Role superRole = Filter.create(roles, Condition.create("roleName", DBConstants.SUPER_ROLE_NAME)).result().get(0);
//			ret = roleIds.contains(superRole.getRoleId());
//		}
//
//		return ret;
//	}

}
