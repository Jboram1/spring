package com.java.www.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.java.www.dto.MemberDto;

@Mapper
public interface MemberMapper {

	//로그인 확인
	MemberDto loginCheck(MemberDto mdto);

	//회원가입
	List<MemberDto> mInsert(MemberDto mdto);

}
