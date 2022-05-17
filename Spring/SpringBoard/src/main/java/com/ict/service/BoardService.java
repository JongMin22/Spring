package com.ict.service;

import java.util.List;

import com.ict.domain.BoardVO;
import com.ict.domain.Criteria;
import com.ict.domain.SearchCriteria;

// 구현 클래스 BoardServiceImpl의 뼈대가 됩니다.
public interface BoardService {

		// 인터페이스 내에 먼저 메서드를 선언하고 , IMPL클래스에서 구현합니다.
	public List<BoardVO> getList(SearchCriteria cri);
	public int countPageNum(SearchCriteria cri);
	public BoardVO select(long bno);
	public void insert(BoardVO vo);
	public void delete(long bno);
	public void update(BoardVO vo);
}
