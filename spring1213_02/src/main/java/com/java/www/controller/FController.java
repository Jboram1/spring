package com.java.www.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.java.www.service.PService;

@Controller
public class FController {
	
	@Autowired
	PService pService;
	
	@GetMapping("/")     //GetMapping, PostMapping, RequestMapping
	public String index() {
		pService.execute();
		return "index";
	}
	
	@GetMapping("list")
	public String list() {
		return "list";
	}
	
	@GetMapping("mwrite")
	public String mwrite() {
		return "mwrite";
	}

}
