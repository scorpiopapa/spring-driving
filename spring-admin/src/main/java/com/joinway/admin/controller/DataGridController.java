package com.joinway.admin.controller;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.jsondoc.core.annotation.Api;
import org.jsondoc.core.annotation.ApiMethod;
import org.jsondoc.core.annotation.ApiResponseObject;
import org.jsondoc.core.pojo.ApiVerb;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joinway.appx.bean.view.DataGridView;
import com.joinway.appx.bean.view.TableResultView;
import com.joinway.appx.service.DataGridService;
import com.joinway.bean.logging.annotation.InputLog;
import com.joinway.bean.logging.annotation.LogIgnore;
import com.joinway.bean.logging.annotation.OutputLog;
import com.joinway.web.audit.ExceptionController;
import com.joinway.web.audit.annotation.Audit;
import com.joinway.web.security.annotation.SecurityCheck;

@Api(name = "DataGrid Controller", description = "......")
@Controller
@RequestMapping("admin")
@Validated
@SecurityCheck
public class DataGridController extends ExceptionController {
	
	private final static Logger log = LoggerFactory.getLogger(DataGridController.class);
	
	@Autowired DataGridService service;
	
	@ApiMethod(path="app/search/{table}", verb=ApiVerb.GET, description=".........", produces=MediaType.APPLICATION_JSON_VALUE)
	@ApiResponseObject
	@RequestMapping(value="search/{table}", produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Audit("search")
	@InputLog
	@OutputLog
	public DataGridView searchTable(@PathVariable("table") String table, @LogIgnore HttpServletRequest request) throws Exception {
//		printParams(request);
		return service.findRecord(table.toUpperCase(), request.getParameterMap());
	}

	@ApiMethod(path="app/save/{table}", verb=ApiVerb.POST, description="....", produces=MediaType.APPLICATION_JSON_VALUE)
	@ApiResponseObject
	@RequestMapping(value="save/{table}", produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Audit("save")
	@InputLog
	@OutputLog
	public TableResultView saveTable(@PathVariable("table") String table, @LogIgnore HttpServletRequest request) throws Exception {
//		printParams(request);
		return service.saveTable(table.toUpperCase(), request.getParameterMap());
	}
	
	@ApiMethod(path="app/delete/{table}", verb=ApiVerb.POST, description="....", produces=MediaType.APPLICATION_JSON_VALUE)
	@ApiResponseObject
	@RequestMapping(value="delete/{table}", produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Audit("delete")
	@InputLog
	@OutputLog
	public TableResultView deleteTable(@PathVariable("table") String table, @RequestParam("id") String ids) {
//		printParams(request);
		TableResultView view;
		
		String[] arr = StringUtils.split(StringUtils.trimToEmpty(ids).replaceAll("\\s+", ""), ",");
		if(ArrayUtils.isNotEmpty(arr)){
			view = service.deleteTable(table.toUpperCase(), arr);
		}else{
			view = new TableResultView();
		}
		
		return view;
	}
	
	@Deprecated
	void printParams(HttpServletRequest request){
		Enumeration<String> enums = request.getParameterNames();
		while(enums.hasMoreElements()){
			String name = enums.nextElement();
			String value = request.getParameter(name);
	
			System.err.println(name + "=[" + value + "]");
//			try {
//				log.info(new String(value.getBytes("ISO-8859-1"), "UTF-8"));
//			} catch (UnsupportedEncodingException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
		}
	}
}

