package com.joinway.mobile.push;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.Notification;

public class JPushTest {

	@Test public void test1() throws Exception {
		JPushClient client = new JPushClient("6234661038a43726dc32fe40", "d5468e2738cd06e4520eef17");
//		PushResult result = client.sendNotificationAll("你好2");
//		PushResult result = client.sendMessageAll("test");
		
		Map<String, String> extras = new HashMap<>();
		extras.put("url", "www.baidu.com");
		
		PushResult result = client.sendPush(PushPayload.newBuilder()
												.setNotification(Notification.android("test", null, extras))
												.setAudience(Audience.all())
												.setPlatform(Platform.all())
												.build());
		System.out.println(result);
		
		if(result.isResultOK()){
			System.out.println("Good!");
		}else{
			System.out.println("Failed!");
		}
	}
	
	@Test public void test2(){
		System.out.println(System.getProperty("user.home"));
	}
}
