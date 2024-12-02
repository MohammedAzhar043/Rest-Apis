package com.springboot.web.Dao;

import org.springframework.data.repository.CrudRepository;

import com.springboot.web.entities.Book;

public interface BookRepository extends CrudRepository<Book, Integer>{

	public Book findById(int id);
}
