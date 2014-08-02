package com.joinway.admin.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joinway.admin.bean.view.PushLogView;
import com.joinway.appx.bean.domain.PushLog;
import com.joinway.appx.bean.domain.UserNoticeLog;
import com.joinway.appx.bean.view.DataGridView;
import com.joinway.appx.repository.NoticeRepository;
import com.joinway.appx.service.CustomTableService;
import com.joinway.appx.service.DefaultTableService;
import com.joinway.db.constant.DBValueConstants;

@Service
public class PushLogTableService extends DefaultTableService implements CustomTableService {

	private final static Logger log = LoggerFactory.getLogger(PushLogTableService.class);
			
	@Autowired NoticeRepository repository;
	
	@Override
	public String getTargetTableName() {
		return "PUSH_LOG";
	}

	@SuppressWarnings("unchecked")
	@Override
	public DataGridView findRecord(String table, Map<String, String[]> params) throws Exception {
		DataGridView view = super.findRecord(table, params);
		
		List<PushLog> rows = (List<PushLog>)view.getRecord().getRows();
		
		List<PushLogView> data = new ArrayList<>();
		
		for(PushLog pl : rows){
			String pageName = pl.getPageName();
			
			PushLogView pushLogView = new PushLogView();
			PropertyUtils.copyProperties(pushLogView, pl);

			List<UserNoticeLog> list = repository.findUserNoticeLog(pageName, DBValueConstants.NO);
			pushLogView.setUnAcceptCount(list == null ? 0 : list.size());
			
			data.add(pushLogView);
		}
		
		view.getRecord().setRows(data);
		
		return view;
	}

}
