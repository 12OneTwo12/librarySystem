package com.librarySystem.library.utils;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class PageVO {

	// 화면에 페이지네이션을 그리는 클래스
	private int end; // 페이지네이션의 끝번호
	private int start; // 페이지네이션의 시작번호
	private boolean next; // 다음버튼 활성화 여부
	private boolean prev; // 이전버튼 황설화 여부
	
	private int realEnd;
	
	private int page; // 현재 조회하는 페이지
	private int amount; // 데이터 개수
	private int total; // 전체게시글 수

	private Criteria cri; // 페이지 기준 클래스
	
	// PageVO는 생성될때 반드시 cri를 받음
	public PageVO(Criteria cri, int total) {
		this.cri = cri;
		this.amount = cri.getAmount();
		this.page = cri.getPageNum();
		this.total = total;
		
		// 1. 끝 페이지 계산 
		this.end = (int)(Math.ceil(this.page/10.0)) * 10; 
		
		// 2. 시작페이지 계산
		// 공식 end - 페이지네이션 개수 + 1
		this.start = this.end - 10 + 1;
		
		// 3. 실제끝번호 계산
		// 데이터가 60개 있다고 가정할 때, 5번 페이지 조회시 end = 6
		// 데이터가 112개 있다고 가정할 때, 11번 페이지 조회시 end = 12
		realEnd = (int)(Math.ceil(this.total/(double)this.amount));
		
		// 4. 마지막 페이지의 결정
		// 데이터가 112개 있다고 가정, 5번 페이지 조회시 end = 10
		// 같은 상황일 때 11번 페이지 조회시에는 end = 11로 만들어야함
		this.end = this.end > this.realEnd ? this.realEnd : this.end;
		
		// 5. 이전버튼 활성화 여부
		this.prev = this.start > 1;
		
		// 6. 다음버튼 활성화 여부
		this.next = this.end < this.realEnd;
		
	}
	
}
