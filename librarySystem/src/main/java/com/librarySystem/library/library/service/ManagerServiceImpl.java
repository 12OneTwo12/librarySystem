package com.librarySystem.library.library.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.librarySystem.library.command.BookVO;
import com.librarySystem.library.command.ManagerVO;
import com.librarySystem.library.command.UserRentalVO;
import com.librarySystem.library.library.mapper.ManagerMapper;
import com.librarySystem.library.utils.Criteria;

@Service("managerService")
public class ManagerServiceImpl implements ManagerService {

	@Autowired
	ManagerMapper managerMapper;

	@Override
	public ManagerVO managerLogin(ManagerVO managerVO) {
		return managerMapper.managerLogin(managerVO);
	}

	@Override
	public int changePw(String managerNewPw) {
		return managerMapper.changePw(managerNewPw);
	}

	@Override
	public int bookRegister(BookVO bookVO) {
		return managerMapper.bookRegister(bookVO);
	}

	@Override
	public int getCount() {
		return managerMapper.getCount();
	}

	@Override
	public List<BookVO> getList(Criteria cri) {
		return managerMapper.getList(cri);
	}

	@Override
	public int getTotal(Criteria cri) {
		return managerMapper.getTotal(cri);
	}

	@Override
	public int deleteBook(String bookNumber) {
		return managerMapper.deleteBook(bookNumber);
	}

	@Override
	public List<UserRentalVO> getReturnList(Criteria cri) {
		return managerMapper.getReturnList(cri);
	}

	@Override
	public int getTotalReturn() {
		return managerMapper.getTotalReturn().size();
	}

}
