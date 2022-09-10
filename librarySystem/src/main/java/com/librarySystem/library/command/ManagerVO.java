package com.librarySystem.library.command;

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
public class ManagerVO {

	private String managerId;
	private String managerPw;
	@Pattern(regexp = "^[a-zA-Z0-9]{4,15}$")
	private String managerNewPw;
	private String managerNewPwCheck;
}
