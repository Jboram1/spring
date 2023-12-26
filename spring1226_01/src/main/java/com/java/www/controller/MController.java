package com.java.www.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	
	@GetMapping("logout")
	public String logout() {
		session.invalidate(); //
		return "member/logout";
	}
	
	//---------로그인 확인 부분------------
	//---------ajax 형태------------
	@PostMapping("ajaxLogin")
	@ResponseBody //데이터 전송(페이지가 열리지 않고 데이터만 전송해줌)
	public String ajaxLogin(MemberDto mdto) {
		
		System.out.println("MC login id :"+mdto.getId());
		System.out.println("MC login pw :"+mdto.getPw());
		
		//service연결 - 1:성공,0:실패
		int result = mService.login(mdto);
		System.out.println("MC login result :"+result);
		
		//ajax은 model에 태우지 않고 return에 result+""형태로 데이터를 직접 보낸다.
		//페이지를 열지 않고 그냥 바로 보여줌
		return result+"";
	}
	
	//---------jsp 형태------------
	@PostMapping("login")
	public String login(MemberDto mdto, Model model) {
		System.out.println("MC login id :"+mdto.getId());
		System.out.println("MC login pw :"+mdto.getPw());
		
		//service연결 - 1:성공,0:실패
		int result = mService.login(mdto);
		//model
		model.addAttribute("result",result);
		System.out.println("MC login result :"+result);
		
		return "member/doLogin";
	}
	
}
