package com.ict.persistence;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ict.domain.BoardVO;
import com.ict.domain.Criteria;
import com.ict.domain.SearchCriteria;
import com.ict.mapper.BoardMapper;

import lombok.extern.log4j.Log4j;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BoardMapperTests {
	
	@Autowired
	private BoardMapper boardMapper;
	
	
	   @Test
	public void testGetList() {
		
		 int pageNum =1;
	//	List<BoardVO> result = boardMapper.getList(cri);
    // log.info("����� �Խù� ���� : " + result);
	}
	
	// @Test
	public void testInsert() {
		
		BoardVO vo = new BoardVO();
		log.info("ä���ֱ��� :" + vo);
		vo.setTitle("���γִ±�");
		vo.setContent("���γִº���");
		vo.setWriter("���γִ±۾���");
		
		boardMapper.insert(vo);
	}
	// @Test
	public void testGetList2() {
		// �������� (�۹�ȣ�� �� ��°�� ū ��ȣ�� ���ּ���.)
		 BoardVO vo = boardMapper.select(21);
		
		
		log.info(vo);
		
	}
	// @Test
	public void testDeleteList() {
		 boardMapper.delete(21);
	}
	//  @Test
	 public void testUpdate() {
		 BoardVO vo = boardMapper.select(2);
		 vo.setTitle("�������");
		 vo.setContent("�������");
		 boardMapper.update(vo);
		 
	 }
	 // @Test
	  public void testUpdate2() {
		  boardMapper.update2("up2�� �ٲ�����", "up2�� �ٲۺ���",1);
		
	  }
	  
	  // ���� ������ ��� �Ǵ��� �����ϱ� ���� �׽�Ʈ�ڵ� 
	  @Test
	  public void testSearchGetList() {
		  SearchCriteria cri = new SearchCriteria();
		  cri.setKeyword("�׽�Ʈ");
		  cri.setSearchType("t");
		  
		  boardMapper.getList(cri);
	  }
}
