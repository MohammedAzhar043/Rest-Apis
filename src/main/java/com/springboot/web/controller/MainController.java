package com.springboot.web.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.web.entities.Book;
import com.springboot.web.service.BookService;

//@Controller
@RestController
public class MainController {

	@Autowired
	private BookService bookService;
//	@RequestMapping(value = "/book", method = RequestMethod.GET)
//	@ResponseBody
//	public Book getBooks() {
//		Book b = new Book();
//		b.setId(1);
//		b.setTitle("java programming");
//		b.setAuthor("Mohammed Azhar");
//
//		return b;
//
////		return "This is for just testing purpose";
//	}

	// getting all the elements
	@GetMapping("/book")
	public ResponseEntity<List<Book>> getAllBooks() {

		List<Book> allBooks = this.bookService.getAllBooks();

		if (allBooks.size() <= 0) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} else {
			return ResponseEntity.of(Optional.of(allBooks));
		}

		/*
		 * allBooks.forEach(e -> { System.out.println(e); });
		 */
		/* return allBooks; */
	}

	// getting the element by using id
	@GetMapping("/book/{bookId}")
	public ResponseEntity<Book> getBookId(@PathVariable("bookId") int id) {

		Book bookById = this.bookService.getBookById(id);

		if (bookById == null) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} else {
			System.out.println(bookById);
			return ResponseEntity.of(Optional.of(bookById));
		}

	}

	// creating
	@PostMapping("/book")
	public ResponseEntity<Book> addNewBook(@RequestBody Book book) {

		Book book2 = null;

		try {
			book2 = this.bookService.adding(book);
			System.out.println(book2);
			return ResponseEntity.status(HttpStatus.CREATED).build();

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

	}

	// deleting

	@DeleteMapping("/book/{bookId}")
	public ResponseEntity<Void> deleteBookById(@PathVariable("bookId") int id) {

		try {
			this.bookService.deleteById(id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} catch (Exception e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	// updating the data
	@PutMapping("/book/{bookId}")
	public ResponseEntity<Book> updateBookByIdAndBook(@RequestBody Book book, @PathVariable("bookId") int id) {

		try {
			this.bookService.update(book, id);
			return ResponseEntity.ok().body(book);
		}
		catch (Exception e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

}
