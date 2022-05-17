package com.ict.controller;



import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ict.domain.BoardVO;
import com.ict.domain.Criteria;
import com.ict.domain.PageMaker;
import com.ict.domain.SearchCriteria;
import com.ict.mapper.BoardMapper;
import com.ict.service.BoardService;

import lombok.extern.log4j.Log4j;

// 컨트롤러가 컨트롤러 기능을 할 수 있도록 처리해주세요.
@Controller

@Service
@Log4j
@RequestMapping("/board")
public class BoardController {

	// 컨트롤러는 Service만 호출하도록 구조를 바꿉니다.
	// Service를 BoardController내부에서 쓸 수 있도록 선언/주입해주세요.
	@Autowired
	private BoardService service;
	
	
	@GetMapping(value="/boardList")
	//@RequestParam(name="사용할변수명", defaultValue="지정하고싶은기본값")
	//@PathVariable의 경우 defaultValue를 직접줄순없으나 ,required = false를 이용해 필수입력을 안받게 처리한 후 
	// 컨트롤러 내부에서 디톨트값을 입력해줄수 있다.
	public String boardList(SearchCriteria cri,Model model) {
		// if(pageNum == null) {pageNum=1L;}
		List<BoardVO> boardList= service.getList(cri);
		log.info(boardList);
		model.addAttribute("boardList",boardList);
		
		// 버튼 처리를 위해 추가로 페이지메이커 생성 및 세팅 
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri); // cri입력
		// 131대사니 실제로 DB내 글 개수를 받아옴
		int countPage = service.countPageNum(cri);
		pageMaker.setTotalBoard(countPage); // calcData()호출도 되면서 순식간에 prev, next, startPage, endPage세팅
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
		service.insert(board);
		
		
		return "redirect:/board/boardList";
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
		log.info("전달 직전 : " + parameters);
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
		log.info("검색어 : "+cri.getKeyword());
		log.info("페이지번호 : " + cri.getPageNum());
		log.info("검색조건 : " + cri.getSearchType());
		service.update(board);
		rttr.addAttribute("pageNum",cri.getPageNum());
		rttr.addAttribute("searchType",cri.getSearchType());
		rttr.addAttribute("keyword",cri.getKeyword());
		
		return "redirect:/board/boardDetail/" + board.getBno();
	}
	
	
	
}
