package com.java.www.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.java.www.dto.BoardDto;
import com.java.www.service.BService;

import jakarta.servlet.http.HttpSession;

@Controller
public class BController {
	
	@Autowired
	BService bService;
	@Autowired
	HttpSession session;
	
	@RequestMapping("bList")
	public String bList(Model model) {
		ArrayList<BoardDto> list = bService.bListAll();
		model.addAttribute("list",list);
		return "bList";
	}
	
	@RequestMapping("bView")
	public String bView(@RequestParam(defaultValue = "1")int bno, Model model) {
		Map<String, Object> map = bService.bListOne(bno);
		model.addAttribute("map",map);
		return "bView";
	}
	
	
	@GetMapping("bInsert")
	public String bInsert() {
		return "bInsert";
	}
	
	@PostMapping("bInsert")
	public String bInsert(BoardDto bdto, @RequestPart MultipartFile files, Model model) throws Exception {
		if(!files.isEmpty()) {
			String oName = files.getOriginalFilename();
			long time = System.currentTimeMillis();
			String nName = time+"_"+oName;
			String upload = "c:/upload/";
			File f = new File(upload+nName);
			files.transferTo(f);
			bdto.setBfile(nName);
		}else {
			bdto.setBfile("");
		}
		bService.bInsert(bdto);
		return "doBInsert";
	}
	
	@RequestMapping("bDelete")
	public String bDelete(@RequestParam(defaultValue = "1")int bno, Model model) {
		bService.bDelete(bno);
		return "bDelete";
	}
	
	

}
