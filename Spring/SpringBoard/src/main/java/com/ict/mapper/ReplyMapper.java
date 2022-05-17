package com.ict.mapper;

import java.util.List;

import com.ict.domain.ReplyVO;

public interface ReplyMapper {
	
	// Ư�� �Խ��� bno�� ���� ��ü ��� ��� ��������
	// bno�� �ۿ� ���� ������ �־����.
	public List<ReplyVO> getList(Long bno);
	
	public void create(ReplyVO vo);
	
	public void update(ReplyVO vo);
	
	// ��� �����ô� ���� ��� �ϳ��� ����.
	public void delete(Long rno);
	
	public void deleteAll(Long bno);
	
	public Long getBno(Long rno);
}
