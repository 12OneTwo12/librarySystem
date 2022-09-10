package com.librarySystem.library.library.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.librarySystem.library.command.BookVO;
import com.librarySystem.library.command.RentalVO;
import com.librarySystem.library.command.UserRentalVO;
import com.librarySystem.library.library.mapper.LibraryMapper;
import com.librarySystem.library.utils.Criteria;

@Service("libraryService")
public class LibraryServiceImpl implements LibraryService {

	@Autowired
	LibraryMapper libraryMapper;

	@Override
	public BookVO getBookVO(String bookSerialNumber) {
		return libraryMapper.getBookVO(bookSerialNumber);
	}

	@Override
	public int rentalBook(RentalVO rentalVO, BookVO bookVO) {
		
		int result = libraryMapper.updateStatus(bookVO.getBookNumber(),"대여중");
		
		if(result == 1) {
			Map<String, Object> map = new HashMap<>();
			map.put("rentalVO", rentalVO);
			map.put("bookVO", bookVO);
			
			return libraryMapper.rentalBook(map);
		} else {
			return 0;
		}
		
		
	}

	@Override
	public RentalVO getRentalData(BookVO bookVO) {
		return libraryMapper.getRentalData(bookVO);
	}

	@Override
	public int realReturnBook(BookVO bookVO) {
		
		int result = libraryMapper.updateStatus(bookVO.getBookNumber(),"대여 가능");
		
		if(result == 1) {
			return libraryMapper.realReturnBook(bookVO);
		} else {
			return 0;
		}
		
	}

	@Override
	public List<UserRentalVO> getMyRentalList(Criteria cri, RentalVO rentalVO) {
		
		Map<String, Object> map = new HashMap<>();
		map.put("cri", cri);
		map.put("rentalVO", rentalVO);
		
		return libraryMapper.getMyRentalList(map);
	}

	@Override
	public int getTotalMyRental(RentalVO rentalVO) {		
		return libraryMapper.getTotalMyRental(rentalVO);
	}

	@Override
	public List<BookVO> viewPopularity(Criteria cri) {
		return libraryMapper.viewPopularity(cri);
	}

	@Override
	public int getTotalPopularity(Criteria cri) {
		return libraryMapper.getTotalPopularity(cri).size();
	}

	@Override
	public List<UserRentalVO> getListBySerialNumber(Criteria cri) {
		return libraryMapper.getListBySerialNumber(cri);
	}

	@Override
	public int getTotalBySerialNumber(Criteria cri) {
		return libraryMapper.getTotalBySerialNumber(cri);
	}

}
