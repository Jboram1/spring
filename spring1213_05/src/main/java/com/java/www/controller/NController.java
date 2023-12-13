package com.java.www.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("notice")
public class NController {
	
	@RequestMapping("noticeList")
	public String noticeList() {
		return "notice/noticeList"; //notice폴더 안에 넣어두면 리턴은 꼭 notice/noticeList 이렇게 적어줘야함.
	}
	
	@RequestMapping("noticeInsert")
	public String noticeInsert() {
		return "notice/noticeInsert";
	}
	
	@RequestMapping("noticeView") //GetMapping, PostMapping 둘 다 가능
	public String noticeView() {
		return "notice/noticeView";
	}
	
	

}
