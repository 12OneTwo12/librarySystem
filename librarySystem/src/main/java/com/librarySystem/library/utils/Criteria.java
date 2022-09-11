package com.librarySystem.library.utils;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Criteria {

	private int pageNum;
	private int amount;

	private String searchType;
	private String searchKey = "";

	public Criteria() {
		this.pageNum = 1;
		this.amount = 10;
	}

	public Criteria(int pageNum, int amount) {
		this.pageNum = pageNum;
		this.amount = amount;
	}

	public int getPageStart() {
		return (pageNum - 1) * amount;
	}

}
