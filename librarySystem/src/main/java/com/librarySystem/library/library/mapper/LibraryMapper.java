package com.librarySystem.library.library.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.librarySystem.library.command.BookVO;
import com.librarySystem.library.command.RentalVO;
import com.librarySystem.library.command.UserRentalVO;
import com.librarySystem.library.utils.Criteria;

public interface LibraryMapper {

	BookVO getBookVO(String bookSerialNumber);

	int rentalBook(Map<String, Object> map);

	int updateStatus(@Param("bookNumber") String bookNumber,@Param("status") String status);

	RentalVO getRentalData(BookVO bookVO);

	int realReturnBook(BookVO bookVO);

	List<UserRentalVO> getMyRentalList(Map<String, Object> map);

	int getTotalMyRental(RentalVO rentalVO);

	List<BookVO> viewPopularity(Criteria cri);

	List<BookVO> getTotalPopularity(Criteria cri);

	List<UserRentalVO> getListBySerialNumber(Criteria cri);

	int getTotalBySerialNumber(Criteria cri);
}
