package com.librarySystem.library.library.service;

import java.util.List;

import javax.validation.Valid;

import com.librarySystem.library.command.BookVO;
import com.librarySystem.library.command.ManagerVO;
import com.librarySystem.library.command.RentalVO;
import com.librarySystem.library.command.UserRentalVO;
import com.librarySystem.library.utils.Criteria;

public interface ManagerService {

	ManagerVO managerLogin(ManagerVO managerVO);

	int changePw(String managerNewPw);

	int bookRegister(BookVO bookVO);

	int getCount();

	List<BookVO> getList(Criteria cri);

	int getTotal(Criteria cri);

	int deleteBook(String bookNumber);
	
}
