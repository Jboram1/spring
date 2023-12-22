package com.java.www.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ser.std.StdKeySerializers.Default;
import com.java.www.dto.BoardDto;
import com.java.www.service.BService;

@Controller
@RequestMapping("board")
public class BoardController {
	@Autowired
	BService bService;
	
	
	@GetMapping("search") //게시글 검색
	public String search(@RequestParam(defaultValue = "1")int page, 
			@RequestParam(required = false)String category,
			@RequestParam(required = false)String searchWord, Model model) {
		
		System.out.println("BC search category:"+category);
		System.out.println("BC search searchWord:"+searchWord);
		
		
		//db에서 가져오기
		Map<String, Object> map = bService.selectSearch(page,category,searchWord);
		//model 저장
		model.addAttribute("map",map);
		System.out.println("게시글 총개수 : "+(int)map.get("countAll"));
		return "board/bList";
	}//bList
	
	@GetMapping("bList") //게시글 전체 가져오기
	public String bList(@RequestParam(defaultValue = "1")int page, 
			@RequestParam(required = false)String category,
			@RequestParam(required = false)String searchWord, Model model) {
		//db에서 가져오기
		Map<String, Object> map = bService.selectAll(page,category,searchWord);
		//model 저장
		model.addAttribute("map",map);
		System.out.println("게시글 총개수 : "+(int)map.get("countAll"));
		return "board/bList";
	}//bList
	
	
	
	@GetMapping("bView") //게시글 1개 가져오기
	public String bView(@RequestParam(defaultValue = "1") int bno, Model model) {
		System.out.println("BController bno: "+bno);
		//게시글 1개 가져오기
		Map<String , Object> map = bService.selectOne(bno);
		//model저장
		model.addAttribute("map",map);
		return "board/bView";
	}//bView

	
	@GetMapping("bInsert") //글쓰기 화면보기
	public String bInsert() {
		return "board/bInsert";
	}//
	
	@PostMapping("bInsert") //글쓰기 저장 //오버로딩
	public String bInsert(BoardDto bdto, @RequestPart MultipartFile files, 
			Model model) throws Exception{
		//MultipartFile files - input type="file" name="files" 여야한다. name을 가져옴.
		//파일첨부의 파일이름
		if(!files.isEmpty()) { //파일첨부가 있으면
			String orgName = files.getOriginalFilename();
			long time = System.currentTimeMillis();
			String newName = time+"_"+orgName; //중복방지를 위한 새로운 이름변경
			String upload = "c:/upload/";      //파일업로드 위치
			File f = new File(upload+newName);
			files.transferTo(f);    //파일을 저장위치에 저장시킴
			bdto.setBfile(newName); //파일이름을 BoardDto에 저장시킴
		}else { //파일첨부가 없으면
			bdto.setBfile("");
		}
		
		//db로 전송
		bService.bInsert(bdto);
		
		return "board/doBInsert";
	}//
	
	
	@PostMapping("bDelete") //게시글삭제
	public String bDelete(@RequestParam(defaultValue = "1") int bno, Model model) {
		System.out.println("BC bno : "+bno);
		bService.bDelete(bno);
		return "board/bDelete";
	}
	
	
	
	@PostMapping("bUpdate") //게시글 수정페이지 보기
	public String bUpdate(@RequestParam(defaultValue = "1") int bno, Model model) {
		System.out.println("BC bno : "+bno);
		Map<String, Object> map = bService.selectOne(bno); //게시글 1개 가져오기
		model.addAttribute("map",map);
		return "board/bUpdate";
	}
	
	@PostMapping("doBUpdate") //게시글 수정 저장
	public String doBUpdate(BoardDto bdto, @RequestPart MultipartFile files) throws Exception {
		//bdto -> bfile
		//lombok확인방법 bdto.get이 나타나야함. 없으면 cmd에 java -jar lombok.jar쳐보기
		System.out.println("bC doBUpdate bno : "+bdto.getBno()); 
		String orgName = "";
		String newName = "";
		if(!files.isEmpty()) { //파일업로드가 되어 있으면 새로운 이름부여
			orgName = files.getOriginalFilename();
			long time = System.currentTimeMillis();
			//newName = String.format("%s_%d",orgName,time); //"%s_%d" 특수문자가 많을 경우 이렇게 쓰는게 좋다
			newName = time+"_"+orgName;
			String upload = "c:/upload/";      //파일저장위치
			File f = new File(upload+newName); //파일생성
			files.transferTo(f);               //파일전송
			bdto.setBfile(newName);            //bdto파일이름 저장 , 파일1개만 저장시 기존에 있는 파일에 덮어쓰기 된다.
		}
		//db전송
		bService.doBUpdate(bdto); //파일업로드가 없으면 기존파일이름 그대로 사용
		
		return "board/doBUpdate";
	}
	
	
	@PostMapping("bReply") //답변달기페이지 보기
	public String bReply(@RequestParam(defaultValue = "1") int bno, Model model) {
		System.out.println("BC bReply bno : "+bno);
		Map<String, Object> map = bService.selectOne(bno); //게시글 1개 가져오기
		model.addAttribute("map",map);
		return "board/bReply";
	}
	
	
	
	@PostMapping("doBReply") //답변달기 저장
	public String doBReply(BoardDto bdto, @RequestPart MultipartFile files, 
			Model model) throws Exception{
		//답변달기 - bgroup,bstep,bindent 값은 bdto에 담져겨 있음.
		//파일첨부의 파일이름
		if(!files.isEmpty()) { //파일첨부가 있으면
			String orgName = files.getOriginalFilename();
			System.out.println("BC doBReply orgName : "+orgName);
			long time = System.currentTimeMillis();
			String newName = time+"_"+orgName; //중복방지를 위한 새로운 이름변경
			String upload = "c:/upload/";      //파일업로드 위치
			File f = new File(upload+newName);
			files.transferTo(f);    //파일을 저장위치에 저장시킴
			bdto.setBfile(newName); //파일이름을 BoardDto에 저장시킴
		}else { //파일첨부가 없으면
			bdto.setBfile("");
			System.out.println("bC doBReply 파일첨부가 없습니다");
		}
		
		//db로 전송
		bService.doBReply(bdto);
		
		return "board/doBReply";
	}//doBReply
	
	
	
	//------------------------------------------
	//----------- 다중업로드리스트 ------------------
	//------------------------------------------
	@GetMapping("bList2") //게시글 전체 가져오기
	public String bList2(@RequestParam(defaultValue = "1")int page, 
			@RequestParam(required = false)String category,
			@RequestParam(required = false)String searchWord, Model model) {
		//db에서 가져오기
				Map<String, Object> map = bService.selectAll(page,category,searchWord);
				//model 저장
				model.addAttribute("map",map);
				System.out.println("게시글 총개수 : "+(int)map.get("countAll"));
		return "board/bList2";
	}//bList
	
	@GetMapping("bView2") //게시글 1개 가져오기
	public String bView2(@RequestParam(defaultValue = "1") int bno, Model model) {
		System.out.println("BController bno: "+bno);
		//게시글 1개 가져오기
		Map<String , Object> map = bService.selectOne(bno);
		//model저장
		model.addAttribute("map",map);
		return "board/bView2";
	}//bView
	
	
	@GetMapping("bInsert2") //다중업로드 화면보기
	public String bInsert2() {
		return "board/bInsert2";
	}
	
	@PostMapping("bInsert2") //다중업로드 저장
	public String bInsert2(BoardDto bdto,List<MultipartFile> files, Model model) throws Exception {
		//MultipartFile files - input type="file" name="files" 여야한다. name을 가져옴.
		//복수개 일때는 List<MultipartFile> files로 받음.
		String orgName="";
		String newName="";
		String mergName = "";
		int i = 0;
		for(MultipartFile file:files) {
			//파일첨부하기
			orgName = file.getOriginalFilename();
			System.out.println("BC 파일첨부 이름 : "+orgName);
			long time = System.currentTimeMillis();
			newName = time+"_"+orgName; //중복방지를 위한 새로운 이름변경
			String upload = "c:/upload/";      //파일업로드 위치
			File f = new File(upload+newName);
			file.transferTo(f);    //파일을 저장위치에 저장시킴
			
			//파일이름을 저장하기
			if(i==0) mergName += time+"_"+orgName; //중복방지를 위한 새로운 이름변경 1.jpg, 2.jpg,
			else mergName += ","+time+"_"+orgName;
			i++;
		}
		bdto.setBfile(mergName); //파일이름을 BoardDto에 저장시킴
		System.out.println("BC 최종 파일첨부 이름 : "+mergName);
		//db연결 - 내용저장
		bService.bInsert(bdto);
		
//		for(int i=0; i<files.size();i++) { //위 for문과 같다
//			
//		}
		
		return "board/bInsert2";
	}//bInsert2
	
	
	
	
	
	
}//class
