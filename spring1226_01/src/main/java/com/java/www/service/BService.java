package com.java.www.service;

import java.util.List;
import java.util.Map;

import com.java.www.dto.BcommentDto;
import com.java.www.dto.BoardDto;

public interface BService {

	//공지사항 전체 가져오기
	List<BoardDto> selectAll();

	//게시글 1개 가져오기
	Map<String, Object> selectOne(int bno);

	//db에 저장된 댓글 1개 가져오기
	BcommentDto BCommentInsert(BcommentDto cdto);

}
