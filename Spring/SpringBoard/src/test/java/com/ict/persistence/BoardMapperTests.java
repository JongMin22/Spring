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
    // log.info("저장된 게시물 정보 : " + result);
	}
	
	// @Test
	public void testInsert() {
		
		BoardVO vo = new BoardVO();
		log.info("채워넣기전 :" + vo);
		vo.setTitle("새로넣는글");
		vo.setContent("새로넣는본문");
		vo.setWriter("새로넣는글쓴이");
		
		boardMapper.insert(vo);
	}
	// @Test
	public void testGetList2() {
		// 가져오기 (글번호는 두 번째로 큰 번호로 해주세요.)
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
		 vo.setTitle("제목수정");
		 vo.setContent("내용수정");
		 boardMapper.update(vo);
		 
	 }
	 // @Test
	  public void testUpdate2() {
		  boardMapper.update2("up2로 바꾼제목", "up2로 바꾼본문",1);
		
	  }
	  
	  // 구문 생성이 어떻게 되는지 관측하기 위한 테스트코드 
	  @Test
	  public void testSearchGetList() {
		  SearchCriteria cri = new SearchCriteria();
		  cri.setKeyword("테스트");
		  cri.setSearchType("t");
		  
		  boardMapper.getList(cri);
	  }
}
