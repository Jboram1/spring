package com.java.www.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.www.dto.BoardDto;
import com.java.www.mapper.BoardMapper;

@Service
public class BServiceImpl implements BService {
	
	@Autowired
	BoardMapper boardMapper;
	
	
	@Override
	public Map<String, Object> selectSearch(int page, String category, String searchWord) {
		//게시글 검색
		//하단넘버링 - page, rowPerpage-1페이지당 게시글갯수, startPage,endPage,maxPage
		if(page<=0) page=1;
		int countPerPage = 10; //1페이지당 게시글갯수
		int bottomPerNum = 10; //하단넘버링 갯수
		int countAll = boardMapper.selectSearchCount(category,searchWord);  //게시글 검색 총개수
		System.out.println("BSIMPL CountAll : "+countAll);
		int maxPage = (int)Math.ceil((double)countAll/countPerPage);
					// 45개 = 45/10 = 4.5 ->5
		int startPage = ((page-1)/bottomPerNum)*bottomPerNum+1;   //5-1=4/10 ->0*10+1
		int endPage = (startPage+bottomPerNum)-1;
		
		int startRow = (page-1)*countPerPage+1; //1,11,21,31,41....
		int endRow = startRow+countPerPage-1;    //10,20,30,40.....
		
		// startPage:1 -endPage:10일때 maxPage가 5이면
		// endPage에 maxPage를 넣어서 1-10나오는 것이 아니라 1~5까지만 나타나도록 함.
		if(endPage>maxPage) endPage = maxPage;
		ArrayList<BoardDto> list = boardMapper.selectSearch(startRow,endRow,category,searchWord);
		
		//데이터전송 - list,page,maxPage,startPage,endPage
		Map<String, Object> map = new HashMap<>();
		map.put("list", list);
		map.put("countAll", countAll);
		map.put("page", page);
		map.put("maxPage", maxPage);
		map.put("startPage", startPage);
		map.put("endPage", endPage);
		
		return map;
	}
	
	@Override
	public Map<String, Object> selectAll(int page,String category, String searchWord) {
		//게시글 전체 가져오기
		//하단넘버링 - page, rowPerpage-1페이지당 게시글갯수, startPage,endPage,maxPage
		if(page<=0) page=1;
		int countPerPage = 10; //1페이지당 게시글갯수
		int bottomPerNum = 10; //하단넘버링 갯수
		int countAll = boardMapper.selectCountAll(category,searchWord);  //게시글 총개수
		int maxPage = (int)Math.ceil((double)countAll/countPerPage);
					// 45개 = 45/10 = 4.5 ->5
		int startPage = ((page-1)/bottomPerNum)*bottomPerNum+1;   //5-1=4/10 ->0*10+1
		int endPage = (startPage+bottomPerNum)-1;
		
		int startRow = (page-1)*countPerPage+1; //1,11,21,31,41....
		int endRow = startRow+countPerPage-1;    //10,20,30,40.....
		
		// startPage:1 -endPage:10일때 maxPage가 5이면
		// endPage에 maxPage를 넣어서 1-10나오는 것이 아니라 1~5까지만 나타나도록 함.
		if(endPage>maxPage) endPage = maxPage;
		ArrayList<BoardDto> list = boardMapper.selectAll(startRow,endRow,category,searchWord);
		
		//데이터전송 - list,page,maxPage,startPage,endPage
		Map<String, Object> map = new HashMap<>();
		map.put("list", list);
		map.put("page", page);
		map.put("countAll", countAll);
		map.put("maxPage", maxPage);
		map.put("startPage", startPage);
		map.put("endPage", endPage);
		
		return map;
	}//selectAll

	
	@Override
	public Map<String , Object> selectOne(int bno) {
		//게시글 1개 가져오기,  이전글, 다음글 가져오기: 총3개
		BoardDto bdto = boardMapper.selectOne(bno);
		BoardDto prevdto = boardMapper.selectOnePrev(bno);
		BoardDto nextdto = boardMapper.selectOneNext(bno);
		
		//조회수 1증가
		boardMapper.bhitUp(bno);
		
		//List<BoardDto> list = new ArrayList<>();
		Map<String, Object> map = new HashMap<>();
		map.put("bdto", bdto);
		map.put("prevdto", prevdto);
		map.put("nextdto", nextdto);
		
		return map;
	}//selectOne

	@Override
	public void bInsert(BoardDto bdto) {
		//글쓰기 저장
		int result = boardMapper.bInsert(bdto);
		System.out.println("bServiceImpl bInsert result : "+result);
	}//bInsert

	
	@Override
	public void bDelete(int bno) {
		//게시글 삭제
		int result = boardMapper.bDelete(bno);
		System.out.println("bServiceImpl bDelete result : "+result);
	}//bDelete

	@Override
	public void doBUpdate(BoardDto bdto) {
		//게시글 수정 저장
		int result = boardMapper.doBUpdate(bdto);
		System.out.println("bServiceImpl doBUpdate result : "+result);
		
	}//doBUpdate

	@Override
	public void doBReply(BoardDto bdto) {
		// 답변달기 저장 - bgroup, bstep, bindent
		//1. 부모보다 큰 bstep은 1씩 증가시킴
		//2. 현재글은 부모bstep+1 저장
		//3. bindent는 부모의 1 더하기
		//4. bgroup은 부모와 같음.
		
		boardMapper.bstepUp(bdto);
		//bdto.setBstep(bdto.getBstep()+1); //여기서 +1해도 되고 쿼리에서 +1해도 됨
		//bdto.setBindent(bdto.getBindent()+1); //쿼리문에 +1했음
		
		int result = boardMapper.doBReply(bdto);
		System.out.println("bServiceImpl doBReply result : "+result);
		
	}//doBReply

	


}//class
