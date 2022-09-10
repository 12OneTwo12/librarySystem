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
	
	@RequestMapping("/register")
	public String register(HttpSession session, RedirectAttributes RA) {
		ManagerVO checkSession = (ManagerVO)session.getAttribute("sessionVO");
		
		if(checkSession == null) {
			RA.addFlashAttribute("msg", "관리자 로그인이 필요합니다");
			return "redirect:/library/index";
		} else {
			return "manager/register";
		}
			
	} 
	
	@RequestMapping("/mypage")
	public String mypage(HttpSession session, RedirectAttributes RA) {
		
		ManagerVO checkSession = (ManagerVO)session.getAttribute("sessionVO");
		
		if(checkSession == null) {
			RA.addFlashAttribute("msg", "관리자 로그인이 필요합니다");
			return "redirect:/library/index";
		} else {
			return "manager/mypage";
		}
	}
	
	
	@RequestMapping("/remove")
	public String remove(HttpSession session, Criteria cri, Model model, RedirectAttributes RA) {
		
		ManagerVO checkSession = (ManagerVO)session.getAttribute("sessionVO");
		
		if(checkSession == null) {
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
	
	@RequestMapping(value = "/managerLogin", method = RequestMethod.POST)
	public String managerLogin(ManagerVO managerVO,HttpSession session, RedirectAttributes RA) {
		
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
	
	@RequestMapping(value = "/changePw", method = RequestMethod.POST)
	public String changePw(@Valid ManagerVO managerVO, Errors errors, RedirectAttributes RA, HttpSession session) {
		
		ManagerVO checkSession = (ManagerVO)session.getAttribute("sessionVO");
		
		if(checkSession == null) {
			RA.addFlashAttribute("msg", "관리자 로그인이 필요합니다");
			return "redirect:/library/index";
		} else {
			if(errors.hasErrors()) {
				RA.addFlashAttribute("msg", "새 비밀번호는 영문, 숫자로 4자리 이상 15자리 미만으로 설정해주세요");
				return "redirect:/manager/mypage";
			} else {
				ManagerVO vo = managerService.managerLogin(managerVO);
				
				if (vo != null) {
					
					if(managerVO.getManagerNewPw().equals(managerVO.getManagerNewPwCheck())) {
						
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
	
	@RequestMapping("/logout")
	public String logout(HttpSession session, RedirectAttributes RA) {
		session.invalidate();
		RA.addFlashAttribute("msg", "로그아웃 완료 됐습니다");
		return "redirect:/library/index";
	}
	
	@RequestMapping("/bookRegister")
	public String bookRegister(@Valid BookVO bookVO, Errors errors, RedirectAttributes RA, Model model,HttpSession session) {
		
		ManagerVO checkSession = (ManagerVO)session.getAttribute("sessionVO");
		
		if(checkSession == null) {
			RA.addFlashAttribute("msg", "관리자 로그인이 필요합니다");
			return "redirect:/library/index";
		} else {
			if(errors.hasErrors()) {
				RA.addFlashAttribute("msg", "책 제목과 작가를 입력해주세요");
				model.addAttribute("bookVO", bookVO);
				return "manager/register";
			} else {
				
				String realSerialNumber = "";
				String count = Integer.toString(managerService.getCount()+1);
				
				if(bookVO.getBookCategory().equals("Java")) {
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
				realSerialNumber = realSerialNumber.substring(0, realSerialNumber.length()-count.length()-1);
				realSerialNumber += count;
				
				bookVO.setBookSerialNumber(realSerialNumber);
				
				int result =  managerService.bookRegister(bookVO);
				
				if(result == 1) {
					BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.CODE_128, realSerialNumber);
					generator.getParameters().setResolution(400);
					String addrs = "C:\\Users\\J\\Desktop\\project\\202209librarySystem\\librarySystem\\src\\main\\webapp\\resources\\barcodes\\";
					File file = new File(addrs+realSerialNumber+".png");
					try {
						generator.save(addrs+realSerialNumber+".png");
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
	
	@RequestMapping("/deleteBook")
	public String deleteBook(@RequestParam("bookNumber") String bookNumber, RedirectAttributes RA,HttpSession session) {
		
		ManagerVO checkSession = (ManagerVO)session.getAttribute("sessionVO");
		
		if(checkSession == null) {
			RA.addFlashAttribute("msg", "관리자 로그인이 필요합니다");
			return "redirect:/library/index";
		} else {
			int result = managerService.deleteBook(bookNumber);
			
			if(result == 1) {
				RA.addFlashAttribute("msg", "삭제 완료 됐습니다");
				return "redirect:/manager/remove";
			} else {
				RA.addFlashAttribute("msg", "삭제 처리에 문제가 생겼습니다");
				return "redirect:/manager/remove";
			}
		}
		
		
	}
	
	@RequestMapping("/returnList")
	public String returnList(RedirectAttributes RA,HttpSession session,Criteria cri,Model model) {
		
		ManagerVO checkSession = (ManagerVO)session.getAttribute("sessionVO");
		
		if(checkSession == null) {
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
