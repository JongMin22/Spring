package com.ict.controller;



import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ict.domain.BoardAttachVO;
import com.ict.domain.BoardVO;
import com.ict.domain.Criteria;
import com.ict.domain.PageMaker;
import com.ict.domain.SearchCriteria;
import com.ict.mapper.BoardMapper;
import com.ict.service.BoardService;

import lombok.extern.log4j.Log4j;

// ��Ʈ�ѷ��� ��Ʈ�ѷ� ����� �� �� �ֵ��� ó�����ּ���.
@Controller

@Service
@Log4j
@RequestMapping("/board")
public class BoardController {

	// ��Ʈ�ѷ��� Service�� ȣ���ϵ��� ������ �ٲߴϴ�.
	// Service�� BoardController���ο��� �� �� �ֵ��� ����/�������ּ���.
	@Autowired
	private BoardService service;
	
	
	@GetMapping(value="/boardList")
	//@RequestParam(name="����Һ�����", defaultValue="�����ϰ�����⺻��")
	//@PathVariable�� ��� defaultValue�� �����ټ������� ,required = false�� �̿��� �ʼ��Է��� �ȹް� ó���� �� 
	// ��Ʈ�ѷ� ���ο��� ����Ʈ���� �Է����ټ� �ִ�.
	public String boardList(SearchCriteria cri,Model model) {
		// if(pageNum == null) {pageNum=1L;}
		List<BoardVO> boardList= service.getList(cri);
		log.info(boardList);
		model.addAttribute("boardList",boardList);
		
		// ��ư ó���� ���� �߰��� ����������Ŀ ���� �� ���� 
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri); // cri�Է�
		// 131���� ������ DB�� �� ������ �޾ƿ�
		int countPage = service.countPageNum(cri);
		pageMaker.setTotalBoard(countPage); // calcData()ȣ�⵵ �Ǹ鼭 ���İ��� prev, next, startPage, endPage����
		model.addAttribute("pageMaker", pageMaker);
	
		return "board/boardList";
	}
	@GetMapping(value="/boardDetail/{bno}")
	public String getboardDetail(@PathVariable long bno, Model model) {
		BoardVO board = service.select(bno);
		model.addAttribute("board",board);
				
	
		return "board/boardDetail";
	}
	
	@GetMapping(value="/boardInsert")
	public String BoardForm() {
		return "board/boardForm";
	}
	
	@PostMapping(value="/boardInsert")
	public String InsertBoard(BoardVO board,Model model) {
		// 첨부파일 디버깅
		log.info("=====================");
		if(board.getAttachList() != null) {
			board.getAttachList().forEach(attach -> log.info(attach));
			
		}
		
		service.insert(board);
		
		
		return "redirect:/board/boardList";
	}
	@GetMapping(value="/getAttachList", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ResponseEntity<List<BoardAttachVO>> getAttachList(Long bno){
		
		return new ResponseEntity<>(service.getAttachList(bno),HttpStatus.OK);
	} 
	
	@PostMapping(value="/boardDelete")
	public String DeleteBoard(long bno,SearchCriteria cri,Model model,RedirectAttributes rttr) {
		service.delete(bno);
		// rttr.addAttribute("pageNum",cri.getPageNum());
		// rttr.addAttribute("searchType",cri.getSearchType());
		// rttr.addAttribute("keyword",cri.getKeyword());
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("pageNum", cri.getPageNum());
		parameters.put("searchType", cri.getSearchType());
		parameters.put("keyword", cri.getKeyword());
		log.info("���� ���� : " + parameters);
		rttr.addAllAttributes(parameters);
		
		return "redirect:/board/boardList";
	}
	@PostMapping(value="/boardUpdateForm")
	public String BoardUpdateForm(long bno, Model model) {
		BoardVO board = service.select(bno);
		model.addAttribute("board",board);
		return "board/boardUpdateForm";
	}
	@GetMapping(value="/boardUpdate")
	public String BoardUpdate(BoardVO board,SearchCriteria cri, Model model, RedirectAttributes rttr) {
		log.info("�˻��� : "+cri.getKeyword());
		log.info("��������ȣ : " + cri.getPageNum());
		log.info("�˻����� : " + cri.getSearchType());
		service.update(board);
		rttr.addAttribute("pageNum",cri.getPageNum());
		rttr.addAttribute("searchType",cri.getSearchType());
		rttr.addAttribute("keyword",cri.getKeyword());
		
		return "redirect:/board/boardDetail/" + board.getBno();
	}
	
	
	
}
