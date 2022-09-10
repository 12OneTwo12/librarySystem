package com.librarySystem.library.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.librarySystem.library.command.BookVO;
import com.librarySystem.library.command.RentalVO;
import com.librarySystem.library.command.UserRentalVO;
import com.librarySystem.library.library.service.LibraryService;
import com.librarySystem.library.library.service.ManagerService;
import com.librarySystem.library.utils.Criteria;
import com.librarySystem.library.utils.PageVO;

@Controller
@RequestMapping("/library")
public class LibraryController {
	
	@Autowired
	@Qualifier("libraryService")
	LibraryService libraryService;
	
	@Autowired
	@Qualifier("managerService")
	ManagerService managerService;

	@RequestMapping("/index")
	public void index() {
	}
	
	@RequestMapping("/rental")
	public void rental() {
	}
	
	@RequestMapping("/returnBook")
	public void returnBook() {
	}
	
	@RequestMapping("/viewList")
	public void viewList() {
	}
	
	@RequestMapping("/viewPopularity")
	public String viewPopularity(Criteria cri, Model model) {
		
		List<BookVO> list = libraryService.viewPopularity(cri);
		PageVO pageVO = new PageVO(cri, libraryService.getTotalPopularity(cri));
		
		model.addAttribute("list", list);
		model.addAttribute("pageVO", pageVO);
		return "library/viewPopularity";
	}
	
	@RequestMapping("/findBook")
	public String findBook(Criteria cri, Model model) {
		
		List<BookVO> list = managerService.getList(cri);
		
		PageVO pageVO = new PageVO(cri, managerService.getTotal(cri));
		
		model.addAttribute("list", list);
		model.addAttribute("pageVO", pageVO);
		
		return "library/findBook";
		
	}
	
	@RequestMapping("/myRentalRecord")
	public String myRentalRecord(RentalVO vo, Criteria cri, Model model) {
		
		List<UserRentalVO> list = libraryService.getMyRentalList(cri, vo);
		
		PageVO pageVO = new PageVO(cri, libraryService.getTotalMyRental(vo));
		
		model.addAttribute("list", list);
		model.addAttribute("pageVO", pageVO);
		model.addAttribute("rentalVO", vo);
		
		return "library/myRentalRecord";
		
	}
	
	@RequestMapping("/rentalBook")
	public String rentalBook(@Valid RentalVO rentalVO, Errors errors, RedirectAttributes RA, Model model) {
		
		if(errors.hasErrors()) {
			model.addAttribute("msg", errors.getAllErrors().get(0).getDefaultMessage());
			model.addAttribute("rentalVO", rentalVO);
			return "library/rental";
		} else {
			BookVO bookVO = libraryService.getBookVO(rentalVO.getBookSerialNumber());
			
			if(bookVO != null && bookVO.getBookStatus().equals("대여 가능")) {
				
				int result = libraryService.rentalBook(rentalVO,bookVO);
				
				if(result == 1) {
					RA.addFlashAttribute("msg", "대여 완료 됐습니다");
					return "redirect:/library/rental";
				} else {
					RA.addFlashAttribute("msg", "등록 과정 중 문제가 생겼습니다");
					return "redirect:/library/rental";
				}
				
			} else {
				model.addAttribute("msg", "책이 대여 가능 상태가 아닙니다 관리자에게 문의해주세요");
				model.addAttribute("rentalVO", rentalVO);
				return "library/rental";
			}
		}
		
		
	}
	
	@RequestMapping("/returnBookCheck")
	public String returnBookCheck(RentalVO rentalVO, RedirectAttributes RA, Model model) {
		
		if(rentalVO.getBookSerialNumber() == "" || rentalVO.getBookSerialNumber() == null) {
			model.addAttribute("msg", "책 일련 번호는 필수 입력 값입니다");
			model.addAttribute("rentalVO", rentalVO);
			return "library/returnBook";
		} else {
			BookVO bookVO = libraryService.getBookVO(rentalVO.getBookSerialNumber());
			if(bookVO == null) {
				model.addAttribute("msg", "책의 정확히 일련번호를 확인해주세요");
				model.addAttribute("rentalVO", rentalVO);
				return "library/returnBook";
			} else {
				if(bookVO.getBookStatus().equals("대여중")) {
					rentalVO = libraryService.getRentalData(bookVO);
					model.addAttribute("bookVO", bookVO);
					model.addAttribute("rentalVO", rentalVO);
					return "library/returnBook";
				} else {
					model.addAttribute("msg", "책이 대여중 상태가 아닙니다");
					model.addAttribute("rentalVO", rentalVO);
					return "library/returnBook";
				}
			}
		}
	}
	
	@RequestMapping("/realReturnBook")
	public String realReturnBook(RentalVO rentalVO, RedirectAttributes RA, Model model) {
		
		if(rentalVO.getBookSerialNumber() == "" || rentalVO.getBookSerialNumber() == null) {
			model.addAttribute("msg", "책 일련 번호는 필수 입력 값입니다");
			model.addAttribute("rentalVO", rentalVO);
			return "library/returnBook";
		} else {
			BookVO bookVO = libraryService.getBookVO(rentalVO.getBookSerialNumber());
			if(bookVO == null) {
				model.addAttribute("msg", "책의 정확히 일련번호를 확인해주세요");
				model.addAttribute("rentalVO", rentalVO);
				return "library/returnBook";
			} else {
				if(bookVO.getBookStatus().equals("대여중")) {
					int result = libraryService.realReturnBook(bookVO);
					
					if(result == 1) {
						RA.addFlashAttribute("msg", "반납 완료 됐습니다");
						return "redirect:/library/returnBook";
					} else {
						RA.addFlashAttribute("msg", "반납에 문제가 생겼습니다");
						return "redirect:/library/returnBook";
					}
				} else {
					model.addAttribute("msg", "책이 대여중 상태가 아닙니다");
					model.addAttribute("rentalVO", rentalVO);
					return "library/returnBook";
				}
			}
		}
		
	}
	
	@RequestMapping("/findBySerialNumber")
	public String findBySerialNumber(Criteria cri,Model model) {
		
		List<UserRentalVO> list = libraryService.getListBySerialNumber(cri);
		
		PageVO pageVO = new PageVO(cri, libraryService.getTotalBySerialNumber(cri));
		
		model.addAttribute("list", list);
		model.addAttribute("pageVO", pageVO);
		
		return "library/findBySerialNumber";
	}
	
}
