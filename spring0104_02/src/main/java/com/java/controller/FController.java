package com.java.controller;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.java.dto.BoardDto;
import com.java.service.BService;

import jakarta.servlet.http.HttpSession;

@Controller
public class FController {
	
	@Autowired BService bService;
	@Autowired HttpSession session;
	
	
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@GetMapping("bview")
	public String bview(@RequestParam(defaultValue = "1") int bno, Model model) {
		//게시글 1개 가져오기
		BoardDto bdto = bService.selectOne(bno);
		model.addAttribute("bdto",bdto);
		return "bview";
	}
	
	@GetMapping("blist")
	public String blist(Model model) {
		//전체 게시글 가져오기
		List<BoardDto> list = bService.blist();
		model.addAttribute("list",list);
		return "blist";
	}
	
	@GetMapping("bwrite")
	public String bwrite() {
		return "bwrite";
	}
	
	//내용부분 - 이미지 추가시 파일업로드
	@PostMapping("summernoteUpload")
	@ResponseBody
	public String summernoteUpload(@RequestPart MultipartFile file) throws Exception {
		String urlLink = "";
		if(!file.isEmpty()) {
			String oriFName = file.getOriginalFilename();
			long time = System.currentTimeMillis();
			String upFName = time+"_"+oriFName;
			String fupload = "c:/upload/";
			
			//파일서버
			File f = new File(fupload+upFName);
			file.transferTo(f);
			
			//파일저장위치
			urlLink = "/upload/"+upFName;
			
		}
		return urlLink;
	}
	
	
	@PostMapping("bwrite")
	public String bwrite(BoardDto bdto, @RequestPart MultipartFile file, Model model) throws Exception {
		//submit버튼 클릭시 게시글 저장
		System.out.println("FC btitle : "+bdto.getBtitle());
		
		if(!file.isEmpty()) {
			String oriFName = file.getOriginalFilename();
			long time = System.currentTimeMillis();
			String upFName = time+"_"+oriFName;
			String fupload = "c:/upload/";
			
			bdto.setBfile(upFName);
		}else {
			bdto.setBfile("");
		}
		
		bService.bwrite(bdto);
		model.addAttribute("result","write-s");
		
		return "dobWrite";
	}

}
