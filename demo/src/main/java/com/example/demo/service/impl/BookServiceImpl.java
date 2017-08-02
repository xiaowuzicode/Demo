package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.dao.BookDao;
import com.example.demo.model.Book;
import com.example.demo.service.BookService;

@Service
public class BookServiceImpl implements BookService{
	@Autowired
	private BookDao bookdao;
	
	
	@Override
	public List<Book> findByReader(String reader) {
		
		return bookdao.findByReader(reader);
	}

	@Override
	public void addorEdit(Book book) {
		// TODO Auto-generated method stub

			bookdao.save(book);
	
	}

	@Override
	public List<Book> findAll() {
		// TODO Auto-generated method stub
		return bookdao.findAll();
	}

	@Override
	public void deleteBook(long id) {
		// TODO Auto-generated method stub
		bookdao.delete(id);
	}

	@Override
	public Book getById(long id) {
		// TODO Auto-generated method stub
		return bookdao.findOne(id);
	}




}
