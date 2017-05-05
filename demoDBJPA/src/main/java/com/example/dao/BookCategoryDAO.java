package com.example.dao;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.entity.BookCategory;

public interface BookCategoryDAO extends CrudRepository<BookCategory, Long>{
	//in @Query, the entity name (not table name) is case sensitive
	//@Query("select b from BookCategory b where b.id =1")
	//public BookCategory findOne(Long id);

}



