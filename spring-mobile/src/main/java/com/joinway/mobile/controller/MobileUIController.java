package com.joinway.mobile.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.joinway.bean.logging.annotation.InputLog;
import com.joinway.bean.logging.annotation.OutputLog;
import com.joinway.web.audit.annotation.Audit;

@Controller
@RequestMapping("")
@Validated
public class MobileUIController {
	// @RequestMapping(value="push/{notice}", method=RequestMethod.GET)
	@RequestMapping(value="push")
	@Audit
	@InputLog
	@OutputLog
	public ModelAndView getMessage() {
	// public ModelAndView getMessage(@PathVariable("notice") @NotBlank String notice, @RequestParam("userId") @Min(1) int userId) throws Exception {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("notice2");
	
		return mv;
	}
}
