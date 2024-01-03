package com.java.www.service;

import java.util.List;

import com.java.www.dto.MemberDto;

public interface MService {

	//로그인 확인 
	String loginCheck(MemberDto mdto);

	//회원가입 저장
	List<MemberDto> mInsert(MemberDto mdto);


}
