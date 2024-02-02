package com.java.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.java.dto.BoardDto;
import com.java.service.BoardService;

@Controller
@RequestMapping("customer") //customer/notice
public class BoardController {
	
	@Autowired BoardService boardService;
	
	
	@GetMapping("notice") //게시글 전체가져오기
	public String notice(Model model) {
		ArrayList<BoardDto> list = boardService.selectAll();
		model.addAttribute("list",list);
		return "customer/notice";
	}
	@GetMapping("notice_view") //게시글 1개 가져오기
	public String notice_view(@RequestParam(defaultValue = "1") int bno, Model model) {
		Map<String, Object> map = boardService.selectOne(bno);
		model.addAttribute("map",map);
		return "customer/notice_view";
	}
	
	@GetMapping("notice_write") //글쓰기 폼
	public String notice_write() {
		return "customer/notice_write";
	}
	
	@PostMapping("notice_write") //게시글 글쓰기저장
//	public String notice_write(BoardDto bdto, @RequestPart MultipartFile files, Model model) { //파일1개 받을때
	public String notice_write(BoardDto bdto, List<MultipartFile> files, Model model) throws Exception { //파일2개 받을때
		//파일업로드 정보 - 파일저장위치
		String fileUrl = "c:/upload/";
		String bfileName = ""; //파일2개를 1개로 이름합칠때 필요
//		String bfileName1 = ""; //컬럼여러개일때
//		String bfileName2 = "";
		System.out.println("작성자 : "+bdto.getId());
		System.out.println("제목 : "+bdto.getBtitle());
		System.out.println("내용 : "+bdto.getBcontent());
		
		//파일첨부가 되었는지 확인
		if(!files.isEmpty()) {
			int i = 0;
			for(MultipartFile file:files) {
				String orgfileName = file.getOriginalFilename();
				long time = System.currentTimeMillis();
				String uploadFileName = time+"_"+orgfileName;
				System.out.println("파일이름 : "+uploadFileName);
				//파일업로드 - 파일이 c:/upload폴더에 추가됨.
				File f = new File(fileUrl+uploadFileName);
				file.transferTo(f);
				
				//파일2개를 1개로 이름합치기 1.jpg, 2.jpg, 3.jpg,,,
				if(i==0) bfileName += uploadFileName;
				else bfileName +=","+uploadFileName;
				
				//컬럼 여러개일때 각각 dto에 파일이름 넣기
//				if(i==0) bdto.setBfile(uploadFileName);
//				else if(i==1) bdto.setBfile2(uploadFileName);
//				else if(i==2) bdto.setBfile3(uploadFileName); //각각 컬럼에 넣어줌
				
				i++;
			}//for
		}//if
		
		
		//파일첨부가 없으면 빈공백, 있으면 1.jpg, 2.jpg
		bdto.setBfile(bfileName);
		System.out.println("최종이름 : "+bfileName);
		
		//글쓰기 저장 service호출
		boardService.insert(bdto);
		
		model.addAttribute("notice_write","success");
		
		return "customer/result";
	}
	

}
