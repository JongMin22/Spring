package com.ict.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ict.domain.ReplyVO;
import com.ict.mapper.BoardMapper;
import com.ict.mapper.ReplyMapper;

@Service
public class ReplyServiceImpl implements ReplyService{
	
	@Autowired
	private ReplyMapper mapper;
	
	// 댓글 쓰기시 board_tbl에도 관여해야 하므로 board테이블을 추가하는 Mapper를 추가선언합니다.
	@Autowired
	private BoardMapper boardmapper;


	@Transactional
	@Override
	public void addReply(ReplyVO vo) {
		
		mapper.create(vo);
		
		boardmapper.updateReplyCount(vo.getBno(), 1);
		
		
	}

	@Override
	public List<ReplyVO> listReply(Long bno) {
		
			return mapper.getList(bno);
	}

	@Override
	public void modifyReply(ReplyVO vo) {
			mapper.update(vo);
	}
	

	@Transactional
	@Override
	public void removeReply(Long rno) {
			Long bno = mapper.getBno(rno);	
			
			mapper.delete(rno);
			
			boardmapper.updateReplyCount(bno, -1);
			
	}
	
	
	
	
	
	
}
