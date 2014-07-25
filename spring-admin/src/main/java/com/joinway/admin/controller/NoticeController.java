package com.joinway.admin.controller;

import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.joinway.admin.service.NoticeService;
import com.joinway.appx.repository.SystemRepository;
import com.joinway.bean.logging.annotation.InputLog;
import com.joinway.web.audit.annotation.Audit;

@Controller
@RequestMapping("notice")
@Validated
public class NoticeController {

	@Autowired SystemRepository repository;
	
	@Autowired NoticeService service;
	
    @RequestMapping(value="{page}")
    @Audit()
    @InputLog
    public ModelAndView acknowledge(@PathVariable("page") @NotBlank String page, @RequestParam("userId") @Min(1) int userId) throws Exception {
        String pushDir = repository.getValue(SystemRepository.PushDir);
        
        service.acknowledge(page, userId);
        
        return new ModelAndView(pushDir + "/" + page);
    }

}

