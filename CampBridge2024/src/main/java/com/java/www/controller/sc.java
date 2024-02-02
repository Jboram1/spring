package com.java.www.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("ss")
public class sc {

	@GetMapping("tSearch")
	public String tSearch() {
		return "/ss/tSearch";
	}// tSearch()
	
}
