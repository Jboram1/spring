package com.java.www.controller;

import java.sql.Timestamp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.java.www.dto.BoardDto;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("notice") //상위폴더
public class NController {
	
	//@RequestMapping("noticeList")
	@RequestMapping(method=RequestMethod.GET,value = "noticeList") //이게정석임 현재는간편해짐
	public String noticeList() {
		return "notice/noticeList"; //notice폴더 안에 넣어두면 리턴은 꼭 notice/noticeList 이렇게 적어줘야함.
	}
	
	@GetMapping("noticeInsert")
	public String noticeInsert() {
		return "notice/noticeInsert";
	}
	
	@PostMapping("doNticeInsert")
	public String doNoticeInsert(HttpServletRequest request, Model model) {
		String btitle = request.getParameter("btitle");
		String bcontent = request.getParameter("bcontent");
		String bfile = request.getParameter("bfile");
		System.out.printf("%s,%s,%s\n",btitle,bcontent,bfile);
		System.out.println("controller btitle : "+btitle);
		System.out.println("controller bcontent : "+bcontent);
		System.out.println("controller bfile : "+bfile);
		Timestamp bdate = new Timestamp(System.currentTimeMillis());
		
		BoardDto bdto = BoardDto.builder() //변수순서바뀌어도 상관없다
				.btitle(btitle)
				.bcontent(bcontent)
				.bdate(bdate)
				.bfile(bfile)
				.build();
//		model.addAttribute("btitle",btitle);
//		model.addAttribute("bcontent",bcontent);
//		model.addAttribute("bdate",bdate);
//		model.addAttribute("bfile",bfile);
		model.addAttribute("bdto",bdto);
		return "notice/noticeView";
	}
	
	@RequestMapping("noticeView") //GetMapping, PostMapping 둘 다 가능
	public String noticeView(Model model) {
		return "notice/noticeView";
	}
	
	

}
