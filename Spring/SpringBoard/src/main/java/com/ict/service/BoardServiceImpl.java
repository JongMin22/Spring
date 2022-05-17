package com.ict.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ict.domain.BoardVO;
import com.ict.domain.Criteria;
import com.ict.domain.SearchCriteria;
import com.ict.mapper.BoardMapper;
import com.ict.mapper.ReplyMapper;

// BoardService �������̽� ����

@Service // �� �����̳ʿ� ���(root-context.xml���� ������Ʈ ��ĵ���� �Ϸ��ؾ� ��ϵ�)

public class BoardServiceImpl implements BoardService{
	// ���񽺰� DAO(Mapper.java)�� ȣ���Ѵٸ� ������ �ϰ� ������������ �ؾ��մϴ�.
	// �ش� �ڵ带 �ۼ��ϼ���.
	@Autowired
	private ReplyMapper mapper;
	@Autowired
	private BoardMapper boardMapper;
	
	// �����ڷ����� INSERT , DELETE , UPDATE ������ ����� �ൿ �������� �޼��带 �����ϴ�.
	// �����ڷ����� �ִ� SELECT ������ �ϳ��� �޼��尡 �ϳ��� �������� ����մϴ�.
	@Override
	public List<BoardVO> getList(SearchCriteria cri){
			return boardMapper.getList(cri);
	}
	@Override
	public int countPageNum(SearchCriteria cri) {
		return boardMapper.countPageNum(cri);
	}
	@Override
	public BoardVO select(long bno) {
		return boardMapper.select(bno);
	}
	@Override
	public void insert(BoardVO vo) {
		 boardMapper.insert(vo);
	}
	@Transactional
	public void delete(long bno) {
		mapper.deleteAll(bno);
		boardMapper.delete(bno);
		
	}
	public void update(BoardVO vo) {
		boardMapper.update(vo);
	}
	
}
