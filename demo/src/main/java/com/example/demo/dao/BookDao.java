package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.Book;

public interface BookDao extends JpaRepository<Book, Long>{
	
	@Query(value="select * from book b where b.reader = ?1",nativeQuery=true)
	List<Book> findByReader(String reader);

}
