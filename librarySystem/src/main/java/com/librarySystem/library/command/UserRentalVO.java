package com.librarySystem.library.command;

import java.time.LocalDateTime;

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
public class UserRentalVO {

	private String bookNumber;
	private String bookSerialNumber;
	private String bookTitle;
	private String bookWriter;
	private String bookStatus;
	private String name;
	private LocalDateTime rentalStart;
	private LocalDateTime rentalEnd;
	
}
