package com.java.www.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.java.www.dto.BoardDto;

@Mapper
public interface BoardMapper {

	//전체 게시글 가져오기
	List<BoardDto> blist();

	//게시글저장
	void write(BoardDto bdto);

	
	//게시글1개 가져오기
	BoardDto selectOne(int bno);

}
