package com.librarySystem.library.command;

import java.time.LocalDateTime;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

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
public class RentalVO {
	
	private String rentalNumber;
	private String bookNumber;
	@NotEmpty(message = "책 일련번호는 필수 입력 값입니다")
	private String bookSerialNumber;
	@NotEmpty(message = "이름은 필수 입력 값입니다")
	private String name;
	@Pattern(regexp = "^[0-9]{6}$", message = "생년월일 6자리를 입력해주세요")
	private String birth;
	@Pattern(regexp = "^[0-9]{10,12}$", message = "연락처를 - 없이 10~12자리 사이로 입력해주세요")
	private String phoneNumber;
	private LocalDateTime rentalStart;
	private LocalDateTime rentalEnd;
	
}
