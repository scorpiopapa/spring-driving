package com.joinway.admin.bean.view;

import com.joinway.appx.bean.domain.PushLog;

public class PushLogView extends PushLog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1987983658656252709L;

	int unAcceptCount;

	public int getUnAcceptCount() {
		return unAcceptCount;
	}

	public void setUnAcceptCount(int unAcceptCount) {
		this.unAcceptCount = unAcceptCount;
	}
	
}
