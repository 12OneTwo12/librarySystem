package com.librarySystem.library.library.service;

import java.util.List;

import com.librarySystem.library.command.BookVO;
import com.librarySystem.library.command.RentalVO;
import com.librarySystem.library.command.UserRentalVO;
import com.librarySystem.library.utils.Criteria;

public interface LibraryService {

	BookVO getBookVO(String bookSerialNumber);

	int rentalBook(RentalVO rentalVO, BookVO bookVO);

	RentalVO getRentalData(BookVO bookVO);

	int realReturnBook(BookVO bookVO);

	List<UserRentalVO> getMyRentalList(Criteria cri, RentalVO rentalVO);

	int getTotalMyRental(RentalVO rentalVO);

	List<BookVO> viewPopularity(Criteria cri);

	int getTotalPopularity(Criteria cri);

	List<UserRentalVO> getListBySerialNumber(Criteria cri);
	
	int getTotalBySerialNumber(Criteria cri);
	
}
