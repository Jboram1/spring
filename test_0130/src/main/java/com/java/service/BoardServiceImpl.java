package com.java.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.dto.BoardDto;
import com.java.mapper.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired BoardMapper boardMapper;
	
	@Override //게시글 전체가져오기
	public ArrayList<BoardDto> selectAll() {
		ArrayList<BoardDto> list = boardMapper.selectAll();
		return list;
	}

	@Override //게시글 1개 가져오기
	public Map<String, Object> selectOne(int bno) {
		//현재글
		BoardDto bdto = boardMapper.selectOne(bno);
		
		//이전글
		BoardDto prebdto = boardMapper.preSelectOne(bno);
		
		//다음글
		BoardDto nextbdto = boardMapper.nextSelectOne(bno);
		
		Map<String, Object> map = new HashMap<>();
		map.put("bdto", bdto);
		map.put("prebdto", prebdto);
		map.put("nextbdto", nextbdto);
		
		return map;
	}

	@Override //글쓰기 저장 service호출
	public void insert(BoardDto bdto) {
		boardMapper.insert(bdto);
		
	}

}
