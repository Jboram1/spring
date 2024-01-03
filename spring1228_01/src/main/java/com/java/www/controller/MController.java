package com.java.www.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.java.www.dto.MemberDto;
import com.java.www.service.MService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("member")
public class MController {
	
	@Autowired MService mService;
	@Autowired HttpSession session;
	
	
	@GetMapping("login")
	public String login() {
		return "member/login";
	}
	
	@PostMapping("loginCheck")
	@ResponseBody
	public String loginCheck(MemberDto mdto) {
		System.out.println("MC login id : "+mdto.getId());
		//service - 로그인 확인
		String result = mService.loginCheck(mdto);
		return result; //사용가능,사용불가
	}
	
	@RequestMapping("step03")
	public String step03() {
		return "member/step03";
	}
	
	@RequestMapping("doStep03")
	public String doStep03(MemberDto mdto) {
		
		List<MemberDto> list = mService.mInsert(mdto);
		
		return "list";
	}
	
	

	
	
	
	
	
}
