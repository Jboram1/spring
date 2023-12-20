package com.java.www.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.www.dto.BoardDto;
import com.java.www.mapper.BoardMapper;

@Service
public class BServiceImpl implements BService {

	@Autowired
	BoardMapper boardMapper;
	
	@Override
	public ArrayList<BoardDto> bListAll() {
		//전체
		ArrayList<BoardDto> list = boardMapper.bListAll();
		return list;
	}

	@Override
	public Map<String, Object> bListOne(int bno) {
		//1개
		BoardDto bdto = boardMapper.bListOne(bno);
		BoardDto prebdto = boardMapper.bListOnePre(bno);
		BoardDto nextbdto = boardMapper.bListOneNext(bno);
		
		Map<String, Object> map = new HashMap<>();
		map.put("bdto", bdto);
		map.put("prebdto", prebdto);
		map.put("nextbdto", nextbdto);
		return map;
	}

	@Override
	public void bInsert(BoardDto bdto) {
		//쓰기 저장
		int result = boardMapper.bInsert(bdto);
	}

	@Override
	public void bDelete(int bno) {
		int result = boardMapper.bDelete(bno);
		
	}



}
