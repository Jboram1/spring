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

	//하단댓글 모두 가져오기
	List<BcommentDto> bCommentSelectAll(int bno);

	//댓글 1개 저장
	void bCommentInsert(BcommentDto cdto);

	//댓글1개 가져오기
	BcommentDto bCommentSelectOne(int cno);

	//댓글삭제
	int bCommentDelete(int cno);

	//댓글 수정 저장
	void BCommentUpdate(BcommentDto cdto);


}
