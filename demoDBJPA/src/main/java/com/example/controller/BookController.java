package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.dao.BookCategoryDAO;
import com.example.dao.BookDAO;
import com.example.entity.Book;
import com.example.entity.BookCategory;

@Controller
public class BookController {
	@Autowired
	BookDAO dao;

	@Autowired
	BookCategoryDAO categoryDao;

	@RequestMapping(value = "/bookCreate", method = RequestMethod.GET)
	public ModelAndView openFormCreate() {
		ModelAndView model = new ModelAndView("bookCreate");

		Iterable<BookCategory> categories = categoryDao.findAll();

		model.addObject("allBookCategories", categories);
		return model;
	}

	@RequestMapping(value = "/bookCreate", method = RequestMethod.POST)
	public ModelAndView processFormCreate(@ModelAttribute Book book) {
		// ModelAndView model = new ModelAndView("bookDone");
		ModelAndView model = new ModelAndView("redirect:/bookRetrieveAll");

		dao.save(book);

		model.addObject(book);
		return model;
	}

	@RequestMapping(value = { "/bookRetrieveAll", "/book" }, method = RequestMethod.GET)
	public ModelAndView retrieveBooks() {

		ModelAndView model = new ModelAndView("bookList");
		Iterable<BookCategory> categories = categoryDao.findAll();
		model.addObject("allBookCategories", categories);
		BookCategory category = categories.iterator().next();//get first category
		model.addObject("bookCategory", category);

		Iterable<Book> books = dao.findAll();
		model.addObject("allBooks", books);
		return model;
	}

	@RequestMapping(value = { "/bookRetrieveByCategory" }, method = RequestMethod.POST)
	public ModelAndView retrieveBooksByCategory(
			@RequestParam(value = "id", required = false, defaultValue = "1") Long id) {
	
/*
	public ModelAndView retrieveBooksByCategory(@ModelAttribute BookCategory category) {
*/
		ModelAndView model = new ModelAndView("bookList");
		// BookCategory category = new BookCategory();
		// category.setId(id);
		// Iterable<Book> books = dao.findByBookCategory(category);
		
		//System.out.println(category.getName());
		Iterable<BookCategory> categories = categoryDao.findAll();
		model.addObject("allBookCategories", categories);
		BookCategory category = categoryDao.findOne(id);
		model.addObject("bookCategory", category);
		
		model.addObject("allBooks", category.getBooks());
		// model.addObject("allBooks",books);
		// model.addObject(allBooks);
		return model;
	}

	@RequestMapping(value = "/bookUpdate", method = RequestMethod.GET)
	public ModelAndView openFormUpdate(
			@RequestParam(value = "id", required = false, defaultValue = "1") Long id) {
		ModelAndView model = new ModelAndView("bookUpdate");
		Book book = dao.findOne(id);
		model.addObject(book);
		Iterable<BookCategory> categories = categoryDao.findAll();

		model.addObject("allBookCategories", categories);

		return model;
	}

	@RequestMapping(value = "/bookUpdate", method = RequestMethod.POST)
	public ModelAndView processFormUpdate(@ModelAttribute Book book) {
		// ModelAndView model = new ModelAndView("bookDone");
		ModelAndView model = new ModelAndView("redirect:/bookRetrieveAll");
		dao.save(book);

		return model;
	}

	@RequestMapping(value = "/bookDelete", method = RequestMethod.GET)
	public ModelAndView deleteBook(
			@RequestParam(value = "id", required = false, defaultValue = "1") Long id) {
		ModelAndView model = new ModelAndView("redirect:/bookRetrieveAll");

		dao.delete(id);

		return model;
	}

}
