package com.librarySystem.library.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.librarySystem.library.command.ManagerVO;

@Controller
@RequestMapping("/library")
public class LibraryController {

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
	
}
