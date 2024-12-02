package com.springboot.web.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.springboot.web.Dao.BookRepository;
import com.springboot.web.entities.Book;

@Component
public class BookService {

	@Autowired
	private BookRepository bookRepository;

//	private static List<Book> lists = new ArrayList<>();
//
//	static {
//		lists.add(new Book(1, "good habits", "Mohammed junaid"));
//		lists.add(new Book(2, "good books", "Mohammed anas"));
//		lists.add(new Book(3, "good teachers", "Mohammed mujahid"));
//		lists.add(new Book(4, "good parents", "Mohammed azhar"));
//		lists.add(new Book(5, "good language", "Mohammed mukram"));
//	}

	// getting all the books
	public List<Book> getAllBooks() {
//		return lists;
		Iterable<Book> all = this.bookRepository.findAll();
		List<Book> list = (List<Book>) all;
		return list;

	}

	// gettig books by id

	/*
	 * public Book getBookById(int id) {
	 * 
	 * Book findById = null;
	 * 
	 * for (Book b : lists) { if (b.getId() == id) { findById = b; } } return
	 * findById;
	 * 
	 * }
	 */

	// getting the element by id
	public Book getBookById(int id) {

		/*
		 * Optional<Book> first = lists.stream().filter(e -> e.getId() ==
		 * id).findFirst(); Book book = first.get();
		 * 
		 * Stream<Book> filter = lists.stream().filter(e -> e.getId() == id);
		 * Optional<Book> first = filter.findFirst(); Book book = first.get();
		 */

		Book book = null;
		try {
//			book = lists.stream().filter(e -> e.getId() == id).findFirst().get();
			book = this.bookRepository.findById(id);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return book;
	}

	// adding a new book
	public Book adding(Book book) {
		// TODO Auto-generated method stub
//		lists.add(book);
		Book save = this.bookRepository.save(book);
		return save;
	}

	// deleting the element by id

	public void deleteById(int id) {

		try {
//			lists = lists.stream().filter(e -> e.getId() != id).collect(Collectors.toList());
			this.bookRepository.deleteById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}

		/*
		 * Book b =null; for(Book b1 :lists) { if(b1.getId()==id) { b = b1; } }
		 * lists.remove(b);
		 */
	}

	public void update(Book book, int id) {
		// TODO Auto-generated method stub

		/*
		 * Stream<Book> map = lists.stream().map(e -> {
		 * 
		 * if (e.getId() == id) { e.setAuthor(book.getAuthor());
		 * e.setTitle(book.getTitle()); } return e; });
		 * 
		 * lists = map.collect(Collectors.toList());
		 */

		/*
		 * for (Book b : lists) { if (b.getId() == id) { b.setAuthor(book.getAuthor());
		 * b.setTitle(book.getTitle()); } }
		 */

		book.setId(id);
		this.bookRepository.save(book);
	}
}
