package com.java.www.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.java.www.dto.BoardDto;

@Mapper
public interface BoardMapper {

	//전체
	ArrayList<BoardDto> bList();

	//1개
	BoardDto selectOne(int bno);

}
