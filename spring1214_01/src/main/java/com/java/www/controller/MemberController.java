package com.java.www.controller;

import java.sql.Timestamp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.java.www.dto.BoardDto;
import com.java.www.dto.MemberDto;

@Controller
@RequestMapping("member")
public class MemberController {
	
	@RequestMapping("mInsert")
	public String mInsert() {
		return "member/memberInsert";
	}
	
	@RequestMapping("doMInsert")
	public String doMInsert(MemberDto mdto, Model model) {
		model.addAttribute("mdto",mdto);
		
		return "member/memberView";
	}
	
	
	
	@RequestMapping("memberUpdate")
	public String memberUpdate(MemberDto mdto, Model model) {
		
		model.addAttribute("mdto",mdto);
		
		return "member/memberUpdate";
	}

	
	@RequestMapping("login")
	public String login() {
		return "member/login";
	}
	
	@PostMapping("doLogin") //@RequestParam(required = false)필수항목아니다, 공란이여도 된다는 말 @RequestParam(defaultValue = "1") 변수가 int타입일때는 무조건 defaultValue로 넣어줘야한다.
	public String doLogin(MemberDto memberDto, 
			BoardDto boardDto, Model model) {
		
		System.out.println("controller id : "+memberDto.getId());
		System.out.println("controller pw : "+memberDto.getPw());
		System.out.println("controller bno : "+boardDto.getBno());
		String id = memberDto.getId();
		String pw = memberDto.getPw();
		int bno = boardDto.getBno();
		
		//기본생성자
		MemberDto mdto = new MemberDto();
		mdto.setId(id);
		System.out.println("mdto.getid : "+mdto.getId());
		Timestamp mdate = new Timestamp(System.currentTimeMillis());
		//전체생성자
		MemberDto mdto2 = new MemberDto(id, pw, "홍길동", "010-1234-1111", "male", "game,golf",mdate);
		//부분생성자 //한번 필요할때는 build쓰기. 자주쓸때는 Dto에 생성해놓기
		MemberDto mdto3 = MemberDto.builder().id(id).pw(pw).name("유관순").gender("femal").build();
		
		System.out.println("controller mdto3 name : "+mdto3.getName());
		
		
		model.addAttribute("id",id); //request
		model.addAttribute("pw",pw);
		model.addAttribute("bno",bno);
		model.addAttribute("mdto",memberDto); //객체를 태울수도있고 변수를 태울수도있다
		
		
		
		return "member/doLogin";
	}
	
}
