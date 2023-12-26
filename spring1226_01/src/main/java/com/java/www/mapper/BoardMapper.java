package com.java.www.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.java.www.dto.BcommentDto;
import com.java.www.dto.BoardDto;

@Mapper
public interface BoardMapper {

	////공지사항 전체 가져오기
	List<BoardDto> selectAll();

	//게시글 1개 가져오기
	BoardDto selectOne(int bno);

	////댓글 전체가져오기
	List<BcommentDto> BCommentSelectAll(int bno);

	//db에 저장된 댓글 1개 가져오기
	void BCommentInsert(BcommentDto cdto);

	//저장된 댓글 1개 가져오기
	BcommentDto BCommentSelectOne(int cno);

}
