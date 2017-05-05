package com.example.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private Long inv;
	private Long Safeinv;
	private String name;
	private String press;
	private int price;
	private int Nop;
	private int Date;



	// JoinColumn refers to the column name in the table
	@ManyToOne
	@JoinColumn(name = "category")
	private BookCategory bookCategory;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BookCategory getBookCategory() {
		return bookCategory;
	}

	public void setBookCategory(BookCategory bookCategory) {
		this.bookCategory = bookCategory;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Long getInv() {
		return inv;
	}

	public void setInv(Long inv) {
		this.inv = inv;
	}
	
	public Long getSafeinv() {
		return Safeinv;
	}

	public void setSafeinv(Long Safeinv) {
		this.Safeinv = Safeinv;
	}
	
	public String getPress() {
		return press;
	}

	public void setPress(String press) {
		this.press = press;
	}
	
	public int getNop() {
		return Nop;
	}

	public void setNop(int nop) {
		this.Nop = nop;
	}
	
	public int getDate() {
		return Date;
	}

	public void setDate(int Date) {
		this.Date = Date;
	}
	
	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	

	
	

}
