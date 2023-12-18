package com.java.www.service;

import java.util.ArrayList;

import com.java.www.dto.BoardDto;

public interface BService {

	//전체
	ArrayList<BoardDto> bList();
	//1개
	BoardDto selectOne(int bno);

}
