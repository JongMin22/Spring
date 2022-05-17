package com.ict.service;

import java.util.List;

import com.ict.domain.BoardVO;
import com.ict.domain.Criteria;
import com.ict.domain.SearchCriteria;

// ���� Ŭ���� BoardServiceImpl�� ���밡 �˴ϴ�.
public interface BoardService {

		// �������̽� ���� ���� �޼��带 �����ϰ� , IMPLŬ�������� �����մϴ�.
	public List<BoardVO> getList(SearchCriteria cri);
	public int countPageNum(SearchCriteria cri);
	public BoardVO select(long bno);
	public void insert(BoardVO vo);
	public void delete(long bno);
	public void update(BoardVO vo);
}
