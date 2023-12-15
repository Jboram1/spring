package com.java.www.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.java.www.dto.MemberDto;


//@RestController //여기로 들어오는 모든url 데이터는 text(있는 그대)로 출력해달라
@Controller
public class DataController {
	
	@ResponseBody //데이터로(만) 넘김
	@RequestMapping("boardBno/{bno}/{bhit}") //boardBno뒤에는 bno를 넣겠다, bno 뒤에 조회수를 넣겠다
	public String boardBno(@PathVariable int bno, @PathVariable String bhit) {
		String txt = "boardBno 글번호"+bno;
		txt += ", 조회수 bhit :"+bhit;
		
		return txt;
	}
	
	@RequestMapping("mInsert")
	public String mInsert() {
		return "mInsert";
	}
	
	@ResponseBody
	@RequestMapping("idCheck")
	public Map<String, Object> idCheck(String id) {
		//db검색 select * from member where id=?
		//앞key, 뒤value
		Map<String, Object> map = new HashMap<>();
		ArrayList<MemberDto> list = new ArrayList<>();
		if(id.equals("aaa")) {
			map.put("result", "fail"); //불가능
		}else {
			map.put("result", "success"); //사용가능
		}
		
		list.add(new MemberDto("ccc","1111","이순신","010","male",
				"game,run",new Timestamp(System.currentTimeMillis())));
		list.add(new MemberDto("ddd","1111","강감찬","010","female",
				"golf",new Timestamp(System.currentTimeMillis())));
		map.put("name", "홍길동");
		map.put("phone", "010-1111-1111");
		map.put("bno", "1");
		map.put("mdto", new MemberDto("bbb","1111","유관순","010","female",
				"game,golf",new Timestamp(System.currentTimeMillis())));
		
		
//		JSONArray jArr = new JSONArray();
//		JSONObject jObj = new JSONObject();
		//json포맷으로 자동 변환 - Map,list
		return map;
	}//idCheck
	
	
	@RequestMapping("aaa")
	public String aaa() {
		return "aaa"; //aaa.jsp파일을 열어줘!
	}
	
	
	@ResponseBody //{}내용이 웹에 나옴 /데이터를 그대로 리턴/
	@RequestMapping("bbb")
	public String bbb() {
		String txt = "{'id':'aaa','pw':'1111','name':'홍길동'}";
		return txt; 
	}

}
