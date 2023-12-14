package com.java.www.controller;


import java.sql.Timestamp;
import java.util.Arrays;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.java.www.dto.MemberDto;

import jakarta.servlet.http.HttpServletRequest;


@Controller
public class MController {
	
	
	@GetMapping("member/memberInsert")
	public String memberInsert(Model model) {
		return "member/memberInsert";
	}
	
	@GetMapping("member/login")
	public String login(Model model) {
		return "member/login";
	}
	
	@PostMapping("member/doLogin")
	public String doLogin(String id, //@RequestParam 자동형변환해줌
			String pw, 
			@RequestParam(defaultValue="1") int bno, Model model) { //(defaultValue="1") = 안적었을때 1로표시 (게시판페이지값에 지정)
		System.out.println("controller id : "+id);
		System.out.println("controller pw : "+pw);
		System.out.println("controller bno : "+bno);
		
		return "/index";
	}
	
	@PostMapping("member/doMemberInsert")
	public String doMemberInsert(HttpServletRequest request, Model model) {
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String name = request.getParameter("name");
		String phone = request.getParameter("phone");
		String gender = request.getParameter("gender");
		String[] hobbys = request.getParameterValues("hobby");
		System.out.printf("%s,%s,%s,%s,%s,%s",id,pw,name,phone,gender,
				Arrays.toString(hobbys)); //hobby에 있는 꺽세표 구분해서 다 찍어줌
		return "member/memberView";
	}
	
	
	
	
	//Model사용
	@RequestMapping("member/memberUpdate")
	public String memberUpdate(Model model) {
		//id를 전달
		//현재 날짜와 시간을 저장
		Timestamp mdate = new Timestamp(System.currentTimeMillis());
		MemberDto mdto
		= new MemberDto("bbb", "1111", "유관순", "010-1111-1111", "male", "game,golf", mdate);
		System.out.println("MemberDto id : "+mdto.getId());
		
		//model.addAttribute("id",id);
		model.addAttribute("mdto",mdto);
		//HttpServletRequest request;
		//request.setAttribute("id", id);
		return "member/memberUpdate";
	}
	
	

	
	//ModelAndView사용
	/*
	@RequestMapping("member/memberInsert")
	public ModelAndView memberInsert() {
		ModelAndView mv = new ModelAndView();
		//id를 전달
		String id = "admin";
		//현재 날짜와 시간을 저장
		Timestamp mdate = new Timestamp(System.currentTimeMillis());
		MemberDto mdto
		= new MemberDto("bbb", "1111", "유관순", "010-1111-1111", "male", "game,golf", mdate);
		System.out.println("MemberDto id : "+mdto.getId());
		
		//model.addAttribute("id",id);
		mv.addObject("mdto",mdto);
		mv.setViewName("member/memberInsert");
		//HttpServletRequest request;
		//request.setAttribute("id", id);
		return mv;
	}
	*/
	
	
}
