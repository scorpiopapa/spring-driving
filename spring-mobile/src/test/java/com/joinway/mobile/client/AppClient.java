package com.joinway.mobile.client;

import com.joinway.mobile.bean.form.LoginForm;
import com.joinway.mobile.bean.form.LogoutForm;
import com.joinway.mobile.bean.form.RegisterForm;
import com.joinway.net.http.HttpClientAdaptor;

public class AppClient {

	static final HttpClientAdaptor client = new HttpClientAdaptor();
	
	static final String BASE_URL = "http://localhost:8080/spring-mobile";
//	static final String BASE_URL = "http://115.28.198.131:8000/spring-mobile";
	
	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		login();
//		register();
//		menu();
//		logout();
//		Object o = null;
//		Object oo = (Object)o;
//		out.println(oo);
	}

	static void register() throws Exception {
		RegisterForm form = new RegisterForm();
		form.setCellPhone("13998429427");
		form.setName("lee1234");
		form.setPassword("123456");
		form.setUserName("lee1234");
		
		client.post(form, BASE_URL + "/register");
//		client.post(BASE_URL + "/register", new PostParameterBuilder().add("name", "lee").add("password", "123").list());
	}

	static void login() throws Exception {
		LoginForm form = new LoginForm();
//		form.setName("lee1235");
//		form.setPassword("123456");
//		form.setMobileType("A");
//		form.setImId("123");
		
		form.setName("lee123");
		form.setPassword("123456");
		form.setMobileType("A");
		form.setImId("123");

		client.post(form, BASE_URL + "/login");
	}

	static void logout() throws Exception {
		LogoutForm form = new LogoutForm();
		form.setUserId(3);
		client.post(form, BASE_URL + "/logout");
	}
	
//	static String postRequest(String url, List<NameValuePair> nvps) throws Exception {
//		out.println(nvps);
//		
//	    HttpPost post = new HttpPost(BASE_URL + url);
//	    
//	    post.setEntity(new UrlEncodedFormEntity(nvps, Consts.UTF_8));
//	    post.addHeader(SecurityConstants.RequestHeader.KEY, SecurityConstants.RequestHeader.VALUE);
//	    
//	    HttpResponse response = client.execute(post);
//	    out.println(response.getStatusLine());
//	    
//	    HttpEntity entity = response.getEntity();
//	    String json = EntityUtils.toString(entity);
//	    out.println(json);
//	    
//	    return json;
//	}
//
//	static String getRequest(String url, String query) throws Exception {
//		HttpGet get = new HttpGet(BASE_URL + url + query);
//		
//		HttpResponse response = client.execute(get);
//		out.println(response.getStatusLine());
//		
//		HttpEntity entity = response.getEntity();
//	    String json = EntityUtils.toString(entity);
//	    out.println(json);
//	    
//	    return json;
//	}
//	
//	static String getRequest(String url) throws Exception {
//		return getRequest(url, "");
//	}
	

}
