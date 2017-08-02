package com.example.demo.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.demo.model.Book;


public interface BookService {
	List<Book> findByReader(String reader);
	void addorEdit(Book book);
	List<Book> findAll();
	void deleteBook(long id);
	Book getById(long id);
}
