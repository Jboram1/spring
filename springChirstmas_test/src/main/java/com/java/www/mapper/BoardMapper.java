package com.java.www.mapper;

import java.util.ArrayList;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.java.www.dto.BoardDto;

@Mapper
public interface BoardMapper {

	//전체
	ArrayList<BoardDto> bListAll();

	//1개
	BoardDto bListOne(int bno);

	//이전글
	BoardDto bListOnePre(int bno);
	//다음글
	BoardDto bListOneNext(int bno);

	//쓰기 저장
	int bInsert(BoardDto bdto);

	//삭제
	int bDelete(int bno);

	



}
