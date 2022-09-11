package com.librarySystem.library.library.service;

import java.util.List;

import com.librarySystem.library.command.BookVO;
import com.librarySystem.library.command.RentalVO;
import com.librarySystem.library.command.UserRentalVO;
import com.librarySystem.library.utils.Criteria;

public interface LibraryService {

	/**
	 * 책 일련번호로 책 정보(title, writer, category, status, bookSerialNumber) 가져오기 위한 메소드
	 * @param bookSerialNumber 책 일련번호
	 * @return BookVO타입의 책 정보
	 */
	BookVO getBookVO(String bookSerialNumber);

	/**
	 * 책 대여 기록 값 저장 기능
	 * @param rentalVO 책 대여 기록에 빌리는 사람 이름, 생년월일, 휴대폰 번호 값 입력 위해 사용
	 * @param bookVO 책 대여 기록에 책 번호, 일련번호 값 입력 위해 사용
	 * @return 성공 == 1, 실패 != 1
	 */
	int rentalBook(RentalVO rentalVO, BookVO bookVO);

	/**
	 * 책 일련번호로 대여중인 기록 값 가져오기 위한 메소드
	 * @param bookVO 책 일련번호 값 전달 위해 사용
	 * @return 책 대여기록 RentalVO
	 */
	RentalVO getRentalData(BookVO bookVO);

	/**
	 * 대여중인 책 일련번호로 반납 처리 하기위한 메소드
	 * @param bookVO 책 일련번호 값 전달 위해 사용
	 * @return 성공 == 1, 실패 != 1
	 */
	int realReturnBook(BookVO bookVO);

	/**
	 * 이름과 생년월일로 책 대여 기록 조회 하기 위한 메소드
	 * @param cri 페이징 위한 파라미터
	 * @param rentalVO 조회 하고자 하는 이름, 생년월일 값 입력 위한 파라미터
	 * @return rental_record 테이블과 books 테이블 join한 값 가져오기 위한 UserRentalVO타입의 List
	 */
	List<UserRentalVO> getMyRentalList(Criteria cri, RentalVO rentalVO);

	/**
	 * 이름과 생년월일로 책 대여 기록 조회한 값 페이징을 위한 총 조회 갯수값 가져오기 위해 사용
	 * @param rentalVO 조회 하고자 하는 이름, 생년월일 값 입력 위한 파라미터
	 * @return 이름과 생년월일로 책 대여 기록 조회한 총 결과값 갯수
	 */
	int getTotalMyRental(RentalVO rentalVO);

	/**
	 * 대여 기록 수를 기준으로 높은순으로 값 전달, 인기 순위를 위한 메소드 
	 * @param cri 페이징 위한 파라미터
	 * @return 인기 순위 목록 List
	 */
	List<BookVO> viewPopularity(Criteria cri);

	/**
	 * 인기 순위 페이징을 위한 총 조회 갯수값 가져오기 위해 사용
	 * @param cri 페이징 위한 파라미터
	 * @return 항목 총 갯수 int
	 */
	int getTotalPopularity(Criteria cri);

	/**
	 * 일련 번호로 대여 기록 및 책 정보 가져오기 위한 메소드
	 * @param cri 페이징 및 검색 값 입력 위한 파라미터
	 * @return rental_record 테이블과 books 테이블 join한 값 가져오기 위한 UserRentalVO타입의 List
	 */
	List<UserRentalVO> getListBySerialNumber(Criteria cri);

	/**
	 * 페이징을 위해 일련 번호로 대여 기록 및 책 정보 가져온 총 갯수값 구하기 위한 메소드
	 * @param cri 페이징 및 검색 값 입력 위한 파라미터
	 * @return 항목 총 갯수 int
	 */
	int getTotalBySerialNumber(Criteria cri);

}
