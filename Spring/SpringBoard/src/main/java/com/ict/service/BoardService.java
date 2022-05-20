package com.ict.service;

import java.util.List;

import com.ict.domain.BoardAttachVO;
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
	
	// 게시물에 연동된 첨부파일 목록 가져오기 
	public List<BoardAttachVO> getAttachList(Long bno);
	
}
