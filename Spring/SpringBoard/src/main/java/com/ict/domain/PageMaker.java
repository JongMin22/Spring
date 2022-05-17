package com.ict.domain;

import lombok.Data;

@Data
public class PageMaker {
	private int totalBoard;
	private int startPage;
	private int endPage;
	private boolean prev;
	private boolean next;
	// 만약 페이지 하단 버튼개수를 유동적으로 가져가고 싶은 경우 
	// displayPageNum을 선언합니다.
	private int displayPageNum;
	// Criteria에서 현재 조회하고 있는 페이지 정보 + 현재 페이지에 깔리는 글 수를 받아와야함.
	private SearchCriteria cri;
	// 필요한 모든 사항을 계산해주는 메서드.
	public void calcData() {
		this.displayPageNum =10;
		
		// 현재페이지(cri.PageNum())을 근거로 페이지 그룹중 끝나는 페이지를 구함 
		this.endPage = (int)(Math.ceil(cri.getPageNum() / 
				(double)displayPageNum) * displayPageNum);
		
		// 끝나는 페이지를 토대로 페이지 그룹의 시작페이지를 구함 .
		this.startPage = (endPage - displayPageNum) +1 ;
		
		// 위의 endPage는 명옥상의(단순 그룹계산으로)끝나는 페이지이기 떄문에 실질적인 글 개수를 통해 보장해줘야함.
		int tempEndPage = (int)(Math.ceil(totalBoard/(double)cri.getNumber()));
		
		if(endPage > tempEndPage) {
			endPage = tempEndPage;
		}
		
		// next,prev버튼 여부 처리
		// prev는 startPage가 1인 경우에만 비활성화 이므로 삼항연산자로 간단히 처리
		prev = startPage == 1 ? false : true; 
		
		//next는 여태까지 출력한 페이지에 속한 글 개수보다 DB내 전체 글이 더 많은경우에 활성화
		next = endPage * cri.getNumber() >= totalBoard ? false : true;
	}
	public void setTotalBoard(int totalBoard) {
		this.totalBoard = totalBoard;
		calcData(); // prev , next, endPage, startPage를 다 구해버림.
	}
	
}
