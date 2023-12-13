package com.java.www.controller;

import java.sql.Timestamp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.java.www.dto.MemberDto;

@Controller
public class MController {

	@RequestMapping("member/tinsert")
	public String tinsert(Model model) {
		String id = "nana";
		Timestamp mdate = new Timestamp(System.currentTimeMillis());
		MemberDto mdto = new MemberDto("nana","1111", "김나나", "010-1234-5678", "Femal", "book,run", mdate);
		
		model.addAttribute("mdto",mdto);
		return "member/tinsert";
	}
}
