package com.ict.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ict.domain.BoardVO;
import com.ict.domain.Criteria;
import com.ict.domain.SearchCriteria;

public interface BoardMapper {
	
	// ��ư �߰��� ���ؼ� pageNum ��� Criteria�� Ȱ���մϴ�.
	public List<BoardVO> getList(SearchCriteria cri);
	
	
	public void insert(BoardVO vo);
	
	// select������ �� ��ȣ�� �Է¹޾Ƽ� �ش� �� "�ϳ�"�� ���� ������ ���� �����̹Ƿ� 
	// �����ڷ����� �� �ϳ��� ����Ҽ��ִ� BoardVO�� �ؾ� ��.
	public BoardVO select(long bno);
	
	
	// �� ������ DELETE �������� �ϴµ�, �� SELECT�����̹Ƿ� �����ڷḦ void�� �����ϴ�.
	// �ϳ��� �۸� ������ �����̹Ƿ�, ������ �� ������ ȣ��� ���� �Է��ϰ� �մϴ�.
	public void delete(long bno);
	
	public void update(BoardVO vo);

	public void update2(@Param("title") String title,@Param("content") String content,
			@Param("bno") long bno);
	// �� ���� => ���� ���� ��ȸ�ϱ� ������ int�����Դϴ�.
	public int countPageNum(SearchCriteria cri);
	
	public void updateReplyCount(@Param("bno") Long bno, @Param("amount") int amount);
	
}
