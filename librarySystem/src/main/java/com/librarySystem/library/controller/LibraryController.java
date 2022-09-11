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

	/**
	 * 책이 빌려진 횟수를 카운트 하여 값이 높은 순부터 값 전달 및 페이지에 출력, -인기 순위 조회 페이지
	 * @param cri 페이징 및 검색 타입과 검색 입력 값을 가져오기위한 파라미터
	 * @param model 페이지에 값 전달
	 * @return viewPopularity 페이지
	 */
	@RequestMapping("/viewPopularity")
	public String viewPopularity(Criteria cri, Model model) {

		List<BookVO> list = libraryService.viewPopularity(cri);
		PageVO pageVO = new PageVO(cri, libraryService.getTotalPopularity(cri));

		model.addAttribute("list", list);
		model.addAttribute("pageVO", pageVO);
		return "library/viewPopularity";
	}

	/**
	 * 검색 타입, 검색하고자 하는 값을 받아 DB에서 조회, findBook페이지에 출력
	 * @param cri 페이징 및 검색 타입과 검색 입력 값을 가져오기위한 파라미터
	 * @param model 페이지에 값 전달
	 * @return findBook페이지
	 */
	@RequestMapping("/findBook")
	public String findBook(Criteria cri, Model model) {

		List<BookVO> list = managerService.getList(cri);

		PageVO pageVO = new PageVO(cri, managerService.getTotal(cri));

		model.addAttribute("list", list);
		model.addAttribute("pageVO", pageVO);

		return "library/findBook";

	}

	/**
	 * 이름과 생년월일을 입력했을 시 DB에서 이름과 생년월일로 조회 및 값 전달
	 * @param vo 책이 빌려진 기록, 값들을 가져오기 위한 파라미터이자 조회 하고자 하는 이름과 생년월일을 받아오기 위한 파라미터
	 * @param cri 페이징 및 검색 타입과 검색 입력 값을 가져오기위한 파라미터
	 * @param model 페이지에 값 전달
	 * @return myRentalRecord페이지
	 */
	@RequestMapping("/myRentalRecord")
	public String myRentalRecord(RentalVO vo, Criteria cri, Model model) {

		List<UserRentalVO> list = libraryService.getMyRentalList(cri, vo);

		PageVO pageVO = new PageVO(cri, libraryService.getTotalMyRental(vo));

		model.addAttribute("list", list);
		model.addAttribute("pageVO", pageVO);
		model.addAttribute("rentalVO", vo);

		return "library/myRentalRecord";

	}

	/**
	 * 책 일련번호, 이름, 생년월일, 연락처 입력시 DB에 값 입력 및 책 대여중으로 상태 변경
	 * @param rentalVO 빌리고자 하는 책 일련번호와 빌리는 사람의 이름, 생년월일, 연락처 값을 가져오기 위한 파라미터 valid 사용으로 이름, 생년월일, 휴대폰 번호 값 필수
	 * @param errors 유효성 검사, 에러 관리
	 * @param RA 화면에 메시지 전달
	 * @param model 페이지에 값 전달
	 * @return 성공 실패 유무와 상관없이 rental
	 */
	@RequestMapping("/rentalBook")
	public String rentalBook(@Valid RentalVO rentalVO, Errors errors, RedirectAttributes RA, Model model) {

		if (errors.hasErrors()) {
			model.addAttribute("msg", errors.getAllErrors().get(0).getDefaultMessage());
			model.addAttribute("rentalVO", rentalVO);
			return "library/rental";
		} else {
			BookVO bookVO = libraryService.getBookVO(rentalVO.getBookSerialNumber());

			if (bookVO != null && bookVO.getBookStatus().equals("대여 가능")) {

				int result = libraryService.rentalBook(rentalVO, bookVO);

				if (result == 1) {
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

	/**
	 * 반납하고자하는 책 일련번호 입력시 화면에 페이지에 정보 출력 
	 * @param rentalVO 반납하고자하는 책 일련번호 값 받아오기 위해 사용
	 * @param RA 화면에 메시지 전달
	 * @param model 화면에 값 전달
	 * @return returnBook 페이지
	 */
	@RequestMapping("/returnBookCheck")
	public String returnBookCheck(RentalVO rentalVO, RedirectAttributes RA, Model model) {

		if (rentalVO.getBookSerialNumber() == "" || rentalVO.getBookSerialNumber() == null) {
			model.addAttribute("msg", "책 일련 번호는 필수 입력 값입니다");
			model.addAttribute("rentalVO", rentalVO);
			return "library/returnBook";
		} else {
			BookVO bookVO = libraryService.getBookVO(rentalVO.getBookSerialNumber());
			if (bookVO == null) {
				model.addAttribute("msg", "책의 정확히 일련번호를 확인해주세요");
				model.addAttribute("rentalVO", rentalVO);
				return "library/returnBook";
			} else {
				if (bookVO.getBookStatus().equals("대여중")) {
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

	/**
	 * 책 반납 기록 DB에 저장 및 책 상태 변경
	 * @param rentalVO 반납하고자 하는 책 데이터 전달을 위해 사용
	 * @param RA 화면에 메시지 전달
	 * @param model 화면에 값 전달
	 * @return returnBook 페이지
	 */
	@RequestMapping("/realReturnBook")
	public String realReturnBook(RentalVO rentalVO, RedirectAttributes RA, Model model) {

		if (rentalVO.getBookSerialNumber() == "" || rentalVO.getBookSerialNumber() == null) {
			model.addAttribute("msg", "책 일련 번호는 필수 입력 값입니다");
			model.addAttribute("rentalVO", rentalVO);
			return "library/returnBook";
		} else {
			BookVO bookVO = libraryService.getBookVO(rentalVO.getBookSerialNumber());
			if (bookVO == null) {
				model.addAttribute("msg", "책의 정확히 일련번호를 확인해주세요");
				model.addAttribute("rentalVO", rentalVO);
				return "library/returnBook";
			} else {
				if (bookVO.getBookStatus().equals("대여중")) {
					int result = libraryService.realReturnBook(bookVO);

					if (result == 1) {
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

	/**
	 * 책 일련번호로 대여 기록 조회
	 * @param cri 페이징 및 검색 입력 값을 가져오기위한 파라미터
	 * @param model 페이지에 값 전달
	 * @return findBySerialNumber 페이지
	 */
	@RequestMapping("/findBySerialNumber")
	public String findBySerialNumber(Criteria cri, Model model) {

		List<UserRentalVO> list = libraryService.getListBySerialNumber(cri);

		PageVO pageVO = new PageVO(cri, libraryService.getTotalBySerialNumber(cri));

		model.addAttribute("list", list);
		model.addAttribute("pageVO", pageVO);

		return "library/findBySerialNumber";
	}

}
