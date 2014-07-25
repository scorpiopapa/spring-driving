package com.joinway.admin.controller;

import javax.validation.Valid;

import org.jsondoc.core.annotation.ApiBodyObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joinway.admin.bean.UserContext;
import com.joinway.admin.bean.form.LoginForm;
import com.joinway.admin.bean.form.PushAllForm;
import com.joinway.admin.bean.form.RegisterForm;
import com.joinway.admin.bean.view.LoginView;
import com.joinway.admin.bean.view.LogoutView;
import com.joinway.admin.bean.view.PushView;
import com.joinway.admin.bean.view.TreeMenuView;
import com.joinway.admin.service.AdminService;
import com.joinway.admin.utils.SessionHelper;
import com.joinway.bean.exception.ValidationException;
import com.joinway.bean.logging.annotation.InputLog;
import com.joinway.bean.logging.annotation.OutputLog;
import com.joinway.web.audit.ExceptionController;
import com.joinway.web.audit.annotation.Audit;
import com.joinway.web.security.annotation.Login;
import com.joinway.web.security.annotation.Logout;
import com.joinway.web.security.annotation.SecurityCheck;
import com.joinway.web.utils.FrameworkHelper;

/**
 * 建议把session相关操作的代码放到controller里，service保持无状态
 * @author Administrator
 *
 */
@Controller
@RequestMapping("admin")
@Validated
public class AdminController extends ExceptionController {

	private final static Logger log = LoggerFactory.getLogger(AdminController.class);
	
	@Autowired AdminService service;
	
	@RequestMapping(value="register", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Login
	@Audit("admin register")
	@InputLog
	@OutputLog
	public LoginView register(@ApiBodyObject @Valid RegisterForm form) throws Exception {
		preLogin(form.getName());
		
		LoginView view = service.register(form);
		
		postLogin(view);
		
		return view;
	}
	
	@RequestMapping(value="login", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Login
	@Audit("admin login")
	@InputLog
	@OutputLog
	public LoginView login(@ApiBodyObject @Valid LoginForm form) throws Exception {
		preLogin(form.getName());
		
		LoginView view = service.login(form);
		
		postLogin(view);
		
		return view;
	}
	
	@RequestMapping(value="login/context", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@SecurityCheck
	@Audit("admin user context")
	@InputLog
	@OutputLog
	public LoginView getLoginContext() throws ValidationException {
		LoginView view = new LoginView();
		
		UserContext uc = SessionHelper.getUserContext(true);
		view.setUserName(uc.getUserName());
		view.setLastLoginTime(uc.getLastLoginTime());
		view.setLoginCount(uc.getLoginCount());
		
		return view;
	}

	@RequestMapping(value="logout", produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Audit("admin logout")
	@Logout
	@InputLog
	@OutputLog
	public LogoutView logout(){
		LogoutView view = service.logout();
		
		UserContext uc = SessionHelper.getUserContext();
		if(uc != null){
			view.setUserName(uc.getUserName());
		}
		
		log.info("user logged out");
		
		return view;
	}
	
	@RequestMapping(value="navigator", produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@SecurityCheck
	@Audit("admin navigator")
	@InputLog
	@OutputLog
	public TreeMenuView navigator() throws Exception {
//		log.info("------------------{}--------------------", FrameworkHelper.getHttpServletRequest().getServletContext());
		return service.getNavigatorMenus(SessionHelper.getUserContext().getUserId());
	}
	
	@RequestMapping(value="push", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Audit("admin push")
	@InputLog
	@OutputLog
	public PushView push(@ApiBodyObject @Valid PushAllForm form) throws Exception {
		return service.push(form);
	}

	void preLogin(String userName){
		UserContext uc = SessionHelper.getUserContext(true);
		// set login user name anyway for audit usage
		uc.setUserName(userName);
	}

	void postLogin(LoginView view){
		UserContext uc = SessionHelper.getUserContext();

		uc.setLastLoginTime(view.getLastLoginTime());
		uc.setLoginCount(view.getLoginCount());
		uc.setUserId(view.getUserId());
		
//		// 设置sso
//		AppContext.set(SecurityConstants.SSO.UID_KEY, uc.getUserId());
	}
}
