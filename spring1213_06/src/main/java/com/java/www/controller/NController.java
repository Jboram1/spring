package com.java.www.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("notice")
public class NController {
	
	@RequestMapping("tlist")
	public String tlist() {
		return "notice/tlist";
	}
	
	@RequestMapping("tview")
	public String tview() {
		return "notice/tview";
	}
	
	
	@RequestMapping("twrite")
	public String twrite() {
		return "notice/twrite";
	}
	

}
