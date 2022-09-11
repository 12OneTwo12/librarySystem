package com.librarySystem.library.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.PageContext;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.aspose.barcode.generation.BarcodeGenerator;
import com.aspose.barcode.generation.EncodeTypes;
import com.librarySystem.library.command.BookVO;
import com.librarySystem.library.command.ManagerVO;
import com.librarySystem.library.command.UserRentalVO;
import com.librarySystem.library.library.service.ManagerService;
import com.librarySystem.library.utils.Criteria;
import com.librarySystem.library.utils.PageVO;

@Controller
@RequestMapping("/manager")
public class ManagerController {

	@Autowired
	@Qualifier("managerService")
	ManagerService managerService;

	/**
	 * 책 신규 등록 페이지
	 * @param session 로그인 세션 값 확인 위한 파라미터, 값 없을 시 메인 페이지로 redirect
	 * @param RA 페이지에 메세지 전달
	 * @return session 값 있으면 register 페이지 없을 시 index 페이지
	 */
	@RequestMapping("/register")
	public String register(HttpSession session, RedirectAttributes RA) {
		ManagerVO checkSession = (ManagerVO) session.getAttribute("sessionVO");

		if (checkSession == null) {
			RA.addFlashAttribute("msg", "관리자 로그인이 필요합니다");
			return "redirect:/library/index";
		} else {
			return "manager/register";
		}

	}

	/**
	 * 관리자 account 비밀번호 관리 페이지
	 * @param session 로그인 세션 값 확인 위한 파라미터, 값 없을 시 메인 페이지로 redirect
	 * @param RA 페이지에 메세지 전달
	 * @return mypage페이지
	 */
	@RequestMapping("/mypage")
	public String mypage(HttpSession session, RedirectAttributes RA) {

		ManagerVO checkSession = (ManagerVO) session.getAttribute("sessionVO");

		if (checkSession == null) {
			RA.addFlashAttribute("msg", "관리자 로그인이 필요합니다");
			return "redirect:/library/index";
		} else {
			return "manager/mypage";
		}
	}

	/**
	 * 책 목록 값 전달 및 출력. 삭제가 가능한 페이지
	 * @param session 로그인 세션 값 확인 위한 파라미터, 값 없을 시 메인 페이지로 redirect
	 * @param cri 페이징 파라미터
	 * @param model 페이지에 값 전달
	 * @param RA 페이지에 메세지 전달
	 * @return remove페이지
	 */
	@RequestMapping("/remove")
	public String remove(HttpSession session, Criteria cri, Model model, RedirectAttributes RA) {

		ManagerVO checkSession = (ManagerVO) session.getAttribute("sessionVO");

		if (checkSession == null) {
			RA.addFlashAttribute("msg", "관리자 로그인이 필요합니다");
			return "redirect:/library/index";
		} else {
			List<BookVO> list = managerService.getList(cri);

			PageVO pageVO = new PageVO(cri, managerService.getTotal(cri));

			model.addAttribute("list", list);
			model.addAttribute("pageVO", pageVO);

			return "manager/remove";
		}

	}

	/**
	 * 관리자 account 로그인
	 * @param managerVO 관리자 account 로그인 값 받기 위한 파라미터
	 * @param session 로그인 성공시 세션에 값 전달 위한 파라미터
	 * @param RA 페이지에 메세지 전달
	 * @return 성공시 mypage페이지 실패시 index페이지
	 */
	@RequestMapping(value = "/managerLogin", method = RequestMethod.POST)
	public String managerLogin(ManagerVO managerVO, HttpSession session, RedirectAttributes RA) {

		ManagerVO vo = managerService.managerLogin(managerVO);
		if (vo != null) {
			session.setMaxInactiveInterval(60000);
			session.setAttribute("sessionVO", vo);
			RA.addFlashAttribute("msg", "로그인 성공");
			return "redirect:/manager/mypage"; // 로그인 성공 후 페이지
		} else {
			RA.addFlashAttribute("msg", "아이디 혹은 비밀번호가 틀렸습니다");
			return "redirect:/library/index";
		}

	}

	/**
	 * 관리자 account 비밀번호 변경 위한 페이지
	 * @param managerVO 관리자 아이디, 비밀번호 값 전달 위한 파라미터
	 * @param errors 유효성 검사
	 * @param RA 페이지에 메세지 전달
	 * @param session 로그인 세션 값 확인 위한 파라미터, 값 없을 시 메인 페이지로 redirect
	 * @return 세션값 없거나 비밀번호 변경 성공시 index페이지, 실패시 mypage페이지
	 */
	@RequestMapping(value = "/changePw", method = RequestMethod.POST)
	public String changePw(@Valid ManagerVO managerVO, Errors errors, RedirectAttributes RA, HttpSession session) {

		ManagerVO checkSession = (ManagerVO) session.getAttribute("sessionVO");

		if (checkSession == null) {
			RA.addFlashAttribute("msg", "관리자 로그인이 필요합니다");
			return "redirect:/library/index";
		} else {
			if (errors.hasErrors()) {
				RA.addFlashAttribute("msg", "새 비밀번호는 영문, 숫자로 4자리 이상 15자리 미만으로 설정해주세요");
				return "redirect:/manager/mypage";
			} else {
				ManagerVO vo = managerService.managerLogin(managerVO);

				if (vo != null) {

					if (managerVO.getManagerNewPw().equals(managerVO.getManagerNewPwCheck())) {

						int result = managerService.changePw(managerVO.getManagerNewPw());

						if (result == 1) {
							RA.addFlashAttribute("msg", "비밀번호가 변경 되었습니다");
							return "redirect:/library/index"; // 로그인 성공 후 페이지
						} else {
							RA.addFlashAttribute("msg", "비밀번호 변경에 실패했습니다");
							return "redirect:/manager/mypage";
						}
					} else {
						RA.addFlashAttribute("msg", "새 비밀번호를 확인해주세요");
						return "redirect:/manager/mypage";
					}
				} else {
					RA.addFlashAttribute("msg", "비밀번호를 확인해주세요");
					return "redirect:/manager/mypage";
				}
			}
		}

	}

	/**
	 * 관리자 계정 로그아웃 기능
	 * @param session 로그인 세션 값 확인 위한 파라미터, 값 없을 시 메인 페이지로 redirect
	 * @param RA 페이지에 메세지 전달
	 * @return index페이지
	 */
	@RequestMapping("/logout")
	public String logout(HttpSession session, RedirectAttributes RA) {
		session.invalidate();
		RA.addFlashAttribute("msg", "로그아웃 완료 됐습니다");
		return "redirect:/library/index";
	}

	/**
	 * 책 신규 등록 페이지 및 기능
	 * @param bookVO 책 등록을 위해 title, writer, category 값 받기 위한 파라미터
	 * @param errors 유효성 검사
	 * @param RA 페이지에 메세지 전달
	 * @param model 페이지에 값 전달
	 * @param session 로그인 세션 값 확인 위한 파라미터, 값 없을 시 메인 페이지로 redirect
	 * @return register 페이지
	 */
	@RequestMapping("/bookRegister")
	public String bookRegister(@Valid BookVO bookVO, Errors errors, RedirectAttributes RA, Model model,
			HttpSession session) {

		ManagerVO checkSession = (ManagerVO) session.getAttribute("sessionVO");

		if (checkSession == null) {
			RA.addFlashAttribute("msg", "관리자 로그인이 필요합니다");
			return "redirect:/library/index";
		} else {
			if (errors.hasErrors()) {
				RA.addFlashAttribute("msg", "책 제목과 작가를 입력해주세요");
				model.addAttribute("bookVO", bookVO);
				return "manager/register";
			} else {

				String realSerialNumber = "";
				String count = Integer.toString(managerService.getCount() + 1);

				if (bookVO.getBookCategory().equals("Java")) {
					realSerialNumber += "1";
				} else if (bookVO.getBookCategory().equals("JS")) {
					realSerialNumber += "2";
				} else if (bookVO.getBookCategory().equals("Python")) {
					realSerialNumber += "3";
				} else if (bookVO.getBookCategory().equals("DB")) {
					realSerialNumber += "4";
				} else if (bookVO.getBookCategory().equals("Others")) {
					realSerialNumber += "5";
				}

				realSerialNumber += "0000000";
				realSerialNumber = realSerialNumber.substring(0, realSerialNumber.length() - count.length() - 1);
				realSerialNumber += count;

				bookVO.setBookSerialNumber(realSerialNumber);

				int result = managerService.bookRegister(bookVO);

				if (result == 1) {
					BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.CODE_128, realSerialNumber);
					generator.getParameters().setResolution(400);
					String addrs = "C:\\Users\\J\\Desktop\\project\\202209librarySystem\\librarySystem\\src\\main\\webapp\\resources\\barcodes\\";
					File file = new File(addrs + realSerialNumber + ".png");
					try {
						generator.save(addrs + realSerialNumber + ".png");
					} catch (IOException e) {
						e.printStackTrace();
					}
					model.addAttribute("msg", "등록이 완료 됐습니다");
					model.addAttribute("realSerialNumber", realSerialNumber);
					return "manager/register";
				} else {
					model.addAttribute("msg", "등록 과정에 문제가 있었습니다. 관리자에게 문의해주세요.");
					model.addAttribute("bookVO", bookVO);
					return "manager/register";
				}

			}

		}

	}

	/**
	 * 책 상태 삭제로 변경하기 위한 기능
	 * @param bookNumber 책 일련번호 값 받기 위한 파라미터
	 * @param RA 페이지에 메세지 전달
	 * @param session 로그인 세션 값 확인 위한 파라미터, 값 없을 시 메인 페이지로 redirect
	 * @return remove 페이지
	 */
	@RequestMapping("/deleteBook")
	public String deleteBook(@RequestParam("bookNumber") String bookNumber, RedirectAttributes RA,
			HttpSession session) {

		ManagerVO checkSession = (ManagerVO) session.getAttribute("sessionVO");

		if (checkSession == null) {
			RA.addFlashAttribute("msg", "관리자 로그인이 필요합니다");
			return "redirect:/library/index";
		} else {
			int result = managerService.deleteBook(bookNumber);

			if (result == 1) {
				RA.addFlashAttribute("msg", "삭제 완료 됐습니다");
				return "redirect:/manager/remove";
			} else {
				RA.addFlashAttribute("msg", "삭제 처리에 문제가 생겼습니다");
				return "redirect:/manager/remove";
			}
		}

	}

	/**
	 * 책 미반납 목록 보기 위한 페이지
	 * @param RA 페이지에 메세지 전달
	 * @param session 로그인 세션 값 확인 위한 파라미터, 값 없을 시 메인 페이지로 redirect
	 * @param cri 페이징 및 반납 필요한 책 List 값을 가져오기위한 파라미터
	 * @param model 페이지에 값 전달
	 * @return returnList 페이지
	 */
	@RequestMapping("/returnList")
	public String returnList(RedirectAttributes RA, HttpSession session, Criteria cri, Model model) {

		ManagerVO checkSession = (ManagerVO) session.getAttribute("sessionVO");

		if (checkSession == null) {
			RA.addFlashAttribute("msg", "관리자 로그인이 필요합니다");
			return "redirect:/library/index";
		} else {

			List<UserRentalVO> list = managerService.getReturnList(cri);

			PageVO pageVO = new PageVO(cri, managerService.getTotalReturn());

			model.addAttribute("list", list);
			model.addAttribute("pageVO", pageVO);

			return "manager/returnList";
		}
	}

}
