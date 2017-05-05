package com.example.dao;


import org.springframework.data.repository.CrudRepository;

import com.example.entity.Book;
import com.example.entity.BookCategory;

public interface BookDAO extends CrudRepository<Book, Long>{
	public Iterable<Book> findByBookCategory(BookCategory category);

}



