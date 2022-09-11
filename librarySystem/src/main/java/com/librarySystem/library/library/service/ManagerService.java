package com.librarySystem.library.library.service;

import java.util.List;

import javax.validation.Valid;

import com.librarySystem.library.command.BookVO;
import com.librarySystem.library.command.ManagerVO;
import com.librarySystem.library.command.RentalVO;
import com.librarySystem.library.command.UserRentalVO;
import com.librarySystem.library.utils.Criteria;

public interface ManagerService {

	/**
	 * 관리자 로그인을 위한 메소드
	 * @param managerVO 관리자 계정 값 조회 및 비교 위한 파라미터
	 * @return 같은 값이 있으면 ManagerVO타입 관리자 계정 값 반환
	 */
	ManagerVO managerLogin(ManagerVO managerVO);

	/**
	 * 관리자 비밀번호 변경을 위한 메소드
	 * @param managerNewPw 관리자 신규 비밀번호
	 * @return 성공 == 1, 실패 != 1
	 */
	int changePw(String managerNewPw);

	/**
	 * 책 등록하기 메소드
	 * @param bookVO 책 정보 입력 위한 파라미터
	 * @return 성공 == 1, 실패 != 1
	 */
	int bookRegister(BookVO bookVO);

	/**
	 * 등록된 책 총 갯수를 구하기 위한 메소드
	 * @return 등록된 책 총 갯수 int
	 */
	int getCount();

	/**
	 * 등록된 책 목록 List를 가져오고 검색 값이 있을 경우 검색 값을 목록 List를 가져오는 메소드
	 * @param cri 페이징 및 검색 타입, 검색 값 입력 위한 파라미터
	 * @return 책 목록 List
	 */
	List<BookVO> getList(Criteria cri);

	/**
	 * 등록된 책 목록 List, 검색 값이 있을 경우 검색 값을 목록 List 목록의 총 갯수 값을 구하기 위한 메소드
	 * @param cri 페이징 및 검색 타입, 검색 값 입력 위한 파라미터
	 * @return 책 목록 List 총 갯수
	 */
	int getTotal(Criteria cri);

	/**
	 * 책 상태를 삭제 상태로 만들기 위한 메소드
	 * @param bookNumber 책 일련번호값 전달 위한 파라미터
	 * @return 성공 == 1, 실패 != 1
	 */
	int deleteBook(String bookNumber);

	/**
	 * 반납 상태가 아닌 책들 목록 List를 가져오기 위한 메소드
	 * @param cri 페이징 위한 파라미터
	 * @return 반납 상태가 아닌 책들 목록 List
	 */
	List<UserRentalVO> getReturnList(Criteria cri);

	/**
	 * 반납 상태가 아닌 책들 목록 List의 총 갯수를 구하기 위한 메소드
	 * @return 반납 상태가 아닌 책들 목록 List의 총 갯수
	 */
	int getTotalReturn();

}
