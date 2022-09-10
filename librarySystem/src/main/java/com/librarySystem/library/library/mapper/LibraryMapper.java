package com.librarySystem.library.library.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.librarySystem.library.command.BookVO;
import com.librarySystem.library.command.RentalVO;
import com.librarySystem.library.command.UserRentalVO;

public interface LibraryMapper {

	BookVO getBookVO(String bookSerialNumber);

	int rentalBook(Map<String, Object> map);

	int updateStatus(@Param("bookNumber") String bookNumber,@Param("status") String status);

	RentalVO getRentalData(BookVO bookVO);

	int realReturnBook(BookVO bookVO);

	List<UserRentalVO> getMyRentalList(Map<String, Object> map);

	int getTotalMyRental(RentalVO rentalVO);

}
