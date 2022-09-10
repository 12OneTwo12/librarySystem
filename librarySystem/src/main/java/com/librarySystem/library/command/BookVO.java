package com.librarySystem.library.command;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BookVO {

	private String bookNumber;
	private String bookSerialNumber;
	@NotNull
	private String bookTitle;
	@NotNull
	private String bookWriter;
	private String bookStatus;
	private String bookCategory;
	private String count;
	
}
