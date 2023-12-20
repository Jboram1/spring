package com.java.www.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.java.www.dto.MemberDto;
import com.java.www.service.MService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import oracle.jdbc.proxy.annotation.Post;

@Controller
public class MController {

	@Autowired
	MService mService;
	@Autowired
	HttpSession session;
	
	
	@RequestMapping("login")
	public String login() {
		return "login";
	}
	
	@RequestMapping("logout")
	public String logout() {
		session.invalidate();
		return "logout";
	}
	
	@RequestMapping("doLogin")
	public String doLogin(MemberDto mdto,Model model, HttpServletRequest request) {
		int result = 0;
		System.out.println("MC id : "+mdto.getId());
		MemberDto memberDto = mService.loginSelect(mdto);
		if(memberDto!=null) {
			session.setAttribute("session_id", memberDto.getId());
			session.setAttribute("session_name", memberDto.getName());
			System.out.println("MC id 있음 : "+memberDto.getId());
			result =1;
		}else {
			System.out.println("MC memberDto : null");
			
		}
		
		model.addAttribute("result",result);
		System.out.println("MC result : "+result);
		return "doLogin";
	}
	
	
	
	
}
