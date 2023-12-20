package com.java.www.service;

import java.util.ArrayList;
import java.util.Map;

import com.java.www.dto.BoardDto;

public interface BService {

	//전체
	ArrayList<BoardDto> bListAll();

	//1개
	Map<String, Object> bListOne(int bno);

	//쓰기저장
	void bInsert(BoardDto bdto);

	//삭제
	void bDelete(int bno);

	

	

}
